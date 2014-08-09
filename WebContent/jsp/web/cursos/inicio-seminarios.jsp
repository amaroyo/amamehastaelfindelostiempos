<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>
<%@ include file="../../common/taglibs.jsp" %>

<html>
	<head>
	   <link rel="stylesheet" type="text/css" href="../css/estilos.css">
		<link rel="stylesheet" type="text/css" href="../css/templates.css">
		<link rel="stylesheet" type="text/css" href="../css/estilosMenu.css">
		<script type="text/javascript" src="../js/utilsajax.js"></script>
		<script type="text/javascript" src="../js/general.js"></script>
		
		
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlx.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlxgrid.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlxgrid_skins.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlxlayout.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlxtabbar.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlxwindows.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/skins/dhtmlxform_dhx_skyblue.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/skins/dhtmlxgrid_dhx_skyblue.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/skins/dhtmlxlayout_dhx_skyblue.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/skins/dhtmlxtoolbar_dhx_skyblue.css">
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/skins/dhtmlxwindows_dhx_skyblue.css">
		
		
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxcommon.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxlayout.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxtabbar.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxtabbarstart.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxform.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxtoolbar.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxgrid.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxcontainer.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxwindows.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxgridcell.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxdataprocessor.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_dyn.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_item_container.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_item_upload.js"></script>
	    

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../skins/imgs/';
	    	
	    	var main_layout, areaTrabajoCursos, listado, toolbarSeminarios,
	    	gridSeminarios, tabbarSeminarios, tabInfo, formInfo;
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	dhtmlxError.catchError("ALL",errorHandler);
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    listado = main_layout.cells('a');
			    listado.setWidth(600);
			    areaTrabajoCursos = main_layout.cells('b');
			    
			    //autosize(horizontal,vertical)
			    //"a;b" 'a' and 'b' will autosize when changing horizontal dimensions of layout
			    //listado.setAutoSize("a;b",null)
			    
		    	listado.setText("<strong><bean:message key="title.seminarios" /></strong>");
			    areaTrabajoCursos.setText("<bean:message key="title.propiedades.seminario" />");
			    	
			    toolbarSeminarios = listado.attachToolbar();
			    toolbarSeminarios.setIconsPath('../skins/imgs/toolbar/');
			    toolbarSeminarios.loadXML('../xml/toolbars/dhxtoolbar-seminarios.xml', function(){
		    		toolbarSeminarios.setItemText('new',"<bean:message key="button.create.seminario"/>");
		    		toolbarSeminarios.setItemText('delete',"<bean:message key="button.eliminar.seminario"/>");
		    		toolbarSeminarios.setItemText('exportExcel',"<bean:message key="button.exportar.excel"/>");
	    			toolbarSeminarios.setItemText('exportPDF',"<bean:message key="button.exportar.pdf"/>");
			    	toolbarSeminarios.setItemText('refresh',"<bean:message key="button.actualizar"/>");
	    		//permisosToolbarSeminarios();
		    });
			    
			    gridSeminarios = listado.attachGrid();
			    gridSeminarios.setIconsPath('../skins/imgs/');
			    gridSeminarios.setHeader(["<strong><bean:message key="label.nombre.seminario" /></strong>",
			    	                      "<strong><bean:message key="label.codigo.seminario" /></strong>",
			    	                      "<strong><bean:message key="label.curso.seminario" /></strong>",
			    	                      "<strong><bean:message key="label.descripcion.seminario" /></strong>"]);
			    
			    //ro = readonly
			    //nombre codigo curso descripcion
			    gridSeminarios.setColTypes("ro,ro,ro,ro");
			    gridSeminarios.setColSorting('str,str,str,str');
			    gridSeminarios.enableMultiselect(false);
			    gridSeminarios.init();
			    gridSeminariosProcessor = new dataProcessor("gridseminarios.do");
			    gridSeminariosProcessor.enableUTFencoding('simple');
			    gridSeminariosProcessor.init(gridSeminarios);	  
			    gridSeminariosProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
			 	// TAG: Prueba
				gridSeminarios.addRow('1','A01,Anatomía,1º,Seminarios de Anatomía');
			    
				gridSeminarios.attachEvent("onRowSelect", doOnRowSelected);				  
		    });
		    
		    
		    function doOnRowSelected(rowID,celInd){
		    	toolbarSeminarios.enableItem('delete');

			    tabbarSeminarios = areaTrabajoCursos.attachTabbar();
			    tabbarSeminarios.addTab('tabInfo','<bean:message key="label.info.general"/>','');
		    	tabInfo = tabbarSeminarios.cells('tabInfo');
		    	tabbarSeminarios.setTabActive('tabInfo');
		    	formInfo = tabInfo.attachForm();
		    	loadFormSeminario();
		    	// obtener el nombre del curso de la bbdd y añadirlo como header a la dcha
				areaTrabajoCursos.setText(areaTrabajoCursos.getText() + "");
		    }
		    
		    function loadFormSeminario(){
	    		formInfo.loadStruct('../xml/forms/seminario_informacion_form.xml', function(){
	    			formInfo.setItemLabel('data','<bean:message key="title.info.general.seminario"/>');
	    			formInfo.setItemLabel('nombre','<bean:message key="label.nombre.seminario"/>');
	    			formInfo.setItemLabel('codigo','<bean:message key="label.codigo.seminario"/>');
	    			formInfo.setItemLabel('curso','<bean:message key="label.curso.seminario"/>');
	    			formInfo.setItemLabel('profesor','<bean:message key="label.profesor.seminario"/>');
	    			formInfo.setItemLabel('descripcion','<bean:message key="label.descripcion.seminario"/>');
	    			//permisosSeminariosForm();
	    			
		    		form.load('editarseminario.do?idSeminario=' + idSeminario, function () {			    			
		    			form.attachEvent("onButtonClick", function(id){
		    				if (id == "aceptar") {
			    				form.send("actualizarseminario.do?!nativeeditor_status=save&idSeminario=" + idSeminario ,"post", function(xml) {
			    					
			    				});
			    				buscar();
		    				}
		    			});
		    		});
	    		});
		    }
		    
		    
		    function buscar() {
		    	gridSeminarios.clearAndLoad("gridSeminarios.do");		    	
		    }
	    	
		    function permisosToolbarSeminarios(){
		    	
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >	    	
			   		toolbarSeminarios.hideItem('new');
			   		toolbarSeminarios.hideItem('sep1');    	
			   		toolbarSeminarios.hideItem('delete');
			   		toolbarSeminarios.hideItem('sep2');
				</logic:notMatch>
	    	}
		    
		    function permisosSeminariosForm(){
	    		<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >    	
	    			formInfo.forEachItem(function(id){
	    				if(getType(id) == "input"){
	    			   		myForm.setReadOnly(id, true);
	    				}
	    				else if(getType(id) == "button"){
	    					formInfo.hideItem(id);
	    				}
	    			});
				</logic:notMatch>	
				
				
	    	}
		    
		    function exportPDF(){
		    	
		    }
		    
		    function exportExcel(){
		    	gridAsignaturas.toExcel('../skins/grid-excel-php/generate.php');
		    }
		  
        </script>
	</head>
	<body>
	</body>
</html>