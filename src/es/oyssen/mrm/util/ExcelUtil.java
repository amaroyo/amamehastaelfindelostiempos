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

import es.oyssen.mrm.negocio.vo.EstanciaUnidadClinicaVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.ProfesorAsociadoVO;
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
