package es.oyssen.mrm.struts.actions.canales;

import java.io.FileInputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.CriterioCanalVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.util.ExcelUtil;

public class ExportarCanalesAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CriterioCanalVO criterio = new CriterioCanalVO();
		String grupo = (String) request.getSession().getAttribute("usuarioIdGrupo");
		if (!grupo.equals("1")) 
			criterio.setIdCanal((String) request.getSession().getAttribute("usuarioIdCanal"));
		
		List<CanalVO> canales = getCanalesService().findByCriterio(criterio );
		HSSFWorkbook libro = ExcelUtil.createCanales(canales);
		
		java.util.Date fechaActual = new java.util.Date();
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Channels(" + fechaActual.toString() + ").xls");

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
