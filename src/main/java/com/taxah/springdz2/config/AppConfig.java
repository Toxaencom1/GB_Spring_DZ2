package com.taxah.springdz2.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;


//не обязательный код, есть в задании
@Configuration
@ConfigurationPropertiesScan("com.taxah.springdz2.config")
public class AppConfig {
}
