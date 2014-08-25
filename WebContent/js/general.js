//**********************/* fin validacion inputs forms*/**********************//

function validateCIF(cif) {
	var valueCif=cif.substr(1,cif.length-2);
	var suma=0;
	for(i=1;i<valueCif.length;i=i+2) {
		suma=suma+parseInt(valueCif.substr(i,1));
	}
	
	var suma2=0;
	for(i=0;i<valueCif.length;i=i+2) {
		result=parseInt(valueCif.substr(i,1))*2;
		if(String(result).length==1) {
			suma2=suma2+parseInt(result);
		}
		else{
			suma2=suma2+parseInt(String(result).substr(0,1))+parseInt(String(result).substr(1,1));
		}
	}
	suma=suma+suma2;
	var unidad=String(suma).substr(1,1);
	unidad=10-parseInt(unidad);
	var primerCaracter=cif.substr(0,1).toUpperCase();
	if(primerCaracter.match(/^[FJKNPQRSUVW]$/)) {
		if(String.fromCharCode(64+unidad).toUpperCase()==cif.substr(cif.length-1,1).toUpperCase())
			return true;
		}
	else if(primerCaracter.match(/^[XYZ]$/)){
		var newcif;
		if(primerCaracter=="X")
			newcif=cif.substr(1);
		else if(primerCaracter=="Y")
			newcif="1"+cif.substr(1);
		else if(primerCaracter=="Z")
			newcif="2"+cif.substr(1);
		return validateDNI(newcif);
	}
	else if(primerCaracter.match(/^[ABCDEFGHLM]$/)){
		if(unidad==10)
			unidad=0;
		if(cif.substr(cif.length-1,1)==String(unidad))
			return true;
	}
	else{
		return validateDNI(cif);
	}
	return false;
 }

function getExtension(fileName) {
    var parts = fileName.split('.');
    return parts[parts.length - 1];
}

function isImageExtension(fileName) {
    var ext = getExtension(fileName);
    switch (ext.toLowerCase()) {
        case 'jpg':
        case 'jpeg':
        case 'bmp':
        case 'gif':
        case 'png':
        //etc
        return true;
    }
    return false;
}


function ucmEsEmail(email) {
	if (getDomain(email) == "ucm.es") {
		return true;
	}
	else {
		form.setNote("correo", { text: '<bean:message key="message.email.institucional" />'} );
		return false;
	}
}

function getDomain(email) {
    var parts = email.split('@');
    return parts[parts.length - 1];
}

function validateDNI(dni) {
	var lockup = 'TRWAGMYFPDXBNJZSQVHLCKE';
	var valueDni=dni.substr(0,dni.length-1);
	var letra=dni.substr(dni.length-1,1).toUpperCase();
 	if(lockup.charAt(valueDni % 23)==letra)
 		return true;
 	return false;
 }

//**********************/* fin validacion inputs forms*/**********************//

// Funcion: go
// Descripcion: Funcion que se redirecciona a la ruta (path) pasada como parametro.
// Parametros:
//  path: Ruta a la que redirigirse.
// ******************************************************************
function go(path) {
    document.location.href = path;
}

function selectAllCheckBox(opciones) {
    if (typeof opciones.length == "undefined") {
        opciones.checked = true;
    } else {
        for (var i = 0; i < opciones.length; i++) {
            opciones[i].checked = true;
	}
    }
}
function deselectAllCheckBox(opciones) {
    if (typeof opciones.length == "undefined") {
        opciones.checked = false;
    } else {
        for (var i = 0; i < opciones.length; i++) {
            opciones[i].checked = false;
	}
    }
}
function selectAllSelect(opciones) {
	for (var i = 0; i < opciones.length; i++) {
		opciones[i].selected = true;
	}
}
function deselectAllSelect(opciones) {
	for (var i = 0; i < opciones.length; i++) {
		opciones[i].selected = false;
	}	
}
function addValue(select, value, text) {
	if (value != '' && text != '') {
		select.options[select.length] = new Option(text, value);
	}
}
function delSelectedAll(select) {
	while (select.selectedIndex > -1) {
		select.options[select.selectedIndex] = null;
	}
}



