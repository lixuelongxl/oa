package com.core136.mapper.platform;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.core136.common.dbutils.MyMapper;

import com.core136.bean.platform.PlatformMenu;
/**
 * 
 * @ClassName:  PlatformMenuMapper
 * @Description:TODO 智能平台菜单设置
 * @author: 稠云技术 
 * @date:   2020年7月21日 下午2:36:06     
 * @Copyright: 2020 www.cyunsoft.com Inc. All rights reserved. 
 * 注意：本内容仅限于江苏稠云信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Mapper
public interface PlatformMenuMapper extends MyMapper<PlatformMenu>{

	/**
	 * 
	 * @Title: getPlatformMenuTree   
	 * @Description: TODO 获取智能平台菜单树结构
	 * @param orgId
	 * @param levelId
	 * @return
	 * List<Map<String,String>>    
	 * @throws
	 */
	public List<Map<String, String>>getPlatformMenuTree(@Param(value="orgId")String orgId,String levelId);
	/**
	 * 
	 * @Title: isExistChild   
	 * @Description: TODO 判断菜单下是否有子菜单
	 * @param orgId
	 * @param levelId
	 * @return
	 * int    
	 * @throws
	 */
	public int isExistChild(@Param(value="orgId")String orgId,@Param(value="levelId")String levelId);
}
