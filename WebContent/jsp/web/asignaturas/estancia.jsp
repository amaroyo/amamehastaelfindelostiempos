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
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxform.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_dyn.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_item_container.js"></script>
	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxcontainer.js"></script>
	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxtabbar.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxtabbar_start.js"></script>
		<script src="../js/dhtmlxSuite/patterns/dhtmlxlayout_pattern4l.js"></script>

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	var main_layout, idAsignatura,nombreAsignatura, gridProfesor, formAlumno, formUsuario;
	    	
	    	dhtmlxEvent(window,"load",function() {
	    		
	    		
	    		<% String idAsignatura = request.getParameter("idAsignatura");%>
	    		idAsignatura="<%=idAsignatura%>";	
	    		
				
	    		
	    		
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
		    			formAlumno.setItemLabel('profesor','<bean:message key="label.profesor.asignatura"/>');
		    			formAlumno.setItemLabel('fechaIni','<bean:message key="label.fecha.ini.estancia"/>');
		    			formAlumno.setItemLabel('fechaFin','<bean:message key="label.fecha.fin.estancia"/>');
			    		
						//Ponemos por defecto que los items no se puedan modificar, y luego con los permisos necesarios 
						//seran modificables.
			    		formAlumno.setReadonly('hospital', true);
			    		formAlumno.setReadonly('clinica', true);
			    		formAlumno.setReadonly('profesor', true);
			    		formAlumno.setReadonly('fechaIni', true);
			    		formAlumno.setReadonly('fechaFin', true);
			    		formAlumno.hideItem('aceptar');
			    		
			    		//Esto por ahora es provisional, cuando se haga una peticion de toda la informacion 
			    		//de las asignaturas, se cogeran el codigo y el nombre de la asignatura
			    		formAlumno.setItemValue('hospital', "Lorem ipsum");
			    		formAlumno.setItemValue('clinica', "Lorem ipsum");
			    		formAlumno.setItemValue('profesor', "A113");
			    		formAlumno.setItemValue('fechaIni', "Lorem ipsum");
			    		formAlumno.setItemValue('fechaFin', "Lorem ipsum");
			    		

			    		
			    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >	    	
				    		formAlumno.setReadonly('hospital', false);
				    		formAlumno.setReadonly('clinica', false);
				    		formAlumno.setReadonly('profesor', false);
				    		formAlumno.setReadonly('fechaIni', false);
				    		formAlumno.setReadonly('fechaFin', false);
				    		formAlumno.showItem('aceptar');
						</logic:match>
			    		
						
						
						/*
						form.load('editarusuario.do?idUsuario=' + idSelectedUser, function () {
							form.attachEvent("onButtonClick", function(id){
								if (id == "aceptar") {
									form.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idSelectedUser ,"post", function(xml) {
	
									});
	
								}
							});
						});
						*/
					
					
		    	});
					
				}
				
				
				function goProfesor(){
					
					main_layout = new dhtmlXLayoutObject(document.body, '2U');
					main_layout.setAutoSize("a;b");
		    		var a = main_layout.cells('a');
		    		var b = main_layout.cells('b');
		    		a.setText(["<strong><bean:message key="label.mis.alumnos.estancia" /></strong>"]);
		    		b.hideHeader();
		    		gridProfesor = a.attachGrid();
					
		    		gridProfesor.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />","<bean:message key="label.centro.asociado" />","<bean:message key="label.turno" />"]);
		    		gridProfesor.setColTypes("ro,ro,ro,ro,ro");
			    	
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
				    		formUsuario.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
				    		
				    		formUsuario.hideItem('aceptar');
				    		
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
								if(formUsuario.getItemValue("fotoUri") == "") {
									var uriNoProfilePic = '../img/no-profile-pic.png';
									formUsuario.getContainer("foto").innerHTML = "<img src="+ uriNoProfilePic +">";
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
				    	
				    	tabbar.addTab('tab_2','<bean:message key="title.info.general.estancia"/>','');
				    	var tab_2 = tabbar.cells('tab_2');
				    	var form2 = tab_2.attachForm();
				    	form2.loadStruct('../xml/forms/estancia_form.xml', function(){
				    		form2.setItemLabel('data','<bean:message key="title.info.general.estancia"/>');
				    		form2.setItemLabel('hospital','<bean:message key="label.hospital.estancia"/>');
				    		form2.setItemLabel('clinica','<bean:message key="label.clinica.estancia"/>');
				    		form2.setItemLabel('profesor','<bean:message key="label.profesor.asignatura"/>');
				    		form2.setItemLabel('fechaIni','<bean:message key="label.fecha.ini.estancia"/>');
				    		form2.setItemLabel('fechaFin','<bean:message key="label.fecha.fin.estancia"/>');
				    	
				    		
				    		//Esto por ahora es provisional, cuando se haga una peticion de toda la informacion 
				    		//de las asignaturas, se cogeran el codigo y el nombre de la asignatura
				    		form2.setItemValue('hospital', "Lorem ipsum");
				    		form2.setItemValue('clinica', "Lorem ipsum");
				    		form2.setItemValue('profesor', "A113");
				    		form2.setItemValue('fechaIni', "Lorem ipsum");
				    		form2.setItemValue('fechaFin', "Lorem ipsum");
			    			
				    	});
				    	
				    	
				    	
						form2.load('estanciaUnidadClinica.do?idUsuario=' + idSelectedUser + "&idAsignatura=" + idAsignatura, function () {
							form.attachEvent("onButtonClick", function(id){
								if (id == "aceptar") {
									//form.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idSelectedUser ,"post", function(xml) {
	
									//});
	
								}
							});
						});
						
						
				    	
					});
				    
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
	    	
	   </script>
	</head>
	<body>
	</body>
</html>