var separadorFecha = "/";
function alerta(a){alert(a);}
defaultStatus = "";


function lTrim(str) {return str.replace(/^(\s)*/, "");}
function rTrim(str) {return str.replace(/(\s)*$/, "");}
function trim(str) {return lTrim(rTrim(str));}
function isEmpty(str){return ((str == null) || (trim(str).length == 0))}
function isInt(str){regExp=/^\d+$/;return regExp.test(str);}
function isFloat(str){regExp=/^((\d*\.\d+)|(\d+\.\d+)|(\d+))$/;return regExp.test(str);}
function isHex(str){regExp=/^(\d|[a-f]|[A-F])+$/;return regExp.test(str);}
function isMail(str) {regExp = /^.+\@.+\..+$/;return regExp.test(str);}
function isIP(str){
	var arr = str.split(".");
	if(arr.length!=4)return false;
	for(i=0;i<4;i++){if(!isInt(arr[i]) || arr[i]<0 || arr[i]>256)return false;}
	return true;
}
function isTlf(str) {
	regExp = /^[^a-z]{9,}$/;
	return regExp.test(str);
}
function entra(p){
	p.style.background='#ffffff';
	p.style.color='#75A594';
}
function sale(p,color){
	p.style.background=color;
	p.style.color='#01603E';
}

function isCreditCard(st) {
  if (st.length > 19) return (false);
  sum = 0; mul = 1; l = st.length;
  for (i = 0; i < l; i++) {
    digit = st.substring(l-i-1,l-i);
    tproduct = parseInt(digit ,10)*mul;
    if (tproduct >= 10) sum += (tproduct % 10) + 1;
    else sum += tproduct;
    if (mul == 1) mul++;
    else mul--;
  }
  if ((sum % 10) == 0) return (true);
  else return (false);
}

function isBisiesto( a ){return ( a % 4 == 0 ) && ( ( a % 100 != 0 ) || ( a % 400 == 0 ) );}

function isFecha (strFecha){

	var arrayFecha = strFecha.split(separadorFecha);
	if (arrayFecha.length !=3) return false;
	dia = arrayFecha[0];
	mes = arrayFecha[1];
	ano = arrayFecha[2];
	if (!isInt(dia) || !isInt(mes) || !isInt(ano)) return false;
	return validarFecha(dia, mes, ano);
}

function validarFecha (intDia, intMes, intAno){
	if (intMes < 1 || intMes > 12) return false;
	var arrayMeses = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	if (isBisiesto(intAno)){arrayMeses[1] = 29;}
	if ((0 < intDia) && (intDia <= arrayMeses[intMes-1])){
		if (intAno>1900 && intAno<3000) return true;
	}
	return false;
}

//valida dos fechas, las compara y devuelve:
//	0 (false) si no son fechas bien formateadas
//	1 si la fecha 1 es posterior
//	2 si la fecha 2 es posterior
//  3 si las fechas son iguales
//(false en caso de que la validacion de las fechas no sea satisfactoria)
function comparaFechas(fecha1, fecha2) {
	if (isFecha(fecha1) && isFecha(fecha2)) {
		if (fecha1 == fecha2) return 3;
		fecha1Arr = fecha1.split(separadorFecha);
		fecha2Arr = fecha2.split(separadorFecha);
		if (fecha2Arr[2] > fecha1Arr[2]) {
			return 2;
		}
		if ((fecha2Arr[2] == fecha1Arr[2]) && (fecha2Arr[1] > fecha1Arr[1])) {
			return 2;
		}
		if ((fecha2Arr[2] == fecha1Arr[2]) && (fecha2Arr[1] == fecha1Arr[1]) && (fecha2Arr[0] > fecha1Arr[0])) {
			return 2;
		}
		return 1;
	}
	return false;
}


