package es.oyssen.mrm.negocio.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOAnexos;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.AnexosService;
import es.oyssen.mrm.negocio.vo.AnexoVO;
import es.oyssen.mrm.struts.forms.asignaturas.SubirArchivoForm;

public class AnexosServiceImpl implements AnexosService{
	
	private static Log log = LogFactory.getLog(AnexosServiceImpl.class);
	private DAOAnexos daoAnexos;

	
	public void setDaoAnexos(DAOAnexos daoAnexos) {
		this.daoAnexos = daoAnexos;
	}
	
	public void insert(AnexoVO anexo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoAnexos.insert(anexo);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando anexo", e);
			throw new ServiceException(e);
		}		
	}

	public void update(AnexoVO anexo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoAnexos.update(anexo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update anexo", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(AnexoVO anexo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoAnexos.delete(anexo);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete anexo", e);
			throw new ServiceException(e);
		}			
	}

	
	
	public List<AnexoVO> findAllByPortafolio(AnexoVO anexo) throws ServiceException {
		try {
			return daoAnexos.findAllByPortafolio(anexo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllByPortafolio anexo", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void process(SubirArchivoForm f) throws ServiceException {
		try {
			log.debug("Procesamos fichero.........");
			
			AnexoVO a = new AnexoVO();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			//get current date time with Date()dateFormat.format(date)
			Date date = new Date();
			
			String n = f.getFichero().getFileName();
			String[] sp = n.split("\\.");
			
			if(f.getNombre().equals("")){	
				a.setNombre(sp[0]  + "." + sp[1].toLowerCase());
			}
			else a.setNombre(f.getNombre() + "." + sp[1].toLowerCase());
			a.setAnexo(f.getFichero().getFileData());
			
			
			a.setFechaSubida(dateFormat.format(date));
			a.setIdPortafolio(f.getIdPortafolio());
			daoAnexos.insert(a);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error procesando fichero SubirArchivoAnexoForm", e);
			throw new ServiceException(e);
		}
		
	}

	@Override
	public AnexoVO findById(AnexoVO a) throws ServiceException {
		try {
			return daoAnexos.findById(a);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findById anexo", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	
}
