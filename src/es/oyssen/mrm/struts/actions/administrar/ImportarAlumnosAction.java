package es.oyssen.mrm.struts.actions.administrar;

import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.ficheros.FileUploadForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import java.io.File;
import java.io.FileOutputStream;
 

 

public class ImportarAlumnosAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FileUploadForm fileUploadForm = (FileUploadForm)form;
		 
	    FormFile file = fileUploadForm.getFile();
	    
	    System.out.println("caca");
 
	    //Get the servers upload directory real path name
	    String filePath = 
               getServlet().getServletContext().getRealPath("/") +"upload";
 
	    //create the upload folder if not exists
	    File folder = new File(filePath);
	    if(!folder.exists()){
	    	folder.mkdir();
	    }
 
	    String fileName = file.getFileName();
 
	    if(!("").equals(fileName)){  
 
	        System.out.println("Server path:" +filePath);
	        File newFile = new File(filePath, fileName);
 
	        if(!newFile.exists()){
	          FileOutputStream fos = new FileOutputStream(newFile);
	          fos.write(file.getFileData());
	          fos.flush();
	          fos.close();
	        }  
 
	        request.setAttribute("uploadedFilePath",newFile.getAbsoluteFile());
	        request.setAttribute("uploadedFileName",newFile.getName());
	    }
	    
	    /*SubirFicheroForm f = (SubirFicheroForm) form;
		System.out.println(form.toString());
		getFicherosLeadService().process(f);*/
		return mapping.findForward("success");
	}
		

}