// Verifica que en el primer string solo aparezcan los del segundo
function verificarCaracteres(strTexto, strCaractValidos) {
	if (strTexto=="") return true;
	for (intCont=0 ; intCont< strTexto.length ; intCont++) {
		if (strCaractValidos.indexOf(strTexto.charAt(intCont))<0) {
			return false;
		}
	}
	return true;
}

//cambia en strOriginal
function cambiarCaracter (strOriginal, chrBuscar, strReplace)
{
	var ss = "";
	for(i=0; i< strOriginal.length; i++) {
		if( strOriginal.charAt(i) == chrBuscar) {
			str += chrReplace;
		}
		else {
			str += strOriginal.charAt(i);
		}
	}
return ss;
}


function Browser() {
        var b=navigator.appName;
        if (b=="Netscape") this.b="ns";
        else if (navigator.userAgent.indexOf("Opera")>0) this.b = "opera";
        else if (b=="Microsoft Internet Explorer") this.b="ie";
        if (!b) alert('Unidentified browser./nThis browser is not supported,');
        this.version=navigator.appVersion;
        this.v=parseInt(this.version);
        this.ns=(this.b=="ns" && this.v>=4);
        this.ns4=(this.b=="ns" && this.v==4);
        this.ns5=(this.b=="ns" && this.v>=5);
        this.ie=(this.b=="ie" && this.v>=4);
        this.ie4=(this.version.indexOf('MSIE 4')>0);
        this.ie5=(this.version.indexOf('MSIE 5')>0);
        this.ie55=(this.version.indexOf('MSIE 5.5')>0);
        this.ie5=(this.ie && !this.ie4);
        this.opera=(this.b=="opera");
        this.dom=((document.createRange&&(document.createRange().createContextualFragment))?true:false);
        var ua=navigator.userAgent.toLowerCase();
        if (ua.indexOf("win")>-1) this.platform="win32";
        else if (ua.indexOf("mac")>-1) this.platform="mac";
        else this.platform="other";
}
is = new Browser();
  function DiaDelAnyo()  {
    var fecha = new Date();
    var dia, dia_sem, mes, anyo;

    dia = fecha.getDate();
    dia_sem = fecha.getDay();
    mes = fecha.getMonth();
    anyo = fecha.getYear();
    if(anyo<2000) anyo += 1900;

    switch(dia_sem)  {
      case 0: dia_sem = 'Domingo'; break;
      case 1: dia_sem = 'Lunes'; break;
      case 2: dia_sem = 'Martes'; break;
      case 3: dia_sem = 'Miercoles'; break;
      case 4: dia_sem = 'Jueves'; break;
      case 5: dia_sem = 'Viernes'; break;
      case 6: dia_sem = 'Sabado'; break;
      }

    switch(mes)  {
      case 0: mes = 'Enero'; break;
      case 1: mes = 'Febrero'; break;
      case 2: mes = 'Marzo'; break;
      case 3: mes = 'Abril'; break;
      case 4: mes = 'Mayo'; break;
      case 5: mes = 'Junio'; break;
      case 6: mes = 'Julio'; break;
      case 7: mes = 'Agosto'; break;
      case 8: mes = 'Septiembre'; break;
      case 9: mes = 'Octubre'; break;
      case 10: mes = 'Noviembre'; break;
      case 11: mes = 'Diciembre'; break;
      }

    document.write("<span class='textomenu2'>" + dia_sem + ", " + dia + " de " + mes + " de " + anyo + "</span>");
    }


  function DiaDelAnyo2()  {
	fecha = new Date();
   var fecha = new Date();
   var dia, dia_sem, mes, anyo;
	var arrayMeses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
	var arrayDia = new Array("Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo");
    dia = fecha.getDate();
    dia_sem = fecha.getDay();
    mes = fecha.getMonth();
    anyo = fecha.getYear();

	dia   = arrayDia[dia_sem] + ",&nbsp;" + dia + "&nbsp;de&nbsp;" + arrayMeses[mes] + "&nbsp;de&nbsp;" + anyo + "&nbsp;&nbsp;";
	horas = (("" + fecha.getHours()).length == 1)?"0" + fecha.getHours():fecha.getHours();
	mins  = (("" + fecha.getMinutes()).length == 1)?"0" + fecha.getMinutes():fecha.getMinutes();
	segs  = (("" + fecha.getSeconds()).length == 1)?"0" + fecha.getSeconds():fecha.getSeconds();

	strEscribe = "<table cellpadding=0 cellspacing=0 border=0><tr>";
	strEscribe += "<td nowrap class=textomenu2 align=left>" + dia + "</td>";
	//strEscribe += "<td class=textomenu2 align=right valign=center>" + horas + ":" + mins + ":" + segs + "&nbsp;</td>";
	strEscribe += "</tr></table>";
	document.write("<div id='CapaFecha' style='position:relative; left:00px; top:00px; width:100%; height:00;visibility:visible; z-index:11;'>" + strEscribe + "</div>");
	//CapaFecha.innerHTML=strEscribe;
	setTimeout("mantenerHora('es')", 1000);
}



  var i = 0;
  function numFoto(numFotos)  {
	i++;
   document.write("<span class='textomenu2'>foto:" + i + "/" + numFotos + "</span>");
}
	function restaNumFoto()  {
		i++;
	   return i;
	}
