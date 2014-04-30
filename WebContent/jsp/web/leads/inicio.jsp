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
	    	
	    	var gridLeads, form, gridActions, idSelectedLead, tabbar, toolbar_1, toolbarActionsGrid, gridLeadsHistory, idSelectedFile;
	    	
	    	var filtrarEstadoWon = '<%=request.getSession().getAttribute("filtrarEstadoWon")%>';
	    	var filtrarEstadoLost = '<%=request.getSession().getAttribute("filtrarEstadoLost")%>';
	    	var creacionDesde = '<%=request.getSession().getAttribute("creacionDesde")%>';
	    	var creacionHasta = '<%=request.getSession().getAttribute("creacionHasta")%>';
	    	var modificacionDesde = '<%=request.getSession().getAttribute("modificacionDesde")%>';
	    	var modificacionHasta = '<%=request.getSession().getAttribute("modificacionHasta")%>';
	    	
		    dhtmlxEvent(window,"load",function() {

		    	dhtmlxError.catchError("ALL",errorHandler);

		    	var main_layout = new dhtmlXLayoutObject(document.body, '2U');

		    	var b = main_layout.cells('b');
		    	b.hideHeader();	
		    	b.setWidth(600);		    	
		    	
		    	var a = main_layout.cells('a');
		    	a.hideHeader();
		    	toolbar_1 = a.attachToolbar();
		    	toolbar_1.setIconsPath('../skins/imgs/toolbar/');
		    	
		    	toolbar_1.loadXML('../xml/toolbars/dhxtoolbar-leads.xml', function(){
		    		toolbar_1.setItemText('importar',"<bean:message key="button.importar"/>");
		    		toolbar_1.setItemText('exportar',"<bean:message key="button.exportar"/>");
		    		toolbar_1.setItemText('delete',"<bean:message key="button.eliminar"/>");
		    		toolbar_1.setItemText('new',"<bean:message key="button.create.lead"/>");
		    		toolbar_1.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
		    		if (filtrarEstadoWon == "false")
		    			toolbar_1.setItemText('statusFilter',"<bean:message key="button.hide.wons"/>");
		    		else
		    			toolbar_1.setItemText('statusFilter',"<bean:message key="button.show.wons"/>");
		    		
		    		if (filtrarEstadoLost == "false")	
		    			toolbar_1.setItemText('LostStatusFilter',"<bean:message key="button.hide.lost"/>");
		    		else
		    			toolbar_1.setItemText('LostStatusFilter',"<bean:message key="button.show.lost"/>");
		    		
		    		toolbar_1.setItemText('dateFilter',"<bean:message key="button.date.filter"/>");

		    		
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>3</permiso>" >		    	
							toolbar_1.hideItem('new');
							toolbar_1.hideItem('sep1');
	    				</logic:notMatch>
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>4</permiso>" >		    	
							toolbar_1.hideItem('delete');
							toolbar_1.hideItem('sep2');
						</logic:notMatch>    				
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>6</permiso>" >		    	
							toolbar_1.hideItem('importar');
							toolbar_1.hideItem('sep3');
						</logic:notMatch> 
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>7</permiso>" >		    	
							toolbar_1.hideItem('exportar');
							toolbar_1.hideItem('sep4');
						</logic:notMatch> 	
					</logic:notMatch>		    		
		    		
		    	});

		    	
		    	gridLeads = a.attachGrid();
		    	gridLeads.setIconsPath('../skins/imgs/');
		    	
		    	gridLeads.setHeader(["<bean:message key="label.channel"/>","<bean:message key="label.empresa"/>","<bean:message key="label.comercial" />","<bean:message key="label.distribuidor" />","<bean:message key="label.servicio" />","<bean:message key="label.estado" />","<bean:message key="label.creation.date" />","<bean:message key="label.last.modification.date" />"]);
		    	gridLeads.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro");
		    	gridLeads.attachHeader("#select_filter,#select_filter,#select_filter,#select_filter,#select_filter,#select_filter,#select_filter,#select_filter");
		    	gridLeads.setColSorting('str,str,str,str,str,str,str,str');
//		    	gridLeads.enableStableSorting(true);
//		    	gridLeads.sortRows(7, "str", "des");
		    	gridLeads.enableMultiselect(true);
		    	gridLeads.init();
				
				gridLeadsProcessor = new dataProcessor("gridleads.do");
				gridLeadsProcessor.enableUTFencoding('simple');				
				gridLeadsProcessor.init(gridLeads);	  
				
				gridLeadsProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	}); 
		    		    	
				
		    	gridLeads.attachEvent("onRowSelect", function(idLead,ind){
		    		idSelectedLead = idLead;
		    		toolbar_1.enableItem('delete');
		    		
			    	tabbar = b.attachTabbar();
			    	tabbar.addTab('tab_1','<bean:message key="label.propiedades"/>','');
			    	var tab_1 = tabbar.cells('tab_1');
			    	tabbar.setTabActive('tab_1');
			    	var form = tab_1.attachForm();
			    	form.loadStruct('../xml/forms/lead_form.xml', function() {
			    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
			    		form.setItemLabel('canal','<bean:message key="label.channel"/>');
			    		form.setItemLabel('servicio','<bean:message key="label.servicio"/>');
			    		form.setItemLabel('comercial','<bean:message key="label.comercial"/>');
			    		form.setItemLabel('responsable','<bean:message key="label.responsable"/>');
			    		form.setItemLabel('distribuidor','<bean:message key="label.distribuidor"/>');
			    		form.setItemLabel('empresa','<bean:message key="label.empresa"/>');
			    		form.setItemLabel('fechaVisita','<bean:message key="label.fecha.visita"/>');
			    		form.setItemLabel('estado','<bean:message key="label.estado"/>');
			    		form.setItemLabel('marketingActivity','<bean:message key="label.marketing.activity"/>');
			    		form.setItemLabel('comentarios','<bean:message key="label.comentarios"/>');
			    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
			    		form.setItemLabel('usuariosPotenciales','<bean:message key="label.potential.users"/>');
			    		form.setItemLabel('usuarios','<bean:message key="label.users"/>');

			    		form.getCombo('canal').readonly(1);
			    		form.getCombo('canal').loadXML("listarcanales.do");
			    		form.getCombo('servicio').readonly(1);
			    		form.getCombo('servicio').loadXML("listarservicios.do");
			    		form.getCombo('distribuidor').readonly(1);
			    		form.getCombo('distribuidor').loadXML("listardistribuidores.do");	
			    		form.getCombo('comercial').readonly(1);
			    		form.getCombo('comercial').loadXML("listarcomerciales.do");
			    		form.getCombo('responsable').readonly(1);
			    		form.getCombo('responsable').loadXML("listarresponsables.do");
			    		form.getCombo('empresa').readonly(1);
			    		form.getCombo('empresa').loadXML("listarempresas.do");

						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
							<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>5</permiso>" >		    	
								form.hideItem('aceptar');
							</logic:notMatch>
						</logic:notMatch>
						
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<grupo>1</grupo>" >
							<logic:match scope="session" name="usuarioYPermisos" value="<grupo>4</grupo>" >		    	
								form.disableItem('canal');
								form.disableItem('distribuidor');
								form.disableItem('comercial');
							</logic:match>
							<logic:match scope="session" name="usuarioYPermisos" value="<grupo>3</grupo>" >		    	
								form.disableItem('canal');
								form.disableItem('distribuidor');
							</logic:match>					
							<logic:match scope="session" name="usuarioYPermisos" value="<grupo>2</grupo>" >		    	
								form.disableItem('canal');
							</logic:match>	
						</logic:notMatch>
						
			    		form.load('editarlead.do?idLead=' + idLead, function () {			    			
			    			form.attachEvent("onButtonClick", function(id){
			    				if (id == "aceptar") {
				    				form.send("actualizarlead.do?!nativeeditor_status=save&idLead=" + idLead ,"post", function(xml) {
				    					
				    				});
				    				buscar();
				    				gridLeadsHistory.load('gridleadshistory.do?idLead='+idLead, 'xml');
			    				}
			    			});
			    		});
			    	});				    		


			    	
			    	
				  	//añadir tab con ficheros
				   	tabbar.addTab('tab_5','<bean:message key="layout.files.list"/>','');
				   	tab_5 = tabbar.cells('tab_5');				
			    	toolbarFicheros = tab_5.attachToolbar();
			    	toolbarFicheros.setIconsPath('../skins/imgs/toolbar/');
			    	
			    	toolbarFicheros.loadXML('../xml/toolbars/dhxtoolbar-ficheros.xml', function(){
			    		toolbarFicheros.setItemText('new',"<bean:message key="button.add.file"/>");
			    		toolbarFicheros.setItemText('delete',"<bean:message key="button.eliminar"/>");
			    		toolbarFicheros.setItemText('refresh',"<bean:message key="button.actualizar"/>");
			    		toolbarFicheros.setItemText('download',"<bean:message key="button.download"/>");
			    		
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
							<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>5</permiso>" >		    	
								toolbarFicheros.hideItem('new');
								toolbarFicheros.hideItem('sep1');
								toolbarFicheros.hideItem('delete');
								toolbarFicheros.hideItem('sep2');
							</logic:notMatch>
						</logic:notMatch>			    		
			    		
			    	});
		            
			    	gridFicheros = tab_5.attachGrid();
			    	gridFicheros.setIconsPath('../skins/imgs/');
			    	
			    	gridFicheros.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.description" />","<bean:message key="label.last.download" />"]);
			    	gridFicheros.setColTypes("ro,ro,ro");
			    	
			    	gridFicheros.setColSorting('str,str,str');
			    	gridFicheros.load("gridficheros.do?idLead="+idLead);
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
					<logic:match scope="session" name="usuarioYPermisos" value="<permiso>20</permiso>" >
						or = true;
					</logic:match>
			    	
					if (or) {			    	
				    	var idEmpresa = gridLeads.getUserData(idLead, "idEmpresa");
				    	tabbar.addTab('tab_2','<bean:message key="label.empresa"/>','');
				    	var tab_2 = tabbar.cells('tab_2');
				    	var form_company = tab_2.attachForm();
				    	form_company.loadStruct('../xml/forms/empresa_form.xml', function() {
				    		form_company.setItemLabel('data','<bean:message key="title.datos.empresa"/>');
				    		form_company.setItemLabel('nombre','<bean:message key="label.nombre"/>');
				    		form_company.setItemLabel('direccion','<bean:message key="label.direccion"/>');
				    		form_company.setItemLabel('ciudad','<bean:message key="label.ciudad"/>');
				    		form_company.setItemLabel('codigoPostal','<bean:message key="label.postal.code"/>');
				    		form_company.setItemLabel('telefono','<bean:message key="label.telefono"/>');
				    		form_company.setItemLabel('telefonoMovil','<bean:message key="label.telefono.movil"/>');
				    		form_company.setItemLabel('informacionContacto','<bean:message key="label.contacto"/>');
				    		form_company.setItemLabel('orgnr','<bean:message key="label.orgnr"/>');
				    		form_company.setItemLabel('comentarios','<bean:message key="label.comments"/>');
				    		form_company.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
				    		
	
							<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
								<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>23</permiso>" >		    	
									form_company.hideItem('aceptar');
								</logic:notMatch>
							</logic:notMatch>
				    		
				    		form_company.load('../empresas/editarempresa.do?idEmpresa=' + idEmpresa, function() {
				    			form_company.attachEvent("onButtonClick", function(id){
				    				if (id == "aceptar") {
				    					form_company.send("../empresas/actualizarempresa.do?!nativeeditor_status=update&idEmpresa=" + idEmpresa ,"post", function(xml) {
					    					
					    				});
				    				}
				    			});			    			
				    		});
				    	});			    		
					}
					
					//TAB ACTIONS HISTORY			    	
			    	tabbar.addTab('tab_3','<bean:message key="title.actions.history"/>','');
			    	var tab_3 = tabbar.cells('tab_3');
			    	var actionsLayout = tab_3.attachLayout("2E");
			    	var layoutActionsForm = actionsLayout.cells('a');
			    	var layoutActionsGrid = actionsLayout.cells('b');
			    	
			    	layoutActionsForm.hideHeader();
			    	layoutActionsForm.setHeight('120');
			    	layoutActionsForm.fixSize(0,1);
			    	layoutActionsGrid.hideHeader();
			    	
			    				    
			    	var form_actions = layoutActionsForm.attachForm();
			    	form_actions.loadStruct('../xml/forms/actions_history_form.xml', function() {
			    		form_actions.setItemLabel('data','<bean:message key="title.new.action"/>');
			    		form_actions.setItemLabel('action','<bean:message key="label.accion"/>');
			    		form_actions.setItemLabel('comments','<bean:message key="label.comentarios"/>');
			    		form_actions.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');

						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
							<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>5</permiso>" >		    	
								form_actions.disableItem('aceptar');
								form_actions.disableItem('sep1');							
							</logic:notMatch>
						</logic:notMatch>			    		
			    		
			    		form_actions.attachEvent("onButtonClick", function(id){
		    				if (id == "aceptar") {
		    					form_actions.send("actionshistory.do?!nativeeditor_status=save&idLead=" + idLead ,"post", function(xml) {
		    						gridActions.clearAndLoad("gridactionshistory.do?idLead="+idLead);
			    				});
		    				}
		    			});			    			
		    			
			    	});		
			    	
			    	
			    	toolbarActionsGrid = layoutActionsGrid.attachToolbar();
			    	toolbarActionsGrid.setIconsPath('../skins/imgs/toolbar/');										
			    	toolbarActionsGrid.loadXML('../xml/toolbars/dhxtoolbar-actions.xml', function(){
			    		toolbarActionsGrid.setItemText('delete',"<bean:message key="button.eliminar"/>");
			    		toolbarActionsGrid.setItemText('refresh',"<bean:message key="button.actualizar"/>");

						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
							<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>5</permiso>" >		    	
								toolbarActionsGrid.hideItem('delete');
								toolbarActionsGrid.hideItem('sep1');
							</logic:notMatch>
						</logic:notMatch>
			    		
			    	});

			    	
			    	gridActions = layoutActionsGrid.attachGrid();
			    	gridActions.setHeader(['<bean:message key="label.fecha" />','<bean:message key="label.accion" />','<bean:message key="label.comentarios" />']);
			    	gridActions.setColTypes("ro,ro,ro");
			    	gridActions.setInitWidths("100,100,*"); 
			    	gridActions.setColAlign('left, left,left');
			    	gridActions.enableRowsHover(true,'hoverGrid');
			    	gridActions.enableMultiselect(false);
			    	gridActions.load("gridactionshistory.do?idLead="+idLead);
			    	gridActions.init();
			    	gridActions.attachEvent("onRowSelect", function(id,ind){
			    		toolbarActionsGrid.enableItem('delete');
					});
			    	
					gridActionsProcessor = new dataProcessor("gridactionshistory.do");
					gridActionsProcessor.enableUTFencoding('simple');
					gridActionsProcessor.init(gridActions);	  
					gridActionsProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
						if(action == 'error'){
			    			dhtmlx.message(tag.firstChild.data,action,4000);
			    		}
			    	}); 

					// TAB LEADS HISTORY
			    	tabbar.addTab('tab_4','<bean:message key="title.leads.history"/>','');
			    	tab_4 = tabbar.cells('tab_4');
			    	gridLeadsHistory = tab_4.attachGrid();
			    	
			    	gridLeadsHistory.setHeader(["<bean:message key="label.action" />","<bean:message key="label.fecha" />","<bean:message key="label.field" />","<bean:message key="label.old.value" />","<bean:message key="label.new.value" />"]);
			    	gridLeadsHistory.setColTypes("ro,ro,ro,ro,ro");
			    	gridLeadsHistory.setColSorting('str,str,str,str,str');
			    	gridLeadsHistory.load('gridleadshistory.do?idLead='+idLead, 'xml');
			    	gridLeadsHistory.init();
					
					
		    		
		    	});

		    	
		    	buscar();		    	


		    });

	
		    function buscar() {
			   	var accion = "gridleads.do";	
			   	accion += "?filtrarEstadoWon="+filtrarEstadoWon;
			   	accion += "&filtrarEstadoLost="+filtrarEstadoLost;
			   	accion += "&creacionDesde="+creacionDesde;
			   	accion += "&creacionHasta="+creacionHasta;
			   	accion += "&modificacionDesde="+modificacionDesde;
			   	accion += "&modificacionHasta="+modificacionHasta;		 
			   	gridLeads.clearAndLoad(accion, function(){
			    	gridLeads.enableStableSorting(true);
			    	gridLeads.sortRows(7, "str", "des");
		    	});
		    }
		    
		    
		    function goImportar() {
				var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("importar", 300,50, 500, 300);
				window.setText('<bean:message key="title.import.leads" />');				
				window.setModal(true);
				window.centerOnScreen();
				window.attachURL("importar.do");
				goActualizar();
		    }
		    
		    function deleteActionHistory() {
		    	if (confirm("<bean:message key="message.confirm.delete.action"/>")) {
		    		gridActions.deleteSelectedRows();
		    		goActualizarActions();
		    	}
		    }
		    
		    function deleteLead() {
		    	if (confirm("<bean:message key="message.confirm.delete.lead"/>")) {
		    		gridLeads.deleteSelectedRows();
		    		goActualizar();
		    	}
		    }
		    
		    function newLead() {
				var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("lead", 300,50, 380, 540);
				window.setText('<bean:message key="title.new.lead" />');				
				window.setModal(true);
				window.centerOnScreen();
				var form = window.attachForm();			
		    	form.loadStruct('../xml/forms/new_lead_form.xml', function() {
		    		
		    		
		    		form.setItemLabel('data2','<bean:message key="title.datos.empresa"/>');
		    		form.setItemLabel('nombre','<bean:message key="label.nombre"/>');
		    		form.disableItem('nombre');
		    		form.setItemLabel('telefono','<bean:message key="label.telefono"/>');
		    		form.disableItem('telefono');
		    		form.setItemLabel('telefonoMovil','<bean:message key="label.telefono.movil"/>');
		    		form.disableItem('telefonoMovil');
		    		form.setItemLabel('email','<bean:message key="label.address.email"/>');
		    		form.disableItem('email');
		    		form.setItemLabel('informacionContacto','<bean:message key="label.contacto"/>');
		    		form.disableItem('informacionContacto');
		    		form.setItemLabel('orgnr','<bean:message key="label.orgnr"/>');
		    		
		    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
		    		form.setItemLabel('canal','<bean:message key="label.channel"/>');
		    		form.setItemLabel('servicio','<bean:message key="label.servicio"/>');
		    		form.setItemLabel('comercial','<bean:message key="label.comercial"/>');
		    		form.setItemLabel('responsable','<bean:message key="label.responsable"/>');
		    		form.setItemLabel('distribuidor','<bean:message key="label.distribuidor"/>');
		    		//form.setItemLabel('empresa','<bean:message key="label.empresa"/>');
		    		form.setItemLabel('estado','<bean:message key="label.estado"/>');
		    		form.setItemLabel('marketingActivity','<bean:message key="label.marketing.activity"/>');
		    		form.setItemLabel('comentarios','<bean:message key="label.comentarios"/>');
		    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
		    		form.setItemLabel('usuariosPotenciales','<bean:message key="label.potential.users"/>');
		    		form.setItemLabel('usuarios','<bean:message key="label.users"/>');
		    		
		    		form.getCombo('canal').readonly(1);
		    		form.getCombo('canal').loadXML("listarcanales.do");
		    		form.getCombo('servicio').readonly(1);
		    		form.getCombo('servicio').loadXML("listarservicios.do");
		    		form.getCombo('comercial').readonly(1);
		    		form.getCombo('comercial').loadXML("listarcomerciales.do");
		    		form.getCombo('responsable').readonly(1);
		    		form.getCombo('responsable').loadXML("listarresponsables.do");		    		
		    		form.getCombo('distribuidor').readonly(1);
		    		form.getCombo('distribuidor').loadXML("listardistribuidores.do");
		    		//form.getCombo('empresa').readonly(1);
		    		//form.getCombo('empresa').loadXML("listarempresas.do");		    		

		    				    		
		    		form.attachEvent("onChange", function(id){		    			
		    			if (id == "orgnr") {
		    				form.load('../empresas/cargarempresa.do?orgnr=' + form.getItemValue("orgnr"), function(){
			    				if (form.getItemValue("nombre") == null) {
			    					form.enableItem('nombre');
			    					form.enableItem('telefono');
			    					form.enableItem('telefonoMovil');
			    					form.enableItem('email');
			    					form.enableItem('informacionContacto');
			    				} else {
			    					form.disableItem('nombre');
			    					form.disableItem('telefono');
			    					form.disableItem('telefonoMovil');
			    					form.disableItem('email');
			    					form.disableItem('informacionContacto');	    					
			    				}
		    				});
		    			}
		    		})
		    		
		    		form.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
	    					if (form.getCombo('canal').getActualValue() != "" && form.getCombo('servicio').getActualValue() != "" &&
	    						form.getCombo('comercial').getActualValue() != "" && form.getCombo('responsable').getActualValue() != "" &&
	    						form.getCombo('distribuidor').getActualValue() != "" && form.getItemValue('orgnr') != null ) { //form.getCombo('empresa').getActualValue() != ""	) {
	    						
	    							    						
				    				form.send("../empresas/actualizarempresa.do?!nativeeditor_status=save","post", function(xml) {});	    							    						
				    				form.send("nuevolead.do?!nativeeditor_status=save","post", function(xml) {});
				    				
				    				window.close();
				    				goActualizar();
	    					} else {
	    						var alerta = '<bean:message key="message.empty.fields"/>';
	    						if (form.getItemValue('orgnr') == null) alerta += "\r- "+'<bean:message key="label.orgnr"/>';	    						
	    						if (form.getCombo('canal').getActualValue() == "") alerta += "\r- "+'<bean:message key="label.channel"/>';
	    						if (form.getCombo('servicio').getActualValue() == "") alerta += "\r- "+'<bean:message key="label.servicio"/>';
	    						if (form.getCombo('comercial').getActualValue() == "") alerta += "\r- "+'<bean:message key="label.comercial"/>';
	    						if (form.getCombo('responsable').getActualValue() == "") alerta += "\r- "+'<bean:message key="label.responsable"/>';
	    						if (form.getCombo('distribuidor').getActualValue() == "") alerta += "\r- "+'<bean:message key="label.distribuidor"/>';
	    						//if (form.getCombo('empresa').getActualValue() == "") alerta += "\r- "+'<bean:message key="label.empresa"/>';
	    						alert(alerta);
	    					}
	    				}
		    		});
		    	});	
		    	
		    			    	
		    	

		    }		 
		    		    
		    function goActualizar() {
		    	buscar();
		    	toolbar_1.disableItem('delete');
		    	tabbar.clearAll();		    	
		    }
		    
		    function goActualizarActions() {
		    	toolbarActionsGrid.disableItem('delete');
		    	gridActions.clearAndLoad("gridactionshistory.do?idLead="+idSelectedLead);
		    }

		    function changeStatusFilter() {
		    	if (filtrarEstadoWon == "false"){
		    		toolbar_1.setItemText('statusFilter',"<bean:message key="button.show.wons"/>");
		    		filtrarEstadoWon= "true";
		    		goActualizar();
		    	} else {
		    		toolbar_1.setItemText('statusFilter',"<bean:message key="button.hide.wons"/>");
		    		filtrarEstadoWon= "false";
		    		goActualizar();
		    	}
		    	
		    }
		    
		    function changeLostStatusFilter() {
		    	if (filtrarEstadoLost == "false"){
		    		toolbar_1.setItemText('LostStatusFilter',"<bean:message key="button.show.lost"/>");
		    		filtrarEstadoLost= "true";
		    		goActualizar();
		    	} else {
		    		toolbar_1.setItemText('LostStatusFilter',"<bean:message key="button.hide.lost"/>");
		    		filtrarEstadoLost= "false";
		    		goActualizar();
		    	}
		    	
		    }
		    
		    function goExportar() {
			   	var accion = "exportarleads.do";	
			   	accion += "?filtrarEstadoWon="+filtrarEstadoWon;
			   	accion += "&filtrarEstadoLost="+filtrarEstadoLost;
			   	accion += "&creacionDesde="+creacionDesde;
			   	accion += "&creacionHasta="+creacionHasta;
			   	accion += "&modificacionDesde="+modificacionDesde;
			   	accion += "&modificacionHasta="+modificacionHasta;
			   	location.href=accion;	
		    }

		    function datesFilter() {
				var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("lead", 300,50, 360, 280);
				window.setText('<bean:message key="label.filter.dates" />');				
				window.setModal(true);
				window.centerOnScreen();
				var form = window.attachForm();					
		    	form.loadStruct('../xml/forms/fecha_form.xml', function() {
		    		
		    		form.setItemLabel('fechaCreacion','<bean:message key="title.creation"/>');
		    		form.setItemLabel('fechaCreacionDesde','<bean:message key="label.from"/>');
		    		form.setItemLabel('fechaCreacionHasta','<bean:message key="label.till"/>');
		    		form.setItemLabel('fechaModificacion','<bean:message key="title.last.modify"/>');
		    		form.setItemLabel('fechaModificacionDesde','<bean:message key="label.from"/>');
		    		form.setItemLabel('fechaModificacionHasta','<bean:message key="label.till"/>');
		    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
		    		
		    		var date = (form.getCalendar("fechaCreacionHasta")).getFormatedDate("%Y/%m/%d");
		    		form.setItemValue("fechaCreacionDesde", date);
		    		form.setItemValue("fechaCreacionHasta", date);
		    		form.setItemValue("fechaModificacionDesde", date);
		    		form.setItemValue("fechaModificacionHasta", date);
		    		
		    		form.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {							
							var calendar = form.getCalendar("fechaCreacionDesde");							
							var date = form.getItemValue("fechaCreacionDesde");
							creacionDesde = calendar.getFormatedDate("%Y/%m/%d", date);							
							date = form.getItemValue("fechaCreacionHasta");
							creacionHasta = calendar.getFormatedDate("%Y/%m/%d", date);							
							date = form.getItemValue("fechaModificacionDesde");
							modificacionDesde = calendar.getFormatedDate("%Y/%m/%d", date);							
							date = form.getItemValue("fechaModificacionHasta");
							modificacionHasta = calendar.getFormatedDate("%Y/%m/%d", date);
							window.close();
							goActualizar();
	    				}
		    		});	
		    	});
		    	
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
				window.attachURL("subir.do?idLead=" + idSelectedLead);
		    }

		    function goActualizarFiles() {
	    		toolbarFicheros.disableItem('delete');
	    		toolbarFicheros.disableItem('download');
		    	gridFicheros.clearAndLoad("gridficheros.do?idLead="+idSelectedLead);
		    }
		      
		    function downloadFile() {
				var archivo = "descargar.do?idFichero=" + idSelectedFile;
				location.href=archivo;
		    }		    
		    
	 	</script>
 		 	
	 	
	 </head>
	<body>
	</body>
</html>