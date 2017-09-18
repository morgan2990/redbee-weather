package com.rodrigo.redbeeweather.api.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.rodrigo.redbeeweather.api.model.WeatherCondition;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class WeatherConditionSerializer extends StdSerializer<WeatherCondition> {
    public WeatherConditionSerializer(Class<WeatherCondition> t) {
        super(t);
    }

    @Override
    public void serialize(WeatherCondition weatherCondition, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("date", new SimpleDateFormat("EEE, d MMM yyyy HH:mm a z").format(weatherCondition.getConditionDate()));
        jsonGenerator.writeNumberField("temp", weatherCondition.getTemperature());
        jsonGenerator.writeStringField("text", weatherCondition.getText());
        jsonGenerator.writeStringField("woeId", weatherCondition.getWoeId());
        jsonGenerator.writeEndObject();
    }
}
