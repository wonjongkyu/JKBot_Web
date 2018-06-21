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
public class BinanceBidResultEntity extends RestCommonVO{
	@JsonProperty("bids")
	private List<List<Object>>  bids;
	@JsonProperty("asks")
	private List<List<Object>> asks;
	
}
