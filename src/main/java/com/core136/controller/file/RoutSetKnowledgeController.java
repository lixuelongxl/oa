package com.core136.controller.file;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.core136.bean.account.Account;
import com.core136.bean.file.Knowledge;
import com.core136.bean.file.KnowledgeComment;
import com.core136.bean.file.KnowledgeLearn;
import com.core136.bean.file.KnowledgeSort;
import com.core136.service.account.AccountService;
import com.core136.service.file.KnowledgeCommentService;
import com.core136.service.file.KnowledgeLearnService;
import com.core136.service.file.KnowledgeSearchService;
import com.core136.service.file.KnowledgeService;
import com.core136.service.file.KnowledgeSortService;
import org.core136.common.retdataunit.RetDataBean;
import org.core136.common.retdataunit.RetDataTools;
import org.core136.common.utils.SysTools;

import tk.mybatis.mapper.entity.Example;

@RestController
@RequestMapping("/set/knowledgeset")
public class RoutSetKnowledgeController {
	@Autowired
	private KnowledgeSortService knowledgeSortService;
	@Autowired
	private KnowledgeService knowledgeService;
	@Autowired
	private KnowledgeLearnService knowledgeLearnService;
	@Autowired
	private KnowledgeSearchService knowledgeSearchService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private KnowledgeCommentService knowledgeCommentService;
	/**
	 * 
	 * @Title: insertKnowledgeComment   
	 * @Description: TODO 添加评论
	 * @param request
	 * @param knowledgeComment
	 * @return
	 * RetDataBean
	 */
	@RequestMapping(value="/insertKnowledgeComment",method=RequestMethod.POST)
	public RetDataBean insertKnowledgeComment(HttpServletRequest request,KnowledgeComment knowledgeComment)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			knowledgeComment.setCommentId(SysTools.getGUID());
			knowledgeComment.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			knowledgeComment.setCreateUser(account.getAccountId());
			knowledgeComment.setOrgId(account.getOrgId());
			return RetDataTools.Ok("添加评论成功！", knowledgeCommentService.addKnowledgeComment(knowledgeComment));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: deleteKnowledgeComment   
	 * @Description: TODO 删除评论
	 * @param request
	 * @param knowledgeComment
	 * @return
	 * RetDataBean
	 */
	@RequestMapping(value="/deleteKnowledgeComment",method=RequestMethod.POST)
	public RetDataBean deleteKnowledgeComment(HttpServletRequest request,KnowledgeComment knowledgeComment)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(StringUtils.isBlank(knowledgeComment.getCommentId()))
			{
				return RetDataTools.NotOk("请求有参数有问题，请检查参数！");
			}
			knowledgeComment.setCreateUser(account.getAccountId());
			knowledgeComment.setOrgId(account.getOrgId());
			return RetDataTools.Ok("删除评论成功！", knowledgeCommentService.deleteKnowledgeComment(knowledgeComment));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: updateKnowledgeComment   
	 * @Description: TODO更新评论
	 * @param request
	 * @param knowledgeComment
	 * @return
	 * RetDataBean
	 */
	@RequestMapping(value="/updateKnowledgeComment",method=RequestMethod.POST)
	public RetDataBean updateKnowledgeComment(HttpServletRequest request,KnowledgeComment knowledgeComment)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(StringUtils.isBlank(knowledgeComment.getCommentId()))
			{
				return RetDataTools.NotOk("请求有参数有问题，请检查参数！");
			}
			Example example = new Example(KnowledgeComment.class);
			example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("commentId",knowledgeComment.getCommentId());
			return RetDataTools.Ok("更新评论成功！", knowledgeCommentService.updateKnowledgeComment(example, knowledgeComment));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: insertKnowledgeLearn   
	 * @Description: TODO 创建学习记录
	 * @param: request
	 * @param: knowledgeLearn
	 * @param: @return      
	 * @return: RetDataBean      

