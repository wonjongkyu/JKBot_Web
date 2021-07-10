/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.web.admin.entity; 

import jk.framework.rest.common.entity.RestCommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class PriceCompareCommonAskBidEntity extends RestCommonVO{
	 
	private String coinSymbol;				// 코인명
	private String coinSymbol2;				// 코인명2
	private String binanceBuyPrice;			// 바이낸스 매수 가격(Bid)
	private String binanceBuySatosiPrice;	// 바이낸스 매수 가격(Bid)
	private String binanceSellPrice;		// 바이낸스 매도 가격(Ask)
	private String binanceSellSatosiPrice;		// 바이낸스 매도 가격(Ask)
	private String upbitBuyPrice;			// 업비트 매수 가격(Bid)
	private String upbitSellPrice;			// 업비트 매도 가격(Bid)

	private String coinPriceWeightA = "0";	// 코인 변동폭
	private String coinPriceWeightB = "0";	// 코인 변동폭
	private String priceGapKrw;				// 원화 가격 차이
	private String priceGapKrw2;			// 원화 가격 차이
	private double priceGapPercent = 0D;	// 김프 (%)
	private double priceGapPercent2 = 0D;	// 김프 (%)
	
	private String PriceUsdtB;
	private String PriceKrwB;
	private String buyRecommend;			// 구매 추천
	
	public PriceCompareCommonAskBidEntity() {
		
	}
	public PriceCompareCommonAskBidEntity(PriceCompareCommonAskBidEntity f) {
		this.coinSymbol 		= f.coinSymbol;
		this.coinSymbol2 		= f.coinSymbol2;		// 코인명2
		this.binanceBuyPrice 	= f.binanceBuyPrice;	// 바이낸스 매수 가격(Bid)
		this.binanceSellPrice 	= f.binanceSellPrice;	// 바이낸스 매도 가격(Ask)
		this.upbitBuyPrice 		= f.upbitBuyPrice;		// 업비트 매수 가격(Bid)
		this.upbitSellPrice 	= f.upbitSellPrice;		// 업비트 매도 가격(Bid)
		this.coinPriceWeightA 	= f.coinPriceWeightA;	// 코인 변동폭
		this.coinPriceWeightB 	= f.coinPriceWeightB;	// 코인 변동폭
		this.priceGapKrw		= f.priceGapKrw;		// 원화 가격 차이
		this.priceGapKrw2		= f.priceGapKrw2;		// 원화 가격 차이
		this.priceGapPercent	= f.priceGapPercent;	// 김프 (%)
		this.priceGapPercent2 	= f.priceGapPercent2;	// 김프 (%)
		this.PriceUsdtB			= f.PriceUsdtB;	
		this.PriceKrwB			= f.PriceKrwB;
		this.binanceBuySatosiPrice = f.binanceBuySatosiPrice;
		this.binanceSellSatosiPrice = f.binanceSellSatosiPrice;
		this.buyRecommend = f.buyRecommend;
	}
}
