<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="<c:url value='js/views/user/mypage.js'/>"></script>
 
<!--  DashBoard 현재 시세 -->
<div class="row hide">
    <div class="col-lg-6">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>나의 계좌 (원화) </h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="#">Config option 1</a>
                            </li>
                            <li><a href="#">Config option 2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <table class="table table-bordered">
                        <thead>
	                        <tr>
	                            <th>#</th>
	                            <th>거래소 명</th>
	                            <th>보유중인 총 원화</th>
	                            <th>사용중 원화</th>
	                            <th>사용가능 원화</th>
	                        </tr>
                        </thead>
                        <tbody>
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
                        </c:choose>
                      
                        </tbody>
                    </table>

                </div>
            </div>
        </div>

</div>
