package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.vo.LogUsuarioVO;

public interface DAOLogsUsuario {
	
	public List<LogUsuarioVO> findAll() throws DAOException;
	
	public void insert(LogUsuarioVO logUsuario) throws DAOException, DAOInsertException;	

}
