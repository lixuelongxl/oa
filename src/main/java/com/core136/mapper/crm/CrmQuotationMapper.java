package com.core136.mapper.crm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.core136.common.dbutils.MyMapper;

import com.core136.bean.crm.CrmQuotation;

@Mapper
public interface CrmQuotationMapper extends MyMapper<CrmQuotation>{

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
	public List<Map<String, String>>getMyCrmQuotationList(@Param(value="orgId")String orgId,@Param(value="accountId")String accountId,
			@Param(value="beginTime")String beginTime,@Param(value="endTime")String endTime,@Param(value="status")String status,
			@Param(value="approvedUser")String approvedUser,@Param(value="search")String search);
	
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
	public List<Map<String, String>>getMyApprovedList(@Param(value="orgId")String orgId,@Param(value="accountId")String accountId,
			@Param(value="search")String search);
	
	
	/**
	 * 
	 * @Title: getApprovedQueryList   
	 * @Description: TODO 查询审批记录
	 * @param orgId
	 * @param opFlag
	 * @param accountId
	 * @param createuser
	 * @param beginTime
	 * @param endTime
	 * @param status
	 * @param search
	 * @return
	 * List<Map<String,String>>
	 */
	public List<Map<String, String>>getApprovedQueryList(@Param(value="orgId")String orgId,@Param(value="opFlag") String opFlag,
			@Param(value="accountId")String accountId,@Param(value="createUser")String createuser,
			@Param(value="beginTime")String beginTime,@Param(value="endTime")String endTime,
			@Param(value="status")String status,@Param(value="search")String search);
	
	
	/**
	 * 
	 * @Title: getQuotationQueryList   
	 * @Description: TODO 报价单查询列表
	 * @param orgId
	 * @param approvedUser
	 * @param createuser
	 * @param beginTime
	 * @param endTime
	 * @param status
	 * @param search
	 * @return
	 * List<Map<String,String>>
	 */
	public List<Map<String, String>>getQuotationQueryList(@Param(value="orgId")String orgId,
			@Param(value="approvedUser")String approvedUser,@Param(value="createUser")String createuser,
			@Param(value="beginTime")String beginTime,@Param(value="endTime")String endTime,
			@Param(value="status")String status,@Param(value="search")String search);
}
