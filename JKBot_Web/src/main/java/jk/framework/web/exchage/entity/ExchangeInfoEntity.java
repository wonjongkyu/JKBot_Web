/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.web.exchage.entity; 

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ExchangeInfoEntity{
	private String exchangeName;		// 거래소 명
	private String coinKorName;			// 코인 한글명
	private String coinSymbolName;		// 코인 심볼명
}
