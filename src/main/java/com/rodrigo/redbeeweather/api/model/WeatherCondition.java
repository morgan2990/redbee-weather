package com.rodrigo.redbeeweather.api.model;

import java.util.Date;

public class WeatherCondition {

    private Date conditionDate;
    private float temperature;
    private String text;
    private String woeId;

    public WeatherCondition(Date conditionDate, float temperature, String text) {
        this.conditionDate = conditionDate;
        this.temperature = temperature;
        this.text = text;
    }

    public String getWoeId() {
        return woeId;
    }

    public void setWoeId(String woeId) {
        this.woeId = woeId;
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
