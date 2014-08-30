package es.oyssen.mrm.struts.actions.asignaturas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;
import es.oyssen.mrm.struts.actions.MrmAction;

public class EliminarTrabajoCampoAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TrabajoDeCampoInfoVO i = new TrabajoDeCampoInfoVO();
		
		String idTrabajoInfo = (String)request.getParameter("idTrabajoInfo");
		
		i.setIdTrabajoInfo(idTrabajoInfo);
		
		/*
		List<TrabajoDeCampoVO> list = getTrabajosDeCampoService().findAllByIdInfo(i);
		
		if(list != null){
			for (TrabajoDeCampoVO n : list) {				
				getTrabajosDeCampoService().delete(n);

			}
		}
		*/
		getTrabajosDeCampoInfoService().delete(i);

		return mapping.findForward("success");
	}

}
