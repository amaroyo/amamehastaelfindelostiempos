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
	    	
	   	    dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	var miGrid, menu, form;
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
			    dhtmlxError.catchError("ALL",errorHandler);
			    menu = new dhtmlXLayoutObject("menu", '1C');
			    
			    menu.cells("a").hideHeader();
			    
			    miGrid = menu.cells("a").attachGrid();
			    miGrid.setIconsPath('../skins/imgs/');		    	
			    miGrid.setHeader(["<strong><bean:message key="label.mi.perfil" /></strong>"]);
			    miGrid.setColTypes("ro");
			    miGrid.enableMultiselect(false);
			    miGrid.init();
			    miGrid.loadXML("../xml/forms/mi_perfil_form.xml");
			    miGrid.attachEvent("onRowSelect",doOnRowSelected);
		    });
		    
		    function doOnRowSelected(rowID,celInd){
		        if (rowID == "b") verFormModificarPass();
		    	else if (rowID == "a") verPerfil();
		    	
		    }
		    
		    function goEntrada() {
				var url = "../entrada.do";
				location.href=url;
	    	}
		    
		    function verFormModificarPass(){
		    	form = new dhtmlXForm("myForm");
		    	form.loadStruct('../xml/forms/contrasena_form.xml', function() {
		    		form.setItemLabel('data','<bean:message key="title.cambiar.pass"/>');
		    		form.setItemLabel('oldPass','<bean:message key="label.ant.pass"/>');
		    		form.setItemLabel('newPass1','<bean:message key="label.nueva.pass"/>');
		    		form.setItemLabel('newPass2','<bean:message key="label.repetir.pass"/>');
		    		form.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
		    		
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
		    	
		    	form = new dhtmlXForm("myForm");
		    	form.loadStruct('../xml/forms/usuario_form.xml', function(){
		    		form.setItemLabel('data','<bean:message key="title.datos.personales"/>');
		    		form.setItemLabel('grupo','<bean:message key="label.group"/>');
		    		form.setItemLabel('nombre','<bean:message key="label.nombre"/>');
		    		form.setItemLabel('apellido1','<bean:message key="label.apellido1"/>');
		    		form.setItemLabel('apellido2','<bean:message key="label.apellido2"/>');
		    		form.setItemLabel('dni','<bean:message key="label.dni"/>');
		    		form.setItemLabel('telefono','<bean:message key="label.telefono"/>');
		    		form.setItemLabel('correo','<bean:message key="label.correo"/>');	
		    		form.setItemLabel('imagen','<bean:message key="label.foto"/>');
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

				
					<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
					idSelectedUser = <%=sessionIdUser%>;

					form.load('editarusuario.do?idUsuario=' + idSelectedUser, function () {
						if(form.getItemValue("imagen") == null) {
							var uriNoProfilePic = '../img/no-profile-pic.png';
							form.getContainer("imagen").innerHTML = "<img src="+ uriNoProfilePic +">";
						}
						form.attachEvent("onButtonClick", function(id){
							if (id == "aceptar") {
								alert();
								document.getElementById("realForm").submit();
								/*form.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idSelectedUser ,"post", function(xml) {
									alert('<bean:message key="message.perfil.cambiado.exito"/>');
								});*/

							}
						});
						form.attachEvent("onEnter", function() {
							form.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idSelectedUser ,"post", function(xml) {
								alert('<bean:message key="message.perfil.cambiado.exito"/>');
							}); 
			    		});
						
						
					});//load
					
		    	});//loadStruct
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
	<div id="layout" style="width:600px">
		<div id="menu" style="float:left; height:100%; width:150px;">
		
		</div>
		<div id="content" style="float:right; height:100%; width:400px;">
			<form id="realForm" method="POST" enctype="multipart/form-data">
				<div id="myForm">
	 
				</div >
			</form >
		</div>
	</div>
	</body>
</html>