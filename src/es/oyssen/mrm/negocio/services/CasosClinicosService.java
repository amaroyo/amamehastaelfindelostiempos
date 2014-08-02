package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.struts.forms.asignaturas.SubirArchivoCasoClinicoForm;
import es.oyssen.mrm.struts.forms.ficheros.SubirFicheroForm;

public interface CasosClinicosService {
	
	//public void insert(CasoClinicoVO casoClinico) throws ServiceException, DAOException;
	
	public void update(CasoClinicoVO casoClinico) throws ServiceException, DAOException;
	
	public void delete(CasoClinicoVO casoClinico) throws ServiceException, DAOException;
	
	public List<CasoClinicoVO> findAllByPortafolio(CasoClinicoVO casoClinico) throws ServiceException;

	public void process(SubirArchivoCasoClinicoForm f) throws ServiceException, DAOException;

	public CasoClinicoVO findByIDs(CasoClinicoVO caso) throws ServiceException;

}

