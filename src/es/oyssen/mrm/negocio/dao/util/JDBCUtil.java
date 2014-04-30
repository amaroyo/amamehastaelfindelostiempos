package es.oyssen.mrm.negocio.dao.util;


public class JDBCUtil {
	
	public static String addWhere(boolean where) {
		return where ? "" : "WHERE ";
	}
	
	public static String addAnd(boolean and) {
		return and ? " AND " : "";
	}
	
	public static String addComma(boolean coma){
		return coma ? ", " : "";
	}
	
	public static String addOr(boolean or){
		return or ? " OR " : "";
	}
	
	/**
	 * 
	 * @param query
	 * @param start
	 * @param count
	 * @return
	 */
	public static String buildQueryPage(String query, int start, int count){
		return " select * from ( select a.*, rowid rn from ( " + query + " ) a  where rowid <= " + (start+count) + " ) where rn > "+start; 
	}
}
