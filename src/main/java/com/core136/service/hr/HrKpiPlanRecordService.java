package com.core136.service.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core136.bean.hr.HrKpiPlanRecord;
import com.core136.mapper.hr.HrKpiPlanRecordMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class HrKpiPlanRecordService {
@Autowired
private HrKpiPlanRecordMapper hrKpiPlanRecordMapper;

public int insertHrKpiPlanRecord(HrKpiPlanRecord hrKpiPlanRecord)
{
	return hrKpiPlanRecordMapper.insert(hrKpiPlanRecord);
}

public int deleteHrKpiPlanRecord(HrKpiPlanRecord hrKpiPlanRecord)
{
	return hrKpiPlanRecordMapper.delete(hrKpiPlanRecord);
}

public int updateHrKpiPlanRecord(Example example,HrKpiPlanRecord hrKpiPlanRecord)
{
	return hrKpiPlanRecordMapper.updateByExample(hrKpiPlanRecord, example);
}

public HrKpiPlanRecord selectOneHrKpiPlanRecord(HrKpiPlanRecord hrKpiPlanRecord)
{
	return hrKpiPlanRecordMapper.selectOne(hrKpiPlanRecord);
}

}
