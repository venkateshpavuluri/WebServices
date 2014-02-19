<!-- @author Venkatesh -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>


<link href="accordionmenu.css" rel="stylesheet" type="text/css" /> 
 <link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" /> 
 <link href="style.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" href="js/jqueryui.css" /> 
<script src="js/jquery-1.7.2.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/spin.js"></script>
<script src="js/spin.min.js"></script>
<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/jquery.signature.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" /> 
<link type="text/css" href="js/jquery.signature.css" rel="stylesheet" />


<style type="text/css">
.kbw-signature { width: 200px; height: 100px; }

	
.error {
	color: red;
	font-style: Bold;
}

img#preloaderAnimation
{
	position: relative;
	top: 50%;
	left: 50%;
}

div#preLoaderDiv
{
	position: fixed;
	_position: absolute;
	_top:expression(eval(document.body.scrollTop));
	width: 100%;
	height: 100%;
	top: 0px;
	left: 0px;
	z-index: 1000;
}

div#preLoaderDiv
{
	background-color: black;
	opacity: 0.7;
	filter: alpha(opacity=70);
}
</style>



<script type="text/javascript">
function checkLoad()
{
	alert("hello");
   if(document.getElementById("button"))
   {
	document.getElementById("preLoaderDiv").style.visibility = "hidden";
   }
}
</script>
<script type="text/javascript">
	setInterval("checkLoad()",100);
</script>
<script type="text/javascript">
$(document).ready(function() {

	$('#sig').signature();
		$('#sig').signature({syncField: '#signatureJSON'}); 
	$('#clear1').click(function() {
		$('#sig').signature('clear');
	});
	
	$('#sigTo').signature({disabled: true}); 
	 $('#sigTo').signature('draw', $('#signatureJSONEdit').val());
	 $('#clear').click(function() {
		$('#sigTo').signature('clear');
		$('#sigTo').signature({disabled: false}); 
		$('#signatureJSONEdit').val("");
		$('#sigTo').signature({syncField: '#signatureJSONEdit'}); 
	 });
	 
	 $('#sumbnid').click(function(event) {   
		 	$('#employeeformadd').validate({ 
				rules : {
						signature:{required:true},
						gender:{required:true},
						fName:{required:true}, 
						lName:{required:true},
						dob:{required:true},
						employeeGroupId:{required:true}
				},
			 	messages : {
					fName:"<font style='color: red;'><b>First</b></font>", 
					signature:"<font style='color: red;'><b>Signature is Required</b></font>",
					 gender:"<font style='color: red;'><b>Gender is Required</b></font>",
					lName:"<font style='color: red;'><b>Last Name is Required</b> </font>",
					dob:"<font style='color: red;'><b>Date Of Birth is Required</b></font>",
					employeeGroupId:"<font style='color: red;'><b>Employee Group is Required</b></font>"
				},  
			}); 
		}); 
	 $('#updateid').click(function(event) {   
		 	$('#employeeFormEdit').validate({ 
				rules : {
					
					signature:{required:true},
						genderEdit:{required:true},
						fNameEdit:{required:true}, 
						lNameEdit:{required:true},
						dobEdit:{required:true},
						employeeGroupIdEdit:{required:true},
				},
				messages : {
					fNameEdit:"<font style='color: red;'><b>First Name is Required</b></font>",
					signature:"<font style='color: red;'><b>Signature is Required</b></font>",
					genderEdit:"<font style='color: red;'><b>Gender is Required</b></font>",
					lNameEdit:"<font style='color: red;'><b>Last Name is Required</b> </font>",
					dobEdit:"<font style='color: red;'><b>Date Of Birth is Required</b></font>",
					employeeGroupIdEdit:"<font style='color: red;'><b>Employee Group is Required</b></font>"
				},
			}); 
		});
});
	 </script>     
<script type="text/javascript">
	function selectCountryCode() {

		//get the form values

		var country = $('#pCountry').val();
		

		$
				.ajax({

					type : "POST",
					url : "getCountryCode.mnt",
					data : "country=" + country,
					success : function(response) {
					
							
							var elem = document.getElementById("countryCode");
							elem.value =response;
						
					},

					error : function(e) {

						//alert('Error: ' + e);

					}

				});

	}
</script>


