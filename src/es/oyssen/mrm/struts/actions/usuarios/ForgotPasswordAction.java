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
		usuario.setEmail(f.getEmail());
		usuario = getUsuariosService().findByEmail(usuario);
		usuarioYPermisos.setUsuario(usuario);				
		
		if (usuario != null){
			String new_pass = generatePassword();
			usuario.setPass(EncriptarUtil.getStringMessageDigest(new_pass, EncriptarUtil.MD5));
			getUsuariosService().update(usuario);
			sendForgotPasswordMessage(usuario.getEmail(),new_pass);
		}
		
		request.getSession().setAttribute("usuarioYPermisos", parseXML(usuarioYPermisos));
		return mapping.findForward("success");
	}
	
	private void sendForgotPasswordMessage(String to,String new_pass){
		
		String from = "medastucorreo@gmail.com";
		String host = "smtp.gmail.com";
		String subject = "Subject";
		String body = "<h6> HTML body </h6>" + new_pass;
		
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(properties,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("medastucorreo@gmail.com","contrasenya");
					}
				}
		);

		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject(subject);
			message.setContent(body,"text/html" );
		
			Transport.send(message);
		}
		catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	
	private String generatePassword() {
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
