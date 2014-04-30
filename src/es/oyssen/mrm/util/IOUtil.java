/*
 * IOUtil.java
 *
 * Created on 19 de noviembre de 2007
 * Copyright (c) 2007 Iván Rodríguez. All rights reserved
 */

package es.oyssen.mrm.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Clase de utilidad para ficheros.
 *
 * @version     1.0
 * @author 	m2da
 */
public final class IOUtil {
	
	static Log log = LogFactory.getLog(IOUtil.class);
    
    /** Constructor privada para evitar varias instancias de la clase. */
    private IOUtil() {
    }
    
    /**
     * Lee un flujo de entrada y almacena su contenido en un array de bytes.
     * @param input Flujo de entrada a leer.
     * @return Un array de bytes con el contenido del flujo de entrada.
     */
    public static byte[] readInputStream(InputStream input) {
        BufferedInputStream bufin = new BufferedInputStream(input);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int bytesRead = -1;
            
            while ((bytesRead = bufin.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            return null;
        } finally {
            try {
                bufin.close();
                baos.close();
            } catch (IOException ignored) {}
        }
    }
    
    
    public static void closeInputStream(InputStream is) {
    	try {
			if (is != null) is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void closeReader(Reader reader) {
    	try {
			if (reader != null) reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void closeOutputStream(OutputStream os) {
    	try {
			if (os != null) os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void closeWriter(Writer writer) {
    	try {
			if (writer != null) writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
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
			closeOutputStream(fos);
			closeInputStream(bais);
		}
	}		    
	
	public static void write(byte[] data, OutputStream os) throws IOException {
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(data);
			copy(bais, os);		
		} catch (IOException e) {
			log.error("Error escribiendo datos", e);
		} finally {
			closeInputStream(bais);
		}
	}
	
	public static void write(File file, OutputStream os) throws IOException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			copy(fis, os);
		} catch (IOException e) {
			log.error("Error copiando fichero al buffer", e);
			throw e;
		} finally {
			closeInputStream(fis);
		}
	}

	public static void copy(InputStream is, OutputStream os) throws IOException {
		try {
			byte[] buffer = new byte[1024];
			int leidos = 0;
			while ((leidos = is.read(buffer)) != -1) {
				os.write(buffer, 0, leidos);
			}	
		} catch (IOException e) {
			log.error("Error copiando fichero al buffer", e);
			throw e;
		}
	}

}
