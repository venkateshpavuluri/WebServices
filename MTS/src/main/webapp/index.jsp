<html>
    <head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>My JSP 'debitNote.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="/resources/demos/style.css" />
<link href="accordionmenu.css" rel="stylesheet" type="text/css" />
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="js/jqueryui.css" />
<script src="js/jquery-1.7.2.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<script src="js/spin.js"></script>
<script src="js/spin.min.js"></script>

<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />   
    </head>
    <script>
    function rotate(){
    	alert("xfg");
 var opts = {
  lines: 13, // The number of lines to draw
  length: 32, // The length of each line
  width: 18, // The line thickness
  radius: 38, // The radius of the inner circle
  corners: 1, // Corner roundness (0..1)
  rotate: 50, // The rotation offset
  direction: 1, // 1: clockwise, -1: counterclockwise
  color: '#000', // #rgb or #rrggbb or array of colors
  speed: 1.5, // Rounds per second
  trail: 60, // Afterglow percentage
  shadow: false, // Whether to render a shadow
  hwaccel: false, // Whether to use hardware acceleration
  className: 'spinner', // The CSS class to assign to the spinner
  zIndex: 2e9, // The z-index (defaults to 2000000000)
  top: 'auto', // Top position relative to parent in px
  left: 'auto' // Left position relative to parent in px
};
var target = document.getElementById('target');
alert(target);
var spinner = new Spinner(opts).spin(target);
    }
</script>
 
 
    <body>

 <input type="text" name="io" id="foo" >
 <button id="add-loading" onclick="rotate()">Click Here</button>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
 <div id="target" align="center"></div>
 <table>
 <tr></tr>
 <tr id="target"></tr>
 </table>
    </body>
</html>
