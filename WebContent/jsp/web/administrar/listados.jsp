<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>
<%@ include file="../../common/taglibs.jsp" %>
<%@ page import="java.util.Enumeration"%>
<%@ page import="es.oyssen.mrm.Const"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>

<html>
	<head>
	    <link rel="stylesheet" type="text/css" href="../css/estilos.css">
	    <link rel="stylesheet" type="text/css" href="../css/templates.css">
	    <link rel="stylesheet" type="text/css" href="../css/estilosMenu.css">
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    
	    <link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlx.css">
	    <link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/skins/dhtmlxform_dhx_skyblue.css">
	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxform.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_item_upload.js"></script>
	    
	    
	    

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../skins/imgs/';
	    	
	    	var miGrid, main_layout, a, b;
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
			    dhtmlxError.catchError("ALL",errorHandler);
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    a = main_layout.cells('a');
			    b =  main_layout.cells('b');
			    
			    main_layout.cells("a").setWidth(150);
			    main_layout.cells("a").setText(["<strong><bean:message key="label.administrar" /></strong>"]);
			    main_layout.cells("b").hideHeader();
			    
			    
			    miGrid = a.attachGrid();
			    miGrid.setIconsPath('../skins/imgs/');		    	
			    miGrid.setHeader(["<bean:message key="label.subir.listados" />"]);
			    //set readonly (ro)
			    miGrid.setColTypes("ro");
			    miGrid.enableMultiselect(false);
			    miGrid.init();
			    miGrid.loadXML("../xml/forms/listados_form.xml");
			    miGrid.attachEvent("onRowSelect",doOnRowSelected);
			    			    
		    });
		    
		    function doOnRowSelected(rowID,celInd){
		    	
		    	var tipo = "";
		    	var alubia = "";
		    	
		    	if(rowID == 'a'){
		    		tipo="alumnos";
		    		alubia='<bean:message key="label.importar.alumnos" />';
	    		}
	    		else if(rowID == 'b'){
	    			tipo="profesores";
	    			alubia='<bean:message key="label.importar.profesores" />';
	    		}
	    		else if(rowID == 'c'){
	    			tipo="usuarios";
	    			alubia='<bean:message key="label.importar.usuarios" />';
	    		}
		    	
		    	
		    	var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("subir", 300,50, 500, 150);
				window.setText(alubia);				
				window.setModal(true);
				window.centerOnScreen();
				window.attachURL("subirArchivo.do?tipoConsulta=" + tipo);
			
		    }
		    
		  
		    
		   
		    
		  
		  
		  
        </script>
	
</html>