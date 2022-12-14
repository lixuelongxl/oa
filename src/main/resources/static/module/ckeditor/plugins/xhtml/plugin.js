CKEDITOR.plugins.add('xhtml', {
    init: function(editor){
        editor.addCommand('xhtml', {
        	exec:function(){
    			$("#xplugin").html(xhtmlmodal);
        		if(bpmfield=="auto")
        			{
        				$(".autodiv").each(function(){
        					$(this).remove();
        				});
        			}
        		var selection = top.editor.getSelection();
        		var element = selection.getSelectedElement();
        		if(element)
        			{
        			var xtype = element.getAttribute('xtype');
        			if(xtype!="xhtml")
        			{
        				return;
        			}
        			$("#name").val(element.getAttribute('name'));
        			$("#name").attr("readonly","readonly");
        			$("#title").val(element.getAttribute('title'));
        			var model=element.getAttribute('model');
        			var json=JSON.parse(model);
        			var attachId = json.attachId;
        			$("#xhtmlfile").attr("data_value",attachId);
        			createTemplate();
        			}else
        				{
        				if(bpmfield=="auto")
	            			{
	            				var content=editor.getData();
	            				$("#name").attr("readonly","readonly");
	            				var maxName =0;
	            				$(content).find('*[xtype]').each(function(){
	            					var thisName = $(this).attr("name");
	            					var thisCount = thisName.substring(7,thisName.length);
	            					if(parseInt(thisCount)>maxName)
	            						{
	            						maxName = parseInt(thisCount);
	            						}
	            				})
	            				$("#name").val(getFieldNameAuto(maxName));
	            			}
        				}
        		$(".js-saveinputbtn").unbind('click').click(function (){
        			var name=$("#name").val();
        			var dataType=$("#dataType").val();
        			var style=$("#style").val();
        			var defaultValue=$("#defaultValue").val();
        			var json={};
        			json.attachId=$("#xhtmlfile").attr("data_value");
        			var modelStr=JSON.stringify(json);
        			var title=$("#title").val();
        			var html = "<img type='text' " +
        						"xtype='xhtml' " +
	        					"title='"+title+"' " +
	        					"name='"+name+"' " +
	        					"model='"+modelStr+"' " +
	        					"src='/module/ckeditor/plugins/xhtml/code.png'/>";
        			if(title=="")
					{
						top.layer.msg("????????????????????????!");
						return false;
					}
        			if($("#name").attr("readonly")=="readonly")
    				{
    				editor.insertHtml(html);
                    $("#xmodal").modal("hide");
    				}else
    				{
        			var content=editor.getData();
    				$(content).find('*[xtype]').each(function(){
    					fieldList[$(this).attr("name")]=$(this).attr("title");
    				});
    				if(fieldList.hasOwnProperty(name))
					{
					top.layer.msg(title+"???????????????,?????????!");
					return false;
					}
        			editor.insertHtml(html);
                    $("#xmodal").modal("hide");
    				}
              	});
        		$('#xmodal').modal("show");
	    	}
	    });
		if ( editor.addMenuItems ){
			editor.addMenuItems({
				xhtml:{
					label : "XHTML??????",
					command : 'xhtml',
					group : 'xhtml',
					order : 1
				}
			});
		}
		if (editor.contextMenu){
			editor.contextMenu.addListener(function(element,selection){
				if (!element || element.isReadOnly()) return null;
				var isImg = element.hasAscendant( 'img', 1 );
				if ( isImg && element.getAttribute('xtype')=="xhtml"){
					return {
						xhtml : CKEDITOR.TRISTATE_OFF
					};
				}
				return null;
			});
		}
    }
});

