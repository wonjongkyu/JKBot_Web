/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.rest.bithumb.controller; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jk.framework.common.util.http.Api_Client;
import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity;
import jk.framework.rest.bithumb.service.BithumbPublicRestService;


/**
 * <pre>
 * com.jkbot.bithumb.RestAPI 
 *    |_ BithumbRestAPI.java
 * 
 * </pre>
 * @date : 2018. 1. 20. 오후 8:54:04
 * @version : 
 * @author : jongkyu
 */
@ResponseBody
@RequestMapping("/api/bithumb/publicapi")
@Controller
public class BithumbPublicRestController{
	private static final Logger logger = LoggerFactory.getLogger(BithumbPublicRestController.class);
    
    @Value("${bithumb.apiUrl}")
    private String apiUrl ;
    @Value("${bithumb.apiConnectKey}")
    private String apiConnectKey ;
    @Value("${bithumb.apiSecretKey}")
    private String apiSecretKey;
    
    
	@Autowired
	BithumbPublicRestService publicService;
	 
	@RequestMapping(value = "/ticker/{currency}", method = RequestMethod.GET)
	public BithumbTickerResultEntity getTicker(@PathVariable String currency) {
		BithumbTickerResultEntity entity = null;
		Api_Client api = new Api_Client(apiUrl, apiConnectKey, apiSecretKey);
		
		try {
		    String result = api.callApi("/public/ticker/"+currency, null);
		    System.out.println(result);
		    
		    Gson gson = new Gson();
		    entity = gson.fromJson(result, BithumbTickerResultEntity.class);
		    // DB에 시세 저장
		    // publicService.save(entity);
			System.out.println(entity.getData().getBTC().getClosing_price());
			
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return entity;
	}
	
}
