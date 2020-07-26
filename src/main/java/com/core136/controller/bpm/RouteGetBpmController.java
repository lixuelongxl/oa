package com.core136.controller.bpm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.core136.bean.account.Account;
import com.core136.bean.account.UserInfo;
import com.core136.bean.bpm.BpmAdvTableQueryParam;
import com.core136.bean.bpm.BpmBiTemplate;
import com.core136.bean.bpm.BpmBusiness;
import com.core136.bean.bpm.BpmChildProcess;
import com.core136.bean.bpm.BpmFlow;
import com.core136.bean.bpm.BpmForm;
import com.core136.bean.bpm.BpmList;
import com.core136.bean.bpm.BpmPluginsRegister;
import com.core136.bean.bpm.BpmProcess;
import com.core136.bean.bpm.BpmRunProcess;
import com.core136.bean.bpm.BpmSealSign;
import com.core136.bean.bpm.BpmSort;
import com.core136.bean.bpm.BpmTemplate;
import com.core136.bean.sys.PageParam;
import com.core136.service.account.AccountService;
import com.core136.service.account.UserInfoService;
import com.core136.service.bpm.BpmBiTemplateService;
import com.core136.service.bpm.BpmBusinessService;
import com.core136.service.bpm.BpmChildProcessService;
import com.core136.service.bpm.BpmEntrustService;
import com.core136.service.bpm.BpmFlowService;
import com.core136.service.bpm.BpmFormCacheService;
import com.core136.service.bpm.BpmFormDataService;
import com.core136.service.bpm.BpmFormService;
import com.core136.service.bpm.BpmFormVersionService;
import com.core136.service.bpm.BpmListService;
import com.core136.service.bpm.BpmLogicCpuService;
import com.core136.service.bpm.BpmMobileFormService;
import com.core136.service.bpm.BpmOptService;
import com.core136.service.bpm.BpmPluginsRegisterService;
import com.core136.service.bpm.BpmProcessService;
import com.core136.service.bpm.BpmRunLogService;
import com.core136.service.bpm.BpmRunProcessService;
import com.core136.service.bpm.BpmSealSignService;
import com.core136.service.bpm.BpmSendToService;
import com.core136.service.bpm.BpmSortService;
import com.core136.service.bpm.BpmTableService;
import com.core136.service.bpm.BpmTemplateService;
import com.core136.service.bpm.BpmUnitsService;
import com.core136.unit.bpm.BpmTableBean;
import com.core136.unit.bpm.BpmTemplateUnit;
import org.core136.common.retdataunit.RetDataBean;
import org.core136.common.retdataunit.RetDataTools;
import org.core136.common.utils.StrTools;
import org.core136.common.utils.SysTools;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/ret/bpmget")
public class RouteGetBpmController {
	@Autowired
	private BpmSortService bpmSortService;
	@Autowired
	private BpmFormService bpmFormService;
	@Autowired
	private BpmFlowService bpmFlowService;
	@Autowired
	private BpmProcessService bpmProcessService;
	@Autowired
	private BpmRunProcessService bpmRunProcessService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private BpmTableService bpmTableService;
	@Autowired
	private BpmListService bpmListService;
	@Autowired
	private BpmFormDataService bpmFormDataService;
	@Autowired
	private BpmLogicCpuService bpmLogicCpuService;
	@Autowired
	private BpmEntrustService bpmEntrustService;
	@Autowired
	private BpmUnitsService bpmUnitsService;
	@Autowired
	private BpmOptService bpmOptService;
	@Autowired
	private BpmRunLogService bpmRunLogService;
	@Autowired
	private BpmSealSignService bpmSealSignService;
	@Autowired
	private BpmPluginsRegisterService bpmPluginsRegisterService;
	@Autowired
	private BpmChildProcessService bpmChildProcessService;
	@Autowired
	private BpmSendToService bpmSendToService;
	@Autowired
	private BpmTemplateService bpmTemplateService;
	@Autowired
	private BpmMobileFormService bpmMobileFormService;
	@Autowired
	private BpmFormCacheService bpmFormCacheService;
	@Autowired
	private BpmFormVersionService bpmFormVersionService;
	@Autowired
	private BpmBusinessService bpmBusinessService;
	@Autowired
	private BpmBiTemplateService bpmBiTemplateService;
	@Autowired
	private AccountService accountService;
	/**
	 * 
	 * @Title: getBiFormDataToFile   
	 * @Description: TODO 导出BI查询结果文件
	 * @param request
	 * @param response
	 * @param bpmBiTemplate
	 * @param conditionJson
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getBiFormDataToFile",method=RequestMethod.POST)
	public void getBiFormDataToFile(HttpServletRequest request,HttpServletResponse response,BpmBiTemplate bpmBiTemplate,String conditionJson)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			bpmBiTemplate.setOrgId(account.getOrgId());
			bpmBiTemplate = bpmBiTemplateService.selectOneBpmBiTemplate(bpmBiTemplate);
			bpmBiTemplateService.getBiFormDataToFile(response,bpmBiTemplate,conditionJson);
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 
	 * @Title: getBiFormDataListByBpmBiTemplate   
	 * @Description: TODO TODO按条件获取BPM报表
	 * @param request
	 * @param pageParam
	 * @param bpmBiTemplate
	 * @param conditionJson
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getBiFormDataListByBpmBiTemplate",method=RequestMethod.POST)
	public RetDataBean getBiFormDataListByBpmBiTemplate(
			HttpServletRequest request,
			PageParam pageParam,
			BpmBiTemplate bpmBiTemplate,String conditionJson
			)
	{
		try
		{
			if(StringUtils.isBlank(pageParam.getSort()))
			{
				pageParam.setSort("ID");
			}else
			{
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if(StringUtils.isBlank(pageParam.getSortOrder()))
			{
				pageParam.setSortOrder("asc");
			}
		Account account=accountService.getRedisAccount(request);
		bpmBiTemplate.setOrgId(account.getOrgId());
		bpmBiTemplate = bpmBiTemplateService.selectOneBpmBiTemplate(bpmBiTemplate);
		String orderBy = pageParam.getSort()+ " " + pageParam.getSortOrder();
		pageParam.setAccountId(account.getAccountId());
		pageParam.setOrgId(account.getOrgId());
		pageParam.setOrderBy(orderBy);
		PageInfo<Map<String, Object>> pageInfo = bpmBiTemplateService.getBiFormDataListByBpmBiTemplate(pageParam, bpmBiTemplate,conditionJson);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: getBpmBiTemplateListByPriv   
	 * @Description: TODO 获取权限内的BPM数据报表
	 * @param request
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getBpmBiTemplateListByPriv",method=RequestMethod.POST)
	public RetDataBean getBpmBiTemplateListByPriv(HttpServletRequest request)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			UserInfo userInfo = accountService.getRedisUserInfo(request);
			return RetDataTools.Ok("请求数据成功!", bpmBiTemplateService.getBpmBiTemplateListByPriv(account.getOrgId(),account.getOpFlag(),account.getAccountId(),userInfo.getDeptId(),userInfo.getLeadLeave()));
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	
	
	
	/**
	 * 
	 * @Title: getBpmBiTemplateList   
	 * @Description: TODO 获取BPM数据报表设置列表
	 * @param request
	 * @param pageParam
	 * @param flowId
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getBpmBiTemplateList",method=RequestMethod.POST)
	public RetDataBean getBpmBiTemplateList(HttpServletRequest request,PageParam pageParam,String flowId)
	{
		try
		{
			if(StringUtils.isBlank(pageParam.getSort()))
			{
				pageParam.setSort("T.SORT_NO");
			}else
			{
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if(StringUtils.isBlank(pageParam.getSortOrder()))
			{
				pageParam.setSortOrder("asc");
			}
		UserInfo userInfo = accountService.getRedisUserInfo(request);;
		pageParam.setOrgId(userInfo.getOrgId());
		pageParam.setOrderBy(pageParam.getSort()+ " " + pageParam.getSortOrder());
		PageInfo<Map<String, String>> pageInfo=bpmBiTemplateService.getBpmBiTemplateList(pageParam,flowId);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: getBpmBiTemplateById   
	 * @Description: TODO 获取BPM报表模版详情
	 * @param request
	 * @param bpmBiTemplate
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getBpmBiTemplateById",method=RequestMethod.POST)
	public RetDataBean getBpmBiTemplateById(HttpServletRequest request,BpmBiTemplate bpmBiTemplate)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			bpmBiTemplate.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求数据成功!", bpmBiTemplateService.selectOneBpmBiTemplate(bpmBiTemplate));
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	
	/**
	 * 
	 * @Title: getBpmBusinessList   
	 * @Description: TODO 获取BPM业务引擎列表
	 * @param request
	 * @param pageParam
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getBpmBusinessList",method=RequestMethod.POST)
	public RetDataBean getBpmBusinessList(HttpServletRequest request,PageParam pageParam)
	{
		try
		{
			if(StringUtils.isBlank(pageParam.getSort()))
			{
				pageParam.setSort("B.SORT_NO");
			}else
			{
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if(StringUtils.isBlank(pageParam.getSortOrder()))
			{
				pageParam.setSortOrder("asc");
			}
			
			UserInfo userInfo = accountService.getRedisUserInfo(request);;
		pageParam.setOrgId(userInfo.getOrgId());
		pageParam.setOrderBy(pageParam.getSort()+ " " + pageParam.getSortOrder());
		PageInfo<Map<String, String>> pageInfo=bpmBusinessService.getBpmBusinessList(pageParam);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getBpmBusinessById   
	 * @Description: TODO 获取业务引擎详情
	 * @param request
	 * @param bpmBusiness
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getBpmBusinessById",method=RequestMethod.POST)
	public RetDataBean getBpmBusinessById(HttpServletRequest request,BpmBusiness bpmBusiness)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			bpmBusiness.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求数据成功!", bpmBusinessService.selectOneBpmBusiness(bpmBusiness));
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	
	/**
	 * 
	 * @Title: getBpmFormVersionList   
	 * @Description: TODO 获取BPM表单版本
	 * @param request
	 * @param pageParam
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value = "/getBpmFormVersionList", method = RequestMethod.POST)
	public RetDataBean getBpmFormVersionList(HttpServletRequest request,PageParam pageParam,String formId)
	{
		try {
			if (StringUtils.isBlank(pageParam.getSort())) {
				pageParam.setSort("F.CREATE_TIME");
			} else {
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if (StringUtils.isBlank(pageParam.getSortOrder())) {
				pageParam.setSortOrder("asc");
			}
			Account account=accountService.getRedisAccount(request);
			String orderBy = pageParam.getSort() + " " + pageParam.getSortOrder();
			pageParam.setOrgId(account.getOrgId());
			pageParam.setOrderBy(orderBy);
			PageInfo<Map<String, String>> pageInfo = bpmFormVersionService.getBpmFormVersionList(pageParam,formId);
			return RetDataTools.Ok("请求数据成功!", pageInfo);
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: getUserInfoInPrivForMobile   
	 * @Description: TODO 移动端经办人员选择 
	 * @param: request
	 * @param: bpmProcess
	 * @param: search
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getUserInfoInPrivForMobile",method=RequestMethod.POST)
	public RetDataBean getUserInfoInPrivForMobile(HttpServletRequest request,BpmProcess bpmProcess,String search)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			bpmProcess.setOrgId(account.getOrgId());
			bpmProcess = bpmProcessService.selectOne(bpmProcess);
			return RetDataTools.Ok("请求数据成功!", bpmProcessService.getUserInfoInPrivForMobile(bpmProcess,search));
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	
	
	/**
	 * 
	 * @Title: getBpmMobileFormSet   
	 * @Description: TODO 获取移动端表单设计列表 
	 * @param: request
	 * @param: bpmForm
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getbpmmobileformset",method=RequestMethod.POST)
	public RetDataBean getBpmMobileFormSet(HttpServletRequest request,BpmForm bpmForm)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			bpmForm.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求数据成功!", bpmMobileFormService.getBpmMobileFormFieldsForSet(bpmForm));
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	
	
	/**
	 * 
	 * @Title: getBpmCacheProcessList   
	 * @Description: TODO 获取缓存步骤列表
	 * @param: request
	 * @param: pageParam
	 * @param: flowId
	 * @param: sortId
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value = "/getBpmCacheProcessList", method = RequestMethod.POST)
	public RetDataBean getBpmCacheProcessList(HttpServletRequest request,PageParam pageParam,String flowId, String sortId)
	{
		try {
			if (StringUtils.isBlank(pageParam.getSort())) {
				pageParam.setSort("P.FLOW_ID");
			} else {
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if (StringUtils.isBlank(pageParam.getSortOrder())) {
				pageParam.setSortOrder("asc");
			}
			Account account=accountService.getRedisAccount(request);
			String orderBy = pageParam.getSort() + " " + pageParam.getSortOrder();
			pageParam.setOrgId(account.getOrgId());
			pageParam.setOrderBy(orderBy);
			PageInfo<Map<String, String>> pageInfo = bpmProcessService.getBpmCacheProcessList(pageParam,flowId,sortId);
			return RetDataTools.Ok("请求数据成功!", pageInfo);
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: getBpmTemplateList   
	 * @Description: TODO 获取BPM模版列表
	 * @param: request
	 * @param: pageParam
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value = "/getBpmTemplateList", method = RequestMethod.POST)
	public RetDataBean getBpmTemplateList(HttpServletRequest request,PageParam pageParam) {
		try {
			if (StringUtils.isBlank(pageParam.getSort())) {
				pageParam.setSort("SORT_NO");
			} else {
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if (StringUtils.isBlank(pageParam.getSortOrder())) {
				pageParam.setSortOrder("asc");
			}
			Account account=accountService.getRedisAccount(request);
			String orderBy = pageParam.getSort() + " " + pageParam.getSortOrder();
			pageParam.setOrderBy(orderBy);
			pageParam.setAccountId(account.getAccountId());
			pageParam.setOrgId(account.getOrgId());
			PageInfo<Map<String, String>> pageInfo = bpmTemplateService.getBpmTemplateList(pageParam);
			return RetDataTools.Ok("请求数据成功!", pageInfo);
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: getBpmTemplateById   
	 * @Description: TODO 获取BPM模版详情
	 * @param: request
	 * @param: bpmTemplate
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getBpmTemplateById",method=RequestMethod.POST)
	public RetDataBean getBpmTemplateById(HttpServletRequest request,BpmTemplate bpmTemplate)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			bpmTemplate.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求数据成功!", bpmTemplateService.selectOneBpmTemplate(bpmTemplate));
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	
	/**
	 * 
	 * @Title: getSendToMeBpmList   
	 * @Description: TODO 获取抄送给我的BPM流程列表
	 * @param: request
	 * @param: pageParam
	 * @param: status
	 * @param: beginTime
	 * @param: endTime
	 * @param: flowId
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getSendToMeBpmList",method=RequestMethod.POST)
	public RetDataBean getSendToMeBpmList(
			HttpServletRequest request,
			PageParam pageParam,
			String status,
			String beginTime,
			String endTime,
			String flowId
			)
	{
		try
		{
			if(StringUtils.isBlank(pageParam.getSort()))
			{
				pageParam.setSort("S.STATUS");
			}else
			{
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if(StringUtils.isBlank(pageParam.getSortOrder()))
			{
				pageParam.setSortOrder("asc");
			}
		Account account=accountService.getRedisAccount(request);
		String orderBy = pageParam.getSort()+ " " + pageParam.getSortOrder();
		pageParam.setAccountId(account.getAccountId());
		pageParam.setOrgId(account.getOrgId());
		pageParam.setOrderBy(orderBy);
		PageInfo<Map<String, String>> pageInfo = bpmSendToService.getSendToMeBpmList(pageParam, beginTime, endTime,flowId,status);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}	
	
	
	/**
	 * 
	 * @Title: getBpmChildProcessPrcs   
	 * @Description: TODO 获取子流程配置信息
	 * @param: request
	 * @param: bpmChildProcess
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getBpmChildProcessPrcs",method=RequestMethod.POST)
	public RetDataBean getBpmChildProcessPrcs(HttpServletRequest request,BpmChildProcess bpmChildProcess)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			bpmChildProcess.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求数据成功!", bpmChildProcessService.getBpmChildProcessPrcs(account.getOrgId(),bpmChildProcess.getProcessId()));
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	/**
	 * 
	 * @Title: getBpmFlowXmlFile   
	 * @Description: TODO 导出BPMFLOW的XML文件 
	 * @param: request
	 * @param: response
	 * @param: bpmFlow      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping(value="/getBpmFlowXmlFile",method=RequestMethod.GET)
	public void getBpmFlowXmlFile(HttpServletRequest request,HttpServletResponse response,BpmFlow bpmFlow)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(StringUtils.isBlank(bpmFlow.getFlowId()))
			{
				return;
			}
			bpmFlow.setOrgId(account.getOrgId());
			bpmFlow = bpmFlowService.selectOne(bpmFlow);
			if(bpmFlow==null)
			{
				return;
			}else {
				bpmFlowService.getBpmFlowXmlFile(bpmFlow,response);
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 
	 * @Title: getFormFile   
	 * @Description: TODO 导出FORM表单文件 
	 * @param: request
	 * @param: response
	 * @param: bpmForm      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping(value="/getFormFile",method=RequestMethod.GET)
	public void getFormFile(HttpServletRequest request,HttpServletResponse response,BpmForm bpmForm)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(StringUtils.isBlank(bpmForm.getFormId()))
			{
				return;
			}
			bpmForm.setOrgId(account.getOrgId());
			bpmForm = bpmFormService.selectOneBpmForm(bpmForm);
			if(bpmForm==null)
			{
				return;
			}else {
				bpmFormService.getFormFile(bpmForm,response);
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 
	 * @Title: getFormTableFields   
	 * @Description: TODO 获取BPM表的字段信息
	 * @param: request
	 * @param: response
	 * @param: bpmForm
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getFormTableFields",method=RequestMethod.POST)
	public RetDataBean getFormTableFields(HttpServletRequest request,HttpServletResponse response,BpmForm bpmForm)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(StringUtils.isBlank(bpmForm.getFormId()))
			{
				return RetDataTools.NotOk("请求有参数有问题,请与管理员联系!");
			}
			bpmForm.setOrgId(account.getOrgId());
			bpmForm = bpmFormService.selectOneBpmForm(bpmForm);
			if(bpmForm==null)
			{
				return RetDataTools.NotOk("请求有参数有问题,请与管理员联系!");
			}else {
				return RetDataTools.Ok("请求数据成功!", bpmFormService.getFormTableFields(bpmForm));
			}
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	
	/**
	 * 
	 * @Title: getAllFormData   
	 * @Description: TODO 获取BPM表数据
	 * @param: request
	 * @param: pageParam
	 * @param: bpmForm
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getAllFormData",method=RequestMethod.POST)
	public RetDataBean getAllFormData(HttpServletRequest request,PageParam pageParam,BpmForm bpmForm,Integer id)
	{
		try
		{
			bpmForm.setId(null);
			if(StringUtils.isBlank(bpmForm.getFormId()))
			{
				return RetDataTools.NotOk("请求有参数有问题,请与管理员联系!");
			}
			if(StringUtils.isBlank(pageParam.getSort()))
			{
				pageParam.setSort("ID");
			}else
			{
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if(StringUtils.isBlank(pageParam.getSortOrder()))
			{
				pageParam.setSortOrder("asc");
			}
		Account account=accountService.getRedisAccount(request);
		pageParam.setOrgId(account.getOrgId());
		pageParam.setAccountId(account.getAccountId());
		pageParam.setOrderBy(pageParam.getSort()+ " " + pageParam.getSortOrder());
		bpmForm.setOrgId(account.getOrgId());
		bpmForm = bpmFormService.selectOneBpmForm(bpmForm);
		if(bpmForm==null)
		{
			return RetDataTools.NotOk("请求参数有问题!请与客理员联系!");
		}else {
			PageInfo<Map<String, Object>> pageInfo=bpmFormService.getAllFormData(pageParam, bpmForm,id);
			return RetDataTools.Ok("请求数据成功!", pageInfo);
		}
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	
	/**
	 * 
	 * @Title: getFormDataTitle   
	 * @Description: TODO 获取BPM数据表表头
	 * @param: request
	 * @param: response
	 * @param: bpmForm
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getFormDataTitle",method=RequestMethod.POST)
	public RetDataBean getFormDataTitle(HttpServletRequest request,HttpServletResponse response,BpmForm bpmForm)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(StringUtils.isBlank(bpmForm.getFormId()))
			{
				return RetDataTools.NotOk("请求有参数有问题,请与管理员联系!");
			}
			bpmForm.setOrgId(account.getOrgId());
			bpmForm = bpmFormService.selectOneBpmForm(bpmForm);
			if(bpmForm==null)
			{
				return RetDataTools.NotOk("请求有参数有问题,请与管理员联系!");
			}else {
				return RetDataTools.Ok("请求数据成功!", bpmFormService.getFormDataTitle(bpmForm));
			}
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	
	
	
	/**
	 * 
	 * @Title: getBpmFormListByBpmTypeId   
	 * @Description: TODO 获取分类下的表单列表
	 * @param: request
	 * @param: pageParam
	 * @param: bpmTypeId
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getBpmFormListByBpmTypeId",method=RequestMethod.POST)
	public RetDataBean getBpmFormListByBpmTypeId(HttpServletRequest request,PageParam pageParam,String bpmTypeId)
	{
		try
		{
			if(StringUtils.isBlank(pageParam.getSort()))
			{
				pageParam.setSort("SORT_NO");
			}else
			{
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if(StringUtils.isBlank(pageParam.getSortOrder()))
			{
				pageParam.setSortOrder("asc");
			}
		Account account=accountService.getRedisAccount(request);
		pageParam.setOrgId(account.getOrgId());
		pageParam.setAccountId(account.getAccountId());
		pageParam.setOrderBy(pageParam.getSort()+ " " + pageParam.getSortOrder());
		PageInfo<Map<String, String>> pageInfo=bpmFormService.getBpmFormListByBpmTypeId(pageParam, bpmTypeId);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: getAllBpmFlowListByFlowSort   
	 * @Description: TODO 获取分类下的BPM 流程列表
	 * @param: request
	 * @param: pageParam
	 * @param: flowSort
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getAllBpmFlowListByFlowSort",method=RequestMethod.POST)
	public RetDataBean getAllBpmFlowListByFlowSort(HttpServletRequest request,PageParam pageParam,String flowSort)
	{
		try
		{
			if(StringUtils.isBlank(pageParam.getSort()))
			{
				pageParam.setSort("B.SORT_NO");
			}else
			{
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if(StringUtils.isBlank(pageParam.getSortOrder()))
			{
				pageParam.setSortOrder("asc");
			}
		Account account=accountService.getRedisAccount(request);
		pageParam.setOrgId(account.getOrgId());
		pageParam.setAccountId(account.getAccountId());
		pageParam.setOrderBy(pageParam.getSort()+ " " + pageParam.getSortOrder());
		PageInfo<Map<String, String>> pageInfo=bpmFlowService.getAllBpmFlowListByFlowSort(pageParam, flowSort);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	
	/**
	 * 
	 * @Title: getFromDataForHtml   
	 * @Description: TODO BPM表单转成HTML
	 * @param: request
	 * @param: response
	 * @param: bpmList      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping(value="/getFromDataForHtml",method=RequestMethod.GET)
	public void getFromDataForHtml(HttpServletRequest request,HttpServletResponse response,BpmList bpmList)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			bpmOptService.getFromDataForHtml(request, response, account, bpmList);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title: getFromDataForWord   
	 * @Description: TODO BPM表单成生WORD
	 * @param: request
	 * @param: response
	 * @param: bpmList      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping(value="/getFromDataForWord",method=RequestMethod.GET)
	public void getFromDataForWord(HttpServletRequest request,HttpServletResponse response,BpmList bpmList)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			bpmOptService.getFromDataForWord(request, response, account, bpmList);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @Title: getFromDataForPdf   
	 * @Description: TODO 生成PDF下载
	 * @param: request
	 * @param: response
	 * @param: bpmList      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping(value="/getFromDataForPdf",method=RequestMethod.GET)
	public void getFromDataForPdf(HttpServletRequest request,HttpServletResponse response,BpmList bpmList)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			bpmOptService.getFromDataForPdf(request, response, account, bpmList);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 
	 * @Title: getBpmPluginsListForProcess   
	 * @Description: TODO 获取BPM插件列表  
	 * @param: request
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getBpmPluginsListForProcess",method=RequestMethod.POST)
	public RetDataBean getBpmPluginsListForProcess(HttpServletRequest request)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			return RetDataTools.Ok("请求成功!", bpmPluginsRegisterService.getBpmPluginsListForProcess(account.getOrgId()));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: getBpmPluginsList   
	 * @Description: TODO 获取BPM注册插件列表     
	 * @param: request
	 * @param: pageParam
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getBpmPluginsList",method=RequestMethod.POST)
	public RetDataBean getBpmPluginsList(HttpServletRequest request,PageParam pageParam)
	{
		try
		{
			if(StringUtils.isBlank(pageParam.getSort()))
			{
				pageParam.setSort("SORT_NO");
			}else
			{
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if(StringUtils.isBlank(pageParam.getSortOrder()))
			{
				pageParam.setSortOrder("asc");
			}
		Account account=accountService.getRedisAccount(request);
		pageParam.setOrgId(account.getOrgId());
		pageParam.setOrderBy(pageParam.getSort()+ " " + pageParam.getSortOrder());
		PageInfo<Map<String, String>> pageInfo=bpmPluginsRegisterService.getBpmPluginsList(pageParam);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getBpmPluginsRegisterById   
	 * @Description: TODO 获取BPM插件详情
	 * @param: request
	 * @param: bpmPluginsRegister
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getBpmPluginsRegisterById",method=RequestMethod.POST)
	public RetDataBean getBpmPluginsRegisterById(HttpServletRequest request,BpmPluginsRegister bpmPluginsRegister)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			bpmPluginsRegister.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求成功!", bpmPluginsRegisterService.selectOneBpmPluginsRegister(bpmPluginsRegister));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: isCantTaskBackFlag   
	 * @Description: TODO 判断当前用户下的流程哪些是可以收回的
	 * @param: request
	 * @param: runId
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/isCantTaskBackFlag",method=RequestMethod.POST)
	public RetDataBean isCantTaskBackFlag(HttpServletRequest request,String runId)
	{
		try
		{
			if(StringUtils.isBlank(runId))
			{
				return RetDataTools.NotOk("您请求的参数有问题,请与管理员联系!");
			}
			Account account=accountService.getRedisAccount(request);
			return RetDataTools.Ok("请求成功!", bpmRunProcessService.isCantTaskBackFlag(account.getOrgId(),runId,account.getAccountId()));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: queryExcel   
	 * @Description: TODO 导出Excel
	 * @param: response
	 * @param: request
	 * @param: resFieldsJson
	 * @param: whereListMapJson
	 * @param: bpmAdvTableQueryParam      
	 * @return: void      
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryExcel", method = RequestMethod.POST)
	public void queryExcel(HttpServletResponse response,HttpServletRequest request, String resFieldsJson,String whereListMapJson, BpmAdvTableQueryParam bpmAdvTableQueryParam) {
		try {
			Account account=accountService.getRedisAccount(request);
			if(StringUtils.isNotBlank(resFieldsJson))
			{
				List<Map<String, String>> tmpList = new ArrayList<Map<String,String>>();
				JSONArray jsonArray =  JSONArray.parseArray(resFieldsJson);
				for(int i=0;i<jsonArray.size();i++)
				{
				Map<String, String> map = (Map<String, String>)JSONUtils.parse(jsonArray.getString(i));
				tmpList.add(map);
				}
				bpmAdvTableQueryParam.setResFields(tmpList);
			}
			if(StringUtils.isNotBlank(whereListMapJson))
			{
				List<Map<String, String>> tmpList = new ArrayList<Map<String,String>>();
				JSONArray jsonArray =  JSONArray.parseArray(whereListMapJson);
				for(int i=0;i<jsonArray.size();i++)
				{
				Map<String, String> map = (Map<String, String>)JSONUtils.parse(jsonArray.getString(i));
				tmpList.add(map);
				}
				bpmAdvTableQueryParam.setWhereListMap(tmpList);
			}
			bpmAdvTableQueryParam.setOrgId(account.getOrgId());
			bpmTableService.queryExcel(response,bpmAdvTableQueryParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @Title: querybpmlist   
	 * @Description: TODO 查询工作流列表
	 * @param: request
	 * @param: resFieldsJson
	 * @param: whereListMapJson
	 * @param: pageParam
	 * @param: bpmAdvTableQueryParam
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/querybpmlist", method = RequestMethod.POST)
	public RetDataBean querybpmlist(HttpServletRequest request, String resFieldsJson,String whereListMapJson, 
			PageParam pageParam,
			BpmAdvTableQueryParam bpmAdvTableQueryParam) {
		try {
			Account account=accountService.getRedisAccount(request);
			if(StringUtils.isNotBlank(whereListMapJson))
			{
				List<Map<String, String>> tmpList = new ArrayList<Map<String,String>>();
				JSONArray jsonArray =  JSONArray.parseArray(whereListMapJson);
				for(int i=0;i<jsonArray.size();i++)
				{
				Map<String, String> map = (Map<String, String>)JSONUtils.parse(jsonArray.getString(i));
				tmpList.add(map);
				}
				bpmAdvTableQueryParam.setWhereListMap(tmpList);
			}
			bpmAdvTableQueryParam.setOrgId(account.getOrgId());
			if(StringUtils.isBlank(pageParam.getSort()))
			{
				pageParam.setSort("B.CREATE_TIME");
			}else
			{
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if(StringUtils.isBlank(pageParam.getSortOrder()))
			{
				pageParam.setSortOrder("DESC");
			}
			pageParam.setOrgId(account.getOrgId());
			pageParam.setOrderBy(pageParam.getSort()+ " " + pageParam.getSortOrder());
			return RetDataTools.Ok("请求成功！", bpmTableService.queryBpmList(pageParam,bpmAdvTableQueryParam));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: queryHtmlBi   
	 * @Description: TODO BPM高级查询
	 * @param: request
	 * @param: bpmAdvTableQueryParam
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryHtmlBi", method = RequestMethod.POST)
	public ModelAndView queryHtmlBi(HttpServletRequest request, String resFieldsJson,String whereListMapJson, BpmAdvTableQueryParam bpmAdvTableQueryParam) {
		ModelAndView mv=null;
		try {
			Account account=accountService.getRedisAccount(request);
			if(StringUtils.isNotBlank(resFieldsJson))
			{
				List<Map<String, String>> tmpList = new ArrayList<Map<String,String>>();
				JSONArray jsonArray =  JSONArray.parseArray(resFieldsJson);
				for(int i=0;i<jsonArray.size();i++)
				{
				Map<String, String> map = (Map<String, String>)JSONUtils.parse(jsonArray.getString(i));
				tmpList.add(map);
				}
				bpmAdvTableQueryParam.setResFields(tmpList);
			}
			if(StringUtils.isNotBlank(whereListMapJson))
			{
				List<Map<String, String>> tmpList = new ArrayList<Map<String,String>>();
				JSONArray jsonArray =  JSONArray.parseArray(whereListMapJson);
				for(int i=0;i<jsonArray.size();i++)
				{
				Map<String, String> map = (Map<String, String>)JSONUtils.parse(jsonArray.getString(i));
				tmpList.add(map);
				}
				bpmAdvTableQueryParam.setWhereListMap(tmpList);
			}
			bpmAdvTableQueryParam.setOrgId(account.getOrgId());
			mv = new ModelAndView("app/core/bpm/search/htmlbi");
			mv.addObject("flowId",bpmAdvTableQueryParam.getFlowId());
			mv.addObject("resFields",bpmAdvTableQueryParam.getResFields());
			mv.addObject("res",bpmTableService.queryHtmlBi(bpmAdvTableQueryParam));
			return mv;
			//return RetDataTools.Ok("请求成功！", bpmTableService.queryHtmlBi(bpmAdvTableQueryParam));
		} catch (Exception e) {
			e.printStackTrace();
			//return RetDataTools.Error(e.getMessage());
			 mv = new ModelAndView("titps");
			return mv;
		}
	}
	
	/**
	 * 
	 * @Title: getAllBpmSealSignList   
	 * @Description: TODO 获取签名列表
	 * @param: request
	 * @param: pageParam
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getAllBpmSealSignList",method=RequestMethod.POST)
	public RetDataBean getAllBpmSealSignList(
			HttpServletRequest request,
			PageParam pageParam
			)
	{
		try
		{
			if(StringUtils.isBlank(pageParam.getSort()))
			{
				pageParam.setSort("B.SORT_NO");
			}else
			{
				pageParam.setSort("B."+StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if(StringUtils.isBlank(pageParam.getSortOrder()))
			{
				pageParam.setSortOrder("asc");
			}
		Account account=accountService.getRedisAccount(request);
		pageParam.setOrgId(account.getOrgId());
		pageParam.setOrderBy(pageParam.getSort()+ " " + pageParam.getSortOrder());
		PageInfo<Map<String, String>> pageInfo=bpmSealSignService.getAllBpmSealSignList(pageParam);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getAllBpmFormListForSelect   
	 * @Description: TODO 系统所有的BPM表单 
	 * @param: request
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getAllBpmFormListForSelect",method=RequestMethod.POST)
	public RetDataBean getAllBpmFormListForSelect(HttpServletRequest request)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("您不是管理员,请与管理员联系!");
			}else
			{
			return RetDataTools.Ok("请求成功!", bpmFormService.getAllBpmFormListForSelect(account.getOrgId()));
			}
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: getRunLogList   
	 * @Description: TODO 获取BPM日志列表  
	 * @param: request
	 * @param: pageParam
	 * @param: createUser
	 * @param: beginTime
	 * @param: endTime
	 * @param: flowId
	 * @param: runId
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getRunLogList",method=RequestMethod.POST)
	public RetDataBean getRunLogList(
			HttpServletRequest request,
			PageParam pageParam,
			String createUser,
			String beginTime,
			String endTime,
			String flowId,
			String runId,
			String formId,
			String logType
			)
	{
		try
		{
			if(StringUtils.isBlank(pageParam.getSort()))
			{
				pageParam.setSort("CREATE_TIME");
			}else
			{
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if(StringUtils.isBlank(pageParam.getSortOrder()))
			{
				pageParam.setSortOrder("asc");
			}
			
		Account account=accountService.getRedisAccount(request);
		pageParam.setAccountId(account.getAccountId());
		pageParam.setOrgId(account.getOrgId());
		pageParam.setOrderBy(pageParam.getSort()+ " " + pageParam.getSortOrder());
		PageInfo<Map<String, String>> pageInfo=bpmRunLogService.getRunLogList(pageParam, createUser, beginTime, endTime, flowId, runId,logType,formId);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: getAllBpmProcessList   
	 * @Description: TODO 获取步骤列表
	 * @param: request
	 * @param: flowId
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getAllBpmProcessList",method=RequestMethod.POST)
	public RetDataBean getAllBpmProcessList(HttpServletRequest request,String flowId)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			return RetDataTools.Ok("请求成功!", bpmProcessService.getAllBpmProcessList(account.getOrgId(),flowId));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getBpmSealSignById   
	 * @Description: TODO 获取签章详情
	 * @param: request
	 * @param: bpmSealSign
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getBpmSealSignById",method=RequestMethod.POST)
	public RetDataBean getBpmSealSignById(HttpServletRequest request,BpmSealSign bpmSealSign)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			bpmSealSign.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求成功!", bpmSealSignService.selectOneBpmSealSign(bpmSealSign));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getRunPrcsStep   
	 * @Description: TODO 获取图形列表数据
	 * @param: request
	 * @param: runId
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getRunPrcsStep",method=RequestMethod.POST)
	public RetDataBean getRunPrcsStep(HttpServletRequest request,String runId)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			return RetDataTools.Ok("请求成功!", bpmRunProcessService.getRunPrcsStep(account.getOrgId(),runId));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getMyProcessListForDesk   
	 * @Description: TODO 获取桌面的待办BPM
	 * @param: request
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getMyProcessListForDesk",method=RequestMethod.POST)
	public RetDataBean getMyProcessListForDesk(HttpServletRequest request)
	{
		try
		{
			UserInfo userInfo = accountService.getRedisUserInfo(request);;
			String nowTime = SysTools.getTime("yyyy-MM-dd");
			return RetDataTools.Ok("请求成功!", bpmRunProcessService.getMyProcessListForDesk(userInfo.getOrgId(),userInfo.getAccountId(),nowTime));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: getBpmOpUserDept
	 * @Description: TODO BPM经办权限人员部门树结构
	 * @param: request
	 * @param: deptId
	 * @param: @return
	 * @return: List<Map<String,Object>>
	 * 
	 */
	@RequestMapping(value = "/getBpmOpUserDept", method = RequestMethod.POST)
	public List<Map<String, Object>> getBpmOpUserDept(HttpServletRequest request, String deptId,
			BpmProcess bpmProcess) {
		try {
			Account account=accountService.getRedisAccount(request);
			bpmProcess.setOrgId(account.getOrgId());
			bpmProcess = bpmProcessService.selectOne(bpmProcess);
			String orgLeaveId = "0";
			if (StringUtils.isNotBlank(deptId)) {
				orgLeaveId = deptId;
			}
			return bpmProcessService.getBpmOpUserDept(bpmProcess, orgLeaveId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @Title: getBpmOpUseByPorcess
	 * @Description: TODO 获取BPM 权限内部门中的人员
	 * @param @param request
	 * @param @param deptId
	 * @param @param bpmProcess
	 * @param @return 设定文件
	 * @return RetDataBean 返回类型
	 * 
	 */
	@RequestMapping(value = "/getBpmOpUseByPorcess", method = RequestMethod.POST)
	public RetDataBean getBpmOpUseByPorcess(HttpServletRequest request, String deptId, BpmProcess bpmProcess) {
		try {
			Account account=accountService.getRedisAccount(request);
			bpmProcess.setOrgId(account.getOrgId());
			bpmProcess = bpmProcessService.selectOne(bpmProcess);
			if (StringUtils.isBlank(deptId)) {
				deptId = "0";
			}
			return RetDataTools.Ok("请求成功！", bpmProcessService.getUserInfoByProcess(bpmProcess, deptId));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}

	/**
	 * 
	* @Title: getEntrustUser 
	* @Description: TODO 获取指定BPM流程步骤权限内的所有人员
	* @param @param request
	* @param @param bpmRunProcess
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value = "/getEntrustUser", method = RequestMethod.POST)
	public RetDataBean getEntrustUser(HttpServletRequest request, BpmRunProcess bpmRunProcess) {
		try {
			Account account=accountService.getRedisAccount(request);
			bpmRunProcess.setOrgId(account.getOrgId());
			bpmRunProcess = bpmRunProcessService.selectOne(bpmRunProcess);
			return RetDataTools.Ok("请求成功！", bpmProcessService.getEntrustUser(bpmRunProcess));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getBpmSortTree
	 * @Description: TODO 获取工作流分类树
	 * @param request
	 * @param leaveId
	 * @return
	 * @return: List<Map<String,Object>>
	 * 
	 */
	@RequestMapping(value = "/getBpmSortTree", method = RequestMethod.POST)
	public List<Map<String, Object>> getBpmSortTree(HttpServletRequest request, String bpmSortId) {
		String levelId = "0";
		try {
			if (StringUtils.isNotBlank(bpmSortId)) {
				levelId = bpmSortId;
			}
			Account account=accountService.getRedisAccount(request);
			return bpmSortService.getBpmSortTree(levelId, account.getOrgId());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @Title: getBpmFormSortTree
	 * @Description: TODO 获取表单分类
	 * @param request
	 * @param bpmSortId
	 * @return
	 * @return: List<Map<String,Object>>
	 * 
	 */
	@RequestMapping(value = "/getBpmFormSortTree", method = RequestMethod.POST)
	public List<Map<String, Object>> getBpmFormSortTree(HttpServletRequest request, String bpmSortId) {
		String levelId = "0";
		try {
			List<Map<String, Object>> tmplist1 = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> tmplist2 = new ArrayList<Map<String, Object>>();
			Account account=accountService.getRedisAccount(request);
			if (StringUtils.isNotBlank(bpmSortId)) {
				levelId = bpmSortId;
				if (!levelId.equals("0")) {
					tmplist1 = bpmFormService.getBpmFormTree(bpmSortId, account.getOrgId());
				}
			}
			tmplist2 = bpmSortService.getBpmFormSortTree(levelId, account.getOrgId());
			tmplist2.addAll(tmplist1);
			return tmplist2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @Title: getBpmSortById
	 * @Description: TODO 获取对应分类信息
	 * @param request
	 * @param bpmSort
	 * @return
	 * @return: RetDataBean
	 * 
	 */
	@RequestMapping(value = "/getBpmSortById", method = RequestMethod.POST)
	public RetDataBean getBpmSortById(HttpServletRequest request, BpmSort bpmSort) {
		try {
			Account account=accountService.getRedisAccount(request);
			bpmSort.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求成功！", bpmSortService.selectOne(bpmSort));
		} catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: getBpmForm
	 * @Description: TODO 获取表单信息
	 * @param request
	 * @param bpmForm
	 * @return
	 * @return: RetDataBean
	 * 
	 */
	@RequestMapping(value = "/getBpmForm", method = RequestMethod.POST)
	public RetDataBean getBpmForm(HttpServletRequest request, BpmForm bpmForm) {
		try {
			Account account=accountService.getRedisAccount(request);
			bpmForm.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求成功！", bpmFormService.selectOneBpmForm(bpmForm));
		} catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: getBpmFormSortTree
	 * @Description: TODO 获取表单分类
	 * @param request
	 * @return
	 * @return: List<Map<String,Object>>
	 * 
	 */
	@RequestMapping(value = "/getBpmFlowSortTree", method = RequestMethod.POST)
	public List<Map<String, String>> getBpmFlowSortTree(HttpServletRequest request) {
		try {
			Account account=accountService.getRedisAccount(request);
			BpmFlow bpmFlow = new BpmFlow();
			bpmFlow.setOrgId(account.getOrgId());
			List<BpmFlow> bpmFlowList = new ArrayList<BpmFlow>();
			bpmFlowList = bpmFlowService.getAllBpmFlowList(bpmFlow);
			return bpmSortService.getAllBpmFlowSort(bpmFlowList);
		} catch (Exception e) {
			return null;
		}
	}
/**
 * 
* @Title: getAllBpmFlowList 
* @Description: TODO 获取所有BPM流程
* @param @param request
* @param @return 设定文件 
* @return RetDataBean 返回类型
 */
	@RequestMapping(value = "/getAllBpmFlowListByManage", method = RequestMethod.POST)
	public RetDataBean getAllBpmFlowListByManage(HttpServletRequest request) {
		try {
			Account account=accountService.getRedisAccount(request);
			if(account.getOpFlag().equals("1"))
			{
				return RetDataTools.Ok("请求成功！", bpmFlowService.getAllBpmFlowListByManage(account.getOrgId()));
			}else
			{
				return RetDataTools.Ok("请求成功！", bpmFlowService.getBpmFlowInByManage(account.getOrgId(),account.getOrgId()));
			}
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: getAllBpmFlowList   
	 * @Description: TODO 获取所有的流程列表
	 * @param: request
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value = "/getAllBpmFlowList", method = RequestMethod.POST)
	public RetDataBean getAllBpmFlowList(HttpServletRequest request) {
		try {
			Account account=accountService.getRedisAccount(request);
			return RetDataTools.Ok("请求成功！", bpmFlowService.getAllBpmFlowListByManage(account.getOrgId()));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getQueryAdvFlowList   
	 * @Description: TODO 获取个人可高级查询的流程
	 * @param: request
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value = "/getQueryAdvFlowList", method = RequestMethod.POST)
	public RetDataBean getQueryAdvFlowList(HttpServletRequest request) {
		try {
			Account account=accountService.getRedisAccount(request);
			if(account.getOpFlag().equals("1"))
			{
				return RetDataTools.Ok("请求成功！", bpmFlowService.getQueryAdvFlowList(account.getOrgId(),null));
			}else
			{
				return RetDataTools.Ok("请求成功！", bpmFlowService.getQueryAdvFlowList(account.getOrgId(),account.getAccountId()));
			}
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title getBpmFlow
	 * @Description TODO 获取流程信息
	 * @param request
	 * @param bpmFlow
	 * @return RetDataBean
	 */
	@RequestMapping(value = "/getBpmFlow", method = RequestMethod.POST)
	public RetDataBean getBpmFlow(HttpServletRequest request, BpmFlow bpmFlow) {
		try {
			Account account=accountService.getRedisAccount(request);
			bpmFlow.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求成功！", bpmFlowService.selectOne(bpmFlow));
		} catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}

	/**
	 * 
	 * @Title getBpmFlow
	 * @Description TODO 获取流程信息
	 * @param request
	 * @param bpmFlow
	 * @return RetDataBean
	 */
	@RequestMapping(value = "/getBpmProcess", method = RequestMethod.POST)
	public RetDataBean getBpmProcess(HttpServletRequest request, BpmProcess bpmProcess) {
		Account account=accountService.getRedisAccount(request);
			return bpmProcessService.getBpmProcess(account, bpmProcess);
	}

	/**
	 * 
	 * @Title getBpmProcessPrcs
	 * @Description TODO 获取BPM步骤信息
	 * @param request
	 * @param bpmProcess
	 * @return RetDataBean
	 */
	@RequestMapping(value = "/getBpmProcessPrcs", method = RequestMethod.POST)
	public RetDataBean getBpmProcessPrcs(HttpServletRequest request, BpmProcess bpmProcess) {
		try {
			Account account=accountService.getRedisAccount(request);
			bpmProcess.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求成功！", bpmProcessService.selectOne(bpmProcess));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}

	/**
	 * 
	 * @Title getBpmFormField
	 * @Description TODO 获取表单所有的字段
	 * @param request
	 * @param bpmProcess
	 * @return RetDataBean
	 */
	@RequestMapping(value = "/getBpmFormFieldByFlowId", method = RequestMethod.POST)
	public RetDataBean getBpmFormFieldByFlowId(HttpServletRequest request, BpmFlow bpmFlow) {
		try {
			Account account=accountService.getRedisAccount(request);
			bpmFlow.setOrgId(account.getOrgId());
			bpmFlow = bpmFlowService.selectOne(bpmFlow);
			BpmForm bpmForm = new BpmForm();
			bpmForm.setFormId(bpmFlow.getFormId());
			bpmForm.setOrgId(bpmFlow.getOrgId());
			bpmForm = bpmFormService.selectOneBpmForm(bpmForm);
			BpmTemplateUnit btu = new BpmTemplateUnit();
			List<BpmTableBean> btbList = new ArrayList<BpmTableBean>();
			btbList = btu.getBpmElement(bpmForm);
			return RetDataTools.Ok("请求成功！", btbList);
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}

	/**
	 * 
	 * @Title getBpmProcessPrcs
	 * @Description TODO 获取权限内的BPM流程
	 * @param request
	 * @param bpmProcess
	 * @return RetDataBean
	 */
	@RequestMapping(value = "/getBpmFlowListByPriv", method = RequestMethod.POST)
	public List<Map<String,String>> getBpmFlowListByPriv(HttpServletRequest request) {
		try {
			UserInfo userInfo = accountService.getRedisUserInfo(request);;
			List<BpmFlow> bpmFlowList = bpmFlowService.getBpmFlowListByAccount(userInfo.getAccountId(),userInfo.getDeptId(), userInfo.getLeadLeave(), userInfo.getOrgId());
			return bpmFlowService.getBpmFlowListByPriv(bpmFlowList);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getCommonBpmFlowListByAccount   
	 * @Description: TODO 获取前10个个人常用流程
	 * @param request
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value = "/getCommonBpmFlowListByAccount", method = RequestMethod.POST)
	public RetDataBean getCommonBpmFlowListByAccount(HttpServletRequest request) {
		try {
			Account account=accountService.getRedisAccount(request);
			List<BpmFlow> bpmFlowList = bpmFlowService.getCommonBpmFlowListByAccount(account.getOrgId(),account.getAccountId());
			return RetDataTools.Ok("请求成功！", bpmFlowList);
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}

	/**
	 * 
	 * @Title getBpmFormFieldByFormId
	 * @Description TODO 按FormId获取表单所有字段名
	 * @param request
	 * @param formId
	 * @return RetDataBean
	 */
	@RequestMapping(value = "/getBpmFormFieldByFormId", method = RequestMethod.POST)
	public RetDataBean getBpmFormFieldByFormId(HttpServletRequest request, String formId) {
		try {
			Account account=accountService.getRedisAccount(request);
			BpmForm bpmForm = new BpmForm();
			bpmForm.setFormId(formId);
			bpmForm.setOrgId(account.getOrgId());
			bpmForm = bpmFormService.selectOneBpmForm(bpmForm);
			return RetDataTools.Ok("请求成功！", bpmTableService.getAllFormField(bpmForm));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
/**
 * 
* @Title: getNowStep 
* @Description: TODO 获取当前BPM处于什么步骤
* @param @param request
* @param @param runId
* @param @return 设定文件 
* @return RetDataBean 返回类型
 */
	@RequestMapping(value = "/getNowStep", method = RequestMethod.POST)
	public RetDataBean getNowStep(HttpServletRequest request, String runId) {
		try {
			Account account=accountService.getRedisAccount(request);
			String orgId = account.getOrgId();
			List<Map<String, String>> btbList = bpmRunProcessService.getNowStep(orgId, runId);
			return RetDataTools.Ok("请求成功！", btbList);
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	* @Title: getChangeBpmUserStep 
	* @Description: TODO 获取可转交的BPM步骤信息
	* @param @param request
	* @param @param runId
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value = "/getChangeBpmUserStep", method = RequestMethod.POST)
	public RetDataBean getChangeBpmUserStep(HttpServletRequest request, String runId) {
		try {
			Account account=accountService.getRedisAccount(request);
			String orgId = account.getOrgId();
			List<Map<String, String>> btbList = bpmRunProcessService.getChangeBpmUserStep(orgId, runId);
			return RetDataTools.Ok("请求成功！", btbList);
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}	
	/**
	 * 
	 * @Title getGoProcessList
	 * @Description TODO 获取待办BPM列表
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @param search
	 * @param sort
	 * @param sortOrder
	 * @return RetDataBean
	 */
	@RequestMapping(value = "/getGoProcessList", method = RequestMethod.POST)
	public RetDataBean getGoProcessList(HttpServletRequest request, Integer pageNumber, Integer pageSize, String search,
			String sort, String sortOrder,
			Integer bpmRunNo,
			String createUser,
			String flowId
			) {
		try {
			if (StringUtils.isBlank(sort)) {
				sort = "B.ID";
			} else {
				sort = "B." + StrTools.upperCharToUnderLine(sort);
			}
			if (StringUtils.isBlank(sortOrder)) {
				sortOrder = "asc";
			}
			String orderBy = sort + " " + sortOrder;
			Account account=accountService.getRedisAccount(request);
			PageInfo<Map<String, Object>> pageInfo = bpmRunProcessService.getGoProcessList(pageNumber, pageSize,
					orderBy, account.getAccountId(), account.getOrgId(),bpmRunNo, createUser,flowId,"%" + search + "%");
			return RetDataTools.Ok("请求数据成功!", pageInfo);
		} catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}

	/**
	 * 
	 * @Title getProcessInfo
	 * @Description TODO 后台生成表单内容
	 * @param request
	 * @param bpmList
	 * @param bpmRunProcess
	 * @return RetDataBean
	 */
	@Transactional(value = "generalTM")
	@RequestMapping(value = "/getProcessInfo", method = RequestMethod.POST)
	public RetDataBean getProcessInfo(HttpServletRequest request, BpmList bpmList, BpmRunProcess bpmRunProcess) {
		try {
			Account account=accountService.getRedisAccount(request);
			bpmList.setOrgId(account.getOrgId());
			bpmList = bpmListService.selectOne(bpmList);
			bpmRunProcess=bpmRunProcessService.getBpmRunProcess(account.getOrgId(),bpmRunProcess.getRunId(),bpmRunProcess.getRunProcessId(),account.getAccountId());
			return RetDataTools.Ok("请求成功！",bpmFormDataService.getProcessInfo(bpmList, bpmRunProcess));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: getBpmPrintData 
	* @Description: TODO 获取BPM流程详情
	* @param @param request
	* @param @param bpmList
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@Transactional(value = "generalTM")
	@RequestMapping(value = "/getBpmPrintData", method = RequestMethod.POST)
	public RetDataBean getBpmPrintData(HttpServletRequest request, BpmList bpmList) {
		try {
			Account account=accountService.getRedisAccount(request);
			bpmList.setOrgId(account.getOrgId());
			bpmList = bpmListService.selectOne(bpmList);
			if(SysTools.isMobileDevice(request))
			{
				return RetDataTools.Ok("请求成功！",bpmFormDataService.getFormMPrintData(request, account, bpmList));
			}else
			{
				return RetDataTools.Ok("请求成功！",bpmFormDataService.getFormPrintData(request, account, bpmList));
			}
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}

	/**
	 * 
	 * @Title getNextPrcs
	 * @Description TODO 获取下一步相关信息
	 * @param request
	 * @param bpmList
	 * @param bpmProcess
	 * @return RetDataBean
	 */
	@RequestMapping(value = "/getNextPrcs", method = RequestMethod.POST)
	public RetDataBean getNextPrcs(HttpServletRequest request, String runId, String oldProcessId,String runProcessId) {
		return bpmLogicCpuService.getNextPrcs(request, runId,oldProcessId,runProcessId);
	}

	/**
	 * 
	 * @Title searchBpmList
	 * @Description TODO 获取待办BPM列表
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @param search
	 * @param sort
	 * @param sortOrder
	 * @return RetDataBean
	 */
	@RequestMapping(value = "/searchBpmList", method = RequestMethod.POST)
	public RetDataBean searchBpmList(HttpServletRequest request, PageParam pageParam,
			String flowId,
			Integer id, String managePriv,
			String status,String createUser) 
	{
		try {
			if (StringUtils.isBlank(pageParam.getSort())) {
				pageParam.setSort("B.ID");
			} else {
				pageParam.setSort("B." + StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if (StringUtils.isBlank(pageParam.getSortOrder())) {
				pageParam.setSortOrder("asc");
			}
			String orderBy = pageParam.getSort() + " " + pageParam.getSortOrder();
			pageParam.setOrderBy(orderBy);
			Account account=accountService.getRedisAccount(request);
			pageParam.setOrgId(account.getOrgId());
			pageParam.setAccountId(account.getAccountId());
			PageInfo<Map<String, Object>> pageInfo = bpmListService.searchBpmList(pageParam,flowId,id,createUser,status,managePriv);
			return RetDataTools.Ok("请求数据成功!", pageInfo);
		} catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: getMyEntrustList
	 * @Description: TODO 获取当前用户已委托的流程列表
	 * @param @param request
	 * @param @param pageParam
	 * @param @return 设定文件
	 * @return RetDataBean 返回类型
	 */
	@RequestMapping(value = "/getMyEntrustList", method = RequestMethod.POST)
	public RetDataBean getMyEntrustList(HttpServletRequest request, PageParam pageParam) {
		try {
			if (StringUtils.isBlank(pageParam.getSort())) {
				pageParam.setSort("E.CREATE_TIME");
			} else {
				pageParam.setSort("E."+StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if (StringUtils.isBlank(pageParam.getSortOrder())) {
				pageParam.setSortOrder("ASC");
			}
			Account account=accountService.getRedisAccount(request);
			String orderBy = pageParam.getSort() + " " + pageParam.getSortOrder();
			pageParam.setAccountId(account.getAccountId());
			pageParam.setOrgId(account.getOrgId());
			pageParam.setOrderBy(orderBy);
			PageInfo<Map<String, String>> pageInfo = bpmEntrustService.getBpmEntrustList(pageParam);
			return RetDataTools.Ok("请求数据成功!", pageInfo);
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}

	/**
	 * 
	* @Title: getMyAllBpmFlowList 
	* @Description: TODO 获取当前用户所有需审批的流程
	* @param @param request
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value = "/getMyAllBpmFlowList", method = RequestMethod.POST)
	public RetDataBean getMyAllBpmFlowList(HttpServletRequest request) {
		try {
			UserInfo userInfo = accountService.getRedisUserInfo(request);
			return RetDataTools.Ok("请求成功！", bpmFlowService.getMyAllBpmFlowList(userInfo.getOrgId(), userInfo.getAccountId(), userInfo.getDeptId(), userInfo.getLeadLeave()));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: getCorssBpmFlowList 
	* @Description: TODO 获取当用户参与的流程列表
	* @param @param request
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value = "/getCorssBpmFlowList", method = RequestMethod.POST)
	public RetDataBean getCorssBpmFlowList(HttpServletRequest request) {
		try {
			Account account=accountService.getRedisAccount(request);
			return RetDataTools.Ok("请求成功！", bpmFlowService.getCorssBpmFlowList(account.getOrgId(),account.getAccountId()));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
/**
 * 
 * @Title: getMyEndBpmFlowList   
 * @Description: TODO 获取已结束的BPM流程
 * @param: request
 * @param: pageParam
 * @param: beginTime
 * @param: endTime
 * @param: flowId
 * @param: @return      
 * @return: RetDataBean      
 * @throws
 */
	@RequestMapping(value="/getMyEndBpmFlowList",method=RequestMethod.POST)
	public RetDataBean getMyEndBpmFlowList(
			HttpServletRequest request,
			PageParam pageParam,
			String range,
			String beginTime,
			String endTime,
			String flowId
			)
	{
		try
		{
			if(StringUtils.isBlank(pageParam.getSort()))
			{
				pageParam.setSort("CREATE_TIME");
			}else
			{
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if(StringUtils.isBlank(pageParam.getSortOrder()))
			{
				pageParam.setSortOrder("asc");
			}
		Account account=accountService.getRedisAccount(request);
		String orderBy = pageParam.getSort()+ " " + pageParam.getSortOrder();
		pageParam.setAccountId(account.getAccountId());
		pageParam.setOrgId(account.getOrgId());
		pageParam.setOrderBy(orderBy);
		PageInfo<Map<String, String>> pageInfo = null;
		pageInfo = bpmListService.getMyEndCrossBpmFlowList(pageParam,flowId, beginTime, endTime,range);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}	

	/**
	 * 
	* @Title: getProcessBpmFlowList 
	* @Description: TODO 获取进程中流程
	* @param @param request
	* @param @param sortId
	* @param @param pageNumber
	* @param @param pageSize
	* @param @param search
	* @param @param sort
	* @param @param sortOrder
	* @param @param pBeginTime
	* @param @param pEndTime
	* @param @param pFlowId
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value="/getProcessBpmFlowList",method=RequestMethod.POST)
	public RetDataBean getProcessBpmFlowList(
			HttpServletRequest request,
			PageParam pageParam,
			Integer pBpmRunNo,
			String pBeginTime,
			String pEndTime,
			String pFlowId
			)
	{
		try
		{
			if(StringUtils.isBlank(pageParam.getSort()))
			{
				pageParam.setSort("CREATE_TIME");
			}else
			{
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if(StringUtils.isBlank(pageParam.getSortOrder()))
			{
				pageParam.setSortOrder("asc");
			}
		Account account=accountService.getRedisAccount(request);
		String orderBy = pageParam.getSort()+ " " + pageParam.getSortOrder();
		pageParam.setAccountId(account.getAccountId());
		pageParam.setOrgId(account.getOrgId());
		pageParam.setOrderBy(orderBy);
		PageInfo<Map<String, String>> pageInfo = bpmListService.getProcessBpmFlowList(pageParam, pBpmRunNo, pFlowId, pBeginTime, pEndTime);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}

	/**
	 * 
	* @Title: getBpmMaintainList 
	* @Description: TODO 获取监控权限内的流程列表
	* @param @param request
	* @param @param sortId
	* @param @param pageNumber
	* @param @param pageSize
	* @param @param search
	* @param @param sort
	* @param @param sortOrder
	* @param @param bpmRunNo
	* @param @param beginTime
	* @param @param endTime
	* @param @param flowId
	* @param @param createUser
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value="/getBpmMaintainList",method=RequestMethod.POST)
	public RetDataBean getBpmMaintainList(
			HttpServletRequest request,
			String sortId,
			Integer pageNumber,
			Integer pageSize,
			String search,
			String sort,
			String sortOrder,
			Integer bpmRunNo,
			String beginTime,
			String endTime,
			String flowId,
			String createUser
			)
	{
		try
		{
			if(StringUtils.isBlank(sort))
			{
				sort="CREATE_TIME";
			}else
			{
				sort=StrTools.upperCharToUnderLine(sort);
			}
			if(StringUtils.isBlank(sortOrder))
			{
				sortOrder="asc";
			}
		Account account=accountService.getRedisAccount(request);
		String orderBy = sort+ " " + sortOrder;
		PageInfo<Map<String, String>> pageInfo =  bpmListService.getBpmMaintainList(pageNumber, pageSize, orderBy,  account.getOrgId(),bpmRunNo,createUser, flowId, beginTime, endTime, "%"+search+"%");
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getMyBpmFlowList   
	 * @Description: TODO 获取发起BPM列表
	 * @param: request
	 * @param: pageParam
	 * @param: flowSort
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value = "/getMyBpmFlowList", method = RequestMethod.POST)
	public RetDataBean getMyBpmFlowList(HttpServletRequest request, 
			PageParam pageParam,
			String flowSort
			) {
		try {
			if (StringUtils.isBlank(pageParam.getSort())) {
				pageParam.setSort("B.SORT_NO");
			} else {
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if (StringUtils.isBlank(pageParam.getSortOrder())) {
				pageParam.setSortOrder("asc");
			}
			String orderBy = pageParam.getSort() + " " + pageParam.getSortOrder();
			pageParam.setOrderBy(orderBy);
			UserInfo userInfo = accountService.getRedisUserInfo(request);;
			pageParam.setOrgId(userInfo.getOrgId());
			pageParam.setAccountId(userInfo.getAccountId());
			PageInfo<Map<String, String>> pageInfo = bpmFlowService.getMyBpmFlowList(pageParam,flowSort,userInfo.getDeptId(),userInfo.getLeadLeave());
			return RetDataTools.Ok("请求数据成功!", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	* @Title: getXBpmSelectList 
	* @Description: TODO 获取备选BPM列表  
	* @param @param request
	* @param @param sortId
	* @param @param pageNumber
	* @param @param pageSize
	* @param @param searchbpm
	* @param @param sort
	* @param @param sortOrder
	* @param @param runId
	* @param @param searchType
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value="/getXBpmSelectList",method=RequestMethod.POST)
	public RetDataBean getXBpmSelectList(
			HttpServletRequest request,
			String sortId,
			Integer pageNumber,
			Integer pageSize,
			String searchbpm,
			String sort,
			String sortOrder,
			String runId,
			String searchType
			)
	{
		try
		{
			if(StringUtils.isBlank(sort))
			{
				sort="CREATE_TIME";
			}else
			{
				sort=StrTools.upperCharToUnderLine(sort);
			}
			if(StringUtils.isBlank(sortOrder))
			{
				sortOrder="desc";
			}
		Account account=accountService.getRedisAccount(request);
		String orderBy = sort+ " " + sortOrder;
		PageInfo<Map<String, String>> pageInfo = new PageInfo<Map<String, String>>();
		if(searchType.equals("1"))
		{
			pageInfo = bpmListService.getBpmSelectMyCreateList(pageNumber, pageSize, orderBy, account.getOrgId(), account.getAccountId(), "%"+searchbpm+"%");
		}else if(searchType.equals("2"))
		{
			pageInfo = bpmListService.getBpmSelectMyCorssList(pageNumber, pageSize, orderBy, account.getOrgId(), account.getAccountId(), "%"+searchbpm+"%");
		}else if(searchType.equals("3"))
		{
			pageInfo = bpmListService.getBpmSelectInManageList(pageNumber, pageSize, orderBy,account.getOrgId(), account.getAccountId(), "%"+searchbpm+"%");
		}else if(searchType.equals("4"))
		{
			BpmList bpmList = new BpmList();
			bpmList.setRunId(runId);
			bpmList.setOrgId(account.getOrgId());
			bpmList = bpmListService.selectOne(bpmList);
			pageInfo = bpmListService.getBpmSelectInFlowIdList(pageNumber, pageSize, orderBy, account.getOrgId(), bpmList.getFlowId(), "%"+searchbpm+"%");
		}
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	* @Title: getBpmRunProcessForMonitor 
	* @Description: TODO获取BPM流程监控列表 
	* @param @param request
	* @param @param pageNumber
	* @param @param pageSize
	* @param @param search
	* @param @param sort
	* @param @param sortOrder
	* @param @param flowId
	* @param @param id
	* @param @param beginTime
	* @param @param endTime
	* @param @param createUser
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value = "/getBpmRunProcessForMonitor", method = RequestMethod.POST)
	public RetDataBean getBpmRunProcessForMonitor(HttpServletRequest request, 
			Integer pageNumber, 
			Integer pageSize, 
			String search,
			String sort, 
			String sortOrder, 
			String flowId,
			String id, 
			String beginTime,
			String endTime,
			String createUser) 
	{
		try {
			if (StringUtils.isBlank(sort)) {
				sort = "R.RUN_ID";
			} else {
				sort = "R." + StrTools.upperCharToUnderLine(sort);
			}
			if (StringUtils.isBlank(sortOrder)) {
				sortOrder = "asc";
			}
			String orderBy = sort + " " + sortOrder;
			Account account=accountService.getRedisAccount(request);
			PageInfo<Map<String, String>> pageInfo = bpmRunProcessService.getBpmRunProcessForMonitor(pageNumber, pageSize, orderBy,
					account.getOrgId(),account.getOpFlag(),account.getAccountId(),id, createUser, beginTime, endTime, flowId ,"%" + search + "%");
			return RetDataTools.Ok("请求数据成功!", pageInfo);
		} catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: getDocNumByBpmFlow 
	* @Description: TODO 生成BPM文号
	* @param @param request
	* @param @param bpmFlow
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value = "/getDocNumByBpmFlow", method = RequestMethod.POST)
	public RetDataBean getDocNumByBpmFlow(HttpServletRequest request,BpmFlow bpmFlow) {
		try {
			Account account=accountService.getRedisAccount(request);
			bpmFlow.setOrgId(account.getOrgId());
			bpmFlow = bpmFlowService.selectOne(bpmFlow);
			return RetDataTools.Ok("请求成功！", bpmUnitsService.getDocNumByBpmFlow(request, bpmFlow));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}	
	
	
	/**
	 * 
	 * @Title getBpmChileTableData
	 * @Description TODO 按权限获取BPM子表数据
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @param search
	 * @param sort
	 * @param sortOrder
	 * @return RetDataBean
	 */
	@RequestMapping(value = "/getBpmChileTableData", method = RequestMethod.POST)
	public RetDataBean getBpmChileTableData(HttpServletRequest request, String eName, String runId) {
		try {
			Account account=accountService.getRedisAccount(request);
			BpmList bpmList = new BpmList();
			bpmList.setRunId(runId);
			bpmList.setOrgId(account.getOrgId());
			bpmList = bpmListService.selectOne(bpmList);
			BpmFlow bpmFlow = new BpmFlow();
			bpmFlow.setOrgId(bpmList.getOrgId());
			bpmFlow.setFlowId(bpmList.getFlowId());
			bpmFlow = bpmFlowService.selectOne(bpmFlow);
			BpmForm bpmForm = new BpmForm();
			bpmForm.setOrgId(bpmFlow.getOrgId());
			bpmForm.setFormId(bpmFlow.getFormId());
			bpmForm = bpmFormService.selectOneBpmForm(bpmForm);
			PageInfo<Map<String, String>> pageInfo=bpmOptService.getChildTableData(bpmForm,eName,account.getOrgId(), runId);
			return RetDataTools.Ok("请求数据成功!", pageInfo);
		} catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	* @Title: getAlternativePrcsList 
	* @Description: TODO 获取备选步骤列表
	* @param @param request
	* @param @param flowId
	* @param @param processIds
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value = "/getAlternativePrcsList", method = RequestMethod.POST)
	public RetDataBean getAlternativePrcsList(HttpServletRequest request, String flowId, String processId,String processIds) {
		try {
			if(StringUtils.isNotBlank(flowId)&&StringUtils.isNotBlank(processId))
			{
				Account account=accountService.getRedisAccount(request);
			String[] prcsArr;
			if(processIds.indexOf(",")>0)
			{
				prcsArr = processIds.split(",");
			}else
			{
				prcsArr = new String [] {processIds};
			}
			List<String> list = new ArrayList<String>();
			list = Arrays.asList(prcsArr);
			return RetDataTools.Ok("请求数据成功!",bpmProcessService.getAlternativePrcsList(account.getOrgId(), flowId,processId, list));
			}else
			{
				return RetDataTools.NotOk("参数格式有问题!"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getCanTaskBackRunProcessList   
	 * @Description: TODO 获取BPM可收回的步骤列表
	 * @param: request
	 * @param: runId
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value = "/getCanTaskBackRunProcessList", method = RequestMethod.POST)
	public RetDataBean getCanTaskBackRunProcessList(HttpServletRequest request, String runId) {
		try {
			Account account=accountService.getRedisAccount(request);
			return RetDataTools.Ok("请求数据成功!", bpmRunProcessService.getCanTaskBackRunProcessList(account.getOrgId(), runId, account.getAccountId()));
		} catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getCanGoBckProcessList   
	 * @Description: TODO 获取可以回退的步骤列表
	 * @param: request
	 * @param: runId
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value = "/getCanGoBckProcessList", method = RequestMethod.POST)
	public RetDataBean getCanGoBckProcessList(HttpServletRequest request, String runId) {
		try {
			Account account=accountService.getRedisAccount(request);
			return RetDataTools.Ok("请求数据成功!", bpmRunProcessService.getCanGoBckProcessList(account.getOrgId(), runId));
		} catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getBpmFormCacheFile   
	 * @Description: TODO 获取缓存文件
	 * @param request
	 * @param type
	 * @param bpmProcess
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value = "/getBpmFormCacheFile", method = RequestMethod.POST)
	public RetDataBean getBpmFormCacheFile(HttpServletRequest request, String type,BpmProcess bpmProcess) {
		try {
			Account account=accountService.getRedisAccount(request);
			if(account.getOpFlag().equals("1"))
			{
			JSONObject jsonObject = new JSONObject();
			bpmProcess.setOrgId(account.getOrgId());
			bpmProcess = bpmProcessService.selectOne(bpmProcess);
			//BpmForm bpmForm = bpmFormCacheService.getBpmFormCacheFile(bpmProcess);
			if(type.equals("html"))
			{
				jsonObject.put("prcsName", bpmProcess.getPrcsName());
				jsonObject.put("value", bpmFormCacheService.getHtmlCache(bpmProcess));
				return RetDataTools.Ok("请求数据成功!", jsonObject);
			}else if(type.equals("style"))
			{
				jsonObject.put("prcsName", bpmProcess.getPrcsName());
				jsonObject.put("value", bpmFormCacheService.getStyleCache(bpmProcess));
				return RetDataTools.Ok("请求数据成功!", jsonObject);
			}else if(type.equals("script"))
			{
				jsonObject.put("prcsName", bpmProcess.getPrcsName());
				jsonObject.put("value", bpmFormCacheService.getScriptCache(bpmProcess));
				return RetDataTools.Ok("请求数据成功!", jsonObject);
			}else if(type.equals("mobilehtml"))
			{
				jsonObject.put("prcsName", bpmProcess.getPrcsName());
				jsonObject.put("value", bpmFormCacheService.getMobileHtmlCache(bpmProcess));
				return RetDataTools.Ok("请求数据成功!", jsonObject);
			}else if(type.equals("mobilestyle"))
			{
				jsonObject.put("prcsName", bpmProcess.getPrcsName());
				jsonObject.put("value", bpmFormCacheService.getMobileStyleCache(bpmProcess));
				return RetDataTools.Ok("请求数据成功!", jsonObject);
			}else if(type.equals("mobilescript"))
			{
				jsonObject.put("prcsName", bpmProcess.getPrcsName());
				jsonObject.put("value", bpmFormCacheService.getMobileScriptCache(bpmProcess));
				return RetDataTools.Ok("请求数据成功!", jsonObject);
			}else {
				return RetDataTools.NotOk("请求的参数有问题，请与管理员联系！");
			}
			}else
			{
				return RetDataTools.NotOk("您不是管理员，请与管理员联系！");
			}
		} catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
}
