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


		String executeCmd = "mysqldump -u " + "root" + " -p" + "" + " --add-drop-database -B " + "enfermeria" + " -r " + "/Users/Aleks/Desktop/enfermeriadump.sql";
		Process runtimeProcess;
		try {

			runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();

			if (processComplete == 0) {

				

				try{
					
					response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

					ServletOutputStream outputStream = response.getOutputStream();
					response.setContentType("text/x-sql");

					
					response.setContentLength(executeCmd.getBytes().length);
					outputStream.write(executeCmd.getBytes()); 


					outputStream.flush();
					outputStream.close();


				} catch (Exception e2) {
					System.out.println("Error in " + getClass().getName() + "\n" + e2);
				}


			} else {
				System.out.println("Could not create the backup");
				return mapping.findForward("error");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return mapping.findForward("success");


	}

}
