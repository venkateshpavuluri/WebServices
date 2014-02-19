<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/resources/demos/style.css" />
<link href="accordionmenu.css" rel="stylesheet" type="text/css" />
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="js/jqueryui.css" />
<script src="js/jquery-1.7.2.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link href="style.css" rel="stylesheet" type="text/css" />
	<script>
$(function() {
	$("#datepick").datepicker({
		
	
		dateFormat :"yy-mm-dd",
		changeMonth : true,
		changeYear : true
	});
});
</script> 
</head>
<body>
<!-- header -->
	<div id="header"> <a href="#" class="logo"><img alt="" src="images/logo.png"></a>
  <div id="header-right">
    <p class="username">Welcome to <span class="color">MTS</span></p>
    <!--<a href="#" class="logout">Logout</a>--> </div>
</div>
<div></div>

<pre></pre> <div align="center">
	<table class="tableGeneral">
				
						<form:form action="callsSearchwithDate.mnt" method="get"
						commandName="callsForm">
				<td><s:message code="label.selectDate"></s:message> <form:input path="dt"  id="datepick"/> </td>
						<td><input type="submit" value="Search"/> </td>
						</form:form></table>


	<c:forEach var="callsPopup" items="${callsPopup}">
						<c:set var="p" value="${callsPopup}"></c:set>
					</c:forEach>
					<c:if test="${p!=null}">
					<div align="center" style="height: 250px;">
					
					<display:table id="callsRow" name="callsPopup"
							requestURI="callsSearchwithDate.mnt" pagesize="5" class="grid-table">
								<display:column property="callNumber"
								title="Mobile Number" sortable="true" />
							<display:column property="imieNumbe"
								title="IMIE Number" sortable="true" />
								<display:column property="duration"
								title="Duration" sortable="true"></display:column>
								<display:column property="callType"
								title="Call Type" sortable="true"></display:column>
								<display:column property="dt"
								title="Date" sortable="true"></display:column>
							
				<display:setProperty name="paging.banner.placement"
								value="bottom" />

							<display:setProperty name="paging.banner.group_size" value="3" />

							<display:setProperty name="paging.banner.some_items_found"
								value="<span class='pagebanner'>{0} {1},listing {2} to {3}. </span>" />
							</display:table>
					</div>
					</c:if>
					</div>
					
									<!--Footer-->
<div id="footer" style="position:absolute; bottom:0;">
 <p class="left">All copy rights reserved <a href="#">Company Name</a>.</p>
 <p class="right">Powered by <a href="http://www.mntsoft.co.in" target="_blank">www.mntsoft.co.in</a>.</p>
</div>
				
</body>
</html>