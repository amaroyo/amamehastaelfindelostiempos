<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>
<%@ include file="../../common/taglibs.jsp" %>

<html>
	<head>
	    <link rel="stylesheet" type="text/css" href="../css/estilos.css">
	    <link rel="stylesheet" type="text/css" href="../css/templates.css">
	    <link rel="stylesheet" type="text/css" href="../css/estilosMenu.css">
	    <link rel="stylesheet" type="text/css" href="../skins/dhtmlx.css">
	    <script type="text/javascript" src="../skins/dhtmlx.js"></script>
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../skins/imgs/';
	    	
	    	var main_layout, areaTrabajoCursos, listado, toolbarCursos, opcionSeminarioAsignatura,
	    	idSelectedCourse, gridCursos, tabbarCursos, tabInfo;
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	dhtmlxError.catchError("ALL",errorHandler);
		    	<% String opcion = request.getParameter("opcion");%>
		    	opcionSeminarioAsignatura="<%=opcion%>";
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    listado = main_layout.cells('a');
			    listado.setWidth(600);
			    areaTrabajoCursos = main_layout.cells('b');
			    
			    //autosize(horizontal,vertical)
			    //"a;b" 'a' and 'b' will autosize when changing horizontal dimensions of layout
			    //listado.setAutoSize("a;b",null)
			    
			    if (opcionSeminarioAsignatura == "seminarios") {
			    	listado.setText("<strong><bean:message key="title.seminarios" /></strong>");
				    areaTrabajoCursos.setText("<bean:message key="title.propiedades.seminario" />");
			    }
			    else if (opcionSeminarioAsignatura == "asignaturas") {
			    	listado.setText("<strong><bean:message key="title.asignaturas" /></strong>");
			    	areaTrabajoCursos.setText("<bean:message key="title.propiedades.asignatura" />");
			    }
			    	
			    toolbarCursos = listado.attachToolbar();
			    toolbarCursos.setIconsPath('../skins/imgs/toolbar/');
			    toolbarCursos.loadXML('../xml/toolbars/dhxtoolbar-cursos.xml', function(){
		    		if(opcionSeminarioAsignatura == "seminarios") {
		    			toolbarCursos.setItemText('new',"<bean:message key="button.create.seminario"/>");
		    			toolbarCursos.setItemText('delete',"<bean:message key="button.eliminar.seminario"/>");
		    		}
		    		else if(opcionSeminarioAsignatura == "asignaturas") {
		    			toolbarCursos.setItemText('new',"<bean:message key="button.create.asignatura"/>");
		    			toolbarCursos.setItemText('delete',"<bean:message key="button.eliminar.asignatura"/>");
		    		}
		    		toolbarCursos.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
		    		<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >	    	
			    		toolbarCursos.hideItem('new');
			    		toolbarCursos.hideItem('sep1');    	
			    		toolbarCursos.hideItem('delete');
			    		toolbarCursos.hideItem('sep2');
					</logic:notMatch>
		    	});
			    
			    
			    gridCursos = listado.attachGrid();
			    gridCursos.setIconsPath('../skins/imgs/');
			    if(opcionSeminarioAsignatura == "seminarios") {
			    	gridCursos.setHeader(["<strong><bean:message key="label.codigo.seminario" /></strong>",
			    	                      "<strong><bean:message key="label.nombre.seminario" /></strong>",
			    	                      "<strong><bean:message key="label.curso.seminario" /></strong>",
			    	                      "<strong><bean:message key="label.descripcion.seminario" /></strong>"]);
			    }
			    else if(opcionSeminarioAsignatura == "asignaturas") {
			   		gridCursos.setHeader(["<strong><bean:message key="label.codigo.asignatura" /></strong>"
			   		                      ,"<strong><bean:message key="label.nombre.asignatura" /></strong>",
			   		                      "<strong><bean:message key="label.curso.asignatura" /></strong>",
			   		                      "<strong><bean:message key="label.descripcion.asignatura" /></strong>"]);
			    }
				
			    //ro = readonly
			    //nombre codigo curso descripcion
			    gridCursos.setColTypes("ro,ro,ro,ro");
			    gridCursos.setColSorting('str,str,str,str');
			    gridCursos.enableMultiselect(false);
			    gridCursos.init();
			    gridCursosProcessor = new dataProcessor("gridcursos.do");
			    gridCursosProcessor.enableUTFencoding('simple');
			    gridCursosProcessor.init(gridCursos);	  
			    gridCursosProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
			    gridCursos.attachEvent("onRowSelect", function(idCurso,ind){
		    		toolbarUsuarios.enableItem('delete');
					idSelectedCourse = idCurso;
					// obtener el nombre del curso de la bbdd y añadirlo como header a la dcha
					areaTrabajoCursos.attachHeader("");
			    
				    tabbarCursos = areaTrabajoCursos.attachTabbar();
				    tabbarCursos.addTab('tabInfo','<bean:message key="label.info.general"/>','');
			    	tabInfo = tabbarCursos.cells('tabInfo');
			    	tabbarCursos.setTabActive('tabInfo');
			    	formInfo = tabInfo.attachForm();
			    	loadFormSeminarioAsignatura(opcionSeminarioAsignatura);
			    	
			    	
			    	
			    });
				  
		    });
		    
		    function loadFormSeminarioAsignatura(){
		    	if(opcionSeminarioAsignatura == "seminarios") {
		    		formInfo.loadStruct('../xml/forms/seminario_informacion_form.xml', function(){
		    			formInfo.setItemLabel('data','<bean:message key="title.info.general.seminario"/>');
		    			formInfo.setItemLabel('nombre','<bean:message key="label.nombre.seminario"/>');
		    			formInfo.setItemLabel('codigo','<bean:message key="label.codigo.seminario"/>');
		    			formInfo.setItemLabel('curso','<bean:message key="label.curso.seminario"/>');
		    			formInfo.setItemLabel('profesor','<bean:message key="label.profesor.seminario"/>');
		    			formInfo.setItemLabel('descripcion','<bean:message key="label.descripcion.seminario"/>');
			    		
			    		<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >    	
			    			formInfo.forEachItem(function(id){
			    				if(getType(id) == "input"){
			    			   		myForm.setReadOnly(id, true);
			    				}
			    				else if(getType(id) == "button"){
			    					form.hideItem(id);
			    				}
			    			});
						</logic:notMatch>		
						
						form.setItemValue('nombreAsignatura', idAsignatura);
			    		form.setItemValue('codigo', idAsignatura);
			    		form.setItemValue('curso', "A113");
			    		form.setItemValue('profesor', "Lorem ipsum");
			    		form.setItemValue('descripcion', "Lorem ipsum dolor sit amet,
		    		
			    		form.load('editarseminario.do?idSeminario=' + idSeminario, function () {			    			
			    			form.attachEvent("onButtonClick", function(id){
			    				if (id == "aceptar") {
				    				form.send("actualizarse.do?!nativeeditor_status=save&idUsuario=" + idUsuario ,"post", function(xml) {
				    					
				    				});
				    				buscar();
			    				}
			    			});
			    		});
		    		});
		    	}
		    	else if(opcionSeminarioAsignatura == "asignaturas") {
		    		formInfo.loadStruct('../xml/forms/asignatura_informacion_form.xml', function(){
		    			formInfo.setItemLabel('data','<bean:message key="title.info.general.asignatura"/>');
		    			formInfo.setItemLabel('nombre','<bean:message key="label.nombre.asignatura"/>');
		    			formInfo.setItemLabel('codigo','<bean:message key="label.codigo.asignatura"/>');
		    			formInfo.setItemLabel('curso','<bean:message key="label.curso.asignatura"/>');
		    			formInfo.setItemLabel('profesor','<bean:message key="label.profesor.asignatura"/>');
		    			formInfo.setItemLabel('descripcion','<bean:message key="label.descripcion.asignatura"/>');
			    		
		    			<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >    	
			    			formInfo.forEachItem(function(id){
			    				if(getType(id) == "input"){
			    			   		myForm.setReadOnly(id, true);
			    				}
			    				else if(getType(id) == "button"){
			    					form.hideItem(id);
			    				}
			    			});
						</logic:notMatch>				    		
			    		
			    		form.load('editarusuario.do?idUsuario=' + idUsuario, function () {			    			
			    			form.attachEvent("onButtonClick", function(id){
			    				if (id == "aceptar") {
				    				form.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idUsuario ,"post", function(xml) {
				    					
				    				});
				    				buscar();
			    				}
			    			});
			    		});
		    		});
		    	}
	    	}
		  
        </script>
	</head>
	<body>
	</body>
</html>