package jk.framework.web.message.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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
	 
	@RequestMapping(value = "/sendMessage",  method = {RequestMethod.GET, RequestMethod.POST})
	public void sendMessage(@RequestBody String result) throws Exception{
		
		Gson gson = new Gson();
		List<PriceCompareEntity> entityList = gson.fromJson(result, new TypeToken<List<PriceCompareEntity>>(){}.getType()); 
		
		int index = 1;
		TelegramMessageEntity messageEntity = new TelegramMessageEntity();
		for (PriceCompareEntity entity : entityList) {
			if(index == 1) {
				messageEntity.setMaxCoinSymbol(entity.getCoinSymbol());
				messageEntity.setMaxPriceKrwA(entity.getPriceKrwA());
				messageEntity.setMaxPriceKrwB(entity.getPriceKrwB());
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
		message.append("[업비트 구매 추천 <b>").append(messageEntity.getMinCoinSymbol()).append("</b>]\r\n");
		message.append("2018.04.27 금요일 오후 2:20").append("\r\n");
		message.append("[최고] ").append(messageEntity.getMaxCoinSymbol()).append(" (" + messageEntity.getMaxPriceGapPercent() + "%)").append("\r\n");
		message.append(" 바이낸스 : ").append(messageEntity.getMaxPriceKrwB() + "원").append("\r\n");
		message.append(" 업비트    :  ").append(messageEntity.getMaxPriceKrwA() + "원").append("\r\n");
		message.append("[최저] ").append(messageEntity.getMinCoinSymbol()).append(" (<b>" + messageEntity.getMinPriceGapPercent() + "</b>%)").append("\r\n");
		message.append(" 바이낸스 : ").append(messageEntity.getMinPriceKrwB() + "원").append("\r\n");
		message.append(" 업비트    :  ").append(messageEntity.getMinPriceKrwA() + "원").append("\r\n");
		
		TelegramBot bot = new TelegramBot(telegramBotToken); 
		SendMessage request = new SendMessage(chatId, message.toString()) 
									.parseMode(ParseMode.HTML) 
									.disableWebPagePreview(true) 
									.disableNotification(false);
		SendResponse sendResponse = bot.execute(request); 
		
		boolean ok = sendResponse.isOk(); 
		Message message2 = sendResponse.message();

		
		/*logger.info("message:::{}", model.getMenuPath());
		String RequestUrl = authorizeApiUrl + "?client_id="+clientId+"&redirect_uri="+redirectUrl+"&response_type=code";
		sessionService.setAttribute("kakaoMessage", model.getMenuPath());*/
	}
 
}
