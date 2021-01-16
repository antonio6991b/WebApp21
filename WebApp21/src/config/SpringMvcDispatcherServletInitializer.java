package config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcDispatcherServletInitializer 
	extends AbstractAnnotationConfigDispatcherServletInitializer  {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {SpringConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/"};
	}
	
	//Set charset UTF-8
	 @Override
	    protected Filter[] getServletFilters() {
	        CharacterEncodingFilter filter = new CharacterEncodingFilter();
	        filter.setEncoding("UTF-8");
	        filter.setForceEncoding(true);
	        return new Filter[] { filter };
	    }

}
