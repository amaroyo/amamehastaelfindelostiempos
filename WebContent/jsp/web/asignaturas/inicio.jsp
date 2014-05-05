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
	    <script type="text/javascript" src="../skins/dhtmlx.js"></script>
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    <script src="../skins/patterns/dhtmlxlayout_pattern4l.js"></script>
	    

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../skins/imgs/';
	    	
	    	var miGrid, miGrid2, tabbar, tab_1, main_layout, areaTrabajoAsignaturas, menuOpciones,idAsignatura,nombreAsignatura;
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	<% String idAsignatura = request.getParameter("idAsignatura");
		    		String nombreAsignatura = request.getParameter("nombreAsignatura"); %>
		    	idAsignatura="<%=idAsignatura%>"; 
		    	nombreAsignatura="<%=nombreAsignatura%>"; 
		    	
			    dhtmlxError.catchError("ALL",errorHandler);
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    menuOpciones = main_layout.cells('a');
			    areaTrabajoAsignaturas = main_layout.cells('b');
			    
			    menuOpciones.setWidth(150);
			    menuOpciones.setText(["<strong><bean:message key="label.mis.asignaturas.componentes" /></strong>"]);
			    areaTrabajoAsignaturas.setText(nombreAsignatura);
			    //main_layout.setAutoSize("a;b", "b;c");
			    
			    
			    miGrid = menuOpciones.attachGrid();
			    //miGrid.setIconsPath('../skins/imgs/');		    	
			    miGrid.setHeader(["<strong><bean:message key="label.mis.asignaturas" /></strong>"]);
			    miGrid.setNoHeader(true);
			    //set readonly (ro)
			    miGrid.setColTypes("ro");
			    miGrid.enableMultiselect(false);
			    miGrid.init();
			    miGrid.loadXML("../xml/forms/mis_asignaturas_componentes_form.xml");
			    miGrid.attachEvent("onRowSelect",doOnRowSelected);
		    			    			    
		    });
		    
		    
		    function doOnRowSelected(rowID,celInd){
		        switch(rowID){
		        
		        	case "a": goInformacion();
		        	case "b": goEstancia();
		        	//case "c": goSeminarios();
		        	//case "d": goCampo();
		        	//case "e": goCasos();
		        	//case "f": goDiario();
		        	//case "g": goRubrica();
		        }	
		    }
		    
		    
		    function goEstancia(){
		    	
		    	document.src="perfil/inicio.do";
		    }
		    
		    function goInformacion(){
		    	var b = areaTrabajoAsignaturas;
		    	
		    	var form = b.attachForm();
		    	
		    	form.loadStruct('../xml/forms/asignatura_informacion_form.xml', function(){
		    		form.setItemLabel('data','<bean:message key="title.info.general.asignatura"/>');
		    		form.setItemLabel('nombreAsignatura','<bean:message key="label.nombre.asignatura"/>');
		    		form.setItemLabel('codigo','<bean:message key="label.codigo.asignatura"/>');
		    		form.setItemLabel('curso','<bean:message key="label.curso.asignatura"/>');
		    		form.setItemLabel('profesor','<bean:message key="label.profesor.asignatura"/>');
		    		form.setItemLabel('descripcion','<bean:message key="label.descripcion.asignatura"/>');
		    		
		    		
		    		<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>36</permiso>" >		    	
							form.hideItem('aceptar');
						</logic:notMatch>
					</logic:notMatch>
		    		
					/*form.load('editarusuario.do?idUsuario=' + idSelectedUser, function () {
					form.attachEvent("onButtonClick", function(id){
						if (id == "aceptar") {
							form.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idSelectedUser ,"post", function(xml) {

							});

						}
					});
				});*/
				
				
	    	});
		    	
		    }
		    
		    function verPerfil(){
		    	var b = main_layout.cells('b');
	    		
		    	var form = b.attachForm();	
		    	
		    	form.loadStruct('../xml/forms/usuario_form.xml', function(){
		    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
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

					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>36</permiso>" >		    	
							form.hideItem('aceptar');
						</logic:notMatch>
					</logic:notMatch>

					<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
					idSelectedUser = <%=sessionIdUser%>;
		    		
					/*form.load('editarusuario.do?idUsuario=' + idSelectedUser, function () {
						form.attachEvent("onButtonClick", function(id){
							if (id == "aceptar") {
								form.send("actualizarusuario.do?!nativeeditor_status=save&idUsuario=" + idSelectedUser ,"post", function(xml) {

								});

							}
						});
					});*/
					
					
		    	});
		    }
		  
        </script>
	</head>
	<body>
	</body>
</html>