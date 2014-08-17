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
		
		
		return null;
	}

	@Override
	public String create(DhtmlxForm f) throws Exception {
		
		return null;
	}
	
	@Override
	public String save(DhtmlxForm f) throws Exception {
		EditarSeminarioForm form = (EditarSeminarioForm) f;
		SeminarioAsignaturaVO seminario = new SeminarioAsignaturaVO();
		seminario.setIdSeminario(form.getIdSeminario());
		seminario.setDescripcion(form.getDescripcion());
		getSeminariosAsignaturaService().update(seminario);
		return "";
		
	}
	
	@Override
	public String parseXML(Object o) throws Exception {
		SeminarioAsignaturaVO c = (SeminarioAsignaturaVO) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		sb.append("<nombre><![CDATA[" + c.getNombre() + "]]></nombre>");
		sb.append("<codigo><![CDATA[" + c.getCodigo() + "]]></codigo>");
		sb.append("<descripcion><![CDATA[" + c.getDescripcion() + "]]></descripcion>");
		sb.append("</data>");
		return sb.toString();
	}

}
