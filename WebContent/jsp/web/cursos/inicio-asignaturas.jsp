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
	    <script type="text/javascript" src="../skins/dhtmlxform_dyn.js"></script>
	     <script type="text/javascript" src="../skins/dhtmlxwindows.js"></script>
	    

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../skins/imgs/';
	    	
	    	var main_layout, areaTrabajoCursos, listado, toolbarAsignaturas,
	    	gridAsignaturas, formInfo, formNuevaAsignatura, dhxWins;
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	dhtmlxError.catchError("ALL",errorHandler);
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    listado = main_layout.cells('a');
			    listado.setWidth(750);
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
		   		gridAsignaturas.setInitWidths("*,150,150");
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
		    	formInfo = areaTrabajoCursos.attachForm();
		    	loadFormAsignatura(rowID);
		    	// obtener el nombre del curso de la bbdd y añadirlo como header a la dcha
				areaTrabajoCursos.setText(areaTrabajoCursos.getText() + "");
		    }
		    
		    function loadFormAsignatura(idAsignatura){
		    	formInfo.loadStruct('../xml/forms/asignatura_informacion_form.xml', function(){
	    			formInfo.setItemLabel('data','<bean:message key="title.info.general.asignatura"/>');
	    			formInfo.setItemLabel('nombre','<bean:message key="label.nombre.asignatura"/>');
	    			formInfo.setItemLabel('codigo','<bean:message key="label.codigo.asignatura"/>');
	    			formInfo.setItemLabel('curso','<bean:message key="label.curso.asignatura"/>');
	    			formInfo.setItemLabel('descripcion','<bean:message key="label.descripcion.asignatura"/>');
	    			//permisosAsignaturasForm();			    		
		    		
	    			formInfo.load('editarasignatura.do?idAsignatura=' + idAsignatura, function () {			    			
	    				formInfo.attachEvent("onButtonClick", function(id){
		    				if (id == "aceptar") {
		    					formInfo.send("actualizarasignatura.do?!nativeeditor_status=save&idAsignatura=" + idAsignatura ,"post", function(xml) {
									alert('<bean:message key="message.asignatura.cambiada.exito"/>');
			    				});
			    				buscar();
		    				}
		    			});
	    				formInfo.attachEvent("onEnter", function() {
							formInfo.send("actualizarasignatura.do?!nativeeditor_status=save&idAsignatura=" + idAsignatura ,"post", function(xml) {
								alert('<bean:message key="message.asignatura.cambiada.exito"/>');
							}); 
			    		});
		    		});
	    		});
		    }
		    
		    function buscar() {
		    	gridAsignaturas.clearAndLoad("gridasignaturas.do");		    	
		    }
		    
		    
		    function newAsignatura() {
		    	dhxWins= new dhtmlXWindows();
				var windowNewAsignatura = dhxWins.createWindow("nuevaAsignatura", 300, 50, 400, 230);
				windowNewAsignatura.setText('<bean:message key="title.crear.nueva.asignatura" />');				
				windowNewAsignatura.setModal(true);
				windowNewAsignatura.centerOnScreen();
				formNuevaAsignatura = windowNewAsignatura.attachForm();			
		    	formNuevaAsignatura.loadStruct('../xml/forms/new_asignatura_common_form.xml', function() {
		    		formNuevaAsignatura.setItemLabel('data','<bean:message key="title.info.general.asignatura"/>');
		    		formNuevaAsignatura.setItemLabel('numeroRubricas','<bean:message key="label.numero.rubricas"/>');
		    		formNuevaAsignatura.setItemLabel('codigo','<bean:message key="label.codigo.asignatura"/>');
		    		formNuevaAsignatura.setItemLabel('curso','<bean:message key="label.curso.asignatura"/>');
		    		formNuevaAsignatura.setItemLabel('siguiente','<bean:message key="button.siguiente"/>');
		    		

		    		/*formNuevaAsignatura.forEachItem(function(id){
		    			if(formNuevaAsignatura.getItemType(id) == "input"){
		    				formNuevaAsignatura.setRequired(id,true);
		    			}
		    		});*/
		    		
					//permisosNuevaAsignaturaCommonForm();		  
		    		
		    		formNuevaAsignatura.attachEvent("onButtonClick", function(id){
	    				if (id == "siguiente") {
	    					createParts();
	    					
	    				}
		    		});
		    		formNuevaAsignatura.attachEvent("onEnter", function() {
		    			createParts();
		    		});
		    	});
		    }
		    
		    function createParts(){
		    	var numeroRubricas = formNuevaAsignatura.getItemValue("numeroRubricas");
				for(var i=numeroRubricas;i>=1;i--){
					newPart(i);
				}
		    }
		    
		    function newPart(currentPart){
				var window = dhxWins.createWindow("nuevaParte"+currentPart, 300, 50, 385, 510);
				window.setText('<bean:message key="title.crear.nueva.asignatura" />'+': ' + '<bean:message key="title.parte.nueva.asignatura" />' +currentPart);				
				window.centerOnScreen();
				var formAsignaturaParte = window.attachForm();
	    		dhxWins.window("nuevaParte"+currentPart).setModal(true);
				formAsignaturaParte.loadStruct('../xml/forms/new_asignatura_part_form.xml', function() {
					formAsignaturaParte.setItemLabel('data','<bean:message key="title.info.general.asignatura"/>');
					formAsignaturaParte.setItemLabel('numeroRubricas','<bean:message key="label.numero.rubricas"/>');
					formAsignaturaParte.setItemLabel('parte','<bean:message key="label.parte"/>');
					formAsignaturaParte.setItemLabel('codigo','<bean:message key="label.codigo.asignatura"/>');
					formAsignaturaParte.setItemLabel('curso','<bean:message key="label.curso.asignatura"/>');
					formAsignaturaParte.setItemLabel('nombre','<bean:message key="label.nombre.asignatura"/>');
					formAsignaturaParte.setItemLabel('descripcion','<bean:message key="label.descripcion.asignatura"/>');
					formAsignaturaParte.setItemLabel('siguiente','<bean:message key="button.siguiente"/>');	  
		    		
					formAsignaturaParte.forEachItem(function(id){
						//formAsignaturaParte.setRequired(id,true);
						if(id == "numeroRubricas" || id == "parte" || id == "codigo" || id == "curso") {
							switch(id) {
								case "numeroRubricas":
								case "codigo":
				    			case "curso":
				    				var valueCopied = formNuevaAsignatura.getItemValue(id);
				    				formAsignaturaParte.setItemValue(id,valueCopied);
				    				break;
				    			case "parte":
				    				var totalParts = formAsignaturaParte.getItemValue("numeroRubricas");
				    				formAsignaturaParte.setItemValue(id,currentPart+"/"+totalParts);
				    				break;
							}
							formAsignaturaParte.disableItem(id);
						}
		    		});
		    		
					formAsignaturaParte.attachEvent("onButtonClick", function(id){
	    				if (id == "siguiente") {
	    					formAsignaturaParte.send("nuevaasignatura.do?","post", function(loader,response) {
	    						resultadoCreateAsignatura(response);
		    				});
		    				window.close();
		    				goActualizar();
	    				}
		    		});
		    	});
			}
		    
			function newRubrica(i){
				var window = dhxWins.createWindow("nuevaRubrica"+i, 300, 50, 385, 510);
				window.setText('<bean:message key="title.crear.nueva.asignatura" />');				
				window.setModal(true);
				window.centerOnScreen();
				var formNuevaAsignatura = window.attachForm();			
		    	formNuevaAsignatura.loadStruct('../xml/forms/new_asignatura_form.xml', function() {
		    		formNuevaAsignatura.setItemLabel('data','<bean:message key="title.info.general.asignatura"/>');
		    		formNuevaAsignatura.setItemLabel('nombre','<bean:message key="label.associated.to"/>');
		    		formNuevaAsignatura.setItemLabel('descripcion','<bean:message key="label.nombre"/>');
		    		formNuevaAsignatura.setItemLabel('siguiente','<bean:message key="button.aceptar"/>');	  
		    		
		    		formNuevaAsignatura.forEachItem(function(id){
		    			form.setRequired(id,true);
		    		});
		    		
		    		formNuevaAsignatura.attachEvent("onButtonClick", function(id){
	    				if (id == "siguiente") {	
	    					
		    				formNuevaAsignatura.send("actualizarusuario.do?!nativeeditor_status=create&idAsociado="+idAsociado,"post", function(xml) {
		    					
		    				});
		    				window.close();
		    				goActualizar();
	    				}
		    		});
		    	});
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
			
			function goActualizar() {
		    	buscar();
		    	toolbarAsignaturas.disableItem('delete');
		    	areaTrabajoCursos.clearAll();  	
		    }
		    
		    function exportExcel(){
		    	gridAsignaturas.toExcel('generateExcel.do');
		    }
		    
		    function Mayor1(n){
		        if(n==""){
		        	return false;
		        }
		        else
		        	return n>=1;
			}
		  
		    function resultadoCreateAsignatura(response){
		    	alert("resultado");
		    }
		    
		    function failedCreateAsignatura() {
	    		alert('fail');
	    	}
	    	
	    	function successfulCreateAsignatura() {
	    		alert('<bean:message key="message.asignatura.creada.exito" />');
	    	}
        </script>
	</head>
	<body>
	</body>
</html>