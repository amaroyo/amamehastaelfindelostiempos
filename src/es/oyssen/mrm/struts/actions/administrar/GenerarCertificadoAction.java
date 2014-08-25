package es.oyssen.mrm.struts.actions.administrar;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.AsignaturaVO;
import es.oyssen.mrm.negocio.vo.EstanciaUnidadClinicaVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.PuntuacionCriterioVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.administrar.GridAlumnosAptosCertificadoForm;

public class GenerarCertificadoAction extends MrmAction {

	private static PDDocument pdfDocument;
	
	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	        
			String idPortafolios = (String) request.getParameter("idPortafolios");
			String dni = (String) request.getParameter("dni");
			String nombre = (String) request.getParameter("nombre");
			String apellidos = (String) request.getParameter("apellidos");
			apellidos = apellidos.replaceAll(",", "");
			String alumno = nombre + " " + apellidos;
			
		
			InputStream originalPdf = this.getClass().getClassLoader().getResourceAsStream("/plantillaCertificado.pdf");
			

		    
		   // String originalPdf = "/Users/Aleks/Desktop/SI/workspace/mysales/bbdd/plantillaCertificado.pdf";
	       // String targetPdf = "/Users/Aleks/Desktop/certificado_"+dni+".pdf";
	        
	        try {
	            populateAndCopy(originalPdf, alumno, dni, idPortafolios, response);
	        } catch (IOException e){
	        	e.printStackTrace();
	        }
	    	catch(COSVisitorException e) {
	            e.printStackTrace();
	        }
	        
