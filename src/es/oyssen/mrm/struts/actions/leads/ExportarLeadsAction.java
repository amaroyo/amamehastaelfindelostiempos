package es.oyssen.mrm.struts.actions.leads;

import java.io.FileInputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.CriterioLeadVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.leads.GridLeadsForm;
import es.oyssen.mrm.util.ExcelUtil;

public class ExportarLeadsAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CriterioLeadVO criterio = new CriterioLeadVO();
		GridLeadsForm f = (GridLeadsForm) form;
		criterio.setFiltrarEstadoWon(f.getFiltrarEstadoWon());
		criterio.setFiltrarEstadoLost(f.getFiltrarEstadoLost());
		criterio.setCreacionDesde(f.getCreacionDesde());
		criterio.setCreacionHasta(f.getCreacionHasta());
		criterio.setModificacionDesde(f.getModificacionDesde());
		criterio.setModificacionHasta(f.getModificacionHasta());
		String grupo = (String) request.getSession().getAttribute("usuarioIdGrupo");
		if (grupo.equals("2"))
			criterio.setCanal((String) request.getSession().getAttribute("usuarioIdCanal"));
		else if (grupo.equals("3")) 
			criterio.setDistribuidor((String) request.getSession().getAttribute("usuarioIdDistribuidor"));
		else if (grupo.equals("4")) 
			criterio.setComercial((String) request.getSession().getAttribute("usuarioIdComercial"));
		List<LeadVO> leads = getLeadsService().findByCriterio(criterio );
		HSSFWorkbook libro = ExcelUtil.createLeads(leads);
		
		java.util.Date fechaActual = new java.util.Date();
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Leads(" + fechaActual.toString() + ").xls");

		FileInputStream stream = null;
		ServletOutputStream sOutStream = response.getOutputStream();
		try 
		{ 			
			libro.write(sOutStream);		
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
