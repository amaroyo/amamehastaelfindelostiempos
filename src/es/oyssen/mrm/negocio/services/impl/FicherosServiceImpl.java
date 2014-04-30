package es.oyssen.mrm.negocio.services.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOFicheros;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.FicherosService;
import es.oyssen.mrm.negocio.vo.CriterioFicheroVO;
import es.oyssen.mrm.negocio.vo.FicheroVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.struts.forms.ficheros.SubirFicheroForm;

public class FicherosServiceImpl implements FicherosService{
	
	private static Log log = LogFactory.getLog(FicherosServiceImpl.class);

	private DAOFicheros daoFicheros;
	
	public void setDaoFicheros(DAOFicheros daoFicheros) {
		this.daoFicheros = daoFicheros;
	}
	
	public List<FicheroVO> findByCriterio(CriterioFicheroVO criterio)
			throws ServiceException {
		try {
			return daoFicheros.findByCriterio(criterio);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public FicheroVO findById(FicheroVO fichero)
			throws ServiceException {
		try {
			return daoFicheros.findById(fichero);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public void update(FicheroVO fichero) throws ServiceException {
		try {
			daoFicheros.update(fichero);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}
	
	public void delete(FicheroVO fichero) throws ServiceException {
		try {
			daoFicheros.delete(fichero);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el fichero", e);
			throw new ServiceException(e);
		}		
	}

	public void deleteByServicio(ServicioVO servicio) throws ServiceException {
		try {
			daoFicheros.deleteByServicio(servicio);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el fichero", e);
			throw new ServiceException(e);
		}		
	}	
	
	public void insert(FicheroVO fichero) throws ServiceException {
		try {
			daoFicheros.insert(fichero);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando fichero", e);
			throw new ServiceException(e);
		}
	}	
	
	public void process(SubirFicheroForm f) throws ServiceException {
		try {
			log.debug("Procesamos fichero.........");
			
			FicheroVO fichero = new FicheroVO();
			fichero.setNombre(f.getFichero().getFileName());
			fichero.setTipoContenido(f.getFichero().getContentType());			
			fichero.setIdServicio(f.getIdServicio());
			fichero.setDatos(f.getFichero().getFileData());
			fichero.setDescripcion(f.getDescripcion());
			daoFicheros.insert(fichero);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error procesando fichero", e);
			throw new ServiceException(e);
		}
		
	}

	public void updateFechaUltimaDescarga(FicheroVO fichero) throws ServiceException {
		try {
			daoFicheros.updateFechaUltimaDescarga(fichero);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}
}
