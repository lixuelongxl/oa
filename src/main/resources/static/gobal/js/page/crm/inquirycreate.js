var tableData = [];
$(function() {
	jeDate("#endTime", {
		format : "YYYY-MM-DD"
	});
	var inquiryId = $("#inquiryId").val();
	if (inquiryId == "") {
		getSelect2();
		$("#upatebtn").remove();
		$("#backbtn").remove();
		$("#savebtn").unbind("click").click(function() {
			createInquiry();
		});
		query();
	} else {
		$("#customerId").addClass("form-control");
		$("#savebtn").remove();
		$("#upatebtn").unbind("click").click(function() {
			updateInquiry();
		});
		getInquiryInfo();
		query();
	}
});

function getInquiryInfo()
{
	$.ajax({
		url : "/ret/crmget/getCrmInquiry",
		type : "post",
		dataType : "json",
		data:{
			inquiryId:$("#inquiryId").val()
		},
		success : function(data) {
			if(data.status==200)
			{
				var info = data.list;
				for(var name in info)
					{
					if(name=="inquiryCode")
						{
							$("#inquiryCodeSpan").html(info[name]);
						}else if(name=="attach")
						{
							$("#attach").attr("data_value", info[name]);
							createAttach("attach",4);
						}else if(name=="packCharges")
						{
							$("input:radio[name='packCharges'][value='"+info[name]+"']").attr("checked","checked");
						}else if(name=="taxFlag")
						{
							$("input:radio[name='taxFlag'][value='"+info[name]+"']").attr("checked","checked");
						}else if(name=="invoiceFlag")
						{
							$("input:radio[name='invoiceFlag'][value='"+info[name]+"']").attr("checked","checked");
						}else if(name=="customerType")
						{
							$("input:radio[name='customerType'][value='"+info[name]+"']").attr("checked","checked");
						}else if(name=="customerId")
						{
							$("#"+name).val(info[name]);
							
						}else
						{
							$("#"+name).val(info[name]);
						}
					}
				
			//	top.layer.msg(data.msg);
				
			}else if(data.status==100)
			{
				top.layer.msg(data.msg);
			}else
				{
				console.log(data.msg);
				}
		}
	});
}


function query() {
	var inquiryId = $("#inquiryId").val()
	$("#myTable").bootstrapTable({
		url : '/ret/crmget/getCrmInquiryDetailList?inquiryId='+inquiryId,
		method : 'post',
		contentType : 'application/x-www-form-urlencoded',
		toolbar : '#toobar',//?????????
		striped : true,//????????????
		cache : false,//????????????
		pagination : true,//????????????
		sidePagination : 'server',//????????????
		pageNumber : 1,//?????????table??????????????????
		pageSize : 10,//????????????
		showFooter : false,//??????????????????
		showPaginationSwitch : true,//???????????? ?????????????????????
		sortable : true,//??????
		search : false,//????????????
		showColumns : true,//???????????? ??????????????????
		showRefresh : true,//??????????????????
		idField : 'detailId',//key?????????
		clickToSelect : true,//????????????checkbox
		pageList : [ 10, 20, 30, 50 ],//????????????????????????
		queryParams : queryParams,
		columns : [ {
			checkbox : true
		}, {
			field : 'num',
			title : '??????',//??????  ?????????
			width : '30px',
			formatter : function(value, row, index) {
				return index + 1;
			}
		}, {
			field : 'productId',
			title : '????????????',
			visible : true,
			width : '200px'
		},{
			field : 'materielCode',
			title : '????????????',
			visible : true,
			width : '200px'
		},  {
			field : 'productName',
			title : '????????????',
			width : '200px'
		}, {
			field : 'model',
			title : '????????????',
			width : '50px'
		}, {
			field : 'count',
			width : '50px',
			title : '??????'
		}, {
			field : 'unit',
			width : '50px',
			title : '??????'
		}, {
			field : 'delivery',
			width : '50px',
			sortable : true,
			title : '?????????'
		}, {
			field : 'remark',
			width : '150px',
			title : '??????'
		}, {
			field : 'opt',
			title : '??????',
			align:'center',
			width : '100px',
			formatter : function(value, row, index) {
				if(value!='<a onclick="saveok('+index+')" class="btn btn-magenta btn-xs" >??????</a>')
				{
					return createOptBtn(index);
				}else
				{
					return value;
				}
				}
		} ],
		onClickCell : function(field, value, row, $element) {
			//alert(row.SystemDesc);
		},
		responseHandler : function(res) {
			if (res.status == "500") {
				console.log(res.msg);
				top.layer.msg(res.msg);
			} else {
				return {
					total : res.list.total, //?????????,?????????key?????????"total"
					rows : res.list.list
				//?????????????????????key?????????????????????dataField????????????.
				};
			}
		}
	});
}
function createOptBtn(index) {
	return html = "<a onclick=\"delrows(" + index + ")\" class=\"btn btn-darkorange btn-xs\" >??????</a>";
}