var xhtmlmodal=['	 <div class="modal fade bs-example-modal-lg" id="xmodal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">',
				'        <div class="modal-dialog">',
				'            <div class="modal-content">',
				'                <div class="modal-header">',
				'                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">??</button>',
				'                    <h4 class="modal-title" id="myLargeModalLabel">XHTML??????</h4>',
				'                </div>',
				'                <div class="modal-body">',
				'                <form class="form-horizontal" role="form" id="xhtmlform">',
				'                	<div class="form-group">',
				'	                    <label class="col-sm-2 control-label no-padding-right">????????????</label>',
				'	                    <div class="col-sm-10">',
				'	                    <input type="text" class="form-control" id="title" name="title" placeholder="????????????">',
				'	                    </div>',
				'	                </div>',
				'	                <div class="form-group">',
				'	                    <label class="col-sm-2 control-label no-padding-right">????????????</label>',
				'	                    <div class="col-sm-10">',
				'	                    <input type="text" class="form-control" id="name" name="name" placeholder="????????????">',
				'	              		 </div>',
				'	                </div>',
				'	                <div class="form-group">',
				'	                    <div class="col-md-offset-2"  id="show_xhtmlfile" style="line-height: 28px;"></div>',
				'	                    <label class="col-sm-2 control-label no-padding-right">XHTML??????</label>',
				'	                    <div class="col-sm-10" style="line-height: 28px;">',
				'						<a class="addfile" href="javascript:void(0)">????????????',
				'						<input data_value="" type="file" onchange="bpmTemplateFileUpLoad();" hidefocus="true" size="1" id="xhtmlfile" name="xhtmlfile" class="addfile" accept=".html,.txt"></a>',
				'	                </div>',
				'	                </div>',
				'                </form>',
				'                </div>',
				'                <div class="modal-footer">',
				'                  <button type="button" class="btn btn-warning" data-dismiss="modal">??????</button>',
				'                 <button type="button" class="btn btn-primary js-saveinputbtn">??????</button>',
				'                </div>',
				'            </div>',
				'        </div>',
				'    </div>'].join("");

function bpmTemplateFileUpLoad()
{
	$.ajaxFileUpload({
        url:'/sys/file/bpmTemplateFileUpLoad',//????????????????????????
        secureuri:false,  //????????????????????????
        async:false,
        dataType: 'json',   //????????????  
        fileElementId:'xhtmlfile', //???????????????ID
        success: function(data,status){
       	 	if(data.status==200)
       	 		{
       	 		console.log(data.list);
       	 		top.layer.msg(data.msg);
       	 		var datalist = data.list;
       	 		$("#xhtmlfile").attr("data_value",datalist[0].attachId);
       	 		createTemplateAttachDiv(datalist);
       	 		}else if(data.status==100)
   				{
   					top.layer.msg(data.msg);
   				}else
       	 			{
       	 			console.log(data.msg);
       	 			}
        },
   //????????????????????????
        error: function (data,status,e){
       	top.layer.msg("??????????????????!?????????????????????!");
       	console.log(data.msg);
        }
   });
}

function createTemplateAttachDiv(attachlist)
{
	var htmlattach="";
	for(var i=0;i<attachlist.length;i++)
	{
		htmlattach+="<span class='btn-group' style=\"margin-right: 10px;margin-bottom: 10px;\" id='attachdiv_"+attachlist[i].attachId+"'>" +
					"<a class=\"btn btn-success\" href=\"javascript:void(0);\">"+attachlist[i].oldName+"</a>" +
					"<a class=\"btn btn-success  dropdown-toggle\" data-toggle=\"dropdown\" href=\"javascript:void(0);\" aria-expanded=\"false\"><i class=\"fa fa-angle-down\"></i></a>" +
					"<ul class=\"dropdown-menu dropdown-success\">" +
					"<li>" +
					"<a href=\"javascript:void(0);delattachTemplate('"+attachlist[i].attachId+"')\">??????</a>" +
					"</li>" +
					"<li>" +
					"<a href=\"/sys/file/getFileDown?attachId="+attachlist[i].attachId+"\">??????</a>" +
					"</li>" +
					"</ul>" +
					"</span>";
	}
	$("#show_xhtmlfile").append("<div>"+htmlattach+"</div>");
}



function createTemplate()
{
	var attachIds = $("#xhtmlfile").attr("data_value");
	if(attachIds!=""&&attachIds!=undefined)
		{
		$.ajax({
			url : "/sys/file/getAttachList",
			type : "post",
			dataType : "json",
			data:{
				attachIds:attachIds
			},
			success : function(data) {
				if(data.status==200){
					var datalist = data.list;
					if(datalist!=null)
						{
						createTemplateAttachDiv(datalist);
						}
				}else if(data.status==100)
				{
					console.log(data.msg);
				}else
					{
					console.log(data.msg);
					}
			}
		});
	}
}

function delattachTemplate(attachId)
{
	var v=$("#xhtmlfile").attr("data_value");
	v = (v+",").replace(attachId+",","");
	$("#xhtmlfile").attr("data_value",v);
	$("#attachdiv_"+attachId).remove();
}
