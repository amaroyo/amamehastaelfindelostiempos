package es.oyssen.mrm.struts.actions.asignaturas;

import es.oyssen.mrm.negocio.vo.DatosUsuarioEstanciaUnidadClinicaVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.asignaturas.UsuariosEstanciasForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.util.StringUtil;

public class UsuariosEstanciasAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		
		UsuariosEstanciasForm form = (UsuariosEstanciasForm) f;
		
		PortafolioVO portafolio = new PortafolioVO();
		
		if (!StringUtil.isNullOrBlank(form.getIdAlumno()) && !StringUtil.isNullOrBlank(form.getIdAsignatura())){
			
			portafolio.setIdAsignatura(form.getIdAsignatura());
			portafolio.setIdAlumno(form.getIdAlumno());
			portafolio.setAnyoAcademico(anyoAcademico);
			
			return getPortafoliosService().findDatosUsuarioEstanciaUnidadClinica(portafolio);
			
		}
		
		else return null;
		
	}

	@Override
	public String create(DhtmlxForm f) throws Exception {
	 /*	
		EditarUsuarioForm form = (EditarUsuarioForm) f;
		UsuarioVO usuario = new UsuarioVO();
		
		//ESTO HAY QUE CAMBIARLO A BUSCAR SEGUN EMAIL O DNI, YA QUE SON UNIQUE
		//EN ESTE CASO EL UNICO CAMPO UNIQUE A PARTE DEL ID ES EL USER (del form)
		usuario.setCorreo(form.getCorreo());
		if (getUsuariosService().findByCorreo(usuario) != null)
			return "usuario not created: correo already exists";
		else{
			usuario.setIdGrupo(form.getGrupo());
			usuario.setCorreo(form.getCorreo());
			usuario.setNombre(form.getNombre());
			usuario.setApellido1(form.getApellido1());
			usuario.setApellido2(form.getApellido2());
			usuario.setDni(form.getDni());
			usuario.setTelefono(form.getTelefono());
			usuario.setFoto(form.getFoto());
			
			if (!StringUtil.isNullOrBlank(form.getContrasenya()))
				usuario.setContrasenya(EncriptarUtil.getStringMessageDigest(form.getContrasenya(), EncriptarUtil.MD5));
			else
				usuario.setContrasenya(null);
			getUsuariosService().insert(usuario);
			return "usuario created";
		}
	*/
		
		return null;
	}
	
	@Override
	public String save(DhtmlxForm f) throws Exception {
		
		
		
		UsuariosEstanciasForm form = (UsuariosEstanciasForm) f;
		DatosUsuarioEstanciaUnidadClinicaVO data = new DatosUsuarioEstanciaUnidadClinicaVO();
		
		
		data.setIdPortafolio(form.getIdPortfolio());
		data.setIdEstanciaUnidad(form.getIdEstanciaUnidad());
		data.setCentroAsociado(form.getHospital());
		data.setUnidadClinica(form.getClinica());
		//No se puede hacer set del nombre del profesor
		data.setNombreProfesor("");
		data.setApellido1Profesor("");
		data.setApellido2Profesor("");
		data.setTurno(form.getTurno());
		data.setFechaInicio(setFechaMySQL(form.getFechaIni()));
		data.setFechaFin(setFechaMySQL(form.getFechaFin()));
		
		getPortafoliosService().updateEstancia(data);
		
		
		return "estancia unidad clinica changed";
		
	}

	

	@Override
	public String parseXML(Object o) throws Exception {
		
		
		
		DatosUsuarioEstanciaUnidadClinicaVO c = (DatosUsuarioEstanciaUnidadClinicaVO) o;
		StringBuffer sb = new StringBuffer();
		String nombreCompleto="";
		String apellidos = "";
		
		sb.append("<data>");
		sb.append("<idPortfolio><![CDATA[" + c.getIdPortafolio() + "]]></idPortfolio>");
		sb.append("<idEstanciaUnidad><![CDATA[" + c.getIdEstanciaUnidad() + "]]></idEstanciaUnidad>");
		sb.append("<hospital><![CDATA[" + c.getCentroAsociado() + "]]></hospital>");
		sb.append("<clinica><![CDATA[" + c.getUnidadClinica() + "]]></clinica>");
		sb.append("<turno><![CDATA[" + c.getTurno() + "]]></turno>");
	
		if (!c.getApellido1Profesor().equals("") || !c.getApellido2Profesor().equals("")){
			apellidos = c.getApellido1Profesor() + " " + c.getApellido2Profesor();
			nombreCompleto = apellidos + ", " + c.getNombreProfesor();
		}
		else
			nombreCompleto = c.getNombreProfesor();

		sb.append("<profesor><![CDATA[" + nombreCompleto + "]]></profesor>");
		sb.append("<fechaIni><![CDATA[" + parsearFecha(c.getFechaInicio()) + "]]></fechaIni>");
		sb.append("<fechaFin><![CDATA[" + parsearFecha(c.getFechaFin()) + "]]></fechaFin>");
		sb.append("</data>");
		
		
		return sb.toString();
	}
	
	
	private String parsearFecha(String fecha){
		String[] sp = fecha.split("-");
		String out = sp[2] + "/" +  sp[1] + "/" + sp[0];
		return out;
	}
	
	private String setFechaMySQL(String fecha) {
		String[] sp = fecha.split("/");
		String out = sp[2] + "-" +  sp[1] + "-" + sp[0];
		return out;
		
	}
	
	private String nombreGrupo(String id){
		if (id.equals("1")) return "Super Admin";
		else if (id.equals("2")) return "Coordinador";
		else if (id.equals("3")) return "Profesor";
		else if (id.equals("4")) return "Alumno";
		else if (id.equals("5")) return "Virtual Tour";
		else return "";
	}
	
	private String idGrupo(String id){
		if (id.equals("Super Admin")) return "1";
		else if (id.equals("Coordinador")) return "2";
		else if (id.equals("Profesor")) return "3";
		else if (id.equals("Alumno")) return "4";
		else if (id.equals("Virtual Tour")) return "5";
		else return "-1";
	}
	


}
