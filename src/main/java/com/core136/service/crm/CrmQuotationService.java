package com.core136.service.crm;

import java.util.List;
import java.util.Map;

import org.core136.common.utils.SysTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.core136.bean.crm.CrmQuotation;
import com.core136.bean.crm.CrmQuotationMx;
import com.core136.bean.sys.PageParam;
import com.core136.mapper.crm.CrmQuotationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

@Service
public class CrmQuotationService {
@Autowired
private CrmQuotationMapper crmQuotationMapper;
@Autowired
private CrmQuotationMxService crmQuotationMxService;

public int insertCrmQuotation(CrmQuotation crmQuotation)
{
	return crmQuotationMapper.insert(crmQuotation);
}

public int deleteCrmQuotation(CrmQuotation crmQuotation)
{
	return crmQuotationMapper.delete(crmQuotation);
}

public int updateCrmQuotation(Example example,CrmQuotation crmQuotation)
{
	return crmQuotationMapper.updateByExampleSelective(crmQuotation, example);
}

public CrmQuotation selectOneCrmQuotation(CrmQuotation crmQuotation)
{
	return crmQuotationMapper.selectOne(crmQuotation);
}
/**
 * 
 * @Title: saveCrmQuotation   
 * @Description: TODO 创建报错单与产品明细
 * @param crmQuotation
 * @param jsonArr
 * @return
 * int
 */
@Transactional(value="generalTM")
public int saveCrmQuotation(CrmQuotation crmQuotation,JSONArray jsonArr)
{
	for(int i=0;i<jsonArr.size();i++)
	{
		CrmQuotationMx crmQuotationMx=JSONObject.parseObject(jsonArr.get(i).toString(),CrmQuotationMx.class);
		crmQuotationMx.setDetailId(SysTools.getGUID());
		crmQuotationMx.setQuotationId(crmQuotation.getQuotationId());
		crmQuotationMx.setInquiryId(crmQuotation.getInquiryId());
		crmQuotationMx.setOrgId(crmQuotation.getOrgId());
		crmQuotationMx.setCreateTime(crmQuotation.getCreateTime());
		crmQuotationMx.setCreateUser(crmQuotation.getCreateUser());
		crmQuotationMxService.insertCrmQuotationMx(crmQuotationMx);
	}
	return insertCrmQuotation(crmQuotation);
}

/**
 * 
 * @Title: updateQuotationAndDetail   
 * @Description: TODO 更新报价单与报价明细
 * @param crmQuotation
 * @param example
 * @param jsonArr
 * @return
 * int
 */
@Transactional(value="generalTM")
public int updateQuotationAndDetail(CrmQuotation crmQuotation,Example example,JSONArray jsonArr)
{
	CrmQuotationMx crmQuotationMxdel = new CrmQuotationMx();
	crmQuotationMxdel.setQuotationId(crmQuotation.getQuotationId());
	crmQuotationMxdel.setOrgId(crmQuotation.getOrgId());
	crmQuotationMxService.deleteCrmQuotationMx(crmQuotationMxdel);
	for(int i=0;i<jsonArr.size();i++)
	{
		CrmQuotationMx crmQuotationMx=JSONObject.parseObject(jsonArr.get(i).toString(),CrmQuotationMx.class);
		crmQuotationMx.setDetailId(SysTools.getGUID());
		crmQuotationMx.setQuotationId(crmQuotation.getQuotationId());
		crmQuotationMx.setInquiryId(crmQuotation.getInquiryId());
		crmQuotationMx.setOrgId(crmQuotation.getOrgId());
		crmQuotationMx.setCreateTime(crmQuotation.getCreateTime());
		crmQuotationMx.setCreateUser(crmQuotation.getCreateUser());
		crmQuotationMxService.insertCrmQuotationMx(crmQuotationMx);
	}
	return updateCrmQuotation(example,crmQuotation);
}



/**
 * 
 * @Title: getMyCrmQuotationList   
 * @Description: TODO 获取报价列表
 * @param orgId
 * @param accountId
 * @param beginTime
 * @param endTime
 * @param status
 * @param search
 * @return
 * List<Map<String,String>>
 */
public List<Map<String, String>>getMyCrmQuotationList(String orgId,String accountId,String beginTime,String endTime,String status,String approvedUser,String search){
	return crmQuotationMapper.getMyCrmQuotationList(orgId, accountId, beginTime, endTime, status,approvedUser, "%"+search+"%");
}
/**
 * 
 * @Title: getMyCrmQuotationList   
 * @Description: TODO 获取报价列表
 * @param pageParam
 * @param beginTime
 * @param endTime
 * @param status
 * @return
 * PageInfo<Map<String,String>>
 */
public PageInfo<Map<String, String>> getMyCrmQuotationList(PageParam pageParam,String beginTime,String endTime,String status,String approvedUser) {
	PageHelper.startPage(pageParam.getPageNumber(), pageParam.getPageSize(),pageParam.getOrderBy());
	List<Map<String,String>> datalist= getMyCrmQuotationList(pageParam.getOrgId(),pageParam.getAccountId(), beginTime,endTime,status,approvedUser,pageParam.getSearch());
	PageInfo<Map<String, String>> pageInfo = new PageInfo<Map<String,String>>(datalist);
	return pageInfo;
}

/**
 * 
 * @Title: getMyApprovedList   
 * @Description: TODO 获取审批列表
 * @param orgId
 * @param accountId
 * @param search
 * @return
 * List<Map<String,String>>
 */
public List<Map<String, String>>getMyApprovedList(String orgId,String accountId,String search)
{
	return crmQuotationMapper.getMyApprovedList(orgId, accountId, "%"+search+"%");
}
/**
 * 
 * @Title: getMyApprovedList   
 * @Description: TODO 获取审批列表
 * @param pageParam
 * @return
 * PageInfo<Map<String,String>>
 */
public PageInfo<Map<String, String>> getMyApprovedList(PageParam pageParam) {
	PageHelper.startPage(pageParam.getPageNumber(), pageParam.getPageSize(),pageParam.getOrderBy());
	List<Map<String,String>> datalist= getMyApprovedList(pageParam.getOrgId(),pageParam.getAccountId(),pageParam.getSearch());
	PageInfo<Map<String, String>> pageInfo = new PageInfo<Map<String,String>>(datalist);
	return pageInfo;
}
/**
 * 
 * @Title: getApprovedQueryList   
 * @Description: TODO 查询审批记录
 * @param orgId
 * @param opFlag
 * @param accountId
 * @param createUser
 * @param beginTime
 * @param endTime
 * @param status
 * @param search
 * @return
 * List<Map<String,String>>
 */
public List<Map<String, String>>getApprovedQueryList(String orgId,String opFlag,String accountId,String createUser,String beginTime,String endTime,String status,String search){
	return crmQuotationMapper.getApprovedQueryList(orgId,opFlag, accountId,createUser, beginTime, endTime, status, "%"+search+"%");
}
/**
 * 
 * @Title: getApprovedQueryList   
 * @Description: TODO 查询审批记录
 * @param pageParam
 * @param createUser
 * @param beginTime
 * @param endTime
 * @param status
 * @return
 * PageInfo<Map<String,String>>
 */
public PageInfo<Map<String, String>> getApprovedQueryList(PageParam pageParam,String createUser,String beginTime,String endTime,String status) {
	PageHelper.startPage(pageParam.getPageNumber(), pageParam.getPageSize(),pageParam.getOrderBy());
	List<Map<String,String>> datalist= getApprovedQueryList(pageParam.getOrgId(),pageParam.getOpFlag(),pageParam.getAccountId(), createUser,beginTime,endTime,status,pageParam.getSearch());
	PageInfo<Map<String, String>> pageInfo = new PageInfo<Map<String,String>>(datalist);
	return pageInfo;
}

/**
 * 
 * @Title: getQuotationQueryList   
 * @Description: TODO 报价单查询列表
 * @param orgId
 * @param approvedUser
 * @param createUser
 * @param beginTime
 * @param endTime
 * @param status
 * @param search
 * @return
 * List<Map<String,String>>
 */
public List<Map<String, String>>getQuotationQueryList(String orgId,String approvedUser,String createUser,String beginTime,String endTime,String status,String search){
	return crmQuotationMapper.getQuotationQueryList(orgId,approvedUser,createUser, beginTime, endTime, status, "%"+search+"%");
}

/**
 * 
 * @Title: getQuotationQueryList   
 * @Description: TODO 报价单查询列表
 * @param pageParam
 * @param approvedUser
 * @param createUser
 * @param beginTime
 * @param endTime
 * @param status
 * @return
 * PageInfo<Map<String,String>>
 */
public PageInfo<Map<String, String>> getQuotationQueryList(PageParam pageParam,String approvedUser,String createUser,String beginTime,String endTime,String status) {
	PageHelper.startPage(pageParam.getPageNumber(), pageParam.getPageSize(),pageParam.getOrderBy());
	List<Map<String,String>> datalist= getQuotationQueryList(pageParam.getOrgId(),approvedUser,createUser,beginTime,endTime,status,pageParam.getSearch());
	PageInfo<Map<String, String>> pageInfo = new PageInfo<Map<String,String>>(datalist);
	return pageInfo;
}


}
