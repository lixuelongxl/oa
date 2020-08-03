package com.core136.config.datasource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * <p>	Title: DataSourceConfig </p>
 * <p>	Description:数据库常规配置类  </p>
 * @author	曹凯
 * @date	2020年7月31日下午2:10:56
 * @version 1.0
 */
@Configuration
public class DataSourceConfig {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
	@Value("${spring.durid.user}")
	private String user;

	@Value("${spring.durid.passwd}")
	private String passwd;

	@Value("${spring.durid.allowip}")
	private String allowip;

	@Value("${spring.datasource.master.url}")
	private String dbUrl;
	@Value("${spring.datasource.master.username}")
	private String username;
	@Value("${spring.datasource.master.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.initialSize}")
	private int initialSize;
	@Value("${spring.datasource.minIdle}")
	private int minIdle;
	@Value("${spring.datasource.maxActive}")
	private int maxActive;
	@Value("${spring.datasource.maxWait}")
	private int maxWait;
	@Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;
	@Value("${spring.datasource.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;
	@Value("${spring.datasource.validationQuery}")
	private String validationQuery;
	@Value("${spring.datasource.testWhileIdle}")
	private boolean testWhileIdle;
	@Value("${spring.datasource.testOnBorrow}")
	private boolean testOnBorrow;
	@Value("${spring.datasource.testOnReturn}")
	private boolean testOnReturn;
	@Value("${spring.datasource.poolPreparedStatements}")
	private boolean poolPreparedStatements;
	@Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
	private int maxPoolPreparedStatementPerConnectionSize;
	@Value("${spring.datasource.filters}")
	private String filters;
	@Value("${spring.datasource.connectionProperties}")
	private String connectionProperties;
	@Value("${spring.datasource.useGlobalDataSourceStat}")
	private boolean useGlobalDataSourceStat;

	@Value("${spring.datasource.slave1.url}")
	private String slave1Url;

	@Value("${spring.datasource.slave1.username}")
	private String slave1Username;

	@Value("${spring.datasource.slave1.password}")
	private String slave1Password;

	@Value("${spring.datasource.slave2.url}")
	private String slave2Url;

	@Value("${spring.datasource.slave2.username}")
	private String slave2Username;

	@Value("${spring.datasource.slave2.password}")
	private String slave2Password;

	@Value("${spring.datasource.slave3.url}")
	private String slave3Url;

	@Value("${spring.datasource.slave3.username}")
	private String slave3Username;

	@Value("${spring.datasource.slave3.password}")
	private String slave3Password;

	@Bean("masterDataSource")
	// @Primary
	public DataSource masterDataSource() {
		DruidDataSource datasource = new DruidDataSource();

		datasource.setUrl(dbUrl);
		datasource.setUsername(username);
		datasource.setPassword(password); // 这里可以做加密处理
		datasource.setDriverClassName(driverClassName);

		// configuration
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		datasource.setPoolPreparedStatements(poolPreparedStatements);
		datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter", e);
		}
		datasource.setConnectionProperties(connectionProperties);

		return datasource;
	}

	@Bean("slave1DataSource")
	public DataSource slave1DataSource() {
		DruidDataSource datasource = new DruidDataSource();

		datasource.setUrl(slave1Url);
		datasource.setUsername(slave1Username);
		datasource.setPassword(slave1Password); // 这里可以做加密处理
		datasource.setDriverClassName(driverClassName);

		// configuration
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		datasource.setPoolPreparedStatements(poolPreparedStatements);
		datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
		datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter", e);
		}
		datasource.setConnectionProperties(connectionProperties);

		return datasource;
	}

	@Bean("slave2DataSource")
	public DataSource slave2DataSource() {
		DruidDataSource datasource = new DruidDataSource();

		datasource.setUrl(slave2Url);
		datasource.setUsername(slave2Username);
		datasource.setPassword(slave2Password); // 这里可以做加密处理
		datasource.setDriverClassName(driverClassName);

		// configuration
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		datasource.setPoolPreparedStatements(poolPreparedStatements);
		datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter", e);
		}
		datasource.setConnectionProperties(connectionProperties);

		return datasource;
	}

	@Bean("slave3DataSource")
	public DataSource slave3DataSource() {
		DruidDataSource datasource = new DruidDataSource();

		datasource.setUrl(slave3Url);
		datasource.setUsername(slave3Username);
		datasource.setPassword(slave3Password); // 这里可以做加密处理
		datasource.setDriverClassName(driverClassName);

		// configuration
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		datasource.setPoolPreparedStatements(poolPreparedStatements);
		datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter", e);
		}
		datasource.setConnectionProperties(connectionProperties);

		return datasource;
	}

	@Bean("myRoutingDataSource")
	@Primary
	public DataSource myRoutingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
			@Qualifier("slave1DataSource") DataSource slave1DataSource,
			@Qualifier("slave2DataSource") DataSource slave2DataSource,
			@Qualifier("slave3DataSource") DataSource slave3DataSource) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DBTypeEnum.MASTER, masterDataSource);
		targetDataSources.put(DBTypeEnum.SLAVE1, slave1DataSource);
		targetDataSources.put(DBTypeEnum.SLAVE2, slave2DataSource);
		targetDataSources.put(DBTypeEnum.SLAVE3, slave3DataSource);
		MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
		myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
		myRoutingDataSource.setTargetDataSources(targetDataSources);
		return myRoutingDataSource;
	}

	/*
	 * public DataSource dataSource(Environment env){ HikariDataSource ds = new
	 * HikariDataSource(); DruidDataSource datasource = new DruidDataSource();
	 * 
	 * ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
	 * ds.setUsername(env.getProperty("spring.datasource.username"));
	 * ds.setPassword(env.getProperty("spring.datasource.password"));
	 * ds.setDriverClassName(env.getProperty(
	 * "spring.datasource.driver-class-name"));
	 * 
	 * return ds; }
	 */

}
