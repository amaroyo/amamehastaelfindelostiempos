package es.oyssen.mrm.struts.actions.cursos;

import es.oyssen.mrm.negocio.vo.AsignaturaVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.cursos.EditarAsignaturaForm;
import es.oyssen.mrm.struts.forms.cursos.EditarSeminarioForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.util.StringUtil;

public class EditarSeminarioAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		
		EditarSeminarioForm form = (EditarSeminarioForm) f;
		SeminarioAsignaturaVO seminario = new SeminarioAsignaturaVO();
		
		if (!StringUtil.isNullOrBlank(form.getIdSeminario())){
			seminario.setIdSeminario(form.getIdSeminario());
			return getSeminariosAsignaturaService().findById(seminario);
			
		} 
		
		/*else //if (!StringUtil.isNullOrBlank(form.getNombre())){
			asignatura.setNombre(form.getNombre());
			return getAsignaturasService().findByNombre(asignatura);
			
		//}
		 * 
		 */
		return null;
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
		SeminarioAsignaturaVO c = (SeminarioAsignaturaVO) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		sb.append("<nombre><![CDATA[" + c.getNombre() + "]]></nombre>");
		sb.append("<codigo><![CDATA[" + c.getCodigo() + "]]></codigo>");
		sb.append("<curso><![CDATA[" + c.getCurso() + "]]></curso>");
		sb.append("<descripcion><![CDATA[" + c.getDescripcion() + "]]></descripcion>");
		sb.append("</data>");
		return sb.toString();
	}

}