var oCapaFecha;
function mantenerHora(capa,idioma) {
	fecha = new Date();
   var fecha = new Date();
   var dia, dia_sem, mes, anyo;
	var arrayMeses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
	var arrayMesesEn = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
	var arrayDia = new Array("Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo");
    if (idioma == "en") {
    	arrayMeses = arrayMesesEn;
    }
    dia = fecha.getDate();
    dia_sem = fecha.getDay();
    mes = fecha.getMonth();
    anyo = fecha.getYear();

	dia   = arrayDia[dia_sem] + ",&nbsp;" + dia + "&nbsp;de&nbsp;" + arrayMeses[mes] + "&nbsp;de&nbsp;" + anyo + "&nbsp;&nbsp;";
	horas = (("" + fecha.getHours()).length == 1)?"0" + fecha.getHours():fecha.getHours();
	mins  = (("" + fecha.getMinutes()).length == 1)?"0" + fecha.getMinutes():fecha.getMinutes();
	segs  = (("" + fecha.getSeconds()).length == 1)?"0" + fecha.getSeconds():fecha.getSeconds();

	strEscribe = "<table cellpadding=0 cellspacing=0 border=0><tr>";
	strEscribe += "<td nowrap class=textomenu2 align=left>" + dia + "</td>";
	//strEscribe += "<td class=textomenu2 align=right valign=center>" + horas + ":" + mins + ":" + segs + "&nbsp;</td>";
	strEscribe += "</tr></table>";
	//CapaFecha = document.getElementById("CapaFecha");
	CapaFecha.innerHTML=strEscribe;
	if (is.ie)CapaFecha.timer = setTimeout("mantenerHora('" + idioma + "')", 1000);
}



