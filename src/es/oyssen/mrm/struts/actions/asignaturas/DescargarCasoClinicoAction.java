package es.oyssen.mrm.struts.actions.asignaturas;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.negocio.vo.CriterioLeadVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.asignaturas.DescargarCasoClinicoForm;
import es.oyssen.mrm.struts.forms.leads.GridLeadsForm;
import es.oyssen.mrm.util.ExcelUtil;

public class DescargarCasoClinicoAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		CasoClinicoVO caso = new CasoClinicoVO();
		DescargarCasoClinicoForm f = (DescargarCasoClinicoForm) form;
		caso.setIdPortafolio(f.getIdPortafolio());
		caso.setIdCasoClinico(f.getIdCasoClinico());
	
		caso = getCasosClinicosService().findByIDs(caso);
		
		
		try{
		
		response.setHeader("Content-Disposition", "attachment; filename=CasoClinico(" + caso.getNombre() + ").pdf");

		ServletOutputStream outputStream = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setContentLength(caso.getCasoClinico().length);
		outputStream.write(caso.getCasoClinico()); 
		outputStream.flush();
		outputStream.close();
		
		} catch (Exception e2) {
			System.out.println("Error in " + getClass().getName() + "\n" + e2);
		}
		
		

		return mapping.findForward("success");
	}

}
