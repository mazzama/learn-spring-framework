package com.mazzama.belajar.thymeleaf.config;

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

@Configuration
@EnableWebMvc
@ComponentScan("com.mazzama.belajar.thymeleaf.controller")
public class MvcWebConfig implements WebMvcConfigurer {
	
	@Autowired
	ApplicationContext applicationContext;
	
	/* Create Template Resolver */
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setApplicationContext(applicationContext);
	    templateResolver.setPrefix("/WEB-INF/views/");
	    templateResolver.setSuffix(".html");
	    return templateResolver;
	}
	
	/* Create Spring Template Engine */
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver());
	    templateEngine.setEnableSpringELCompiler(true);
	    return templateEngine;
	}
	
	/* Register ThymeleafViewResolver */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		registry.viewResolver(resolver);
	}

}
