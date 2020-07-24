$(function(){
	$.ajax({
		url : "/ret/bpmget/getBpmBusinessById",
		type : "post",
		dataType : "json",
		data:{businessId:businessId},
		success : function(data) {
			if(data.status=="200")
			{
				var recordInfo = data.list;
				for(var id in recordInfo)
				{
					if(id=="dbSourceId")
					{
					}else if(id=="flowId")
					{
						
					}else
					{
						$("#"+id).html(recordInfo[id]);
					}
				}
			}else if(data.status=="100")
			{
				top.layer.msg(data.msg);
			}else if(data.status=="500")
			{
				console.log(data.msg);
			}
		}
		})
})