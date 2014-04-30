<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="common/taglibs.jsp"%>
<html>
	<head>
		<title><bean:message key="desconexion.title" /></title>
		<link rel="stylesheet" type="text/css" href="../css/estilos.css">
		<script language="JavaScript" src="../js/general.js"></script>
		<script language="JavaScript">
        function successfull() {
            window.close();
        }
        function error(msg) {
            alert("<bean:message key="desconexion.mensaje"/>" + msg);
        }
        function desconectar() {
          loadURL("../logout.do");
        }
    </script>
	</head>
	<body class="nogeneral" onload="setTimeout('desconectar()', 500);">
		<table border="0" cellpadding="0" cellspacing="10">
			<tr>
				<td class="desplegable" height="10px" colspan="2">
					<bean:message key="desconexion.mensaje.cerrando" /><bean:message key="aplicacion.nombre" />
				</td>
			</tr>
			<tr>
				<td>
					<html:img src="../img/cargando.gif" />
				</td>
				<td>
					<bean:message key="descargando.mensaje" />
				</td>
			</tr>
		</table>
	</body>
</html>