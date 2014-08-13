package es.oyssen.mrm.struts.actions.asignaturas;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.AsignaturaVO;
import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.negocio.vo.UsuarioPortafolioVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class DescargarTodosTrabajosCampoAlumnosAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		
		PortafolioVO p = new PortafolioVO();
		String idTrabajoInfo=(String)request.getParameter("idTrabajoInfo");
		String idAsignatura=(String)request.getParameter("idAsignatura");
		p.setIdAsignatura(idAsignatura);
		String anyoAcademico = (String) request.getSession().getAttribute("anyoAcademico");
		p.setAnyoAcademico(anyoAcademico);
		List<UsuarioPortafolioVO> listUsuarioPortafolio = getPortafoliosService().findUsuariosByAsignatura(p);
		
		//Informacion de la asignatura
		AsignaturaVO a = new AsignaturaVO();
		a.setIdAsignatura(idAsignatura);
		a = getAsignaturasService().findById(a);
		//Información de la practica
		TrabajoDeCampoInfoVO info = new TrabajoDeCampoInfoVO();
		info.setIdTrabajoInfo(idTrabajoInfo);
		info = getTrabajosDeCampoInfoService().findById(info);
		
		
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		
		if(listUsuarioPortafolio != null){
			for (UsuarioPortafolioVO up : listUsuarioPortafolio) {
				TrabajoDeCampoVO trabajo = new TrabajoDeCampoVO();
				trabajo.setIdPortafolio(up.getIdPortafolio());
				trabajo.setIdTrabajoInfo(idTrabajoInfo);
				trabajo = getTrabajosDeCampoService().findByPortafolioInfo(trabajo);

				if(trabajo.getTrabajoDeCampo()!=null){
					
					String[] sp = trabajo.getNombreTrabajo().split("\\.");
					String nombre = up.getApellido1() + "_" + up.getApellido2() + "_" + up.getNombre()+"_"+up.getDni()+"."+sp[1];
					
					ZipEntry entry = new ZipEntry(nombre);
					entry.setSize(trabajo.getTrabajoDeCampo().length);
					zos.putNextEntry(entry);
					zos.write(trabajo.getTrabajoDeCampo());
					zos.closeEntry();
				}

			}
		}
		
		zos.close();
		byte[] zippedFile = baos.toByteArray();
	
		
		try{
			
			String nmbreArchivo = "TrabajosCampo_de_" + a.getCodigo() +"_" + info.getNombre() + "_Curso_" + anyoAcademico;
			nmbreArchivo = nmbreArchivo.replaceAll("\\s","");
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
