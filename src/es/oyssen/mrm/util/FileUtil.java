package es.oyssen.mrm.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtil {
	
	static Log log = LogFactory.getLog(FileUtil.class);
	
	public static void createFile(String fileName, byte[] datos) throws FileNotFoundException, IOException {
		createFile(new File(fileName), datos);
	}
	
	public static void createFile(File file, byte[] datos) throws FileNotFoundException, IOException {
		FileOutputStream fos = null;
		ByteArrayInputStream bais = new ByteArrayInputStream(datos);
		try {
			fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int leidos = 0;
			while ((leidos = bais.read(buffer)) != -1) {
				fos.write(buffer, 0, leidos);
			}			
		} catch (FileNotFoundException e) {
			log.error(e);
			throw e;
		} catch (IOException e) {
			log.error(e);
			throw e;
		} finally {
			IOUtil.closeOutputStream(fos);
			IOUtil.closeInputStream(bais);
		}
	}		
	
	
	public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
    
        // Get the size of the file
        long length = file.length();
    
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
    
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
    
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
    
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
	
	public static String readFileAsString(String filePath)
    throws java.io.IOException{
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }



}
