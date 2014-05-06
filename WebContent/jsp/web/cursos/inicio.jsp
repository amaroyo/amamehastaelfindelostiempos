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
	    <link rel="stylesheet" type="text/css" href="../skins/dhtmlx.css">
	    <script type="text/javascript" src="../skins/dhtmlx.js"></script>
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    <script src="../skins/patterns/dhtmlxlayout_pattern4l.js"></script>
	    

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../skins/imgs/';
	    	
	    	var miGrid, miGrid2, tabbar, tab_1, main_layout, areaTrabajoCursos, listado, opcionSeminarioOAsignatura;
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	<% String opcion = request.getParameter("opcion");%>
		    	opcionSeminarioOAsignatura="<%=opcion%>";
		    	
			    dhtmlxError.catchError("ALL",errorHandler);
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    listado = main_layout.cells('a');
			    areaTrabajoCursos = main_layout.cells('b');
			    
			    listado.setWidth(250);
			    if(opcionSeminarioOAsignatura == "seminarios") {
			    	
			    }
			    else if(opcionSeminarioOAsignatura == "asignaturas") {
			    	
			    }
			    	listado.setText(["<strong><bean:message key="label.seminarios" /></strong>"]);
			    	listado.setText(["<strong><bean:message key="label.asignaturas" /></strong>"]);
			    areaTrabajoCursos.setText(nombreAsignatura);
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
		        
		        	case "a": goInformacion();
		        	case "b": goEstancia();
		        	case "c": goSeminarios();
		        	case "d": goCampo();
		        	case "e": goCasos();
		        	case "f": goDiario();
		        	case "g": goRubrica();
		        }	
		    }
		    
		    
		    function goEstanciaAlumno(){
		    	
		    	
		    }
		    
		    function goEstancia(){
		    	
		    	<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
    				goEstanciaProfesor();
    			</logic:match>
    			
    			<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
    				goEstanciaAlumno();
    			</logic:notMatch>	
		    }
		    
		    function goInformacion(){
		    	areaTrabajoAsignaturas.attachURL("informacion.do?idAsignatura="+idAsignatura);
		    }
		    
		  
        </script>
	</head>
	<body>
	</body>
</html>