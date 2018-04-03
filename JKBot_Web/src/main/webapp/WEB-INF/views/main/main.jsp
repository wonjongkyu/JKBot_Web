<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
<script src="<c:url value='js/views/main/main.js'/>"></script>

<div class="wrapper wrapper-content  animated fadeInRight">
	<!--  빗썸 시세 -->
	<%@include file="/WEB-INF/views/ticker/bithumbTicker.jsp"%>	
	<!--  빗썸 개인 계좌 정보 조회 -->		
	<%@include file="/WEB-INF/views/info/account/bithumbInfoAccount.jsp"%>	
	<!--  빗썸 자동 매수 -->
	<%@include file="/WEB-INF/views/trade/autoBuy.jsp"%>				
</div>
        
