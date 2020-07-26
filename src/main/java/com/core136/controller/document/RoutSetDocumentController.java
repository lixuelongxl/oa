package com.core136.controller.document;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.core136.common.retdataunit.RetDataBean;
import org.core136.common.retdataunit.RetDataTools;
import org.core136.common.utils.SysTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.core136.bean.account.Account;
import com.core136.bean.account.UserInfo;
import com.core136.bean.bpm.BpmEntrust;
import com.core136.bean.bpm.BpmFlow;
import com.core136.bean.bpm.BpmForm;
import com.core136.bean.document.DocumentList;
import com.core136.bean.document.DocumentNumber;
import com.core136.bean.document.DocumentPluginsRegister;
import com.core136.bean.document.DocumentRunProcess;
import com.core136.bean.document.DocumentSendTo;
import com.core136.bean.document.DocumentTemplate;
import com.core136.bean.document.DocumentProcess;
import com.core136.bean.document.DocumentChildProcess;
import com.core136.bean.document.DocumentEntrust;
import com.core136.bean.document.DocumentFlow;
import com.core136.bean.document.DocumentForm;
import com.core136.bean.document.DocumentSort;
import com.core136.service.account.AccountService;
import com.core136.service.document.DocumentChildProcessService;
import com.core136.service.document.DocumentEntrustService;
import com.core136.service.document.DocumentFlowService;
import com.core136.service.document.DocumentFormService;
import com.core136.service.document.DocumentFormVersionService;
import com.core136.service.document.DocumentListFileService;
import com.core136.service.document.DocumentListService;
import com.core136.service.document.DocumentNumberService;
import com.core136.service.document.DocumentOptService;
import com.core136.service.document.DocumentPluginsRegisterService;
import com.core136.service.document.DocumentProcessService;
import com.core136.service.document.DocumentRunProcessService;
import com.core136.service.document.DocumentSendToService;
import com.core136.service.document.DocumentSortService;
import com.core136.service.document.DocumentTemplateService;

import tk.mybatis.mapper.entity.Example;

@RestController
@RequestMapping("/set/documentset")
public class RoutSetDocumentController {
	@Autowired
	private DocumentSortService documentSortService;
	@Autowired
	private DocumentFormService documentFormService;
	@Autowired
	private DocumentFlowService documentFlowService;
	@Autowired
	private DocumentProcessService documentProcessService;
	@Autowired
	private DocumentChildProcessService documentChildProcessService;
	@Autowired
	private DocumentOptService documentOptService;
	@Autowired
	private DocumentRunProcessService documentRunProcessService;
	@Autowired
	private DocumentListService documentListService;
	@Autowired
	private DocumentTemplateService documentTemplateService;
	@Autowired
	private DocumentListFileService documentListFileService;
	@Autowired
	private DocumentNumberService documentNumberService;
	@Autowired
	private DocumentPluginsRegisterService documentPluginsRegisterService;
	@Autowired
	private DocumentEntrustService documentEntrustService;
	@Autowired
	private DocumentSendToService documentSendToService;
	@Autowired
	private DocumentFormVersionService documentFormVersionService;
	@Autowired
	private AccountService accountService;

