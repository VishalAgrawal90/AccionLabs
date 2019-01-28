package com.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author vishal
 *
 */
@Configuration
@ComponentScan(basePackages = "com.*")
@EnableTransactionManagement(proxyTargetClass = true)
public class ApplicationConfig {

	@Bean(name = "InventoryQueries")
	public PropertiesFactoryBean mapper() {
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocation(new ClassPathResource("db/sql/inventory.xml"));
		return bean;
	}
}
