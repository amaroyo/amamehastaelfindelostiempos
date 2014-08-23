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
	    <script src="../js/dhtmlxSuite/patterns/dhtmlxlayout_pattern4l.js"></script>
	    

	    <script type="text/javascript">
	    
    		dhtmlx.image_path='../js/dhtmlxSuite/imgs/';

	    	var main_layout, formInfo, idAsignatura,nombreAsignatura;
	    	
	    	dhtmlxEvent(window,"load",function() {
	    		
	    		
	    		<% String idAsignatura = request.getParameter("idAsignatura");%>
	    		idAsignatura="<%=idAsignatura%>";

	    		main_layout = new dhtmlXLayoutObject(document.body, '1C');
	    		var a = main_layout.cells('a');
	    		a.hideHeader();
				formInfo = a.attachForm();
		    	
				formInfo.loadStruct('../xml/forms/asignatura_informacion_form.xml', function(){
	    			formInfo.setItemLabel('data','<bean:message key="title.info.general.asignatura"/>');
	    			formInfo.setItemLabel('nombre','<bean:message key="label.nombre.asignatura"/>');
	    			formInfo.setItemLabel('codigo','<bean:message key="label.codigo.asignatura"/>');
	    			formInfo.setItemLabel('curso','<bean:message key="label.curso.asignatura"/>');
	    			formInfo.setItemLabel('descripcion','<bean:message key="label.descripcion.asignatura"/>');
	    			formInfo.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
		    					    		
		    		permisosFormInfoAsignaturas();
	    			
	    			formInfo.load('editarasignatura.do?idAsignatura=' + idAsignatura, function () {	
						formInfo.attachEvent("onButtonClick", function(id){
							if (id == "aceptar") {
								formInfo.send("actualizarasignatura.do?!nativeeditor_status=save&idAsignatura=" + idAsignatura ,"post", function(xml) {
									alert('<bean:message key="message.asignatura.cambiada.exito"/>');
								});

							}
						});
						formInfo.attachEvent("onEnter", function() {
							formInfo.send("actualizarasignatura.do?!nativeeditor_status=save&idAsignatura=" + idAsignatura ,"post", function(xml) {
								alert('<bean:message key="message.asignatura.cambiada.exito"/>');
							}); 
			    		});
						
						
					});
	    	});
	    		
	    });
	    	
	    function permisosFormInfoAsignaturas() {
	    	<logic:match scope="session" name="usuarioYPermisos" value="<grupo>4</grupo>" >	    	
				formInfo.setReadonly('nombre', true);
	    		formInfo.setReadonly('codigo', true);
	    		formInfo.setReadonly('curso', true);
	    		formInfo.setReadonly('profesor', true);
	    		formInfo.setReadonly('descripcion', true);
	    		formInfo.hideItem('aceptar');
			</logic:match>
	    }
	    	
	   </script>
	</head>
	<body>
	</body>
</html>