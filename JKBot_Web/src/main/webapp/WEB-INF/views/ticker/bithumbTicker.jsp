<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="<c:url value='js/views/ticker/ticker.js'/>"></script>

<!--  DashBoard 현재 시세 -->
<div class="row col-lg-12">
   <div>
		<div class="ibox float-e-margins">
			<div class="ibox-title">
		       	<h5> 빗썸 실시간 시세 <small>v 0.1</small> </h5>
		       	<div class="ibox-tools">
		           <a class="collapse-link">
		               <i class="fa fa-chevron-up"></i>
		           </a>
		           <a class="dropdown-toggle" data-toggle="dropdown" href="#">
		               <i class="fa fa-wrench"></i>
		           </a>
		           <!-- <ul class="dropdown-menu dropdown-user">
		              <li><a href="#">Config option 1</a>
		              </li>
		              <li><a href="#">Config option 2</a>
		              </li>
		          </ul> -->
		            <a class="close-link">
		                <i class="fa fa-times"></i>
		            </a>
		        </div>
		    </div>
	    	<div class="ibox-content">
	            <div class="jqGrid_wrapper">
	                <table id="bithumbTicker_table"></table>
	                <div id="bithumbTicker_page"></div>
	           </div>
	        </div>
	    </div>
	</div>
</div>