
var proxyJsonp="https://script.google.com/macros/s/AKfycbwmqG55tt2d2FcT_WQ3WjCSKmtyFpkOcdprSITn45-4UgVJnzp9/exec";
jQuery.ajaxOrig=jQuery.ajax;jQuery.ajax=function(a,b){function d(a){a=encodeURI(a).replace(/&/g,"%26");return proxyJsonp+"?url="+a+"&callback=?"}var c="object"===typeof a?a:b||{};c.url=c.url||("string"===typeof a?a:"");var c=jQuery.ajaxSetup({},c),e=function(a,c){var b=document.createElement("a");b.href=a;return c.crossOrigin&&"http"==a.substr(0,4).toLowerCase()&&"localhost"!=b.hostname&&"127.0.0.1"!=b.hostname&&b.hostname!=window.location.hostname}(c.url,c);c.proxy&&0<c.proxy.length&&(proxyJsonp=c.proxy,"object"===typeof a?
a.crossDomain=!0:"object"===typeof b&&(b.crossDomain=!0));e&&("object"===typeof a?a.url&&(a.url=d(a.url),a.charset&&(a.url+="&charset="+a.charset),a.dataType="json"):"string"===typeof a&&"object"===typeof b&&(a=d(a),b.charset&&(a+="&charset="+b.charset),b.dataType="json"));return jQuery.ajaxOrig.apply(this,arguments)};jQuery.ajax.prototype=new jQuery.ajaxOrig;jQuery.ajax.prototype.constructor=jQuery.ajax;

$(document).ready(function() {
	
	setInterval(function(){		// 0.5초마다 빗썸 이더리움 가격 호출
		getBithumbEthPrice();
	}, 1000);
	
});


//BTC-KRW 가격 저장
function getBithumbEthPrice(){
	$.ajax({
		type : "GET",
		contentType : 'application/json',
		url : 'https://api.bithumb.com/public/ticker/ETH',
		dataType : 'json',
		timeout : 50000,
		crossOrigin: true,
		success : function(data) {
			var result = JSON.parse(data);
			var price = result.data.closing_price;
			
			var etosi = $(".list-bot").children().find('.price');
 			$.each(etosi, function(){
 				var etosi_price = $(this).text();
 				var krw_price = price * etosi_price;
 				krw_price = krw_price.toFixed(2); // 소숫점 둘째자리에서 반올림
 				krw_price +='원';
 				$(this).next().text(krw_price);
			});
 
/* 			// div list-bot 클래스 밑에 .price 클래스 찾고, pir 클래스에 원화가격 넣어준다.
			var etosi = $(".price").text();
			console.log(etosi);
			$(".price").append( etosi*price + '원'); */
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