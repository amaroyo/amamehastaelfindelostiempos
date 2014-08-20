package es.oyssen.mrm.struts.actions.asignaturas;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.asignaturas.SubirArchivoForm;
import es.oyssen.mrm.struts.forms.ficheros.SubirFicheroForm;
import es.oyssen.mrm.util.ExcelUtil;
import es.oyssen.mrm.util.StringUtil;

public class SubirArchivoAction extends MrmAction {

	private static final int MAX_SIZE_MYSQL = 1048576; //max_allowed_packet=1M / to be changed in my.ini
	
	
	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SubirArchivoForm f = (SubirArchivoForm) form;	
		String tipo="";
		if (!StringUtil.isNullOrBlank(f.getTipoConsulta())) tipo = f.getTipoConsulta();
		String nombre = "";
		String[] sp = {};
		if (!tipo.equals("") && !StringUtil.isNullOrBlank(f.getFichero().getFileName())){
			nombre = f.getFichero().getFileName();
			sp = nombre.split("\\.");
		}
		//lo he puesto ya que si no, archivos grandes como peliculas, y que tengan caracteres raros, todo lo q llega del form es null
		//y se salta el isNullOrBlank y salta excepcion
		if(!tipo.equals("") && !f.getFichero().getFileName().equals("") && f.getFichero().getFileSize()>0){
			
			if(((sp[sp.length-1].toLowerCase()).equals("pdf") || (sp[sp.length-1].toLowerCase()).equals("doc") || (sp[sp.length-1].toLowerCase()).equals("docx")) && f.getFichero().getFileSize()<MAX_SIZE_MYSQL) { 
	
				if (tipo.equals("CasoClinico")){
					PortafolioVO p = new PortafolioVO();
					p.setIdAlumno(f.getIdAlumno());
					p.setIdAsignatura(f.getIdAsignatura());
					String anyoAcademico = (String)request.getSession().getAttribute("anyoAcademico");
					p.setAnyoAcademico(anyoAcademico);
					f.setIdPortafolio(getPortafoliosService().findByAlumnoAsignatura(p).getIdPortafolio());
					getCasosClinicosService().process(f);
					return mapping.findForward("success");
				}
				else if (tipo.equals("TrabajoCampoInfo")){
					getTrabajosDeCampoInfoService().process(f);
					return mapping.findForward("success");
				}
				else if (tipo.equals("TrabajoCampoPractica")){
					TrabajoDeCampoVO tc = new TrabajoDeCampoVO();
					tc.setIdPortafolio(f.getIdPortafolio());
					tc.setIdTrabajoDeCampo(f.getIdTrabajoCampo());
					tc = getTrabajosDeCampoService().findByIDsTC(tc);
								
					String fechaLimite = parsearFechaLimite(tc.getFechaLimite(),false);
					boolean bloqueado = chequearDeadLine(fechaLimite);
					if(!bloqueado){
						
						String n = f.getFichero().getFileName();
						String[] split = n.split("\\.");
						
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						//get current date time with Date() dateFormat.format(date)
						Date date = new Date();
						
						
						if(f.getNombre().equals("")){	
							tc.setNombreTrabajo(split[0] + "_" + ((dateFormat.format(date)).replaceAll(" ", "_")).replaceAll("/", "_")+"." + split[1].toLowerCase());
						}
						else tc.setNombreTrabajo(f.getNombre()+ "_" + ((dateFormat.format(date)).replaceAll(" ", "_")).replaceAll("/", "_")+"." + split[1].toLowerCase());
						
						tc.setTrabajoDeCampo(f.getFichero().getFileData());
						
						getTrabajosDeCampoService().updateTrabajoCampo(tc);
						
						return mapping.findForward("success");
					}
					else return mapping.findForward("error");
				}				
				else if (tipo.equals("TrabajoCampoCorreccion")){
					TrabajoDeCampoVO tc = new TrabajoDeCampoVO();
					tc.setIdPortafolio(f.getIdPortafolio());
					tc.setIdTrabajoDeCampo(f.getIdTrabajoCampo());
					tc = getTrabajosDeCampoService().findByIDsTC(tc);
								
	
					String n = f.getFichero().getFileName();
					String[] split = n.split("\\.");
					
					if(f.getNombre().equals("")){	
						tc.setNombreCorreccion(split[0]+ "." + split[1].toLowerCase());
					}
					else tc.setNombreCorreccion(f.getNombre()+ "." + split[1].toLowerCase());
					
					tc.setCorreccionTrabajo(f.getFichero().getFileData());
					
					getTrabajosDeCampoService().updateTrabajoCampoCorreccion(tc);
					return mapping.findForward("success");
				
				}
				else if (tipo.equals("DiarioReflexivo")){
					PortafolioVO p = new PortafolioVO();
					p.setIdAlumno(f.getIdAlumno());
					p.setIdAsignatura(f.getIdAsignatura());
					String anyoAcademico = (String)request.getSession().getAttribute("anyoAcademico");
					p.setAnyoAcademico(anyoAcademico);
					f.setIdPortafolio(getPortafoliosService().findByAlumnoAsignatura(p).getIdPortafolio());
					getDiariosReflexivosService().process(f);
					return mapping.findForward("success");
				}
				
				else return mapping.findForward("error");
			}
			else if(((sp[sp.length-1].toLowerCase()).equals("xls") || (sp[sp.length-1].toLowerCase()).equals("xlsx")) && f.getFichero().getFileSize()<MAX_SIZE_MYSQL) {
				if (tipo.equals("usuarios")){

					if(parsearUsuarios(f.getFichero().getInputStream())) return mapping.findForward("success");
					else return mapping.findForward("error");
				}
				else if (tipo.equals("profesores")){
					System.out.print("llegue profesores");
					return mapping.findForward("success");
				}
				else if (tipo.equals("alumnos")){
					System.out.print("llegue alumnos");
					return mapping.findForward("success");
				}
				else return mapping.findForward("error");
			}
			else return mapping.findForward("cancel");
			
		}	
		return mapping.findForward("error");
	}
	
	
	


	private static String parsearFechaLimite(String fechaLimite, boolean b) {
		String[] fl = fechaLimite.split(" ");
		String[] date = fl[0].split("-");
		String[] hora = fl[1].split("\\.");
		String out = "";
		if (b) out = "Día: " + date[2] + "/" +  date[1] + "/" + date[0] + " Hora: " + hora[0];
		else out = date[2] + "/" +  date[1] + "/" + date[0] + " " + hora[0];
		return out;
	}
	
	
	private static boolean chequearDeadLine(String fechaLimite) {

		Date tiempoActual = new Date();
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
		String dateInString = fechaLimite;
		
		Date fechaL = null;
		try {
			fechaL = sdf.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		 		
		return tiempoActual.after(fechaL);

	}
	
	
	private boolean parsearUsuarios(InputStream inputStream) {
		boolean error=false;
		String errorlog="REGISTRO DE ERRORES\r\n\r\n";
		
		try {
			log.debug("Procesamos fichero de carga.........");
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = workbook.getSheetAt(0);
	
			log.debug("Procesando archivo excel: " + workbook.getSheetName(0));
			
			Iterator<Row> rows = sheet.rowIterator();
			if (rows.hasNext()) {
				HSSFRow row = (HSSFRow) rows.next();
				while (rows.hasNext()) {
					boolean usuarioCorrecto=true;
					row = (HSSFRow) rows.next();
					
					UsuarioVO usuario = ExcelUtil.parseUsuario(row);
					if(usuario.getNombre().equals("")) {
						usuarioCorrecto=false;
						error=true;
						errorlog += "Error en linea: " + row.getRowNum() +". Razon: El nombre del usuario es vacio.\r\n";
					}
					if(usuario.getApellido1().equals("")) {
						usuarioCorrecto=false;
						error=true;
						errorlog += "Error en linea: " + row.getRowNum() +". Razon: El primer apellido del usuario es vacio.\r\n";
					}
					if(usuario.getCorreo().equals("")) {
						usuarioCorrecto=false;
						error=true;
						errorlog += "Error en linea: " + row.getRowNum() +". Razon: El correo del usuario " + usuario.getNombre() + ", " +usuario.getApellido1() +  " es vacio.\r\n";
					}
					if(!emailCorrecto(usuario.getCorreo())){
						usuarioCorrecto=false;
						error=true;
						errorlog += "Error en linea: " + row.getRowNum() +". Razon: El correo del usuario " + usuario.getNombre() + ", " +usuario.getApellido1() +  " es incorrecto.\r\n";
					}
					if(getUsuariosService().findByCorreo(usuario)!=null){
						usuarioCorrecto=false;
						error=true;
						errorlog += "Error en linea: " + row.getRowNum() +". Razon: El correo del usuario " + usuario.getNombre() + ", " +usuario.getApellido1() +  " ya existe en la base de datos.\r\n";
					}
					if(usuario.getDni().equals("")) {
						usuarioCorrecto=false;
						error=true;
						errorlog += "Error en linea: " + row.getRowNum() +". Razon: El dni del usuario " + usuario.getNombre() + ", " +usuario.getApellido1() +  " es vacio.\r\n";
					}
					if(!dniCorrecto(usuario.getDni())){
						usuarioCorrecto=false;
						error=true;
						errorlog += "Error en linea: " + row.getRowNum() +". Razon: El dni del usuario " + usuario.getNombre() + ", " +usuario.getApellido1() +  " es incorrecto.\r\n";
					}
					if(getUsuariosService().findByDni(usuario)!=null){
						usuarioCorrecto=false;
						error=true;
						errorlog += "Error en linea: " + row.getRowNum() +". Razon: El dni del usuario " + usuario.getNombre() + ", " +usuario.getApellido1() +  " ya existe en la base de datos.\r\n";
					}
					
										
					if(usuarioCorrecto) {
						String contrasenya="";
						contrasenya=generarPassword();
						usuario.setContrasenya(contrasenya);						
						getUsuariosService().insert(usuario);
						enviarCorreo(usuario);
					}
				}
			}
			
			if(!error) return true;
			else {
				generarRegistroErrores(errorlog);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error procesando fichero", e);
			return false;
		}
		
	}





	private boolean dniCorrecto(String correo) {
		correo.matches([X-Z]{1})([-]?)(\d{7})([-]?)([A-Z]{1});
	}





	private boolean emailCorrecto(String correo) {
		if(correo.contains("@")){
			String[] sp = correo.split("@");
			if (sp[1].equals("ucm.es")) return true;
			else return false;
		}
		else return false;
	}
	

}
