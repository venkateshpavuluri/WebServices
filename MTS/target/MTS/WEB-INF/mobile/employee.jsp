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

<title>jQuery UI Tabs - Default functionality</title>

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
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$('#sumbnid')
								.click(
									
										function(event) {
											
											$("#employeeform")
													.validate(
															{
																rules : {
																	mobileNo: {
																		required : true,
																	},
																	
																},
																 messages : {
																	 mobileNo : "<font style='color: red;'><b>Mobile No is Required</b></font>"
																		
																	},
															});
										});
						$('#updateBtn')
								.click(
										function(event) {
											$("#employeeFormEdit")
													.validate(
															{
																rules : {
																	mobileNoEdit : {
																		required : true,
																	},
																	
																},
																messages : {
																	mobileNoEdit : "<font style='color: red;'><b>Mobile Number is Required</b></font>"
																	
																},
															});

										});

					});
</script>
<script>
$(function() {
	$("#dob,#doj").datepicker();
	$("#dobEdit,#dojEdit").datepicker();
	
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

<script>
	$(function() {
		$("#tabs").tabs();
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
		<div class="PageTitle">Employee</div>
		<div id="tabs" class="TabbedPanels">
			<ul class="TabbedPanelsTabGroup">
				<c:forEach var="materialValues" items="${employeeEditValues}">
					<li class="TabbedPanelsTab"><a href="#tabs-1" id="edit"><s:message
								code="label.edit"></s:message> </a></li>

				</c:forEach>
				<li class="TabbedPanelsTab"><a href="#tabs-2" id="search"><s:message
							code="label.search"></s:message></a></li>
				<li class="TabbedPanelsTab"><a href="#tabs-3" id="add"><s:message
							code="label.add"></s:message></a></li>
			</ul>
			<div id="tabs-2" class="TabbedPanelsContent">
				<div align="left">
					<!-- ============================================Begin EmployeeSearch ======================================================= -->

					<form:form action="employeeSearch.mnt" method="get"
						commandName="employee" >
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
							<%-- <tr>
								<td><s:message code="label.materialCode"></s:message></td>

								<td><form:select path="material_Id" class="select">
										<form:option value="0">--Select--</form:option>
										<form:option value="0" class="select">All</form:option>
										<form:options items="${materialSearchIds}" />
									</form:select></td>

								<td><input type="submit"
									value="<s:message code="label.search"/>"
									class="btn btn-primary" "/></td>
							</tr>
 --%>
							<tr>
								<td width="15%"><s:message code="label.employeeName" /></td>
								<td><%-- <form:select path="xmlLabel" cssClass="select">

										<form:options items="${xmlItems}" />
									</form:select> <s:bind path="materialAdd.operations">
								<select class="select" name="operations">
								<option value="<s:message code='assignedOperator'/>"><s:message code="label.equalsTo"/> </option>
								<option value="<s:message code='notequalsOperator'/>"><s:message code="label.notEqualsTo"/> </option>
							 <option value="<s:message code='beginsWithOperator'/>"> <s:message code="label.beginsWith"/> </option> 
								<option value="<s:message code='endsWithOperator'/>"><s:message code="label.endsWith"/> </option>
								<option value="<s:message code='containsOperator'/>"><s:message code="label.contains"/></option>
								</select>
									 </s:bind> --%>  <form:input path="fName" cssClass="textbox" />
									
								
							
								
								
								<input type="submit"
									value="<s:message code="label.search"/>"
									class="btn btn-primary" /></td>
								
							</tr>

						</table>
					</form:form>
					<!-- ============================================End Search ====================================== -->
					<c:forEach var="employeeSearch" items="${employeeSearch}">
						<c:set var="g" value="${employeeSearch}"></c:set>
					</c:forEach>
					<c:if test="${g!=null}">
					
						<!-- ============================================Begin DisplayTable ================================================= -->
						<display:table id="employeeRow" name="employeeSearch"
							requestURI="employeeSearch.mnt" pagesize="5" class="grid-table">

							<display:column property="fName"
								titleKey="label.fName" sortable="true"></display:column>
							<display:column property="gender"
								titleKey="label.gender" sortable="true" />
							<display:column property="dob"
								titleKey="label.dob" sortable="true" />
							<display:column property="doj" titleKey="label.doj"
								media="html" sortable="true" />
							<display:column property="mobileNo"
								titleKey="label.mobileNo" media="html" sortable="true" />
							

							<display:column property="phoneNo"
								titleKey="label.phoneNo" sortable="true" />
								<display:column property="eMail"
								titleKey="label.eMail" sortable="true" />
								
								<display:column property="pAdd"
								titleKey="label.pAdd" sortable="true" />
								<display:column property="pCity"
								titleKey="label.pCity" sortable="true" />
								<display:column property="tAdd"
								titleKey="label.tAdd" sortable="true" />
								<display:column property="tCity"
								titleKey="label.tCity" sortable="true" />
								
							<display:column titleKey="label.edit" style="color:white">
							
							
						
								<a
									href="employeeEditHome.mnt?employeeCodeEdit=<c:out value="${employeeRow.employeeId}"/>"
									style="color: red"><img src="images/Edit.jpg" width="20px"
									height="20px" /> </a>	
							</display:column>

							<display:column titleKey="label.delete">
							
								<a href="employeeDelete.mnt?employeeCodeDelete=<c:out value="${employeeRow.employeeId}"/>"
									style="color: red"
									onclick="return confirm('Do u want to Delete The Record?')"><img
									src="images/Delete.jpg" width="20px" height="20px" /></a>
									
							</display:column>
							<%-- <display:column title="View Map"><a href="mtHome.mnt?employeeCodeEdit=<c:out value="${employeeRow.employeeId}"/>" target="_blank"  onclick="return popitup('../MNTERP/mobiletracking.jsp')"><u>Viewmap</u></a></display:column>
							 --%>
							<display:column title="View Map"><a href="mtHome.mnt?employeeCodeEdit=<c:out value="${employeeRow.employeeId}"/>" target="_blank"  onclick="return popitup('mtHome.mnt?employeeCodeEdit=<c:out value="${employeeRow.employeeId}"/>')"><u><font color="blue">Viewmap</font></u></a></display:column>
							
							<display:setProperty name="paging.banner.placement"
								value="bottom" />

							<display:setProperty name="paging.banner.group_size" value="3" />

							<display:setProperty name="paging.banner.some_items_found"
								value="<span class='pagebanner'>{0} {1},listing {2} to {3}. </span>" />
						</display:table>
					</c:if>

				</div>
			</div>
			<!-- ============================================End DisplayTable ================================================= -->

			<div id="tabs-3" class="TabbedPanelsContent">
				<div align="left" class="TabbedPanelsContent">


					<!--=================== ============================================Begin MaterialAdd================================================= -->

					<form:form action="employeeAdd.mnt" id="employeeform" name="employee"
						method="POST" commandName="employee">

						<table class="tableGeneral">
							<tr>
								<td colspan="2"><c:forEach var="a"
										items="${saveduplicatCheck}">
										<div class="alert-warning">
									<strong>Warning !</strong>
											 <c:out value="${a}"></c:out>
										</div>
									</c:forEach></td>
							</tr>
							<form:hidden path="aid" />


							<tr>
								<td><s:message code="label.fName" /></td>
								<td><form:input path="fName" id="fName"
										class="textbox"/></td>
									<td><s:message code="label.lName" /></td>
									<td><form:input path="lName" id="lName"
										class="textbox" /></td>	
										
										
							</tr>

							<tr>
								<td><s:message code="label.mName" /></td>
								<td><form:input path="mName" id="mName"
										class="textbox"/></td>
										<td><s:message code="label.employeeGroupId" /></td>
									<td><form:select path="employeeGroupId" id="employeeGroupId"
										class="select" >
										<form:option value="0">--Select--</form:option>
										<form:options  items="${employeeGroup}"/></form:select>
										
										</td>	
							</tr>
							<tr>
								<td><s:message code="label.gender"></s:message></td>
								<td><form:input path="gender"
										id="gender" class="textbox" /></td>
										<td><s:message code="label.dob" /></td>
									<td>
										<form:input path="dob"
										id="dob" class="textbox" />
										</td>	
										
							</tr>
							<tr>
								<td><s:message code="label.doj"/></td>
								<td><form:input path="doj" id="doj" class="textbox" /> </td>
									
										<td><s:message code="label.pAdd" /></td>
									<td>
										<form:input path="pAdd" id="pAdd" class="textbox"/>
										</td>
							</tr>
							<tr>
								<td><s:message code="label.pCity"/></td>
								<td><form:input path="pCity" id="pCity" class="textbox"/>
								</td>
								
									<td><s:message code="label.pState" /></td>
									<td><form:input path="pState" id="pState"
										class="textbox" /></td>
							</tr>
							<tr>
								<td><s:message code="label.pCountry" /></td>
								<td>
								<form:select path="pCountry" id="pCountry"
										class="select" >
										<form:option value="0">--Select--</form:option>
										<form:options  items="${country}"/></form:select>
								</td>
									<td><s:message code="label.tAdd" /></td>
									<td><form:input path="tAdd" id="tAdd"
										class="textbox" /></td>
								
							</tr>
							<form:hidden path="aid" id="aid" />
							<tr>
								<td><s:message code="label.tCity"/></td>
								<td><form:input path="tCity" id="tCity" class="textbox" /> </td>
									
										<td><s:message code="label.tState" /></td>
									<td><form:input path="tState" id="tState"  class="textbox"/>
										</td>
										
									
							</tr>
							<tr><td><s:message code="label.tCountry"/></td>
							<td>
							<form:select path="tCountry" id="tCountry" cssClass="select">
										<form:option value="0">--Select--</form:option>
										<form:options  items="${country}"/></form:select>
							</td>
							
							<td><s:message code="label.eMail" /></td>
									<td><form:input path="eMail" id="eMail"
										class="textbox" /></td>
							
							</tr>
							<tr>
								<td><s:message code="label.mobileNo" /><font
									color="red">*</font></td>
									<td><form:input path="mobileNo" id="mobileNo"
										class="textbox" /></td>
										<td><s:message code="label.phoneNo"/></td>
							<td><form:input path="phoneNo" id="phoneNo" class="textbox"/>
							
							 </td>
							
							</tr>
							<tr><td><s:message code="label.status"/></td>
							<td>
							<form:input path="status" id="status" class="textbox"/>
							 </td>
							
								
							</tr>
						
							<tr>
								<td colspan="2"><input type="submit"
									value="<s:message code="label.save"/>" class="btn btn-primary"
									id="sumbnid" /><input type="reset" class="btn btn-primary"
									value="<s:message code="label.reset"/>" /></td>
							</tr>

						</table>
					</form:form>
					<!-- ============================================End MaterialAdd==================================================================================== -->
					<!-- </td></tr></table>	 -->

				</div>
			</div>
			<div id="tabs-1" class="TabbedPanelsContent">
				<div align="left" class="TabbedPanelsContent">
					<!-- ============================================Begin EmployeeEdit==================================================================================== -->
					 <c:forEach var="employeeEditValues" items="${employeeEditValues }">
						<form:form action="employeeUpdate.mnt" name="materialFormEdit"
							id="employeeFormEdit" method="POST" commandName="employee">
								<table class="tableGeneral">
							<tr>
								<td colspan="2"><c:forEach var="a"
										items="${upDuplicatCheck }">
										<div class="alert-warning">
										<strong>Warning !</strong>
											 <c:out value="${a}"></c:out>
										</div>
									</c:forEach></td>
							</tr>
							<form:hidden path="aid" />

                              <form:hidden path="employeeId" />
							<tr>
								<td><s:message code="label.fName" /></td>
								<td><form:input path="fNameEdit" id="fNameEdit"
										class="textbox"/></td>
									<td><s:message code="label.lName" /></td>
									<td><form:input path="lNameEdit" id="lNameEdit"
										class="textbox" /></td>	
										
										
							</tr>

							<tr>
								<td><s:message code="label.mName" /></td>
								<td><form:input path="mNameEdit" id="mNameEdit"
										class="textbox"/></td>
										<td><s:message code="label.employeeGroupId" /></td>
									<td><form:select path="employeeGroupIdEdit" id="employeeGroupIdEdit"
										class="select" >
										<form:option value="0">--Select--</form:option>
										<form:options  items="${employeeGroup}"/></form:select>
										
										</td>	
							</tr>
							<tr>
								<td><s:message code="label.gender"></s:message></td>
								<td><form:input path="genderEdit"
										id="genderEdit" class="textbox" /></td>
										<td><s:message code="label.dob" /></td>
									<td>
										<form:input path="dobEdit"
										id="dobEdit" class="textbox" />
										</td>	
										
							</tr>
							<tr>
								<td><s:message code="label.doj"/></td>
								<td><form:input path="dojEdit" id="dojEdit" class="textbox"/> </td>
									
										<td><s:message code="label.pAdd" /></td>
									<td>
										<form:input path="pAddEdit" id="pAddEdit" class="textbox"/>
										</td>
							</tr>
							<tr>
								<td><s:message code="label.pCity"/></td>
								<td><form:input path="pCityEdit" id="pCityEdit" class="textbox" />
								</td>
								
									<td><s:message code="label.pState" /></td>
									<td><form:input path="pStateEdit" id="pStateEdit"
										class="textbox" /></td>
							</tr>
							<tr>
								<td><s:message code="label.pCountry"/></td>
								<td>
								<form:select path="pCountryEdit" id="pCountryEdit"
										class="select" >
										<form:option value="0">--Select--</form:option>
										<form:options  items="${country}"/></form:select>
								</td>
									<td><s:message code="label.tAdd" /></td>
									<td><form:input path="tAddEdit" id="tAddEdit"
										class="textbox" /></td>
								
							</tr>
							<form:hidden path="aid" id="aid" />
							<tr>
								<td><s:message code="label.tCity"/></td>
								<td><form:input path="tCityEdit" id="tCityEdit" class="textbox"/> </td>
									
										<td><s:message code="label.tState" /></td>
									<td><form:input path="tStateEdit" id="tStateEdit" class="textbox"/>
										</td>
										
									
							</tr>
							<tr><td><s:message code="label.tCountry"/></td>
							<td>
							<form:select path="tCountryEdit" id="tCountryEdit" cssClass="select">
										<form:option value="0">--Select--</form:option>
										<form:options  items="${country}"/></form:select>
							</td>
							
							<td><s:message code="label.eMail" /></td>
									<td><form:input path="eMailEdit" id="eMailEdit"
										class="textbox" /></td>
							
							</tr>
							<tr><td><s:message code="label.phoneNo"/></td>
							<td><form:input path="phoneNoEdit" id="phoneNoEdit" class="textbox"/>
							
							 </td>
							
								<td><s:message code="label.mobileNo" /><font
									color="red">*</font></td>
									<td><form:input path="mobileNoEdit" id="mobileNoEdit"
										class="textbox" /></td>
							</tr>
							<tr><td><s:message code="label.status"/></td>
							<td>
							<form:input path="statusEdit" id="statusEdit" class="textbox"/>
							 </td>
							
								
							</tr>
						
							<tr>
								<td colspan="2"><input type="submit"
									value="<s:message code="label.update"/>" class="btn btn-primary"
									id="updateBtn" /><input type="reset" class="btn btn-primary"
									value="<s:message code="label.reset"/>" /></td>
							</tr>

						</table>
						</form:form>
					</c:forEach> 
				</div>
				<!-- ============================================End MaterialEdit==================================================================================== -->
			</div>
		</div>
		</div>
</body>
</html>




