CKEDITOR.plugins.add('xsql', {
    init: function(editor){
        editor.addCommand('xsql', {
        	exec:function(){
    			$("#xplugin").html(xsqlmodal);
        		if(bpmfield=="auto")
        			{
        				$(".autodiv").each(function(){
        					$(this).remove();
        				});
        			}
        		getDbSource();
        		var selection = top.editor.getSelection();
        		var element = selection.getSelectedElement();
        		if(element)
        			{
        			var xtype = element.getAttribute('xtype');
        			if(xtype!="xsql")
        			{
        				return;
        			}
        			$("#name").val(element.getAttribute('name'));
        			$("#name").attr("readonly","readonly");
        			$("#dataType").val(element.getAttribute('dataType'));
        			$("#style").val(element.getAttribute('style'));
        			$("#defaultValue").val(element.getAttribute('defaultValue'));
        			$("#title").val(element.getAttribute('title'));
        			var model=element.getAttribute('model');
        			var json=JSON.parse(model);
        			$("#dbSource").val(json.dbSource);
        			$("#sql").val(json.sql);
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
        			var title=$("#title").val();
        			var dbSource=$("#dbSource").val();
        			var sql=$("#sql").val().replace(/\'/g,"&#39;");
        			var json={};
        			json.dbSource=dbSource;
        			json.sql=sql;
        			var modelStr=JSON.stringify(json)
        			var html = "<input type='text' " +
        						"xtype='xsql' " +
	        					"title='"+title+"' " +
	        					"name='"+name+"' " +
	        					"dataType='"+dataType+"' " +
	        					"model='"+modelStr+"' " +
	        					"defaultValue='"+defaultValue+"' " +
	        					"value='"+defaultValue+"' " +
	        					"style='"+style+"' " +
	        					"class='form-control' " +
	        					"placeholder='SQL??????|"+title+"'/>";
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
				xsql:{
					label : "SQL??????",
					command : 'xsql',
					group : 'xsql',
					order : 1
				}
			});
		}
		if (editor.contextMenu){
			editor.contextMenu.addListener(function(element,selection){
				if (!element || element.isReadOnly()) return null;
				var isInput = element.hasAscendant( 'input', 1 );
				if ( isInput && element.getAttribute('xtype')=="xsql"){
					return {
						xsql : CKEDITOR.TRISTATE_OFF
					};
				}
				return null;
			});
		}
    }
});
function getDbSource()
{
	$.ajax({
		url:"/ret/sysget/getDbSourceList",
		type:"POST",
		dataType:"json",
		success:function(data){
			if(data.status==200)
				{
				var html="<option value='SYS_DB'>????????????</option>";
				for(var i=0;i<data.list.length;i++)
					{
					html+="<option value='"+data.list[i].dbSourceId+"'>"+data.list[i].dbSourceName+"</option>";
					}
				$("#dbSource").html(html);
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


var xsqlmodal=['	 <div class="modal fade bs-example-modal-lg" id="xmodal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">',
				'        <div class="modal-dialog">',
				'            <div class="modal-content">',
				'                <div class="modal-header">',
				'                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">??</button>',
				'                    <h4 class="modal-title" id="myLargeModalLabel">SQL??????</h4>',
				'                </div>',
				'                <div class="modal-body">',
				'                <form class="form-horizontal" role="form" id="xsqlform">',
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
				'	                </div>',
				'	                </div>',
				'	                <div class="form-group autodiv">',
				'	                    <label class="col-sm-2 control-label no-padding-right">????????????</label>',
				'	                    <div class="col-sm-10">',
				'	                    <select class="form-control" id="dataType" name="dataType" placeholder="????????????">',
				'							<option>?????????</option>',
				'							<option value="int">?????????</option>',
				'							<option value="varchar">?????????</option>',
				'							<option value="text">?????????</option>',
				'						</select>',
				'	                	</div>',
				'	                </div>',
				'	                <div class="form-group">',
				'	                    <label class="col-sm-2 control-label no-padding-right">?????????</label>',
				'	                    <div class="col-sm-10">',
				'	                    <input type="text" class="form-control" id="defaultValue" name="defaultValue" placeholder="?????????">',
				'	               		</div>',
				'	                </div>',
				'	                <div class="form-group">',
				'	                    <label class="col-sm-2 control-label no-padding-right">?????????</label>',
				'	                    <div class="col-sm-10">',
				'	                    <select id="dbSource" name="dbSource" style="border-radius: 0px"></select>',
				'	                </div>',
				'	                </div>',
				'	                <div class="form-group">',
				'	                    <label class="col-sm-2 control-label no-padding-right">??????SQL</label>',
				'	                    <div class="col-sm-10">',
				'	                    <textarea rows="5" class="form-control" id="sql" name="sql"></textarea>',
				'	                </div>',
				'	                </div>',
				'	                <div class="form-group">',
				'	                    <label class="col-sm-2 control-label no-padding-right">????????????</label>',
				'	                    <div class="col-sm-10">',
				'	                    <textarea rows="5" class="form-control" id="style" name="style"></textarea>',
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