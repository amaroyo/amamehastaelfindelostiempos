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
	    
	    	dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	
	    	var main_layout, areaTrabajoCursos, listado, toolbarAsignaturas, numeroRubricas,
	    	gridAsignaturas, formInfo, formRubrica, formNewRubrica, formAnexo, dhxWins, tabbar, tab_info, tab_rubrica, tab_anexo,
	    	grupos_criterios_rubrica, grupos_anexo_rubrica;
	    	var num_criterios_grupo=new Array();
	    	
	    	var windowsNewAsignatura = new Array();
	    	var formsNewAsignatura = new Array();
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	dhtmlxError.catchError("ALL",errorHandler);
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    listado = main_layout.cells('a');
			    listado.setWidth(500);
			    areaTrabajoCursos = main_layout.cells('b');
			    
			    //autosize(horizontal,vertical)
			    //"a;b" 'a' and 'b' will autosize when changing horizontal dimensions of layout
			    //listado.setAutoSize("a;b",null)
			    
		    	listado.setText("<strong><bean:message key="title.asignaturas" /></strong>");
		    	areaTrabajoCursos.setText("<bean:message key="title.propiedades.asignatura" />");
			    	
			    toolbarAsignaturas = listado.attachToolbar();
			    toolbarAsignaturas.setIconsPath('../js/dhtmlxSuite/imgs/toolbar/');
			    toolbarAsignaturas.loadXML('../xml/toolbars/dhxtoolbar-asignaturas.xml', function(){
	    			toolbarAsignaturas.setItemText('new',"<bean:message key="button.create.asignatura"/>");
	    			toolbarAsignaturas.setItemText('delete',"<bean:message key="button.eliminar.asignatura"/>");
	    			toolbarAsignaturas.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
	    		//permisosToolbarAsignaturas();
		    	});
			    
			    
			    gridAsignaturas = listado.attachGrid();
			    gridAsignaturas.setIconsPath('../skins/imgs/');
		   		gridAsignaturas.setHeader(["<strong><bean:message key="label.nombre.asignatura" /></strong>"
		   		                      ,"<strong><bean:message key="label.codigo.asignatura" /></strong>",
		   		                      "<strong><bean:message key="label.curso.asignatura" /></strong>"]);
		   		gridAsignaturas.setInitWidths("*,60,50");
		   		gridAsignaturas.setColAlign("left,center,center");
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
		    	//toolbarAsignaturas.enableItem('delete');
		    	tabbar = areaTrabajoCursos.attachTabbar();
		    	
		    	tabbar.addTab('formAsignatura','<bean:message key="title.info.general"/>');
		    	tab_info = tabbar.cells('formAsignatura');
		    	tabbar.setTabActive('formAsignatura');
		    	loadFormAsignatura(rowID);
		    	
		    	tabbar.addTab('rubricaAsignatura','<bean:message key="title.rubrica"/>');
		    	tab_rubrica = tabbar.cells('rubricaAsignatura');
		    	loadFormRubrica(rowID);
		    	
		    	tabbar.addTab('anexoAsignatura','<bean:message key="title.anexo"/>');
		    	tab_anexo = tabbar.cells('anexoAsignatura');
		    	loadFormAnexo(rowID);
		    	
				areaTrabajoCursos.setText(areaTrabajoCursos.getText());
				areaTrabajoCursos.showHeader();
		    }
		    
		    
		    function loadFormRubrica(idAsignatura){

		    	formRubrica = tab_rubrica.attachForm();
		    	formRubrica.loadStruct('../xml/forms/rubrica_form.xml', function(){
	    			formRubrica.setItemLabel('resultados','<bean:message key="title.resultados.competencias"/>');
	    			formRubrica.setItemValue('competencias',dameCompetenciasAsignatura(idAsignatura));

	    			grupos_criterios_rubrica = dameGruposCriteriosAsignatura(idAsignatura);
	    			for(var i=0;i<grupos_criterios_rubrica.length;i++){
	    				formRubrica.addItem(null, grupos_criterios_rubrica[i], i+1);
	    			}
	    			
	    			//permisosRubricasForm();	
	    			/*formRubrica.load('competenciasasignatura.do?idAsignatura=' + idAsignatura, function () {			    			
	    				/*formRubrica.attachEvent("onButtonClick", function(id){
		    				if (id == "aceptar") {
		    					formRubrica.send("actualizarasignatura.do?!nativeeditor_status=save&idAsignatura=" + idAsignatura ,"post", function(xml) {
									alert('<bean:message key="message.asignatura.cambiada.exito"/>');
			    				});
			    				buscar();
		    				}
		    			});
	    				formRubrica.attachEvent("onEnter", function() {
							formRubrica.send("actualizarasignatura.do?!nativeeditor_status=save&idAsignatura=" + idAsignatura ,"post", function(xml) {
								alert('<bean:message key="message.asignatura.cambiada.exito"/>');
							}); 
			    		});
		    		});*/
	    		});
		    }
		    
		    function loadFormAnexo(idAsignatura){

		    	formAnexo = tab_anexo.attachForm();
		    	formAnexo.loadStruct('../xml/forms/anexo_form.xml', function(){
	    			formAnexo.setItemLabel('anexo',dameAnexoAsignatura(idAsignatura));
	    			grupos_anexo_rubrica = dameGruposAnexoAsignatura(idAsignatura);
	    			for(var i=0;i<grupos_anexo_rubrica.length;i++){
	    				formAnexo.addItem("anexo", grupos_anexo_rubrica[i], i);
	    			}
	    			
	    			//permisosRubricasForm();	
	    			/*formRubrica.load('anexoasignatura.do?idAsignatura=' + idAsignatura, function () {			    			
	    				/*formRubrica.attachEvent("onButtonClick", function(id){
		    				if (id == "aceptar") {
		    					formRubrica.send("actualizarasignatura.do?!nativeeditor_status=save&idAsignatura=" + idAsignatura ,"post", function(xml) {
									alert('<bean:message key="message.asignatura.cambiada.exito"/>');
			    				});
			    				buscar();
		    				}
		    			});
	    				formRubrica.attachEvent("onEnter", function() {
							formRubrica.send("actualizarasignatura.do?!nativeeditor_status=save&idAsignatura=" + idAsignatura ,"post", function(xml) {
								alert('<bean:message key="message.asignatura.cambiada.exito"/>');
							}); 
			    		});
		    		});*/
	    		});
		    }
		    
		    function loadFormAsignatura(idAsignatura){
		    	formInfo = tab_info.attachForm();
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
		    
		    function deleteAsignatura() {
		    	
		    }
		    
		    function newAsignatura() {
		    	dhxWins= new dhtmlXWindows();
		    	windowsNewAsignatura = new Array();
		    	windowsNewAsignatura[0] = dhxWins.createWindow("nuevaAsignatura", 300, 50, 350, 205);
		    	var windowNewAsignatura = windowsNewAsignatura[0];
		    	windowNewAsignatura.setText('<bean:message key="title.crear.nueva.asignatura" />');
		    	windowNewAsignatura.centerOnScreen();
				windowNewAsignatura.setModal(true);
				formsNewAsignatura = new Array();
				formsNewAsignatura[0] = windowNewAsignatura.attachForm();
				var formNewAsignatura = formsNewAsignatura[0];
				formNewAsignatura.loadStruct('../xml/forms/new_asignatura_common_form.xml', function() {
					formNewAsignatura.setItemLabel('data','<bean:message key="title.info.general.asignatura"/>');
					formNewAsignatura.setItemLabel('numeroRubricas','<bean:message key="label.numero.rubricas"/>');
					formNewAsignatura.setItemLabel('codigo','<bean:message key="label.codigo.asignatura"/>');
					formNewAsignatura.setItemLabel('curso','<bean:message key="label.curso.asignatura"/>');
					formNewAsignatura.setItemLabel('siguiente','<bean:message key="button.siguiente"/>');
		    		
					formNewAsignatura.forEachItem(function(id){
						if(formNewAsignatura.getItemType(id) == "input"){
							formNewAsignatura.setRequired(id,true);
						}
		    		});
		    		
		    		formNewAsignatura.setFocusOnFirstActive();
					//permisosNuevaAsignaturaCommonForm();		  
		    		
		    		formNewAsignatura.attachEvent("onButtonClick", function(id){
	    				if (id == "siguiente") {
	    					windowNewAsignatura.setModal(false);
	    					windowNewAsignatura.hide();
	    					crearPartes(1);	
	    				}
		    		});
		    	});
		    }
		    
		    function crearPartes(currentPart){
		    	windowsNewAsignatura[currentPart] = dhxWins.createWindow("nuevaParte"+currentPart, 300, 50, 405, 415);
		    	var windowNewAsignaturaPart = windowsNewAsignatura[currentPart];
		    	windowNewAsignaturaPart.setText('<bean:message key="title.crear.nueva.asignatura" />'+': ' + '<bean:message key="title.parte.nueva.asignatura" /> ' +currentPart);				
		    	windowNewAsignaturaPart.centerOnScreen();
		    	windowNewAsignaturaPart.setModal(true);
				formsNewAsignatura[currentPart] = windowNewAsignaturaPart.attachForm();
				var formNewAsignaturaPart = formsNewAsignatura[currentPart];
				numeroRubricas = parseInt(formsNewAsignatura[0].getItemValue("numeroRubricas"));
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
						if(formNewAsignaturaPart.getItemType(id) == "input"){
							formNewAsignaturaPart.setRequired(id,true);
						}
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
		    		});
		    		
					formNewAsignaturaPart.setFocusOnFirstActive();
					
					formNewAsignaturaPart.attachEvent("onButtonClick", function(id){
	    				if (id == "siguiente") {
	    					if(!existeEnNombresAnteriores(currentPart)){
		    					formNewAsignaturaPart.send("buscarasignatura.do","post", function(loader,response) {
		    						resultadoBuscarParteAsignatura(response,currentPart);
			    				});
	    					}
	    				}
	    				else if (id == "anterior") {
	    					windowsNewAsignatura[currentPart].setModal(false);
	    					windowsNewAsignatura[currentPart].hide();
	    					windowsNewAsignatura[currentPart-1].setModal(true);
	    					windowsNewAsignatura[currentPart-1].show();
	    				}
		    		});
		    	});
			}
			
		    function existeEnNombresAnteriores(currentPart){
		    	for(var i=currentPart-1;i>0;i--) {
		    		if(formsNewAsignatura[currentPart].getItemValue('nombre').toLowerCase() == formsNewAsignatura[i].getItemValue('nombre').toLowerCase()){
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
		    
		    function resultadoBuscarParteAsignatura(response,currentPart){
		    	if(response == "No existe ninguna asignatura con ese nombre") {
		    		if(currentPart == numeroRubricas) {
		    			crearRubricas(1);
		    		}
		    		else{
	    				windowsNewAsignatura[currentPart].hide();
	    				windowsNewAsignatura[currentPart].setModal(false);
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
		    
		    function crearAsignaturaCompleta(){
		    	var error=false;
		    	for(var i=1;i<=numeroRubricas;i++) {
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
		    
		    
		    
		    function crearRubricasAsignaturaCompleta(){
		    	for(var i=numeroRubricas+1;i<=numeroRubricas*2;i++) {
		    		formsNewAsignatura[i].send("crearrubricaasignatura.do","post", function(loader,response) {
		    			
					});
		    	}
		    }
		    
		    
		    
		    function crearRubricas(currentPart){
		    	windowsNewAsignatura[numeroRubricas+currentPart] = dhxWins.createWindow("gruposRubrica"+currentPart, 300, 50, 1200, 490);
		    	var windowNewRubrica = windowsNewAsignatura[numeroRubricas+currentPart];
		    	windowNewRubrica.setText('<bean:message key="title.crear.nueva.asignatura" />'+': '+'<bean:message key="title.parte.nueva.asignatura" /> ' +currentPart+' '+'<bean:message key="title.crear.nueva.asignatura.grupos.rubrica" />');				
		    	windowNewRubrica.centerOnScreen();
		    	windowNewRubrica.setModal(true);
				formsNewAsignatura[numeroRubricas+currentPart] = windowsNewAsignatura[numeroRubricas+currentPart].attachForm();
				formNewRubrica = formsNewAsignatura[numeroRubricas+currentPart];
				formNewRubrica.loadStruct('../xml/forms/new_grupos_rubrica_form.xml', function() {
					formNewRubrica.setItemLabel('data','<bean:message key="title.grupos.criterios.rubrica"/>');
					formNewRubrica.setItemLabel('parte','<bean:message key="label.parte"/>');
					formNewRubrica.setItemLabel('codigo','<bean:message key="label.codigo.asignatura"/>');
					formNewRubrica.setItemLabel('curso','<bean:message key="label.curso.asignatura"/>');
					formNewRubrica.setItemLabel('nombre','<bean:message key="label.nombre.asignatura.rubrica"/>');
					formNewRubrica.setItemLabel('labelCompetencias','<bean:message key="label.competencias"/>');
					formNewRubrica.setItemLabel('labelAnexo1','<bean:message key="label.anexo"/>');
					formNewRubrica.setItemLabel('labelNota','<bean:message key="label.criterios.nota"/>');
					formNewRubrica.setItemLabel('value(nota_grupo_1)','<strong><bean:message key="label.nombre.grupo.criterios"/>'+' '+'1</strong>');
					formNewRubrica.setItemLabel('value(nota_criterio_1_1)','<bean:message key="label.criterio"/>'+' '+'1');
					formNewRubrica.setItemLabel('nota_boton_nuevo_criterio_1','<bean:message key="button.add.nuevo.criterio"/>');
					formNewRubrica.setItemLabel('nota_boton_borrar_criterio_1','<bean:message key="button.delete.nuevo.criterio"/>');	  
					formNewRubrica.setItemLabel('nota_boton_nuevo_grupo_1','<strong><bean:message key="button.add.nuevo.grupo"/></strong>');	  
					formNewRubrica.setItemLabel('nota_boton_borrar_grupo_1','<strong><bean:message key="button.delete.nuevo.grupo"/></strong>');	  
					formNewRubrica.setItemLabel('labelTexto','<bean:message key="label.criterios.texto"/>');
					formNewRubrica.setItemLabel('value(texto_grupo_1)','<strong><bean:message key="label.nombre.grupo.criterios"/>'+' '+'1</strong>');
					formNewRubrica.setItemLabel('value(texto_criterio_1_1)','<bean:message key="label.criterio"/>'+' '+'1');
					formNewRubrica.setItemLabel('texto_boton_nuevo_criterio_1','<bean:message key="button.add.nuevo.criterio"/>');	  
					formNewRubrica.setItemLabel('texto_boton_nuevo_grupo_1','<strong><bean:message key="button.add.nuevo.grupo"/></strong>');	  
					formNewRubrica.setItemLabel('texto_boton_borrar_grupo_1','<strong><bean:message key="button.delete.nuevo.grupo"/></strong>');	  
					formNewRubrica.setItemLabel('anterior','<bean:message key="button.anterior"/>');	  
					formNewRubrica.setItemLabel('siguiente','<bean:message key="button.siguiente"/>');	
					
					
					formNewRubrica.forEachItem(function(id){
						if(formNewRubrica.getItemType(id) == "input"){
							formNewRubrica.setRequired(id,true);
						}
						switch(id) {
							case "codigo":
			    			case "curso":
			    				var valueCopied = formsNewAsignatura[0].getItemValue(id);
			    				formNewRubrica.setItemValue(id,valueCopied);
			    				break;
			    			case "parte":
			    				formNewRubrica.setItemValue(id,currentPart+" / "+numeroRubricas);
			    				break;
			    			case "nombre":
			    				var valueCopied = formsNewAsignatura[currentPart].getItemValue(id);
			    				formNewRubrica.setItemValue(id,valueCopied);
			    				break;
						}
		    		});
					
					formNewRubrica.setFocusOnFirstActive();
		
					num_criterios_grupo = new Array(2);
					num_criterios_grupo[1] = new Array();
					num_criterios_grupo[2] = new Array();
					num_criterios_grupo[1][1] = 2;
					num_criterios_grupo[2][1] = 2;

	    			formNewRubrica.attachEvent("onButtonClick", function(id){
	    				if(id.indexOf("nota_boton_nuevo_criterio") != -1){
	    					botonNuevoCriterio(id,"nota",1);
	    				}
	    				else if(id.indexOf("nota_boton_borrar_criterio") != -1){
	    					botonBorrarCriterio(id,"nota",1);
	    				}
	    				else if(id.indexOf("nota_boton_nuevo_grupo") != -1){
	    					botonNuevoGrupo(id,"nota",1);
						}
						else if(id.indexOf("nota_boton_borrar_grupo") != -1){
	    					botonBorrarGrupo(id,"nota",1);
						}
						else if(id.indexOf("texto_boton_nuevo_criterio") != -1){
	    					botonNuevoCriterio(id,"texto",2);
	    				}
	    				else if(id.indexOf("texto_boton_borrar_criterio") != -1){
	    					botonBorrarCriterio(id,"texto",2);
	    				}
	    				else if(id.indexOf("texto_boton_nuevo_grupo") != -1){
	    					botonNuevoGrupo(id,"texto",2);
						}
						else if(id.indexOf("texto_boton_borrar_grupo") != -1){
	    					botonBorrarGrupo(id,"texto",2);
						}
							
						else if (id == "siguiente") {
	    					if(currentPart == numeroRubricas){
	    						crearAsignaturaCompleta();
	    						crearRubricasAsignaturaCompleta();
		    					cerrarVentanas();
	    		    		}
	    		    		else{
	    	    				windowsNewAsignatura[numeroRubricas+currentPart].hide();
	    	    				windowsNewAsignatura[numeroRubricas+currentPart].setModal(false);
	    			    		crearRubricas(currentPart+1);
	    		    		}
	    				}
	    				else if (id == "anterior"){
	    					windowsNewAsignatura[numeroRubricas+currentPart].setModal(false);
	    					windowsNewAsignatura[numeroRubricas+currentPart].hide();
	    					windowsNewAsignatura[numeroRubricas+currentPart-1].setModal(true);
	    					windowsNewAsignatura[numeroRubricas+currentPart-1].show();
	    				}
	    			});
		    	});
		    }
		    
		    
		    
		    function botonNuevoCriterio(id,type,col){
		    	var grupo_add_nuevo_criterio=id.charAt(id.length-1);
				var criterio_nuevo_nombre="value("+type+"_criterio_"+grupo_add_nuevo_criterio+"_"+num_criterios_grupo[col][grupo_add_nuevo_criterio]+")";
				var criterio_nueva_etiqueta='<bean:message key="label.criterio"/>'+' '+num_criterios_grupo[col][grupo_add_nuevo_criterio];
				var criterio_nuevo={type:"input", name:criterio_nuevo_nombre, label:criterio_nueva_etiqueta, labelWidth:"140", style:"width:180", required:"true"};
				formNewRubrica.addItem(type+"_bloque_"+grupo_add_nuevo_criterio, criterio_nuevo, num_criterios_grupo[col][grupo_add_nuevo_criterio], true);
				formNewRubrica.setRequired(criterio_nuevo_nombre,true);
				formNewRubrica.enableItem(type+"_boton_borrar_criterio_"+grupo_add_nuevo_criterio);
				num_criterios_grupo[col][grupo_add_nuevo_criterio]=num_criterios_grupo[col][grupo_add_nuevo_criterio]+1;
		    }
		    
		    function botonBorrarCriterio(id,type,col){
		    	var grupo_add_nuevo_criterio=id.charAt(id.length-1);
				num_criterios_grupo[col][grupo_add_nuevo_criterio]=num_criterios_grupo[col][grupo_add_nuevo_criterio]-1;
				var criterio_nuevo_nombre="value("+type+"_criterio_"+grupo_add_nuevo_criterio+"_"+num_criterios_grupo[col][grupo_add_nuevo_criterio]+")";
				formNewRubrica.removeItem(criterio_nuevo_nombre);
				if(num_criterios_grupo[col][grupo_add_nuevo_criterio]==2){
					formNewRubrica.disableItem(id);
				}
		    }
		    
		    function botonNuevoGrupo(id,type,col){
		    	var grupo_add_nuevo_criterio=parseInt(id.charAt(id.length-1))+1;
				num_criterios_grupo[col][grupo_add_nuevo_criterio]=0;
				var bloque_grupo_nuevo_nombre=type+"_bloque_"+grupo_add_nuevo_criterio;
				var bloque_grupo_nuevo={type:"block", name:bloque_grupo_nuevo_nombre};
				formNewRubrica.addItem(type, bloque_grupo_nuevo, id.charAt(id.length-1)*2, true);
				
				var criterio_nuevo_grupo_nombre="value("+type+"_grupo_"+grupo_add_nuevo_criterio+")";
				var criterio_nuevo_grupo_etiqueta='<strong><bean:message key="label.nombre.grupo.criterios"/>'+' '+grupo_add_nuevo_criterio+'</strong>';
				var criterio_nuevo={type:"input", name:criterio_nuevo_grupo_nombre, label:criterio_nuevo_grupo_etiqueta, labelWidth:"140", style:"width:180", required:"true"};
				formNewRubrica.addItem(type+"_bloque_"+grupo_add_nuevo_criterio, criterio_nuevo, num_criterios_grupo[col][grupo_add_nuevo_criterio], true);							
				formNewRubrica.setRequired(criterio_nuevo_grupo_nombre,true);
				num_criterios_grupo[col][grupo_add_nuevo_criterio]=num_criterios_grupo[col][grupo_add_nuevo_criterio]+1;
				
				var criterio_nuevo_nombre="value("+type+"_criterio_"+grupo_add_nuevo_criterio+"_1)";
				var criterio_nueva_etiqueta='<bean:message key="label.criterio"/>'+' 1';
				var criterio_nuevo={type:"input", name:criterio_nuevo_nombre, label:criterio_nueva_etiqueta, labelWidth:"140", style:"width:180", required:"true"};
				formNewRubrica.addItem(type+"_bloque_"+grupo_add_nuevo_criterio, criterio_nuevo, num_criterios_grupo[col][grupo_add_nuevo_criterio], true);
				num_criterios_grupo[col][grupo_add_nuevo_criterio]=num_criterios_grupo[col][grupo_add_nuevo_criterio]+1;
				
				var bloque_botones_nuevo_nombre=type+"_bloque_botones_"+grupo_add_nuevo_criterio;
				var bloque_botones_nuevo={type:"block", name:bloque_botones_nuevo_nombre};
				formNewRubrica.addItem(type, bloque_botones_nuevo, id.charAt(id.length-1)*2+1, true);
				
				var boton_nuevo_criterio_nombre=type+"_boton_nuevo_criterio_"+grupo_add_nuevo_criterio;
				var boton_nuevo_criterio_value='<bean:message key="button.add.nuevo.criterio"/>';
				var bloque_boton_nuevo_criterio={type:"button", name:boton_nuevo_criterio_nombre, value:boton_nuevo_criterio_value};
				formNewRubrica.addItem(type+"_bloque_botones_"+grupo_add_nuevo_criterio, bloque_boton_nuevo_criterio, 0, true);
				formNewRubrica.addItem(type+"_bloque_botones_"+grupo_add_nuevo_criterio, {type:"newcolumn"}, 1, true);
				var boton_borrar_criterio_nombre=type+"_boton_borrar_criterio_"+grupo_add_nuevo_criterio;
				var boton_borrar_criterio_value='<bean:message key="button.delete.nuevo.criterio"/>';
				var bloque_boton_borrar_criterio={type:"button", name:boton_borrar_criterio_nombre, value:boton_borrar_criterio_value, disabled:"true"};
				formNewRubrica.addItem(type+"_bloque_botones_"+grupo_add_nuevo_criterio, bloque_boton_borrar_criterio, 2, true);
				
				formNewRubrica.removeItem(id);
				var boton_nuevo_grupo_nombre=type+"_boton_nuevo_grupo_"+grupo_add_nuevo_criterio;
				var boton_nuevo_grupo_value='<strong><bean:message key="button.add.nuevo.grupo"/></strong>';
				var boton_nuevo_grupo={type:"button", name:boton_nuevo_grupo_nombre, value:boton_nuevo_grupo_value};
				formNewRubrica.addItem(type+"_bloque_boton", boton_nuevo_grupo, 0, true);
				formNewRubrica.addItem(type+"_bloque_boton", {type:"newcolumn"}, 1, true);
				formNewRubrica.removeItem(type+"_boton_borrar_grupo_"+id.charAt(id.length-1));
				var boton_borrar_grupo_nombre=type+"_boton_borrar_grupo_"+grupo_add_nuevo_criterio;
				var boton_borrar_grupo_value='<strong><bean:message key="button.delete.nuevo.grupo"/></strong>';
				var boton_borrar_grupo={type:"button", name:boton_borrar_grupo_nombre, value:boton_borrar_grupo_value};
				formNewRubrica.addItem(type+"_bloque_boton", boton_borrar_grupo, 2, true);
		    }
		    
		    function botonBorrarGrupo(id,type,col){
		    	var grupo_add_nuevo_criterio=parseInt(id.charAt(id.length-1));
				
				formNewRubrica.removeItem(type+"_bloque_botones_"+grupo_add_nuevo_criterio);
				formNewRubrica.removeItem(type+"_bloque_"+grupo_add_nuevo_criterio);

				formNewRubrica.removeItem(id);
				var boton_nuevo_grupo_nombre=type+"_boton_nuevo_grupo_"+(grupo_add_nuevo_criterio-1);
				var boton_nuevo_grupo_value='<strong><bean:message key="button.add.nuevo.grupo"/></strong>';
				var boton_nuevo_grupo={type:"button", name:boton_nuevo_grupo_nombre, value:boton_nuevo_grupo_value};
				formNewRubrica.addItem(type+"_bloque_boton", boton_nuevo_grupo, 0, true);
				formNewRubrica.addItem(type+"_bloque_boton", {type:"newcolumn"}, 1, true);
				formNewRubrica.removeItem(type+"_boton_nuevo_grupo_"+id.charAt(id.length-1));
				var boton_borrar_grupo_nombre=type+"_boton_borrar_grupo_"+(grupo_add_nuevo_criterio-1);
				var boton_borrar_grupo_value='<strong><bean:message key="button.delete.nuevo.grupo"/></strong>';
				var boton_borrar_grupo={type:"button", name:boton_borrar_grupo_nombre, value:boton_borrar_grupo_value};
				formNewRubrica.addItem(type+"_bloque_boton", boton_borrar_grupo, 2, true);
				
				if(grupo_add_nuevo_criterio-1 == 1){
					formNewRubrica.disableItem(boton_borrar_grupo_nombre);
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
			
			function dameGruposCriteriosAsignatura(idAsignatura){
	    		var url = "gruposcriteriosasignatura.do?idAsignatura="+idAsignatura;
	    		var xmlhttp = initRequest();
	    		xmlhttp.onreadystatechange=function(){
	    			if (xmlhttp.readyState===4) {
	        	        if(xmlhttp.status===200) { //GET returning a response
	        	        	return createArrayGruposCriteriosFromXML(xmlhttp.responseXML);
	        	        }
	        	    }
	    		}
	    	    xmlhttp.open("GET",url,false);
	    	    xmlhttp.send(null);
	    	    return xmlhttp.onreadystatechange();
	    	}
			
			function dameGruposAnexoAsignatura(idAsignatura){
	    		var url = "gruposanexoasignatura.do?idAsignatura="+idAsignatura;
	    		var xmlhttp = initRequest();
	    		xmlhttp.onreadystatechange=function(){
	    			if (xmlhttp.readyState===4) {
	        	        if(xmlhttp.status===200) { //GET returning a response
	        	        	return createArrayGruposCriteriosFromXML(xmlhttp.responseXML);
	        	        }
	        	    }
	    		}
	    	    xmlhttp.open("GET",url,false);
	    	    xmlhttp.send(null);
	    	    return xmlhttp.onreadystatechange();
	    	}
			
			function dameAnexoAsignatura(idAsignatura){
	    		var url = "anexoasignatura.do?idAsignatura="+idAsignatura;
	    		var xmlhttp = initRequest();
	    		xmlhttp.onreadystatechange=function(){
	    			if (xmlhttp.readyState===4) {
	        	        if(xmlhttp.status===200) { //GET returning a response
	        	        	return xmlhttp.responseXML.getElementsByTagName("anexo")[0].firstChild.nodeValue;
	        	        }
	        	    }
	    		}
	    	    xmlhttp.open("GET",url,false);
	    	    xmlhttp.send(null);
	    	    return xmlhttp.onreadystatechange();
	    	}
			
			function dameCompetenciasAsignatura(idAsignatura){
	    		var url = "competenciasasignatura.do?idAsignatura="+idAsignatura;
	    		var xmlhttp = initRequest();
	    		xmlhttp.onreadystatechange=function(){
	    			if (xmlhttp.readyState===4) {
	        	        if(xmlhttp.status===200) { //GET returning a response
	        	        	return xmlhttp.responseXML.getElementsByTagName("competencias")[0].firstChild.nodeValue;
	        	        }
	        	    }
	    		}
	    	    xmlhttp.open("GET",url,false);
	    	    xmlhttp.send(null);
	    	    return xmlhttp.onreadystatechange();
	    	}
			
			function initRequest() {
	    	    if (window.XMLHttpRequest) {
	    	        xmlhttp = new XMLHttpRequest();
	    	    } else if (window.ActiveXObject) {
	    	        isIE = true;
	    	        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	    	    }
	    	    return xmlhttp;
	    	}
			
			
			function createArrayGruposCriteriosFromXML(xml){
				var items = new Array();
				var criterios_grupo = new Array();
				var grupos = xml.getElementsByTagName("grupo");
				var criterios;
				var id_grupo, nombre_grupo, id_criterio, nombre_criterio;
				var id_grupo_id_criterio;
				for(var i=0;i<grupos.length;i++) {
	    	        id_grupo=grupos[i].getElementsByTagName("id_grupo")[0].firstChild.nodeValue;
	    	        nombre_grupo=grupos[i].getElementsByTagName("nombre_grupo")[0].firstChild.nodeValue;
	    	        criterios = grupos[i].getElementsByTagName("criterio");
	    	        criterios_grupo = new Array();
	    	        for(var j=0;j<criterios.length;j++){
	    	        	id_criterio=criterios[j].getElementsByTagName("id_criterio")[0].firstChild.nodeValue;
		    	        id_grupo_id_criterio=id_grupo+"_"+id_criterio;
		    	        nombre_criterio=criterios[j].getElementsByTagName("nombre_criterio")[0].firstChild.nodeValue;
		    	        criterios_grupo[j]={type:"input", name:id_grupo_id_criterio, value:nombre_criterio, rows:"1", disabled:"true", style:"width:800"};
	    	        }
	    	        items[i]={type:"fieldset", name:id_grupo, label:nombre_grupo, width:"100%", list:criterios_grupo};
		    	}
				return items;
	    	}
			
        </script>
	</head>
	<body>
	</body>
</html>