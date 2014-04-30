package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOGrupos;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.GruposService;
import es.oyssen.mrm.negocio.vo.GrupoVO;


public class GruposServiceImpl implements GruposService{
	
	private static Log log = LogFactory.getLog(GruposServiceImpl.class);

	private DAOGrupos daoGrupos;
	
	
	public void setDaoGrupos(DAOGrupos daoGrupos) {
		this.daoGrupos = daoGrupos;
	}
	

	public List<GrupoVO> findAll()
			throws ServiceException {
		try {
			return daoGrupos.findAll();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
}
