<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<script src="<c:url value='js/views/user/mypage.js'/>"></script>
 
<!--  DashBoard 현재 시세 -->
<div class="row">
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
                        		<c:when test="${entityList != null}">
                        			<c:forEach var="item" items="${entityList}" varStatus="status">
			                        <tr>
			                            <td>${status.count}</td>
			                            <td>${item.exchagneName}</td>
			                            <td>${item.totalCurrency}</td>
			                            <td>${item.inUserCurrency}</td>
			                            <td>${item.availableCurrency}</td>
			                        </tr>
			                   	 	</c:forEach>
                        		</c:when>
                        	</c:choose>
	                        <c:otherwise>
	                       		<tr>
	                       			<td role="5">조회 결과가 없습니다.</td>
	                       		</tr>
	                        </c:otherwise>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>

</div>
        
        
        
       
