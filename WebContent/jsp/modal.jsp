<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript">
	function cargar() {
		var marco = document.getElementById("dialog");		
		marco.src = dialogArguments;		
	}
</script>
</head>
<body onload="cargar();">
	<iframe src="vacio.do" id="dialog" name="contenido" width="100%" height="100%" frameborder="0"></iframe>
</body>
</html>