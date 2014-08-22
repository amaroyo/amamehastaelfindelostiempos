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
	    
	    	dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	
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
		    	
	    		else if(rowID == 'd'){
	    			goRegistroErrores();
	    		}
		    	
		    	if(rowID != 'd'){
			    	var dhxWins= new dhtmlXWindows();
					var window = dhxWins.createWindow("subir", 300,50, 500, 170);
					window.setText(alubia);				
					window.setModal(true);
					window.centerOnScreen();
					window.attachURL("subirArchivo.do?tipoConsulta=" + tipo);
		    	}
			
		    }
		    
		  
		    function goRegistroErrores(){
		    	var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("subir", 300,50, 350, 350);
				window.setText('<bean:message key="label.registro.errores" />');				
				window.setModal(true);
				window.centerOnScreen();
				var layout = window.attachLayout("1C","dhx_skyblue");
				var a = layout.cells('a');
				a.hideHeader();
				var gridErrores = a.attachGrid();
				gridErrores.setIconsPath('../skins/imgs/');		   
  
			    
				gridErrores.setHeader(["<bean:message key="label.tipo" />","<bean:message key="label.fecha" />"]);
			    
			    
			    //alineacion del contenido en la columna
			    gridErrores.setColAlign("left,left");
			    
			    gridErrores.setColTypes("ro,ro");
		    	
			    gridErrores.enableMultiselect(false);
			    gridErrores.setColSorting('str,str');
			    gridErrores.init();
		    	
				var gridProcessorMios = new dataProcessor("gridRegistroErrores.do?");
				gridProcessorMios.enableUTFencoding('simple');
				gridProcessorMios.init(gridErrores);	  
				gridProcessorMios.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});		    	

				gridErrores.clearAndLoad("gridRegistroErrores.do?");
				
				gridErrores.attachEvent("onRowSelect",function doOnRowSelected(rowID,celInd){
		    		
					var accion = "descargarRegistroError.do";
					accion += "?tipoConsulta="+"RegistroError";
					accion += "&idError=" + rowID;
					location.href=accion;
					
		    	});
		    }
		   
		    
		  
		  
		  
        </script>
	
</html>