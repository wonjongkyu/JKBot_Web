	

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
	getCoinnest();
	getUpbit();
	setInterval(function(){
		getCoinnest();
		getUpbit();
	}, 10000);
});
	
function getCoinnest() {
	
	  var url = "https://api.coinnest.co.kr/api/pub/ticker?coin=" + coin;
	  $.getJSON(url,   
	    {   
	       /* tags: "mount rainier",   
	        tagmode: "any",   
	        format: "json"   */
	      },   
	      function(data) {   
	        alert(data.last);
	        alert(data.buy);
	        
/*	        $.each(data.items, function(i,item){   
  	          $("<img/>").attr("src", item.media.m).appendTo("#images");   
  	          if ( i == 3 ) return false;   
  	        });   */
	    });  
}


function getUpbit(coin) {
	  if(coin==null){
	    coin = "btc";
	  }
	  
	  var url = "https://crix-api-endpoint.upbit.com/v1/crix/candles/minutes/1?code=CRIX.UPBIT.KRW-" + coin;
	 
}

 