package es.oyssen.mrm.struts.actions.empresas;

import java.io.FileInputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioEmpresaVO;
import es.oyssen.mrm.negocio.vo.EmpresaVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.util.ExcelUtil;

public class ExportarEmpresasAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CriterioEmpresaVO criterio = new CriterioEmpresaVO();
		
		List<EmpresaVO> empresas = getEmpresasService().findByCriterio(criterio );
		HSSFWorkbook libro = ExcelUtil.createEmpresas(empresas);
		
		java.util.Date fechaActual = new java.util.Date();
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Companies(" + fechaActual.toString() + ").xls");

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
