<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*,java.util.*,DB.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css"/>
<title>Tracking</title>

<!-- <style type="text/css">
      html { height: 100% }
      body { height:80%;width:80%; margin: 80px; padding: 0 }
      #map-canvas { height: 100% }
    </style> -->

<script type="text/javascript">
	
		function GotoAjax() {
var imeino = $('#imeino').val();

					
						$.ajax({
								type : "POST",
								
								url : "MobileTracking",
								data : "imeino=" + imeino,

									success : function(data) {
										load(data);

								},
								error : function(errorThrown){
									alert("eroor"+errorThrown);
								}
							});
						

			}

</script>
<script
		src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>

	<script type="text/javascript">
	var b,a,c;
		function load(data) {
		
			var beaches=[];
			
			$.each(data, function(idx, obj) {
				 a=obj.source;
				 b=obj.destination;
				 c=obj.latitude;
				 
				 beaches.push([c,a,b]);
				
				
			});
			
			
			
			var mapOptions = {
				zoom : 4,
				center : new google.maps.LatLng(17.3660, 78.4760),

			};
			var map = new google.maps.Map(
					document.getElementById('map-canvas'), mapOptions);
			
			

			setMarkers(map, beaches);

			function setMarkers(map, locations) {
				for ( var i = 0; i < locations.length; i++) {
					var beach = locations[i];
					var myLatLng = new google.maps.LatLng(beach[1], beach[2]);
					var letter = String.fromCharCode("A".charCodeAt(0) + i);
					var marker = new google.maps.Marker({
						position : myLatLng,
						map : map,
						icon : "http://maps.google.com/mapfiles/marker"
								+ letter + ".png",
						animation : google.maps.Animation.BOUNCE,
						title : beach[0],
						zIndex : beach[3],

					});

				}

			}
		}
	</script>




<style>
html,body,#map-canvas {
	height: 88%;
	width: 100%;
	margin: 0px;
	padding: 0px
}
</style>




</head>

<%@include file="../Home/Header.jsp" %>


<body  onload="GotoAjax()">
	
	

	<%	try {
			// String connectionURL = "jdbc:sqlserver://192.168.1.103:1433;databaseName=MobileTracking";
			Connection connection = null;
			PreparedStatement ps, ps1 = null;
			ResultSet rs, rs1 = null;
			DatabaseConnection db = new DatabaseConnection();
			connection = db.getConn();
	int id=Integer.parseInt(request.getParameter("employeeCodeEdit"));
			// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
			//  connection = DriverManager.getConnection("jdbc:sqlserver://192.168.1.103:1433;databaseName=MobileTracking", "mnterpuser", "mnterpuser");
			ps = connection
					.prepareStatement("select distinct IMIENumber,MobileNo from EmployeeIMIE where Employee_Id='"+ id + "' ");
			rs = ps.executeQuery();
	%>

	<table>
	
		<tr>
		
			<td><select  style="visibility: hidden;" id="imeino" name="imeino" onchange="GotoAjax()" >
					
					<%
						while (rs.next()) {
					%>

					<option value="<%=rs.getString(1)%>">
						<%=rs.getString(2)%>
					</option>

					<%
						}
							request.getAttribute("jsondata");
							//System.out.println(request.getAttribute("jsondata"));
					%>
					<%=request.getAttribute("jsondata")%>
					<%
						connection.close();
						} catch (Exception ex) {
							ex.printStackTrace();
							//out.println("Unable to connect to database");
						}
					%>
			</select></td>
			<td> <input type="hidden" id="dt" name="date" /> 
			<!-- <input	type="submit" onclick="load(data);" id="sub" value="Show Map" /> --></td>
		</tr>
	</table>
	
	<div align="center" id="map-canvas"></div>
	
	

	
</body>
<%@include file="../Home/Footer.jsp" %>
</html>