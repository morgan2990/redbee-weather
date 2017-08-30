package com.rodrigo.redbeeweather.api.main;

import com.rodrigo.redbeeweather.api.repository.*;
import com.rodrigo.redbeeweather.api.service.BoardService;
import com.rodrigo.redbeeweather.api.service.BoardServiceImpl;
import com.rodrigo.redbeeweather.api.service.LocationService;
import com.rodrigo.redbeeweather.api.service.LocationServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.rodrigo.redbeeweather.api")
@EntityScan("com.rodrigo.redbeeweather.api.model")
@EnableAutoConfiguration
public class Application {


    public Application (){

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
