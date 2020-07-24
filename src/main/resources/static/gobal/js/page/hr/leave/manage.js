$(function() {
	QuerySelect("leaveType");
	jeDate("#beginTimeQuery", {
		format : "YYYY-MM-DD"
	});
	jeDate("#endTimeQuery", {
		format : "YYYY-MM-DD"
	});
	query();
	$(".js-simple-query").unbind("click").click(function(){
		$("#myTable").bootstrapTable("refresh");
	})
		jeDate("#lastSalaryTime", {
		format: "YYYY-MM-DD",
		maxDate:getSysDate()
	});
	jeDate("#applyTime", {
		format: "YYYY-MM-DD"
	});
	jeDate("#planTime", {
		format: "YYYY-MM-DD"
	});
	jeDate("#factTime", {
		format: "YYYY-MM-DD"
	});
	$('#remark').summernote({ height:300 });
	$(".js-auto-select").each(function(){
		var module = $(this).attr("module");
		createAutoSelect(module);
	})

})

function query() {
	$("#myTable").bootstrapTable({
		url : '/ret/hrget/getHrLeaveRecordList',
		method : 'post',
		contentType : 'application/x-www-form-urlencoded',
		toolbar : '#toobar',//工具列
		striped : true,//隔行换色
		cache : false,//禁用缓存
		pagination : true,//启动分页
		sidePagination : 'server',//分页方式
		pageNumber : 1,//初始化table时显示的页码
		pageSize : 10,//每页条目
		showFooter : false,//是否显示列脚
		showPaginationSwitch : true,//是否显示 数据条数选择框
		sortable : true,//排序
		search : true,//启用搜索
		sortOrder: "asc",
		showColumns : true,//是否显示 内容列下拉框
		showRefresh : true,//显示刷新按钮
		idField : 'recordId',//key值栏位
		clickToSelect : true,//点击选中checkbox
		pageList : [ 10, 20, 30, 50 ],//可选择单页记录数
		queryParams : queryParams,
		columns : [ {
			checkbox : true
		}, {
			field : 'num',
			title : '序号',//标题  可不加
			width : '50px',
			formatter : function(value, row, index) {
				return index + 1;
			}
		},{
			field : 'userId',
			title : '人员姓名',
			sortable : true,
			width : '80px',
			formatter : function(value, row, index) {
				if (row.userName == "" || row.userName == null) {
					return getHrUserNameByStr(row.userId);
				} else {
					return row.userName;
				}
			}
		}, {
			field : 'deptId',
			width : '80px',
			title : '部门名称',
			formatter : function(value, row, index) {
				return getHrDeptNameByStr(value);
			}
		},{
			field : 'post',
			width : '50px',
			title : '职务',
			formatter : function(value, row, index) {
				return getHrUserLevelByStr(value);
			}
		},{
			field : 'leaveType',
			width : '100px',
			title : '离职类型',
			formatter : function(value, row, index) {
				return getHrClassCodeName("leaveType",value);
			}
		}, {
			field : 'lastSalaryTime',
			width : '50px',
			title : '停薪日期'
		},{
			field : 'applyTime',
			title : '申请离职日期',
			width : '100px'
		}, {
			field : 'factTime',
			width : '50px',
			title : '离职日期'
		},  {
			field : 'opt',
			title : '操作',
			align : 'center',
			width : '120px',
			formatter : function(value, row, index) {
				return createOptBtn(row.recordId);
			}
		} ],
		onClickCell : function(field, value, row, $element) {
			//alert(row.SystemDesc);
		},
		responseHandler : function(res) {
			if (res.status == "500") {
				top.layer.msg(res.msg);
			} else {
				return {
					total : res.list.total, //总页数,前面的key必须为"total"
					rows : res.list.list
				//行数据，前面的key要与之前设置的dataField的值一致.
				};
			}
		}
	});
}

