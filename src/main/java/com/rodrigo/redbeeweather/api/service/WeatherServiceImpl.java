package com.rodrigo.redbeeweather.api.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.rodrigo.redbeeweather.api.model.Board;
import com.rodrigo.redbeeweather.api.model.Location;
import com.rodrigo.redbeeweather.api.model.WeatherCondition;
import com.rodrigo.redbeeweather.api.util.WeatherConditionDeserializer;


import com.rodrigo.redbeeweather.api.util.WeatherConditionSerializer;
import com.rodrigo.redbeeweather.api.util.YahooWeatherConditionDeserializer;
import io.socket.client.IO;
import io.socket.client.Socket;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;


import java.io.*;
import java.net.*;
import java.util.Set;


@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private LocationService locationService;

    @Autowired
    private Jedis jedis;

    private Socket socket;

    private ObjectMapper objectMapper;

    private ObjectMapper yahooMapper;

    public WeatherServiceImpl() {

        try {
            socket = IO.socket("http://localhost:9092/");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        socket.connect();
        objectMapper = new ObjectMapper();
        yahooMapper = new ObjectMapper();
        SimpleModule objectModule = new SimpleModule();
        SimpleModule yahooModule = new SimpleModule();
        objectModule.addDeserializer(WeatherCondition.class, new WeatherConditionDeserializer());
        objectModule.addSerializer(WeatherCondition.class, new WeatherConditionSerializer(WeatherCondition.class));
        yahooModule.addDeserializer(WeatherCondition.class, new YahooWeatherConditionDeserializer());
        objectMapper.registerModule(objectModule);
        yahooMapper.registerModule(yahooModule);
    }


    private InputStream executeQuery(String query) throws IOException {
        String baseUrl = "https://query.yahooapis.com/v1/public/yql?q=";
        String fullUrlStr = baseUrl + URLEncoder.encode(query, "UTF-8") + "&format=json";
        URL fullUrl = new URL(fullUrlStr);
        return fullUrl.openStream();
    }

    @Override
    public void populateWeatherCondition(Long requestedBy, Location location) throws IOException {


        WeatherCondition weatherCondition = retrieveWeatherCondition(requestedBy, location);

        location.setWeatherCondition(weatherCondition);

        updateCachedWeatherCondition(location.getId(), requestedBy, serializeCondition(weatherCondition));


    }

    @Override
    public void populateWeatherCondition(Location location) throws IOException {

        WeatherCondition weatherCondition = retrieveWeatherCondition(location);

        location.setWeatherCondition(weatherCondition);

    }

    private WeatherCondition retrieveWeatherCondition(Location location) {
        return retrieveWeatherConditionByQuery(buildRedisKeyForLocation(location.getId()), location);
    }

    private WeatherCondition retrieveWeatherCondition(Long requestedBy, Location location) {
        return retrieveWeatherConditionByQuery(buildRedisKeyForBoardAndLocation(requestedBy, location.getId()), location);
    }

    private WeatherCondition retrieveWeatherConditionByQuery(String query, Location location) {
        try {
            String serializedCondition = jedis.get(query);
            WeatherCondition condition;
            if (serializedCondition != null) {
                condition = objectMapper.readValue(serializedCondition, WeatherCondition.class);
            } else {
                condition = retrieveWeatherConditionFromService(location);
            }
            return condition;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildRedisKeyForLocation(Long locationId) {
        return "wc:loc" + locationId.toString();
    }

    private String buildRedisKeyForBoardAndLocation(Long boardId, Long locationId) {
        return "wc:b" + boardId.toString() + "l" + locationId.toString();
    }

    private WeatherCondition retrieveWeatherConditionFromService(Location location) throws IOException {
        String query = "select item.condition from weather.forecast where woeid = %s";
        query = String.format(query, location.getWoeId());
        InputStream is = executeQuery(query);
        WeatherCondition condition = yahooMapper.readValue(is, WeatherCondition.class);
        is.close();
        condition.setWoeId(location.getWoeId());
        return condition;
    }

    @Transactional
    @Scheduled(cron = "0 0/1 * 1/1 * *\n")
    public void updateWheaterConditions() {
        Set<Location> locationSet = locationService.getAllLocations();

        for (Location eachLocation : locationSet) {
            WeatherCondition condition = null;
            String conditionString;
            try {
                condition = retrieveWeatherConditionFromService(eachLocation);
                conditionString = serializeCondition(condition);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            updateGenericCachedWeatherCondition(eachLocation.getId(), conditionString);
            for (Board eachBoard : eachLocation.getBoards()) {

                exportWeather(eachLocation.getId(), eachBoard.getId(), condition);
            }

        }
    }

    private void exportWeather(Long locationId, Long boardId, WeatherCondition weatherCondition) {

        String condition = serializeCondition(weatherCondition);

        updateCachedWeatherCondition(locationId, boardId, condition);

        socket.emit("weatherCondition", "{boardId:" + boardId + ",locationId:" + locationId + ",condition" + condition + "}");

    }

    private String serializeCondition(WeatherCondition weatherCondition) {
        String condition;

        try {
            condition = objectMapper.writeValueAsString(weatherCondition);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return condition;
    }

    private void updateCachedWeatherCondition(Long locationId, Long boardId, String condition) {

        updateCachedWeatherConditionForBoard(locationId, boardId, condition);


    }

    private void updateCachedWeatherConditionForBoard(Long locationId, Long boardId, String condition) {
        String keyForBoardAndLocation = "wc:b" + boardId.toString() + "l" + locationId.toString();
        jedis.set(keyForBoardAndLocation, condition);
    }

    private void updateGenericCachedWeatherCondition(Long locationId, String condition) {
        String keyForLocation = "wc:loc" + locationId.toString();
        jedis.set(keyForLocation, condition);
    }


}