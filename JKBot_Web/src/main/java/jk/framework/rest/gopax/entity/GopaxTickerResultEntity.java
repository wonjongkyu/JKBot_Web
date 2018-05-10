/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.rest.gopax.entity; 

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jk.framework.rest.common.entity.RestCommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
  	[{
  	 "time":"2018-05-10T02:13:31.000Z"
  	,"date":1525918411
  	,"id":8485544
  	,"price":250
  	,"amount":30696.12375658
  	,"side":"sell"
  	}]
 
 */
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class GopaxTickerResultEntity extends RestCommonVO{
	@JsonProperty("time")
	private String time;			
	@JsonProperty("date")
	private String date;			
	@JsonProperty("id")
	private String id;				
	@JsonProperty("price")
	private String price;		
	@JsonProperty("amount")
	private String amount;	
	@JsonProperty("side")
	private String side;
}