	        return mapping.findForward("success");
	    }
	 
	    private void populateAndCopy(InputStream originalPdf, String alumno, String dni, String idPortafolios,HttpServletResponse response) throws IOException, COSVisitorException {
	        pdfDocument = PDDocument.load(originalPdf);
	        
	        //System.out.println("Number of pages: "+pdfDocument.getNumberOfPages());
	        //printFields();
	        
	        setField("alumno", alumno);
	        setField("dni", dni);
	        
	        int anyo_primero = 9999;
	        int anyo_ultimo = 0;
	        
	        int contador801148 = 1;
	        int contador801149 = 1;
	        int contador801150 = 1;
	        int contador801151 = 1;
	        int contador801152 = 1;
	        int contador801153 = 1;
	        
	        String[] portafolios = idPortafolios.split("-");
	        
	        if (portafolios.length>0){
		        for (String id : portafolios) {
	
		        	PortafolioVO p = new PortafolioVO();
					p.setIdPortafolio(id);
					try {
						p=getPortafoliosService().findById(p);
					} catch (ServiceException e) {
						p = new PortafolioVO("","","","");
					}
		        	
		        	String anyoAcademico = p.getAnyoAcademico();
		        	String[] ann = anyoAcademico.split("/");
		        	int izq = Integer.parseInt(ann[0]);
		        	int der = Integer.parseInt(ann[1]);
		        	if(izq < anyo_primero) anyo_primero = izq;
		        	if(der > anyo_ultimo) anyo_ultimo = der;
					
		        	AsignaturaVO a = new AsignaturaVO();
		        	a.setIdAsignatura(p.getIdAsignatura());
		        	try {
						a = getAsignaturasService().findById(a);
					} catch (ServiceException e) {
						a = new AsignaturaVO("","","");
						
					}
		        	
		        	int cual = Integer.parseInt(a.getCodigo());
		        	
		        	EstanciaUnidadClinicaVO e = new EstanciaUnidadClinicaVO();
		        	e.setIdPortafolio(id);
		        	List<EstanciaUnidadClinicaVO> list = null;
		        	try {
						list = getEstanciasUnidadClinicaService().findAllByPortafolio(e);
					} catch (ServiceException e1) {
						list = null;
					}
		        			        	
		        	if(!list.isEmpty()){
			        	switch (cual){
							case 801148: {					        	
								setField("801148_curso", anyoAcademico);
								for (EstanciaUnidadClinicaVO est : list) {
									if(contador801148<=4){
								        setField("801148_institucion"+contador801148, est.getCentroAsociado());
								        setField("801148_servicio"+contador801148, est.getUnidadClinica());
								        setField("801148_horas"+contador801148, calcularHoras(est.getFechaInicio(),est.getFechaFin()));	
								        contador801148++;
									}
								}
								break;
								
							}
							case 801149: {				        	
								setField("801149_curso", anyoAcademico);
								for (EstanciaUnidadClinicaVO est : list) {
									if(contador801149<=4){
								        setField("801149_institucion"+contador801149, est.getCentroAsociado());
								        setField("801149_servicio"+contador801149, est.getUnidadClinica());
								        setField("801149_horas"+contador801149, calcularHoras(est.getFechaInicio(),est.getFechaFin()));	
								        contador801149++;
									}
								}
								break;
								
							}
							case 801150: {					        	
								setField("801150_curso", anyoAcademico);
								for (EstanciaUnidadClinicaVO est : list) {
									if(contador801150<=4){
								        setField("801150_institucion"+contador801150, est.getCentroAsociado());
								        setField("801150_servicio"+contador801150, est.getUnidadClinica());
								        setField("801150_horas"+contador801150, calcularHoras(est.getFechaInicio(),est.getFechaFin()));	
								        contador801150++;
									}
								}
								break;
								
							}
							case 801151: {					        	
								setField("801151_curso", anyoAcademico);
								for (EstanciaUnidadClinicaVO est : list) {
									if(contador801151<=4){
								        setField("801151_institucion"+contador801151, est.getCentroAsociado());
								        setField("801151_servicio"+contador801151, est.getUnidadClinica());
								        setField("801151_horas"+contador801151, calcularHoras(est.getFechaInicio(),est.getFechaFin()));	
								        contador801151++;
									}
								}
								break;
								
							}
							case 801152: {					        	
								setField("801152_curso", anyoAcademico);
								for (EstanciaUnidadClinicaVO est : list) {
									if(contador801152<=4){
								        setField("801152_institucion"+contador801152, est.getCentroAsociado());
								        setField("801152_servicio"+contador801152, est.getUnidadClinica());
								        setField("801152_horas"+contador801152, calcularHoras(est.getFechaInicio(),est.getFechaFin()));	
								        contador801152++;
									}
								}
								break;
								
							}
							case 801153: {					        	
								setField("801153_curso", anyoAcademico);
								for (EstanciaUnidadClinicaVO est : list) {
									if(contador801153<=4){
								        setField("801153_institucion"+contador801153, est.getCentroAsociado());
								        setField("801153_servicio"+contador801153, est.getUnidadClinica());
								        setField("801153_horas"+contador801153, calcularHoras(est.getFechaInicio(),est.getFechaFin()));	
								        contador801153++;
									}
								}
								break;
								
							}
							default: {break;}
			        	}
		        	
		        	}	
		        	
		        	
		        }
	        }
	        
	        

	        
	        setField("desde", Integer.toString(anyo_primero));
	        setField("hasta", Integer.toString(anyo_ultimo));
	        
	        
	        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			String[] f = dateFormat.format(date).split("/");
	        
	        setField("dia", f[2]);
	        setField("mes", parsearMes(f[1]));
	        setField("anyo", f[0]);
	        
	        
	        
	        
	        
	        String filename="Certificado_"+dni+".pdf";
	        
	        
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
			
			ServletOutputStream outputStream = response.getOutputStream();
			response.setContentType("application/pdf");
			pdfDocument.save(outputStream);
			//response.setContentLength(len);
			//outputStream.write(); 
			
			
			outputStream.flush();
			pdfDocument.close();
			outputStream.close();
	        
	    }
	    
	    
	    private String calcularHoras(String fInicio, String fFin) {
	    	
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	    	int dias = 0;
	    	String horas="";
	    	
			Date fechaIni = null;
			Date fechaFin = null;
			try {
				fechaIni = dateFormat.parse(fInicio);
				fechaFin = dateFormat.parse(fFin);
			} catch (ParseException e) {
				
			}
			long diff = fechaFin.getTime() - fechaIni.getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);
			
			int semanas = (int) (diffDays / 7);
			dias = (int) diffDays;
			
			dias = dias - 2*semanas;
			
			horas = Integer.toString(dias*7);
			return horas;
			 
		}

		private static String parsearMes(String m) {
			int mes = Integer.parseInt(m);
			
			switch (mes){
				case 1: return "Enero";
				case 2: return "Febrero";
				case 3: return "Marzo";
				case 4: return "Abril";
				case 5: return "Mayo";
				case 6: return "Junio";
				case 7: return "Julio";
				case 8: return "Agosto";
				case 9: return "Septiembre";
				case 10: return "Octubre";
				case 11: return "Noviembre";
				case 12: return "Diciembre";
				default: return "Indefinido";
			}
		}

		public static void setField(String name, String value ) throws IOException {
	        PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
	        PDAcroForm acroForm = docCatalog.getAcroForm();
	        PDField field = acroForm.getField( name );
	        if( field != null ) {
	            field.setValue(value);
	        }
	        else {
	            System.err.println( "No field found with name:" + name );
	        }
	    }
	 
	    @SuppressWarnings("rawtypes")
	    public static void printFields() throws IOException {
	        PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
	        PDAcroForm acroForm = docCatalog.getAcroForm();
	        List fields = acroForm.getFields();
	        Iterator fieldsIter = fields.iterator();
	 
	        System.out.println(new Integer(fields.size()).toString() + " top-level fields were found on the form");
	 
	        while( fieldsIter.hasNext()) {
	            PDField field = (PDField)fieldsIter.next();
	               processField(field, "|--", field.getPartialName());
	        }
	    }
	    
	    @SuppressWarnings("rawtypes")
	    private static void processField(PDField field, String sLevel, String sParent) throws IOException
	    {
	        List kids = field.getKids();
	        if(kids != null) {
	            Iterator kidsIter = kids.iterator();
	            if(!sParent.equals(field.getPartialName())) {
	               sParent = sParent + "." + field.getPartialName();
	            }
	            
	            System.out.println(sLevel + sParent);
	            
	            while(kidsIter.hasNext()) {
	               Object pdfObj = kidsIter.next();
	               if(pdfObj instanceof PDField) {
	                   PDField kid = (PDField)pdfObj;
	                   processField(kid, "|  " + sLevel, sParent);
	               }
	            }
	         }
	         else {
	             String outputString = sLevel + sParent + "." + field.getPartialName() + ",  type=" + field.getClass().getName();
	             System.out.println(outputString);
	         }
	    }
}
