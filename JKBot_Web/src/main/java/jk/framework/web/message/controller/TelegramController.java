package jk.framework.web.message.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import jk.framework.common.util.etc.SessionService;
import jk.framework.web.admin.entity.PriceCompareEntity;
import jk.framework.web.message.entity.TelegramMessageEntity;

/**
 * Handles requests for the application home page. 
 */
@RequestMapping("/telegram")
@Controller
public class TelegramController {
			
	@Value("${telegram.botToken}")
	private String telegramBotToken ;
	
	@Value("${telegram.chatId}")
	private String chatId ;
			
    @Autowired
    SessionService sessionService;
    
	private static final Logger logger = LoggerFactory.getLogger(TelegramController.class);
   		

	@RequestMapping(value = "/sendMessage/{type}",  method = {RequestMethod.GET, RequestMethod.POST})
	public void sendMessage(@RequestBody String result, @PathVariable String type) throws Exception{
		System.out.println("텔레그램 메시지 발송");
		System.out.println(result);
		Gson gson = new Gson();
		List<PriceCompareEntity> entityList = gson.fromJson(result, new TypeToken<List<PriceCompareEntity>>(){}.getType()); 
		
		String site1 = "";
		String site2 = "";
		if(type.equals("1") || type.equals("2")) {
			site1 = "업비트";
			site2 = "바이낸스";
		}else if(type.equals("3") || type.equals("4")) {
			site1 = "업비트";
			site2 = "빗썸";
		}else if(type.equals("5") || type.equals("6")) {
			site1 = "빗썸";
			site2 = "바이낸스";
		}
		
		TelegramMessageEntity messageEntity = new TelegramMessageEntity();
		StringBuffer message = new StringBuffer();
		int index = 1;
		if(type.equals("1") || type.equals("3") || type.equals("5")) {
			for (PriceCompareEntity entity : entityList) {
				if(index == 1) {
					messageEntity.setMaxCoinSymbol(entity.getCoinSymbol2());
					messageEntity.setMaxPriceKrwA(entity.getBinanceSellPrice());
					messageEntity.setMaxPriceKrwB(entity.getUpbitBuyPrice());
					messageEntity.setMaxPriceGapPercent(String.valueOf(entity.getPriceGapPercent2()));
				}
				if(index == entityList.size()) {
					messageEntity.setMinCoinSymbol(entity.getCoinSymbol());
					messageEntity.setMinPriceKrwA(entity.getPriceKrwA());
					messageEntity.setMinPriceKrwB(entity.getPriceKrwB());
					messageEntity.setMinPriceGapPercent(String.valueOf(entity.getPriceGapPercent2()));
				}
				index++;
				logger.debug("sendMessage.getPriceGapPercent::{}", entity.getPriceGapPercent());
			}
			
			if(messageEntity.getMaxCoinSymbol() != null) {
				message.append("[" + site2 + "] 구매 추천 <b>").append(messageEntity.getMaxCoinSymbol()).append("</b>]\r\n");
				message.append("[김프] ").append(messageEntity.getMaxCoinSymbol()).append(" (" + messageEntity.getMaxPriceGapPercent() + "%)").append("\r\n");
				message.append(" " + site2 + " : ").append(messageEntity.getMaxPriceKrwA() + "원").append("\r\n");
				message.append(" " + site1 + " :  ").append(messageEntity.getMaxPriceKrwB() + "원").append("\r\n");
			}
			
		}else if(type.equals("2") || type.equals("4") || type.equals("6")) {
			for (PriceCompareEntity entity : entityList) {
				if(index == 1) {
	                messageEntity.setMaxCoinSymbol(entity.getCoinSymbol());
	                messageEntity.setMaxPriceKrwA(entity.getBinanceBuyPrice());
	                messageEntity.setMaxPriceKrwB(entity.getUpbitSellPrice());
	                messageEntity.setMaxPriceGapPercent(String.valueOf(entity.getPriceGapPercent()));
				}
				if(index == entityList.size()) {
					messageEntity.setMinCoinSymbol(entity.getCoinSymbol());
					messageEntity.setMinPriceKrwA(entity.getPriceKrwA());
					messageEntity.setMinPriceKrwB(entity.getPriceKrwB());
					messageEntity.setMinPriceGapPercent(String.valueOf(entity.getPriceGapPercent()));
				}
				index++;
				logger.debug("sendMessage.getPriceGapPercent::{}", entity.getPriceGapPercent());
			}
			if(messageEntity.getMaxCoinSymbol() != null) {
				message.append("[" + site1 + "] 구매 추천 <b>").append(messageEntity.getMinCoinSymbol()).append("</b>]\r\n");
				message.append("[김프] ").append(messageEntity.getMinCoinSymbol()).append(" (" + messageEntity.getMinPriceGapPercent() + "%)").append("\r\n");
				message.append(" " + site1 + " :  ").append(messageEntity.getMaxPriceKrwB() + "원").append("\r\n");
				message.append(" " + site2 + " : ").append(messageEntity.getMaxPriceKrwA() + "원").append("\r\n");
			}
		}
		
	
		/*
		message.append("[업비트 구매 추천 <b>").append(messageEntity.getMinCoinSymbol()).append("</b>]\r\n");
		message.append("2018.04.27 금요일 오후 2:20").append("\r\n");
		message.append("[최고] ").append(messageEntity.getMaxCoinSymbol()).append(" (" + messageEntity.getMaxPriceGapPercent() + "%)").append("\r\n");
		message.append(" 바이낸스 : ").append(messageEntity.getMaxPriceKrwB() + "원").append("\r\n");
		message.append(" 업비트    :  ").append(messageEntity.getMaxPriceKrwA() + "원").append("\r\n");
		message.append("[최저] ").append(messageEntity.getMinCoinSymbol()).append(" (<b>" + messageEntity.getMinPriceGapPercent() + "</b>%)").append("\r\n");
		message.append(" 바이낸스 : ").append(messageEntity.getMinPriceKrwB() + "원").append("\r\n");
		message.append(" 업비트    :  ").append(messageEntity.getMinPriceKrwA() + "원").append("\r\n");
		*/
		if(messageEntity.getMaxCoinSymbol() != null) {
			TelegramBot bot = new TelegramBot(telegramBotToken); 
			SendMessage request = new SendMessage(chatId, message.toString()) 
										.parseMode(ParseMode.HTML) 
										.disableWebPagePreview(true) 
										.disableNotification(false);
			SendResponse sendResponse = bot.execute(request); 
			
			boolean ok = sendResponse.isOk(); 
			Message message2 = sendResponse.message();
		}

		
		/*logger.info("message:::{}", model.getMenuPath());
		String RequestUrl = authorizeApiUrl + "?client_id="+clientId+"&redirect_uri="+redirectUrl+"&response_type=code";
		sessionService.setAttribute("kakaoMessage", model.getMenuPath());*/
	}
	
	
	@RequestMapping(value = "/sendMessage2",  method = {RequestMethod.GET, RequestMethod.POST})
	public void sendMessage2(@RequestBody String result) throws Exception{
		System.out.println("텔레그램 메시지 발송");
		System.out.println(result);
		Gson gson = new Gson();
		List<PriceCompareEntity> entityList = gson.fromJson(result, new TypeToken<List<PriceCompareEntity>>(){}.getType()); 
		
		int index = 1;
		TelegramMessageEntity messageEntity = new TelegramMessageEntity();
		for (PriceCompareEntity entity : entityList) {
			if(index == 1) {
                messageEntity.setMaxCoinSymbol(entity.getCoinSymbol());
                messageEntity.setMaxPriceKrwA(entity.getBinanceBuyPrice());
                messageEntity.setMaxPriceKrwB(entity.getUpbitSellPrice());
                messageEntity.setMaxPriceGapPercent(String.valueOf(entity.getPriceGapPercent()));
			}
			if(index == entityList.size()) {
				messageEntity.setMinCoinSymbol(entity.getCoinSymbol());
				messageEntity.setMinPriceKrwA(entity.getPriceKrwA());
				messageEntity.setMinPriceKrwB(entity.getPriceKrwB());
				messageEntity.setMinPriceGapPercent(String.valueOf(entity.getPriceGapPercent()));
			}
			index++;
			logger.debug("sendMessage.getPriceGapPercent::{}", entity.getPriceGapPercent());
		}
		StringBuffer message = new StringBuffer();

		if(messageEntity.getMaxCoinSymbol() != null) {
			message.append("[업비트 구매 추천 <b>").append(messageEntity.getMinCoinSymbol()).append("</b>]\r\n");
			message.append("[김프] ").append(messageEntity.getMinCoinSymbol()).append(" (" + messageEntity.getMinPriceGapPercent() + "%)").append("\r\n");
			message.append(" 업비트    :  ").append(messageEntity.getMaxPriceKrwB() + "원").append("\r\n");
			message.append(" 바이낸스 : ").append(messageEntity.getMaxPriceKrwA() + "원").append("\r\n");
		
			TelegramBot bot = new TelegramBot(telegramBotToken); 
			SendMessage request = new SendMessage(chatId, message.toString()) 
										.parseMode(ParseMode.HTML) 
										.disableWebPagePreview(true) 
										.disableNotification(false);
			SendResponse sendResponse = bot.execute(request); 
			
			boolean ok = sendResponse.isOk(); 
			Message message2 = sendResponse.message();
		}

		
		/*logger.info("message:::{}", model.getMenuPath());
		String RequestUrl = authorizeApiUrl + "?client_id="+clientId+"&redirect_uri="+redirectUrl+"&response_type=code";
		sessionService.setAttribute("kakaoMessage", model.getMenuPath());*/
	}
 
}
