package es.oyssen.mrm.struts.actions.cursos;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.RubricaVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.cursos.EditarAsignaturaForm;

public class AnexoAsignaturaAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RubricaVO rubricaIn = new RubricaVO();
		RubricaVO rubricaOut = new RubricaVO();
	    
	    //if(!StringUtil.isNullOrBlank(f.geIdAsignatura())){
			rubricaIn.setIdAsignatura((String)request.getParameter("idAsignatura"));
	    	rubricaOut = getRubricasService().findById(rubricaIn);
	    			
			//application/json or application/xml text/html
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out;
		    out = response.getWriter();
			out.print(parseXML(rubricaOut));
			
			out.flush();
			out.close();
			return mapping.findForward("success");
			
		//}
	}
	
	public String parseXML(Object o) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append("<asignatura>");
		if (o != null){
			RubricaVO rubrica = (RubricaVO) o;
			sb.append("<id_asignatura><![CDATA[" + rubrica.getIdAsignatura() + "]]></id_asignatura>");
			sb.append("<anexo><![CDATA[" + rubrica.getAnexo() + "]]></anexo>");
		}
		sb.append("</asignatura>");
		return sb.toString();
	}

}
