package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@ComponentScan("controllers")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer{

	 private final ApplicationContext applicationContext;

	    @Autowired
	    public SpringConfig(ApplicationContext applicationContext) {
	        this.applicationContext = applicationContext;
	        
	    }

	    
		@Bean
	    public SpringResourceTemplateResolver templateResolver() {
	        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	        
	        templateResolver.setApplicationContext(applicationContext);
	        templateResolver.setPrefix("/WEB-INF/views/");
	        templateResolver.setSuffix(".html");
	        templateResolver.setTemplateMode(TemplateMode.HTML);
	        templateResolver.setCharacterEncoding("UTF-8");
	        return templateResolver;
	    }

	    @Bean
	    public SpringTemplateEngine templateEngine() {
	        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	        templateEngine.setTemplateResolver(templateResolver());
	        
	        templateEngine.setEnableSpringELCompiler(true);
	        
	        return templateEngine;
	    }

	    @Override
	    public void configureViewResolvers(ViewResolverRegistry registry) {
	        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	        resolver.setCharacterEncoding("UTF-8");
	       
	        resolver.setTemplateEngine(templateEngine());
	     
	        registry.viewResolver(resolver);
	        
	    }
	    /*
	    @Bean
	    public DataSource dataSource() {
	    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    	dataSource.setDriverClassName("org.postgresql.Driver");
	    	dataSource.setUrl("jdbc:postgresql://localhost:5432/soul_beer");
	    	dataSource.setUsername("postgres");
	    	dataSource.setPassword("13121994");
	    	
	    	return dataSource;
	    }
	    
	    @Bean
	    public JdbcTemplate jdbcTemplate() {
	    	return new JdbcTemplate(dataSource());
	    }
	    */
	    
	    
}
