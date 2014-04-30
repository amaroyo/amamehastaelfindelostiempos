package es.oyssen.mrm.struts.actions.comerciales;

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
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.util.ExcelUtil;

public class ExportarComercialesAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CriterioComercialVO criterio = new CriterioComercialVO();
		String grupo = (String) request.getSession().getAttribute("usuarioIdGrupo");
		if (grupo.equals("2"))
			criterio.setIdCanal((String) request.getSession().getAttribute("usuarioIdCanal"));
		else if (grupo.equals("3")) 
			criterio.setIdDistribuidor((String) request.getSession().getAttribute("usuarioIdDistribuidor"));
		else if (grupo.equals("4")) 
			criterio.setIdComercial((String) request.getSession().getAttribute("usuarioIdComercial"));
		
		List<ComercialVO> comerciales = getComercialesService().findByCriterio(criterio );
		HSSFWorkbook libro = ExcelUtil.createComerciales(comerciales);
		
		java.util.Date fechaActual = new java.util.Date();
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Sales rep(" + fechaActual.toString() + ").xls");

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
