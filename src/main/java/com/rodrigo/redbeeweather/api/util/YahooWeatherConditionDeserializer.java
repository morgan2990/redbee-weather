package com.rodrigo.redbeeweather.api.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.rodrigo.redbeeweather.api.model.WeatherCondition;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YahooWeatherConditionDeserializer extends StdDeserializer<WeatherCondition> {

    public YahooWeatherConditionDeserializer() {
        this(null);
    }

    public YahooWeatherConditionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public WeatherCondition deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        Date date;
        try {
            date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a z"	).parse((node.get("query").get("results").get("channel").get("item").get("condition").get("date")).asText());
        } catch (ParseException e) {
            throw new RuntimeException(e);

        }
        String text = (node.get("query").get("results").get("channel").get("item").get("condition").get("text").asText());
        Float temp = Float.valueOf((node.get("query").get("results").get("channel").get("item").get("condition").get("temp")).asText());

        return new WeatherCondition(date, temp, text);
    }
}
