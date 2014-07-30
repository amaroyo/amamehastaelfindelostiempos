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
		    	
		    	
		    	<% String anyo = (String) session.getAttribute("anyoAcademico");%>
				var ann = ("<%=anyo%>");
		    	
				var mywindow;
				
			    dhtmlxError.catchError("ALL",errorHandler);


				var dhxWins= new dhtmlXWindows(document.body);
				mywindow = dhxWins.createWindow("CambiarAnyoAcademico", 300, 50, 465, 300);
				mywindow.setText('<bean:message key="title.time.machine" />');				
				mywindow.setModal(true);
				mywindow.centerOnScreen();
				var form = mywindow.attachForm();			
		    	form.loadStruct('../xml/forms/cambiar_anyo_academico.xml', function() {
		    		form.setItemLabel('data1','<bean:message key="title.aviso.importante"/>');
		    		form.setItemLabel('labelAviso','<bean:message key="message.cambiar.anyo.academico"/>');
		    		form.setItemLabel('data2','<bean:message key="title.seleccione.anyo"/>');
		    		form.setItemLabel('anyo','<bean:message key="label.anyo"/>');
		    		
		
		    		var data = getData();
		    		form.reloadOptions('anyo', data);
		    				    					    			
		    		form.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
	    					var selectedYear = form.getItemValue("anyo");
	    					if(selectedYear != ann){
	    						
	    						var mensaje;
	    						mensaje = cambiarCursoAcademico(selectedYear);  
	    						alert(mensaje);
	    						mywindow.close();
		    					goEntrada();
	    					}
	    					else alert('<bean:message key="message.mismo.anyo"/>' + " " + ann);
	    					
	    				}
		    		});
		    	});
			    			    			    
		    });
		    
		    
		    function getData(){
		    	var d = [ {text: "2014/2015", value: "2014/2015", selected: true},
                          {text: "2013/2014", value: "2013/2014"},
                          {text: "2012/2013", value: "2012/2013"}
                		];
		    	return d;
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
	    	
	    	
	    	function cambiarCursoAcademico(selectedYear){
	    		var url = "cambiarCursoAcademico.do?anyo=" + selectedYear;
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
	    		
	    		if(nombre=="exito"){
	    			return '<bean:message key="message.cambio.satisfactorio"/>';
	    		}
	    		else return '<bean:message key="message.algo.salio.mal"/>';

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