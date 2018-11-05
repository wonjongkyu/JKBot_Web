
$(document).ready(function() {
	
	setInterval(function(){		// 0.5초마다 빗썸 이더리움 가격 호출
		getBithumbEthPrice();
	}, 2000);
	
});

//BTC-KRW 가격 저장
function getBithumbEthPrice(){
	$.ajax({
		type : "GET",
		contentType : 'application/json',
		url : 'https://api.bithumb.com/public/ticker/ETH',
		dataType : 'json',
		timeout : 50000,
		success : function(data) {
			var result = data; //JSON.parse(data);
			var price = result.data.closing_price;
			
			var etosi = $(".list-bot").children().find('.price');
 			$.each(etosi, function(){
 				var etosi_price = $(this).text();
 				var krw_price = price * etosi_price;
 				krw_price = krw_price.toFixed(2); // 소숫점 둘째자리에서 반올림
 				krw_price +='원';
 				$(this).next().text(krw_price);
			});
 			
			var etosi2 = $(".list-top").children().find('.price');
 			$.each(etosi2, function(){
 				var etosi_price = $(this).text();
 				var krw_price = price * etosi_price;
 				krw_price = krw_price.toFixed(2); // 소숫점 둘째자리에서 반올림
 				krw_price +='원';
 				$(this).next().text(krw_price);
			});
 
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