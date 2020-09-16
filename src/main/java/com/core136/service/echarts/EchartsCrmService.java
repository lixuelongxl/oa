package com.core136.service.echarts;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core136.bean.account.Account;
import com.core136.mapper.echarts.EchartsCrmMapper;

import cyunsoft.bi.option.bean.OptionConfig;
import cyunsoft.bi.option.property.OptionSeries;
import cyunsoft.bi.option.property.OptionTitle;
import cyunsoft.bi.option.resdata.LegendData;
import cyunsoft.bi.option.resdata.SeriesData;
import cyunsoft.bi.option.style.Emphasis;
import cyunsoft.bi.option.style.ItemStyle;
import cyunsoft.bi.option.units.LineOption;
import cyunsoft.bi.option.units.PieOption;

@Service
public class EchartsCrmService {
private PieOption pieOption = new PieOption();
private LineOption lineOption = new LineOption();
@Autowired
EchartsCrmMapper echartsCrmMapper;

/**
 * 
 * @Title: getBiQuotationByDeptPie   
 * @Description: TODO 部门报价单占比前10的占比
 * @param account
 * @return
 * OptionConfig    

 */
public OptionConfig getBiQuotationByDeptPie(Account account)
{
	OptionConfig optionConfig = new OptionConfig();
	List<Map<String, String>> resdataList = getBiQuotationByDeptPie(account.getOrgId());
	OptionSeries[] optionSeriesArr = new OptionSeries[1];
	SeriesData[] dataArr = new SeriesData[resdataList.size()];
	int selectedLeng=0;
	if(dataArr.length>=10)
	{
		selectedLeng=10;
	}else
	{
		selectedLeng = dataArr.length;
	}
	String[] selected = new String[selectedLeng];
	LegendData[] legendDatas = new LegendData[dataArr.length];
	for(int i=0;i<dataArr.length;i++)
	{
		if(StringUtils.isBlank(resdataList.get(i).get("name")))
		{
			resdataList.get(i).put("name", "other"+i);
		}
		if(i<selectedLeng)
		{
			selected[i]=resdataList.get(i).get("name").toString();
		}
		LegendData legendData = new LegendData();
		legendData.setName(resdataList.get(i).get("name").toString());
		legendDatas[i]=legendData;
		SeriesData seriesData = new SeriesData();
		seriesData.setName(resdataList.get(i).get("name").toString());
		seriesData.setValue(Double.valueOf(String.valueOf(resdataList.get(i).get("value"))));
		dataArr[i]=seriesData;
	}
	OptionSeries optionSeries = new OptionSeries();
	optionSeries.setName("人员部门");
	optionSeries.setType("pie");
	optionSeries.setRadius("55%");
	optionSeries.setCenter(new String[]{"40%", "50%"});
	Emphasis emphasis = new Emphasis();
	ItemStyle itemStyle = new ItemStyle();
	itemStyle.setShadowBlur(10);
	itemStyle.setShadowOffsetX(0);
	itemStyle.setShadowColor("rgba(0, 0, 0, 0.5)");
	emphasis.setItemStyle(itemStyle);
	optionSeries.setData(dataArr);
	optionSeriesArr[0] = optionSeries;
	optionConfig.setSeries(optionSeriesArr);
	optionConfig = pieOption.getPieLegendChartOption(legendDatas,selected,optionSeriesArr);
	OptionTitle optionTitle = new OptionTitle();
	optionTitle.setText("部门报价单统计");
	optionTitle.setSubtext("部门报价单占比");
	optionTitle.setLeft("center");
	optionConfig.setTitle(optionTitle);
	return optionConfig;
}
public List<Map<String, String>> getBiQuotationByDeptPie(String orgId)
{
	return echartsCrmMapper.getBiQuotationByDeptPie(orgId);
}

/**
 * 
 * @Title: getBiQuotationByProductPie   
 * @Description: TODO 获取产品分类前10的占比
 * @param account
 * @return
 * OptionConfig    

 */
public OptionConfig getBiQuotationByProductPie(Account account)
{
	OptionConfig optionConfig = new OptionConfig();
	List<Map<String, String>> resdataList = getBiQuotationByProductPie(account.getOrgId());
	OptionSeries[] optionSeriesArr = new OptionSeries[1];
	SeriesData[] dataArr = new SeriesData[resdataList.size()];
	int selectedLeng=0;
	if(dataArr.length>=10)
	{
		selectedLeng=10;
	}else
	{
		selectedLeng = dataArr.length;
	}
	String[] selected = new String[selectedLeng];
	LegendData[] legendDatas = new LegendData[dataArr.length];
	for(int i=0;i<dataArr.length;i++)
	{
		if(StringUtils.isBlank(resdataList.get(i).get("name")))
		{
			resdataList.get(i).put("name", "other"+i);
		}
		if(i<selectedLeng)
		{
			selected[i]=resdataList.get(i).get("name").toString();
		}
		LegendData legendData = new LegendData();
		legendData.setName(resdataList.get(i).get("name").toString());
		legendDatas[i]=legendData;
		SeriesData seriesData = new SeriesData();
		seriesData.setName(resdataList.get(i).get("name").toString());
		seriesData.setValue(Double.valueOf(String.valueOf(resdataList.get(i).get("value"))));
		dataArr[i]=seriesData;
	}
	OptionSeries optionSeries = new OptionSeries();
	optionSeries.setName("询价产品分类");
	optionSeries.setType("pie");
	optionSeries.setRadius("55%");
	optionSeries.setCenter(new String[]{"40%", "50%"});
	Emphasis emphasis = new Emphasis();
	ItemStyle itemStyle = new ItemStyle();
	itemStyle.setShadowBlur(10);
	itemStyle.setShadowOffsetX(0);
	itemStyle.setShadowColor("rgba(0, 0, 0, 0.5)");
	emphasis.setItemStyle(itemStyle);
	optionSeries.setData(dataArr);
	optionSeriesArr[0] = optionSeries;
	optionConfig.setSeries(optionSeriesArr);
	optionConfig = pieOption.getPieLegendChartOption(legendDatas,selected,optionSeriesArr);
	OptionTitle optionTitle = new OptionTitle();
	optionTitle.setText("产品分类总数统计");
	optionTitle.setSubtext("产品分类总数占比");
	optionTitle.setLeft("center");
	optionConfig.setTitle(optionTitle);
	return optionConfig;
}

public List<Map<String, String>> getBiQuotationByProductPie(String orgId)
{
	return echartsCrmMapper.getBiQuotationByProductPie(orgId);
}

/**
 * 
 * @Title: getBiQuotationByMonthLine   
 * @Description: TODO 按月份统计报价单
 * @param account
 * @return
 * OptionConfig
 */
public OptionConfig getBiQuotationByMonthLine(Account account)
{
	OptionConfig optionConfig = new OptionConfig();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    Date y = c.getTime();
    String endTime = format.format(y);
    c.add(Calendar.YEAR, -1);
    y = c.getTime();
    String beginTime = format.format(y);
	List<Map<String, Object>> resList = getBiQuotationByMonthLine(account.getOrgId(), beginTime, endTime);
	String [] xAxisData = new String[resList.size()];
	Double[] resData = new Double[resList.size()];
	for(int i=0;i<resList.size();i++)
	{
		xAxisData[i]=resList.get(i).get("createTime").toString();
		resData[i]=Double.valueOf(resList.get(i).get("total").toString());
	}
	optionConfig = lineOption.getBasicLineChartOption(xAxisData, resData);
	return optionConfig;
}
public List<Map<String, Object>> getBiQuotationByMonthLine(String orgId,String beginTime, String endTime)
{
	return echartsCrmMapper.getBiQuotationByMonthLine(orgId,beginTime, endTime);
}
/**
 * 
 * @Title: getBiQuotationByAccountPie   
 * @Description: TODO 获取报价单人员占比
 * @param account
 * @return
 * OptionConfig
 */
public OptionConfig getBiQuotationByAccountPie(Account account)
{
	OptionConfig optionConfig = new OptionConfig();
	List<Map<String, String>> resdataList = getBiQuotationByAccountPie(account.getOrgId());
	OptionSeries[] optionSeriesArr = new OptionSeries[1];
	SeriesData[] dataArr = new SeriesData[resdataList.size()];
	int selectedLeng=0;
	if(dataArr.length>=10)
	{
		selectedLeng=10;
	}else
	{
		selectedLeng = dataArr.length;
	}
	String[] selected = new String[selectedLeng];
	LegendData[] legendDatas = new LegendData[dataArr.length];
	for(int i=0;i<dataArr.length;i++)
	{
		if(StringUtils.isBlank(resdataList.get(i).get("name")))
		{
			resdataList.get(i).put("name", "other"+i);
		}
		if(i<selectedLeng)
		{
			selected[i]=resdataList.get(i).get("name").toString();
		}
		LegendData legendData = new LegendData();
		legendData.setName(resdataList.get(i).get("name").toString());
		legendDatas[i]=legendData;
		SeriesData seriesData = new SeriesData();
		seriesData.setName(resdataList.get(i).get("name").toString());
		seriesData.setValue(Double.valueOf(String.valueOf(resdataList.get(i).get("value"))));
		dataArr[i]=seriesData;
	}
	OptionSeries optionSeries = new OptionSeries();
	optionSeries.setName("员工");
	optionSeries.setType("pie");
	optionSeries.setRadius("55%");
	optionSeries.setCenter(new String[]{"40%", "50%"});
	Emphasis emphasis = new Emphasis();
	ItemStyle itemStyle = new ItemStyle();
	itemStyle.setShadowBlur(10);
	itemStyle.setShadowOffsetX(0);
	itemStyle.setShadowColor("rgba(0, 0, 0, 0.5)");
	emphasis.setItemStyle(itemStyle);
	optionSeries.setData(dataArr);
	optionSeriesArr[0] = optionSeries;
	optionConfig.setSeries(optionSeriesArr);
	optionConfig = pieOption.getPieLegendChartOption(legendDatas,selected,optionSeriesArr);
	OptionTitle optionTitle = new OptionTitle();
	optionTitle.setText("员工报价单统计");
	optionTitle.setSubtext("员工报价单占比");
	optionTitle.setLeft("center");
	optionConfig.setTitle(optionTitle);
	return optionConfig;
}

/**
 * 
 * @Title: getBiQuotationByAccountPie   
 * @Description: TODO 获取报价单人员占比
 * @param orgId
 * @return
 * List<Map<String,String>>
 */
public List<Map<String, String>> getBiQuotationByAccountPie(String orgId)
{
	return echartsCrmMapper.getBiQuotationByAccountPie(orgId);
}
/**
 * 
 * @Title: getBiInquiryByDeptPie   
 * @Description: TODO 部门询价单占比前10的占比
 * @param account
 * @return
 * OptionConfig    

 */
public OptionConfig getBiInquiryByDeptPie(Account account)
{
	OptionConfig optionConfig = new OptionConfig();
	List<Map<String, String>> resdataList = getBiInquiryByDeptPie(account.getOrgId());
	OptionSeries[] optionSeriesArr = new OptionSeries[1];
	SeriesData[] dataArr = new SeriesData[resdataList.size()];
	int selectedLeng=0;
	if(dataArr.length>=10)
	{
		selectedLeng=10;
	}else
	{
		selectedLeng = dataArr.length;
	}
	String[] selected = new String[selectedLeng];
	LegendData[] legendDatas = new LegendData[dataArr.length];
	for(int i=0;i<dataArr.length;i++)
	{
		if(StringUtils.isBlank(resdataList.get(i).get("name")))
		{
			resdataList.get(i).put("name", "other"+i);
		}
		if(i<selectedLeng)
		{
			selected[i]=resdataList.get(i).get("name").toString();
		}
		LegendData legendData = new LegendData();
		legendData.setName(resdataList.get(i).get("name").toString());
		legendDatas[i]=legendData;
		SeriesData seriesData = new SeriesData();
		seriesData.setName(resdataList.get(i).get("name").toString());
		seriesData.setValue(Double.valueOf(String.valueOf(resdataList.get(i).get("value"))));
		dataArr[i]=seriesData;
	}
	OptionSeries optionSeries = new OptionSeries();
	optionSeries.setName("人员部门");
	optionSeries.setType("pie");
	optionSeries.setRadius("55%");
	optionSeries.setCenter(new String[]{"40%", "50%"});
	Emphasis emphasis = new Emphasis();
	ItemStyle itemStyle = new ItemStyle();
	itemStyle.setShadowBlur(10);
	itemStyle.setShadowOffsetX(0);
	itemStyle.setShadowColor("rgba(0, 0, 0, 0.5)");
	emphasis.setItemStyle(itemStyle);
	optionSeries.setData(dataArr);
	optionSeriesArr[0] = optionSeries;
	optionConfig.setSeries(optionSeriesArr);
	optionConfig = pieOption.getPieLegendChartOption(legendDatas,selected,optionSeriesArr);
	OptionTitle optionTitle = new OptionTitle();
	optionTitle.setText("部门询价单统计");
	optionTitle.setSubtext("部门询价单占比");
	optionTitle.setLeft("center");
	optionConfig.setTitle(optionTitle);
	return optionConfig;
}
public List<Map<String, String>> getBiInquiryByDeptPie(String orgId)
{
	return echartsCrmMapper.getBiInquiryByDeptPie(orgId);
}

/**
 * 
 * @Title: getBiInqueryByProductPie   
 * @Description: TODO 获取产品分类前10的占比
 * @param account
 * @return
 * OptionConfig    

 */
public OptionConfig getBiInquiryByProductPie(Account account)
{
	OptionConfig optionConfig = new OptionConfig();
	List<Map<String, String>> resdataList = getBiInquiryByProductPie(account.getOrgId());
	OptionSeries[] optionSeriesArr = new OptionSeries[1];
	SeriesData[] dataArr = new SeriesData[resdataList.size()];
	int selectedLeng=0;
	if(dataArr.length>=10)
	{
		selectedLeng=10;
	}else
	{
		selectedLeng = dataArr.length;
	}
	String[] selected = new String[selectedLeng];
	LegendData[] legendDatas = new LegendData[dataArr.length];
	for(int i=0;i<dataArr.length;i++)
	{
		if(StringUtils.isBlank(resdataList.get(i).get("name")))
		{
			resdataList.get(i).put("name", "other"+i);
		}
		if(i<selectedLeng)
		{
			selected[i]=resdataList.get(i).get("name").toString();
		}
		LegendData legendData = new LegendData();
		legendData.setName(resdataList.get(i).get("name").toString());
		legendDatas[i]=legendData;
		SeriesData seriesData = new SeriesData();
		seriesData.setName(resdataList.get(i).get("name").toString());
		seriesData.setValue(Double.valueOf(String.valueOf(resdataList.get(i).get("value"))));
		dataArr[i]=seriesData;
	}
	OptionSeries optionSeries = new OptionSeries();
	optionSeries.setName("询价产品分类");
	optionSeries.setType("pie");
	optionSeries.setRadius("55%");
	optionSeries.setCenter(new String[]{"40%", "50%"});
	Emphasis emphasis = new Emphasis();
	ItemStyle itemStyle = new ItemStyle();
	itemStyle.setShadowBlur(10);
	itemStyle.setShadowOffsetX(0);
	itemStyle.setShadowColor("rgba(0, 0, 0, 0.5)");
	emphasis.setItemStyle(itemStyle);
	optionSeries.setData(dataArr);
	optionSeriesArr[0] = optionSeries;
	optionConfig.setSeries(optionSeriesArr);
	optionConfig = pieOption.getPieLegendChartOption(legendDatas,selected,optionSeriesArr);
	OptionTitle optionTitle = new OptionTitle();
	optionTitle.setText("产品分类总数统计");
	optionTitle.setSubtext("产品分类总数占比");
	optionTitle.setLeft("center");
	optionConfig.setTitle(optionTitle);
	return optionConfig;
}

public List<Map<String, String>> getBiInquiryByProductPie(String orgId)
{
	return echartsCrmMapper.getBiInquiryByProductPie(orgId);
}

/**
 * 
 * @Title: getBiInquiryByMonthLine   
 * @Description: TODO 按月份统计工作量
 * @param account
 * @return
 * OptionConfig
 */
public OptionConfig getBiInquiryByMonthLine(Account account)
{
	OptionConfig optionConfig = new OptionConfig();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    Date y = c.getTime();
    String endTime = format.format(y);
    c.add(Calendar.YEAR, -1);
    y = c.getTime();
    String beginTime = format.format(y);
	List<Map<String, Object>> resList = getBiInquiryByMonthLine(account.getOrgId(), beginTime, endTime);
	String [] xAxisData = new String[resList.size()];
	Double[] resData = new Double[resList.size()];
	for(int i=0;i<resList.size();i++)
	{
		xAxisData[i]=resList.get(i).get("createTime").toString();
		resData[i]=Double.valueOf(resList.get(i).get("total").toString());
	}
	optionConfig = lineOption.getBasicLineChartOption(xAxisData, resData);
	return optionConfig;
}
public List<Map<String, Object>> getBiInquiryByMonthLine(String orgId,String beginTime, String endTime)
{
	return echartsCrmMapper.getBiInquiryByMonthLine(orgId,beginTime, endTime);
}
/**
 * 
 * @Title: getBiInquiryByAccountPie   
 * @Description: TODO 获取询价单人员占比
 * @param account
 * @return
 * OptionConfig
 */
public OptionConfig getBiInquiryByAccountPie(Account account)
{
	OptionConfig optionConfig = new OptionConfig();
	List<Map<String, String>> resdataList = getBiInquiryByAccountPie(account.getOrgId());
	OptionSeries[] optionSeriesArr = new OptionSeries[1];
	SeriesData[] dataArr = new SeriesData[resdataList.size()];
	int selectedLeng=0;
	if(dataArr.length>=10)
	{
		selectedLeng=10;
	}else
	{
		selectedLeng = dataArr.length;
	}
	String[] selected = new String[selectedLeng];
	LegendData[] legendDatas = new LegendData[dataArr.length];
	for(int i=0;i<dataArr.length;i++)
	{
		if(StringUtils.isBlank(resdataList.get(i).get("name")))
		{
			resdataList.get(i).put("name", "other"+i);
		}
		if(i<selectedLeng)
		{
			selected[i]=resdataList.get(i).get("name").toString();
		}
		LegendData legendData = new LegendData();
		legendData.setName(resdataList.get(i).get("name").toString());
		legendDatas[i]=legendData;
		SeriesData seriesData = new SeriesData();
		seriesData.setName(resdataList.get(i).get("name").toString());
		seriesData.setValue(Double.valueOf(String.valueOf(resdataList.get(i).get("value"))));
		dataArr[i]=seriesData;
	}
	OptionSeries optionSeries = new OptionSeries();
	optionSeries.setName("员工");
	optionSeries.setType("pie");
	optionSeries.setRadius("55%");
	optionSeries.setCenter(new String[]{"40%", "50%"});
	Emphasis emphasis = new Emphasis();
	ItemStyle itemStyle = new ItemStyle();
	itemStyle.setShadowBlur(10);
	itemStyle.setShadowOffsetX(0);
	itemStyle.setShadowColor("rgba(0, 0, 0, 0.5)");
	emphasis.setItemStyle(itemStyle);
	optionSeries.setData(dataArr);
	optionSeriesArr[0] = optionSeries;
	optionConfig.setSeries(optionSeriesArr);
	optionConfig = pieOption.getPieLegendChartOption(legendDatas,selected,optionSeriesArr);
	OptionTitle optionTitle = new OptionTitle();
	optionTitle.setText("员工询价单统计");
	optionTitle.setSubtext("员工询价单占比");
	optionTitle.setLeft("center");
	optionConfig.setTitle(optionTitle);
	return optionConfig;
}

/**
 * 
 * @Title: getBiInquiryByAccountPie   
 * @Description: TODO 获取询价单人员占比
 * @param orgId
 * @return
 * List<Map<String,String>>
 */
public List<Map<String, String>> getBiInquiryByAccountPie(String orgId)
{
	return echartsCrmMapper.getBiInquiryByAccountPie(orgId);
}

/**
 * 
 * @Title: getBiCustomerLevelPie   
 * @Description: TODO 获取CRM客户等级占比
 * @param account
 * @return
 * OptionConfig    

 */
public OptionConfig getBiCustomerLevelPie(Account account)
{
	OptionConfig optionConfig = new OptionConfig();
	List<Map<String, String>> resdataList = getBiCustomerLevelPie(account.getOrgId());
	OptionSeries[] optionSeriesArr = new OptionSeries[1];
	SeriesData[] dataArr = new SeriesData[resdataList.size()];
	int selectedLeng=0;
	if(dataArr.length>=10)
	{
		selectedLeng=10;
	}else
	{
		selectedLeng = dataArr.length;
	}
	String[] selected = new String[selectedLeng];
	LegendData[] legendDatas = new LegendData[dataArr.length];
	for(int i=0;i<dataArr.length;i++)
	{
		if(StringUtils.isBlank(resdataList.get(i).get("name")))
		{
			resdataList.get(i).put("name", "other"+i);
		}
		if(i<selectedLeng)
		{
			selected[i]=resdataList.get(i).get("name").toString();
		}
		LegendData legendData = new LegendData();
		legendData.setName(resdataList.get(i).get("name").toString());
		legendDatas[i]=legendData;
		SeriesData seriesData = new SeriesData();
		seriesData.setName(resdataList.get(i).get("name").toString());
		seriesData.setValue(Double.valueOf(String.valueOf(resdataList.get(i).get("value"))));
		dataArr[i]=seriesData;
	}
	OptionSeries optionSeries = new OptionSeries();
	optionSeries.setName("等级");
	optionSeries.setType("pie");
	optionSeries.setRadius("55%");
	optionSeries.setCenter(new String[]{"40%", "50%"});
	Emphasis emphasis = new Emphasis();
	ItemStyle itemStyle = new ItemStyle();
	itemStyle.setShadowBlur(10);
	itemStyle.setShadowOffsetX(0);
	itemStyle.setShadowColor("rgba(0, 0, 0, 0.5)");
	emphasis.setItemStyle(itemStyle);
	optionSeries.setData(dataArr);
	optionSeriesArr[0] = optionSeries;
	optionConfig.setSeries(optionSeriesArr);
	optionConfig = pieOption.getPieLegendChartOption(legendDatas,selected,optionSeriesArr);
	OptionTitle optionTitle = new OptionTitle();
	optionTitle.setText("客户等级数据统计");
	optionTitle.setSubtext("客户等级占比");
	optionTitle.setLeft("center");
	optionConfig.setTitle(optionTitle);
	return optionConfig;
}
/**
 * 
 * @Title: getBiCustomerLevelPie   
 * @Description: TODO 获取CRM客户等级占比
 * @param orgId
 * @return
 * List<Map<String,String>>
 */
public List<Map<String, String>> getBiCustomerLevelPie(String orgId)
{
	return echartsCrmMapper.getBiCustomerLevelPie(orgId);
}

/**
 * 
 * @Title: getBiCustomerAreaPie   
 * @Description: TODO  获取CRM地区占比
 * @param account
 * @return
 * OptionConfig    

 */
public OptionConfig getBiCustomerAreaPie(Account account)
{
	OptionConfig optionConfig = new OptionConfig();
	List<Map<String, String>> resdataList = getBiCustomerAreaPie(account.getOrgId());
	OptionSeries[] optionSeriesArr = new OptionSeries[1];
	SeriesData[] dataArr = new SeriesData[resdataList.size()];
	int selectedLeng=0;
	if(dataArr.length>=10)
	{
		selectedLeng=10;
	}else
	{
		selectedLeng = dataArr.length;
	}
	String[] selected = new String[selectedLeng];
	LegendData[] legendDatas = new LegendData[dataArr.length];
	for(int i=0;i<dataArr.length;i++)
	{
		if(StringUtils.isBlank(resdataList.get(i).get("name")))
		{
			resdataList.get(i).put("name", "other"+i);
		}
		if(i<selectedLeng)
		{
			selected[i]=resdataList.get(i).get("name").toString();
		}
		LegendData legendData = new LegendData();
		legendData.setName(resdataList.get(i).get("name").toString());
		legendDatas[i]=legendData;
		SeriesData seriesData = new SeriesData();
		seriesData.setName(resdataList.get(i).get("name").toString());
		seriesData.setValue(Double.valueOf(String.valueOf(resdataList.get(i).get("value"))));
		dataArr[i]=seriesData;
	}
	OptionSeries optionSeries = new OptionSeries();
	optionSeries.setName("地区");
	optionSeries.setType("pie");
	optionSeries.setRadius("55%");
	optionSeries.setCenter(new String[]{"40%", "50%"});
	Emphasis emphasis = new Emphasis();
	ItemStyle itemStyle = new ItemStyle();
	itemStyle.setShadowBlur(10);
	itemStyle.setShadowOffsetX(0);
	itemStyle.setShadowColor("rgba(0, 0, 0, 0.5)");
	emphasis.setItemStyle(itemStyle);
	optionSeries.setData(dataArr);
	optionSeriesArr[0] = optionSeries;
	optionConfig.setSeries(optionSeriesArr);
	optionConfig = pieOption.getPieLegendChartOption(legendDatas,selected,optionSeriesArr);
	OptionTitle optionTitle = new OptionTitle();
	optionTitle.setText("客户地区数据统计");
	optionTitle.setSubtext("客户地区数量占比");
	optionTitle.setLeft("center");
	optionConfig.setTitle(optionTitle);
	return optionConfig;
}
/**
 * 
 * @Title: getBiCustomerAreaPie   
 * @Description: TODO  获取CRM地区占比
 * @param orgId
 * @return
 * List<Map<String,String>>
 */
public List<Map<String, String>> getBiCustomerAreaPie(String orgId)
{
	return echartsCrmMapper.getBiCustomerAreaPie(orgId);
}


/**
 * 
 * @Title: getBiCustomerKeepUserPie   
 * @Description: TODO 获取CRM销售人员的占比
 * @param account
 * @return
 * OptionConfig    

 */
public OptionConfig getBiCustomerKeepUserPie(Account account)
{
	OptionConfig optionConfig = new OptionConfig();
	List<Map<String, String>> resdataList = getBiCustomerKeepUserPie(account.getOrgId());
	OptionSeries[] optionSeriesArr = new OptionSeries[1];
	SeriesData[] dataArr = new SeriesData[resdataList.size()];
	int selectedLeng=0;
	if(dataArr.length>=10)
	{
		selectedLeng=10;
	}else
	{
		selectedLeng = dataArr.length;
	}
	String[] selected = new String[selectedLeng];
	LegendData[] legendDatas = new LegendData[dataArr.length];
	for(int i=0;i<dataArr.length;i++)
	{
		if(StringUtils.isBlank(resdataList.get(i).get("name")))
		{
			resdataList.get(i).put("name", "other"+i);
		}
		if(i<selectedLeng)
		{
			selected[i]=resdataList.get(i).get("name").toString();
		}
		LegendData legendData = new LegendData();
		legendData.setName(resdataList.get(i).get("name").toString());
		legendDatas[i]=legendData;
		SeriesData seriesData = new SeriesData();
		seriesData.setName(resdataList.get(i).get("name").toString());
		seriesData.setValue(Double.valueOf(String.valueOf(resdataList.get(i).get("value"))));
		dataArr[i]=seriesData;
	}
	OptionSeries optionSeries = new OptionSeries();
	optionSeries.setName("业务员");
	optionSeries.setType("pie");
	optionSeries.setRadius("55%");
	optionSeries.setCenter(new String[]{"40%", "50%"});
	Emphasis emphasis = new Emphasis();
	ItemStyle itemStyle = new ItemStyle();
	itemStyle.setShadowBlur(10);
	itemStyle.setShadowOffsetX(0);
	itemStyle.setShadowColor("rgba(0, 0, 0, 0.5)");
	emphasis.setItemStyle(itemStyle);
	optionSeries.setData(dataArr);
	optionSeriesArr[0] = optionSeries;
	optionConfig.setSeries(optionSeriesArr);
	optionConfig = pieOption.getPieLegendChartOption(legendDatas,selected,optionSeriesArr);
	OptionTitle optionTitle = new OptionTitle();
	optionTitle.setText("业务员客户数量统计");
	optionTitle.setSubtext("客户数量数据占比");
	optionTitle.setLeft("center");
	optionConfig.setTitle(optionTitle);
	return optionConfig;
}
/**
 * 
 * @Title: getBiCustomerAreaPie   
 * @Description: TODO 获取CRM销售人员的占比
 * @param orgId
 * @return
 * List<Map<String,String>>
 */
public List<Map<String, String>> getBiCustomerKeepUserPie(String orgId)
{
	return echartsCrmMapper.getBiCustomerKeepUserPie(orgId);
}

/**
 * 
 * @Title: getARAPOptionConfig   
 * @Description: TODO 获取财务门户的收支
 * @param account
 * @return
 * OptionConfig    

 */
public OptionConfig getBiCustomerIndustryPie(Account account)
{
	
	OptionConfig optionConfig = new OptionConfig();
	List<Map<String, String>> resdataList = getBiCustomerIndustryPie(account.getOrgId());
	OptionSeries[] optionSeriesArr = new OptionSeries[1];
	SeriesData[] dataArr = new SeriesData[resdataList.size()];
	int selectedLeng=0;
	if(dataArr.length>=10)
	{
		selectedLeng=10;
	}else
	{
		selectedLeng = dataArr.length;
	}
	String[] selected = new String[selectedLeng];
	LegendData[] legendDatas = new LegendData[dataArr.length];
	for(int i=0;i<dataArr.length;i++)
	{
		if(StringUtils.isBlank(resdataList.get(i).get("name")))
		{
			resdataList.get(i).put("name", "other"+i);
		}
		if(i<selectedLeng)
		{
			selected[i]=resdataList.get(i).get("name").toString();
		}
		LegendData legendData = new LegendData();
		legendData.setName(resdataList.get(i).get("name").toString());
		legendDatas[i]=legendData;
		SeriesData seriesData = new SeriesData();
		seriesData.setName(resdataList.get(i).get("name").toString());
		seriesData.setValue(Double.valueOf(String.valueOf(resdataList.get(i).get("value"))));
		dataArr[i]=seriesData;
	}
	OptionSeries optionSeries = new OptionSeries();
	optionSeries.setName("行业");
	optionSeries.setType("pie");
	optionSeries.setRadius("55%");
	optionSeries.setCenter(new String[]{"40%", "50%"});
	Emphasis emphasis = new Emphasis();
	ItemStyle itemStyle = new ItemStyle();
	itemStyle.setShadowBlur(10);
	itemStyle.setShadowOffsetX(0);
	itemStyle.setShadowColor("rgba(0, 0, 0, 0.5)");
	emphasis.setItemStyle(itemStyle);
	optionSeries.setData(dataArr);
	optionSeriesArr[0] = optionSeries;
	optionConfig.setSeries(optionSeriesArr);
	optionConfig = pieOption.getPieLegendChartOption(legendDatas,selected,optionSeriesArr);
	OptionTitle optionTitle = new OptionTitle();
	optionTitle.setText("客户行业数据统计");
	optionTitle.setSubtext("客户行业数据占比");
	optionTitle.setLeft("center");
	optionConfig.setTitle(optionTitle);
	return optionConfig;
}
/**
 * 
 * @Title: getBiCustomerIndustryPie   
 * @Description: TODO 获取财务门户的收支
 * @param orgId
 * @return
 * List<Map<String,String>>
 */
public List<Map<String, String>> getBiCustomerIndustryPie(String orgId)
{
	return echartsCrmMapper.getBiCustomerIndustryPie(orgId);
}
}
