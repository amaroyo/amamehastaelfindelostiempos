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
		
		if (!StringUtil.isNullOrBlank(form.getUser())){
			
			usuario.setUser(form.getUser());
			return getUsuariosService().findByUser(usuario);
			
		} 
		else if (!StringUtil.isNullOrBlank(form.getIdUsuario())){
			
			usuario.setIdUsuario(form.getIdUsuario());
			return getUsuariosService().findById(usuario);
			
		}
		else {
			
			usuario.setEmail(form.getEmail());
			return getUsuariosService().findByEmail(usuario);
			
		}
	}

	@Override
	public void create(DhtmlxForm f) throws Exception {
		
		EditarUsuarioForm form = (EditarUsuarioForm) f;
		UsuarioVO usuario = new UsuarioVO();
		
		//ESTO HAY QUE CAMBIARLO A BUSCAR SEGUN EMAIL O DNI, YA QUE SON UNIQUE
		//EN ESTE CASO EL UNICO CAMPO UNIQUE A PARTE DEL ID ES EL USER (del form)
		usuario.setUser(form.getUser());
		if (getUsuariosService().findByUser(usuario) != null)
			System.out.println("================>ESTE USUARIO YA EXISTE<===========================");
		else{
			usuario.setEmail(form.getEmail());
			usuario.setIdGrupo(form.getIdGrupo());
			usuario.setIdAsociado(form.getIdAsociado());
			usuario.setNombre(form.getNombre());
			usuario.setTelefono(form.getTelefono());
			usuario.setTelefonoMovil(form.getTelefonoMovil());
			usuario.setDireccion(form.getDireccion());
			usuario.setCodigoPostal(form.getCodigoPostal());
			usuario.setCiudad(form.getCiudad());
			usuario.setPais(form.getPais());		
			usuario.setComentarios(form.getComentarios());
			usuario.setUser(form.getUser());
			
			if (!StringUtil.isNullOrBlank(form.getPass()))
				usuario.setPass(EncriptarUtil.getStringMessageDigest(form.getPass(), EncriptarUtil.MD5));
			else
				usuario.setPass(null);
			
	
			getUsuariosService().insert(usuario);
		}

	}
	
	@Override
	public void save(DhtmlxForm f) throws Exception {
		
		
		//REHACERLO, MEJOR HACEAR MODIFICACIONES SEGUN EL DNI
		//EN MIS ALUMNOS, SOLO APARECERA NOMBRE, APELLIDOS Y DNI
		
		EditarUsuarioForm form = (EditarUsuarioForm) f;
		UsuarioVO usuario = new UsuarioVO();
		
		usuario.setEmail(form.getEmail());
		
		if (StringUtil.isNullOrBlank(form.getIdUsuario()))
			usuario.setIdUsuario(getUsuariosService().findByEmail(usuario).getIdUsuario());
		else 
			usuario.setIdUsuario(form.getIdUsuario());
			
		usuario.setIdGrupo(form.getIdGrupo());
		usuario.setIdAsociado(form.getIdAsociado());
		usuario.setNombre(form.getNombre());
		usuario.setTelefono(form.getTelefono());
		usuario.setTelefonoMovil(form.getTelefonoMovil());
		usuario.setDireccion(form.getDireccion());
		usuario.setCodigoPostal(form.getCodigoPostal());
		usuario.setCiudad(form.getCiudad());
		usuario.setPais(form.getPais());		
		usuario.setComentarios(form.getComentarios());
		usuario.setUser(form.getUser());
		
		if (!StringUtil.isNullOrBlank(form.getPass()))
			usuario.setPass(EncriptarUtil.getStringMessageDigest(form.getPass(), EncriptarUtil.MD5));
		else
			usuario.setPass(null);
		
		getUsuariosService().update(usuario);
		
	}

	@Override
	public String parseXML(Object o) throws Exception {
		UsuarioVO c = (UsuarioVO) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		sb.append("<grupo><![CDATA[" + nombreGrupo(c.getIdGrupo()) + "]]></grupo>");
		sb.append("<nombre><![CDATA[" + c.getNombre() + "]]></nombre>");
		sb.append("<telefono><![CDATA[" + c.getTelefono() + "]]></telefono>");
		sb.append("<telefonoMovil><![CDATA[" + c.getTelefonoMovil() + "]]></telefonoMovil>");
		sb.append("<direccion><![CDATA[" + c.getDireccion() + "]]></direccion>");
		sb.append("<codigoPostal><![CDATA[" + c.getCodigoPostal() + "]]></codigoPostal>");
		sb.append("<ciudad><![CDATA[" + c.getCiudad() + "]]></ciudad>");
		sb.append("<pais><![CDATA[" + c.getPais() + "]]></pais>");
		sb.append("<email><![CDATA[" + c.getEmail() + "]]></email>");
		sb.append("<comentarios><![CDATA[" + c.getComentarios() + "]]></comentarios>");
		sb.append("<user><![CDATA[" + c.getUser() + "]]></user>");
		//sb.append("<pass></pass>");
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
	


}
