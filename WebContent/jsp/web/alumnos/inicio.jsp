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
	    <link rel="stylesheet" type="text/css" href="../skins/dhtmlxform_dhx_skyblue.css">
	    <script type="text/javascript" src="../skins/dhtmlx.js"></script>
	    <script type="text/javascript" src="../skins/dhtmlxform.js"></script>
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../skins/imgs/';
	    	
	    	var miGrid, tabbar, tab_1,tab_2,tab_3,tab_4,tab_5,tab_6,tab_7, main_layout, form, b, a;
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
			    dhtmlxError.catchError("ALL",errorHandler);
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    a = main_layout.cells('a');
			    b = main_layout.cells('b');
			    
			    
			    a.setText("<strong><bean:message key="label.todos.mis.alumnos" /></strong>");
			    a.setWidth(380);
			    b.hideHeader();
			    
			    
			    miGrid = a.attachGrid();
			    miGrid.setIconsPath('../skins/imgs/');		    	
			    miGrid.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />"]);
			    
			    //anchura de las columnas, en porcentaje. La suma tiene que ser igual a 100
			    miGrid.setInitWidthsP("25,60,15");
			    //alineacion del contenido en la columna
			    miGrid.setColAlign("left,left,left");
			    
			    miGrid.setColTypes("ro,ro,ro");
		    	
			    miGrid.enableMultiselect(false);
			    miGrid.setColSorting('str,str,str');
			    miGrid.init();
		    	
				var gridProcessor = new dataProcessor("gridusuarios.do");
				gridProcessor.enableUTFencoding('simple');
				gridProcessor.init(miGrid);	  
				gridProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});		    	

			    miGrid.attachEvent("onRowSelect",doOnRowSelected);
			    
			    buscar();
			    			    
		    });
		    
		   
		    function doOnRowSelected(rowID,celInd){
		    	
		    	var dni = miGrid.cells(rowID,2).getValue();
		    	
		    	tabbar = b.attachTabbar();
		    	tabbar.addTab('tab_1','<bean:message key="title.datos.personales"/>','');
		    	tab_1 = tabbar.cells('tab_1');
		    	tabbar.setTabActive('tab_1');
		    	//goInformacion(dni);
		    	
		    	tabbar.addTab('tab_2','<bean:message key="title.info.general.estancia"/>','');
		    	tab_2 = tabbar.cells('tab_2');
		    	//goEstancia(dni);
		    	
		    	tabbar.addTab('tab_3','<bean:message key="title.seminarios"/>','');
		    	tab_3 = tabbar.cells('tab_3');
		    	//goSeminarios(dni);
		    	
		    	tabbar.addTab('tab_4','<bean:message key="title.trabajos.campo"/>','');
		    	tab_4 = tabbar.cells('tab_4');
		    	//goTrabajos(dni);
		    	
		    	tabbar.addTab('tab_5','<bean:message key="title.casos.clinicos"/>','');
		    	tab_5 = tabbar.cells('tab_5');
		    	//goCasos(dni);
		    	
		    	tabbar.addTab('tab_6','<bean:message key="title.diario.reflexivo"/>','');
		    	tab_6 = tabbar.cells('tab_6');
		    	//goCasos(dni);
		    	
		    	tabbar.addTab('tab_7','<bean:message key="title.rubrica"/>','');
		    	tab_7 = tabbar.cells('tab_7');
		    	//goCasos(dni);
		    	
		    	
		    }
		    
		    
		    function buscar() {
		    	miGrid.clearAndLoad("gridusuarios.do");		    	
		    }
		    
		    
		    
		    
        </script>
	</head>
	<body>
	</body>
</html>