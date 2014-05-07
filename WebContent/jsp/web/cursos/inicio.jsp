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
	    	
	    	var main_layout, areaTrabajoCursos, listado, toolbarCursos, opcionSeminarioOAsignatura,
	    	idSelectedCourse, gridCursos, tabbarCursos, tabInfo;
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	dhtmlxError.catchError("ALL",errorHandler);
		    	<% String opcion = request.getParameter("opcion");%>
		    	opcionSeminarioOAsignatura="<%=opcion%>";
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    listado = main_layout.cells('a');
			    listado.setWidth(600);
			    areaTrabajoCursos = main_layout.cells('b');
			    
			    //autosize(horizontal,vertical)
			    //"a;b" 'a' and 'b' will autosize when changing horizontal dimensions of layout
			    //listado.setAutoSize("a;b",null)
			    
			    if (opcionSeminarioOAsignatura == "seminarios") {
			    	listado.setText("<strong><bean:message key="title.seminarios" /></strong>");
				    areaTrabajoCursos.setText("<bean:message key="title.propiedades.seminario" />");
			    }
			    else if (opcionSeminarioOAsignatura == "asignaturas") {
			    	listado.setText("<strong><bean:message key="title.asignaturas" /></strong>");
			    	areaTrabajoCursos.setText("<bean:message key="title.propiedades.asignatura" />");
			    }
			    	
			    toolbarCursos = listado.attachToolbar();
			    toolbarCursos.setIconsPath('../skins/imgs/toolbar/');
			    toolbarCursos.loadXML('../xml/toolbars/dhxtoolbar-cursos.xml', function(){
		    		if(opcionSeminarioOAsignatura == "seminarios") {
		    			toolbarCursos.setItemText('new',"<bean:message key="button.create.seminario"/>");
		    			toolbarCursos.setItemText('delete',"<bean:message key="button.eliminar.seminario"/>");
		    		}
		    		else if(opcionSeminarioOAsignatura == "asignaturas") {
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
			    if(opcionSeminarioOAsignatura == "seminarios") {
			    	gridCursos.setHeader(["<strong><bean:message key="label.codigo.seminario" /></strong>",
			    	                      "<strong><bean:message key="label.nombre" /></strong>",
			    	                      "<strong><bean:message key="label.curso" /></strong>",
			    	                      "<strong><bean:message key="label.descripcion" /></strong>"]);
			    }
			    else if(opcionSeminarioOAsignatura == "asignaturas") {
			   		gridCursos.setHeader(["<strong><bean:message key="label.codigo.asignatura" /></strong>"
			   		                      ,"<strong><bean:message key="label.nombre" /></strong>",
			   		                      "<strong><bean:message key="label.curso" /></strong>",
			   		                      "<strong><bean:message key="label.descripcion" /></strong>"]);
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
			    	tabbarCursos.setTabActive('tab_1');
			    	form = tab_1.attachForm();
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
			    });
				  
		    });
		  
        </script>
	</head>
	<body>
	</body>
</html>