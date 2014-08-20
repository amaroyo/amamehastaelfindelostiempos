<%@ include file="../../common/taglibs.jsp" %>

<html>
	<head>
	    <link rel="stylesheet" type="text/css" href="../css/estilos.css">
	    <link rel="stylesheet" type="text/css" href="../css/templates.css">
	    <link rel="stylesheet" type="text/css" href="../css/estilosMenu.css">
	    <link rel="stylesheet" type="text/css" href="../skins/dhtmlx.css">
	    <script type="text/javascript" src="../skins/dhtmlx.js"></script>
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    

	    <script type="text/javascript">    
		    function importar() {
		    	document.forms[0].submit();
		    }
	 	</script>
	 	
	 </head>
	<body>
		<html:form action="/administrar/importarUsuarios" enctype="multipart/form-data">
			<table>
				<tr>
					<td><bean:message key="label.fichero" /></td>
					<td><html:file property="fichero"></html:file> </td>
					<td><html:button property="aceptar" onclick="importar()" ><bean:message key="button.importar" /></html:button>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>