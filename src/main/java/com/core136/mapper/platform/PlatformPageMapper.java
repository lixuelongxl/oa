package com.core136.mapper.platform;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.core136.common.dbutils.MyMapper;

import com.core136.bean.platform.PlatformPage;

@Mapper
public interface PlatformPageMapper extends MyMapper<PlatformPage>{

	/**
	 * 
	 * @Title: getManagePlatformPageList   
	 * @Description: TODO 获取页面管理列表
	 * @param orgId
	 * @param opFlag
	 * @param accountId
	 * @param pageType
	 * @return
	 * List<Map<String,String>>    
	 * @throws
	 */
	public List<Map<String, String>>getManagePlatformPageList(@Param(value="orgId")String orgId,@Param(value="opFlag")String opFlag,
			@Param(value="accountId")String accountId,@Param(value="pageType")String pageType,@Param(value="search")String search);
	
}
