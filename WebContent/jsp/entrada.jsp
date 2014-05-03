<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>
<%@ include file="common/taglibs.jsp" %>

<html>
<head>  
	<meta http-equiv="cache-control" content="no-cache" />
    <title><bean:message key="aplicacion.nombre"/></title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
    <script language="JavaScript" src="js/general.js"></script>   

	    <link rel="stylesheet" type="text/css" href="skins/dhtmlx.css">	    
	    <script type="text/javascript" src="skins/dhtmlx.js"></script>
    
    <script>
    	var dhxWins, toolbar, width, height, nota;
    	dhtmlx.image_path='skins/imgs/';
    	
    	function init() {
			<logic:notEmpty name="usuarioYPermisos">
	    		toolbar = new dhtmlXToolbarObject("toolbarObj");
		        toolbar.setIconsPath("img/toolbar/");
		        toolbar.loadXML('xml/toolbars/dhxtoolbar-mrm.xml', function() {
		        	
		        	
		        	toolbar.setItemText('miPerfil', '<bean:message key="label.mi.perfil" />');
		        	toolbar.setItemText('misAsignaturas', '<bean:message key="label.mis.asignaturas" />');
					toolbar.setItemText('leads', '<bean:message key="title.leads" />');
					toolbar.setItemText('comerciales', '<bean:message key="title.comerciales" />');
					toolbar.setItemText('responsables', '<bean:message key="title.responsables" />');
					toolbar.setItemText('empresas', '<bean:message key="title.empresas" />');
					toolbar.setItemText('distribuidores', '<bean:message key="title.distribuidores" />');
					toolbar.setItemText('servicios', '<bean:message key="title.servicios" />');
					toolbar.setItemText('canales', '<bean:message key="title.channels" />');
					toolbar.setItemText('grupos', '<bean:message key="title.groups" />');
					toolbar.setItemText('usuarios', '<bean:message key="title.users" />');
					toolbar.setItemText('logsUsuarios', '<bean:message key="title.logs.users" />');
					toolbar.setItemText('exit', '<bean:message key="label.salir" />');
					
					
	
	
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>2</permiso>" >		    	
							toolbar.hideItem('leads');
							toolbar.hideItem('sep3');
		    			</logic:notMatch>
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>8</permiso>" >		    	
							toolbar.hideItem('comerciales');
							toolbar.hideItem('sep4');
						</logic:notMatch>    				
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>12</permiso>" >		    	
							toolbar.hideItem('responsables');
							toolbar.hideItem('sep5');
						</logic:notMatch> 
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>16</permiso>" >		    	
							toolbar.hideItem('distribuidores');
							toolbar.hideItem('sep6');
						</logic:notMatch> 
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>20</permiso>" >		    	
							toolbar.hideItem('empresas');
							toolbar.hideItem('sep7');
						</logic:notMatch>
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>24</permiso>" >		    	
							toolbar.hideItem('servicios');
							toolbar.hideItem('sep8');
						</logic:notMatch>
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>28</permiso>" >		    	
							toolbar.hideItem('canales');
							toolbar.hideItem('sep9');
						</logic:notMatch>	
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>34</permiso>" >
							toolbar.hideItem('usuarios');
							toolbar.hideItem('sep11');
						</logic:notMatch>
					</logic:notMatch>
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<grupo>1</grupo>" >
						toolbar.hideItem('grupos');
						toolbar.hideItem('sep10');
						toolbar.hideItem('logsUsuarios');
						toolbar.hideItem('sep12');
					</logic:notMatch>
				</logic:notEmpty>
	        });	 
		        
	        
	        toolbar.attachEvent('onXLE', function() {
		        
	        });	       	        	        
    	}
    	
    	function initWrong() {
    		alert('<bean:message key="message.wrong.user.pass" />');
    	}
    	
    	function initLock() {
    		alert('<bean:message key="message.distributor.locked" />');
    	}
    	
    	function failedForgotPassword() {
    		alert('<bean:message key="message.forgot.password.failed" />');
    	}
    	
    	function successfulForgotPassword() {
    		alert('<bean:message key="message.forgot.password.successful" />');
    	}
    	
    	function failedUpdatePassword() {
    		alert('<bean:message key="message.pass.no.correcto" />');
    	}
    	
    	function successfulUpdatePassword() {
    		alert('<bean:message key="message.pass.cambiado.exito" />');
    	}
    	
    	function goComerciales() {
    		document.getElementById("areatrabajo").src="comerciales/inicio.do";
    	}
    	function goDistribuidores() {
    		document.getElementById("areatrabajo").src="distribuidores/inicio.do";
    	}
    	function goServicios() {
    		document.getElementById("areatrabajo").src="servicios/inicio.do";
    	}
    	function goLeads() {
    		document.getElementById("areatrabajo").src="leads/inicio.do";
    	}
    	function goResponsables() {
    		document.getElementById("areatrabajo").src="responsables/inicio.do";
    	}
    	function goEmpresas() {
    		document.getElementById("areatrabajo").src="empresas/inicio.do";
    	}
    	function goCanales() {
    		document.getElementById("areatrabajo").src="canales/inicio.do";
    	}
    	function goGrupos() {
    		document.getElementById("areatrabajo").src="grupos/inicio.do";
    	}
    	function goUsuarios() {
    		document.getElementById("areatrabajo").src="usuarios/inicio.do";
    	}
    	
    	function goLogsUsuarios() {
    		document.getElementById("areatrabajo").src="logs/inicio.do";
    	}
    	function goMiPerfil() {
    		document.getElementById("areatrabajo").src="perfil/inicio.do";
    	}
    	function goMisAsignaturas() {
    		document.getElementById("areatrabajo").src="asignaturas/inicio.do";
    	}

    	function logout() {
    		if (confirm('<bean:message key="message.aplicacion.salir" />')) {
				var url = "./bienvenida/logoutusuario.do";
				location.href=url;
    			//window.close();
    		}
    	}
    	
    	

    </script>     
