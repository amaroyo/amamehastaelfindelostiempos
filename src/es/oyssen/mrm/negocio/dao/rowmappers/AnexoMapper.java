package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.AnexoVO;
import es.oyssen.mrm.util.StringUtil;


public class AnexoMapper implements RowMapper {

	public static final String FIELD_ID_ANEXO = "id_anexo";
	public static final String FIELD_ID_PORTAFOLIO = "id_portafolio";
	public static final String FIELD_ANEXO = "anexo";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	AnexoVO o = new AnexoVO();
    	o.setIdAnexo(StringUtil.nullToString(rs.getString(FIELD_ID_ANEXO)));
    	o.setIdPortafolio(StringUtil.nullToString(rs.getString(FIELD_ID_PORTAFOLIO)));
    	o.setAnexo(StringUtil.nullToString(rs.getString(FIELD_ANEXO)));
		return o;
	}
}

