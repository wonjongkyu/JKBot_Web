/*
 *	업비트 	코인네스트
 * 	BTC		btc
	BCC		bch
	ETH		eth
	BTG		btg
	NEO		neo
	QTUM	qtum
	ETC		etc
	OMG		omg
	
	document.ready
	- coinnest 배열만큼 함수 호출
	- upbit 배열만큼 함수 호출
	10초마다.			
 */
var coinnestCoinArray = ['btc','bch','eth','btg','qtum','etc','omg','neo'];
var upbitCoinArray = ['BTC','BCC','ETH','BTG','QTUM','ETC','OMG','NEO'];

$(document).ready(function() {
	getCompareCoinprice();
	setInterval(function(){
		getCompareCoinprice();
	}, 20000);
});

/*
 * 업비트-바이낸스 가격 가져오는 function
 */
function getCompareCoinprice() {
	var data = {}
	
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/price/priceCompare",
		// data : JSON.stringify(data),
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			var result = data;
			
			var resultJsonArray = new Array();
			var resultHtml = "";
			$("#priceTbody").html('');
			$.each(result, function(){
				var name = this.closing_price;
				var resultVO = new Object();
				resultVO.coinSymbol = this.coinSymbol;
				resultVO.priceKrwA = this.priceKrwA;
				resultVO.priceKrwB = this.priceKrwB;
				resultVO.priceBtcB = this.priceBtcB;
				resultVO.priceUsdtB = this.priceUsdtB;
				resultVO.priceGapKrw = this.priceGapKrw;
				resultVO.priceGapPercent = this.priceGapPercent;
				resultVO.status = this.status;
				resultHtml += "<tr>";
				resultHtml += "<td>-</td>";
				resultHtml += "<td>" + this.coinSymbol + "</td>";
				resultHtml += "<td>" + comma(this.priceBtcB) + "</td>";
				resultHtml += "<td>" + comma(this.priceUsdtB) + "</td>";
				resultHtml += "<td>" + comma(this.priceKrwB) + "</td>";
				resultHtml += "<td>" + comma(this.priceKrwA) + "</td>";
				resultHtml += "<td>" + "-" + "</td>";
				if(this.priceGapPercent == null){
					this.priceGapPercent = 0;
				}
				
				if(this.priceGapPercent.indexOf("-") > -1){
					resultHtml += '<td class="text-sucess">' + comma(this.priceGapKrw) + ' (' + comma(this.priceGapPercent) + ') ' + '<i class="fa fa-level-down"></i></td>';
				}else if(this.priceGapPercent == 0){
					resultHtml += "<td>" + comma(this.priceGapKrw) + ' (' + comma(this.priceGapPercent) + ') ' + "</td>";
				}else {
					resultHtml += '<td class="text-danger">' + comma(this.priceGapKrw) + ' (' + comma(this.priceGapPercent) + ')   ' + '<i class="fa fa-level-up"></i></td>';
				}
				resultHtml += "</tr>";
				// 마이너스 빨간색 플러스 파란색 
				resultJsonArray.push(resultVO);
			});
			
      /*      <td class="text-navy"> <i class="fa fa-level-up"></i> 40% </td>
            <td class="text-warning"> <i class="fa fa-level-down"></i> -20% </td>
            <td class="text-navy"> <i class="fa fa-level-up"></i> 26% </td>*/
        
			$("#priceTbody").html(resultHtml);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}
	
/*function getCoinnest() {
	
	  var url = "https://api.coinnest.co.kr/api/pub/ticker?coin=" + coin;
	  $.getJSON(url,   
	    {   
	        tags: "mount rainier",   
	        tagmode: "any",   
	        format: "json"   
	      },   
	      function(data) {   
	        alert(data.last);
	        alert(data.buy);
	        
	        $.each(data.items, function(i,item){   
  	          $("<img/>").attr("src", item.media.m).appendTo("#images");   
  	          if ( i == 3 ) return false;   
  	        });   
	    });  
}


function getUpbit(coin) {
	  if(coin==null){
	    coin = "btc";
	  }
	  
	  var url = "https://crix-api-endpoint.upbit.com/v1/crix/candles/minutes/1?code=CRIX.UPBIT.KRW-" + coin;
	 
}*/

 