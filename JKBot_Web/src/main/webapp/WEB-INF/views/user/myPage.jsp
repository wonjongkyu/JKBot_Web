<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <script src="<c:url value='/js/views/user/mypage.js'/>"></script> --%>

<div class="row">
	<div class="col-lg-9">
	    <div class="ibox float-e-margins">
	        <div class="ibox-title">
	            <span class="label label-primary pull-right">2018.05.09</span>
	            <h5>총 자산</h5>
	        </div>
	        <div class="ibox-content">
	
	            <div class="row">
	                <div class="col-md-6">
	                    <h1 class="no-margins">5,500,000 원</h1>
	                    <div class="font-bold text-navy">44% <i class="fa fa-level-up"></i> <small>Rapid pace</small></div>
	                </div>
	                <!-- <div class="col-md-6">
	                    <h1 class="no-margins">206,12</h1>
	                    <div class="font-bold text-navy">22% <i class="fa fa-level-up"></i> <small>Slow pace</small></div>
	                </div> -->
	            </div>
	        </div>
	    </div>
	</div>
</div>
<div class="row">
	<div class="col-lg-3">
	    <div class="ibox float-e-margins">
	        <div class="ibox-title">
	            <span class="label label-success pull-right">Total</span>
	            <h5>총 수익</h5>
	        </div>
	        <div class="ibox-content">
	            <h1 class="no-margins">386,200</h1>
	            
	            <div class="stat-percent font-bold text-success">98% <i class="fa fa-level-down"></i></i></div>
	            <!-- <small>Total views</small> -->
	        </div>
	    </div>
	</div>
	<div class="col-lg-3">
	    <div class="ibox float-e-margins">
	        <div class="ibox-title">
	            <span class="label label-info pull-right">Monthly</span>
	            <h5>월간 수익</h5>
	        </div>
	        <div class="ibox-content">
	            <h1 class="no-margins">80,800</h1>
	            <div class="stat-percent font-bold text-info">20% <i class="fa fa-level-up"></i></div>
	            <!-- <small>New orders</small> -->
	        </div>
	    </div>
	</div>
	<div class="col-lg-3">
	    <div class="ibox float-e-margins">
	        <div class="ibox-title">
	       		<span class="label label-danger pull-right">Daily</span>
	            <h5>오늘의 수익</h5>
	        </div>
	        <div class="ibox-content">
	            <h1 class="no-margins">386,200</h1>
	            <div class="stat-percent font-bold text-success">98% <i class="fa fa-bolt"></i></div>
	            <!-- <small>Total views</small> -->
	        </div>
	    </div>
	</div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>코인 가계부 ()</h5>

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

                <table class="footable table table-stripped toggle-arrow-tiny">
                    <thead>
	                    <tr>
	                        <th data-toggle="true">No</th>
	                        <th>거래 날짜</th>
	                        <th>수익금</th>
	                        <th>수익률</th>
	                        <th>성공여부</th>
	                        <th data-hide="all">업비트 -> 바이낸스</th>
	                        <th data-hide="all">바이낸스 -> 업비트</th>
	                        <th data-hide="all">수익률</th>
	                        <th data-hide="all">수익금</th>
	                    </tr>
                    </thead>
                    <tbody>
	                    <tr>
	                        <td>1</td>
	                        <td>2018-05-13</td>
	                        <td>500,000 KRW</td>
	                        <td>3.3%</td>
	                        <td><span class="pie">성공</span></td>
	                        <td>20%</td>
	                        <td>Jul 14, 2013</td>
	                        <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
	                    </tr>
	                    <tr>
	                        <td>Alpha project</td>
	                        <td>Alice Jackson</td>
	                        <td>0500 780909</td>
	                        <td>Nec Euismod In Company</td>
	                        <td><span class="pie">6,9</span></td>
	                        <td>40%</td>
	                        <td>Jul 16, 2013</td>
	                        <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
	                    </tr> 
	               	</tbody>
	                    
                    <tfoot>
	                    <tr>
	                        <td colspan="5">
	                            <ul class="pagination pull-right"></ul>
	                        </td>
	                    </tr>
					</tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
            
 
<script>
	$('.footable').footable();
	$('.footable2').footable();
</script>
                