<%@ include file="../../common/taglibs.jsp" %>
<html>
	<head>
	    <link rel="stylesheet" type="text/css" href="../css/estilos.css">
	    <link rel="stylesheet" type="text/css" href="../css/templates.css">
	    <link rel="stylesheet" type="text/css" href="../css/estilosMenu.css">
	    <link rel="stylesheet" type="text/css" href="../skins/dhtmlx.css">
	    <script type="text/javascript" src="../skins/dhtmlx.js"></script>
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    

	    <script type="text/javascript">

	    	dhtmlx.image_path='../skins/imgs/';
	    	
	    	var gridLeads, gridFicheros, toolbarServicios, toolbarFicheros, form, tabbar, idSelectedService, idSelectedFile;
  	
		    dhtmlxEvent(window,"load",function() {
		    	
			    dhtmlxError.catchError("ALL",errorHandler);

			    var main_layout = new dhtmlXLayoutObject(document.body, '2U');

			    var a = main_layout.cells('a');
		    	a.hideHeader();
		    	toolbarServicios = a.attachToolbar();
		    	toolbarServicios.setIconsPath('../skins/imgs/toolbar/');
		    	
		    	toolbarServicios.loadXML('../xml/toolbars/dhxtoolbar-servicios.xml', function(){
		    		toolbarServicios.setItemText('new',"<bean:message key="button.create.service"/>");
		    		toolbarServicios.setItemText('delete',"<bean:message key="button.eliminar"/>");
		    		toolbarServicios.setItemText('exportar',"<bean:message key="button.exportar"/>");
		    		toolbarServicios.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>25</permiso>" >		    	
							toolbarServicios.hideItem('new');
							toolbarServicios.hideItem('sep1');
	    				</logic:notMatch>
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>26</permiso>" >		    	
							toolbarServicios.hideItem('delete');
							toolbarServicios.hideItem('sep2');
						</logic:notMatch>    			
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>42</permiso>" >		    	
							toolbarServicios.hideItem('exportar');
							toolbarServicios.hideItem('sep3');
						</logic:notMatch> 						
					</logic:notMatch>		    		
		    		
		    	});	    	
		    	gridLeads = a.attachGrid();
		    	gridLeads.setIconsPath('../skins/imgs/');
		    	
		    	gridLeads.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.contact.name" />","<bean:message key="label.supplier" />"]);
		    	gridLeads.setColTypes("ro,ro,ro");
		    	
		    	gridLeads.setColSorting('str,str,str');
		    	gridLeads.enableMultiselect(true);
		    	gridLeads.init();
		    	
				gridLeadsProcessor = new dataProcessor("gridservicios.do");
				gridLeadsProcessor.enableUTFencoding('simple');
				gridLeadsProcessor.init(gridLeads);	  
				gridLeadsProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
		    	
				gridLeads.attachEvent("onRowSelect", function(idServicio,ind){
					idSelectedService = idServicio;
					toolbarServicios.enableItem('delete');
		    		
		    		b.showHeader();
		    		b.setText("<bean:message key="title.servicio.propiedades"/>");
			    	
				   	tabbar = b.attachTabbar();
				   	tabbar.addTab('tab_1','<bean:message key="label.propiedades"/>','');
				   	tab_1 = tabbar.cells('tab_1');
				   	tabbar.setTabActive('tab_1');
				   	form = tab_1.attachForm();
				   	form.loadStruct('../xml/forms/servicio_form.xml', function(){
			    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
			    		form.setItemLabel('nombre','<bean:message key="label.nombre"/>');
			    		form.setItemLabel('personaContacto','<bean:message key="label.contact.name"/>');
			    		form.setItemLabel('proveedor','<bean:message key="label.supplier"/>');
			    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');

						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
							<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>27</permiso>" >		    	
								form.hideItem('aceptar');
							</logic:notMatch>
						</logic:notMatch>			    		
			    		
				   		form.load('editarservicio.do?idServicio=' + idServicio, function () {			    			
				   			form.attachEvent("onButtonClick", function(id){
				   				if (id == "aceptar") {
				    				form.send("actualizarservicio.do?!nativeeditor_status=save&idServicio=" + idServicio ,"post", function(xml) {
				    					
				    				});
				    				buscar();
				   				}
				   			});
				   		});
				   	});	
				   	
				   	
				  	//añadir tab con ficheros
				   	tabbar.addTab('tab_2','<bean:message key="layout.files.list"/>','');
				   	tab_2 = tabbar.cells('tab_2');				
			    	toolbarFicheros = tab_2.attachToolbar();
			    	toolbarFicheros.setIconsPath('../skins/imgs/toolbar/');
			    	
			    	toolbarFicheros.loadXML('../xml/toolbars/dhxtoolbar-ficheros.xml', function(){
			    		toolbarFicheros.setItemText('new',"<bean:message key="button.add.file"/>");
			    		toolbarFicheros.setItemText('delete',"<bean:message key="button.eliminar"/>");
			    		toolbarFicheros.setItemText('refresh',"<bean:message key="button.actualizar"/>");
			    		toolbarFicheros.setItemText('download',"<bean:message key="button.download"/>");
			    		
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
							<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>27</permiso>" >		    	
								toolbarFicheros.hideItem('new');
								toolbarFicheros.hideItem('sep1');
								toolbarFicheros.hideItem('delete');
								toolbarFicheros.hideItem('sep2');
							</logic:notMatch>
						</logic:notMatch>			    		
			    		
			    	});
		            
			    	gridFicheros = tab_2.attachGrid();
			    	gridFicheros.setIconsPath('../skins/imgs/');
			    	
			    	gridFicheros.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.description" />","<bean:message key="label.last.download" />"]);
			    	gridFicheros.setColTypes("ro,ro,ro");
			    	
			    	gridFicheros.setColSorting('str,str,str');
			    	gridFicheros.load("gridficheros.do?idServicio="+idServicio);
			    	gridFicheros.init();

			    	gridFicherosProcessor = new dataProcessor("gridficheros.do");
			    	gridFicherosProcessor.enableUTFencoding('simple');
			    	gridFicherosProcessor.init(gridFicheros);	  
			    	gridFicherosProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
			    			dhtmlx.message(tag.firstChild.data,action,4000);
			    		}
			    	});		    	
			    	
			    	gridFicheros.attachEvent("onRowSelect", function(idFichero,ind){
			    		idSelectedFile = idFichero;			    		
			    		toolbarFicheros.enableItem('delete');
			    		toolbarFicheros.enableItem('download');
			    	});	
			    	
			    	
					var or = false;
					<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
						or = true;
					</logic:match>
					<logic:match scope="session" name="usuarioYPermisos" value="<permiso>2</permiso>" >
						or = true;
					</logic:match>
			    	
					if (or) {
				    	tabbar.addTab('tab_3','<bean:message key="label.leads"/>','');
				    	tab_3 = tabbar.cells('tab_3');
				    	var grid_2 = tab_3.attachGrid();
				    	
				    	grid_2.setHeader(["<bean:message key="label.empresa"/>","<bean:message key="label.responsable"/>","<bean:message key="label.distribuidor"/>","<bean:message key="label.comercial"/>","<bean:message key="label.estado"/>","<bean:message key="label.last.modification.date"/>"]);
				    	grid_2.setColTypes("ro,ro,ro,ro,ro,ro");
				    	grid_2.attachHeader("#select_filter,#select_filter");
				    	grid_2.setColSorting('str,str,str,str,str,str');
				    	grid_2.init();
				    	grid_2.load('gridleads.do?idServicio=' + idServicio, function(){
				    		grid_2.enableStableSorting(true);
				    		grid_2.sortRows(5, "str", "des");
				    	}, 'xml');
				    	grid_2.attachEvent("onRowSelect", function(id,ind){
							var dhxWins= new dhtmlXWindows();
							var window = dhxWins.createWindow("lead", 300,50, 365, 475);
							window.setText('<bean:message key="title.lead"/>');				
							window.setModal(true);
							window.centerOnScreen();
						
							var form_lead = window.attachForm();		    	
					    	form_lead.loadStruct('../xml/forms/lead_form.xml', function() {
					    		form_lead.setItemLabel('data','<bean:message key="title.info.general"/>');
					    		form_lead.setItemLabel('canal','<bean:message key="label.channel"/>');
					    		form_lead.setItemLabel('servicio','<bean:message key="label.servicio"/>');
					    		form_lead.setItemLabel('comercial','<bean:message key="label.comercial"/>');
					    		form_lead.setItemLabel('responsable','<bean:message key="label.responsable"/>');
					    		form_lead.setItemLabel('distribuidor','<bean:message key="label.distribuidor"/>');
					    		form_lead.setItemLabel('empresa','<bean:message key="label.empresa"/>');
					    		form_lead.setItemLabel('fechaVisita','<bean:message key="label.fecha.visita"/>');
					    		form_lead.setItemLabel('estado','<bean:message key="label.estado"/>');
					    		form_lead.setItemLabel('marketingActivity','<bean:message key="label.marketing.activity"/>');
					    		form_lead.setItemLabel('comentarios','<bean:message key="label.comentarios"/>');
					    		form_lead.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
					    		form_lead.setItemLabel('usuariosPotenciales','<bean:message key="label.potential.users"/>');
					    		form_lead.setItemLabel('usuarios','<bean:message key="label.users"/>');
	
					    		form_lead.getCombo('canal').readonly(1);
					    		form_lead.getCombo('canal').loadXML("../leads/listarcanales.do");
					    		form_lead.getCombo('servicio').readonly(1);
					    		form_lead.getCombo('servicio').loadXML("../leads/listarservicios.do");
					    		form_lead.getCombo('distribuidor').readonly(1);
					    		form_lead.getCombo('distribuidor').loadXML("../leads/listardistribuidores.do");	
					    		form_lead.getCombo('comercial').readonly(1);
					    		form_lead.getCombo('comercial').loadXML("../leads/listarcomerciales.do");
					    		form_lead.getCombo('responsable').readonly(1);
					    		form_lead.getCombo('responsable').loadXML("../leads/listarresponsables.do");
					    		form_lead.getCombo('empresa').readonly(1);
					    		form_lead.getCombo('empresa').loadXML("../leads/listarempresas.do");

								<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
									<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>5</permiso>" >		    	
										form_lead.hideItem('aceptar');
									</logic:notMatch>
								</logic:notMatch>					    		
					    		
					    		form_lead.load('../leads/editarlead.do?idLead=' + id);
					    	});				    	
				    		
				    	});			    	
					}
			    	
					
		    	});
		    	
			    buscar();
			    
		    	var b = main_layout.cells('b');
		    	b.hideHeader();
			    
			    
		    });

		    
		    function buscar() {
		    	gridLeads.clearAndLoad("gridservicios.do");		    	
		    }

		    function newService() {
				var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("service", 300,50, 355, 210);
				window.setText('<bean:message key="title.new.servicio" />');				
				window.setModal(true);
				window.centerOnScreen();
				var form = window.attachForm();			
		    	form.loadStruct('../xml/forms/new_servicio_form.xml', function() {
		    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
		    		form.setItemLabel('nombre','<bean:message key="label.nombre"/>');
		    		form.setItemLabel('personaContacto','<bean:message key="label.contact.name"/>');
		    		form.setItemLabel('proveedor','<bean:message key="label.supplier"/>');
		    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
		    				    					    			
		    		form.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
		    				form.send("actualizarservicio.do?!nativeeditor_status=save","post", function(xml) {
		    					
		    				});
		    				window.close();
		    				goActualizar();
	    				}
		    		});
		    	});			    	
		    }		    
		    
		    function deleteService() {
		    	if (confirm("<bean:message key="message.confirm.delete.service"/>")) {
		    		gridLeads.deleteSelectedRows();
		    		goActualizar();
		    	}
		    }
		    
		    function goActualizar() {
		    	buscar();
		    	toolbarServicios.disableItem('delete');
		    	tabbar.clearAll();	
		    }	
		    
		    function deleteFile() {
		    	if (confirm("<bean:message key="message.confirm.delete.file"/>")) {
		    		gridFicheros.deleteSelectedRows();
		    		goActualizarFiles();
		    	}
		    }
		    
		    function newFile() {
				var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("upload", 300, 50, 500, 300);
				window.setText('<bean:message key="title.upload.file" />');				
				window.setModal(true);
				window.centerOnScreen();
				window.attachURL("subir.do?idServicio=" + idSelectedService);
		    }

		    function goActualizarFiles() {
	    		toolbarFicheros.disableItem('delete');
	    		toolbarFicheros.disableItem('download');
		    	gridFicheros.clearAndLoad("gridficheros.do?idServicio="+idSelectedService);
		    }
		      
		    function downloadFile() {
				var archivo = "descargar.do?idFichero=" + idSelectedFile;
				location.href=archivo;
		    }
		    
		    function goExportar() {
			   	var accion = "exportarservicios.do";
			   	location.href=accion;	
		    }
			    
        </script>
	</head>
	<body>
	</body>
</html>