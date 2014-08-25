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
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlx.css">
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
	    <script src="../js/dhtmlxSuite/patterns/dhtmlxlayout_pattern4l.js"></script>
	    <script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_dyn.js"></script>
	    

	    <script type="text/javascript">
	    
    		dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	var main_layout, idAsignatura, nombreAsignatura, gridProfesor, gridAlumnoRealizado, gridAlumnoPendiente,idSessionUser;
	    	
	    	dhtmlxEvent(window,"load",function() {
	    		
	    		
	    		<% String idAsignatura = request.getParameter("idAsignatura");%>
	    		idAsignatura="<%=idAsignatura%>";	
	    		<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
				 idSessionUser = <%=sessionIdUser%>;
	    		
	    		
	    		<logic:notMatch scope="session" name="usuarioYPermisos" value="<grupo>4</grupo>" >
					goProfesor();
				</logic:notMatch>
			
				<logic:match scope="session" name="usuarioYPermisos" value="<grupo>4</grupo>" >
					goAlumno();
				</logic:match>	
	    	});    		
				
				function goAlumno(){
					
					main_layout = new dhtmlXLayoutObject(document.body, '2U');
		    		var a = main_layout.cells('a');
		    		var b = main_layout.cells('b');
		    		a.hideHeader();
		    		b.setText('<bean:message key="label.descripcion.seminario"/>');
		    		b.setWidth(500);
		    		
		    		var tabbar = a.attachTabbar();
			    	tabbar.addTab('tab_realizados','<bean:message key="title.seminarios.realizados"/>','');
			    	var tab_realizados = tabbar.cells('tab_realizados');
			    	tabbar.setTabActive('tab_realizados');
			    	gridAlumnoRealizado = tab_realizados.attachGrid();
			    	
			    	gridAlumnoRealizado.setHeader(["<bean:message key="label.nombre.seminario" />","<bean:message key="label.codigo.seminario" />","<bean:message key="label.anyo.realizacion.seminario" />"]);
			    	gridAlumnoRealizado.setColTypes("ro,ro,ro");
			    	
			    	gridAlumnoRealizado.setColSorting('str,str,str');
			    	gridAlumnoRealizado.enableMultiselect(false);
			    	gridAlumnoRealizado.init();
			    	
			    	var gridAlumnoRealizadoPro = new dataProcessor("gridSeminariosRealizadosUsuario.do?idAlumno=" + idSessionUser + "&idAsignatura=" + idAsignatura + "&peticion=realizados");
			    	gridAlumnoRealizadoPro.enableUTFencoding('simple');
			    	gridAlumnoRealizadoPro.init(gridAlumnoRealizado);	  
			    	gridAlumnoRealizadoPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
			    			dhtmlx.message(tag.firstChild.data,action,4000);
			    		}
			    	});
		    		
			    	gridAlumnoRealizado.clearAndLoad("gridSeminariosRealizadosUsuario.do?idAlumno=" + idSessionUser + "&idAsignatura=" + idAsignatura + "&peticion=realizados");
			    	
			    	gridAlumnoRealizado.attachEvent("onRowSelect", function(row,ind){
			    		
			    		var formSeminarioAlumno = b.attachForm();
			    		
			    		formSeminarioAlumno.loadStruct('../xml/forms/seminario_informacion_form.xml', function(){
			    			formSeminarioAlumno.setItemLabel('data','<bean:message key="title.info.general.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('nombre','<bean:message key="label.nombre.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('codigo','<bean:message key="label.codigo.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('descripcion','<bean:message key="label.descripcion.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
			    			formSeminarioAlumno.setItemLabel('asignatura','<bean:message key="label.asignatura.asociada"/>');
			    			
			    			//ya se sabe que es un alumno por el filtro inicial
			    			formSeminarioAlumno.setRequired('nombre', false);
			    			formSeminarioAlumno.setRequired('codigo', false);
			    			formSeminarioAlumno.hideItem('aceptar');
			    			formSeminarioAlumno.hideItem('asignatura');
				    		formSeminarioAlumno.setReadonly('nombre', true);
				    		formSeminarioAlumno.setReadonly('codigo', true);
				    		formSeminarioAlumno.setReadonly('descripcion', true);
			    		
							formSeminarioAlumno.load('editarseminario.do?idSeminario=' + row);
				    			
			    		});//loadStrut
			    	});//attachEvent
			    	
			    	tabbar.addTab('tad_pendientes','<bean:message key="title.seminarios.pendientes"/>','');
			    	var tad_pendientes = tabbar.cells('tad_pendientes');
			    	
			    	gridAlumnoPendiente = tad_pendientes.attachGrid();
		    		
			    	gridAlumnoPendiente.setHeader(["<bean:message key="label.nombre.seminario" />","<bean:message key="label.codigo.seminario" />"]);
			    	gridAlumnoPendiente.setColTypes("ro,ro");
			    	
			    	gridAlumnoPendiente.setColSorting('str,str');
			    	gridAlumnoPendiente.enableMultiselect(false);
			    	gridAlumnoPendiente.init();
			    	
			    	
			    	var gridAlumnoPendientePro = new dataProcessor("gridSeminariosPendientesUsuario.do?idAlumno=" + idSessionUser + "&idAsignatura=" + idAsignatura + "&peticion=pendientes");
			    	gridAlumnoPendientePro.enableUTFencoding('simple');
			    	gridAlumnoPendientePro.init(gridAlumnoPendiente);	  
			    	gridAlumnoPendientePro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
			    			dhtmlx.message(tag.firstChild.data,action,4000);
			    		}
			    	});
		    		
			    	gridAlumnoPendiente.clearAndLoad("gridSeminariosPendientesUsuario.do?idAlumno=" + idSessionUser + "&idAsignatura=" + idAsignatura + "&peticion=pendientes");
			    	
			    	
			    	gridAlumnoPendiente.attachEvent("onRowSelect", function(row,ind){

			    		var formSeminarioAlumno = b.attachForm();
			    		
			    		formSeminarioAlumno.loadStruct('../xml/forms/seminario_informacion_form.xml', function(){
			    			formSeminarioAlumno.setItemLabel('data','<bean:message key="title.info.general.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('nombre','<bean:message key="label.nombre.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('codigo','<bean:message key="label.codigo.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('descripcion','<bean:message key="label.descripcion.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
			    			formSeminarioAlumno.setItemLabel('asignatura','<bean:message key="label.asignatura.asociada"/>');
			    			
			    			//ya se sabe que es un alumno por el filtro inicial
			    			formSeminarioAlumno.setRequired('nombre', false);
			    			formSeminarioAlumno.setRequired('codigo', false);
			    			formSeminarioAlumno.hideItem('aceptar');
			    			formSeminarioAlumno.hideItem('asignatura');
				    		formSeminarioAlumno.setReadonly('nombre', true);
				    		formSeminarioAlumno.setReadonly('codigo', true);
				    		formSeminarioAlumno.setReadonly('descripcion', true);
			    		
							formSeminarioAlumno.load('editarseminario.do?idSeminario=' + row);
				    			
			    		});//loadStrut
			    	});//attachEvent
			    	
		    	
				 	//buscarAlumno();		
				}
				
	
				
				function goProfesor(){
					
					main_layout = new dhtmlXLayoutObject(document.body, '1C');
		    		var a = main_layout.cells('a');
		    		a.hideHeader();
		    		var tabbar = a.attachTabbar();
		    		var optsSeminarios = dameSeminariosAsignatura();
		    		var numSeminarios=optsSeminarios.length;
		    		
					
		    		//WHILEEEEEEEELELELELLELELELEEEEEE
		    		for (var i=0; i<numSeminarios;i++){
		    			
		    			var string = optsSeminarios[i].toString();
		    			var parts = string.split(',');
		    			
		    			var t = parts[0];
		    			tabbar.addTab(t,parts[1],'');
				    	var tab = tabbar.cells(t);
				    	if(i==0) tabbar.setTabActive(t);
				    	
				    	var grid = tab.attachGrid();
				    	
				    	grid.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />","<bean:message key="label.telefono" />","<bean:message key="label.user.email" />","<bean:message key="label.anyo.realizacion.seminario" />"]);
				    	grid.setColTypes("ro,ro,ro,ro,ro,ro");
				    	
				    	grid.enableMultiselect(false);
				    	grid.setColSorting('str,str,str,str,str,str');
				    	grid.init();
				    	
						
				    	var gridProcessorPro = new dataProcessor("gridUsuariosSeminariosAsignatura.do?idSeminario=" + t);
				    	
				    	gridProcessorPro.enableUTFencoding('simple');
				    	gridProcessorPro.init(grid);	  
				    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
							if(action == 'error'){
				    			dhtmlx.message(tag.firstChild.data,action,4000);
				    		}
				    	});
				    	
		    			grid.clearAndLoad("gridUsuariosSeminariosAsignatura.do?idSeminario=" + t);
		    		}//FOR
		    	
		    		
				}
		    	
		    	function dameSeminariosAsignatura(){
		    		var url = "seminariosAsignaturas.do?idAsignatura=" + idAsignatura;
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
		    		var seminarios = xml.getElementsByTagName("seminario");
		    		var id, nombre, seminario;
		    		var opts = new Array();
		    		for(var i=0;i<seminarios.length;i++) {
		    	        id=seminarios[i].getElementsByTagName("id")[0].firstChild.nodeValue;
		    	        nombre=seminarios[i].getElementsByTagName("nombre")[0].firstChild.nodeValue;
		    	        seminario=[id,nombre];
		    	       	opts[i] = seminario;
		    	    }
		    		return opts;

		    	}
	    	
	   </script>
	</head>
	<body>
	</body>
</html>