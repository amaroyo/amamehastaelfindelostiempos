package es.oyssen.mrm.struts.actions.asignaturas;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.PuntuacionCriterioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.asignaturas.NotasRubricaForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.util.StringUtil;


public class NotasRubricaAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		
		NotasRubricaForm form = (NotasRubricaForm) f;
		PuntuacionCriterioVO puntuacionCriterio = new PuntuacionCriterioVO();
		PortafolioVO portafolio = new PortafolioVO();
		
		if (idGrupoUsuario.equals("4")){
			portafolio.setAnyoAcademico(anyoAcademico);
			portafolio.setIdAlumno(idUsuario);
			portafolio.setIdAsignatura(form.getIdAsignatura());
			form.setIdPortafolio(getPortafoliosService().findByAlumnoAsignatura(portafolio).getIdPortafolio());
		}
		puntuacionCriterio.setIdPortafolio(form.getIdPortafolio());
		return getPuntuacionCriteriosService().findAllByPortafolio(puntuacionCriterio);
	}

	@Override
	public String create(DhtmlxForm f) throws Exception {
		
		return null;
	}
	
	@Override
	public String save(DhtmlxForm f) throws Exception {
		NotasRubricaForm form = (NotasRubricaForm) f;
		PuntuacionCriterioVO puntuacionCriterio = new PuntuacionCriterioVO();
		PortafolioVO portafolio = new PortafolioVO();
		
		if (idGrupoUsuario.equals("4")){
			portafolio.setAnyoAcademico(anyoAcademico);
			portafolio.setIdAlumno(idUsuario);
			portafolio.setIdAsignatura(form.getIdAsignatura());
			form.setIdPortafolio(getPortafoliosService().findByAlumnoAsignatura(portafolio).getIdPortafolio());
		}
		puntuacionCriterio.setIdPortafolio(form.getIdPortafolio());
		
		Iterator it = form.getValues().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			puntuacionCriterio.setIdCriterio(((String) e.getKey()).substring(10));
			puntuacionCriterio.setNota((String) e.getValue());
			getPuntuacionCriteriosService().insertOnDuplicateKeyUpdate(puntuacionCriterio);
		}
		return "notas changed";
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
			sb.append("<idCriterio"+idCriterio+"><![CDATA[" + nota + "]]></idCriterio"+idCriterio+">");
		}
		sb.append("</data>");
		return sb.toString();
	}

}