<script type="text/javascript">
	function selectCountryCodeEdit() {

		//get the form values

		var countryEdit = $('#pCountryEdit').val();
		$
				.ajax({

					type : "POST",
					url : "getCountryCodeEdit.mnt",
					data : "countryEdit=" + countryEdit,
					success : function(response) {
							var elem = document.getElementById("countryCodeEdit");
							elem.value =response;
						
					},

					error : function(e) {

						//alert('Error: ' + e);

					}

				});

	}
</script>



<script type="text/javascript">
	function doAjaxPost() {

		//get the form values

		var mobileno = $('#mobileno').val();
		var countryphCode = $('#countryCode').val();
		$
				.ajax({

					type : "POST",

					url : "employeeDuplicateCheck.mnt", 

					data : "mobileno=" + mobileno+ "&countryphCode="
					+ countryphCode,

					success : function(response) {
						
						var checkResponse = "Warning ! Mobile Number is already exists. Please try some other name";
						if (checkResponse == response) {
							document.getElementById("ProcessdescDupMessage").style.display = "block";
							$('#ProcessdescDupMessage').html(response);
							$('#mobileNoAjax').val("same");
							
						} else {
							document.getElementById("ProcessdescDupMessage").style.display = "none";
							$('#mobileNoAjax').val("");
						}
					},

					error : function(e) {

						//alert('Error: ' + e);
					}
				});
	}
</script>

<script type="text/javascript">
	function doAjaxPostEdit() {

		//get the form values

		var mobilenoEdit = $('#mobileNoEdit').val();
		var imieId= $('#imieNumberEdit').val();
		var countryCodeEdit=$('#countryCodeEdit').val();

		$
				.ajax({

					type : "POST",
					
					url : "employeeDuplicateCheckEdit.mnt",

					data : "mobilenoEdit=" +mobilenoEdit + "&imieId="
					+ imieId+ "&countryCodeEdit="
					+ countryCodeEdit,

					success : function(response) {

						var checkResponse = "Warning ! Mobile Number is already exists. Please try some other name";
						if (checkResponse == response) {
							document.getElementById("ProcessdescDupMessageEdit").style.display = "block";
							$('#ProcessdescDupMessageEdit').html(response);
							$('#mobileNoAjaxEdit').val("same");
							
						} else {
							document.getElementById("ProcessdescDupMessageEdit").style.display = "none";
							$('#mobileNoAjaxEdit').val("");
						}
					},

					error : function(e) {

						//alert('Error: ' + e);

					}

				});

	}
</script>
	
<script>
$(function() {
	$("#dob,#doj").datepicker();
	$("#dobEdit,#dojEdit").datepicker();
	
});
</script>
<script type="text/javascript">
function doconfirm(){
	alert("If Want to Delete Click Ok");

}

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

<script>
	$(document).ready(function() {
		$('#tdToogle').click(function() {
			$('#leftMenu').toggle('800');
		});
	});
</script>
<script>
	$(function() {
		$("#tabs,#tabss").tabs();
	});
</script>

 <script type="text/javascript">
	$(document).ready(function() {
		$('#add,#search').click(function(e) {
			$('#edit').hide();
			$('#signatureJSON').val('');
			$('#successmessage').hide();
			$('#savemessage').hide();

		});
	});
</script>

<!-- Horizantol scroll -->
<style type="text/css">
#scroll {
	overflow: auto;
	overflow-y: hidden;
	overflow-x: scroll;
	width: 100%;
}
</style>
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
		
		if ($('#advanceSearchHidden').val() == "1") {
			$('#advanceSearchDiv').show();
			$('#advanceSearchButtonId').show();
			$('#mainSearch').hide();
			$('#advanceSearch').hide();
			$('#basicSearch').show();
		}
	});
</script>
<script type="text/javascript">
	$(function() {
		$('#basicSearch').click(function() {
			$("#advanceSearchHidden").val("0");
			$('#mainSearch').show();
			$('#advanceSearchDiv').hide();
			$('#advanceSearch').show();
			$('#basicSearch').hide();
			return false;
		});
	});
