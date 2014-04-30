/*
 * DateUtil.java
 *
 * Created on 13 de septiembre de 2007
 */

package es.oyssen.mrm.util;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public final class DateUtil {
    
    /** Patron de fechas por defecto. */
    public static final String PATTERN_DATE_DEFAULT = "dd/MM/yyyy";
    
    /** Patron de fechas por defecto en la BB.DD. */
    public static final String PATTERN_DATE_BBDD = "yyyy-MM-dd";
    
    /** Patron de fechas y horas por defecto. */
    public static final String PATTERN_DATE_TIME_DEFAULT = "dd/MM/yyyy HH:mm:ss";
    
    /** Contructor privado para evitar multiples instancias de la clase. */
    private DateUtil() {}
    
    /**
     * Convierte una fecha a String con el formato de fecha por defecto.
     * @param date Fecha a convertir.
     * @return Un String con la representacion de la fecha con el formato por defecto.
     */
    public static String dateToString(Date date) {
        return dateToString(date, DateUtil.PATTERN_DATE_DEFAULT);
    }
    
    /**
     * Convierte una fecha a String con el formato de fecha especificado en pattern.
     * @param date Fecha a convertir.
     * @param pattern Patron a utilizar. Si este es nulo o vacio se le asigna
     *        el patron por defecto.
     * @return Un String con la representacion de la fecha con el formato pasado.
     */
    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (pattern == null || pattern.length() == 0) {
            pattern = DateUtil.PATTERN_DATE_DEFAULT;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
    
    /**
     * Convierte la fecha de un objeto Calendar a String con el formato de
     * fecha por defecto.
     * @param calendar Objeto Calendar a convertir.
     * @return Un String con la representacion del objeto Calendar con el formato por defecto.
     */
    public static String dateToString(Calendar calendar) {
        if (calendar == null) {
            return "";
        }
        return dateToString(calendar.getTime(), DateUtil.PATTERN_DATE_DEFAULT);
    }
    
    /**
     * Convierte la fecha de un objeto Calendar a String con el formato especificado
     * en pattern.
     * @param calendar Objeto Calendar a convertir.
     * @param pattern Patron a utilizar. Si este es nulo o vacio se le asigna
     *        el patron por defecto.
     * @return Un String con la representacion del objeto Calendar con el formato por defecto.
     */
    public static String dateToString(Calendar calendar, String pattern) {
        if (calendar == null) {
            return "";
        }
        return dateToString(calendar.getTime(), pattern);
    }
    
    /**
     * Convierte una fecha de BB.DD a String con el formato de fecha por defecto.
     * @param date Fecha de BB.DD (java.sql.Date) a convertir.
     * @return Un String con la representacion de la fecha de la BB.DD. con el formato por defecto.
     */
    public static String sqlDateToString(java.sql.Date date) {
        return dateToString((java.util.Date)date, DateUtil.PATTERN_DATE_DEFAULT);
    }
    
    /**
     * Convierte una fecha de BB.DD a String con el formato de fecha por defecto.
     * @param date Fecha de BB.DD (java.sql.Date) a convertir.
     * @param pattern Patron a utilizar. Si este es nulo o vacio se le asigna
     *        el patron por defecto.
     * @return Un String con la representacion de la fecha de la BB.DD. con el formato por defecto.
     */
    public static String sqlDateToString(java.sql.Date date, String pattern) {
        return dateToString((java.util.Date)date, pattern);
    }
    
    /**
     * Convierte una cadena que almacena una fecha con el formato por defecto
     * a una fecha.
     * @param date Cadena que almacena la fecha a convertir.
     * @return Una fecha (Date) de la cadena pasada con el formato por defecto.
     */
    public static Date stringToDate(String date) throws ParseException {
        return stringToDate(date, DateUtil.PATTERN_DATE_DEFAULT);
    }
    
    /**
     * Convierte una cadena que almacena una fecha con el formato por defecto
     * a una fecha.
     * @param date Cadena que almacena la fecha a convertir.
     * @param pattern Patron a utilizar. Si este es nulo o vacio se le asigna
     *        el patron por defecto.
     * @return Una fecha (Date) de la cadena pasada con el formato por defecto.
     */
    public static Date stringToDate(String date, String pattern) throws ParseException {
        if (date == null || date.length() == 0) {
            return null;
        }
        if (pattern == null || pattern.length() == 0) {
            pattern = DateUtil.PATTERN_DATE_DEFAULT;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return df.parse(date);
    }
    
    /**
     * Convierte una cadena que almacena una fecha con el formato por defecto
     * de la BB.DD a una fecha de BB.DD (java.sql.Date).
     * @param date Cadena que almacena la fecha a convertir.
     * @return Una fecha (java.sql.Date) de la cadena pasada con el formato por defecto en la BB.DD.
     */
    public static java.sql.Date stringToSqlDate(String date) throws ParseException {
        return stringToSqlDate(date, DateUtil.PATTERN_DATE_DEFAULT);
    }
    
    /**
     * Convierte una cadena que almacena una fecha con el formato por defecto
     * de la BB.DD a una fecha de BB.DD (java.sql.Date).
     * @param date Cadena que almacena la fecha a convertir.
     * @param pattern Patron a utilizar. Si este es nulo o vacio se le asigna
     *        el patron por defecto en la BB.DD.
     * @return Una fecha (java.sql.Date) de la cadena pasada con el formato por defecto en la BB.DD.
     */
    public static java.sql.Date stringToSqlDate(String date, String pattern) throws ParseException {
        if (date == null || date.length() == 0) {
            return null;
        }
        return new java.sql.Date(stringToDate(date, DateUtil.PATTERN_DATE_DEFAULT).getTime());
    }
    
    /**
     * Convierte un objeto Date a un objeto Calendar que almacena una fecha.
     * @param date Fecha a convertir.
     * @return Un objeto Calendar con la fecha pasada.
     */
    public static Calendar dateToCalendar(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    
    /**
     * Convierte una fecha de java.util a una fecha de java.sql.
     * @param date Fecha a convertir.
     * @return Una fecha java.sql.
     */
    public static java.sql.Date dateToSqlDate(Date date) {
        return date == null ? null : new java.sql.Date(date.getTime());
    }
    
    /**
     * Convierte una fecha de java.util a una fecha de java.sql.
     * @param date Fecha a convertir.
     * @return Una fecha java.sql.
     */
    public static Date sqlDateToDate(java.sql.Date date) throws ParseException {
        return date == null
                ? null
                : stringToDate(date.toString(), DateUtil.PATTERN_DATE_BBDD);
    }
    
    /**
     * Convierte una fecha String a Date
     * @param formatoFecha formato Fecha
     * @param locale Locale del idioma
     * @param fecha string a convertir
     * @return Date fecha.
     * @throws ParseException
     */
    public static Date stringToDate(String formatoFecha,Locale locale,String fecha) throws ParseException{
    	
    	int where;
    	int index = 1;
    	//parseo de fecha con formato ordinal
    	while (fecha.indexOf("st", index) >=0 && index < fecha.length()){
	    	if ((where = fecha.indexOf("st")) >= 0 && (fecha.substring(where -1, where).equals(" ") || StringUtil.isNumeric(fecha.substring(where -1, where)))) {
	    		fecha = fecha.substring( 0, where)+ "th" + fecha.substring( where + 2 );
	    		break;
	    	} else{
	    		index = where+2;
	    	}
    	}
    	while (fecha.indexOf("nd", index) >=0 && index < fecha.length()){
	    	if ((where = fecha.indexOf("nd")) >= 0 && (fecha.substring(where -1, where).equals(" ") || StringUtil.isNumeric(fecha.substring(where -1, where)))) {
	    		fecha = fecha.substring( 0, where)+ "th"+ fecha.substring( where + 2 );
	    		break;
	    	} else{
	    		index = where+2;
	    	}
    	}
    	while (fecha.indexOf("nd", index) >=0 && index < fecha.length()){
		    if ((where = fecha.indexOf("rd")) >= 0 && (fecha.substring(where -1, where).equals(" ") || StringUtil.isNumeric(fecha.substring(where -1, where)))) {
		    		fecha = fecha.substring( 0, where)+ "th"+ fecha.substring( where + 2 );
		    		break;
		    } else{
	    		index = where+2;
	    	}
    	}
    	return new SimpleDateFormat(formatoFecha,locale).parse(fecha);
    }
    
    public static long diffDays(Date start, Date end){
    	return (end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000);
    }

}
