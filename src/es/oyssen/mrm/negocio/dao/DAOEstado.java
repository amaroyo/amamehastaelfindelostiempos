package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.vo.EstadoVO;

public interface DAOEstado {
	
	public List<EstadoVO> findAll() throws DAOException;
	
	public EstadoVO findById(EstadoVO estado) throws DAOException;
	
	public EstadoVO findByNombre(EstadoVO estado) throws DAOException;
	
	public void insert(EstadoVO estado) throws DAOException, DAOInsertException;
}