function addRow() {
	var count = $('#myTable').bootstrapTable('getData').length;
	if($("#productId" + (count - 1)).length<1)
	{
	if (count != 0 && $("#productId" + (count - 1)).length>0) {
				var row = {};
				row.num = count - 1;
				row.productId = $("#productId" + (count - 1)).val();
				row.materielCode = $("#materielCode" + (count - 1)).text();
				row.productName = $("#productName" + (count - 1)).text();
				row.model = $("#model" + (count - 1)).text();
				row.unit = $("#unit" + (count - 1)).text();
				row.count = $("#count" + (count - 1)).val();
				row.delivery = $("#delivery" + (count - 1)).val();
				row.remark = $("#remark" + (count - 1)).val();
				row.opt = createOptBtn(count - 1);
				tableData.push(row);
				$("#myTable").bootstrapTable('load', tableData);
	}
	var _data = {
		num : count,
		materielCode : "<div id='materielCode" + count + "' name='materielCode'></div>",
		productId : "<div id='productId" + count + "' name='productId'></div>",
		productName : "<input type='hidden' style='width:100%' id='productName" + count + "' name='productName'/>",
		model : "<div id='model" + count + "' name='model'></div>",
		count : "<input type='text' class='form-control' id='count" + count + "' name='count'/>",
		unit : "<div id='unit" + count + "' name='unit'></div>",
		delivery : "<input type='text' class='form-control' id='delivery" + count + "' name='delivery' readonly='readonly'/>",
		remark : "<input type='text' class='form-control' id='remark" + count + "' name='remark'/>",
		opt : "<a onclick=\"saveok(" +count+ ")\" class=\"btn btn-magenta btn-xs\" >??????</a>"
	}
	$("#myTable").bootstrapTable('append', _data)
	jeDate("#delivery" + count, {
		format : "YYYY-MM-DD"
	});
	$("#productName" + count).select2({
		theme : "bootstrap",
		allowClear : true,
		placeholder : "??????????????????????????????",
		query : function(query) {
			var url = "/ret/erpget/getProductSelect2";
			var param = {
				search : query.term
			}; // ???????????????query.term????????????select2??????????????????.
			var type = "json";
			var data = {
				results : []
			};
			$.post(url, param, function(datas) {
				var datalist = datas.list;
				for (var i = 0, len = datalist.length; i < len; i++) {
					var land = datalist[i];
					var option = {
						"id" : land.productId,
						"text" : land.productName,
						"model" : land.model,
						"materielCode" : land.materielCode,
						"unit" : land.unit
					};
					data.results.push(option);
				}
				query.callback(data);
			}, type);
		},
		escapeMarkup : function(markup) {
			return markup;
		},
		minimumInputLength : 2,
		formatResult : function(data) {
			return '<div class="select2-user-result">' + data.text + '</div>'
		},
		formatSelection : function(data) {
			$("#productId" + count).html(data.id);
			$("#materielCode" + count).html(data.materielCode);
			$("#productName" + count).html(data.text);
			$("#model" + count).html(data.model);
			$("#unit" + count).html(getUnitName(data.unit));
			return data.text;
		},
		initSelection : function(data, cb) {
			console.log(data);
			cb(data);
		}
	});
	}else
	{
	top.layer.msg("???????????????????????????????????????!??????????????????!")
	return;
	}
}
function saveok(count)
{
	tableData = $('#myTable').bootstrapTable('getData');
	let popped = tableData.pop();
		var row = {};
		row.num = count;
		row.productId = $("#productId" + count).text();
		row.materielCode = $("#materielCode" + count).text();
		row.productName = $("#productName" + count).text();
		row.model = $("#model" +count ).text();
		row.unit = $("#unit" + count).text();
		row.count = $("#count" + count ).val();
		row.delivery = $("#delivery" +count).val();
		row.remark = $("#remark"+ count).val();
		row.opt = createOptBtn(count);
		tableData.push(row);
		$("#myTable").bootstrapTable('load', tableData);
}

