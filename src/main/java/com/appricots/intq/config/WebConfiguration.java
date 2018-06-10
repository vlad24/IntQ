package com.appricots.intq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.appricots.intq"})
public class WebConfiguration extends WebMvcConfigurerAdapter{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    // Dispatcher servlet will not be bothered with resource requests
		registry
				.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}


	@Bean
	public ViewResolver setupViewResolver() {
	    // When views are given away by controller the resolver determines the view
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		// Set JstlView class so that jstl views are supported
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
}
