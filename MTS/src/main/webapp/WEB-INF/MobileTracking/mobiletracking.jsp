<%@page import="com.mnt.mobiletracking.util.DbConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*,java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="js/jquery-1.9.1.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">

<title>Tracking</title>

<!-- <style type="text/css">
      html { height: 100% }
      body { height:80%;width:80%; margin: 80px; padding: 0 }
      #map-canvas { height: 100% }
    </style> -->

<script type="text/javascript">
	$(document).ready(
			function() {
				$('#imeino').change(
						function() {
							
						$.ajax({
								type : "POST",
								
								url : "MobileTracking",
								data : "imeino=" + $(this).val(),

									success : function(data) {
										load(data);

								},
								error : function(errorThrown){
								
								}
							});
						});

			});
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
				zoom : 5,
				center : new google.maps.LatLng(17.3660, 78.4760),

			};
			var map = new google.maps.Map(
					document.getElementById('map-canvas'), mapOptions);
			
			

			setMarkers(map, beaches);

			function setMarkers(map, locations) {
				for ( var i = 0; i < locations.length; i++) {
					var beach = locations[i];
					var myLatLng = new google.maps.LatLng(beach[1], beach[2]);
					//var letter = String.fromCharCode("1".charCodeAt(0) + i);
					var marker = new google.maps.Marker({
						position : myLatLng,
						map : map,
						icon : "http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld="+i+"|FF0000|000000",
						animation : google.maps.Animation.BOUNCE,
						title : beach[0],
						zIndex : beach[3],

					});

				}

			}
		}
	</script>
<script>
  $(function() {
    $( "#dt" ).datepicker();
  });
  </script>



<style>
html,body,#map-canvas {
	height: 100%;
	width: 100%;
	margin: 0px;
	padding: 0px
}
</style>



</head>




<body>
	
	<h1 align="center">
		<font color="blue">Mobile Tracker</font>
	</h1>
	<%!Connection connection = null;
	PreparedStatement ps, ps1 = null;
	ResultSet rs, rs1 = null; %>

	<%
		try {
			// String connectionURL = "jdbc:sqlserver://192.168.1.103:1433;databaseName=MobileTracking";
			
			
			connection = DbConnection.getConnection();
			// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
			//  connection = DriverManager.getConnection("jdbc:sqlserver://192.168.1.103:1433;databaseName=MobileTracking", "mnterpuser", "mnterpuser");
			ps = connection
					.prepareStatement("select distinct IMIE_Number from MobileGEOCode");
			rs = ps.executeQuery();
	%>

	<table>
		<tr>
			<td>IMEI Number :</td>
			<td><select style="width: 100px;" id="imeino" name="imeino"
				onchange="selectAjax">
					
					<%
						while (rs.next()) {
					%>

					<option value="<%=rs.getString(1)%>">
						<%=rs.getString(1)%>
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
	finally{
		try{
		if(connection!=null){
			connection.close();
		}
		if(ps!=null){
			ps.close();
		}
		if(ps1!=null){
			ps1.close();
		}
		if(rs!=null){
			rs.close();
		}
		if(rs1!=null){
			rs1.close();
		}
	
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
					%>
			</select></td>
			<td> <input type="hidden" id="dt" name="date"  />  
			<!-- <input	type="submit" onclick="load(data);" id="sub" value="Show Map" /> --></td>
		</tr>
	</table>
	<br></br>
	<div align="center" id="map-canvas"></div>
	
	<br></br>

	
</body>
</html>