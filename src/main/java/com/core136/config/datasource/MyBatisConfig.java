package com.core136.config.datasource;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
/**
 * 
 * <p>	Title: MyBatisConfig </p>
 * <p>	Description: 配置Mybatis配置 </p>
 * @author	曹凯
 * @date	2020年7月31日下午2:12:35
 * @version 1.0
 */
@Configuration
public class MyBatisConfig {

	@Resource(name = "myRoutingDataSource")
	private DataSource myRoutingDataSource;
	
	

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(myRoutingDataSource);
//		mybatis.mapper-locations=classpath*:mapper/**/*Mapper.xml
//		mybatis.type-aliases-package=com.core136.bean.*
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**/*Mapper.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.core136.bean.*");
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager platformTransactionManager() {
		return new DataSourceTransactionManager(myRoutingDataSource);
	}
}