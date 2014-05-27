package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.GrupoPermisoVO;
import es.oyssen.mrm.negocio.vo.PermisoVO;

public interface DAOGrupoPermisos {
	
	public List<PermisoVO> findByGrupo(GrupoVO grupo) throws DAOException;
	
	public void insert(GrupoPermisoVO permisoGrupo) throws DAOException, DAOInsertException;
	
	public void delete(GrupoPermisoVO permisoGrupo) throws DAOException, DAODeleteException;

}
