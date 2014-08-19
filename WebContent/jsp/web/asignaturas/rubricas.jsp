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
	    

	    <script type="text/javascript">
	    
    		dhtmlx.image_path='../js/dhtmlxSuite/imgs/';
	    	var main_layout, idAsignatura, nombreAsignatura, gridProfesores,gridAlumnos,tab, profesor,a,b,idSession, tabbar;
	    	
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
				
				tabbar.addTab('rubrica',"<bean:message key="title.rubrica"/>",'');
				tabbar.addTab('anexo1',"<bean:message key="title.anexo.uno"/>",'');
				tabbar.addTab('anexo2',"<bean:message key="title.anexo.dos"/>",'');
				
				tabbar.setTabActive('rubrica');
				
				tabbar.attachEvent("onTabClick", function(id, lastId){
	    			if(id == 'rubrica') goRubrica(identificador);
	    			else if (id == 'anexo1') goAnexo1(identificador);
	    			else if (id == 'anexo2') goAnexo2(identificador);
	    		});
			}
			
			function goRubrica(identificador){
				
				
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
				
				
				alert("Rubrica");
			}
			function goAnexo1(identificador){
				alert("Anexo1");
			}
			function goAnexo2(identificador){
				alert("Anexo2");
			}
			
			
	    	
	   </script>
	</head>
	<body>
	</body>
</html>