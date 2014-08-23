package es.oyssen.mrm.struts.actions.administrar;

import java.io.IOException;
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

public class GenerarCertificadoAction extends MrmAction {

	private static PDDocument pdfDocument;
	
	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	        
	        String originalPdf = "C:\\Users\\pecas\\Desktop\\PROYECTO\\workspace\\mysales\\bbdd\\plantillaCertificado.pdf";
	        String targetPdf = "C:\\Users\\pecas\\Desktop\\PROYECTO\\workspace\\mysales\\bbdd\\certificado_"+"02286631V"+".pdf";
	        
	        try {
	            populateAndCopy(originalPdf, targetPdf);
	        } catch (IOException e){
	        	e.printStackTrace();
	        }
	    	catch(COSVisitorException e) {
	            e.printStackTrace();
	        }
	        
	        System.out.println("Complete");
	        return null;
	    }
	 
	    private static void populateAndCopy(String originalPdf, String targetPdf) throws IOException, COSVisitorException {
	        pdfDocument = PDDocument.load(originalPdf);
	        
	        //System.out.println("Number of pages: "+pdfDocument.getNumberOfPages());
	        printFields();
	        
	        setField("alumno", "Raquel Álvarez Ramírez");
	        setField("dni", "02286631V");
	        setField("desde", "2006");
	        setField("hasta", "2014");
	        
	        setField("801148_curso", "2006/2007");
	        setField("801148_institucion1", "HUGM Hospital Gregorio Marañon");
	        setField("801148_servicio1", "Urgencias");
	        setField("801148_horas1", "462");
	        
	        setField("801149_curso", "2007/2008");
	        setField("801149_institucion1", "HUGM Hospital Gregorio Marañon");
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
	        setField("801151_servicio2", "Dermatología");
	        setField("801151_horas2", "200");
	        
	        setField("801152_curso", "2010/2011");
	        setField("801152_institucion1", "HUGM Hospital Gregorio Marañon");
	        setField("801152_servicio1", "Urgencias");
	        setField("801152_horas1", "300");
	        
	        setField("801153_curso", "2010/2011");
	        setField("801153_institucion1", "HUGM Hospital Gregorio Marañon");
	        setField("801153_servicio1", "Urgencias");
	        setField("801153_horas1", "462");

	        setField("dia", "3");
	        setField("mes", "Julio");
	        setField("anyo", "2011");
	        
	        pdfDocument.save(targetPdf);
	        pdfDocument.close();
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
