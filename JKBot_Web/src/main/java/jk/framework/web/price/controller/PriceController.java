package jk.framework.web.price.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	
    @RequestMapping(value = "/compare", method = RequestMethod.GET)
	public ModelAndView priceCompare(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/price/priceCompare");
		return mav;
    }
	
}
