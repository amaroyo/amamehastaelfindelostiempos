function cargarCCAA(formname, pais) {
	var index = pais.selectedIndex;
	if (index > 0) {
		loadURL("buscarccaadireccion.do?pais=" + pais.options[index].value, "setCCAA('" + formname + "',req)");	
	} else {
		cleanAndDisabledSelect(document.forms[formname].comunidadAutonoma);
		cleanAndDisabledSelect(document.forms[formname].provincia);
		cleanAndDisabledSelect(document.forms[formname].localidad);
		cleanAndDisabledSelect(document.forms[formname].entidadMenor);
	}
}
function setCCAA(formname,req) {
		var ccaa = document.forms[formname].comunidadAutonoma;
		cleanAndEnabledSelect(ccaa);
  		var options = req.responseXML.getElementsByTagName("opcion");
  		ccaa.options.length = options.length;
		ccaa.options[0] = new Option("","");
  		for (i=1; i<=options.length; i++) {	   
  			ccaa.options[i] = new Option(options[i-1].attributes.getNamedItem("descripcion").value, options[i-1].attributes.getNamedItem("valor").value);
  		} 	
}

function cargarProvincias(formname, ccaa) {
	var pais = document.forms[formname].pais;
	var index = ccaa.selectedIndex;
	if (index > 0) {
		loadURL("buscarprovinciasdireccion.do?pais=" +  pais.options[pais.selectedIndex].value + "&ccaa=" + ccaa.options[index].value, "setProvincia('" + formname + "', req)");	
	} else {
		cleanAndDisabledSelect(document.forms[formname].provincia);
		cleanAndDisabledSelect(document.forms[formname].localidad);
		cleanAndDisabledSelect(document.forms[formname].entidadMenor);
	}
}
function setProvincia(formname, req) {
		var provincia = document.forms[formname].provincia;
		cleanAndEnabledSelect(provincia);
  		var options = req.responseXML.getElementsByTagName("opcion");
  		provincia.options.length = options.length;
		provincia.options[0] = new Option("","");
  		for (i=1; i<=options.length; i++) {	   
  			provincia.options[i] = new Option(options[i-1].attributes.getNamedItem("descripcion").value, options[i-1].attributes.getNamedItem("valor").value);
  		} 	
}

function cargarLocalidades(formname, provincia) {
	var pais = document.forms[formname].pais;
	var ccaa = document.forms[formname].comunidadAutonoma;
	var index = provincia.selectedIndex;
	if (index > 0) {
		loadURL("buscarlocalidadesdireccion.do?pais=" +  pais.options[pais.selectedIndex].value + "&ccaa=" +  ccaa.options[ccaa.selectedIndex].value + "&provincia=" + provincia.options[index].value, "setLocalidad('" + formname + "',req)");	
	} else {
		cleanAndDisabledSelect(document.forms[formname].localidad);
		cleanAndDisabledSelect(document.forms[formname].entidadMenor);
	}
}
function setLocalidad(formname, req) {
	var localidad = document.forms[formname].localidad;
	cleanAndEnabledSelect(localidad); 		
  	var options = req.responseXML.getElementsByTagName("opcion");
  	localidad.options.length = options.length;
	localidad.options[0] = new Option("","");
  	for (i=1; i<=options.length; i++) {	   
  			localidad.options[i] = new Option(options[i-1].attributes.getNamedItem("descripcion").value, options[i-1].attributes.getNamedItem("valor").value);
  	} 	
}

function cargarEntidadesMenores(formname, localidad) {
	var pais = document.forms[formname].pais;
	var ccaa = document.forms[formname].comunidadAutonoma;
	var provincia = document.forms[formname].provincia;
	var index = localidad.selectedIndex;
	if (index > 0) {
		loadURL("buscarentidadesmenoresdireccion.do?pais=" + pais.options[pais.selectedIndex].value + "&ccaa=" + ccaa.options[ccaa.selectedIndex].value + "&provincia=" + provincia.options[provincia.selectedIndex].value + "&localidad=" + localidad.options[index].value, "setEntidadMenor('" + formname + "', req)");	
	} else {
		cleanAndDisabledSelect(document.forms[formname].entidadMenor);
	}
}
function setEntidadMenor(formname, req) {
	var entidadMenor = document.forms[formname].entidadMenor;
	cleanAndEnabledSelect(entidadMenor);
  	var options = req.responseXML.getElementsByTagName("opcion");
  	entidadMenor.options.length = options.length;
	entidadMenor.options[0] = new Option("","");
  	for (i=1; i<=options.length; i++) {	   
  			entidadMenor.options[i] = new Option(options[i-1].attributes.getNamedItem("descripcion").value, options[i-1].attributes.getNamedItem("valor").value);
  	} 	
}

