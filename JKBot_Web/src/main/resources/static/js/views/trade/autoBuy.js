	$(document).ready(function() {
		// getUserBalanceInfo();
	});
	
	/*
	 * 빗썸이면서 KRW만 조회해온다.
	 * 
	 * 
	 */
	function getUserBalanceInfo() {
		var data = {
				   "userId" : "wonjongkyu",
				   "coinSymbolName" : "KRW"
			};
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/user/balanceInfo",
			data : JSON.stringify(data),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log(data);
				
				/*
				<c:choose>
            	<c:when test="${entityList ne null }">
                	<c:forEach items="${entityList}" var="list" varStatus="status">
                		<c:if test ="${list.coinSymbolName eq 'KRW'}">
						  	<tr>
						  		<td>${status.count}</td>
						  		<%-- <td><c:out value="${list.userId}"/></td> --%>
						  		<td><c:out value="${list.exchangeName}"/></td>
							  	<td><c:out value="${list.totalCurrency}"/></td>
							  	<td><c:out value="${list.inUserCurrency}"/></td>
							  	<td><c:out value="${list.availableCurrency}"/></td>
						  	</tr>
					  	</c:if>  
					</c:forEach> 
            	</c:when>
            	<c:otherwise>
            		<tr>
            			<td colspan="5">조회 결과가 없습니다. </td>
            		</tr>
            	</c:otherwise>
            </c:choose>*/
            
            
		        
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
