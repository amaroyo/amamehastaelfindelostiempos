package es.oyssen.mrm.negocio.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOGruposCriteriosRubricas;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.CriterioRubricaMapper;
import es.oyssen.mrm.negocio.dao.rowmappers.GrupoCriteriosRubricasMapper;
import es.oyssen.mrm.negocio.dao.rowmappers.GruposCriteriosRubricaAsignaturaMapper;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.CriterioRubricaVO;
import es.oyssen.mrm.negocio.vo.GrupoCriteriosRubricasVO;
import es.oyssen.mrm.negocio.vo.GruposCriteriosRubricaAsignaturaVO;


public class MySqlDAOGruposCriteriosRubricasImpl extends DAOBase implements DAOGruposCriteriosRubricas{

	private static String SQL_INSERT = "insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (?,?,?)";
	private static String SQL_UPDATE = "update grupos_criterios_rubricas set nombre=?";
	private static String SQL_DELETE = "delete from grupos_criterios_rubricas where id_grupo_criterio = ? ";
	private static String SQL_FIND_BY_ID = "select * from grupos_criterios_rubricas where id_grupo_criterio = ?";
	private static String SQL_FIND_BY_ASIGNATURA = "select * from grupos_criterios_rubricas where id_asignatura = ?";
	private static String SQL_FIND_GRUPOS_CRITERIOS_RUBRICA_ASIGNATURA = "select gcr.id_asignatura, gcr.id_grupo_criterio, gcr.nombre as nombre_grupo_criterio, gcr.tipo, cr.id_criterio, cr.nombre as nombre_criterio " +
																			"from grupos_criterios_rubricas as gcr, criterios_rubricas as cr " +
																			"where gcr.id_asignatura = cr.id_asignatura and gcr.id_grupo_criterio = cr.id_grupo_criterio " +
																			"and gcr.tipo = 'NOTA' and gcr.id_asignatura = ?";
	private static String SQL_FIND_GRUPOS_ANEXO_RUBRICA_ASIGNATURA = "select gcr.id_asignatura, gcr.id_grupo_criterio, gcr.nombre as nombre_grupo_criterio, gcr.tipo, cr.id_criterio, cr.nombre as nombre_criterio " +
																			"from grupos_criterios_rubricas as gcr, criterios_rubricas as cr " +
																			"where gcr.id_asignatura = cr.id_asignatura and gcr.id_grupo_criterio = cr.id_grupo_criterio " +
																			"and gcr.tipo = 'TEXTO' and gcr.id_asignatura = ?";
	


	public void insert(final GrupoCriteriosRubricasVO grupoCriterioRubrica) throws DAOException,
	DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_grupo_criterio"});
					ps.setString(1, grupoCriterioRubrica.getIdAsignatura());
					ps.setString(2, grupoCriterioRubrica.getNombre());
					ps.setString(3, grupoCriterioRubrica.getTipo());
					return ps;
					
				}
			}
			,kh);
			grupoCriterioRubrica.setIdGrupoCriterio(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}	
	}

	public void update(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws DAOException,
	DAOUpdateException {
		try {
			 
			String query = SQL_UPDATE;

			query += " where id_grupo_criterio = ?";

			getJdbcTemplate().update(query, new Object[]{
					grupoCriterioRubrica.getNombre(),
					grupoCriterioRubrica.getIdGrupoCriterio()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}

	}

	public void delete(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws DAOException,
	DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{grupoCriterioRubrica.getIdGrupoCriterio()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}

	}

	@Override
	public GrupoCriteriosRubricasVO findById(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws DAOException {
		try {
			return (GrupoCriteriosRubricasVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_ID, new Object[]{grupoCriterioRubrica.getIdGrupoCriterio()}, new GrupoCriteriosRubricasMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public List<GrupoCriteriosRubricasVO> findAllByAsignatura(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_ASIGNATURA, new Object[]{grupoCriterioRubrica.getIdAsignatura()}, new GrupoCriteriosRubricasMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public List<GruposCriteriosRubricaAsignaturaVO> findGruposCriteriosRubricaAsignatura(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_GRUPOS_CRITERIOS_RUBRICA_ASIGNATURA, new Object[]{grupoCriterioRubrica.getIdAsignatura()}, new GruposCriteriosRubricaAsignaturaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public List<GruposCriteriosRubricaAsignaturaVO> findGruposAnexoRubricaAsignatura(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_GRUPOS_ANEXO_RUBRICA_ASIGNATURA, new Object[]{grupoCriterioRubrica.getIdAsignatura()}, new GruposCriteriosRubricaAsignaturaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	
	
	

}


