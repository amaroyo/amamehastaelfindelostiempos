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
				mywindow.setText('<bean:message key="title.generar.certificados" />');				
				mywindow.setModal(true);
				mywindow.centerOnScreen();
				
				var layout = mywindow.attachLayout("1C","dhx_skyblue");
				var a = layout.cells('a');
				a.hideHeader();
				var gridAlumnosCertificado = a.attachGrid();
				gridAlumnosCertificado.setIconsPath('../skins/imgs/');		   
  
			    
				gridAlumnosCertificado.setHeader(["<bean:message key="label.nombre" />","<bean:message key="label.apellido" />","<bean:message key="label.dni" />"]);
			    
			    //alineacion del contenido en la columna
			    gridAlumnosCertificado.setColAlign("left,left,left");
			    
			    gridAlumnosCertificado.setColTypes("ro,ro,ro");
		    	
			    gridAlumnosCertificado.enableMultiselect(false);
			    gridAlumnosCertificado.setColSorting('str,str,str');
			    gridAlumnosCertificado.init();
		    	
				var gridProcessorMios = new dataProcessor("gridAlumnosAptosCertificado.do?");
				gridProcessorMios.enableUTFencoding('simple');
				gridProcessorMios.init(gridAlumnosCertificado);	  
				gridProcessorMios.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
					if(action == 'error'){
		    			dhtmlx.message(tag.firstChild.data,action,4000);
		    		}
		    	});		    	

				gridAlumnosCertificado.clearAndLoad("gridAlumnosAptosCertificado.do?");
				
				gridAlumnosCertificado.attachEvent("onRowSelect",function doOnRowSelected(rowID,celInd){
		
					var cellObjdni = gridAlumnosCertificado.cellById(rowID,2);
					var dni = cellObjdni.getValue();
					
					var cellObjnombre = gridAlumnosCertificado.cellById(rowID,0);
					var nombre = cellObjnombre.getValue();
					
					var cellObjapp = gridAlumnosCertificado.cellById(rowID,1);
					var app = cellObjapp.getValue();
					
					var accion = "generarCertificado.do";
					accion += "?idPortafolios="+rowID;
					accion += "&dni="+dni;
					accion += "&nombre="+nombre;
					accion += "&apellidos="+app;
					location.href=accion;
					
					
		    	});
				
				
			    			    			    
		    });

	    	
	    	
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