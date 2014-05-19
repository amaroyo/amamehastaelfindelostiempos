package es.oyssen.mrm.util;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;

import es.oyssen.mrm.negocio.vo.ActionHistoryVO;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.ContactoCanalVO;
import es.oyssen.mrm.negocio.vo.ContactoDistribuidorVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.negocio.vo.EmpresaVO;
import es.oyssen.mrm.negocio.vo.FicheroVO;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.LeadHistoryVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.negocio.vo.LogUsuarioVO;
import es.oyssen.mrm.negocio.vo.PermisoGrupoVO;
import es.oyssen.mrm.negocio.vo.PermisoVO;
import es.oyssen.mrm.negocio.vo.ResponsableVO;
import es.oyssen.mrm.negocio.vo.ServicioUsuarioVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.struts.Constantes;


public class UtilXML {
	
	private static Log log = LogFactory.getLog(UtilXML.class);
	
	protected static MessageResources messages = MessageResources.getMessageResources(Constantes.APPLICATION_RESOURCES);	
	
	private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";


	


	public static final String buildXmlGridComerciales(List<ComercialVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (ComercialVO comercial : list) {
				sb.append("<row id=\"" +comercial.getIdComercial() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(comercial.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(comercial.getTelefono()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(comercial.getTelefonoMovil()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(comercial.getDireccion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(comercial.getCodigoPostal()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(comercial.getCiudad()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(comercial.getPais()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(comercial.getEmail()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(comercial.getComentarios()) + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static final String buildXmlGridResponsables(List<ResponsableVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (ResponsableVO responsable : list) {
				sb.append("<row id=\"" +responsable.getIdResponsable() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(responsable.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(responsable.getTelefono()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(responsable.getTelefonoMovil()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(responsable.getDireccion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(responsable.getCodigoPostal()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(responsable.getCiudad()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(responsable.getPais()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(responsable.getEmail()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(responsable.getComentarios()) + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}	
	
	
	public static final String buildXmlGridLeads(List<LeadVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (LeadVO lead : list) {
				sb.append("<row id=\"" +lead.getIdLead() + "\">");
				sb.append("<userdata name=\"idEmpresa\">" + lead.getEmpresa().getIdEmpresa() + "</userdata>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getCanal().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getEmpresa().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getComercial().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getDistribuidor().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getServicio().getNombre()) + "]]></cell>");				
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getEstado().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + DateUtil.dateToString(lead.getFechaCreacion(), "yyyy/MM/dd") + "]]></cell>");
				sb.append("<cell><![CDATA[" + DateUtil.dateToString(lead.getFechaVisita(), "yyyy/MM/dd") + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}	

	
	public static final String buildXmlGridActionsHistory(List<ActionHistoryVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (ActionHistoryVO action : list) {
				sb.append("<row id=\"" +action.getIdActionHistory() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(DateUtil.dateToString(action.getDate(), "yyyy/MM/dd")) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(action.getAction().getName()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(action.getComments()) + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}		
	
	public static final String buildXmlGridEmpresas(List<EmpresaVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (EmpresaVO empresa : list) {
				sb.append("<row id=\"" +empresa.getIdEmpresa() + "\">");								
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(empresa.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(empresa.getTelefono()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(empresa.getTelefonoMovil()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(empresa.getEmail()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(empresa.getInformacionContacto()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(empresa.getDireccion()) + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}	
	
	
	
	public static final String buildXmlGridLeadsComerciales(List<LeadVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (LeadVO lead : list) {
				sb.append("<row id=\"" +lead.getIdLead() + "\">");								
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getEmpresa().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getDistribuidor().getNombre()) + "]]></cell>");				
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getResponsable().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getServicio().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getEstado().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + DateUtil.dateToString(lead.getFechaVisita(), "yyyy/MM/dd") + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static final String buildXmlGridLeadsResponsables(List<LeadVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (LeadVO lead : list) {
				sb.append("<row id=\"" +lead.getIdLead() + "\">");								
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getEmpresa().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getDistribuidor().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getComercial().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getServicio().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getEstado().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + DateUtil.dateToString(lead.getFechaVisita(), "yyyy/MM/dd") + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static final String buildXmlGridLeadsDistribuidores(List<LeadVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (LeadVO lead : list) {
				sb.append("<row id=\"" +lead.getIdLead() + "\">");								
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getEmpresa().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getResponsable().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getComercial().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getServicio().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getEstado().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + DateUtil.dateToString(lead.getFechaVisita(), "yyyy/MM/dd") + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static final String buildXmlGridLeadsServicios(List<LeadVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (LeadVO lead : list) {
				sb.append("<row id=\"" +lead.getIdLead() + "\">");								
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getEmpresa().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getDistribuidor().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getDistribuidor().getNombre()) + "]]></cell>");				
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getComercial().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getEstado().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + DateUtil.dateToString(lead.getFechaVisita(), "yyyy/MM/dd") + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static final String buildXmlGridLeadsCanales(List<LeadVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (LeadVO lead : list) {
				sb.append("<row id=\"" +lead.getIdLead() + "\">");								
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getEmpresa().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getDistribuidor().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getComercial().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getServicio().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getEstado().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + DateUtil.dateToString(lead.getFechaVisita(), "yyyy/MM/dd") + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}

	public static final String buildXmlGridLeadsEmpresas(List<LeadVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (LeadVO lead : list) {
				sb.append("<row id=\"" +lead.getIdLead() + "\">");												
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getResponsable().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getDistribuidor().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getComercial().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getServicio().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(lead.getEstado().getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + DateUtil.dateToString(lead.getFechaVisita(), "yyyy/MM/dd") + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	
	public static final String buildXmlGridDistribuidores(List<DistribuidorVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (DistribuidorVO distribuidor : list) {
				sb.append("<row id=\"" +distribuidor.getIdDistribuidor() + "\">");												
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getTelefono()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getTelefonoMovil()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getDireccion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getCodigoPostal()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getCiudad()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getPais()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getEmail()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getDireccionFacturacion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getCodigoPostalFacturacion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getCiudadFacturacion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getPaisFacturacion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getNombreFacturacion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getTelefonoFacturacion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getTelefonoMovilFacturacion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getEmailFacturacion()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(distribuidor.getBloqueado()) + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}

	public static final String buildXmlGridServicios(List<ServicioVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (ServicioVO servicio : list) {
				sb.append("<row id=\"" +servicio.getIdServicio() + "\">");												
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(servicio.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(servicio.getPersonaContacto()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(servicio.getProveedor()) + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}	

	public static final String buildXmlGridContactoDistribuidor(List<ContactoDistribuidorVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (ContactoDistribuidorVO contacto : list) {
				sb.append("<row id=\"" +contacto.getIdContacto() + "\">");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getIdDistribuidor()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getTelefono()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getTelefonoMovil()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getDireccion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getCodigoPostal()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getCiudad()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getPais()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getEmail()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getCargo()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getComentarios()) + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}	

	public static final String buildXmlGridFichero(List<FicheroVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (FicheroVO fichero : list) {
				sb.append("<row id=\"" +fichero.getIdFichero() + "\">");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(fichero.getIdServicio()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(fichero.getNombre()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(fichero.getRuta()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(fichero.getDescripcion()) + "]]></cell>");				
				sb.append("<cell><![CDATA[" + DateUtil.dateToString(fichero.getFechaUltimaDescarga(), "yyyy/MM/dd") + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}	

	
	public static final String buildXmlGridCanales(List<CanalVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (CanalVO canal : list) {
				sb.append("<row id=\"" +canal.getIdCanal() + "\">");												
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getTelefono()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getTelefonoMovil()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getDireccion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getCodigoPostal()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getCiudad()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getPais()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getEmail()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getDireccionFacturacion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getCodigoPostalFacturacion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getCiudadFacturacion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getPaisFacturacion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getNombreFacturacion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getTelefonoFacturacion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getTelefonoMovilFacturacion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getEmailFacturacion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getDireccionWeb()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(canal.getMrmResponsable()) + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}	

	public static final String buildXmlGridContactoCanal(List<ContactoCanalVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (ContactoCanalVO contacto : list) {
				sb.append("<row id=\"" +contacto.getIdContacto() + "\">");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getIdDistribuidor()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getTelefono()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getTelefonoMovil()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getDireccion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getCodigoPostal()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getCiudad()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getPais()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getEmail()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getCargo()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(contacto.getComentarios()) + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}	

	
	
	public static final String buildXmlComboCanales(List<CanalVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<complete>");
		if(list != null){
			for (CanalVO canal : list) {
				sb.append("<option value=\"" + canal.getIdCanal() + "\" >" + "<![CDATA[" + StringUtil.nullToString(canal.getNombre()) + "]]>" + "</option>");
			}
		}
		sb.append("</complete>");
		return sb.toString();
	}
	
	public static final String buildXmlComboServicios(List<ServicioVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<complete>");
		if(list != null){
			for (ServicioVO servicio : list) {
				sb.append("<option value=\"" + servicio.getIdServicio() + "\" >" + "<![CDATA[" + StringUtil.nullToString(servicio.getNombre()) + "]]>" + "</option>");
			}
		}
		sb.append("</complete>");
		return sb.toString();
	}
	
	public static final String buildXmlComboComerciales(List<ComercialVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<complete>");
		if(list != null){
			for (ComercialVO comercial : list) {
				sb.append("<option value=\"" + comercial.getIdComercial() + "\" >" + "<![CDATA[" + StringUtil.nullToString(comercial.getNombre()) + "]]>" + "</option>");
			}
		}
		sb.append("</complete>");
		return sb.toString();
	}
	
	public static final String buildXmlComboDistribuidores(List<DistribuidorVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<complete>");
		if(list != null){
			for (DistribuidorVO distribuidor : list) {
				sb.append("<option value=\"" + distribuidor.getIdDistribuidor() + "\" >" + "<![CDATA[" + StringUtil.nullToString(distribuidor.getNombre()) + "]]>" + "</option>");
			}
		}
		sb.append("</complete>");
		return sb.toString();
	}	
	
	public static final String buildXmlComboEmpresas(List<EmpresaVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<complete>");
		if(list != null){
			for (EmpresaVO empresa : list) {
				//sb.append("<option value=\"" + empresa.getIdEmpresa() + "\" >" + "<![CDATA[" + StringUtil.nullToString(empresa.getNombre()) + "]]>" + "</option>");
				sb.append("<option value=\"" + empresa.getIdEmpresa() + "\" >" + "<![CDATA[" + StringUtil.nullToString(empresa.getOrgN()) + "]]>" + "</option>");
			}
		}
		sb.append("</complete>");
		return sb.toString();
	}
	
	public static final String buildXmlComboResponsables(List<ResponsableVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<complete>");
		if(list != null){
			for (ResponsableVO responsable : list) {
				sb.append("<option value=\"" + responsable.getIdResponsable() + "\" >" + "<![CDATA[" + StringUtil.nullToString(responsable.getNombre()) + "]]>" + "</option>");
			}
		}
		sb.append("</complete>");
		return sb.toString();
	}	
	
	public static final String buildXmlComboPermisos(List<PermisoVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<complete>");
		if(list != null){
			for (PermisoVO permiso : list) {
				sb.append("<option value=\"" + permiso.getIdPermiso() + "\" >" + "<![CDATA[" + StringUtil.nullToString(permiso.getNombre()) + "]]>" + "</option>");
			}
		}
		sb.append("</complete>");
		return sb.toString();
	}	
	
	public static final String buildXmlGridLeadsHistory(List<LeadHistoryVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);	
		sb.append("<rows>");
		if(list != null){
			for (LeadHistoryVO leadHistory : list) {
				sb.append("<row id=\"" +leadHistory.getIdHistory() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(leadHistory.getTipo()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + DateUtil.dateToString(leadHistory.getFecha(), "yyyy/MM/dd") + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(leadHistory.getCampo()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(leadHistory.getValorAnterior()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(leadHistory.getValorPosterior()) + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	
	public static final String buildXmlGridGrupos(List<GrupoVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);	
		sb.append("<rows>");
		if(list != null){
			for (GrupoVO grupo : list) {
				sb.append("<row id=\"" +grupo.getIdGrupo() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(grupo.getNombre()) + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static final String buildXmlGridPermisosGrupo(List<PermisoGrupoVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);	
		sb.append("<rows>");
		if(list != null){
			for (PermisoGrupoVO permisoGrupo : list) {
				sb.append("<row id=\"" +permisoGrupo.getIdPermisoGrupo() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(permisoGrupo.getNombre()) + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}

	public static final String buildXmlGridUsuarios(List<UsuarioVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (UsuarioVO usuario : list) {
				sb.append("<row id=\"" +usuario.getIdUsuario() + "\">");
				sb.append("<cell><![CDATA[" + nombreGrupo(StringUtil.nullToString(usuario.getIdGrupo())) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getTelefono()) + "]]></cell>");
				
				// CUIDADO REVISAR
				
				
				
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getTelefonoMovil()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getDireccion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getCodigoPostal()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getCiudad()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getPais()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getCorreo()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getComentarios()) + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static final String buildXmlGridUsuariosGrupo(List<UsuarioVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (UsuarioVO usuario : list) {
				sb.append("<row id=\"" +usuario.getIdUsuario() + "\">");
				//sb.append("<cell><![CDATA[" + nombreGrupo(StringUtil.nullToString(usuario.getIdGrupo())) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getTelefono()) + "]]></cell>");

				// CUIDADO REVISAR
				
				
				
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getTelefonoMovil()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getDireccion()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getCodigoPostal()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getCiudad()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getPais()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getCorreo()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getComentarios()) + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static final String buildXmlGridServiciosUsuario(List<ServicioUsuarioVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (ServicioUsuarioVO servicioUsuario : list) {
				sb.append("<row id=\"" +servicioUsuario.getIdServicioUsuario() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(servicioUsuario.getNombre()) + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static final String buildXmlGridLogsUsuario(List<LogUsuarioVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (LogUsuarioVO logUsuario : list) {
				sb.append("<row id=\"" +logUsuario.getIdLogUsuario() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(logUsuario.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(logUsuario.getUser()) + "]]></cell>");
				sb.append("<cell><![CDATA[" +  DateUtil.dateToString(logUsuario.getFecha(), "yyyy/MM/dd") + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	private static final String nombreGrupo(String id){
		if (id.equals("1")) return "Super user";
		else if (id.equals("2")) return "Channel";
		else if (id.equals("3")) return "Distributor";
		else if (id.equals("4")) return "Sales rep.";
		else if (id.equals("5")) return "Supplier";
		else return "";
	}
}

