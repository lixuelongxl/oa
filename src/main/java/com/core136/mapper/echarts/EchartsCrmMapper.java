package com.core136.mapper.echarts;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EchartsCrmMapper {
	/**
	 * 
	 * @Title: getBiCustomerIndustryPie   
	 * @Description: TODO 获取CRM的行业占比
	 * @param orgId
	 * @return
	 * List<Map<String,String>>    

	 */
public List<Map<String,String>>getBiCustomerIndustryPie(@Param(value="orgId")String orgId);
/**
 * 
 * @Title: getBiCustomerKeepUserPie   
 * @Description: TODO 获取CRM销售人员的占比
 * @param orgId
 * @return
 * List<Map<String,String>>    

 */
public List<Map<String,String>>getBiCustomerKeepUserPie(@Param(value="orgId")String orgId);

/**
 * 
 * @Title: getBiCustomerAreaPie   
 * @Description: TODO 获取CRM地区占比
 * @param orgId
 * @return
 * List<Map<String,String>>    

 */
public List<Map<String,String>>getBiCustomerAreaPie(@Param(value="orgId")String orgId);

/**
 * 
 * @Title: getBiCustomerLevelPie   
 * @Description: TODO 获取CRM客户等级占比
 * @param orgId
 * @return
 * List<Map<String,String>>    

 */
public List<Map<String,String>>getBiCustomerLevelPie(@Param(value="orgId")String orgId);

/**
 * 
 * @Title: getBiInquiryByAccountPie   
 * @Description: TODO 获取询价单人员占比
 * @param orgId
 * @return
 * List<Map<String,String>>
 */
public List<Map<String, String>>getBiInquiryByAccountPie(@Param(value="orgId")String orgId);

/**
 * 
 * @Title: getBiInquiryByMonthLine   
 * @Description: TODO 按月份统计工作量
 * @param orgId
 * @param beginTime
 * @param endTime
 * @return
 * List<Map<String,String>>    

 */
public List<Map<String,Object>>getBiInquiryByMonthLine(@Param(value="orgId")String orgId,@Param(value="beginTime")String beginTime,@Param(value="endTime")String endTime); 

/**
 * 
 * @Title: getBiInquiryByProductPie   
 * @Description: TODO 获取产品占比
 * @param orgId
 * @return
 * List<Map<String,String>>
 */
public List<Map<String, String>>getBiInquiryByProductPie(@Param(value="orgId")String orgId);

/**
 * 
 * @Title: getBiInquiryByDeptPie   
 * @Description: TODO 部门询价单占比前10的占比
 * @param orgId
 * @return
 * List<Map<String,String>>
 */
public List<Map<String, String>>getBiInquiryByDeptPie(@Param(value="orgId")String orgId);


/**
 * 
 * @Title: getBiQuotationByAccountPie   
 * @Description: TODO 获取报价单人员占比
 * @param orgId
 * @return
 * List<Map<String,String>>
 */
public List<Map<String, String>>getBiQuotationByAccountPie(@Param(value="orgId")String orgId);

/**
 * 
 * @Title: getBiQuotationByMonthLine   
 * @Description: TODO 按月份统计报价单
 * @param orgId
 * @param beginTime
 * @param endTime
 * @return
 * List<Map<String,String>>    

 */
public List<Map<String,Object>>getBiQuotationByMonthLine(@Param(value="orgId")String orgId,@Param(value="beginTime")String beginTime,@Param(value="endTime")String endTime); 

/**
 * 
 * @Title: getBiQuotationByProductPie   
 * @Description: TODO 获取产品占比
 * @param orgId
 * @return
 * List<Map<String,String>>
 */
public List<Map<String, String>>getBiQuotationByProductPie(@Param(value="orgId")String orgId);

/**
 * 
 * @Title: getBiQuotationByDeptPie   
 * @Description: TODO 部门报价单占比前10的占比
 * @param orgId
 * @return
 * List<Map<String,String>>
 */
public List<Map<String, String>>getBiQuotationByDeptPie(@Param(value="orgId")String orgId);
}
