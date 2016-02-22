package com.springmvcsampler.config;

import com.springmvcsampler.Application;
import com.springmvcsampler.registry.registration.annotation.ServiceRegistration;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;

import static org.springframework.context.annotation.ComponentScan.Filter;

@ServiceRegistration(serviceName = "MyService")
@Configuration
@ComponentScan(basePackageClasses = Application.class, excludeFilters = @Filter({Controller.class, Configuration.class}))
@EnableJpaRepositories("com.springmvcsampler.repository")
class ApplicationConfig {
	
	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setLocation(new ClassPathResource("/persistence.properties"));
		return ppc;
	}

}