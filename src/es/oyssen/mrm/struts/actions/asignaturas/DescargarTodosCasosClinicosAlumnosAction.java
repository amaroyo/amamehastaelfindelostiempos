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

import es.oyssen.mrm.negocio.vo.AsignaturaVO;
import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.UsuarioPortafolioVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class DescargarTodosCasosClinicosAlumnosAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		PortafolioVO p = new PortafolioVO();
		String idAsignatura=(String)request.getParameter("idAsignatura");
		p.setIdAsignatura(idAsignatura);
		String anyoAcademico = (String) request.getSession().getAttribute("anyoAcademico");
		p.setAnyoAcademico(anyoAcademico);
		List<UsuarioPortafolioVO> listUsuarioPortafolio = getPortafoliosService().findUsuariosByAsignatura(p);
		
		//Informacion de la asignatura
		AsignaturaVO a = new AsignaturaVO();
		a.setIdAsignatura(idAsignatura);
		a = getAsignaturasService().findById(a);
		
		
		ByteArrayOutputStream baosFinal = new ByteArrayOutputStream();
		ZipOutputStream zosFinal = new ZipOutputStream(baosFinal);
		
		if(listUsuarioPortafolio != null){
			for (UsuarioPortafolioVO up : listUsuarioPortafolio) {
				CasoClinicoVO caso = new CasoClinicoVO();
				caso.setIdPortafolio(up.getIdPortafolio());
				//Lista con todos los casos clinicos de un portafolio
				List<CasoClinicoVO> casosPortafolio =  getCasosClinicosService().findAllByPortafolio(caso);
				
				//Crearemos Zip de Zips
				ZipEntry entry = new ZipEntry(up.getApellido1() + "_" + up.getApellido2() + "_" + up.getNombre()+"_"+up.getDni()+".zip");
				
				ByteArrayOutputStream subBaos = new ByteArrayOutputStream();
				ZipOutputStream subZos = new ZipOutputStream(subBaos);
				
				if(casosPortafolio != null){
					for (CasoClinicoVO c : casosPortafolio) {	
						if(c.getCasoClinico()!=null){
							String[] nmbreArchivo = c.getNombre().split("\\.");
							nmbreArchivo[0] = nmbreArchivo[0] + "_" + (c.getFechaSubida()).replaceAll(" ", "_");
							nmbreArchivo[0] = nmbreArchivo[0].replaceAll("\\s","");
							String[] s = nmbreArchivo[0].split("\\.");
							ZipEntry subEntry = new ZipEntry(s[0].replaceAll(":", "_")+"."+nmbreArchivo[1]);
							subEntry.setSize(c.getCasoClinico().length);
							subZos.putNextEntry(subEntry);
							subZos.write(c.getCasoClinico());
							subZos.closeEntry();
						}
					}
				}
				subZos.close();
				byte[] zippedFilePorAlumno = subBaos.toByteArray();
				
				entry.setSize(zippedFilePorAlumno.length);
				zosFinal.putNextEntry(entry);
				zosFinal.write(zippedFilePorAlumno);
				zosFinal.closeEntry();
			}
		}
		
		zosFinal.close();
		byte[] zippedTotalFiles = baosFinal.toByteArray();
		
		

		
	
	/*	
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
	*/
		
		try{
			//He preferido coger el codigo y no el nombre ya que viene con caracteres
			//raros desde la BBDD y no se genera la extension del archivo aunq el archivo esta bien
			String nmbreArchivo = "Casos_Clinicos_de_" + a.getCodigo() + "_Curso_" + anyoAcademico;
			
			//**********************************************
			response.setHeader("Content-Disposition", "attachment; filename="+ nmbreArchivo +".zip");
	
			ServletOutputStream outputStream = response.getOutputStream();
			response.setContentType("application/zip");
			response.setContentLength(zippedTotalFiles.length);
			outputStream.write(zippedTotalFiles); 
			outputStream.flush();
			outputStream.close();
		
		} catch (Exception e2) {
			System.out.println("Error in " + getClass().getName() + "\n" + e2);
		}
		
		

		return mapping.findForward("success");
	}

}
