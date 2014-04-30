package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.CriterioEmpresaVO;
import es.oyssen.mrm.negocio.vo.EmpresaVO;

public interface DAOEmpresas {
	
	public List<EmpresaVO> findByCriterio(CriterioEmpresaVO criterio) throws DAOException;
	
	public EmpresaVO findById(EmpresaVO empresa) throws DAOException;
	
	public EmpresaVO findByOrgn(EmpresaVO empresa) throws DAOException;
	
	public void insert(EmpresaVO empresa) throws DAOException, DAOInsertException;
	
	public void update(EmpresaVO empresa) throws DAOException, DAOUpdateException;
	
	public void delete(EmpresaVO empresa) throws DAOException, DAODeleteException;

}
