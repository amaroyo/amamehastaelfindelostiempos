<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>
<%@ include file="../../common/taglibs.jsp" %>
<%@  page import="java.util.Enumeration"%>
<%@ page import="es.oyssen.mrm.Const"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<html>
	<head>
	    <link rel="stylesheet" type="text/css" href="../css/estilos.css">
	    <link rel="stylesheet" type="text/css" href="../css/templates.css">
	    <link rel="stylesheet" type="text/css" href="../css/estilosMenu.css">
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    
		<link rel="stylesheet" type="text/css" href="../js/dhtmlxSuite/dhtmlx.css">
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
	    <script src="../js/dhtmlxSuite/patterns/dhtmlxlayout_pattern4l.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_dyn.js"></script>
	    

	    <script type="text/javascript">
	    
    		dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	var main_layout, idAsignatura, nombreAsignatura, gridProfesores,gridAlumnos,tab, profesor,a,b,idSession, tabbar,
	    	formRubrica, formAnexo, tab_rubrica, tab_anexo1, tab_anexo2, competencias, anexo, numeroCriterios,gridAnexos2,toolbarServicios,ID;
	    	
	    	
	    	
	    	
	    	dhtmlxEvent(window,"load",function() {
	    		
	    		//inicializo profesor a falso para tener un poco de seguridad
	    		profesor=false;
	    		
	    		<% String idAsignatura = request.getParameter("idAsignatura");%>
	    		idAsignatura="<%=idAsignatura%>";	
	    		<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
				 idSession = <%=sessionIdUser%>;
	    		
	    		
	    		<logic:notMatch scope="session" name="usuarioYPermisos" value="<grupo>4</grupo>" >
					profesor=true;
					main_layout = new dhtmlXLayoutObject(document.body, '2U');
		    		a = main_layout.cells('a');
		    		b = main_layout.cells('b');
		    		b.setWidth(900);
		    		a.hideHeader();
					b.setText('<bean:message key="label.rubrica.alumno"/>');
				</logic:notMatch>
		
				<logic:match scope="session" name="usuarioYPermisos" value="<grupo>4</grupo>" >
					profesor=false;
					main_layout = new dhtmlXLayoutObject(document.body, '1C');
		    		a = main_layout.cells('a');
		    		a.hideHeader();
				</logic:match>	
	    		
		    	
		    	if (profesor) goGridProfesores();
		    	else goGridAlumnos();
	
				
	    	});
			
			function goGridAlumnos(){
				ID=idSession;
				initRubrica(idSession);
				
			}
			
			function goGridProfesores(){
				
				gridProfesores = a.attachGrid();
		    	gridProfesores.setIconsPath('../skins/imgs/');		   
			    gridProfesores.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />"]);
			    
			    //anchura de las columnas, en porcentaje. La suma tiene que ser igual a 100
			    gridProfesores.setInitWidthsP("27,50,23");
			    //alineacion del contenido en la columna
			    gridProfesores.setColAlign("left,left,left");
			    
			    gridProfesores.setColTypes("ro,ro,ro");
			    
			    gridProfesores.enableMultiselect(false);
			    gridProfesores.setColSorting('str,str,str');
			    gridProfesores.init();
		    	
				var gridProcessor = new dataProcessor("gridUsuariosProfesor.do?busqueda=MisAsignaturas&idAsignatura=" + idAsignatura);
				gridProcessor.enableUTFencoding('simple');
				gridProcessor.init(gridProfesores);	  
				gridProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});	

				gridProfesores.attachEvent("onRowSelect",doOnRowSelected);
				gridProfesores.clearAndLoad("gridUsuariosProfesor.do?busqueda=MisAsignaturas&idAsignatura=" + idAsignatura);
				
			}
			
			function doOnRowSelected(rowID,celInd){
				var sp = rowID.split("-");
				var idPortafolio = sp[3];
				ID=idPortafolio;
				initRubrica(idPortafolio);
		    }
			
			
			function initRubrica(identificador){
				
				if(profesor) tabbar = b.attachTabbar();
				else tabbar = a.attachTabbar();
		    	dameRubricaAsignatura(idAsignatura);
				
				tabbar.addTab('rubrica',"<bean:message key='title.rubrica'/>",'');
				tab_rubrica = tabbar.cells('rubrica');
				goRubrica(identificador);
				
				tabbar.addTab('anexo1',"<bean:message key='title.anexo.uno'/>",'');
		    	tab_anexo1 = tabbar.cells('anexo1');
		    	goAnexo1(identificador);
		    	
				tabbar.addTab('anexo2',"<bean:message key='title.anexo.dos'/>",'');
				tab_anexo2 = tabbar.cells('anexo2');
		    	goAnexo2(identificador);
				
				
				tabbar.setTabActive('rubrica');
			}
			
			function goRubrica(identificador){
				
				formRubrica = tab_rubrica.attachForm();
		    	formRubrica.loadStruct('../xml/forms/rubrica_form.xml', function(){
	    			formRubrica.setItemLabel('resultados','<bean:message key="title.resultados.competencias"/>');
	    			formRubrica.setItemValue('competencias',competencias);

	    			var i;
	    			var grupos_criterios_rubrica = dameGruposCriteriosAsignatura(idAsignatura);
	    			for(i=0;i<grupos_criterios_rubrica.length;i++){
	    				formRubrica.addItem(null, grupos_criterios_rubrica[i], i+1);
	    			}
	    			formRubrica.addItem(null,{type:"button", name:"aceptar", value:"Modificar"},i+1);
	    			formRubrica.setItemLabel('aceptar','<bean:message key="button.modificar"/>');
	    			
  					var items = new Array();
  					for(var j=1;j<=10;j=j+2){
						items[j] = {type:"input", name:"contador_"+((j+1)/2), label:"<strong>"+((j+1)/2)+"</strong>", position:"label-top", labelWidth:"10", inputWidth:"30", readonly:"true"};
		    			items[j+1] = {type:"newcolumn"};
	    			}
  					formRubrica.addItem(null,{type:"fieldset", name:"puntuacion", label:'<bean:message key="label.total.puntuacion"/>', inputWidth:"auto"},i+1);
  					formRubrica.addItem("puntuacion",{type:"block", name:"block_contadores", list:items},0);
  					formRubrica.addItem("puntuacion",{type:"label", label:'<strong><bean:message key="label.nota.final"/></strong>'},1);
  					formRubrica.addItem("puntuacion",{type:"input", name:"nota", inputWidth:"50", readonly:"true"},2);
  					formRubrica.addItem("puntuacion",{type:"input", name:"aclaraciones", label:'<bean:message key="label.nota.final.aclaraciones"/>', labelWidth:"700", inputWidth:"0", position:"label-right", readonly:"true"},3);
  					formRubrica.addItem("puntuacion",{type:"input", name:"rango1", label:'<bean:message key="label.nota.rango1"/>', labelWidth:"700", inputWidth:"0", position:"label-right", readonly:"true"},4);
  					formRubrica.addItem("puntuacion",{type:"input", name:"rango2", label:'<bean:message key="label.nota.rango2"/>', labelWidth:"700", inputWidth:"0", position:"label-right", readonly:"true"},5);
  					formRubrica.addItem("puntuacion",{type:"input", name:"rango3", label:'<bean:message key="label.nota.rango3"/>', labelWidth:"700", inputWidth:"0", position:"label-right", readonly:"true"},6);
  					formRubrica.addItem("puntuacion",{type:"input", name:"rango4", label:'<bean:message key="label.nota.rango4"/>', labelWidth:"700", inputWidth:"0", position:"label-right", readonly:"true"},7);
  					formRubrica.addItem("puntuacion",{type:"input", name:"observaciones", label:'<bean:message key="label.nota.final.observaciones"/>', labelWidth:"700", inputWidth:"0", position:"label-right", readonly:"true"},8);
  					
  					loadNotasRubrica(identificador);
  					contarValores();
  					
	    			permisosRubricasForm();
  					
    				formRubrica.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
	    					formRubrica.send("actualizarnotasrubrica.do?!nativeeditor_status=save&idPortafolio=" + identificador+"&idAsignatura="+idAsignatura,"post", function(xml) {
	    						contarValores();
	    						alert('<bean:message key="message.notas.cambiadas.exito"/>');
		    				});
	    				}
	    			});
    				formRubrica.attachEvent("onEnter", function() {
    					formRubrica.send("actualizarnotasrubrica.do?!nativeeditor_status=save&idPortafolio="+identificador+"&idAsignatura="+idAsignatura,"post", function(xml) {
							contarValores();
    						alert('<bean:message key="message.notas.cambiadas.exito"/>');
	    				});
		    		});
	    		});
			}
			function goAnexo1(identificador){
				
				formAnexo = tab_anexo1.attachForm();
		    	formAnexo.loadStruct('../xml/forms/anexo_form.xml', function(){
	    			formAnexo.setItemLabel('anexo',anexo);
	    			var grupos_anexo_rubrica = dameGruposAnexoAsignatura(idAsignatura);
	    			for(var i=0;i<grupos_anexo_rubrica.length;i++){
	    				formAnexo.addItem("anexo", grupos_anexo_rubrica[i], i);
	    			}
	    			formAnexo.addItem(null,{type:"button", name:"aceptar", value:"Modificar"},i+1);
	    			formAnexo.setItemLabel('aceptar','<bean:message key="button.modificar"/>');

	    			
	    			permisosAnexoForm();
	    			formAnexo.load("notasrubrica.do?idPortafolio=" + identificador+"&idAsignatura="+idAsignatura, function () {			    			
	    				formAnexo.attachEvent("onButtonClick", function(id){
		    				if (id == "aceptar") {
		    					formAnexo.forEachItem(function(name){
		    						if(formAnexo.getItemType(name) == "input"){
		    							formAnexo.setItemValue("value("+name+")", formAnexo.getItemValue(name));
		    						}
		    					});
		    					formAnexo.send("actualizarnotasrubrica.do?!nativeeditor_status=save&idPortafolio=" + identificador+"&idAsignatura="+idAsignatura,"post", function(xml) {
		    						alert('<bean:message key="message.notas.cambiadas.exito"/>');
			    				});
		    				}
		    			});
	    				formAnexo.attachEvent("onEnter", function() {
	    					formAnexo.forEachItem(function(name){
	    						if(formAnexo.getItemType(name) == "input"){
	    							formAnexo.setItemValue("value("+name+")", formAnexo.getItemValue(name));
	    						}
	    					});
	    					formAnexo.send("actualizarnotasrubrica.do?!nativeeditor_status=save&idPortafolio="+identificador+"&idAsignatura="+idAsignatura,"post", function(xml) {
	    						alert('<bean:message key="message.notas.cambiadas.exito"/>');
		    				});
			    		});
	    			});
	    		});	
			}
			
			
		
			function goAnexo2(identificador){
				
				toolbarServicios = tab_anexo2.attachToolbar();
		    	toolbarServicios.setIconsPath('../img/toolbar/');

		    	
		    	toolbarServicios.loadXML('../xml/toolbars/dhxtoolbar-trabajos-campo.xml', function(){
		    		toolbarServicios.setItemText('crearTrabajoCampo',"<bean:message key="button.crear.trabajo.campo"/>");
		    		toolbarServicios.setItemText('modificarTrabajoCampo',"<bean:message key="button.cambiar.trabajo.campo"/>");
		    		toolbarServicios.setItemText('subirPractica',"<bean:message key="button.subir.practica"/>");
		    		toolbarServicios.setItemText('descargarTodos',"<bean:message key="button.descargar.anexos"/>");
		    		toolbarServicios.setItemText('descargarTodosAlumno',"<bean:message key="button.descargar.anexos.alumno"/>");
		    		toolbarServicios.setItemText('fechaLimite',"<bean:message key="button.fecha.limite"/>");
		    		toolbarServicios.setItemText('refresh',"<bean:message key="button.actualizar"/>");
		    		
		    		toolbarServicios.hideItem('crearTrabajoCampo');
		    		toolbarServicios.hideItem('sep1');
		    		toolbarServicios.hideItem('fechaLimite');
		    		toolbarServicios.hideItem('sep5');
		    		toolbarServicios.hideItem('modificarTrabajoCampo');
		    		toolbarServicios.hideItem('sep3');
		    		toolbarServicios.hideItem('descargarTodos');
		    		
		    		<logic:match scope="session" name="usuarioYPermisos" value="<grupo>4</grupo>" >
		    	
			    		toolbarServicios.hideItem('descargarTodos');
			    		toolbarServicios.hideItem('sep3');
			    		toolbarServicios.hideItem('descargarTodosAlumno');
			    		toolbarServicios.hideItem('sep4');		    		
			    	
		    		</logic:match>
				
		    	});
				
				gridAnexos2 = tab_anexo2.attachGrid();
		    	
				gridAnexos2.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.fecha" />","<bean:message key="label.enlace" />"]);
				gridAnexos2.setColTypes("ro,ro,ro");
		    	
				gridAnexos2.setColSorting('str,str,str');
				gridAnexos2.enableMultiselect(false);
				gridAnexos2.init();
		    	
				var accion= "";
				
				if (profesor) accion = "gridAnexos2UsuarioAsignatura.do?idPortafolio=" + identificador;
				else accion = "gridAnexos2UsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + identificador;
				
		    	var gridProcessorPro = new dataProcessor(accion);
		    	gridProcessorPro.enableUTFencoding('simple');
		    	gridProcessorPro.init(gridAnexos2);	  
		    	gridProcessorPro.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});
		    	
		    	gridAnexos2.attachEvent("onRowSelect",function(rowID,celInd){
		    		
					var cellObj = gridAnexos2.cellById(rowID,celInd);
					if(celInd=='2' && cellObj.getValue()=="Descargar") {
						var parts = rowID.split("-");
						//alert("Descargar Archivo con idPortafolio=" + parts[0] + " y idCasoClinico=" + parts[1]);
						var accion = "descargarAnexo.do";
						accion += "?tipoConsulta="+"Anexo";
						accion += "&idPortafolio="+parts[0];
						accion += "&idAnexo="+parts[1];
						location.href=accion;
					}
		
				});
		    	
		    	
		    	gridAnexos2.clearAndLoad(accion);
				
			}
			
		
			
			function loadNotasRubrica(idPortafolio){
	    		var url = "notasrubrica.do?idPortafolio=" + idPortafolio+"&idAsignatura="+idAsignatura;
	    		var xmlhttp = initRequest();
	    		xmlhttp.onreadystatechange=function(){
	    			if (xmlhttp.readyState===4) {
	        	        if(xmlhttp.status===200) { //GET returning a response
	        	        	return checkRadioButtonsFromXML(xmlhttp.responseXML);
	        	        }
	        	    }
	    		}
	    	    xmlhttp.open("GET",url,false);
	    	    xmlhttp.send(null);
	    	    return xmlhttp.onreadystatechange();
	    	}

			function checkRadioButtonsFromXML(xml){
				var length = xml.documentElement.childNodes.length;
				for(var i=0;i<length;i++) {
	    	        var idCriterio = xml.documentElement.childNodes.item(i).nodeName;
	    	        var notaCriterio = xml.documentElement.childNodes.item(i).childNodes[0].nodeValue;
	    	        formRubrica.checkItem("value("+idCriterio+")",notaCriterio);
				}
	    	}
			
			function dameRubricaAsignatura(idAsignatura){
	    		var url = "rubricaasignatura.do?idAsignatura="+idAsignatura;
	    		var xmlhttp = initRequest();
	    		xmlhttp.onreadystatechange=function(){
	    			if (xmlhttp.readyState===4) {
	        	        if(xmlhttp.status===200) { //GET returning a response
	        	        	competencias = xmlhttp.responseXML.getElementsByTagName("competencias")[0].firstChild.nodeValue;
	        	        	anexo = xmlhttp.responseXML.getElementsByTagName("anexo")[0].firstChild.nodeValue;
	        	        	numeroCriterios = xmlhttp.responseXML.getElementsByTagName("numero_criterios")[0].firstChild.nodeValue;
	        	        }
	        	    }
	    		}
	    	    xmlhttp.open("GET",url,false);
	    	    xmlhttp.send(null);
	    	    return xmlhttp.onreadystatechange();
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
	        	        	return createArrayGruposAnexoFromXML(xmlhttp.responseXML);
	        	        }
	        	    }
	    		}
	    	    xmlhttp.open("GET",url,false);
	    	    xmlhttp.send(null);
	    	    return xmlhttp.onreadystatechange();
	    	}
			
			function createArrayGruposCriteriosFromXML(xml){
				var items = new Array();
				var criterios_grupo = new Array();
				var grupos = xml.getElementsByTagName("grupo");
				var criterios;
				var id_grupo, nombre_grupo, id_criterio, nombre_criterio;
				for(var i=0;i<grupos.length;i++) {
	    	        id_grupo=grupos[i].getElementsByTagName("id_grupo")[0].firstChild.nodeValue;
	    	        nombre_grupo=grupos[i].getElementsByTagName("nombre_grupo")[0].firstChild.nodeValue;
	    	        criterios = grupos[i].getElementsByTagName("criterio");
	    	        criterios_grupo = new Array();
	    	        for(var j=0;j<criterios.length;j++){
	    	        	id_criterio=criterios[j].getElementsByTagName("id_criterio")[0].firstChild.nodeValue;
		    	        nombre_criterio=criterios[j].getElementsByTagName("nombre_criterio")[0].firstChild.nodeValue;
		    	        var radios = new Array();
		    	        for(var k=1;k<=10;k=k+2){
		    	        	radios[k] = {type:"radio", name:"value(idCriterio"+id_criterio+")", value:((k+1)/2), label:((k+1)/2)};
		    	        	radios[k+1] = {type:"newcolumn"};
		    	        }
		    	        criterios_grupo[j]={type:"label", label:nombre_criterio, labelWidht:"100", list:radios};
	    	        }
	    	        items[i]={type:"fieldset", name:id_grupo, label:nombre_grupo, inputWidth:"auto", list:criterios_grupo};
		    	}
				return items;
	    	}
			
			
			function createArrayGruposAnexoFromXML(xml){
				var items = new Array();
				var criterios_grupo = new Array();
				var grupos = xml.getElementsByTagName("grupo");
				var criterios;
				var id_grupo, nombre_grupo, id_criterio, nombre_criterio;
				for(var i=0;i<grupos.length;i++) {
	    	        id_grupo=grupos[i].getElementsByTagName("id_grupo")[0].firstChild.nodeValue;
	    	        nombre_grupo=grupos[i].getElementsByTagName("nombre_grupo")[0].firstChild.nodeValue;
	    	        criterios = grupos[i].getElementsByTagName("criterio");
	    	        criterios_grupo = new Array();
	    	        for(var j=0;j<(criterios.length)*2;j=j+2){
	    	        	id_criterio=criterios[j/2].getElementsByTagName("id_criterio")[0].firstChild.nodeValue;
		    	        nombre_criterio=criterios[j/2].getElementsByTagName("nombre_criterio")[0].firstChild.nodeValue; 
		    	        criterios_grupo[j] = {type:"input", name:"idCriterio"+id_criterio, label:"<strong>"+nombre_criterio+"</strong>", position:"label-top", labelWidth:"700", inputWidth:"700", rows:"3"};
		    	        criterios_grupo[j+1] = {type:"hidden", name:"value(idCriterio"+id_criterio+")"};
	    	        }
	    	        items[i]={type:"fieldset", name:id_grupo, label:nombre_grupo, inputWidth:"auto", list:criterios_grupo};
		    	}
				return items;
	    	}
			
			function contarValores(){
				contador = new Array();
				contador = [0,0,0,0,0,0];
				var sumatorio = 0;
				formRubrica.forEachItem(function(name,value){
					if(formRubrica.getItemType(name, value) == "radio"){
						if(formRubrica.isItemChecked(name, value)){
							contador[value]=contador[value]+1;
							sumatorio = sumatorio + value;
						}
					}
				});
				for(var i=1;i<=5;i++){
					formRubrica.setItemValue("contador_"+i, contador[i]);
				}
				formRubrica.setItemValue("nota", Math.round(((sumatorio/numeroCriterios)*2) * 100) / 100);
			}
			
			function subirPractica(){
				var dhxWins= new dhtmlXWindows();
				var window = dhxWins.createWindow("subir", 300,50, 500, 170);
				window.setText('<bean:message key="title.subir.practica" />');				
				window.setModal(true);
				window.centerOnScreen();
				
				if (profesor) accion = "subirArchivo.do?tipoConsulta=AnexoProfesor" + "&idPortafolio=" + ID;
				else accion = "subirArchivo.do?tipoConsulta=AnexoAlumno" + "&idAsignatura=" + idAsignatura + "&idAlumno=" + ID;
		
				window.attachURL(accion);
				//goActualizar();
			}
			
			function descargarTodosAlumno(){
				if (idPortafolio == -1) alert("<bean:message key="message.error.seleccionar.alumno" />");
				else {
					var accion = "descargarCasoClinicosAlumno.do";
					accion += "?idPortafolio=" + idPortafolio;
					location.href=accion;
				}
			}
			
			function descargarTodos(){
				var accion = "descargarTodosCasosClinicosAlumnos.do";
				accion += "?idAsignatura=" + idAsignatura;
				location.href=accion;
			}
			
			
			
			function goActualizar() {
				
				if (profesor) {
					gridAnexos2.clearAndLoad("gridAnexos2UsuarioAsignatura.do?idPortafolio=" + ID);
					
				}
				else gridAnexos2.clearAndLoad("gridAnexos2UsuarioAsignatura.do?idAsignatura=" + idAsignatura + "&idAlumno=" + ID);		    	
		    			    	
		    }
			
			
			function permisosRubricasForm(){
    			formRubrica.setReadonly('competencias',true);
    			formRubrica.setReadonly('nota',true);
    			for(var i=1;i<=5;i++){
        			formRubrica.setReadonly('contador_'+i,true);
    			}
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>2</permiso>" >
	    		<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>3</permiso>" >	
	    		formRubrica.forEachItem(function(id,value){
	    			if(formRubrica.getItemType(id, value) == "radio"){
	        			formRubrica.disableItem(id,value);
	    			}
	    		});
    			formRubrica.hideItem('aceptar');
    			</logic:notMatch>
    			</logic:notMatch>	
    			</logic:notMatch>	
			}
			
			function permisosAnexoForm(){
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>2</permiso>" >
	    		<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>3</permiso>" >	
	    		formAnexo.forEachItem(function(id){
	    			if(formAnexo.getItemType(id) == "input"){
	        			formAnexo.setReadonly(id,true);
	    			}
	    		});
    			formAnexo.hideItem('aceptar');
    			</logic:notMatch>
    			</logic:notMatch>	
    			</logic:notMatch>	
			}
	   </script>
	</head>
	<body>
	</body>
</html>