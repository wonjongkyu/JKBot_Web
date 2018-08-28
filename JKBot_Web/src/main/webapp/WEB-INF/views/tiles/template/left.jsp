<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="<%= request.getContextPath()%>"></c:set>  
	
<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav metismenu" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element"> <span>
                        <img alt="image" class="img-circle" src="${contextPath}/img/profile_small.jpg" />
                         </span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">David Williams</strong>
                         </span> <span class="text-muted text-xs block">Art Director <b class="caret"></b></span> </span> </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a href="profile.html">Profile</a></li>
                        <li><a href="contacts.html">Contacts</a></li>
                        <li><a href="mailbox.html">Mailbox</a></li>
                        <li class="divider"></li>
                        <li><a href="login.html">Logout</a></li>
                    </ul>
                </div>
                <div class="logo-element">
                    IN+
                </div>
            </li>
           	<li class="active">
                <a href="${contextPath}/main"><i class="fa fa-th-large"></i> <span class="nav-label">DashBoard</span></a>
            </li>
           	<li>
                <a href="${contextPath}/user/mypage"><i class="fa fa-th-large"></i> <span class="nav-label">나의 통장</span></a>
            </li>
            <li>
                <a href="${contextPath}/price/compare"><i class="fa fa-th-large"></i> <span class="nav-label">재정거래(DB)</span></a>
            </li>
            <li>
                <a href="${contextPath}/admin/compare"><i class="fa fa-th-large"></i> <span class="nav-label">관리자(API)</span></a>
            </li>
            <li>
                <a href="${contextPath}/admin/compare2"><i class="fa fa-th-large"></i> <span class="nav-label">관리자(매도,매수)</span></a>
            </li>
            <li>
                <a href="index.html"><i class="fa fa-th-large"></i> <span class="nav-label">Dashboards</span> <span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li><a href="index.html">Dashboard v.1</a></li>
                    <li><a href="dashboard_2.html">Dashboard v.2</a></li>
                    <li><a href="dashboard_3.html">Dashboard v.3</a></li>
                    <li class="active"><a href="dashboard_4_1.html">Dashboard v.4</a></li>
                    <li><a href="dashboard_5.html">Dashboard v.5 </a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>