<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>
<%@ include file="common/taglibs.jsp" %>

<html>
<head>  
	<meta http-equiv="cache-control" content="no-cache" />
    <title><bean:message key="aplicacion.nombre"/></title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
    <script language="JavaScript" src="js/general.js"></script>   

	    <link rel="stylesheet" type="text/css" href="js/dhtmlxSuite/dhtmlx.css">	 
	    <link rel="stylesheet" type="text/css" href="js/dhtmlxSuite/skins/dhtmlxtoolbar_dhx_skyblue.css">	    
	    <script type="text/javascript" src="js/dhtmlxSuite/dhtmlxtoolbar.js"></script>
	    <script type="text/javascript" src="js/dhtmlxSuite/dhtmlx.js"></script>
	    <script type="text/javascript" src="js/dhtmlxSuite/dhtmlxcommon.js"></script>
    
    <script>
    	var dhxWins, toolbar, width, height, nota, optsAsignaturas, anyoActual;
    	
    	<% String anyoActual = (String) session.getAttribute("anyoActual"); %>
    	anyoActual = "<%=anyoActual%>";
    	
    	dhtmlx.image_path='js/dhtmlxSuite/imgs/';
    	
    	function init() {
    		
			<logic:notEmpty name="usuarioYPermisos">
	    		toolbar = new dhtmlXToolbarObject("toolbarObj");
		        toolbar.setIconsPath("img/toolbar/");
		        toolbar.loadXML('xml/toolbars/dhxtoolbar-mrm.xml', function() {
		        	
		        	toolbar.setItemText('miPerfil', '<bean:message key="label.mi.perfil" />');
		        	toolbar.setItemText('misAlumnos', '<bean:message key="label.mis.alumnos" />');
					toolbar.setItemText('grupos', '<bean:message key="title.groups" />');
					toolbar.setItemText('usuarios', '<bean:message key="title.users" />');
					
					<% String anyo = (String) session.getAttribute("anyoAcademico");%>
					var ann = ("<%=anyo%>");
					
					toolbar.setItemText('timeMachine', '<bean:message key="title.cambiar.anyo.academico" />' + " " + "<strong>" + ann +"</strong>");
					toolbar.setItemText('exit', '<bean:message key="label.salir" />');
					
					
					optsAsignaturas = dameAsignaturasUsuario();
					toolbar.addButtonSelect('misAsignaturas',2, '<bean:message key="button.select.mis.asignaturas" />',
							optsAsignaturas, 'asignaturas.png', null, 'disabled', true, "100");
				
					
					var optsCursos = dameCursos();
					toolbar.addButtonSelect('cursos', 6, '<bean:message key="button.select.cursos" />',
							optsCursos, 'cursos.png', null, 'disabled', true, "5");
					
					var optsAdministrar = dameOpcionesAdministrar();
					toolbar.addButtonSelect('administrar',8, '<bean:message key="button.select.administrar" />',
							optsAdministrar, 'administrar.png', null, 'disabled', true, "10");
					
					
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
					toolbar.forEachListOption('administrar',function(optionAdministrarId){
						if(id==optionAdministrarId){
							goAdministrar(optionAdministrarId);
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
	    			<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>2</permiso>" >
	    				toolbar.hideItem("cursos");
		    			toolbar.hideItem("sep4");
		    			toolbar.hideItem("administrar");
		    			toolbar.hideItem("sep5");
		    			toolbar.hideItem("grupos");
		    			toolbar.hideItem("sep6");
		    			toolbar.hideItem("usuarios");
		    			toolbar.hideItem("sep7");
		    			toolbar.hideItem("timeMachine");
		    			toolbar.hideItem("sep8");
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>3</permiso>" >	    	
							toolbar.hideItem("misAlumnos");
			    			toolbar.hideItem("sep3");
						</logic:notMatch>//permiso3
					</logic:notMatch>//permiso2
				</logic:notMatch>//permiso1
			</logic:notEmpty>
				
			if (anyoActual=="falso"){
				toolbar.hideItem("administrar");
    			toolbar.hideItem("sep5");
    			toolbar.hideItem("grupos");
    			toolbar.hideItem("sep6");
    			toolbar.hideItem("usuarios");
    			toolbar.hideItem("sep7");
			}	
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
    	function goMisAlumnos() {
    		document.getElementById("areatrabajo").src="alumnos/inicio.do";
    	}
    	function goCursos(opcion) {
    		document.getElementById("areatrabajo").src="cursos/inicio.do?opcion=" + opcion;
    	}
    	
    	
    	function goAdministrar(opcion) {
    		switch(opcion){
    		case "subirListados":
    			document.getElementById("areatrabajo").src="administrar/listados.do";
    			break;
    		case "generarCertificados":
    			document.getElementById("areatrabajo").src="administrar/certificados.do";
    			break;
    		case "copiaSeguridad":
    			if (confirm("<bean:message key="message.copia.seguridad"/>")) {
    				
					var d= new dhtmlXWindows();
    				var w = d.createWindow("s", 300,50, 1, 1);	
    				w.hide();
					w.attachURL("./administrar/copiaSeguridad.do");
					setTimeout(function(){w.close();},1000);
    			}
    			break;
    		case "cerrarCurso":
    			alert("cerrar");
    			//document.getElementById("areatrabajo").src="administrar/inicio.do?opcion=" + opcion;
    			break;
    		}

    	}
    	
    	
    	function dameAsignaturasUsuario(){
    		var url = "asignaturasusuario.do";
    		var xmlhttp = initRequest();
    		xmlhttp.onreadystatechange=function(){
    			if (xmlhttp.readyState===4) {
        	        if(xmlhttp.status===200) { //GET returning a response
        	        	return createArrayFromXML(xmlhttp.responseXML);
        	        }
        	    }
    		}
    	    xmlhttp.open("GET",url,false);
    	    xmlhttp.send(null);
    	    return xmlhttp.onreadystatechange();
    	}
    	
    	function createArrayFromXML(xml){
    		var icon = 'libro.png';
    		var asignaturas = xml.getElementsByTagName("asignatura");
    		var id, nombre, asignatura;
    		var opts = new Array();
    		for(var i=0;i<asignaturas.length;i++) {
    	        id=asignaturas[i].getElementsByTagName("id")[0].firstChild.nodeValue;
    	        nombre=asignaturas[i].getElementsByTagName("nombre")[0].firstChild.nodeValue;
				asignatura=[id,'obj',nombre,icon];
    	       	opts[i] = asignatura;
    	    }
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
    	
    	function goBackInTime(){
    		document.getElementById("areatrabajo").src="curso_academico/inicio.do";
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
	
</logic:notEmpty>    
</html>