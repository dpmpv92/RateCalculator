package com.dpmpv92.ratecalculator;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@ComponentScan(basePackages = {"com.dpmpv92.ratecalculator"})
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

}
