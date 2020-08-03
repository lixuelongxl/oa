package com.core136.config.datasource;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * <p>	Title: DBContextHolder </p>
 * <p>	Description:用于加载数据源  </p>
 * @author	曹凯
 * @date	2020年7月31日下午2:11:26
 * @version 1.0
 */
public class DBContextHolder {

	private final static Logger LOGGER = LoggerFactory.getLogger(DBContextHolder.class); 
	
	private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

	private static final AtomicInteger counter = new AtomicInteger(-1);

	public static void set(DBTypeEnum dbType) {
		contextHolder.set(dbType);
	}

	public static DBTypeEnum get() {
		return contextHolder.get();
	}

	public static void master() {
		set(DBTypeEnum.MASTER);
		LOGGER.info("切换到master");
	}

	public static void slave() {
		// 轮询
		int index = counter.getAndIncrement() % 2;
		if (counter.get() > 9999) {
			counter.set(-1);
		}
		if (index == 0) {
			set(DBTypeEnum.SLAVE1);
			LOGGER.info("切换到slave1");
		} else if(index == 1){
			set(DBTypeEnum.SLAVE2);
			LOGGER.info("切换到slave2");
		}else {
			set(DBTypeEnum.SLAVE3);
			LOGGER.info("切换到slave3");
		}
	}
}
