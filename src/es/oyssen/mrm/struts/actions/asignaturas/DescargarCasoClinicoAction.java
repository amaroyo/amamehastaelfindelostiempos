package es.oyssen.mrm.struts.actions.asignaturas;

import java.io.FileInputStream;
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
		
		
		//List<LeadVO> leads = getLeadsService().findByCriterio(criterio );
//		HSSFWorkbook libro = ExcelUtil.createLeads(leads);
		
		//java.util.Date fechaActual = new java.util.Date();
		
		response.setContentType("application/vnd.ms-excel");
//		response.setHeader("Content-Disposition", "attachment; filename=Leads(" + fechaActual.toString() + ").xls");

		FileInputStream stream = null;
		ServletOutputStream sOutStream = response.getOutputStream();
		try 
		{ 			
//			libro.write(sOutStream);		
			sOutStream.flush();
		}
		finally 
		{
			if (stream != null) 
			{
				stream.close(); 
			}
		}

		return mapping.findForward("success");
	}

}
