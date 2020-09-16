package com.core136.service.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core136.bean.hr.HrKpiPlanItem;
import com.core136.mapper.hr.HrKpiPlanItemMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class HrKpiPlanItemService {
@Autowired 
private HrKpiPlanItemMapper hrKpiPlanItemMapper;

public int insertHrKpiPlanItem(HrKpiPlanItem hrKpiPlanItem)
{
	return hrKpiPlanItemMapper.insert(hrKpiPlanItem);
}

public int deleteHrKpiPlanItem(HrKpiPlanItem hrKpiPlanItem)
{
	return hrKpiPlanItemMapper.delete(hrKpiPlanItem);
}

public int updateHrKpiPlanItem(Example example,HrKpiPlanItem hrKpiPlanItem)
{
	return hrKpiPlanItemMapper.updateByExampleSelective(hrKpiPlanItem, example);
}
public HrKpiPlanItem selectOneHrKpiPlanItem(HrKpiPlanItem hrKpiPlanItem)
{
	return hrKpiPlanItemMapper.selectOne(hrKpiPlanItem);
}

}
