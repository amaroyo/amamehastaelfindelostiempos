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
	    	
	    	var gridLeads;
	    	
		    dhtmlxEvent(window,"load",function() {

		    	dhtmlxError.catchError("ALL",errorHandler);

		    	var main_layout = new dhtmlXLayoutObject(document.body, '1C');

		    	var a = main_layout.cells('a');
		    	a.hideHeader();
		    	var form = a.attachForm();
		    	form.loadStruct('../xml/forms/lead_form.xml', function() {
							    		
		    	});		
		    	
		    	

		    });

	
	 	</script>
	 	
	 </head>
	<body>
	</body>
</html>