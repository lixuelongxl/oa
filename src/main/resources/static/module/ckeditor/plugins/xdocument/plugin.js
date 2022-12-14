CKEDITOR.plugins.add('xdocument', {
    init: function(editor){
        editor.addCommand('xdocument', {
        	exec:function(){
    			$("#xplugin").html(xdocumentmodal);
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
        			if(xtype!="xdocument")
        			{
        				return;
        			}
        			$("#name").val(element.getAttribute('name'));
        			$("#name").attr("readonly","readonly");
        			$("#title").val(element.getAttribute('title'));
        			var model=element.getAttribute('model');
        			var json=JSON.parse(model);
        			$("#model").val(json.type);
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
        			var style=$("#style").val();
        			var model=$("#model").val();
        			var title=$("#title").val();
        			var json={};
        			json.type=model;
        			var modelStr=JSON.stringify(json);
        			var html = "<img type='text' " +
        						"xtype='xdocument' " +
	        					"title='"+title+"' " +
	        					"name='"+name+"' " +
	        					"src='/module/ckeditor/plugins/xdocument/documentlist.png'"+
	        					"model='"+modelStr+"'/>";
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
				xdocument:{
					label : "???????????????",
					command : 'xdocument',
					group : 'xdocument',
					order : 1
				}
			});
		}
		if (editor.contextMenu){
			editor.contextMenu.addListener(function(element,selection){
				if (!element || element.isReadOnly()) return null;
				var isInput = element.hasAscendant( 'img', 1 );
				if ( isInput && element.getAttribute('xtype')=="xdocument"){
					return {
						xdocument : CKEDITOR.TRISTATE_OFF
					};
				}
				return null;
			});
		}
    }
});

var xdocumentmodal=['	 <div class="modal fade bs-example-modal-lg" id="xmodal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">',
				'        <div class="modal-dialog">',
				'            <div class="modal-content">',
				'                <div class="modal-header">',
				'                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">??</button>',
				'                    <h4 class="modal-title" id="myLargeModalLabel">???????????????</h4>',
				'                </div>',
				'                <div class="modal-body">',
				'                <form class="form-horizontal" role="form" id="xdocumentform">',
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
				'	               		 </div>',
				'	                </div>',
				'	                <div class="form-group">',
				'	                    <label class="col-sm-2 control-label no-padding-right">????????????</label>',
				'	                    <div class="col-sm-10">',
				'	                    <select class="form-control" id="model" name="model" placeholder="????????????"  style="border-radius:0px">',
				'							<option value="1">????????????????????????</option>',
				'							<option value="2">????????????????????????</option>',
				'							<option value="3">????????????????????????</option>',
				'							<option value="4">??????????????????</option>',
				'						</select>',
				'	                	</div>',
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