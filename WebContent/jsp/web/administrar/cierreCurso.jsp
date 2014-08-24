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
		

	    <script type="text/javascript" src="../js/dhtmlxSuite/dhtmlx.js"></script>
		<script type="text/javascript" src="../js/dhtmlxSuite/ext/dhtmlxform_dyn.js"></script>


	    
	    
	    
	    

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	
	    	
  	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	
		    	
		    	
				var mywindow;
				
			    dhtmlxError.catchError("ALL",errorHandler);


				var dhxWins= new dhtmlXWindows(document.body);
				mywindow = dhxWins.createWindow("CerrarCursoAcademico", 300, 50, 425, 360);
				mywindow.setText('<bean:message key="title.cerrar.curso" />');				
				mywindow.setModal(true);
				mywindow.centerOnScreen();
				var form = mywindow.attachForm();			
		    	form.loadStruct('../xml/forms/cerrar_curso_academico.xml', function() {
		    		form.setItemLabel('data1','<bean:message key="title.aviso.importante"/>');
		    		form.setItemLabel('labelAviso1','<bean:message key="message.cerrar.curso.academico"/>');
		    		form.setItemLabel('data2','<bean:message key="title.copia.seguridad"/>');
		    		form.setItemLabel('labelAviso2','<bean:message key="message.cerrar.curso.academico.copia.seguridad"/>');
		    		form.setItemLabel('bbdd','<bean:message key="button.crear.copia.seguridad"/>');
		    		form.setItemLabel('aceptar','<bean:message key="button.select.cerrar.curso"/>');
		    		
		    		
		
		    		
		    				    					    			
		    		form.attachEvent("onButtonClick", function(id){
		    			if (id == "bbdd"){
							var d= new dhtmlXWindows();
		    				var w = d.createWindow("s", 300,50, 1, 1);	
		    				w.hide();
							w.attachURL("copiaSeguridad.do");
							setTimeout(function(){w.close();},1000);
		    			}
		    			
	    				if (id == "aceptar") {
	    					
    						var mensaje;
    						mensaje = cerrarCursoAcademico();  
    						alert(mensaje);
    						mywindow.close();
	    					goEntrada();
	    					
	    					
	    				}
		    		});
		    	});
			    			    			    
		    });
		    
		    
		   
		    

		    
		   
		    
		    function initRequest() {
	    	    if (window.XMLHttpRequest) {
	    	        xmlhttp = new XMLHttpRequest();
	    	    } else if (window.ActiveXObject) {
	    	        isIE = true;
	    	        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	    	    }
	    	    return xmlhttp;
	    	}
	    	
	    	
	    	function cerrarCursoAcademico(){
	    		var url = "cerrarCursoAcademico.do";
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
	    		var seminarios = xml.getElementsByTagName("cambio");
	    		var id, nombre, seminario;
	    		for(var i=0;i<seminarios.length;i++) {
	    	        //id=seminarios[i].getElementsByTagName("id")[0].firstChild.nodeValue;
	    	        nombre=seminarios[i].getElementsByTagName("nombre")[0].firstChild.nodeValue;
	    	        //seminario=[id,nombre];
	    	    }
	    		
	    		
	    		return nombre;

	    	}
	    	
	    	function goEntrada() {
				//var url = "../entrada.do";
				//location.href=url;
				
				//window.parent.parent.document.getElementById("toolbarTd").innerHTML="";
		    	window.parent.document.location.href="../entrada.do";
	    	}
		    
        </script>
	</head>
	<body>
	</body>
</html>