package es.oyssen.mrm.negocio.services.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import es.oyssen.mrm.negocio.dao.DAOCanales;
import es.oyssen.mrm.negocio.dao.DAOComerciales;
import es.oyssen.mrm.negocio.dao.DAODistribuidores;
import es.oyssen.mrm.negocio.dao.DAOEmpresas;
import es.oyssen.mrm.negocio.dao.DAOEstado;
import es.oyssen.mrm.negocio.dao.DAOLeadActionsHistory;
import es.oyssen.mrm.negocio.dao.DAOLeads;
import es.oyssen.mrm.negocio.dao.DAOLeadsHistory;
import es.oyssen.mrm.negocio.dao.DAOMarketingActivities;
import es.oyssen.mrm.negocio.dao.DAOResponsables;
import es.oyssen.mrm.negocio.dao.DAOResponsablesComerciales;
import es.oyssen.mrm.negocio.dao.DAOServicios;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.LeadsService;
import es.oyssen.mrm.negocio.vo.ActionHistoryVO;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioDistribuidorVO;
import es.oyssen.mrm.negocio.vo.CriterioEmpresaVO;
import es.oyssen.mrm.negocio.vo.CriterioLeadVO;
import es.oyssen.mrm.negocio.vo.CriterioResponsableVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.negocio.vo.EmpresaVO;
import es.oyssen.mrm.negocio.vo.EstadoVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.negocio.vo.MarketingActivityVO;
import es.oyssen.mrm.negocio.vo.ResponsableVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.util.ExcelUtil;

public class LeadsServiceImpl implements LeadsService {
	
	private static Log log = LogFactory.getLog(LeadsServiceImpl.class);
	
	private DAOLeads daoLeads;
	private DAOComerciales daoComerciales;
	private DAOResponsables daoResponsables;
	private DAOResponsablesComerciales daoResponsablesComerciales;
	private DAOEmpresas daoEmpresas;
	private DAOEstado daoEstado;
	private DAOServicios daoServicios;
	private DAODistribuidores daoDistribuidores;
	private DAOMarketingActivities daoMarketingActivities;
	private DAOLeadActionsHistory daoLeadActionsHistory;
	private DAOCanales daoCanales;
	private DAOLeadsHistory daoLeadsHistory;

	
	public void setDaoLeadsHistory(
			DAOLeadsHistory daoLeadsHistory) {
		this.daoLeadsHistory = daoLeadsHistory;
	}
	
	public void setDaoLeadActionsHistory(
			DAOLeadActionsHistory daoLeadActionsHistory) {
		this.daoLeadActionsHistory = daoLeadActionsHistory;
	}
	
	public void setDaoMarketingActivities(
			DAOMarketingActivities daoMarketingActivities) {
		this.daoMarketingActivities = daoMarketingActivities;
	}
	
	public void setDaoDistribuidores(DAODistribuidores daoDistribuidores) {
		this.daoDistribuidores = daoDistribuidores;
	}
	
	public void setDaoServicios(DAOServicios daoServicios) {
		this.daoServicios = daoServicios;
	}
	
	public void setDaoEstado(DAOEstado daoEstado) {
		this.daoEstado = daoEstado;
	}
	
	public void setDaoEmpresas(DAOEmpresas daoEmpresas) {
		this.daoEmpresas = daoEmpresas;
	}
	
	public void setDaoResponsablesComerciales(
			DAOResponsablesComerciales daoResponsablesComerciales) {
		this.daoResponsablesComerciales = daoResponsablesComerciales;
	}
	
	public void setDaoResponsables(DAOResponsables daoResponsables) {
		this.daoResponsables = daoResponsables;
	}
	
	public void setDaoComerciales(DAOComerciales daoComerciales) {
		this.daoComerciales = daoComerciales;
	}
	
	public void setDaoLeads(DAOLeads daoLeads) {
		this.daoLeads = daoLeads;
	}
	
