package es.oyssen.mrm.negocio.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOTrabajosDeCampoInfo;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.TrabajosDeCampoInfoService;
import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;
import es.oyssen.mrm.struts.forms.asignaturas.SubirArchivoForm;


public class TrabajosDeCampoInfoServiceImpl implements TrabajosDeCampoInfoService{
	
	
	private static Log log = LogFactory.getLog(TrabajosDeCampoInfoServiceImpl.class);
	private DAOTrabajosDeCampoInfo daoTrabajosDeCampoInfo;

	
	public void setDaoTrabajosDeCampoInfo(DAOTrabajosDeCampoInfo daoTrabajosDeCampoInfo) {
		this.daoTrabajosDeCampoInfo = daoTrabajosDeCampoInfo;
	}
	
	public String  insert(TrabajoDeCampoInfoVO trabajo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			return daoTrabajosDeCampoInfo.insert(trabajo);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando TrabajoDeCampoInfo", e);
			throw new ServiceException(e);
		}		
	}

	public void update(TrabajoDeCampoInfoVO trabajo) 
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoTrabajosDeCampoInfo.update(trabajo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update TrabajoDeCampoInfo", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(TrabajoDeCampoInfoVO trabajo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoTrabajosDeCampoInfo.delete(trabajo);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete TrabajoDeCampoInfo", e);
			throw new ServiceException(e);
		}			
	}

	
	
	public TrabajoDeCampoInfoVO findById(TrabajoDeCampoInfoVO trabajo) throws ServiceException {
		try {
			return daoTrabajosDeCampoInfo.findById(trabajo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findById TrabajoDeCampoInfo", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void process(SubirArchivoForm f) throws ServiceException {
		
		try {
			log.debug("Procesamos fichero.........");

			TrabajoDeCampoInfoVO t = new TrabajoDeCampoInfoVO();
			t.setIdTrabajoInfo(f.getIdTrabajoInfo());
			t = daoTrabajosDeCampoInfo.findById(t);
			t.setEnunciado(f.getFichero().getFileData());
			daoTrabajosDeCampoInfo.update(t);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error procesando fichero SubirArchivoTrabajoCampoForm", e);
			throw new ServiceException(e);
		}

	}
	
	
}
