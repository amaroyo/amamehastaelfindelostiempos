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
	    	
	    	var gridLeads, gridContactos, toolbarDistribuidores, toolbarContactos, form, tabbar, idSelectedDistributor, idSelectedContact;
  	
		    dhtmlxEvent(window,"load",function() {
		    	
			    dhtmlxError.catchError("ALL",errorHandler);


				var dhxWins= new dhtmlXWindows(document.body);
				var window = dhxWins.createWindow("contact", 300,50, 365, 210);
				window.setText('<bean:message key="title.cambiar.pass" />');				
				window.setModal(true);
				window.centerOnScreen();
				var form = window.attachForm();			
		    	form.loadStruct('../xml/forms/contrasena_form.xml', function() {
		    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
		    		form.setItemLabel('oldPass','<bean:message key="label.ant.pass"/>');
		    		form.setItemLabel('newPass1','<bean:message key="label.nueva.pass"/>');
		    		form.setItemLabel('newPass2','<bean:message key="label.repetir.pass"/>');
		    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');

		    				    					    			
		    		form.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
	    					if (form.getItemValue("newPass1") == form.getItemValue("newPass2")){
	    						var newPass = form.getItemValue("newPass1");
	    						var oldPass = form.getItemValue("oldPass");
			    				form.send("../contrasena/actualizarcontrasena.do?oldPass=" + oldPass + "&newPass=" + newPass,"post", function(xml) {
			    					alert("Su contraseña ha sido cambiada con éxito");
			    				});
			    				window.close();
	    					} else {
	    						alert('<bean:message key="message.pass.no.coincide" />');
	    					}
	    				}
		    		});
		    	});
			    			    			    
		    });

		    
        </script>
	</head>
	<body>
	</body>
</html>