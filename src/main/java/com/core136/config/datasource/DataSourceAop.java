package com.core136.config.datasource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>	Title: DataSourceAop </p>
 * <p>	Description: 切面入口类 </p>
 * @author	曹凯
 * @date	2020年7月31日下午2:10:03
 * @version 1.0
 */
@Aspect
@Component
public class DataSourceAop {
	@Pointcut("!@annotation(com.core136.config.datasource.Master) " +
            "&& ((execution(* com.core136.service..*.select*(..)) " +
            "|| execution(* com.core136.service..*.get*(..)))" 
            + "|| execution(* com.core136.service..*.query*(..)))" 
            + "|| execution(* com.core136.service..*.check*(..)))" 
			)
    public void readPointcut() {
		System.out.println("readPointcut");
    }

    @Pointcut("@annotation(com.core136.config.datasource.Master) " +
            "|| execution(* com.core136.service..*.save*(..)) " +
            "|| execution(* com.core136.service..*.insert*(..)) " +
            "|| execution(* com.core136.service..*.add*(..)) " +
            "|| execution(* com.core136.service..*.update*(..)) " +
            "|| execution(* com.core136.service..*.edit*(..)) " +
            "|| execution(* com.core136.service..*.delete*(..)) " +
            "|| execution(* com.core136.service..*.remove*(..))" 
    		)
    public void writePointcut() {
		System.out.println("writePointcut");
    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }
    
}
