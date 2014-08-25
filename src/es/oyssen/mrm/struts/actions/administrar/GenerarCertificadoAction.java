package es.oyssen.mrm.struts.actions.administrar;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
			apellidos = apellidos.replaceAll("", "");
			String alumno = nombre + " " + apellidos;
			
		
			InputStream originalPdf = this.getClass().getClassLoader().getResourceAsStream("/plantillaCertificado.pdf");
			

		    
		   // String originalPdf = "/Users/Aleks/Desktop/SI/workspace/mysales/bbdd/plantillaCertificado.pdf";
	        String targetPdf = "/Users/Aleks/Desktop/certificado_"+dni+".pdf";
	        
	        try {
	            populateAndCopy(originalPdf, targetPdf, alumno, dni, idPortafolios);
	        } catch (IOException e){
	        	e.printStackTrace();
	        }
	    	catch(COSVisitorException e) {
	            e.printStackTrace();
	        }
	        
	        //System.out.println("Complete");
	        return null;
	    }
	 
	    private static void populateAndCopy(InputStream originalPdf, String targetPdf, String alumno, String dni, String idPortafolios) throws IOException, COSVisitorException {
	        pdfDocument = PDDocument.load(originalPdf);
	        
	        //System.out.println("Number of pages: "+pdfDocument.getNumberOfPages());
	        //printFields();
	        
	        setField("alumno", alumno);
	        setField("dni", dni);
	        
	        
	        setField("801148_curso", "2006/2007");
	        setField("801148_institucion1", "HUGM Hospital Gregorio Marañon");
	        setField("801148_servicio1", "Urgencias");
	        setField("801148_horas1", "462");
	        
	        setField("801149_curso", "2007/2008");
	        setField("801149_institucion1", "HUGM Hospital Gregorio Marñon");
	        setField("801149_servicio1", "Maternidad");
	        setField("801149_horas1", "462");
	        
	        setField("801150_curso", "2008/2009");
	        setField("801150_institucion1", "HUIL Hospital Infanta Leonor");
	        setField("801150_servicio1", "Digestivo");
	        setField("801150_horas1", "400");
	        
	        setField("801151_curso", "2009/2010");
	        setField("801151_institucion1", "HULP Hospital La Paz");
	        setField("801151_servicio1", "Urgencias");
	        setField("801151_horas1", "385");
	        setField("801151_institucion2", "12 de Octubre");
	        setField("801151_servicio2", "Dermatologia");
	        setField("801151_horas2", "200");
	        
	        setField("801152_curso", "2010/2011");
	        setField("801152_institucion1", "HUGM Hospital Gregorio Marañon");
	        setField("801152_servicio1", "Urgencias");
	        setField("801152_horas1", "300");
	        
	        setField("801153_curso", "2010/2011");
	        setField("801153_institucion1", "HUGM Hospital Gregorio Marañon");
	        setField("801153_servicio1", "Urgencias");
	        setField("801153_horas1", "462");

	        
	        setField("desde", "2006");
	        setField("hasta", "2014");
	        
	        
	        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			String[] f = dateFormat.format(date).split("/");
	        
	        setField("dia", f[2]);
	        setField("mes", parsearMes(f[1]));
	        setField("anyo", f[0]);
	        
	        
	        
	        pdfDocument.save(targetPdf);
	        pdfDocument.close();
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
