package es.oyssen.mrm.struts.actions.leads;

import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.negocio.vo.EmpresaVO;
import es.oyssen.mrm.negocio.vo.EstadoVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.negocio.vo.MarketingActivityVO;
import es.oyssen.mrm.negocio.vo.ResponsableVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.struts.forms.leads.LeadForm;
import es.oyssen.mrm.struts.forms.leads.NuevoLeadForm;
import es.oyssen.mrm.util.DateUtil;
import es.oyssen.mrm.util.StringUtil;

public class NuevoLeadAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		LeadForm form = (LeadForm) f;
		LeadVO lead = new LeadVO();
		lead.setIdLead(form.getIdLead());
		return getLeadsService().findById(lead);
	}


	public void save(DhtmlxForm f) throws Exception {
		NuevoLeadForm form = (NuevoLeadForm) f;
		LeadVO lead = new LeadVO();
//		lead.setIdLead(form.getIdLead());
		lead.setComentarios(form.getComentarios());
		EstadoVO estado = new EstadoVO();
		estado.setIdEstado(form.getEstado());
		lead.setEstado(estado);
		MarketingActivityVO marketingActivity = new MarketingActivityVO(form.getMarketingActivity(), null);
		lead.setMarketingActivity(marketingActivity);

		if ("undefined".equals(form.getComentarios())) {
			lead.setComentarios(form.getComentarios());
		}

		
		ComercialVO comercial = new ComercialVO(form.getComercial(), null);			
		lead.setComercial(comercial);
		
		DistribuidorVO distribuidor = new DistribuidorVO(form.getDistribuidor(), null);
		lead.setDistribuidor(distribuidor );
		
//		EmpresaVO empresa = new EmpresaVO();
//		empresa.setIdEmpresa(form.getEmpresa());			
//		lead.setEmpresa(empresa );
		EmpresaVO empresa = new EmpresaVO();
		empresa.setOrgN(form.getOrgnr());
		empresa = getEmpresasService().findByOrgn(empresa);
		lead.setEmpresa(empresa );
		
		lead.setEstado(estado);
		
		lead.setMarketingActivity(marketingActivity);
		
		ResponsableVO responsable = new ResponsableVO(form.getResponsable(), null);
		lead.setResponsable(responsable);
		
		ServicioVO servicio = new ServicioVO(form.getServicio(), null);
		lead.setServicio(servicio);			
		
		CanalVO canal = new CanalVO(form.getCanal(), null);
		lead.setCanal(canal);	
		
		lead.setUsuariosPotenciales(form.getUsuariosPotenciales());
		lead.setUsuarios(form.getUsuarios());
		
//		if (!StringUtil.isNullOrBlank(lead.getIdLead())) {
//			getLeadsService().update(lead);
//			if (!StringUtil.isNullOrBlank(lead.getCanal().getIdCanal())) {
//				getDistribuidoresService().updateCanal(lead.getIdLead());
//				getComercialesService().updateCanal(lead.getIdLead());
//			}
//		} else {
			getLeadsService().insert(lead);
//		}
	}	
	
	@Override
	public String parseXML(Object o) throws Exception {
		LeadVO l = (LeadVO) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		sb.append("<servicio><![CDATA[" + ((l.getServicio() != null) ? l.getServicio().getNombre() : "") + "]]></servicio>");
		sb.append("<comercial><![CDATA[" + ((l.getComercial() != null) ? l.getComercial().getNombre() : "") + "]]></comercial>");
		sb.append("<responsable><![CDATA[" + ((l.getResponsable() != null) ? l.getResponsable().getNombre() : "") + "]]></responsable>");
		sb.append("<distribuidor><![CDATA[" + ((l.getDistribuidor() != null) ? l.getDistribuidor().getNombre() : "") + "]]></distribuidor>");
		sb.append("<estado>" + ((l.getEstado()!=null) ? l.getEstado().getIdEstado() : "") + "</estado>");
		sb.append("<marketingActivity>" + ((l.getMarketingActivity() != null) ? StringUtil.nullToString(l.getMarketingActivity().getIdMarketingActivity()) : "") + "</marketingActivity>");
		sb.append("<comentarios><![CDATA[" + l.getComentarios() + "]]></comentarios>");
		sb.append("<fechaVisita>" + DateUtil.dateToString(l.getFechaVisita(), "dd/MM/yyyy") + "</fechaVisita>");
		sb.append("<orgnr>" + l.getEmpresa().getOrgN() + "</orgnr>");
		sb.append("<nombre><![CDATA[" + l.getEmpresa().getNombre() + "]]></nombre>");
		sb.append("<telefono><![CDATA[" + l.getEmpresa().getTelefono() + "]]></telefono>");
		sb.append("<telefonoMovil><![CDATA[" + l.getEmpresa().getTelefonoMovil() + "]]></telefonoMovil>");
		sb.append("<email><![CDATA[" + l.getEmpresa().getEmail() + "]]></email>");
		sb.append("<informacionContacto><![CDATA[" + l.getEmpresa().getInformacionContacto() + "]]></informacionContacto>");
		sb.append("<canal><![CDATA[" + ((l.getCanal() != null) ? l.getCanal().getNombre() : "") + "]]></canal>");
		sb.append("<empresa><![CDATA[" + ((l.getEmpresa() != null) ? l.getEmpresa().getNombre() : "") + "]]></empresa>");
		sb.append("<usuariosPotenciales><![CDATA[" + l.getUsuariosPotenciales()  + "]]></usuariosPotenciales>");
		sb.append("<usuarios><![CDATA[" + l.getUsuarios() + "]]></usuarios>");
		sb.append("</data>");
		
		return sb.toString();
	}

}
