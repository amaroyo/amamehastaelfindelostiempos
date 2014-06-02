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
	    	gridAsignaturas, formInfo, dhxWins;
	    	
	    	var windowsNewAsignatura = new Array();
	    	var formsNewAsignatura = new Array();
	    	
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
		    
		    
		    function newAsignatura() {
		    	dhxWins= new dhtmlXWindows();
		    	windowsNewAsignatura[0] = dhxWins.createWindow("nuevaAsignatura", 300, 50, 400, 215);
		    	var windowNewAsignatura = windowsNewAsignatura[0];
		    	windowNewAsignatura.setText('<bean:message key="title.crear.nueva.asignatura" />');
		    	windowNewAsignatura.centerOnScreen();
				formsNewAsignatura[0] = windowsNewAsignatura[0].attachForm();
				var formNewAsignatura = formsNewAsignatura[0];
				formNewAsignatura.loadStruct('../xml/forms/new_asignatura_common_form.xml', function() {
					formNewAsignatura.setItemLabel('data','<bean:message key="title.info.general.asignatura"/>');
					formNewAsignatura.setItemLabel('numeroRubricas','<bean:message key="label.numero.rubricas"/>');
					formNewAsignatura.setItemLabel('codigo','<bean:message key="label.codigo.asignatura"/>');
					formNewAsignatura.setItemLabel('curso','<bean:message key="label.curso.asignatura"/>');
					formNewAsignatura.setItemLabel('siguiente','<bean:message key="button.siguiente"/>');
		    		

		    		/*formNewAsignatura.forEachItem(function(id){
		    			if(formNewAsignatura.getItemType(id) == "input"){
		    				formNewAsignatura.setRequired(id,true);
		    			}
		    		});*/
		    		
					//permisosNuevaAsignaturaCommonForm();		  
		    		
		    		formNewAsignatura.attachEvent("onButtonClick", function(id){
	    				if (id == "siguiente") {
	    					windowNewAsignatura.hide();
	    					crearPartes(1);	
	    				}
		    		});
		    	});
		    }
		    
		    function crearPartes(currentPart){
		    	windowsNewAsignatura[currentPart] = dhxWins.createWindow("nuevaParte"+currentPart, 300, 50, 405, 440);
		    	var windowNewAsignaturaPart = windowsNewAsignatura[currentPart];
		    	windowNewAsignaturaPart.setText('<bean:message key="title.crear.nueva.asignatura" />'+': ' + '<bean:message key="title.parte.nueva.asignatura" />' +currentPart);				
		    	windowNewAsignaturaPart.centerOnScreen();
				formsNewAsignatura[currentPart] = windowsNewAsignatura[currentPart].attachForm();
				var formNewAsignaturaPart = formsNewAsignatura[currentPart];
				var numeroRubricas = formsNewAsignatura[0].getItemValue("numeroRubricas");
				formNewAsignaturaPart.loadStruct('../xml/forms/new_asignatura_part_form.xml', function() {
					formNewAsignaturaPart.setItemLabel('data','<bean:message key="title.info.general.asignatura"/>');
					formNewAsignaturaPart.setItemLabel('numeroRubricas','<bean:message key="label.numero.rubricas"/>');
					formNewAsignaturaPart.setItemLabel('parte','<bean:message key="label.parte"/>');
					formNewAsignaturaPart.setItemLabel('codigo','<bean:message key="label.codigo.asignatura"/>');
					formNewAsignaturaPart.setItemLabel('curso','<bean:message key="label.curso.asignatura"/>');
					formNewAsignaturaPart.setItemLabel('nombre','<bean:message key="label.nombre.asignatura"/>');
					formNewAsignaturaPart.setItemLabel('descripcion','<bean:message key="label.descripcion.asignatura"/>');
					formNewAsignaturaPart.setItemLabel('anterior','<bean:message key="button.anterior"/>');	  
					formNewAsignaturaPart.setItemLabel('siguiente','<bean:message key="button.siguiente"/>');	  
		    		
					formNewAsignaturaPart.forEachItem(function(id){
						//formsNewAsignatura[currentPart].setRequired(id,true);
						if(id == "numeroRubricas" || id == "parte" || id == "codigo" || id == "curso") {
							switch(id) {
								case "numeroRubricas":
								case "codigo":
				    			case "curso":
				    				var valueCopied = formsNewAsignatura[0].getItemValue(id);
				    				formNewAsignaturaPart.setItemValue(id,valueCopied);
				    				break;
				    			case "parte":
				    				formNewAsignaturaPart.setItemValue(id,currentPart+" / "+numeroRubricas);
				    				break;
							}
							formNewAsignaturaPart.disableItem(id);
						}
		    		});
		    		
					formNewAsignaturaPart.attachEvent("onButtonClick", function(id){
	    				if (id == "siguiente") {
	    					if(!existeEnNombresAnteriores(numeroRubricas)){
		    					formNewAsignaturaPart.send("buscarasignatura.do","post", function(loader,response) {
		    						resultadoBuscarParteAsignatura(response,currentPart,numeroRubricas);
			    				});
	    					}
	    				}
	    				else if (id == "anterior") {
	    					windowsNewAsignatura[currentPart].hide();
	    					windowsNewAsignatura[currentPart-1].show();
	    				}
		    		});
		    	});
			}
			
		    function existeEnNombresAnteriores(numeroRubricas){
		    	var length = formsNewAsignatura.length;
		    	for(var i=length-2;i>0;i--) {
		    		if(formsNewAsignatura[length-1].getItemValue('nombre').toLowerCase() == formsNewAsignatura[i].getItemValue('nombre').toLowerCase()){
		    			if(numeroRubricas == 1) {
			    			alert('<bean:message key="message.asignatura.no.creada" />:'+"\n"+
								"Nombre: "+formsNewAsignatura[i].getItemValue('nombre')+"\n"+
								"Código: "+formsNewAsignatura[i].getItemValue('codigo')+"\n"+
								"Curso: "+formsNewAsignatura[i].getItemValue('curso'));
		    			}
			    		else{
			    			alert('<bean:message key="message.parte.asignatura.no.creada" />:'+"\n"+
		    						"Parte: "+formsNewAsignatura[i].getItemValue('parte')+"\n"+	
	    							"Nombre: "+formsNewAsignatura[i].getItemValue('nombre')+"\n"+
	    							"Código: "+formsNewAsignatura[i].getItemValue('codigo')+"\n"+
	    							"Curso: "+formsNewAsignatura[i].getItemValue('curso'));
			    		}
		    			return true;
		    		}
		    	}
		    	return false;
		    }
		    
		    function resultadoBuscarParteAsignatura(response,currentPart,numeroRubricas){
		    	if(response == "No existe ninguna asignatura con ese nombre") {
		    		if(numeroRubricas == 1 || currentPart == numeroRubricas) {
		    			crearAsignaturaCompleta(numeroRubricas);
		    			cerrarVentanas();
		    		}
		    		else{
	    				windowsNewAsignatura[currentPart].hide();
			    		crearPartes(currentPart+1);
		    		}
		    	}
		    	else/* if(response == "Ya existe una asignatura con ese nombre:")*/{
		    		if(numeroRubricas == 1) {
		    			alert(response.replace("Ya existe una asignatura con ese nombre", '<bean:message key="message.asignatura.no.creada" />'));
		    		}
		    		else{
		    			alert(response.replace("Ya existe una asignatura con ese nombre", '<bean:message key="message.parte.asignatura.no.creada" />'));
		    		}
		    	}
		    }
		    
		    function crearAsignaturaCompleta(numeroRubricas){
		    	var error=false;
		    	for(var i=1;i<formsNewAsignatura.length;i++) {
		    		formsNewAsignatura[i].send("crearasignatura.do","post", function(loader,response) {
		    			if(response == "asignatura no creada: ya existe una asignatura con ese nombre"){
		    				error=true;
		    				if(numeroRubricas == 1) {
		    					alert('<bean:message key="message.error.crear.asignatura" />:'+"\n"+
		    							"Nombre: "+formsNewAsignatura[i].getItemValue('nombre')+"\n"+
		    							"Código: "+formsNewAsignatura[i].getItemValue('codigo')+"\n"+
		    							"Curso: "+formsNewAsignatura[i].getItemValue('curso'));
		    				}
		    				else {
		    					alert('<bean:message key="message.error.crear.parte.asignatura" />:'+"\n"+
		    						"Parte: "+formsNewAsignatura[i].getItemValue('parte')+"\n"+	
	    							"Nombre: "+formsNewAsignatura[i].getItemValue('nombre')+"\n"+
	    							"Código: "+formsNewAsignatura[i].getItemValue('codigo')+"\n"+
	    							"Curso: "+formsNewAsignatura[i].getItemValue('curso'));
		    				}
		    			}
					});
		    	}
		    	if(!error){
	    			successfulCrearAsignatura();
		    	}
		    }
		    
		    function cerrarVentanas(){
		    	for(var i=1;i<windowsNewAsignatura.length;i++)
	    			windowsNewAsignatura[i].close();
		    }
	    	
	    	function successfulCrearAsignatura() {
	    		alert('<bean:message key="message.asignatura.creada.exito" />');
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
		    
		    function Mayor1(n){
		        if(n==""){
		        	return false;
		        }
		        else
		        	return n>=1;
			}
			
			function goActualizar() {
				buscar();
				toolbarAsignaturas.disableItem('delete');
				areaTrabajoCursos.detachObject(true);
		    }
			
			function buscar() {
		    	gridAsignaturas.clearAndLoad("gridasignaturas.do");		    	
		    }
			
        </script>
	</head>
	<body>
	</body>
</html>