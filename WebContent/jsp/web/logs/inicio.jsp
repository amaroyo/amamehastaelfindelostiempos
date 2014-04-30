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
	    	
	    	var gridLeads, toolbarUsuarios, tabbar,tab_1,tab_2, form, idSelectedUserService, toolbarServicios, tab_3, gridServicios, idSelectedUser;
	    	
		    dhtmlxEvent(window,"load",function() {

		    	dhtmlxError.catchError("ALL",errorHandler);

		    	var main_layout = new dhtmlXLayoutObject(document.body, '2U');

		    	var a = main_layout.cells('a');
		    	a.hideHeader();

		    	gridLeads = a.attachGrid();
		    	gridLeads.setIconsPath('../skins/imgs/');
		    	
		    	gridLeads.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.user" />","<bean:message key="label.login.date" />"]);
		    	gridLeads.setColTypes("ro,ro,ro");
		    	gridLeads.attachHeader("#select_filter,#select_filter,#select_filter");
		    	gridLeads.setColSorting('str,str,str');
		    	gridLeads.init();		    			    	
				
		    	
		    	gridLeadsProcessor = new dataProcessor("gridusuarios.do");
				gridLeadsProcessor.enableUTFencoding('simple');
				gridLeadsProcessor.init(gridLeads);	  
				gridLeadsProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});		    	
		    	
		    	gridLeads.attachEvent("onRowSelect", function(idLogUsuario,ind){

		    		b.showHeader();
		    		b.setText("<bean:message key="title.comercial.propiedades" />");
		    		
			    	tabbar = b.attachTabbar();
			    	tabbar.addTab('tab_1','<bean:message key="label.propiedades"/>','');
			    	tab_1 = tabbar.cells('tab_1');
			    	tabbar.setTabActive('tab_1');
			    	form = tab_1.attachForm();
			    	form.loadStruct('../xml/forms/usuario_form.xml', function(){
			    		form.setItemLabel('data','<bean:message key="title.info.general"/>');
			    		form.setItemLabel('grupo','<bean:message key="label.group"/>');
			    		form.setItemLabel('nombre','<bean:message key="label.nombre"/>');
			    		form.setItemLabel('telefono','<bean:message key="label.telefono"/>');
			    		form.setItemLabel('telefonoMovil','<bean:message key="label.telefono.movil"/>');
			    		form.setItemLabel('direccion','<bean:message key="label.direccion"/>');
			    		form.setItemLabel('codigoPostal','<bean:message key="label.postal.code"/>');
			    		form.setItemLabel('ciudad','<bean:message key="label.ciudad"/>');
			    		form.setItemLabel('pais','<bean:message key="label.pais"/>');
			    		form.setItemLabel('email','<bean:message key="label.address.email"/>');
			    		form.setItemLabel('comentarios','<bean:message key="label.comentarios"/>');
			    		form.setItemLabel('user','<bean:message key="label.user"/>');
			    		form.setItemLabel('pass','<bean:message key="label.pass"/>');			    		
			    		form.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');	  		    	
						form.hideItem('aceptar');
	    					    						
			    		form.load('../usuarios/editarusuario.do?user=' + gridLeads.cells(idLogUsuario,1).getValue(), function () {
			    		});
			    	});
		    	});
		    	
		    	
		    	
		    	buscar();		    	

		    	var b = main_layout.cells('b');
		    	b.hideHeader();

		    	
		    });

	
		    function buscar() {
		    	gridLeads.clearAndLoad("gridlogsusuarios.do", function(){
			    	gridLeads.enableStableSorting(true);
			    	gridLeads.sortRows(2, "str", "des");
		    	});		    	
		    }
		
		    
	 	</script>
	 	
	 </head>
	<body>
	</body>
</html>