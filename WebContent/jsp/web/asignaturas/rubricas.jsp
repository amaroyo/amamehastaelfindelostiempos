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
	    	formRubrica, tab_rubrica, tab_anexo1, competencias, anexo, numeroCriterios;
	    	
	    	dhtmlxEvent(window,"load",function() {
	    		
	    		//inicializo profesor a falso para tener un poco de seguridad
	    		profesor=false;
	    		
	    		<% String idAsignatura = request.getParameter("idAsignatura");%>
	    		idAsignatura="<%=idAsignatura%>";	
	    		<% String sessionIdUser = (String) session.getAttribute("idUsuario"); %>
				 idSession = <%=sessionIdUser%>;
	    		
	    		
	    		<logic:match scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					profesor=true;
					main_layout = new dhtmlXLayoutObject(document.body, '2U');
		    		a = main_layout.cells('a');
		    		b = main_layout.cells('b');
		    		b.setWidth(900);
		    		a.hideHeader();
					b.setText('<bean:message key="label.rubrica.alumno"/>');
				</logic:match>
		
				<logic:notMatch scope="session" name="usuarioYPermisos" value="<permiso>1</permiso>" >
					profesor=false;
					main_layout = new dhtmlXLayoutObject(document.body, '1C');
		    		a = main_layout.cells('a');
		    		a.hideHeader();
				</logic:notMatch>	
	    		
		    	
		    	if (profesor) goGridProfesores();
		    	else goGridAlumnos();
	
				
	    	});
			
			function goGridAlumnos(){
				

				/*********************
				 * Tanto aqui como en anexo1 y dos hay que distinguir de donde viene la llamada.
				 * Si viene desde alumno, identificador sera el idUsuario del alumno, en cambio, si
				 * la llamada viene desde un profesor, el identificador sera el portafolio. Me imagino
				 * que eso te ahorrara un par de consultas :)
				 * Tambien tienes acceso a la variable global idAsignatura.
				 *
				 * Ten cuidado al realizar la query para el alumno con el idAlumno, para coger el 
				 * buen portafolio hay que meter el anyo academico!
				 * 
				 * Gracias por ocuparte de esta parte =)
				 **********************
				 */
				
				
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
		    	
				var gridProcessor = new dataProcessor("gridUsuariosProfesor.do");
				gridProcessor.enableUTFencoding('simple');
				gridProcessor.init(gridProfesores);	  
				gridProcessor.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});	

				gridProfesores.attachEvent("onRowSelect",doOnRowSelected);
				gridProfesores.clearAndLoad("gridUsuariosProfesor.do");
				
			}
			
			function doOnRowSelected(rowID,celInd){
				var sp = rowID.split("-");
				var idPortafolio = sp[3];
				initRubrica(idPortafolio);
		    }
			
			
			function initRubrica(identificador){
				
				if(profesor) tabbar = b.attachTabbar();
				else tabbar = a.attachTabbar();
		    	dameRubricaAsignatura(idAsignatura);
				
				tabbar.addTab('rubrica',"<bean:message key="title.rubrica"/>",'');
				tab_rubrica = tabbar.cells('rubrica');
				goRubrica(identificador);
				
				tabbar.addTab('anexo1',"<bean:message key="title.anexo.uno"/>",'');
		    	tab_anexo1 = tabbar.cells('anexo1');
		    	goAnexo1(identificador);
		    	
				tabbar.addTab('anexo2',"<bean:message key="title.anexo.dos"/>",'');
				
				tabbar.setTabActive('rubrica');
			}
			
			function goRubrica(identificador){
				
				formRubrica = tab_rubrica.attachForm();
		    	formRubrica.loadStruct('../xml/forms/rubrica_form.xml', function(){
	    			formRubrica.setItemLabel('resultados','<bean:message key="title.resultados.competencias"/>');
	    			formRubrica.setItemValue('competencias',competencias);

	    			var i;
	    			grupos_criterios_rubrica = dameGruposCriteriosAsignatura(idAsignatura);
	    			for(i=0;i<grupos_criterios_rubrica.length;i++){
	    				formRubrica.addItem(null, grupos_criterios_rubrica[i], i+1);
	    			}
	    			formRubrica.addItem(null,{type:"button", name:"aceptar", value:"Modificar"},i+1);
	    			formRubrica.setItemLabel('aceptar','<bean:message key="button.modificar"/>');

	    			//permisosRubricasForm();
	    			
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
				
			}
			function goAnexo2(identificador){
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
				var criterios = xml.getElementsByTagName("criterio");
				for(var i=0;i<criterios.length;i++) {
	    	        var idCriterio = criterios[i].getElementsByTagName("idCriterio")[0].firstChild.nodeValue;
	    	        var notaCriterio = criterios[i].getElementsByTagName("nota")[0].firstChild.nodeValue;
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
			
			function createArrayGruposCriteriosFromXML(xml){
				var items = new Array();
				var criterios_grupo = new Array();
				var grupos = xml.getElementsByTagName("grupo");
				var criterios;
				var id_grupo, nombre_grupo, id_criterio, nombre_criterio;
				var id_grupo_id_criterio;
				for(var i=0;i<grupos.length;i++) {
	    	        id_grupo=grupos[i].getElementsByTagName("id_grupo")[0].firstChild.nodeValue;
	    	        nombre_grupo=grupos[i].getElementsByTagName("nombre_grupo")[0].firstChild.nodeValue;
	    	        criterios = grupos[i].getElementsByTagName("criterio");
	    	        criterios_grupo = new Array();
	    	        for(var j=0;j<criterios.length;j++){
	    	        	id_criterio=criterios[j].getElementsByTagName("id_criterio")[0].firstChild.nodeValue;
		    	        //id_grupo_id_criterio=id_grupo+"_"+id_criterio;
		    	        nombre_criterio=criterios[j].getElementsByTagName("nombre_criterio")[0].firstChild.nodeValue;
		    	        var radios = new Array();
		    	        for(var k=1;k<=10;k=k+2){
		    	        	radios[k] = {type:"radio", name:"value("+id_criterio+")", value:((k+1)/2), label:((k+1)/2)};
		    	        	radios[k+1] = {type:"newcolumn"};
		    	        }
		    	        criterios_grupo[j]={type:"label", label:nombre_criterio, labelWidht:"100", list:radios};
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
	   </script>
	</head>
	<body>
	</body>
</html>