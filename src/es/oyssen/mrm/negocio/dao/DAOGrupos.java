package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.vo.GrupoVO;

public interface DAOGrupos {
	
	public List<GrupoVO> findAll() throws DAOException;

}
