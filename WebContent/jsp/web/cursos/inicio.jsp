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
	    	
	    	var gridCursos, gridCursos2, tabbar, tab_1, main_layout, areaTrabajoCursos, listado, opcionSeminarioOAsignatura;
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	<% String opcion = request.getParameter("opcion");%>
		    	opcionSeminarioOAsignatura="<%=opcion%>";
		    	
			    dhtmlxError.catchError("ALL",errorHandler);
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    listado = main_layout.cells('a');
			    areaTrabajoCursos = main_layout.cells('b');
			    
			    listado.setWidth(250);
			    if(opcionSeminarioOAsignatura == "seminarios") {
			    	listado.setText(["<strong><bean:message key="label.seminarios" /></strong>"]);
			    }
			    else if(opcionSeminarioOAsignatura == "asignaturas") {
			    	listado.setText(["<strong><bean:message key="label.asignaturas" /></strong>"]);
			    }
			    	
			    toolbarUsuarios = listado.attachToolbar();
		    	toolbarUsuarios.setIconsPath('../skins/imgs/toolbar/');
		    	
		    	toolbarUsuarios.loadXML('../xml/toolbars/dhxtoolbar-usuarios.xml', function(){
		    		if(opcionSeminarioOAsignatura == "seminarios") {
		    			toolbarUsuarios.setItemText('new',"<bean:message key="button.create.seminario"/>");
		    		}
		    		else if(opcionSeminarioOAsignatura == "asignaturas") {
		    			toolbarUsuarios.setItemText('new',"<bean:message key="button.create.asignatura"/>");
		    		}
		    		toolbarUsuarios.setItemText('delete',"<bean:message key="button.eliminar"/>");
		    		toolbarUsuarios.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
		    		<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >	    	
						toolbarUsuarios.hideItem('new');
						toolbarUsuarios.hideItem('sep1');    	
						toolbarUsuarios.hideItem('delete');
						toolbarUsuarios.hideItem('sep2');
					</logic:notMatch>
			    
			    gridCursos = listado.attachGrid();
			    gridCursos.setIconsPath('../skins/imgs/');		    	
			    gridCursos.setHeader(["<strong><bean:message key="label.cursos" /></strong>"]);
			    gridCursos.setNoHeader(true);
			    //ro = readonly
			    //nombre codigo curso descripcion
			    gridCursos.setColTypes("ro,ro,ro,ro");
			    gridLeads.setColSorting('str,str,str,str,str');
			    // ??????????????
			    gridCursos.enableMultiselect(true);
			    gridCursos.init();
			    gridCursos.loadXML("../xml/forms/mis_asignaturas_componentes_form.xml");
			    gridCursos.attachEvent("onRowSelect",doOnRowSelected);
		    			    			    
			    gridCursosProcessor = new dataProcessor("gridcursos.do");
			    gridCursosProcessor.enableUTFencoding('simple');
			    gridCursosProcessor.init(gridCursos);	  
			    gridCursosProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});	
			    
			    
			  //areaTrabajoCursos.setText(nombreAsignatura);
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