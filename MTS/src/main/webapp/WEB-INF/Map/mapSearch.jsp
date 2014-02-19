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
<title>jQuery UI Tabs - Default functionality</title>



<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>

<script language="javascript" type="text/javascript">
<!--
function popitup(url) {
	newwindow=window.open(url,'name','toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=1,height=800,width=900');
	
	if (window.focus) {
		newwindow.focus();
		}
	return false;
}

// -->
</script>
<script type="text/javascript">
	$(document).ready(function() {
		if (document.getElementById("aid").value == 1) {

			var index = $('#tabs li a').index($('a[href="#tabs-3"]').get(0));

			$('#tabs').tabs({
				active : index
			});
		}

	});
</script>


<style type="text/css">
.required {
	color: red;
	font-style: Bold;
}

.TabbedPanelsContent {
	border: 5px;
	font-style: Bold;
}
</style>


<script type="text/javascript">
	$(document).ready(function() {
		$('#sumbnid').click(function(e) {
			var aid = document.getElementById("aid").value = 1;
		});
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#search').click(function(e) {
			$('#edit').hide();

		});
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#add').click(function(e) {
			$('#edit').hide();
			$('#successmessage').hide();
			$('#savemessage').hide();

		});
	});
</script>

</head>
<body>
	<div class="divUserDefault">
		<div class="PageTitle">Map</div>
		<div id="tabs" class="TabbedPanels">
			<ul class="TabbedPanelsTabGroup">
			
				<li class="TabbedPanelsTab"><a href="#tabs-2" id="search"><s:message
							code="label.search"></s:message></a></li>
				
			</ul>
			<div id="tabs-2" class="TabbedPanelsContent">
				<div align="left">
					<!-- ============================================Begin EmployeeSearch ======================================================= -->

					<form:form action="seacrhMapValues.mnt" method="get"
						commandName="mapSearch">
						<table class="tableGeneral">
							<tr>

								<td colspan="3"><c:forEach var="employeeSave"
										items="${param.list}">
										<div class="alert-success" id="savemessage">
											 <strong>$uccess!</strong>
											<c:out value="${param.success}"></c:out>
										</div>
									</c:forEach></td>
							</tr>
							<tr>

								<td colspan="3"><c:forEach var="employeeSaveError"
										items="${param.listw}">
										<div class="alert-danger" id="savemessage">
											<strong>Eror !:</strong>
											<c:out value="${param.warning}"></c:out>
										</div>
									</c:forEach></td>
							</tr>
							
							<tr>

								<td colspan="3"><c:forEach var="employeeUpdate"
										items="${employeeUpdate}">
										<div class="alert-success" id="successmessage">
										<font color="green">	<strong>Success!</strong>
											<c:out value="${employeeUpdate}"></c:out></font>
										</div>
									</c:forEach></td>
							</tr>
								<tr>

								<td colspan="3"><c:forEach var="employeeUpdateError"
										items="${employeeUpdateError}">
										<div class="alert-danger" id="successmessage">
										<font color="red">	<strong>Error !:</strong>
											<c:out value="${employeeUpdateError}"></c:out></font>
										</div>
									</c:forEach></td>
							</tr>
					
							<tr>
								<td width="15%"><s:message code="label.mobileNo" /></td>
								<td> <form:select path="mobileNumer" cssClass="select">
<form:option value="">--Select--</form:option>
<form:options items="${mapsearch}"/>
</form:select> 

								<input type="submit"
									value="<s:message code="label.search"/>"
									class="btn btn-primary" /></td>
								
							</tr>

						</table>
					</form:form>
					<!-- ============================================End Search ====================================== -->
					<c:forEach var="mapvalues" items="${mapvalues}">
						<c:set var="g" value="${mapvalues}"></c:set>
					</c:forEach>
					<c:if test="${g!=null}">
					<display:table id="mapRow" name="mapvalues"
							requestURI="seacrhMapValues.mnt" pagesize="5" class="grid-table">


							<display:column property="empName"
								title="Employee Name" sortable="true"></display:column>
								<display:column property="mobileNumer"
								title="Mobile Number" sortable="true"></display:column>
							<display:column property="imieNo"
								title="IMIE Number" sortable="true" />
							<display:column property="dt"
								title="Date" sortable="true" />
								<%-- <display:column title="View Map" value="ViewMap"></display:column> --%>
								<display:column title="View Map"><a href="mtHome.mnt?employeeCodeEdit=<c:out value="${mapRow.employeeId}"/>" target="_blank"  onclick="return popitup('mtHome.mnt?employeeCodeEdit=<c:out value="${mapRow.employeeId}"/>')"><u><font color="blue">Viewmap</font></u></a></display:column>
							
				<display:setProperty name="paging.banner.placement"
								value="bottom" />

							<display:setProperty name="paging.banner.group_size" value="3" />

							<display:setProperty name="paging.banner.some_items_found"
								value="<span class='pagebanner'>{0} {1},listing {2} to {3}. </span>" />
							</display:table>
					</c:if>
					

				</div>
			</div>
		
		</div>
		</div>
</body>
</html>




