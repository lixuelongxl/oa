package com.core136.controller.platform;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.core136.common.retdataunit.RetDataBean;
import org.core136.common.retdataunit.RetDataTools;
import org.core136.common.utils.SysTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.core136.bean.account.Account;
import com.core136.bean.dataupload.DataUploadHandle;
import com.core136.bean.platform.PlatformMenu;
import com.core136.bean.platform.PlatformPage;
import com.core136.service.account.AccountService;
import com.core136.service.platform.PlatformMenuService;
import com.core136.service.platform.PlatformPageService;

import tk.mybatis.mapper.entity.Example;

@RestController
@RequestMapping("/set/platformset")
public class RoutSetPlatformController {
@Autowired
private PlatformMenuService platformMenuService;
@Autowired
private PlatformPageService platformPageService;
@Autowired
private AccountService accountService;
/**
 * 
 * @Title: insertPlatformPage   
 * @Description: TODO 添加页面
 * @param request
 * @param platformPage
 * @return
 * RetDataBean    
 * @throws
 */
@RequestMapping(value="/insertPlatformPage",method=RequestMethod.POST)
public RetDataBean insertPlatformPage (HttpServletRequest request,PlatformPage platformPage)
{
	try
	{
		Account account=accountService.getRedisAccount(request);
		platformPage.setPageId(SysTools.getGUID());
		platformPage.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
		platformPage.setCreateUser(account.getAccountId());
		platformPage.setOrgId(account.getOrgId());
		return RetDataTools.Ok("创建页面成功!",platformPageService.insertPlatformPage(platformPage));
	}catch (Exception e) {
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title: deletPlatformPage   
 * @Description: TODO 删除页面
 * @param request
 * @param platformPage
 * @return
 * RetDataBean    
 * @throws
 */
@RequestMapping(value="/deletePlatformPage",method=RequestMethod.POST)
public RetDataBean deletPlatformPage(HttpServletRequest request,PlatformPage platformPage)
{
	try
	{
		if(StringUtils.isBlank(platformPage.getPageId()))
		{
			return RetDataTools.NotOk("请求参数有问题,请检查!");
		}
		Account account=accountService.getRedisAccount(request);
		platformPage.setOrgId(account.getOrgId());
		return RetDataTools.Ok("页面删除成功!",platformPageService.deletePlatformPage(platformPage));
	}catch (Exception e) {
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title: updatePlatformPage   
 * @Description: TODO 更新页面信息
 * @param request
 * @param platformPage
 * @return
 * RetDataBean    
 * @throws
 */
@RequestMapping(value="/updatePlatformPage",method=RequestMethod.POST)
public RetDataBean updatePlatformPage(HttpServletRequest request,PlatformPage platformPage)
{
	try
	{
		if(StringUtils.isBlank(platformPage.getPageId()))
		{
			return RetDataTools.NotOk("请求参数有问题,请检查!");
		}
		Account account=accountService.getRedisAccount(request);
		Example example = new Example(PlatformPage.class);
		example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("pageId",platformPage.getPageId());
		return RetDataTools.Ok("更新成功!",platformPageService.updatePlatformPage(example, platformPage));
	}catch (Exception e) {
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title: insertPlatformMenu   
 * @Description: TODO 创建菜单
 * @param request
 * @param platformMenu
 * @return
 * RetDataBean    
 * @throws
 */
@RequestMapping(value="/insertPlatformMenu",method=RequestMethod.POST)
public RetDataBean insertPlatformMenu (HttpServletRequest request,PlatformMenu platformMenu)
{
	try
	{
		Account account=accountService.getRedisAccount(request);
		platformMenu.setMenuId(SysTools.getGUID());
		platformMenu.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
		platformMenu.setCreateUser(account.getAccountId());
		platformMenu.setOrgId(account.getOrgId());
		return RetDataTools.Ok("创建菜单成功!",platformMenuService.insertPlatformMenu(platformMenu));
	}catch (Exception e) {
		return RetDataTools.Error(e.getMessage());
	}
}
/**
 * 
 * @Title: deletePlatformMenu   
 * @Description: TODO 删除菜单
 * @param request
 * @param platformMenu
 * @return
 * RetDataBean    
 * @throws
 */
@RequestMapping(value="/deletePlatformMenu",method=RequestMethod.POST)
public RetDataBean deletePlatformMenu(HttpServletRequest request,PlatformMenu platformMenu)
{
	try
	{
		if(StringUtils.isBlank(platformMenu.getMenuId()))
		{
			return RetDataTools.NotOk("请求参数有问题,请检查!");
		}
		Account account=accountService.getRedisAccount(request);
		platformMenu.setOrgId(account.getOrgId());
		return RetDataTools.Ok("菜单删除成功!",platformMenuService.deletePlatformMenu(platformMenu));
	}catch (Exception e) {
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title: updatePlatformMenu  
 * @Description: TODO 更新菜单信息
 * @param request
 * @param platformPage
 * @return
 * RetDataBean    
 * @throws
 */
@RequestMapping(value="/updatePlatformMenu",method=RequestMethod.POST)
public RetDataBean updatePlatformMenu(HttpServletRequest request,PlatformMenu platformMenu)
{
	try
	{
		if(StringUtils.isBlank(platformMenu.getMenuId()))
		{
			return RetDataTools.NotOk("请求参数有问题,请检查!");
		}
		Account account=accountService.getRedisAccount(request);
		Example example = new Example(PlatformMenu.class);
		example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("menuId",platformMenu.getMenuId());
		return RetDataTools.Ok("更新成功!",platformMenuService.updatePlatformMenu(example, platformMenu));
	}catch (Exception e) {
		return RetDataTools.Error(e.getMessage());
	}
}


}
