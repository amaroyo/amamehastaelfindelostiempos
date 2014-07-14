<%@ include file="../../common/taglibs.jsp" %>

<html>
	<head>
	    <link rel="stylesheet" type="text/css" href="../css/estilos.css">
	    <link rel="stylesheet" type="text/css" href="../css/templates.css">
	    <link rel="stylesheet" type="text/css" href="../css/estilosMenu.css">
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    
	     <link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlx.css">
		

	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
	    		

	    <script type="text/javascript">	  	    
	    <% 
	    String accion = "/leads/subirFichero?idLead=" + (String)request.getParameter("idLead"); 
	    %>
		    function subir() {		    	
		    	document.forms[0].submit();
		    }
	 	</script>
	 	
	 </head>
	<body>
		<html:form action="<%=accion%>" enctype="multipart/form-data">
			<table>
				<tr>
					<td><label for="labelDescription">Description</label></td>
					<td>					
						<div id="div_descripcion" style="width:200px; height:30px;">
							<input type="text" name="descripcion"  size="55px">
						</div>
					</td>
				</tr>
				<tr>
					<td><bean:message key="label.fichero" /></td>
					<td><html:file property="fichero"></html:file> </td>										
					<td><html:button property="aceptar" onclick="subir()" ><bean:message key="button.upload" /></html:button>
				</tr>
			</table>
		</html:form>
	</body>
</html>