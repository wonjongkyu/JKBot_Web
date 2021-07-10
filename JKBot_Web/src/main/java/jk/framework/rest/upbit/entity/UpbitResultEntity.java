/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.rest.upbit.entity; 

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
public class UpbitResultEntity extends RestCommonVO{
	// 코인 symbol, 평균가, 구매 개수 저장
	private String coinSymbolName;		// 코인 심볼명
	
	private String askCoinAveragePrice;		// 코인 평균가격
	private String askCoinAmout;			// 구매가능 코인 개수
	private String bidCoinAveragePrice;		// 코인 평균가격
	private String bidCoinAmout;			// 구매가능 코인 개수
	private String buyRecommend;			// 구매 추천
	
	
}
