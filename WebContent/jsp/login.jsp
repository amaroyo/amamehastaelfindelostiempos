<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>
<%@ page import="es.oyssen.mrm.Const"%>
<%@ include file="common/taglibs.jsp" %>

<html>
<head>  
	<meta http-equiv="cache-control" content="no-cache" />
    <title><bean:message key="aplicacion.nombre"/></title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
    <script language="JavaScript" src="js/general.js"></script>   

	    <link rel="stylesheet" type="text/css" href="skins/dhtmlx.css">	    
	    <script type="text/javascript" src="skins/dhtmlx.js"></script>
    
    <script>
    	var dhxWins, toolbar, width, height, nota;
    	dhtmlx.image_path='skins/imgs/';
    	
    	function init() {
    	}

    </script>     
</head>
<body class="ventana" onload="init();">
    <table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%">
       <tr><td><%@include file="common/cabecera.jsp" %></td></tr>  

        <tr><td>
        	<div id="toolbarObj"></div>
        </td></tr> 
                
        <tr>
            <td valign="top" height="98%" align="center"><iframe name="trabajo" id="areatrabajo" height="95%" width="100%" scrolling="auto" src="bienvenida/inicio.do" frameborder="0"></iframe>
            </td>
        </tr>      
      </table>
</body>
</html>
