package com.core136.controller.platform;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app/core")
public class PlatformPageController {
	
	/**
	 * 
	 * @Title: goMenuSet   
	 * @Description: TODO 智能应用页面设置
	 * @param request
	 * @param pageId
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/platform/formdesign")
	public ModelAndView goFormDesign(HttpServletRequest request,String view)
	{
		ModelAndView mv = null;
		try
		{
			if(StringUtils.isBlank(view))
			{
				mv = new ModelAndView("app/core/platform/form/designmanage");
			}else
			{
				if(view.equals("design"))
				{
					mv = new ModelAndView("app/core/platform/form/design");
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
	 * @Title: goMenuSet   
	 * @Description: TODO 菜单设置
	 * @param request
	 * @param pageId
	 * @return
	 * ModelAndView    
	 * @throws
	 */
	@RequestMapping("/platform/menuset")
	public ModelAndView goMenuSet(HttpServletRequest request,String pageId)
	{
		try
		{
		ModelAndView mv = new ModelAndView("app/core/platform/menu/index");
		return mv;
		}catch (Exception e) {
			ModelAndView mv = new ModelAndView("titps");
			return mv;
		}
	}
	
	
//	@RequestMapping("/platform/test")
//	public ModelAndView goContracttotal(HttpServletRequest request,String pageId)
//	{
//		try
//		{
//		File file = new File("d:/123.html");
//		String ss = FileUtils.readFileToString(file);
//		ModelAndView mv = new ModelAndView("app/core/platform/page/index","content",ss);
//		return mv;
//		}catch (Exception e) {
//			ModelAndView mv = new ModelAndView("titps");
//			return mv;
//		}
//	}
}
