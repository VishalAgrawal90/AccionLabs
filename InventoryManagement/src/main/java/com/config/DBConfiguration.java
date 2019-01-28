package com.config;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import constants.Constant;

/**
 * @author vishal
 *
 */
@Configuration
public class DBConfiguration {
	Logger logger = LogManager.getLogger(DBConfiguration.class);
	/**
	 * @return H2 db datasource
	 *
	 */
	@Bean
	public DataSource dataSource() {
		logger.info("Initializing db configuration....");
		return new EmbeddedDatabaseBuilder()
				.generateUniqueName(false)
				.setName(Constant.dbName)
				.setType(EmbeddedDatabaseType.H2)
				.setScriptEncoding(Constant.scriptEncoding)
				.addScript(Constant.schemaSQLLocation)
				.addScript(Constant.insertSQLLocation)
				.build();
	}

	/**
	 * @creating object of DataSourceTransactionManager
	 *
	 */
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		DataSourceTransactionManager  manager =  new DataSourceTransactionManager();
		manager.setDataSource(dataSource);
		return manager;
	}

	/**
	 * @creating object of JdbcTemplate
	 *
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}
}
