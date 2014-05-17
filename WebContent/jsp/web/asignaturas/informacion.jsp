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
	    <link rel="stylesheet" type="text/css" href="../skins/dhtmlx.css">
	    <script type="text/javascript" src="../skins/dhtmlx.js"></script>
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    <script src="../skins/patterns/dhtmlxlayout_pattern4l.js"></script>
	    

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../skins/imgs/';
	    	var main_layout, form, idAsignatura,nombreAsignatura;
	    	
	    	dhtmlxEvent(window,"load",function() {
	    		
	    		
	    		<% String idAsignatura = request.getParameter("idAsignatura");%>
	    		idAsignatura="<%=idAsignatura%>";

	    		main_layout = new dhtmlXLayoutObject(document.body, '1C');
	    		var a = main_layout.cells('a');
	    		a.hideHeader();
				form = a.attachForm();
		    	
		    	form.loadStruct('../xml/forms/asignatura_informacion_form.xml', function(){
		    		form.setItemLabel('data','<bean:message key="title.info.general.asignatura"/>');
		    		form.setItemLabel('nombre','<bean:message key="label.nombre.asignatura"/>');
		    		form.setItemLabel('codigo','<bean:message key="label.codigo.asignatura"/>');
		    		form.setItemLabel('curso','<bean:message key="label.curso.asignatura"/>');
		    		form.setItemLabel('profesor','<bean:message key="label.profesor.asignatura"/>');
		    		form.setItemLabel('descripcion','<bean:message key="label.descripcion.asignatura"/>');
		    		
					//Ponemos por defecto que los items no se puedan modificar, y luego con los permisos necesarios 
					//seran modificables.
		    		form.setReadonly('nombre', true);
		    		form.setReadonly('codigo', true);
		    		form.setReadonly('curso', true);
		    		form.setReadonly('profesor', true);
		    		form.setReadonly('descripcion', true);
		    		form.hideItem('aceptar');
		    		
		    		//Esto por ahora es provisional, cuando se haga una peticion de toda la informacion 
		    		//de las asignaturas, se cogeran el codigo y el nombre de la asignatura
		    		form.setItemValue('nombre', idAsignatura);
		    		form.setItemValue('codigo', idAsignatura);
		    		form.setItemValue('curso', "A113");
		    		form.setItemValue('profesor', "Lorem ipsum");
		    		form.setItemValue('descripcion', "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
	

		    		
		    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >	    	
							form.setReadonly('nombre', false);
				    		form.setReadonly('codigo', false);
				    		form.setReadonly('curso', false);
				    		form.setReadonly('profesor', false);
				    		form.setReadonly('descripcion', false);
				    		form.showItem('aceptar');
					</logic:match>
		    		
					
					<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
					var idSelectedUser = <%=sessionIdUser%>;
					
					/*form.load('editarusuario.do?idUsuario=' + idSelectedUser, function () {
					form.attachEvent("onButtonClick", function(id){
						if (id == "aceptar") {
							form.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idSelectedUser ,"post", function(xml) {

							});

						}
					});
				});*/
				
				
	    	});
	    		
	    });
	    	
	    
	    	
	   </script>
	</head>
	<body>
	</body>
</html>