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
	    	
	    	var gridLeads, toolbarComerciales, tabbar,tab_1,tab_2, form;
	    	
		    dhtmlxEvent(window,"load",function() {

		    	dhtmlxError.catchError("ALL",errorHandler);

		    	var main_layout = new dhtmlXLayoutObject(document.body, '2U');

		    	var a = main_layout.cells('a');
		    	a.hideHeader();
		    	toolbarComerciales = a.attachToolbar();
		    	toolbarComerciales.setIconsPath('../skins/imgs/toolbar/');
		    	
		    	toolbarComerciales.loadXML('../xml/toolbars/dhxtoolbar-comerciales.xml', function(){
		    		toolbarComerciales.setItemText('new',"<bean:message key="button.create.sales.rep"/>");
		    		toolbarComerciales.setItemText('delete',"<bean:message key="button.eliminar"/>");
		    		toolbarComerciales.setItemText('exportar',"<bean:message key="button.exportar"/>");
		    		toolbarComerciales.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>9</permiso>" >		    	
							toolbarComerciales.hideItem('new');
							toolbarComerciales.hideItem('sep1');
	    				</logic:notMatch>
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>10</permiso>" >		    	
							toolbarComerciales.hideItem('delete');
							toolbarComerciales.hideItem('sep2');
						</logic:notMatch> 
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>38</permiso>" >		    	
							toolbarComerciales.hideItem('exportar');
							toolbarComerciales.hideItem('sep3');
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
		    	
				gridLeadsProcessor = new dataProcessor("gridcomerciales.do");
				gridLeadsProcessor.enableUTFencoding('simple');
				gridLeadsProcessor.init(gridLeads);	  
				gridLeadsProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});		    	
		    	
		    	gridLeads.attachEvent("onRowSelect", function(idComercial,ind){
		    		toolbarComerciales.enableItem('delete');
		    		
		    		b.showHeader();
		    		b.setText("<bean:message key="title.comercial.propiedades" />");

		    		
			    	tabbar = b.attachTabbar();
			    	tabbar.addTab('tab_1','<bean:message key="label.propiedades"/>','');
			    	tab_1 = tabbar.cells('tab_1');
			    	tabbar.setTabActive('tab_1');
			    	form = tab_1.attachForm();
			    	form.loadStruct('../xml/forms/comercial_form.xml', function(){
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
			    		
			    		form.load('editarcomercial.do?idComercial=' + idComercial, function () {			    			
			    			form.attachEvent("onButtonClick", function(id){
			    				if (id == "aceptar") {
				    				form.send("actualizarcomercial.do?!nativeeditor_status=save&idComercial=" + idComercial ,"post", function(xml) {
				    					
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
					    	//grid_1.setIconsPath('../codebase/imgs/');
					    	
					    	grid_1.setHeader(["<bean:message key="label.empresa" />","<bean:message key="label.distribuidor" />","<bean:message key="label.responsable" />","<bean:message key="label.servicio" />","<bean:message key="label.estado"/>","<bean:message key="label.last.modification.date"/>"]);
					    	grid_1.setColTypes("ro,ro,ro,ro,ro,ro");
					    	grid_1.attachHeader("#select_filter,#select_filter");
					    	grid_1.setColSorting('str,str,str,str,str,str');
					    	grid_1.init();
					    	grid_1.load('gridleads.do?idComercial=' + idComercial, function(){
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
		    	gridLeads.clearAndLoad("gridcomerciales.do");		    	
		    }
		
		    function newSalesRep() {
				var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("sales rep.", 300, 50, 385, 425);
				window.setText('<bean:message key="title.new.sales.rep" />');				
				window.setModal(true);
				window.centerOnScreen();
				var form = window.attachForm();			
		    	form.loadStruct('../xml/forms/new_comercial_form.xml', function() {
		    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
		    		form.setItemLabel('idDistribuidor','<bean:message key="label.distribuidor"/>');
		    		form.getCombo('idDistribuidor').readonly(1);
		    		form.getCombo('idDistribuidor').loadXML("../leads/listardistribuidores.do");		    		
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
		    				    					    			
		    		form.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
	    					if (form.getCombo('idDistribuidor').getActualValue() != "") {
			    				form.send("actualizarcomercial.do?!nativeeditor_status=save","post", function(xml) {
			    					
			    				});
			    				window.close();
			    				goActualizar();
	    					} else {
	    						var alerta = '<bean:message key="message.empty.fields"/>';
	    						if (form.getCombo('idDistribuidor').getActualValue() == "") alerta += "\r- "+'<bean:message key="label.distribuidor"/>';
	    						alert(alerta);
	    					}
	    				}
		    		});
		    	});
		    }		    
		    
		    function deleteComercial() {
		    	if (confirm("<bean:message key="message.confirm.delete.sales.rep"/>")) {
		    		gridLeads.deleteSelectedRows();
		    		goActualizar();
		    	}
		    }
		    
		    function goActualizar() {
		    	buscar();
		    	toolbarComerciales.disableItem('delete');
		    	tabbar.clearAll();		    	
		    }
		    
		    function goExportar() {
			   	var accion = "exportarcomerciales.do";
			   	location.href=accion;	
		    }
		    
	 	</script>
	 	
	 </head>
	<body>
	</body>
</html>