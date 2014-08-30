package es.oyssen.mrm.struts.actions.administrar;

import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.struts.actions.MrmAction;

public class CopiaSeguridadAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		//deberiamos meter fecha
		String fileName = "CopiaSeguridadEnfermeria.sql";
		Process runtimeProcess;
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
			
			
			try {
				runtimeProcess = Runtime.getRuntime().exec(exec);
			} catch (Exception e) {
				return mapping.findForward("error");
			}
			 
		}
		else {
			
			try {
				runtimeProcess = Runtime.getRuntime().exec("mysqldump -u root enfermeria");
			} catch (Exception e) {
				return mapping.findForward("error");
			}
		}
		

			
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
				return mapping.findForward("error");
			}
	
		return mapping.findForward("success");


	}

}
