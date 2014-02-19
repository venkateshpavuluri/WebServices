<%@ page language="java" contentType="text/html; charset=UTF-8"
	session="false" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>
<link href="Lstyle.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css"></link>
 <link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css' /> 
<script src="js/accordionmenu.js" type="text/javascript"></script>
<link href="accordionmenu.css" rel="stylesheet" type="text/css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<style type="text/css">
            #shade, #modal { display: none; }
            #shade { position: fixed; z-index: 100; top: 0; left: 0; width: 100%; height: 100%; }
            #modal { position: fixed; z-index: 101; top: 33%; left: 25%; width: 50%; }
            #shade { background: silver; opacity: 0.5; filter: alpha(opacity=50); }
        </style>
<style type="text/css">
.inner {
  position: absolute;
}
</style>
         
        <script>
  $(function() {
	  $('#forgot').click(function() {
	 
    $( "#dialog" ).dialog();
     modal.style.display=shade.style.display= 'block';
   /* document.getElementById('getpass').onclick= function() {
        modal.style.display=shade.style.display= 'none';
        //$("dialog").attr("disabled", "disabled"); 
        
      //  dialog.style.display='none';
    }; */
	  });
  });
  
  </script>
</head>

<body>
<form action="login.mnt" method="get">
<!--Header-->
<div id="header"> <a href="#" class="logo"><img alt="" src="images/logo.png"></a>
  <div id="header-right">
    <p class="username">Welcome to <span class="color">MTS</span></p>
    <!--<a href="#" class="logout">Logout</a>--> </div>
</div>

<div id="body-wrap">
  <div id="login-block">
   <div class="textbox bordertop">
   <input type="text" name="" id="" onfocus="if(this.value=='User Name...') this.value='';" onblur="if(this.value=='') this.value='User Name...';" value="User Name..." class="">
   </div>
   <div class="textbox borderbottom" style="margin-top:-1px;">
   <input type="password" name="" id="" onfocus="if(this.value=='password') this.value='';" onblur="if(this.value=='') this.value='password';" value="password" class="">
   </div>
   <input type="submit" value="Login" class="login-btn" />
  </div>
</div>



<!--Footer-->
<div id="footer" style="position:absolute; bottom:0;">
 <p class="left">All copy rights reserved <a href="#">Company Name</a>.</p>
 <p class="right">Powered by <a href="http://www.mntsoft.co.in" target="_blank">www.mntsoft.co.in</a>.</p>
</div>

</body>
</html>