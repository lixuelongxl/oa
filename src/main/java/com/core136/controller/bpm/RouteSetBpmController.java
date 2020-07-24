package com.core136.controller.bpm;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.core136.bean.account.Account;
import com.core136.bean.account.UserInfo;
import com.core136.bean.bpm.BpmBiTemplate;
import com.core136.bean.bpm.BpmBusiness;
import com.core136.bean.bpm.BpmChildProcess;
import com.core136.bean.bpm.BpmEntrust;
import com.core136.bean.bpm.BpmFlow;
import com.core136.bean.bpm.BpmForm;
import com.core136.bean.bpm.BpmFormVersion;
import com.core136.bean.bpm.BpmList;
import com.core136.bean.bpm.BpmPluginsRegister;
import com.core136.bean.bpm.BpmProcess;
import com.core136.bean.bpm.BpmRunProcess;
import com.core136.bean.bpm.BpmSealSign;
import com.core136.bean.bpm.BpmSendTo;
import com.core136.bean.bpm.BpmSort;
import com.core136.bean.bpm.BpmTemplate;
import com.core136.service.bpm.BpmBiTemplateService;
import com.core136.service.bpm.BpmBusinessService;
import com.core136.service.bpm.BpmChildProcessService;
import com.core136.service.bpm.BpmEntrustService;
import com.core136.service.bpm.BpmFlowService;
import com.core136.service.bpm.BpmFormCacheService;
import com.core136.service.bpm.BpmFormService;
import com.core136.service.bpm.BpmFormVersionService;
import com.core136.service.bpm.BpmListService;
import com.core136.service.bpm.BpmOptService;
import com.core136.service.bpm.BpmPluginsRegisterService;
import com.core136.service.bpm.BpmProcessService;
import com.core136.service.bpm.BpmRunProcessService;
import com.core136.service.bpm.BpmSealSignService;
import com.core136.service.bpm.BpmSendToService;
import com.core136.service.bpm.BpmSortService;
import com.core136.service.bpm.BpmTemplateService;
import com.core136.service.bpm.BpmUnitsService;
import org.core136.common.retdataunit.RetDataBean;
import org.core136.common.retdataunit.RetDataTools;
import org.core136.common.utils.SysTools;

import tk.mybatis.mapper.entity.Example;

@RestController
@RequestMapping("/set/bpmset")
public class RouteSetBpmController {
	@Autowired
	private BpmListService bpmListService;
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
	private BpmOptService bpmOptService;
	@Autowired
	private BpmEntrustService bpmEntrustService;
	@Autowired
	private BpmUnitsService bpmUnitsService;
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
	private BpmFormCacheService bpmFormCacheService;
	@Autowired
	private BpmFormVersionService bpmFormVersionService;
	@Autowired
	private BpmBusinessService bpmBusinessService;
	@Autowired
	private BpmBiTemplateService bpmBiTemplateService;
	
