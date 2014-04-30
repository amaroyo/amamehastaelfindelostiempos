package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioLeadVO;
import es.oyssen.mrm.negocio.vo.EmpresaVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.negocio.vo.PuntoVentaVO;

public interface DAOLeads {
	
	public LeadVO findById(LeadVO lead) throws DAOException;
	
	public List<LeadVO> findByCriterio(CriterioLeadVO criterio) throws DAOException;
	
	public void insert(LeadVO lead) throws DAOException, DAOInsertException;
	
	public void update(LeadVO lead) throws DAOException, DAOUpdateException;
	
	public void delete(LeadVO lead) throws DAOException, DAODeleteException;
	
	public void delete(EmpresaVO empresa) throws DAOException;

	public List<LeadVO> findByComercial(ComercialVO comercial) throws DAOException;	
	
	public List<LeadVO> findByPuntoVenta(PuntoVentaVO punto) throws DAOException;
	
	public void updateFechaModificacion(LeadVO lead) throws DAOException, DAOUpdateException;

}
