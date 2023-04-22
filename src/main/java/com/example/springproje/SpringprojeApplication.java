package com.example.springproje;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.example.springproje.mapper")
@EnableJpaAuditing
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class SpringprojeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringprojeApplication.class, args);
    }

}
