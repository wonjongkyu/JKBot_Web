package jk.framework.web.history.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jk.framework.common.util.etc.SessionService;
import jk.framework.web.history.entity.TradeHistoryEntity;
import jk.framework.web.history.service.HistroyService;

/**
 * Handles requests for the application home page. 
 */
@RequestMapping("/history")
@Controller
public class HistoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(HistoryController.class);
 
	@Autowired
	HistroyService historyService;
	
    @Autowired
    SessionService sessionService;
    
    @ResponseBody
    @RequestMapping(value = "/saveTradeHistory",  method = {RequestMethod.GET, RequestMethod.POST})
 	public void saveTradeHistory(@RequestBody String result) throws Exception{
 		
 		Gson gson = new Gson();
 		TradeHistoryEntity entityList = gson.fromJson(result, TradeHistoryEntity.class); 
 		historyService.insertTradeHistory(entityList);
 		 
 	}
}
