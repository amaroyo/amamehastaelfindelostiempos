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
	    	var main_layout, idAsignatura, nombreAsignatura, gridProfesores,gridAlumnos,tab, profesor,a,b;
	    	
	    	dhtmlxEvent(window,"load",function() {
	    		
	    		//inicializo profesor a falso para tener un poco de seguridad
	    		profesor=false;
	    		
	    		<% String idAsignatura = request.getParameter("idAsignatura");%>
	    		idAsignatura="<%=idAsignatura%>";	
	    		<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
				var idSelectedUser = <%=sessionIdUser%>;
	    		

				
				
	    		
	    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					profesor=true;
					main_layout = new dhtmlXLayoutObject(document.body, '2U');
		    		a = main_layout.cells('a');
		    		b = main_layout.cells('b');
		    		b.setWidth(700);
		    		a.hideHeader();
					b.setText('<bean:message key="label.rubrica.alumno"/>');
				</logic:match>
		
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					profesor=false;
					main_layout = new dhtmlXLayoutObject(document.body, '1C');
		    		a = main_layout.cells('a');
		    		a.hideHeader();
				</logic:notMatch>	
	    		
	    		
	    		
				
				
		    	toolbarServicios = a.attachToolbar();
		    	toolbarServicios.setIconsPath('../img/toolbar/');

		    	
		    	toolbarServicios.loadXML('../xml/toolbars/dhxtoolbar-trabajos-campo.xml', function(){
		    		toolbarServicios.setItemText('crearTrabajoCampo',"<bean:message key="button.crear.trabajo.campo"/>");
		    		toolbarServicios.setItemText('subirPractica',"<bean:message key="button.subir.practica"/>");
		    		toolbarServicios.setItemText('descargarTodos',"<bean:message key="button.descargar.casos"/>");
		    		toolbarServicios.setItemText('descargarTodosAlumno',"<bean:message key="button.descargar.casos.alumno"/>");
		    		toolbarServicios.setItemText('fechaLimite',"<bean:message key="button.fecha.limite"/>");
		    		toolbarServicios.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    	});
		    	
		    	if (profesor) goGridProfesores();
		    	else goGridAlumnos();
				
				
	    		
				
				
	    	});
	    	

			
			function subirPractica(){
				alert("subir Practica");
			}
			
			function descargarTodos(){
				alert("Descargar Todos");
			}
			
			
			
			function fechaLimite(){
				alert("Fecha Limite");
			}
			
			function goActualizar() {
				if (profesor) gridProfesor.clearAndLoad("gridusuarios.do");	 
				else gridAlumno.clearAndLoad("gridusuarios.do");		    	
		    	tabbar.clearAll();		    	
		    }
			
			function goGridAlumnos(){
				
				alert("Falta rúbrica form");
				
			}
			
			function goGridProfesores(){
				
				gridProfesores = a.attachGrid();
		    	
				gridProfesores.setHeader(["<bean:message key="label.alumno" />","<bean:message key="label.dni" />","<bean:message key="label.fecha" />","<bean:message key="label.enlace" />"]);
				gridProfesores.setColTypes("ro,ro,ro,ro");
		    	
				gridProfesores.setColSorting('str,str,str,str');
				gridProfesores.enableMultiselect(false);
				gridProfesores.init();
		    	
		    	var gridProcessorPro = new dataProcessor("gridusuarios.do");
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridProfesores);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});

		    	
		    	gridProfesores.attachEvent("onRowSelect",doOnRowSelected);
		    	
		    	gridProfesores.clearAndLoad("gridusuarios.do");
				
			}
			
			function doOnRowSelected(rowID,celInd){
				
				alert("Falta rúbrica form");
		    	
		    }
			
			
			
			
			
	    	
	   </script>
	</head>
	<body>
	</body>
</html>