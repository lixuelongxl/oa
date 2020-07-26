package com.core136.controller.bpm;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.core136.common.utils.SysTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.core136.bean.account.Account;
import com.core136.bean.bpm.BpmFlow;
import com.core136.bean.bpm.BpmForm;
import com.core136.bean.bpm.BpmFormVersion;
import com.core136.bean.bpm.BpmList;
import com.core136.bean.bpm.BpmProcess;
import com.core136.service.account.AccountService;
import com.core136.service.bpm.BpmFlowService;
import com.core136.service.bpm.BpmFormCacheService;
import com.core136.service.bpm.BpmFormService;
import com.core136.service.bpm.BpmFormVersionService;
import com.core136.service.bpm.BpmListService;
import com.core136.service.bpm.BpmOptService;
import com.core136.service.bpm.BpmProcessService;
import com.core136.unit.bpm.BpmFormDataBean;

@Controller
@RequestMapping("/app/core")
public class BpmPageController {
	@Autowired
	private BpmFlowService bpmFlowService;
	@Autowired
	private BpmOptService bpmOptService;
	@Autowired
	private BpmListService bpmListService;
	@Autowired
	private BpmFormService bpmFormService;
	@Autowired
	private BpmProcessService bpmProcessService;
	@Autowired
	private BpmFormCacheService bpmFormCacheService;
	@Autowired
	private BpmFormVersionService bpmFormVersionService;
	@Autowired
	private AccountService accountService;
	@Value("${app.bpmtable}")
	private String bpmtable;
	
