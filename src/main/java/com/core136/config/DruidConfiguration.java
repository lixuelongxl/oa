//package com.core136.config;
//import java.sql.SQLException;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//
///**
// * 
// * @ClassName:  DruidConfiguration   
// * @Description:手动配置Druid 
// * @author: 刘绍全
// * @date:   2018年10月18日 下午1:32:36   
// *     
// * @Copyright: 2018 www.cyunsoft.com Inc. All rights reserved. 
// * 注意：本内容仅限于江苏稠云信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
// */
//@Configuration
//public class DruidConfiguration {
//	/**
//	 * 
//	 * @Title: registrationBean
//	 * @author:刘绍全
//	 * @Description: 添加Druid用户
//	 * @param: @return      
//	 * @return: ServletRegistrationBean      
//
//	 */
//	
//	 @Bean
//	    public ServletRegistrationBean<StatViewServlet> registrationBean() {
//			ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(), "/druid/*");
//	        /** 初始化参数配置，initParams**/
//	        //白名单
//	        bean.addInitParameter("allow", allowip);
//	        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
//	       // bean.addInitParameter("deny", "192.168.1.113");
//	        //登录查看信息的账号密码.
//	        bean.addInitParameter("loginUsername", user);
//	        bean.addInitParameter("loginPassword", passwd);
//	        //是否能够重置数据.
//	        bean.addInitParameter("resetEnable", "false");
//	        return bean;
//	    }
//	 
//	 @Bean
//	    public FilterRegistrationBean<WebStatFilter> druidStatFilter() {
//	        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<WebStatFilter>(new WebStatFilter());
//	        //添加过滤规则.
//	        bean.addUrlPatterns("/*");
//	        //添加不需要忽略的格式信息.
//	        bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//	        return bean;
//	    }
//	 @Value("${spring.durid.user}")
//		private String user;
//		
//		@Value("${spring.durid.passwd}")
//		private String passwd;
//		
//		@Value("${spring.durid.allowip}")
//		private String allowip;
//		
//	 @Value("${spring.datasource.master.url}")
//	 private String dbUrl;
//	 @Value("${spring.datasource.master.username}")
//	 private String username;
//	 @Value("${spring.datasource.master.password}")
//	 private String password;
//	 @Value("${spring.datasource.driver-class-name}")
//	 private String driverClassName;
//	 @Value("${spring.datasource.initialSize}")
//	 private int initialSize;
//	 @Value("${spring.datasource.minIdle}")
//	 private int minIdle;
//	 @Value("${spring.datasource.maxActive}")
//	 private int maxActive;
//	 @Value("${spring.datasource.maxWait}")
//	 private int maxWait;
//	 @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
//	 private int timeBetweenEvictionRunsMillis;
//	 @Value("${spring.datasource.minEvictableIdleTimeMillis}")
//	 private int minEvictableIdleTimeMillis;
//	 @Value("${spring.datasource.validationQuery}")
//	 private String validationQuery;
//	 @Value("${spring.datasource.testWhileIdle}")
//	 private boolean testWhileIdle;
//	 @Value("${spring.datasource.testOnBorrow}")
//	 private boolean testOnBorrow;
//	 @Value("${spring.datasource.testOnReturn}")
//	 private boolean testOnReturn;
//	 @Value("${spring.datasource.poolPreparedStatements}")
//	 private boolean poolPreparedStatements;
//	 @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
//	 private int maxPoolPreparedStatementPerConnectionSize;
//	 @Value("${spring.datasource.filters}")
//	 private String filters;
//	 @Value("${spring.datasource.connectionProperties}")
//	 private String connectionProperties;
//	 @Value("${spring.datasource.useGlobalDataSourceStat}")
//	 private boolean useGlobalDataSourceStat;
//
//	 @Bean     //声明其为Bean实例
//	 @Primary  //在同样的DataSource中，首先使用被标注的DataSource
//	 public DataSource dataSource(){
//	     DruidDataSource datasource = new DruidDataSource();
//	     datasource.setUrl(this.dbUrl);
//	     datasource.setUsername(username);
//	     datasource.setPassword(password);
//	     datasource.setDriverClassName(driverClassName);
//
//	     //configuration
//	     datasource.setInitialSize(initialSize);
//	     datasource.setMinIdle(minIdle);
//	     datasource.setMaxActive(maxActive);
//	     datasource.setMaxWait(maxWait);
//	     datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//	     datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//	     datasource.setValidationQuery(validationQuery);
//	     datasource.setTestWhileIdle(testWhileIdle);
//	     datasource.setTestOnBorrow(testOnBorrow);
//	     datasource.setTestOnReturn(testOnReturn);
//	     datasource.setPoolPreparedStatements(poolPreparedStatements);
//	     datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
//	     datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
//	     try {
//	         datasource.setFilters(filters);
//	     } catch (SQLException e) {
//	         System.err.println("druid configuration initialization filter: "+ e);
//	     }
//	     datasource.setConnectionProperties(connectionProperties);
//	     return datasource;
//	 }
//}
