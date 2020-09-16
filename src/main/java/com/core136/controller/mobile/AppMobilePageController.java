package com.core136.controller.mobile;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/appmobile")
public class AppMobilePageController {
	/**
	 * 
	 * @Title: goAppIndex   
	 * @Description: TODO 移动端登陆
	 * @param request
	 * @return
	 * ModelAndView    

	 */
	@RequestMapping("/index")
	public ModelAndView goAppIndex(HttpServletRequest request)
	{
		ModelAndView mv =null;
		try
		{
		mv= new ModelAndView("app/mobile/app/index");
		return mv;
		}catch (Exception e) {
			mv = new ModelAndView("titps");
			return mv;
		}
		
	}
	
}
