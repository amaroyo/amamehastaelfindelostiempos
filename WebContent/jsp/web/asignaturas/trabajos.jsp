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
	    	var main_layout, idAsignatura, nombreAsignatura, gridProfesores,gridAlumnos, profesor,a,b, tabbar, idSession;
	    	
	    	dhtmlxEvent(window,"load",function() {
	    		
	    		//inicializo profesor a falso para tener un poco de seguridad
	    		profesor=false;
	    		
	    		<% String idAsignatura = request.getParameter("idAsignatura");%>
	    		idAsignatura="<%=idAsignatura%>";	
	    		<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
				idSession = <%=sessionIdUser%>;
	    		

				
				
	    		
	    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					profesor=true;
					main_layout = new dhtmlXLayoutObject(document.body, '2U');
		    		a = main_layout.cells('a');
		    		b = main_layout.cells('b');
		    		b.setWidth(250);
		    		a.hideHeader();
					b.setText('<bean:message key="label.opciones.alumno"/>');
					initProfesor();
				</logic:match>
		
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					profesor=false;
					main_layout = new dhtmlXLayoutObject(document.body, '2U');
		    		a = main_layout.cells('a');
		    		b = main_layout.cells('b');
		    		a.hideHeader();
		    		b.setText('<bean:message key="label.descripcion.practica"/>');
				    b.setWidth(750);
				    goGridAlumnos();
				</logic:notMatch>	
	    		
	    		
	    	});
	    	
	    	
	    	function initProfesor(){

				 
	    		
	    		tabbar = a.attachTabbar();
	    		
	    		var optsTrabajosCampo = dameTrabajosCampoAsignatura();
	    		var numTrabajosCampo = optsTrabajosCampo.length;
	    		
	    		
				
	    		for (var i=0; i<numTrabajosCampo;i++) {
	    			
	    			var nombreTrabajoCampo = optsTrabajosCampo[i].toString();
	    		  
	    			tabbar.addTab(nombreTrabajoCampo,nombreTrabajoCampo,'');
	    			//no se como hacer que sea activa y que ademas este seleccionada para 
	    			//disparar al metodo onSelect para que lo rellene con datos...
			    	//alert("Cargando...." + (i+1) + "/" + numTrabajosCampo + " prácticas. Por favor, espere...");
	    			if(i==0) tabbar.setTabActive(nombreTrabajoCampo);
	    			
	    			initTabContent(nombreTrabajoCampo);
	    		}
	    	}
	    	
		    	
	    	function initTabContent(nombreTrabajoCampo){
				
				var tab = tabbar.cells(nombreTrabajoCampo);
		    	var toolbarServicios = tab.attachToolbar();
		    	toolbarServicios.setIconsPath('../img/toolbar/');

		    	
		    	toolbarServicios.loadXML('../xml/toolbars/dhxtoolbar-trabajos-campo.xml', function(){
		    		toolbarServicios.setItemText('crearTrabajoCampo',"<bean:message key="button.crear.trabajo.campo"/>");
		    		toolbarServicios.setItemText('subirPractica',"<bean:message key="button.subir.practica"/>");
		    		toolbarServicios.setItemText('descargarTodos',"<bean:message key="button.descargar.casos"/>");
		    		toolbarServicios.setItemText('descargarTodosAlumno',"<bean:message key="button.descargar.casos.alumno"/>");
		    		toolbarServicios.setItemText('fechaLimite',"<bean:message key="button.fecha.limite"/>");
		    		toolbarServicios.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
		    	
			    	<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
			    		toolbarServicios.hideItem('subirPractica');
			    		toolbarServicios.hideItem('sep2');
			    		
			    	</logic:match>
			    	<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
			    	
			    		toolbarServicios.hideItem('crearTrabajoCampo');
			    		toolbarServicios.hideItem('sep1');
			    		toolbarServicios.hideItem('descargarTodos');
			    		toolbarServicios.hideItem('sep3');
			    		toolbarServicios.hideItem('descargarTodosAlumno');
			    		toolbarServicios.hideItem('sep4');
			    		toolbarServicios.hideItem('fechaLimite');
			    		toolbarServicios.hideItem('sep5');
			    	
			    	</logic:notMatch>
		    	});
		    	
		    	
		    	goGridProfesores(tab,nombreTrabajoCampo);
		    	
			}
		    	
			
			function subirPractica(){
				alert("subir Practica");
			}
			function descargarTodosAlumno(){
				alert("Descargar Todos Alumno");
			}
			
			function descargarTodos(){
				alert("Descargar Todos");
			}
			
			function subirCorrecciones(){
				alert("Subir Correcciones");
			}
			
			function fechaLimite(){
				alert("Fecha Limite");
			}
			
			function goActualizar() {
				var nombreTrabajoCampo = tabbar.getActiveTab();
				if (profesor) gridProfesores.clearAndLoad("gridUsuariosTrabajosCampoAsignatura.do?idAsignatura=" + idAsignatura + "&nombreTrabajoCampo=" + nombreTrabajoCampo);	 
				else gridAlumnos.clearAndLoad("gridTrabajosCampoUsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + idSession);		    	
		    			    	
		    }
			
			function goGridAlumnos(){
				
				var toolbarServiciosAlumnosRefrescar = a.attachToolbar();
				toolbarServiciosAlumnosRefrescar.setIconsPath('../img/toolbar/');

		    	
				toolbarServiciosAlumnosRefrescar.loadXML('../xml/toolbars/dhxtoolbar-trabajos-campo.xml', function(){
					toolbarServiciosAlumnosRefrescar.setItemText('crearTrabajoCampo',"<bean:message key="button.crear.trabajo.campo"/>");
					toolbarServiciosAlumnosRefrescar.setItemText('subirPractica',"<bean:message key="button.subir.practica"/>");
					toolbarServiciosAlumnosRefrescar.setItemText('descargarTodos',"<bean:message key="button.descargar.casos"/>");
					toolbarServiciosAlumnosRefrescar.setItemText('descargarTodosAlumno',"<bean:message key="button.descargar.casos.alumno"/>");
					toolbarServiciosAlumnosRefrescar.setItemText('fechaLimite',"<bean:message key="button.fecha.limite"/>");
					toolbarServiciosAlumnosRefrescar.setItemText('refresh',"<bean:message key="button.actualizar"/>");		    		
			    	toolbarServiciosAlumnosRefrescar.hideItem('subirPractica');
			    	toolbarServiciosAlumnosRefrescar.hideItem('sep2');			    	
			    	toolbarServiciosAlumnosRefrescar.hideItem('crearTrabajoCampo');
			    	toolbarServiciosAlumnosRefrescar.hideItem('sep1');
			    	toolbarServiciosAlumnosRefrescar.hideItem('descargarTodos');
			    	toolbarServiciosAlumnosRefrescar.hideItem('sep3');
			    	toolbarServiciosAlumnosRefrescar.hideItem('descargarTodosAlumno');
			    	toolbarServiciosAlumnosRefrescar.hideItem('sep4');
			    	toolbarServiciosAlumnosRefrescar.hideItem('fechaLimite');
			    	toolbarServiciosAlumnosRefrescar.hideItem('sep5');
			    	
		    	});
				
				
				gridAlumnos = a.attachGrid();
				gridAlumnos.setHeader(["<bean:message key="label.trabajo.campo" />","<bean:message key="label.fecha.limite" />","<bean:message key="label.corregido" />"]);
				gridAlumnos.setInitWidthsP("40,40,20");
				gridAlumnos.setColTypes("ro,ro,ro");
				gridAlumnos.setColSorting('str,str');
				gridAlumnos.enableMultiselect(false);
				gridAlumnos.init();
				
				
		    	
				
				
		    	var gridProcessorPro = new dataProcessor("gridTrabajosCampoUsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + idSession);
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridAlumnos);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
		    	
		    	gridAlumnos.attachEvent("onRowSelect",doOnRowSelectedAlumnos);
		    	
		    	gridAlumnos.clearAndLoad("gridTrabajosCampoUsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + idSession);
				
				
				
			}
			
			function doOnRowSelectedAlumnos(rowID,celInd){
				
				
				alert(rowID);
				
	    		var trabajoCampo = b.attachForm();
		    	
	    		trabajoCampo.loadStruct('../xml/forms/trabajo_de_campo.xml', function(){
	    			trabajoCampo.setItemLabel('data','<bean:message key="title.info.general.trabajo.campo"/>');
	    			trabajoCampo.setItemLabel('nombre','<bean:message key="label.nombre"/>');
	    			trabajoCampo.setItemLabel('descripcion','<bean:message key="label.descripcion.asignatura"/>');
	    			trabajoCampo.setItemLabel('fechaFin','<bean:message key="label.fecha.fin.estancia"/>');
	    			trabajoCampo.setItemLabel('descargarCorreccion','<bean:message key="button.descargar.correccion"/>');
	    			trabajoCampo.setItemLabel('descargarInformacion','<bean:message key="button.descargar.informacion"/>');
	    			trabajoCampo.setItemLabel('subirPractica','<bean:message key="button.subir.trabajo.campo"/>');
	    			trabajoCampo.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
		    		
	    			
					//Ponemos por defecto que los items no se puedan modificar, y luego con los permisos necesarios 
					//seran modificables.
		    		trabajoCampo.setReadonly('nombre', true);
		    		trabajoCampo.setReadonly('descripcion', true);
		    		trabajoCampo.setReadonly('fechaFin', true);
		    		trabajoCampo.hideItem('aceptar');
		    		trabajoCampo.disableItem('descargarCorreccion');
		    		trabajoCampo.disableItem('descargarInformacion');
		    		trabajoCampo.disableItem('subirPractica');
		    		
		    		
		    	/*	
					
		    		formAlumno.load('editarEstanciaUnidadClinica.do?idAlumno=' + idSessionUser + '&idAsignatura=' + idAsignatura);
					
		    		
		    		
		    		var cf = formAlumno.getCalendar("fechaFin");
		    		cf.attachEvent("onShow", function(){
		    		    cf.hide();
		    		});
		    		*/
		    		
	    	});
				
				
				
			}
			
			
			function goGridProfesores(tab,nombreTrabajoCampo){
				
								
				gridProfesores = tab.attachGrid();
		    	
				gridProfesores.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />","<bean:message key="label.fecha.limite" />","<bean:message key="label.enlace.practica" />","<bean:message key="label.enlace.correccion" />"]);
				gridProfesores.setColTypes("ro,ro,ro,ro,ro,ro");
		    	
				gridProfesores.setColSorting('str,str,str,str,str,str');
				gridProfesores.enableMultiselect(false);
				gridProfesores.init();
		    	
		    	var gridProcessorPro = new dataProcessor("gridUsuariosTrabajosCampoAsignatura.do?idAsignatura=" + idAsignatura + "&nombreTrabajoCampo=" + nombreTrabajoCampo);
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridProfesores);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});

		    	
		    	gridProfesores.attachEvent("onRowSelect",doOnRowSelected);
		    	
		    	gridProfesores.clearAndLoad("gridUsuariosTrabajosCampoAsignatura.do?idAsignatura=" + idAsignatura + "&nombreTrabajoCampo=" + nombreTrabajoCampo);
				
			}
			
			function doOnRowSelected(rowID,celInd){
				var miGrid = b.attachGrid();
			    miGrid.setIconsPath('../skins/imgs/');		    	
			    miGrid.setHeader(["<strong><bean:message key="label.mi.perfil" /></strong>"]);
			    //set readonly (ro)
			    miGrid.setColTypes("ro");
			    miGrid.setNoHeader(true);
			    miGrid.enableMultiselect(false);
			    miGrid.init();
			    miGrid.loadXML("../xml/forms/asignaturas_trabajos_opciones.xml");
			    miGrid.attachEvent("onRowSelect",doOnRowSelectedOptions); 
		    	
		    }
			
			function doOnRowSelectedOptions(rowID,celInd){
				
				if (rowID == "a") alert("Descargar");
		    	else if (rowID == "b") alert("Subir corrección");
		    	else alert("Cambiar la fecha de entrega");
			}
			
			
			function initRequest() {
	    	    if (window.XMLHttpRequest) {
	    	        xmlhttp = new XMLHttpRequest();
	    	    } else if (window.ActiveXObject) {
	    	        isIE = true;
	    	        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	    	    }
	    	    return xmlhttp;
	    	}
	    	
	    	
	    	function dameTrabajosCampoAsignatura(){
	    		var url = "trabajosCampoAsignaturas.do?idAsignatura=" + idAsignatura;
	    		var xmlhttp = initRequest();
	    		xmlhttp.onreadystatechange=function(){
	    			if (xmlhttp.readyState===4) {
	        	        if(xmlhttp.status===200) { //GET returning a response
	        	        	return createArrayFromXML(xmlhttp.responseXML);
	        	        }
	        	    }
	    		}
	    	    xmlhttp.open("GET",url,false);
	    	    xmlhttp.send(null);
	    	    return xmlhttp.onreadystatechange();
	    	}
	    	
	    	function createArrayFromXML(xml){
	    		var seminarios = xml.getElementsByTagName("trabajo");
	    		var id, nombre, seminario;
	    		var opts = new Array();
	    		for(var i=0;i<seminarios.length;i++) {
	    	        //id=seminarios[i].getElementsByTagName("id")[0].firstChild.nodeValue;
	    	        nombre=seminarios[i].getElementsByTagName("nombre")[0].firstChild.nodeValue;
	    	        //seminario=[id,nombre];
	    	       	opts[i] = nombre;
	    	    }
	    		return opts;

	    	}
			
			
	    	
	   </script>
	</head>
	<body>
	</body>
</html>