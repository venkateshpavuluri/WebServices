<!-- author pvenkateswarlu -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css"/>
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css"/>
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
    <!--<a href="#" class="logout">Logout</a>--> </div></div>
		
		
			<pre></pre>	<div align="center">
					<!-- ============================================Begin EmployeeSearch ======================================================= -->

					<!-- ============================================End Search ====================================== -->
					<table class="tableGeneral">
				
					<form:form action="SearchmessageswithDate.mnt" method="GET"
						commandName="messageSearch">
				<td><s:message code="label.selectDate"></s:message><form:input path="dt"  id="datepick"/>  </td>
						<td><input type="submit" value="Search" /> </td>
						
						</form:form>	</table>
					
					<c:forEach var="msgdatevalues" items="${msgdatevalues}">
						<c:set var="g" value="${msgdatevalues}"></c:set>
					</c:forEach>
					<c:if test="${g!=null}">
					<display:table id="msgdtRow" name="msgdatevalues"
							requestURI="SearchmessageswithDate.mnt" pagesize="5" class="grid-table">

							<display:column property="messageid"
								title="Message ID" media="none" sortable="true"></display:column>
							<display:column property="imienumber"
								title="IMIE Number" sortable="true"></display:column>
								<display:column property="messagenumber"
								title="Message Number" sortable="true" />
							<display:column property="messagetype"
								title="Message Type" sortable="true" />
							
							<display:column property="dt"
								title="Date" sortable="true"></display:column>
				<display:setProperty name="paging.banner.placement"
								value="bottom" />

							<display:setProperty name="paging.banner.group_size" value="3" />
								<display:setProperty name="paging.banner.some_items_found"
								value="<span class='pagebanner'>{0} {1},listing {2} to {3}. </span>" />
							</display:table>
					</c:if>

				</div>
			
		
									<!--Footer-->
<div id="footer" style="position:absolute; bottom:0;">
 <p class="left">All copy rights reserved <a href="#">Company Name</a>.</p>
 <p class="right">Powered by <a href="http://www.mntsoft.co.in" target="_blank">www.mntsoft.co.in</a>.</p>
</div>		
		
</body>

</html>




