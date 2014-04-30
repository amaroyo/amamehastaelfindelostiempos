	/******** FUNCION PARA LECTURA DE NODOS XML *********************/
	function getNodeValue(root,node){
		var resultado = root.getElementsByTagName(node);
		if (resultado != null && resultado[0] != null && resultado[0].childNodes[0] != null && typeof resultado[0].childNodes[0] != "undefined") {
			return resultado[0].childNodes[0].data;
		}
		return "";	
	}

	
	/********************** FUNCION PARA CONSTRUIR LA URI DE LOS DATOS DE UN FORMULARIO **************************/
	function buildURIFromForm(formName)
	{
		var cadena = "";
		var frm = document.getElementById(formName);
		for (var i=0;i<frm.elements.length;i++)
		{
			if (frm.elements[i].type != 'fieldset') {
				if(frm.elements[i].name != undefined){
					if(frm.elements[i].type == 'checkbox'){
						if(frm.elements[i].checked == true){
							cadena+=frm.elements[i].name+"="+frm.elements[i].value+"&";
						}
					}else{
						cadena+=frm.elements[i].name+"="+frm.elements[i].value+"&";
					}
				}
			}
		}
		return cadena;
	}

		
	/********************** FUNCION PARA SETEAR LOS DATOS DE UN FORMULARIO DESDE UN XML **************************/
	function setElementsFormFromXML(formName, xml)
	{	
		var root = xml.getElementsByTagName("data")[0];
		
		var frm = document.getElementById(formName);
		for (i=0;i<frm.elements.length;i++)
		{
			if(frm.elements[i].name != undefined){
				var valorNodoXML = getNodeValue(root, frm.elements[i].name);
//				if(xml.getElementsByTagName(frm.elements[i].name)[0].childNodes[0] != undefined){
//					valorNodo = xml.getElementsByTagName(frm.elements[i].name)[0].childNodes[0].nodeValue;
//				}
				if(frm.elements[i].type == 'checkbox'){
					frm.elements[i].checked = eval(valorNodoXML);
				}else if(frm.elements[i].type == 'radio'){
					if (valorNodoXML == frm.elements[i].value) {
						frm.elements[i].checked = true;
					}
				} else {
					frm.elements[i].value = valorNodoXML;
				}
			}
		}
	}

	
	/** Funcion que controla los errores generales de carga de componentes */
	function errorHandler(type, desc, erData){
		//alert("Se ha producido un error durante el proceso de carga")
	}

	/**
 	 * Funcion que muestra un mensaje de dhtmlx message para errores y confirmaciones
	 * @param xml   <data><action type="error|info|update|..." >Texto del mensaje</action></data>
	 * @param isConfirm, true para mostrar mensaje de información, false para mostrar sólo si hay error
	 * @return
	 */
	function displayMessage(xml, isConfirm)
	{
		
		var data = xml.getElementsByTagName("data")[0];
		var nodoAction;
		if(data != null && typeof data != "undefined"){
			nodoAction = data.getElementsByTagName("action")[0];
		}else{
			nodoAction = xml;
		}
		
		if(nodoAction != null && typeof nodoAction != "undefined" && nodoAction.firstChild !=null && typeof nodoAction.firstChild !="undefined"){
			
			var tipo = nodoAction.getAttribute("type");
			var message = nodoAction.firstChild.data;
			
			if(tipo == "error") {
				dhtmlx.message(message,tipo,4000);
			}
			else if(isConfirm)
				dhtmlx.message(message,"info",2000);
		}
	}
	
