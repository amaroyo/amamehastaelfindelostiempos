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
	    	var main_layout, idAsignatura,nombreAsignatura;
	    	
	    	dhtmlxEvent(window,"load",function() {
	    		
	    		
	    		<% String idAsignatura = request.getParameter("idAsignatura");%>
	    		idAsignatura="<%=idAsignatura%>";	
	    		<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
				var idSelectedUser = <%=sessionIdUser%>;
	    		
	    		
	    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					goProfesor();
				</logic:match>
			
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					goAlumno();
				</logic:notMatch>	
	    		
				
				function goAlumno(){
					
					main_layout = new dhtmlXLayoutObject(document.body, '1C');
		    		var a = main_layout.cells('a');
		    		a.hideHeader();
					var form = a.attachForm();
			    	
			    	form.loadStruct('../xml/forms/estancia_form.xml', function(){
			    		form.setItemLabel('data','<bean:message key="title.info.general.estancia"/>');
			    		form.setItemLabel('hospital','<bean:message key="label.hospital.estancia"/>');
			    		form.setItemLabel('clinica','<bean:message key="label.clinica.estancia"/>');
			    		form.setItemLabel('profesor','<bean:message key="label.profesor.asignatura"/>');
			    		form.setItemLabel('fechaIni','<bean:message key="label.fecha.ini.estancia"/>');
			    		form.setItemLabel('fechaFin','<bean:message key="label.fecha.fin.estancia"/>');
			    		
						//Ponemos por defecto que los items no se puedan modificar, y luego con los permisos necesarios 
						//seran modificables.
			    		form.setReadonly('hospital', true);
			    		form.setReadonly('clinica', true);
			    		form.setReadonly('profesor', true);
			    		form.setReadonly('fechaIni', true);
			    		form.setReadonly('fechaFin', true);
			    		form.hideItem('aceptar');
			    		
			    		//Esto por ahora es provisional, cuando se haga una peticion de toda la informacion 
			    		//de las asignaturas, se cogeran el codigo y el nombre de la asignatura
			    		form.setItemValue('hospital', "Lorem ipsum");
			    		form.setItemValue('clinica', "Lorem ipsum");
			    		form.setItemValue('profesor', "A113");
			    		form.setItemValue('fechaIni', "Lorem ipsum");
			    		form.setItemValue('fechaFin', "Lorem ipsum");
			    		

			    		
			    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >	    	
								form.setReadonly('hospital', false);
					    		form.setReadonly('clinica', false);
					    		form.setReadonly('profesor', false);
					    		form.setReadonly('fechaIni', false);
					    		form.setReadonly('fechaFin', false);
					    		form.showItem('aceptar');
						</logic:match>
			    		
						
						
						
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
				
				
				function goProfesor(){
					
					main_layout = new dhtmlXLayoutObject(document.body, '2U');
					main_layout.setAutoSize("a;b");
		    		var a = main_layout.cells('a');
		    		var b = main_layout.cells('b');
		    		a.setText(["<strong><bean:message key="label.mis.alumnos.estancia" /></strong>"]);
		    		b.hideHeader();
					var miGrid = a.attachGrid();
					
					miGrid.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />","a","b"]);
			    	miGrid.setColTypes("ro,ro,ro,ro,ro");
			    	
				    miGrid.enableMultiselect(false);
				    miGrid.setColSorting('str,str,str,str,str');
				    miGrid.init();
				    
				    
				   
					var idSelectedUser = <%=sessionIdUser%>;
					//ORIGINAL
				    //gridAlumnosProcessor = new dataProcessor("gridMisAlumnos.do?idUsuario"+idSelectedUser);
					//PRUEBA
				    gridAlumnosProcessor = new dataProcessor("gridMisAlumnos.do");
					
				    gridAlumnosProcessor.enableUTFencoding('simple');
				    gridAlumnosProcessor.init(miGrid);	  
				    gridAlumnosProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
			    			dhtmlx.message(tag.firstChild.data,action,4000);
			    		}
			    	});	
					
					
					
				    
			    			    			    
					
				}
	    		
	    });
	    	
	    
	    	
	   </script>
	</head>
	<body>
	</body>
</html>