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
	    <link rel="stylesheet" type="text/css" href="../skins/dhtmlxform_dhx_skyblue.css">
	    <script type="text/javascript" src="../skins/dhtmlx.js"></script>
	    <script type="text/javascript" src="../skins/dhtmlxform.js"></script>
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../skins/imgs/';
	    	
	    	var miGrid, tabbar, tab_1,tab_2,tab_3,tab_4,tab_5,tab_6,tab_7, main_layout, form, b, a, gridAlumnoRealizado;
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
			    dhtmlxError.catchError("ALL",errorHandler);
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    a = main_layout.cells('a');
			    b = main_layout.cells('b');
			    
			    
			    a.setText("<strong><bean:message key="label.todos.mis.alumnos" /></strong>");
			    a.setWidth(380);
			    b.hideHeader();
			    
			    
			    miGrid = a.attachGrid();
			    miGrid.setIconsPath('../skins/imgs/');		    	
			    miGrid.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />"]);
			    
			    //anchura de las columnas, en porcentaje. La suma tiene que ser igual a 100
			    miGrid.setInitWidthsP("25,60,15");
			    //alineacion del contenido en la columna
			    miGrid.setColAlign("left,left,left");
			    
			    miGrid.setColTypes("ro,ro,ro");
		    	
			    miGrid.enableMultiselect(false);
			    miGrid.setColSorting('str,str,str');
			    miGrid.init();
		    	
				var gridProcessor = new dataProcessor("gridusuarios.do");
				gridProcessor.enableUTFencoding('simple');
				gridProcessor.init(miGrid);	  
				gridProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});		    	

			    miGrid.attachEvent("onRowSelect",doOnRowSelected);
			    
			    buscarMisAlumnos();
			    			    
		    });
		    
		   
		    function doOnRowSelected(rowID,celInd){
		    	
		    	var dni = miGrid.cells(rowID,2).getValue();
		    	
		    	tabbar = b.attachTabbar();
		    	tabbar.addTab('tab_1','<bean:message key="title.datos.personales"/>','');
		    	tab_1 = tabbar.cells('tab_1');
		    	tabbar.setTabActive('tab_1');
		    	goInformacion(dni);
		    	
		    	tabbar.addTab('tab_2','<bean:message key="title.info.general.estancia"/>','');
		    	tab_2 = tabbar.cells('tab_2');
		    	goEstancia(dni);
		    	
		    	tabbar.addTab('tab_3','<bean:message key="title.seminarios"/>','');
		    	tab_3 = tabbar.cells('tab_3');
		    	goSeminarios(dni);
		    	
		    	tabbar.addTab('tab_4','<bean:message key="title.trabajos.campo"/>','');
		    	tab_4 = tabbar.cells('tab_4');
		    	//goTrabajos(dni);
		    	
		    	tabbar.addTab('tab_5','<bean:message key="title.casos.clinicos"/>','');
		    	tab_5 = tabbar.cells('tab_5');
		    	//goCasos(dni);
		    	
		    	tabbar.addTab('tab_6','<bean:message key="title.diario.reflexivo"/>','');
		    	tab_6 = tabbar.cells('tab_6');
		    	//goCasos(dni);
		    	
		    	tabbar.addTab('tab_7','<bean:message key="title.rubrica"/>','');
		    	tab_7 = tabbar.cells('tab_7');
		    	//goCasos(dni);
		    	
		    	
		    }
		    
		    
		    function buscarMisAlumnos() {
		    	miGrid.clearAndLoad("gridusuarios.do");		    	
		    }
		    
		    function buscarSeminarios() {
		    	gridAlumnoRealizado.clearAndLoad("gridusuarios.do");		    	
		    }
		    
		    function goInformacion(dni){
		    	
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
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>36</permiso>" >		    	
							form.hideItem('aceptar');
						</logic:notMatch>
					</logic:notMatch>			    		
		    		
		    		
				// HACER LAS MODIFICACIONES NECESARIAS PARA REALIZAR UNA BUSQUEDA SEGUN DNI CUANDO ESTE HECHA LA BBDD
				//	form.load('editarusuario.do?email=' + selectedEmail, function () {			    			
		    	//		form.attachEvent("onButtonClick", function(id){
		    	//			if (id == "aceptar") {
			    //				form.send("actualizarusuario.do?!nativeeditor_status=save&email=" + selectedEmail ,"post", function(xml) {
			    //					
			    //				});
			    //				buscar();
		    	//			}
		    	//		});
		    	//	});
					
		    	});
		    	
		    	
		    }
		    
		    
		    function goEstancia(dni){
		    	
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
				
				
		    }
		    
		    function goSeminarios(dni){	    	
		    	
		    	var mini_layout = tabbar.cells('tab_3').attachLayout("2U","dhx_skyblue");
		    	
		    	var ma = mini_layout.cells('a');
			    var mb = mini_layout.cells('b');
			    
			    ma.setText('<bean:message key="title.seminarios.realizados"/>');
			    
			    mb.setText('<bean:message key="label.descripcion.seminario"/>');
	    		mb.setWidth(500);
			    
			    
				gridAlumnoRealizado = ma.attachGrid();
		    	
		    	gridAlumnoRealizado.setHeader(["<bean:message key="label.nombre.seminario" />","<bean:message key="label.codigo.seminario" />","<bean:message key="label.descripcion.seminario" />"]);
		    	gridAlumnoRealizado.setColTypes("ro,ro,ro");
		    	
		    	gridAlumnoRealizado.setColSorting('str,str,str');
		    	gridAlumnoRealizado.enableMultiselect(false);
		    	gridAlumnoRealizado.init();
		    	
		    	var gridAlumnoRealizadoPro = new dataProcessor("gridusuarios.do");
		    	gridAlumnoRealizadoPro.enableUTFencoding('simple');
		    	gridAlumnoRealizadoPro.init(gridAlumnoRealizado);	  
		    	gridAlumnoRealizadoPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
	    		
		    	gridAlumnoRealizado.attachEvent("onRowSelect", function(row,ind){

		    		selectedCode=gridAlumnoRealizado.cells(row,1).getValue();
		    		
		    		
		    		var formSeminarioAlumno = mb.attachForm();
		    		
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
			    

		    	buscarSeminarios();
		    }
		    
		    
		    
		    
		    
		    
        </script>
	</head>
	<body>
	</body>
</html>