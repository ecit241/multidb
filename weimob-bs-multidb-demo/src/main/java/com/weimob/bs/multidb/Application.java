package com.weimob.bs.multidb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Adam on 2015/12/31.
 */
@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
public class Application {
    final static Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.info("start success");
    }
}