	public void setDaoCanales(DAOCanales daoCanales) {
		this.daoCanales = daoCanales;
	}
	
	
	public void process(InputStream fis, String idCanal) throws ServiceException {
		try {
			log.debug("Procesamos fichero de carga.........");
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheetAt(0);
	
			log.debug("Procesando archivo excel: " + workbook.getSheetName(0));
			
			Iterator rows = sheet.rowIterator();
			if (rows.hasNext()) {
				HSSFRow row = (HSSFRow) rows.next();
				while (rows.hasNext()) {			
					row = (HSSFRow) rows.next();
					LeadVO lead = ExcelUtil.parseLead(row);
					CanalVO canal = new CanalVO();
					canal.setIdCanal(idCanal);
					lead.setCanal(canal);
					insertByName(lead);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error procesando fichero", e);
			throw new ServiceException(e);
		}
		
	}

	public List<LeadVO> findByCriterio(CriterioLeadVO criterio)
			throws ServiceException {
		try {
			return daoLeads.findByCriterio(criterio);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public LeadVO findById(LeadVO lead) throws ServiceException {
		try {
			LeadVO leadVO = daoLeads.findById(lead);
			leadVO.setComercial(daoComerciales.findById(leadVO.getComercial()));
			leadVO.setEmpresa(daoEmpresas.findById(leadVO.getEmpresa()));
			leadVO.setResponsable(daoResponsables.findById(leadVO.getResponsable()));			
			leadVO.setCanal(daoCanales.findById(leadVO.getCanal()));
			leadVO.setEmpresa(daoEmpresas.findById(leadVO.getEmpresa()));
//			leadVO.setEstado(daoEstado.findById(leadVO.getEstado()));
//			leadVO.setServicio(daoServicios.findById(leadVO.getServicio()));
			return leadVO;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	public void update(LeadVO lead) throws ServiceException {
		try {
			LeadVO leadAnterior = daoLeads.findById(lead);
			daoLeads.update(lead);
			LeadVO leadActual = daoLeads.findById(lead);
			daoLeadsHistory.insertByLead(leadActual, leadAnterior);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}
	public void addAction(LeadVO lead, ActionHistoryVO action) throws ServiceException {
		try {
			daoLeadActionsHistory.insert(lead, action);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	public List<ActionHistoryVO> findActionsHistory(LeadVO lead)
			throws ServiceException {
		try {
			return daoLeadActionsHistory.findByCriterio(lead);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	public void deleteActionHistory(ActionHistoryVO action)
			throws ServiceException {
		try {
			daoLeadActionsHistory.delete(action);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public void insertByName(LeadVO lead) throws ServiceException {
		try {
			lead.setFechaCreacion(new Date());
			if (lead.getDistribuidor() != null) {
				CriterioDistribuidorVO criterio = new CriterioDistribuidorVO();
				criterio.setNombre(lead.getDistribuidor().getNombre());
				List<DistribuidorVO> distribuidores = daoDistribuidores.findByCriterio(criterio);						
				if (distribuidores.size() == 1) {
					lead.setDistribuidor(distribuidores.get(0));
				} else {
					lead.getDistribuidor().setIdCanal(lead.getCanal().getIdCanal());
					daoDistribuidores.insert(lead.getDistribuidor());							
				}
			}
			if (lead.getComercial() != null) {
				CriterioComercialVO criterioComercial = new CriterioComercialVO();
				criterioComercial.setNombre(lead.getComercial().getNombre());
				List<ComercialVO> comerciales = daoComerciales.findByCriterio(criterioComercial );
				if (comerciales.size() == 1) {
					lead.setComercial(comerciales.get(0));
				} else {
					lead.getComercial().setIdCanal(lead.getCanal().getIdCanal());
					lead.getComercial().setIdDistribuidor(lead.getDistribuidor().getIdDistribuidor());
					daoComerciales.insert(lead.getComercial());
				}
			}			
			if (lead.getResponsable() != null) {
				CriterioResponsableVO criterioResponsable = new CriterioResponsableVO();
				criterioResponsable.setNombre(lead.getResponsable().getNombre());			
				List<ResponsableVO> responsables = daoResponsables.findByCriterio(criterioResponsable);
				if (responsables.size() == 1) {
					lead.setResponsable(responsables.get(0));
				} else {
					daoResponsables.insert(lead.getResponsable());
				}
			}
			if (lead.getComercial() != null && lead.getResponsable() != null) {
				ComercialVO comercial = daoResponsablesComerciales.findComercial(lead.getResponsable(), lead.getComercial());
				if (comercial == null) {
					daoResponsablesComerciales.insert(lead.getResponsable(), lead.getComercial());
				}
			}
			
			if (lead.getServicio() != null) {
				ServicioVO servicio = daoServicios.findByNombre(lead.getServicio());
				if (servicio != null) {
					lead.setServicio(servicio);
				} else {
					daoServicios.insert(lead.getServicio());
				}
			}
	
			if (lead.getEstado() != null) {
				EstadoVO estado = (lead.getEstado().getIdEstado() != null) ? daoEstado.findById(lead.getEstado()) : daoEstado.findByNombre(lead.getEstado());						
				if (estado != null) {
					lead.setEstado(estado);
				} else {
					daoEstado.insert(lead.getEstado());
				}
			}
			
			if (lead.getMarketingActivity() != null) {
				MarketingActivityVO ma = (lead.getMarketingActivity().getIdMarketingActivity() != null) ?  daoMarketingActivities.findById(lead.getMarketingActivity()) : daoMarketingActivities.findByName(lead.getMarketingActivity());						
				if (ma != null) {
					lead.setMarketingActivity(ma);
				}
			}
			
			if (lead.getEmpresa() != null) {
				CriterioEmpresaVO criterioEmpresa = new CriterioEmpresaVO();
				criterioEmpresa.setNombre(lead.getEmpresa().getNombre());
	//			criterioEmpresa.setCiudad(lead.getEmpresa().getCiudad());
				List<EmpresaVO> empresas = daoEmpresas.findByCriterio(criterioEmpresa );
				if (empresas.size() == 1) {
					lead.setEmpresa(empresas.get(0));
				} else {
					daoEmpresas.insert(lead.getEmpresa());
				}						
			}
			
			if (lead.getCanal() != null) {
				CanalVO canal = daoCanales.findById(lead.getCanal());
				lead.setCanal(canal);
			}			
			
			daoLeads.insert(lead);
			daoLeadsHistory.insertByLead(lead);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando lead", e);
			throw new ServiceException(e);
		}
	}
	
	public void insert(LeadVO lead) throws ServiceException {
		try {
			lead.setFechaCreacion(new Date());
			
			if (lead.getComercial() != null) {
				ComercialVO comercial = daoComerciales.findById(lead.getComercial());
				lead.setComercial(comercial);
			}
			
			if (lead.getResponsable() != null) {			
				ResponsableVO responsable = daoResponsables.findById(lead.getResponsable());
				lead.setResponsable(responsable);
			}
			
			if (lead.getComercial() != null && lead.getResponsable() != null) {
				ComercialVO comercial = daoResponsablesComerciales.findComercial(lead.getResponsable(), lead.getComercial());
				if (comercial == null) {
					daoResponsablesComerciales.insert(lead.getResponsable(), lead.getComercial());
				}
			}
	
			if (lead.getDistribuidor() != null) {
				DistribuidorVO distribuidor = daoDistribuidores.findById(lead.getDistribuidor());						
				lead.setDistribuidor(distribuidor);
			}
	
			if (lead.getServicio() != null) {
				ServicioVO servicio = daoServicios.findById(lead.getServicio());
				lead.setServicio(servicio);
			}
	
			if (lead.getEstado() != null) {
				EstadoVO estado = (lead.getEstado().getIdEstado() != null) ? daoEstado.findById(lead.getEstado()) : daoEstado.findByNombre(lead.getEstado());						
				if (estado != null) {
					lead.setEstado(estado);
				} else {
					daoEstado.insert(lead.getEstado());
				}
			}
			
			if (lead.getMarketingActivity() != null) {
				MarketingActivityVO ma = (lead.getMarketingActivity().getIdMarketingActivity() != null) ?  daoMarketingActivities.findById(lead.getMarketingActivity()) : daoMarketingActivities.findByName(lead.getMarketingActivity());						
				if (ma != null) {
					lead.setMarketingActivity(ma);
				}
			}
			
			if (lead.getEmpresa() != null) {
				EmpresaVO empresa = daoEmpresas.findById(lead.getEmpresa());
				lead.setEmpresa(empresa);						
			}
			
			if (lead.getCanal() != null) {
				CanalVO canal = daoCanales.findById(lead.getCanal());
				lead.setCanal(canal);
			}			
			
			daoLeads.insert(lead);				
			daoLeadsHistory.insertByLead(lead);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando lead", e);
			throw new ServiceException(e);
		}
	}	
	
	public void delete(LeadVO lead) throws ServiceException {
		try {
			daoLeads.delete(lead);
			daoLeadsHistory.deleteByLead(lead);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el lead", e);
			throw new ServiceException(e);
		}		
	}
	public void updateFechaModificacion(LeadVO lead) throws ServiceException {
		try {
			daoLeads.updateFechaModificacion(lead);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}	
}
