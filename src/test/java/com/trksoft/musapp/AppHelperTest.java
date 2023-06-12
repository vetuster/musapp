package com.trksoft.musapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class AppHelperTest {
	
	@Autowired
	AppHelper appHelper;

	@Test
	void getStartAppMsgTest() {
		log.debug("==>");
		
		var startAppMsg = appHelper.getStartAppMsg();
		log.debug("{}", startAppMsg);
		assertNotNull(startAppMsg);
		
		var endAppMsg = appHelper.getEndAppMsg();
		log.debug("{}", endAppMsg);
		assertNotNull(endAppMsg);
		
		log.debug("<==");
	}

}
