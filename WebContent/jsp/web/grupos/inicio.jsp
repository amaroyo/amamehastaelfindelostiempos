<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>
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
	    

			dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	
	    	var gridLeads, gridPermisos, idSelectedGroup, idSelectedPermisoGrupo, toolbarPermisos,idSelectedUser;
  	
		    dhtmlxEvent(window,"load",function() {
		    	
			    dhtmlxError.catchError("ALL",errorHandler);

			    var main_layout = new dhtmlXLayoutObject(document.body, '2U');

			    var a = main_layout.cells('a');
		    	a.hideHeader();
		    	   	
		    	gridLeads = a.attachGrid();
		    	//gridLeads.setIconsPath('../skins/imgs/');		    	
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
				   	toolbarPermisos.setIconsPath('../img/toolbar/');
			    	
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
			    	gridUsuarios.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.address.email" />"]);
			    	gridUsuarios.setColTypes("ro,ro,ro");			    	
			    	gridUsuarios.setColSorting('str,str,str');
			    	gridUsuarios.load("../usuarios/gridusuarios.do?idGrupo="+idGrupo);			    	
			    	gridUsuarios.init();
			    	
			    	gridUsuarios.attachEvent("onRowSelect", function(idUsuario,ind){
			    		idSelectedUser=idUsuario;
						var dhxWins= new dhtmlXWindows();
						var window = dhxWins.createWindow("user", 300,50, 385, 510);
						window.setText('<bean:message key="title.user"/>');				
						window.setModal(true);
						window.centerOnScreen();
					
						var form = window.attachForm();		    	
						form.loadStruct('../xml/forms/usuario_form.xml', function(){
				    		form.setItemLabel('data','<bean:message key="title.datos.personales"/>');
				    		form.setItemLabel('grupo','<bean:message key="label.group"/>');
				    		form.setItemLabel('nombre','<bean:message key="label.nombre"/>');
				    		form.setItemLabel('apellido1','<bean:message key="label.apellido1"/>');
				    		form.setItemLabel('apellido2','<bean:message key="label.apellido2"/>');
				    		form.setItemLabel('dni','<bean:message key="label.dni"/>');
				    		form.setItemLabel('telefono','<bean:message key="label.telefono"/>');
				    		form.setItemLabel('correo','<bean:message key="label.correo"/>');	
				    		form.setItemLabel('foto','<bean:message key="label.foto"/>');
				    		form.setItemLabel('fotoFile','<bean:message key="label.max.size"/>');
				    		form.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
				    		
							
				    		
				    		form.forEachItem(function(id){
				    			switch(id) {
					    			case "grupo":
					    			case "nombre":
					    			case "apellido1":
					    			case "dni":
					    			case "correo":
					    				form.setRequired(id,true);
					    				break;
				    			}
				    		});
				    		
				    		
				    		form.enableLiveValidation(true);
				    		form.setItemFocus("nombre");

							loadFormPerfil();
								
							form.attachEvent("onChange", function (id, value){
								if(id == "fotoFile"){
									if(isImageExtension(value)) {
										 //previewPicture(rowID);
							    	}
									else {
										alert('<bean:message key="message.error.formato.imagen"/>');
										document.forms[0].elements.namedItem("fotoFile").value=null;
									}
								}
							});
							 
							
							form.attachEvent("onButtonClick", function(id){
								if(id == "aceptar"){
									document.forms[0].submit();
									//document.getElementById("realForm").submit();
									//form.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idSelectedUser,"post", function(xml) {
										alert('<bean:message key="message.perfil.cambiado.exito"/>');
										loadFormPerfil();
								}
							});//onButtonClick
							
						});//load			    	
			    		
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
		    		form.getCombo('idPermiso').loadXML("listarpermisos.do?idGrupo=" + idSelectedGroup);
		    		
		    		form.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
		    				form.send("actualizarpermisogrupo.do?!nativeeditor_status=save&idGrupo=" + idSelectedGroup,"post", function(xml) {
		    					
		    				});
		    				window.close();
	    				}
		    		});
		    		
		    	});
		    }
		    
		    function loadFormPerfil() {
		    	form.load('editarusuario.do?idUsuario=' + idSelectedUser, function () {
					if(form.getItemValue("fotoImagen") == "") {
						var uriNoProfilePic = '../img/no-profile-pic.png';
						form.getContainer("foto").innerHTML = "<img src="+ uriNoProfilePic +" />";
					}
					else{
						var profilePic = form.getItemValue("fotoImagen");
						form.getContainer("foto").innerHTML = "<img src=data:image/jpg;base64,"+ profilePic +" style='width:105px;height:140px'/>";
					}
		    	});
		    }
		    
        </script>
	</head>
	<body>
	</body>
</html>