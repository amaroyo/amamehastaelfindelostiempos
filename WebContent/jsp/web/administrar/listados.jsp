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
	    <link rel="stylesheet" type="text/css" href="../skins/dhtmlx.css">
	    <link rel="stylesheet" type="text/css" href="../skins/dhtmlxform_dhx_skyblue.css">
	    <script type="text/javascript" src="../skins/dhtmlx.js"></script>
	    <script type="text/javascript" src="../skins/dhtmlxform.js"></script>
	    <script type="text/javascript" src="../skins/dhtmlxform_item_upload.js"></script>
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    

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
		    	importarForm = b.attachForm();
		    	importarForm.loadStruct('../xml/forms/importar_form.xml', function() {
		    		setFormLabels(rowID);
		    		
		    		importarForm.attachEvent("onBeforeFileAdd",function(realName, size){
		    			
		    			var myUploader = importarForm.getUploader("subir");
						myUploader.clear();
						return true;
		    		});
		    		
		    		importarForm.attachEvent("onEnter", function() {
		    			goImportar();
		    		});
		    		
		    		importarForm.attachEvent("onButtonClick", function(id){
	    				if(id == "import") {
	    					onSubmit();
	    				}
	    				
		    		});
		    	});
		    	
				goActualizar();
		    }
		    
		    function onSubmit(){
				if(importarForm.getUploaderStatus("subir") == 1){
					var fileData = importarForm.getItemValue("subir");
				    /*(item's name)+“_count” - the count of uploaded files (item name “myFiles” is used for the code above)
				    (item's name)+“_r_”+(0..count-1) - the real name of the file
				    (item's name)+“_s_”+(0..count-1) - the file name that the server returns after uploading*/
			    	var fileName = fileData.subir_r_0;
			    	if(isExcelExtension(fileName)) {
						goImportar(rowID);
			    	}
				}
		    }
		    
		    function getExtension(fileName) {
			    var parts = fileName.split('.');
			    return parts[parts.length - 1];
			}
		    
		    function isExcelExtension(fileName) {
		        var ext = getExtension(fileName);
		        switch (ext.toLowerCase()) {
		        case 'xls':
		        case 'xlsx':
		            //etc
		            return true;
		        }
		        return false;
		    }
		    
		    function setFormLabels(rowID){
		    	if(rowID == 'a'){
	    			importarForm.setItemLabel('data','<bean:message key="title.importar.alumnos"/>');
		    		importarForm.setItemLabel('seleccionar','<bean:message key="label.seleccionar.archivo"/>');
		    		importarForm.setItemLabel('import','<bean:message key="label.importar.alumnos"/>');
	    		}
	    		else if(rowID == 'b'){
	    			importarForm.setItemLabel('data','<bean:message key="title.importar.profesores"/>');
		    		importarForm.setItemLabel('seleccionar','<bean:message key="label.seleccionar.archivo"/>');
		    		importarForm.setItemLabel('import','<bean:message key="label.importar.profesores"/>');
	    		}
	    		else if(rowID == 'c'){
	    			importarForm.setItemLabel('data','<bean:message key="title.importar.usuarios"/>');
		    		importarForm.setItemLabel('seleccionar','<bean:message key="label.seleccionar.archivo"/>');
		    		importarForm.setItemLabel('import','<bean:message key="label.importar.usuarios"/>');
	    		}
		    }
		    
		    function goImportar(rowID) {
		    	
		    	if(rowID == 'a'){
		    		importarForm.send("administrar/importarAlumnos", function(loader, response) {
		                alert(loader.xmlDoc.responseText);
		            });
	    		}
	    		else if(rowID == 'b'){
	    			importarForm.send("administrar/importarProfesores", function(loader, response) {
		                alert("<pre>" + response + "</pre>"+rowID);
		            });
	    		}
	    		else if(rowID == 'c'){
	    			importarForm.send("administrar/importarUsuarios", function(loader, response) {
		                alert("<pre>" + response + "</pre>"+rowID);
		            });
	    		}
		    	
		    	
		    	/*importarForm.send("actualizarcontrasena.do?oldPass=" + oldPass + "&newPass=" + newPass,"post", function(xml) {
					goEntrada();
				});*/
		    }
		    
		    function goActualizar() {
		    	/*buscar();
		    	toolbar_1.disableItem('delete');
		    	tabbar.clearAll();*/		    	
		    }
		  
        </script>
	</head>
	<body>
		<form name="myForm" method="post" enctype="multipart/form-data" onsubmit="onSubmit()">
			<div id="importarForm"></div>
		</form>
	</body>
</html>