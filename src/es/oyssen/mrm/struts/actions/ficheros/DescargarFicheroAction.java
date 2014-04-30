package es.oyssen.mrm.struts.actions.ficheros;

import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.FicheroVO;
import es.oyssen.mrm.struts.actions.MrmAction;

public class DescargarFicheroAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		FicheroVO fichero = new FicheroVO();
		fichero.setIdFichero(request.getParameter("idFichero"));
		getFicherosService().updateFechaUltimaDescarga(fichero);
		FicheroVO ficheroResultado = getFicherosService().findById(fichero);
		
		response.setContentType(fichero.getTipoContenido());
		response.setHeader("Content-Disposition", "attachment; filename="+ficheroResultado.getNombre());

		FileInputStream stream = null;
		ServletOutputStream sOutStream = response.getOutputStream();
		try 
		{ 			
			sOutStream.write(ficheroResultado.getDatos());			
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
