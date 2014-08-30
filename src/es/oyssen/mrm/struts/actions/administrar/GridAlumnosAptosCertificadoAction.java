package es.oyssen.mrm.struts.actions.administrar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.PuntuacionCriterioVO;
import es.oyssen.mrm.negocio.vo.RubricaVO;
import es.oyssen.mrm.negocio.vo.UsuarioPortafolioVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;


public class GridAlumnosAptosCertificadoAction extends DHTMLXGridAction {
	
	private HashMap<Integer, Pares> notasAlumnos;
	
	public class Pares {
		private UsuarioVO usuario;
		private List<Integer> portafolios;
		
		public Pares(){
			
		}
		
		public UsuarioVO getUsuario() {
			return usuario;
		}
		public void setUsuario(UsuarioVO usuario) {
			this.usuario = usuario;
		}
		public List<Integer> getPortafolios() {
			return portafolios;
		}
		public void setPortafolios(List<Integer> portafolios) {
			this.portafolios = portafolios;
		}
		
		
	}
	
	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		
		//GridAlumnosAptosCertificadoForm form = (GridAlumnosAptosCertificadoForm) f;
		notasAlumnos =  new HashMap<Integer,Pares>();
		List<UsuarioPortafolioVO> idAlumnosPotenciales = getPortafoliosService().findAlumnosPotencialesCertificado();
		
		if(!idAlumnosPotenciales.isEmpty()){
			for (UsuarioPortafolioVO u : idAlumnosPotenciales) {				
			
				PortafolioVO p = new PortafolioVO();
				p.setIdPortafolio(u.getIdPortafolio());
				p=getPortafoliosService().findById(p);		
				
				RubricaVO r = new RubricaVO();
				r.setIdAsignatura(p.getIdAsignatura());
				r = getRubricasService().findById(r);
				
				int dividendo = Integer.parseInt(r.getNumeroCriterios());
				
				List<PuntuacionCriterioVO> notas = getPuntuacionCriteriosService().findAllNotasByPortafolio(p);
				
				
				if (!notas.isEmpty()){
					if(dividendo==notas.size()){
						int sum=0;
						for (PuntuacionCriterioVO pcr : notas) {
							try {
								sum += Integer.parseInt(pcr.getNota());
							} catch (Exception e) {
								sum+=0;
							}
						}
						sum=sum*2;
						double notaMedia = (double)sum/(double)dividendo;
						if(notaMedia >= 5.0){
							if(notasAlumnos.containsKey(Integer.parseInt(u.getIdUsuario()))){						
								notasAlumnos.get(Integer.parseInt(u.getIdUsuario())).getPortafolios().add(Integer.parseInt(p.getIdPortafolio()));
							}
							else{
								List<Integer> l = new ArrayList<Integer>();
								l.add(Integer.parseInt(p.getIdPortafolio()));
								
								UsuarioVO us = new UsuarioVO();
								us.setIdUsuario(u.getIdUsuario());
								us.setNombre(u.getNombre());
								us.setApellido1(u.getApellido1());
								us.setApellido2(u.getApellido2());
								us.setDni(u.getDni());
								
								Pares par = new Pares();
								par.setUsuario(us);
								par.setPortafolios(l);
								
								notasAlumnos.put(Integer.parseInt(u.getIdUsuario()), par);
							}
						}
					}
				}
			
			}
			
		}
		
		List<Pares> aprobados = new ArrayList<Pares>();
 		
		Iterator<Integer> it = notasAlumnos.keySet().iterator();
		while (it.hasNext()) {
			Integer entry = (Integer) it.next();
			if(notasAlumnos.get(entry).getPortafolios().size() == 7){
				aprobados.add(notasAlumnos.get(entry));
			}
		}
		
		
		return UtilXML.buildXmlAlumnosCertificados(aprobados);
		
	}


	@Override
	public void insert(DhtmlxGridForm f) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(DhtmlxGridForm f) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(DhtmlxGridForm f) throws Exception {
		
	}

}
