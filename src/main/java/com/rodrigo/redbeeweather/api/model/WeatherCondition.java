package com.rodrigo.redbeeweather.api.model;

import java.util.Date;

public class WeatherCondition {

    private Date conditionDate;
    private float temperature;
    private String text;

    public WeatherCondition(Date conditionDate, float temperature, String text) {
        this.conditionDate = conditionDate;
        this.temperature = temperature;
        this.text = text;
    }

    public Date getConditionDate() {
        return conditionDate;
    }

    public float getTemperature() {
        return temperature;
    }

    public String getText() {
        return text;
    }
}
