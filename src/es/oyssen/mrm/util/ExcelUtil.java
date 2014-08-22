package es.oyssen.mrm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.negocio.vo.EmpresaVO;
import es.oyssen.mrm.negocio.vo.EstadoVO;
import es.oyssen.mrm.negocio.vo.EstanciaUnidadClinicaVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.negocio.vo.MarketingActivityVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.ProfesorAsociadoVO;
import es.oyssen.mrm.negocio.vo.ResponsableVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;

public class ExcelUtil {

	
	public static EstanciaUnidadClinicaVO parsearEstancia(HSSFRow row) {
		
		EstanciaUnidadClinicaVO es = new EstanciaUnidadClinicaVO();
		try {
			
			String centroHospital = (row.getCell(6) != null) ? row.getCell(6).getStringCellValue() : "";
			es.setCentroAsociado(centroHospital);
			if (centroHospital.contains("Mañana")) es.setTurno("M - Mañana");
			else if (centroHospital.contains("Tarde")) es.setTurno("T - Tarde");
			else es.setTurno("");
			
			es.setUnidadClinica((row.getCell(7) != null) ? row.getCell(7).getStringCellValue() : "");
			
			try{
				Date dIni = (row.getCell(8) != null) ? row.getCell(8).getDateCellValue() : null;
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (dIni!=null){
					String fIni = dateFormat.format(dIni);
					es.setFechaInicio(fIni);
				}
				else es.setFechaInicio("");
			}
			catch (Exception ex){
				es.setFechaInicio("");
			}
			
			try{
				Date dFin = (row.getCell(9) != null) ? row.getCell(9).getDateCellValue() : null;
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (dFin!=null){
					String fFin = dateFormat.format(dFin);
					es.setFechaFin(fFin);
				}
				else es.setFechaFin("");
			}
			catch (Exception ex){
				es.setFechaFin("");
			}
			
			if (!CompararFechas(es.getFechaInicio(), es.getFechaFin())) {
				es.setFechaInicio("-1");
				es.setFechaFin("-1");
			}
			
			return es;
		} catch (Exception e) {
			es = new EstanciaUnidadClinicaVO("","","","");
			return es;
		}
	}
	
	
	public static PortafolioVO parsearPortafolio(HSSFRow row) {
		PortafolioVO p = new PortafolioVO();
		try {
			
			String cursoAcademico = (row.getCell(2) != null) ? row.getCell(2).getStringCellValue() : "";
			if(!cursoAcademico.equals("")){
				String[] sp = {};
				sp = cursoAcademico.split("-");
				try{
					if(Integer.parseInt(sp[sp.length-1])>0 && Integer.parseInt(sp[sp.length-1])<99){
						if (Integer.parseInt(sp[0])>2000 && Integer.parseInt(sp[0])<2099){
							String anyoAcademico = sp[0] + "/20" + sp[sp.length-1];
							p.setAnyoAcademico(anyoAcademico);
						}
						else p.setAnyoAcademico("");
					}
				}
				catch (Exception e){
					p.setAnyoAcademico("");
				}
			}
			else p.setAnyoAcademico("");
			
			int cod = row.getCell(5).getCellType();
			if (cod == 0 ) {
				double c = (row.getCell(5) != null) ? row.getCell(5).getNumericCellValue() : 0;
				int a = (int) c;
				p.setIdAsignatura(Integer.toString(a));
			}
			else if (cod == 1){
				p.setIdAsignatura((row.getCell(5) != null) ? row.getCell(5).getStringCellValue() : "");
			}
			else p.setIdAsignatura("0");
				
				
			
			
			return p;
		} catch (Exception e) {
			p = new PortafolioVO("","","","");
			return p;
		}
	}
	
	
	
