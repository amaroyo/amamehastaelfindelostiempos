package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.util.StringUtil;


public class AnyoMapper implements RowMapper {


    public static final String FIELD_ANYO_ACADEMICO = "anyo_academico";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	PortafolioVO o = new PortafolioVO();
    	
    	o.setAnyoAcademico(StringUtil.nullToString(rs.getString(FIELD_ANYO_ACADEMICO)));

		return o;
	}
}

