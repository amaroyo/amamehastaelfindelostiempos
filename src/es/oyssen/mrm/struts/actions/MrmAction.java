package es.oyssen.mrm.struts.actions;

import es.oyssen.mrm.negocio.services.AnexosService;
import es.oyssen.mrm.negocio.services.AsignaturasService;
import es.oyssen.mrm.negocio.services.CanalesService;
import es.oyssen.mrm.negocio.services.CasosClinicosService;
import es.oyssen.mrm.negocio.services.ComercialesService;
import es.oyssen.mrm.negocio.services.ContactosCanalService;
import es.oyssen.mrm.negocio.services.ContactosDistribuidorService;
import es.oyssen.mrm.negocio.services.CriteriosRubricasService;
import es.oyssen.mrm.negocio.services.DiariosReflexivosService;
import es.oyssen.mrm.negocio.services.DistribuidoresService;
import es.oyssen.mrm.negocio.services.EmpresasService;
import es.oyssen.mrm.negocio.services.EstanciasUnidadClinicaService;
import es.oyssen.mrm.negocio.services.FicherosLeadService;
import es.oyssen.mrm.negocio.services.FicherosService;
import es.oyssen.mrm.negocio.services.GruposService;
import es.oyssen.mrm.negocio.services.LeadsHistoryService;
import es.oyssen.mrm.negocio.services.LeadsService;
import es.oyssen.mrm.negocio.services.LogsUsuarioService;
import es.oyssen.mrm.negocio.services.PermisosGrupoService;
import es.oyssen.mrm.negocio.services.PermisosService;
import es.oyssen.mrm.negocio.services.PortafoliosService;
import es.oyssen.mrm.negocio.services.ProfesoresAsociadosService;
import es.oyssen.mrm.negocio.services.PuntosVentaService;
import es.oyssen.mrm.negocio.services.PuntuacionCriteriosService;
import es.oyssen.mrm.negocio.services.ResponsablesService;
import es.oyssen.mrm.negocio.services.SeminariosRealizadosService;
import es.oyssen.mrm.negocio.services.ServiciosService;
import es.oyssen.mrm.negocio.services.ServiciosUsuarioService;
import es.oyssen.mrm.negocio.services.TrabajosDeCampoService;
import es.oyssen.mrm.negocio.services.UsuariosService;
import es.oyssen.mrm.struts.Constantes;

public abstract class MrmAction extends SpringBaseAction implements Constantes{
	
	

	public ComercialesService getComercialesService() {
		return (ComercialesService) getBean("comercialesService");
	}
	

	public PuntosVentaService getPuntosVentaService() {
		return (PuntosVentaService) getBean("puntosVentaService");
	}

	
	public LeadsService getLeadsService() {
		return (LeadsService) getBean("leadsService");
	}

	public EmpresasService getEmpresasService() {
		return (EmpresasService) getBean("empresasService");
	}

	
	public ResponsablesService getResponsablesService() {
		return (ResponsablesService) getBean("responsablesService");
	}
	
	public DistribuidoresService getDistribuidoresService() {
		return (DistribuidoresService) getBean("distribuidoresService");
	}
	
	public ServiciosService getServiciosService() {
		return (ServiciosService) getBean("serviciosService");
	}
	
	public ContactosDistribuidorService getContactosDistribuidorService() {
		return (ContactosDistribuidorService) getBean("contactosDistribuidorService");
	}
	
	public FicherosService getFicherosService() {
		return (FicherosService) getBean("ficherosService");
	}
	
	public CanalesService getCanalesService() {
		return (CanalesService) getBean("canalesService");
	}

	public ContactosCanalService getContactosCanalService() {
		return (ContactosCanalService) getBean("contactosCanalService");
	}	
	
	public LeadsHistoryService getLeadsHistoryService() {
		return (LeadsHistoryService) getBean("leadsHistoryService");
	}	
	
	public GruposService getGruposService() {
		return (GruposService) getBean("gruposService");
	}
	
	public PermisosGrupoService getPermisosGrupoService() {
		return (PermisosGrupoService) getBean("permisosGrupoService");
	}

	public PermisosService getPermisosService() {
		return (PermisosService) getBean("permisosService");
	}
	
	public ServiciosUsuarioService getServiciosUsuarioService() {
		return (ServiciosUsuarioService) getBean("serviciosUsuarioService");
	}

	public LogsUsuarioService getLogsUsuarioService() {
		return (LogsUsuarioService) getBean("logsUsuarioService");
	}

	public FicherosLeadService getFicherosLeadService() {
		return (FicherosLeadService) getBean("ficherosLeadService");
	}
	
	public UsuariosService getUsuariosService() {
		return (UsuariosService) getBean("usuariosService");
	}
	
	public AsignaturasService getAsignaturasService() {
		return (AsignaturasService) getBean("asignaturasService");
	}
	
	public PortafoliosService getPortafoliosService() {
		return (PortafoliosService) getBean("portafoliosService");
	}
	
	public ProfesoresAsociadosService getProfesoresAsociadosService() {
		return (ProfesoresAsociadosService) getBean("profesoresAsociadosService");
	}
	
	public EstanciasUnidadClinicaService getEstanciasUnidadClinicaService() {
		return (EstanciasUnidadClinicaService) getBean("estanciasUnidadClinicaService");
	}
	
	public TrabajosDeCampoService getTrabajosDeCampoService() {
		return (TrabajosDeCampoService) getBean("trabajosDeCampoService");
	}
	
	public SeminariosRealizadosService getSeminariosRealizadosService() {
		return (SeminariosRealizadosService) getBean("seminariosRealizadosService");
	}
	
	public DiariosReflexivosService getDiariosReflexivosService() {
		return (DiariosReflexivosService) getBean("diariosReflexivosService");
	}
	
	public AnexosService getAnexosService() {
		return (AnexosService) getBean("anexosService");
	}
	
	public CasosClinicosService getCasosClinicosService() {
		return (CasosClinicosService) getBean("casosClinicosService");
	}
	
	public PuntuacionCriteriosService getPuntuacionCriteriosService() {
		return (PuntuacionCriteriosService) getBean("puntuacionCriteriosService");
	}
	
	public CriteriosRubricasService getCriteriosRubricasService() {
		return (CriteriosRubricasService) getBean("criteriosRubricasService");
	}
	
	
	
}
