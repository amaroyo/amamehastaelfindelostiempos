<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>
<%@ include file="../../common/taglibs.jsp" %>

<html>
	<head>
	    <link rel="stylesheet" type="text/css" href="../css/estilos.css">
	    <link rel="stylesheet" type="text/css" href="../css/templates.css">
	    <link rel="stylesheet" type="text/css" href="../css/estilosMenu.css">
	    <link rel="stylesheet" type="text/css" href="../skins/dhtmlx.css">
	    <script type="text/javascript" src="../skins/dhtmlx.js"></script>
	    <script type="text/javascript" src="../skins/dhtmlxdataprocessor.js"></script>
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    <script type="text/javascript" src="../skins/dhtmlxgrid.js"></script>
	    <script type="text/javascript" src="../skins/dhtmlxgrid_export.js"></script>
	    

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../skins/imgs/';
	    	
	    	var main_layout, areaTrabajoCursos, listado, toolbarAsignaturas,
	    	gridAsignaturas, tabbarAsignaturas, tabInfo, formInfo;
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	dhtmlxError.catchError("ALL",errorHandler);
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    listado = main_layout.cells('a');
			    listado.setWidth(700);
			    areaTrabajoCursos = main_layout.cells('b');
			    
			    //autosize(horizontal,vertical)
			    //"a;b" 'a' and 'b' will autosize when changing horizontal dimensions of layout
			    //listado.setAutoSize("a;b",null)
			    
		    	listado.setText("<strong><bean:message key="title.asignaturas" /></strong>");
		    	areaTrabajoCursos.setText("<bean:message key="title.propiedades.asignatura" />");
			    	
			    toolbarAsignaturas = listado.attachToolbar();
			    toolbarAsignaturas.setIconsPath('../skins/imgs/toolbar/');
			    toolbarAsignaturas.loadXML('../xml/toolbars/dhxtoolbar-asignaturas.xml', function(){
	    			toolbarAsignaturas.setItemText('new',"<bean:message key="button.create.asignatura"/>");
	    			toolbarAsignaturas.setItemText('delete',"<bean:message key="button.eliminar.asignatura"/>");
	    			toolbarAsignaturas.setItemText('exportExcel',"<bean:message key="button.exportar.excel"/>");
	    			toolbarAsignaturas.setItemText('exportPDF',"<bean:message key="button.exportar.pdf"/>");
	    			toolbarAsignaturas.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
	    		//permisosToolbarAsignaturas();
		    });
			    
			    
			    gridAsignaturas = listado.attachGrid();
			    gridAsignaturas.setIconsPath('../skins/imgs/');
		   		gridAsignaturas.setHeader(["<strong><bean:message key="label.nombre.asignatura" /></strong>"
		   		                      ,"<strong><bean:message key="label.codigo.asignatura" /></strong>",
		   		                      "<strong><bean:message key="label.curso.asignatura" /></strong>"]);
				
			    //ro = readonly
			    //nombre codigo curso descripcion
			    gridAsignaturas.setColTypes("ro,ro,ro");
			    gridAsignaturas.setColSorting("str,str,str");
			    gridAsignaturas.enableMultiselect(false);
			    gridAsignaturas.init();
			    gridAsignaturasProcessor = new dataProcessor("gridasignaturas.do");
			    gridAsignaturasProcessor.enableUTFencoding('simple');
			    gridAsignaturasProcessor.init(gridAsignaturas);	  
			    gridAsignaturasProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
			    
			    gridAsignaturas.attachEvent("onRowSelect", doOnRowSelected);
			    buscar();
		    });
		    
		    
		    function doOnRowSelected(rowID,celInd){
		    	toolbarAsignaturas.enableItem('delete');

			    tabbarAsignaturas = areaTrabajoCursos.attachTabbar();
			    tabbarAsignaturas.addTab('tabInfo','<bean:message key="label.info.general"/>','');
		    	tabInfo = tabbarAsignaturas.cells('tabInfo');
		    	tabbarAsignaturas.setTabActive('tabInfo');
		    	formInfo = tabInfo.attachForm();
		    	loadFormAsignatura();
		    	// obtener el nombre del curso de la bbdd y añadirlo como header a la dcha
				areaTrabajoCursos.setText(areaTrabajoCursos.getText() + "");
		    }
		    
		    function loadFormAsignatura(){
		    	formInfo.loadStruct('../xml/forms/asignatura_informacion_form.xml', function(){
	    			formInfo.setItemLabel('data','<bean:message key="title.info.general.asignatura"/>');
	    			formInfo.setItemLabel('nombre','<bean:message key="label.nombre.asignatura"/>');
	    			formInfo.setItemLabel('codigo','<bean:message key="label.codigo.asignatura"/>');
	    			formInfo.setItemLabel('curso','<bean:message key="label.curso.asignatura"/>');
	    			formInfo.setItemLabel('descripcion','<bean:message key="label.descripcion.asignatura"/>');
	    			//permisosAsignaturasForm();			    		
		    		
	    			formInfo.load('editarasignatura.do?idAsignatura=' + 1, function () {			    			
	    				formInfo.attachEvent("onButtonClick", function(id){
		    				if (id == "aceptar") {
		    					formInfo.send("actualizarasignatura.do?!nativeeditor_status=save&idAsignatura=" + idAsignatura ,"post", function(xml) {
			    					
			    				});
			    				buscar();
		    				}
		    			});
		    		});
	    		});
		    }
		    
		    function buscar() {
		    	gridAsignaturas.clearAndLoad("gridasignaturas.do");		    	
		    }
		    
			function permisosToolbarAsignaturas(){ 	
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >	    	
			   		toolbarAsignaturas.hideItem('new');
			   		toolbarAsignaturas.hideItem('sep1');    	
			   		toolbarAsignaturas.hideItem('delete');
			   		toolbarAsignaturas.hideItem('sep2');
				</logic:notMatch>
	    	}
	    	
	    	function permisosAsignaturasForm(){
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
		    	gridAsignaturas.toExcel('generateExcel.do');
		    }
		  
        </script>
	</head>
	<body>
	</body>
</html>