function queryParams(params) {
	var temp = {
		search : params.search,
		pageSize : this.pageSize,
		pageNumber : this.pageNumber,
		sort : params.sort,
		sortOrder : params.order,
		userId : $("#userIdQuery").attr("data-value"),
		endTime : $("#endTimeQuery").val(),
		beginTime : $("#beginTimeQuery").val(),
		leaveType : $("#leaveTypeQuery").val()
	};
	return temp;
};
function createOptBtn(recordId) {
	var html = "<a href=\"javascript:void(0);edit('" + recordId + "')\" class=\"btn btn-primary btn-xs\">编辑</a>&nbsp;&nbsp;" 
	+ "<a href=\"javascript:void(0);deleteReocrd('" + recordId + "')\" class=\"btn btn-darkorange btn-xs\" >删除</a>&nbsp;&nbsp;";
	html += "<a href=\"javascript:void(0);details('" + recordId + "')\" class=\"btn btn-sky btn-xs\" >详情</a>";
	return html;
}

function deleteReocrd(recordId)
{
	if(confirm("确定删除当前记录吗？"))
    {
	$.ajax({
		url : "/set/hrset/deleteHrLeaveRecord",
		type : "post",
		dataType : "json",
		data:{recordId:recordId},
		success : function(data) {
			if(data.status=="200")
			{
				top.layer.msg(data.msg);
				$("#myTable").bootstrapTable("refresh");
			}else if(data.status=="100")
			{
				top.layer.msg(data.msg);
			}else
			{
				console.log(data.msg);
			}
		}
	});
    }
}
function edit(recordId)
{
	$("#leavelistdiv").hide();
	$("#leavediv").show();
	$(".js-back-btn").unbind("click").click(function(){
		goback();
	})
	$.ajax({
		url : "/ret/hrget/getHrLeaveRecordById",
		type : "post",
		dataType : "json",
		data:{recordId:recordId},
		success : function(data) {
			if(data.status=="200")
			{
				var workInfo = data.list;
				for(var id in workInfo)
				{
					if(id=="attach")
					{
						$("#show_hrattach").html("");
						$("#hrattach").attr("data_value", workInfo.attach);
						createAttach("hrattach", 4);
					}else if(id=="userId")
					{
						$("#"+id).val(getHrUserNameByStr(workInfo[id]));
						$("#"+id).attr("data-value",workInfo[id]);
					}else if(id=="remark")
					{
						$("#remark").code(workInfo[id]);
					}else
					{
						$("#"+id).val(workInfo[id]);
					}
				}
				$(".js-update-save").unbind("click").click(function(){
					updateHrWorkRecord(recordId);
				})
			}else if(data.status=="100")
			{
				top.layer.msg(data.msg);
			}else if(data.status=="500")
			{
				console.log(data.msg);
			}
		}
		})
}
function details(recordId)
{
	window.open("/app/core/hr/leavedetails?recordId="+recordId);
}
function updateHrWorkRecord(recordId)
{
	$.ajax({
		url : "/set/hrset/updateHrLeaveRecord",
		type : "post",
		dataType : "json",
		data:{
			recordId:recordId,
			sortNo:$("#sortNo").val(),
			deptId:$("#deptId").attr("data-value"),
			userId:$("#userId").attr("data-value"),
			post:$("#post").attr("data-value"),
			leaveType:$("#leaveType").val(),
			lastSalaryTime:$("#lastSalaryTime").val(),
			applyTime:$("#applyTime").val(),
			planTime:$("#planTime").val(),
			factTime:$("#factTime").val(),
			factTime:$("#factTime").val(),
			salary:$("#salary").val(),
			trace:$("#trace").val(),
			leaveCondition:$("#leaveCondition").val(),
			attach:$("#hrattach").attr("data_value"),
			remark:$("#remark").code()
		},
		success : function(data) {
			if(data.status=="200")
			{
				top.layer.msg(data.msg);
				location.reload();
			}else if(data.status=="100")
			{
				top.layer.msg(data.msg);
			}else if(data.status=="500")
			{
				console.log(data.msg);
			}
		}
	})
}
function goback()
{
	$("#leavediv").hide();
	$("#leavelistdiv").show();
}