package com.core136.mapper.crm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.core136.common.dbutils.MyMapper;

import com.core136.bean.crm.CrmQuotationMx;

@Mapper
public interface CrmQuotationMxMapper extends MyMapper<CrmQuotationMx>{

	/**
	 * 
	 * @Title: getCrmInquiryDetailListForQuotation   
	 * @Description: TODO 获取报价单明细
	 * @param orgId
	 * @param inquiryId
	 * @param quotationId
	 * @return
	 * List<Map<String,String>>
	 */
	public List<Map<String, String>>getCrmInquiryDetailListForQuotation(@Param(value="orgId")String orgId,@Param(value="inquiryId")String inquiryId,@Param("quotationId")String quotationId);
}
