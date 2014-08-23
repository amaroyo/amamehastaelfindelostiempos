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
		<script type="text/javascript" src="../js/dhtmlxSuite/dhtmlxcommon.js"></script>
		
	    

	    <script type="text/javascript">
		    	    	
		    dhtmlxEvent(window,"load",function() {
				generarCertificado();
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
		    
		    function generarCertificado(){
	    		var url = "generarcertificado.do";
	    		var xmlhttp = initRequest();
	    		xmlhttp.onreadystatechange=function(){
	    			if (xmlhttp.readyState===4) {
	        	        if(xmlhttp.status===200) { //GET returning a response
	        	        	return xmlhttp.responseXML;
	        	        }
	        	    }
	    		}
	    	    xmlhttp.open("GET",url,false);
	    	    xmlhttp.send(null);
	    	    return xmlhttp.onreadystatechange();
	    	}
	 	</script>
	 	
	 </head>
	<body>
	</body>
</html>