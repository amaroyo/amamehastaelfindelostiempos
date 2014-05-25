<%@ include file="../../common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>

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
  	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	var refresh="false";
		    	<% String timeout = request.getParameter("timeout");%>
				var refresh="<%=timeout%>"; 
				if(refresh == "true") {
					//window.parent.parent.document.getElementById("toolbarTd").innerHTML="";
			    	window.parent.parent.document.location.href="../login.do";
			   	}
		    	
			    dhtmlxError.catchError("ALL",errorHandler);
			    //var form = new dhtmlXForm(document.body);
			    var form = new dhtmlXForm("myForm");	
		    	form.loadStruct('../xml/forms/bienvenida_form.xml', function() {
		    		form.setItemLabel('data','<bean:message key="title.bienvenida"/>');
		    		form.setItemLabel('correo','<bean:message key="label.user.email"/>');
		    		form.setItemLabel('pass','<bean:message key="label.user.password"/>');
		    		form.setItemLabel('aceptar','<bean:message key="button.entrar"/>');
		    		form.setItemLabel('newPass','<bean:message key="button.olvide.pass"/>');
		    		
		    		form.setFocusOnFirstActive();
		    				    		
		    		form.attachEvent("onEnter", function() {
		    			 var correo = form.getItemValue("correo");
	    					var pass = form.getItemValue("pass");
		    				form.send("autenticacionusuario.do?correo=" + correo + "&pass=" + pass, "post", function(loader) {
		    					goEntrada();
		    				});
		    		});
		    		
		    		form.attachEvent("onButtonClick", function(id){
		    			
	    				if (id == "aceptar") {
	    					var user = form.getItemValue("user");
	    					var pass = form.getItemValue("pass");
		    				form.send("autenticacionusuario.do?user=" + user + "&pass=" + pass, "post", function(loader) {
		    					goEntrada();
		    				});
	    				}
	    				else if (id == "newPass") {
	    					var email=prompt('<bean:message key="message.forgot.password"/>');

	    		    		if (email!=null)
	    		    		  {
	    		    			form.send("forgotpassword.do?email=" + email, "post", function(loader) {
	    	    					goEntrada();
	    	    				});
	    		    		  }
	    				}
		    		});
		    	});	
		    	
		    });
		    
		    
		    
	    	function goEntrada() {
				var url = "../entrada.do";
				location.href=url;
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