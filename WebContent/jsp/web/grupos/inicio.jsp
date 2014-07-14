<%@ include file="../../common/taglibs.jsp" %>
<html>
	<head>
	    <link rel="stylesheet" type="text/css" href="../css/estilos.css">
	    <link rel="stylesheet" type="text/css" href="../css/templates.css">
	    <link rel="stylesheet" type="text/css" href="../css/estilosMenu.css">
	    <link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlx.css">
	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../skins/imgs/';
	    	
	    	var gridLeads, gridPermisos, idSelectedGroup, idSelectedPermisoGrupo, toolbarPermisos;
  	
		    dhtmlxEvent(window,"load",function() {
		    	
			    dhtmlxError.catchError("ALL",errorHandler);

			    var main_layout = new dhtmlXLayoutObject(document.body, '2U');

			    var a = main_layout.cells('a');
		    	a.hideHeader();
		    	   	
		    	gridLeads = a.attachGrid();
		    	gridLeads.setIconsPath('../skins/imgs/');		    	
		    	gridLeads.setHeader(["<bean:message key="label.nombre" />"]);
		    	gridLeads.setColTypes("ro");		    	
		    	gridLeads.setColSorting('str');
		    	gridLeads.enableMultiselect(true);
		    	gridLeads.init();

				gridLeads.attachEvent("onRowSelect", function(idGrupo,ind){
					idSelectedGroup = idGrupo;
		    		
		    		b.showHeader();
		    		b.setText("<bean:message key="title.responsable.propiedades"/>");
			    					   					

				   	//añadir tab con permisos
				   	tabbar = b.attachTabbar();
				   	tabbar.addTab('tab_1','<bean:message key="layout.allowed.actions"/>','');
				   	tab_1 = tabbar.cells('tab_1');
				   	tabbar.setTabActive('tab_1');
			    	
				   	toolbarPermisos = tab_1.attachToolbar();
				   	toolbarPermisos.setIconsPath('../skins/imgs/toolbar/');
			    	
				   	toolbarPermisos.loadXML('../xml/toolbars/dhxtoolbar-permisos.xml', function(){
				   		toolbarPermisos.setItemText('new',"<bean:message key="button.add.permission"/>");
				   		toolbarPermisos.setItemText('delete',"<bean:message key="button.eliminar"/>");
				   		toolbarPermisos.setItemText('refresh',"<bean:message key="button.actualizar"/>");
			    	});
			    	
			    	gridPermisos = tab_1.attachGrid();
			    	gridPermisos.setIconsPath('../skins/imgs/');			    	
			    	gridPermisos.setHeader(["<bean:message key="label.nombre" />"]);
			    	gridPermisos.setColTypes("ro");			    	
			    	gridPermisos.setColSorting('str');
			    	gridPermisos.load("gridpermisosgrupo.do?idGrupo="+idGrupo);			    	
			    	gridPermisos.init();
			    	

			    	gridPermisosProcessor = new dataProcessor("gridpermisosgrupo.do");
			    	gridPermisosProcessor.enableUTFencoding('simple');
			    	gridPermisosProcessor.init(gridPermisos);	  
			    	gridPermisosProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
			    			dhtmlx.message(tag.firstChild.data,action,4000);
			    		}
			    	});		    	
			    	
			    	gridPermisos.attachEvent("onRowSelect", function(idPermisoGrupo,ind){
			    		idSelectedPermisoGrupo = idPermisoGrupo;
			    		toolbarPermisos.enableItem('delete');
			    	});
			    	
			    	
			    	tabbar.addTab('tab_2','<bean:message key="layout.users"/>','');
			    	tab_2 = tabbar.cells('tab_2');
			    	gridUsuarios = tab_2.attachGrid();
			    	gridUsuarios.setIconsPath('../skins/imgs/');			    	
			    	gridUsuarios.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.telefono" />","<bean:message key="label.telefono.movil" />","<bean:message key="label.address.email" />"]);
			    	gridUsuarios.setColTypes("ro,ro,ro,ro");			    	
			    	gridUsuarios.setColSorting('str,str,str,str');
			    	gridUsuarios.load("../usuarios/gridusuarios.do?idGrupo="+idGrupo);			    	
			    	gridUsuarios.init();
			    	
			    	gridUsuarios.attachEvent("onRowSelect", function(idUsuario,ind){
			    		
						var dhxWins= new dhtmlXWindows();
						var window = dhxWins.createWindow("user", 300,50, 385, 510);
						window.setText('<bean:message key="title.user"/>');				
						window.setModal(true);
						window.centerOnScreen();
					
						var form = window.attachForm();		    	
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
				    		
				    		form.load('../usuarios/editarusuario.do?idUsuario=' + idUsuario, function () {			    			
				    			form.attachEvent("onButtonClick", function(id){
				    				if (id == "aceptar") {
					    				form.send("../usuarios/actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idUsuario ,"post", function(xml) {
					    					
					    				});
					    				buscar();
				    				}
				    			});
				    		});
				    	});				    	
			    		
			    	});
			    	
			    	
		    	});
		    	
			    buscar();
			    
		    	var b = main_layout.cells('b');
		    	b.hideHeader();
			    
			    
		    });

		    
		    function buscar() {
		    	gridLeads.clearAndLoad("gridgrupos.do");		    	
		    }

		    function goActualizarPermisosGrupo() {
		    	toolbarPermisos.disableItem('delete');
		    	gridPermisos.clearAndLoad("gridpermisosgrupo.do?idGrupo="+idSelectedGroup);
		    }
		    
		    function deletePermisoGrupo() {
		    	if (confirm("<bean:message key="message.confirm.delete.permission.group"/>")) {
		    		gridPermisos.deleteSelectedRows();
		    		goActualizarPermisosGrupo();
		    	}
		    }
		    
		    function newPermisoGrupo() {
		    	var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("contact", 300,50, 360, 160);
				window.setText('<bean:message key="layout.add.permission" />');				
				window.setModal(true);
				window.centerOnScreen();
				var form = window.attachForm();			
		    	form.loadStruct('../xml/forms/add_permiso_form.xml', function() {
		    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
		    		form.setItemLabel('idPermiso','<bean:message key="label.permission"/>');
		    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
		    		form.getCombo('idPermiso').readonly(1);
		    		form.getCombo('idPermiso').loadXML("listarpermisos.do");
		    		
		    		form.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
		    				form.send("actualizarpermisogrupo.do?!nativeeditor_status=save&idGrupo=" + idSelectedGroup,"post", function(xml) {
		    					
		    				});
		    				window.close();
		    				goActualizarDitributorContacts();
	    				}
		    		});
		    		
		    	});
		    }
		    
        </script>
	</head>
	<body>
	</body>
</html>