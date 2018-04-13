	

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
	// getCoinnest();
	// getUpbit();
	// test();
	getCompareCoinprice();
	setInterval(function(){
		getCompareCoinprice();
		// getCoinnest();
		// getUpbit();
	}, 60000);
});

/*
 * 테스트용 function
 */
function test(){
	var data = {}
	
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/upbit/publicapi/ticker/test",
		// data : JSON.stringify(data),
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			console.log(data);
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


/*
 * 바이낸스 가격 가져오는 function
 */
function getCompareCoinprice() {
	var data = {}
	
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/binance/api/v1/ticker/24hr",
		// data : JSON.stringify(data),
		dataType : 'json',
		timeout : 60000,
		success : function(data) {
			console.log(data);
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

 