</script>
</head>
<body>
	<div class="divUserDefault">
		<div class="PageTitle">Delivery Note</div>
		<div id="tabs" class="TabbedPanels">
			<ul class="TabbedPanelsTabGroup">
				<c:forEach var="editvalues" items="${employeeEditValues}">
					<c:set var="editvalues" value="${editvalues}"></c:set>
				
				<li class="TabbedPanelsTab"><a href="#tabs-1" id="edit"><s:message
								code="label.edit" /></a></li>

				</c:forEach>
				<li class="TabbedPanelsTab"><a href="#tabs-2" id="search"><s:message
								code="label.search" /></a></li>
				<li class="TabbedPanelsTab"><a href="#tabs-3" id="add"><s:message
								code="label.add" /></a></li>
			</ul>



			 <div id="tabs-2" class="TabbedPanelsContent">
				<div align="left">
				
					
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
							

							<tr>
								<td width="15%"><s:message code="label.employeeName" /></td>
								<td>
								  <form:input path="fName" cssClass="textbox" />
								<input type="submit"
									value="<s:message code="label.search"/>"
									class="btn btn-primary"/></td>
							</tr>
						</table>
						<div id="target"></div>
					</form:form>
					<!-- ============================================End Search ====================================== -->
					<c:forEach var="employeeSearch" items="${employeeSearch}">
						<c:set var="g" value="${employeeSearch}"></c:set>
					</c:forEach>
					<c:if test="${g!=null}">
					
						<!-- ============================================Begin DisplayTable ================================================= -->
					<div id="button">
						<display:table id="employeeRow" name="employeeSearch"
							requestURI="employeeSearch.mnt" pagesize="5" class="grid-table">

							<display:column property="fName"
								titleKey="label.fName" sortable="true"></display:column>
						
							<display:column property="doj" titleKey="label.doj"
								media="html" sortable="true" />
							<display:column property="mobileNumber"
								titleKey="label.mobileNo" media="html" sortable="true" />
                                <display:column property="imeiNumber"
								title="IMEI Number" media="html" sortable="true" />
								<display:column property="eMail"
								titleKey="label.eMail" sortable="true" />
								<display:column property="pAdd"
								titleKey="label.pAdd" sortable="true" />
								<display:column property="tAdd"
								titleKey="label.tAdd" sortable="true" />
							<display:column titleKey="label.edit" style="color:white">
								<a
									href="employeeEditHome.mnt?employeeCodeEdit=<c:out value="${employeeRow.employeeId}"/>"
									style="color: red" ><img src="images/Edit.jpg" width="20px"
									height="20px" /> </a>	
							</display:column>
							<%-- <display:column titleKey="label.delete" style="color:white">
								<a href="employeeDelete.mnt?employeeCodeDelete=<c:out value="${employeeRow.employeeId}"/>"
									style="color: red"
									onclick="return confirm('Do u want to Delete The Record?')">
									<img src="images/Delete.jpg" width="20px" height="20px" /></a>	
							</display:column> --%>
							<display:column titleKey="label.delete">
								<a href="employeeDelete.mnt?employeeCodeDelete=<c:out value="${employeeRow.employeeId}"/>"
									 onclick="return confirm('Do You Want to Delete the Selected Record ?')"><img
									src="images/Delete.jpg" width="20px" height="20px" /></a>
							</display:column>
							<display:column title="View Map"><a href="mtHome.mnt?employeeCodeEdit=<c:out value="${employeeRow.employeeId}"/>" target="_blank"  onclick="return popitup('mtHome.mnt?employeeCodeEdit=<c:out value="${employeeRow.employeeId}"/>')"><u><font color="blue">View Map</font></u></a></display:column>
							<display:column title="View Message History"><a href="seacrhMessageValues.mnt?mobileNumber=<c:out value="${employeeRow.mobileNumber}"/>&date=<c:out value="${employeeRow.doj}"/>" target="_blank"  onclick="return popitup('seacrhMessageValues.mnt?mobileNumber=<c:out value="${employeeRow.mobileNumber}"/>&date=<c:out value="${employeeRow.doj}"/> ')"><u><font color="blue">View Message History</font></u></a></display:column>
							<display:column title="View Calls History"><a href="searchCalls.mnt?mobileNumber=<c:out value="${employeeRow.mobileNumber}"/>&date=<c:out value="${employeeRow.doj}"/>" target="_blank"  onclick="return popitup('searchCalls.mnt?mobileNumber=<c:out value="${employeeRow.mobileNumber}"/>&date=<c:out value="${employeeRow.doj}"/> ')"><u><font color="blue">View Calls History</font></u></a></display:column>
							<display:setProperty name="paging.banner.placement"
								value="bottom" />
							<display:setProperty name="paging.banner.group_size" value="3" />
							<display:setProperty name="paging.banner.some_items_found"
								value="<span class='pagebanner'>{0} {1},listing {2} to {3}. </span>" />
						</display:table></div>
					</c:if>
				</div>
			</div> 
