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
	    	var main_layout, idAsignatura,nombreAsignatura, gridProfesor, formAlumno;
	    	
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
		    		formAlumno = a.attachForm();
			    	
		    		formAlumno.loadStruct('../xml/forms/estancia_form.xml', function(){
		    			formAlumno.setItemLabel('data','<bean:message key="title.info.general.estancia"/>');
		    			formAlumno.setItemLabel('hospital','<bean:message key="label.hospital.estancia"/>');
		    			formAlumno.setItemLabel('clinica','<bean:message key="label.clinica.estancia"/>');
		    			formAlumno.setItemLabel('profesor','<bean:message key="label.profesor.asignatura"/>');
		    			formAlumno.setItemLabel('fechaIni','<bean:message key="label.fecha.ini.estancia"/>');
		    			formAlumno.setItemLabel('fechaFin','<bean:message key="label.fecha.fin.estancia"/>');
			    		
						//Ponemos por defecto que los items no se puedan modificar, y luego con los permisos necesarios 
						//seran modificables.
			    		formAlumno.setReadonly('hospital', true);
			    		formAlumno.setReadonly('clinica', true);
			    		formAlumno.setReadonly('profesor', true);
			    		formAlumno.setReadonly('fechaIni', true);
			    		formAlumno.setReadonly('fechaFin', true);
			    		formAlumno.hideItem('aceptar');
			    		
			    		//Esto por ahora es provisional, cuando se haga una peticion de toda la informacion 
			    		//de las asignaturas, se cogeran el codigo y el nombre de la asignatura
			    		formAlumno.setItemValue('hospital', "Lorem ipsum");
			    		formAlumno.setItemValue('clinica', "Lorem ipsum");
			    		formAlumno.setItemValue('profesor', "A113");
			    		formAlumno.setItemValue('fechaIni', "Lorem ipsum");
			    		formAlumno.setItemValue('fechaFin', "Lorem ipsum");
			    		

			    		
			    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >	    	
				    		formAlumno.setReadonly('hospital', false);
				    		formAlumno.setReadonly('clinica', false);
				    		formAlumno.setReadonly('profesor', false);
				    		formAlumno.setReadonly('fechaIni', false);
				    		formAlumno.setReadonly('fechaFin', false);
				    		formAlumno.showItem('aceptar');
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
		    		gridProfesor = a.attachGrid();
					
		    		gridProfesor.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />","a","b"]);
		    		gridProfesor.setColTypes("ro,ro,ro,ro,ro");
			    	
		    		gridProfesor.enableMultiselect(false);
		    		gridProfesor.setColSorting('str,str,str,str,str');
		    		gridProfesor.init();
				    
				    
				   
					var idSelectedUser = <%=sessionIdUser%>;
					//ORIGINAL
				    //gridAlumnosProcessor = new dataProcessor("gridMisAlumnos.do?idUsuario"+idSelectedUser);
					//PRUEBA
				    var gridAlumnosProfesorProcessor = new dataProcessor("gridusuarios.do");
					
				    gridAlumnosProfesorProcessor.enableUTFencoding('simple');
				    gridAlumnosProfesorProcessor.init(gridProfesor);	  
				    gridAlumnosProfesorProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
			    			dhtmlx.message(tag.firstChild.data,action,4000);
			    		}
			    	});	
					
				    
				    
				    gridProfesor.attachEvent("onRowSelect", function(row,ind){
						
				    	
				    	selectedEmail=gridProfesor.cells(row,4).getValue();

				    	var tabbar = b.attachTabbar();
				    	tabbar.addTab('tab_1','<bean:message key="label.propiedades"/>','');
				    	var tab_1 = tabbar.cells('tab_1');
				    	tabbar.setTabActive('tab_1');
				    	var form = tab_1.attachForm();
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
									form.hideItem('aceptar');								
							</logic:notMatch>			    		
				    		
				    		form.load('editarusuario.do?email=' + selectedEmail, function () {			    			
				    			form.attachEvent("onButtonClick", function(id){
				    				if (id == "aceptar") {
					    				form.send("actualizarusuario.do?!nativeeditor_status=save&email=" + selectedEmail ,"post", function(xml) {
					    					
					    				});
					    				buscar();
				    				}
				    			});
				    		});
				    	});
				    	
				    	
				    
			    			    			    
					});
				    
				    buscar();
				}
				
				function buscar() {
					gridProfesor.clearAndLoad("gridusuarios.do");		    	
			    }
	    		
	    });
	    	
	    
	    	
	   </script>
	</head>
	<body>
	</body>
</html>