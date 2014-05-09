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
	    	var main_layout, idAsignatura,nombreAsignatura, gridProfesor, gridAlumnoRealizado,gridAlumnoPendiente;
	    	
	    	dhtmlxEvent(window,"load",function() {
	    		
	    		
	    		<% String idAsignatura = request.getParameter("idAsignatura");%>
	    		idAsignatura="<%=idAsignatura%>";	
	    		<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
				var idSelectedUser = <%=sessionIdUser%>;
	    		
	    		
	    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					goProfesor();
				</logic:match>
			
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					goAlumno();
				</logic:notMatch>	
	    		
				
				function goAlumno(){
					
					main_layout = new dhtmlXLayoutObject(document.body, '2U');
		    		var a = main_layout.cells('a');
		    		var b = main_layout.cells('b');
		    		a.hideHeader();
		    		b.setText('<bean:message key="label.descripcion.seminario"/>');
		    		b.setWidth(500);
		    		
		    		var tabbar = a.attachTabbar();
			    	tabbar.addTab('tab_1','<bean:message key="title.seminarios.realizados"/>','');
			    	var tab_1 = tabbar.cells('tab_1');
			    	tabbar.setTabActive('tab_1');
			    	gridAlumnoRealizado = tab_1.attachGrid();
			    	
			    	gridAlumnoRealizado.setHeader(["<bean:message key="label.nombre.seminario" />","<bean:message key="label.codigo.seminario" />","<bean:message key="label.descripcion.seminario" />"]);
			    	gridAlumnoRealizado.setColTypes("ro,ro,ro");
			    	
			    	gridAlumnoRealizado.setColSorting('str,str,str');
			    	gridAlumnoRealizado.enableMultiselect(false);
			    	gridAlumnoRealizado.init();
			    	
			    	gridAlumnoRealizadoPro = new dataProcessor("gridusuarios.do");
			    	gridAlumnoRealizadoPro.enableUTFencoding('simple');
			    	gridAlumnoRealizadoPro.init(gridAlumnoRealizado);	  
			    	gridAlumnoRealizadoPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
			    			dhtmlx.message(tag.firstChild.data,action,4000);
			    		}
			    	});
		    		
			    	gridAlumnoRealizado.attachEvent("onRowSelect", function(row,ind){

			    		selectedCode=gridAlumnoRealizado.cells(row,1).getValue();
			    		
			    		
			    		var formSeminarioAlumno = b.attachForm();
			    		
			    		formSeminarioAlumno.loadStruct('../xml/forms/seminario_informacion_form.xml', function(){
			    			formSeminarioAlumno.setItemLabel('data','<bean:message key="title.info.general.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('nombre','<bean:message key="label.nombre.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('codigo','<bean:message key="label.codigo.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('curso','<bean:message key="label.curso.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('profesor','<bean:message key="label.profesor.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('descripcion','<bean:message key="label.descripcion.seminario"/>');
				    		
				    			
							
							formSeminarioAlumno.setItemValue('nombre', "caca");
							formSeminarioAlumno.setItemValue('codigo', "caca");
							formSeminarioAlumno.setItemValue('curso', "A113");
							formSeminarioAlumno.setItemValue('profesor', "Lorem ipsum");
							formSeminarioAlumno.setItemValue('descripcion', "Lorem ipsum dolor sit amet");
			    		
							/*formSeminarioAlumno.load('editarseminario.do?idSeminario=' + idSeminario, function () {			    			
								formSeminarioAlumno.attachEvent("onButtonClick", function(id){
				    				if (id == "aceptar") {
				    					formSeminarioAlumno.send("actualizarse.do?!nativeeditor_status=save&idUsuario=" + idUsuario ,"post", function(xml) {
					    					
					    				});
					    				//buscar();
				    				}
				    			});
				    		});*/
				    			
			    		});
	
		    		
			    	});
			    	
			    	tabbar.addTab('tab_2','<bean:message key="title.seminarios.pendientes"/>','');
			    	var tab_2 = tabbar.cells('tab_2');
			    	
			    	gridAlumnoPendiente = tab_2.attachGrid();
		    		
			    	gridAlumnoPendiente.setHeader(["<bean:message key="label.nombre.seminario" />","<bean:message key="label.codigo.seminario" />","<bean:message key="label.descripcion.seminario" />"]);
			    	gridAlumnoPendiente.setColTypes("ro,ro,ro");
			    	
			    	gridAlumnoPendiente.setColSorting('str,str,str');
			    	gridAlumnoPendiente.enableMultiselect(false);
			    	gridAlumnoPendiente.init();
			    	
			    	
			    	gridAlumnoPendientePro = new dataProcessor("gridusuarios.do");
			    	gridAlumnoPendientePro.enableUTFencoding('simple');
			    	gridAlumnoPendientePro.init(gridAlumnoPendiente);	  
			    	gridAlumnoPendientePro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
			    			dhtmlx.message(tag.firstChild.data,action,4000);
			    		}
			    	});
		    		
			    	gridAlumnoPendiente.attachEvent("onRowSelect", function(row,ind){

			    		selectedCode=gridAlumnoPendiente.cells(row,1).getValue();
			    		
			    		
			    		var formSeminarioAlumno = b.attachForm();
			    		
			    		formSeminarioAlumno.loadStruct('../xml/forms/seminario_informacion_form.xml', function(){
			    			formSeminarioAlumno.setItemLabel('data','<bean:message key="title.info.general.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('nombre','<bean:message key="label.nombre.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('codigo','<bean:message key="label.codigo.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('curso','<bean:message key="label.curso.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('profesor','<bean:message key="label.profesor.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('descripcion','<bean:message key="label.descripcion.seminario"/>');
				    		
				    			
							
							formSeminarioAlumno.setItemValue('nombre', "cacaPENDIENTE");
							formSeminarioAlumno.setItemValue('codigo', "cacaPENDIENTE");
							formSeminarioAlumno.setItemValue('curso', "A113PENDIENTE");
							formSeminarioAlumno.setItemValue('profesor', "Lorem ipsumPENDIENTE");
							formSeminarioAlumno.setItemValue('descripcion', "Lorem ipsum dolor sit ametPENDIENTE");
			    		
							/*formSeminarioAlumno.load('editarseminario.do?idSeminario=' + idSeminario, function () {			    			
								formSeminarioAlumno.attachEvent("onButtonClick", function(id){
				    				if (id == "aceptar") {
				    					formSeminarioAlumno.send("actualizarse.do?!nativeeditor_status=save&idUsuario=" + idUsuario ,"post", function(xml) {
					    					
					    				});
					    				//buscar();
				    				}
				    			});
				    		});*/
				    			
			    		});
	
		    		
			    	});
			    	
		    	
				 	buscarAlumno();		
				}
				
	
				
				function goProfesor(){
					
					main_layout = new dhtmlXLayoutObject(document.body, '2U');
					main_layout.setAutoSize("a;b");
		    		var a = main_layout.cells('a');
		    		var b = main_layout.cells('b');
		    		a.setText(["<strong><bean:message key="label.mis.alumnos.estancia" /></strong>"]);
		    		b.hideHeader();
		    		gridProfesor = a.attachGrid();
					
		    		gridProfesor.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />","a","b"]);
		    		gridProfesor.setColTypes("ro,ro,ro,ro,ro");
			    	
		    		gridProfesor.enableMultiselect(false);
		    		gridProfesor.setColSorting('str,str,str,str,str');
		    		gridProfesor.init();
				    
				    
				   
					var idSelectedUser = <%=sessionIdUser%>;
					//ORIGINAL
				    //gridAlumnosProcessor = new dataProcessor("gridMisAlumnos.do?idUsuario"+idSelectedUser);
					//PRUEBA
				    var gridAlumnosProfesorProcessor = new dataProcessor("gridusuarios.do");
					
				    gridAlumnosProfesorProcessor.enableUTFencoding('simple');
				    gridAlumnosProfesorProcessor.init(gridProfesor);	  
				    gridAlumnosProfesorProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
			    			dhtmlx.message(tag.firstChild.data,action,4000);
			    		}
			    	});	
					
				    
				    
				    gridProfesor.attachEvent("onRowSelect", function(row,ind){
						
				    	
				    	selectedEmail=gridProfesor.cells(row,4).getValue();

				    	var tabbar = b.attachTabbar();
				    	tabbar.addTab('tab_1','<bean:message key="title.datos.personales"/>','');
				    	var tab_1 = tabbar.cells('tab_1');
				    	tabbar.setTabActive('tab_1');
				    	var form = tab_1.attachForm();
				    	form.loadStruct('../xml/forms/usuario_form.xml', function(){
				    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
				    		form.setItemLabel('grupo','<bean:message key="label.group"/>');
				    		form.setItemLabel('nombre','<bean:message key="label.nombre"/>');
				    		form.setItemLabel('telefono','<bean:message key="label.telefono"/>');
				    		form.setItemLabel('telefonoMovil','<bean:message key="label.telefono.movil"/>');
				    		form.setItemLabel('direccion','<bean:message key="label.direccion"/>');
				    		form.setItemLabel('codigoPostal','<bean:message key="label.postal.code"/>');
				    		form.setItemLabel('ciudad','<bean:message key="label.ciudad"/>');
				    		form.setItemLabel('pais','<bean:message key="label.pais"/>');
				    		form.setItemLabel('email','<bean:message key="label.address.email"/>');
				    		form.setItemLabel('comentarios','<bean:message key="label.comentarios"/>');
				    		form.setItemLabel('user','<bean:message key="label.user"/>');
				    		form.setItemLabel('pass','<bean:message key="label.pass"/>');			    		
				    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');

							<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >		    	
									form.hideItem('aceptar');								
							</logic:notMatch>			    		
				    		
				    		form.load('editarusuario.do?email=' + selectedEmail, function () {			    			
				    			form.attachEvent("onButtonClick", function(id){
				    				if (id == "aceptar") {
					    				form.send("actualizarusuario.do?!nativeeditor_status=save&email=" + selectedEmail ,"post", function(xml) {
					    					
					    				});
					    				buscarProfe();
				    				}
				    			});
				    		});
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
				    
				    buscarProfe();
				}
				
				function buscarProfe() {
					gridProfesor.clearAndLoad("gridusuarios.do");		    	
			    }
	    		
				function buscarAlumno() {
					gridAlumnoRealizado.clearAndLoad("gridusuarios.do");
					gridAlumnoPendiente.clearAndLoad("gridusuarios.do");
			    }
	    });
	    	
	    
	    	
	   </script>
	</head>
	<body>
	</body>
</html>