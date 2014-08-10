package es.oyssen.mrm.negocio.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAODiariosReflexivos;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.DiariosReflexivosService;
import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.negocio.vo.DiarioReflexivoVO;
import es.oyssen.mrm.struts.forms.asignaturas.SubirArchivoForm;

public class DiariosReflexivosServiceImpl implements DiariosReflexivosService{
	
	private static Log log = LogFactory.getLog(DiariosReflexivosServiceImpl.class);
	private DAODiariosReflexivos daoDiariosReflexivos;

	
	public void setDaoDiariosReflexivos(DAODiariosReflexivos daoDiariosReflexivos) {
		this.daoDiariosReflexivos = daoDiariosReflexivos;
	}
	
	public void insert(DiarioReflexivoVO diarioReflexivo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoDiariosReflexivos.insert(diarioReflexivo);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando diario reflexivo", e);
			throw new ServiceException(e);
		}		
	}

	public void update(DiarioReflexivoVO diarioReflexivo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoDiariosReflexivos.update(diarioReflexivo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update diario reflexivo", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(DiarioReflexivoVO diarioReflexivo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoDiariosReflexivos.delete(diarioReflexivo);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete diario reflexivo", e);
			throw new ServiceException(e);
		}			
	}

	
	
	public List<DiarioReflexivoVO> findAllByPortafolio(DiarioReflexivoVO diarioReflexivo) throws ServiceException {
		try {
			return daoDiariosReflexivos.findAllByPortafolio(diarioReflexivo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllByPortafolio diario reflexivo", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void process(SubirArchivoForm f) throws ServiceException {
		try {
			log.debug("Procesamos fichero.........");
			
			DiarioReflexivoVO diario = new DiarioReflexivoVO();
			
			String n = f.getFichero().getFileName();
			String[] sp = n.split("\\.");
			
			if(f.getNombre().equals("")){	
				diario.setNombre(sp[0] + "." + sp[1].toLowerCase());
			}
			else diario.setNombre(f.getNombre() + "." + sp[1].toLowerCase());
			diario.setDiarioReflexivo(f.getFichero().getFileData());
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			//get current date time with Date()
			Date date = new Date();
			diario.setFechaSubida(dateFormat.format(date));
			diario.setIdPortafolio(f.getIdPortafolio());
			daoDiariosReflexivos.insert(diario);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error procesando fichero SubirArchivoFormDIarioReflexivo", e);
			throw new ServiceException(e);
		}
		
	}
	
	
	
}