	public static ProfesorAsociadoVO parsearProfesorAsociado(HSSFRow row) {
		ProfesorAsociadoVO p = new ProfesorAsociadoVO();
		try {
			
			String cursoAcademico = (row.getCell(2) != null) ? row.getCell(2).getStringCellValue() : "";
			if(!cursoAcademico.equals("")){
				String[] sp = {};
				sp = cursoAcademico.split("-");
				try{
					if(Integer.parseInt(sp[sp.length-1])>0 && Integer.parseInt(sp[sp.length-1])<99){
						if (Integer.parseInt(sp[0])>2000 && Integer.parseInt(sp[0])<2099){
							String anyoAcademico = sp[0] + "/20" + sp[sp.length-1];
							p.setAnyoAcademico(anyoAcademico);
						}
						else p.setAnyoAcademico("");
					}
				}
				catch (Exception e){
					p.setAnyoAcademico("");
				}
			}
			else p.setAnyoAcademico("");
			int cod = row.getCell(5).getCellType();
			if (cod == 0 ) {
				double c = (row.getCell(5) != null) ? row.getCell(5).getNumericCellValue() : 0;
				int a = (int) c;
				p.setIdAsignatura(Integer.toString(a));
			}
			else if (cod == 1){
				p.setIdAsignatura((row.getCell(5) != null) ? row.getCell(5).getStringCellValue() : "");
			}
			else p.setIdAsignatura("0");
				
				
			
			String centroHospital = (row.getCell(6) != null) ? row.getCell(6).getStringCellValue() : "";
			p.setCentroAsociado(centroHospital);
			if (centroHospital.contains("Mañana")) p.setTurno("M - Mañana");
			else if (centroHospital.contains("Tarde")) p.setTurno("T - Tarde");
			else p.setTurno("");
			return p;
		} catch (Exception e) {
			p = new ProfesorAsociadoVO("","","","","");
			return p;
		}
		
	}

	
	public static UsuarioVO parsearCandidato(HSSFRow row) {
		UsuarioVO u = new UsuarioVO();
		try {
			u.setDni((row.getCell(1) != null) ? row.getCell(1).getStringCellValue() : "");
			return u;
		} catch (Exception e) {
			u = new UsuarioVO("","","","","","","");
			return u;
		}
	}
	
	public static UsuarioVO parseUsuario(HSSFRow row) {
		UsuarioVO u = new UsuarioVO();
		try {
			u.setApellido1((row.getCell(0) != null) ? row.getCell(0).getStringCellValue() : "");
			u.setApellido2((row.getCell(1) != null) ? row.getCell(1).getStringCellValue() : "");
			u.setNombre((row.getCell(2) != null) ? row.getCell(2).getStringCellValue() : "");
			u.setDni((row.getCell(3) != null) ? row.getCell(3).getStringCellValue() : "");
			u.setTelefono((row.getCell(4) != null) ? row.getCell(4).getStringCellValue() : "");
			u.setCorreo((row.getCell(5) != null) ? row.getCell(5).getStringCellValue() : "");
			return u;
		} catch (Exception e) {
			u = new UsuarioVO("","","","","","","");
			return u;
		}
	}	
	
	public static LeadVO parseLead(HSSFRow row) {
		LeadVO lead = new LeadVO();
		lead.setResponsable(parseResponsable(row));
		lead.setServicio(parseServicio(row));
		lead.setDistribuidor(parseDistribuidor(row));
		lead.setComercial(parseComercial(row));
		lead.setMarketingActivity(parseMarketingActivity(row));
		lead.setEmpresa(parseEmpresa(row));
		lead.setEstado(parseEstado(row));
		lead.setFechaCreacion(new Date());

//		lead.setFechaVisita((row.getCell(17) != null) ? row.getCell(17).getDateCellValue() : null);
		lead.setComentarios(getCellValue(row.getCell(14)));
		
		lead.setUsuariosPotenciales(getCellValue(row.getCell(12)));
		lead.setUsuarios(getCellValue(row.getCell(13)));		
		
		return lead;
	}
	
	private static EstadoVO parseEstado(HSSFRow row) {
		String nombre = (row.getCell(11) != null) ? row.getCell(11).getStringCellValue() : "";
		EstadoVO estado = (!StringUtil.isNullOrBlank(nombre)) ? new EstadoVO(null, nombre) : null;
		return estado;
	}

	private static MarketingActivityVO parseMarketingActivity(HSSFRow row) {
		String nombre = (row.getCell(4) != null) ? row.getCell(4).getStringCellValue() : "";
		MarketingActivityVO ma = (!StringUtil.isNullOrBlank(nombre)) ? new MarketingActivityVO(null, nombre) : null;
		return ma;
	}
	
	private static ResponsableVO parseResponsable(HSSFRow row) {
		String nombre = (row.getCell(0) != null) ? row.getCell(0).getStringCellValue() : "";
		ResponsableVO responsable = (!StringUtil.isNullOrBlank(nombre)) ? new ResponsableVO(null, nombre) : null;
		return responsable;
	}

