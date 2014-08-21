package es.oyssen.mrm.struts.actions.asignaturas;

import java.util.Iterator;
import java.util.List;

import es.oyssen.mrm.negocio.vo.PuntuacionCriterioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.asignaturas.NotasRubricaForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;

public class NotasRubricaAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		
		NotasRubricaForm form = (NotasRubricaForm) f;
		PuntuacionCriterioVO puntuacionCriterio = new PuntuacionCriterioVO();
		
		//if (!StringUtil.isNullOrBlank(form.getIdAsignatura())){
			puntuacionCriterio.setIdPortafolio(form.getIdPortafolio());
			return getPuntuacionCriteriosService().findAllByPortafolio(puntuacionCriterio);
			
		//} 
	}

	@Override
	public String create(DhtmlxForm f) throws Exception {
		
		return null;
	}
	
	@Override
	public String save(DhtmlxForm f) throws Exception {
		/*EditarAsignaturaForm form = (EditarAsignaturaForm) f;
		AsignaturaVO asignatura = new AsignaturaVO();
		asignatura.setNombre(form.getNombre());
		asignatura.setCodigo(form.getCodigo());
		asignatura.setCurso(form.getCurso());
		asignatura.setDescripcion(form.getDescripcion());
		
		if (!StringUtil.isNullOrBlank(form.getIdAsignatura())){
			asignatura.setIdAsignatura(form.getIdAsignatura());
			if (getAsignaturasService().findById(asignatura) != null) {
				getAsignaturasService().update(asignatura);
				return "asignatura changed";
			}
			else
				return "asignatura not changed: asignatura does not exist";
		}
		else {//if (!StringUtil.isNullOrBlank(form.getNombre()))
			asignatura.setNombre(form.getNombre());
			if (getAsignaturasService().findByNombre(asignatura) != null) {
				getAsignaturasService().update(asignatura);
				return "asignatura changed";
			}
			else
				return "asignatura not changed: asignatura does not exist";
		}*/
		return null;
	}
	
	@Override
	public String parseXML(Object o) throws Exception {
		List<PuntuacionCriterioVO> c = (List<PuntuacionCriterioVO>) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		Iterator it = c.iterator();
		while(it.hasNext()){
			PuntuacionCriterioVO puntuacionCriterio = (PuntuacionCriterioVO) it.next();
			String idCriterio = puntuacionCriterio.getIdCriterio();
			String nota = puntuacionCriterio.getNota();
			sb.append("<criterio>");
			sb.append("<idCriterio><![CDATA[" + idCriterio + "]]></idCriterio>");
			sb.append("<nota><![CDATA[" + nota + "]]></nota>");
			sb.append("</criterio>");
		}
		sb.append("</data>");
		return sb.toString();
	}

}