var menuskin=0
var display_url=0
function showmenuie5(){
	var rightedge=document.body.clientWidth-event.clientX
	var bottomedge=document.body.clientHeight-event.clientY
	if (rightedge<ie5menu.offsetWidth)
		ie5menu.style.left=document.body.scrollLeft+event.clientX-ie5menu.offsetWidth
	else
		ie5menu.style.left=document.body.scrollLeft+event.clientX
	if (bottomedge<ie5menu.offsetHeight)
		ie5menu.style.top=document.body.scrollTop+event.clientY-ie5menu.offsetHeight
	else
		ie5menu.style.top=document.body.scrollTop+event.clientY
		ie5menu.style.visibility="visible"
	return false
}
function hidemenuie5(){
	ie5menu.style.visibility="hidden"
}
function highlightie5(){
	if (event.srcElement.className=="menuitems"){
		event.srcElement.style.backgroundColor="#ffffff"
		event.srcElement.style.color="#01603E"
		if (display_url==1)
			window.status=event.srcElement.url
	}
}
function lowlightie5(){
	if (event.srcElement.className=="menuitems"){
		event.srcElement.style.backgroundColor=""
		event.srcElement.style.color="#01603E"
		window.status=''
	}
}
function desactivarFormulario(form) {
  var componentes = form.elements;
  for (i=0;i<componentes.length;i++) {
    var componente = componentes[i];
    if (componente.type!="hidden") {
      componente.disabled = true;
    }
  }
}
//*********************  COLAPSADORES ***************//
var previousColapse = "";
function colapsar(idcelda) {
  var capaInterior = idcelda +'_contenido';
  if (document.getElementById(capaInterior).className == "esconder") {
         //Si hay algun desplegable abierto, lo cerramos (por motivos esteticos de pantalla)
    if (previousColapse!="") colapsar(previousColapse);
    document.getElementById(capaInterior).className = "mostrar";
    document.getElementById(idcelda + "_imagen").src = 'img/ordenar-arriba.gif';
    document.getElementById(idcelda + "_imagen").alt = 'Ocultar';
    previousColapse = idcelda;
  } else {
    document.getElementById(capaInterior).className = "esconder";
    document.getElementById(idcelda + "_imagen").src = 'img/ordenar-abajo.gif';
    document.getElementById(idcelda + "_imagen").alt = 'Mostrar';
    previousColapse = "";
  }
}
//********************* FIN COLAPSADORES ***************//

//**************** FUNCIONES PARA IMPRIMIR ************//
function imprimirListado(url) {
  impresora.location.href = url;
}
function imprimir() {
  parent.impresora.focus();
  parent.window.print();
}
//**************** FIN FUNCIONES PARA IMPRIMIR ************//


//////////////IMAGENES SIMULANDO CARGA///////////
function cargando() {
  var capa = document.getElementById("cargando");
  var top = ((this.window.document.body.clientHeight / 2 ) - 150) + "px";
  var left = ((this.window.document.body.clientWidth / 2 ) - 200) + "px";
  capa.style.left = left;
  capa.style.top = top;
  capa.style.visibility="visible";
}

function cargandoOpcion(done) {
 var capa = document.getElementById("cargando");
 if (!done) {
//  var top = ((this.window.document.body.scrollTop + (this.window.document.body.clientHeight / 2) )-45) + "px";
  var top = ((this.window.document.body.scrollTop + (this.window.document.body.clientHeight / 2) )-60) + "px";
  var left = ((this.window.document.body.scrollLeft + (this.window.document.body.clientWidth / 2) )-160) + "px";
  capa.style.left = left;
  capa.style.top = top;
  capa.style.visibility="visible";
 } else {
   capa.style.visibility="hidden";
 }
}

//////////////////////// JS MANTENIMIENTO DE CLAVES DE USUARIO ////////////////
function verificarCaract(strTexto) {
  var strCaractValidos = "abcdefghijklmnopqrstuvwxyz";
  strCaractValidos += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  strCaractValidos += "0123456789_-*";
  for (intCont=0 ; intCont< strTexto.length ; intCont++) {
    if (strCaractValidos.indexOf(strTexto.charAt(intCont))<0) {
      return "Ha indtroducido un caracter no valido\n Solo se admiten letras, numeros,asteriscos y los guiones bajo y normal";
    }
  }
  return "";
}
function verificarLetras(strTexto) {
	var strCaractValidos = "abcdefghijklmnopqrstuvwxyz";
	strCaractValidos += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	for (intCont=0 ; intCont< strTexto.length ; intCont++) {
		if (strCaractValidos.indexOf(strTexto.charAt(intCont))>-1) {
			return true;
		}
	}
	return false;
}

function verificarNumeros(strTexto) {
	var strCaractValidos = "0123456789";
	for (intCont=0 ; intCont< strTexto.length ; intCont++) {
		if (strCaractValidos.indexOf(strTexto.charAt(intCont))==-1) {
			return false;
		}
	}
	return true;
}

