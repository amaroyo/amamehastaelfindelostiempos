<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>
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
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_dyn.js"></script>
		

	<script type="text/javascript">	  	    
	    <% 
	    String idAlumno = request.getParameter("idAlumno");
	    String idAsignatura = request.getParameter("idAsignatura");
	    String accion = "/asignaturas/subirArchivoCasoClinico?idAlumno=" + idAlumno + "&idAsignatura=" + idAsignatura; 
	 
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
					<td><label for="labelDescription"><bean:message key="label.caso.clinico" /></label></td>
					<td>					
						<div id="div_descripcion" style="width:200px; height:30px;">
							<input type="text" name="nombre"  size="55px">
						</div>
					</td>
					
				</tr>
				<tr>
					<td><bean:message key="label.fichero" /></td>
					<td><html:file property="fichero"></html:file> </td>										
					<td><html:button property="aceptar" onclick="subir()" ><bean:message key="button.subir" /></html:button>
					
				</tr>
				<tr>
					<td width="15%" align="right">
					&nbsp;
					</td>
				</tr>
				<tr>
					<td><label for="labelDescription2"> 
					<strong><bean:message key="label.atencion" /></strong></label></td>
					<td><label for="labelDescription3"><bean:message key="label.max.size.type" /></label></td>
				</tr>
			</table>
		</html:form>
	</body>
</html>