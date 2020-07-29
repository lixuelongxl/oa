package com.core136.controller.platform;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.core136.common.retdataunit.RetDataBean;
import org.core136.common.retdataunit.RetDataTools;
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

@RestController
@RequestMapping("/ret/platformget")
public class RoutGetPlatformController {
	@Autowired
	private PlatformMenuService platformMenuService;
	@Autowired
	private PlatformPageService platformPageService;
	@Autowired
	private AccountService accountService;
	
	/**
	 * 
	 * @Title: getknowledgeSortTree   
	 * @Description: TODO 获取平台菜单树结构
	 * @param request
	 * @param sortId
	 * @return
	 * List<Map<String,String>>    
	 * @throws
	 */
	@RequestMapping(value="/getknowledgeSortTree",method=RequestMethod.POST)
	public List<Map<String,String>> getknowledgeSortTree(HttpServletRequest request,String sortId)
	{
		try
		{
			String levelId = "0";
			if(StringUtils.isNotBlank(sortId))
			{
				levelId = sortId;
			}
			Account account=accountService.getRedisAccount(request);
			return platformMenuService.getPlatformMenuTree(account.getOrgId(), levelId);
		}catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getDataUploadHandleById   
	 * @Description: TODO 获取页面详情
	 * @param request
	 * @param platformPage
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getPlatformPageById",method=RequestMethod.POST)
	public RetDataBean getPlatformPageById(HttpServletRequest request,PlatformPage platformPage)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			platformPage.setOrgId(account.getOrgId());
			return RetDataTools.Ok("数据请求成功!",platformPageService.selectOnePlatformPage(platformPage));
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: getPlatformMenuById   
	 * @Description: TODO 获取菜单详情
	 * @param request
	 * @param platformMenu
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getPlatformMenuById",method=RequestMethod.POST)
	public RetDataBean getPlatformMenuById(HttpServletRequest request,PlatformMenu platformMenu)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			platformMenu.setOrgId(account.getOrgId());
			return RetDataTools.Ok("数据请求成功!",platformMenuService.selectOnePlatformMenu(platformMenu));
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
}
