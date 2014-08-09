package es.oyssen.mrm.struts.actions.asignaturas;


import java.util.List;
import java.util.zip.ZipEntry;

import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.asignaturas.CrearTrabajoCampoForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;

public class CrearTrabajoCampoAction extends DHTMLXFormAction {

	
	
	
	@Override
	public Object load(DhtmlxForm f) throws Exception {
		/*
		EditarUsuarioForm form = (EditarUsuarioForm) f;
		UsuarioVO usuario = new UsuarioVO();
		
		if (!StringUtil.isNullOrBlank(form.getCorreo())){
			
			usuario.setCorreo(form.getCorreo());
			return getUsuariosService().findByCorreo(usuario);
			
		}
		
		else  if (!StringUtil.isNullOrBlank(form.getIdUsuario())){
			
			usuario.setIdUsuario(form.getIdUsuario());
			return getUsuariosService().findById(usuario);
			
		}
		else {//if (!StringUtil.isNullOrBlank(form.getDni()))
			
			usuario.setDni(form.getDni());
			return getUsuariosService().findByDni(usuario);
			
		}*/
	return null;
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
			//System.out.println(form.getFoto());
			//usuario.setFoto(form.getFoto());
			
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
		
		
		
		
		CrearTrabajoCampoForm form = (CrearTrabajoCampoForm) f;
		
		PortafolioVO p = new PortafolioVO();
		TrabajoDeCampoInfoVO t = new TrabajoDeCampoInfoVO();
		
		p.setAnyoAcademico(anyoAcademico);
		p.setIdAsignatura(form.getIdAsignatura());
		p.setIdProfesor(form.getIdProfesor());
		t.setNombre(form.getNombre());
		t.setDescripcion(form.getDescripcion());
		String fechaFinal = setFechaMySQL(form.getFechaFin()) + " " + form.getHora();
		
		String idTrabajoInfo = getTrabajosDeCampoInfoService().insert(t);
		
		//Vamos a crear Un trabajo de Campo para todos los alumnos de una asignatura
		//En un futuro habra que tener en cuenta al profesor y sus alumnos
		
		List<PortafolioVO> portafolios= getPortafoliosService().findByAsignatura(p);
		
		if(portafolios != null){
			for (PortafolioVO c : portafolios) {				
				TrabajoDeCampoVO tr = new TrabajoDeCampoVO();
				tr.setIdPortafolio(c.getIdPortafolio());
				tr.setIdTrabajoInfo(idTrabajoInfo);
				tr.setFechaLimite(fechaFinal);
				getTrabajosDeCampoService().insert(tr);

			}
		}
		
		return "trabajo de campo created";
		
	}

	@Override
	public String parseXML(Object o) throws Exception {
		UsuarioVO c = (UsuarioVO) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		sb.append("<grupo><![CDATA[" + nombreGrupo(c.getIdGrupo()) + "]]></grupo>");
		sb.append("<correo><![CDATA[" + c.getCorreo() + "]]></correo>");
		sb.append("<nombre><![CDATA[" + c.getNombre() + "]]></nombre>");
		sb.append("<apellido1><![CDATA[" + c.getApellido1() + "]]></apellido1>");
		sb.append("<apellido2><![CDATA[" + c.getApellido2() + "]]></apellido2>");
		sb.append("<dni><![CDATA[" + c.getDni() + "]]></dni>");
		sb.append("<telefono><![CDATA[" + c.getTelefono() + "]]></telefono>");
		sb.append("<fotoImagen><![CDATA[" + c.getFotoImagen() + "]]></fotoImagen>");
		//sb.append("<contrasenya></contrasenya>");
		sb.append("</data>");
		
		
		return sb.toString();
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
	
	private String setFechaMySQL(String fecha) {
		String[] sp = fecha.split("/");
		String out = sp[2] + "-" +  sp[1] + "-" + sp[0];
		return out;
		
	}


}