	/**
	 * 
	 * @Title: goBpmBiDetails   
	 * @Description: TODO BPM数据报表详情
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/bpm/bpmbidetails")
	public ModelAndView  goBpmBiDetails()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/bpmset/bpmbidetails");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}
	/**
	 * 
	 * @Title: goBpmReport   
	 * @Description: TODO BPM数据报表
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/bpm/bpmreport")
	public ModelAndView  goBpmReport(String view)
	{
		ModelAndView mv = null;
		try
		{
			if(StringUtils.isBlank(view))
			{
				mv = new ModelAndView("app/core/bpm/search/bpmreport");
			}else
			{
				if(view.equals("data"))
				{
					mv = new ModelAndView("app/core/bpm/search/bpmdatareport");
				}
			}
		return mv;
		}catch (Exception e) {
		mv = new ModelAndView("titps");
		return mv;
	}
	}
	
	
	/**
	 * 
	 * @Title: goBpmBiSet   
	 * @Description: TODO 工作流报表模版
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/bpm/bpmbiset")
	public ModelAndView  goBpmBiSet(String view)
	{
		ModelAndView mv = null;
		try
		{
			if(StringUtils.isBlank(view))
			{
				mv = new ModelAndView("app/core/bpm/bpmset/bpmbiset");
			}else
			{
				if(view.equals("create"))
				{
					mv = new ModelAndView("app/core/bpm/bpmset/bpmbisetcreate");
				}else if(view.equals("edit"))
				{
					mv = new ModelAndView("app/core/bpm/bpmset/bpmbisetedit");
				}
			}
		return mv;
		}catch (Exception e) {
		mv = new ModelAndView("titps");
		return mv;
	}
	}
	/**
	 * 
	 * @Title: goBusinessdetails   
	 * @Description: TODO 业务引擎详情
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/bpm/businessdetails")
	public ModelAndView  goBusinessdetails()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/bpmset/businessdetails");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}
	/**
	 * 
	 * @Title: goBpmFromversionDesigner   
	 * @Description: TODO 表单历史版本预览
	 * @param request
	 * @param versionId
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/bpm/bpmfromversiondesigner")
	public ModelAndView  goBpmFromversionDesigner(HttpServletRequest request,String versionId)
	{
		try
		{
			BpmFormVersion bpmFormVersion = new BpmFormVersion();
			Account account=accountService.getRedisAccount(request);
			bpmFormVersion.setVersionId(versionId);
			bpmFormVersion.setOrgId(account.getOrgId());
			bpmFormVersion = bpmFormVersionService.selectOneBpmFormVserion(bpmFormVersion);
		ModelAndView mv = new ModelAndView("/app/core/bpm/form/bpmfromversiondesigner");
		mv.addObject("htmlCode",bpmFormVersion.getHtmlCode());
		mv.addObject("style",bpmFormVersion.getStyle());
		mv.addObject("script",bpmFormVersion.getScript());
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}
	
	
	/**
	 * 
	 * @Title: gonBusinessRule   
	 * @Description: TODO BPM业务规则
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/bpm/businessrule")
	public ModelAndView  gonBusinessRule()
	{
		try
		{
		ModelAndView mv = new ModelAndView("/app/core/bpm/bpmset/businessrule");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}
	


	/**
	 * 
	 * @Title: gonNextPrcs   
	 * @Description: TODO 移动端办理下一步操作界面
	 * @param: @return      
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/bpm/nextprcs")
	public ModelAndView  gonNextPrcs()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/work/mobile/nextprcs");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}	
	
	/**
	 * 
	 * @Title: goBpmCache   
	 * @Description: TODO BPM流程缓存
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/bpm/bpmcache")
	public ModelAndView  goBpmCache()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/bpmset/bpmcache");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}	
	/**
	 * 
	 * @Title: goBpmTemplate   
	 * @Description: TODO 红头模版设置
	 * @param: @return      
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/bpm/bpmtemplate")
	public ModelAndView  goBpmTemplate()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/bpmset/bpmtemplate");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}
	
	/**
	 * 
	 * @Title: goBpmPlugins
	 * @Description: TODO BPM插件设置
	 * @param: @return      
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/bpm/bpmplugins")
	public ModelAndView  goBpmPlugins()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/bpmset/setplugins");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}
	
	/**
	 * 
	 * @Title: goBpmRunLog   
	 * @Description: TODO BPM流程操作日志
	 * @param: @return      
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/bpm/bpmrunlog")
	public ModelAndView  goBpmRunLog()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/maintain/bpmrunlog");
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}
	
	
	@RequestMapping("/bpm/bpmsealsign")
	public ModelAndView  goBpmSealSign()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/bpmset/bpmsealsign");
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}
	
	/**
	 * 
	 * @Title: goBpmMobileDesigner   
	 * @Description: TODO 移动端表单设计
	 * @param: @return      
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/bpm/bpmfrommobiledesigner")
	public ModelAndView  goBpmMobileDesigner()
	{
		try
		{
		ModelAndView mv = new ModelAndView("/app/core/bpm/form/form_mobile_designer");
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}	
	
	/**
	 * 
	 * @Title: gocreateform
	 * @author:刘绍全
	 * @Description: 工作流表单创建   
	 * @param: @return      
	 * @return: String      

	 */
	@RequestMapping("/bpm/createform")
	public ModelAndView  gocreateform(String view)
	{
		ModelAndView mv = null;
		try
		{
			if(StringUtils.isBlank(view))
			{
					mv = new ModelAndView("app/core/bpm/form/form_create");
					mv.addObject("bpmtable",bpmtable);
			}else {
				if(view.equals("tabledata"))
				{
					mv = new ModelAndView("app/core/bpm/form/bpmtabledata");
				}else if(view.equals("version"))
				{
					mv = new ModelAndView("app/core/bpm/form/formversion");	
				}else if(view.equals("formfields"))
				{
					mv = new ModelAndView("app/core/bpm/form/formfields");	
				}
			}
			return mv;
		}catch (Exception e) {
		// TODO: handle exception
		mv = new ModelAndView("titps");
		return mv;
	}
	}

@Value("${app.bpmfield}")
private String bpmfield;
	/**
	 * 
	 * @Title: godesignerform
	 * @author:刘绍全
	 * @Description: 工作流表单设计  关联菜单
	 * @param: @return      
	 * @return: String      

	 */
	@RequestMapping("/bpm/designerform")
	public ModelAndView  godesignerform(HttpServletRequest request)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/form/form_designer");
		mv.addObject("bpmfield",bpmfield);
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
	
