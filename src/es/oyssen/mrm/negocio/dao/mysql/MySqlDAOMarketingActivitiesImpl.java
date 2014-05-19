package es.oyssen.mrm.negocio.dao.mysql;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOMarketingActivities;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.rowmappers.MarketingMapper;
import es.oyssen.mrm.negocio.vo.MarketingActivityVO;

public class MySqlDAOMarketingActivitiesImpl extends DAOBase implements DAOMarketingActivities {
	
	private static String SQL_FIND_ID = "select * from marketing_activities where id_marketing_activity = ?";
	private static String SQL_FIND_NAME = "select * from marketing_activities where upper(nombre) = upper(?)";
	private static String SQL_FINDALL = "select * from marketing_activities";



	public List<MarketingActivityVO> findAll() throws DAOException {
		try {
			//return getJdbcTemplate().query(SQL_FINDALL, new MarketingMapper());
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public MarketingActivityVO findById(MarketingActivityVO pk) throws DAOException {
		try {
			return (MarketingActivityVO) getJdbcTemplate().queryForObject(SQL_FIND_ID, new Object[]{pk.getIdMarketingActivity()}, new MarketingMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}	
	}

	public MarketingActivityVO findByName(MarketingActivityVO pk)
			throws DAOException {
		try {
			return (MarketingActivityVO) getJdbcTemplate().queryForObject(SQL_FIND_NAME, new Object[]{pk.getNombre()}, new MarketingMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}	
	}

}
