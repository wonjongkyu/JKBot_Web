	$(document).ready(function() {
		// getInfoAccount();
	});
	
	
	function getInfoAccount() {
		var data = {}
	
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/api/bithumb/privateapi/info/account/QTUM",
			// data : JSON.stringify(data),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				var result = data.data;
				var html = '';		// 화면 출력용 변수
				$("#bithumbInfoAccount").empty();	// Div 초기화
				
				var balance = this.status;
				html += '<div class="col-lg-3">';
				html += '<div class="ibox float-e-margins">';
				html += '<div class="ibox-title">';
				html += '<span class="label label-success pull-right">Bithumb</span>';
				html += '<h5>QTUM</h5>';
				html +=	'</div>';
				html += '<div class="ibox-content" id="bithumbBalance_qtum">';
				html += '<h1 class="no-margins">' + result.balance  +'</h1>';
				// html += ' <div class="stat-percent font-bold text-success">98% <i class="fa fa-bolt"></i></div>';
				html += '</div></div></div>';
				
				$("#bithumbInfoAccount").html(html);
				
				// getInfoAccount();

		        setTimeout(function(){
		            $('.wrapper-content').removeClass('animated fadeInRight');
		        },700);
		        
			},
			error : function(e) {
				console.log("ERROR: ", e);
				// display(e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}
	

	function getInfoBalance() {
		var data = {}
	
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/api/bithumb/privateapi/info/balance/QTUM",
			// data : JSON.stringify(data),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				var result = data.data;
				var html = '';		// 화면 출력용 변수
				$("#bithumbBalance_qtum").empty();	// Div 초기화
				
				var in_use_krw = this.in_use_krw;
				html += ' <small>' + in_use_krw + '</small>';
				// html += ' <div class="stat-percent font-bold text-success">98% <i class="fa fa-bolt"></i></div>';
				
				$("#bithumbBalance_qtum").html(html);

		        setTimeout(function(){
		            $('.wrapper-content').removeClass('animated fadeInRight');
		        },700);
		        
			},
			error : function(e) {
				console.log("ERROR: ", e);
				// display(e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}
	
	
	
	 
 
