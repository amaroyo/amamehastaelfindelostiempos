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
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/skins/dhtmlxform_dhx_skyblue.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlxtabbar.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlxlayout.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlxwindows.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlxcalendar.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/skins/dhtmlxwindows_dhx_skyblue.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/skins/dhtmlxcalendar_dhx_skyblue.css">
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxform.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_dyn.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_item_container.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_item_calendar.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxcalendar.js"></script>
	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxcontainer.js"></script>
	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxtabbar.js"></script>
	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxwindows.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxtabbar_start.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_dyn.js"></script>
		<script src="../js/dhtmlxSuite/patterns/dhtmlxlayout_pattern4l.js"></script>
		
		

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	var main_layout,idAsignatura, nombreAsignatura, gridProfesor, formAlumno, formUsuario, fechaIni, fechaFin, idSessionUser;
	    	
	    	dhtmlxEvent(window,"load",function() {
	    		
	    		
	    		<% String idAsignatura = request.getParameter("idAsignatura");%>
	    		idAsignatura="<%=idAsignatura%>";	
	    		<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
				idSessionUser = <%=sessionIdUser%>;
	    		
	    		
	    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					goProfesor();
				</logic:match>
			
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					goAlumno();
				</logic:notMatch>	
	    		
	    	});	
	    	
				function goAlumno(){
					
					main_layout = new dhtmlXLayoutObject(document.body, '1C');
		    		var a = main_layout.cells('a');
		    		a.hideHeader();
		    		formAlumno = a.attachForm();
			    	
		    		formAlumno.loadStruct('../xml/forms/estancia_form.xml', function(){
		    			formAlumno.setItemLabel('data','<bean:message key="title.info.general.estancia"/>');
		    			formAlumno.setItemLabel('hospital','<bean:message key="label.hospital.estancia"/>');
		    			formAlumno.setItemLabel('clinica','<bean:message key="label.clinica.estancia"/>');
		    			formAlumno.setItemLabel('turno','<bean:message key="label.turno"/>');
		    			formAlumno.setItemLabel('profesor','<bean:message key="label.profesor.asignatura"/>');
		    			formAlumno.setItemLabel('fechaIni','<bean:message key="label.fecha.ini.estancia"/>');
		    			formAlumno.setItemLabel('fechaFin','<bean:message key="label.fecha.fin.estancia"/>');
		    			formAlumno.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
			    		
		    			formAlumno.hideItem('aceptar');
		    			
						//Ponemos por defecto que los items no se puedan modificar, y luego con los permisos necesarios 
						//seran modificables.
			    		formAlumno.setReadonly('hospital', true);
			    		formAlumno.setReadonly('clinica', true);
			    		formAlumno.setReadonly('turno', true);
			    		formAlumno.setReadonly('profesor', true);
			    		formAlumno.setReadonly('fechaIni', true);
			    		formAlumno.setReadonly('fechaFin', true);
			    		formAlumno.hideItem('aceptar');
			    		
			    		
			    		
						
			    		formAlumno.load('editarEstanciaUnidadClinica.do?idAlumno=' + idSessionUser + '&idAsignatura=' + idAsignatura);
						
			    		var ci = formAlumno.getCalendar("fechaIni");
			    		
			    		ci.attachEvent("onShow", function(){
			    		    ci.hide();
			    		});
			    		
			    		var cf = formAlumno.getCalendar("fechaFin");
			    		cf.attachEvent("onShow", function(){
			    		    cf.hide();
			    		});
		    	});
		    		
		    		
					
				}
				
				
				function goProfesor(){
					
					main_layout = new dhtmlXLayoutObject(document.body, '2U');
					main_layout.setAutoSize("a;b");
		    		var a = main_layout.cells('a');
		    		var b = main_layout.cells('b');
		    		a.setText(["<strong><bean:message key="label.mis.alumnos.estancia" /></strong>"]);
		    		b.hideHeader();
		    		b.setWidth(500);
		    		gridProfesor = a.attachGrid();
					
		    		gridProfesor.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />","<bean:message key="label.centro.asociado" />","<bean:message key="label.turno" />","<bean:message key="label.mio" />"]);
		    		gridProfesor.setInitWidthsP("15,21,13,26,18,7");
		    		gridProfesor.setColTypes("ro,ro,ro,ro,ro,ro");
		    		gridProfesor.setColAlign("left,left,left,left,left,center");
			    	
		    		gridProfesor.enableMultiselect(false);
		    		gridProfesor.setColSorting('str,str,str,str,str');
		    		gridProfesor.init();
				    
				    
				   
					
				
				    var gridAlumnosProfesorProcessor = new dataProcessor("gridUsuariosEstancias.do?idAsignatura=" + idAsignatura);
					
				    gridAlumnosProfesorProcessor.enableUTFencoding('simple');
				    gridAlumnosProfesorProcessor.init(gridProfesor);	  
				    gridAlumnosProfesorProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
			    			dhtmlx.message(tag.firstChild.data,action,4000);
			    		}
			    	});	
					
				    
				    
				    gridProfesor.attachEvent("onRowSelect", function(row,ind){
						
				    	var idSelectedUser=row;

				    	var tabbar = b.attachTabbar();
				    	tabbar.addTab('tab_1','<bean:message key="title.datos.personales"/>','');
				    	var tab_1 = tabbar.cells('tab_1');
				    	tabbar.setTabActive('tab_1');
				    	formUsuario = tab_1.attachForm();
				    	formUsuario.loadStruct('../xml/forms/usuario_form.xml', function(){
				    		formUsuario.setItemLabel('data','<bean:message key="title.datos.personales"/>');
				    		formUsuario.setItemLabel('grupo','<bean:message key="label.group"/>');
				    		formUsuario.setItemLabel('nombre','<bean:message key="label.nombre"/>');
				    		formUsuario.setItemLabel('apellido1','<bean:message key="label.apellido1"/>');
				    		formUsuario.setItemLabel('apellido2','<bean:message key="label.apellido2"/>');
				    		formUsuario.setItemLabel('dni','<bean:message key="label.dni"/>');
				    		formUsuario.setItemLabel('telefono','<bean:message key="label.telefono"/>');
				    		formUsuario.setItemLabel('correo','<bean:message key="label.address.email"/>');	
				    		formUsuario.setItemLabel('foto','<bean:message key="label.foto"/>');	
				    		formUsuario.setItemLabel('fotoFile','<bean:message key="label.max.size"/>');
				    		formUsuario.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
				    		
				    		formUsuario.hideItem('aceptar');
				    		formUsuario.removeItem('fotoFile');
				    		
				    		formUsuario.forEachItem(function(id){
				    			switch(id) {
					    			case "grupo":{
					    				formUsuario.setReadonly(id,true);
					    				break;
					    			}
					    			case "nombre":{
					    				formUsuario.setReadonly(id,true);
					    				break;
					    			}
					    			case "apellido1":{
					    				formUsuario.setReadonly(id,true);
					    				break;
					    			}
					    			case "apellido2":{
					    				formUsuario.setReadonly(id,true);
					    				break;
					    			}
					    			case "dni":{
					    				formUsuario.setReadonly(id,true);
					    				break;
					    			}
					    			case "telefono":{
					    				formUsuario.setReadonly(id,true);
					    				break;
					    			}
					    			case "correo":{
					    				formUsuario.setReadonly(id,true);
					    				break;
					    			}
					    			default: break;
				    			}
				    		});
				    		
				    		//Aqui lo pondría con logic match para gente con permiso para modifiacar datos!
				    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>3</permiso>" >	
				    			formUsuario.forEachItem(function(id){
					    			switch(id) {
						    			case "grupo":{
						    				formUsuario.setReadonly(id,false);
						    				formUsuario.setRequired(id,true);
						    				break;
						    			}
						    			case "nombre":{
						    				formUsuario.setReadonly(id,false);
						    				formUsuario.setRequired(id,true);
						    				break;
						    			}
						    			case "apellido1":{
						    				formUsuario.setReadonly(id,false);
						    				formUsuario.setRequired(id,true);
						    				break;
						    			}
						    			case "apellido2":{
						    				formUsuario.setReadonly(id,false);
						    				break;
						    			}
						    			case "dni":{
						    				formUsuario.setReadonly(id,false);
						    				formUsuario.setRequired(id,true);
						    				break;
						    			}
						    			case "correo":{
						    				formUsuario.setReadonly(id,false);
						    				formUsuario.setRequired(id,true);
						    				break;
						    			}
						    			case "telefono":{
						    				formUsuario.setReadonly(id,false);
						    				break;
						    			}
						    			default: break;
					    			}
					    		});
				    			formUsuario.showItem('aceptar');
								
				    			formUsuario.enableLiveValidation(true);
					    		//foto LONGBLOB, 
					    		formUsuario.setFocusOnFirstActive();
								
							</logic:match>			    		
							
							formUsuario.load('editarusuario.do?idUsuario=' + idSelectedUser, function () {
								if(formUsuario.getItemValue("fotoImagen") == "") {
									var uriNoProfilePic = '../img/no-profile-pic.png';
									formUsuario.getContainer("foto").innerHTML = "<img src="+ uriNoProfilePic +" />";
								}
								else{
									var profilePic = formUsuario.getItemValue("fotoImagen");
									formUsuario.getContainer("foto").innerHTML = "<img src=data:image/jpg;base64,"+ profilePic +" style='width:105px;height:140px'/>";
								}
								formUsuario.attachEvent("onButtonClick", function(id){
									if (id == "aceptar") {
										formUsuario.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idSelectedUser ,"post", function(xml) {
											alert('<bean:message key="message.perfil.cambiado.exito"/>');
										});

									}
								});
								formUsuario.attachEvent("onEnter", function() {
									formUsuario.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idSelectedUser ,"post", function(xml) {
										alert('<bean:message key="message.perfil.cambiado.exito"/>');
									}); 
					    		});
								
								
							});//load
							
				    		
				    	});
				    	
				    	
				    	//INFORMACION DE LA ESTANCIA EN LA UNIDAD CLINICA SELECCIONADA
				    	tabbar.addTab('tab_2','<bean:message key="title.info.general.estancia"/>','');
				    	var tab_2 = tabbar.cells('tab_2');
				    	var form2 = tab_2.attachForm();
				    	form2.loadStruct('../xml/forms/estancia_form.xml', function(){
				    		form2.setItemLabel('data','<bean:message key="title.info.general.estancia"/>');
				    		form2.setItemLabel('hospital','<bean:message key="label.hospital.estancia"/>');
				    		form2.setItemLabel('clinica','<bean:message key="label.clinica.estancia"/>');
				    		form2.setItemLabel('turno','<bean:message key="label.turno"/>');
				    		form2.setItemLabel('profesor','<bean:message key="label.profesor.asignatura"/>');
				    		form2.setItemLabel('fechaIni','<bean:message key="label.fecha.ini.estancia"/>');
				    		form2.setItemLabel('fechaFin','<bean:message key="label.fecha.fin.estancia"/>');
				    		form2.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
				    		
				    		form2.hideItem('aceptar');
			    			
				    		
				    		form2.forEachItem(function(id){
				    			switch(id) {
					    			case "hospital":{
					    				form2.setReadonly(id,true);
					    				break;
					    			}
					    			case "clinica":{
					    				form2.setReadonly(id,true);
					    				break;
					    			}
					    			case "profesor":{
					    				form2.setReadonly(id,true);
					    				break;
					    			}
					    			
					    			default: break;
				    			}
				    		});
				    		
				    		
				    		//Aqui lo pondría con logic match para gente con permiso para modifiacar datos!
				    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>3</permiso>" >	
				    			form2.forEachItem(function(id){
				    				switch(id) {
					    			case "hospital":{
					    				form2.setReadonly(id,false);
					    				form2.setRequired(id,true);
					    				break;
					    			}
					    			case "clinica":{
					    				form2.setReadonly(id,false);
					    				form2.setRequired(id,true);
					    				break;
					    			}
					    			
					    			
					    			default: break;
				    			}
					    		});
				    			form2.showItem('aceptar');
								
				    			form2.enableLiveValidation(true);
					    		form2.setFocusOnFirstActive();
					    		
					    		
					    		form2.attachEvent("onButtonClick", function(id){
					    			
					    			var calendarIni, calendarFin, dateIni, dateFin;	
					    			
									calendarIni = form2.getCalendar("fechaIni");							
									dateIni = form2.getItemValue("fechaIni");
									fechaIni = calendarIni.getFormatedDate("%d/%m/%Y", dateIni);
				    										
									calendarFin = form2.getCalendar("fechaFin");							
									dateFin = form2.getItemValue("fechaFin");
									fechaFin = calendarFin.getFormatedDate("%d/%m/%Y", dateFin);	
				    			
					    		});	
								
							</logic:match>	

							
							form2.load('editarEstanciaUnidadClinica.do?idAlumno=' + idSelectedUser + '&idAsignatura=' + idAsignatura, function () {
								
								
								form2.attachEvent("onButtonClick", function(id){
									if (id == "aceptar") {
										if(fechasMenores(fechaIni,fechaFin)){	
											form2.send("actualizarEstanciaUnidadClinica.do?!nativeeditor_status=save&idAlumno=" + idSelectedUser + '&idAsignatura=' + idAsignatura,"post", function(xml) {
												alert('<bean:message key="message.estancia.clinica.exito"/>');
											});
										}
										else alert('<bean:message key="message.fechas.error"/>');

									}
								});
								form2.attachEvent("onEnter", function() {
									if(fechasMenores(fechaIni,fechaFin)){
										form2.send("actualizarEstanciaUnidadClinica.do?!nativeeditor_status=save&idAlumno=" + idSelectedUser + '&idAsignatura=' + idAsignatura,"post", function(xml) {
											alert('<bean:message key="message.estancia.clinica.exito"/>');
										}); 
									}
									else alert('<bean:message key="message.fechas.error"/>');
					    		});
								
								
							});//load
							
							
							
				    	});
				    	
				    	
				    	
				
				    	
					});//Grid Professor attach event
				    
				    buscar();
				}
				
				function buscar() {
					gridProfesor.clearAndLoad("gridUsuariosEstancias.do?idAsignatura=" + idAsignatura);		    	
			    }
	    		
	    
	    		function ucmEsEmail(correo) {
		    		if (getDomain(correo) == "ucm.es") {
		    			return true;
		    		}
		    		else {
		    			formUsuario.setNote("correo", { text: '<bean:message key="message.email.institucional" />'} );
		    			alert("false");
		    			return false;
		    		}
		    	}
		    	
		    	function getDomain(correo) {
				    var parts = correo.split('@');
				    return parts[parts.length - 1];
		    	}	    
		    	
		    	function fechasMenores(f1,f2){
		    		var partsIni = f1.split('/');
		    		var partsFin = f2.split('/');
		    		
		    		if (partsIni[2]>partsFin[2]) return false;
		    		else if (partsIni[2]==partsFin[2]) {
		    			if (partsIni[1]>partsFin[1]) return false;
		    			else if (partsIni[1]==partsFin[1]) {
		    				if (partsIni[0]>partsFin[0]) return false;
			    			else if (partsIni[0]==partsFin[0]) {
			    				return false;
			    			}
			    			else return true;
		    			}
		    			else return true;
		    		}
		    		else return true;
		    		
		    	}
	    	
	   </script>
	</head>
	<body>
	</body>
</html>