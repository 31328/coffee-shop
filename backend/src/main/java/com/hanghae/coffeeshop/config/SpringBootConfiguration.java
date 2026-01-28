package com.hanghae.coffeeshop.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.hanghae.coffeeshop")
public class SpringBootConfiguration implements WebMvcConfigurer {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper returnValue = new ModelMapper();
        returnValue.getConfiguration().setAmbiguityIgnored(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PACKAGE_PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return returnValue;
    }
}