/*
//////////////////////////////////////////////
/////// TIPOS Y SUBTIPOS
//////////////////////////////////////////////
*/
function cargarListaSubtipos(tabla, formname, tipo) {
	var index = tipo.selectedIndex;
	if (index > 0) {
		loadURL("buscarsubtiposajax.do?tabla=" + tabla  + "&p_tipo=" + tipo.options[index].value, "setListaSubtipos('" + formname + "', req)");	
	} else {
		cleanAndDisabledSelect(document.forms[formname].p_subtipo);
	}
}
	
function setListaSubtipos(formname, req) {
	var subtipo = document.forms[formname].p_subtipo;
	
	cleanAndEnabledSelect(document.forms[formname].p_subtipo);
 	var options = req.responseXML.getElementsByTagName("opcion");
 	subtipo.options.length = options.length;
	subtipo.options[0] = new Option("","");
 	for (i=1;i<=options.length;i++) {	   		
 		subtipo.options[i] = new Option(options[i-1].attributes.getNamedItem("descripcion").value, options[i-1].attributes.getNamedItem("valor").value);
 	} 			
}	  	  

function cargarListaCategorias(lista, formname, subtipo) {
	var index = subtipo.selectedIndex;
	if (index > 0) {
		loadURL("buscarcategorias.do?lista=" + lista  + "&subtipo=" + subtipo.options[index].value, "setListaCategorias('" + formname + "', req)");	
	} else {
		cleanAndDisabledSelect(document.forms[formname].categoria);
	}
}
	
function setListaCategorias(formname, req) {
	var categoria = document.forms[formname].categoria;
	cleanAndEnabledSelect(document.forms[formname].categoria);
 	var options = req.responseXML.getElementsByTagName("opcion");
 	categoria.options.length = options.length;
	categoria.options[0] = new Option("","");
 	for (i=1;i<=options.length;i++) {	   		
 		categoria.options[i] = new Option(options[i-1].attributes.getNamedItem("descripcion").value, options[i-1].attributes.getNamedItem("valor").value);
 	} 		
}	  	  
function cargarListasOrigenDestino(formname,grupo){
	var index = grupo.selectedIndex;
    if (index > 0) {
    	loadURL("buscarorigendestinoajax.do?grupo=" + grupo.options[index].value , "setListaOrigenDestino('" + formname + "',req)");
    } else {
    	cleanAndDisabledSelect(document.forms[formname].p_tipo_ent_codigo);
    	cleanAndDisabledSelect(document.forms[formname].p_tipo_ent_codigo2);
    }
    
} 
function setListaOrigenDestino(formname, req){
	var p_tipo_ent_codigo = document.forms[formname].p_tipo_ent_codigo;
	var p_tipo_ent_codigo2 = document.forms[formname].p_tipo_ent_codigo2;
	p_tipo_ent_codigo.options.length = 0;
	p_tipo_ent_codigo2.options.length = 0;
	cleanAndEnabledSelect(document.forms[formname].p_tipo_ent_codigo);
	cleanAndEnabledSelect(document.forms[formname].p_tipo_ent_codigo2);
	var optionsorigen = req.responseXML.getElementsByTagName("opcionOrigen");
	p_tipo_ent_codigo.options.length = optionsorigen.length;
	p_tipo_ent_codigo.options[0]=new Option("","");
	
		
	for (i=1;i<=optionsorigen.length;i++) {	   		
 		p_tipo_ent_codigo.options[i] = new Option(optionsorigen[i-1].attributes.getNamedItem("descripcion").value, optionsorigen[i-1].attributes.getNamedItem("valor").value);
 		
 	} 	
	var optionsdestino = req.responseXML.getElementsByTagName("opcionDestino");
	p_tipo_ent_codigo2.options.length = optionsdestino.length;
	p_tipo_ent_codigo2.options[0]=new Option("","");
	for (i=1;i<=optionsdestino.length;i++) {	   		
 		p_tipo_ent_codigo2.options[i] = new Option(optionsdestino[i-1].attributes.getNamedItem("descripcion").value, optionsdestino[i-1].attributes.getNamedItem("valor").value);
 		
 	} 	
}
