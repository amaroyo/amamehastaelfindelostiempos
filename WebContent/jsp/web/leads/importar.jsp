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
		    function importar() {
		    	document.forms[0].submit();
		    }
	 	</script>
	 	
	 </head>
	<body>
		<html:form action="/leads/importarLeads" enctype="multipart/form-data">
			<table>
				<tr>
					<td><label for="channel">Channel</label></td>
					<td>					
						<div id="combo_canal" style="width:200px; height:30px;"></div>
						<script>
							dhtmlx.image_path='../skins/imgs/';
					    	var z = new dhtmlXCombo("combo_canal", "canal", 200);
					   	 	z.readonly(1);
					    	z.loadXML("listarcanales.do");
						</script>
					</td>
				</tr>
				<tr>
					<td><bean:message key="label.fichero" /></td>
					<td><html:file property="fichero"></html:file> </td>
					<td><html:button property="aceptar" onclick="importar()" ><bean:message key="button.importar" /></html:button>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>