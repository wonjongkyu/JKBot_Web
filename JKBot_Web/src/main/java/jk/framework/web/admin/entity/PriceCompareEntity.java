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
public class PriceCompareEntity extends RestCommonVO{
	/*
	 * 코인명
		A코인가격
		B코인 원화 가격
		B코인 USDT 가격
		A-B코인 원화 가격 차이
		
		코인	
		binance(BTC)	
		binance($)	
		binance(￦)	
		upbit(￦)	
		BTC차액(￦)	
		김치프리미엄(￦
	 */
	private String coinSymbol;		// 코인명
	private String priceKrwA;		// 한국거래소 원화 가격 
	private String priceKrwB;		// 비교대상 코인 원화 가격
	private String priceBtcB;		// 비교대상 코인 BTC 가격
	private String priceUsdtB;		// 비교대상 코인 USDT 가격
	private String priceGapKrw;		// 원화 가격 차이
	private String transferFeeA;	// 전송 수수료 (업비트)
	private String transferFeeB;	// 전송 수수료 (바이낸스)
	private String coinPriceWeightA = "0";	// 코인 변동폭
	private String coinPriceWeightB = "0";	// 코인 변동폭
	private String lastPriceA;			// 이전 가격
	private String lastPriceB;			// 이전 가격
	private double priceGapPercent = 0D;	// 김프 (%)
	private String priceKrwB2;		// 비교대상 코인 원화 가격
	private String priceBtcB2;		// 비교대상 코인 BTC 가격
	private String priceGapKrw2;		// 원화 가격 차이
	private double priceGapPercent2 = 0D;	// 김프 (%)
	
	private String coinSymbol2;
	private String binanceSellPrice;
	private String binanceBuyPrice;
	private String upbitBuyPrice;
	private String upbitSellPrice;
	
}
