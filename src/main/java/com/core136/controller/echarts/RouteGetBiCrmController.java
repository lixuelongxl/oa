package com.core136.controller.echarts;

import javax.servlet.http.HttpServletRequest;

import org.core136.common.retdataunit.RetDataBean;
import org.core136.common.retdataunit.RetDataTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.core136.bean.account.Account;
import com.core136.service.account.AccountService;
import com.core136.service.echarts.EchartsCrmService;

@RestController
@RequestMapping("/ret/echartscrmget")
public class RouteGetBiCrmController {
@Autowired
private EchartsCrmService echartsCrmService;
@Autowired
private AccountService accountService;

/**
 * 
 * @Title: getBiQuotationByDeptPie   
 * @Description: TODO 部门报价单占比前10的占比
 * @param request
 * @return
 * RetDataBean
 */
@RequestMapping(value = "/getBiQuotationByDeptPie", method = RequestMethod.POST)
public RetDataBean getBiQuotationByDeptPie(HttpServletRequest request) {
	try {
		Account account=accountService.getRedisAccount(request);
		return RetDataTools.Ok("请求成功!", echartsCrmService.getBiQuotationByDeptPie(account));
	} catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title: getBiQuotationByProductPie   
 * @Description: TODO 获取产品分类前10的占比
 * @param request
 * @return
 * RetDataBean
 */
@RequestMapping(value = "/getBiQuotationByProductPie", method = RequestMethod.POST)
public RetDataBean getBiQuotationByProductPie(HttpServletRequest request) {
	try {
		Account account=accountService.getRedisAccount(request);
		return RetDataTools.Ok("请求成功!", echartsCrmService.getBiQuotationByProductPie(account));
	} catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title: getBiQuotationByMonthLine   
 * @Description: TODO 按月份统计报价单
 * @param request
 * @return
 * RetDataBean    

 */
@RequestMapping(value = "/getBiQuotationByMonthLine", method = RequestMethod.POST)
public RetDataBean getBiQuotationByMonthLine(HttpServletRequest request) {
	try {
		Account account=accountService.getRedisAccount(request);
		return RetDataTools.Ok("请求成功!", echartsCrmService.getBiQuotationByMonthLine(account));
	} catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title: getBiQuotationByAccountPie   
 * @Description: TODO 获取报价单人员占比
 * @param request
 * @return
 * RetDataBean
 */
@RequestMapping(value = "/getBiQuotationByAccountPie", method = RequestMethod.POST)
public RetDataBean getBiQuotationByAccountPie(HttpServletRequest request) {
	try {
		Account account=accountService.getRedisAccount(request);
		return RetDataTools.Ok("请求成功!", echartsCrmService.getBiQuotationByAccountPie(account));
	} catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Error(e.getMessage());
	}
}
/**
 * 
 * @Title: getBiInquiryByDeptPie   
 * @Description: TODO 部门询价单占比前10的占比
 * @param request
 * @return
 * RetDataBean
 */
@RequestMapping(value = "/getBiInquiryByDeptPie", method = RequestMethod.POST)
public RetDataBean getBiInquiryByDeptPie(HttpServletRequest request) {
	try {
		Account account=accountService.getRedisAccount(request);
		return RetDataTools.Ok("请求成功!", echartsCrmService.getBiInquiryByDeptPie(account));
	} catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title: getBiInquiryByProductPie   
 * @Description: TODO 获取产品分类前10的占比
 * @param request
 * @return
 * RetDataBean
 */
@RequestMapping(value = "/getBiInquiryByProductPie", method = RequestMethod.POST)
public RetDataBean getBiInquiryByProductPie(HttpServletRequest request) {
	try {
		Account account=accountService.getRedisAccount(request);
		return RetDataTools.Ok("请求成功!", echartsCrmService.getBiInquiryByProductPie(account));
	} catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title: getBiInquiryByMonthLine   
 * @Description: TODO 按月份统计工作量
 * @param request
 * @return
 * RetDataBean    

 */
@RequestMapping(value = "/getBiInquiryByMonthLine", method = RequestMethod.POST)
public RetDataBean getBiInquiryByMonthLine(HttpServletRequest request) {
	try {
		Account account=accountService.getRedisAccount(request);
		return RetDataTools.Ok("请求成功!", echartsCrmService.getBiInquiryByMonthLine(account));
	} catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title: getBiInquiryByAccountPie   
 * @Description: TODO 获取询价单人员占比
 * @param request
 * @return
 * RetDataBean
 */
@RequestMapping(value = "/getBiInquiryByAccountPie", method = RequestMethod.POST)
public RetDataBean getBiInquiryByAccountPie(HttpServletRequest request) {
	try {
		Account account=accountService.getRedisAccount(request);
		return RetDataTools.Ok("请求成功!", echartsCrmService.getBiInquiryByAccountPie(account));
	} catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title: getBiCustomerIndustryPie   
 * @Description: TODO 获取crm的客户行业占比
 * @param request
 * @return
 * RetDataBean    

 */
@RequestMapping(value = "/getBiCustomerIndustryPie", method = RequestMethod.POST)
public RetDataBean getBiCustomerIndustryPie(HttpServletRequest request) {
	try {
		Account account=accountService.getRedisAccount(request);
		return RetDataTools.Ok("请求成功!", echartsCrmService.getBiCustomerIndustryPie(account));
	} catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title: getBiCustomerKeepUserPie   
 * @Description: TODO 获取CRM销售人员的占比
 * @param request
 * @return
 * RetDataBean    

 */
@RequestMapping(value = "/getBiCustomerKeepUserPie", method = RequestMethod.POST)
public RetDataBean getBiCustomerKeepUserPie(HttpServletRequest request) {
	try {
		Account account=accountService.getRedisAccount(request);
		return RetDataTools.Ok("请求成功!", echartsCrmService.getBiCustomerKeepUserPie(account));
	} catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title: getBiCustomerAreaPie   
 * @Description: TODO 获取CRM地区占比
 * @param request
 * @return
 * RetDataBean    

 */
@RequestMapping(value = "/getBiCustomerAreaPie", method = RequestMethod.POST)
public RetDataBean getBiCustomerAreaPie(HttpServletRequest request) {
	try {
		Account account=accountService.getRedisAccount(request);
		return RetDataTools.Ok("请求成功!", echartsCrmService.getBiCustomerAreaPie(account));
	} catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Error(e.getMessage());
	}
}
/**
 * 
 * @Title: getBiCustomerLevelPie   
 * @Description: TODO 获取CRM客户等级占比
 * @param request
 * @return
 * RetDataBean    

 */
@RequestMapping(value = "/getBiCustomerLevelPie", method = RequestMethod.POST)
public RetDataBean getBiCustomerLevelPie(HttpServletRequest request) {
	try {
		Account account=accountService.getRedisAccount(request);
		return RetDataTools.Ok("请求成功!", echartsCrmService.getBiCustomerLevelPie(account));
	} catch (Exception e) {
		e.printStackTrace();
		return RetDataTools.Error(e.getMessage());
	}
}
}
