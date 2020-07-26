package com.core136.controller.document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.core136.common.retdataunit.RetDataBean;
import org.core136.common.retdataunit.RetDataTools;
import org.core136.common.utils.StrTools;
import org.core136.common.utils.SysTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.core136.bean.account.Account;
import com.core136.bean.account.UserInfo;
import com.core136.bean.document.DocumentFlow;
import com.core136.bean.document.DocumentList;
import com.core136.bean.document.DocumentNumber;
import com.core136.bean.document.DocumentPluginsRegister;
import com.core136.bean.document.DocumentChildProcess;
import com.core136.bean.document.DocumentForm;
import com.core136.bean.document.DocumentProcess;
import com.core136.bean.document.DocumentRunProcess;
import com.core136.bean.document.DocumentSort;
import com.core136.bean.document.DocumentTemplate;
import com.core136.bean.sys.PageParam;
import com.core136.service.account.AccountService;
import com.core136.service.document.DocumentChildProcessService;
import com.core136.service.document.DocumentEntrustService;
import com.core136.service.document.DocumentFlowService;
import com.core136.service.document.DocumentFormDataService;
import com.core136.service.document.DocumentFormService;
import com.core136.service.document.DocumentListFileService;
import com.core136.service.document.DocumentListService;
import com.core136.service.document.DocumentLogicCpuService;
import com.core136.service.document.DocumentMobileFormService;
import com.core136.service.document.DocumentNumberService;
import com.core136.service.document.DocumentOptService;
import com.core136.service.document.DocumentPluginsRegisterService;
import com.core136.service.document.DocumentProcessService;
import com.core136.service.document.DocumentRunLogService;
import com.core136.service.document.DocumentRunProcessService;
import com.core136.service.document.DocumentSendToService;
import com.core136.service.document.DocumentSortService;
import com.core136.service.document.DocumentTableService;
import com.core136.service.document.DocumentTemplateService;
import com.core136.service.document.DocumentUnitsService;
import com.core136.unit.document.DocumentTableBean;
import com.core136.unit.document.DocumentTemplateUnit;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/ret/documentget")
public class RoutGetDocumentController {
	@Autowired
	private DocumentSortService documentSortService;
	@Autowired
	private DocumentFormService documentFormService;
	@Autowired
	private DocumentTableService documentTableService;
	@Autowired
	private DocumentFlowService documentFlowService;
	@Autowired
	private DocumentProcessService documentProcessService;
	@Autowired
	private DocumentChildProcessService documentChildProcessService;
	@Autowired
	private DocumentPluginsRegisterService documentPluginsRegisterService;
	@Autowired
	private DocumentUnitsService documentUnitsService;
	@Autowired
	private DocumentListService documentListService;
	@Autowired
	private DocumentRunProcessService documentRunProcessService;
	@Autowired
	private DocumentFormDataService documentFormDataService;
	@Autowired
	private DocumentLogicCpuService documentLogicCpuService;
	@Autowired
	private DocumentOptService documentOptService;
	@Autowired
	private DocumentTemplateService documentTemplateService;
	@Autowired
	private DocumentRunLogService documentRunLogService;
	@Autowired
	private DocumentMobileFormService documentMobileFormService;
	@Autowired
	private DocumentListFileService documentListFileService;
	@Autowired
	private DocumentNumberService documentNumberService;
	@Autowired
	private DocumentEntrustService documentEntrustService;
	@Autowired
	private DocumentSendToService documentSendToService;
	@Autowired
	private AccountService accountService;
	
