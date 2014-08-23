package es.oyssen.mrm.struts.actions.asignaturas;

import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.asignaturas.GridUsuariosEstanciasForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;


public class GridUsuariosEstanciasAction extends DHTMLXGridAction {
	
	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		GridUsuariosEstanciasForm form = (GridUsuariosEstanciasForm) f;
		
		
			PortafolioVO portafolio = new PortafolioVO();
			portafolio.setIdAsignatura(form.getIdAsignatura());
			portafolio.setAnyoAcademico(anyoAcademico);
			return UtilXML.buildXmlGridUsuariosEstanciasUnidadClinica(getPortafoliosService().findUsuariosEstanciaUnidadClinica(portafolio),idUsuario);

	}


	@Override
	public void insert(DhtmlxGridForm f) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(DhtmlxGridForm f) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(DhtmlxGridForm f) throws Exception {
		/*UsuarioVO usuario = new UsuarioVO();
		usuario.setIdUsuario(f.getGr_id());
		getUsuariosService().delete(usuario);*/
	}

}
