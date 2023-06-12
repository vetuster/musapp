package com.trksoft.musapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class MusappApplication {

	@Autowired
	AppHelper appHelper;
	
	
	public static void main(String[] args) {
		SpringApplication.run(MusappApplication.class, args);
	}

	
    @PostConstruct
    private void startupApplication() {
    	log.info(appHelper.getStartAppMsg());
    }
    
    @PreDestroy
    private void shutdownApplication() {
    	log.info(appHelper.getEndAppMsg());
    }
}
