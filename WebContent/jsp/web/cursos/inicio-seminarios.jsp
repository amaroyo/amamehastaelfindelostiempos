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
	    	
	    	var main_layout, areaTrabajoCursos, listado, toolbarSeminarios,
	    	gridSeminarios, tabbarSeminarios, tabInfo, formInfo, idSeminario;
	    	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	dhtmlxError.catchError("ALL",errorHandler);
			    main_layout = new dhtmlXLayoutObject(document.body, '2U');
			    listado = main_layout.cells('a');
			    listado.setWidth(600);
			    areaTrabajoCursos = main_layout.cells('b');


			    
		    	listado.setText("<strong><bean:message key="title.seminarios" /></strong>");
			    areaTrabajoCursos.setText("<bean:message key="title.propiedades.seminario" />");
			    	
			    toolbarSeminarios = listado.attachToolbar();
			    toolbarSeminarios.setIconsPath('../js/dhtmlxSuite/imgs/toolbar/');
			    toolbarSeminarios.loadXML('../xml/toolbars/dhxtoolbar-seminarios.xml', function(){
		    		toolbarSeminarios.setItemText('new',"<bean:message key="button.create.seminario"/>");
		    		toolbarSeminarios.setItemText('delete',"<bean:message key="button.eliminar.seminario"/>");
			    	toolbarSeminarios.setItemText('refresh',"<bean:message key="button.actualizar"/>");
			    	toolbarSeminarios.hideItem("delete");
			    	toolbarSeminarios.hideItem("sep1");
	    		//permisosToolbarSeminarios();
		    });
			    
			    gridSeminarios = listado.attachGrid();
			    gridSeminarios.setIconsPath('../skins/imgs/');
			    gridSeminarios.setHeader(["<strong><bean:message key="label.nombre.seminario" /></strong>",
			    	                      "<strong><bean:message key="label.codigo.seminario" /></strong>",
			    	                      "<strong><bean:message key="label.curso.seminario" /></strong>",
			    	                      "<strong><bean:message key="label.asignatura.asociada" /></strong>"]);
			    


			    gridSeminarios.setColTypes("ro,ro,ro,ro");
			    gridSeminarios.setColSorting('str,str,str,str');
			    gridSeminarios.enableMultiselect(false);
			    gridSeminarios.init();
			    gridSeminariosProcessor = new dataProcessor("gridSeminarios.do");
			    gridSeminariosProcessor.enableUTFencoding('simple');
			    gridSeminariosProcessor.init(gridSeminarios);	  
			    gridSeminariosProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
			 	
			    gridSeminarios.clearAndLoad("gridSeminarios.do");	
				gridSeminarios.attachEvent("onRowSelect", doOnRowSelected);				  
		    });
		    
		    
		    function doOnRowSelected(row,celInd){

		    	idSeminario = row;
		    	
		    	var formSeminarioAlumno = areaTrabajoCursos.attachForm();
	    		
	    		formSeminarioAlumno.loadStruct('../xml/forms/seminario_informacion_form.xml', function(){
	    			formSeminarioAlumno.setItemLabel('data','<bean:message key="title.info.general.seminario"/>');
	    			formSeminarioAlumno.setItemLabel('nombre','<bean:message key="label.nombre.seminario"/>');
	    			formSeminarioAlumno.setItemLabel('codigo','<bean:message key="label.codigo.seminario"/>');
	    			formSeminarioAlumno.setItemLabel('descripcion','<bean:message key="label.descripcion.seminario"/>');
	    			formSeminarioAlumno.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
	    			formSeminarioAlumno.setItemLabel('asignatura','<bean:message key="label.asignatura.asociada"/>');
	    			formSeminarioAlumno.hideItem('aceptar');
	    			formSeminarioAlumno.hideItem('asignatura');
	    			
	    			
	    			
					//Ponemos por defecto que los items no se puedan modificar, y luego con los permisos necesarios 
					//seran modificables.
		    		formSeminarioAlumno.setReadonly('nombre', true);
		    		formSeminarioAlumno.setReadonly('codigo', true);
		    		formSeminarioAlumno.setReadonly('descripcion', true);
		    		
		    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>6</permiso>" >
    					formSeminarioAlumno.showItem('aceptar');
    					formSeminarioAlumno.setReadonly('descripcion', false);
    				</logic:match>
	    		
    				
					
					
	    		formSeminarioAlumno.load('editarseminario.do?idSeminario=' + row, function () {
						
						
	    			formSeminarioAlumno.attachEvent("onButtonClick", function(id){
						if (id == "aceptar") {
								
							formSeminarioAlumno.send("editarseminario.do?!nativeeditor_status=save&idSeminario=" + row,"post", function(xml) {
								alert('<bean:message key="message.seminario.cambiado.exito"/>');
							});
							

						}
					});
	    			formSeminarioAlumno.attachEvent("onEnter", function() {
							
	    				formSeminarioAlumno.send("editarseminario.do?!nativeeditor_status=save&idSeminario=" + row,"post", function(xml) {
							alert('<bean:message key="message.seminario.cambiado.exito"/>');
						}); 
							
			    	});
						
						
					});//load
					
					
		    			
	    		});
		    }
		    
		    
		    
		    
		    function buscar() {
		    	gridSeminarios.clearAndLoad("gridSeminarios.do");		    	
		    }
	    	
		    function permisosToolbarSeminarios(){
		    	
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >	    	
			   		toolbarSeminarios.hideItem('new');
			   		toolbarSeminarios.hideItem('sep1');    	
			   		toolbarSeminarios.hideItem('delete');
			   		toolbarSeminarios.hideItem('sep2');
				</logic:notMatch>
	    	}
		    
		    function permisosSeminariosForm(){
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
		    
		    function goActualizar(){
		    	gridSeminarios.clearAndLoad("gridSeminarios.do");	
		    }
		    
		    
		    function nuevoSeminario(){
		    	
	    		var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("subir", 300,50, 500, 400);
				window.setText('<bean:message key="title.trabajo.de.campo" />');				
				window.setModal(true);
				window.centerOnScreen();
				
				var formNTC = window.attachForm();
				formNTC.loadStruct('../xml/forms/seminario_informacion_form.xml', function(){
					formNTC.setItemLabel('data','<bean:message key="title.info.general.seminario"/>');
					formNTC.setItemLabel('nombre','<bean:message key="label.nombre.seminario"/>');
					formNTC.setItemLabel('codigo','<bean:message key="label.codigo.seminario"/>');
					formNTC.setItemLabel('descripcion','<bean:message key="label.descripcion.seminario"/>');
					formNTC.setItemLabel('asignatura','<bean:message key="label.asignatura.asociada"/>');
					formNTC.setItemLabel('aceptar','<bean:message key="button.aceptar"/>');
					formNTC.setRequired('nombre', true);
					formNTC.setRequired('codigo', true);
					formNTC.setRequired('asignatura', true);
					
					var data = retrieveData();
					formNTC.reloadOptions('asignatura', data);
		    		
					var bloqueado = true;
					formNTC.attachEvent("onChange", function(name,value){
							
			    		   if(name == "codigo"){
			    			   bloqueado=true;
			    			   formNTC.clearNote("codigo");
			    			   var opts = chequeaSeminario(formNTC.getItemValue("codigo"))
			    			   var ext = opts[0].toString();
			    			   if (ext == "existe"){
			    				   formNTC.setNote("codigo", { text: '<bean:message key="message.existe.codigo.seminario" />'} );
			    			   }
			    			   else if (formNTC.getItemValue("codigo") != ""){
			    				   bloqueado = false;
			    				   formNTC.setNote("codigo", { text: '<img src=../img/grid/corregida.png></img>'} );
			    			   }
			    		   }
					});
					
					formNTC.attachEvent("onButtonClick", function(id){
		    			if(id == "aceptar"){
						
							if (bloqueado){
								alert('<bean:message key="message.incorrecto.codigo.seminario"/>');
							}
							else {
								formNTC.send("editarseminario.do?!nativeeditor_status=create","post", function(xml) {
									goActualizar();
									alert('<bean:message key="message.seminario.cambiado.exito"/>');
									window.close();
									
								});
							}
					
		    			}
					});
					
				});
				
				
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
	    	
	    	
	    	function chequeaSeminario(codigo){
	    		var url = "checkCodigoSeminario.do?codigo=" + codigo;
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
	    	
	    	function createArrayFromXML(xml){
	    		var seminarios = xml.getElementsByTagName("seminario");
	    		var id, nombre, seminario;
	    		var opts = new Array();
	    		for(var i=0;i<seminarios.length;i++) {
	    	        id=seminarios[i].getElementsByTagName("check")[0].firstChild.nodeValue;
	    	        
	    	        
	    	       	opts[i] = id;
	    	    }
	    		return opts;

	    	}
		   
	    	
	    	
	    	
		  
	    	function retrieveData(){
	    		var url = "dameAsignaturas.do";
	    		var xmlhttp = initRequest();
	    		xmlhttp.onreadystatechange=function(){
	    			if (xmlhttp.readyState===4) {
	        	        if(xmlhttp.status===200) { //GET returning a response
	        	        	return createArrayFromXMLAsignaturas(xmlhttp.responseXML);
	        	        }
	        	    }
	    		}
	    	    xmlhttp.open("GET",url,false);
	    	    xmlhttp.send(null);
	    	    return xmlhttp.onreadystatechange();
	    	}
	    	
	    	function createArrayFromXMLAsignaturas(xml){
	    		var seminarios = xml.getElementsByTagName("asignatura");
	    		var id, nombre, seminario;
	    		var opts = new Array();
	    		for(var i=0;i<seminarios.length;i++) {
	    	        id=seminarios[i].getElementsByTagName("id")[0].firstChild.nodeValue;
	    	       
	    	        nombre=seminarios[i].getElementsByTagName("nombre")[0].firstChild.nodeValue;
	    	        if(i==0){seminario={text:nombre, value:id, selected:true};}
	    	        else seminario={text:nombre, value:id};
	    	       	opts[i] = seminario;
	    	    }
	    		return opts;

	    	}
	    	
        </script>
	</head>
	<body>
	</body>
</html>