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

	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxcommon.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_dyn.js"></script>
	    

	    <script type="text/javascript">
	    	
	   	    dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	var miGrid, menu, form, idSelectedUser;
	    	

	    	<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
	    	<% String accion = "/perfil/actualizarusuario?!nativeeditor_status=save&idUsuario=" + sessionIdUser;%>
			idSelectedUser = <%=sessionIdUser%>;
	    	
	    	
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
		    	document.getElementById("myForm").innerHTML = "";
		    	form = new dhtmlXForm("myForm");
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
					
				permisosFormPerfilPropio();
				});//load
		    }
		    
		    function permisosFormPerfilPropio(){
		    	<logic:notEmpty name="usuarioYPermisos">
		    		<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
		    			<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>2</permiso>" >
							<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>3</permiso>" >	    	
							form.setReadonly("nombre", true);
							form.setReadonly("apellido1", true);
							form.setReadonly("apellido2", true);
							form.setReadonly("dni", true);
							</logic:notMatch>//permiso3
						</logic:notMatch>//permiso2
					</logic:notMatch>//permiso1
				</logic:notEmpty>
		    }
    
		    function getExtension(fileName) {
			    var parts = fileName.split('.');
			    return parts[parts.length - 1];
			}
		    
		    function isImageExtension(fileName) {
		        var ext = getExtension(fileName);
		        switch (ext.toLowerCase()) {
			        case 'jpg':
			        case 'jpeg':
			        case 'bmp':
			        case 'gif':
			        case 'png':
		            //etc
		            return true;
		        }
		        return false;
		    }
		    
		    function previewPicture(input){
		    	
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
	<div id="layout" style="width:100%; height:100%;">
		<div id="menu" style="float:left; height:100%; width:150px;">
		
		</div>
		<div id="content" style="float:left; height:100%; width:550px;">
			<html:form action="<%=accion%>" enctype="multipart/form-data" target="response_area_iframe">
				<div id="myForm">
	 
				</div >
			</html:form>
		</div>
		<iframe name="response_area_iframe" frameBorder="0" height="0"></iframe>
	</div>
	</body>
</html>