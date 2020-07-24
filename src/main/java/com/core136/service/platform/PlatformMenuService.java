package com.core136.service.platform;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core136.bean.platform.PlatformMenu;
import com.core136.mapper.platform.PlatformMenuMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class PlatformMenuService {
@Autowired
private PlatformMenuMapper platformMenuMapper;

public int insertPlatformMenu(PlatformMenu platformMenu)
{
	return platformMenuMapper.insert(platformMenu);
}

public int deletePlatformMenu(PlatformMenu platformMenu)
{
	return platformMenuMapper.delete(platformMenu);
}

public int updatePlatformMenu(Example example,PlatformMenu platformMenu)
{
	return platformMenuMapper.updateByExampleSelective(platformMenu, example);
}

public PlatformMenu selectOnePlatformMenu(PlatformMenu platformMenu)
{
	return platformMenuMapper.selectOne(platformMenu);
}
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
public List<Map<String, String>>getPlatformMenuTree(String orgId,String levelId)
{
	return platformMenuMapper.getPlatformMenuTree(orgId, levelId);
}
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
public int isExistChild(String orgId,String levelId)
{
	return platformMenuMapper.isExistChild(orgId, levelId);
}
}