function delrows(index) {
	$('#myTable').bootstrapTable('remove', {
		field : 'num',
		values : [index]
	});
	var newData = $('#myTable').bootstrapTable('getData');
	var newTableData =[];
	for(var i=0;i<newData.length;i++)
		{
			var json = newData[i];
			json.num=i;
			json.opt=createOptBtn(i);
			newTableData.push(json);
		}
	$("#myTable").bootstrapTable('load', newTableData);
}

function queryParams(params) {
	var temp = {
		search : params.search,
		pageSize : this.pageSize,
		pageNumber : this.pageNumber,
		sort : params.sort,
		sortOrder : params.order
	};
	return temp;
};

function createInquiry() {
	console.log($("#inquiryCode").val());
	$.ajax({
		url : "/set/crmset/createCrmInquiry",
		type : "post",
		dataType : "json",
		data : {
			title : $("#title").val(),
			customerInquiryCode : $("#customerInquiryCode").val(),
			cashType : $("#cashType").val(),
			customerId : $("#customerId").val(),
			inquiryCode:$("#inquiryCode").val(),
			payType : $("#payType").val(),
			linkMan : $("#linkMan").val(),
			tel : $("#tel").val(),
			endTime : $("#endTime").val(),
			sendAdd : $("#sendAdd").val(),
			customerType : $('input:radio[name="customerType"]:checked').val(),
			packCharges : $('input:radio[name="packCharges"]:checked').val(),
			taxFlag : $('input:radio[name="taxFlag"]:checked').val(),
			invoiceFlag : $('input:radio[name="invoiceFlag"]:checked').val(),
			attach : $("#attach").attr("data_value"),
			remark : $("#remark").val(),
			detail :JSON.stringify($('#myTable').bootstrapTable('getData'))
		},
		success : function(data) {
			if (data.status == "500") {
				console.log(data.msg);
				top.layer.msg("?????????????????????????????????????????????")
			} else if (data.status == "100") {
				top.layer.msg(data.msg);
			} else {
				top.layer.msg(data.msg);
				window.location.reload();
			}
		}
	})
}

function updateInquiry() {
	$.ajax({
		url : "/set/crmset/updateCrmInquiry",
		type : "post",
		dataType : "json",
		data : {
			inquiryId:$("#inquiryId").val(),
			title : $("#title").val(),
			inquiryCode:$("#inquiryCode").val(),
			customerInquiryCode : $("#customerInquiryCode").val(),
			cashType : $("#cashType").val(),
			customerId : $("#customerId").val(),
			payType : $("#payType").val(),
			linkMan : $("#linkMan").val(),
			tel : $("#tel").val(),
			endTime : $("#endTime").val(),
			sendAdd : $("#sendAdd").val(),
			customerType : $('input:radio[name="customerType"]:checked').val(),
			packCharges : $('input:radio[name="packCharges"]:checked').val(),
			taxFlag : $('input:radio[name="taxFlag"]:checked').val(),
			invoiceFlag : $('input:radio[name="invoiceFlag"]:checked').val(),
			attach : $("#attach").attr("data_value"),
			remark : $("#remark").val(),
			detail :JSON.stringify($('#myTable').bootstrapTable('getData'))
		},
		success : function(data) {
			if (data.status == "500") {
				console.log(data.msg);
				top.layer.msg("?????????????????????????????????????????????")
			} else if (data.status == "100") {
				top.layer.msg(data.msg);
			} else {
				top.layer.msg(data.msg);
				window.location.reload();
			}
		}
	})
}

function getSelect2() {
	$("#customerId").select2({
		// data : customerJson,
		theme : "bootstrap",
		allowClear : true,
		placeholder : "?????????????????????",
		query : function(query) {
			var url = "/ret/crmget/getSelect2CustomerList";
			var param = {
				search : query.term
			}; // ???????????????query.term????????????select2??????????????????.
			var type = "json";
			var data = {
				results : []
			};
			$.post(url, param, function(datas) {
				var datalist = datas.list;
				for (var i = 0, len = datalist.length; i < len; i++) {
					var land = datalist[i];
					var name = land.cnName != '' ? land.cnName : land.enName;
					var option = {
						"id" : name,
						"text" : name,
						"legalPerson" : land.legalPerson,
						"registAddr" : land.registerAddr
					};
					data.results.push(option);
				}
				query.callback(data);
			}, type);
		},
		escapeMarkup : function(markup) {
			return markup;
		},
		minimumInputLength : 2,
		formatResult : function(data) {
			return '<div class="select2-user-result">' + data.text + '</div>'
		},
		formatSelection : function(data) {
			return data.text;
		},
		initSelection : function(data, cb) {
			console.log(data);
			cb(data);
		}
	});
}