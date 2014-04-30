package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.LeadHistoryVO;
import es.oyssen.mrm.util.StringUtil;

public class LeadHistoryMapper implements RowMapper {

	public static final String FIELD_ID_HISTORY = "id_history";
	public static final String FIELD_ID_LEAD = "id_lead";
	public static final String FIELD_TIPO = "tipo";
    public static final String FIELD_FECHA = "fecha";
    public static final String FIELD_CAMPO = "campo";
    public static final String FIELD_VALOR_ANTERIOR = "valorAnterior";
    public static final String FIELD_VALOR_POSTERIOR = "valorPosterior";

    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	LeadHistoryVO o = new LeadHistoryVO();
    	o.setIdHistory(StringUtil.nullToString(rs.getString(FIELD_ID_HISTORY)));
    	o.setIdLead(StringUtil.nullToString(rs.getString(FIELD_ID_LEAD)));
    	o.setTipo(StringUtil.nullToString(rs.getString(FIELD_TIPO)));
    	o.setFecha(rs.getDate(FIELD_FECHA));
    	o.setCampo(StringUtil.nullToString(rs.getString(FIELD_CAMPO)));
    	o.setValorAnterior(StringUtil.nullToString(rs.getString(FIELD_VALOR_ANTERIOR)));
    	o.setValorPosterior(StringUtil.nullToString(rs.getString(FIELD_VALOR_POSTERIOR)));    	
		return o;
	}

}
