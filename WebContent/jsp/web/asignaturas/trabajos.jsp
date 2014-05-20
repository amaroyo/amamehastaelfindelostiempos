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
	    	var main_layout, idAsignatura, nombreAsignatura, gridProfesores,gridAlumnos,tab, profesor,a,b, tabbar;
	    	
	    	dhtmlxEvent(window,"load",function() {
	    		
	    		//inicializo profesor a falso para tener un poco de seguridad
	    		profesor=false;
	    		
	    		<% String idAsignatura = request.getParameter("idAsignatura");%>
	    		idAsignatura="<%=idAsignatura%>";	
	    		<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
				var idSelectedUser = <%=sessionIdUser%>;
	    		

				
				
	    		
	    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					profesor=true;
					main_layout = new dhtmlXLayoutObject(document.body, '2U');
		    		a = main_layout.cells('a');
		    		b = main_layout.cells('b');
		    		b.setWidth(250);
		    		a.hideHeader();
					b.setText('<bean:message key="label.opciones.alumno"/>');
				</logic:match>
		
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					profesor=false;
					main_layout = new dhtmlXLayoutObject(document.body, '1C');
		    		a = main_layout.cells('a');
		    		a.hideHeader();
				</logic:notMatch>	
	    		
	    		
	    		
	    		
	    		tabbar = a.attachTabbar();
	    		
	    		//HE AQUI QUE TENEMOS QUE HACER UNA CONSULTA EN MYSQL PARA SABER CUANTAS PRACTICAS
	    		//VA A HABER SEGUN LA ASIGNATURA PINCHADA PARA HACER EL WHILE
	    		
	    		var numTrabajosCampo=0;
	    		switch(idAsignatura){
		    		case "idAsignatura1": {numTrabajosCampo=1;break;}
		        	case "idAsignatura2": {numTrabajosCampo=2;break;}
		        	case "idAsignatura3": {numTrabajosCampo=3;break;}
		        	case "idAsignatura4": {numTrabajosCampo=4;break;}
		        	case "idAsignatura5": {numTrabajosCampo=5;break;}
		        	case "idAsignatura6": {numTrabajosCampo=6;break;}
		        	case "idAsignatura7": {numTrabajosCampo=7;break;}
	    		}
				
	    		for (var i=0; i<numTrabajosCampo;i++) {
	    			var mytab = "tab_"+i;
	    			tabbar.addTab(mytab,'Práctica ' + i,'');
	    			//no se como hacer que sea activa y que ademas este seleccionada para 
	    			//disparar al metodo onSelect para que lo rellene con datos...
			    	//alert("Cargando...." + (i+1) + "/" + numTrabajosCampo + " prácticas. Por favor, espere...");
	    			if(i==0) tabbar.setTabActive(mytab);
	    			
	    			initTabContent(mytab);
	    		}
	    	});
	    	
	    	
	    	
	    	
		    	
	    	function initTabContent(tabID){
				
				tab = tabbar.cells(tabID);
								
		    	var toolbarServicios = tab.attachToolbar();
		    	toolbarServicios.setIconsPath('../img/toolbar/');

		    	
		    	toolbarServicios.loadXML('../xml/toolbars/dhtxtoolbar-trabajos-campo.xml', function(){
		    		toolbarServicios.setItemText('subirPractica',"<bean:message key="button.subir.practica"/>");
		    		toolbarServicios.setItemText('descargarTodos',"<bean:message key="button.descargar.trabajos"/>");
		    		toolbarServicios.setItemText('subirCorrecciones',"<bean:message key="button.subir.correcciones"/>");
		    		toolbarServicios.setItemText('fechaLimite',"<bean:message key="button.fecha.limite"/>");
		    		toolbarServicios.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    	});
		    	
		    	if (profesor) goGridProfesores(tab);
		    	else goGridAlumnos(tab);
			}
		    	
			
			function subirPractica(){
				alert("subir Practica");
			}
			
			function descargarTodos(){
				alert("Descargar Todos");
			}
			
			function subirCorrecciones(){
				alert("Subir Correcciones");
			}
			
			function fechaLimite(){
				alert("Fecha Limite");
			}
			
			function goActualizar() {
				if (profesor) gridProfesores.clearAndLoad("gridusuarios.do");	 
				else gridAlumnos.clearAndLoad("gridusuarios.do");		    	
		    	//tabbar.clearAll();		    	
		    }
			
			function goGridAlumnos(tab){
				
				gridAlumnos = tab.attachGrid();
		    	
				gridAlumnos.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.fecha" />","<bean:message key="label.enlace" />"]);
				gridAlumnos.setColTypes("ro,ro,ro");
		    	
				gridAlumnos.setColSorting('str,str,str');
				gridAlumnos.enableMultiselect(false);
				gridAlumnos.init();
		    	
		    	var gridProcessorPro = new dataProcessor("gridusuarios.do");
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridAlumnos);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
		    	
		    	gridAlumnos.clearAndLoad("gridusuarios.do");
				
			}
			
			function goGridProfesores(tab){
				
				gridProfesores = tab.attachGrid();
		    	
				gridProfesores.setHeader(["<bean:message key="label.alumno" />","<bean:message key="label.dni" />","<bean:message key="label.fecha" />","<bean:message key="label.enlace" />"]);
				gridProfesores.setColTypes("ro,ro,ro,ro");
		    	
				gridProfesores.setColSorting('str,str,str,str');
				gridProfesores.enableMultiselect(false);
				gridProfesores.init();
		    	
		    	var gridProcessorPro = new dataProcessor("gridusuarios.do");
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridProfesores);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});

		    	
		    	gridProfesores.attachEvent("onRowSelect",doOnRowSelected);
		    	
		    	gridProfesores.clearAndLoad("gridusuarios.do");
				
			}
			
			function doOnRowSelected(rowID,celInd){
				var miGrid = b.attachGrid();
			    miGrid.setIconsPath('../skins/imgs/');		    	
			    miGrid.setHeader(["<strong><bean:message key="label.mi.perfil" /></strong>"]);
			    //set readonly (ro)
			    miGrid.setColTypes("ro");
			    miGrid.setNoHeader(true);
			    miGrid.enableMultiselect(false);
			    miGrid.init();
			    miGrid.loadXML("../xml/forms/asignaturas_trabajos_opciones.xml");
			    miGrid.attachEvent("onRowSelect",doOnRowSelectedOptions); 
		    	
		    }
			
			function doOnRowSelectedOptions(rowID,celInd){
				
				if (rowID == "a") alert("Descargar");
		    	else if (rowID == "b") alert("Subir corrección");
		    	else alert("Cambiar la fecha de entrega");
			}
			
	    	
	   </script>
	</head>
	<body>
	</body>
</html>