	 */
	@RequestMapping(value="/insertKnowledgeLearn",method=RequestMethod.POST)
	public RetDataBean insertKnowledgeLearn(HttpServletRequest request,KnowledgeLearn knowledgeLearn)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("您不是系统管理员,请与管理员联系!");
			}
			knowledgeLearn.setLearnId(SysTools.getGUID());
			knowledgeLearn.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			knowledgeLearn.setCreateUser(account.getAccountId());
			knowledgeLearn.setOrgId(account.getOrgId());
			return RetDataTools.Ok("添加知识分类成功！", knowledgeLearnService.insertKnowledgeLearn(knowledgeLearn));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: insertKnowledgeSort   
	 * @Description: TODO 添加知识分类
	 * @param: request
	 * @param: knowledgeSort
	 * @param: @return      
	 * @return: RetDataBean      

	 */
	@RequestMapping(value="/insertKnowledgeSort",method=RequestMethod.POST)
	public RetDataBean insertKnowledgeSort(HttpServletRequest request,KnowledgeSort knowledgeSort)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("您不是系统管理员,请与管理员联系!");
			}
			if(StringUtils.isBlank(knowledgeSort.getSortLeave()))
			{
				knowledgeSort.setSortLeave("0");
			}
			knowledgeSort.setSortId(SysTools.getGUID());
			knowledgeSort.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			knowledgeSort.setCreateUser(account.getAccountId());
			knowledgeSort.setOrgId(account.getOrgId());
			return RetDataTools.Ok("添加知识分类成功！", knowledgeSortService.insertKnowledgeSort(knowledgeSort));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: deleteKnowledgeSort   
	 * @Description: TODO 删除知识分类  
	 * @param: request
	 * @param: knowledgeSort
	 * @param: @return      
	 * @return: RetDataBean      

	 */
	@RequestMapping(value="/deleteKnowledgeSort",method=RequestMethod.POST)
	public RetDataBean deleteKnowledgeSort(HttpServletRequest request,KnowledgeSort knowledgeSort)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("您不是系统管理员,请与管理员联系!");
			}
			if(StringUtils.isBlank(knowledgeSort.getSortId()))
			{
				return RetDataTools.NotOk("请求有参数有问题，请检查参数！");
			}
			knowledgeSort.setOrgId(account.getOrgId());
			return RetDataTools.Ok("删除知识分类成功！", knowledgeSortService.deleteKnowledgeSort(knowledgeSort));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: updateKnowledgeSort   
	 * @Description: TODO 更新知识分类 
	 * @param: request
	 * @param: knowledgeSort
	 * @param: @return      
	 * @return: RetDataBean      

	 */
	@RequestMapping(value="/updateKnowledgeSort",method=RequestMethod.POST)
	public RetDataBean updateKnowledgeSort(HttpServletRequest request,KnowledgeSort knowledgeSort)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(!account.getOpFlag().equals("1"))
			{
				return RetDataTools.NotOk("您不是系统管理员,请与管理员联系!");
			}
			if(StringUtils.isBlank(knowledgeSort.getSortId()))
			{
				return RetDataTools.NotOk("请求有参数有问题，请检查参数！");
			}
			Example example = new Example(KnowledgeSort.class);
			example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("sortId",knowledgeSort.getSortId());
			return RetDataTools.Ok("更新知识分类成功！", knowledgeSortService.updateKnowledgeSort(example, knowledgeSort));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: resetKnowledgeindex   
	 * @Description: TODO  重建知识文档索引
	 * @param request
	 * @param knowledge
	 * @return
	 * RetDataBean
	 */
	@RequestMapping(value="/resetKnowledgeindex",method=RequestMethod.POST)
	public RetDataBean resetKnowledgeindex(HttpServletRequest request,Knowledge knowledge)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			knowledge.setOrgId(account.getOrgId());
			knowledgeService.resetKnowledgeindex(knowledge);
			return RetDataTools.Ok("重建知识文档索引成功！");
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @Title: insertKnowledge   
	 * @Description: TODO 添加知识
	 * @param: request
	 * @param: knowledge
	 * @param: @return      
	 * @return: RetDataBean      

	 */
	@RequestMapping(value="/insertKnowledge",method=RequestMethod.POST)
	public RetDataBean insertKnowledge(HttpServletRequest request,Knowledge knowledge)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			knowledge.setKnowledgeId(SysTools.getGUID());
			knowledge.setCreateTime(SysTools.getTime("yyyy-MM-dd HH:mm:ss"));
			knowledge.setCreateUser(account.getAccountId());
			knowledge.setOrgId(account.getOrgId());
			return RetDataTools.Ok("添加知识文档成功！", knowledgeService.addKnowledge(knowledge));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: updateKnowledge   
	 * @Description: TODO 更新知识
	 * @param request
	 * @param knowledge
	 * @return
	 * RetDataBean    

	 */
	@RequestMapping(value="/updateKnowledge",method=RequestMethod.POST)
	public RetDataBean updateKnowledge(HttpServletRequest request,Knowledge knowledge)
	{
		try
		{
			if(StringUtils.isBlank(knowledge.getKnowledgeId()))
			{
				return RetDataTools.NotOk("请求参数有问题,请检查!");
			}
			Account account=accountService.getRedisAccount(request);
			Example example = new Example(Knowledge.class);
			example.createCriteria().andEqualTo("orgId",account.getOrgId()).andEqualTo("knowledgeId",knowledge.getKnowledgeId());
			return RetDataTools.Ok("更新成功!",knowledgeService.updateKnowledge(example, knowledge));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: deleteIndex   
	 * @Description: TODO 删除知识文档的索引
	 * @param request
	 * @param knowledge
	 * @return
	 * RetDataBean
	 */
	@RequestMapping(value="/deleteKnowledgeindex",method=RequestMethod.POST)
	public RetDataBean deleteKnowledgeindex(HttpServletRequest request,Knowledge knowledge)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(!account.getOpFlag().equals("1"))
			{
				knowledge.setCreateUser(account.getAccountId());
			}
			if(StringUtils.isBlank(knowledge.getKnowledgeId()))
			{
				return RetDataTools.NotOk("请求有参数有问题，请检查参数！");
			}
			knowledge.setOrgId(account.getOrgId());
			knowledgeService.deleteIndexByAttachId(knowledge);
			return RetDataTools.Ok("删除知识文档成功！");
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: deleteKnowledge   
	 * @Description: TODO 删除知识文档
	 * @param: request
	 * @param: knowledge
	 * @param: @return      
	 * @return: RetDataBean      

	 */
	@RequestMapping(value="/deleteKnowledge",method=RequestMethod.POST)
	public RetDataBean deleteKnowledge(HttpServletRequest request,Knowledge knowledge)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			if(!account.getOpFlag().equals("1"))
			{
				knowledge.setCreateUser(account.getAccountId());
			}
			if(StringUtils.isBlank(knowledge.getKnowledgeId()))
			{
				return RetDataTools.NotOk("请求有参数有问题，请检查参数！");
			}
			knowledge.setOrgId(account.getOrgId());
			return RetDataTools.Ok("删除知识文档成功！", knowledgeService.deleteKnowledgeAndIndex(knowledge));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
}
