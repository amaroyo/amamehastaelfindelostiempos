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
	    <link rel="stylesheet" type="text/css" href="../skins/dhtmlx.css">
	    <link rel="stylesheet" type="text/css" href="../skins/dhtmlxform_dhx_skyblue.css">
	    <script type="text/javascript" src="../skins/dhtmlx.js"></script>
	    <script type="text/javascript" src="../skins/dhtmlxform.js"></script>
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../skins/imgs/';
	    	
	    	var miGrid, tabbar, tab_1, main_layout, form, b, a;
	    	
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
			    //set readonly (ro)
			    miGrid.setColTypes("ro");
			    miGrid.enableMultiselect(false);
			    miGrid.init();
			    miGrid.loadXML("../xml/forms/mi_perfil_form.xml");
			    miGrid.attachEvent("onRowSelect",doOnRowSelected);
			    b = main_layout.cells('b');
		    	form = b.attachForm();
			    
			    			    
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
		    	
		    	form.loadStruct('../xml/forms/contrasena_form.xml', function() {
		    		form.setItemLabel('data','<bean:message key="title.cambiar.pass"/>');
		    		form.setItemLabel('oldPass','<bean:message key="label.ant.pass"/>');
		    		form.setItemLabel('newPass1','<bean:message key="label.nueva.pass"/>');
		    		form.setItemLabel('newPass2','<bean:message key="label.repetir.pass"/>');
		    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');

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
		    	if(form.getItemValue("newPass1") != "" && form.getItemValue("newPass2") != ""){
					if (form.getItemValue("newPass1") == form.getItemValue("newPass2")){
						if(form.getItemValue("newPass1") != form.getItemValue("oldPass")){
    						var newPass = form.getItemValue("newPass1");
    						var oldPass = form.getItemValue("oldPass");
		    				form.send("actualizarcontrasena.do?oldPass=" + oldPass + "&newPass=" + newPass,"post", function(loader, response) {
		    					//resultadoCambiarPassword(loader,response);
		    				});
						}
						else{
							alert('<bean:message key="message.pass.iguales" />');
						}
					} 
					else {
						alert('<bean:message key="message.pass.no.coincide" />');
					}
				}
				else {
					alert('<bean:message key="message.pass.vacio" />');
				}
		    }
		    
		    function resultadoCambiarPassword(loader, response) {
		    	   /*if(response.indexOf("HTTP Status") == -1) {
		    		      editUtForm.unload();
		    		      editUtForm = new dhtmlXForm("utenteForm", response)
		    		      flashMessage("Utente creato con successo");
		    		}*/
		    }
		    
		    function verPerfil(){
		    	
		    	form.loadStruct('../xml/forms/usuario_form.xml', function(){
		    		form.setItemLabel('data','<bean:message key="title.datos.personales"/>');
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
		    		
		    		form.setFocusOnFirstActive();

					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>36</permiso>" >		    	
							form.hideItem('aceptar');
						</logic:notMatch>
					</logic:notMatch>

					
					form.attachEvent("onEnter", function() {
						form.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idSelectedUser ,"post", function(xml) {
							alert('<bean:message key="message.perfil.cambiado.exito"/>');
						}); 
		    		});
					
					<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
					idSelectedUser = <%=sessionIdUser%>;
		    		
					form.load('editarusuario.do?idUsuario=' + idSelectedUser, function () {
						form.attachEvent("onButtonClick", function(id){
							if (id == "aceptar") {
								form.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idSelectedUser ,"post", function(xml) {
									alert('<bean:message key="message.perfil.cambiado.exito"/>');
								});

							}
						});
					});
					
					
		    	});
		    }
		  
        </script>
	</head>
	<body>
	</body>
</html>