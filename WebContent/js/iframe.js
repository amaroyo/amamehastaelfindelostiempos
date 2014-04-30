function dimensionamos() {
	
   	window.resizeTo(document.body.scrollWidth,document.body.scrollHeight);
   
}
function activa(obj){
	var activo = document.getElementById("current");
	if (activo) {
		activo.id='';
	  	obj.id='current';
	  	}
}
/*function mostrariframe(obj){
	var activo = document.getElementById("visible");
	if (activo) {
		activo.id='hidden';
	  	obj.id='visible';
	  	}
}
*/
function redimensionamos(iframe) {
	var activa=document.getElementById(iframe);	
	var contenido = eval("document." + activa.name + ".document.getElementById('contenido')");
	if (contenido)	activa.height = contenido.scrollHeight + 10;
	
/*
	alert('iframe ' + activa.name + '->'+ activa.contentWindow.name);
	alert(window.document.body.scrollWidth + '*' + window.document.body.scrollHeight);
	var w=window.document.body.scrollWidth;
	var h=window.document.body.scrollHeight;
	activa.contentWindow.resizeTo(w,h);	
   	alert(window.document.body.scrollWidth + '*' + window.document.body.scrollHeight);
   	alert(activa.document.body.scrollWidth+'vs'+activa.document.body.scrollHeight);  	
*/   
}
function cargarContador(obj, valor){
	var contador = document.getElementById('cont_'+obj);
	if (contador != null)
		contador.innerHTML=valor;
}
function cargadaPestanaSelected(iframe){
	redimensionamos(iframe);
}

// Funciones para la capa de cargandoOpaco
function ajustarCapa() {
	var capa = document.getElementById("cargando");
	var width = document.body.clientWidth;
	var height = document.body.clientHeight;
	capa.style.width = width;
	capa.style.height = height;
	capa.style.visibility = "visible";
}		

function cargadas() {
	var capa = document.getElementById("cargando");
	capa.style.width = 0;
	capa.style.height = 0;			
	capa.style.visibility = "hidden";
}

var numIFramesPdteCargar = 0;
function iframeCargado() {
	numIFramesPdteCargar  = numIFramesPdteCargar - 1;
	// Si es cero, ya se han cargado todos los iframes
	if (numIFramesPdteCargar == 0) cargadas();
}		


function cargando() {			
	mostrarCargando();
	window.onunload = ocultarCargando;
}
function mostrarCargando() {
	window.parent.capacargando(true);
}
function ocultarCargando() {
	window.parent.capacargando(false);
}		
function capacargando(mostrar) {
	alert("Operacion: " + mostrar);
}
