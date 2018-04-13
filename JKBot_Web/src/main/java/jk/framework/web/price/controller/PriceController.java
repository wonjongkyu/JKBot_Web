package jk.framework.web.price.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jk.framework.rest.binance.entity.BinanceTickerResultEntity;
import jk.framework.rest.binance.service.BinanacePublicRestService;
import jk.framework.rest.upbit.entity.UpbitTickerResultEntity;
import jk.framework.rest.upbit.service.UpbitPublicRestService;
import jk.framework.web.price.entity.PriceCompareEntity;

/**
 * Handles requests for the application home page. 
 */
@RequestMapping("/price")
@Controller
public class PriceController {
	
	private static final Logger logger = LoggerFactory.getLogger(PriceController.class);
	
	// bithumb apiKey
    @Value("${bithumb.apiConnectKey}")
    private String apiConnectKey ;
    @Value("${bithumb.apiSecretKey}")
    private String apiSecretKey;
    
    @Value("${binance.apiUrl}")
    private String binanceApiUrl ;
    @Value("${upbit.apiUrl}")
    private String upbitApiUrl ;
	
    @Autowired
    BinanacePublicRestService binancePublicService;

    @Autowired
    UpbitPublicRestService upbitPublicService;
	
    /**
     * <pre>
     * 1. 개요 : 김프 계산 컨트롤러
     * 2. 처리내용 : 
     * 	2.1 업비트의 코인들을 가져온다. (KRW)
     * 	2.2 바이낸스의 코인들을 가져온다. (USDT 먼저..)
     * 	2.3 두 개를 merge 하는 작업
     * </pre>
     * @Method Name : priceCompare
     * @date : 2018. 4. 13.
     * @author : Hyundai
     * @history : 
     *	-----------------------------------------------------------------------
     *	변경일				작성자						변경내용  
     *	----------- ------------------- ---------------------------------------
     *	2018. 4. 13.		Hyundai				최초 작성 
     *	-----------------------------------------------------------------------
     * 
     * @param model
     * @return
     */ 	
    @RequestMapping(value = "/compare", method = RequestMethod.GET)
	public ModelAndView priceCompare(Model model) {
		ModelAndView mav = new ModelAndView();

		// 가져올 코인 코드
		HashSet<String> coinList = new HashSet<String>();
		coinList.add("BTC");
		coinList.add("ETH");
		coinList.add("BCC");
		coinList.add("NEO");
		coinList.add("LTC");
		coinList.add("QTUM");
		
		// 바이낸스 모든 코인 시세 가져오기
		/*
			BTCUSDT
			ETHUSDT
			// BNBUSDT
			BCCUSDT
			NEOUSDT
			LTCUSDT
			QTUMUSDT
		 */
		List<BinanceTickerResultEntity> binanceResultEntity = binancePublicService.getTicker(binanceApiUrl,coinList);
		System.out.println(binanceResultEntity);
		List<UpbitTickerResultEntity> upBitResultEntity = upbitPublicService.getTicker(upbitApiUrl, coinList);
		System.out.println(upBitResultEntity);
		
		List<PriceCompareEntity> resultEntity = new ArrayList<PriceCompareEntity>();
		
		
		
		
		mav.setViewName("/price/priceCompare");
		return mav;
    }
	
}
