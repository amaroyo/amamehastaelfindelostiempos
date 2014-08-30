package es.oyssen.mrm.struts.actions;

import es.oyssen.mrm.negocio.services.AnexosService;
import es.oyssen.mrm.negocio.services.AsignaturasService;
import es.oyssen.mrm.negocio.services.CasosClinicosService;
import es.oyssen.mrm.negocio.services.CriteriosRubricasService;
import es.oyssen.mrm.negocio.services.DiariosReflexivosService;
import es.oyssen.mrm.negocio.services.ErroresLogService;
import es.oyssen.mrm.negocio.services.EstanciasUnidadClinicaService;
import es.oyssen.mrm.negocio.services.GrupoPermisosService;
import es.oyssen.mrm.negocio.services.GruposCriteriosRubricasService;
import es.oyssen.mrm.negocio.services.GruposService;
import es.oyssen.mrm.negocio.services.PermisosService;
import es.oyssen.mrm.negocio.services.PortafoliosService;
import es.oyssen.mrm.negocio.services.ProfesoresAsociadosService;
import es.oyssen.mrm.negocio.services.PuntuacionCriteriosService;
import es.oyssen.mrm.negocio.services.RubricasService;
import es.oyssen.mrm.negocio.services.SeminariosAsignaturaService;
import es.oyssen.mrm.negocio.services.SeminariosRealizadosService;
import es.oyssen.mrm.negocio.services.TrabajosDeCampoInfoService;
import es.oyssen.mrm.negocio.services.TrabajosDeCampoService;
import es.oyssen.mrm.negocio.services.UsuariosPermisosService;
import es.oyssen.mrm.negocio.services.UsuariosService;
import es.oyssen.mrm.struts.Constantes;

public abstract class MrmAction extends SpringBaseAction implements Constantes{
	
	
	public GruposService getGruposService() {
		return (GruposService) getBean("gruposService");
	}
	
	public GrupoPermisosService getGrupoPermisosService() {
		return (GrupoPermisosService) getBean("grupoPermisosService");
	}
	
	public UsuariosPermisosService getUsuariosPermisosService() {
		return (UsuariosPermisosService) getBean("usuariosPermisosService");
	}
	
	public PermisosService getPermisosService() {
		return (PermisosService) getBean("permisosService");
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
	
	public GruposCriteriosRubricasService getGruposCriteriosRubricasService() {
		return (GruposCriteriosRubricasService) getBean("gruposCriteriosRubricasService");
	}
	
	public RubricasService getRubricasService() {
		return (RubricasService) getBean("rubricasService");
	}
	
	public SeminariosAsignaturaService getSeminariosAsignaturaService() {
		return (SeminariosAsignaturaService) getBean("seminariosAsignaturaService");
	}
	
	public TrabajosDeCampoInfoService getTrabajosDeCampoInfoService() {
		return (TrabajosDeCampoInfoService) getBean("trabajosDeCampoInfoService");
	}
	
	public ErroresLogService getErroresLogService() {
		return (ErroresLogService) getBean("erroresLogService");
	}
	
	
}
