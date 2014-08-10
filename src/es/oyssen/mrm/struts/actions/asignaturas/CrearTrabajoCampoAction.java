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
		
		CrearTrabajoCampoForm form = (CrearTrabajoCampoForm) f;
		TrabajoDeCampoVO t = new TrabajoDeCampoVO();
		
		t.setIdPortafolio(form.getIdPortafolio());
		t.setIdTrabajoDeCampo(form.getIdTrabajoCampo());

		return getTrabajosDeCampoService().findByIDs(t);
			
		
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
		String fechaFinal = setFechaMySQL(form.getFechaFin()) + " " + form.getHora() + ":59";
		
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
		
		return idTrabajoInfo;
		
	}

	@Override
	public String parseXML(Object o) throws Exception {
		
		TrabajoDeCampoVO t = (TrabajoDeCampoVO) o;
		StringBuffer sb = new StringBuffer();
		
		String fl = parsearFechaLimite(t.getFechaLimite(),false);
		String[] sp = fl.split(" ");
		
		sb.append("<data>");
		sb.append("<nombre><![CDATA[" + t.getNombre() + "]]></nombre>");
		sb.append("<descripcion><![CDATA[" + t.getDescripcion() + "]]></descripcion>");
		sb.append("<fechaFin><![CDATA[" + sp[0] + "]]></fechaFin>");
		sb.append("<hora><![CDATA[" + sp[1]+ "]]></hora>");
		
		
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
	
	private static String parsearFechaLimite(String fechaLimite, boolean b) {
		String[] fl = fechaLimite.split(" ");
		String[] date = fl[0].split("-");
		String[] hora = fl[1].split("\\.");
		String out = "";
		if (b) out = "Día: " + date[2] + "/" +  date[1] + "/" + date[0] + " Hora: " + hora[0];
		else out = date[2] + "/" +  date[1] + "/" + date[0] + " " + hora[0];
		return out;
	}


}
