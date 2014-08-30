package es.oyssen.mrm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;

import es.oyssen.mrm.negocio.vo.ActionHistoryVO;
import es.oyssen.mrm.negocio.vo.AnexoVO;
import es.oyssen.mrm.negocio.vo.AsignaturaVO;
import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.negocio.vo.DiarioReflexivoVO;
import es.oyssen.mrm.negocio.vo.ErrorLogVO;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.PermisoVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaAnyoVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaCodigoVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.negocio.vo.UsuarioAnyoSeminarioVO;
import es.oyssen.mrm.negocio.vo.UsuarioAsignaturaVO;
import es.oyssen.mrm.negocio.vo.UsuarioEstanciaUnidadClinicaVO;
import es.oyssen.mrm.negocio.vo.UsuarioPortafolioVO;
import es.oyssen.mrm.negocio.vo.UsuarioTrabajoCampoVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.struts.Constantes;
import es.oyssen.mrm.struts.actions.administrar.GridAlumnosAptosCertificadoAction.Pares;


public class UtilXML {
	
	private static Log log = LogFactory.getLog(UtilXML.class);
	
	protected static MessageResources messages = MessageResources.getMessageResources(Constantes.APPLICATION_RESOURCES);	
	
	private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";


	
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
	
	public static final String buildXmlGridPermisosGrupo(List<PermisoVO> list, GrupoVO g) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);	
		sb.append("<rows>");
		if(list != null){
			for (PermisoVO permisoGrupo : list) {
				sb.append("<row id=\"" +permisoGrupo.getIdPermiso() + "-" + g.getIdGrupo()+ "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(permisoGrupo.getNombre()) + "]]></cell>");
				sb.append("</row>");
			}
		}
		sb.append("</rows>");
		return sb.toString();

	}
	
	public static String buildXmlGridPermisosUsuario(List<PermisoVO> list, UsuarioVO u) {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);	
		sb.append("<rows>");
		if(list != null){
			for (PermisoVO permisoUsuario : list) {
				sb.append("<row id=\"" +permisoUsuario.getIdPermiso()+ "-" + u.getIdUsuario()+ "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(permisoUsuario.getNombre()) + "]]></cell>");
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
				if(!usuario.getIdUsuario().equals("1")){
					sb.append("<row id=\"" +usuario.getIdUsuario() + "\">");
					sb.append("<cell><![CDATA[" + nombreGrupo(StringUtil.nullToString(usuario.getIdGrupo())) + "]]></cell>");
					sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getNombre()) + "]]></cell>");
					String apellidos = usuario.getApellido1();
					if(usuario.getApellido2() != "") apellidos = apellidos + ", " + usuario.getApellido2();
					sb.append("<cell><![CDATA[" + StringUtil.nullToString(apellidos) + "]]></cell>");
					sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getTelefono()) + "]]></cell>");	
					sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getCorreo()) + "]]></cell>");
					sb.append("</row>");	
				}	
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	
	public static final String buildXmlGridAsignaturas(List<AsignaturaVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (AsignaturaVO asignatura : list) {
				sb.append("<row id=\"" +asignatura.getIdAsignatura() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(asignatura.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(asignatura.getCodigo()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(asignatura.getCurso()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(asignatura.getDescripcion()) + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static final String buildXmlGridUsuariosEstanciasUnidadClinica(List<UsuarioEstanciaUnidadClinicaVO> list, String idSesion) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (UsuarioEstanciaUnidadClinicaVO ueuc : list) {
				sb.append("<row id=\"" +ueuc.getIdUsuario() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(ueuc.getNombre()) + "]]></cell>");
				String apellidos = ueuc.getApellido1();
				if(ueuc.getApellido2() != "") apellidos = apellidos + ", " + ueuc.getApellido2();
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(apellidos) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(ueuc.getDni()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(ueuc.getCentroAsociado()) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(ueuc.getUnidadClinica()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(ueuc.getTurno()) + "]]></cell>"); 
				if(ueuc.getIdProfesor().equals(idSesion)) sb.append("<cell><![CDATA[<img src=" + "../img/grid/corregida.png"+ "></img>]]></cell>");
				else sb.append("<cell><![CDATA["+""+"]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static final String buildXmlGridUsuariosSeminariosAsignatura(List<UsuarioAnyoSeminarioVO> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (UsuarioAnyoSeminarioVO usr : list) {
				sb.append("<row id=\"" +usr.getIdUsuario() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(usr.getNombre()) + "]]></cell>");
				String apellidos = usr.getApellido1();
				if(usr.getApellido2() != "") apellidos = apellidos + ", " + usr.getApellido2();
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(apellidos) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(usr.getDni()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(usr.getTelefono()) + "]]></cell>");				
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(usr.getCorreo()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(usr.getAnyo_academico()) + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static String buildXmlGridUsuariosPortafolioAsignatura(List<UsuarioPortafolioVO> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (UsuarioPortafolioVO usr : list) {
				sb.append("<row id=\"" +usr.getIdPortafolio() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(usr.getNombre()) + "]]></cell>");
				String apellidos = usr.getApellido1();
				if(usr.getApellido2() != "") apellidos = apellidos + ", " + usr.getApellido2();
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(apellidos) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(usr.getDni()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(usr.getTelefono()) + "]]></cell>");				
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(usr.getCorreo()) + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	
	public static String buildXmlGridUsuariosTrabajosCampoAsignatura(List<UsuarioTrabajoCampoVO> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (UsuarioTrabajoCampoVO utc : list) {
				
				
				
				boolean subido = (utc.getTrabajoDeCampoFile() != null);
				String s="F";
				if(subido) s="T";
				
				boolean corregido =(utc.getCorreccionTrabajoFile() != null);
				String c = "F";
				if(corregido) c="T";
				
				sb.append("<row id=\"" +s+"-"+c+"-"+utc.getIdPortafolio() + "-" + utc.getIdTrabajoDeCampo() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(utc.getNombre()) + "]]></cell>");
				String apellidos = utc.getApellido1();
				if(utc.getApellido2() != "") apellidos = apellidos + ", " + utc.getApellido2();
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(apellidos) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(utc.getDni()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(parsearFechaLimite(utc.getFechaLimite(),true)) + "]]></cell>");				
				
				
				
				if(utc.getTrabajoDeCampoFile() != null) sb.append("<cell><![CDATA[<img src=" + "../img/grid/corregida.png"+ "></img>]]></cell>");
				else sb.append("<cell><![CDATA[<img src=" + "../img/grid/nocorregida.png"+ "></img>]]></cell>");
						
			
				if(utc.getCorreccionTrabajoFile() != null) sb.append("<cell><![CDATA[<img src=" + "../img/grid/corregida.png"+ "></img>]]></cell>");
				else sb.append("<cell><![CDATA[<img src=" + "../img/grid/nocorregida.png"+ "></img>]]></cell>");
				
				
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
				if(!usuario.getIdUsuario().equals("1")){
					sb.append("<row id=\"" +usuario.getIdUsuario() + "\">");			
					sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getNombre()) + "]]></cell>");
					
					String apellidos = usuario.getApellido1();
					if(usuario.getApellido2() != "") apellidos = apellidos + ", " + usuario.getApellido2();	
					
					sb.append("<cell><![CDATA[" + StringUtil.nullToString(apellidos) + "]]></cell>");
					sb.append("<cell><![CDATA[" + StringUtil.nullToString(usuario.getCorreo()) + "]]></cell>");
					sb.append("</row>");
				}
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	
	public static String buildXmlGridCasosClinicosAsignaturaUsuario(List<CasoClinicoVO> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (CasoClinicoVO cc : list) {
				sb.append("<row id=\"" +cc.getIdPortafolio() + "-" + cc.getIdCasoClinico() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(cc.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(parsearFechaLimite(cc.getFechaSubida(),false)) + "]]></cell>");
				String descarga = "";
				if(cc.getCasoClinico() != null) descarga="Descargar";
				sb.append("<cell><![CDATA[" +  descarga + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static String buildXmlGridDiariosReflexivosAsignaturaUsuario(List<DiarioReflexivoVO> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (DiarioReflexivoVO dr : list) {
				sb.append("<row id=\"" +dr.getIdPortafolio() + "-" + dr.getIdDiarioReflexivo() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(dr.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(parsearFechaLimite(dr.getFechaSubida(),false)) + "]]></cell>");
				String descarga = "";
				if(dr.getDiarioReflexivo() != null) descarga="Descargar";
				sb.append("<cell><![CDATA[" +  descarga + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static String buildXmlGridSeminariosRealizadosUsuario(List<SeminarioAsignaturaAnyoVO> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (SeminarioAsignaturaAnyoVO saa : list) {
				sb.append("<row id=\"" +saa.getIdSeminario() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(saa.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(saa.getCodigo()) + "]]></cell>");
				sb.append("<cell><![CDATA[" +  StringUtil.nullToString(saa.getAnyoAcademico()) + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static String buildXmlGridSeminariosPendientesUsuario(List<SeminarioAsignaturaVO> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (SeminarioAsignaturaVO sa : list) {
				sb.append("<row id=\"" +sa.getIdSeminario() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(sa.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(sa.getCodigo()) + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static String buildXmlGridTrabajosCampoUsuarioAsignatura(List<TrabajoDeCampoVO> list, boolean profesor) {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (TrabajoDeCampoVO tc : list) {
				
				boolean corregido = (tc.getCorreccionTrabajo() != null);
				String c="F";
				if(corregido) c="T";
				String fechaLimite = parsearFechaLimite(tc.getFechaLimite(),false);
				boolean bloqueado = chequearDeadLine(fechaLimite);
				String b = "T";
				if(!bloqueado) b="F";
				boolean info = (tc.getEnunciado() != null);
				String i = "F";
				if(info) i = tc.getIdTrabajoInfo();
				sb.append("<row id=\""+ c + "-" + b + "-" + i + "-" +tc.getIdPortafolio() + "-" + tc.getIdTrabajoDeCampo() + "-" + tc.getIdTrabajoInfo() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(tc.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(parsearFechaLimite(tc.getFechaLimite(),true)) + "]]></cell>");
				
				
				if(corregido){
					sb.append("<cell><![CDATA[<img src=" + "../img/grid/corregida.png"+ "></img>]]></cell>");
					
				}
				else {
					 sb.append("<cell><![CDATA[<img src=" + "../img/grid/nocorregida.png"+ "></img>]]></cell>");
					
				}
				
				
				if(tc.getTrabajoDeCampo() != null) {
					if(!profesor) sb.append("<cell><![CDATA[" +  "Descargar" + "]]></cell>");
					else sb.append("<cell><![CDATA[<img src=" + "../img/grid/corregida.png"+ "></img>]]></cell>");
				}
				else {
					if(!profesor) sb.append("<cell><![CDATA[" +  "" + "]]></cell>");
					else sb.append("<cell><![CDATA[<img src=" + "../img/grid/nocorregida.png"+ "></img>]]></cell>");
				}
				
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}

	
	
	public static String buildXmlGridCasosClinicosUsuarioAsignatura(List<CasoClinicoVO> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (CasoClinicoVO cc : list) {
				sb.append("<row id=\"" +cc.getIdPortafolio() + "-" + cc.getIdCasoClinico() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(cc.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(parsearFechaLimite(cc.getFechaSubida(),true)) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(cc.getCasoClinico()) + "]]></cell>");
				String descarga = "";
				if(cc.getCasoClinico() != null) descarga="Descargar";
				sb.append("<cell><![CDATA[" +  descarga + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}

	
	public static String buildXmlGridAnexos2UsuarioAsignatura(List<AnexoVO> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (AnexoVO a : list) {
				sb.append("<row id=\"" +a.getIdPortafolio() + "-" + a.getIdAnexo() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(a.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(parsearFechaLimite(a.getFechaSubida(),true)) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(cc.getCasoClinico()) + "]]></cell>");
				String descarga = "";
				if(a.getAnexo() != null) descarga="Descargar";
				sb.append("<cell><![CDATA[" +  descarga + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}

	
	public static String buildXmlGridDiariosReflexivosUsuarioAsignatura(List<DiarioReflexivoVO> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (DiarioReflexivoVO dr : list) {
				sb.append("<row id=\"" +dr.getIdPortafolio() + "-" + dr.getIdDiarioReflexivo() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(dr.getNombre()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(parsearFechaLimite(dr.getFechaSubida(),true)) + "]]></cell>");
				//sb.append("<cell><![CDATA[" + StringUtil.nullToString(dr.getDiarioReflexivo()) + "]]></cell>");
				String descarga = "";
				if(dr.getDiarioReflexivo() != null) descarga="Descargar";
				sb.append("<cell><![CDATA[" +  descarga + "]]></cell>");
				sb.append("<cell><![CDATA[" +  "Descargar" + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static String buildXmlGridUsuariosProfesores(List<UsuarioAsignaturaVO> list, String idUsuario) {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (UsuarioAsignaturaVO ua : list) {
				sb.append("<row id=\"" +ua.getIdUsuario() + "-" + ua.getIdAsignatura() + "-" + ua.getNombreAsignatura() + "-" + ua.getIdPortafolio() + "\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(ua.getNombre()) + "]]></cell>");
				String apellidos = ua.getApellido1();
				if(ua.getApellido2() != "") apellidos = apellidos + ", " + ua.getApellido2();			
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(apellidos) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(ua.getDni()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(ua.getCodigo()) + "]]></cell>");
				String sel = "0";
				if(ua.getIdProfesor().equals(idUsuario)) sel="1";
				sb.append("<cell><![CDATA[" + sel + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
		
	}
	
	public static String buildXmlGridSeminarios(List<SeminarioAsignaturaCodigoVO> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (SeminarioAsignaturaCodigoVO sa : list) {
				sb.append("<row id=\"" +sa.getIdSeminario() +"\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(sa.getNombre()) + "]]></cell>");
						
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(sa.getCodigo()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(sa.getCurso()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(sa.getCodigoAsignatura()) + "]]></cell>");
				
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static String buildXmlRegistroErrores(List<ErrorLogVO> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (ErrorLogVO e : list) {
				sb.append("<row id=\"" +e.getIdError() +"\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(e.getTipo()) + "]]></cell>");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(parsearFechaLimite(e.getFecha(),false)) + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	public static String buildXmlAlumnosCertificados(List<Pares> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(XML_HEADER);
		sb.append("<rows>");
		if(list != null){
			for (Pares par : list) {
				String port = par.getPortafolios().toString();
				port = port.replaceAll(" ", "-");
				port=port.replaceAll(",","");
				port=port.replaceAll("\\[","");
				port=port.replaceAll("\\]","");
				UsuarioVO u = par.getUsuario();
				sb.append("<row id=\"" +port +"\">");
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(u.getNombre()) + "]]></cell>");				
				String apellidos = u.getApellido1();
				if(u.getApellido2() != "") apellidos = apellidos + ", " + u.getApellido2();			
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(apellidos) + "]]></cell>");
				
				sb.append("<cell><![CDATA[" + StringUtil.nullToString(u.getDni()) + "]]></cell>");
				sb.append("</row>");				
			}
		}
		sb.append("</rows>");
		return sb.toString();
	}
	
	
	private static final String nombreGrupo(String id){
		if (id.equals("1")) return "Admin";
		else if (id.equals("2")) return "Coordinador";
		else if (id.equals("3")) return "Profesor";
		else if (id.equals("4")) return "Alumno";
		else if (id.equals("5")) return "Virtual Tour";
		else if (id.equals("6")) return "Indefinido";

		else return "";
	}

	private static String parsearFechaLimite(String fechaLimite, boolean b) {
		String[] fl = fechaLimite.split(" ");
		String[] date = fl[0].split("-");
		String[] hora = fl[1].split("\\.");
		String out = "";
		if (b) out = "Día: " + date[2] + "/" +  date[1] + "/" + date[0] + " Hora: " + hora[0];
		else out = date[2] + "/" +  date[1] + "/" + date[0] + " " + hora[0];
		return out;
	}

	
	private static boolean chequearDeadLine(String fechaLimite) {

		Date tiempoActual = new Date();
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
		String dateInString = fechaLimite;
		
		Date fechaL = null;
		try {
			fechaL = sdf.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		 		
		return tiempoActual.after(fechaL);

	}

	

	

	

	

	


	
	
	

	

	

	

	

	
}

