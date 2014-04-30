package es.oyssen.mrm.negocio.services.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOFicherosLead;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.FicherosLeadService;
import es.oyssen.mrm.negocio.vo.CriterioFicheroVO;
import es.oyssen.mrm.negocio.vo.FicheroVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.struts.forms.ficheros.SubirFicheroForm;

public class FicherosLeadServiceImpl implements FicherosLeadService{
	
	private static Log log = LogFactory.getLog(FicherosLeadServiceImpl.class);

	private DAOFicherosLead daoFicherosLead;
	
	public void setDaoFicherosLead(DAOFicherosLead daoFicherosLead) {
		this.daoFicherosLead = daoFicherosLead;
	}
	
	public List<FicheroVO> findByCriterio(CriterioFicheroVO criterio)
			throws ServiceException {
		try {
			return daoFicherosLead.findByCriterio(criterio);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public FicheroVO findById(FicheroVO fichero)
			throws ServiceException {
		try {
			return daoFicherosLead.findById(fichero);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public void update(FicheroVO fichero) throws ServiceException {
		try {
			daoFicherosLead.update(fichero);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}
	
	public void delete(FicheroVO fichero) throws ServiceException {
		try {
			daoFicherosLead.delete(fichero);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el fichero del lead", e);
			throw new ServiceException(e);
		}		
	}

	public void deleteByServicio(ServicioVO servicio) throws ServiceException {
		try {
			daoFicherosLead.deleteByServicio(servicio);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el fichero del lead", e);
			throw new ServiceException(e);
		}		
	}	
	
	public void insert(FicheroVO fichero) throws ServiceException {
		try {
			daoFicherosLead.insert(fichero);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando fichero del lead", e);
			throw new ServiceException(e);
		}
	}	
	
	public void process(SubirFicheroForm f) throws ServiceException {
		try {
			log.debug("Procesamos fichero.........");
			
			FicheroVO fichero = new FicheroVO();
			fichero.setNombre(f.getFichero().getFileName());
			fichero.setTipoContenido(f.getFichero().getContentType());			
			fichero.setIdLead(f.getIdLead());
			fichero.setDatos(f.getFichero().getFileData());
			fichero.setDescripcion(f.getDescripcion());
			daoFicherosLead.insert(fichero);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error procesando fichero", e);
			throw new ServiceException(e);
		}
		
	}

	public void updateFechaUltimaDescarga(FicheroVO fichero) throws ServiceException {
		try {
			daoFicherosLead.updateFechaUltimaDescarga(fichero);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}
}
