package com.core136.controller.bi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.core136.bean.account.Account;
import com.core136.bean.bi.BiSort;
import com.core136.bean.bi.BiTemplate;
import com.core136.bean.bpm.BpmList;
import com.core136.service.account.AccountService;
import com.core136.service.bi.BiSortService;
import com.core136.service.bi.BiTemplateService;
import com.core136.service.bi.BiTypeService;
import com.core136.service.bi.JasperreportsUnit;

import org.core136.common.retdataunit.RetDataBean;
import org.core136.common.retdataunit.RetDataTools;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/ret/biget")
public class RouteGetBiController {
@Autowired
private BiSortService biSortService;
@Autowired
private BiTypeService biTypeService;
@Autowired
private BiTemplateService biTemplateService;
@Autowired
private AccountService accountService;
@Autowired
private JasperreportsUnit jasperreportsUnit;

/**
 * 
 * @Title: getJasReportForPdf   
 * @Description: TODO 获取pdf格式报表
 * @param request
 * @param response
 * @param biTemplate
 * void
 */
@RequestMapping(value="/getJasReportForPdf",method=RequestMethod.GET)
public void getJasReportForPdf(HttpServletRequest request,HttpServletResponse response,BiTemplate biTemplate)
{
	try
	{
		Account account=accountService.getRedisAccount(request);
		biTemplate.setOrgId(account.getOrgId());
		jasperreportsUnit.getRepForPdf(request, response,biTemplate);
	}catch (Exception e) {
		
		//e.printStackTrace();
	}
}

/**
 * 
 * @Title: getBiSortTreeForParent   
 * @Description: TODO 获取BI分类结构 
 * @param request
 * @param sortId
 * @return
 * List<Map<String,Object>>
 */
@RequestMapping(value = "/getBiSortTreeForParent", method = RequestMethod.POST)
public List<Map<String, Object>> getBiSortTreeForParent(HttpServletRequest request, String sortId) {
	String levelId = "0";
	try {
		List<Map<String, Object>> tmplist1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> tmplist2 = new ArrayList<Map<String, Object>>();
		Account account=accountService.getRedisAccount(request);
		if (StringUtils.isNotBlank(sortId)) {
			levelId = sortId;
			if (!levelId.equals("0")) {
				tmplist1 = biTemplateService.getBiTemplateTree(account.getOrgId(), levelId, account.getAccountId());
			}
		}
		tmplist2 = biSortService.getBiSortTreeForParent(levelId, account.getOrgId());
		tmplist2.addAll(tmplist1);
		return tmplist2;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}



/**
 * 
 * @Title getBiSortTree   
 * @Description TODO 获取BI分类结构 
 * @param request
 * @param levelId
 * @return      
 * List<Map<String,Object>>
 */
	@RequestMapping(value="/getBiSortTree",method=RequestMethod.POST)
	public List<Map<String,Object>> getBiSortTree(HttpServletRequest request,String sortId)
	{
		try
		{
			String levelId = "0";
			if(StringUtils.isNotBlank(sortId))
			{
				levelId = sortId;
			}
			Account account=accountService.getRedisAccount(request);
			return biSortService.getBiSortTree(levelId, account.getOrgId());
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
/**
 * 
 * @Title getBiSortById   
 * @Description TODO 获取分类信息
 * @param request
 * @param biSort
 * @return      
 * RetDataBean
 */
	@RequestMapping(value="/getBiSortById",method=RequestMethod.POST)
	public RetDataBean getBiSortById(HttpServletRequest request,BiSort biSort)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			biSort.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求成功！", biSortService.selectOne(biSort));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
/**
 * 	
* @Title: getBiType 
* @Description: TODO 获取BI类型
* @param @param request
* @param @return 设定文件 
* @return RetDataBean 返回类型 

 */
	@RequestMapping(value="/getBiType",method=RequestMethod.POST)
	public RetDataBean getBiType(HttpServletRequest request)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			return RetDataTools.Ok("请求成功！", biTypeService.getAllBiType(account.getOrgId()));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	* @Title: getBiTemplateList 
	* @Description: TODO 按分类获取报表模版列表
	* @param @param request
	* @param @param pageNumber
	* @param @param pageSize
	* @param @param search
	* @param @param sort
	* @param @param sortOrder
	* @param @return 设定文件 
	* @return RetDataBean 返回类型 

	 */
	@RequestMapping(value="/getBiTemplateList",method=RequestMethod.POST)
	public RetDataBean getBiTemplateList(
			HttpServletRequest request,
			Integer pageNumber,
			Integer pageSize,
			String search,
			String sort,
			String sortOrder,
			String levelId
			)
	{
		try
		{
			if(StringUtils.isBlank(sort))
			{
				sort="SORT_NO";
			}
			if(StringUtils.isBlank(sortOrder))
			{
				sortOrder="asc";
			}
		String orderBy = sort+ " " + sortOrder;
		Account account=accountService.getRedisAccount(request);
		PageInfo<Map<String, Object>> pageInfo=biTemplateService.getBiTemplateList(pageNumber,pageSize,orderBy,levelId,account.getOrgId(),"%"+search+"%");
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	* @Title: getBiTemplateById 
	* @Description: TODO 获取BI模版详情
	* @param @param request
	* @param @param biTemplate
	* @param @return 设定文件 
	* @return RetDataBean 返回类型 

	 */
	@RequestMapping(value="/getBiTemplateById",method=RequestMethod.POST)
	public RetDataBean getBiTemplateById(HttpServletRequest request,BiTemplate biTemplate)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			biTemplate.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求成功！", biTemplateService.selectOne(biTemplate));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
}
