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
					
					
					var optsAsignaturas = dameAsignaturasUsuario();
					toolbar.addButtonSelect('misAsignaturas',2, '<bean:message key="button.select.mis.asignaturas" />',
							optsAsignaturas, 'asignaturas.png', null, 'disabled', true, "100", 'select');
				
					
					var optsCursos = dameCursos();
					toolbar.addButtonSelect('cursos', 4, '<bean:message key="button.select.cursos" />',
							optsCursos, 'cursos.png', null, 'disabled', true, "5", 'select');
					
					var optsAdministrar = dameOpcionesAdministrar();
					toolbar.addButtonSelect('administrar',6, '<bean:message key="button.select.administrar" />',
							optsAdministrar, 'administrar.png', null, 'disabled', true, "10", 'select');
	
					permisosToolbarGeneral();
	        });	 
		        
		        toolbar.attachEvent("onClick", function(id){
					toolbar.forEachListOption('misAsignaturas',function(optionAsigId){
						if(id==optionAsigId){
							var nombre = toolbar.getListOptionText('misAsignaturas', optionAsigId);
							goMisAsignaturas(optionAsigId,nombre);
						}
					});
					toolbar.forEachListOption('cursos',function(optionCursosId){
						if(id==optionCursosId){
							goCursos(optionCursosId);
						}
					});
				});
		        
	        toolbar.attachEvent('onXLE', function() {
		        
	        });
	        </logic:notEmpty>
    	}
    	
    	
    	function permisosToolbarGeneral(){
    		<logic:notEmpty name="usuarioYPermisos">
	    		<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>44</permiso>" >	    	
						toolbar.hideItem('cursos');
						toolbar.hideItem('sep3');
						toolbar.hideItem('administrar');
						toolbar.hideItem('sep4');
					</logic:notMatch>
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>2</permiso>" >		    	
						toolbar.hideItem('leads');
						toolbar.hideItem('sep5');
					</logic:notMatch>
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>8</permiso>" >		    	
						toolbar.hideItem('comerciales');
						toolbar.hideItem('sep6');
					</logic:notMatch>    				
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>12</permiso>" >		    	
						toolbar.hideItem('responsables');
						toolbar.hideItem('sep7');
					</logic:notMatch> 
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>16</permiso>" >		    	
						toolbar.hideItem('distribuidores');
						toolbar.hideItem('sep8');
					</logic:notMatch> 
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>20</permiso>" >		    	
						toolbar.hideItem('empresas');
						toolbar.hideItem('sep9');
					</logic:notMatch>
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>24</permiso>" >		    	
						toolbar.hideItem('servicios');
						toolbar.hideItem('sep10');
					</logic:notMatch>
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>28</permiso>" >		    	
						toolbar.hideItem('canales');
						toolbar.hideItem('sep11');
					</logic:notMatch>	
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>34</permiso>" >
						toolbar.hideItem('usuarios');
						toolbar.hideItem('sep13');
					</logic:notMatch>
				</logic:notMatch>
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<grupo>1</grupo>" >
					toolbar.hideItem('grupos');
					toolbar.hideItem('sep12');
					toolbar.hideItem('logsUsuarios');
					toolbar.hideItem('sep14');
				</logic:notMatch>
			</logic:notEmpty>
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
    	function goMisAsignaturas(idAsignatura,nombreAsignatura) {
    		document.getElementById("areatrabajo").src="asignaturas/inicio.do?idAsignatura=" + idAsignatura + "&nombreAsignatura=" + nombreAsignatura;
    	}
    	function goCursos(opcion) {
    		document.getElementById("areatrabajo").src="cursos/inicio.do?opcion=" + opcion;

    	}
    	
    	function dameAsignaturasUsuario(){
    		<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
    		idSelectedUser = <%=sessionIdUser%>;
    		//esto se obtendra de la bbdd
    		 var opts = [['idAsignatura1', 'obj', '801148 - Prácticas Clínicas de Enfermería: Cuidados Básicos','libro.png'],
                         ['idAsignatura2', 'obj', '801149 - Prácticas Clínicas de Enfermería: Metodología Enfermera','libro.png'],
                         ['sep1', 'sep', ''],
                         ['idAsignatura3', 'obj', '801150 - Prácticas Clínicas de Enfermería I','libro.png'],
                         ['idAsignatura4', 'obj', '801151 - Prácticas Clínicas de Enfermería II: Atención Especializada','libro.png'],
                         ['idAsignatura5', 'obj', '801151 - Prácticas Clínicas de Enfermería II: Atención Primaria','libro.png'],
                         ['sep2', 'sep', ''],
                         ['idAsignatura6', 'obj', '801152 - Prácticas Clínicas de Enfermería III','libro.png'],
                         ['idAsignatura7', 'obj', '801153 - Prácticas Clínicas de Enfermería IV','libro.png'],
                        ];
			
			return opts;
    	}
    	
    	function dameCursos(){
    		var opts = [['asignaturas', 'obj', ["<bean:message key="button.select.asignaturas" />"],'libro.png'],
    		            ['sep1', 'sep', ''],
                        ['seminarios', 'obj', ["<bean:message key="button.select.seminarios" />"],'seminarios.png']
                        ];
			
			return opts;
    		
    	}
    	
    	function dameOpcionesAdministrar(){
    		var opts = [['subirListados', 'obj', ["<bean:message key="button.select.subir.listados" />"],'subirListados.png'],
    		            ['sep1', 'sep', ''],
                        ['generarCertificados', 'obj', ["<bean:message key="button.select.generar.certificados" />"],'generarCertificados.png'],
			    		['sep2', 'sep', ''],
			            ['copiaSeguridad', 'obj', ["<bean:message key="button.select.copias.seguridad" />"],'copiaSeguridad.png'],
			            ['sep3', 'sep', ''],
			            ['cerrarCurso', 'obj', ["<bean:message key="button.select.cerrar.curso" />"],'cerrarCurso.png']
    					];
			
			return opts;
    		
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