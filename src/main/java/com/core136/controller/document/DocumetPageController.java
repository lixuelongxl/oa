package com.core136.controller.document;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.core136.common.utils.SysTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.core136.bean.account.Account;
import com.core136.bean.document.DocumentFlow;
import com.core136.bean.document.DocumentForm;
import com.core136.bean.document.DocumentList;
import com.core136.service.document.DocumentFlowService;
import com.core136.service.document.DocumentFormService;
import com.core136.service.document.DocumentListService;
import com.core136.service.document.DocumentOptService;
import com.core136.unit.document.DocumentFormDataBean;

@Controller
@RequestMapping("/app/core/document")
public class DocumetPageController {
	@Value("${app.bpmtable}")
	private String bpmtable;
	@Value("${app.bpmfield}")
	private String bpmfield;
	@Autowired
	private DocumentFormService documentFormService;
	@Autowired
	private DocumentFlowService documentFlowService;
	@Autowired
	private DocumentOptService documentOptService;
	@Autowired
	private DocumentListService documentListService;
	
	/**
	 * 
	 * @Title: goSendtome   
	 * @Description: TODO 公文抄送
	 * @param request
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/sendtome")
	public ModelAndView  goSendtome(HttpServletRequest request)
	{
		ModelAndView mv = null;
	try
	{
		mv = new ModelAndView("/app/core/document/work/sendtome");
		return mv;
	}catch (Exception e) {
		mv = new ModelAndView("titps");
		return mv;
	}
	}
	/**
	 * 
	 * @Title: goSetentrust   
	 * @Description: TODO 公文流程委托
	 * @param request
	 * @param view
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/setentrust")
	public ModelAndView  goSetentrust(HttpServletRequest request)
	{
		ModelAndView mv = null;
	try
	{
		mv = new ModelAndView("/app/core/document/set/documententrust");
		return mv;
	}catch (Exception e) {
		mv = new ModelAndView("titps");
		return mv;
	}
	}
	
	/**
	 * 
	 * @Title: goDocumentDocumentPlugins   
	 * @Description: TODO 收发文差价设置
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/documentplugins")
	public ModelAndView  goDocumentDocumentPlugins()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/document/set/documentplugins");
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}
	
	/**
	 * 
	 * @Title: goDocumentFilingQuery 
	 * @Description: TODO 收发文归档查询
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/filingquery")
	public ModelAndView  goDocumentFilingQuery()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/document/filing/filingquery");
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}
	/**
	 * 
	 * @Title: goDocumentFiling   
	 * @Description: TODO 收发文归档
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/filing")
	public ModelAndView  goDocumentFiling()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/document/filing/filing");
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}
	/**
	 * 
	 * @Title: goDocumentRunLog   
	 * @Description: TODO 公文日志
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/documentrunlog")
	public ModelAndView  goDocumentRunLog()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/document/set/documentrunlog");
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
	 * @Description: TODO 收发文流程走向图
	 * @param request
	 * @param runId
	 * @param runProcessId
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/documentFlowChart")
	public ModelAndView  bpmFlowChart(HttpServletRequest request,String runId,String runProcessId)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/document/documentflowchart");
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
	 * @Title: goSummary   
	 * @Description: TODO 收发文汇总
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/summary")
	public ModelAndView  goSummary()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/document/search/summary");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}
	
	/**
	 * 
	 * @Title: goDocumentManage   
	 * @Description: TODO 收发文管理
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/documentmanage")
	public ModelAndView  goDocumentManage()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/document/documentmanage");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}
	
	/**
	 * 
	 * @Title: goHontTouTemplate   
	 * @Description: TODO 收文发红头文件设置
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/hongtoutemplate")
	public ModelAndView  goHontTouTemplate()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/document/set/hongtoutemplate");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}
	/**
	 * 
	 * @Title: goQuery   
	 * @Description: TODO 收发文查询
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/query")
	public ModelAndView  goQuery(String view)
	{
		ModelAndView mv= null;
		try
		{
			if(StringUtils.isBlank(view))
			{
				mv = new ModelAndView("app/core/document/search/index");
			}else 
			{
				if(view.equals("advsearch"))
				{
					mv = new ModelAndView("app/core/document/search/advsearch");	
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
	 * @Title: gonNextPrcs   
	 * @Description: TODO 移动端办理下一步操作界面
	 * @param: @return      
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping("/nextprcs")
	public ModelAndView  gonNextPrcs()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/document/work/mobile/nextprcs");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}	
	
	/**
	 * 
	 * @Title: goDocumentRead   
	 * @Description: TODO 收发文表单详情
	 * @param request
	 * @param runId
	 * @param flowId
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/documentread")
	public ModelAndView  goDocumentRead(HttpServletRequest request,String runId,String flowId)
	{
		ModelAndView mv=null;
		try
		{
		Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
		if(SysTools.isMobileDevice(request))
		{
			mv = new ModelAndView("app/core/document/work/mread");
		}else
		{
			mv = new ModelAndView("app/core/document/work/read");
		}
		if(StringUtils.isBlank(flowId))
		{
			DocumentList documentList = new DocumentList();
			documentList.setOrgId(account.getOrgId());
			documentList.setRunId(runId);
			documentList=documentListService.selectOne(documentList);
			mv.addObject("flowId",documentList.getFlowId());
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
	 * @Title: godowork   
	 * @Description: TODO 收发文流程办理
	 * @param request
	 * @param runId
	 * @param runProcessId
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/dowork")
	public ModelAndView  godowork(HttpServletRequest request,String runId,String runProcessId)
	{
		ModelAndView mv=null;
		try
		{
		if(SysTools.isMobileDevice(request))
			{
				Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
				DocumentFormDataBean documentFormDataBean = documentOptService.doMWork(request,account, runId, runProcessId);
				if(documentFormDataBean==null)
				{
					mv = new ModelAndView("app/dingding/document/mybpmlist");
				}else
				{
					mv = new ModelAndView("app/core/document/work/mobile/domwork");
					mv.addObject("documentFormDataBean",documentFormDataBean);
				}
				return mv;
			}else
			{
				Account account=(Account)request.getSession().getAttribute("LOGIN_USER");
				DocumentFormDataBean documentFormDataBean = documentOptService.doWork(request,account, runId, runProcessId);
				if(documentFormDataBean==null)
				{
					mv = new ModelAndView("app/core/document/dispatchapproved");
				}else
				{
					mv = new ModelAndView("app/core/document/work/dowork");
					mv.addObject("documentFormDataBean",documentFormDataBean);
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
	 * @Title: goReceiptapproved   
	 * @Description: TODO收文办理列表
	 * @param view
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/receiptapproved")
	public ModelAndView goReceiptapproved(String view)
	{
		ModelAndView mv = null;
		try
		{
			if(StringUtils.isBlank(view))
			{
				mv = new ModelAndView("app/core/document/work/receiptapproved");
			}else
			{
				if(view.equals("prcs"))
				{
					mv = new ModelAndView("app/core/document/work/receiptprcs");
				}else if (view.equals("end"))
				{
					mv = new ModelAndView("app/core/document/work/receiptend");
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
	 * @Title: goDispatchapproved   
	 * @Description: TODO 
	 * @param request
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/dispatchapproved")
	public ModelAndView goDispatchapproved(String view)
	{
		ModelAndView mv = null;
		try
		{
			if(StringUtils.isBlank(view))
			{
				mv = new ModelAndView("app/core/document/work/dispatchapproved");
			}else
			{
				if(view.equals("prcs"))
				{
					mv = new ModelAndView("app/core/document/work/dispatchprcs");
				}else if (view.equals("end"))
				{
					mv = new ModelAndView("app/core/document/work/dispatchend");
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
	 * @Title: goDocumentDesignFlowChart   
	 * @Description: TODO 收文发设计流程图
	 * @param request
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/documentDesignFlowChart")
	public ModelAndView  goDocumentDesignFlowChart(HttpServletRequest request)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/document/documentdesignflowchart");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
	
	/**
	 * 
	 * @Title: goDocumentNumber   
	 * @Description: TODO 收发文文号管理
	 * @param request
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/documentNumber")
	public ModelAndView  goDocumentNumber(HttpServletRequest request)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/document/set/documentnumber");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
	
	/**
	 * 
	 * @Title: goDispatchCreate   
	 * @Description: TODO 发文
	 * @param request
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/dispatchcreate")
	public ModelAndView  goDispatchCreate(HttpServletRequest request)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/document/dispatch/documentcreat");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
	/**
	 * 
	 * @Title: goReceiptCreate   
	 * @Description: TODO 收文登记
	 * @param request
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/receiptcreate")
	public ModelAndView  goReceiptCreate(HttpServletRequest request)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/document/receipt/documentcreat");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
	/**
	 * 
	 * @Title: goDocumentFlow   
	 * @Description: TODO 公文流程设计
	 * @param request
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/documentflow")
	public ModelAndView  goDocumentFlow(String view)
	{
		ModelAndView mv = null;
		try
		{
		if(StringUtils.isBlank(view))
		{
			mv = new ModelAndView("app/core/document/set/documentflowmanage");
		}else
		{
			if(view.equals("create"))
			{
				mv = new ModelAndView("app/core/document/set/documentflow");
			}else if(view.equals("edit"))
			{
				mv = new ModelAndView("app/core/document/set/documentflowedit");
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
	 * @Title: goDocumentFlowDesigner   
	 * @Description: TODO 收发文流程设计
	 * @param request
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/documentflowdesigner")
	public ModelAndView  goDocumentFlowDesigner(HttpServletRequest request,DocumentFlow documentFlow)
	{
		try
		{
		Account account = (Account)request.getSession().getAttribute("LOGIN_USER");
		documentFlow.setOrgId(account.getOrgId());
		documentFlow = documentFlowService.selectOneDocumentFlow(documentFlow);
		ModelAndView mv = new ModelAndView("app/core/document/set/documentflowdesigner");
		mv.addObject("flowName",documentFlow.getFlowName());
		return mv;
		}catch (Exception e) {
		// TODO: handle exception
		ModelAndView mv = new ModelAndView("titps");
		return mv;
		}
	}
	
	/**
	 * 
	 * @Title: goDocumentFormmDesigner   
	 * @Description: TODO 移动端公文表单设计
	 * @param request
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/documentformmdesigner")
	public ModelAndView  goDocumentFormmDesigner(HttpServletRequest request)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/document/set/documentformmdesigner");
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
	 * @Title: goDocumentFormDesigner   
	 * @Description: TODO 收发文表单设计
	 * @param request
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/documentformdesigner")
	public ModelAndView  goDocumentFormDesigner(HttpServletRequest request)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/document/set/documentformdesigner");
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
	* @Title: goDocumentFormTemplate 
	* @Description: TODO 表单模版
	* @param @param request
	* @param @return 设定文件 
	* @return ModelAndView 返回类型
	 */
	@RequestMapping("/documentformtemplate")
	public ModelAndView  goDocumentFormTemplate(HttpServletRequest request,DocumentForm documentForm)
	{
		ModelAndView mv = null;
		try
		{
		Account account = (Account)request.getSession().getAttribute("LOGIN_USER");
		documentForm.setOrgId(account.getOrgId());
		documentForm = documentFormService.selectOneDocumentForm(documentForm);
		mv = new ModelAndView("app/core/bpm/form/formtemplate");
		mv.addObject("htmlCode",documentForm.getHtmlCode());
		mv.addObject("style",documentForm.getStyle());
		mv.addObject("script",documentForm.getScript());
		return mv;
		}catch (Exception e) {
		mv = new ModelAndView("titps");
		return mv;
		}
	}
	
	/**
	 * 
	 * @Title: goDocumentForm   
	 * @Description: TODO 公文表单列表
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/documentform")
	public ModelAndView  goDocumentForm(String view)
	{
		ModelAndView mv = null;
		try
		{
			if(StringUtils.isBlank(view))
			{
				mv = new ModelAndView("app/core/document/set/documentform");
				mv.addObject("bpmtable",bpmtable);
			}else {
				if(view.equals("formfields"))
				{
					mv = new ModelAndView("app/core/document/set/formfields");	
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
	 * @Title: goDocumentSort   
	 * @Description: TODO 
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/documentsort")
	public ModelAndView  goDocumentSort()
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/document/set/documentsort");
		return mv;
		}catch (Exception e) {
		ModelAndView mv = new ModelAndView("titps");
		return mv;
	}
	}
}