function verificarPassword(strTexto) {
  var strLetras = "abcdefghijklmnopqrstuvwxyz";
  strLetras += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  var strNumeros = "0123456789";
  var strEspeciales = "_-*";
  var letras = false;
  var numeros = false;
  var especiales = false;

  for (intCont=0 ; intCont< strTexto.length ; intCont++) {
    var character = strTexto.charAt(intCont);
    if (!letras && strLetras.indexOf(character) != -1) {
      letras = true;
    }
    if (!numeros && strNumeros.indexOf(character) != -1) {
      numeros = true;
    }
    if (!especiales && strEspeciales.indexOf(character) != -1) {
      especiales = true;
    }
  }
  if (letras && numeros && especiales) return true;
  return false;
}

  function calculaNif(nif) {
    cadena="TRWAGMYFPDXBNJZSQVHLCKET"
    posicion = nif % 23
    letra = cadena.substring(posicion,posicion+1)
    return letra
  }

function DiferenciaFechas(CadenaFecha2,diasMin) {
   var msg = "La fecha no es valida, revisela por favor";
 if(isFecha(CadenaFecha2)){
   msg = "Debe haber un minimo de 45 dias de validez de la clave";
   //Obtiene los datos del formulario
   CadenaFecha1 = fechaHoy();
   //Obtiene dia, mes y anyo
   var fecha1 = new fecha( CadenaFecha1 )
   var fecha2 = new fecha( CadenaFecha2 )

   //Obtiene objetos Date
   var miFecha1 = new Date( fecha1.anio, fecha1.mes, fecha1.dia )
   var miFecha2 = new Date( fecha2.anio, fecha2.mes - 1, fecha2.dia )
   //Resta fechas y redondea
   var diferencia = miFecha2.getTime() - miFecha1.getTime()
   var dias = Math.floor(diferencia / (1000 * 60 * 60 * 24))
   //alert ('La diferencia es de ' + dias + ' dias')
   msg = (dias < diasMin)?msg:"";
 }
   return msg;
}

function fecha(cadena) {

   //Separador para la introduccion de las fechas
   var separador = "/"

   //Separa por dia, mes y anyo
   if ( cadena.indexOf( separador ) != -1 ) {
        var posi1 = 0
        var posi2 = cadena.indexOf( separador, posi1 + 1 )
        var posi3 = cadena.indexOf( separador, posi2 + 1 )
        this.dia = cadena.substring( posi1, posi2 )
        this.mes = (cadena.substring( posi2 + 1, posi3 ))
        this.anio = cadena.substring( posi3 + 1, cadena.length )
   } else {
        this.dia = 0
        this.mes = 0
        this.anio = 0
   }
}

/* Funcion que devuelve en un String la fecha de Hoy
 * @return - fechaDia: La fecha de Hoy
 */
function fechaHoy()  {
    var fecha = new Date();
    var dia, mes, anyo;
    dia = fecha.getDate();
    mes = fecha.getMonth();
    if (mes.toString().length < 2) {
    	mes = '0' + mes.toString();
    }
    anyo = fecha.getFullYear();
    var fechaDia = dia + "/" + mes + "/" + anyo;
    return fechaDia;
}


// funciones para las pestanyas
var capaMarcada = "propiedades";
function mostrar(capa){
        if(capaMarcada != "")
          document.getElementById(capaMarcada).className = "pestanas";
        document.getElementById(capa).className = "pestanasMarcada";
        capaMarcada = capa;
        colapsarPestanas(capa);
        //alert(capa + "  " + document.getElementById(capa).className);
}

function entrar(capa){
	if(capaMarcada != capa)
			document.getElementById(capa).className = "entrar";
}
function salir(capa){
	if(capaMarcada != capa)
			document.getElementById(capa).className = "pestanas";
}
var previousPestana = "";
function colapsarPestanas(idcelda) {
  if (previousPestana == "")
    document.getElementById(idcelda).className = "pestanasMarcada";
  var capaInterior = idcelda +'_contenido';
  if (document.getElementById(capaInterior).className == "esconder") {
         //Si hay algun desplegable abierto, lo cerramos (por motivos esteticos de pantalla)
    if (previousPestana!="") colapsarPestanas(previousPestana);
    document.getElementById(capaInterior).className = "mostrar";
    previousPestana = idcelda;
  } else {
    document.getElementById(capaInterior).className = "esconder";
    previousPestana = "";
  }
}

