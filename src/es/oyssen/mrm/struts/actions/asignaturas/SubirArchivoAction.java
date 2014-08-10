package es.oyssen.mrm.struts.actions.asignaturas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.asignaturas.SubirArchivoForm;
import es.oyssen.mrm.struts.forms.ficheros.SubirFicheroForm;

public class SubirArchivoAction extends MrmAction {

	private static final int MAX_SIZE_MYSQL = 1048576; //max_allowed_packet=1M / to be changed in my.ini
	
	
	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SubirArchivoForm f = (SubirArchivoForm) form;	
		String tipo = f.getTipoConsulta();
		String nombre = f.getFichero().getFileName();
		String[] sp = nombre.split("\\.");
		if(((sp[1].toLowerCase()).equals("pdf") || (sp[1].toLowerCase()).equals("doc") || (sp[1].toLowerCase()).equals("docx")) && f.getFichero().getFileSize()<MAX_SIZE_MYSQL) { 

			if (tipo.equals("CasoClinico")){
				PortafolioVO p = new PortafolioVO();
				p.setIdAlumno(f.getIdAlumno());
				p.setIdAsignatura(f.getIdAsignatura());
				String anyoAcademico = (String)request.getSession().getAttribute("anyoAcademico");
				p.setAnyoAcademico(anyoAcademico);
				f.setIdPortafolio(getPortafoliosService().findByAlumnoAsignatura(p).getIdPortafolio());
				getCasosClinicosService().process(f);
			}
			else if (tipo.equals("TrabajoCampoInfo")){
				getTrabajosDeCampoInfoService().process(f);
			}
			else if (tipo.equals("TrabajoCampoPractica")){
				TrabajoDeCampoVO tc = new TrabajoDeCampoVO();
				tc.setIdPortafolio(f.getIdPortafolio());
				tc.setIdTrabajoDeCampo(f.getIdTrabajoCampo());
				tc = getTrabajosDeCampoService().findByIDs(tc);
							
				String fechaLimite = parsearFechaLimite(tc.getFechaLimite(),false);
				boolean bloqueado = chequearDeadLine(fechaLimite);
				if(!bloqueado){
					
					String n = f.getFichero().getFileName();
					String[] split = n.split("\\.");
					
					if(f.getNombre().equals("")){	
						tc.setNombreArchivo(split[0] + "." + split[1].toLowerCase());
					}
					else tc.setNombreTrabajo(f.getNombre() + "." + split[1].toLowerCase());
					
					tc.setTrabajoDeCampo(f.getFichero().getFileData());
					
					getTrabajosDeCampoService().updateTrabajoCampo(tc);
				}
				else return mapping.findForward("error");
			}
				
			return mapping.findForward("success");
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
	

}
