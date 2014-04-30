package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.CriterioFicheroVO;
import es.oyssen.mrm.negocio.vo.FicheroVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.struts.forms.ficheros.SubirFicheroForm;

public interface FicherosLeadService {

	public List<FicheroVO> findByCriterio(CriterioFicheroVO criterio) throws ServiceException;
	
	public FicheroVO findById(FicheroVO fichero) throws ServiceException;
	
	public void update(FicheroVO fichero) throws ServiceException;
	
	public void delete(FicheroVO fichero) throws ServiceException;
	
	public void insert(FicheroVO fichero) throws ServiceException;
	
	public void process(SubirFicheroForm f) throws ServiceException;
	
	public void deleteByServicio(ServicioVO servicio) throws ServiceException;
	
	public void updateFechaUltimaDescarga(FicheroVO fichero) throws ServiceException;

}
