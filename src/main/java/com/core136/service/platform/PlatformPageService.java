package com.core136.service.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core136.bean.platform.PlatformPage;
import com.core136.mapper.platform.PlatformPageMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class PlatformPageService {

	@Autowired
	private PlatformPageMapper platformPageMapper;
	
	public int insertPlatformPage(PlatformPage platformPage)
	{
		return platformPageMapper.insert(platformPage);
	}
	
	public int deletePlatformPage(PlatformPage platformPage)
	{
		return platformPageMapper.delete(platformPage);
	}
	
	public int updatePlatformPage(Example example,PlatformPage platformPage)
	{
		return platformPageMapper.updateByExampleSelective(platformPage, example);
	}
	public PlatformPage selectOnePlatformPage(PlatformPage platformPage)
	{
		return platformPageMapper.selectOne(platformPage);
	}
	
}
