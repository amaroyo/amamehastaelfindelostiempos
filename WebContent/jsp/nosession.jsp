<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="common/taglibs.jsp"%>

<jsp:useBean id="error" scope="request" class="java.lang.String" />

<html>
	<head>
		<title><bean:message key="nosession.title" /></title>
		<link rel="stylesheet" type="text/css" href="css/estilos.css">
		<script language="JavaScript">
        function cerrarVentana() {
	    		window.top.self.close();
          return;
        }
    </script>
	</head>

	<body>
		<br>&nbsp;<br>&nbsp;<br>&nbsp;
		<div class="error" align="center"><%=error%></div>
		<br>&nbsp;<br>&nbsp;
		<p align="center">
			<html:img srcKey="image.logo" />
		</p>
		<p align="center" class="texto2color">
			No tiene sesión de navegador o no tiene acceso al recurso pedido.<br>
			Por favor, vuelva a autenticarse para acceder a la aplicación.<br>
		</p>
		<p align="center" >
			<a href="javascript:cerrarVentana()"> <u>Cerrar
					Navegador</u> </a>
		</p>
	</body>
</html>