	/**
	 * 
	 * @Title: clonedocumentFlow   
	 * @Description: TODO 流程克隆
	 * @param: request
	 * @param: bpmFlow
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/clonedocumentFlow",method=RequestMethod.POST)
	public RetDataBean clonedocumentFlow(HttpServletRequest request,DocumentFlow documentFlow)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("您不是管理员,不能进行BPM克隆操作!");
			}
			if(StringUtils.isBlank(documentFlow.getFlowId()))
			{
				return RetDataTools.NotOk("请求有参数有问题,请与管理员联系!");
			}
			documentFlow.setOrgId(account.getOrgId());
			documentFlow = documentFlowService.selectOneDocumentFlow(documentFlow);
			return documentFlowService.cloneDocumentFlow(account,documentFlow);
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
			Account account=accountService.getRedisAccount(request);
			DocumentForm documentForm = new DocumentForm();
			documentForm.setFormId(formId);
			documentForm.setOrgId(account.getOrgId());
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("您不是管理员,不能进行导入操作!");
			}
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
				return documentFormService.importFormHtml(account,documentForm,file);
			}
			}catch (Exception e) {
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
	public RetDataBean updateSendToStatus(HttpServletRequest request,DocumentSendTo documentSendTo)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentSendTo.setRevTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			documentSendTo.setStatus("1");
			Example example = new Example(DocumentSendTo.class);
			example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("sendToId",documentSendTo.getSendToId());
			return RetDataTools.Ok("状态成功!",documentSendToService.updateDocumentSendTo(example, documentSendTo));
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	
	/**
	 * 
	 * @Title: doNotPassEndDocument   
	 * @Description: TODO 审批意见不同意时中途结束流程
	 * @param request
	 * @param runId
	 * @param runProcessId
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/doNotPassEndDocument",method=RequestMethod.POST)
	public RetDataBean doNotPassEndDocument(HttpServletRequest request,String runId,String runProcessId)
	{
		try
		{
			if(StringUtils.isBlank(runId)||StringUtils.isBlank(runProcessId))
			{
				return RetDataTools.NotOk("请求参数有问题!");
			}else
			{
				Account account=accountService.getRedisAccount(request);
				return RetDataTools.Ok("流程结束",documentRunProcessService.doNotPassEndDocument(account, runId, runProcessId));
			}
	}catch (Exception e) {
			// TODO: handle exception
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
	public RetDataBean delEntrust(HttpServletRequest request,DocumentEntrust documentEntrust)
	{
			try
			{
				Account account=accountService.getRedisAccount(request);
				if(StringUtils.isNotBlank(documentEntrust.getEntrustId()))
				{
					documentEntrust.setFromId(account.getAccountId());
					documentEntrust.setOrgId(account.getOrgId());
					return RetDataTools.Ok("删除委托成功！",documentEntrustService.deleteDocumentEntrust(documentEntrust));
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
	* @Description: TODO 创建公文流程委托规则
	* @param @param request
	* @param @param bpmEntrust
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value="/stopEntrust",method=RequestMethod.POST)
	public RetDataBean stopEntrust(HttpServletRequest request,DocumentEntrust documentEntrust)
	{
			try
			{
				Account account=accountService.getRedisAccount(request);
				if(StringUtils.isNotBlank(documentEntrust.getEntrustId()))
				{
					documentEntrust.setStatus("1");
						Example example = new Example(BpmEntrust.class);
						example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("entrustId",documentEntrust.getEntrustId()).andEqualTo("fromId",account.getAccountId());
						return RetDataTools.Ok("终止成功！",documentEntrustService.updateDocumentEntrust(documentEntrust, example));
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
	 * @Title: createEntrust   
	 * @Description: TODO 创建委托规则
	 * @param request
	 * @param documentEntrust
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/createEntrust",method=RequestMethod.POST)
	public RetDataBean createEntrust(HttpServletRequest request,DocumentEntrust documentEntrust)
	{
			try
			{
				Account account=accountService.getRedisAccount(request);
				if(StringUtils.isNotBlank(documentEntrust.getFlowId()))
				{
					if(documentEntrustService.isExist(account.getOrgId(), documentEntrust.getFlowId(), account.getAccountId())>0)
					{
						documentEntrust.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
						Example example = new Example(BpmEntrust.class);
						example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("flowId",documentEntrust.getFlowId());
						return RetDataTools.Ok("委托成功！",documentEntrustService.updateDocumentEntrust(documentEntrust, example));
						
					}else
					{
						documentEntrust.setEntrustId(SysTools.getGUID());
						documentEntrust.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
						documentEntrust.setCreateUser(account.getAccountId());
						documentEntrust.setFromId(account.getAccountId());
						documentEntrust.setOrgId(account.getOrgId());
						documentEntrust.setStatus("0");
						return RetDataTools.Ok("委托成功！",documentEntrustService.insertDocumentEntrust(documentEntrust));
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
	 * @Title: inserDocumentPluginsRegister   
	 * @Description: TODO 注册BPM插件
	 * @param: request
	 * @param: bpmPluginsRegister
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/inserDocumentPluginsRegister",method=RequestMethod.POST)
	public RetDataBean insertDocumentPluginsRegister(HttpServletRequest request,DocumentPluginsRegister documentPluginsRegister)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentPluginsRegister.setPluginsId(SysTools.getGUID());
			documentPluginsRegister.setCreateUser(account.getAccountId());
			documentPluginsRegister.setStatus("0");
			documentPluginsRegister.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			documentPluginsRegister.setOrgId(account.getOrgId());
			return RetDataTools.Ok("注册插件成功!", documentPluginsRegisterService.insertDocumentPluginsRegister(documentPluginsRegister));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: deleteDocumentPluginsRegister   
	 * @Description: TODO 删除插件
	 * @param: request
	 * @param: bpmPluginsRegister
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/deleteDocumentPluginsRegister",method=RequestMethod.POST)
	public RetDataBean deleteDocumentPluginsRegister(HttpServletRequest request,DocumentPluginsRegister documentPluginsRegister)
	{
		try
		{
			if(StringUtils.isBlank(documentPluginsRegister.getPluginsId()))
			{
				return RetDataTools.NotOk("请求参数出错,请与系统管理联系!");
			}
			Account account=accountService.getRedisAccount(request);
			documentPluginsRegister.setOrgId(account.getOrgId());
			return RetDataTools.Ok("删除插件成功!", documentPluginsRegisterService.deleteDocumentPluginsRegist(documentPluginsRegister));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: updateDcoumentPluginsRegister   
	 * @Description: TODO 更新插件记录   
	 * @param: request
	 * @param: bpmPluginsRegister
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/updateDcoumentPluginsRegister",method=RequestMethod.POST)
	public RetDataBean updateDcoumentPluginsRegister(HttpServletRequest request,DocumentPluginsRegister documentPluginsRegister)
	{
		try
		{
			if(StringUtils.isBlank(documentPluginsRegister.getPluginsId()))
			{
				return RetDataTools.NotOk("请求参数出错,请与系统管理联系!");
			}
			Account account=accountService.getRedisAccount(request);
			documentPluginsRegister.setOrgId(account.getOrgId());
			Example example = new Example(DocumentPluginsRegister.class);
			example.createCriteria().andEqualTo("orgId", account.getOrgId()).andEqualTo("pluginsId",documentPluginsRegister.getPluginsId());
			return RetDataTools.Ok("更新插件成功!", documentPluginsRegisterService.updateDocumentPluginsRegister(example, documentPluginsRegister));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: insertDocumentNumber   
	 * @Description: TODO 添加公文文号规则
	 * @param request
	 * @param documentNumber
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/insertDocumentNumber",method=RequestMethod.POST)
	public RetDataBean insertDocumentNumber (HttpServletRequest request,DocumentNumber documentNumber)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentNumber.setConfigId(SysTools.getGUID());
			documentNumber.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			documentNumber.setCreateUser(account.getAccountId());
			documentNumber.setOrgId(account.getOrgId());
			return RetDataTools.Ok("添加成功!",documentNumberService.insertDocumentNumber(documentNumber));
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: deleteDocumentNumber   
	 * @Description: TODO 删除文号规则
	 * @param request
	 * @param documentNumber
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/deleteDocumentNumber",method=RequestMethod.POST)
	public RetDataBean deleteDocumentNumber(HttpServletRequest request,DocumentNumber documentNumber)
	{
		try
		{
			if(StringUtils.isBlank(documentNumber.getConfigId()))
			{
				return RetDataTools.NotOk("请求参数有问题,请检查!");
			}
			Account account=accountService.getRedisAccount(request);
			documentNumber.setOrgId(account.getOrgId());
			return RetDataTools.Ok("删除成功!",documentNumberService.deleteDocumentNumber(documentNumber));
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: updateDocumentNumber   
	 * @Description: TODO 更新文号规则
	 * @param request
	 * @param documentNumber
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/updateDocumentNumber",method=RequestMethod.POST)
	public RetDataBean updateDocumentNumber(HttpServletRequest request,DocumentNumber documentNumber)
	{
		try
		{
			if(StringUtils.isBlank(documentNumber.getConfigId()))
			{
				return RetDataTools.NotOk("请求参数有问题,请检查!");
			}
			Account account=accountService.getRedisAccount(request);
			Example example = new Example(DocumentNumber.class);
			example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("configId",documentNumber.getConfigId());
			return RetDataTools.Ok("更新成功!",documentNumberService.updateDocumentNumber(example, documentNumber));
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: documentToFile   
	 * @Description: TODO 公文归档
	 * @param request
	 * @param sortId
	 * @param runId
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/documentToFile",method=RequestMethod.POST)
	public RetDataBean documentToFile(HttpServletRequest request,String sortId, String runId)
	{
		try
		{
				if(StringUtils.isBlank(runId)||StringUtils.isBlank(sortId))
				{
					return RetDataTools.NotOk("对不起，您请求的参数有问题！");
				}
					Account account=accountService.getRedisAccount(request);
					DocumentList documentList = new DocumentList();
					documentList.setOrgId(account.getOrgId());
					documentList.setRunId(runId);
					documentList = documentListService.selectOne(documentList);
					return RetDataTools.Ok("归档成功！",documentListFileService.documentToFile(documentList,runId,account));
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
		}
			
	}
	
	/**
	 * 
	 * @Title: updateMobileDocumentForm   
	 * @Description: TODO 更新移动端表单模版
	 * @param: request
	 * @param: bpmForm
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/updateMobileDocumentForm",method=RequestMethod.POST)
	public RetDataBean updateMobileDocumentForm(HttpServletRequest request,DocumentForm documentForm)
	{
		try
		{
				if(StringUtils.isBlank(documentForm.getFormId()))
				{
					return RetDataTools.NotOk("对不起，您请求的参数有问题！");
				}
					Account account=accountService.getRedisAccount(request);
				if(!account.getOpFlag().equals("1"))
				{
					return RetDataTools.NotOk("您不是管理员，请与管理员联系！");
				}else
				{
					Example example = new Example(DocumentForm.class);
					example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("formId",documentForm.getFormId());
					return RetDataTools.Ok("保存成功！",documentFormService.updateDocumentForm(example,documentForm));
		}
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
		}
			
	}
/**
 * 
* @Title: changeDocumentUser 
* @Description: TODO BPM流程转交
* @param @param request
* @param @param runId
* @param @param runProcessId
* @param @param accountId
* @param @return 设定文件 
* @return RetDataBean 返回类型
 */
@RequestMapping(value="/changeDocumentUser",method=RequestMethod.POST)
public RetDataBean changeDocumentUser(HttpServletRequest request,String runId,String runProcessId,String accountId)
{
	try
	{
		if(StringUtils.isBlank(runProcessId))
		{
			return RetDataTools.NotOk("参数有问题!");
		}else
		{
			Account account=accountService.getRedisAccount(request);
			UserInfo userInfo = accountService.getRedisUserInfo(request);
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("您无权限转交流程,请与系统管理员联系!");
			}
			DocumentRunProcess documentRunProcess = new DocumentRunProcess();
			documentRunProcess.setOrgId(account.getOrgId());
			documentRunProcess.setRunProcessId(runProcessId);
			documentRunProcess.setAccountId(accountId);
			documentRunProcess.setCreateUser(account.getAccountId());
			documentRunProcess.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			Example example = new Example(DocumentRunProcess.class);
			example.createCriteria().andEqualTo("orgId",documentRunProcess.getOrgId()).andEqualTo("runProcessId",documentRunProcess.getRunProcessId()).andEqualTo("runId",documentRunProcess.getRunId());
			return RetDataTools.Ok("流程转交成功!",documentRunProcessService.changeDocumentUser(account,userInfo,documentRunProcess, example));
		}
}catch (Exception e) {
		// TODO: handle exception
	return RetDataTools.Error(e.getMessage());
}
}
	
	/**
	 * 
	* @Title: delDocumentList 
	* @Description: TODO 管理员逻辑删除BPM流程
	* @param @param request
	* @param @param documentList
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value="/delDocumentList",method=RequestMethod.POST)
	public RetDataBean delDocumentList(HttpServletRequest request,DocumentList documentList)
	{
			try
			{
				Account account=accountService.getRedisAccount(request);
				documentList.setOrgId(account.getOrgId());
				if(account.getOpFlag().equals("1"))
				{
					return RetDataTools.Ok("物理删除成功！",documentListService.deleteDocumentList(documentList,account));
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
	 * @Title: doTaskBack   
	 * @Description: TODO 收回收发文步骤
	 * @param: request
	 * @param: documentRunProcess
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping("/doTaskBack")
	public RetDataBean doTaskBack(HttpServletRequest request,DocumentRunProcess documentRunProcess) {
		try {
			UserInfo userInfo = accountService.getRedisUserInfo(request);
			if(StringUtils.isBlank(documentRunProcess.getRunProcessId())) {
				return RetDataTools.NotOk("请求参数有问题,请检查!");
			}
			documentRunProcess.setOrgId(userInfo.getOrgId());
			documentRunProcessService.doTaskBack(userInfo,documentRunProcess);
			return RetDataTools.Ok("收回成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: doDocumentUrge 
	* @Description: TODO 收发文催办
	* @param @param request
	* @param @param runIds
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping("/doDocumentUrge")
	public RetDataBean doDocumentUrge(HttpServletRequest request,String runIds) {
		try {
			if(StringUtils.isBlank(runIds)) {
				return RetDataTools.NotOk("请求参数有问题,请检查!");
			}
			Account account=accountService.getRedisAccount(request);
			UserInfo userInfo = accountService.getRedisUserInfo(request);
			documentOptService.doDocumentUrge(account,userInfo, runIds);
			return RetDataTools.Ok("催办信息已发送!","");
		} catch (Exception e) {
			e.printStackTrace();
			return RetDataTools.Error(e.getMessage());
		}
	}
	

	/**
	 * 
	 * @Title: insertDocumentTemplate   
	 * @Description: TODO 添加BPM红头模版
	 * @param: request
	 * @param: documentTemplate
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/insertDocumentTemplate",method=RequestMethod.POST)
	public RetDataBean insertDocumentTemplate(HttpServletRequest request,DocumentTemplate documentTemplate)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentTemplate.setTemplateId(SysTools.getGUID());
			documentTemplate.setCreateUser(account.getAccountId());
			documentTemplate.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			documentTemplate.setOrgId(account.getOrgId());
			return RetDataTools.Ok("模版添加成功!", documentTemplateService.insertDocumentTemplate(documentTemplate));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: deleteDocumentTemplate   
	 * @Description: TODO 删除BPM流程红头模版
	 * @param: request
	 * @param: documentTemplate
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/deleteDocumentTemplate",method=RequestMethod.POST)
	public RetDataBean deleteDocumentTemplate(HttpServletRequest request,DocumentTemplate documentTemplate)
	{
		try
		{
			if(StringUtils.isBlank(documentTemplate.getTemplateId()))
			{
				return RetDataTools.NotOk("请求的参数有问题！请与管理员联系");
			}
			Account account=accountService.getRedisAccount(request);
			documentTemplate.setOrgId(account.getOrgId());
			return RetDataTools.Ok("模版删除成功!", documentTemplateService.deleteDocumentTemplate(documentTemplate));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: updateDocumentTemplate   
	 * @Description: TODO 更新模版
	 * @param: request
	 * @param: documentTemplate
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/updateDocumentTemplate",method=RequestMethod.POST)
	public RetDataBean updateDocumentTemplate(HttpServletRequest request,DocumentTemplate documentTemplate)
	{
		try
		{
			if(StringUtils.isBlank(documentTemplate.getTemplateId()))
			{
				return RetDataTools.NotOk("请求的参数有问题！请与管理员联系");
			}
			Account account=accountService.getRedisAccount(request);
			Example example = new Example(DocumentTemplate.class);
			example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("templateId",documentTemplate.getTemplateId());
			documentTemplate.setOrgId(account.getOrgId());
			return RetDataTools.Ok("模版更新成功!", documentTemplateService.updateDocumentTemplate(example, documentTemplate));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: addProcessAfter   
	 * @Description: TODO 后加签
	 * @param request
	 * @param addAccountId
	 * @param documentRunProcess
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/addProcessAfter",method=RequestMethod.POST)
	public RetDataBean addProcessAfter(HttpServletRequest request,String addAccountId,DocumentRunProcess documentRunProcess)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(StringUtils.isBlank(addAccountId))
			{
				return RetDataTools.NotOk("加签人员不能为空!");
			}
			documentRunProcess.setOrgId(account.getOrgId());
			documentRunProcess = documentRunProcessService.selectOne(documentRunProcess);
			DocumentRunProcess newDocumentRunProcess = new DocumentRunProcess();
			newDocumentRunProcess.setRunProcessId(SysTools.getGUID());
			newDocumentRunProcess.setRunId(documentRunProcess.getRunId());
			newDocumentRunProcess.setProcessId(documentRunProcess.getProcessId());
			newDocumentRunProcess.setOpFlag("1");
			newDocumentRunProcess.setAccountId(addAccountId);
			newDocumentRunProcess.setCreateUser(documentRunProcess.getAccountId());
			newDocumentRunProcess.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			newDocumentRunProcess.setSendId(documentRunProcess.getRunProcessId());
			newDocumentRunProcess.setStatus("0");
			newDocumentRunProcess.setOrgId(account.getOrgId());
			return RetDataTools.Ok("后加签成功!", documentRunProcessService.addProcessAfter(documentRunProcess, newDocumentRunProcess));
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
	public RetDataBean addProcessBefore(HttpServletRequest request,String addAccountId,DocumentRunProcess documentRunProcess)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(StringUtils.isBlank(addAccountId))
			{
				return RetDataTools.NotOk("加签人员不能为空!");
			}
			documentRunProcess.setOrgId(account.getOrgId());
			documentRunProcess = documentRunProcessService.selectOne(documentRunProcess);
			DocumentRunProcess newDocumentRunProcess = new DocumentRunProcess();
			newDocumentRunProcess.setRunProcessId(SysTools.getGUID());
			newDocumentRunProcess.setRunId(documentRunProcess.getRunId());
			newDocumentRunProcess.setProcessId(documentRunProcess.getProcessId());
			newDocumentRunProcess.setOpFlag("1");
			newDocumentRunProcess.setAccountId(addAccountId);
			newDocumentRunProcess.setCreateUser(documentRunProcess.getAccountId());
			newDocumentRunProcess.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			newDocumentRunProcess.setSendId(documentRunProcess.getRunProcessId());
			newDocumentRunProcess.setStatus("0");
			newDocumentRunProcess.setOrgId(account.getOrgId());
			return RetDataTools.Ok("前加签成功!", documentRunProcessService.addProcessBefore(documentRunProcess, newDocumentRunProcess));
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: setGobackOpt   
	 * @Description: TODO 收发文回退操作
	 * @param request
	 * @param documentRunProcess
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/setGobackOpt",method=RequestMethod.POST)
	public RetDataBean setGobackOpt(HttpServletRequest request,DocumentRunProcess documentRunProcess)
	{
		try
		{
			if(StringUtils.isBlank(documentRunProcess.getRunProcessId()))
			{
				return RetDataTools.NotOk("请求参数有问题!");
			}else
			{
				Account account=accountService.getRedisAccount(request);
				documentRunProcess.setOrgId(account.getOrgId());
				documentRunProcess.setAccountId(account.getAccountId());
				documentRunProcess = documentRunProcessService.selectOne(documentRunProcess);
				return RetDataTools.Ok("回退成功!",documentRunProcessService.setGobackOpt(documentRunProcess));
			}
	}catch (Exception e) {
			// TODO: handle exception
		return RetDataTools.Error(e.getMessage());
	}
	}
	/**
	 * 
	 * @Title: updateDocumentList   
	 * @Description: TODO 更新收发文
	 * @param: request
	 * @param: documentList
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/updateDocumentList",method=RequestMethod.POST)
	public RetDataBean updateDocumentList(HttpServletRequest request,DocumentList documentList)
	{
		try
		{
			if(StringUtils.isBlank(documentList.getRunId()))
			{
				return RetDataTools.NotOk("参数有问题!");
			}
			Account account=accountService.getRedisAccount(request);
			documentList.setOrgId(account.getOrgId());
			Example example = new Example(DocumentList.class);
			example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("runId",documentList.getRunId());
			return RetDataTools.Ok("操作成功!",documentListService.updateDocumentList(documentList, example));	
		}catch (Exception e) {
		return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: setFollowOpt 
	* @Description: TODO 关注设置
	* @param @param request
	* @param @param documentList
	* @param @param isFollow
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value="/setFollowOpt",method=RequestMethod.POST)
	public RetDataBean setFollowOpt(HttpServletRequest request,DocumentList documentList,boolean isFollow)
	{
		try
		{
			if(StringUtils.isBlank(documentList.getRunId()))
			{
				return RetDataTools.NotOk("参数有问题!");
			}
			Account account=accountService.getRedisAccount(request);
			documentList.setOrgId(account.getOrgId());
			documentList = documentListService.selectOne(documentList);
			return RetDataTools.Ok("关注更新!",documentListService.setFollow(documentList, isFollow,account));	
		}catch (Exception e) {
		return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: doCompleteDocument 
	* @Description: TODO 办理完成
	* @param @param request
	* @param @param runId
	* @param @param runProcessId
	* @param @return 设定文件 
	* @return RetDataBean 返回类型
	 */
	@RequestMapping(value="/doCompleteDocument",method=RequestMethod.POST)
	public RetDataBean doCompleteDocument(HttpServletRequest request,String runId,String runProcessId)
	{
			try
			{
				if(StringUtils.isNotBlank(runId)&&StringUtils.isNotBlank(runProcessId))
				{
					String createTime = SysTools.getTime("yyyy-MM-dd HH:mm:ss");
					Account account=accountService.getRedisAccount(request);
					DocumentList documentList = new DocumentList();
					documentList.setRunId(runId);
					documentList.setOrgId(account.getOrgId());
					documentList=documentListService.selectOne(documentList);
					DocumentRunProcess documentRunProcess = new DocumentRunProcess();
					documentRunProcess.setOrgId(account.getOrgId());
					documentRunProcess.setRunProcessId(runProcessId);
					documentRunProcess = documentRunProcessService.selectOne(documentRunProcess);
					documentRunProcess.setEndTime(createTime);
					DocumentProcess documentProcess = new DocumentProcess();
					documentProcess.setOrgId(account.getOrgId());
					documentProcess.setProcessId(documentRunProcess.getProcessId());
					documentProcess = documentProcessService.selectOne(documentProcess);
					if(documentProcess.getOpRule().equals("3"))
					{
					documentOptService.setOpFlagByOpRule(documentProcess, documentRunProcess);
					}
					return documentRunProcessService.doCompleteDocument(request,documentProcess,documentList, documentRunProcess);
					
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
	 * @Title: goSaveFormData   
	 * @Description: TODO 保存收发文表单数据
	 * @param request
	 * @param documentList
	 * @param documentRunProcess
	 * @param formData
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@Transactional(value="generalTM")
	@RequestMapping(value="/saveFormData",method=RequestMethod.POST)
	public RetDataBean goSaveFormData(HttpServletRequest request,DocumentList documentList,DocumentRunProcess documentRunProcess,String formData)
	{
			try
			{
				JSONObject formDataJson = JSONObject.parseObject(formData);
				Map<String,String> formDataMap =(Map)formDataJson;
				Account account=accountService.getRedisAccount(request);
				documentRunProcess.setOrgId(account.getOrgId());
				documentList.setOrgId(account.getOrgId());
				
				DocumentRunProcess newDocumentRunProcess = new DocumentRunProcess();
				newDocumentRunProcess.setOrgId(account.getOrgId());
				newDocumentRunProcess.setRunId(documentRunProcess.getRunId());
				newDocumentRunProcess.setRunProcessId(documentRunProcess.getRunProcessId());
				newDocumentRunProcess = documentRunProcessService.selectOne(newDocumentRunProcess);
				documentRunProcess.setProcessId(newDocumentRunProcess.getProcessId());
				documentRunProcess.setAttach(request.getParameter("runAttach"));
				
				DocumentProcess documentProcess = new DocumentProcess();
				documentProcess.setProcessId(newDocumentRunProcess.getProcessId());
				documentProcess.setOrgId(newDocumentRunProcess.getOrgId());
				documentProcess = documentProcessService.selectOne(documentProcess);
				
				DocumentFlow documentFlow = new DocumentFlow();
				documentFlow.setOrgId(documentProcess.getOrgId());
				documentFlow.setFlowId(documentProcess.getFlowId());
				documentFlow=documentFlowService.selectOneDocumentFlow(documentFlow);
				
				
				DocumentForm documentForm = new DocumentForm();
				documentForm.setFormId(documentFlow.getFormId());
				documentForm.setOrgId(account.getOrgId());
				documentForm = documentFormService.selectOneDocumentForm(documentForm);
				
				return RetDataTools.Ok("工作保存成功!",documentOptService.saveDocumentFormData(account, formDataMap, documentList, documentForm, documentRunProcess));
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
	 * @param request
	 * @param nextPrcsInfo
	 * @param runId
	 * @param runProcessId
	 * @param remindNextUser
	 * @param remindCreateUser
	 * @param remindParticipant
	 * @param msgContent
	 * @param autoSendUser
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/goNextProcess",method=RequestMethod.POST)
	public RetDataBean goNextProcess(HttpServletRequest request,String nextPrcsInfo,String runId,
			String runProcessId,String remindNextUser,String remindCreateUser,String remindParticipant,String msgContent,String autoSendUser)
	{
		Account account=accountService.getRedisAccount(request);
		UserInfo userInfo = accountService.getRedisUserInfo(request);
		return documentRunProcessService.goNextProcess(userInfo,account, nextPrcsInfo, runId, runProcessId, remindNextUser,remindCreateUser,remindParticipant, msgContent,autoSendUser);
	}
	
	/**
	 * 
	 * @Title: startDocument   
	 * @Description: TODO 创建收发文流程
	 * @param request
	 * @param documentList
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/startDocument",method=RequestMethod.POST)
	public RetDataBean startDocument(HttpServletRequest request,DocumentList documentList)
	{
			try
			{
				Account account=accountService.getRedisAccount(request);
				documentList.setRunId(SysTools.getGUID());
				documentList.setDelFlag("0");
				documentList.setStatus("0");
				documentList.setEndTime("");
				documentList.setOpUserStr(account.getAccountId());
				documentList.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
				documentList.setCreateUser(account.getAccountId());
				documentList.setOrgId(account.getOrgId());
				DocumentFlow documentFlow = new DocumentFlow();
				documentFlow.setOrgId(account.getOrgId());
				documentFlow.setFlowId(documentList.getFlowId());
				documentFlow = documentFlowService.selectOneDocumentFlow(documentFlow);
				return documentOptService.startDocument(documentList,documentFlow);
			}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
			}
	}
	
	
	/**
	 * 
	 * @Title: setDocumentChildProcess   
	 * @Description: TODO   
	 * @param: request
	 * @param: documentChildProcess
	 * @param: @return      
	 * @return: RetDataBean      
	 * @throws
	 */
	@RequestMapping(value="/setDocumentChildProcess",method=RequestMethod.POST)
	public RetDataBean setDocumentChildProcess(HttpServletRequest request,DocumentChildProcess documentChildProcess,DocumentProcess documentProcess)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(StringUtils.isBlank(documentChildProcess.getProcessId()))
			{
				return RetDataTools.NotOk("请求的参数有问题,请与管理员联系");
			}
			return RetDataTools.Ok("设置成功!", documentChildProcessService.setDocumentChildProcess(account, documentChildProcess,documentProcess));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}	
	
	/**
	 * 
	 * @Title saveDocumentProcessLayout   
	 * @Description TODO 批量更新布局  
	 * @param request
	 * @param documentProcesss
	 * @return      
	 * RetDataBean
	 */
	@RequestMapping(value="/saveDocumentProcessLayout",method=RequestMethod.POST)
	public RetDataBean saveDocumentProcessLayout(HttpServletRequest request,@RequestBody DocumentProcess[] documentProcessList)
	{
	try
		{
			Account account=accountService.getRedisAccount(request);
			return RetDataTools.Ok("更新布局成功！", documentProcessService.saveDocumentProcessLayout(documentProcessList, account));
		}catch (Exception e) {
				return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title toEndProcess   
	 * @Description TODO 指向结束节点
	 * @param request
	 * @param documentProcess
	 * @return      
	 * RetDataBean
	 */
	@RequestMapping(value="/toEndProcess",method=RequestMethod.POST)
	public RetDataBean toEndProcess(HttpServletRequest request,DocumentProcess documentProcess)
	{
		Account account=accountService.getRedisAccount(request);
		return documentProcessService.toEndProcess(account, documentProcess);
	}	
	
	/**
	 * 
	 * @Title deleteDocumentProcess   
	 * @Description TODO 删除步骤ID  
	 * @param request
	 * @param documentProcess
	 * @return      
	 * RetDataBean
	 */
	@RequestMapping(value="/deleteDocumentProcess",method=RequestMethod.POST)
	public RetDataBean deleteDocumentProcess(HttpServletRequest request,DocumentProcess documentProcess)
	{
			try
			{
				if(StringUtils.isBlank(documentProcess.getProcessId()))
				{
					return RetDataTools.NotOk("请求参数有问题,请检查!");
				}
				Account account=accountService.getRedisAccount(request);
				documentProcess.setOrgId(account.getOrgId());
				if(StringUtils.isNotBlank(documentProcess.getProcessId()))
				{
					return RetDataTools.Ok("删除成功！",documentProcessService.deleteDocumentProcess(documentProcess));
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
	 * @Title updateDocumentProcess   
	 * @Description TODO 更新收发文步骤设置  
	 * @param request
	 * @param documentProcess
	 * @return      
	 * RetDataBean
	 */
	@RequestMapping(value="/updateDocumentProcess",method=RequestMethod.POST)
	public RetDataBean updateDocumentProcess(HttpServletRequest request,DocumentProcess documentProcess)
	{
		Account account=accountService.getRedisAccount(request);
		return  documentProcessService.doUpdateDocumentProcess(account,documentProcess);
	}
	/**
	 * 
	 * @Title: getDocumentChildProcessPrcs   
	 * @Description: TODO 获取子流程配置信息
	 * @param request
	 * @param documentChildProcess
	 * @return
	 * RetDataBean    
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
	 * @Title: insertDocumentProcess   
	 * @Description: TODO 新建步骤
	 * @param request
	 * @param documentProcess
	 * @param parentId
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/insertDocumentProcess",method=RequestMethod.POST)
	public RetDataBean insertDocumentProcess(HttpServletRequest request,DocumentProcess documentProcess,String parentId)
	{
		Account account=accountService.getRedisAccount(request);
		return documentProcessService.createProcessNormal(account,documentProcess,parentId);
	}

	/**
	 * 
	* @Title: insertDocumentFlow
	* @Description: TODO 创建收发文流程
	* @param @param request
	* @param @param documentFlow
	* @param @return 设定文件 
	* @return RetDataBean 返回类型 

	 */
	@RequestMapping(value="/insertDocumentFlow",method=RequestMethod.POST)
	public RetDataBean insertDocumentFlow(HttpServletRequest request,DocumentFlow documentFlow)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentFlow.setFlowId(SysTools.getGUID());
			documentFlow.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			documentFlow.setCreateUser(account.getAccountId());
			documentFlow.setOrgId(account.getOrgId());
			return RetDataTools.Ok("创建成功!", documentFlowService.insertDocumentFlow(documentFlow));
		}catch (Exception e) {
			
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: deleteDocumentFlow
	* @Description: TODO 删除公文流程
	* @param @param request
	* @param @param documentForm
	* @param @return 设定文件 
	* @return RetDataBean 返回类型 

	 */
	@RequestMapping(value="/deleteDocumentFlow",method=RequestMethod.POST)
	public RetDataBean deleteDocumentFlow(HttpServletRequest request,DocumentFlow documentFlow)
	{
		try
		{
			if(StringUtils.isBlank(documentFlow.getFlowId()))
			{
				return RetDataTools.NotOk("请求参数有问题,请检查!");
			}else
			{
			Account account=accountService.getRedisAccount(request);
			documentFlow.setOrgId(account.getOrgId());
			return RetDataTools.Ok("删除收发文成功!",documentFlowService.deleteDocumentFlow(documentFlow));
			}
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: updateDocumentFlow   
	 * @Description: TODO 更新收发文流程
	 * @param request
	 * @param documentFlow
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/updateDocumentFlow",method=RequestMethod.POST)
	public RetDataBean updateDocumentFlow(HttpServletRequest request,DocumentFlow documentFlow)
	{
		try
		{
			if(StringUtils.isBlank(documentFlow.getFlowId()))
			{
				return RetDataTools.NotOk("请求参数有问题,请检查!");
			}else
			{
			Account account=accountService.getRedisAccount(request);
			Example example = new Example(DocumentFlow.class);
			example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("flowId",documentFlow.getFlowId());
			return RetDataTools.Ok("更新公文流程成功!", documentFlowService.updateDocumentFlow(example,documentFlow));
			}
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: updateDocumentFormHtmlCode   
	 * @Description: TODO 更新表单代码，同时更新并生成表单数据库表结构   
	 * @param request
	 * @param documentForm
	 * @return
	 * RetDataBean    
	 * @throws
	 */
	@RequestMapping(value="/updateDocumentFormHtmlCode",method=RequestMethod.POST)
	public RetDataBean updateDocumentFormHtmlCode(HttpServletRequest request,DocumentForm documentForm)
	{
		Account account=accountService.getRedisAccount(request);
		return documentFormService.updateDocumentFormHtmlCode(account,documentForm);
}
	/**
	 * 
	* @Title: insertDocumentForm
	* @Description: TODO 创建公文分表单
	* @param @param request
	* @param @param documentForm
	* @param @return 设定文件 
	* @return RetDataBean 返回类型 

	 */
	@RequestMapping(value="/insertDocumentForm",method=RequestMethod.POST)
	public RetDataBean insertDocumentForm(HttpServletRequest request,DocumentForm documentForm)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentForm.setFormId(SysTools.getGUID());
			documentForm.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			documentForm.setCreateUser(account.getAccountId());
			documentForm.setOrgId(account.getOrgId());
			return RetDataTools.Ok("创建成功!", documentFormService.insertDocumentForm(documentForm));
		}catch (Exception e) {
			
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: deleteDocumentForm
	* @Description: TODO 删除公文表单
	* @param @param request
	* @param @param documentForm
	* @param @return 设定文件 
	* @return RetDataBean 返回类型 

	 */
	@RequestMapping(value="/deleteDocumentForm",method=RequestMethod.POST)
	public RetDataBean deleteDocumentForm(HttpServletRequest request,DocumentForm documentForm)
	{
		try
		{
			if(StringUtils.isBlank(documentForm.getFormId()))
			{
				return RetDataTools.NotOk("请求参数有问题,请检查!");
			}else
			{
				Account account=accountService.getRedisAccount(request);
				DocumentFlow documentFlow = new DocumentFlow();
				documentFlow.setFormId(documentForm.getFormId());
				documentFlow.setOrgId(account.getOrgId());
				if(documentFlowService.getDocumentFlowCount(documentFlow)==0)
				{
					documentForm.setOrgId(account.getOrgId());
				}else
				{
					return RetDataTools.NotOk("对不起,目前有流程正在使用该表单,删除失败!");
				}
		
			return RetDataTools.Ok("删除公文表单成功!",documentFormService.deleteDocumentForm(documentForm));
			}
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	* @Title: updateDocumentForm 
	* @Description: TODO 更新公文表单
	* @param @param request
	* @param @param documentForm
	* @param @return 设定文件 
	* @return RetDataBean 返回类型 

	 */
	@RequestMapping(value="/updateDocumentForm",method=RequestMethod.POST)
	public RetDataBean updateDocumentForm(HttpServletRequest request,DocumentForm documentForm)
	{
		try
		{
			if(StringUtils.isBlank(documentForm.getFormId()))
			{
				return RetDataTools.NotOk("请求参数有问题,请检查!");
			}else
			{
			Account account=accountService.getRedisAccount(request);
			Example example = new Example(DocumentForm.class);
			example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("formId",documentForm.getFormId());
			return RetDataTools.Ok("更新公文表单成功!", documentFormService.updateDocumentForm(example,documentForm));
			}
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	* @Title: insertDocumentSort
	* @Description: TODO 创建公文分类
	* @param @param request
	* @param @param documentSort
	* @param @return 设定文件 
	* @return RetDataBean 返回类型 

	 */
	@RequestMapping(value="/insertDocumentSort",method=RequestMethod.POST)
	public RetDataBean insertDocumentSort(HttpServletRequest request,DocumentSort documentSort)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentSort.setSortId(SysTools.getGUID());
			documentSort.setCreateTime("yyyy-MM-dd HH:mm:ss");
			documentSort.setCreateUser(account.getAccountId());
			documentSort.setOrgId(account.getOrgId());
			return RetDataTools.Ok("创建成功!", documentSortService.insertDocumentSort(documentSort));
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: deleteDocumentSort
	* @Description: TODO 删除公文分类
	* @param @param request
	* @param @param documentSort
	* @param @return 设定文件 
	* @return RetDataBean 返回类型 

	 */
	@RequestMapping(value="/deleteDocumentSort",method=RequestMethod.POST)
	public RetDataBean deleteDocumentSort(HttpServletRequest request,DocumentSort documentSort)
	{
		try
		{
			if(StringUtils.isBlank(documentSort.getSortId()))
			{
				return RetDataTools.NotOk("请求参数有问题,请检查!");
			}else
			{
			Account account=accountService.getRedisAccount(request);
			documentSort.setOrgId(account.getOrgId());
			return RetDataTools.Ok("删除公文分类成功!",documentSortService.deleteDocumentSort(documentSort));
			}
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	* @Title: updateDocumentSort 
	* @Description: TODO 更新公文分类
	* @param @param request
	* @param @param documentSort
	* @param @return 设定文件 
	* @return RetDataBean 返回类型 

	 */
	@RequestMapping(value="/updateDocumentSort",method=RequestMethod.POST)
	public RetDataBean updateDocumentSort(HttpServletRequest request,DocumentSort documentSort)
	{
		try
		{
			if(StringUtils.isBlank(documentSort.getSortId()))
			{
				return RetDataTools.NotOk("请求参数有问题,请检查!");
			}else
			{
			Account account=accountService.getRedisAccount(request);
			Example example = new Example(DocumentSort.class);
			example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("sortId",documentSort.getSortId());
			return RetDataTools.Ok("更新公文分类成功!", documentSortService.updateDocumentSort(example,documentSort));
			}
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
			Account account=accountService.getRedisAccount(request);
			UserInfo userInfo = accountService.getRedisUserInfo(request);
			documentOptService.doDocumentUrge(account,userInfo, runIds);
			return RetDataTools.Ok("催办信息已发送!","");
		} catch (Exception e) {
			e.printStackTrace();
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
	@RequestMapping(value="/setDocumentFormVersion",method=RequestMethod.POST)
	public RetDataBean setDocumentFormVersion(HttpServletRequest request,DocumentForm documentForm,String title,String remark)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			documentForm.setOrgId(account.getOrgId());
			documentForm = documentFormService.selectOneDocumentForm(documentForm);
			return RetDataTools.Ok("生成版本成功!", documentFormVersionService.setDocumentFormVersion(account,documentForm,title,remark));
		}catch (Exception e) {
			// TODO: handle exception
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
	@RequestMapping(value="/clearDocumentFlowData",method=RequestMethod.POST)
	public RetDataBean clearDocumentFlowData(HttpServletRequest request,DocumentFlow documentFlow)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("您不是管理员,不能进行BPM初始化操作!");
			}
			if(StringUtils.isBlank(documentFlow.getFlowId()))
			{
				return RetDataTools.NotOk("请求有参数有问题,请与管理员联系!");
			}
			documentFlow.setOrgId(account.getOrgId());
			documentFlow = documentFlowService.selectOneDocumentFlow(documentFlow);
			return documentFlowService.clearDocumentFlowData(documentFlow,account);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
}
