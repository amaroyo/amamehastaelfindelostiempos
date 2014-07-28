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
	    

	    <script type="text/javascript">
	    
    		dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	var main_layout, idAsignatura, nombreAsignatura, gridProfesor, gridAlumnoRealizado, gridAlumnoPendiente,idSessionUser;
	    	
	    	dhtmlxEvent(window,"load",function() {
	    		
	    		
	    		<% String idAsignatura = request.getParameter("idAsignatura");%>
	    		idAsignatura="<%=idAsignatura%>";	
	    		<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
				 idSessionUser = <%=sessionIdUser%>;
	    		
	    		
	    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					goProfesor();
				</logic:match>
			
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					goAlumno();
				</logic:notMatch>	
	    	});    		
				
				function goAlumno(){
					
					main_layout = new dhtmlXLayoutObject(document.body, '2U');
		    		var a = main_layout.cells('a');
		    		var b = main_layout.cells('b');
		    		a.hideHeader();
		    		b.setText('<bean:message key="label.descripcion.seminario"/>');
		    		b.setWidth(500);
		    		
		    		var tabbar = a.attachTabbar();
			    	tabbar.addTab('tab_1','<bean:message key="title.seminarios.realizados"/>','');
			    	var tab_1 = tabbar.cells('tab_1');
			    	tabbar.setTabActive('tab_1');
			    	gridAlumnoRealizado = tab_1.attachGrid();
			    	
			    	gridAlumnoRealizado.setHeader(["<bean:message key="label.nombre.seminario" />","<bean:message key="label.codigo.seminario" />","<bean:message key="label.anyo.realizacion.seminario" />"]);
			    	gridAlumnoRealizado.setColTypes("ro,ro,ro");
			    	
			    	gridAlumnoRealizado.setColSorting('str,str,str');
			    	gridAlumnoRealizado.enableMultiselect(false);
			    	gridAlumnoRealizado.init();
			    	
			    	var gridAlumnoRealizadoPro = new dataProcessor("gridSeminariosRealizadosUsuario.do?idAlumno=" + idSessionUser + "&idAsignatura=" + idAsignatura);
			    	gridAlumnoRealizadoPro.enableUTFencoding('simple');
			    	gridAlumnoRealizadoPro.init(gridAlumnoRealizado);	  
			    	gridAlumnoRealizadoPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
			    			dhtmlx.message(tag.firstChild.data,action,4000);
			    		}
			    	});
		    		
			    	gridAlumnoRealizado.clearAndLoad("gridSeminariosRealizadosUsuario.do?idAlumno=" + idSessionUser + "&idAsignatura=" + idAsignatura);
			    	
			    	gridAlumnoRealizado.attachEvent("onRowSelect", function(row,ind){


			    		
			    		var formSeminarioAlumno = b.attachForm();
			    		
			    		formSeminarioAlumno.loadStruct('../xml/forms/seminario_informacion_form.xml', function(){
			    			formSeminarioAlumno.setItemLabel('data','<bean:message key="title.info.general.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('nombre','<bean:message key="label.nombre.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('codigo','<bean:message key="label.codigo.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('curso','<bean:message key="label.curso.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('profesor','<bean:message key="label.profesor.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('descripcion','<bean:message key="label.descripcion.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
				    		
			    			formSeminarioAlumno.hideItem('aceptar');
			    			
							//Ponemos por defecto que los items no se puedan modificar, y luego con los permisos necesarios 
							//seran modificables.
				    		formSeminarioAlumno.setReadonly('nombre', true);
				    		formSeminarioAlumno.setReadonly('codigo', true);
				    		formSeminarioAlumno.setReadonly('curso', true);
				    		formSeminarioAlumno.setReadonly('profesor', true);
				    		formSeminarioAlumno.setReadonly('descripcion', true);
			    		
							formSeminarioAlumno.load('editarseminario.do?idSeminario=' + row);
				    			
			    		});
	
		    		
			    	});
			    	
			    	tabbar.addTab('tab_2','<bean:message key="title.seminarios.pendientes"/>','');
			    	var tab_2 = tabbar.cells('tab_2');
			    	
			    	gridAlumnoPendiente = tab_2.attachGrid();
		    		
			    	gridAlumnoPendiente.setHeader(["<bean:message key="label.nombre.seminario" />","<bean:message key="label.codigo.seminario" />","<bean:message key="label.descripcion.seminario" />"]);
			    	gridAlumnoPendiente.setColTypes("ro,ro,ro");
			    	
			    	gridAlumnoPendiente.setColSorting('str,str,str');
			    	gridAlumnoPendiente.enableMultiselect(false);
			    	gridAlumnoPendiente.init();
			    	
			    	
			    	var gridAlumnoPendientePro = new dataProcessor("gridusuarios.do");
			    	gridAlumnoPendientePro.enableUTFencoding('simple');
			    	gridAlumnoPendientePro.init(gridAlumnoPendiente);	  
			    	gridAlumnoPendientePro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
			    			dhtmlx.message(tag.firstChild.data,action,4000);
			    		}
			    	});
		    		
			    	gridAlumnoPendiente.attachEvent("onRowSelect", function(row,ind){

			    		
			    		
			    		
			    		var formSeminarioAlumno = b.attachForm();
			    		
			    		formSeminarioAlumno.loadStruct('../xml/forms/seminario_informacion_form.xml', function(){
			    			formSeminarioAlumno.setItemLabel('data','<bean:message key="title.info.general.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('nombre','<bean:message key="label.nombre.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('codigo','<bean:message key="label.codigo.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('curso','<bean:message key="label.curso.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('profesor','<bean:message key="label.profesor.seminario"/>');
			    			formSeminarioAlumno.setItemLabel('descripcion','<bean:message key="label.descripcion.seminario"/>');
				    		
				    			
							
							formSeminarioAlumno.setItemValue('nombre', "cacaPENDIENTE");
							formSeminarioAlumno.setItemValue('codigo', "cacaPENDIENTE");
							formSeminarioAlumno.setItemValue('curso', "A113PENDIENTE");
							formSeminarioAlumno.setItemValue('profesor', "Lorem ipsumPENDIENTE");
							formSeminarioAlumno.setItemValue('descripcion', "Lorem ipsum dolor sit ametPENDIENTE");
			    		
							/*formSeminarioAlumno.load('editarseminario.do?idSeminario=' + idSeminario, function () {			    			
								formSeminarioAlumno.attachEvent("onButtonClick", function(id){
				    				if (id == "aceptar") {
				    					formSeminarioAlumno.send("actualizarse.do?!nativeeditor_status=save&idUsuario=" + idUsuario ,"post", function(xml) {
					    					
					    				});
					    				//buscar();
				    				}
				    			});
				    		});*/
				    			
			    		});
	
		    		
			    	});
			    	
		    	
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
				
				
				
				
				
				
				function initRequest() {
		    	    if (window.XMLHttpRequest) {
		    	        xmlhttp = new XMLHttpRequest();
		    	    } else if (window.ActiveXObject) {
		    	        isIE = true;
		    	        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		    	    }
		    	    return xmlhttp;
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