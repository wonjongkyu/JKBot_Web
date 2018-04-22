package jk.framework.web.price.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jk.framework.common.util.etc.SessionService;
import jk.framework.rest.binance.service.BinanacePublicRestService;
import jk.framework.rest.upbit.service.UpbitPublicRestService;
import jk.framework.web.admin.controller.AdminController;
import jk.framework.web.admin.entity.ExchangeRateEntity;
import jk.framework.web.admin.entity.PriceCompareEntity;
import jk.framework.web.admin.service.AdminService;
import jk.framework.web.price.service.PriceService;

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
    
    @Autowired
    PriceService priceService;
    
    @Autowired
    AdminService adminService;
    
    @Autowired
    SessionService sessionService;
	
    
    /**
     * <pre>
     * 1. 개요 : 김프 계산 페이지 연결
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : compare
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
    @RequestMapping(value = "/compareTest", method = RequestMethod.GET)
	public ModelAndView compareTest(Model model) {
		ModelAndView mav = new ModelAndView();
		// 환율 가져오기
		AdminController rate = new AdminController();
		rate.getExchangeRate(model);		
		// getPriceExchangeRate(model);
		mav.setViewName("/price/priceCompareTest");
		return mav;
    }
    
    /**
     * <pre>
     * 1. 개요 : 김프 계산 페이지 연결
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : compare
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
	public ModelAndView compare(Model model) {
		ModelAndView mav = new ModelAndView();
		// 환율 가져오기
		getExchangeRate(model);		
		// getPriceExchangeRate(model);
		mav.setViewName("/price/priceCompare");
		return mav;
    }
    
    
    /**
     * <pre>
     * 1. 개요 : 김프 계산 컨트롤러
     * 2. 처리내용 : 
     * 	2.1 업비트의 코인들을 가져온다. (KRW)
     * 	2.2 바이낸스의 코인들을 가져온다. (USDT 먼저..)
     * 	2.3 두 개를 merge 하는 작업
     * </pre>
     * @Method Name : priceCompare
     * @date : 2018. 4. 16.
     * @author : Hyundai
     * @history : 
     *	-----------------------------------------------------------------------
     *	변경일				작성자						변경내용  
     *	----------- ------------------- ---------------------------------------
     *	2018. 4. 16.		Hyundai				최초 작성 
     *	-----------------------------------------------------------------------
     * 
     * @param model
     * @return
     */ 	
    @ResponseBody
    @RequestMapping(value = "/priceCompare/{symbolType}", method = RequestMethod.GET)
   	public List<PriceCompareEntity> priceCompare(Model model, @PathVariable String symbolType) {
    	List<PriceCompareEntity> result = new ArrayList<PriceCompareEntity>();
    	result = priceService.selectAllCoinPrice(symbolType);
    	return result;
	}
    
    @ResponseBody
    @RequestMapping(value = "/getExchangeRate", method = RequestMethod.GET)
	public List<ExchangeRateEntity> getExchangeRate(Model model) {
		// 환율
    	List<ExchangeRateEntity>  result = adminService.getExchangeRate();
    	
		if(result != null) {
            Double exchangeRate = 0D;	// 환율
    	    for (ExchangeRateEntity exchangeRateEntity : result) {
    	    	exchangeRate = Double.parseDouble(exchangeRateEntity.getRate());
    		}
    	    
			sessionService.setAttribute("exchangeRate", String.valueOf(exchangeRate) );
		}
		return result;
    }
}
