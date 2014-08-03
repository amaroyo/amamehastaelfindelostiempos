package es.oyssen.mrm.struts.actions.asignaturas;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mcavallo.opencloud.util.Pair;

import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class DescargarCasoClinicoAlumnoAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		
		CasoClinicoVO caso = new CasoClinicoVO();
		caso.setIdPortafolio((String)request.getParameter("idPortafolio"));
	
		List<CasoClinicoVO> casos =  getCasosClinicosService().findAllByPortafolio(caso);
		
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		
		if(casos != null){
			for (CasoClinicoVO c : casos) {				
				if(c.getCasoClinico()!=null){
					ZipEntry entry = new ZipEntry(c.getNombre()+".pdf");
					entry.setSize(c.getCasoClinico().length);
					zos.putNextEntry(entry);
					zos.write(c.getCasoClinico());
					zos.closeEntry();
				}

			}
		}
		
		zos.close();
		byte[] zippedFile = baos.toByteArray();
	
		
		try{
			
			
			//**********************************************
			response.setHeader("Content-Disposition", "attachment; filename=CasosClinicosAlumno.zip");
	
			ServletOutputStream outputStream = response.getOutputStream();
			response.setContentType("application/zip");
			response.setContentLength(zippedFile.length);
			outputStream.write(zippedFile); 
			outputStream.flush();
			outputStream.close();
		
		} catch (Exception e2) {
			System.out.println("Error in " + getClass().getName() + "\n" + e2);
		}
		
		

		return mapping.findForward("success");
	}

}
