package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.GrupoVO;


public interface GruposService {

	public List<GrupoVO> findAll() throws ServiceException;

}
