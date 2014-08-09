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
	    	
	    	var main_layout, areaTrabajoCursos, listado, toolbarAsignaturas,
	    	gridAsignaturas, formInfo, formRubrica, dhxWins, tabbar, tab_info, tab_rubrica, rubrica_layout,
	    	competencias_layout, grupos_layout;
	    	
	    	var windowsNewAsignatura = new Array();
	    	var formsNewAsignatura = new Array();
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	dhtmlxError.catchError("ALL",errorHandler);
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    listado = main_layout.cells('a');
			    listado.setWidth(600);
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
		    	
				areaTrabajoCursos.setText(areaTrabajoCursos.getText());
				areaTrabajoCursos.showHeader();
		    }
		    
		    
		    function loadFormRubrica(idAsignatura){

		    	formRubrica = tab_rubrica.attachForm();
		    	formRubrica.loadStruct('../xml/forms/rubrica_form.xml', function(){
	    			formRubrica.setItemLabel('competencias','<bean:message key="title.resultados.competencias"/>');
	    			
	    			var itemData = createArrayGruposCriteriosFromXML();
	    			for(var i=0;i++;i<itemData.lenght){
	    				formRubrica.addItem("grupos", itemData[i]);
	    			}
	    			//myForm.addItem("query", {type: "newcolumn"}, lastItemIndex+1+q);
	    			
	    			
	    			//permisosRubricasForm();	
	    			/*formRubrica.load('editarasignatura.do?idAsignatura=' + idAsignatura, function () {			    			
	    				formRubrica.attachEvent("onButtonClick", function(id){
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
		    	windowsNewAsignatura[0] = dhxWins.createWindow("nuevaAsignatura", 300, 50, 400, 215);
		    	var windowNewAsignatura = windowsNewAsignatura[0];
		    	windowNewAsignatura.setText('<bean:message key="title.crear.nueva.asignatura" />');
		    	windowNewAsignatura.centerOnScreen();
				windowNewAsignatura.setModal(true);
				formsNewAsignatura[0] = windowsNewAsignatura[0].attachForm();
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
		    	windowsNewAsignatura[currentPart] = dhxWins.createWindow("nuevaParte"+currentPart, 300, 50, 405, 440);
		    	var windowNewAsignaturaPart = windowsNewAsignatura[currentPart];
		    	windowNewAsignaturaPart.setText('<bean:message key="title.crear.nueva.asignatura" />'+': ' + '<bean:message key="title.parte.nueva.asignatura" /> ' +currentPart);				
		    	windowNewAsignaturaPart.centerOnScreen();
		    	windowNewAsignaturaPart.setModal(true);
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
						if(formNewAsignaturaPart.getItemType(id) == "input"){
							formNewAsignaturaPart.setRequired(id,true);
						}
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
	    					if(!existeEnNombresAnteriores(currentPart,numeroRubricas)){
		    					formNewAsignaturaPart.send("buscarasignatura.do","post", function(loader,response) {
		    						resultadoBuscarParteAsignatura(response,currentPart,numeroRubricas);
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
			
		    function existeEnNombresAnteriores(currentPart,numeroRubricas){
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
		    
		    function resultadoBuscarParteAsignatura(response,currentPart,numeroRubricas){
		    	if(response == "No existe ninguna asignatura con ese nombre") {
		    		if(numeroRubricas == 1 || currentPart == numeroRubricas) {
		    			crearRubricas(numeroRubricas);
		    			crearAsignaturaCompleta(numeroRubricas);
		    			cerrarVentanas();
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
	        	        	return createArrayFromXML(xmlhttp.responseXML);
	        	        }
	        	    }
	    		}
	    	    xmlhttp.open("GET",url,false);
	    	    xmlhttp.send(null);
	    	    return xmlhttp.onreadystatechange();
	    	}
			
			function createArrayGruposCriteriosFromXML(xml){
				
				/*var pos = 1;
    			var itemData = [{type: "select", name: "video_codec", label: "Codec", validate: "NotEmpty", options:[
						{text: "DivX", value: "DivX"},
						{text: "XviD", value: "XviD", selected: true}
				]},
				{type: "select", name: "video_codec", label: "Codec2", validate: "NotEmpty", options:[
							{text: "DivX", value: "DivX"},
							{text: "XviD", value: "XviD", selected: true}
					]},
					{type: "select", name: "video_codec", label: "Codec3", validate: "NotEmpty", options:[
							{text: "DivX", value: "DivX"},
							{text: "XviD", value: "XviD", selected: true}
					]}];
				
				
				
				
				
	    		var icon = 'libro.png';
	    		var asignaturas = xml.getElementsByTagName("asignatura");
	    		var id, nombre, asignatura;
	    		var opts = new Array();
	    		for(var i=0;i<asignaturas.length;i++) {
	    	        id=asignaturas[i].getElementsByTagName("id")[0].firstChild.nodeValue;
	    	        nombre=asignaturas[i].getElementsByTagName("nombre")[0].firstChild.nodeValue;
					asignatura=[id,'obj',nombre,icon];
	    	       	opts[i] = asignatura;
	    	    }
	    		return opts;*/

	    	}
			
        </script>
	</head>
	<body>
	</body>
</html>