	/**
	 * 
	* @Title: goPrescription 
	* @Description: TODO BPM时效分析
	* @param @param request
	* @param @param formId
	* @param @return 设定文件 
	* @return ModelAndView 返回类型
	 */
	@RequestMapping("/bpm/prescription")
	public ModelAndView  goPrescription(HttpServletRequest request)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/maintain/prescription");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
	
	/**
	 * 
	 * @Title: createfromsort
	 * @author:刘绍全
	 * @Description: 跳转到表单分类设置页面   
	 * @param: @return      
	 * @return: String      

	 */
	@RequestMapping("/bpm/addformsort")
	public String createformsort() {
		return "app/core/bpm/form/formsort_add";
	}
	/**
	 * 
	 * @Title goprocess   
	 * @Description TODO BPM流程设置
	 * @param request
	 * @return      
	 * ModelAndView
	 */
	@RequestMapping("/bpm/process")
	public ModelAndView  goprocess(HttpServletRequest request)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/process/index");
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
	
	/**
	 * 
	 * @Title goProcessDesigner   
	 * @Description TODO 流程设计
	 * @param request
	 * @param flowId
	 * @return      
	 * ModelAndView
	 */
	@RequestMapping("/bpm/processdesigner")
	public ModelAndView  goProcessDesigner(HttpServletRequest request,String flowId)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/process/process_designer");
		Account account=accountService.getRedisAccount(request);
		BpmFlow bpmFlow = new BpmFlow();
		bpmFlow.setFlowId(flowId);
		bpmFlow.setOrgId(account.getOrgId());
		bpmFlow = bpmFlowService.selectOne(bpmFlow);
		mv.addObject("flowId",flowId);
		mv.addObject("flowName",bpmFlow.getFlowName());
		return mv;
		}catch (Exception e) {
			e.printStackTrace();
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
	
	/**
	 * 
	 * @Title gocreatebpm   
	 * @Description TODO 创建BPM
	 * @param request
	 * @return      
	 * ModelAndView
	 */
	@RequestMapping("/bpm/createbpm")
	public ModelAndView  gocreatebpm(HttpServletRequest request)
	{
		try
		{
		Account account=accountService.getRedisAccount(request);
		ModelAndView mv = new ModelAndView("app/core/bpm/work/createbpm");
		mv.addObject("accountId",account.getAccountId());
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
	
	/**
	 * 
	* @Title: bpmread 
	* @Description: TODO BPM查看详情页面
	* @param @param request
	* @param @param runId
	* @param @param flowId
	* @param @return 设定文件 
	* @return ModelAndView 返回类型
	 */
	@RequestMapping("/bpm/bpmread")
	public ModelAndView  bpmread(HttpServletRequest request,String runId,String flowId)
	{
		ModelAndView mv=null;
		try
		{
		Account account=accountService.getRedisAccount(request);
		if(SysTools.isMobileDevice(request))
		{
			mv = new ModelAndView("app/core/bpm/search/mread");
		}else
		{
			mv = new ModelAndView("app/core/bpm/search/read");
		}
		if(StringUtils.isBlank(flowId))
		{
			BpmList bpmList = new BpmList();
			bpmList.setOrgId(account.getOrgId());
			bpmList.setRunId(runId);
			bpmList=bpmListService.selectOne(bpmList);
			mv.addObject("flowId",bpmList.getFlowId());
		}else
		{
			mv.addObject("flowId",flowId);
		}
		mv.addObject("runId",runId);
		return mv;
		}catch (Exception e) {
		mv = new ModelAndView("titps");
		return mv;
		}
	}
	
	/**
	 * 
	* @Title: bpmprint 
	* @Description: TODO BPM打印页面
	* @param @param request
	* @param @param runId
	* @param @param flowId
	* @param @return 设定文件 
	* @return ModelAndView 返回类型
	 */
	@RequestMapping("/bpm/bpmprint")
	public ModelAndView  bpmprint(HttpServletRequest request,String runId,String flowId)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/search/print");
		mv.addObject("runId",runId);
		mv.addObject("flowId",flowId);
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
/**
 * 	
 * @Title godowork   
 * @Description TODO BPM处理  
 * @param request
 * @return      
 * ModelAndView
 */
	@RequestMapping("/bpm/dowork")
	public ModelAndView  godowork(HttpServletRequest request,String runId,String runProcessId)
	{
		ModelAndView mv=null;
		try
		{
		if(SysTools.isMobileDevice(request))
			{
				Account account=accountService.getRedisAccount(request);
				BpmFormDataBean bpmFormDataBean = bpmOptService.doMWork(request,account, runId, runProcessId);
				if(bpmFormDataBean==null)
				{
					mv = new ModelAndView("app/dingding/bpm/mybpmlist");
				}else
				{
					mv = new ModelAndView("app/core/bpm/work/mobile/domwork");
					mv.addObject("bpmFormDataBean",bpmFormDataBean);
				}
				return mv;
			}else
			{
				Account account=accountService.getRedisAccount(request);
				BpmFormDataBean bpmFormDataBean = bpmOptService.doWork(request,account, runId, runProcessId);
				if(bpmFormDataBean==null)
				{
					mv = new ModelAndView("app/core/bpm/work/doprocess");
				}else
				{
					mv = new ModelAndView("app/core/bpm/work/dowork");
					mv.addObject("bpmFormDataBean",bpmFormDataBean);
				}
				return mv;
			}
		}catch (Exception e) {
		// TODO: handle exception
		mv = new ModelAndView("titps");
		return mv;
		}
	}
	
	/**
	 * 
	 * @Title goProcess   
	 * @Description TODO BPM 待办列表 
	 * @param request
	 * @param runId
	 * @param runProcessId
	 * @return      
	 * ModelAndView
	 */
	@RequestMapping("/bpm/doprocess")
	public ModelAndView  goProcess(HttpServletRequest request,String view)
	{
		try
		{
			ModelAndView mv = null;
		if(StringUtils.isBlank(view))
		{
			mv = new ModelAndView("app/core/bpm/work/doprocess");
		}else {
			if(view.equals("inprocess")){
				mv = new ModelAndView("app/core/bpm/work/inprocess");
			}else if(view.equals("endprocess")){
				mv = new ModelAndView("app/core/bpm/work/endprocess");
			}else if(view.equals("sendtome")){
				mv = new ModelAndView("app/core/bpm/work/sendtome");
			}
		}
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
	
	/**
	 * 
	 * @Title goMaintain   
	 * @Description TODO BPM维护  
	 * @param request
	 * @param runId
	 * @param runProcessId
	 * @return      
	 * ModelAndView
	 */
	@RequestMapping("/bpm/domaintain")
	public ModelAndView  goMaintain(HttpServletRequest request,String runId,String runProcessId)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/maintain/query");
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
	
	/**
	 * 
	* @Title: doMonitor 
	* @Description: TODO BPM监控
	* @param @param request
	* @param @param runId
	* @param @param runProcessId
	* @param @return 设定文件 
	* @return ModelAndView 返回类型
	 */
	@RequestMapping("/bpm/domonitor")
	public ModelAndView  doMonitor(HttpServletRequest request,String runId,String runProcessId)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/maintain/monitor");
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
	/**
	 * 
	* @Title: bpmFlowChart 
	* @Description: TODO BPM审批记录流程图
	* @param @param request
	* @param @param runId
	* @param @param runProcessId
	* @param @return 设定文件 
	* @return ModelAndView 返回类型
	 */
	@RequestMapping("/bpm/bpmFlowChart")
	public ModelAndView  bpmFlowChart(HttpServletRequest request,String runId,String runProcessId)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/search/bpmflowchart");
		mv.addObject("runId",runId);
		mv.addObject("runProcessId",runProcessId);
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
	/**
	 * 
	* @Title: bpmFlowChart 
	* @Description: TODO 查看BPM设计流程图
	* @param @param request
	* @param @return 设定文件 
	* @return ModelAndView 返回类型
	 */
	@RequestMapping("/bpm/bpmDesignFlowChart")
	public ModelAndView  bpmFlowChart(HttpServletRequest request)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/process/bpmdesignflowchart");
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
	/**
	 * 
	* @Title: bpmFormTemplate 
	* @Description: TODO 表单模版
	* @param @param request
	* @param @return 设定文件 
	* @return ModelAndView 返回类型
	 */
	@RequestMapping("/bpm/bpmFormTemplate")
	public ModelAndView  bpmFormTemplate(HttpServletRequest request,BpmForm bpmForm)
	{
		ModelAndView mv = null;
		try
		{
			Account account=accountService.getRedisAccount(request);
		bpmForm.setOrgId(account.getOrgId());
		bpmForm = bpmFormService.selectOneBpmForm(bpmForm);
		mv = new ModelAndView("app/core/bpm/form/formtemplate");
		mv.addObject("htmlCode",bpmForm.getHtmlCode());
		mv.addObject("style",bpmForm.getStyle());
		mv.addObject("script",bpmForm.getScript());
		return mv;
		}catch (Exception e) {
		mv = new ModelAndView("titps");
		return mv;
		}
	}
	
	/**
	 * 
	 * @Title: bpmFormTemplateCache   
	 * @Description: TODO 步骤缓存预览
	 * @param request
	 * @param bpmProcess
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/bpm/bpmFormTemplateCache")
	public ModelAndView  bpmFormTemplateCache(HttpServletRequest request,BpmProcess bpmProcess)
	{
		ModelAndView mv = null;
		try
		{
			Account account=accountService.getRedisAccount(request);
		bpmProcess.setOrgId(account.getOrgId());
		bpmProcess = bpmProcessService.selectOne(bpmProcess);
		mv = new ModelAndView("app/core/bpm/form/formtemplatecache");
		mv.addObject("htmlCode",bpmFormCacheService.getHtmlCache(bpmProcess));
		mv.addObject("style",bpmFormCacheService.getStyleCache(bpmProcess));
		mv.addObject("script",bpmFormCacheService.getScriptCache(bpmProcess));
		return mv;
		}catch (Exception e) {
		mv = new ModelAndView("titps");
		return mv;
		}
	}
	/**
	 * 
	 * @Title: bpmMobileFormTemplateCache   
	 * @Description: TODO 移动端步骤缓存预览
	 * @param request
	 * @param bpmProcess
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/bpm/bpmMobileFormTemplateCache")
	public ModelAndView  bpmMobileFormTemplateCache(HttpServletRequest request,BpmProcess bpmProcess)
	{
		ModelAndView mv = null;
		try
		{
		Account account=accountService.getRedisAccount(request);
		bpmProcess.setOrgId(account.getOrgId());
		bpmProcess = bpmProcessService.selectOne(bpmProcess);
		mv = new ModelAndView("app/core/bpm/form/formmobiletemplatecache");
		mv.addObject("htmlCode",bpmFormCacheService.getMobileHtmlCache(bpmProcess));
		mv.addObject("style",bpmFormCacheService.getMobileStyleCache(bpmProcess));
		mv.addObject("script",bpmFormCacheService.getMobileScriptCache(bpmProcess));
		return mv;
		}catch (Exception e) {
		mv = new ModelAndView("titps");
		return mv;
		}
	}
	
	/**
	 * 
	 * @Title goSearch   
	 * @Description TODO BPM 查询
	 * @param request
	 * @param runId
	 * @param runProcessId
	 * @return      
	 * ModelAndView
	 */
	@RequestMapping("/bpm/search")
	public ModelAndView  goSearch(HttpServletRequest request,String view)
	{
		try
		{
		ModelAndView mv = null;
		if(StringUtils.isBlank(view))
		{
			mv = new ModelAndView("app/core/bpm/search/index");
		}else if(view.equals("advsearch")){
			mv = new ModelAndView("app/core/bpm/search/advsearch");
		}else if(view.equals("tablesearch")){
			mv = new ModelAndView("app/core/bpm/search/bpmtablesearch");
		}
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
	
	/**
	 * 
	* @Title: setEntrust 
	* @Description: TODO 流程委托 
	* @param @param request
	* @param @return 设定文件 
	* @return ModelAndView 返回类型
	 */
	@RequestMapping("/bpm/setentrust")
	public ModelAndView  setEntrust(HttpServletRequest request)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/bpm/bpmset/entrust");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
}
