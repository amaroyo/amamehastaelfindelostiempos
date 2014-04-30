package es.oyssen.mrm.negocio.vo;

public class MarketingActivityVO {
	private String idMarketingActivity;
	private String nombre;
	
	public MarketingActivityVO() {
				
	}
	
	public MarketingActivityVO(String id,String nombre) {
		this.idMarketingActivity = id;
		this.nombre = nombre;
	}
	
	public String getIdMarketingActivity() {
		return idMarketingActivity;
	}
	public void setIdMarketingActivity(String idMarketingActivity) {
		this.idMarketingActivity = idMarketingActivity;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
