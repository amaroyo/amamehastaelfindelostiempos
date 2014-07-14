<%@ include file="../../common/taglibs.jsp" %>
<html>
	<head>
	    <link rel="stylesheet" type="text/css" href="../css/estilos.css">
	    <link rel="stylesheet" type="text/css" href="../css/templates.css">
	    <link rel="stylesheet" type="text/css" href="../css/estilosMenu.css">
	    <link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlx.css">
	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../skins/imgs/';
	    	
	    	var gridLeads, gridContactos, toolbarCanales, toolbarContactos, form, tabbar, idSelectedChannel, idSelectedContact;
  	
		    dhtmlxEvent(window,"load",function() {
		    	
			    dhtmlxError.catchError("ALL",errorHandler);

			    var main_layout = new dhtmlXLayoutObject(document.body, '2U');

			    var a = main_layout.cells('a');
		    	a.hideHeader();
		    	toolbarCanales = a.attachToolbar();
		    	toolbarCanales.setIconsPath('../skins/imgs/toolbar/');
		    	
		    	toolbarCanales.loadXML('../xml/toolbars/dhxtoolbar-canales.xml', function(){
		    		toolbarCanales.setItemText('new',"<bean:message key="button.create.channel"/>");
		    		toolbarCanales.setItemText('delete',"<bean:message key="button.eliminar"/>");
		    		toolbarCanales.setItemText('exportar',"<bean:message key="button.exportar"/>");
		    		toolbarCanales.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>29</permiso>" >		    	
							toolbarCanales.hideItem('new');
							toolbarCanales.hideItem('sep1');
	    				</logic:notMatch>
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>30</permiso>" >		    	
							toolbarCanales.hideItem('delete');
							toolbarCanales.hideItem('sep2');
						</logic:notMatch>    	
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>43</permiso>" >		    	
							toolbarCanales.hideItem('exportar');
							toolbarCanales.hideItem('sep3');
						</logic:notMatch> 						
					</logic:notMatch>			    		
		    		
		    	});	    	
		    	gridLeads = a.attachGrid();
		    	gridLeads.setIconsPath('../skins/imgs/');
		    	
		    	gridLeads.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.telefono" />","<bean:message key="label.telefono.movil" />","<bean:message key="label.address.email" />"]);
		    	gridLeads.setColTypes("ro,ro,ro,ro");
		    	
		    	gridLeads.setColSorting('str,str,str,str');
		    	gridLeads.enableMultiselect(true);
		    	gridLeads.init();
		    	
				gridLeadsProcessor = new dataProcessor("gridcanales.do");
				gridLeadsProcessor.enableUTFencoding('simple');
				gridLeadsProcessor.init(gridLeads);	  
				gridLeadsProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
		    	
				gridLeads.attachEvent("onRowSelect", function(idCanal,ind){
					idSelectedChannel = idCanal;
					toolbarCanales.enableItem('delete');
		    		
		    		b.showHeader();
		    		b.setText("<bean:message key="title.responsable.propiedades"/>");
			    	
				   	tabbar = b.attachTabbar();
				   	tabbar.addTab('tab_1','<bean:message key="label.propiedades"/>','');
				   	tab_1 = tabbar.cells('tab_1');
				   	tabbar.setTabActive('tab_1');
				   	form = tab_1.attachForm();
				   	form.loadStruct('../xml/forms/canal_form.xml', function(){
				   		form.setItemLabel('data','<bean:message key="title.info.general"/>');
				   		form.setItemLabel('nombre','<bean:message key="label.nombre"/>');
				   		form.setItemLabel('telefono','<bean:message key="label.telefono"/>');
				   		form.setItemLabel('telefonoMovil','<bean:message key="label.telefono.movil"/>');
				   		form.setItemLabel('direccion','<bean:message key="label.direccion"/>');
				   		form.setItemLabel('codigoPostal','<bean:message key="label.postal.code"/>');
				   		form.setItemLabel('ciudad','<bean:message key="label.ciudad"/>');
				   		form.setItemLabel('pais','<bean:message key="label.pais"/>');
				   		form.setItemLabel('email','<bean:message key="label.address.email"/>');				   		
				   		form.setItemLabel('direccionFacturacion','<bean:message key="label.invoice.address"/>');
				   		form.setItemLabel('codigoPostalFacturacion','<bean:message key="label.invoice.postal.code"/>');
				   		form.setItemLabel('ciudadFacturacion','<bean:message key="label.invoice.ciudad"/>');
				   		form.setItemLabel('paisFacturacion','<bean:message key="label.invoice.pais"/>');
				   		form.setItemLabel('nombreFacturacion','<bean:message key="label.invoice.nombre"/>');
				   		form.setItemLabel('telefonoFacturacion','<bean:message key="label.invoice.telefono"/>');
				   		form.setItemLabel('telefonoMovilFacturacion','<bean:message key="label.invoice.telefono.movil"/>');
				   		form.setItemLabel('emailFacturacion','<bean:message key="label.invoice.address.email"/>');				   		
				   		form.setItemLabel('direccionWeb','<bean:message key="label.web.address"/>');
				   		form.setItemLabel('mrmResponsable','<bean:message key="label.mrm.responsible"/>');				   		
				   		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');

						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
							<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>31</permiso>" >		    	
								form.hideItem('aceptar');
							</logic:notMatch>
						</logic:notMatch>				   		
				   		
				   		form.load('editarcanal.do?idCanal=' + idCanal, function () {			    			
				   			form.attachEvent("onButtonClick", function(id){
				   				if (id == "aceptar") {
				    				form.send("actualizarcanal.do?!nativeeditor_status=save&idCanal=" + idCanal ,"post", function(xml) {
				    					
				    				});
				    				buscar();
				   				}
				   			});
				   		});
				   	});					

				   	//añadir tab con contactos
				   	tabbar.addTab('tab_2','<bean:message key="layout.contact.list"/>','');
				   	tab_2 = tabbar.cells('tab_2');				
			    	toolbarContactos = tab_2.attachToolbar();
			    	toolbarContactos.setIconsPath('../skins/imgs/toolbar/');
			    	
			    	toolbarContactos.loadXML('../xml/toolbars/dhxtoolbar-contactos-canal.xml', function(){
			    		toolbarContactos.setItemText('new',"<bean:message key="button.create.contact"/>");
			    		toolbarContactos.setItemText('delete',"<bean:message key="button.eliminar"/>");
			    		toolbarContactos.setItemText('refresh',"<bean:message key="button.actualizar"/>");
			    		toolbarContactos.setItemText('edit',"<bean:message key="button.editar"/>");
			    		
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
							<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>31</permiso>" >		    	
								toolbarContactos.hideItem('new');
								toolbarContactos.hideItem('sep1');
								toolbarContactos.hideItem('delete');
								toolbarContactos.hideItem('sep2');
								toolbarContactos.hideItem('edit');
							</logic:notMatch>
						</logic:notMatch>			    		
			    		
			    	});
			    	
			    	gridContactos = tab_2.attachGrid();
			    	gridContactos.setIconsPath('../skins/imgs/');
			    	
			    	gridContactos.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.telefono" />","<bean:message key="label.telefono.movil" />","<bean:message key="label.address.email" />"]);
			    	gridContactos.setColTypes("ro,ro,ro,ro");
			    	
			    	gridContactos.setColSorting('str,str,str,str');
			    	gridContactos.load("gridcontactoscanal.do?idCanal="+idCanal);
			    	gridContactos.init();

			    	gridContactosProcessor = new dataProcessor("gridcontactoscanal.do");
			    	gridContactosProcessor.enableUTFencoding('simple');
			    	gridContactosProcessor.init(gridContactos);	  
			    	gridContactosProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
			    			dhtmlx.message(tag.firstChild.data,action,4000);
			    		}
			    	});		    	
			    	
			    	gridContactos.attachEvent("onRowSelect", function(idContacto,ind){
			    		toolbarContactos.enableItem('delete');
			    		toolbarContactos.enableItem('edit');
			    		idSelectedContact = idContacto;
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
				    	
				    	grid_2.setHeader(["<bean:message key="label.responsable"/>","<bean:message key="label.distribuidor"/>","<bean:message key="label.comercial"/>","<bean:message key="label.servicio"/>","<bean:message key="label.estado"/>","<bean:message key="label.last.modification.date"/>"]);
				    	grid_2.setColTypes("ro,ro,ro,ro,ro,ro");
				    	grid_2.attachHeader("#select_filter,#select_filter");
				    	grid_2.setColSorting('str,str,str,str,str,str');
				    	grid_2.init();
				    	grid_2.load('gridleads.do?idCanal=' + idCanal, function(){
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
			    	
					
					or = false;
					<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
						or = true;
					</logic:match>
					<logic:match scope="session" name="usuarioYPermisos" value="<permiso>8</permiso>" >
						or = true;
					</logic:match>					
					
					if (or) {					
				    	tabbar.addTab('tab_4','<bean:message key="title.comerciales"/>','');
				    	tab_4 = tabbar.cells('tab_4');
				    	var grid_3 = tab_4.attachGrid();
				    	
				    	grid_3.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.telefono" />","<bean:message key="label.telefono.movil" />","<bean:message key="label.address.email" />"]);
				    	grid_3.setColTypes("ro,ro,ro,ro");
				    	grid_3.setColSorting('str,str,str,str');
				    	grid_3.init();
				    	grid_3.load('gridcomerciales.do?idCanal=' + idCanal, 'xml');
				    	grid_3.attachEvent("onRowSelect", function(id,ind){
							var dhxWins= new dhtmlXWindows();
							var window = dhxWins.createWindow("sales rep.", 300, 50, 365, 410);
							window.setText('<bean:message key="title.comercial" />');				
							window.setModal(true);
							window.centerOnScreen();
							var form = window.attachForm();			
					    	form.loadStruct('../xml/forms/comercial_form.xml', function() {
					    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
					    		form.setItemLabel('nombre','<bean:message key="label.nombre"/>');
					    		form.setItemLabel('telefono','<bean:message key="label.telefono"/>');
					    		form.setItemLabel('telefonoMovil','<bean:message key="label.telefono.movil"/>');
					    		form.setItemLabel('direccion','<bean:message key="label.direccion"/>');
					    		form.setItemLabel('codigoPostal','<bean:message key="label.postal.code"/>');
					    		form.setItemLabel('ciudad','<bean:message key="label.ciudad"/>');
					    		form.setItemLabel('pais','<bean:message key="label.pais"/>');
					    		form.setItemLabel('email','<bean:message key="label.address.email"/>');
					    		form.setItemLabel('comentarios','<bean:message key="label.comentarios"/>');
					    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');

								<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
									<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>11</permiso>" >		    	
										form.hideItem('aceptar');
									</logic:notMatch>
								</logic:notMatch>					    		
					    		
					    		form.load('../comerciales/editarcomercial.do?idComercial=' + id);
					    	});				    	
				    		
				    	});
					}
			    	
					or = false;
					<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
						or = true;
					</logic:match>
					<logic:match scope="session" name="usuarioYPermisos" value="<permiso>16</permiso>" >
						or = true;
					</logic:match>					
					
					if (or) {					
				    	tabbar.addTab('tab_5','<bean:message key="title.distribuidores"/>','');
				    	tab_5 = tabbar.cells('tab_5');
				    	var grid_4 = tab_5.attachGrid();
				    	
				    	grid_4.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.telefono" />","<bean:message key="label.telefono.movil" />","<bean:message key="label.address.email" />"]);
				    	grid_4.setColTypes("ro,ro,ro,ro");
				    	grid_4.setColSorting('str,str,str,str');
				    	grid_4.init();
				    	grid_4.load('griddistribuidores.do?idCanal=' + idCanal, 'xml');
				    	grid_4.attachEvent("onRowSelect", function(id,ind){
							var dhxWins= new dhtmlXWindows();
							var window = dhxWins.createWindow("sales rep.", 300, 50, 420, 520);
							window.setText('<bean:message key="title.distribuidor" />');				
							window.setModal(true);
							window.centerOnScreen();
							var form = window.attachForm();			
					    	form.loadStruct('../xml/forms/distribuidor_form.xml', function() {
					    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
					    		form.setItemLabel('nombre','<bean:message key="label.nombre"/>');
					    		form.setItemLabel('telefono','<bean:message key="label.telefono"/>');
					    		form.setItemLabel('telefonoMovil','<bean:message key="label.telefono.movil"/>');
					    		form.setItemLabel('direccion','<bean:message key="label.direccion"/>');
					    		form.setItemLabel('codigoPostal','<bean:message key="label.postal.code"/>');
					    		form.setItemLabel('ciudad','<bean:message key="label.ciudad"/>');
					    		form.setItemLabel('pais','<bean:message key="label.pais"/>');
					    		form.setItemLabel('email','<bean:message key="label.address.email"/>');
					    		form.setItemLabel('comentarios','<bean:message key="label.comentarios"/>');
					    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');

								<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
									<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>19</permiso>" >		    	
										form.hideItem('aceptar');
									</logic:notMatch>
								</logic:notMatch>					    		
					    		
					    		form.load('../distribuidores/editardistribuidor.do?idDistribuidor=' + id);
					    	});				    	
				    		
				    	});
					}
			    	
		    	});
		    	
			    buscar();
			    
		    	var b = main_layout.cells('b');
		    	b.hideHeader();
			    
			    
		    });

		    
		    function buscar() {
		    	gridLeads.clearAndLoad("gridcanales.do");
		    }

		    function newChannel() {
				var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("channel", 300,50, 410, 520);
				window.setText('<bean:message key="title.new.channel" />');				
				window.setModal(true);
				window.centerOnScreen();
				var form = window.attachForm();			
		    	form.loadStruct('../xml/forms/new_canal_form.xml', function() {
		    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
		    		form.setItemLabel('nombre','<bean:message key="label.nombre"/>');
		    		form.setItemLabel('telefono','<bean:message key="label.telefono"/>');
		    		form.setItemLabel('telefonoMovil','<bean:message key="label.telefono.movil"/>');
		    		form.setItemLabel('direccion','<bean:message key="label.direccion"/>');
		    		form.setItemLabel('codigoPostal','<bean:message key="label.postal.code"/>');
		    		form.setItemLabel('ciudad','<bean:message key="label.ciudad"/>');
		    		form.setItemLabel('pais','<bean:message key="label.pais"/>');
		    		form.setItemLabel('email','<bean:message key="label.address.email"/>');
		    		form.setItemLabel('comentarios','<bean:message key="label.comentarios"/>');
			   		form.setItemLabel('direccionFacturacion','<bean:message key="label.invoice.address"/>');
			   		form.setItemLabel('codigoPostalFacturacion','<bean:message key="label.invoice.postal.code"/>');
			   		form.setItemLabel('ciudadFacturacion','<bean:message key="label.invoice.ciudad"/>');
			   		form.setItemLabel('paisFacturacion','<bean:message key="label.invoice.pais"/>');
			   		form.setItemLabel('nombreFacturacion','<bean:message key="label.invoice.nombre"/>');
			   		form.setItemLabel('telefonoFacturacion','<bean:message key="label.invoice.telefono"/>');
			   		form.setItemLabel('telefonoMovilFacturacion','<bean:message key="label.invoice.telefono.movil"/>');
			   		form.setItemLabel('emailFacturacion','<bean:message key="label.invoice.address.email"/>');
			   		form.setItemLabel('direccionWeb','<bean:message key="label.web.address"/>');
			   		form.setItemLabel('mrmResponsable','<bean:message key="label.mrm.responsible"/>');			   		
		    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
		    		
		    		form.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
		    				form.send("actualizarcanal.do?!nativeeditor_status=save","post", function(xml) {
		    					
		    				});
		    				window.close();
		    				goActualizar();
	    				}
		    		});
		    	});			    	
		    }		    
		    
		    function deleteChannel() {
		    	if (confirm("<bean:message key="message.confirm.delete.channel"/>")) {
		    		gridLeads.deleteSelectedRows();
		    		goActualizar();
		    	}
		    }
		    
		    function goActualizar() {
		    	buscar();
		    	toolbarCanales.disableItem('delete');
		    	tabbar.clearAll();
		    }	

		    
		    
		    function newChannelContact() {
				var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("contact", 300,50, 365, 420);
				window.setText('<bean:message key="title.new.contact" />');				
				window.setModal(true);
				window.centerOnScreen();
				var form = window.attachForm();			
		    	form.loadStruct('../xml/forms/contacto_canal_form.xml', function() {
		    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
		    		form.setItemLabel('nombre','<bean:message key="label.nombre"/>');
		    		form.setItemLabel('telefono','<bean:message key="label.telefono"/>');
		    		form.setItemLabel('telefonoMovil','<bean:message key="label.telefono.movil"/>');
		    		form.setItemLabel('direccion','<bean:message key="label.direccion"/>');
		    		form.setItemLabel('codigoPostal','<bean:message key="label.postal.code"/>');
		    		form.setItemLabel('ciudad','<bean:message key="label.ciudad"/>');
		    		form.setItemLabel('pais','<bean:message key="label.pais"/>');
		    		form.setItemLabel('email','<bean:message key="label.address.email"/>');
		    		form.setItemLabel('cargo','<bean:message key="label.position"/>');
		    		form.setItemLabel('comentarios','<bean:message key="label.comentarios"/>');
		    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');

		    				    					    			
		    		form.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
		    				form.send("actualizarcontactocanal.do?!nativeeditor_status=save&idCanal=" + idSelectedChannel,"post", function(xml) {
		    					
		    				});		    				
		    				window.close();
		    				goActualizarChannelContacts();
	    				}
		    		});
		    	});					
		    }

		    function deleteChannelContact() {
		    	if (confirm("<bean:message key="message.confirm.delete.distributor.contact"/>")) {
		    		gridContactos.deleteSelectedRows();
		    		toolbarContactos.disableItem('delete');
		    		toolbarContactos.disableItem('edit');
		    	}
		    }		    
		    
		    function goActualizarChannelContacts() {
	    		toolbarContactos.disableItem('delete');
	    		toolbarContactos.disableItem('edit');		    	
		    	gridContactos.clearAndLoad("gridcontactoscanal.do?idCanal="+idSelectedChannel);
		    }
		    
		    function updateChannelContact() {
				var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("contact", 300,50, 400, 535);
				window.setText('<bean:message key="layout.contact.edit" />');				
				window.setModal(true);
				window.centerOnScreen();
				var form = window.attachForm();			
		    	form.loadStruct('../xml/forms/contacto_canal_form.xml', function() {
		    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
		    		form.setItemLabel('nombre','<bean:message key="label.nombre"/>');
		    		form.setItemLabel('telefono','<bean:message key="label.telefono"/>');
		    		form.setItemLabel('telefonoMovil','<bean:message key="label.telefono.movil"/>');
		    		form.setItemLabel('direccion','<bean:message key="label.direccion"/>');
		    		form.setItemLabel('codigoPostal','<bean:message key="label.postal.code"/>');
		    		form.setItemLabel('ciudad','<bean:message key="label.ciudad"/>');
		    		form.setItemLabel('pais','<bean:message key="label.pais"/>');
		    		form.setItemLabel('email','<bean:message key="label.address.email"/>');
		    		form.setItemLabel('cargo','<bean:message key="label.position"/>');
		    		form.setItemLabel('comentarios','<bean:message key="label.comentarios"/>');
		    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');

		    		form.load('editarcontactocanal.do?idContacto=' + idSelectedContact, function () {
			   			form.attachEvent("onButtonClick", function(id){
			   				if (id == "aceptar") {
			    				form.send("actualizarcontactocanal.do?!nativeeditor_status=save&idContacto=" + idSelectedContact + "&idCanal=" + idSelectedChannel,"post", function(xml) {
			    					
			    				});
			    				window.close();
			    				goActualizarChannelContacts();
			   				}
			   			});
			   		});
		    		
		    	});			    	
		    }		    

		    function goExportar() {
			   	var accion = "exportarcanales.do";
			   	location.href=accion;	
		    }
		    
        </script>
	</head>
	<body>
	</body>
</html>