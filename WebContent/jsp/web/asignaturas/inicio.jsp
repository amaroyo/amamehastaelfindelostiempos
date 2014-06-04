<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>
<%@ include file="../../common/taglibs.jsp" %>
<%@  page import="java.util.Enumeration"%>
<%@ page import="es.oyssen.mrm.Const"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<html>
	<head>
	    <link rel="stylesheet" type="text/css" href="../css/estilos.css">
	    <link rel="stylesheet" type="text/css" href="../css/templates.css">
	    <link rel="stylesheet" type="text/css" href="../css/estilosMenu.css">
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlx.css">
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
	    <script src="../js/dhtmlxSuite/patterns/dhtmlxlayout_pattern4l.js"></script>
	    

	    <script type="text/javascript">
	    
    		dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	var miGrid, miGrid2, tabbar, tab_1, main_layout, areaTrabajoAsignaturas, menuOpciones,idAsignatura,nombreAsignatura;
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	<% String idAsignatura = request.getParameter("idAsignatura");
		    		String nombreAsignatura = request.getParameter("nombreAsignatura"); %>
		    	idAsignatura="<%=idAsignatura%>"; 
		    	nombreAsignatura="<%=nombreAsignatura%>"; 
		    	
			    dhtmlxError.catchError("ALL",errorHandler);
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    menuOpciones = main_layout.cells('a');
			    areaTrabajoAsignaturas = main_layout.cells('b');
			    
			    menuOpciones.setWidth(150);
			    menuOpciones.setText(["<strong><bean:message key="label.mis.asignaturas.componentes" /></strong>"]);
			    areaTrabajoAsignaturas.setText(nombreAsignatura);
			    //main_layout.setAutoSize("a;b", "b;c");
			    
			    
			    miGrid = menuOpciones.attachGrid();
			    //miGrid.setIconsPath('../skins/imgs/');		    	
			    miGrid.setHeader(["<strong><bean:message key="label.mis.asignaturas" /></strong>"]);
			    miGrid.setNoHeader(true);
			    //set readonly (ro)
			    miGrid.setColTypes("ro");
			    miGrid.enableMultiselect(false);
			    miGrid.init();
			    miGrid.loadXML("../xml/forms/mis_asignaturas_componentes_form.xml");
			    miGrid.attachEvent("onRowSelect",doOnRowSelected);
		    			    			    
		    });
		    
		    
		    function doOnRowSelected(rowID,celInd){
		        switch(rowID){
		        
		        	case "a": {goInformacion();break;}
		        	case "b": {goEstancia();break;}
		        	case "c": {goSeminarios();break;}
		        	case "d": {goTrabajos();break;}
		        	case "e": {goCasos();break;}
		        	case "f": {goDiario();break;}
		        	case "g": {goRubrica();break;}
		        }	
		    }
		    
		    function goRubrica(){
		    	areaTrabajoAsignaturas.attachURL("rubricas.do?idAsignatura="+idAsignatura);
		    }
		    
		    function goDiario(){
		    	areaTrabajoAsignaturas.attachURL("diario.do?idAsignatura="+idAsignatura);
		    }
		    
		    function goCasos(){
		    	areaTrabajoAsignaturas.attachURL("casos.do?idAsignatura="+idAsignatura);
		    }
		    
		    function goTrabajos(){
		    	areaTrabajoAsignaturas.attachURL("trabajos.do?idAsignatura="+idAsignatura);    	
		    }
		    
		    function goSeminarios(){
		    	areaTrabajoAsignaturas.attachURL("seminarios.do?idAsignatura="+idAsignatura);    	
		    }
		    
		    function goEstancia(){
		    	areaTrabajoAsignaturas.attachURL("estancia.do?idAsignatura="+idAsignatura);    	
		    }
		    
		    function goInformacion(){
		    	areaTrabajoAsignaturas.attachURL("informacion.do?idAsignatura="+idAsignatura);
		    }
		    
		  
        </script>
	</head>
	<body>
	</body>
</html>