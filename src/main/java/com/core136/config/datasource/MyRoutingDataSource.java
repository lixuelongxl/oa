package com.core136.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;
/**
 * 
 * <p>	Title: MyRoutingDataSource </p>
 * <p>	Description:根据路由获取数据源  </p>
 * @author	曹凯
 * @date	2020年7月31日下午2:12:44
 * @version 1.0
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource{

	@Nullable
	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return DBContextHolder.get();
	}


}