	/**
	 * 
	 * @Title: getDocumentFlowXmlFile   
	 * @Description: TODO 导出BPMFLOW的XML文件 
	 * @param: request
	 * @param: response
	 * @param: documentFlow      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping(value="/getDocumentFlowXmlFile",method=RequestMethod.GET)
	public void getDocumentFlowXmlFile(HttpServletRequest request,HttpServletResponse response,DocumentFlow documentFlow)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(StringUtils.isBlank(documentFlow.getFlowId()))
			{
				return;
			}
			documentFlow.setOrgId(account.getOrgId());
			documentFlow = documentFlowService.selectOneDocumentFlow(documentFlow);
			if(documentFlow==null)
			{
				return;
			}else {
				documentFlowService.getDocumentFlowXmlFile(documentFlow,response);
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 
	 * @Title: getSendToMeDocumentList   
	 * @Description: TODO 获取抄送给我的公文流程列表
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
	@RequestMapping(value="/getSendToMeDocumentList",method=RequestMethod.POST)
	public RetDataBean getSendToMeDocumentList(
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
		PageInfo<Map<String, String>> pageInfo = documentSendToService.getSendToMeDocumentList(pageParam, beginTime, endTime,flowId,status);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}	
	
	
	
	/**
	 * 
	* @Title: getEntrustUser 
	* @Description: TODO 获取指定收发文流程步骤权限内的所有人员
	* @param @param request
	* @param @param documentRunProcess
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value = "/getEntrustUser", method = RequestMethod.POST)
	public RetDataBean getEntrustUser(HttpServletRequest request, DocumentRunProcess documentRunProcess) {
		try {
			Account account=accountService.getRedisAccount(request);
			documentRunProcess.setOrgId(account.getOrgId());
			documentRunProcess = documentRunProcessService.selectOne(documentRunProcess);
			return RetDataTools.Ok("请求成功！", documentProcessService.getEntrustUser(documentRunProcess));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: getMyAllDocumentFlowList 
	* @Description: TODO 获取当前用户所有需审批的流程
	* @param @param request
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value = "/getMyAllDocumentFlowList", method = RequestMethod.POST)
	public RetDataBean getMyAllDocumentFlowList(HttpServletRequest request) {
		try {
			UserInfo userInfo = accountService.getRedisUserInfo(request);
			return RetDataTools.Ok("请求成功！",documentFlowService.getMyAllDocumentFlowList(userInfo.getOrgId(), userInfo.getAccountId(), userInfo.getDeptId(), userInfo.getLeadLeave()));
		} catch (Exception e) {
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
			PageInfo<Map<String, String>> pageInfo = documentEntrustService.getDocumentEntrustList(pageParam);
			return RetDataTools.Ok("请求数据成功!", pageInfo);
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: getDocumentPluginsRegisterById   
	 * @Description: TODO 获取BPM插件详情
	 * @param: request
	 * @param: documentPluginsRegister
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getDocumentPluginsRegisterById",method=RequestMethod.POST)
	public RetDataBean getDocumentPluginsRegisterById(HttpServletRequest request,DocumentPluginsRegister documentPluginsRegister)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentPluginsRegister.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求成功!", documentPluginsRegisterService.selectOneDocumentPluginsRegister(documentPluginsRegister));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: getDocumentPluginsList   
	 * @Description: TODO 获取收发文插件列表
	 * @param request
	 * @param pageParam
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getDocumentPluginsList",method=RequestMethod.POST)
	public RetDataBean getDocumentPluginsList(HttpServletRequest request,PageParam pageParam)
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
		PageInfo<Map<String, String>> pageInfo=documentPluginsRegisterService.getDocumentPluginsList(pageParam);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: getDocumentNumberListForSelect   
	 * @Description: TODO 获取收发文规则FOR select
	 * @param request
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getDocumentNumberListForSelect",method=RequestMethod.POST)
	public RetDataBean getDocumentNumberListForSelect(HttpServletRequest request)
	{
		try
		{
			Account account = accountService.getRedisAccount(request);
			return RetDataTools.Ok("请求数据成功!", documentNumberService.getDocumentNumberListForSelect(account.getOrgId()));
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	
	/**
	 * 
	 * @Title: getDocumentNumberById   
	 * @Description: TODO 获取文号规则详情
	 * @param request
	 * @param documentNumber
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getDocumentNumberById",method=RequestMethod.POST)
	public RetDataBean getDocumentNumberById(HttpServletRequest request,DocumentNumber documentNumber)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentNumber.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求数据成功!", documentNumberService.selectOneDocumentNumber(documentNumber));
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	/**
	 * 
	 * @Title: getDocumentNumberList   
	 * @Description: TODO 获取文号列表
	 * @param request
	 * @param pageParam
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getDocumentNumberList",method=RequestMethod.POST)
	public RetDataBean getDocumentNumberList(
			HttpServletRequest request,
			PageParam pageParam
			)
	{
		try
		{
			if(StringUtils.isBlank(pageParam.getSort()))
			{
				pageParam.setSort("N.SORT_NO");
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
		PageInfo<Map<String, String>> pageInfo=documentNumberService.getDocumentNumberList(pageParam);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getFormTableFields   
	 * @Description: TODO 获取公文表的字段信息
	 * @param: request
	 * @param: response
	 * @param: documentForm
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getFormTableFields",method=RequestMethod.POST)
	public RetDataBean getFormTableFields(HttpServletRequest request,HttpServletResponse response,DocumentForm documentForm)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(StringUtils.isBlank(documentForm.getFormId()))
			{
				return RetDataTools.NotOk("请求有参数有问题,请与管理员联系!");
			}
			documentForm.setOrgId(account.getOrgId());
			documentForm = documentFormService.selectOneDocumentForm(documentForm);
			if(documentForm==null)
			{
				return RetDataTools.NotOk("请求有参数有问题,请与管理员联系!");
			}else {
				return RetDataTools.Ok("请求数据成功!", documentFormService.getFormTableFields(documentForm));
			}
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	/**
	 * 
	 * @Title: getDocumentFileQueryList   
	 * @Description: TODO 归档查询
	 * @param request
	 * @param pageParam
	 * @param sortId
	 * @param beginTime
	 * @param endTime
	 * @param flowId
	 * @param filingUser
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getDocumentFileQueryList",method=RequestMethod.POST)
	public RetDataBean getDocumentFileQueryList(
			HttpServletRequest request,
			PageParam pageParam,
			String sortId,
			String beginTime,
			String endTime,
			String flowId,
			String filingUser
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
		PageInfo<Map<String, String>> pageInfo=documentListFileService.getDocumentFileQueryList(pageParam, sortId,filingUser,flowId, beginTime, endTime);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getDocumentMobileFormSet   
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param documentForm
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getdocumentmobileformset",method=RequestMethod.POST)
	public RetDataBean getDocumentMobileFormSet(HttpServletRequest request,DocumentForm documentForm)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentForm.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求数据成功!", documentMobileFormService.getDocumentMobileFormFieldsForSet(documentForm));
			}catch (Exception e) {
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
		PageInfo<Map<String, String>> pageInfo=documentRunLogService.getRunLogList(pageParam, createUser, beginTime, endTime, flowId, runId,logType,formId);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: getDocumentSummary   
	 * @Description: TODO 获取收发文的汇总数据
	 * @param request
	 * @param flowId
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getDocumentSummary",method=RequestMethod.POST)
	public RetDataBean getDocumentSummary(HttpServletRequest request,String flowId)
	{
		try
		{
			UserInfo userInfo = accountService.getRedisUserInfo(request);
			return RetDataTools.Ok("请求成功!", documentListService.getDocumentSummary(userInfo.getOrgId(),flowId));
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
			UserInfo userInfo = accountService.getRedisUserInfo(request);
			String nowTime = SysTools.getTime("yyyy-MM-dd");
			return RetDataTools.Ok("请求成功!", documentRunProcessService.getMyProcessListForDesk(userInfo.getOrgId(),userInfo.getAccountId(),nowTime));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	* @Title: getDocumentMaintainList 
	* @Description: TODO 获取监控权限内的流程列表
	* @param @param request
	* @param @param sortId
	* @param @param pageNumber
	* @param @param pageSize
	* @param @param search
	* @param @param sort
	* @param @param sortOrder
	* @param @param documentRunNo
	* @param @param beginTime
	* @param @param endTime
	* @param @param flowId
	* @param @param createUser
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value="/getDocumentMaintainList",method=RequestMethod.POST)
	public RetDataBean getDocumentMaintainList(
			HttpServletRequest request,
			String sortId,
			Integer pageNumber,
			Integer pageSize,
			String search,
			String sort,
			String sortOrder,
			Integer documentRunNo,
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
		PageInfo<Map<String, String>> pageInfo =  documentListService.getDocumentMaintainList(pageNumber, pageSize, orderBy,  account.getOrgId(),documentRunNo,createUser, flowId, beginTime, endTime, "%"+search+"%");
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: getDocumentToFilingList   
	 * @Description: TODO 获取可归档的公文流程
	 * @param request
	 * @param sortId
	 * @param pageNumber
	 * @param pageSize
	 * @param search
	 * @param sort
	 * @param sortOrder
	 * @param documentRunNo
	 * @param beginTime
	 * @param endTime
	 * @param flowId
	 * @param createUser
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getDocumentToFilingList",method=RequestMethod.POST)
	public RetDataBean getDocumentToFilingList(
			HttpServletRequest request,
			String sortId,
			Integer pageNumber,
			Integer pageSize,
			String search,
			String sort,
			String sortOrder,
			Integer documentRunNo,
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
		PageInfo<Map<String, String>> pageInfo =  documentListService.getDocumentToFilingList(pageNumber, pageSize, orderBy,  account.getOrgId(),documentRunNo,createUser, flowId, beginTime, endTime, "%"+search+"%");
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title searchDocumentList
	 * @Description TODO 查询收发文列表
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @param search
	 * @param sort
	 * @param sortOrder
	 * @return RetDataBean
	 */
	@RequestMapping(value = "/searchDocumentList", method = RequestMethod.POST)
	public RetDataBean searchDocumentList(HttpServletRequest request, PageParam pageParam,
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
			PageInfo<Map<String, Object>> pageInfo = documentListService.searchDocumentList(pageParam,flowId,id,createUser,status,managePriv);
			return RetDataTools.Ok("请求数据成功!", pageInfo);
		} catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}

	
	/**
	 * 
	 * @Title: getMyEndDocumentFlowList1  
	 * @Description: TODO 获取已结束的收文流程
	 * @param: request
	 * @param: pageParam
	 * @param: beginTime
	 * @param: endTime
	 * @param: flowId
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
		@RequestMapping(value="/getMyEndDocumentFlowList1",method=RequestMethod.POST)
		public RetDataBean getMyEndDocumentFlowList1(
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
			pageInfo = documentListService.getMyEndCrossDocumentFlowList1(pageParam,flowId, beginTime, endTime,range);
			return RetDataTools.Ok("请求数据成功!", pageInfo);
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
		}
	
	/**
	 * 
	 * @Title: getMyEndDocumentFlowList   
	 * @Description: TODO 获取已结束的收文流程
	 * @param: request
	 * @param: pageParam
	 * @param: beginTime
	 * @param: endTime
	 * @param: flowId
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
		@RequestMapping(value="/getMyEndDocumentFlowList",method=RequestMethod.POST)
		public RetDataBean getMyEndDocumentFlowList(
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
			pageInfo = documentListService.getMyEndCrossDocumentFlowList(pageParam,flowId, beginTime, endTime,range);
			return RetDataTools.Ok("请求数据成功!", pageInfo);
			}catch (Exception e) {
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
			return RetDataTools.Ok("请求成功!", documentRunProcessService.isCantTaskBackFlag(account.getOrgId(),runId,account.getAccountId()));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
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
			return RetDataTools.Ok("请求数据成功!", documentRunProcessService.getCanTaskBackRunProcessList(account.getOrgId(), runId, account.getAccountId()));
		} catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: getProcessDocumentFlowList 
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
	@RequestMapping(value="/getProcessDocumentFlowList",method=RequestMethod.POST)
	public RetDataBean getProcessDocumentFlowList(
			HttpServletRequest request,
			PageParam pageParam,
			Integer pDocumentRunNo,
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
		PageInfo<Map<String, String>> pageInfo = documentListService.getProcessDocumentFlowList(pageParam, pDocumentRunNo, pFlowId, pBeginTime, pEndTime);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	* @Title: getProcessDocumentFlowList1
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
	@RequestMapping(value="/getProcessDocumentFlowList1",method=RequestMethod.POST)
	public RetDataBean getProcessDocumentFlowList1(
			HttpServletRequest request,
			PageParam pageParam,
			Integer pDocumentRunNo,
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
		PageInfo<Map<String, String>> pageInfo = documentListService.getProcessDocumentFlowList1(pageParam, pDocumentRunNo, pFlowId, pBeginTime, pEndTime);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: getCorssDocumentFlowList 
	* @Description: TODO 获取当用户参与的收发文流程列表
	* @param @param request
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value = "/getCorssDocumentFlowList", method = RequestMethod.POST)
	public RetDataBean getCorssDocumentFlowList(HttpServletRequest request) {
		try {
			Account account=accountService.getRedisAccount(request);
			return RetDataTools.Ok("请求成功！", documentFlowService.getCorssDocumentFlowList(account.getOrgId(),account.getAccountId()));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: getCorssDocumentFlowList1
	* @Description: TODO 获取当用户参与的收发文流程列表
	* @param @param request
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value = "/getCorssDocumentFlowList1", method = RequestMethod.POST)
	public RetDataBean getCorssDocumentFlowList1(HttpServletRequest request) {
		try {
			Account account=accountService.getRedisAccount(request);
			return RetDataTools.Ok("请求成功！", documentFlowService.getCorssDocumentFlowList(account.getOrgId(),account.getAccountId()));
		} catch (Exception e) {
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
			return RetDataTools.Ok("请求成功!", documentRunProcessService.getRunPrcsStep(account.getOrgId(),runId));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getDocumentTemplateListByFlowId   
	 * @Description: TODO 按收发文流程获取红头列表
	 * @param request
	 * @param runId
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value = "/getDocumentTemplateListByFlowId", method = RequestMethod.POST)
	public RetDataBean getDocumentTemplateListByFlowId(HttpServletRequest request,String runId) {
		try {
			//UserInfo userInfo = (UserInfo)request.getSession().getAttribute("USER_INFO");
			DocumentList documentList = new DocumentList();
			//documentList.setOrgId(userInfo.getOrgId());
			documentList.setRunId(runId);
			documentList = documentListService.selectOne(documentList);
			return RetDataTools.Ok("请求成功！", documentTemplateService.getDocumentTemplateListByFlowId(documentList.getOrgId(),documentList.getFlowId()));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getAllDocumentFlowList   
	 * @Description: TODO 获取所有的流程列表
	 * @param: request
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value = "/getAllDocumentFlowList", method = RequestMethod.POST)
	public RetDataBean getAllDocumentFlowList(HttpServletRequest request) {
		try {
			Account account=accountService.getRedisAccount(request);
			return RetDataTools.Ok("请求成功！", documentFlowService.getAllDocumentFlowListByManage(account.getOrgId()));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: getDcoumentTemplateById   
	 * @Description: TODO 获取收发文模版详情
	 * @param: request
	 * @param: documentTemplate
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */	
	@RequestMapping(value="/getDocumentTemplateById",method=RequestMethod.POST)
	public RetDataBean getDocumentTemplateById(HttpServletRequest request,DocumentTemplate doucmentTemplate)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			doucmentTemplate.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求数据成功!", documentTemplateService.selectOneDocumentTemplate(doucmentTemplate));
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	
	
	/**
	 * 
	 * @Title: getDocumentTemplateList   
	 * @Description: TODO 获取收发文模版列表
	 * @param: request
	 * @param: pageParam
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value = "/getDocumentTemplateList", method = RequestMethod.POST)
	public RetDataBean getDocumentTemplateList(HttpServletRequest request,PageParam pageParam) {
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
			PageInfo<Map<String, String>> pageInfo = documentTemplateService.getDocumentTemplateList(pageParam);
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
	 * @param: documentProcess
	 * @param: search
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getUserInfoInPrivForMobile",method=RequestMethod.POST)
	public RetDataBean getUserInfoInPrivForMobile(HttpServletRequest request,DocumentProcess documentProcess,String search)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentProcess.setOrgId(account.getOrgId());
			documentProcess = documentProcessService.selectOne(documentProcess);
			return RetDataTools.Ok("请求数据成功!", documentProcessService.getUserInfoInPrivForMobile(documentProcess,search));
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	
	/**
	 * 
	* @Title: getDocumentPrintData 
	* @Description: TODO 获取收发文流程详情
	* @param @param request
	* @param @param documentList
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@Transactional(value = "generalTM")
	@RequestMapping(value = "/getDocumentPrintData", method = RequestMethod.POST)
	public RetDataBean getDocumentPrintData(HttpServletRequest request, DocumentList documentList) {
		try {
			Account account=accountService.getRedisAccount(request);
			documentList.setOrgId(account.getOrgId());
			documentList = documentListService.selectOne(documentList);
			if(SysTools.isMobileDevice(request))
			{
				return RetDataTools.Ok("请求成功！",documentFormDataService.getFormMPrintData(request, account, documentList));
			}else
			{
				return RetDataTools.Ok("请求成功！",documentFormDataService.getFormPrintData(request, account, documentList));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: getFromDataForHtml   
	 * @Description: TODO BPM表单转成HTML
	 * @param: request
	 * @param: response
	 * @param: documentList      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping(value="/getFromDataForHtml",method=RequestMethod.GET)
	public void getFromDataForHtml(HttpServletRequest request,HttpServletResponse response,DocumentList documentList)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentOptService.getFromDataForHtml(request, response, account, documentList);
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
	 * @param: documentList      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping(value="/getFromDataForWord",method=RequestMethod.GET)
	public void getFromDataForWord(HttpServletRequest request,HttpServletResponse response,DocumentList documentList)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentOptService.getFromDataForWord(request, response, account, documentList);
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
	 * @param: documentList      
	 * @return: void      
	 * @throws
	 */
	@RequestMapping(value="/getFromDataForPdf",method=RequestMethod.GET)
	public void getFromDataForPdf(HttpServletRequest request,HttpServletResponse response,DocumentList documentList)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentOptService.getFromDataForPdf(request, response, account, documentList);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title: getDocumentFlow   
	 * @Description: TODO 获取收发文流程信息
	 * @param request
	 * @param documentFlow
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value = "/getDocumentFlow", method = RequestMethod.POST)
	public RetDataBean getDocumentFlow(HttpServletRequest request, DocumentFlow documentFlow) {
		try {
			Account account=accountService.getRedisAccount(request);
			documentFlow.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求成功！", documentFlowService.selectOneDocumentFlow(documentFlow));
		} catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}

	
	/**
	 * 
	 * @Title: getDocumentOpUseByPorcess
	 * @Description: TODO 获取收发文权限内部门中的人员
	 * @param @param request
	 * @param @param deptId
	 * @param @param documentProcess
	 * @param @return 设定文件
	 * @return RetDataBean 返回类型
	 * 
	 */
	@RequestMapping(value = "/getDocumentOpUseByPorcess", method = RequestMethod.POST)
	public RetDataBean getDocumentOpUseByPorcess(HttpServletRequest request, String deptId, DocumentProcess documentProcess) {
		try {
			Account account=accountService.getRedisAccount(request);
			documentProcess.setOrgId(account.getOrgId());
			documentProcess = documentProcessService.selectOne(documentProcess);
			if (StringUtils.isBlank(deptId)) {
				deptId = "0";
			}
			return RetDataTools.Ok("请求成功！", documentProcessService.getUserInfoByProcess(documentProcess, deptId));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: getDocumentOpUserDept
	 * @Description: TODO 收发文经办权限人员部门树结构
	 * @param: request
	 * @param: deptId
	 * @param: @return
	 * @return: List<Map<String,Object>>
	 * 
	 */
	@RequestMapping(value = "/getDocumentOpUserDept", method = RequestMethod.POST)
	public List<Map<String, Object>> getDocumentOpUserDept(HttpServletRequest request, String deptId,
			DocumentProcess documentProcess) {
		try {
			Account account=accountService.getRedisAccount(request);
			documentProcess.setOrgId(account.getOrgId());
			documentProcess = documentProcessService.selectOne(documentProcess);
			String orgLeaveId = "0";
			if (StringUtils.isNotBlank(deptId)) {
				orgLeaveId = deptId;
			}
			return documentProcessService.getDocumentOpUserDept(documentProcess, orgLeaveId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @Title getDocumentChileTableData
	 * @Description TODO 按权限获取收发文子表数据
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @param search
	 * @param sort
	 * @param sortOrder
	 * @return RetDataBean
	 */
	@RequestMapping(value = "/getDocumentChileTableData", method = RequestMethod.POST)
	public RetDataBean getDocumentChileTableData(HttpServletRequest request, String eName, String runId) {
		try {
			Account account=accountService.getRedisAccount(request);
			DocumentList documentList = new DocumentList();
			documentList.setRunId(runId);
			documentList.setOrgId(account.getOrgId());
			documentList = documentListService.selectOne(documentList);
			DocumentFlow documentFlow = new DocumentFlow();
			documentFlow.setOrgId(documentList.getOrgId());
			documentFlow.setFlowId(documentList.getFlowId());
			documentFlow = documentFlowService.selectOneDocumentFlow(documentFlow);
			DocumentForm documentForm = new DocumentForm();
			documentForm.setOrgId(documentFlow.getOrgId());
			documentForm.setFormId(documentFlow.getFormId());
			documentForm = documentFormService.selectOneDocumentForm(documentForm);
			PageInfo<Map<String, String>> pageInfo=documentOptService.getChildTableData(documentForm,eName,account.getOrgId(), runId);
			return RetDataTools.Ok("请求数据成功!", pageInfo);
		} catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: getXDocumentSelectList 
	* @Description: TODO 获取备选收发文列表数据  
	* @param @param request
	* @param @param sortId
	* @param @param pageNumber
	* @param @param pageSize
	* @param @param searchdocument
	* @param @param sort
	* @param @param sortOrder
	* @param @param runId
	* @param @param searchType
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value="/getXDocumentSelectList",method=RequestMethod.POST)
	public RetDataBean getXDocumentSelectList(
			HttpServletRequest request,
			String sortId,
			Integer pageNumber,
			Integer pageSize,
			String searchdocument,
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
			pageInfo = documentListService.getDocumentSelectMyCreateList(pageNumber, pageSize, orderBy, account.getOrgId(), account.getAccountId(), "%"+searchdocument+"%");
		}else if(searchType.equals("2"))
		{
			pageInfo = documentListService.getDocumentSelectMyCorssList(pageNumber, pageSize, orderBy, account.getOrgId(), account.getAccountId(), "%"+searchdocument+"%");
		}else if(searchType.equals("3"))
		{
			pageInfo = documentListService.getDocumentSelectInManageList(pageNumber, pageSize, orderBy,account.getOrgId(), account.getAccountId(), "%"+searchdocument+"%");
		}else if(searchType.equals("4"))
		{
			DocumentList documentList = new DocumentList();
			documentList.setRunId(runId);
			documentList.setOrgId(account.getOrgId());
			documentList = documentListService.selectOne(documentList);
			pageInfo = documentListService.getDocumentSelectInFlowIdList(pageNumber, pageSize, orderBy, account.getOrgId(), documentList.getFlowId(), "%"+searchdocument+"%");
		}
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getChangeDocumentUserStep   
	 * @Description: TODO 获取可转交的BPM步骤信息
	 * @param request
	 * @param runId
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value = "/getChangeDocumentUserStep", method = RequestMethod.POST)
	public RetDataBean getChangeDocumentUserStep(HttpServletRequest request, String runId) {
		try {
			Account account=accountService.getRedisAccount(request);
			String orgId = account.getOrgId();
			List<Map<String, String>> btbList = documentRunProcessService.getChangeDocumentUserStep(orgId, runId);
			return RetDataTools.Ok("请求成功！", btbList);
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}	
	/**
	 * 
	* @Title: getNowStep 
	* @Description: TODO 获取当前收发文处于什么步骤
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
				List<Map<String, String>> btbList = documentRunProcessService.getNowStep(orgId, runId);
				return RetDataTools.Ok("请求成功！", btbList);
			} catch (Exception e) {
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
			return RetDataTools.Ok("请求数据成功!", documentRunProcessService.getCanGoBckProcessList(account.getOrgId(), runId));
		} catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title getNextPrcs
	 * @Description TODO 获取下一步相关信息
	 * @param request
	 * @param documentList
	 * @param documentProcess
	 * @return RetDataBean
	 */
	@RequestMapping(value = "/getNextPrcs", method = RequestMethod.POST)
	public RetDataBean getNextPrcs(HttpServletRequest request, String runId, String oldProcessId,String runProcessId) {
		return documentLogicCpuService.getNextPrcs(request, runId,oldProcessId,runProcessId);
	}
	
	/**
	 * 
	 * @Title: getGoProcessList   
	 * @Description: TODO 获取待办发文
	 * @param request
	 * @param pageParam
	 * @param documentRunNo
	 * @param createUser
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value = "/getGoProcessList1", method = RequestMethod.POST)
	public RetDataBean getGoProcessList1(
			HttpServletRequest request, 
			PageParam pageParam,
			Integer documentRunNo,
			String createUser
			) {
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
			Account account=accountService.getRedisAccount(request);
			pageParam.setOrderBy(orderBy);
			pageParam.setAccountId(account.getAccountId());
			pageParam.setOrgId(account.getOrgId());
			PageInfo<Map<String, Object>> pageInfo = documentRunProcessService.getGoProcessList1(pageParam,documentRunNo, createUser);
			return RetDataTools.Ok("请求数据成功!", pageInfo);
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getGoProcessList   
	 * @Description: TODO 获取待办发文
	 * @param request
	 * @param pageParam
	 * @param documentRunNo
	 * @param createUser
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value = "/getGoProcessList", method = RequestMethod.POST)
	public RetDataBean getGoProcessList(
			HttpServletRequest request, 
			PageParam pageParam,
			Integer documentRunNo,
			String createUser
			) {
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
			Account account=accountService.getRedisAccount(request);
			pageParam.setOrderBy(orderBy);
			pageParam.setAccountId(account.getAccountId());
			pageParam.setOrgId(account.getOrgId());
			PageInfo<Map<String, Object>> pageInfo = documentRunProcessService.getGoProcessList(pageParam,documentRunNo, createUser);
			return RetDataTools.Ok("请求数据成功!", pageInfo);
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getDocumentFormData   
	 * @Description: TODO 后台生成表单内容
	 * @param request
	 * @param documentList
	 * @param documentRunProcess
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@Transactional(value = "generalTM")
	@RequestMapping(value = "/getProcessInfo", method = RequestMethod.POST)
	public RetDataBean getProcessInfo(HttpServletRequest request, DocumentList documentList, DocumentRunProcess documentRunProcess) {
		try {
			Account account=accountService.getRedisAccount(request);
			documentList.setOrgId(account.getOrgId());
			documentList = documentListService.selectOne(documentList);
			documentRunProcess=documentRunProcessService.getDocumentRunProcess(account.getOrgId(),documentRunProcess.getRunId(),documentRunProcess.getRunProcessId(),account.getAccountId());
			return RetDataTools.Ok("请求成功！",documentFormDataService.getProcessInfo(documentList, documentRunProcess));
		} catch (Exception e) {
			e.printStackTrace();
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getDocNumByDocumentFlow   
	 * @Description: TODO 自动生成收发文标题
	 * @param request
	 * @param documentFlow
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value = "/getDocNumByDocumentFlow", method = RequestMethod.POST)
	public RetDataBean getDocNumByDocumentFlow(HttpServletRequest request,DocumentFlow documentFlow) {
		try {
			Account account=accountService.getRedisAccount(request);
			documentFlow.setOrgId(account.getOrgId());
			documentFlow = documentFlowService.selectOneDocumentFlow(documentFlow);
			return RetDataTools.Ok("请求成功！", documentUnitsService.getDocNumByDocumentFlow(request, documentFlow));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}	
	
	/**
	 * 
	 * @Title: getMyDocumentReceiptFlowList   
	 * @Description: TODO 获取收文列表
	 * @param request
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getMyDocumentReceiptFlowList",method=RequestMethod.POST)
	public RetDataBean getMyDocumentReceiptFlowList(HttpServletRequest request)
	{
		try
		{
			UserInfo userInfo = accountService.getRedisUserInfo(request);
			return RetDataTools.Ok("请求成功!", documentFlowService.getMyDocumentReceiptFlowList(userInfo.getOrgId(),userInfo.getAccountId(),userInfo.getDeptId(),userInfo.getLeadId()));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getMyDocumentDispatchFlowList   
	 * @Description: TODO 获取发起发文列表
	 * @param request
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getMyDocumentDispatchFlowList",method=RequestMethod.POST)
	public RetDataBean getMyDocumentDispatchFlowList(HttpServletRequest request)
	{
		try
		{
			UserInfo userInfo = accountService.getRedisUserInfo(request);
			return RetDataTools.Ok("请求成功!", documentFlowService.getMyDocumentDispatchFlowList(userInfo.getOrgId(),userInfo.getAccountId(),userInfo.getDeptId(),userInfo.getLeadId()));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	* @Title: getAllDocumentFlowListByManage 
	* @Description: TODO 获取所有收发文流程
	* @param @param request
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
		@RequestMapping(value = "/getAllDocumentFlowListByManage", method = RequestMethod.POST)
		public RetDataBean getAllDocumentFlowListByManage(HttpServletRequest request) {
			try {
				Account account=accountService.getRedisAccount(request);
				if(account.getOpFlag().equals("1"))
				{
					return RetDataTools.Ok("请求成功！", documentFlowService.getAllDocumentFlowListByManage(account.getOrgId()));
				}else
				{
					return RetDataTools.NotOk("您没有权限!");
				}
			} catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
		}
	
	/**
	 * 
	 * @Title: getDocumentPluginsListForProcess   
	 * @Description: TODO 获取BPM插件列表  
	 * @param: request
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getDocumentPluginsListForProcess",method=RequestMethod.POST)
	public RetDataBean getDocumentPluginsListForProcess(HttpServletRequest request)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			return RetDataTools.Ok("请求成功!", documentPluginsRegisterService.getDocumentPluginsListForProcess(account.getOrgId()));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title getDocumentProcessPrcs
	 * @Description TODO 获取收发文步骤信息
	 * @param request
	 * @param documentProcess
	 * @return RetDataBean
	 */
	@RequestMapping(value = "/getDocumentProcessPrcs", method = RequestMethod.POST)
	public RetDataBean getDocumentProcessPrcs(HttpServletRequest request, DocumentProcess documentProcess) {
		try {
			Account account=accountService.getRedisAccount(request);
			documentProcess.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求成功！", documentProcessService.selectOne(documentProcess));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getDocumentChildProcessPrcs   
	 * @Description: TODO 获取收发文子流程配置信息
	 * @param: request
	 * @param: documentChildProcess
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/getDocumentChildProcessPrcs",method=RequestMethod.POST)
	public RetDataBean getDocumentChildProcessPrcs(HttpServletRequest request,DocumentChildProcess documentChildProcess)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentChildProcess.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求数据成功!", documentChildProcessService.getDocumentChildProcessPrcs(account.getOrgId(),documentChildProcess.getProcessId()));
			}catch (Exception e) {
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
			return RetDataTools.Ok("请求数据成功!",documentProcessService.getAlternativePrcsList(account.getOrgId(), flowId,processId, list));
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
	 * @Title: getDocumentProcess   
	 * @Description: TODO 获取流程信息
	 * @param request
	 * @param documentProcess
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value = "/getDocumentProcess", method = RequestMethod.POST)
	public RetDataBean getDocumentProcess(HttpServletRequest request, DocumentProcess documentProcess) {
		Account account=accountService.getRedisAccount(request);
			return documentProcessService.getDocumentProcess(account, documentProcess);
	}
	
	/**
	 * 
	 * @Title: getDocumentFormFieldByFlowId   
	 * @Description: TODO 获取表单所有的字段
	 * @param request
	 * @param documentFlow
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value = "/getDocumentFormFieldByFlowId", method = RequestMethod.POST)
	public RetDataBean getDocumentFormFieldByFlowId(HttpServletRequest request, DocumentFlow documentFlow) {
		try {
			Account account=accountService.getRedisAccount(request);
			documentFlow.setOrgId(account.getOrgId());
			documentFlow = documentFlowService.selectOneDocumentFlow(documentFlow);
			DocumentForm documentForm = new DocumentForm();
			documentForm.setFormId(documentFlow.getFormId());
			documentForm.setOrgId(documentFlow.getOrgId());
			documentForm = documentFormService.selectOneDocumentForm(documentForm);
			DocumentTemplateUnit btu = new DocumentTemplateUnit();
			List<DocumentTableBean> btbList = new ArrayList<DocumentTableBean>();
			btbList = btu.getDocumentElement(documentForm);
			return RetDataTools.Ok("请求成功！", btbList);
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: getDocumentFlowById   
	 * @Description: TODO 获取收发文流程详情
	 * @param request
	 * @param documentFlow
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getDocumentFlowById",method=RequestMethod.POST)
	public RetDataBean getDocumentFlowById(HttpServletRequest request,DocumentFlow documentFlow)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentFlow.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求成功!", documentFlowService.selectOneDocumentFlow(documentFlow));
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getDocumentFlowList   
	 * @Description: TODO 获取收发文流程列表
	 * @param request
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value = "/getDocumentFlowList", method = RequestMethod.POST)
	public RetDataBean getDocumentFlowList(HttpServletRequest request) {
		try {
			Account account=accountService.getRedisAccount(request);
			return RetDataTools.Ok("请求成功！", documentFlowService.getDocumentFlowList(account.getOrgId()));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getDocumentFormFieldByFormId   
	 * @Description: TODO 按FormId获取表单所有字段名
	 * @param request
	 * @param formId
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value = "/getDocumentFormFieldByFormId", method = RequestMethod.POST)
	public RetDataBean getDocumentFormFieldByFormId(HttpServletRequest request, String formId) {
		try {
			Account account=accountService.getRedisAccount(request);
			DocumentForm documentForm = new DocumentForm();
			documentForm.setFormId(formId);
			documentForm.setOrgId(account.getOrgId());
			documentForm = documentFormService.selectOneDocumentForm(documentForm);
			return RetDataTools.Ok("请求成功！", documentTableService.getAllFormField(documentForm));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getDocumentFormSelectList   
	 * @Description: TODO 获取公文表单下拉列表
	 * @param request
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value = "/getDocumentFormSelectList", method = RequestMethod.POST)
	public RetDataBean getDocumentFormSelectList(HttpServletRequest request) {
		try {
			Account account=accountService.getRedisAccount(request);
			return RetDataTools.Ok("请求成功！", documentFormService.getDocumentFormSelectList(account.getOrgId()));
		} catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: getDocumentFormList   
	 * @Description: TODO 公文表单列表
	 * @param request
	 * @param pageParam
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getDocumentFormList",method=RequestMethod.POST)
	public RetDataBean getDocumentFormList(HttpServletRequest request,PageParam pageParam
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
		PageInfo<Map<String, String>> pageInfo=documentFormService.getDocumentFormList(pageParam);
		return RetDataTools.Ok("请求数据成功!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getDocumentFormById   
	 * @Description: TODO 获取公文表单详情
	 * @param request
	 * @param documentForm
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getDocumentFormById",method=RequestMethod.POST)
	public RetDataBean getDocumentFormById(HttpServletRequest request,DocumentForm documentForm)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentForm.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求成功!", documentFormService.selectOneDocumentForm(documentForm));
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getDocumentSortById   
	 * @Description: TODO 获取公文分类详情
	 * @param request
	 * @param documentSort
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getDocumentSortById",method=RequestMethod.POST)
	public RetDataBean getDocumentSortById(HttpServletRequest request,DocumentSort documentSort)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentSort.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求成功!", documentSortService.selectOneDocumentSort(documentSort));
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: getDocumentSortTree   
	 * @Description: TODO 获取公文分类树
	 * @param request
	 * @param sortId
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/getDocumentSortTree",method=RequestMethod.POST)
	public List<Map<String, Object>> getDocumentSortTree(HttpServletRequest request,String sortId)
	{
		String levelId = "0";
		try
		{
			if (StringUtils.isNotBlank(sortId)) {
			levelId = sortId;
			}
			Account account=accountService.getRedisAccount(request);
			return documentSortService.getDocumentSortTree(account.getOrgId(),levelId);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getDocumentFormSortTree   
	 * @Description: TODO 获取公文表单分类
	 * @param request
	 * @param sortId
	 * @return
	 * List<Map<String,String>>    
	 * @throws
	 */
	@RequestMapping(value = "/getDocumentFormSortTree", method = RequestMethod.POST)
	public List<Map<String, Object>> getDocumentFormSortTree(HttpServletRequest request, String sortId) {
		String levelId = "0";
		try {
			List<Map<String, Object>> tmplist1 = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> tmplist2 = new ArrayList<Map<String, Object>>();
			Account account=accountService.getRedisAccount(request);
			if (StringUtils.isNotBlank(sortId)) {
				levelId = sortId;
				if (!levelId.equals("0")) {
					tmplist1 = documentSortService.getDocumentSortTree(account.getOrgId(),sortId);
				}
			}
			tmplist2 = documentSortService.getDocumentFormSortTree(account.getOrgId(),levelId);
			tmplist2.addAll(tmplist1);
			return tmplist2;
		} catch (Exception e) {
			return null;
		}
	}
}
