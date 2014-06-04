<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>
<%@ include file="../../common/taglibs.jsp" %>
<%@ page import="java.util.Enumeration"%>
<%@ page import="es.oyssen.mrm.Const"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>

<html>
	<head>
	    <link rel="stylesheet" type="text/css" href="../css/estilos.css">
	    <link rel="stylesheet" type="text/css" href="../css/templates.css">
	    <link rel="stylesheet" type="text/css" href="../css/estilosMenu.css">
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    
	    <link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlx.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlxgrid.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlxgrid_skins.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlxlayout.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/skins/dhtmlxform_dhx_skyblue.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/skins/dhtmlxgrid_dhx_skyblue.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/skins/dhtmlxlayout_dhx_skyblue.css">

	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxcommon.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxlayout.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxform.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxgrid.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxcontainer.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_dyn.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_item_container.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_item_upload.js"></script>
	    

	    <script type="text/javascript">
	    	
	    	var miGrid, main_layout, form, b, a;
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
			    dhtmlxError.catchError("ALL",errorHandler);
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    a = main_layout.cells('a');
			    
			    main_layout.cells("a").setWidth(150);
			    main_layout.cells("a").hideHeader();
			    main_layout.cells("b").hideHeader();
			    
			    
			    miGrid = a.attachGrid();
			    miGrid.setIconsPath('../skins/imgs/');		    	
			    miGrid.setHeader(["<strong><bean:message key="label.mi.perfil" /></strong>"]);
			    miGrid.setColTypes("ro");
			    miGrid.enableMultiselect(false);
			    miGrid.init();
			    miGrid.loadXML("../xml/forms/mi_perfil_form.xml");
			    miGrid.attachEvent("onRowSelect",doOnRowSelected);    
		    });
		    
		    function doOnRowSelected(rowID,celInd){
		    	b = main_layout.cells('b');
		    	form = b.attachForm();
		        if (rowID == "b") verFormModificarPass();
		    	else if (rowID == "a") verPerfil();
		    	
		    }
		    
		    
		    function goEntrada() {
				var url = "../entrada.do";
				location.href=url;
	    	}
		    
		    function verFormModificarPass(){
		    	
		    	form.loadStruct('../xml/forms/contrasena_form.xml', function() {
		    		form.setItemLabel('data','<bean:message key="title.cambiar.pass"/>');
		    		form.setItemLabel('oldPass','<bean:message key="label.ant.pass"/>');
		    		form.setItemLabel('newPass1','<bean:message key="label.nueva.pass"/>');
		    		form.setItemLabel('newPass2','<bean:message key="label.repetir.pass"/>');
		    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
		    		
		    		form.forEachItem(function(id){
		    			switch(id) {
			    			case "oldPass":
			    			case "newPass1":
			    			case "newPass2":
			    				form.setRequired(id,true);
			    				break;
		    			}
		    		});
		    		
		    		
		    		form.enableLiveValidation(true);
		    		form.setFocusOnFirstActive();
		    		
		    		form.attachEvent("onEnter", function() {
		    			processPasswords();
		    		});
		    		
		    		form.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
	    					processPasswords();
	    				}
	    				
		    		});
		    	});
		    }
		    
		    
		    function processPasswords(){
				if (form.getItemValue("newPass1") == form.getItemValue("newPass2")){
					if(form.getItemValue("newPass1") != form.getItemValue("oldPass")){
   						var newPass = form.getItemValue("newPass1");
   						var oldPass = form.getItemValue("oldPass");
	    				form.send("actualizarcontrasena.do?oldPass=" + oldPass + "&newPass=" + newPass,"post", function(loader, response) {
	    					resultadoCambiarPassword(response);
	    				});
					}
					else{
						alert('<bean:message key="message.pass.iguales" />');
						form.setItemValue("newPass1", "");
						form.setItemValue("newPass2", "");
					}
				} 
				else {
					alert('<bean:message key="message.pass.no.coincide" />');
					form.setItemValue("newPass1", "");
					form.setItemValue("newPass2", "");
				}
   			}
		    
		    
		    
		    function resultadoCambiarPassword(response) {
		    	if(response == "password changed"){
		    		successfulUpdatePassword();
		    		form.clear();
		    	}
		    	else
		    		if(response == "password not changed: incorrect password"){
		    			failedUpdatePassword();
		    			form.setItemValue("oldPass", "");
			    	}
		    }
		    
		    
		    
		    function failedUpdatePassword() {
	    		alert('<bean:message key="message.pass.no.correcto" />');
	    	}
	    	
	    	function successfulUpdatePassword() {
	    		alert('<bean:message key="message.pass.cambiado.exito" />');
	    	}
	    	
	    	function ucmEsEmail(email) {
	    		if (getDomain(email) == "ucm.es") {
	    			return true;
	    		}
	    		else {
	    			form.setNote("correo", { text: '<bean:message key="message.email.institucional" />'} );
	    			return false;
	    		}
	    	}
	    	
	    	function getDomain(email) {
			    var parts = email.split('@');
			    return parts[parts.length - 1];
			}
	    	
		    
		    function verPerfil(){
		    	
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
		    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
		    		
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
		    		//foto LONGBLOB, 
		    		form.setItemFocus("nombre");

				
					<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
					idSelectedUser = <%=sessionIdUser%>;

					form.load('editarusuario.do?idUsuario=' + idSelectedUser, function () {
						if(form.getItemValue("fotoUri") == "") {
							var uriNoProfilePic = '../img/no-profile-pic.png';
							form.getContainer("foto").innerHTML = "<img src="+ uriNoProfilePic +">";
						}
						form.attachEvent("onButtonClick", function(id){
							if (id == "aceptar") {
								form.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idSelectedUser ,"post", function(xml) {
									alert('<bean:message key="message.perfil.cambiado.exito"/>');
								});

							}
						});
						form.attachEvent("onEnter", function() {
							form.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idSelectedUser ,"post", function(xml) {
								alert('<bean:message key="message.perfil.cambiado.exito"/>');
							}); 
			    		});
						
						
					});//load
					
		    	});
		    }
		  
		    
		    function permisosFormPerfil(){
		    	<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>36</permiso>" >		    	
						form.hideItem('aceptar');
					</logic:notMatch>
				</logic:notMatch>
		    }
        </script>
	</head>
	<body>
	</body>
</html>