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
	    	var main_layout, idAsignatura, nombreAsignatura, gridProfesores,gridAlumnos,tab, profesor,a,b,idSession,idPortafolio,minitoolbarServicios,gridProfesoresAlumno;
	    	
	    	dhtmlxEvent(window,"load",function() {
	    		
	    		//inicializo profesor a falso para tener un poco de seguridad
	    		profesor=false;
	    		idPortafolio=-1;
	    		
	    		<% String idAsignatura = request.getParameter("idAsignatura");%>
	    		idAsignatura="<%=idAsignatura%>";	
	    		<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
				idSession = <%=sessionIdUser%>;
	    		

				
				
	    		
	    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					profesor=true;
					main_layout = new dhtmlXLayoutObject(document.body, '2U');
		    		a = main_layout.cells('a');
		    		b = main_layout.cells('b');
		    		b.setWidth(350);
		    		a.hideHeader();
					b.setText('<bean:message key="label.casos.clinicos.alumno"/>');
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
		    		
		    		toolbarServicios.hideItem('crearTrabajoCampo');
		    		toolbarServicios.hideItem('sep1');
		    		toolbarServicios.hideItem('fechaLimite');
		    		toolbarServicios.hideItem('sep5');
		    		
		    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
			    		toolbarServicios.hideItem('subirPractica');
			    		toolbarServicios.hideItem('sep2');
			    		toolbarServicios.disableItem('descargarTodosAlumno');
		    		
		    		</logic:match>
		    		<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
		    	
			    		toolbarServicios.hideItem('descargarTodos');
			    		toolbarServicios.hideItem('sep3');
			    		toolbarServicios.hideItem('descargarTodosAlumno');
			    		toolbarServicios.hideItem('sep4');
			    		
			    		
			    	
		    		</logic:notMatch>
		    	
		    	});
		    	
		    	if (profesor) goGridProfesores();
		    	else goGridAlumnos();
				
				
	    		
				
				
	    	});
	    	

			
			function subirPractica(){
				var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("subir", 300,50, 500, 150);
				window.setText('<bean:message key="title.subir.practica" />');				
				window.setModal(true);
				window.centerOnScreen();
				window.attachURL("subirArchivo.do?tipoConsulta=CasoClinico" + "&idAsignatura=" + idAsignatura + "&idAlumno=" + idSession + "&idTipo=CasoClinico");
				//goActualizar();
			}
			
			function descargarTodosAlumno(){
				if (idPortafolio == -1) alert("<bean:message key="message.error.seleccionar.alumno" />");
				else {
					var accion = "descargarCasoClinicosAlumno.do";
					accion += "?idPortafolio=" + idPortafolio;
					location.href=accion;
				}
			}
			
			function descargarTodos(){
				var accion = "descargarTodosCasosClinicosAlumnos.do";
				accion += "?idAsignatura=" + idAsignatura;
				location.href=accion;
			}
			
			function subirCorrecciones(){
				alert("Subir Correcciones");
			}
			
			function fechaLimite(){
				alert("Fecha Limite");
			}
			
			function goActualizar() {
				
				if (profesor) {
					gridProfesores.clearAndLoad("gridUsuariosCasosClinicosAsignatura.do?idAsignatura=" + idAsignatura);
					toolbarServicios.disableItem('descargarTodosAlumno');
				}
				else gridAlumnos.clearAndLoad("gridCasosClinicosUsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + idSession);		    	
		    	//tabbar.clearAll();		    	
		    }
			
			function goGridAlumnos(){
				
				gridAlumnos = a.attachGrid();
		    	
				gridAlumnos.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.fecha" />","<bean:message key="label.enlace" />"]);
				gridAlumnos.setColTypes("ro,ro,ro");
		    	
				gridAlumnos.setColSorting('str,str,str');
				gridAlumnos.enableMultiselect(false);
				gridAlumnos.init();
		    	
		    	var gridProcessorPro = new dataProcessor("gridCasosClinicosUsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + idSession);
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridAlumnos);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
		    	
		    	gridAlumnos.attachEvent("onRowSelect",doOnRowDescargasOptions);
		    	gridAlumnos.clearAndLoad("gridCasosClinicosUsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + idSession);
				
			}
			
			
			function doOnRowDescargasOptions(rowID,celInd){
				var cellObj = gridAlumnos.cellById(rowID,celInd);
				if(celInd=='2' && cellObj.getValue()=="Descargar") {
					var parts = rowID.split("-");
					//alert("Descargar Archivo con idPortafolio=" + parts[0] + " y idCasoClinico=" + parts[1]);
					var accion = "descargarCasoClinico.do";
					accion += "?idPortafolio="+parts[0];
					accion += "&idCasoClinico="+parts[1];
					location.href=accion;
				}
	
			}
			
			
			function goGridProfesores(){
				
				gridProfesores = a.attachGrid();
		    	
				gridProfesores.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />","<bean:message key="label.telefono" />","<bean:message key="label.user.email" />"]);
				gridProfesores.setColTypes("ro,ro,ro,ro,ro");
		    	
				gridProfesores.setColSorting('str,str,str,str,str');
				gridProfesores.enableMultiselect(false);
				gridProfesores.init();
		    	
		    	var gridProcessorPro = new dataProcessor("gridUsuariosCasosClinicosAsignatura.do?idAsignatura=" + idAsignatura);
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridProfesores);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});

		    	
		    	gridProfesores.attachEvent("onRowSelect",doOnRowSelected);
		    	
		    	gridProfesores.clearAndLoad("gridUsuariosCasosClinicosAsignatura.do?idAsignatura=" + idAsignatura);
				
			}
			
			function doOnRowSelected(rowID,celInd){
				
				idPortafolio=rowID;

				toolbarServicios.enableItem('descargarTodosAlumno');
				gridProfesoresAlumno = b.attachGrid();
				
		    	
				gridProfesoresAlumno.setHeader(["<bean:message key="label.nombre" />", "<bean:message key="label.fecha" />", "<bean:message key="label.enlace" />"]);
				gridProfesoresAlumno.setColTypes("ro,ro,ro");
		    	
				gridProfesoresAlumno.setColSorting('str,str,str');
				gridProfesoresAlumno.enableMultiselect(false);
				gridProfesoresAlumno.init();
		    	
		    	var gridProcessorPro = new dataProcessor("gridCasosClinicosAsignaturaUsuario.do?idPortafolio=" + rowID);
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridProfesoresAlumno);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});

		    	
		    	
		    	gridProfesoresAlumno.attachEvent("onRowSelect",doOnRowSelectedOptions);
		    	gridProfesoresAlumno.clearAndLoad("gridCasosClinicosAsignaturaUsuario.do?idPortafolio=" + rowID);
		    	
		    }
			
			function doOnRowSelectedOptions(rowID,celInd){
				var cellObj = gridProfesoresAlumno.cellById(rowID,celInd);
				if(celInd=='2' && cellObj.getValue()=="Descargar") {
					var parts = rowID.split("-");
					//alert("Descargar Archivo con idPortafolio=" + parts[0] + " y idCasoClinico=" + parts[1]);
					var accion = "descargarCasoClinico.do";
					accion += "?idPortafolio="+parts[0];
					accion += "&idCasoClinico="+parts[1];
					location.href=accion;
				}
	
			}
			
			
			
	    	
	    	
			
			
	    	
	   </script>
	</head>
	<body>
	</body>
</html>