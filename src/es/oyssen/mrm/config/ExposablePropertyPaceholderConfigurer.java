package es.oyssen.mrm.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class ExposablePropertyPaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private Map resolvedProps;
	
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		resolvedProps = new HashMap();
		for (Iterator it = props.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			resolvedProps.put(key, parseStringValue(props.getProperty(key), props, new HashSet()));
		}
	}
	
	public Map getResolvedProps() {
		return resolvedProps;
	}
}
