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
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
	    <script src="../js/dhtmlxSuite/patterns/dhtmlxlayout_pattern4l.js"></script>
	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxform.js"></script>
	    

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	
	    	var miGrid, tabbar, tab_1,tab_2,tab_3,tab_4,tab_5,tab_6,tab_7, main_layout, form, b, a, gridAlumnoRealizadoSem, gridProfesoresTrab,
	    				gridProfesoresCasos;
	    	
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
			    });
			    
			    miGrid = a.attachGrid();
			    miGrid.setIconsPath('../skins/imgs/');		   
			    
			    // INSERTAR EL LABEL CORRECTO PARA ASIGNATURAS DESPUES DEL UPDATE!!!
			    
			    miGrid.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />","CODIGO"]);
			    
			    //anchura de las columnas, en porcentaje. La suma tiene que ser igual a 100
			    miGrid.setInitWidthsP("22,50,13,15");
			    //alineacion del contenido en la columna
			    miGrid.setColAlign("left,left,left,left");
			    
			    miGrid.setColTypes("ro,ro,ro,ro");
		    	
			    miGrid.enableMultiselect(false);
			    miGrid.setColSorting('str,str,str,ro');
			    miGrid.init();
		    	
				var gridProcessor = new dataProcessor("gridusuarios.do");
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
		    	
		    	var dni = miGrid.cells(rowID,2).getValue();
		    	var asignatura = miGrid.cells(rowID,3).getValue();
		    	
		    	
		    	b.setText(asignatura + " - " + "NOMBRE DE ASIGNATURA SELECCIONADA");
		    	tabbar = b.attachTabbar();
		    	b.showHeader();
		    	
		    	tabbar.addTab('tab_1','<bean:message key="title.datos.personales"/>','');
		    	tab_1 = tabbar.cells('tab_1');
		    	tabbar.setTabActive('tab_1');
		    	goInformacion(dni,asignatura);
		    	
		    	tabbar.addTab('tab_2','<bean:message key="title.info.general.estancia"/>','');
		    	tab_2 = tabbar.cells('tab_2');
		    	goEstancia(dni,asignatura);
		    	
		    	tabbar.addTab('tab_3','<bean:message key="title.seminarios"/>','');
		    	tab_3 = tabbar.cells('tab_3');
		    	goSeminarios(dni,asignatura);
		    	
		    	tabbar.addTab('tab_4','<bean:message key="title.trabajos.campo"/>','');
		    	tab_4 = tabbar.cells('tab_4');
		    	goTrabajos(dni,asignatura);
		    	
		    	tabbar.addTab('tab_5','<bean:message key="title.casos.clinicos"/>','');
		    	tab_5 = tabbar.cells('tab_5');
		    	goCasos(dni,asignatura);
		    	
		    	tabbar.addTab('tab_6','<bean:message key="title.diario.reflexivo"/>','');
		    	tab_6 = tabbar.cells('tab_6');
		    	goDiarios(dni,asignatura);
		    	
		    	tabbar.addTab('tab_7','<bean:message key="title.rubrica"/>','');
		    	tab_7 = tabbar.cells('tab_7');
		    	goRubricas(dni,asignatura);
		    	
		    	
		    }
		    
		    
		    
		    
		    function buscarMisAlumnos() {
		    	miGrid.clearAndLoad("gridusuarios.do");		    	
		    }
		    
		    function buscarSeminarios() {
		    	gridAlumnoRealizadoSem.clearAndLoad("gridusuarios.do");		    	
		    }
		    
		    function goInformacion(dni,asignatura){
		    	
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
		    
		    
		    function goEstancia(dni,asignatura){
		    	
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
		    
		    function goSeminarios(dni,asignatura){	    	
		    	
		    	var mini_layout = tabbar.cells('tab_3').attachLayout("2U","dhx_skyblue");
		    	
		    	var ma = mini_layout.cells('a');
			    var mb = mini_layout.cells('b');
			    
			    ma.setText('<bean:message key="title.seminarios.realizados"/>');
			    
			    mb.setText('<bean:message key="label.descripcion.seminario"/>');
	    		mb.setWidth(500);
			    
			    
				gridAlumnoRealizadoSem = ma.attachGrid();
		    	
		    	gridAlumnoRealizadoSem.setHeader(["<bean:message key="label.nombre.seminario" />","<bean:message key="label.codigo.seminario" />","<bean:message key="label.descripcion.seminario" />"]);
		    	gridAlumnoRealizadoSem.setColTypes("ro,ro,ro");
		    	
		    	gridAlumnoRealizadoSem.setColSorting('str,str,str');
		    	gridAlumnoRealizadoSem.enableMultiselect(false);
		    	gridAlumnoRealizadoSem.init();
		    	
		    	var gridAlumnoRealizadoSemPro = new dataProcessor("gridusuarios.do");
		    	gridAlumnoRealizadoSemPro.enableUTFencoding('simple');
		    	gridAlumnoRealizadoSemPro.init(gridAlumnoRealizadoSem);	  
		    	gridAlumnoRealizadoSemPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
	    		
		    	gridAlumnoRealizadoSem.attachEvent("onRowSelect", function(row,ind){

		    		selectedCode=gridAlumnoRealizadoSem.cells(row,1).getValue();
		    		
		    		
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
		    
		    
			function goTrabajos(dni,asignatura){
				
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
		    		toolbarServicios.setItemText('subirPractica',"<bean:message key="button.subir.practica"/>");
		    		toolbarServicios.setItemText('descargarTodos',"<bean:message key="button.descargar.casos"/>");
		    		toolbarServicios.setItemText('descargarTodosAlumno',"<bean:message key="button.descargar.casos.alumno"/>");
		    		toolbarServicios.setItemText('fechaLimite',"<bean:message key="button.fecha.limite"/>");
		    		toolbarServicios.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
		    	});
		    	
		    	
				gridProfesoresTrab = ma.attachGrid();
		    	
				gridProfesoresTrab.setHeader(["<bean:message key="label.alumno" />","<bean:message key="label.dni" />","<bean:message key="label.fecha" />","<bean:message key="label.enlace" />"]);
				gridProfesoresTrab.setColTypes("ro,ro,ro,ro");
		    	
				gridProfesoresTrab.setColSorting('str,str,str,str');
				gridProfesoresTrab.enableMultiselect(false);
				gridProfesoresTrab.init();
		    	
		    	var gridProcessorPro = new dataProcessor("gridusuarios.do");
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridProfesoresTrab);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});

		    	
		    	gridProfesoresTrab.attachEvent("onRowSelect", function doOnRowSelected(rowID,celInd){
		    		var miGrid = mb.attachGrid();
				    miGrid.setIconsPath('../skins/imgs/');		    	
				    miGrid.setHeader(["<strong><bean:message key="label.mi.perfil" /></strong>"]);
				    //set readonly (ro)
				    miGrid.setColTypes("ro");
				    miGrid.setNoHeader(true);
				    miGrid.enableMultiselect(false);
				    miGrid.init();
				    miGrid.loadXML("../xml/forms/asignaturas_trabajos_opciones.xml");
				    miGrid.attachEvent("onRowSelect",doOnRowSelectedOptions); 
		    		
		    	});
		    	
		    	gridProfesoresTrab.clearAndLoad("gridusuarios.do");
		    	
		    }
			
   
			
			function doOnRowSelectedOptions(rowID,celInd){
				
				if (rowID == "a") alert("Descargar");
		    	else if (rowID == "b") alert("Subir corrección");
		    	else alert("Cambiar la fecha de entrega");
			}
		    
			
			function subirPractica(){
				alert("subir Practica");
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
				gridProfesoresTrab.clearAndLoad("gridusuarios.do");	 	   		    	
		    }
			
			function seleccionarAlumnos(){
				alert("seleccionarAlumnos");
			}
			
			function goActualizarAlumnos(){
				alert("actualizar alumnos");
			}
			
			function goCasos(dni,asignatura){
				
				var mini_layout = tabbar.cells('tab_5').attachLayout("2U","dhx_skyblue");
		    	
		    	var ma = mini_layout.cells('a');
			    var mb = mini_layout.cells('b');
			    
			    mb.setWidth(250);
	    		ma.hideHeader();
				mb.setText('<bean:message key="label.casos.clinicos.alumno"/>');
				
				var toolbarServiciosCasos = ma.attachToolbar();
				toolbarServiciosCasos.setIconsPath('../img/toolbar/');

		    	
				toolbarServiciosCasos.loadXML('../xml/toolbars/dhxtoolbar-trabajos-campo.xml', function(){
					toolbarServiciosCasos.setItemText('crearTrabajoCampo',"<bean:message key="button.crear.trabajo.campo"/>");
					toolbarServiciosCasos.setItemText('subirPractica',"<bean:message key="button.subir.practica"/>");
					toolbarServiciosCasos.setItemText('descargarTodos',"<bean:message key="button.descargar.casos"/>");
					toolbarServiciosCasos.setItemText('descargarTodosAlumno',"<bean:message key="button.descargar.casos.alumno"/>");
					toolbarServiciosCasos.setItemText('fechaLimite',"<bean:message key="button.fecha.limite"/>");
					toolbarServiciosCasos.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
		    	});
				
				
				gridProfesoresCasos = ma.attachGrid();
		    	
				gridProfesoresCasos.setHeader(["<bean:message key="label.alumno" />","<bean:message key="label.dni" />","<bean:message key="label.fecha" />","<bean:message key="label.enlace" />"]);
				gridProfesoresCasos.setColTypes("ro,ro,ro,ro");
		    	
				gridProfesoresCasos.setColSorting('str,str,str,str');
				gridProfesoresCasos.enableMultiselect(false);
				gridProfesoresCasos.init();
		    	
		    	var gridProcessorPro = new dataProcessor("gridusuarios.do");
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridProfesoresCasos);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});

		    	
		    	gridProfesoresCasos.attachEvent("onRowSelect",function doOnRowSelected(rowID,celInd){
		    		
		    		var gridProfesoresAlumno = mb.attachGrid();
					
			    	
					gridProfesoresAlumno.setHeader(["<bean:message key="label.nombre" />", "<bean:message key="label.fecha" />"]);
					gridProfesoresAlumno.setColTypes("ro,ro");
			    	
					gridProfesoresAlumno.setColSorting('str, str');
					gridProfesoresAlumno.enableMultiselect(false);
					gridProfesoresAlumno.init();
			    	
			    	var gridProcessorPro = new dataProcessor("gridusuarios.do");
			    	gridProcessorPro.enableUTFencoding('simple');
			    	gridProcessorPro.init(gridProfesoresAlumno);	  
			    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
			    			dhtmlx.message(tag.firstChild.data,action,4000);
			    		}
			    	});

			    	   	
			    	gridProfesoresAlumno.attachEvent("onRowSelect",doOnRowSelectedOptionsCasos);
			    	gridProfesoresAlumno.clearAndLoad("gridusuarios.do");
		    	});
		    	
		    	gridProfesoresCasos.clearAndLoad("gridusuarios.do");
				
				
			}
			
			function doOnRowSelectedOptionsCasos(rowID,celInd){
				alert("Descargar Archivo");
	
			}
		    
			
			function goDiarios(dni,asignatura){
				
				var mini_layout = tabbar.cells('tab_6').attachLayout("2U","dhx_skyblue");
		    	
		    	var ma = mini_layout.cells('a');
			    var mb = mini_layout.cells('b');
			    
			    mb.setWidth(250);
	    		ma.hideHeader();
				mb.setText('<bean:message key="label.diario.reflexivo.alumno"/>');
				
				var toolbarServiciosDiarios = ma.attachToolbar();
				toolbarServiciosDiarios.setIconsPath('../img/toolbar/');

		    	
				toolbarServiciosDiarios.loadXML('../xml/toolbars/dhxtoolbar-trabajos-campo.xml', function(){
					toolbarServiciosDiarios.setItemText('crearTrabajoCampo',"<bean:message key="button.crear.trabajo.campo"/>");
					toolbarServiciosDiarios.setItemText('subirPractica',"<bean:message key="button.subir.practica"/>");
					toolbarServiciosDiarios.setItemText('descargarTodos',"<bean:message key="button.descargar.casos"/>");
					toolbarServiciosDiarios.setItemText('descargarTodosAlumno',"<bean:message key="button.descargar.casos.alumno"/>");
					toolbarServiciosDiarios.setItemText('fechaLimite',"<bean:message key="button.fecha.limite"/>");
					toolbarServiciosDiarios.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
		    	});
				
				
				gridProfesoresDiarios = ma.attachGrid();
		    	
				gridProfesoresDiarios.setHeader(["<bean:message key="label.alumno" />","<bean:message key="label.dni" />","<bean:message key="label.fecha" />","<bean:message key="label.enlace" />"]);
				gridProfesoresDiarios.setColTypes("ro,ro,ro,ro");
		    	
				gridProfesoresDiarios.setColSorting('str,str,str,str');
				gridProfesoresDiarios.enableMultiselect(false);
				gridProfesoresDiarios.init();
		    	
		    	var gridProcessorPro = new dataProcessor("gridusuarios.do");
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridProfesoresDiarios);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});

		    	
		    	gridProfesoresDiarios.attachEvent("onRowSelect",function doOnRowSelected(rowID,celInd){
		    		
		    		var gridProfesoresAlumno = mb.attachGrid();
					
			    	
					gridProfesoresAlumno.setHeader(["<bean:message key="label.nombre" />", "<bean:message key="label.fecha" />"]);
					gridProfesoresAlumno.setColTypes("ro,ro");
			    	
					gridProfesoresAlumno.setColSorting('str, str');
					gridProfesoresAlumno.enableMultiselect(false);
					gridProfesoresAlumno.init();
			    	
			    	var gridProcessorPro = new dataProcessor("gridusuarios.do");
			    	gridProcessorPro.enableUTFencoding('simple');
			    	gridProcessorPro.init(gridProfesoresAlumno);	  
			    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
			    			dhtmlx.message(tag.firstChild.data,action,4000);
			    		}
			    	});

			    	   	
			    	gridProfesoresAlumno.attachEvent("onRowSelect",doOnRowSelectedOptionsDiarios);
			    	gridProfesoresAlumno.clearAndLoad("gridusuarios.do");
		    	});
		    	
		    	gridProfesoresDiarios.clearAndLoad("gridusuarios.do");
			}
			
			
			function doOnRowSelectedOptionsDiarios(rowID,celInd){
				alert("Descargar Archivo");
	
			}
			
			
			function goRubricas(dni,asignatura){
				
				
			}
		    
		    
        </script>
	</head>
	<body>
	</body>
</html>