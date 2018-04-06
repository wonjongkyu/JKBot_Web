/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.common.util.controller; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class CommonController {
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Value("${bithumb.apiUrl}")
    private String apiUrl ;
    @Value("${bithumb.apiConnectKey}")
    private String apiConnectKey ;
    @Value("${bithumb.apiSecretKey}")
    private String apiSecretKey;
	 
}
