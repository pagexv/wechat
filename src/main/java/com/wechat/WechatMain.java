package com.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties
@EnableAutoConfiguration
@EntityScan(basePackages = "controller.wechat.*")
@SpringBootApplication

public class WechatMain {


    public static void main(String [] args){
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(WechatMain.class, args);
    }
}
