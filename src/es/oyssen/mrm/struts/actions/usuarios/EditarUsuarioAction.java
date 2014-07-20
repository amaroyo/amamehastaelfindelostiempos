package es.oyssen.mrm.struts.actions.usuarios;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.struts.forms.usuarios.EditarUsuarioForm;
import es.oyssen.mrm.util.EncriptarUtil;
import es.oyssen.mrm.util.StringUtil;

public class EditarUsuarioAction extends DHTMLXFormAction {

	//in pixels
	private static final int WIDTH = 105;
	private static final int HEIGHT = 140;
	
	
	@Override
	public Object load(DhtmlxForm f) throws Exception {
		
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
			
		}
	}

	@Override
	public String create(DhtmlxForm f) throws Exception {
		
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

	}
	
	@Override
	public String save(DhtmlxForm f) throws Exception {
		
		
		//REHACERLO, MEJOR HACER MODIFICACIONES SEGUN EL DNI
		//EN MIS ALUMNOS, SOLO APARECERA NOMBRE, APELLIDOS Y DNI
		
		EditarUsuarioForm form = (EditarUsuarioForm) f;
		
		UsuarioVO usuario = new UsuarioVO();
		
		
		if (StringUtil.isNullOrBlank(form.getIdUsuario())) {
			if (!StringUtil.isNullOrBlank(form.getCorreo())) {
				usuario.setCorreo(form.getCorreo());
				usuario.setIdUsuario(getUsuariosService().findByCorreo(usuario).getIdUsuario());
			}
		}
		else  {
			usuario.setIdUsuario(form.getIdUsuario());
		}
		usuario.setIdGrupo(idGrupo(form.getGrupo()));
		usuario.setCorreo(form.getCorreo());
		usuario.setNombre(form.getNombre());
		usuario.setApellido1(form.getApellido1());
		usuario.setApellido2(form.getApellido2());
		usuario.setDni(form.getDni());
		usuario.setTelefono(form.getTelefono());
		
		byte[] fotoFile = form.getFotoFile().getFileData();
		if(form.getFotoFile().getFileSize() <= 0){
			fotoFile = null;
		}
		else {
			
			// convert byte array to BufferedImage
			InputStream in = new ByteArrayInputStream(fotoFile);
			BufferedImage originalImage = ImageIO.read(in);
			
			int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			
			// resizing
			BufferedImage resizedImage = new BufferedImage(WIDTH, HEIGHT, type);
			Graphics2D g = resizedImage.createGraphics();
			g.setComposite(AlphaComposite.Src);
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			g.drawImage(originalImage, 0, 0, WIDTH, HEIGHT, null);
			g.dispose();	
			
			// convert back BufferedImage to byte array
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(resizedImage, "jpg", baos);
			baos.flush();
			fotoFile = baos.toByteArray();
			baos.close();
		}
		
		usuario.setFotoFile(fotoFile);
		usuario.setContrasenya(null);
		getUsuariosService().update(usuario);
		return "usuario changed";
		
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
	


}
