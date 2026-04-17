package com.yuncodelab.sku;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yuncodelab.sku.infrastructure.mapper")
public class SkuEngineJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkuEngineJavaApplication.class, args);
    }
}
