function entrarPestanya(capa)
{
	if(document.getElementById(capa).className!="pestanasMenu_marcada"){
		document.getElementById(capa).className = "entrar";
	}
}

function salirPestanya(capa)
{
	if(document.getElementById(capa).className!="pestanasMenu_marcada"){
		document.getElementById(capa).className = "pestanasMenu";
	}
}

function cargarPestanya(capa)
{
	document.location.href = "consultas.do?pestana="+capa;
	cargando();
}