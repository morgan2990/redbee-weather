package com.rodrigo.redbeeweather.api.main;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import redis.clients.jedis.Jedis;


@Configuration

public class RedisConfiguration {

   @Bean
    public Jedis jedis(){
       return new Jedis();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

}
