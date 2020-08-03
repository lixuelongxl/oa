$(function(){
	$(".js-create").unbind("click").click(function(){
		$("#pageCreateModal").modal("show");
		$(".js-save").unbind("click").click(function(){
			insertPlatformPage();
		})
	})
});
function insertPlatformPage()
{
	$.ajax({
		url : "/set/platformset/insertPlatformPage",
		type : "post",
		dataType : "json",
		data : {
			sortNo : $("#sortNo").val(),
			pageName : $("#pageName").val(),
			pageType : $("#pageType").val(),
			cacheFlag : $("#cacheFlag").val(),
			remark : $("#renark").val()
		},
		success : function(data) {
			if (data.status == 200) {
				top.layer.msg(data.msg);
				$("#pageCreateModal").modal("hide");
			} else {
				console.log(data.msg);
			}
		}
	});
}