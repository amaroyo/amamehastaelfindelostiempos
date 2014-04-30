package es.oyssen.mrm.struts;

public interface Constantes {
	
	public final String APPLICATION_RESOURCES = "es/oyssen/mrm/struts/ApplicationResources";

    /**  
     * Clave del atributo de sesion bajo el cual se almacenan los datos de  
     * autenticaci—n establecidos por el filtro de autenticaci—n. 
     * Este atributo es establecido en el filtro de autenticacion.
     */
    public static final String AUTENTICACION_SSO_KEY = "autenticacionSSO";
    
    /** 
     * Clave del atributo de sesion bajo el cual se almacenan los datos de la 
     * sesion del usuario. 
     * Este atributo suele ser establecido tipicamente en la acci—n de Login.
     */
    public static final String SESION_USUARIO_KEY = "es.m2da.sip.arquitectura.SESION_USUARIO";
    
    public static final String LOCALE_KEY = "org.apache.struts.action.LOCALE";
    
	//CONSTANTES DE REDIRECCIONES A NIVEL DE CONTROLADOR
	public final String FORWARD_SUCCESS_KEY = "success";
	public final String FORWARD_CANCEL_KEY = "cancel";
	public final String FORWARD_ERROR = "error";


}
