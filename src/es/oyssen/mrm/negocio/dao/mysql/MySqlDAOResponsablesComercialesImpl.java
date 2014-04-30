package es.oyssen.mrm.negocio.dao.mysql;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOResponsablesComerciales;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.rowmappers.ComercialMapper;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.ResponsableVO;

public class MySqlDAOResponsablesComercialesImpl extends DAOBase implements DAOResponsablesComerciales {
	
	private static String SQL_INSERT = "insert into responsables_comerciales (id_responsable, id_comercial) values (?,?)";
	private static String SQL_DELETE = "delete from responsables_comerciales where id_responsable = ? and id_comercial = ?";
	
	private static String SQL_FIND_COMERCIALES = "select c.* from responsables_comerciales rc inner join comerciales c on c.id_comercial = rc.id_comercial where rc.id_responsable = ?";
	private static String SQL_FIND_COMERCIAL = "select c.* from responsables_comerciales rc inner join comerciales c on c.id_comercial = rc.id_comercial where rc.id_responsable = ? and rc.id_comercial = ?";
	
	public List<ComercialVO> findComerciales(ResponsableVO responsable)
			throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_COMERCIALES, new Object[]{responsable.getIdResponsable()}, new ComercialMapper());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public ComercialVO findComercial(ResponsableVO responsable,
			ComercialVO comercial) throws DAOException {
		try {
			return (ComercialVO) getJdbcTemplate().queryForObject(SQL_FIND_COMERCIAL, new Object[]{responsable.getIdResponsable(), comercial.getIdComercial()}, new ComercialMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}

	public void insert(ResponsableVO responsable, ComercialVO comercial)
			throws DAOException, DAOInsertException {
		try {
			getJdbcTemplate().update(SQL_INSERT, new Object[]{responsable.getIdResponsable(), comercial.getIdComercial()});
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void delete(ResponsableVO responsable, ComercialVO comercial)
			throws DAOException, DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{responsable.getIdResponsable(), comercial.getIdComercial()});
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}




}
