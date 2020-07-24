package com.core136.config;

import java.util.Properties;

import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.core136.unit.Init.listen.AuthInterceptor;
import com.github.pagehelper.PageHelper;
import com.zhuozhengsoft.pageoffice.poserver.AdminSeal;
import com.zhuozhengsoft.pageoffice.poserver.Server;

@Configuration
public class AppConfig implements WebMvcConfigurer{
	@Value("${pageOffice.seal.adminPwd}")
	private String sealPassword;
	@Value("${pageOffice.lic.path}")
	private String licPath;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
    }
    
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }
	
    /**
     * 
     * @Title: pageHelper   
     * @Description: TODO 分页助手生效   
     * @param: @return      
     * @return: PageHelper      

     */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("dialect","mysql");
        pageHelper.setProperties(p);
        return pageHelper;
    }
    
	 @Bean(name = "viewResolver")
	   public BeetlSpringViewResolver getBeetlSpringViewResolver(BeetlGroupUtilConfiguration beetlConfig) {
	       BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
	       beetlSpringViewResolver.setConfig(beetlConfig);
	       beetlSpringViewResolver.setContentType("textml;charset=UTF-8");
	       beetlSpringViewResolver.setSuffix(".html");
	       return beetlSpringViewResolver;
	   }
	 
	 /**
		 * 添加PageOffice的服务器端授权程序Servlet（必须）
		 * @return
		 */
		@Bean
	    public ServletRegistrationBean<Server> servletRegistrationBean() {
			com.zhuozhengsoft.pageoffice.poserver.Server poserver = new com.zhuozhengsoft.pageoffice.poserver.Server();
			poserver.setSysPath(licPath);//设置PageOffice注册成功后,license.lic文件存放的目录
			ServletRegistrationBean<Server> srb = new ServletRegistrationBean<Server>(poserver);
			srb.addUrlMappings("/poserver.zz");
			srb.addUrlMappings("/posetup.exe");
			srb.addUrlMappings("/pageoffice.js");
			srb.addUrlMappings("/jquery.min.js");
			srb.addUrlMappings("/pobstyle.css");
			srb.addUrlMappings("/sealsetup.exe");
	        return srb;// 
	    }
		
		/**
		 * 添加印章管理程序Servlet（可选）
		 * @return
		 */
		@Bean
	    public ServletRegistrationBean<AdminSeal> servletRegistrationBean2() {
			com.zhuozhengsoft.pageoffice.poserver.AdminSeal adminSeal = new com.zhuozhengsoft.pageoffice.poserver.AdminSeal();
			adminSeal.setAdminPassword(sealPassword);//设置印章管理员admin的登录密码
			adminSeal.setSysPath(licPath);//设置印章数据库文件poseal.db存放的目录
			ServletRegistrationBean<AdminSeal> srb = new ServletRegistrationBean<AdminSeal>(adminSeal);
			srb.addUrlMappings("/adminseal.zz");
			srb.addUrlMappings("/alimage.zz");
			srb.addUrlMappings("/loginseal.zz");
			srb.addUrlMappings("/sealimage.zz");
	        return srb;
	    }
}