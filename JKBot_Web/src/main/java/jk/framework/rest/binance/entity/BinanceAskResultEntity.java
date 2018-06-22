/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.rest.binance.entity; 

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jk.framework.rest.common.entity.RestCommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
 
/*
 * 
 * 
  {
	"lastUpdateId": 94172128,
	"bids": [
		["0.00171750", "2.02000000", []]
	],
	"asks": [
		["0.00171750", "2.02000000", []]
	]
   }
 *
 */
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class BinanceAskResultEntity extends RestCommonVO{
	// 코인 symbol, 평균가, 구매 개수 저장
	private String coinSymbolName;		// 코인 심볼명
	
	private String coinAveragePrice;	// 코인 평균가격
	
	private String coinAmout;			// 구매가능 코인 개수
	
	
}
