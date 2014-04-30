package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.PermisoGrupoVO;

public interface DAOPermisosGrupo {
	
	public List<PermisoGrupoVO> findByGrupo(GrupoVO grupo) throws DAOException;
	
	public void insert(PermisoGrupoVO permisoGrupo) throws DAOException, DAOInsertException;
	
	public void delete(PermisoGrupoVO permisoGrupo) throws DAOException, DAODeleteException;

}
