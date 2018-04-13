/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.rest.upbit.controller; 

import java.util.List;

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
import com.google.gson.reflect.TypeToken;

import jk.framework.common.util.http.Api_Client;
import jk.framework.rest.upbit.entity.UpbitTickerResultEntity;
import jk.framework.rest.upbit.service.UpbitPublicRestService;

/**
 * <pre>
 * com.jkbot.bithumb.RestAPI 
 *    |_ BithumbRestAPI.java
 * 
 * </pre>
 * @date : 2018. 1. 20. 오후 8:54:04
 * @version : 
 * @author : jongkyu
 * 
 * https://crix-api-endpoint.upbit.com/v1/crix/candles/기간타입/기간?code=CRIX.UPBIT.마켓-암호화폐기호&count=시세데이터수&to=최종시세데이터일시
 * 
 */
// /v1/crix/candles/minutes/1?code=CRIX.UPBIT.KRW-

@ResponseBody
@RequestMapping("/upbit/publicapi")
@Controller
public class UpbitPublicRestController {
	private static final Logger logger = LoggerFactory.getLogger(UpbitPublicRestController.class);
    
	@Value("${upbit.apiUrl}")
    private String apiUrl ;
	
	@Autowired
	private UpbitPublicRestService upbitPublicService;
	 
	@RequestMapping(value = "/ticker/{currency}", method = RequestMethod.GET)
	public List<UpbitTickerResultEntity> getTicker(@PathVariable String currency) {
		List<UpbitTickerResultEntity> resultEntity = null;
		upbitPublicService.getTicker(apiUrl);
		return resultEntity;
	}
}
