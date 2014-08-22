package es.oyssen.mrm.struts.actions.asignaturas;

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

public class DescargarAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DescargarForm f = (DescargarForm) form;
		String tipo = f.getTipoConsulta();
		
		String[] sp = {"",""};
		int len=0;
		Object data = null;
		String fileName = "";
		
		if (tipo.equals("CasoClinico")){
			
			CasoClinicoVO caso = new CasoClinicoVO();
			
			caso.setIdPortafolio(f.getIdPortafolio());
			caso.setIdCasoClinico(f.getIdCasoClinico());
		
			caso = getCasosClinicosService().findByIDs(caso);
			data = caso.getCasoClinico();
			if(data!=null){
				len = caso.getCasoClinico().length;
				sp=caso.getNombre().split("\\.");
				fileName="CasoClinico("+sp[0] + ")." + sp[1];
			}
			
		}
		else if (tipo.equals("DiarioReflexivo")){
			
			DiarioReflexivoVO d = new DiarioReflexivoVO();
			
			d.setIdPortafolio(f.getIdPortafolio());
			d.setIdDiarioReflexivo(f.getIdDiarioReflexivo());
		
			d = getDiariosReflexivosService().findByIDs(d);
			data = d.getDiarioReflexivo();
			if(data!=null){
				len = d.getDiarioReflexivo().length;
				sp = d.getNombre().split("\\.");
				fileName="DiarioReflexivo("+sp[0] + ")." + sp[1];
			}
			
		}
		else if (tipo.equals("TrabajoCampoAlumno")){
			
			TrabajoDeCampoVO t = new TrabajoDeCampoVO();
			t.setIdPortafolio(f.getIdPortafolio());
			t.setIdTrabajoDeCampo(f.getIdTrabajoCampo());
			
			t = getTrabajosDeCampoService().findByIDsTC(t);
			data = t.getTrabajoDeCampo();
			if(data!=null){
				len = t.getTrabajoDeCampo().length;
				sp = t.getNombreTrabajo().split("\\.");
				fileName="TrabajoDeCampo("+sp[0] + ")." + sp[1];
			}
		}
		else if (tipo.equals("TrabajoCampoCorreccion")){
			
			TrabajoDeCampoVO t = new TrabajoDeCampoVO();
			t.setIdPortafolio(f.getIdPortafolio());
			t.setIdTrabajoDeCampo(f.getIdTrabajoCampo());
			
			t = getTrabajosDeCampoService().findByIDsTC(t);
			data = t.getCorreccionTrabajo();
			if(data!=null){
				len = t.getCorreccionTrabajo().length;
				sp = t.getNombreCorreccion().split("\\.");
				fileName="CorreccionTrabajoDeCampo("+sp[0] + ")." + sp[1];
			}
		}
		else if (tipo.equals("TrabajoCampoInformacion")){
			
			TrabajoDeCampoVO t = new TrabajoDeCampoVO();
			t.setIdPortafolio(f.getIdPortafolio());
			t.setIdTrabajoDeCampo(f.getIdTrabajoCampo());
			
			t = getTrabajosDeCampoService().findByIDsTC(t);
			data = t.getEnunciado();
			if(data!=null){
				len = t.getEnunciado().length;
				sp = t.getNombreArchivo().split("\\.");
				fileName="InformacionTrabajoDeCampo("+sp[0] + ")." + sp[1];
			}
		}
		else if (tipo.equals("RegistroError")){
			
			ErrorLogVO e = new ErrorLogVO();
			e.setIdError(f.getIdError());
			e = getErroresLogService().findById(e);
			
			data = e.getDescripcion().getBytes();
			if(data!=null){
				len = e.getDescripcion().getBytes().length;
				sp[0] = e.getTipo();
				sp[1] = "txt";
				fileName= sp[0] + "." + sp[1];
			}
		}
		
		
		
		if(data!=null){ ///SE RALLA EL JSP!!!! CUANDO HAY UN CASO CLINICO IGUAL A NULL!!!!!! :@
			try{
				
				
				
				
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		
				ServletOutputStream outputStream = response.getOutputStream();
				if (sp[1].equals("pdf")) response.setContentType("application/pdf");
				else if ((sp[1]).equals("doc")) response.setContentType("application/msword");
				else if ((sp[1]).equals("docx")) response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
				else response.setContentType("text/plain");
				
				response.setContentLength(len);
				outputStream.write((byte[])data); 
				
				
				outputStream.flush();
				outputStream.close();
				
			
			} catch (Exception e2) {
				System.out.println("Error in " + getClass().getName() + "\n" + e2);
			}
		
		}

		return mapping.findForward("success");
	}

}
