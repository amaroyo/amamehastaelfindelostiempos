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
	    	
	    	var gridLeads, toolbarResponsables, form, tabbar;
	    	
		    dhtmlxEvent(window,"load",function() {

		    	dhtmlxError.catchError("ALL",errorHandler);

		    	var main_layout = new dhtmlXLayoutObject(document.body, '2U');

		    	var a = main_layout.cells('a');
		    	a.hideHeader();
		    	toolbarResponsables = a.attachToolbar();
		    	toolbarResponsables.setIconsPath('../skins/imgs/toolbar/');
		    	
		    	toolbarResponsables.loadXML('../xml/toolbars/dhxtoolbar-responsables.xml', function(){
		    		toolbarResponsables.setItemText('new',"<bean:message key="button.create.responsible"/>");
		    		toolbarResponsables.setItemText('delete',"<bean:message key="button.eliminar"/>");
		    		toolbarComerciales.setItemText('exportar',"<bean:message key="button.exportar"/>");
		    		toolbarResponsables.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
					<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>13</permiso>" >		    	
							toolbarResponsables.hideItem('new');
							toolbarResponsables.hideItem('sep1');
	    				</logic:notMatch>
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>14</permiso>" >		    	
							toolbarResponsables.hideItem('delete');
							toolbarResponsables.hideItem('sep2');
						</logic:notMatch>   
						<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>39</permiso>" >		    	
							toolbarResponsables.hideItem('exportar');
							toolbarResponsables.hideItem('sep3');
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
		    	
				gridLeadsProcessor = new dataProcessor("gridresponsables.do");
				gridLeadsProcessor.enableUTFencoding('simple');
				gridLeadsProcessor.init(gridLeads);	  
				gridLeadsProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
		    	
		    	gridLeads.attachEvent("onRowSelect", function(idResponsable,ind){
		    		toolbarResponsables.enableItem('delete');
		    		
		    		b.showHeader();
		    		b.setText("<bean:message key="title.responsable.propiedades"/>");

		    		
			    	tabbar = b.attachTabbar();
			    	tabbar.addTab('tab_1','<bean:message key="label.propiedades"/>','');
			    	tab_1 = tabbar.cells('tab_1');
			    	tabbar.setTabActive('tab_1');
			    	form = tab_1.attachForm();
			    	form.loadStruct('../xml/forms/responsable_form.xml', function(){
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
							<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>15</permiso>" >		    	
								form.hideItem('aceptar');
							</logic:notMatch>
						</logic:notMatch>			    		
			    		
			    		form.load('editarresponsable.do?idResponsable=' + idResponsable, function () {			    			
			    			form.attachEvent("onButtonClick", function(id){
			    				if (id == "aceptar") {
				    				form.send("actualizarresponsable.do?!nativeeditor_status=save&idResponsable=" + idResponsable ,"post", function(xml) {
				    					
				    				});
				    				buscar();
			    				}
			    			});
			    		});
			    	});
/*
			    	tabbar.addTab('tab_2','<bean:message key="label.comerciales"/>','');
			    	tab_2 = tabbar.cells('tab_2');
			    	var grid_1 = tab_2.attachGrid();
			    	
			    	grid_1.setHeader(["<bean:message key="label.nombre"/>"]);
			    	grid_1.setColTypes("ro");
			    	//grid_1.attachHeader("#select_filter");
			    	grid_1.setColSorting('str');
			    	grid_1.init();
			    	grid_1.load('gridcomerciales.do?idResponsable=' + idResponsable, 'xml');
			    	grid_1.attachEvent("onRowSelect", function(id,ind){
						var dhxWins= new dhtmlXWindows();
						var window = dhxWins.createWindow("comercial", 300,50, 365, 390);
						window.setText('<bean:message key="label.comercial"/>');				
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
				    		
				    		form.load('../comerciales/editarcomercial.do?idComercial=' + id);
				    	});				    	
			    		
			    	});
*/			    		    				    	
			    	
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
				    	
				    	grid_2.setHeader(["<bean:message key="label.empresa"/>","<bean:message key="label.distribuidor"/>","<bean:message key="label.comercial"/>","<bean:message key="label.servicio"/>","<bean:message key="label.estado"/>","<bean:message key="label.last.modification.date"/>"]);
				    	grid_2.setColTypes("ro,ro,ro,ro,ro,ro");
				    	grid_2.attachHeader("#select_filter,#select_filter");
				    	grid_2.setColSorting('str,str,str,str,str,str');
				    	grid_2.init();
				    	grid_2.load('gridleads.do?idResponsable=' + idResponsable, function(){
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
		    	gridLeads.clearAndLoad("gridresponsables.do");		    	
		    }

		    function newResponsible() {
				var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("responsible", 300,50, 365, 410);
				window.setText('<bean:message key="title.new.responsible" />');				
				window.setModal(true);
				window.centerOnScreen();
				var form = window.attachForm();			
		    	form.loadStruct('../xml/forms/new_responsable_form.xml', function() {
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
		    				    					    			
		    		form.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
		    				form.send("actualizarresponsable.do?!nativeeditor_status=save","post", function(xml) {
		    					
		    				});
		    				window.close();
		    				goActualizar();
	    				}
		    		});
		    	});			    	
		    }		    
		    
		    function deleteResponsable() {
		    	if (confirm("<bean:message key="message.confirm.delete.responsible"/>")) {
		    		gridLeads.deleteSelectedRows();
		    		goActualizar();
		    	}
		    }
		    
		    function goActualizar() {
		    	buscar();
		    	toolbarResponsables.disableItem('delete');
		    	tabbar.clearAll();		    	
		    }
		    
		    function goExportar() {
			   	var accion = "exportarresponsables.do";
			   	location.href=accion;	
		    }		    
	 	</script>
	 	
	 </head>
	<body>
	</body>
</html>