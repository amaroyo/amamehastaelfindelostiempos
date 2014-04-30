package es.oyssen.mrm.listeners;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.oyssen.mrm.config.ExposablePropertyPaceholderConfigurer;
import es.oyssen.mrm.negocio.dao.DAOMarketingActivities;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.vo.MarketingActivityVO;


public class MrmContextListener implements ServletContextListener {
	
	static Log log = LogFactory.getLog(MrmContextListener.class);

	public void contextDestroyed(ServletContextEvent ctx) {
		log.debug("Destruyendo contexto de mrm.....");
	}

	public void contextInitialized(ServletContextEvent ctx) {
		log.debug("Inicializando contexto de mrm.....");
		ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx.getServletContext());
		
		// Exponemos las propiedades definidas en el archivo de configuracion en el contexto de la aplicacion
		ExposablePropertyPaceholderConfigurer configurer = (ExposablePropertyPaceholderConfigurer) appContext.getBean("propertyConfigurer");
		ctx.getServletContext().setAttribute("configProperties", configurer.getResolvedProps());
		
		DAOMarketingActivities daoMarketing = (DAOMarketingActivities) appContext.getBean("daoMarketingActivities");
		try {
			List<MarketingActivityVO> marketingActivities = daoMarketing.findAll();
			ctx.getServletContext().setAttribute("marketing_activities", marketingActivities);
		} catch (DAOException e) {
			e.printStackTrace();
		}		
		
		log.debug("Contexto inicializado: mrm");		
	}

}