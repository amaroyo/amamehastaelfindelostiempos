<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>
<%@ include file="../../common/taglibs.jsp" %>
<%@ page import="java.util.Enumeration"%>
<%@ page import="es.oyssen.mrm.Const"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    
	  
	    
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
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlxgrid.js">
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
	    
	    	
		
	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxgrid.js"></script>
	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxgridcell.js"></script>
	    <script type="text/javascript" src="../js/dhtmlxSuite/excells/dhtmlxgrid_excell_grid.js"></script>	    
 		<script type="text/javascript" src="../js/dhtmlxSuite/excells/dhtmlxgrid_excell_acheck.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxgrid_deprecated.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxgrid_drag.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxgrid_export.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxgrid_filter.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxgrid_keymap_access.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxgrid_keymap_extra.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxgrid_keymap_excel.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxgrid_nxml.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxgrid_selection.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxgrid_srnd.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxgrid_start.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxgrid_validation.js"></script>
		
		
		

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	
	    	var miGrid, tabbar, tab_1,tab_2,tab_3,tab_4,tab_5,tab_6,tab_7,tab_8,tab_9, main_layout, form, b, a, gridAlumnoRealizadoSem, gridProfesoresTrab,
	    				gridProfesoresCasos, idAsignatura, idAlumno, idPortafolio,
	    				formRubrica, formAnexo, competencias, anexo, numeroCriterios,gridAnexos2,anyoActual,formUsuario;
	    	
	    	
	    	<% String anyoActual = (String) session.getAttribute("anyoActual"); %>
	    	anyoActual = "<%=anyoActual%>";
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
			    dhtmlxError.catchError("ALL",errorHandler);
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    a = main_layout.cells('a');
			    b = main_layout.cells('b');
			    
			    
			    a.setText("<strong><bean:message key="label.todos.mis.alumnos" /></strong>");
			    a.setWidth(380);
			    b.hideHeader();
			    
			    var toolbarSeleccionarAlumnos = a.attachToolbar();
			    toolbarSeleccionarAlumnos.setIconsPath('../img/toolbar/');

			    toolbarSeleccionarAlumnos.loadXML('../xml/toolbars/dhxtoolbar-seleccionar-alumnos.xml', function(){
			    	toolbarSeleccionarAlumnos.setItemText('seleccionarAlumnos',"<bean:message key="button.seleccionar.mis.alumnos"/>");		    	
			    	toolbarSeleccionarAlumnos.setItemText('refresh',"<bean:message key="button.actualizar"/>");
			    	if(anyoActual=="falso"){
			    		toolbarSeleccionarAlumnos.hideItem("sep1");
			    		toolbarSeleccionarAlumnos.hideItem('seleccionarAlumnos');
			    	}
			    
			    });
			    
			    miGrid = a.attachGrid();
			    miGrid.setIconsPath('../skins/imgs/');		   
			    
			    // INSERTAR EL LABEL CORRECTO PARA ASIGNATURAS DESPUES DEL UPDATE!!!
			    
			    miGrid.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />","<bean:message key="label.codigo.asignatura" />"]);
			    
			    //anchura de las columnas, en porcentaje. La suma tiene que ser igual a 100
			    miGrid.setInitWidthsP("22,45,18,15");
			    //alineacion del contenido en la columna
			    miGrid.setColAlign("left,left,left,left");
			    
			    miGrid.setColTypes("ro,ro,ro,ro");
			    
			    
			    miGrid.enableMultiselect(false);
			    miGrid.setColSorting('str,str,str,str');
			    miGrid.init();
		    	
				var gridProcessor = new dataProcessor("gridUsuariosProfesor.do?busqueda=no");
				gridProcessor.enableUTFencoding('simple');
				gridProcessor.init(miGrid);	  
				gridProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});		    	

			    miGrid.attachEvent("onRowSelect",doOnRowSelectedMiGrid);
			    
			    buscarMisAlumnos();
			    			    
		    });
		    
		   
		    function doOnRowSelectedMiGrid(rowID,celInd){
		    	
		    	var sp = rowID.split("-");
		    	idAlumno = sp[0];
		    	idAsignatura = sp[1];
		    	var nombreAsignatura = sp[2];
		    	idPortafolio = sp[3];
		    	
		    	var codigo = miGrid.cells(rowID,3).getValue();
		    	
		    	
		    	b.setText(codigo + " - " + nombreAsignatura);
		    	tabbar = b.attachTabbar();
		    	b.showHeader();
		    	
		    	tabbar.addTab('tab_1','<bean:message key="title.datos.personales"/>','');
		    	tab_1 = tabbar.cells('tab_1');
		    	tabbar.setTabActive('tab_1');
		    	goInformacion();
		    	
		    	tabbar.addTab('tab_2','<bean:message key="title.info.general.estancia"/>','');
		    	tab_2 = tabbar.cells('tab_2');
		    	goEstancia();
		    	
		    	tabbar.addTab('tab_3','<bean:message key="title.seminarios"/>','');
		    	tab_3 = tabbar.cells('tab_3');
		    	goSeminarios();
		    	
		    	tabbar.addTab('tab_4','<bean:message key="title.trabajos.campo"/>','');
		    	tab_4 = tabbar.cells('tab_4');
		    	goTrabajos();
		    	
		    	tabbar.addTab('tab_5','<bean:message key="title.casos.clinicos"/>','');
		    	tab_5 = tabbar.cells('tab_5');
		    	goCasos();
		    	
		    	tabbar.addTab('tab_6','<bean:message key="title.diario.reflexivo"/>','');
		    	tab_6 = tabbar.cells('tab_6');
		    	goDiarios();
		    	
		    	dameRubricaAsignatura(idAsignatura);
		    	
		    	tabbar.addTab('tab_7','<bean:message key="title.rubrica"/>','');
		    	tab_7 = tabbar.cells('tab_7');
		    	goRubricas();
				
				tabbar.addTab('tab_8',"<bean:message key='title.anexo.uno'/>",'');
		    	tab_8 = tabbar.cells('tab_8');
		    	goAnexo1();
		    	
		    	tabbar.addTab('tab_9',"<bean:message key='title.anexo.dos'/>",'');
		    	tab_9 = tabbar.cells('tab_9');
		    	goAnexo2();
		    	
		    }
		    
		    
		    
		    
		    function buscarMisAlumnos() {
		    	miGrid.clearAndLoad("gridUsuariosProfesor.do?busqueda=no");		    	
		    }
		    
		    
		    
		    function goInformacion(){
		    	
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
			    			default: break;
		    			}
		    		});
		    		
		    		//Aqui lo pondría con logic match para gente con permiso para modifiacar datos!
		    		
		    			formUsuario.forEachItem(function(id){
			    			switch(id) {
				    			
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
						
			    		
			    		if(anyoActual=="falso"){
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
				    	}
							    		
					
			    	formUsuario.load('editarusuario.do?idUsuario=' + idAlumno, function () {
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
								if(validateCIF(formUsuario.getItemValue("dni")) == false){
									alert('<bean:message key="message.dni.no.correcto"/>');
								}
								else{
									formUsuario.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idAlumno ,"post", function(loader, response) {
										if(response == "usuario changed"){
											buscarMisAlumnos();
											alert('<bean:message key="message.perfil.cambiado.exito"/>');
			    				    	}
			    				    	else{
			    				    		if(response == "usuario not changed: dni already exists"){
			    				    			alert('<bean:message key="message.usuario.failed.dni" />');
			    				    		}
			    					    }
										
									});
								}

							}
						});
						
						
						formUsuario.attachEvent("onEnter", function() {
							if(validateCIF(formUsuario.getItemValue("dni")) == false){
								alert('<bean:message key="message.dni.no.correcto"/>');
							}
							else{
								formUsuario.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idAlumno ,"post", function(loader, response) {
									if(response == "usuario changed"){
										buscarMisAlumnos();
										alert('<bean:message key="message.perfil.cambiado.exito"/>');
		    				    	}
		    				    	else{
		    				    		if(response == "usuario not changed: dni already exists"){
		    				    			alert('<bean:message key="message.usuario.failed.dni" />');
		    				    		}
		    					    }
									
								});
							}
			    		});
						
						
					});//load
					
		    		
		    	});
		    	
		    	
		    }
		    
		    
		    function goEstancia(){
		    	
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
			    			case "turno":{
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
		    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>19</permiso>" >
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
				    			case "turno":{
				    				form2.setReadonly(id,false);
				    				form2.setRequired(id,true);
				    				break;
				    			}
				    			
				    			
				    			default: break;
			    			}
				    		});
			    			form2.showItem('aceptar');
		    			</logic:match>
				
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
						
					if(anyoActual=="falso"){
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
				    			case "turno":{
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
			    		
						var ci = form2.getCalendar("fechaIni");
			    		
			    		ci.attachEvent("onShow", function(){
			    		    ci.hide();
			    		});
			    		
			    		var cf = form2.getCalendar("fechaFin");
			    		cf.attachEvent("onShow", function(){
			    		    cf.hide();
			    		});
			    	}

					
					form2.load('editarEstanciaUnidadClinica.do?idAlumno=' + idAlumno + '&idAsignatura=' + idAsignatura, function () {
						
						
						form2.attachEvent("onButtonClick", function(id){
							if (id == "aceptar") {
								if(fechasMenores(fechaIni,fechaFin)){	
									form2.send("actualizarEstanciaUnidadClinica.do?!nativeeditor_status=save&idAlumno=" + idAlumno + '&idAsignatura=' + idAsignatura,"post", function(xml) {
										alert('<bean:message key="message.estancia.clinica.exito"/>');
									});
								}
								else alert('<bean:message key="message.fechas.error"/>');

							}
						});
						form2.attachEvent("onEnter", function() {
							if(fechasMenores(fechaIni,fechaFin)){
								form2.send("actualizarEstanciaUnidadClinica.do?!nativeeditor_status=save&idAlumno=" + idAlumno + '&idAsignatura=' + idAsignatura,"post", function(xml) {
									alert('<bean:message key="message.estancia.clinica.exito"/>');
								}); 
							}
							else alert('<bean:message key="message.fechas.error"/>');
			    		});
						
						
					});//load
					
					
					
		    	});
		    	
		    	
		    	
				
				
		    }
		    
		    function goSeminarios(){	    	
		    	
		    	var mini_layout = tabbar.cells('tab_3').attachLayout("2U","dhx_skyblue");
		    	
		    	var ma = mini_layout.cells('a');
			    var mb = mini_layout.cells('b');
			    
			    ma.setText('<bean:message key="title.seminarios.realizados"/>');
			    
			    mb.setText('<bean:message key="label.descripcion.seminario"/>');
	    		mb.setWidth(500);
			    
			    
				gridAlumnoRealizadoSem = ma.attachGrid();
		    	
				gridAlumnoRealizadoSem.setHeader(["<bean:message key="label.nombre.seminario" />","<bean:message key="label.codigo.seminario" />","<bean:message key="label.anyo.realizacion.seminario" />"]);
				gridAlumnoRealizadoSem.setColTypes("ro,ro,ro");
		    	
				gridAlumnoRealizadoSem.setColSorting('str,str,str');
				gridAlumnoRealizadoSem.enableMultiselect(false);
				gridAlumnoRealizadoSem.init();
		    	
		    	var gridAlumnoRealizadoPro = new dataProcessor("gridSeminariosRealizadosUsuario.do?idAlumno=" + idAlumno + "&idAsignatura=" + idAsignatura + "&peticion=realizados");
		    	gridAlumnoRealizadoPro.enableUTFencoding('simple');
		    	gridAlumnoRealizadoPro.init(gridAlumnoRealizadoSem);	  
		    	gridAlumnoRealizadoPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
	    		
		    	gridAlumnoRealizadoSem.clearAndLoad("gridSeminariosRealizadosUsuario.do?idAlumno=" + idAlumno + "&idAsignatura=" + idAsignatura + "&peticion=realizados");
		    	
	    		
		    	gridAlumnoRealizadoSem.attachEvent("onRowSelect", function(row,ind){


		    		
		    		var formSeminarioAlumno = mb.attachForm();
		    		
		    		formSeminarioAlumno.loadStruct('../xml/forms/seminario_informacion_form.xml', function(){
		    			formSeminarioAlumno.setItemLabel('data','<bean:message key="title.info.general.seminario"/>');
		    			formSeminarioAlumno.setItemLabel('nombre','<bean:message key="label.nombre.seminario"/>');
		    			formSeminarioAlumno.setItemLabel('codigo','<bean:message key="label.codigo.seminario"/>');
		    			formSeminarioAlumno.setItemLabel('descripcion','<bean:message key="label.descripcion.seminario"/>');
		    			formSeminarioAlumno.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
		    			formSeminarioAlumno.setItemLabel('asignatura','<bean:message key="label.asignatura.asociada"/>');
		    			
		    			formSeminarioAlumno.setRequired('nombre', false);
		    			formSeminarioAlumno.setRequired('codigo', false);
		    			formSeminarioAlumno.hideItem('aceptar');
		    			formSeminarioAlumno.hideItem('asignatura');
		    			
		    		
		    			
						//Ponemos por defecto que los items no se puedan modificar, y luego con los permisos necesarios 
						//seran modificables.
			    		formSeminarioAlumno.setReadonly('nombre', true);
			    		formSeminarioAlumno.setReadonly('codigo', true);
			    		formSeminarioAlumno.setReadonly('descripcion', true);
			    		
			    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>18</permiso>" >
				    		formSeminarioAlumno.showItem('aceptar');
		    				formSeminarioAlumno.setReadonly('descripcion', false);	
		    			</logic:match>
		    			
		    			if(anyoActual=="falso"){
		    				formSeminarioAlumno.setReadonly('nombre', true);
				    		formSeminarioAlumno.setReadonly('codigo', true);
				    		formSeminarioAlumno.setReadonly('descripcion', true);
				    		formSeminarioAlumno.hideItem('aceptar');
			    			formSeminarioAlumno.hideItem('asignatura');
		    			}
						
						
						
		    		formSeminarioAlumno.load('editarseminario.do?idSeminario=' + row, function () {
							
							
		    			formSeminarioAlumno.attachEvent("onButtonClick", function(id){
							if (id == "aceptar") {
									
								formSeminarioAlumno.send("editarseminario.do?!nativeeditor_status=save&idSeminario=" + row,"post", function(xml) {
									alert('<bean:message key="message.seminario.cambiado.exito"/>');
								});
								

							}
						});
		    			formSeminarioAlumno.attachEvent("onEnter", function() {
								
		    				formSeminarioAlumno.send("editarseminario.do?!nativeeditor_status=save&idSeminario=" + row,"post", function(xml) {
								alert('<bean:message key="message.seminario.cambiado.exito"/>');
							}); 
								
				    	});
							
							
						});//load
						
						
			    			
		    		});

	    		
		    	});
			    

		    }
		    
		    
			function goTrabajos(){
				
				var mini_layout = tabbar.cells('tab_4').attachLayout("2U","dhx_skyblue");
		    	
		    	var ma = mini_layout.cells('a');
			    var mb = mini_layout.cells('b');
			    
	    		mb.setWidth(250);
	    		ma.hideHeader();
				mb.setText('<bean:message key="label.opciones.alumno"/>');
				
				var toolbarServicios = ma.attachToolbar();
		    	toolbarServicios.setIconsPath('../img/toolbar/');

		    	
		    	toolbarServicios.loadXML('../xml/toolbars/dhxtoolbar-trabajos-campo.xml', function(){
		    		toolbarServicios.setItemText('crearTrabajoCampo',"<bean:message key="button.crear.trabajo.campo"/>");
		    		toolbarServicios.setItemText('modificarTrabajoCampo',"<bean:message key="button.cambiar.trabajo.campo"/>");
		    		toolbarServicios.setItemText('subirPractica',"<bean:message key="button.subir.practica"/>");
		    		toolbarServicios.setItemText('descargarTodos',"<bean:message key="button.descargar.trabajos"/>");
		    		toolbarServicios.setItemText('descargarTodosAlumno',"<bean:message key="button.descargar.trabajos.alumno"/>");
		    		toolbarServicios.setItemText('fechaLimite',"<bean:message key="button.fecha.limite"/>");
		    		toolbarServicios.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
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
		    		
		    		
		    		
		    	});
		    	
		    	
				gridProfesoresTrab = ma.attachGrid();
		    	
				gridProfesoresTrab.setHeader(["<bean:message key="label.trabajo.campo" />","<bean:message key="label.fecha.limite" />","<bean:message key="label.corregido" />","<bean:message key="label.subido" />"]);
				gridProfesoresTrab.setInitWidthsP("45,30,10,15");
				gridProfesoresTrab.setColTypes("ro,ro,ro,ro");
				gridProfesoresTrab.setColSorting('str,str,ro,ro');
				gridProfesoresTrab.setColAlign("left,left,left,center,center");
				gridProfesoresTrab.enableMultiselect(false);
				gridProfesoresTrab.init();
		    	
		    	var gridProcessorPro = new dataProcessor("gridTrabajosCampoUsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + idAlumno);
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridProfesoresTrab);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});

		    	
		    	gridProfesoresTrab.attachEvent("onRowSelect", function doOnRowSelected(rowID,celInd){ 	
					
		    		var sp = rowID.split("-");
				
					var subido = "F";
					
					var cellObj = gridProfesoresTrab.cellById(rowID,"3");
					var s = cellObj.getValue();
					
					var ch = (s.indexOf("nocorregida.png") > -1);
					
					if(!ch){
						subido = "T";
					}
						 
					var corregido = sp[0];
					var idPortafolio = sp[3];
					var idTrabajoCampo = sp[4];
					var idTrabajoInfo = sp[5];
					
					
	
					var gridOpcionesAlumno = mb.attachGrid();
					
					gridOpcionesAlumno.setIconsPath('../skins/imgs/');		    	
					gridOpcionesAlumno.setHeader(["<strong><bean:message key="label.mi.perfil" /></strong>"]);
				    //set readonly (ro)
				    gridOpcionesAlumno.setColTypes("ro");
				    gridOpcionesAlumno.setNoHeader(true);
				    gridOpcionesAlumno.enableMultiselect(false);
				    gridOpcionesAlumno.init();
				    gridOpcionesAlumno.loadXML("../xml/forms/asignaturas_trabajos_opciones.xml");
				   
				    if(anyoActual=="falso"){	
				    	setTimeout(function(){gridOpcionesAlumno.cellById("b",0).setValue(" ");
				    	gridOpcionesAlumno.cellById("d",0).setValue(" ");},100);				    	
				    }
				    
				    gridOpcionesAlumno.attachEvent("onRowSelect",function(rowId,cellIndex){
				    	if (rowId == "a" && subido == "T") {
				    		
				    		var accion = "descargarTrabajoCampoAlumno.do";
							accion += "?tipoConsulta="+"TrabajoCampoAlumno";
							accion += "&idPortafolio="+idPortafolio;
							accion += "&idTrabajoCampo="+idTrabajoCampo;
							location.href=accion;
				    		
				    	}
				    	else if (rowId == "b" && anyoActual=="verdadero") {
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
				    	else if (rowId == "d" && anyoActual=="verdadero") {
				    		
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
					    		   }//if
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
													setTimeout(function(){goTrabajos();},1000);
												});
												
											}//if
						    			}//if
						    			else {alert("<bean:message key="message.algo.incorrecto"/>");}
						    			
					
							    	});//attachEvent onbuttonclick
					    			
					    		});//load
							});//loadStrut
				    	}//else row d
				    	
				    });//grid attachEvent
		    		
		    	});
		    	
		    	gridProfesoresTrab.clearAndLoad("gridTrabajosCampoUsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + idAlumno);
		    	
		    }
			
   
			
			
		    
			
			
			function seleccionarAlumnos(){
				
	    		var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("subir", 300,50, 950, 420);
				window.setText('<bean:message key="title.seleccionar.alumnos" />');				
				window.setModal(true);
				window.centerOnScreen();
				
				var toolbarServicios = window.attachToolbar();
		    	toolbarServicios.setIconsPath('../img/toolbar/');
		    	toolbarServicios.addButton("botonAceptar", "1", "Aceptar", "aceptar.gif");
		    	toolbarServicios.addSeparator("sp1","2");
		    	toolbarServicios.addButton("refrescarAlumnos", "3", "Refrescar", "recargar.png");
		    	
		    	
				var mini_layout = window.attachLayout("2U","dhx_skyblue");
		    	
		    	var ma = mini_layout.cells('a');
			    var mb = mini_layout.cells('b');
		    	
			    ma.setText("<strong><bean:message key="label.mis.alumnos" /></strong>");
			    mb.setText("<strong><bean:message key="label.demas.alumnos" /></strong>");
			    
				var gridMios = ma.attachGrid();
				gridMios.setIconsPath('../skins/imgs/');		   
  
			    
				gridMios.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />","<bean:message key="label.codigo.asignatura" />","<bean:message key="label.mio" />"]);
			    
			    //anchura de las columnas, en porcentaje. La suma tiene que ser igual a 100
			    gridMios.setInitWidthsP("21,38,17,14,10");
			    //alineacion del contenido en la columna
			    gridMios.setColAlign("left,left,left,left,left");
			    
			    gridMios.setColTypes("ro,ro,ro,ro,ch");
		    	
			    gridMios.enableMultiselect(false);
			    gridMios.setColSorting('str,str,str,str,str');
			    gridMios.init();
		    	
				var gridProcessorMios = new dataProcessor("gridUsuariosProfesor.do?");
				gridProcessorMios.enableUTFencoding('simple');
				gridProcessorMios.init(gridMios);	  
				gridProcessorMios.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});		    	

				gridMios.clearAndLoad("gridUsuariosProfesor.do?");	
				
				var gridDemas = mb.attachGrid();
				gridDemas.setIconsPath('../skins/imgs/');		   
  
			    
				gridDemas.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />","<bean:message key="label.codigo.asignatura" />","<bean:message key="label.mio" />"]);
			    
			    //anchura de las columnas, en porcentaje. La suma tiene que ser igual a 100
			    gridDemas.setInitWidthsP("21,38,17,14,10");
			    //alineacion del contenido en la columna
			    gridDemas.setColAlign("left,left,left,left,left");
			    
			    gridDemas.setColTypes("ro,ro,ro,ro,ch");
		    	
			    gridDemas.enableMultiselect(false);
			    gridDemas.setColSorting('str,str,str,str,str');
			    gridDemas.init();
		    	
				var gridProcessorDemas = new dataProcessor("gridUsuariosProfesor.do?busqueda=si");
				gridProcessorDemas.enableUTFencoding('simple');
				gridProcessorDemas.init(gridDemas);	  
				gridProcessorDemas.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});		    	

				gridDemas.clearAndLoad("gridUsuariosProfesor.do?busqueda=si");	
				
				
				toolbarServicios.attachEvent("onClick", function(id){
	    			if(id == "botonAceptar"){
	    				var aceptados="";
	    				var noaceptados="";
	    				gridMios.forEachRow(function(id){
	    					var parts = id.split("-");	    					
	    					var cellObj = gridMios.cellById(id,4);
	    					if(cellObj.getValue()=="0") {
	    						if (noaceptados=="") noaceptados = parts[3];
	    						else noaceptados += "," +  parts[3];
	    					}	
	    					
	    				});
	    				gridDemas.forEachRow(function(id){
	    					var parts = id.split("-");
	    					
	    					var cellObj = gridDemas.cellById(id,4);
	    					if(cellObj.getValue()=="1") {
	    						if (aceptados=="") aceptados = parts[3];
	    						else aceptados += "," + parts[3];
	    					}
	    					
	    					
	    				});
	    				var d= new dhtmlXWindows();
	    				var w = d.createWindow("s", 300,50, 1, 1);	
	    				w.hide();
						w.attachURL("actualizarAlumnos.do?aceptados=" + aceptados + "&noaceptados=" + noaceptados);
						
						setTimeout(function(){gridMios.clearAndLoad("gridUsuariosProfesor.do?");gridDemas.clearAndLoad("gridUsuariosProfesor.do?busqueda=si");w.close();buscarMisAlumnos();},1000);
						
	    				
	    			}
	    			else if (id == "refrescarAlumnos"){
	    				gridMios.clearAndLoad("gridUsuariosProfesor.do?");
	    				gridDemas.clearAndLoad("gridUsuariosProfesor.do?busqueda=si");
	    			}
				});
				
			}
			
			function goActualizarAlumnos(){
				 buscarMisAlumnos();
			}
			
			function goCasos(){
				
				var mini_layout = tabbar.cells('tab_5').attachLayout("1C","dhx_skyblue");
		    	
		    	var ma = mini_layout.cells('a');
			    
			    
				ma.setText('<bean:message key="label.casos.clinicos.alumno"/>');
				
				var toolbarServiciosCasos = ma.attachToolbar();
				toolbarServiciosCasos.setIconsPath('../img/toolbar/');
				
				toolbarServiciosCasos.loadXML('../xml/toolbars/dhxtoolbar-trabajos-campo.xml', function(){
					toolbarServiciosCasos.setItemText('crearTrabajoCampo',"<bean:message key="button.crear.trabajo.campo"/>");
					toolbarServiciosCasos.setItemText('modificarTrabajoCampo',"<bean:message key="button.cambiar.trabajo.campo"/>");
					toolbarServiciosCasos.setItemText('subirPractica',"<bean:message key="button.subir.practica"/>");
					toolbarServiciosCasos.setItemText('descargarTodos',"<bean:message key="button.descargar.casos"/>");
					toolbarServiciosCasos.setItemText('descargarTodosAlumno',"<bean:message key="button.descargar.casos.alumno"/>");
					toolbarServiciosCasos.setItemText('fechaLimite',"<bean:message key="button.fecha.limite"/>");
					toolbarServiciosCasos.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
					toolbarServiciosCasos.hideItem('crearTrabajoCampo');
					toolbarServiciosCasos.hideItem('sep1');
					toolbarServiciosCasos.hideItem('fechaLimite');
					toolbarServiciosCasos.hideItem('sep5');
					toolbarServiciosCasos.hideItem('modificarTrabajoCampo');
					toolbarServiciosCasos.hideItem('sep3');
					toolbarServiciosCasos.hideItem('subirPractica');
					toolbarServiciosCasos.hideItem('sep2');
					toolbarServiciosCasos.hideItem('descargarTodos');
					toolbarServiciosCasos.hideItem('sep3');
				});
				
				gridProfesoresCasos = ma.attachGrid();
		    	

		    	
				gridProfesoresCasos.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.fecha" />","<bean:message key="label.enlace" />"]);
				gridProfesoresCasos.setColTypes("ro,ro,ro");
		    	
				gridProfesoresCasos.setColSorting('str,str,str');
				gridProfesoresCasos.enableMultiselect(false);
				gridProfesoresCasos.init();
		    	
		    	var gridProcessorPro = new dataProcessor("gridCasosClinicosUsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + idAlumno);
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridProfesoresCasos);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
		    	
		    	
		    	gridProfesoresCasos.clearAndLoad("gridCasosClinicosUsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + idAlumno);

		    	
		    	gridProfesoresCasos.attachEvent("onRowSelect",function doOnRowSelected(rowID,celInd){
		    		
		    		var cellObj = gridProfesoresCasos.cellById(rowID,celInd);
					if(celInd=='2' && cellObj.getValue()=="Descargar") {
						var parts = rowID.split("-");
						//alert("Descargar Archivo con idPortafolio=" + parts[0] + " y idCasoClinico=" + parts[1]);
						var accion = "descargarCasoClinico.do";
						accion += "?tipoConsulta="+"CasoClinico";
						accion += "&idPortafolio="+parts[0];
						accion += "&idCasoClinico="+parts[1];
						location.href=accion;
					}
		    	});
				
			}
			
			
		    
			
			function goDiarios(){
				
				var mini_layout = tabbar.cells('tab_6').attachLayout("1C","dhx_skyblue");
		    	
		    	var ma = mini_layout.cells('a');
			    
				ma.setText('<bean:message key="label.diario.reflexivo.alumno"/>');
				
				var toolbarServiciosDiarios = ma.attachToolbar();
				toolbarServiciosDiarios.setIconsPath('../img/toolbar/');

		    	
				toolbarServiciosDiarios.loadXML('../xml/toolbars/dhxtoolbar-trabajos-campo.xml', function(){
					toolbarServiciosDiarios.setItemText('crearTrabajoCampo',"<bean:message key="button.crear.trabajo.campo"/>");
					toolbarServiciosDiarios.setItemText('modificarTrabajoCampo',"<bean:message key="button.cambiar.trabajo.campo"/>");
					toolbarServiciosDiarios.setItemText('subirPractica',"<bean:message key="button.subir.practica"/>");
					toolbarServiciosDiarios.setItemText('descargarTodos',"<bean:message key="button.descargar.diarios"/>");
					toolbarServiciosDiarios.setItemText('descargarTodosAlumno',"<bean:message key="button.descargar.diarios.alumno"/>");
					toolbarServiciosDiarios.setItemText('fechaLimite',"<bean:message key="button.fecha.limite"/>");
					toolbarServiciosDiarios.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
					toolbarServiciosDiarios.hideItem('crearTrabajoCampo');
					toolbarServiciosDiarios.hideItem('sep1');
					toolbarServiciosDiarios.hideItem('fechaLimite');
					toolbarServiciosDiarios.hideItem('sep5');
					toolbarServiciosDiarios.hideItem('modificarTrabajoCampo');
					toolbarServiciosDiarios.hideItem('sep3');
					toolbarServiciosDiarios.hideItem('subirPractica');
					toolbarServiciosDiarios.hideItem('sep2');
					toolbarServiciosDiarios.hideItem('descargarTodos');
		    		toolbarServiciosDiarios.hideItem('sep3');
		    		
		    	});
				
				
				gridProfesoresDiarios = ma.attachGrid();
		    	
				gridProfesoresDiarios.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.fecha" />","<bean:message key="label.enlace" />"]);
				gridProfesoresDiarios.setColTypes("ro,ro,ro");
		    	
				gridProfesoresDiarios.setColSorting('str,str,str');
				gridProfesoresDiarios.enableMultiselect(false);
				gridProfesoresDiarios.init();
		    	
		    	var gridProcessorPro = new dataProcessor("gridDiariosReflexivosUsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + idAlumno);
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridProfesoresDiarios);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
		    	
		    	gridProfesoresDiarios.clearAndLoad("gridDiariosReflexivosUsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + idAlumno);
						    	
		    	gridProfesoresDiarios.attachEvent("onRowSelect",function doOnRowSelected(rowID,celInd){
		    		
		    		var cellObj = gridProfesoresDiarios.cellById(rowID,celInd);
					if(celInd=='2' && cellObj.getValue()=="Descargar") {
						var parts = rowID.split("-");
						//alert("Descargar Archivo con idPortafolio=" + parts[0] + " y idCasoClinico=" + parts[1]);
						var accion = "descargarDiarioReflexivo.do";
						accion += "?tipoConsulta="+"DiarioReflexivo";
						accion += "&idPortafolio="+parts[0];
						accion += "&idDiarioReflexivo="+parts[1];
						location.href=accion;
					}
		    	});
		    	
		    	
			}
			
		
			
			
			function goRubricas(){
				formRubrica = tab_7.attachForm();
		    	formRubrica.loadStruct('../xml/forms/rubrica_form.xml', function(){
	    			formRubrica.setItemLabel('resultados','<bean:message key="title.resultados.competencias"/>');
	    			formRubrica.setItemValue('competencias',competencias);

	    			var i;
	    			var grupos_criterios_rubrica = dameGruposCriteriosAsignatura();
	    			for(i=0;i<grupos_criterios_rubrica.length;i++){
	    				formRubrica.addItem(null, grupos_criterios_rubrica[i], i+1);
	    			}
	    			formRubrica.addItem(null,{type:"button", name:"aceptar", value:"Modificar"},i+1);
	    			formRubrica.setItemLabel('aceptar','<bean:message key="button.modificar"/>');

	    			permisosRubricasForm();
	    			
  					var items = new Array();
  					for(var j=1;j<=10;j=j+2){
						items[j] = {type:"input", name:"contador_"+((j+1)/2), label:"<strong>"+((j+1)/2)+"</strong>", position:"label-top", labelWidth:"10", inputWidth:"30", readonly:"true"};
		    			items[j+1] = {type:"newcolumn"};
	    			}
  					formRubrica.addItem(null,{type:"fieldset", name:"puntuacion", label:'<bean:message key="label.total.puntuacion"/>', inputWidth:"auto"},i+1);
  					formRubrica.addItem("puntuacion",{type:"block", name:"block_contadores", list:items},0);
  					formRubrica.addItem("puntuacion",{type:"label", label:'<strong><bean:message key="label.nota.final"/></strong>'},1);
  					formRubrica.addItem("puntuacion",{type:"input", name:"nota", inputWidth:"50", readonly:"true"},2);
  					formRubrica.addItem("puntuacion",{type:"input", name:"aclaraciones", label:'<bean:message key="label.nota.final.aclaraciones"/>', labelWidth:"700", inputWidth:"0", position:"label-right", readonly:"true"},3);
  					formRubrica.addItem("puntuacion",{type:"input", name:"rango1", label:'<bean:message key="label.nota.rango1"/>', labelWidth:"700", inputWidth:"0", position:"label-right", readonly:"true"},4);
  					formRubrica.addItem("puntuacion",{type:"input", name:"rango2", label:'<bean:message key="label.nota.rango2"/>', labelWidth:"700", inputWidth:"0", position:"label-right", readonly:"true"},5);
  					formRubrica.addItem("puntuacion",{type:"input", name:"rango3", label:'<bean:message key="label.nota.rango3"/>', labelWidth:"700", inputWidth:"0", position:"label-right", readonly:"true"},6);
  					formRubrica.addItem("puntuacion",{type:"input", name:"rango4", label:'<bean:message key="label.nota.rango4"/>', labelWidth:"700", inputWidth:"0", position:"label-right", readonly:"true"},7);
  					formRubrica.addItem("puntuacion",{type:"input", name:"observaciones", label:'<bean:message key="label.nota.final.observaciones"/>', labelWidth:"700", inputWidth:"0", position:"label-right", readonly:"true"},8);
  					
  					loadNotasRubrica();
  					contarValores();
  					if(anyoActual=="falso"){
  						formRubrica.hideItem("aceptar");
  					}
    				formRubrica.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
	    					formRubrica.send("actualizarnotasrubrica.do?!nativeeditor_status=save&idPortafolio=" +idPortafolio+"&idAsignatura="+idAsignatura,"post", function(xml) {
	    						contarValores();
	    						alert('<bean:message key="message.notas.cambiadas.exito"/>');
		    				});
	    				}
	    			});
    				formRubrica.attachEvent("onEnter", function() {
    					formRubrica.send("actualizarnotasrubrica.do?!nativeeditor_status=save&idPortafolio="+idPortafolio+"&idAsignatura="+idAsignatura,"post", function(xml) {
							contarValores();
    						alert('<bean:message key="message.notas.cambiadas.exito"/>');
	    				});
		    		});
	    		});
				
			}
			
			
			function goAnexo1(){
				formAnexo = tab_8.attachForm();
		    	formAnexo.loadStruct('../xml/forms/anexo_form.xml', function(){
	    			formAnexo.setItemLabel('anexo',anexo);
	    			var grupos_anexo_rubrica = dameGruposAnexoAsignatura();
	    			for(var i=0;i<grupos_anexo_rubrica.length;i++){
	    				formAnexo.addItem("anexo", grupos_anexo_rubrica[i], i);
	    			}
	    			formAnexo.addItem(null,{type:"button", name:"aceptar", value:"Modificar"},i+1);
	    			formAnexo.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
	    			if(anyoActual=="falso"){
	    				formAnexo.hideItem("aceptar");
  					}
	    			
	    			permisosRubricasForm();
	    			formAnexo.load("notasrubrica.do?idPortafolio=" +idPortafolio+"&idAsignatura="+idAsignatura, function () {			    			
	    				formAnexo.attachEvent("onButtonClick", function(id){
		    				if (id == "aceptar") {
		    					formAnexo.forEachItem(function(name){
		    						if(formAnexo.getItemType(name) == "input"){
		    							formAnexo.setItemValue("value("+name+")", formAnexo.getItemValue(name));
		    						}
		    					});
		    					formAnexo.send("actualizarnotasrubrica.do?!nativeeditor_status=save&idPortafolio=" +idPortafolio+"&idAsignatura="+idAsignatura,"post", function(xml) {
		    						alert('<bean:message key="message.notas.cambiadas.exito"/>');
			    				});
		    				}
		    			});
	    				formAnexo.attachEvent("onEnter", function() {
	    					formAnexo.forEachItem(function(name){
	    						if(formAnexo.getItemType(name) == "input"){
	    							formAnexo.setItemValue("value("+name+")", formAnexo.getItemValue(name));
	    						}
	    					});
	    					formAnexo.send("actualizarnotasrubrica.do?!nativeeditor_status=save&idPortafolio="+idPortafolio+"&idAsignatura="+idAsignatura,"post", function(xml) {
	    						alert('<bean:message key="message.notas.cambiadas.exito"/>');
		    				});
			    		});
	    			});
	    		});	
			}
				
			function goAnexo2(){
				var toolbarServiciosAnexo2= tab_9.attachToolbar();

				toolbarServiciosAnexo2.setIconsPath('../img/toolbar/');

		    	
				toolbarServiciosAnexo2.loadXML('../xml/toolbars/dhxtoolbar-trabajos-campo.xml', function(){
					toolbarServiciosAnexo2.setItemText('crearTrabajoCampo',"<bean:message key="button.crear.trabajo.campo"/>");
					toolbarServiciosAnexo2.setItemText('modificarTrabajoCampo',"<bean:message key="button.cambiar.trabajo.campo"/>");
					toolbarServiciosAnexo2.setItemText('subirPractica',"<bean:message key="button.subir.practica"/>");
					toolbarServiciosAnexo2.setItemText('descargarTodos',"<bean:message key="button.descargar.anexos"/>");
					toolbarServiciosAnexo2.setItemText('descargarTodosAlumno',"<bean:message key="button.descargar.anexos.alumno"/>");
					toolbarServiciosAnexo2.setItemText('fechaLimite',"<bean:message key="button.fecha.limite"/>");
					toolbarServiciosAnexo2.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
					toolbarServiciosAnexo2.hideItem('crearTrabajoCampo');
					toolbarServiciosAnexo2.hideItem('sep1');
					toolbarServiciosAnexo2.hideItem('fechaLimite');
					toolbarServiciosAnexo2.hideItem('sep5');
					toolbarServiciosAnexo2.hideItem('modificarTrabajoCampo');
					toolbarServiciosAnexo2.hideItem('sep3');
					toolbarServiciosAnexo2.hideItem('descargarTodos');
		    		
		    		<logic:match scope="session" name="usuarioYPermisos" value="<grupo>4</grupo>" >
		    	
		    			toolbarServiciosAnexo2.hideItem('descargarTodos');
		    			toolbarServiciosAnexo2.hideItem('sep3');
		    			toolbarServiciosAnexo2.hideItem('descargarTodosAlumno');
		    			toolbarServiciosAnexo2.hideItem('sep4');		    		
			    	
		    		</logic:match>
		    		
		    		if(anyoActual=="falso"){
		    			toolbarServiciosAnexo2.hideItem('sep1');
		    			toolbarServiciosAnexo2.hideItem('subirPractica');
		    			toolbarServiciosAnexo2.hideItem('sep2');
		    			toolbarServiciosAnexo2.hideItem('descargarTodos');
		    			toolbarServiciosAnexo2.hideItem('sep3');
		    			toolbarServiciosAnexo2.hideItem('descargarTodosAlumno');
		    			toolbarServiciosAnexo2.hideItem('sep4');
		    			toolbarServiciosAnexo2.hideItem('sep5');
		    			toolbarServiciosAnexo2.hideItem('sep6');
		    		}
				
		    	});
				
				gridAnexos2 = tab_9.attachGrid();
		    	
				gridAnexos2.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.fecha" />","<bean:message key="label.enlace" />"]);
				gridAnexos2.setColTypes("ro,ro,ro");
		    	
				gridAnexos2.setColSorting('str,str,str');
				gridAnexos2.enableMultiselect(false);
				gridAnexos2.init();
		    	
				
				
		    	var gridProcessorPro = new dataProcessor("gridAnexos2UsuarioAsignatura.do?idPortafolio=" + idPortafolio);
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridAnexos2);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
		    	
		    	gridAnexos2.attachEvent("onRowSelect",function(rowID,celInd){
		    		
					var cellObj = gridAnexos2.cellById(rowID,celInd);
					if(celInd=='2' && cellObj.getValue()=="Descargar") {
						var parts = rowID.split("-");
						//alert("Descargar Archivo con idPortafolio=" + parts[0] + " y idCasoClinico=" + parts[1]);
						var accion = "descargarAnexo.do";
						accion += "?tipoConsulta="+"Anexo";
						accion += "&idPortafolio="+parts[0];
						accion += "&idAnexo="+parts[1];
						location.href=accion;
					}
		
				});
		    	
		    	
		    	gridAnexos2.clearAndLoad("gridAnexos2UsuarioAsignatura.do?idPortafolio=" + idPortafolio);	
				
				
			}
			
			function subirPractica(){
				var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("subir", 300,50, 500, 170);
				window.setText('<bean:message key="title.subir.practica" />');				
				window.setModal(true);
				window.centerOnScreen();
				
				window.attachURL("subirArchivo.do?tipoConsulta=AnexoProfesor" + "&idPortafolio=" + idPortafolio);
				//goActualizar();
			}
			
			function ucmEsEmail(correo) {
	    		if (getDomain(correo) == "ucm.es") {
	    			return true;
	    		}
	    		else {
	    			formUsuario.setNote("correo", { text: '<bean:message key="message.email.institucional" />'} );
	    			
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
	    	
	    	function descargarTodosAlumno(){
	    		var tipo = tabbar.getActiveTab();
	    		if (tipo == "tab_4"){
		    		var accion = "descargarTrabajosCampoAlumno.do";
					accion += "?idPortafolio=" + idPortafolio;
					location.href=accion;
	    		}
	    		else if (tipo == "tab_5"){
	    			var accion = "descargarCasoClinicosAlumno.do";
					accion += "?idPortafolio=" + idPortafolio;
					location.href=accion;
	    		}
	    		else if (tipo == "tab_6"){
	    			var accion = "descargarDiariosReflexivosAlumno.do";
					accion += "?idPortafolio=" + idPortafolio;
					location.href=accion;
	    		}
	    		else if (tipo == "tab_9"){
	    			var accion = "descargarAnexosAlumno.do";
					accion += "?idPortafolio=" + idPortafolio;
					location.href=accion;
	    		}
	    	}
	    	
	    	function goActualizar(){
	    		var tipo = tabbar.getActiveTab();
	    		
	    		if(tipo == "tab_4") gridProfesoresTrab.clearAndLoad("gridTrabajosCampoUsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + idAlumno);
	    		else if (tipo == "tab_5") gridProfesoresCasos.clearAndLoad("gridCasosClinicosUsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + idAlumno);
	    		else if (tipo == "tab_6") gridProfesoresDiarios.clearAndLoad("gridDiariosReflexivosUsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + idAlumno);				
	    		else if (tipo == "tab_9") gridAnexos2.clearAndLoad("gridAnexos2UsuarioAsignatura.do?idPortafolio=" + idPortafolio);
	    	}
	    	
	    	
	    	function dameRubricaAsignatura(){
	    		var url = "rubricaasignatura.do?idAsignatura="+idAsignatura;
	    		var xmlhttp = initRequest();
	    		xmlhttp.onreadystatechange=function(){
	    			if (xmlhttp.readyState===4) {
	        	        if(xmlhttp.status===200) { //GET returning a response
	        	        	competencias = xmlhttp.responseXML.getElementsByTagName("competencias")[0].firstChild.nodeValue;
	        	        	anexo = xmlhttp.responseXML.getElementsByTagName("anexo")[0].firstChild.nodeValue;
	        	        	numeroCriterios = xmlhttp.responseXML.getElementsByTagName("numero_criterios")[0].firstChild.nodeValue;
	        	        }
	        	    }
	    		}
	    	    xmlhttp.open("GET",url,false);
	    	    xmlhttp.send(null);
	    	    return xmlhttp.onreadystatechange();
	    	}
		    
	    	function dameGruposCriteriosAsignatura(){
	    		var url = "gruposcriteriosasignatura.do?idAsignatura="+idAsignatura;
	    		var xmlhttp = initRequest();
	    		xmlhttp.onreadystatechange=function(){
	    			if (xmlhttp.readyState===4) {
	        	        if(xmlhttp.status===200) { //GET returning a response
	        	        	return createArrayGruposCriteriosFromXML(xmlhttp.responseXML);
	        	        }
	        	    }
	    		}
	    	    xmlhttp.open("GET",url,false);
	    	    xmlhttp.send(null);
	    	    return xmlhttp.onreadystatechange();
	    	}
	    	
	    	function dameGruposAnexoAsignatura(){
	    		var url = "gruposanexoasignatura.do?idAsignatura="+idAsignatura;
	    		var xmlhttp = initRequest();
	    		xmlhttp.onreadystatechange=function(){
	    			if (xmlhttp.readyState===4) {
	        	        if(xmlhttp.status===200) { //GET returning a response
	        	        	return createArrayGruposAnexoFromXML(xmlhttp.responseXML);
	        	        }
	        	    }
	    		}
	    	    xmlhttp.open("GET",url,false);
	    	    xmlhttp.send(null);
	    	    return xmlhttp.onreadystatechange();
	    	}
	    	
	    	function loadNotasRubrica(){
	    		var url = "notasrubrica.do?idPortafolio=" + idPortafolio+"&idAsignatura="+idAsignatura;
	    		var xmlhttp = initRequest();
	    		xmlhttp.onreadystatechange=function(){
	    			if (xmlhttp.readyState===4) {
	        	        if(xmlhttp.status===200) { //GET returning a response
	        	        	return checkRadioButtonsFromXML(xmlhttp.responseXML);
	        	        }
	        	    }
	    		}
	    	    xmlhttp.open("GET",url,false);
	    	    xmlhttp.send(null);
	    	    return xmlhttp.onreadystatechange();
	    	}
	    	
	    	function contarValores(){
				contador = new Array();
				contador = [0,0,0,0,0,0];
				var sumatorio = 0;
				formRubrica.forEachItem(function(name,value){
					if(formRubrica.getItemType(name, value) == "radio"){
						if(formRubrica.isItemChecked(name, value)){
							contador[value]=contador[value]+1;
							sumatorio = sumatorio + value;
						}
					}
				});
				for(var i=1;i<=5;i++){
					formRubrica.setItemValue("contador_"+i, contador[i]);
				}
				formRubrica.setItemValue("nota", Math.round(((sumatorio/numeroCriterios)*2) * 100) / 100);
			}
	    	
	    	
	    	function createArrayGruposCriteriosFromXML(xml){
				var items = new Array();
				var criterios_grupo = new Array();
				var grupos = xml.getElementsByTagName("grupo");
				var criterios;
				var id_grupo, nombre_grupo, id_criterio, nombre_criterio;
				for(var i=0;i<grupos.length;i++) {
	    	        id_grupo=grupos[i].getElementsByTagName("id_grupo")[0].firstChild.nodeValue;
	    	        nombre_grupo=grupos[i].getElementsByTagName("nombre_grupo")[0].firstChild.nodeValue;
	    	        criterios = grupos[i].getElementsByTagName("criterio");
	    	        criterios_grupo = new Array();
	    	        for(var j=0;j<criterios.length;j++){
	    	        	id_criterio=criterios[j].getElementsByTagName("id_criterio")[0].firstChild.nodeValue;
		    	        nombre_criterio=criterios[j].getElementsByTagName("nombre_criterio")[0].firstChild.nodeValue;
		    	        var radios = new Array();
		    	        for(var k=1;k<=10;k=k+2){
		    	        	radios[k] = {type:"radio", name:"value(idCriterio"+id_criterio+")", value:((k+1)/2), label:((k+1)/2)};
		    	        	radios[k+1] = {type:"newcolumn"};
		    	        }
		    	        criterios_grupo[j]={type:"label", label:nombre_criterio, labelWidht:"100", list:radios};
	    	        }
	    	        items[i]={type:"fieldset", name:id_grupo, label:nombre_grupo, inputWidth:"auto", list:criterios_grupo};
		    	}
				return items;
	    	}
			
			
			function createArrayGruposAnexoFromXML(xml){
				var items = new Array();
				var criterios_grupo = new Array();
				var grupos = xml.getElementsByTagName("grupo");
				var criterios;
				var id_grupo, nombre_grupo, id_criterio, nombre_criterio;
				for(var i=0;i<grupos.length;i++) {
	    	        id_grupo=grupos[i].getElementsByTagName("id_grupo")[0].firstChild.nodeValue;
	    	        nombre_grupo=grupos[i].getElementsByTagName("nombre_grupo")[0].firstChild.nodeValue;
	    	        criterios = grupos[i].getElementsByTagName("criterio");
	    	        criterios_grupo = new Array();
	    	        for(var j=0;j<(criterios.length)*2;j=j+2){
	    	        	id_criterio=criterios[j/2].getElementsByTagName("id_criterio")[0].firstChild.nodeValue;
		    	        nombre_criterio=criterios[j/2].getElementsByTagName("nombre_criterio")[0].firstChild.nodeValue; 
		    	        criterios_grupo[j] = {type:"input", name:"idCriterio"+id_criterio, label:"<strong>"+nombre_criterio+"</strong>", position:"label-top", labelWidth:"700", inputWidth:"700", rows:"3"};
		    	        criterios_grupo[j+1] = {type:"hidden", name:"value(idCriterio"+id_criterio+")"};
	    	        }
	    	        items[i]={type:"fieldset", name:id_grupo, label:nombre_grupo, inputWidth:"auto", list:criterios_grupo};
		    	}
				return items;
	    	}
			
			function checkRadioButtonsFromXML(xml){
				var length = xml.documentElement.childNodes.length;
				for(var i=0;i<length;i++) {
	    	        var idCriterio = xml.documentElement.childNodes.item(i).nodeName;
	    	        var notaCriterio = xml.documentElement.childNodes.item(i).childNodes[0].nodeValue;
	    	        formRubrica.checkItem("value("+idCriterio+")",notaCriterio);
				}
	    	}
			

			function permisosRubricasForm(){
    			formRubrica.setReadonly('competencias',true);
    			formRubrica.setReadonly('nota',true);
    			for(var i=1;i<=5;i++){
        			formRubrica.setReadonly('contador_'+i,true);
    			}
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>2</permiso>" >
	    		<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>3</permiso>" >	
	    		formRubrica.forEachItem(function(id,value){
	    			if(formRubrica.getItemType(id, value) == "radio"){
	        			formRubrica.disableItem(id,value);
	    			}
	    		});
    			formRubrica.hideItem('aceptar');
    			</logic:notMatch>
    			</logic:notMatch>	
    			</logic:notMatch>	
    			
    			if(anyoActual=="falso"){
    				formRubrica.setReadonly('competencias',true);
        			formRubrica.setReadonly('nota',true);
        			for(var i=1;i<=5;i++){
            			formRubrica.setReadonly('contador_'+i,true);
        			}
        			formRubrica.forEachItem(function(id,value){
    	    			if(formRubrica.getItemType(id, value) == "radio"){
    	        			formRubrica.disableItem(id,value);
    	    			}
    	    		});
        			formRubrica.hideItem('aceptar');
    			}
			}
			
			function permisosAnexoForm(){
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>2</permiso>" >
	    		<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>3</permiso>" >	
	    		formAnexo.forEachItem(function(id){
	    			if(formAnexo.getItemType(id) == "input"){
	        			formAnexo.setReadonly(id,true);
	    			}
	    		});
    			formAnexo.hideItem('aceptar');
    			</logic:notMatch>
    			</logic:notMatch>	
    			</logic:notMatch>	
    			
    			if(anyoActual=="falso"){
    				formAnexo.forEachItem(function(id){
    	    			if(formAnexo.getItemType(id) == "input"){
    	        			formAnexo.setReadonly(id,true);
    	    			}
    	    		});
        			formAnexo.hideItem('aceptar');
    			}
			}
        </script>
	</head>
	<body>
	</body>
</html>