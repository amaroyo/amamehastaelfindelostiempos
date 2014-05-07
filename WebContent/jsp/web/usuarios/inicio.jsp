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
	    	
	    	var gridLeads, toolbarUsuarios, tabbar,tab_1,tab_2, form, idSelectedUserService, toolbarServicios, tab_3, gridServicios, idSelectedUser;
	    	
		    dhtmlxEvent(window,"load",function() {

		    	dhtmlxError.catchError("ALL",errorHandler);

		    	var main_layout = new dhtmlXLayoutObject(document.body, '2U');

		    	var a = main_layout.cells('a');
		    	a.hideHeader();
		    	toolbarUsuarios = a.attachToolbar();
		    	toolbarUsuarios.setIconsPath('../skins/imgs/toolbar/');
		    	
		    	toolbarUsuarios.loadXML('../xml/toolbars/dhxtoolbar-usuarios.xml', function(){
		    		toolbarUsuarios.setItemText('new',"<bean:message key="button.create.user"/>");
		    		toolbarUsuarios.setItemText('delete',"<bean:message key="button.eliminar"/>");
		    		toolbarUsuarios.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>35</permiso>" >		    	
							toolbarUsuarios.hideItem('new');
							toolbarUsuarios.hideItem('sep1');
	    				</logic:notMatch>
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>37</permiso>" >		    	
							toolbarUsuarios.hideItem('delete');
							toolbarUsuarios.hideItem('sep2');
						</logic:notMatch>
					</logic:notMatch>
		    		
		    		
		    	});
		    	gridLeads = a.attachGrid();
		    	gridLeads.setIconsPath('../skins/imgs/');
		    	
		    	gridLeads.setHeader(["<bean:message key="label.group" />","<bean:message key="label.nombre" />","<bean:message key="label.telefono" />","<bean:message key="label.telefono.movil" />","<bean:message key="label.address.email" />"]);
		    	gridLeads.setColTypes("ro,ro,ro,ro,ro");
		    	
		    	gridLeads.setColSorting('str,str,str,str,str');
		    	gridLeads.enableMultiselect(true);
		    	gridLeads.init();
		    	
				gridLeadsProcessor = new dataProcessor("gridusuarios.do");
				gridLeadsProcessor.enableUTFencoding('simple');
				gridLeadsProcessor.init(gridLeads);	  
				gridLeadsProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});		    	
		    	
		    	gridLeads.attachEvent("onRowSelect", function(idUsuario,ind){
		    		toolbarUsuarios.enableItem('delete');
		    		
		    	
		    		idSelectedUser = idUsuario;
		    		
		    		
		    		b.showHeader();
		    		b.setText("<bean:message key="title.comercial.propiedades" />");

		    		
			    	tabbar = b.attachTabbar();
			    	tabbar.addTab('tab_1','<bean:message key="label.propiedades"/>','');
			    	tab_1 = tabbar.cells('tab_1');
			    	tabbar.setTabActive('tab_1');
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
			    	
			    	
			    	tabbar.addTab('tab_2','<bean:message key="layout.allowed.actions"/>','');
			    	tab_2 = tabbar.cells('tab_2');
			    	gridPermisos = tab_2.attachGrid();
			    	gridPermisos.setIconsPath('../skins/imgs/');			    	
			    	gridPermisos.setHeader(["<bean:message key="label.nombre" />"]);
			    	gridPermisos.setColTypes("ro");			    	
			    	gridPermisos.setColSorting('str');
			    	idGrupo = 1;
			    	gridPermisos.load("../grupos/gridpermisosgrupo.do?idUsuario="+idUsuario);			    	
			    	gridPermisos.init();
			    	
			    	
			    	
			    	tabbar.addTab('tab_3','<bean:message key="layout.list.of.services"/>','');
			    	tab_3 = tabbar.cells('tab_3');
			    	
			    	toolbarServicios = tab_3.attachToolbar();
			    	toolbarServicios.setIconsPath('../skins/imgs/toolbar/');
			    	toolbarServicios.loadXML('../xml/toolbars/dhxtoolbar-servicios-usuario.xml', function(){
			    		toolbarServicios.setItemText('new',"<bean:message key="button.add.service"/>");
			    		toolbarServicios.setItemText('delete',"<bean:message key="button.eliminar"/>");
			    		toolbarServicios.setItemText('refresh',"<bean:message key="button.actualizar"/>");
			    	});
			    	
			    	gridServicios = tab_3.attachGrid();
			    	gridServicios.setIconsPath('../skins/imgs/');			    	
			    	gridServicios.setHeader(["<bean:message key="label.nombre" />"]);
			    	gridServicios.setColTypes("ro");			    	
			    	gridServicios.setColSorting('str');
			    	gridServicios.load("gridserviciosusuario.do?idUsuario="+idUsuario);			    	
			    	gridServicios.init();
			    	
				    gridServiciosProcessor = new dataProcessor("gridserviciosusuario.do");
				    gridServiciosProcessor.enableUTFencoding('simple');
				    gridServiciosProcessor.init(gridServicios);	  
				    gridServiciosProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
				    		dhtmlx.message(tag.firstChild.data,action,4000);
				    	}
				    });		    	
				    
				    gridServicios.attachEvent("onRowSelect", function(idServicioUsuario,ind){
				    	toolbarServicios.enableItem('delete');
				    	idSelectedUserService = idServicioUsuario;
				    });
			    	
		    	});//OnRowSelected
		    	
		    	buscar();		    	

		    	var b = main_layout.cells('b');
		    	b.hideHeader();

		    	
		    });

	
		    function buscar() {
		    	gridLeads.clearAndLoad("gridusuarios.do");		    	
		    }
		
		    function newUser() {
				var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("users", 300, 50, 385, 510);
				window.setText('<bean:message key="title.new.user" />');				
				window.setModal(true);
				window.centerOnScreen();
				var form = window.attachForm();			
		    	form.loadStruct('../xml/forms/new_usuario_form.xml', function() {
		    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
		    		form.setItemLabel('idGrupo','<bean:message key="label.group"/>');
		    		form.setItemLabel('idAsociado','<bean:message key="label.associated.to"/>');
		    		form.setItemLabel('nombre','<bean:message key="label.nombre"/>');
		    		form.setItemLabel('telefono','<bean:message key="label.telefono"/>');
		    		form.setItemLabel('telefonoMovil','<bean:message key="label.telefono.movil"/>');
		    		form.setItemLabel('direccion','<bean:message key="label.direccion"/>');
		    		form.setItemLabel('codigoPostal','<bean:message key="label.postal.code"/>');
		    		form.setItemLabel('ciudad','<bean:message key="label.ciudad"/>');
		    		form.setItemLabel('pais','<bean:message key="label.pais"/>');
		    		form.setItemLabel('email','<bean:message key="label.address.email"/>');
		    		form.setItemLabel('user','<bean:message key="label.user"/>');
		    		form.setItemLabel('pass','<bean:message key="label.pass"/>');
		    		form.setItemLabel('comentarios','<bean:message key="label.comentarios"/>');
		    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
		    		
		    		form.getCombo('idGrupo').readonly(1);
    				form.getCombo('idGrupo').setComboValue("0");
    				form.getCombo('idGrupo').setComboText("");
    				
					<logic:match scope="session" name="usuarioYPermisos" value="<grupo>2</grupo>" >
						form.getCombo('idGrupo').clearAll();
						form.getCombo('idGrupo').addOption([[3, 'Distributor'], [4, 'Sales rep.'], [5, 'Supplier']]);
					</logic:match>
					<logic:match scope="session" name="usuarioYPermisos" value="<grupo>3</grupo>" >		    	
						form.getCombo('idGrupo').clearAll();
						form.getCombo('idGrupo').addOption([[4, 'Sales rep.'], [5, 'Supplier']]);
					</logic:match>
					<logic:match scope="session" name="usuarioYPermisos" value="<grupo>4</grupo>" >		    	
						form.getCombo('idGrupo').clearAll();
						form.getCombo('idGrupo').addOption([[5, 'Supplier']]);
					</logic:match>					
					<logic:match scope="session" name="usuarioYPermisos" value="<grupo>5</grupo>" >		    	
						form.getCombo('idGrupo').clearAll();
					</logic:match>		    		

					
		    		form.getCombo('idAsociado').readonly(1);
    				form.getCombo('idAsociado').setComboValue("0");
    				form.getCombo('idAsociado').setComboText("");
		    		form.disableItem('idAsociado');
		    		
		    		form.getCombo('idGrupo').attachEvent("onChange", function(){
		    			var grupo = form.getCombo('idGrupo').getActualValue();
		    			
		    			if (grupo == "1") { 
		    				form.getCombo('idAsociado').setComboValue("0");
		    				form.getCombo('idAsociado').setComboText("");
		    				form.getCombo('idAsociado').clearAll();
		    				form.disableItem('idAsociado');
		    			} else if (grupo == "2") {
		    				form.enableItem('idAsociado');
		    				form.getCombo('idAsociado').setComboValue("0");
		    				form.getCombo('idAsociado').setComboText("");
		    				form.getCombo('idAsociado').loadXML("../leads/listarcanales.do");
		    			} else if (grupo == "3") { 
		    				form.enableItem('idAsociado');
		    				form.getCombo('idAsociado').setComboValue("0");		    				
		    				form.getCombo('idAsociado').setComboText("");
		    				form.getCombo('idAsociado').loadXML("../leads/listardistribuidores.do");
		    			} else if(grupo == "4") {
		    				form.enableItem('idAsociado');
		    				form.getCombo('idAsociado').setComboValue("0");
		    				form.getCombo('idAsociado').setComboText("");		    				
		    				form.getCombo('idAsociado').loadXML("../leads/listarcomerciales.do");
		    			} else if(grupo == "5") {
		    				form.getCombo('idAsociado').setComboValue("0");
		    				form.getCombo('idAsociado').setComboText("");
		    				form.getCombo('idAsociado').clearAll();
		    				form.disableItem('idAsociado');
		    			}
		    		});
		    		
		    		form.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {	    		
	    					var idAsociado = form.getCombo('idAsociado').getActualValue();
		    				form.send("actualizarusuario.do?!nativeeditor_status=save&idAsociado="+idAsociado,"post", function(xml) {
		    					
		    				});
		    				window.close();
		    				goActualizar();
	    				}
		    		});
		    	});
		    }		    
		    
		    function deleteUser() {
		    	if (confirm("<bean:message key="message.confirm.delete.user"/>")) {
		    		gridLeads.deleteSelectedRows();
		    		goActualizar();
		    	}
		    }
		    
		    function goActualizar() {
		    	buscar();
		    	toolbarUsuarios.disableItem('delete');
		    	tabbar.clearAll();		    	
		    }
		    
		    
		    
		    
		    function goActualizarUserServices() {
		    	toolbarServicios.disableItem('delete');
		    	gridServicios.clearAndLoad("gridserviciosusuario.do?idUsuario="+idSelectedUser);
		    }
		    
		    function deleteUserService() {
		    	if (confirm("<bean:message key="message.confirm.delete.service"/>")) {
		    		gridServicios.deleteSelectedRows();
		    		goActualizarUserServices();
		    	}
		    }
	
		    function newUserService() {
		    	var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("service", 300,50, 360, 160);
				window.setText('<bean:message key="layout.add.service" />');				
				window.setModal(true);
				window.centerOnScreen();
				var form = window.attachForm();			
		    	form.loadStruct('../xml/forms/add_servicio_form.xml', function() {
		    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
		    		form.setItemLabel('idServicio','<bean:message key="label.services"/>');
		    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
		    		form.getCombo('idServicio').readonly(1);
		    		form.getCombo('idServicio').loadXML("../leads/listarservicios.do");
		    		
		    		form.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
		    				form.send("actualizarserviciousuario.do?!nativeeditor_status=save&idUsuario=" + idSelectedUser,"post", function(xml) {
		    					
		    				});
		    				window.close();
		    				goActualizarUserServices();
	    				}
		    		});
		    		
		    	});
		    }
		    
	 	</script>
	 	
	 </head>
	<body>
	</body>
</html>