/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.web.history.entity; 

import jk.framework.rest.common.entity.RestCommonVO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class TradeHistoryEntity extends RestCommonVO{

	private String exchangeName_A;	// 거래소명 A
	private String coinSymbol_A;	// 코인명 A
	private String totalPrice_A;	// 총자산 A
	private String exchangeName_B;	// 거래소명 B
	private String coinSymbol_B;	// 코인명 B
	private String totalPrice_B;	// 총 자산 B
	private String profitPrice;		// 총 수익금 
	private String profitRate;		// 총 수익률 
	private String inserDt;			// 등록일
	
 
}
