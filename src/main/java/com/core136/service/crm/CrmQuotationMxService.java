package com.core136.service.crm;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core136.bean.crm.CrmQuotationMx;
import com.core136.bean.sys.PageParam;
import com.core136.mapper.crm.CrmQuotationMxMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

@Service
public class CrmQuotationMxService {
@Autowired
private CrmQuotationMxMapper crmQuotationMxMapper;

public int insertCrmQuotationMx(CrmQuotationMx crmQuotationMx)
{
	return crmQuotationMxMapper.insert(crmQuotationMx);
}

public int deleteCrmQuotationMx(CrmQuotationMx crmQuotationMx)
{
	return crmQuotationMxMapper.delete(crmQuotationMx);
}

public int updateCrmQuotationMx(Example example, CrmQuotationMx crmQuotationMx)
{
	return crmQuotationMxMapper.updateByExampleSelective(crmQuotationMx, example);
}

public CrmQuotationMx selectOneCrmQuotationMx(CrmQuotationMx crmQuotationMx)
{
	return crmQuotationMxMapper.selectOne(crmQuotationMx);
}
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
public List<Map<String, String>>getCrmInquiryDetailListForQuotation(String orgId,String inquiryId,String quotationId)
{
	return crmQuotationMxMapper.getCrmInquiryDetailListForQuotation(orgId, inquiryId,quotationId);
}

/**
 * 
 * @Title: getCrmInquiryDetailListForQuotation   
 * @Description: TODO 获取报价单明细
 * @param pageParam
 * @param inquiryId
 * @param quotationId
 * @return
 * PageInfo<Map<String,String>>
 */
public PageInfo<Map<String, String>> getCrmInquiryDetailListForQuotation(PageParam pageParam,String inquiryId,String quotationId) {
	PageHelper.startPage(pageParam.getPageNumber(), pageParam.getPageSize(),pageParam.getOrderBy());
	List<Map<String,String>> datalist= getCrmInquiryDetailListForQuotation(pageParam.getOrgId(), inquiryId,quotationId);
	PageInfo<Map<String, String>> pageInfo = new PageInfo<Map<String,String>>(datalist);
	return pageInfo;
}
}