///////////////////////////////// PARSEO DE OBJETOS /////////////////////////////////////////////////////////
	/**
	 * Objeto Nota en Javascript que corresponde con el Objeto NotaVO de Java.
	 */
	nota = {
		idNota:'',
		titulo: '',
		fechaCreacion: '',
		fechaModificacion: '',
		contenido: ''
	};
	
	
	/**
	 * Funcion que parsea un XML y lo convierte en una Nota de Javascript.
	 * @param xml - XML a parsear.
	 */
	function fromXMLtoNota(xml) {
		
		var data = xml.getElementsByTagName('data')[0];		
		if(data != null && typeof data != 'undefined') {

			nodoAction = data.getElementsByTagName('action')[0];			
			if(nodoAction != null && typeof nodoAction != 'undefined') {
				
				var tipo = nodoAction.getAttribute('type');
				if (tipo == 'edit' || tipo == 'new') {
					
					nodoNota = data.getElementsByTagName('nota')[0];
					if(nodoNota != null && typeof nodoNota != 'undefined') {
						
						this.nota.idNota = getNodeValue(nodoNota, 'idNota');
						this.nota.titulo = getNodeValue(nodoNota, 'title');
						this.nota.fechaCreacion = getNodeValue(nodoNota, 'fechaCreacion');
						this.nota.fechaModificacion = getNodeValue(nodoNota, 'fechaModificacion');
						this.nota.contenido = getNodeValue(nodoNota, 'content');
					}
					
				} else if (tipo == "error") {
					displayMessage(xml, false);
				} 
			}
		} 
	}
	
	/**
	 * Funcion que parsea un XML desde el action de traduccion.
 	 * Obtiene el texto traducido y lo devuelve como un string.
 	 * @param xml - El fichero XML que contiene la traducción.
 	 * @return String - Cadena de texto plano con la traducción.
	 */
	function parseaTraduccion(xml) {
		var traduccion = null;
		
		var data = xml.getElementsByTagName('data')[0];		
		if(data != null && typeof data != 'undefined') {

			nodoAction = data.getElementsByTagName('action')[0];			
			if(nodoAction != null && typeof nodoAction != 'undefined') {
				var tipo = nodoAction.getAttribute('type');
				if (tipo == 'traduccion') {
					traduccion = getNodeValue(data, 'action');
				}
				else if (tipo == 'error') {
					traduccion = null;
				}
			}
		}
		return traduccion;
	}
	
	function parseaMensajeVisorEntidades(xml){
		var data, nodoAction, nodoEntidad, result;
		result = "";
		data = xml.getElementsByTagName('data')[0];		
		if(data != null && typeof data != 'undefined') {

			nodoAction = data.getElementsByTagName('action')[0];			
			if(nodoAction != null && typeof nodoAction != 'undefined') {
				
				nodoEntidad = data.getElementsByTagName('entidad')[0];			
				if(nodoEntidad != null && typeof nodoEntidad != 'undefined') {
					
					var idEntidad = nodoEntidad.getAttribute('idEntidad');
					var tipoEntidad = nodoEntidad.getAttribute('tipoEntidad');
					var descripcion = nodoEntidad.getAttribute('descripcion');
					var idCarpeta = nodoEntidad.getAttribute('idCarpeta');
					
					result = "<?xml version='1.0' encoding='UTF-8'?>\
					<response-diagram>\
				    <resultado><codigo>0</codigo><mensaje>OK</mensaje></resultado>\
				    <response-new-entity>\
				         <entidad id='"+ idEntidad +"' tipoentidad='"+ tipoEntidad +"' descripcion='"+ descripcion +"'></entidad>\
				    </response-new-entity>\
				    </response-diagram>";
										
				}
			}
		}
		return result;
	}
	
	/**
	 * Funcion que parsea un XML con formato de Dhtmlx devuelto por un action.
 	 * Retorna el id del elemento que provocó cualquiera de las operaciones: insert, update, delete , drag and drop o incluso
 	 * drag and drop de grid a árbol se produjeron sin errores o retona -1 si se produjo algún fallo.
 	 * @param xml - El fichero XML que contiene la respuesta desde el servidor.
 	 * @param showMessage - Booleano que indica si quieres mostrar un mensaje.
 	 * @return sid - El id del elemento que provocó la llamada. En caso de error devuelve -1.
	 */
	function processResponseXMLAjax(xml, showErrorMessage) {
		var data = xml.getElementsByTagName('data')[0];		
		if(data != null && typeof data != 'undefined') {
			nodoAction = data.getElementsByTagName('action')[0];			
			if(nodoAction != null && typeof nodoAction != 'undefined') {
				
				var tipo = nodoAction.getAttribute('type');
				if (tipo == 'insert' || tipo == 'update' || tipo == 'delete' || tipo == 'draganddrop' || tipo == 'dndgridtotreeop' || tipo == 'copy' || tipo == 'cut') {
					return nodoAction.getAttribute('tid');
				} else if (tipo == 'error') {
					if (showErrorMessage)
						displayMessage(xml, true);
					return -1;
				}
			}
		}
	};