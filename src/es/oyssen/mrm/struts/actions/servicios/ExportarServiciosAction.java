package es.oyssen.mrm.struts.actions.servicios;

import java.io.FileInputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.CriterioServicioVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.util.ExcelUtil;

public class ExportarServiciosAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CriterioServicioVO criterio = new CriterioServicioVO();
		String usuario = (String) request.getSession().getAttribute("idUsuario");
		String grupo = (String) request.getSession().getAttribute("usuarioIdGrupo");
		if (!grupo.equals("1"))
			criterio.setIdUsuario(usuario);
		
		List<ServicioVO> servicios = getServiciosService().findByCriterio(criterio );
		HSSFWorkbook libro = ExcelUtil.createServicios(servicios);
		
		java.util.Date fechaActual = new java.util.Date();
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Services(" + fechaActual.toString() + ").xls");

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
