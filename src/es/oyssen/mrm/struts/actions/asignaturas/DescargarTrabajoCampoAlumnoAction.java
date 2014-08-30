package es.oyssen.mrm.struts.actions.asignaturas;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.struts.actions.MrmAction;


public class DescargarTrabajoCampoAlumnoAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		
		TrabajoDeCampoVO t = new TrabajoDeCampoVO();
		String idPortafolio = (String)request.getParameter("idPortafolio");
		t.setIdPortafolio(idPortafolio);
		
		PortafolioVO p = new PortafolioVO();
		p.setIdPortafolio(idPortafolio);
	
		//Lista con todos los casos clinicos de un portafolio
		List<TrabajoDeCampoVO> trabajos =  getTrabajosDeCampoService().findAllByPortafolio(t);
		
		//Informacion de un alumno
		UsuarioVO u = getPortafoliosService().findAlumnoByPortafolio(p);
		
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		
		if(trabajos != null){
			for (TrabajoDeCampoVO tr : trabajos) {				
				if(tr.getTrabajoDeCampo()!=null){
					String nmbreArchivo = tr.getNombreTrabajo();
					nmbreArchivo = nmbreArchivo.replaceAll("\\s","");
					nmbreArchivo = nmbreArchivo.replaceAll("/","_");
					nmbreArchivo = nmbreArchivo.replaceAll(":","_");
					ZipEntry entry = new ZipEntry(nmbreArchivo);
					entry.setSize(tr.getTrabajoDeCampo().length);
					zos.putNextEntry(entry);
					zos.write(tr.getTrabajoDeCampo());
					zos.closeEntry();
				}

			}
		}
		
		zos.close();
		byte[] zippedFile = baos.toByteArray();
	
		
		try{
			
			String nmbreArchivo = "Trabajos_Campo_de_" + u.getApellido1() + "_" + u.getApellido2()+"_" + u.getNombre()+"_"+u.getDni();
			
			//**********************************************
			response.setHeader("Content-Disposition", "attachment; filename="+ nmbreArchivo +".zip");
	
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
