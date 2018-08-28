/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.rest.binance.controller; 

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jk.framework.common.util.http.Api_Client;
import jk.framework.rest.binance.entity.BinanceTickerResultEntity;
import jk.framework.rest.binance.service.BinanacePublicRestService;

 
/**
 * <pre>
 * jk.framework.rest.binance.controller 
 *    |_ BinancePublicRestController.java
 * 
 * </pre>
 * @date : 2018. 4. 9. 오후 1:11:21
 * @version : 
 * @author : Hyundai
 */
@ResponseBody
@RequestMapping("/binance/api/v1")
@Controller
public class BinancePublicRestController{
	private static final Logger logger = LoggerFactory.getLogger(BinancePublicRestController.class);
    
    @Value("${binance.apiUrl}")
    private String apiUrl ;
	
    @Autowired
    BinanacePublicRestService binancePublicService;

	 
	/**
	 * <pre>
	 * 1. 개요 : 바이낸스 모든 코인의 시세를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getTicker
	 * @date : 2018. 4. 13.
	 * @author : Hyundai
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 13.		Hyundai				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */ 	
	@RequestMapping(value = "/ticker/24hr", method = RequestMethod.GET)
	public List<BinanceTickerResultEntity> getTicker() {
		// 모든 코인 찾기
		List<BinanceTickerResultEntity> resultEntity = binancePublicService.getAllTicker(apiUrl);
		return resultEntity;
	}
	
}