<div id="div3"></div>
			<div id="tabs-3" class="TabbedPanelsContent">
				<div align="left">

					<form:form action="employeeAdd.mnt" id="employeeformadd" name="employee"
						method="POST" commandName="employee">

						<table class="">
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
						
								<td><s:message code="label.fName" /><span class="required">*</span></td>
								<td><form:input path="fName" id="fName"
										class="textbox" ></form:input></td>
									<td><s:message code="label.lName" /><span class="required">*</span></td>
									<td><form:input path="lName" id="lName"
										class="textbox" /></td>
										
										
							</tr>

							<tr>
								<td><s:message code="label.mName" /></td>
								<td><form:input path="mName" id="mName"
										class="textbox"/></td>
										<td><s:message code="label.employeeGroupId" /><span class="required">*</span></td>
									<td><form:select path="employeeGroupId" id="employeeGroupId"
										class="select" >
										<form:option value="">--Select--</form:option>
										<form:options  items="${employeeGroup}"/></form:select>
										
										</td>	
							</tr>
							<tr>
								<td><s:message code="label.gender"></s:message><span class="required">*</span></td>
								<td>
										<s:message code="label.male"></s:message> <form:radiobutton path="gender" id="gender"  value="M"/>
									<s:message code="label.female"></s:message> <form:radiobutton path="gender" id="gender"   value="F"/>
										</td>
										<td><s:message code="label.dob" /><span class="required">*</span></td>
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
								<td><form:hidden path="countryCode" readonly="true" id="countryCode" />
								<form:select path="pCountry" id="pCountry"
										class="select" onchange="selectCountryCode()" >
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
							
							<tr><td><s:message code="label.status"/></td>
							<td>
							<form:input path="status" id="status" class="textbox"/>
							 </td>
							
								
							</tr>
							<tr>
							<td>Signature<span class="required">*</span></td>
									<td><div id="sig" ></div><input type="button" name="Clear" id="clear1" value="Clear" /> <form:hidden path="signature"
											id="signatureJSON" class="textbox"  />
											</td>
							</tr>
							</table>
							<div id="tabss" align="center">
							<ul>

								<li><a href="#tab1"><s:message code="label.employeeIMEIDetails"></s:message> </a></li>

							</ul>

							<!-- Tab-1 -->

							<div align="center">
								<!--  <div style="overflow-y:hidden;overflow-x:scroll;">  -->
									
									<!-- <div align="center"> -->
									
									 <script>
										var btrfqid = 1;
										$(function() {

											/* var currentText = $(this).find(":selected").text(); */
											
											
											var matid = $("#imieNumber"), mno = $("#mobileno"), hiddenrfqID = $("#hiddenIdrfqPopUp"),mobileNoAjax=$("#mobileNoAjax"),
											
											
											allFields = $([]).add(matid).add(mno).add(hiddenrfqID).add(mobileNoAjax),
											 tips = $(".validateTips");

											

											function updateTips(t) {
												tips.text(t).addClass(
														"ui-state-highlight");
												setTimeout(
														function() {
															tips.removeClass("ui-state-highlight",1500);}, 
															500);
											}

											function checkLength(o, n, min, max) {
												if (o.val().length > max
														|| o.val().length < min) {
													o
															.addClass("ui-state-error");
													updateTips("Length of "
															+ n
															+ " must be between "
															+ min + " and "
															+ max + ".");
													return false;
												} else {
													return true;
												}
											}
											function checkLength1(o, n) {
												if (o.val()=="") {
													o.addClass("ui-state-error");
													updateTips("Required");
													return false;
												} else {
													return true;
												}
											}
											function checkLength3(o, n) {
												if (o.val()=="same") {
													o.addClass("ui-state-error");
													updateTips("");
													return false;
												} else {
													return true;
												}
											}

											function checkRegexp(o, regexp, n) {
												if (!(regexp.test(o.val()))) {
													o
															.addClass("ui-state-error");
													updateTips(n);
													return false;
												} else {
													return true;
												}
											}

											$("#dialog-form-Rfq")
													.dialog(
															{
																autoOpen : false,
																height : 230,
																width : 400,
																modal : true,
																buttons : {
																	Submit : function() {
																		var bValid2 = true;
																		allFields.removeClass("ui-state-error");
																		 bValid2 = bValid2
																			&& checkLength1(
																					matid,
																					
																					"IMEI is Required");
																		  bValid2 = bValid2
																			&& checkRegexp(
																					matid,/^([0-9.])+$/i,
																					
																					"IMEI is Required  And Must be  Number");
                                 									
																		  bValid2 = bValid2
																			&& checkLength1(
																					mno,
																					
																					"Mobile Number is Required");
																		  bValid2 = bValid2
																			&& checkLength3(mobileNoAjax,
																					
																					"");
																		  
																		  
																	/* 	  bValid2 = bValid2
																			&& checkRegexp(
																					mno,/^([0-9.+])+$/i,
																					
																					"Mobile Number is Required  And Must be  Number");
																		 */
																
																	 	if (bValid2) {
																			//alert("hiddenid"+hiddenID.val());
																			
																			if (hiddenrfqID
																					.val() == "0"
																					|| hiddenrfqID
																							.val() == "") {

																				$("#RfqAdd tbody")
																						.append(
																								"<tr id="+btrfqid+">"
																										
																										+ "<td align='center'><input type='text' name='imieNumber' id='imieNumber"
																										+ btrfqid
																										+ "' value="
																										+ matid.val()
																										+ "  class='textbox'readonly/></td>"
																										+ "<td align='center'><input type='text' name='mobileNo' id='mobileno"
																										+ btrfqid
																										+ "' value="
																										+ mno.val()
																										+ "  class='textbox'readonly/></td>"
																										
																										+"<td><a href='#'  onclick='editRfq("
																										+ btrfqid
																										+ ")'><strong><img src='images/Edit.jpg' height='25px' width='25px' /></strong></a></td>"
																										+ "<td><a href='#'  onclick='removeRfq("
																										+ btrfqid
																										+ ")'><strong><img src='images/button_cancel.png' height='25px' width='25px'/></strong></a></td>"
																										+ "</tr>");
																				
																				btrfqid++;
																				$(
																						this)
																						.dialog(
																								"close");
																			}
																		
																			if (hiddenrfqID
																					.val() != "0") {
																				
																				$('#imieNumber'+ hiddenrfqID.val()).val(matid.val());
																				$('#mobileno'+ hiddenrfqID.val()).val(mno.val());
																				 
																				
																				
																				$(
																						'#hiddenIdrfqPopUp')
																						.val(
																								"0");
																				$(
																						this)
																						.dialog(
																								"close");
																			}
																			}
 																			},
																	Cancel : function() {
																		$(this)
																				.dialog(
																						"close");
																	}
																},
																close : function() {
																	allFields
																			.val(
																					"")
																			.removeClass(
																					"ui-state-error");
																}
															});

										
											$("#Rfqlineadd")
													.button()
													.click(
															function() {
																$("#dialog-form-Rfq")
																		.dialog(
																				"open");
																//alert("opened");
															});
										});
										function removeRfq(id) {
											//alert("removing row " + id);
											$('#' + id).remove();
										}
										function editRfq(id) {
											//alert("edit row " + id);
											$("#dialog-form-Rfq").dialog("open");
										  
											$('#imieNumber').val($('#imieNumber' + id).val());
											
											$('#mobileno').val($('#mobileno' + id).val());

											$('#hiddenIdrfqPopUp').val(id);
										
									}
									</script> 


									 <div id="dialog-form-Rfq" align="center" title="Add Details">
										<p class="validateTips" align="center" ><font color="red"></font></p>
										<table  cellspacing=0>
											
									
											
												<tr>
												<td>  <s:message code="label.mobileNo"/><span class="required">*</span> </td>
											 <td> <form:input path="mobileNo" id="mobileno"
													  class="textbox" onkeyup="doAjaxPost()"/> 
														<input type="hidden"
													name="hiddenIdrfqPopUp" id="hiddenIdrfqPopUp" value="0" />
													
													<input type="hidden" name="mobileNoAjax" id="mobileNoAjax"
													  class="textbox"/>
													</td>
													<!-- <td class="alert-warning" id="ProcessDupMessage"
									style="display: none;"></td> -->
											</tr>
											<tr>
												<td><s:message code="label.imieNumber"/><span class="required">*</span> </td>
												<td><form:input path="imieNumber" id="imieNumber" class="textbox"/></td>
											</tr>

										</table>
										<table>
										<tr  class="alert-warning" id="ProcessdescDupMessage"
									style="display: none;width:300px; "></tr>
										</table>
									</div> 


									
									<div id="users-contain-Rfq">
										<!-- class="ui-widget" -->
										<h3></h3>
										<table id="RfqAdd" class="grid-table">
											<thead>
												<tr>
													 <th><s:message code="label.imieNumber"></s:message></th>
													<th> <s:message code="label.mobileNo"/></th>
													
													<th><s:message code="label.edit"/></th>
													<th><s:message code="label.remove"/></th>
			
												</tr>
											</thead>
											<tbody>
												
											</tbody> 
										</table>
										<button id="Rfqlineadd" type="button">Add IMEI Details</button>
									</div>
									
								

								<!-- </div> -->
								</div>
						</div>
							<table>
						
							<tr>
								<td colspan="2"><input type="submit"
									value="<s:message code="label.save"/>" class="btn btn-primary"
									id="sumbnid" /><input type="reset" class="btn btn-primary"
									value="<s:message code="label.reset"/>" /></td>
							</tr>

						</table>
					
						

						
					</form:form>

				</div>
			</div>
			<div id="tabs-1" class="TabbedPanelsContent">
				<div align="left" class="TabbedPanelsContent">
					<form:form action="employeeUpdate.mnt" method="POST"
						commandName="employee" id="employeeFormEdit">
						
							
						<c:forEach var="employeeEditValues" items="${employeeEditValues }">
							<c:set var="edit" value="${employeeEditValues}"></c:set>
						</c:forEach>
						<c:if test="${edit!=null}">




							
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
								<td><s:message code="label.fName" /><span class="required">*</span></td>
								<td><form:input path="fNameEdit" id="fNameEdit"
										class="textbox"/></td>
									<td><s:message code="label.lName" /><span class="required">*</span></td>
									<td><form:input path="lNameEdit" id="lNameEdit"
										class="textbox" /></td>	
										
										
							</tr>
							<tr>
								<td><s:message code="label.mName" /></td>
								<td><form:input path="mNameEdit" id="mNameEdit"
										class="textbox"/></td>
										<td><s:message code="label.employeeGroupId" /><span class="required">*</span></td>
									<td><form:select path="employeeGroupIdEdit" id="employeeGroupIdEdit"
										class="select" >
										<form:option value="">--Select--</form:option>
										<form:options  items="${employeeGroup}"/></form:select>
										
										</td>	
							</tr>
							<tr>
								<td><s:message code="label.gender"/><span class="required">*</span></td>
								<td>
								<s:message code="label.male"/> <form:radiobutton path="genderEdit"  value="M" />
									<s:message code="label.female"/> <form:radiobutton path="genderEdit" value="F"/>
										</td>
										<td><s:message code="label.dob" /><span class="required">*</span></td>
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
							<form:hidden path="countryCodeEdit" id="countryCodeEdit" />
								<td><s:message code="label.pCountry"/></td>
								<td>
								<form:select path="pCountryEdit" id="pCountryEdit"
										class="select" onchange="selectCountryCodeEdit()">
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
							<tr><td><s:message code="label.status"/></td>
							<td>
							<form:input path="statusEdit" id="statusEdit" class="textbox"/>
							 </td>
							 
							 
						</tr>
						<tr>
						<td>Signature<span
												class="required">*</span></td>
									<td><div id="sigTo" ></div> <input type="button" name="Clear" id="clear" value="Clear" /><form:hidden path="signature"
											id="signatureJSONEdit" class="textbox"  />
											</td>
						</tr>
						</table>
						<div id="tabss" align="center">
								<ul>

									<li><a href="#tab1">Employee IMIE Details</a></li>

								</ul>
							<div align="center">
								<!--  <div style="overflow-y:hidden;overflow-x:scroll;">  -->
									
									<!-- <div align="center"> -->
									
									 <script>
										var btrfqid = 1;
										$(function() {

											/* var currentText = $(this).find(":selected").text(); */
											
											
											var matid = $("#imieNumberEdit"), mno = $("#mobileNoEdit"), hiddenrfqID = $("#hiddenIdrfqPopUpEdit"),mobileNoAjaxEdit=$("#mobileNoAjaxEdit"),
											
											
											allFields = $([]).add(matid).add(mno).add(hiddenrfqID).add(mobileNoAjaxEdit),
											 tips = $(".validateTips");

											

											function updateTips(t) {
												tips.text(t).addClass(
														"ui-state-highlight");
												setTimeout(
														function() {
															tips.removeClass("ui-state-highlight",1500);}, 
															500);
											}

											function checkLength(o, n, min, max) {
												if (o.val().length > max
														|| o.val().length < min) {
													o
															.addClass("ui-state-error");
													updateTips("Length of "
															+ n
															+ " must be between "
															+ min + " and "
															+ max + ".");
													return false;
												} else {
													return true;
												}
											}
											function checkLength1(o, n) {
												if (o.val()=="") {
													o.addClass("ui-state-error");
													updateTips("Required");
													return false;
												} else {
													return true;
												}
											}
											function checkLength3(o, n) {
												if (o.val()=="same") {
													o.addClass("ui-state-error");
													updateTips("");
													return false;
												} else {
													return true;
												}
											}

											
											function checkRegexp(o, regexp, n) {
												if (!(regexp.test(o.val()))) {
													o
															.addClass("ui-state-error");
													updateTips(n);
													return false;
												} else {
													return true;
												}
											}

											$("#dialog-form-RfqEdit")
													.dialog(
															{
																autoOpen : false,
																height : 230,
																width : 400,
																modal : true,
																buttons : {
																	Submit : function() {
																		var bValid2 = true;
																		allFields.removeClass("ui-state-error");
																		  bValid2 = bValid2
																			&& checkLength1(
																					matid,
																					
																					"IMEI is Required");
																		  bValid2 = bValid2
																			&& checkRegexp(
																					matid,/^([0-9.])+$/i,
																					
																					"IMEI is Required  And Must be  Number");
                                    									
																		  bValid2 = bValid2
																			&& checkLength1(
																					mno,
																					
																					"Mobile Number is Required");
																		  bValid2 = bValid2
																			&& checkLength3(
																					mobileNoAjaxEdit,
																					
																					"");
																		  
																		  
																		  /* bValid2 = bValid2
																			&& checkRegexp(
																					mno,/^([0-9.])+$/i,
																					
																					"Mobile Number is Required  And Must be  Number"); */
																
																	 	if (bValid2) {
																			//alert("hiddenid"+hiddenID.val());
																			
																			if (hiddenrfqID
																					.val() == "0"
																					|| hiddenrfqID
																							.val() == "") {

																				$("#RfqAddEdit tbody")
																						.append(
																								"<tr id="+btrfqid+">"
																										
																										+ " <s:bind path='employee.employeeImeiId' ><input type='hidden'name='employeeImeiId'  value='0'/></s:bind> <td align='center'><input type='text' name='imeiNumberEdit' id='imeiNumberEdit"
																										+ btrfqid
																										+ "' value="
																										+ matid.val()
																										+ "  class='textbox'readonly/></td>"
																										+ "<td align='center'><input type='text' name='mobileNoEdit' id='mobileNoEdit"
																										+ btrfqid
																										+ "' value="
																										+ mno.val()
																										+ "  class='textbox'readonly/></td>"
																										+"<td><a href='#'  onclick='editRfqEdit("
																										+ btrfqid
																										+ ")'><strong><img src='images/Edit.jpg' height='25px' width='25px'/></strong></a></td>"
																										+ "<td><a href='#'  onclick='removeRfqEdit("
																										+ btrfqid
																										+ ")'><strong><img src='images/button_cancel.png' height='25px' width='25px'/></strong></a></td>"
																										+ "</tr>");
																				
																				btrfqid++;
																				$(
																						this)
																						.dialog(
																								"close");
																			}
																		
																			if (hiddenrfqID
																					.val() != "0") {
																				
																				$('#imeiNumberEdit'+ hiddenrfqID.val()).val(matid.val());
																				$('#mobileNoEdit'+ hiddenrfqID.val()).val(mno.val());
																				
																				
																				$(
																						'#hiddenIdrfqPopUp')
																						.val(
																								"0");
																				$(
																						this)
																						.dialog(
																								"close");
																			}
																			}
 																			},
																	Cancel : function() {
																		$(this)
																				.dialog(
																						"close");
																	}
																},
																close : function() {
																	allFields
																			.val(
																					"")
																			.removeClass(
																					"ui-state-error");
																}
															});

										
											$("#RfqlineaddEdit")
													.button()
													.click(
															function() {
																$("#dialog-form-RfqEdit")
																		.dialog(
																				"open");
																//alert("opened");
															});
										});
										function removeRfq(id) {
											//alert("removing row " + id);
											$('#' + id).remove();
										}
										function editRfqEdit(id) {
											//alert("edit row " + id);
											//alert("hello"+$('#imeiNumberEdit' + id).val()+"----"+$('#mobileNoEdit' + id).val()+"---"+id);
											$("#dialog-form-RfqEdit").dialog("open");
										  
											$('#imieNumberEdit').val($('#imeiNumberEdit' + id).val());
											
											$('#mobileNoEdit').val($('#mobileNoEdit' + id).val());
											$('#hiddenIdrfqPopUpEdit').val(id);
										
									}
									</script> 


									 <div id="dialog-form-RfqEdit" align="center" title="Add Details">
										<p class="validateTips" align="center" ><font color="red"></font></p>
										<table class="table" cellspacing=0>
											
									
											
												<tr>
												<td> <s:message code="label.mobileNo"/><span class="required">*</span> </td>
												<td><form:input path="mobileNoEdit" id="mobileNoEdit"
													  class="textbox" onclick="doAjaxPostEdit()"/> 
														<input type="hidden"
													name="hiddenIdrfqPopUp" id="hiddenIdrfqPopUpEdit" value="0" />
													<input type="hidden" name="mobileNoAjaxEdit" id="mobileNoAjaxEdit"
													  class="textbox"/>
													</td>
											
											</tr>
											<tr>
												<td><s:message code="label.imieNumber"/><span class="required">*</span> </td>
												<td><form:input path="imieNumberEdit" id="imieNumberEdit" class="textbox"/></td>
											</tr>

										</table>
										<table>
										<tr  class="alert-warning" id="ProcessdescDupMessageEdit"
									style="display: none;width:300px; "></tr>
										</table>
									</div> 


									
									<div id="users-contain-RfqEdit">
										<!-- class="ui-widget" -->
										<h3></h3>
										<table id="RfqAddEdit" class="grid-table">
											<thead>
												<tr>
													<th><s:message code="label.imieNumber"/></th>
													<th><s:message code="label.mobileNo"/></th>
													<th><s:message code="label.edit"/></th>
													<th><s:message code="label.remove"/></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${employeechildvalues}"
												var="employeechildvalues"> 
                                              <s:bind path="employee.employeeImeiId">
															<input type="hidden" name="employeeImeiId" id="employeeImeiId${employeechildvalues.employeeImeiId}"
																value="${employeechildvalues.employeeImeiId}"  />
														</s:bind>
             
													
										<tr id="${employeechildvalues.employeeImeiId}">
									
													<td><s:bind path="employee.imeiNumberEdit">
															<input type="text" name="imeiNumberEdit"  class="textbox" id="imeiNumberEdit${employeechildvalues.employeeImeiId}"
																value="${employeechildvalues.imeiNumberEdit}"  />
														</s:bind></td>
													<td>	<s:bind path="employee.mobileNoEdit">
																	<input type="text" name="mobileNoEdit" class="textbox"
																		id="mobileNoEdit${employeechildvalues.employeeImeiId}"
																		value="${employeechildvalues.mobileNoEdit}" readonly/>
																</s:bind>

														<input type="hidden" name="Checkdelete${employeechildvalues.employeeImeiId}" id="${employeechildvalues.employeeImeiId}Checkdelete" value="0"/></td>
													
													
													<td><a href='#' id="${employeechildvalues.employeeImeiId}" onclick="editRfqEdit(this.id)"><strong><img src='images/Edit.jpg' height='25px' width='25px'/></strong></a></td>
													<td><a href='#' id="${employeechildvalues.employeeImeiId}" onclick="getID11(this)"><strong><img src='images/button_cancel.png' height='25px' width='25px'/></strong></a></td>
												</tr>
															<script type="text/javascript">
																function getID11(
																		link) {
																	//alert(link.id);
																	alert('Do You Want to Delete the Selected Record');
																
																
																	document
																			.getElementById(link.id
																					+ "Checkdelete").value = "1";
																	document
																			.getElementById(link.id).style.display = "none";
																}
																function editProcessDetailsInEdit(
																		link) {
																	
																	$("#dialog-form-RfqEdit")
																			.dialog(
																					"open");
																	$('#imieNumberEdit'+link.id)
																			.val(
																					$('#imieNumberEdit').val());
																	$('#mobileNoEdit')
																			.val($('#mobileNoEdit'+ link.id).val());
																	

																	$('#hiddenIdrfqeditPopUp')
																			.val(
																					link.id);
																	}
																</script>
											</c:forEach>
											</tbody>
										</table>
										<button id="RfqlineaddEdit" type="button"><s:message code="label.addIMEIDetails"/> </button>
									</div>
									</div>
							</div>
							<table>
								<tr>
									<td colspan="2" align="center"><input type="submit"
										value="Update" class="btn btn-primary" id="updateid" />
                                    </td> 
								</tr>
							</table>
						</c:if>
					</form:form>
				</div>
			</div> 
		</div>
	</div>
	</body></html>
		