	private static DistribuidorVO parseDistribuidor(HSSFRow row) {
		String nombre = (row.getCell(2) != null) ? row.getCell(2).getStringCellValue() : "";
		DistribuidorVO dist = (!StringUtil.isNullOrBlank(nombre)) ? new DistribuidorVO(null, nombre) : null;
		return dist;
	}

	private static ComercialVO parseComercial(HSSFRow row) {
		String nombre = (row.getCell(3) != null) ? row.getCell(3).getStringCellValue() : "";
		ComercialVO comercial = (!StringUtil.isNullOrBlank(nombre)) ? new ComercialVO(null, nombre) : null;
		return comercial;
	}

	private static ServicioVO parseServicio(HSSFRow row) {
		String nombre = (row.getCell(1) != null) ? row.getCell(1).getStringCellValue() : "";
		ServicioVO servicio = (!StringUtil.isNullOrBlank(nombre)) ? new ServicioVO(null, nombre) : null;
		return servicio;
	}
	
	private static EmpresaVO parseEmpresa(HSSFRow row) {
		if (row.getCell(6) == null) return null;
		EmpresaVO empresa = new EmpresaVO();

		
		empresa.setOrgN(getCellValue(row.getCell(5)));
		empresa.setNombre(getCellValue(row.getCell(6)));
		
		empresa.setInformacionContacto(getCellValue(row.getCell(7)));
		empresa.setTelefonoMovil(getCellValue(row.getCell(8)));
		empresa.setEmail(getCellValue(row.getCell(9)));
		empresa.setDireccion(getCellValue(row.getCell(10)));

		//		empresa.setNumeroCoches((row.getCell(8) != null) ? row.getCell(8).getNumericCellValue() + "" : "");
//		empresa.setComentarios((row.getCell(10) != null) ? row.getCell(10).getStringCellValue() : "") ;
//		empresa.setDireccion((row.getCell(11) != null) ? row.getCell(11).getStringCellValue() : "");
//		empresa.setItescoN((row.getCell(12) != null) ? row.getCell(12).getNumericCellValue() + "" : "");
//		empresa.setCodigoPostal((row.getCell(13) != null) ? row.getCell(13).getNumericCellValue() + "" : "");
//		empresa.setCiudad((row.getCell(14) != null) ? row.getCell(14).getStringCellValue() : "");
//		empresa.setTelefono((row.getCell(15) != null) ? row.getCell(15).getStringCellValue() : "");
//		empresa.setInformacionContacto((row.getCell(16) != null) ? row.getCell(16).getStringCellValue() : "");
		return empresa;
	}	
	
	
	private static String getCellValue(HSSFCell cell) {
		String value = "";
		if (cell != null) {
			if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
				value = "";
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				//value = cell.getNumericCellValue();			
				value = String.valueOf((long)cell.getNumericCellValue());
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				value = cell.getStringCellValue() + "";
			}
		}
		return value;
	}
	
	

	
	private static void createCell(HSSFRow row, int i, String value, HSSFCellStyle cellStyle){
		HSSFCell cell = row.createCell(i);
		cell.setCellValue(value);
		if(cellStyle != null){
			cell.setCellStyle(cellStyle);
		}
	}
	
	private static HSSFCellStyle createStyle(HSSFWorkbook book){
		HSSFCellStyle createCellStyle = book.createCellStyle();
		createCellStyle.setFillForegroundColor(new HSSFColor.BLUE_GREY().getIndex());
		createCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFFont font = book.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setColor(new HSSFColor.WHITE().getIndex());
		createCellStyle.setFont(font);
		
		return createCellStyle;
	}

	private static void autoSize(HSSFSheet sheet, int size){
		for (int i = 0; i < size; i++) {
			sheet.autoSizeColumn(i);
		}
	}
	
	public static HSSFWorkbook createLeads(List<LeadVO> leads){
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("Leads");

		int i = 0;
		HSSFRow cabecera = sheet.createRow(i++);

		HSSFCellStyle style = createStyle(book);
		
		createCell(cabecera, 0, "Responsible", style);
		createCell(cabecera, 1, "Service", style);
		createCell(cabecera, 2, "Distributor", style);
		createCell(cabecera, 3, "Sales Rep.", style);
		createCell(cabecera, 4, "Marketing activity", style);
		createCell(cabecera, 5, "Org number", style);
		createCell(cabecera, 6, "Customer", style);	
		createCell(cabecera, 7, "Contact", style);
		createCell(cabecera, 8, "Mobile phone number", style);
		createCell(cabecera, 9, "Email", style);
		createCell(cabecera, 10, "Adrress", style);
		createCell(cabecera, 11, "Status", style);
		createCell(cabecera, 12, "Potential Users", style);
		createCell(cabecera, 13, "Users", style);
		createCell(cabecera, 14, "Comment", style);


		
		for (Iterator iterator = leads.iterator(); iterator.hasNext();) {
			LeadVO lead = (LeadVO) iterator.next();
			HSSFRow row = sheet.createRow(i++);

			createCell(row, 0, lead.getResponsable().getNombre(), null);
			createCell(row, 1, lead.getServicio().getNombre(), null);
			createCell(row, 2, lead.getDistribuidor().getNombre(), null);
			createCell(row, 3, lead.getComercial().getNombre(), null);			
			createCell(row, 4, lead.getMarketingActivity().getNombre(), null);
			createCell(row, 5, lead.getEmpresa().getOrgN(), null);			
			createCell(row, 6, lead.getEmpresa().getNombre(), null);
			createCell(row, 7, lead.getEmpresa().getInformacionContacto(), null);
			createCell(row, 8, lead.getEmpresa().getTelefonoMovil(), null);
			createCell(row, 9, lead.getEmpresa().getEmail(), null);
			createCell(row, 10, lead.getEmpresa().getDireccion(), null);			
			createCell(row, 11, lead.getEstado().getNombre(), null);
			createCell(row, 12, lead.getUsuariosPotenciales(), null);
			createCell(row, 13, lead.getUsuarios(), null);
			createCell(row, 14, lead.getComentarios(), null);

		}

		autoSize(sheet, 11);

		return book;
	}

	
	public static HSSFWorkbook createComerciales(List<ComercialVO> comerciales){
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("Sales rep");

		int i = 0;
		HSSFRow cabecera = sheet.createRow(i++);

		HSSFCellStyle style = createStyle(book);
		
		createCell(cabecera, 0, "Name", style);
		createCell(cabecera, 1, "Phone number", style);
		createCell(cabecera, 2, "Mobile phone number", style);
		createCell(cabecera, 3, "Address", style);
		createCell(cabecera, 4, "Postal code", style);
		createCell(cabecera, 5, "City", style);
		createCell(cabecera, 6, "Country", style);
		createCell(cabecera, 7, "Email address", style);
		createCell(cabecera, 8, "Comments", style);


		for (Iterator iterator = comerciales.iterator(); iterator.hasNext();) {
			ComercialVO comercial = (ComercialVO) iterator.next();
			HSSFRow row = sheet.createRow(i++);

			createCell(row, 0, comercial.getNombre(), null);
			createCell(row, 1, comercial.getTelefono(), null);
			createCell(row, 2, comercial.getTelefonoMovil(), null);
			createCell(row, 3, comercial.getDireccion(), null);			
			createCell(row, 4, comercial.getCodigoPostal(), null);
			createCell(row, 5, comercial.getCiudad(), null);			
			createCell(row, 6, comercial.getPais(), null);			
			createCell(row, 7, comercial.getEmail(), null);
			createCell(row, 8, comercial.getComentarios(), null);

		}

		autoSize(sheet, 9);

		return book;
	}
	
	public static HSSFWorkbook createResponsables(List<ResponsableVO> responsables){
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("Responsibles");

		int i = 0;
		HSSFRow cabecera = sheet.createRow(i++);

		HSSFCellStyle style = createStyle(book);
		
		createCell(cabecera, 0, "Name", style);
		createCell(cabecera, 1, "Phone number", style);
		createCell(cabecera, 2, "Mobile phone number", style);
		createCell(cabecera, 3, "Address", style);
		createCell(cabecera, 4, "Postal code", style);
		createCell(cabecera, 5, "City", style);
		createCell(cabecera, 6, "Country", style);
		createCell(cabecera, 7, "Email address", style);
		createCell(cabecera, 8, "Comments", style);


		for (Iterator iterator = responsables.iterator(); iterator.hasNext();) {
			ResponsableVO responsable = (ResponsableVO) iterator.next();
			HSSFRow row = sheet.createRow(i++);

			createCell(row, 0, responsable.getNombre(), null);
			createCell(row, 1, responsable.getTelefono(), null);
			createCell(row, 2, responsable.getTelefonoMovil(), null);
			createCell(row, 3, responsable.getDireccion(), null);			
			createCell(row, 4, responsable.getCodigoPostal(), null);
			createCell(row, 5, responsable.getCiudad(), null);			
			createCell(row, 6, responsable.getPais(), null);			
			createCell(row, 7, responsable.getEmail(), null);
			createCell(row, 8, responsable.getComentarios(), null);

		}

		autoSize(sheet, 9);

		return book;
	}
	
	public static HSSFWorkbook createDistribuidores(List<DistribuidorVO> distribuidores){
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("Distributors");

		int i = 0;
		HSSFRow cabecera = sheet.createRow(i++);

		HSSFCellStyle style = createStyle(book);
		
		createCell(cabecera, 0, "Name", style);
		createCell(cabecera, 1, "Phone number", style);
		createCell(cabecera, 2, "Mobile phone number", style);
		createCell(cabecera, 3, "Address", style);
		createCell(cabecera, 4, "Postal code", style);
		createCell(cabecera, 5, "City", style);
		createCell(cabecera, 6, "Country", style);
		createCell(cabecera, 7, "Email address", style);		
		createCell(cabecera, 8, "Invoice address", style);
		createCell(cabecera, 9, "Invoice postal code", style);
		createCell(cabecera, 10, "Invoice city", style);
		createCell(cabecera, 11, "Invoice country", style);
		createCell(cabecera, 12, "Invoice name", style);
		createCell(cabecera, 13, "Invoice phone number", style);
		createCell(cabecera, 14, "Invoice mobile phone number", style);
		createCell(cabecera, 15, "Invoice Email address", style);
		
		



		for (Iterator iterator = distribuidores.iterator(); iterator.hasNext();) {
			DistribuidorVO distribuidor = (DistribuidorVO) iterator.next();
			HSSFRow row = sheet.createRow(i++);

			createCell(row, 0, distribuidor.getNombre(), null);
			createCell(row, 1, distribuidor.getTelefono(), null);
			createCell(row, 2, distribuidor.getTelefonoMovil(), null);
			createCell(row, 3, distribuidor.getDireccion(), null);			
			createCell(row, 4, distribuidor.getCodigoPostal(), null);
			createCell(row, 5, distribuidor.getCiudad(), null);			
			createCell(row, 6, distribuidor.getPais(), null);			
			createCell(row, 7, distribuidor.getEmail(), null);			
			createCell(row, 8, distribuidor.getDireccionFacturacion(), null);
			createCell(row, 9, distribuidor.getCodigoPostalFacturacion(), null);
			createCell(row, 10, distribuidor.getCiudadFacturacion(), null);
			createCell(row, 11, distribuidor.getPaisFacturacion(), null);
			createCell(row, 12, distribuidor.getNombreFacturacion(), null);
			createCell(row, 13, distribuidor.getTelefonoFacturacion(), null);
			createCell(row, 14, distribuidor.getTelefonoMovilFacturacion(), null);
			createCell(row, 15, distribuidor.getEmailFacturacion(), null);

		}

		autoSize(sheet, 16);

		return book;
	}	
	
	public static HSSFWorkbook createEmpresas(List<EmpresaVO> empresas){
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("Companies");

		int i = 0;
		HSSFRow cabecera = sheet.createRow(i++);

		HSSFCellStyle style = createStyle(book);
		
		createCell(cabecera, 0, "Org. Number", style);
		createCell(cabecera, 1, "Name", style);
		createCell(cabecera, 2, "Phone number", style);
		createCell(cabecera, 3, "Mobile phone number", style);
		createCell(cabecera, 4, "Email address", style);
		createCell(cabecera, 5, "Contact", style);
		createCell(cabecera, 6, "Adrress", style);


		for (Iterator iterator = empresas.iterator(); iterator.hasNext();) {
			EmpresaVO empresa = (EmpresaVO) iterator.next();
			HSSFRow row = sheet.createRow(i++);

			createCell(row, 0, empresa.getOrgN(), null);
			createCell(row, 1, empresa.getNombre(), null);
			createCell(row, 2, empresa.getTelefono(), null);
			createCell(row, 3, empresa.getTelefonoMovil(), null);
			createCell(row, 4, empresa.getEmail(), null);			
			createCell(row, 5, empresa.getInformacionContacto(), null);	
			createCell(row, 6, empresa.getDireccion(), null);	

		}

		autoSize(sheet, 6);

		return book;
	}
	
	public static HSSFWorkbook createServicios(List<ServicioVO> servicios){
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("Services");

		int i = 0;
		HSSFRow cabecera = sheet.createRow(i++);

		HSSFCellStyle style = createStyle(book);
		
		createCell(cabecera, 0, "Name", style);
		createCell(cabecera, 1, "Contact name", style);
		createCell(cabecera, 2, "Supplier", style);
	


		for (Iterator iterator = servicios.iterator(); iterator.hasNext();) {
			ServicioVO servicio = (ServicioVO) iterator.next();
			HSSFRow row = sheet.createRow(i++);

			createCell(row, 0, servicio.getNombre(), null);
			createCell(row, 1, servicio.getPersonaContacto(), null);
			createCell(row, 2, servicio.getProveedor(), null);		

		}

		autoSize(sheet, 3);

		return book;
	}	

	public static HSSFWorkbook createCanales(List<CanalVO> canales){
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("Channels");

		int i = 0;
		HSSFRow cabecera = sheet.createRow(i++);

		HSSFCellStyle style = createStyle(book);
		
		createCell(cabecera, 0, "Name", style);
		createCell(cabecera, 1, "Phone number", style);
		createCell(cabecera, 2, "Mobile phone number", style);
		createCell(cabecera, 3, "Address", style);
		createCell(cabecera, 4, "Postal code", style);
		createCell(cabecera, 5, "City", style);
		createCell(cabecera, 6, "Country", style);
		createCell(cabecera, 7, "Email address", style);		
		createCell(cabecera, 8, "Invoice address", style);
		createCell(cabecera, 9, "Invoice postal code", style);
		createCell(cabecera, 10, "Invoice city", style);
		createCell(cabecera, 11, "Invoice country", style);
		createCell(cabecera, 12, "Invoice name", style);
		createCell(cabecera, 13, "Invoice phone number", style);
		createCell(cabecera, 14, "Invoice mobile phone number", style);
		createCell(cabecera, 15, "Invoice Email address", style);
		createCell(cabecera, 16, "Web address", style);
		createCell(cabecera, 17, "Responsible", style);
		
		



		for (Iterator iterator = canales.iterator(); iterator.hasNext();) {
			CanalVO canal = (CanalVO) iterator.next();
			HSSFRow row = sheet.createRow(i++);

			createCell(row, 0, canal.getNombre(), null);
			createCell(row, 1, canal.getTelefono(), null);
			createCell(row, 2, canal.getTelefonoMovil(), null);
			createCell(row, 3, canal.getDireccion(), null);			
			createCell(row, 4, canal.getCodigoPostal(), null);
			createCell(row, 5, canal.getCiudad(), null);			
			createCell(row, 6, canal.getPais(), null);			
			createCell(row, 7, canal.getEmail(), null);			
			createCell(row, 8, canal.getDireccionFacturacion(), null);
			createCell(row, 9, canal.getCodigoPostalFacturacion(), null);
			createCell(row, 10, canal.getCiudadFacturacion(), null);
			createCell(row, 11, canal.getPaisFacturacion(), null);
			createCell(row, 12, canal.getNombreFacturacion(), null);
			createCell(row, 13, canal.getTelefonoFacturacion(), null);
			createCell(row, 14, canal.getTelefonoMovilFacturacion(), null);
			createCell(row, 15, canal.getEmailFacturacion(), null);
			createCell(row, 16, canal.getDireccionWeb(), null);
			createCell(row, 17, canal.getMrmResponsable(), null);

		}

		autoSize(sheet, 18);

		return book;
	}



	private static boolean CompararFechas(String ini, String fin) {


		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
		String dateIni = ini;
		String dateFin = fin;
		
		Date fechaIni = null;
		Date fechaFin = null;
		try {
			fechaIni = sdf.parse(dateIni);
			fechaFin = sdf.parse(dateFin);
		} catch (ParseException e) {
			return false;
		}
		
		
		 		
		return fechaIni.before(fechaFin);

	}


	

	
	

		
	
}
