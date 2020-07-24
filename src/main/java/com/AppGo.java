package com;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * 
 * @ClassName: AppGo
 * @Description:WEB启动，主程序入口
 * @author: 刘绍全
 * @date: 2018年10月18日 下午1:03:28
 * @Copyright: 2018 www.cyunsoft.com Inc. All rights reserved.
 * 注意：本内容仅限于江苏稠云信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@SpringBootApplication
@EnableTransactionManagement // 开启事务管理
@EnableAsync(proxyTargetClass = true)
public class AppGo extends SpringBootServletInitializer{
	public static void main(String[] args) {
		try
		{
		SpringApplication.run(AppGo.class, args);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		return builder.sources(AppGo.class);
	}
	
	/**
	 * 
	 * @Title: generalTM
	 * @author:刘绍全
	 * @Description: Durid 数据源事务管理定义
	 * @param:  dataSource
	 * @param: @return
	 * @return: PlatformTransactionManager
	 * 
	 */
	@Bean(name = "generalTM") // 给事务管理器命名
	public PlatformTransactionManager generalTM(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * 一般事务只管在两个表或两个表以前更新时需要 在@Service类的方法上添加 @Transactional(value="事务名") 例如：
	 * 
	 * @Transactional(value="generalTM") 
	 * @Override public Integer
	 * addWorkFlowType(WorkFlowType workFlowType)
	 * {
	 * return workFlowTypeMapper.addWorkFlowType(workFlowType);
	 *  }
	 * 
	 * 
	 * 
	 */

}
