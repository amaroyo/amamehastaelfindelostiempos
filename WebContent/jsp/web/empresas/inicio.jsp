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
	    	
	    	var gridLeads, form, tabbar, toolbarEmpresas;
	    	
		    dhtmlxEvent(window,"load",function() {

		    	dhtmlxError.catchError("ALL",errorHandler);

		    	var main_layout = new dhtmlXLayoutObject(document.body, '2U');

		    	var a = main_layout.cells('a');
		    	a.hideHeader();
		    	toolbarEmpresas = a.attachToolbar();
		    	toolbarEmpresas.setIconsPath('../skins/imgs/toolbar/');	
		    	toolbarEmpresas.loadXML('../xml/toolbars/dhxtoolbar-empresas.xml', function(){
		    		toolbarEmpresas.setItemText('new',"<bean:message key="button.create.company"/>");
		    		toolbarEmpresas.setItemText('delete',"<bean:message key="button.eliminar"/>");
		    		toolbarEmpresas.setItemText('exportar',"<bean:message key="button.exportar"/>");
		    		toolbarEmpresas.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>21</permiso>" >		    	
							toolbarEmpresas.hideItem('new');
							toolbarEmpresas.hideItem('sep1');
	    				</logic:notMatch>
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>22</permiso>" >		    	
							toolbarEmpresas.hideItem('delete');
							toolbarEmpresas.hideItem('sep2');
						</logic:notMatch>    
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>41</permiso>" >		    	
							toolbarEmpresas.hideItem('exportar');
							toolbarEmpresas.hideItem('sep3');
						</logic:notMatch>						
					</logic:notMatch>		    		
		    		
		    	});

		    	gridLeads = a.attachGrid();
		    	gridLeads.setIconsPath('../skins/imgs/');
		    	
		    	gridLeads.setHeader(["<bean:message key="label.nombre"/>","<bean:message key="label.telefono"/>","<bean:message key="label.telefono.movil"/>","<bean:message key="label.address.email"/>","<bean:message key="label.contacto"/>"]);
		    	gridLeads.setColTypes("ro,ro,ro,ro,ro");
		    	
		    	gridLeads.setColSorting('str,str,str,str,str');
		    	gridLeads.enableMultiselect(true);
		    	gridLeads.init();
				gridLeadsProcessor = new dataProcessor("gridempresas.do");
				gridLeadsProcessor.enableUTFencoding('simple');
				gridLeadsProcessor.init(gridLeads);	  
				gridLeadsProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	}); 
		    	
		    	gridLeads.attachEvent("onRowSelect", function(idEmpresa,ind){
		    		toolbarEmpresas.enableItem('delete');
		    		
		    		b.showHeader();
		    		b.setText("<bean:message key="title.empresa.propiedades"/>");
		    		

			    	tabbar = b.attachTabbar();
			    	tabbar.addTab('tab_1','<bean:message key="label.propiedades"/>','');
			    	tab_1 = tabbar.cells('tab_1');
			    	tabbar.setTabActive('tab_1');
			    	form = tab_1.attachForm();
			    	form.loadStruct('../xml/forms/empresa_form.xml', function() {
			    		form.setItemLabel('data','<bean:message key="title.datos.empresa"/>');
			    		form.setItemLabel('nombre','<bean:message key="label.nombre"/>');
			    		form.setItemLabel('telefono','<bean:message key="label.telefono"/>');
			    		form.setItemLabel('telefonoMovil','<bean:message key="label.telefono.movil"/>');
			    		form.setItemLabel('email','<bean:message key="label.address.email"/>');
			    		form.setItemLabel('informacionContacto','<bean:message key="label.contacto"/>');
			    		form.setItemLabel('direccion','<bean:message key="label.direccion"/>');
			    		form.setItemLabel('orgnr','<bean:message key="label.orgnr"/>');
			    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');

						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
							<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>23</permiso>" >		    	
								form.hideItem('aceptar');
							</logic:notMatch>
						</logic:notMatch>			    		
			    		
			    		form.load('editarempresa.do?idEmpresa=' + idEmpresa, function() {
			    			form.attachEvent("onButtonClick", function(id){
			    				if (id == "aceptar") {
				    				form.send("actualizarempresa.do?!nativeeditor_status=update&idEmpresa=" + idEmpresa ,"post", function(xml) {
				    					
				    				});
				    				buscar();
			    				}
			    			});			    			
			    		});
			    	});

					var or = false;
					<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
						or = true;
					</logic:match>
					<logic:match scope="session" name="usuarioYPermisos" value="<permiso>2</permiso>" >
						or = true;
					</logic:match>
			    	
					if (or) {			
						tabbar.addTab('tab_2','<bean:message key="label.leads"/>','');
				    	tab_2 = tabbar.cells('tab_2');
				    	var grid_1 = tab_2.attachGrid();			    	
				    	grid_1.setHeader(["<bean:message key="label.responsable"/>","<bean:message key="label.distribuidor"/>","<bean:message key="label.comercial"/>","<bean:message key="label.servicio"/>","<bean:message key="label.estado"/>","<bean:message key="label.last.modification.date"/>"]);
				    	grid_1.setColTypes("ro,ro,ro,ro,ro,ro");
				    	grid_1.attachHeader("#select_filter,#select_filter");
				    	grid_1.setColSorting('str,str,str,str,str,str');
				    	grid_1.init();
				    	grid_1.load('gridleads.do?idEmpresa=' + idEmpresa, function(){
				    		grid_1.enableStableSorting(true);
				    		grid_1.sortRows(5, "str", "des");
				    	}, 'xml');		 
				    	grid_1.attachEvent("onRowSelect", function(id,ind){
							var dhxWins= new dhtmlXWindows();
							var window = dhxWins.createWindow("lead", 300,50, 365, 475);
							window.setText('<bean:message key="title.lead"/>');				
							window.setModal(true);
							window.centerOnScreen();
						
							var form_lead = window.attachForm();		    	
					    	form_lead.loadStruct('../xml/forms/lead_form.xml', function() {
					    		form_lead.setItemLabel('data','<bean:message key="title.info.general"/>');
					    		form_lead.setItemLabel('servicio','<bean:message key="label.servicio"/>');
					    		form_lead.setItemLabel('comercial','<bean:message key="label.comercial"/>');
					    		form_lead.setItemLabel('responsable','<bean:message key="label.responsable"/>');
					    		form_lead.setItemLabel('fechaVisita','<bean:message key="label.fecha.visita"/>');
					    		form_lead.setItemLabel('estado','<bean:message key="label.estado"/>');
					    		form_lead.setItemLabel('marketingActivity','<bean:message key="label.marketing.activity"/>');
					    		form_lead.setItemLabel('comentarios','<bean:message key="label.comentarios"/>');
					    		form_lead.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
					    		form_lead.setItemLabel('usuariosPotenciales','<bean:message key="label.potential.users"/>');
					    		form_lead.setItemLabel('usuarios','<bean:message key="label.users"/>');

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
		    	gridLeads.clearAndLoad("gridempresas.do");		    	
		    }
		    
		    function deleteEmpresa() {
		    	if (confirm("<bean:message key="message.confirm.delete.company"/>")) {
		    		gridLeads.deleteSelectedRows();
		    		goActualizar();
		    	}
		    }
		    
		    function goActualizar() {
		    	buscar();
		    	toolbarEmpresas.disableItem('delete');
		    	tabbar.clearAll();
		    }
		    
		    function newCompany() {
				var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("company", 300,50, 365, 320);
				window.setText('<bean:message key="title.new.company" />');				
				window.setModal(true);
				window.centerOnScreen();
				var form = window.attachForm();			
		    	form.loadStruct('../xml/forms/new_empresa_form.xml', function() {
		    		form.setItemLabel('data','<bean:message key="title.datos.empresa"/>');
		    		form.setItemLabel('nombre','<bean:message key="label.nombre"/>');
		    		form.setItemLabel('telefono','<bean:message key="label.telefono"/>');
		    		form.setItemLabel('telefonoMovil','<bean:message key="label.telefono.movil"/>');
		    		form.setItemLabel('email','<bean:message key="label.address.email"/>');
		    		form.setItemLabel('informacionContacto','<bean:message key="label.contacto"/>');
		    		form.setItemLabel('direccion','<bean:message key="label.direccion"/>');
		    		form.setItemLabel('orgnr','<bean:message key="label.orgnr"/>');
		    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
		    		
		    		form.setItemValue('orgnr',"");
		    				    					    			
		    		form.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
	    					if (form.getItemValue('orgnr') != "") {
			    				form.send("actualizarempresa.do?!nativeeditor_status=save","post", function(xml) {
			    					
			    				});
			    				window.close();
			    				goActualizar();
	    					} else {
	    						var alerta = '<bean:message key="message.empty.fields"/>';
	    						if (form.getItemValue('orgnr') == "") alerta += "\r- "+'<bean:message key="label.orgnr"/>';
	    						alert(alerta);
	    					}		    				
	    				}
		    		});
		    	});			    	
		    }
		    
		    function goExportar() {
			   	var accion = "exportarempresas.do";
			   	location.href=accion;	
		    }		    
		    
	 	</script>
	 	
	 </head>
	<body>
	</body>
</html>