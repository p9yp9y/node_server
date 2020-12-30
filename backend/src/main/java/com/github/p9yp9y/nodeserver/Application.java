package com.github.p9yp9y.nodeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.github.dobrosi.autorest.AutoRestConfiguration;

@ServletComponentScan
@SpringBootApplication
@ImportAutoConfiguration(classes = {AutoRestConfiguration.class})
public class Application  {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}