/////////////// CAMBIAR CONTRASENYA //////////////////
function cambiarContrasenya() {
    window.location.href = "./controller?action=Web&subaction=gestion_password";
}

/////////////// FUNCIONES DE VENTANAS ///////////////
// Detectar el cierre de las ventana "closeEvent" 
var windowClosedEvent = true;
function cerrarAplicacion(url) {
    if (windowClosedEvent) {
        openWindow(url, "Logout", 500, 250, false);
    }
}
function desconectar(aplicacionId, url) {
    try {
        if (window.opener != window.self) {
            window.opener.top.cerrarAplicacion(aplicacionId);
        }
    } catch (e) {}
    cerrarAplicacion(url);
}

//Abre una ventana y la centra en la pantalla
function openModal(url, arguments, width, height, fitToSize) {
    var _width = width;
    var _height = height;
    if (fitToSize) {
        _width = screen.width - 100;
        _height = screen.height - 100;    	    	
    }

    var left = screen.width/2 - _width/2;
    var top = (screen.height)/2 - _height/2;
	var properties = "dialogWidth:" + _width + "px; dialogHeight:" + _height + "px; dialogLeft:" + left + "px; dialogTop:" + top + "px; status=no; center=yes; resizable=yes";
	dialogArgumentsArray = new Array();
	dialogArgumentsArray[0] = url;
	dialogArgumentsArray[1] = arguments;
	
	return window.showModalDialog("modaldialog.do", dialogArgumentsArray, properties);
}

// Abre una ventana y la centra en la pantalla

function openModalWindow(url, arguments, width, height) {
   var left = screen.width/2 - width/2;
    var top = (screen.height)/2 - height/2;
	var properties = "dialogWidth:" + width + "px; dialogHeight:" + height + "px; center=yes,resizable=no,status=yes,scroll=no";
	var time = new Date().getTime();
	
	//return window.open(url, "ventana");
	return window.showModalDialog("modaldialog.do", url, properties);
}

function openWindow(url, name, width, height, status) {
    var left = screen.width/2 - width/2;
    var top = (screen.height)/2 - height/2;
    width = width - 10;
    height = height - 77;
    var properties = "toolbar=no,menubar=no,scrollbars=auto,resizable=yes,location=no,directories=no";

    if (width) properties += ",width=" + width + ",left=" + left;
    if (height) properties += ",height=" + height + ",top=" + top;
    if (status) properties += ",status=yes";
    var ventana = window.open(url, name, properties);
    ventana.focus();
    return ventana;
}


function abrirVentanaModal(url, arguments, width, height, status) {
    var left = screen.width/2 - width/2;
    var top = screen.height/2 - height/2;
	var properties = "dialogWidth:" + width + "px; dialogHeight:" + height + "px; dialogLeft:" + left + "px; dialogTop:" + top + "px; status=" + (status ? "yes" : "no") + "; center=yes";
	return window.showModalDialog("modaldialog.do", url, properties);
	//return window.showModalDialog(url, arguments, properties);
}

