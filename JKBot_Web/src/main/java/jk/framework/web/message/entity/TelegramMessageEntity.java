/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.web.message.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class TelegramMessageEntity {
	private String minCoinSymbol;		// 코인명
	private String minPriceKrwA;		// 한국거래소 원화 가격 
	private String minPriceKrwB;		// 비교대상 코인 원화 가격
	private String minPriceGapPercent;	// 김프 (%)
	
	private String maxCoinSymbol;		// 코인명
	private String maxPriceKrwA;		// 한국거래소 원화 가격 
	private String maxPriceKrwB;		// 비교대상 코인 원화 가격
	private String maxPriceGapPercent;	// 김프 (%)
}