</head>


<logic:empty name="usuarioYPermisos">
	<body class="ventana" >
		<iframe name="trabajo" id="areatrabajo" height="100%" width="100%" scrolling="auto" src="bienvenida/inicio.do" frameborder="0"></iframe>
	</body>
</logic:empty>
<logic:notEmpty name="usuarioYPermisos">

  	<logic:match scope="session" name="usuarioYPermisos" value="<existe>YES</existe>" >
		<logic:match scope="session" name="usuarioYPermisos" value="<bloqueado>NO</bloqueado>" >
			<body class="ventana" onload="init();">
			    <table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%">
			        <!-- <tr><td><%@include file="common/cabecera.jsp" %></td></tr> -->
			
			        <tr><td>
			        	<div id="toolbarObj"></div>
			        </td></tr>
			                
			        <tr>
			            <td valign="top" height="100%" align="center"><iframe name="trabajo" id="areatrabajo" height="100%" width="100%" scrolling="auto" frameborder="0"></iframe>
			            </td>
			        </tr>      
			      </table>
		     </body>
		</logic:match> 
		<logic:match scope="session" name="usuarioYPermisos" value="<bloqueado>YES</bloqueado>" >
			<body class="ventana" onload="initLock();">
				<iframe name="trabajo" id="areatrabajo" height="100%" width="100%" scrolling="auto" src="bienvenida/inicio.do" frameborder="0"></iframe>
			</body>
		</logic:match> 
	</logic:match>      
	
	<logic:match scope="session" name="usuarioYPermisos" value="<existe>NO</existe>" >
		<body class="ventana" onload="initWrong();">
			<iframe name="trabajo" id="areatrabajo" height="100%" width="100%" scrolling="auto" src="bienvenida/inicio.do" frameborder="0"></iframe>
		</body>
	</logic:match>
	
	<logic:match scope="session" name="usuarioYPermisos" value="<existe_usuario_forgot_password>NO</existe_usuario_forgot_password>" >
		<body class="ventana" onload="failedForgotPassword();">
			<iframe name="trabajo" id="areatrabajo" height="100%" width="100%" scrolling="auto" src="bienvenida/inicio.do" frameborder="0"></iframe>
		</body>
	</logic:match>
	<logic:match scope="session" name="usuarioYPermisos" value="<existe_usuario_forgot_password>YES</existe_usuario_forgot_password>" >
		<body class="ventana" onload="successfulForgotPassword();">
			<iframe name="trabajo" id="areatrabajo" height="100%" width="100%" scrolling="auto" src="bienvenida/inicio.do" frameborder="0"></iframe>
		</body>
	</logic:match>
	
	<logic:match scope="session" name="usuarioYPermisos" value="<update_password_correct>NO</update_password_correct>" >
		<body class="ventana" onload="failedUpdatePassword();">
			<iframe name="trabajo" id="areatrabajo" height="100%" width="100%" scrolling="auto" src="contrasena/inicio.do" frameborder="0"></iframe>
		</body>
	</logic:match>
	<logic:match scope="session" name="usuarioYPermisos" value="<update_password_correct>YES</update_password_correct>" >
		<body class="ventana" onload="successfulUpdatePassword();">
			<iframe name="trabajo" id="areatrabajo" height="100%" width="100%" scrolling="auto" src="perfil/inicio.do" frameborder="0"></iframe>
		</body>
	</logic:match>  
	
</logic:notEmpty>    
</html>