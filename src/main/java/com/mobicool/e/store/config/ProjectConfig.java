package com.mobicool.e.store.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMapExtensionsKt;

@Configuration
public class ProjectConfig {
    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }

}