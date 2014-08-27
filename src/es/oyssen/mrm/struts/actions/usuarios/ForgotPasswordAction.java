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
	
	public static void sendPasswordMessage(String correo, String nombre, String apellido, String new_pass, String type){
		
		final String from = "facultad.de.enfermeria.ucm@gmail.com";
		final String password = "proyecto1314";
		String host = "smtp.gmail.com";
		String subject = "";
		String body = "";
		
		if(type.equals("forgot")){
			subject="Solicitud de recuperaciÃ³n de contraseÃ±a";
			body = crearCuerpo(type,new_pass,nombre,correo);
		}
		else if(type.equals("new")){
			subject="Bienvenido";
			body = crearCuerpo(type,new_pass,nombre,correo);
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
			message.setContent(body,"text/html");
		
			Transport.send(message);
			
		}
		catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	
	private static String crearCuerpo(String type, String new_pass, String nombre, String correo) {
		
		String linea1 = "";
		if(type.equals("forgot")){
			linea1 = "Parece que has olvidado tu contraseña y has solicitado un restablecimiento de la misma."; 
		}
		else if(type.equals("new")){
			linea1 = "Gracias por registrarte en la herramienta para la gestión de prácticas y portafolio de la facultad."+"<br>"+
						"Este correo electrónico y su contenido está dirigido exclusivamente a su destinatario y puede contener información confidencial. " +
						"Si crees que lo has recibido por error, puedes simplemente ignorarlo.";
		}
		String message = "";
		
		message += "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>"+
	    "<html xmlns='http://www.w3.org/1999/xhtml'>"+
	    "<head>"+
	      "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
	      "<title>[SUBJECT]</title>"+
	      "<style type='text/css'>"+
	      "body {"+
	       "padding-top: 0 !important;"+
	       "padding-bottom: 0 !important;"+
	       "padding-top: 0 !important;"+
	       "padding-bottom: 0 !important;"+
	       "margin:0 !important;"+
	       "width: 100% !important;"+
	       "-webkit-text-size-adjust: 100% !important;"+
	       "-ms-text-size-adjust: 100% !important;"+
	       "-webkit-font-smoothing: antialiased !important;"+
	     "}"+
	     ".tableContent img {"+
	       "border: 0 !important;"+
	       "display: block !important;"+
	       "outline: none !important;"+
	     "}"+
	     "a{"+
	      "color:#382F2E;"+
	    "}"+

	    "p, h1{"+
	      "color:#382F2E;"+
	      "margin:0;"+
	    "}"+
	 "p{"+
	      "text-align:left;"+
	      "color:#999999;"+
	      "font-size:14px;"+
	      "font-weight:normal;"+
	      "line-height:19px;"+
	    "}"+

	    "a.link1{"+
	      "color:#382F2E;"+
	    "}"+
	    "a.link2{"+
	      "font-size:16px;"+
	      "text-decoration:none;"+
	      "color:#ffffff;"+
	    "}"+

	    "h2{"+
	      "text-align:left;"+
	       "color:#222222; "+
	       "font-size:19px;"+
	      "font-weight:normal;"+
	    "}"+
	    "div,p,ul,h1{"+
	      "margin:0;"+
	    "}"+


	    "</style>"+
	"<script type='colorScheme' class='swatch active'>"+
	"{"+
	    "'name':'Default',"+
	    "'bgBody':'ffffff',"+
	    "'link:'ffffff',"+
	    "'color':'999999',"+
	    "'bgItem':'ffffff',"+
	    "'title':'222222'"+
	"}"+
	"</script>"+
	  "</head>"+
	  "<body paddingwidth='0' paddingheight='0' bgcolor='#ffffff'  style='padding-top: 0; padding-bottom: 0; padding-top: 0; padding-bottom: 0; background-repeat: repeat; width: 100% !important; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-font-smoothing: antialiased;' offset='0' toppadding='0' leftpadding='0'>"+
	    "<table width='100%' border='0' cellspacing='0' cellpadding='0' class='tableContent' align='center' bgcolor='#ffffff' style='font-family:Helvetica, Arial,serif;'>"+

	      
	      "<tr><td height='35'></td></tr>"+

	      "<tr>"+
	        "<td>"+
	          "<table width='600' border='0' cellspacing='0' cellpadding='0' align='center'>"+
	            "<tr>"+
	              "<td width='40'></td>"+
	              "<td width='520'>"+
	                "<table width='520' border='0' cellspacing='0' cellpadding='0' align='center'>"+

	"<!-- =============================== Header ====================================== -->      "+     

	                  "<tr><td height='75'></td></tr>"+
	"<!-- =============================== Body ====================================== -->"+

	                  "<tr>"+
	                    "<td class='movableContentContainer' valign='top'>"+

	                    
"<div lass='movableContent'>"+
"<table width='520' border='0' cellspacing='0' cellpadding='0' align='left'>"+
  "<tr>"+
    "<td valign='top' align='left'>"+
      "<div class='contentEditableContainer contentImageEditable'>"+
        "<div class='contentEditable'>"+
          "<img src='images/line.png' width='251' height='43' alt='' data-default='placeholder' data-max-width='560'>"+
        "</div>"+
      "</div>"+
    "</td>"+
  "</tr>"+
"</table>"+
"</div>"+
	                      
      "<div lass='movableContent'>"+
        "<table width='520' border='0' cellspacing='0' cellpadding='0' align='center'>"+
          "<tr>"+
            "<td valign='top' align='center'>"+
              "<div class='contentEditableContainer contentTextEditable'>"+
                "<div class='contentEditable'>"+
                  "<p style='text-align:center;margin:0;font-family:Georgia,Time,sans-serif;font-size:26px;color:#222222;'><span style='color:#DC2828;'>Facultad de Enfermería, Fisioterapia y Podología de la UCM</span></p>"+
                "</div>"+
              "</div>"+
            "</td>"+
          "</tr>"+
        "</table>"+
      "</div>"+

      "<div class='movableContent'>"+
        "<table width='520' border='0' cellspacing='0' cellpadding='0' align='center'>"+
          "<tr><td height='55'></td></tr>"+
          "<tr>"+
            "<td align='left'>"+
              "<div class='contentEditableContainer contentTextEditable'>"+
                "<div class='contentEditable' align='center'>"+
                  "<h2 >Bienvenid@ "+nombre+",</h2>"+
                "</div>"+
              "</div>"+
            "</td>"+
          "</tr>"+

          "<tr><td height='15'> </td></tr>"+

          "<tr>"+
            "<td align='left'>"+
              "<div class='contentEditableContainer contentTextEditable'>"+
                "<div class='contentEditable' align='center'>"+
                  "<p  style='text-align:left;color:#999999;font-size:14px;font-weight:normal;line-height:19px;'>"+
                  	linea1+
                  	"<br>"+
                    "A continuación te facilitamos tu nueva contraseña. Recuerda que puedes cambiarla en cualquier momento en el el apartado <a class='link1' class='color:#382F2E;' href='#'>Mi Perfil</a> . "+
                    "<br>"+
                    "<br>"+
                    "Nueva contraseña: "+new_pass+
                    "<br>"+
                    "<br>"+
                    "<br>"+
                    "Atentamente,"+
                    "<br>"+
                    "<span style='color:#222222;'>Universidad Complutense de Madrid.</span>"+
                  "</p>"+
                "</div>"+
              "</div>"+
            "</td>"+
          "</tr>"+

          "<tr><td height='55'></td></tr>"+

          "<tr>"+
            "<td align='center'>"+
              "<table>"+
                "<tr>"+
                  "<td align='center' bgcolor='#DC2828' style='background:#DC2828; padding:15px 18px;-webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px;'>"+
                    "<div class='contentEditableContainer contentTextEditable'>"+
                      "<div class='contentEditable' align='center'>"+
                        "<a href='#' class='link2'>Ir ahora</a>"+
                      "</div>"+
                    "</div>"+
                  "</td>"+
                "</tr>"+
              "</table>"+
            "</td>"+
          "</tr>"+
          "<tr><td height='20'></td></tr>"+
        "</table>"+
      "</div>"+

      "<div lass='movableContent'>"+
        "<table width='520' border='0' cellspacing='0' cellpadding='0' align='center'>"+
          "<tr><td height='65'></td></tr>"+
          "<tr><td  style='border-bottom:1px solid #DDDDDD;'></td></tr>"+

          "<tr><td height='25'></td></tr>"+

          "<tr>"+
            "<td>"+
              "<table width='520' border='0' cellspacing='0' cellpadding='0' align='center'>"+
                "<tr>"+
                  "<td valign='top' align='left' width='370'>"+
                    "<div class='contentEditableContainer contentTextEditable'>"+
                      "<div class='contentEditable' align='center'>"+
                        "<p  style='text-align:left;color:#CCCCCC;font-size:12px;font-weight:normal;line-height:20px;'>"+
                          "<span style='font-weight:bold;'> </span>"+
                          "<br>"+
                          "Este correo electrónico ha sido generado automáticamente y este buzón no se supervisa, por lo que si contestas a este mensaje no recibirás ninguna respuesta. Si tienes cualquier duda o sugerencia no dudes en contactar con tu profesor o coordinador o directamente con la secretaría del centro. Estaremos encantados de atenderte."+
                          "<br>"+
                          "<a href='[UNSUBSCRIBE]' class='link1' class='color:#382F2E;'></a>"+
                        "</p>"+
                      "</div>"+
                    "</div>"+
                  "</td>"+
                "</tr>"+
              "</table>"+
            "</td>"+
          "</tr>"+
        "</table>"+
      "</div>"+

    "</td>"+
  "</tr>"+
	"<!-- =============================== footer ====================================== -->"+

	                "</table>"+
	              "</td>"+
	              "<td width='40'></td>"+
	            "</tr>"+
	          "</table>"+
	        "</td>"+
	      "</tr>"+

	      "<tr><td height='88'></td></tr>"+
	    "</table>"+

	    "<!--Default Zone-->"+
	    "<!--Default Zone End-->"+
      "</body>"+
      "</html>";
		return message;
		
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
