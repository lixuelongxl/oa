CKEDITOR.plugins.add('xsymbol', {
    init: function(editor){
        editor.addCommand('xsymbol', {
        	exec:function(){
    			$("#xplugin").html(xsymbolmodal);
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
        			if(xtype!="xsymbol")
        			{
        				return;
        			}
        			$("#name").val(element.getAttribute('name'));
        			$("#name").attr("readonly","readonly");
        			$("#dataType").val(element.getAttribute('dataType'));
        			$("#style").val(element.getAttribute('style'));
        			$("#title").val(element.getAttribute('title'));
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
        			var title=$("#title").val();
        			var html = "<input type='text' " +
        						"xtype='xsymbol' " +
	        					"title='"+title+"' " +
	        					"name='"+name+"' " +
	        					"dataType='"+dataType+"' " +
	        					"style='"+style+"' " +
	        					"class='form-control' " +
	        					"placeholder='????????????|"+title+"'/>";
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
				xsymbol:{
					label : "??????????????????",
					command : 'xsymbol',
					group : 'xsymbol',
					order : 1
				}
			});
		}
		if (editor.contextMenu){
			editor.contextMenu.addListener(function(element,selection){
				if (!element || element.isReadOnly()) return null;
				var isInput = element.hasAscendant( 'input', 1 );
				if ( isInput && element.getAttribute('xtype')=="xsymbol"){
					return {
						xsymbol : CKEDITOR.TRISTATE_OFF
					};
				}
				return null;
			});
		}
    }
});

var xsymbolmodal=['	 <div class="modal fade bs-example-modal-lg" id="xmodal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">',
				'        <div class="modal-dialog">',
				'            <div class="modal-content">',
				'                <div class="modal-header">',
				'                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">??</button>',
				'                    <h4 class="modal-title" id="myLargeModalLabel">??????????????????</h4>',
				'                </div>',
				'                <div class="modal-body">',
				'                <form class="form-horizontal" role="form" id="xsymbolform">',
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
				'	                    <label class="col-sm-2 control-label no-padding-right">????????????</label>',
				'	                    <div class="col-sm-10">',
				'	                    <textarea rows="5" class="form-control" id="style" name="style"></textarea>',
				'	                </div>',
				'	                </div>',
				'	                <div class="form-group">',
				'	                    <label class="col-sm-2 control-label no-padding-right">???</label>',
				'	                    <div class="col-sm-10" style="line-height:34px;padding-left: 25px;">??????????????????????????????????????????????????????</div>',
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