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
	    	
	    	var gridCursos, gridCursos2, tabbar, tab_1, main_layout, areaTrabajoCursos, listado, toolbarCursos,opcionSeminarioOAsignatura, idSelectedCourse, gridCursos;
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	<% String opcion = request.getParameter("opcion");%>
		    	opcionSeminarioOAsignatura="<%=opcion%>";
		    	
			    dhtmlxError.catchError("ALL",errorHandler);
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    listado = main_layout.cells('a');
			    areaTrabajoCursos = main_layout.cells('b');
			    
			    listado.setWidth(500);
			    //autosize(horizontal,vertical)
			    //"a;b" 'a' and 'b' will autosize when changing horizontal dimensions of layout
			    //listado.setAutoSize("a;b",null)
			    if(opcionSeminarioOAsignatura == "seminarios") {
			    	listado.setText(["<strong><bean:message key="title.seminarios" /></strong>"]);
				    areaTrabajoCursos.setText("<bean:message key="title.propiedades.seminario" />");
			    }
			    else if(opcionSeminarioOAsignatura == "asignaturas") {
			    	listado.setText(["<strong><bean:message key="title.asignaturas" /></strong>"]);
			    	areaTrabajoCursos.setText("<bean:message key="title.propiedades.asignatura" />");
			    }
			    	
			    toolbarCursos = listado.attachToolbar();
			    toolbarCursos.setIconsPath('../skins/imgs/toolbar/');
		    	
			    toolbarCursos.loadXML('../xml/toolbars/dhxtoolbar-cursos.xml', function(){
		    		if(opcionSeminarioOAsignatura == "seminarios") {
		    			toolbarCursos.setItemText('new',"<bean:message key="button.create.seminario"/>");
		    			toolbarCursos.setItemText('delete',"<bean:message key="button.eliminar.seminario"/>");
		    		}
		    		else if(opcionSeminarioOAsignatura == "asignaturas") {
		    			toolbarCursos.setItemText('new',"<bean:message key="button.create.asignatura"/>");
		    			toolbarCursos.setItemText('delete',"<bean:message key="button.eliminar.asignatura"/>");
		    		}
		    		
		    		toolbarCursos.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
		    		<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >	    	
			    		toolbarCursos.hideItem('new');
			    		toolbarCursos.hideItem('sep1');    	
			    		toolbarCursos.hideItem('delete');
			    		toolbarCursos.hideItem('sep2');
					</logic:notMatch>
		    	});
					
			    gridCursos = listado.attachGrid();
			    gridCursos.setIconsPath('../skins/imgs/');		    	
			    gridCursos.setHeader(["<strong><bean:message key="title.cursos" /></strong>"]);
			    gridCursos.setNoHeader(true);
			    //ro = readonly
			    //nombre codigo curso descripcion
			    gridCursos.setColTypes("ro,ro,ro,ro");
			    gridCursos.setColSorting('str,str,str,str,str');
			    // ??????????????
			    gridCursos.enableMultiselect(true);
			    gridCursos.init();
		    			    			    
			    gridCursosProcessor = new dataProcessor("gridcursos.do");
			    gridCursosProcessor.enableUTFencoding('simple');
			    gridCursosProcessor.init(gridCursos);	  
			    gridCursosProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
			    
			    gridCursosProcessor.attachEvent("onRowSelect", function(idCurso,ind){
		    		toolbarUsuarios.enableItem('delete');
			    
					idSelectedCourse = idCurso;
					// obtener el nombre del curso de la bbdd
					//areaTrabajoCursos.setText("<bean:message key="title.propiedades.curso" />");
					
			    });
					
			  
		    });
		  
        </script>
	</head>
	<body>
	</body>
</html>