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
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlxwindows.css">
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxwindows.js"></script>
	
	    <script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_dyn.js"></script>
	    

	    <script type="text/javascript">
	    
    		dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	var main_layout, idAsignatura, nombreAsignatura, gridProfesores,gridAlumnos, profesor,a,b, tabbar, idSession,miGrid,miGridActivado,anyoActual;
	    	
	    	dhtmlxEvent(window,"load",function() {
	    		
	    		//inicializo profesor a falso para tener un poco de seguridad
	    		profesor=false;
	    		miGridActivado=false;
	    		
	    		<% String idAsignatura = request.getParameter("idAsignatura");%>
	    		idAsignatura="<%=idAsignatura%>";	
	    		<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
				idSession = <%=sessionIdUser%>;
				<% String anyoActual = (String) session.getAttribute("anyoActual"); %>
			    anyoActual = "<%=anyoActual%>";

				
				
	    		
	    		<logic:notMatch scope="session" name="usuarioYPermisos" value="<grupo>4</grupo>" >
					profesor=true;
					
					main_layout = new dhtmlXLayoutObject(document.body, '2U');
		    		a = main_layout.cells('a');
		    		b = main_layout.cells('b');
		    		b.setWidth(250);
		    		a.hideHeader();
					b.setText('<bean:message key="label.opciones.alumno"/>');
					initProfesor();
				</logic:notMatch>
		
				<logic:match scope="session" name="usuarioYPermisos" value="<grupo>4</grupo>" >
					profesor=false;
					main_layout = new dhtmlXLayoutObject(document.body, '2U');
		    		a = main_layout.cells('a');
		    		b = main_layout.cells('b');
		    		a.hideHeader();
		    		b.setText('<bean:message key="label.descripcion.practica"/>');
				    b.setWidth(650);
				    goGridAlumnos();
				</logic:match>	
	    		
	    		
	    	});
	    	
	    	
	    	function initProfesor(){

	    		tabbar = a.attachTabbar();
	    		
	    		var optsTrabajosCampo = dameTrabajosCampoAsignatura();
	    		var numTrabajosCampo = optsTrabajosCampo.length;
	    		for (var i=0; i<numTrabajosCampo;i++) {
	    			
	    		
	    			var string = optsTrabajosCampo[i].toString();
	    			var parts = string.split(',');
	    			
	    			
	    			var idTrabajoInfo = parts[0];
	    			tabbar.addTab(idTrabajoInfo,parts[1],'');
	    			//no se como hacer que sea activa y que ademas este seleccionada para 
	    			//disparar al metodo onSelect para que lo rellene con datos...
			    	//alert("Cargando...." + (i+1) + "/" + numTrabajosCampo + " prácticas. Por favor, espere...");
	    			if(i==0) tabbar.setTabActive(idTrabajoInfo);
	    			
	    			initTabContent(idTrabajoInfo);
	    		}
	    		
	    		tabbar.attachEvent("onTabClick", function(id, lastId){
	    			goActualizarMiGrid();
	    		});
	    	}
	    	
		    	
	    	function initTabContent(idTrabajoInfo){
				
				var tab = tabbar.cells(idTrabajoInfo);
		    	var toolbarServicios = tab.attachToolbar();
		    	toolbarServicios.setIconsPath('../img/toolbar/');

		    	
		    	toolbarServicios.loadXML('../xml/toolbars/dhxtoolbar-trabajos-campo.xml', function(){
		    		toolbarServicios.setItemText('crearTrabajoCampo',"<bean:message key="button.crear.trabajo.campo"/>");
		    		toolbarServicios.setItemText('modificarTrabajoCampo',"<bean:message key="button.cambiar.trabajo.campo"/>");
		    		toolbarServicios.setItemText('subirPractica',"<bean:message key="button.subir.practica"/>");
		    		toolbarServicios.setItemText('descargarTodos',"<bean:message key="button.descargar.trabajos"/>");
		    		toolbarServicios.setItemText('descargarTodosAlumno',"<bean:message key="button.descargar.casos.alumno"/>");
		    		toolbarServicios.setItemText('fechaLimite',"<bean:message key="button.fecha.limite"/>");
		    		toolbarServicios.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
		    	
			    	<logic:notMatch scope="session" name="usuarioYPermisos" value="<grupo>4</grupo>" >
			    		toolbarServicios.hideItem('subirPractica');
			    		toolbarServicios.hideItem('sep3');
			    		toolbarServicios.hideItem('descargarTodosAlumno');
			    		toolbarServicios.hideItem('sep5');
			    		
			    	</logic:notMatch>
			    	<logic:match scope="session" name="usuarioYPermisos" value="<grupo>4</grupo>" >
			    	
			    		toolbarServicios.hideItem('crearTrabajoCampo');
			    		toolbarServicios.hideItem('sep1');
			    		toolbarServicios.hideItem('modificarTrabajoCampo');
			    		toolbarServicios.hideItem('sep2');
			    		toolbarServicios.hideItem('descargarTodos');
			    		toolbarServicios.hideItem('sep4');
			    		toolbarServicios.hideItem('descargarTodosAlumno');
			    		toolbarServicios.hideItem('sep5');
			    		toolbarServicios.hideItem('fechaLimite');
			    		toolbarServicios.hideItem('sep6');
			    	
			    	</logic:match>
			    	
			    	if(anyoActual=="falso"){
			    		
			    		toolbarServicios.hideItem('subirPractica');
			    		toolbarServicios.hideItem('sep3');
			    		toolbarServicios.hideItem('crearTrabajoCampo');
			    		toolbarServicios.hideItem('sep1');
			    		toolbarServicios.hideItem('modificarTrabajoCampo');
			    		toolbarServicios.hideItem('sep2');
			    		toolbarServicios.hideItem('descargarTodos');
			    		toolbarServicios.hideItem('sep4');
			    		toolbarServicios.hideItem('fechaLimite');
			    		toolbarServicios.hideItem('sep6');
			    	}
			    	
		    	});
		    	
		    	
		    	goGridProfesores(tab,idTrabajoInfo);
		    	
			}
		    	
	    	
	    	function modificarTrabajoCampo(){
	    		var idTrabajoInfo = tabbar.getActiveTab();
	    		var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("subir", 300,50, 500, 420);
				window.setText('<bean:message key="title.trabajo.de.campo" />');				
				window.setModal(true);
				window.centerOnScreen();
				
				var formNTC = window.attachForm();
				formNTC.loadStruct('../xml/forms/trabajo_de_campo.xml', function(){
					formNTC.setItemLabel('data','<bean:message key="title.info.general.trabajo.campo"/>');
					formNTC.setItemLabel('nombre','<bean:message key="label.nombre"/>');
					formNTC.setItemLabel('descripcion','<bean:message key="label.descripcion.asignatura"/>');
					formNTC.setItemLabel('fechaFin','<bean:message key="label.fecha.fin.estancia"/>');
					formNTC.setItemLabel('hora','<bean:message key="label.hora"/>');
					formNTC.setItemLabel('descargarCorreccion','<bean:message key="button.descargar.correccion"/>');
					formNTC.setItemLabel('descargarInformacion','<bean:message key="button.subir.informacion.adicional"/>');
					formNTC.setItemLabel('subirPractica','<bean:message key="button.subir.trabajo.campo"/>');
					formNTC.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
					formNTC.setItemLabel('eliminar','<bean:message key="button.eliminar.trabajo.campo"/>');
		    		formNTC.setRequired('nombre', true);		    		
		    		formNTC.setRequired('fechaFin', true);
		    		formNTC.setRequired('hora', true);
		    		formNTC.hideItem('descargarCorreccion');
		    		formNTC.hideItem('subirPractica');
		    		
		    		var correcto=false;
		    		var click=false;
		    		
		    		formNTC.attachEvent("onChange", function(name,value){
		    		   if(name == "hora"){
		    			   click=true;
		    			   var hora = formNTC.getItemValue("hora");
		    			   var sp = hora.split(":");
		    			   if(sp.length==2){
		    				   var isnum = /^\d+$/.test(sp[0]);
		    				   isnum = isnum && /^\d+$/.test(sp[1]);
		    				   if(isnum){
		    					   if (sp[0] < 0 || sp[0] > 24 || sp[1] < 0 || sp[1] > 60) {
			    					   formNTC.setNote("hora", { text: '<bean:message key="message.hora.correcta" />'} );
			    					   correcto=false;
		    				   		}
		    					   else {correcto=true;}
		    				   }
		    				   else {formNTC.setNote("hora", { text: '<bean:message key="message.hora.correcta" />'} );correcto=false;}
		    			   }
		    			   else {formNTC.setNote("hora", { text: '<bean:message key="message.hora.correcta" />'} );correcto=false;}
		    		   }
		    		});
		    		
		    		formNTC.load('verTrabajoCampo.do?idTrabajoInfo='+ idTrabajoInfo, function () {	
			    		formNTC.attachEvent("onButtonClick", function(id){
			    			if(id == "eliminar"){
			    				if (confirm("<bean:message key="message.eliminar.trabajo.campo"/>")) {
			    					window.close();
			    					var dhxWins2= new dhtmlXWindows();
				    				var window2 = dhxWins2.createWindow("subir", 300,50, 500, 170);
				    				window2.setText('<bean:message key="title.eliminar.trabajo.campo" />');				
				    				window2.setModal(true);
				    				window2.centerOnScreen();
				    				window2.attachURL("eliminarTrabajo.do?idTrabajoInfo="+ idTrabajoInfo);
				    				setTimeout(function(){initProfesor();},1000);
				    				
			    				}
			    			}
			    			if(!click || (click && correcto)){
								if(id == "descargarInformacion"){
						    		if (confirm("<bean:message key="message.subir.info.adicional2"/>")) {
						    			formNTC.send("crearTrabajoCampo.do?!nativeeditor_status=save&idTrabajoInfo="+ idTrabajoInfo,"post", function(loader, response) {
						    				window.close();
						    				var dhxWins2= new dhtmlXWindows();
						    				var window2 = dhxWins2.createWindow("subir", 300,50, 500, 170);
						    				window2.setText('<bean:message key="title.subir.practica" />');				
						    				window2.setModal(true);
						    				window2.centerOnScreen();
						    				window2.attachURL("subirArchivo.do?tipoConsulta=TrabajoCampoInfo" + "&idTrabajoInfo=" + response);
						    				setTimeout(function(){initProfesor();},1000);
											
										});
							    	}
								}
								else if(id == "aceptar"){
									formNTC.send("crearTrabajoCampo.do?!nativeeditor_status=save&idTrabajoInfo="+ idTrabajoInfo,"post", function(loader, response) {
										alert("<bean:message key="message.trabajo.de.campo.modificado.exito"/>");
										window.close();
										//var url = "trabajos.do";
										//location.href=url;
										setTimeout(function(){initProfesor();},1000);
									});
									
								}
			    			}
			    			else {alert("<bean:message key="message.algo.incorrecto"/>");}
			    			
		
				    	});
		    			
		    		});
				});
				
	    	}
			
			
			
			
			function descargarTodos(){
				var idTrabajoInfo = tabbar.getActiveTab();
				var accion = "descargarTodosTrabajosCampoAlumnos.do";
				accion += "?idAsignatura=" + idAsignatura;
				accion += "&idTrabajoInfo=" + idTrabajoInfo;
				location.href=accion;
			}
			
			
			
			function fechaLimite(){
				var idTrabajoInfo = tabbar.getActiveTab();
	    		var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("subir", 300,50, 500, 190);
				window.setText('<bean:message key="title.trabajo.de.campo" />');				
				window.setModal(true);
				window.centerOnScreen();
				
				var formNTC = window.attachForm();
				formNTC.loadStruct('../xml/forms/trabajo_de_campo.xml', function(){
					formNTC.setItemLabel('data','<bean:message key="title.cambiar.fecha.general"/>');
					formNTC.setItemLabel('nombre','<bean:message key="label.nombre"/>');
					formNTC.setItemLabel('descripcion','<bean:message key="label.descripcion.asignatura"/>');
					formNTC.setItemLabel('fechaFin','<bean:message key="label.fecha.fin.estancia"/>');
					formNTC.setItemLabel('hora','<bean:message key="label.hora"/>');
					formNTC.setItemLabel('descargarCorreccion','<bean:message key="button.descargar.correccion"/>');
					formNTC.setItemLabel('descargarInformacion','<bean:message key="button.subir.informacion.adicional"/>');
					formNTC.setItemLabel('subirPractica','<bean:message key="button.subir.trabajo.campo"/>');
					formNTC.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
					formNTC.setItemLabel('eliminar','<bean:message key="button.eliminar.trabajo.campo"/>');
		    		formNTC.setRequired('nombre', false);
		    		formNTC.hideItem('nombre');
		    		formNTC.hideItem('descripcion');
		    		formNTC.hideItem('descargarInformacion');
		    		formNTC.hideItem('eliminar');
		    		formNTC.setRequired('fechaFin', true);
		    		formNTC.setRequired('hora', true);
		    		formNTC.hideItem('descargarCorreccion');
		    		formNTC.hideItem('subirPractica');
		    		
		    		var correcto=false;
		    		var click = false;
		    		
		    		formNTC.attachEvent("onChange", function(name,value){
		    		   if(name == "hora"){
		    			   click=true;
		    			   var hora = formNTC.getItemValue("hora");
		    			   var sp = hora.split(":");
		    			   if(sp.length==2){
		    				   var isnum = /^\d+$/.test(sp[0]);
		    				   isnum = isnum && /^\d+$/.test(sp[1]);
		    				   if(isnum){
		    					   if (sp[0] < 0 || sp[0] > 24 || sp[1] < 0 || sp[1] > 60) {
			    					   formNTC.setNote("hora", { text: '<bean:message key="message.hora.correcta" />'} );
			    					   correcto=false;
		    				   		}
		    					   else {correcto=true;}
		    				   }
		    				   else {formNTC.setNote("hora", { text: '<bean:message key="message.hora.correcta" />'} );correcto=false;}
		    			   }
		    			   else {formNTC.setNote("hora", { text: '<bean:message key="message.hora.correcta" />'} );correcto=false;}
		    		   }
		    		});
		    		
		    		formNTC.load('verTrabajoCampo.do?idTrabajoInfo='+ idTrabajoInfo, function () {	
			    		formNTC.attachEvent("onButtonClick", function(id){
			    			
			    			if(!click || (click && correcto)){
								if(id == "aceptar"){
									formNTC.send("crearTrabajoCampo.do?!nativeeditor_status=save&idTrabajoInfo="+ idTrabajoInfo,"post", function(loader, response) {
										alert("<bean:message key="message.trabajo.de.campo.fecha.modificada.exito"/>");
										window.close();
										//var url = "trabajos.do";
										//location.href=url;
										setTimeout(function(){initProfesor();},1000);
									});
									
								}
			    			}
			    			else {alert("<bean:message key="message.algo.incorrecto"/>");}
			    			
		
				    	});
		    			
		    		});
				});
			}
			
			function crearTrabajoCampo(){
				var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("subir", 300,50, 500, 400);
				window.setText('<bean:message key="title.trabajo.de.campo" />');				
				window.setModal(true);
				window.centerOnScreen();
				
				var formNTC = window.attachForm();
				formNTC.loadStruct('../xml/forms/trabajo_de_campo.xml', function(){
					formNTC.setItemLabel('data','<bean:message key="title.info.general.trabajo.campo"/>');
					formNTC.setItemLabel('nombre','<bean:message key="label.nombre"/>');
					formNTC.setItemLabel('descripcion','<bean:message key="label.descripcion.asignatura"/>');
					formNTC.setItemLabel('fechaFin','<bean:message key="label.fecha.fin.estancia"/>');
					formNTC.setItemLabel('hora','<bean:message key="label.hora"/>');
					formNTC.setItemLabel('descargarCorreccion','<bean:message key="button.descargar.correccion"/>');
					formNTC.setItemLabel('descargarInformacion','<bean:message key="button.subir.informacion.adicional"/>');
					formNTC.setItemLabel('subirPractica','<bean:message key="button.subir.trabajo.campo"/>');
					formNTC.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
					formNTC.setItemLabel('eliminar','<bean:message key="button.eliminar.trabajo.campo"/>');
					formNTC.hideItem('eliminar');
		    		formNTC.setRequired('nombre', true);		    		
		    		formNTC.setRequired('fechaFin', true);
		    		formNTC.setRequired('hora', true);
		    		formNTC.hideItem('descargarCorreccion');
		    		formNTC.hideItem('subirPractica');
		    		
		    		var correcto = false;
		    		
		    		formNTC.attachEvent("onChange", function(name,value){
		    		   if(name == "hora"){
		    			   var hora = formNTC.getItemValue("hora");
		    			   var sp = hora.split(":");
		    			   if(sp.length==2){
		    				   var isnum = /^\d+$/.test(sp[0]);
		    				   isnum = isnum && /^\d+$/.test(sp[1]);
		    				   if(isnum){
		    					   if (sp[0] < 0 || sp[0] > 24 || sp[1] < 0 || sp[1] > 60) {
			    					   formNTC.setNote("hora", { text: '<bean:message key="message.hora.correcta" />'} );
			    					   correcto=false;
		    				   		}
		    					   else {correcto=true;}
		    				   }
		    				   else {formNTC.setNote("hora", { text: '<bean:message key="message.hora.correcta" />'} );correcto=false;}
		    			   }
		    			   else {formNTC.setNote("hora", { text: '<bean:message key="message.hora.correcta" />'} );correcto=false;}
		    		   }
		    		});
		    		
		    		formNTC.attachEvent("onButtonClick", function(id){
		    			if(correcto){
							if(id == "descargarInformacion"){
					    		if (confirm("<bean:message key="message.subir.info.adicional"/>")) {
					    			formNTC.send("crearTrabajoCampo.do?!nativeeditor_status=create&idAsignatura=" + idAsignatura + "&idProfesor=" + idSession,"post", function(loader, response) {
					    				window.close();
					    				var dhxWins2= new dhtmlXWindows();
					    				var window2 = dhxWins2.createWindow("subir", 300,50, 500, 170);
					    				window2.setText('<bean:message key="title.subir.practica" />');				
					    				window2.setModal(true);
					    				window2.centerOnScreen();
					    				window2.attachURL("subirArchivo.do?tipoConsulta=TrabajoCampoInfo" + "&idTrabajoInfo=" + response);
					    				setTimeout(function(){initProfesor();},1000);
										
									});
						    	}
							}
							else if(id == "aceptar"){
								formNTC.send("crearTrabajoCampo.do?!nativeeditor_status=create&idAsignatura=" + idAsignatura + "&idProfesor=" + idSession,"post", function(loader, response) {
									alert("<bean:message key="message.trabajo.de.campo.exito"/>");
									window.close();
									//var url = "trabajos.do";
									//location.href=url;
									setTimeout(function(){initProfesor();},1000);
								});
								
							}
		    			}
		    			else {alert("<bean:message key="message.algo.incorrecto"/>");}
		    			
	
			    	});
		    			
		    		
				});
				
				
				
			}
			
			function goActualizar() {
				
				if (profesor) {
					var idTrabajoInfo = tabbar.getActiveTab();
					gridProfesores.clearAndLoad("gridUsuariosTrabajosCampoAsignatura.do?idAsignatura=" + idAsignatura + "&idTrabajoInfo=" + idTrabajoInfo);	
					initProfesor();
				}
				else gridAlumnos.clearAndLoad("gridTrabajosCampoUsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + idSession);		    	
		    			    	
		    }
			
			function goGridAlumnos(){
				
				var toolbarServiciosAlumnosRefrescar = a.attachToolbar();
				toolbarServiciosAlumnosRefrescar.setIconsPath('../img/toolbar/');

		    	
				toolbarServiciosAlumnosRefrescar.loadXML('../xml/toolbars/dhxtoolbar-trabajos-campo.xml', function(){
					toolbarServiciosAlumnosRefrescar.setItemText('crearTrabajoCampo',"<bean:message key="button.crear.trabajo.campo"/>");
					toolbarServiciosAlumnosRefrescar.setItemText('modificarTrabajoCampo',"<bean:message key="button.cambiar.trabajo.campo"/>");
					toolbarServiciosAlumnosRefrescar.setItemText('subirPractica',"<bean:message key="button.subir.practica"/>");
					toolbarServiciosAlumnosRefrescar.setItemText('descargarTodos',"<bean:message key="button.descargar.casos"/>");
					toolbarServiciosAlumnosRefrescar.setItemText('descargarTodosAlumno',"<bean:message key="button.descargar.casos.alumno"/>");
					toolbarServiciosAlumnosRefrescar.setItemText('fechaLimite',"<bean:message key="button.fecha.limite"/>");
					toolbarServiciosAlumnosRefrescar.setItemText('refresh',"<bean:message key="button.actualizar"/>");		    		
			    	
					//ya se sabe que es un alumno
					toolbarServiciosAlumnosRefrescar.hideItem('subirPractica');
			    	toolbarServiciosAlumnosRefrescar.hideItem('sep2');			
			    	toolbarServiciosAlumnosRefrescar.hideItem('modificarTrabajoCampo');
			    	toolbarServiciosAlumnosRefrescar.hideItem('sep6');
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
				gridAlumnos.setHeader(["<bean:message key="label.trabajo.campo" />","<bean:message key="label.fecha.limite" />","<bean:message key="label.corregido" />","<bean:message key="label.enlace" />"]);
				gridAlumnos.setInitWidthsP("45,30,10,15");
				gridAlumnos.setColTypes("ro,ro,ro,ro");
				gridAlumnos.setColSorting('str,str,ro,ro');
				gridAlumnos.setColAlign("left,left,left,center,left");
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
				
				var parts = rowID.split("-");
				var corregido = parts[0];
				var bloqueado = parts[1];
				var info = parts[2];
				var idPortafolio = parts[3];
				var idTrabajoCampo = parts[4];
				
				
				var cellObj = gridAlumnos.cellById(rowID,celInd);
				if(celInd=='3' && cellObj.getValue()=="Descargar") {
					
					var accion = "descargarTrabajoCampoAlumno.do";
					accion += "?tipoConsulta="+"TrabajoCampoAlumno";
					accion += "&idPortafolio="+idPortafolio;
					accion += "&idTrabajoCampo="+idTrabajoCampo;
					location.href=accion;
				}
				
	    		var trabajoCampo = b.attachForm();
		    	
	    		trabajoCampo.loadStruct('../xml/forms/trabajo_de_campo.xml', function(){
	    			trabajoCampo.setItemLabel('data','<bean:message key="title.info.general.trabajo.campo"/>');
	    			trabajoCampo.setItemLabel('nombre','<bean:message key="label.nombre"/>');
	    			trabajoCampo.setItemLabel('descripcion','<bean:message key="label.descripcion.asignatura"/>');
	    			trabajoCampo.setItemLabel('fechaFin','<bean:message key="label.fecha.fin.estancia"/>');
	    			trabajoCampo.setItemLabel('hora','<bean:message key="label.hora"/>');
	    			trabajoCampo.setItemLabel('descargarCorreccion','<bean:message key="button.descargar.correccion"/>');
	    			trabajoCampo.setItemLabel('descargarInformacion','<bean:message key="button.descargar.informacion"/>');
	    			trabajoCampo.setItemLabel('subirPractica','<bean:message key="button.subir.trabajo.campo"/>');
	    			trabajoCampo.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
	    			trabajoCampo.setItemLabel('eliminar','<bean:message key="button.eliminar.trabajo.campo"/>');
	    			
	    			
					//ya sabemos que es un alumno
		    		trabajoCampo.setReadonly('nombre', true);
		    		trabajoCampo.setReadonly('descripcion', true);
		    		trabajoCampo.setReadonly('fechaFin', true);
		    		trabajoCampo.setReadonly('hora', true);
		    		trabajoCampo.setRequired('nombre', false);		    		
		    		trabajoCampo.setRequired('fechaFin', false);
		    		trabajoCampo.setRequired('hora', false);
		    		trabajoCampo.disableItem('descargarCorreccion');
		    		trabajoCampo.disableItem('descargarInformacion');
		    		trabajoCampo.disableItem('subirPractica');
		    		trabajoCampo.hideItem('eliminar');
		    		trabajoCampo.hideItem('aceptar');
		    		
		    		
		    		if(bloqueado=="F") trabajoCampo.enableItem('subirPractica');
		    		if(corregido=="T") trabajoCampo.enableItem('descargarCorreccion');
		    		if(info!="F") trabajoCampo.enableItem('descargarInformacion');
		    		
		    		
		    		trabajoCampo.load('verTrabajoCampo.do?idPortafolio=' + idPortafolio + '&idTrabajoCampo=' + idTrabajoCampo);
		    		
		    		var cf = trabajoCampo.getCalendar("fechaFin");
		    		cf.attachEvent("onShow", function(){
		    		    cf.hide();
		    		});
		    		
		    		
		    		trabajoCampo.attachEvent("onButtonClick", function(id){
	    				if (id == "subirPractica") {
	    					var dhxWinsA= new dhtmlXWindows();
	    					var windowAlumno = dhxWinsA.createWindow("subir", 300,50, 500, 170);
	    					windowAlumno.setText('<bean:message key="button.subir.trabajo.campo" />');				
	    					windowAlumno.setModal(true);
	    					windowAlumno.centerOnScreen();
	    					windowAlumno.attachURL("subirArchivo.do?tipoConsulta=TrabajoCampoPractica" + "&idPortafolio=" + idPortafolio + "&idTrabajoCampo=" + idTrabajoCampo);
	    					      
	    				}
	    				else if (id == "descargarInformacion"){
	    					var accion = "descargarTrabajoCampoInformacion.do";
							accion += "?tipoConsulta="+"TrabajoCampoInformacion";
							accion += "&idPortafolio="+idPortafolio;
							accion += "&idTrabajoCampo="+idTrabajoCampo;
							location.href=accion;
	    				}
	    				else if (id == "descargarCorreccion"){
	    					var accion = "descargarTrabajoCampoCorreccion.do";
							accion += "?tipoConsulta="+"TrabajoCampoCorreccion";
							accion += "&idPortafolio="+idPortafolio;
							accion += "&idTrabajoCampo="+idTrabajoCampo;
							location.href=accion;
	    				}
	    				
	    				
		    		});
		    		
		    		
	    	});
				
			}
			
			
			function goGridProfesores(tab,idTrabajoInfo){
				
								
				gridProfesores = tab.attachGrid();
		    	
				gridProfesores.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />","<bean:message key="label.fecha.limite" />","<bean:message key="label.enlace.practica" />","<bean:message key="label.enlace.correccion" />"]);
				gridProfesores.setColTypes("ro,ro,ro,ro,ro,ro");
		    	
				gridProfesores.setColSorting('str,str,str,str,str,str');
				gridProfesores.setColAlign("left,left,left,left,center,center");
				gridProfesores.enableMultiselect(false);
				gridProfesores.init();
		    	
		    	var gridProcessorPro = new dataProcessor("gridUsuariosTrabajosCampoAsignatura.do?idAsignatura=" + idAsignatura + "&idTrabajoInfo=" + idTrabajoInfo);
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridProfesores);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});

		    	
		    	gridProfesores.attachEvent("onRowSelect",doOnRowSelected);
		    	
		    	gridProfesores.clearAndLoad("gridUsuariosTrabajosCampoAsignatura.do?idAsignatura=" + idAsignatura + "&idTrabajoInfo=" + idTrabajoInfo);
				
			}
			
			function doOnRowSelected(rowIDs,celInd){
				
				var sp = rowIDs.split("-");
				var subido = sp[0];
				var corregido = sp[1];
				var idPortafolio = sp[2];
				var idTrabajoCampo = sp[3];
				

				miGrid = b.attachGrid();
				
			    miGrid.setIconsPath('../skins/imgs/');		    	
			    miGrid.setHeader(["<strong><bean:message key="label.mi.perfil" /></strong>"]);
			    //set readonly (ro)
			    miGrid.setColTypes("ro");
			    miGrid.setNoHeader(true);
			    miGrid.enableMultiselect(false);
			    miGrid.init();
			    miGrid.loadXML("../xml/forms/asignaturas_trabajos_opciones.xml");
			    miGridActivado=true;
			    if(anyoActual=="falso"){	
			    	setTimeout(function(){miGrid.cellById("b",0).setValue(" ");
			    	miGrid.cellById("d",0).setValue(" ");},100);				    	
			    }
			    miGrid.attachEvent("onRowSelect",function(rowId,cellIndex){
			    	if (rowId == "a" && subido == "T") {
			    		
			    		var accion = "descargarTrabajoCampoAlumno.do";
						accion += "?tipoConsulta="+"TrabajoCampoAlumno";
						accion += "&idPortafolio="+idPortafolio;
						accion += "&idTrabajoCampo="+idTrabajoCampo;
						location.href=accion;
			    		
			    	}
			    	else if (rowId == "b") {
			    		var dhxWinsA= new dhtmlXWindows();
    					var windowAlumno = dhxWinsA.createWindow("subir", 300,50, 500, 170);
    					windowAlumno.setText('<bean:message key="title.subir.correccion" />');				
    					windowAlumno.setModal(true);
    					windowAlumno.centerOnScreen();
    					windowAlumno.attachURL("subirArchivo.do?tipoConsulta=TrabajoCampoCorreccion" + "&idPortafolio=" + idPortafolio + "&idTrabajoCampo=" + idTrabajoCampo);
			    	}
			    	else  if (rowId == "c" && corregido == "T") {
			    		var accion = "descargarTrabajoCampoCorreccion.do";
						accion += "?tipoConsulta="+"TrabajoCampoCorreccion";
						accion += "&idPortafolio="+idPortafolio;
						accion += "&idTrabajoCampo="+idTrabajoCampo;
						location.href=accion;
			    	}
			    	else if (rowId == "d") {
			    		var idTrabajoInfo = tabbar.getActiveTab();
			    		var dhxWins= new dhtmlXWindows();
						var window = dhxWins.createWindow("subir", 300,50, 500, 190);
						window.setText('<bean:message key="title.trabajo.de.campo" />');				
						window.setModal(true);
						window.centerOnScreen();
						
						var formNTC = window.attachForm();
						formNTC.loadStruct('../xml/forms/trabajo_de_campo.xml', function(){
							formNTC.setItemLabel('data','<bean:message key="title.cambiar.fecha.general.alumno"/>');
							formNTC.setItemLabel('nombre','<bean:message key="label.nombre"/>');
							formNTC.setItemLabel('descripcion','<bean:message key="label.descripcion.asignatura"/>');
							formNTC.setItemLabel('fechaFin','<bean:message key="label.fecha.fin.estancia"/>');
							formNTC.setItemLabel('hora','<bean:message key="label.hora"/>');
							formNTC.setItemLabel('descargarCorreccion','<bean:message key="button.descargar.correccion"/>');
							formNTC.setItemLabel('descargarInformacion','<bean:message key="button.subir.informacion.adicional"/>');
							formNTC.setItemLabel('subirPractica','<bean:message key="button.subir.trabajo.campo"/>');
							formNTC.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
							formNTC.setItemLabel('eliminar','<bean:message key="button.eliminar.trabajo.campo"/>');
				    		formNTC.setRequired('nombre', false);
				    		formNTC.hideItem('nombre');
				    		formNTC.hideItem('descripcion');
				    		formNTC.hideItem('descargarInformacion');
				    		formNTC.hideItem('eliminar');
				    		formNTC.setRequired('fechaFin', true);
				    		formNTC.setRequired('hora', true);
				    		formNTC.hideItem('descargarCorreccion');
				    		formNTC.hideItem('subirPractica');
				    		
				    		var correcto=false;
				    		var click = false;
				    		
				    		formNTC.attachEvent("onChange", function(name,value){
				    		   if(name == "hora"){
				    			   click=true;
				    			   var hora = formNTC.getItemValue("hora");
				    			   var sp = hora.split(":");
				    			   if(sp.length==2){
				    				   var isnum = /^\d+$/.test(sp[0]);
				    				   isnum = isnum && /^\d+$/.test(sp[1]);
				    				   if(isnum){
				    					   if (sp[0] < 0 || sp[0] > 24 || sp[1] < 0 || sp[1] > 60) {
					    					   formNTC.setNote("hora", { text: '<bean:message key="message.hora.correcta" />'} );
					    					   correcto=false;
				    				   		}
				    					   else {correcto=true;}
				    				   }
				    				   else {formNTC.setNote("hora", { text: '<bean:message key="message.hora.correcta" />'} );correcto=false;}
				    			   }
				    			   else {formNTC.setNote("hora", { text: '<bean:message key="message.hora.correcta" />'} );correcto=false;}
				    		   }
				    		});
				    		
				    		formNTC.load('verTrabajoCampo.do?idTrabajoInfo='+ idTrabajoInfo, function () {	
					    		formNTC.attachEvent("onButtonClick", function(id){
					    			
					    			if(!click || (click && correcto)){
										if(id == "aceptar"){
											formNTC.send("crearTrabajoCampo.do?!nativeeditor_status=save&idPortafolio=" + idPortafolio + "&idTrabajoCampo="+ idTrabajoCampo + "&cambioFechaIndividual=Si","post", function(xml) {
												alert("<bean:message key="message.trabajo.de.campo.modificado.exito"/>");
												window.close();
												//var url = "trabajos.do";
												//location.href=url;
												setTimeout(function(){initProfesor();},1000);
											});
											
										}
					    			}
					    			else {alert("<bean:message key="message.algo.incorrecto"/>");}
					    			
				
						    	});
				    			
				    		});
						});
			    	}
			    	
			    });
		    }
			
			function goActualizarMiGrid(){
				gridProfesores.clearSelection();
				if(miGridActivado){
					miGrid.clearAll();
				}
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
	    	        id=seminarios[i].getElementsByTagName("id")[0].firstChild.nodeValue;
	    	        nombre=seminarios[i].getElementsByTagName("nombre")[0].firstChild.nodeValue;
	    	        seminario=[id,nombre];
	    	       	opts[i] = seminario;
	    	    }
	    		return opts;

	    	}
			
			
	    	
	   </script>
	</head>
	<body>
	</body>
</html>