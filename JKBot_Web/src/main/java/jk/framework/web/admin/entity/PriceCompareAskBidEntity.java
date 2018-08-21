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
public class PriceCompareAskBidEntity extends RestCommonVO{
	 
	private String coinSymbol;		// 코인명
	private String priceBidKrwA;		// 한국거래소 원화 가격  (Bid)
	private String priceAskKrwA;		// 한국거래소 원화 가격  (Ask)
	private String coinPriceWeightA = "0";	// 코인 변동폭
	private String coinPriceWeightB = "0";	// 코인 변동폭

	private String priceBidKrwB;			// 비교대상 코인 원화 가격 (Bid)
	private String priceAskKrwB;			// 비교대상 코인 원화 가격 (Ask)
	private String priceGapKrw;				// 원화 가격 차이
	private String priceGapKrw2;			// 원화 가격 차이
	private double priceGapPercent = 0D;	// 김프 (%)
	private double priceGapPercent2 = 0D;	// 김프 (%)
}
