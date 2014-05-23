package es.oyssen.mrm.struts.actions.usuarios;

import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.struts.forms.usuarios.EditarUsuarioForm;
import es.oyssen.mrm.util.EncriptarUtil;
import es.oyssen.mrm.util.StringUtil;

public class EditarUsuarioAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		
		EditarUsuarioForm form = (EditarUsuarioForm) f;
		UsuarioVO usuario = new UsuarioVO();
		
		if (!StringUtil.isNullOrBlank(form.getEmail())){
			
			usuario.setCorreo(form.getEmail());
			return getUsuariosService().findByCorreo(usuario);
			
		} 
		else if (!StringUtil.isNullOrBlank(form.getIdUsuario())){
			
			usuario.setIdUsuario(form.getIdUsuario());
			return getUsuariosService().findById(usuario);
			
		}
		else {//if (!StringUtil.isNullOrBlank(form.getDni()))
			
			usuario.setDni(form.getDni());
			return getUsuariosService().findByDni(usuario);
			
		}
	}

	@Override
	public void create(DhtmlxForm f) throws Exception {
		
		EditarUsuarioForm form = (EditarUsuarioForm) f;
		UsuarioVO usuario = new UsuarioVO();
		
		//ESTO HAY QUE CAMBIARLO A BUSCAR SEGUN EMAIL O DNI, YA QUE SON UNIQUE
		//EN ESTE CASO EL UNICO CAMPO UNIQUE A PARTE DEL ID ES EL USER (del form)
		usuario.setCorreo(form.getEmail());
		if (getUsuariosService().findByCorreo(usuario) != null)
			System.out.println("================>ESTE USUARIO YA EXISTE<===========================");
		else{
			usuario.setIdGrupo(form.getGrupo());
			usuario.setCorreo(form.getEmail());
			usuario.setNombre(form.getNombre());
			usuario.setApellido1(form.getApellido1());
			usuario.setApellido2(form.getApellido2());
			usuario.setDni(form.getDni());
			usuario.setTelefono(form.getTelefono());
			usuario.setFoto(form.getFotoUri());
			
			if (!StringUtil.isNullOrBlank(form.getContrasenya()))
				usuario.setContrasenya(EncriptarUtil.getStringMessageDigest(form.getContrasenya(), EncriptarUtil.MD5));
			else
				usuario.setContrasenya(null);
			
	
			getUsuariosService().insert(usuario);
		}

	}
	
	@Override
	public void save(DhtmlxForm f) throws Exception {
		
		
		//REHACERLO, MEJOR HACER MODIFICACIONES SEGUN EL DNI
		//EN MIS ALUMNOS, SOLO APARECERA NOMBRE, APELLIDOS Y DNI
		
		EditarUsuarioForm form = (EditarUsuarioForm) f;
		
		UsuarioVO usuario = new UsuarioVO();
		
		
		if (StringUtil.isNullOrBlank(form.getIdUsuario())) {
			if (!StringUtil.isNullOrBlank(form.getEmail())) {
				usuario.setCorreo(form.getEmail());
				usuario.setIdUsuario(getUsuariosService().findByCorreo(usuario).getIdUsuario());
			}
		}
		else  {
			usuario.setIdUsuario(form.getIdUsuario());
		}
		usuario.setIdGrupo(idGrupo(form.getGrupo()));
		usuario.setCorreo(form.getEmail());
		usuario.setNombre(form.getNombre());
		usuario.setApellido1(form.getApellido1());
		usuario.setApellido2(form.getApellido2());
		usuario.setDni(form.getDni());
		usuario.setTelefono(form.getTelefono());
		usuario.setFoto(form.getFotoUri());
		usuario.setContrasenya(null);
		
		getUsuariosService().update(usuario);
		
	}

	@Override
	public String parseXML(Object o) throws Exception {
		UsuarioVO c = (UsuarioVO) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		sb.append("<grupo><![CDATA[" + nombreGrupo(c.getIdGrupo()) + "]]></grupo>");
		sb.append("<email><![CDATA[" + c.getCorreo() + "]]></email>");
		sb.append("<nombre><![CDATA[" + c.getNombre() + "]]></nombre>");
		sb.append("<apellido1><![CDATA[" + c.getApellido1() + "]]></apellido1>");
		sb.append("<apellido2><![CDATA[" + c.getApellido2() + "]]></apellido2>");
		sb.append("<dni><![CDATA[" + c.getDni() + "]]></dni>");
		sb.append("<telefono><![CDATA[" + c.getTelefono() + "]]></telefono>");
		sb.append("<foto><![CDATA[" + c.getFoto() + "]]></foto>");
		//sb.append("<contrasenya></contrasenya>");
		sb.append("</data>");
		
		
		return sb.toString();
	}
	
	private String nombreGrupo(String id){
		if (id.equals("1")) return "Super user";
		else if (id.equals("2")) return "Channel";
		else if (id.equals("3")) return "Distributor";
		else if (id.equals("4")) return "Sales rep.";
		else if (id.equals("5")) return "Supplier";
		else return "";
	}
	
	private String idGrupo(String id){
		if (id.equals("Super user")) return "1";
		else if (id.equals("Channel")) return "2";
		else if (id.equals("Distributor")) return "3";
		else if (id.equals("Sales rep.")) return "4";
		else if (id.equals("Supplier")) return "5";
		else return "-1";
	}
	


}
