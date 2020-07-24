package com.core136.service.weixin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.core136.common.SessionMap;
import org.core136.common.SysRunConfig;
import org.core136.common.enums.EventTypeEnums;
import org.core136.common.utils.StrTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core136.bean.account.Account;
import com.core136.bean.account.Unit;
import com.core136.bean.account.UserInfo;
import com.core136.bean.account.UserPriv;
import com.core136.bean.sys.SysMenu;
import com.core136.config.AppGobalConstant;
import com.core136.service.account.AccountService;
import com.core136.service.account.UnitService;
import com.core136.service.account.UserInfoService;
import com.core136.service.account.UserPrivService;
import com.core136.service.sys.SysLogService;
import com.core136.service.sys.SysMenuService;
import com.taobao.api.ApiException;

@Service
public class WeiXinLoginService {
	@Autowired
	private AccountService accountService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private SysLogService sysLogService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private UserPrivService userPrivService;
	
	/**
	 * 
	 * @Title: weiXinLogin   
	 * @Description: TODO 微信客户端登陆
	 * @param request
	 * @param wAccountId
	 * @param orgId
	 * @throws ApiException
	 * void    
	 * @throws
	 */
	public void weiXinLogin(HttpServletRequest request,String wAccountId,String orgId) throws ApiException
	{
		boolean isRegist = SysRunConfig.getIsRegist();
		if(isRegist)
		{
		HttpSession session = request.getSession(true);
		Account account = accountService.wxLogin(orgId, wAccountId);
		if(account !=null) {
			String userPrivIds = account.getUserPriv();
			if(StringUtils.isNotBlank(userPrivIds))
			{
				String [] privIdArr = userPrivIds.split(",");
				String sysMenuIds="";
				String mobilePrivIds="";
				for(int i=0;i<privIdArr.length;i++)
				{
					if(StringUtils.isNotBlank(privIdArr[i]))
					{
					UserPriv userPriv = userPrivService.getUserPrivByPrivId(privIdArr[i], account.getOrgId());
					sysMenuIds+=userPriv.getUserPrivStr()+",";
					mobilePrivIds+=userPriv.getMobilePriv()+",";
					}
				}
				List<String> sysMenuIdList = StrTools.strToList(sysMenuIds);
				List<String> mobilePrivList = StrTools.strToList(mobilePrivIds);
				List<SysMenu> sysMenuList = sysMenuService.getSysMenuByAccount(sysMenuIdList, account.getOrgId());
				session.setAttribute("SYS_MENU_LIST", sysMenuList);
				session.setAttribute("SYS_APP_LIST", mobilePrivList);
				}
				Unit unit  = new Unit();
				unit.setOrgId(orgId);
				unit = unitService.selectOne(unit);
				session.setAttribute("UNIT", unit);
				if(unit.getOrgName().equals(""))
				{
					session.setAttribute("SOFT_NAME",AppGobalConstant.SOFT_NAME);
				}else
				{
					session.setAttribute("SOFT_NAME",unit.getOrgName());
				}
				session.setAttribute("LOGIN_USER", account);
				UserInfo userInfo  = userInfoService.getUserInfoByAccountId(account.getAccountId(), account.getOrgId());
				session.setAttribute("USER_INFO", userInfo);
				SessionMap.addSession(session);
				accountService.updateLastLoginTime(account);
				sysLogService.createLog(request, account,EventTypeEnums.SYS_LOG_LOGIN,"微信登陆成功");
		}else
			{
				sysLogService.createLog(request, account,EventTypeEnums.SYS_LOG_LOGIN,"微信登陆失败");
			}
		}
	}
	
}