	/**
	 * 
	 * @Title: insertBpmBiTemplate   
	 * @Description: TODO 添加BPM报表模版
	 * @param request
	 * @param bpmBiTemplate
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/insertBpmBiTemplate",method=RequestMethod.POST)
	public RetDataBean insertBpmBiTemplate(HttpServletRequest request,BpmBiTemplate bpmBiTemplate)
	{
		try
		{
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			bpmBiTemplate.setTemplateId(SysTools.getGUID());
			bpmBiTemplate.setCreateUser(account.getAccountId());
			bpmBiTemplate.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			bpmBiTemplate.setOrgId(account.getOrgId());
			return RetDataTools.Ok("BPM报表模版添加成功!",bpmBiTemplateService.insertBpmBiTemplate(bpmBiTemplate));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: deleteBpmBiTemplate   
	 * @Description: TODO 删除BPM报表模版
	 * @param request
	 * @param bpmBiTemplate
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/deleteBpmBiTemplate",method=RequestMethod.POST)
	public RetDataBean deleteBpmBiTemplate(HttpServletRequest request,BpmBiTemplate bpmBiTemplate)
	{
		try
		{
			if(StringUtils.isBlank(bpmBiTemplate.getTemplateId()))
			{
				return RetDataTools.NotOk("请求的参数有问题！请与管理员联系");
			}
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			bpmBiTemplate.setOrgId(account.getOrgId());
			return RetDataTools.Ok("BPM报表模版删除成功!", bpmBiTemplateService.deleteBpmBiTemplate(bpmBiTemplate));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: updateBpmBiTemplate   
	 * @Description: TODO 更新BPM报表模版
	 * @param request
	 * @param bpmBiTemplate
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/updateBpmBiTemplate",method=RequestMethod.POST)
	public RetDataBean updateBpmBiTemplate(HttpServletRequest request,BpmBiTemplate bpmBiTemplate)
	{
		try
		{
			if(StringUtils.isBlank(bpmBiTemplate.getTemplateId()))
			{
				return RetDataTools.NotOk("请求的参数有问题！请与管理员联系");
			}
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			Example example = new Example(BpmBiTemplate.class);
			example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("templateId",bpmBiTemplate.getTemplateId());
			return RetDataTools.Ok("BPM报表模版更新成功!", bpmBiTemplateService.updateBpmBiTemplate(example, bpmBiTemplate));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: insertBpmBusiness   
	 * @Description: TODO 添加业务BPM引擎 
	 * @param: request
	 * @param: bpmBusiness
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/insertBpmBusiness",method=RequestMethod.POST)
	public RetDataBean insertBpmBusiness(HttpServletRequest request,BpmBusiness bpmBusiness)
	{
		try
		{
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			bpmBusiness.setBusinessId(SysTools.getGUID());
			bpmBusiness.setCreateUser(account.getAccountId());
			bpmBusiness.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			bpmBusiness.setOrgId(account.getOrgId());
			return RetDataTools.Ok("业务引擎添加成功!", bpmBusinessService.insertBpmBusiness(bpmBusiness));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: deleteBpmBusiness   
	 * @Description: TODO 删除BPM业务引擎
	 * @param: request
	 * @param: bpmBusiness
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/deleteBpmBusiness",method=RequestMethod.POST)
	public RetDataBean deleteBpmBusiness(HttpServletRequest request,BpmBusiness bpmBusiness)
	{
		try
		{
			if(StringUtils.isBlank(bpmBusiness.getBusinessId()))
			{
				return RetDataTools.NotOk("请求的参数有问题！请与管理员联系");
			}
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			bpmBusiness.setOrgId(account.getOrgId());
			return RetDataTools.Ok("业务引擎删除成功!", bpmBusinessService.deleteBpmBusiness(bpmBusiness));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: updateBpmBusiness   
	 * @Description: TODO 更新BPM业务引擎
	 * @param: request
	 * @param: bpmBusiness
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/updateBpmBusiness",method=RequestMethod.POST)
	public RetDataBean updateBpmBusiness(HttpServletRequest request,BpmBusiness bpmBusiness)
	{
		try
		{
			if(StringUtils.isBlank(bpmBusiness.getBusinessId()))
			{
				return RetDataTools.NotOk("请求的参数有问题！请与管理员联系");
			}
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			Example example = new Example(BpmBusiness.class);
			example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("businessId",bpmBusiness.getBusinessId());
			bpmBusiness.setOrgId(account.getOrgId());
			return RetDataTools.Ok("业务引擎更新成功!", bpmBusinessService.updateBpmBusiness(example, bpmBusiness));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: recoveryBpmFormVersion   
	 * @Description: TODO 恢复表单
	 * @param request
	 * @param bpmFormVersion
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/recoveryBpmFormVersion",method=RequestMethod.POST)
	public RetDataBean recoveryBpmFormVersion(HttpServletRequest request,BpmFormVersion bpmFormVersion)
	{
		try
		{
			if(StringUtils.isBlank(bpmFormVersion.getVersionId()))
			{
				return RetDataTools.NotOk("请求的参数有问题！请与管理员联系");
			}
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			bpmFormVersion.setOrgId(account.getOrgId());
			bpmFormVersion = bpmFormVersionService.selectOneBpmFormVserion(bpmFormVersion);
			return RetDataTools.Ok("表单恢复成功!", bpmFormVersionService.recoveryBpmFormVersion(bpmFormVersion));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: deleteBpmFormVersion   
	 * @Description: TODO 表单版本删除
	 * @param request
	 * @param bpmFormVersion
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/deleteBpmFormVersion",method=RequestMethod.POST)
	public RetDataBean deleteBpmFormVersion(HttpServletRequest request,BpmFormVersion bpmFormVersion)
	{
		try
		{
			if(StringUtils.isBlank(bpmFormVersion.getVersionId()))
			{
				return RetDataTools.NotOk("请求的参数有问题！请与管理员联系");
			}
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			bpmFormVersion.setOrgId(account.getOrgId());
			return RetDataTools.Ok("表单版本删除成功!", bpmFormVersionService.deleteBpmFormVersion(bpmFormVersion));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: setBpmFormVersion   
	 * @Description: TODO 生成表单版本
	 * @param request
	 * @param bpmForm
	 * @param title
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/setBpmFormVersion",method=RequestMethod.POST)
	public RetDataBean setBpmFormVersion(HttpServletRequest request,BpmForm bpmForm,String title,String remark)
	{
		try
		{
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			bpmForm.setOrgId(account.getOrgId());
			bpmForm = bpmFormService.selectOneBpmForm(bpmForm);
			return RetDataTools.Ok("生成版本成功!", bpmFormVersionService.setBpmFormVersion(account,bpmForm,title,remark));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: insertBpmTemplate   
	 * @Description: TODO 添加BPM红头模版
	 * @param: request
	 * @param: bpmTemplate
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/insertBpmTemplate",method=RequestMethod.POST)
	public RetDataBean insertBpmTemplate(HttpServletRequest request,BpmTemplate bpmTemplate)
	{
		try
		{
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			bpmTemplate.setTemplateId(SysTools.getGUID());
			bpmTemplate.setCreateUser(account.getAccountId());
			bpmTemplate.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			bpmTemplate.setOrgId(account.getOrgId());
			return RetDataTools.Ok("模版添加成功!", bpmTemplateService.insertBpmTemplate(bpmTemplate));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: deleteBpmTemplate   
	 * @Description: TODO 删除BPM流程红头模版
	 * @param: request
	 * @param: bpmTemplate
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/deleteBpmTemplate",method=RequestMethod.POST)
	public RetDataBean deleteBpmTemplate(HttpServletRequest request,BpmTemplate bpmTemplate)
	{
		try
		{
			if(StringUtils.isBlank(bpmTemplate.getTemplateId()))
			{
				return RetDataTools.NotOk("请求的参数有问题！请与管理员联系");
			}
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			bpmTemplate.setOrgId(account.getOrgId());
			return RetDataTools.Ok("模版删除成功!", bpmTemplateService.deleteBpmTemplate(bpmTemplate));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: updateBpmTemplate   
	 * @Description: TODO 更新模版
	 * @param: request
	 * @param: bpmTemplate
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/updateBpmTemplate",method=RequestMethod.POST)
	public RetDataBean updateBpmTemplate(HttpServletRequest request,BpmTemplate bpmTemplate)
	{
		try
		{
			if(StringUtils.isBlank(bpmTemplate.getTemplateId()))
			{
				return RetDataTools.NotOk("请求的参数有问题！请与管理员联系");
			}
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			Example example = new Example(BpmTemplate.class);
			example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("templateId",bpmTemplate.getTemplateId());
			bpmTemplate.setOrgId(account.getOrgId());
			return RetDataTools.Ok("模版更新成功!", bpmTemplateService.updateBpmTemplate(example, bpmTemplate));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: updateSendToStatus   
	 * @Description: TODO 更新抄送状态
	 * @param: request
	 * @param: bpmSendTo
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/updateSendToStatus",method=RequestMethod.POST)
	public RetDataBean updateSendToStatus(HttpServletRequest request,BpmSendTo bpmSendTo)
	{
		try
		{
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			bpmSendTo.setRevTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			bpmSendTo.setStatus("1");
			Example example = new Example(BpmSendTo.class);
			example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("sendToId",bpmSendTo.getSendToId());
			return RetDataTools.Ok("状态成功!",bpmSendToService.updateBpmSendTo(example, bpmSendTo));
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	/**
	 * 
	 * @Title: importFlowXml   
	 * @Description: TODO BPM流程配置文件导入
	 * @param: request
	 * @param: file
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/importFlowXml",method=RequestMethod.POST)
	public RetDataBean importFlowXml(HttpServletRequest request,MultipartFile file)
	{
		try
		{
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("您不是管理员,不能进行导入操作!");
			}
				return bpmFlowService.importFlowXml(account,file);
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	
	
	/**
	 * 
	 * @Title: cloneBpmFlow   
	 * @Description: TODO 流程克隆
	 * @param: request
	 * @param: bpmFlow
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/cloneBpmFlow",method=RequestMethod.POST)
	public RetDataBean cloneBpmFlow(HttpServletRequest request,BpmFlow bpmFlow)
	{
		try
		{
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("您不是管理员,不能进行BPM克隆操作!");
			}
			if(StringUtils.isBlank(bpmFlow.getFlowId()))
			{
				return RetDataTools.NotOk("请求有参数有问题,请与管理员联系!");
			}
			bpmFlow.setOrgId(account.getOrgId());
			bpmFlow = bpmFlowService.selectOne(bpmFlow);
			return bpmFlowService.cloneBpmFlow(account,bpmFlow);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: clearBpmFlowData   
	 * @Description: TODO 初始化BPM流程
	 * @param: request
	 * @param: bpmFlow
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/clearBpmFlowData",method=RequestMethod.POST)
	public RetDataBean clearBpmFlowData(HttpServletRequest request,BpmFlow bpmFlow)
	{
		try
		{
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("您不是管理员,不能进行BPM初始化操作!");
			}
			if(StringUtils.isBlank(bpmFlow.getFlowId()))
			{
				return RetDataTools.NotOk("请求有参数有问题,请与管理员联系!");
			}
			bpmFlow.setOrgId(account.getOrgId());
			bpmFlow = bpmFlowService.selectOne(bpmFlow);
			return bpmFlowService.clearBpmFlowData(bpmFlow,account);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: importFormHtml   
	 * @Description: TODO BPM表单导入
	 * @param: request
	 * @param: file
	 * @param: bpmForm
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/importFormHtml",method=RequestMethod.POST)
	public RetDataBean importFormHtml(HttpServletRequest request,MultipartFile file)
	{
		try
		{
			String formId = request.getParameter("formId");
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			BpmForm bpmForm = new BpmForm();
			bpmForm.setFormId(formId);
			bpmForm.setOrgId(account.getOrgId());
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("您不是管理员,不能进行导入操作!");
			}
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
				return bpmFormService.importFormHtml(account,bpmForm,file);
			}
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	
	/**
	 * 
	 * @Title: setBpmChildProcess   
	 * @Description: TODO   
	 * @param: request
	 * @param: bpmChildProcess
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/setBpmChildProcess",method=RequestMethod.POST)
	public RetDataBean setBpmChildProcess(HttpServletRequest request,BpmChildProcess bpmChildProcess,BpmProcess bpmProcess)
	{
		try
		{
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			if(StringUtils.isBlank(bpmChildProcess.getProcessId()))
			{
				return RetDataTools.NotOk("请求的参数有问题,请与管理员联系");
			}
			return RetDataTools.Ok("设置成功!", bpmChildProcessService.setBpmChildProcess(account, bpmChildProcess,bpmProcess));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: insertBpmPluginsRegister   
	 * @Description: TODO 注册BPM插件
	 * @param: request
	 * @param: bpmPluginsRegister
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/insertBpmPluginsRegister",method=RequestMethod.POST)
	public RetDataBean insertBpmPluginsRegister(HttpServletRequest request,BpmPluginsRegister bpmPluginsRegister)
	{
		try
		{
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			bpmPluginsRegister.setPluginsId(SysTools.getGUID());
			bpmPluginsRegister.setCreateUser(account.getAccountId());
			bpmPluginsRegister.setStatus("0");
			bpmPluginsRegister.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			bpmPluginsRegister.setOrgId(account.getOrgId());
			return RetDataTools.Ok("注册插件成功!", bpmPluginsRegisterService.insertBpmPluginsRegister(bpmPluginsRegister));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: deleteBpmPluginsRegister   
	 * @Description: TODO 删除插件
	 * @param: request
	 * @param: bpmPluginsRegister
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/deleteBpmPluginsRegister",method=RequestMethod.POST)
	public RetDataBean deleteBpmPluginsRegister(HttpServletRequest request,BpmPluginsRegister bpmPluginsRegister)
	{
		try
		{
			if(StringUtils.isBlank(bpmPluginsRegister.getPluginsId()))
			{
				return RetDataTools.NotOk("请求参数出错,请与系统管理联系!");
			}
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			bpmPluginsRegister.setOrgId(account.getOrgId());
			return RetDataTools.Ok("删除插件成功!", bpmPluginsRegisterService.deleteBpmPluginsRegist(bpmPluginsRegister));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: updateBpmPluginsRegister   
	 * @Description: TODO 更新插件记录   
	 * @param: request
	 * @param: bpmPluginsRegister
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/updateBpmPluginsRegister",method=RequestMethod.POST)
	public RetDataBean updateBpmPluginsRegister(HttpServletRequest request,BpmPluginsRegister bpmPluginsRegister)
	{
		try
		{
			if(StringUtils.isBlank(bpmPluginsRegister.getPluginsId()))
			{
				return RetDataTools.NotOk("请求参数出错,请与系统管理联系!");
			}
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			bpmPluginsRegister.setOrgId(account.getOrgId());
			Example example = new Example(BpmPluginsRegister.class);
			example.createCriteria().andEqualTo("orgId", account.getOrgId()).andEqualTo("pluginsId",bpmPluginsRegister.getPluginsId());
			return RetDataTools.Ok("更新插件成功!", bpmPluginsRegisterService.updateBpmPluginsRegister(example, bpmPluginsRegister));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: addProcessAfter   
	 * @Description: TODO 后加签
	 * @param: request
	 * @param: accountId
	 * @param: bpmRunProcess
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/addProcessAfter",method=RequestMethod.POST)
	public RetDataBean addProcessAfter(HttpServletRequest request,String addAccountId,BpmRunProcess bpmRunProcess)
	{
		try
		{
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			if(StringUtils.isBlank(addAccountId))
			{
				return RetDataTools.NotOk("加签人员不能为空!");
			}
			bpmRunProcess.setOrgId(account.getOrgId());
			bpmRunProcess = bpmRunProcessService.selectOne(bpmRunProcess);
			BpmRunProcess newBpmRunProcess = new BpmRunProcess();
			newBpmRunProcess.setRunProcessId(SysTools.getGUID());
			newBpmRunProcess.setRunId(bpmRunProcess.getRunId());
			newBpmRunProcess.setProcessId(bpmRunProcess.getProcessId());
			newBpmRunProcess.setOpFlag("1");
			newBpmRunProcess.setAccountId(addAccountId);
			newBpmRunProcess.setCreateUser(bpmRunProcess.getAccountId());
			newBpmRunProcess.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			newBpmRunProcess.setSendId(bpmRunProcess.getRunProcessId());
			newBpmRunProcess.setStatus("0");
			newBpmRunProcess.setOrgId(account.getOrgId());
			return RetDataTools.Ok("后加签成功!", bpmRunProcessService.addProcessAfter(bpmRunProcess, newBpmRunProcess));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: addProcessBefore   
	 * @Description: TODO 前加签
	 * @param: request
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/addProcessBefore",method=RequestMethod.POST)
	public RetDataBean addProcessBefore(HttpServletRequest request,String addAccountId,BpmRunProcess bpmRunProcess)
	{
		try
		{
			Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
			if(StringUtils.isBlank(addAccountId))
			{
				return RetDataTools.NotOk("加签人员不能为空!");
			}
			bpmRunProcess.setOrgId(account.getOrgId());
			bpmRunProcess = bpmRunProcessService.selectOne(bpmRunProcess);
			BpmRunProcess newBpmRunProcess = new BpmRunProcess();
			newBpmRunProcess.setRunProcessId(SysTools.getGUID());
			newBpmRunProcess.setRunId(bpmRunProcess.getRunId());
			newBpmRunProcess.setProcessId(bpmRunProcess.getProcessId());
			newBpmRunProcess.setOpFlag("1");
			newBpmRunProcess.setAccountId(addAccountId);
			newBpmRunProcess.setCreateUser(bpmRunProcess.getAccountId());
			newBpmRunProcess.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			newBpmRunProcess.setSendId(bpmRunProcess.getRunProcessId());
			newBpmRunProcess.setStatus("0");
			newBpmRunProcess.setOrgId(account.getOrgId());
			return RetDataTools.Ok("前加签成功!", bpmRunProcessService.addProcessBefore(bpmRunProcess, newBpmRunProcess));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: insertBpmSealSign   
	 * @Description: TODO 添加签名  
	 * @param: request
	 * @param: bpmSealSign
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/insertBpmSealSign",method=RequestMethod.POST)
	public RetDataBean insertBpmSealSign(HttpServletRequest request,BpmSealSign bpmSealSign,String attachId)
	{
		try
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("您不是管理员,请与管理员联系!");
			}
			bpmSealSign.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			bpmSealSign.setCreateUser(account.getAccountId());
			bpmSealSign.setSealSignId(SysTools.getGUID());
			bpmSealSign.setOrgId(account.getOrgId());
			return RetDataTools.Ok("签名添加成功！", bpmSealSignService.addBpmSealSign(bpmSealSign,attachId));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: deleteBpmSealSign   
	 * @Description: TODO 删除签名
	 * @param: request
	 * @param: bpmSealSign
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/deleteBpmSealSign",method=RequestMethod.POST)
	public RetDataBean deleteBpmSealSign(HttpServletRequest request,BpmSealSign bpmSealSign)
	{
		try
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			if(StringUtils.isBlank(bpmSealSign.getSealSignId()))
			{
				return RetDataTools.NotOk("请求参数有问题,请检查!");
			}
			bpmSealSign.setOrgId(account.getOrgId());
			return RetDataTools.Ok("签名删除成功！", bpmSealSignService.deleteBpmSealSign(bpmSealSign));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: updateBpmSealSign   
	 * @Description: TODO 更新签名
	 * @param: request
	 * @param: bpmSealSign
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/updateBpmSealSign",method=RequestMethod.POST)
	public RetDataBean updateBpmSealSign(HttpServletRequest request,BpmSealSign bpmSealSign,String attachId)
	{
		try
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			if(StringUtils.isBlank(bpmSealSign.getSealSignId()))
			{
				return RetDataTools.NotOk("请求参数有问题,请检查!");
			}
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("您不是管理员,请与管理员联系!");
			}
			
			
			bpmSealSign.setOrgId(account.getOrgId());
			Example example = new Example(BpmSealSign.class);
			example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("sealSignId",bpmSealSign.getSealSignId());
			return RetDataTools.Ok("签名删除成功！", bpmSealSignService.deleteBpmSealSign(bpmSealSign));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: insertBpmSort   
	 * @Description: TODO 添加新分类
	 * @param request
	 * @param bpmSort
	 * @return      
	 * @return: RetDataBean      

	 */
	@RequestMapping(value="/insertBpmSort",method=RequestMethod.POST)
	public RetDataBean insertBpmSort(HttpServletRequest request,BpmSort bpmSort)
	{
		try
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			if(StringUtils.isBlank(bpmSort.getLevelId()))
			{
				bpmSort.setLevelId("0");
			}
			if(StringUtils.isBlank(bpmSort.getManageAccountId()))
			{
				bpmSort.setManageAccountId(account.getAccountId());
			}
			bpmSort.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			bpmSort.setCreateUser(account.getAccountId());
			bpmSort.setBpmSortId(SysTools.getGUID());
			bpmSort.setOrgId(account.getOrgId());
			return RetDataTools.Ok("分类添加成功！", bpmSortService.insertBpmSort(bpmSort));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: delBpmSort   
	 * @Description: TODO 删除工作流分类
	 * @param request
	 * @param bpmSort
	 * @return      
	 * @return: RetDataBean      

	 */
	@RequestMapping(value="/delBpmSort",method=RequestMethod.POST)
	public RetDataBean delBpmSort(HttpServletRequest request,BpmSort bpmSort)
	{
		try
		{
			if(StringUtils.isBlank(bpmSort.getBpmSortId()))
			{
				return RetDataTools.NotOk("请求参数有问题,请检查!");
			}
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			bpmSort.setOrgId(account.getOrgId());
			return RetDataTools.Ok("请求成功！", bpmSortService.delBpmSort(bpmSort));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: updateBpmSort   
	 * @Description: TODO 更新分类信息
	 * @param request
	 * @param bpmSort
	 * @return      
	 * @return: RetDataBean      

	 */
	@RequestMapping(value="/updateBpmSort",method=RequestMethod.POST)
	public RetDataBean updateBpmSort(HttpServletRequest request,BpmSort bpmSort)
	{
		try
		{
			if(StringUtils.isBlank(bpmSort.getBpmSortId()))
			{
				return RetDataTools.NotOk("请求参数有问题,请检查!");
			}
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			if(StringUtils.isBlank(bpmSort.getLevelId()))
			{
				bpmSort.setLevelId("0");
			}
			bpmSort.setOrgId(account.getOrgId());
			Example example = new Example(BpmSort.class);
			example.createCriteria().andEqualTo("orgId",bpmSort.getOrgId()).andEqualTo("bpmSortId",bpmSort.getBpmSortId());
			return RetDataTools.Ok("更新分类成功！", bpmSortService.updateBpmSort(bpmSort,example));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: insertBpmForm   
	 * @Description: TODO 添加表单基本信息
	 * @param request
	 * @param bpmForm
	 * @return      
	 * @return: RetDataBean      

	 */
	@RequestMapping(value="/insertBpmForm",method=RequestMethod.POST)
	public RetDataBean insertBpmForm(HttpServletRequest request,BpmForm bpmForm)
	{
		try
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("对不起,您不是系统管理员!"); 
			}
			bpmForm.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			bpmForm.setCreateUser(account.getAccountId());
			bpmForm.setFormId(SysTools.getGUID());
			bpmForm.setOrgId(account.getOrgId());
			return RetDataTools.Ok("添加表单成功！", bpmFormService.insertBpmForm(bpmForm));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: deleteBpmForm   
	 * @Description: TODO删除表单
	 * @param request
	 * @param bpmForm
	 * @return      
	 * @return: RetDataBean      

	 */
	@RequestMapping(value="/deleteBpmForm",method=RequestMethod.POST)
	public RetDataBean deleteBpmForm(HttpServletRequest request,BpmForm bpmForm)
	{
		try
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("对不起,您不是系统管理员!"); 
			}
			if(StringUtils.isNotBlank(bpmForm.getFormId()))
			{
				BpmFlow bpmFlow = new BpmFlow();
				bpmFlow.setFormId(bpmForm.getFormId());
				bpmFlow.setOrgId(account.getOrgId());
				if(bpmFlowService.getBpmFlowCount(bpmFlow)==0)
				{
					bpmForm.setOrgId(account.getOrgId());
					return RetDataTools.Ok("删除表单成功！", bpmFormService.deleteBpmForm(bpmForm));
				}else
				{
					return RetDataTools.NotOk("对不起,目前有流程正在使用该表单,删除失败!");
				}
			}else
			{
				return RetDataTools.NotOk("参数格式有问题!"); 
			}
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title updateBpmForm   
	 * @Description TODO 更新表单Style 和 js 脚本  
	 * @param request
	 * @param bpmForm
	 * @return      
	 * RetDataBean
	 */
	@RequestMapping(value="/updateBpmForm",method=RequestMethod.POST)
	public RetDataBean updateBpmForm(HttpServletRequest request,BpmForm bpmForm)
	{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			return bpmFormService.doUpdateBpmForm(account,bpmForm);
	}
	/**
	 * 
	 * @Title: updateMobileBpmForm   
	 * @Description: TODO 更新移动端表单模版
	 * @param: request
	 * @param: bpmForm
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/updateMobileBpmForm",method=RequestMethod.POST)
	public RetDataBean updateMobileBpmForm(HttpServletRequest request,BpmForm bpmForm)
	{
		try
		{
				if(StringUtils.isBlank(bpmForm.getFormId()))
				{
					return RetDataTools.NotOk("对不起，您请求的参数有问题！");
				}
					Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
				if(!account.getOpFlag().equals("1"))
				{
					return RetDataTools.NotOk("您不是管理员，请与管理员联系！");
				}else
				{
					Example example = new Example(BpmForm.class);
					example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("formId",bpmForm.getFormId());
					return RetDataTools.Ok("保存成功！",bpmFormService.updateBpmForm(bpmForm, example));
		}
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
		}
			
	}
	
	/**
	 * 
	 * @Title updateBpmFormHtmlCode   
	 * @Description TODO 更新表单代码，同时更新并生成表单数据库表结构   
	 * @param request
	 * @param bpmForm
	 * @return      
	 * RetDataBean
	 */
	@RequestMapping(value="/updateBpmFormHtmlCode",method=RequestMethod.POST)
	public RetDataBean updateBpmFormHtmlCode(HttpServletRequest request,BpmForm bpmForm)
	{
		Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
		return bpmFormService.updateBpmFormHtmlCode(account,bpmForm);
}

/**
 * 	
 * @Title insertBpmProcess   
 * @Description TODO 新建步骤
 * @param request
 * @param bpmProcess
 * @return      
 * RetDataBean
 */
@RequestMapping(value="/insertBpmProcess",method=RequestMethod.POST)
public RetDataBean insertBpmProcess(HttpServletRequest request,BpmProcess bpmProcess,String parentId)
{
	Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
	return bpmProcessService.createProcessNormal(account,bpmProcess,parentId);
}

/**
 * 
 * @Title saveBpmProcessLayout   
 * @Description TODO 批量更新布局  
 * @param request
 * @param bpmProcesss
 * @return      
 * RetDataBean
 */
@RequestMapping(value="/saveBpmProcessLayout",method=RequestMethod.POST)
public RetDataBean saveBpmProcessLayout(HttpServletRequest request,@RequestBody BpmProcess[] bpmProcessList)
{
try
	{
		Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
		return RetDataTools.Ok("更新布局成功！", bpmProcessService.saveBpmProcessLayout(bpmProcessList, account));
	}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title insertBpmFlow   
 * @Description TODO 创建流程 
 * @param request
 * @param bpmFlow
 * @return      
 * RetDataBean
 */
@RequestMapping(value="/insertBpmFlow",method=RequestMethod.POST)
public RetDataBean insertBpmFlow(HttpServletRequest request,BpmFlow bpmFlow)
{
	try
	{
		Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
		bpmFlow.setFlowId(SysTools.getGUID());
		bpmFlow.setCreateUser(account.getAccountId());
		bpmFlow.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
		if(StringUtils.isBlank(bpmFlow.getFreeToOther()))
		{
			bpmFlow.setFreeToOther("0");
		}
		bpmFlow.setOrgId(account.getOrgId());
		return RetDataTools.Ok("创建流程成功！", bpmFlowService.insertBpmFlow(bpmFlow));
	}catch (Exception e) {
		// TODO: handle exception
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title updateBpmFlow   
 * @Description TODO 更新流程
 * @param request
 * @param bpmFlow
 * @return      
 * RetDataBean
 */
@RequestMapping(value="/updateBpmFlow",method=RequestMethod.POST)
public RetDataBean updateBpmFlow(HttpServletRequest request,BpmFlow bpmFlow)
{
	Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
	return bpmFlowService.doUpdateBpmFlow(account,bpmFlow);
}

/**
 * 
 * @Title deleteBpmFlow   
 * @Description TODO 删除流程
 * @param request
 * @param bpmFlow
 * @return      
 * RetDataBean
 */
@RequestMapping(value="/deleteBpmFlow",method=RequestMethod.POST)
public RetDataBean deleteBpmFlow(HttpServletRequest request,BpmFlow bpmFlow)
{
	try
	{
		if(StringUtils.isBlank(bpmFlow.getFlowId()))
		{
			return RetDataTools.NotOk("请求参数有问题,请检查!");
		}
		Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
		bpmFlow.setOrgId(account.getOrgId());
		return RetDataTools.Ok("更新流程成功！", bpmFlowService.deleteBpmFlow(bpmFlow));
	}catch (Exception e) {
		// TODO: handle exception
		return RetDataTools.Error(e.getMessage());
	}
}
/**
 * 
 * @Title updateBpmProcess   
 * @Description TODO 更新步骤设置  
 * @param request
 * @param bpmProcess
 * @return      
 * RetDataBean
 */
@RequestMapping(value="/updateBpmProcess",method=RequestMethod.POST)
public RetDataBean updateBpmProcess(HttpServletRequest request,BpmProcess bpmProcess)
{
	Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
	return  bpmProcessService.doUpdateBpmProcess(account,bpmProcess);
}		
/**
 * 
 * @Title deleteBpmProcess   
 * @Description TODO 删除步骤ID  
 * @param request
 * @param bpmProcess
 * @return      
 * RetDataBean
 */
@RequestMapping(value="/deleteBpmProcess",method=RequestMethod.POST)
public RetDataBean deleteBpmProcess(HttpServletRequest request,BpmProcess bpmProcess)
{
		try
		{
			if(StringUtils.isBlank(bpmProcess.getProcessId()))
			{
				return RetDataTools.NotOk("请求参数有问题,请检查!");
			}
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			bpmProcess.setOrgId(account.getOrgId());
			if(StringUtils.isNotBlank(bpmProcess.getProcessId()))
			{
				return RetDataTools.Ok("删除成功！",bpmProcessService.deleteBpmProcess(bpmProcess));
			}else
			{
				return RetDataTools.NotOk("系统参数有问题!");
			}
			
		}catch (Exception e) {
				// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
}

/**
 * 
 * @Title toEndProcess   
 * @Description TODO 指向结束节点
 * @param request
 * @param bpmProcess
 * @return      
 * RetDataBean
 */
@RequestMapping(value="/toEndProcess",method=RequestMethod.POST)
public RetDataBean toEndProcess(HttpServletRequest request,BpmProcess bpmProcess)
{
	Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
	return bpmProcessService.toEndProcess(account, bpmProcess);
}	
/**
 * 
 * @Title: quickBpm   
 * @Description: TODO快速创建BPM流程
 * @param: request
 * @param: bpmList
 * @param: @return      
 * @return: RetDataBean      
 * @throws
 */
@RequestMapping(value="/quickBpm",method=RequestMethod.POST)
public RetDataBean quickBpm(HttpServletRequest request,String flowId)
{
		try
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			BpmFlow bpmFlow = new BpmFlow();
			bpmFlow.setOrgId(account.getOrgId());
			bpmFlow.setFlowId(flowId);
			bpmFlow = bpmFlowService.selectOne(bpmFlow);
			BpmList bpmList = new BpmList();
			String title = bpmUnitsService.getDocNumByBpmFlow(request, bpmFlow);
			if(StringUtils.isBlank(title))
			{
				UserInfo userInfo = (UserInfo)request.getSession().getAttribute("USER_INFO");
				bpmList.setFlowTitle(bpmFlow.getFlowName()+" "+userInfo.getUserName() +" "+SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			}else {
				bpmList.setFlowTitle(title);
			}
			bpmList.setRunId(SysTools.getGUID());
			bpmList.setFlowId(bpmFlow.getFlowId());
			bpmList.setDelFlag("0");
			bpmList.setStatus("0");
			bpmList.setEndTime("");
			bpmList.setUrgency("0");
			bpmList.setOpUserStr(account.getAccountId());
			bpmList.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			bpmList.setCreateUser(account.getAccountId());
			bpmList.setOrgId(account.getOrgId());
			return bpmOptService.startBpm(bpmList,bpmFlow);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
}


/**
 * 
 * @Title startBpm   
 * @Description TODO 发起BPM流程
 * @param request
 * @param bpmProcess
 * @return      
 * RetDataBean
 */
@RequestMapping(value="/startBpm",method=RequestMethod.POST)
public RetDataBean startBpm(HttpServletRequest request,BpmList bpmList)
{
		try
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			bpmList.setRunId(SysTools.getGUID());
			bpmList.setDelFlag("0");
			bpmList.setStatus("0");
			bpmList.setEndTime("");
			bpmList.setOpUserStr(account.getAccountId());
			bpmList.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			bpmList.setCreateUser(account.getAccountId());
			bpmList.setOrgId(account.getOrgId());
			BpmFlow bpmFlow = new BpmFlow();
			bpmFlow.setOrgId(account.getOrgId());
			bpmFlow.setFlowId(bpmList.getFlowId());
			bpmFlow = bpmFlowService.selectOne(bpmFlow);
			return bpmOptService.startBpm(bpmList,bpmFlow);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
}
/**
 * 
* @Title: delBpmList 
* @Description: TODO 管理员逻辑删除BPM流程
* @param @param request
* @param @param bpmList
* @param @return 设定文件 
* @return RetDataBean 返回类型
 */
@RequestMapping(value="/delBpmList",method=RequestMethod.POST)
public RetDataBean delBpmList(HttpServletRequest request,BpmList bpmList)
{
		try
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			bpmList.setOrgId(account.getOrgId());
			if(account.getOpFlag().equals("1"))
			{
				return RetDataTools.Ok("物理删除成功！",bpmListService.deleteBpmList(bpmList,account));
			}else
			{
				return RetDataTools.NotOk("系统参数有问题!");
			}
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
}

/**
 * 
* @Title: stopEntrust 
* @Description: TODO 创建BPM流程委托规则
* @param @param request
* @param @param bpmEntrust
* @param @return 设定文件 
* @return RetDataBean 返回类型
 */
@RequestMapping(value="/stopEntrust",method=RequestMethod.POST)
public RetDataBean stopEntrust(HttpServletRequest request,BpmEntrust bpmEntrust)
{
		try
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			if(StringUtils.isNotBlank(bpmEntrust.getEntrustId()))
			{
				bpmEntrust.setStatus("1");
					Example example = new Example(BpmEntrust.class);
					example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("entrustId",bpmEntrust.getEntrustId()).andEqualTo("fromId",account.getAccountId());
					return RetDataTools.Ok("终止成功！",bpmEntrustService.updateBpmEntrust(bpmEntrust, example));
			}else
			{
				return RetDataTools.NotOk("系统参数有问题!");
			}
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
}
/**
 * 
* @Title: delBpm 
* @Description: TODO 删除BPM流程
* @param @param request
* @param @param runId
* @param @param runProcessId
* @param @return 设定文件 
* @return RetDataBean 返回类型
 */
@RequestMapping(value="/delBpm",method=RequestMethod.POST)
public RetDataBean delBpm(HttpServletRequest request,String runId, String runProcessId)
{
	try
	{
		Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
		if(StringUtils.isNotBlank(runProcessId)&&StringUtils.isNotBlank(runId))
		{
			return bpmOptService.delBpm(account, runId, runProcessId);
		}else
		{
			return RetDataTools.NotOk("系统参数有问题!");
		}
	}catch (Exception e) {
		return RetDataTools.Error(e.getMessage());
	}
}


/**
 * 
* @Title: delEntrust 
* @Description: TODO 删除指定的委托规则
* @param @param request
* @param @param bpmEntrust
* @param @return 设定文件 
* @return RetDataBean 返回类型
 */

@RequestMapping(value="/delEntrust",method=RequestMethod.POST)
public RetDataBean delEntrust(HttpServletRequest request,BpmEntrust bpmEntrust)
{
		try
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			if(StringUtils.isNotBlank(bpmEntrust.getEntrustId()))
			{
				bpmEntrust.setFromId(account.getAccountId());
				bpmEntrust.setOrgId(account.getOrgId());
				return RetDataTools.Ok("终止成功！",bpmEntrustService.deleteBpmEntrust(bpmEntrust));
			}else
			{
				return RetDataTools.NotOk("系统参数有问题!");
			}
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
}

@RequestMapping(value="/createEntrust",method=RequestMethod.POST)
public RetDataBean createEntrust(HttpServletRequest request,BpmEntrust bpmEntrust)
{
		try
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			if(StringUtils.isNotBlank(bpmEntrust.getFlowId()))
			{
				if(bpmEntrustService.isExist(account.getOrgId(), bpmEntrust.getFlowId(), account.getAccountId())>0)
				{
					bpmEntrust.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
					Example example = new Example(BpmEntrust.class);
					example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("flowId",bpmEntrust.getFlowId());
					return RetDataTools.Ok("委托成功！",bpmEntrustService.updateBpmEntrust(bpmEntrust, example));
					
				}else
				{
					bpmEntrust.setEntrustId(SysTools.getGUID());
					bpmEntrust.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
					bpmEntrust.setCreateUser(account.getAccountId());
					bpmEntrust.setFromId(account.getAccountId());
					bpmEntrust.setOrgId(account.getOrgId());
					bpmEntrust.setStatus("0");
					return RetDataTools.Ok("委托成功！",bpmEntrustService.insertBpmEntrust(bpmEntrust));
				}
			}else
			{
				return RetDataTools.NotOk("系统参数有问题!");
			}
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
}

/**
 * 
 * @Title goSaveFormData   
 * @Description TODO 保存BPM表单数据
 * @param request
 * @param bpmList
 * @return      
 * RetDataBean
 */
@Transactional(value="generalTM")
@RequestMapping(value="/saveFormData",method=RequestMethod.POST)
public RetDataBean goSaveFormData(HttpServletRequest request,BpmList bpmList,BpmRunProcess bpmRunProcess,String formData)
{
		try
		{
			JSONObject formDataJson = JSONObject.parseObject(formData);
			Map<String,String> formDataMap =(Map)formDataJson;
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			bpmRunProcess.setOrgId(account.getOrgId());
			bpmList.setOrgId(account.getOrgId());
			
			BpmRunProcess newBpmRunProcess = new BpmRunProcess();
			newBpmRunProcess.setOrgId(account.getOrgId());
			newBpmRunProcess.setRunId(bpmRunProcess.getRunId());
			newBpmRunProcess.setRunProcessId(bpmRunProcess.getRunProcessId());
			newBpmRunProcess = bpmRunProcessService.selectOne(newBpmRunProcess);
			bpmRunProcess.setProcessId(newBpmRunProcess.getProcessId());
			bpmRunProcess.setAttach(request.getParameter("runAttach"));
			
			BpmProcess bpmProcess = new BpmProcess();
			bpmProcess.setProcessId(newBpmRunProcess.getProcessId());
			bpmProcess.setOrgId(newBpmRunProcess.getOrgId());
			bpmProcess = bpmProcessService.selectOne(bpmProcess);
			
			BpmFlow bpmFlow = new BpmFlow();
			bpmFlow.setOrgId(bpmProcess.getOrgId());
			bpmFlow.setFlowId(bpmProcess.getFlowId());
			bpmFlow=bpmFlowService.selectOne(bpmFlow);
			
			
			BpmForm bpmForm = new BpmForm();
			bpmForm.setFormId(bpmFlow.getFormId());
			bpmForm.setOrgId(account.getOrgId());
			bpmForm = bpmFormService.selectOneBpmForm(bpmForm);
			
			return RetDataTools.Ok("工作保存成功!",bpmOptService.saveBpmFormData(account, formDataMap, bpmList, bpmForm, bpmRunProcess));
		}catch (Exception e) {
				// TODO: handle exception
			e.printStackTrace();
			return RetDataTools.Error(e.getMessage());
		}
}







/**
 * 
 * @Title: goNextProcess   
 * @Description: TODO 下一步
 * @param: request
 * @param: nextPrcsInfo
 * @param: runId
 * @param: runProcessId
 * @param: processId
 * @param: @return      
 * @return: RetDataBean      
 * @throws
 */
@RequestMapping(value="/goNextProcess",method=RequestMethod.POST)
public RetDataBean goNextProcess(HttpServletRequest request,String nextPrcsInfo,String runId,
		String runProcessId,String remindNextUser,String remindCreateUser,String remindParticipant,String msgContent,String autoSendUser)
{
	Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
	UserInfo userInfo = (UserInfo)request.getSession().getAttribute("USER_INFO");
	try
	{
	return bpmRunProcessService.goNextProcess(userInfo,account, nextPrcsInfo, runId, runProcessId, remindNextUser,remindCreateUser,remindParticipant, msgContent,autoSendUser);
	}catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Fail();
	}
	}

/**
 * 
 * @Title: doNotPassEndBpm   
 * @Description: TODO 审批意见不同意时中途结束流程
 * @param request
 * @param runId
 * @param runProcessId
 * @return
 * RetDataBean    
 * @throws
 */
@RequestMapping(value="/doNotPassEndBpm",method=RequestMethod.POST)
public RetDataBean doNotPassEndBpm(HttpServletRequest request,String runId,String runProcessId)
{
	try
	{
		if(StringUtils.isBlank(runId)||StringUtils.isBlank(runProcessId))
		{
			return RetDataTools.NotOk("请求参数有问题!");
		}else
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			return RetDataTools.Ok("流程结束",bpmRunProcessService.doNotPassEndBpm(account, runId, runProcessId));
		}
}catch (Exception e) {
		// TODO: handle exception
	return RetDataTools.Error(e.getMessage());
}
	
}

/**
 * 
 * @Title: setGobackOpt   
 * @Description: TODO BPM 回退操作
 * @param: request
 * @param: runId
 * @param: runProcessId
 * @param: accountId
 * @param: @return      
 * @return: RetDataBean      
 * @throws
 */
@RequestMapping(value="/setGobackOpt",method=RequestMethod.POST)
public RetDataBean setGobackOpt(HttpServletRequest request,BpmRunProcess bpmRunProcess)
{
	try
	{
		if(StringUtils.isBlank(bpmRunProcess.getRunProcessId()))
		{
			return RetDataTools.NotOk("请求参数有问题!");
		}else
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			bpmRunProcess.setOrgId(account.getOrgId());
			bpmRunProcess.setAccountId(account.getAccountId());
			bpmRunProcess = bpmRunProcessService.selectOne(bpmRunProcess);
			return RetDataTools.Ok("回退成功!",bpmRunProcessService.setGobackOpt(bpmRunProcess));
		}
}catch (Exception e) {
		// TODO: handle exception
	return RetDataTools.Error(e.getMessage());
}
}
/**
 * 
 * @Title: setCanGobackOpt   
 * @Description: TODO 任一一步回退
 * @param: request
 * @param: runProcessId
 * @param: tagerRunProcessId
 * @param: @return      
 * @return: RetDataBean      
 * @throws
 */
@RequestMapping(value="/setCanGobackOpt",method=RequestMethod.POST)
public RetDataBean setCanGobackOpt(HttpServletRequest request,String runId,String runProcessId,String tagerRunProcessId)
{
	try
	{
		if(StringUtils.isBlank(runProcessId)||StringUtils.isBlank(tagerRunProcessId)||StringUtils.isBlank(runId))
		{
			return RetDataTools.NotOk("请求参数有问题!");
		}else
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			BpmRunProcess bpmRunProcess = new BpmRunProcess();
			bpmRunProcess.setRunProcessId(runProcessId);
			bpmRunProcess.setRunId(runId);
			bpmRunProcess.setOrgId(account.getOrgId());
			bpmRunProcess.setAccountId(account.getAccountId());
			bpmRunProcess = bpmRunProcessService.selectOne(bpmRunProcess);
			if(bpmRunProcess==null)
			{
				return RetDataTools.NotOk("请求参数有问题!");
			}
			BpmRunProcess tagerBpmRunProcess = new BpmRunProcess();
			tagerBpmRunProcess.setRunProcessId(tagerRunProcessId);
			tagerBpmRunProcess.setRunId(runId);
			tagerBpmRunProcess.setOrgId(account.getOrgId());
			tagerBpmRunProcess = bpmRunProcessService.selectOne(tagerBpmRunProcess);
			if(tagerBpmRunProcess==null)
			{
				return RetDataTools.NotOk("请求参数有问题!");
			}
			return RetDataTools.Ok("回退成功!",bpmRunProcessService.setCanGobackOpt(bpmRunProcess,tagerBpmRunProcess));
		}
}catch (Exception e) {
		// TODO: handle exception
	return RetDataTools.Error(e.getMessage());
}
}

@RequestMapping(value="/setMaintainGobackOpt",method=RequestMethod.POST)
public RetDataBean setMaintainGobackOpt(HttpServletRequest request,BpmRunProcess bpmRunProcess)
{
	try
	{
		if(StringUtils.isBlank(bpmRunProcess.getRunProcessId()))
		{
			return RetDataTools.NotOk("请求参数有问题!");
		}else
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			bpmRunProcess.setOrgId(account.getOrgId());
			bpmRunProcess = bpmRunProcessService.selectOne(bpmRunProcess);
			return RetDataTools.Ok("管理员回退成功!",bpmRunProcessService.setMaintainGobackOpt(account,bpmRunProcess));
		}
}catch (Exception e) {
		// TODO: handle exception
	return RetDataTools.Error(e.getMessage());
}
}

/**
 * 
* @Title: doCompleteBpm 
* @Description: TODO 办理完成
* @param @param request
* @param @param runId
* @param @param runProcessId
* @param @return 设定文件 
* @return RetDataBean 返回类型
 */
@RequestMapping(value="/doCompleteBpm",method=RequestMethod.POST)
public RetDataBean doCompleteBpm(HttpServletRequest request,String runId,String runProcessId)
{
		try
		{
			if(StringUtils.isNotBlank(runId)&&StringUtils.isNotBlank(runProcessId))
			{
				String createTime = SysTools.getTime("yyyy-MM-dd HH:mm:ss");
				Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
				BpmList bpmList = new BpmList();
				bpmList.setRunId(runId);
				bpmList.setOrgId(account.getOrgId());
				bpmList=bpmListService.selectOne(bpmList);
				BpmRunProcess bpmRunProcess = new BpmRunProcess();
				bpmRunProcess.setOrgId(account.getOrgId());
				bpmRunProcess.setRunProcessId(runProcessId);
				bpmRunProcess = bpmRunProcessService.selectOne(bpmRunProcess);
				bpmRunProcess.setEndTime(createTime);
				BpmProcess bpmProcess = new BpmProcess();
				bpmProcess.setOrgId(account.getOrgId());
				bpmProcess.setProcessId(bpmRunProcess.getProcessId());
				bpmProcess = bpmProcessService.selectOne(bpmProcess);
				if(bpmProcess.getOpRule().equals("3"))
				{
				bpmOptService.setOpFlagByOpRule(bpmProcess, bpmRunProcess);
				}
				return bpmRunProcessService.doCompleteBpm(request,bpmProcess,bpmList, bpmRunProcess);
				
			}else
			{
				return RetDataTools.NotOk("参数有问题!");
			}
		}catch (Exception e) {
				// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
}

/**
 * 
* @Title: changeBpmUser 
* @Description: TODO BPM流程转交
* @param @param request
* @param @param runId
* @param @param runProcessId
* @param @param accountId
* @param @return 设定文件 
* @return RetDataBean 返回类型
 */
@RequestMapping(value="/changeBpmUser",method=RequestMethod.POST)
public RetDataBean changeBpmUser(HttpServletRequest request,String runId,String runProcessId,String accountId)
{
	try
	{
		if(StringUtils.isBlank(runProcessId))
		{
			return RetDataTools.NotOk("参数有问题!");
		}else
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			UserInfo userInfo = (UserInfo)request.getSession().getAttribute("USER_INFO");
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("您无权限转交BPM流程,请与系统管理员联系!");
			}
			BpmRunProcess bpmRunProcess = new BpmRunProcess();
			bpmRunProcess.setOrgId(account.getOrgId());
			bpmRunProcess.setRunProcessId(runProcessId);
			bpmRunProcess.setAccountId(accountId);
			bpmRunProcess.setCreateUser(account.getAccountId());
			bpmRunProcess.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			Example example = new Example(BpmRunProcess.class);
			example.createCriteria().andEqualTo("orgId",bpmRunProcess.getOrgId()).andEqualTo("runProcessId",bpmRunProcess.getRunProcessId()).andEqualTo("runId",bpmRunProcess.getRunId());
			return RetDataTools.Ok("流程转交成功!",bpmRunProcessService.changeBpmUser(account,userInfo,bpmRunProcess, example));
		}
}catch (Exception e) {
		// TODO: handle exception
	return RetDataTools.Error(e.getMessage());
}
}

/**
 * 
 * @Title: updateBpmList   
 * @Description: TODO 更新BPM
 * @param: request
 * @param: bpmList
 * @param: @return      
 * @return: RetDataBean      
 * @throws
 */
@RequestMapping(value="/updateBpmList",method=RequestMethod.POST)
public RetDataBean updateBpmList(HttpServletRequest request,BpmList bpmList)
{
	try
	{
		if(StringUtils.isBlank(bpmList.getRunId()))
		{
			return RetDataTools.NotOk("参数有问题!");
		}
		Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
		bpmList.setOrgId(account.getOrgId());
		Example example = new Example(BpmList.class);
		example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("runId",bpmList.getRunId());
		return RetDataTools.Ok("操作成功!",bpmListService.updateBpmList(bpmList, example));	
	}catch (Exception e) {
	return RetDataTools.Error(e.getMessage());
	}
}
/**
 * 
* @Title: setFollowOpt 
* @Description: TODO 关注设置
* @param @param request
* @param @param bpmList
* @param @param isFollow
* @param @return 设定文件 
* @return RetDataBean 返回类型
 */
@RequestMapping(value="/setFollowOpt",method=RequestMethod.POST)
public RetDataBean setFollowOpt(HttpServletRequest request,BpmList bpmList,boolean isFollow)
{
	try
	{
		if(StringUtils.isBlank(bpmList.getRunId()))
		{
			return RetDataTools.NotOk("参数有问题!");
		}
		Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
		bpmList.setOrgId(account.getOrgId());
		bpmList = bpmListService.selectOne(bpmList);
		return RetDataTools.Ok("关注更新!",bpmListService.setFollow(bpmList, isFollow,account));	
	}catch (Exception e) {
	return RetDataTools.Error(e.getMessage());
	}
}
/**
 * 
* @Title: doBpmUrge 
* @Description: TODO BPM催办
* @param @param request
* @param @param runIds
* @param @return 设定文件 
* @return RetDataBean 返回类型
 */
@RequestMapping("/doBpmUrge")
public RetDataBean doBpmUrge(HttpServletRequest request,String runIds) {
	try {
		if(StringUtils.isBlank(runIds)) {
			return RetDataTools.NotOk("请求参数有问题,请检查!");
		}
		Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
		UserInfo userInfo=(UserInfo)request.getSession().getAttribute("USER_INFO");
		bpmOptService.doBpmUrge(account,userInfo, runIds);
		return RetDataTools.Ok("催办信息已发送!","");
	} catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Error(e.getMessage());
	}
}


/**
 * 
 * @Title: doTaskBack   
 * @Description: TODO 收回BPM步骤
 * @param: request
 * @param: bpmRunProcess
 * @param: @return      
 * @return: RetDataBean      
 * @throws
 */
@RequestMapping("/doTaskBack")
public RetDataBean doTaskBack(HttpServletRequest request,BpmRunProcess bpmRunProcess) {
	try {
		UserInfo userInfo=(UserInfo)request.getSession().getAttribute("USER_INFO");
		if(StringUtils.isBlank(bpmRunProcess.getRunProcessId())) {
			return RetDataTools.NotOk("请求参数有问题,请检查!");
		}
		bpmRunProcess.setOrgId(userInfo.getOrgId());
		bpmRunProcessService.doTaskBack(userInfo,bpmRunProcess);
		return RetDataTools.Ok("BPM收回成功!");
	} catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Error(e.getMessage());
	}
}
/**
 * 
 * @Title: batchUpdateFormCache   
 * @Description: TODO 批量更新缓存
 * @param request
 * @param paramStr
 * @return
 * RetDataBean    
 * @throws
 */
@RequestMapping("/batchUpdateFormCache")
public RetDataBean batchUpdateFormCache(HttpServletRequest request,String paramStr) {
	try {
		Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
		if(StringUtils.isBlank(paramStr)) {
			return RetDataTools.NotOk("请求参数有问题,请检查!");
		}else
		{
			JSONArray jsonArray = JSONArray.parseArray(paramStr);
			bpmFormCacheService.batchUpdateFormCache(account, jsonArray);
			return RetDataTools.Ok("BPM表单缓存更新成功!");
		}
	} catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title: updateFormCache   
 * @Description: TODO 更新表缓存
 * @param request
 * @param type
 * @param value
 * @param bpmProcess
 * @return
 * RetDataBean    
 * @throws
 */
@RequestMapping("/updateFormCache")
public RetDataBean updateFormCache(HttpServletRequest request,String type,String value,BpmProcess bpmProcess) {
	try {
		if(StringUtils.isBlank(type)||StringUtils.isBlank(bpmProcess.getProcessId())) {
			return RetDataTools.NotOk("请求参数有问题,请检查!");
		}else
		{
			Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
			if(account.getOpFlag().equals("1"))
			{
				bpmProcess.setOrgId(account.getOrgId());
				bpmProcess = bpmProcessService.selectOne(bpmProcess);
				if(type.equals("html"))
				{
					bpmFormCacheService.updateHtmlCache(bpmProcess, value);
					return RetDataTools.Ok("更新BPM表单缓存成功！");
				}else if(type.equals("style"))
				{
					bpmFormCacheService.updateStyleCache(bpmProcess, value);
					return RetDataTools.Ok("更新BPM表单缓存成功！");
				}else if(type.equals("script"))
				{
					bpmFormCacheService.updateScriptCache(bpmProcess, value);
					return RetDataTools.Ok("更新BPM表单缓存成功！");
				}else if(type.equals("mobilehtml"))
				{
					bpmFormCacheService.updateMobileHtmlCache(bpmProcess, value);
					return RetDataTools.Ok("更新BPM表单缓存成功！");
				}else if(type.equals("mobilestyle"))
				{
					bpmFormCacheService.updateMobileStyleCache(bpmProcess, value);
					return RetDataTools.Ok("更新BPM表单缓存成功！");
				}else if(type.equals("mobilescript"))
				{
					bpmFormCacheService.updateMobileScriptCache(bpmProcess, value);
					return RetDataTools.Ok("更新BPM表单缓存成功！");
				}else
				{
					return RetDataTools.NotOk("BPM表单缓存更新失败!");
				}
			}else
			{
				return RetDataTools.NotOk("您不是管理员！请与管理员联系！");
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Error(e.getMessage());
	}
}



}
