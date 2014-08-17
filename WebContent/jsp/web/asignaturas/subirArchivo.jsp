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
	    
	    String tipoConsulta = request.getParameter("tipoConsulta");
	    String accion = "/asignaturas/inicio";
	    if(tipoConsulta.equals("CasoClinico")) {
	    	String idAlumno = request.getParameter("idAlumno");
	    	String idAsignatura = request.getParameter("idAsignatura");
	    	accion = "/asignaturas/subirArchivoCasoClinico?tipoConsulta=" + tipoConsulta + "&idAlumno=" + idAlumno + "&idAsignatura=" + idAsignatura;
	    } 
	    else if(tipoConsulta.equals("TrabajoCampoInfo")){
	    	String idTrabajoInfo = request.getParameter("idTrabajoInfo");
	    	accion = "/asignaturas/subirArchivoTrabajoCampoInfo?tipoConsulta=" + tipoConsulta + "&idTrabajoInfo=" + idTrabajoInfo;
	    }
	    else if(tipoConsulta.equals("TrabajoCampoPractica")){
	    	String idPortafolio = request.getParameter("idPortafolio");	    	
	    	String idTrabajoCampo = request.getParameter("idTrabajoCampo");
	    	accion = "/asignaturas/subirArchivoTrabajoCampo?tipoConsulta=" + tipoConsulta + "&idPortafolio=" + idPortafolio + "&idTrabajoCampo=" + idTrabajoCampo;
	    }
	    else if(tipoConsulta.equals("TrabajoCampoCorreccion")){
	    	String idPortafolio = request.getParameter("idPortafolio");	    	
	    	String idTrabajoCampo = request.getParameter("idTrabajoCampo");
	    	accion = "/asignaturas/subirArchivoTrabajoCampoCorreccion?tipoConsulta=" + tipoConsulta + "&idPortafolio=" + idPortafolio + "&idTrabajoCampo=" + idTrabajoCampo;
	    }
	    else if(tipoConsulta.equals("TrabajoCampoCorreccionProfesor")){
	    	String idPortafolio = request.getParameter("idPortafolio");	    	
	    	String idTrabajoCampo = request.getParameter("idTrabajoCampo");
	    	tipoConsulta = "TrabajoCampoCorreccion";
	    	accion = "/asignaturas/subirArchivoTrabajoCampoCorreccion?tipoConsulta=" + tipoConsulta + "&idPortafolio=" + idPortafolio + "&idTrabajoCampo=" + idTrabajoCampo;
	    }
	    else if(tipoConsulta.equals("DiarioReflexivo")){
	    	String idAlumno = request.getParameter("idAlumno");
	    	String idAsignatura = request.getParameter("idAsignatura");
	    	accion = "/asignaturas/subirArchivoDiarioReflexivo?tipoConsulta=" + tipoConsulta + "&idAlumno=" + idAlumno + "&idAsignatura=" + idAsignatura;
	    }
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
					<td><label for="labelDescription"><bean:message key="label.fichero" /></label></td>
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