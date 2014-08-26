<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>
<%@ include file="../../common/taglibs.jsp" %>
<html>
	<head>
	       <link rel="stylesheet" type="text/css" href="../css/estilos.css">
	    <link rel="stylesheet" type="text/css" href="../css/templates.css">
	    <link rel="stylesheet" type="text/css" href="../css/estilosMenu.css">
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    <link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlx.css">
	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
	    <script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxdataprocessor.js"></script>
	    <script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_dyn.js"></script>

	    <script type="text/javascript">
		    
	    	dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	
	    	var gridLeads, toolbarUsuarios, tabbar,tab_1,tab_2, formUsuario, idSelectedUserService, toolbarServicios, gridServicios, idSelectedUser,formNewUser;
	    	
	    	<% String accion = "/grupos/actualizarusuario?!nativeeditor_status=save"; %>
	    	
		    dhtmlxEvent(window,"load",function() {

		    	dhtmlxError.catchError("ALL",errorHandler);

		    	var main_layout = new dhtmlXLayoutObject(document.body, '2U');

		    	var a = main_layout.cells('a');
		    	a.hideHeader();
		    	toolbarUsuarios = a.attachToolbar();
		    	toolbarUsuarios.setIconsPath('../img/toolbar/');
		    	
		    	toolbarUsuarios.loadXML('../xml/toolbars/dhxtoolbar-usuarios.xml', function(){
		    		toolbarUsuarios.setItemText('new',"<bean:message key="button.create.user"/>");
		    		toolbarUsuarios.setItemText('delete',"<bean:message key="button.eliminar"/>");
		    		toolbarUsuarios.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
					
		    		
		    		
		    	});
		    	gridLeads = a.attachGrid();
		    	gridLeads.setIconsPath('../skins/imgs/');
		    	
		    	gridLeads.setHeader(["<bean:message key="label.group" />","<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.telefono" />","<bean:message key="label.address.email" />"]);
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
		    	
		    	gridLeads.attachEvent("onRowSelect", function(row,ind){
		    		toolbarUsuarios.enableItem('delete');
		    		
		    		idUsuario=row;
		    		idSelectedUser=row;
		    		selectedEmail=gridLeads.cells(row,4).getValue();
		    		
		    		
		    		
		    		b.showHeader();
		    		b.setText("<bean:message key="title.comercial.propiedades" />");

		    		
			    	tabbar = b.attachTabbar();
			    	tabbar.addTab('tab_1','<bean:message key="label.propiedades"/>','');
			    	tab_1 = tabbar.cells('tab_1');
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
						formUsuario.setItemLabel('correo','<bean:message key="label.correo"/>');	
						formUsuario.setItemLabel('foto','<bean:message key="label.foto"/>');
						formUsuario.setItemLabel('fotoFile','<bean:message key="label.max.size"/>');
						formUsuario.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
			    		
						
			    		
						formUsuario.forEachItem(function(id){
			    			switch(id) {
				    			case "nombre":
				    			case "apellido1":
				    			case "dni":
				    				formUsuario.setRequired(id,true);
				    				break;
			    			}
			    		});
			    		
			    		
						formUsuario.enableLiveValidation(true);
						formUsuario.setItemFocus("nombre");

						loadFormPerfil();
							
						formUsuario.attachEvent("onChange", function (id, value){
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
						 
						
						formUsuario.attachEvent("onButtonClick", function(id){
							if(id == "aceptar"){
								document.forms[0].submit();
								//document.getElementById("realForm").submit();
								//form.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idSelectedUser,"post", function(xml) {
									alert('<bean:message key="message.perfil.cambiado.exito"/>');
									loadFormPerfil();
							}
						});//onButtonClick
						
					});//load			    	
		    		
		    	
			    	
			    	
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
				var window = dhxWins.createWindow("users", 300, 50, 385, 280);
				window.setText('<bean:message key="title.new.user" />');				
				window.setModal(true);
				window.centerOnScreen();
				formNewUser = window.attachForm();			
				formNewUser.loadStruct('../xml/forms/new_usuario_form.xml', function() {
					formNewUser.setItemLabel('data','<bean:message key="title.datos.personales"/>');
					formNewUser.setItemLabel('grupo','<bean:message key="label.group"/>');
					formNewUser.setItemLabel('nombre','<bean:message key="label.nombre"/>');
					formNewUser.setItemLabel('apellido1','<bean:message key="label.apellido1"/>');
					formNewUser.setItemLabel('apellido2','<bean:message key="label.apellido2"/>');
					formNewUser.setItemLabel('dni','<bean:message key="label.dni"/>');
					formNewUser.setItemLabel('telefono','<bean:message key="label.telefono"/>');
					formNewUser.setItemLabel('correo','<bean:message key="label.correo"/>');	
					formNewUser.setItemLabel('foto','<bean:message key="label.foto"/>');
					formNewUser.setItemLabel('fotoFile','<bean:message key="label.max.size"/>');
					formNewUser.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
		    		
					formNewUser.removeItem('foto');
					formNewUser.removeItem('fotoFile');
					
					formNewUser.getCombo('grupo').readonly(1);
		    		formNewUser.getCombo('grupo').setComboValue("0");
		    		formNewUser.getCombo('grupo').setComboText("");
    				    		
		    		formNewUser.forEachItem(function(id){
		    			switch(id) {
			    			case "nombre":
			    			case "apellido1":
			    			case "dni":
			    			case "correo":
			    				formNewUser.setRequired(id,true);
			    				break;
		    			}
		    		});
				
		    		
					
		    		
		    		formNewUser.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {	    		
	    					
	    					formNewUser.send("actualizarusuario.do?!nativeeditor_status=create","post", function(xml) {
		    					
		    				});
		    				window.close();
		    				goActualizar();
	    				}
		    		});
		    	});
		    }		    
		    
		    function deleteUser() {
		    	
		    	var cellObj = gridLeads.cellById(idSelectedUser,0);				
		    	if(cellObj.getValue()=="Indefinido"){
			    	if (confirm("<bean:message key="message.confirm.delete.user"/>")) {
			    		gridLeads.deleteSelectedRows();
			    		goActualizar();
			    	}
		    	}
		    	else alert("<bean:message key="message.eliminar.usuario.mensaje"/>");
		    }
		    
		    function goActualizar() {
		    	buscar();
		    	toolbarUsuarios.disableItem('delete');
		    	tabbar.clearAll();		    	
		    }
		    
		    function loadFormPerfil() {
		    	formUsuario.load('editarusuario.do?idUsuario=' + idSelectedUser, function () {
					if(formUsuario.getItemValue("fotoImagen") == "") {
						var uriNoProfilePic = '../img/no-profile-pic.png';
						formUsuario.getContainer("foto").innerHTML = "<img src="+ uriNoProfilePic +" />";
					}
					else{
						var profilePic = form.getItemValue("fotoImagen");
						formUsuario.getContainer("foto").innerHTML = "<img src=data:image/jpg;base64,"+ profilePic +" style='width:105px;height:140px'/>";
					}
		    	});
		    }
		    
		    function ucmEsEmail(email) {
	    		if (getDomain(email) == "ucm.es") {
	    			return true;
	    		}
	    		else {
	    			formNewUser.setNote("correo", { text: '<bean:message key="message.email.institucional" />'} );
	    			return false;
	    		}
	    	}

	    	function getDomain(email) {
	    	    var parts = email.split('@');
	    	    return parts[parts.length - 1];
	    	}
	    	
		    
		    
	 	</script>
	 	
	 </head>
	<body>
	<div id="layout" style="width:100%; height:100%;">
		<div id="menu" style="float:left; height:100%; width:150px;">
		
		</div>
		<div id="content" style="float:left; height:100%; width:550px;">
			<html:form action="<%=accion%>"  enctype="multipart/form-data" target="response_area_iframe">
				<div id="myForm">
	 
				</div >
			</html:form>
		</div>
		<iframe name="response_area_iframe" frameBorder="0" height="0"></iframe>
	</div>
	</body>
</html>