/////////////// FUNCIONES AJAX ///////////////////////
var funcionSuccessfull = "";
function loadURL(url,funcion) {
    req = false;
    // for native XMLHttpRequest object
    if(window.XMLHttpRequest) {
        try {
            req = new XMLHttpRequest();
        } catch(e) {
            req = false;
        }
    // for IE/Windows ActiveX version
    } else if(window.ActiveXObject) {
        try {
            req = new ActiveXObject("Msxml2.XMLHTTP");
      	} catch(e) {
            try {
                req = new ActiveXObject("Microsoft.XMLHTTP");
            } catch(e) {
          		req = false;
            }
        }
    }
    if(req) {
        if(funcion==null || funcion==''){
            funcionSuccessfull = "successfull(req)";
        } else {
            funcionSuccessfull = funcion;
        }
        req.onreadystatechange = processReqChange;
        req.open("GET", url, true);
        req.setRequestHeader( "If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT" );
        req.send( null );
    }
}
// Procesa el estado de la respuesta del servidor a la llamada 
function processReqChange() {
    if (req.readyState == 4) { //si se ha ejecutado la llamada
        if (req.status == 200) { //si no hay ningun error
            //successfull(req);
            eval(""+funcionSuccessfull);
        } else {
            error(req.statusText);
        }
    }
}
//Funcion a la que se llama si no se produce ningun error en la llamada
function successfull() {
    alert("Proceso finalizado satisfactoriamente");
    return true;
}
//Funcion a la que se llama si se produce algun error durante la llamada
function error(msg) {
    alert("Se ha producido un error durante la ejecucion del proceso: " + msg);
}



//
//
// FUNCIONES PARA LA GESTION DE COOKIES
//
//
function createCookie(name, value, days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime() + (days*24*60*60*1000));
		var expires = "; expires=" + date.toGMTString();
	} else {
		var expires = "";
	}
	document.cookie = name + "=" + value +expires + "; path=/";
}

function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(";");
	for (var i=0;i<ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') c = c.substring(1, c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
	}
	return null;
}

function deleteCookie(name) {
	createCookie(name, "", -1);
}
///////////////////////////////////////////////////


function cleanAndDisabledSelect(select) {
	select.options.length = 0;
	select.disabled = true;
}
function cleanAndEnabledSelect(select) {
	select.options.length = 0;
	select.disabled = false;
}

///////////////////////////////////////////
// FUNCIONES GENERALES DE VISUALIZACION DE DOCUMENTOS, MULTIMEDIA, IMAGENES, ETC.
//////////////////////////////////////////
function mostrarContenidoEntidad(codigo, tipoEntidad) {
	openModalWindow("mostrarcontenidoentidad.do?codigo=" + codigo + "&tipoEntidad=" + tipoEntidad, "contenido", 600, 800, true);
}

function ajustarImagenes(id, maxWidth, maxHeight) {
	var images = document.getElementById(id).getElementsByTagName("img");
	for (i=0; i<images.length; i++) {
		var width = images[i].width;
		var height = images[i].height;
		if (width > maxWidth) {
			var factor = 1 - ((width-maxWidth) / width);
			images[i].width = w * factor;
			images[i].height = height * factor;
		}
	}
}

function ajustarImagen(id, maxWidth, maxHeight) {
	var image = document.getElementById(id);
	var width = image.width;
	var height = image.height;
	if (width > maxWidth) {
		var factor = 1 - ((width-maxWidth) / width);
		image.width = w * factor;
		image.height = height * factor;
	}
}

function informeEntidad(codigo, tipoEntidad) {
	var informe = openModalWindow("generarinformeentidad.do?codigo=" + codigo + "&tipoEntidad=" + tipoEntidad, "generarinformeentidad", "1000", "700", true);
}

function ayuda() {
	openModalWindow("ayuda.do", "ayuda", "1000", "700", true);
}

///////////////////////////////////////////
//FUNCIONES GENERALES SOBRE NOTAS.
//////////////////////////////////////////

function fillDataInView() {
	idNota = nota.idNota;
	toolbar_notas.setValue('titulo', nota.titulo);

	getAppletFrame();
	if (appletFrame.document.htmleditor) {	
		editor = appletFrame.document.htmleditor;
		editor.setContent(nota.contenido);
	} else {		
		setTimeout('fillDataInView()',500);
	}
}
	
function newIdNota() {
	var loader = dhtmlxAjax.postSync('nuevanota.do'); 
	fromXMLtoNota(loader.xmlDoc.responseXML);
	idNota = nota.idNota;
}
