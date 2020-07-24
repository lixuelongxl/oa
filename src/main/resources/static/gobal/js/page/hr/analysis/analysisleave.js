var myChart;
$(function(){
	$(".js-query").unbind("click").click(function(){
		createeCharts();
	})
})
function createeCharts()
{
	if(myChart!=undefined)
	{
		myChart.clear();
	}
	$("#dataDiv").html("<div id=\"main1\" style=\"height: 400px;\" class=\"col-sm-12\"></div>")
	var dataType=$("input:radio[name='dataType']:checked").val();
	var echartType=$("input:radio[name='echartType']:checked").val()
	var deptId=$("#deptId").attr("data-value");
	if(dataType=="1")
	{
		$("#echartTitle").html("离职类型");
	}else if(dataType=="2")
	{
		$("#echartTitle").html("按部门分析");
	}
	if(echartType=="1")
	{
		getLeavePieForAnalysis(dataType,deptId);
	}else if(echartType=="2")
	{
		getLeaveBarForAnalysis(dataType,deptId);
	}else if(echartType=="3")
	{
		getLeaveTableForAnalysis(dataType,deptId)
	}
		
}

function getLeaveTableForAnalysis(dataType,deptId)
{
	$.ajax({
		url : "/ret/echartshrget/getLeaveTableForAnalysis",
		type : "post",
		dataType : "json",
		data:{dataType:dataType,deptId:deptId},
		success : function(data) {
			if(data.status=="200")
			{
				var recordInfo = data.list;
				var html="<table class=\"table table-hover table-striped table-bordered\">";
				for(var i=0;i<recordInfo.length;i++)
				{
					html+="<tr>";
					html+="<td width='200px;'>"+(recordInfo[i].name==undefined?("other"+i):recordInfo[i].name)+"</td>";
					html+="<td>"+recordInfo[i].value+"</td>";
					html+="</tr>";
				}
				html+="</table>";
				$("#main1").html(html);
			}else if(data.status=="100")
			{
				top.layer.msg(data.msg);
			}else
			{
				console.log(data.list);
			}
		}
	})
}

function getLeavePieForAnalysis(dataType,deptId)
{
	$.ajax({
		url : "/ret/echartshrget/getLeavePieForAnalysis",
		type : "post",
		dataType : "json",
		data:{dataType:dataType,deptId:deptId},
		success : function(data) {
			if(data.status=="200")
			{
				var option = data.list;
				myChart = echarts.init(document.getElementById('main1'));
				myChart.setOption(option);
				window.addEventListener("resize",function(){
				    myChart.resize();
				});
			}else if(data.status=="100")
			{
				top.layer.msg(data.msg);
			}else
			{
				console.log(data.list);
			}
		}
	})
}
function getLeaveBarForAnalysis(dataType,deptId)
{
	$.ajax({
		url : "/ret/echartshrget/getLeaveBarForAnalysis",
		type : "post",
		dataType : "json",
		data:{dataType:dataType,deptId:deptId},
		success : function(data) {
			if(data.status=="200")
			{
				var option = data.list;
				myChart = echarts.init(document.getElementById('main1'));
				myChart.setOption(option);
				window.addEventListener("resize",function(){
				    myChart.resize();
				});
			}else if(data.status=="100")
			{
				top.layer.msg(data.msg);
			}else
			{
				console.log(data.list);
			}
		}
	})
}