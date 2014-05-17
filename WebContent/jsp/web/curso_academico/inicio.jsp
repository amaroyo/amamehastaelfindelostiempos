<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="error.jsp" %>
<%@ include file="../../common/taglibs.jsp" %>
<html>
	<head>
	    <link rel="stylesheet" type="text/css" href="../css/estilos.css">
	    <link rel="stylesheet" type="text/css" href="../css/templates.css">
	    <link rel="stylesheet" type="text/css" href="../css/estilosMenu.css">
	    <link rel="stylesheet" type="text/css" href="../skins/dhtmlx.css">
	    <script type="text/javascript" src="../skins/dhtmlx.js"></script>
	    <script type="text/javascript" src="../skins/dhtmlxform_dyn.js"></script>
	    <script type="text/javascript" src="../js/utilsajax.js"></script>
	    <script type="text/javascript" src="../js/general.js"></script>
	    

	    <script type="text/javascript">
	    
	    	dhtmlx.image_path='../skins/imgs/';
	    	
	    	
  	
		    dhtmlxEvent(window,"load",function() {
		    	
		    	
		    	<% String anyo = (String) session.getAttribute("anyo_academico");%>
				var ann = ("<%=anyo%>");
		    	
				
			    dhtmlxError.catchError("ALL",errorHandler);


				var dhxWins= new dhtmlXWindows(document.body);
				var window = dhxWins.createWindow("CambiarAnyoAcademico", 300, 50, 465, 300);
				window.setText('<bean:message key="title.time.machine" />');				
				window.setModal(true);
				window.centerOnScreen();
				var form = window.attachForm();			
		    	form.loadStruct('../xml/forms/cambiar_anyo_academico.xml', function() {
		    		form.setItemLabel('data1','<bean:message key="title.aviso.importante"/>');
		    		form.setItemLabel('labelAviso','<bean:message key="message.cambiar.anyo.academico"/>');
		    		form.setItemLabel('data2','<bean:message key="title.seleccione.anyo"/>');
		    		form.setItemLabel('anyo','<bean:message key="label.anyo"/>');
		    		
		
		    		var data = getData();
		    		form.reloadOptions('anyo', data);
		    				    					    			
		    		form.attachEvent("onButtonClick", function(id){
	    				if (id == "aceptar") {
	    					var selec = form.getItemValue("anyo");
	    					if(selec != ann){
	    						//HAY Q HACER UN SET DEL ATRIBUTO!!!!!
	    						alert('<bean:message key="message.cambio.satisfactorio"/>');
	    					}
	    					window.close();
	    					
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

		    
        </script>
	</head>
	<body>
	</body>
</html>