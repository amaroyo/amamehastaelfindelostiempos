<%@ include file="../../common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>

<html>
	<head>
	    <link rel="stylesheet" type="text/css" href="../css/estilos.css">
	    
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    
	    <link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/skins/dhtmlxform_dhx_skyblue.css">
	    <link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlxwindows.css">
	    <link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/skins/dhtmlxwindows_dhx_skyblue.css">
	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxcommon.js"></script>
	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxform.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_dyn.js"></script>
	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxwindows.js"></script>
	 
	    
	    

	    <script type="text/javascript">
	    
	    	var formLogin, formForgot, dhxWins;
		    dhtmlxEvent(window,"load",function() {
		    	
		    	var refresh="false";
		    	<% String timeout = request.getParameter("timeout");%>
				var refresh="<%=timeout%>"; 
				if(refresh == "true") {
					//window.parent.parent.document.getElementById("toolbarTd").innerHTML="";
			    	window.parent.parent.document.location.href="../login.do";
			   	}
		    	
			    dhtmlxError.catchError("ALL",errorHandler);
			    formLogin = new dhtmlXForm("myForm");	
		    	formLogin.loadStruct('../xml/forms/bienvenida_form.xml', function() {
		    		formLogin.setItemLabel('data','<bean:message key="title.bienvenida"/>');
		    		formLogin.setItemLabel('correo','<bean:message key="label.user.email"/>');
		    		formLogin.setItemLabel('pass','<bean:message key="label.user.password"/>');
		    		formLogin.setItemLabel('aceptar','<bean:message key="button.entrar"/>');
		    		formLogin.setItemLabel('newPass','<bean:message key="button.olvide.pass"/>');
		    		
		    		formLogin.setRequired("correo",true);
		    		formLogin.setRequired("pass",true);

		    		
		    		formLogin.enableLiveValidation(true);
		    		formLogin.setFocusOnFirstActive();
		    				    		
		    		formLogin.attachEvent("onEnter", function() {
		    				formLogin.send("autenticacionusuario.do", "post", function(loader) {
		    					goEntrada();
		    				});
		    		});
		    		
		    		formLogin.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
		    				formLogin.send("autenticacionusuario.do", "post", function(loader) {
		    					goEntrada();
		    				});
	    				}
	    				else if (id == "newPass") {
	    					forgotPass();
	    				}
		    		});
		    	});	
		    	
		    });
		    
		    function forgotPass(){
		    	dhxWins= new dhtmlXWindows();
		    	windowForgot = dhxWins.createWindow("forgot", 300, 50, 250, 150);
		    	windowForgot.setText('<bean:message key="title.olvide.mi.pass" />');
		    	windowForgot.centerOnScreen();
		    	windowForgot.setModal(true);
				formForgot = windowForgot.attachForm();
				formForgot.loadStruct('../xml/forms/forgot_password_form.xml', function() {
					formForgot.setItemLabel('data','<bean:message key="message.forgot.password"/>');
					formForgot.setItemLabel('email','<bean:message key="label.user.email"/>');
					formForgot.setItemLabel('newPass','<bean:message key="button.olvide.pass"/>');
		    		
					formForgot.setRequired("email",true);	
		    		
					formForgot.enableLiveValidation(true);
					formForgot.setFocusOnFirstActive();
		    				    		
					formForgot.attachEvent("onEnter", function() {
						formForgot.send("forgotpassword.do", "post", function(loader) {
							windowForgot.close();	
							goEntrada();
		    			});
		    		});
		    		
					formForgot.attachEvent("onButtonClick", function(id){
	    				if (id == "newPass") {
	    					formForgot.send("forgotpassword.do", "post", function(loader) {
		    					windowForgot.close();
	    						goEntrada();
		    				});
	    				}
					});
				});
		    }
		    
	    	function goEntrada() {
				var url = "../entrada.do";
				location.href=url;
	    	}
	    	
	    	function ucmEsEmail(email) {
	    		if (getDomain(email) == "ucm.es") {
	    			return true;
	    		}
	    		else {
	    			formLogin.setNote("correo", { text: '<bean:message key="message.email.institucional" />'} );
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
	      <table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%">
	        <tr>	                       
	           <td width="42%" height="100%"></td>
	           <td width="58%" height="100%"> 
	           		<div id="myForm" style="float: both;"></div>
	           </td>       
	        </tr>              
	      </table>
	</body>
</html>