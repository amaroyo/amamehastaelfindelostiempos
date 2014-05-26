package es.oyssen.mrm.struts.actions.cursos;

import es.oyssen.mrm.negocio.vo.AsignaturaVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.cursos.EditarAsignaturaForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.util.StringUtil;

public class EditarAsignaturaAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		
		EditarAsignaturaForm form = (EditarAsignaturaForm) f;
		AsignaturaVO asignatura = new AsignaturaVO();
		
		if (!StringUtil.isNullOrBlank(form.getIdAsignatura())){
			asignatura.setIdAsignatura(form.getIdAsignatura());
			return getAsignaturasService().findById(asignatura);
			
		} 
		else //if (!StringUtil.isNullOrBlank(form.getNombre())){
			asignatura.setNombre(form.getNombre());
			return getAsignaturasService().findByNombre(asignatura);
			
		//}
	}

	@Override
	public void create(DhtmlxForm f) throws Exception {
		EditarAsignaturaForm form = (EditarAsignaturaForm) f;
		AsignaturaVO asignatura = new AsignaturaVO();
		asignatura.setNombre(form.getNombre());
		if (getAsignaturasService().findByNombre(asignatura) != null)
			System.out.println("=======================>ESTA ASIGNATURA YA EXISTE<=======================");
		else{
			asignatura.setNombre(form.getNombre());
			asignatura.setCodigo(form.getCodigo());
			asignatura.setCurso(form.getCurso());
			asignatura.setDescripcion(form.getDescripcion());
			getAsignaturasService().insert(asignatura);
		}
	}
	
	@Override
	public void save(DhtmlxForm f) throws Exception {
		EditarAsignaturaForm form = (EditarAsignaturaForm) f;
		AsignaturaVO asignatura = new AsignaturaVO();
		asignatura.setNombre(form.getNombre());
		asignatura.setCodigo(form.getCodigo());
		asignatura.setCurso(form.getCurso());
		asignatura.setDescripcion(form.getDescripcion());
		
		if (!StringUtil.isNullOrBlank(form.getIdAsignatura())){
			asignatura.setIdAsignatura(form.getIdAsignatura());
			getAsignaturasService().update(asignatura);
		}
	}

	@Override
	public String parseXML(Object o) throws Exception {
		AsignaturaVO c = (AsignaturaVO) o;
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
