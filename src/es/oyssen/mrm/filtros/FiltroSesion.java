package es.oyssen.mrm.filtros;

import java.io.*; 

import javax.servlet.*; 
import javax.servlet.http.*; 

public class FiltroSesion implements Filter { 
  
	FilterConfig fc; 
	
	public void doFilter (ServletRequest request, ServletResponse response,	FilterChain chain) {

		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		HttpSession session;
		if (request instanceof HttpServletRequest){
			session = ((HttpServletRequest)request).getSession();
			try{
				String path = ((HttpServletRequest) request).getRequestURI();
				if (session.getAttribute("usuarioYPermisos") != null || path.contains("login.do") || path.contains("bienvenida")){
					chain.doFilter(request, response);
				}else{
					((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/bienvenida/timeoutusuario.do");
				}
			}catch (IOException io){
				System.out.println("IOException raised");
			}catch (ServletException se){
				System.out.println("ServletException raised");
			}
		}
	}
  
	public void init(FilterConfig filterConfig) { 
	  this.fc = filterConfig; 
	} 
  
	public void destroy() {
	  this.fc = null; 
	}
  
}
