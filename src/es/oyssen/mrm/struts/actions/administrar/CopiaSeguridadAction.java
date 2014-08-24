package es.oyssen.mrm.struts.actions.administrar;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.negocio.vo.DiarioReflexivoVO;
import es.oyssen.mrm.negocio.vo.ErrorLogVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.asignaturas.DescargarForm;

public class CopiaSeguridadAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		//deberiamos meter fecha
		String fileName = "CopiaSeguridadEnfermeria.sql";
	
		String[] exec = {};
		
		String so = System.getProperty("os.name");
		
		if (so.equals("Mac OS X")){			
			String expr = new StringBuilder()
		    .append("/usr/local/mysql/bin/mysqldump").append(' ')
		    .append("-u ").append("root").append(' ')
		    //.append("-p").append("").append(' ') NO PASSWORD
		    .append("--add-drop-database").append(' ')
		    .append("-B").append(' ')
		    .append("enfermeria").append(' ')
		    //.append(">").append(' ')
		    //.append("/Users/Aleks/Desktop/enfermeriadump.sql")
		    .toString();
			
			
			exec = new String[]{"/bin/bash", "-c", expr};
			
			
			 
		}
		else {
			
			String expr = new StringBuilder()
		    .append("mysqldump").append(' ')
		    .append("-u ").append("root").append(' ')
		    //.append("-p").append("").append(' ') NO PASSWORD
		    //.append("--add-drop-database").append(' ')
		    //.append("-B").append(' ')
		    .append("enfermeria")
		    .toString();
			
			
			
			
			
			exec = new String[]{expr};
		}
		
		
		Process runtimeProcess;
		try {

			runtimeProcess = Runtime.getRuntime().exec(exec);
	        
			
			try{
				
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

				ServletOutputStream outputStream = response.getOutputStream();
				response.setContentType("text/x-sql");

				InputStream i = runtimeProcess.getInputStream();
				
				 byte[] buffer = new byte[1024]; // Adjust if you want
				 int bytesRead;
				 while ((bytesRead = i.read(buffer)) != -1){
					 outputStream.write(buffer, 0, bytesRead);
				 }
				


				outputStream.flush();
				outputStream.close();
				

			} catch (Exception e2) {
				System.out.println("Error in " + getClass().getName() + "\n" + e2);
			}


			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return mapping.findForward("success");


	}

}
