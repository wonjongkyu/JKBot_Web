/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.web.user.entity; 

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserBalanceEntity{
	// TableName : jk_coin_type
	private String coinKorName;			// 코인 한글명 
	
	// TableName : jk_user_balance
	private String exchangeName;		// 거래소 명
	private String coinSymbolName;		// 코인 심볼명
	private String totalCurrency;		// 보유중인 통화 수
	private String inUserCurrency;		// 사용중 통화 수
	private String availableCurrency;	// 사용가능 통화 수
	private String xcoinLast;			// 마지막 거래체결 금액(bithumb)
}
