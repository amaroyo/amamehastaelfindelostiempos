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
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxcommon.js"></script>
	   


	    <script type="text/javascript">
	    	
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	dhtmlxError.catchError("ALL",errorHandler);
		    	<% String opcion = request.getParameter("opcion");%>
		    	opcionSeminarioAsignatura="<%=opcion%>";
			    if (opcionSeminarioAsignatura == "seminarios") {
					document.location.href="./inicio-seminarios.do";
			    }
		    	else if (opcionSeminarioAsignatura == "asignaturas") {
		    		document.location.href="./inicio-asignaturas.do";
		    	}
		    });
		  
        </script>
	</head>
	<body>
	</body>
</html>