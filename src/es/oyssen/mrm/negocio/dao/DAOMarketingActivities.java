package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.vo.MarketingActivityVO;

public interface DAOMarketingActivities {	
	
	public List<MarketingActivityVO> findAll() throws DAOException;
	public MarketingActivityVO findById(MarketingActivityVO pk) throws DAOException;
	public MarketingActivityVO findByName(MarketingActivityVO pk) throws DAOException;
}
