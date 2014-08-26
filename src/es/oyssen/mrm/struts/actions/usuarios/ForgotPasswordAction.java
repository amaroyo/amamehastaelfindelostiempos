package es.oyssen.mrm.struts.actions.usuarios;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.negocio.vo.UsuarioYPermisos;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.usuarios.ForgotPasswordForm;
import es.oyssen.mrm.util.EncriptarUtil;

import java.security.SecureRandom;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class ForgotPasswordAction extends MrmAction {
	
	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ForgotPasswordForm f = (ForgotPasswordForm) form;		
		UsuarioYPermisos usuarioYPermisos = new UsuarioYPermisos();
			
		UsuarioVO usuario = new UsuarioVO();
		usuario.setCorreo(f.getEmail());
		usuario = getUsuariosService().findByCorreo(usuario);
		usuarioYPermisos.setUsuario(usuario);				
		
		if (usuario != null){
			String new_pass = generatePassword();
			usuario.setContrasenya(EncriptarUtil.getStringMessageDigest(new_pass, EncriptarUtil.MD5));
			getUsuariosService().update(usuario);
			sendPasswordMessage(usuario.getCorreo(),usuario.getNombre(),usuario.getApellido1(),new_pass,"forgot");
		}
		
		request.getSession().setAttribute("usuarioYPermisos", parseXML(usuarioYPermisos));
		return mapping.findForward("success");
	}
	
	public static void sendPasswordMessage(String correo, String nombre, String apellido,String new_pass,String type){
		
		final String from = "facultad.de.enfermeria.ucm@gmail.com";
		final String password = "proyecto1314";
		String host = "smtp.gmail.com";
		String subject = "";
		String body = "";
		
		if(type.equals("forgot")){
			subject="Recuperación de contraseña de la Facultad de Enfermería";
			body = crearCuerpo(type,new_pass,nombre,apellido,correo);
		}
		else if(type.equals("new")){
			subject="Bienvenido a la Facultad de Enfermería";
			body = crearCuerpo(type,new_pass,nombre,apellido,correo);
		}
		
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(properties,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(from,password);
					}
				}
		);

		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(correo));
			message.setSubject(subject);
			message.setContent(body,"text/html" );
		
			Transport.send(message);
			
		}
		catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	
	private static String crearCuerpo(String type, String new_pass, String nombre, String apellido,String correo) {
		
		String message = "";
		if(type.equals("forgot")){
			message = 	"Hola " + nombre + " " + apellido + ",\r\r";
			message += "Conforme a tu solicitud, tu contraseña se ha reestablecido.\n\r";
			message += "Entra en la Facultad de Enfermería ahora y disfruta de tus prácticas: http://www.facultad.enfermeria.es\n\r\n\r";
			message += "Tu nueva contraseña es la siguiente:  " + new_pass + "\n\r\n\r";
			message += "Gracias por seguir confiando en nosotros, \n\r";
			message += "                    - Universidad Complutense de Madrid. \n\r\n\r\n\r\n\r";
			message += "\r";
			message += "Comparte tus habilidades, comparte tu conocimiento\n\r\n\r";
			message += "Si crees que has recibido este correo por error, por favor, envíanos un email a: facultad.de.enfermeria.ucm@gmail.com \n\r";
			message += "Este correo ha sido enviado por un sistema automático de envíos y no permite respuesta. " +
					"Si deseas contactar con la Facultad de Enfermería o tienes alguna duda o sugerencia, puedes escribirnos a: facultad.de.enfermeria.ucm@gmail.com \r";
			return message;
		}
		
		else if(type.equals("new")){
			message =  "Hola " + nombre + " " + apellido + ",\r\r";
			message += "¡Bienvenido a la Facultad de Enfermería! \n\r";
			message += "Ya puedes comenzar a realizar prácticas, consultar tus horarios, y hacer muchas más cosas.\r";
			message += "Entra en la Facultad de Enfermería ahora y comienza a sacar partido a tus estudios: http://www.facultad.enfermeria.es\n\r\n\r";
			message += "Tu usuario es:                           "+ correo + "\r";
			message += "La contraseña de tu cuenta es:  " + new_pass + "\n\r\n\r";
			message += "Gracias por registrarte, \n\r";
			message += "                    - Universidad Complutense de Madrid. \n\r\n\r\n\r\n\r";
			message += "<LOGO>\r";
			message += "Comparte tus habilidades, comparte tu conocimiento\n\r\n\r";
			message += "Este correo electrónico y su contenido está dirigido exclusivamente a su destinatario y puede contener información confidencial. " +
					"Si crees que lo has recibido por error, por favor, envíanos un email a: facultad.de.enfermeria.ucm@gmail.com \n\r";
			message += "Este correo ha sido enviado por un sistema automático de envíos y no permite respuesta. Si deseas contactar con la Facultad de Enfermería o " +
					"tienes alguna duda o sugerencia, puedes escribirnos a: facultad.de.enfermeria.ucm@gmail.com \r";
			return message;
		}
		else return message;
		
		
		
	}

	public static String generatePassword() {
		int lenght = 10;
		char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		SecureRandom random = new SecureRandom();
		StringBuffer pass = new StringBuffer(lenght);
        for (int i = 0; i < lenght; i++)
        {
            pass.append(alphabet[random.nextInt(alphabet.length)]);
        }

        return pass.toString();
		
	}
	
	private static final String parseXML(Object o) throws Exception {
		UsuarioYPermisos c = (UsuarioYPermisos) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		sb.append("<existe_usuario_forgot_password>" + ((c.getUsuario() == null) ? "NO" : "YES") + "</existe_usuario_forgot_password>");
		sb.append("</data>");
		return sb.toString();
	}

}
