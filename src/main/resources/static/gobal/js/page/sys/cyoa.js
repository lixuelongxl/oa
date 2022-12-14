function getUserPrivNamesByIds(userPrivIds) {
	var returnData = "";
	$.ajax({
		url : "/ret/unitget/getUserPrivNamesByIds",
		type : "post",
		dataType : "json",
		async : false,
		data : {
			userPrivIds : userPrivIds
		},
		success : function(data) {
			var tmpstr = "";
			if (data.status == 200) {
				var lists = data.list;
				for (var i = 0; i < lists.length; i++) {
					tmpstr += lists[i].userPrivName + ",";
				}
				returnData = tmpstr;
			} else if (data.status == 100) {
				top.layer.msg(data.msg);
			} else {
				console.log(data.msg);
			}
		}
	});
	return returnData;
}
function getNoReadSms() {
	$.ajax({
		url : "/ret/sysget/getNoReadSms",
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data.status == 200) {
				
				if (data.list) {
					var msgbody = "";
					for (var i = 0; i < data.list.length; i++) {
						var url = data.list[i].smsUrl;
						var smsId = data.list[i].smsId;
						msgbody += [ '<li>', '<a href=\"javascript:void(0);readSysSms(\''+url+'\',\''+smsId+'\');\">', '   <img style="width:42px;height:42px;" src="/sys/file/getOtherHeadImg?headImg=' + data.list[i].headImg + '" class="message-avatar" alt="' + data.list[i].userName + '">', '   <div class="message">',
								'       <span class="message-sender">', data.list[i].userName, '       </span>', '       <span class="message-time">', data.list[i].smsSendTime, '       </span>', 
								'       <span class="message-subject spanStyle">',data.list[i].smsTitle,
								'       </span>', '       <span class="message-body">', data.list[i].smsContent, '       </span>', '   </div>', '                                        </a>', '                                    </li>' ].join("");

					}
					$("#msgTotal").html(data.list.length);
					$(".dropdown-messages").html(msgbody);
				}
			} else if (data.status == 100) {
				top.layer.msg(data.msg);
			} else {
				console.log(data.msg);
			}
		}
	});
	getNoReadSmsCount()
}

function readSysSms(url,smsId)
{
	$.ajax({
		url : "/set/oaset/updateSms",
		type : "post",
		dataType : "json",
		data:{smsId:smsId,smsStatus:'1'},
		success : function(data) {
			if (data.status == 200) {
				getNoReadSms();
				window.open(url);
			} else if (data.status == 100) {
				top.layer.msg(data.msg);
			} else {
				console.log(data.msg);
			}
		}
	})
}
function getNoReadSmsCount() {
	$.ajax({
		url : "/ret/sysget/getNoReadSmsCount",
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data.status == 200) {
				$(".noreandmsg").html(data.list);
			} else if (data.status == 100) {
				top.layer.msg(data.msg);
			} else {
				console.log(data.msg);
			}
		}
	})
}

function getCodeClass(eId, module) {
	$.ajax({
		url : "/ret/sysget/getCodeClassByModule",
		type : "post",
		dataType : "json",
		data : {
			module : module
		},
		success : function(data) {
			if (data.status == 200) {
				var html = "<option value=''>?????????</option>";
				for (var i = 0; i < data.list.length; i++) {
					html += "<option value='" + data.list[i].codeValue + "'>" + data.list[i].codeName + "</option>";
				}
				$("#" + eId).html(html);
			} else if (data.status == 100) {
				top.layer.msg(data.msg);
			} else {
				console.log(data.msg);
			}
		}
	})
}

function getCodeClassName(value, module) {
	var returnStr;
	$.ajax({
		url : "/ret/sysget/getCodeClassByModule",
		type : "post",
		dataType : "json",
		async : false,
		data : {
			module : module
		},
		success : function(data) {
			if (data.status == 200) {

				for (var i = 0; i < data.list.length; i++) {
					if (data.list[i].codeValue == value) {
						returnStr = data.list[i].codeName;
						return;
					}
				}
			} else if (data.status == 100) {
				top.layer.msg(data.msg);
			} else {
				console.log(data.msg);
			}
		}
	})
	return returnStr;
}

function getSmsConfig(eId, module) {
	$.ajax({
		url : "/ret/sysget/getSmsConfig",
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data.status == 200) {
				var html = "";
				var json = JSON.parse(data.list.config);
				var mustjson = JSON.parse(data.list.mustChecked);
				if (json) {
					var smsConfig = json[module];
					var mustChecked = mustjson[module];
					for (var i = 0; i < smsConfig.length; i++) {
						var checkedHtml="";
						if(isInArray(mustChecked,smsConfig[i]))
						{
							checkedHtml=" onclick='return false;' checked='checked'";
						}
						html += "<div class='checkbox' style='display: inline-block;'><label><input type='checkbox' name='" + eId + "' value='" + smsConfig[i] + "'"+checkedHtml+" ><span class='text'>";
						if (smsConfig[i] == "webSms") {
							html += "????????????";
						} else if (smsConfig[i] == "mobileSms") {
							html += "???????????? ";
						} else if (smsConfig[i] == "appSms") {
							html += "APP?????? ";
						} else if (smsConfig[i] == "webMail") {
							html += "???????????? ";
						} else if (smsConfig[i] == "ddSms") {
							html += "???????????? ";
						}else if (smsConfig[i] == "wxSms") {
							html += "???????????? ";
						}
						html += "</span></label></div>";
					}
				}
				$("#" + eId).html(html);
			} else if (data.status == 100) {
				top.layer.msg(data.msg);
			} else {
				console.log(data.msg);
			}
		}
	})
}

function getCheckBoxValue(eId) {
	var returnStr = [];
	$('input[name="' + eId + '"]:checked').each(function() {
		returnStr.push($(this).val());
	});
	return returnStr.join(",");
}

function getUserInfoDeatilsByAccountId(accountId) {
	var userInfo = {};
	if (accountId) {
		$.ajax({
			url : "/ret/unitget/getUserInfoDeatilsByAccountId",
			type : "post",
			dataType : "json",
			async : false,
			data : {
				accountId : accountId
			},
			success : function(data) {
				if (data.status == 200) {
					userInfo = data.list;
				} else if (data.status == 100) {
					console.log(data.msg);
				} else {
					console.log(data.msg);
				}
			}
		});
	}
	return userInfo
}

function getUserNameByStr(accountIds) {
	if (accountIds == "@all") {
		return "????????????";
	} else {
		var userNames = [];
		if (accountIds) {
			$.ajax({
				url : "/ret/unitget/getUserNamesByAccountIds",
				type : "post",
				dataType : "json",
				async : false,
				data : {
					accountIds : accountIds
				},
				success : function(data) {
					if (data.status == 200) {
						for (var i = 0; i < data.list.length; i++) {
							userNames.push(data.list[i].userName);
						}
					} else if (data.status == 100) {
						console.log(data.msg);
					} else {
						console.log(data.msg);
					}
				}
			});
		}
		return userNames.join(",");
	}
}

function getUserLevelStr(userLevelIds) {
	if (userLevelIds == "@all") {
		return "????????????";
	} else {
		var userLevelNames = [];
		if (userLevelIds) {
			$.ajax({
				url : "/ret/unitget/getUserLevelByLevelIds",
				type : "post",
				dataType : "json",
				async : false,
				data : {
					userLevelIds : userLevelIds
				},
				success : function(data) {
					if (data.status == "200") {
						for (var i = 0; i < data.list.length; i++) {
							userLevelNames.push(data.list[i].name);
						}
					} else if (data.status == "100") {
						console.log(data.msg);
					} else {
						console.log(data.msg);
					}
				}
			});
		}
		return userLevelNames.join(",");
	}
}

function getDeptNameByDeptIds(deptIds) {
	if (deptIds == "@all") {
		return "????????????";
	} else {
		var deptNames = [];
		if (deptIds) {
			$.ajax({
				url : "/ret/unitget/getUnitDeptByDeptIds",
				type : "post",
				dataType : "json",
				async : false,
				data : {
					deptIds : deptIds
				},
				success : function(data) {
					if (data.status == 200) {
						for (var i = 0; i < data.list.length; i++) {
							deptNames.push(data.list[i].deptName);
						}
					} else if (data.status == 100) {
						console.log(data.msg);
					} else {
						console.log(data.msg);
					}
				}
			});
		}
		return deptNames.join(",");
	}
}

function getSysTime() {
	var myDate = new Date();
	//???????????????
	var year = myDate.getFullYear();
	//???????????????
	var month = myDate.getMonth() + 1;
	//???????????????
	var date = myDate.getDate();
	var h = myDate.getHours(); //?????????????????????(0-23)
	var m = myDate.getMinutes(); //?????????????????????(0-59)
	var s = myDate.getSeconds();
	var now = year + '-' + getNow(month) + "-" + getNow(date) + " " + getNow(h) + ':' + getNow(m) + ":" + getNow(s);
	return now;
}
/**
 * ??????????????? yyyy-MM-dd HH:mm:ss
 * @param myDate
 * @returns
 */
function formatTime(myDate) {
	var year = myDate.getFullYear();
	//???????????????
	var month = myDate.getMonth() + 1;
	//???????????????
	var date = myDate.getDate();
	var h = myDate.getHours(); //?????????????????????(0-23)
	var m = myDate.getMinutes(); //?????????????????????(0-59)
	var s = myDate.getSeconds();
	var now = year + '-' + getNow(month) + "-" + getNow(date) + " " + getNow(h) + ':' + getNow(m) + ":" + getNow(s);
	return now;
}
/**
 * ??????????????? yyyy-MM-dd
 * @param myDate
 * @returns
 */
function formatDate(myDate) {
	var year = myDate.getFullYear();
	var month = myDate.getMonth() + 1;
	var date = myDate.getDate();
	var now = year + '-' + getNow(month) + "-" + getNow(date);
	return now;
}
/**
 * ?????????
 * @param dayNumber
 * @param dateStr
 * @returns
 */
function addDay(dayNumber, dateStr) {
	var date = new Date(Date.parse(dateStr.replace(/-/g, "/")));
    date = date ? date : new Date();
    var ms = dayNumber * (1000 * 60 * 60 * 24)
    var newDate = new Date(date.getTime() + ms);
    return formatDate(newDate);
}

/**
 * ??????????????????
 * @returns
 */
function getSysDate() {
	var myDate = new Date();
	//???????????????
	var year = myDate.getFullYear();
	//???????????????
	var month = myDate.getMonth() + 1;
	//???????????????
	var date = myDate.getDate();
	var now = year + '-' + getNow(month) + "-" + getNow(date);
	return now;
}
// ??????????????????
function getNow(s) {
	return s < 10 ? '0' + s : s;
}
String.prototype.endWith = function(s) {
	if (s == null || s == "" || this.length == 0 || s.length > this.length)
		return false;
	if (this.substring(this.length - s.length) == s)
		return true;
	else
		return false;
	return true;
}

String.prototype.startWith = function(s) {
	if (s == null || s == "" || this.length == 0 || s.length > this.length)
		return false;
	if (this.substr(0, s.length) == s)
		return true;
	else
		return false;
	return true;
}
function arr_del(arr, index) {
	return arr.slice(0, index - 1).concat(arr.slice(index));
}

function getfileClass(filename) {
	var point = filename.lastIndexOf(".");
	var type = filename.substr(point)
	if (type == ".txt") {
		return "fa fa-file-text";
	} else if (type == ".pdf") {
		return "fa fa-file-pdf-o";
	} else if (type == ".xls" || type == ".xlsx") {
		return "fa fa-file-excel-o";
	} else if (type == ".doc" || type == ".docx" || type == ".wps" || type == ".dot") {
		return "fa fa-file-word-o";
	} else if (type == ".jpg" || type == ".png" || type == ".bpm") {
		return "fa fa-file-image-o";
	} else if (type == ".rar" || type == ".zip" || type == ".gz" || type == ".tar") {
		return "fa fa-file-zip-o";
	} else if (type == ".ppt" || type == ".pptx" || type == ".ppts") {
		return "fa fa-file-powerpoint-o";
	} else {
		return "fa fa-file";
	}
}

function checkTime(i) {
	if (i < 10) {
		i = "0" + i
	}
	return i;
}

function clearSelect(id) {
	$("#" + id).attr("data-value", "");
	$("#" + id).val("");

}
/*????????????*/
function unique(arr) {
	if (!Array.isArray(arr)) {
		console.log('type error!')
		return;
	}
	arr = arr.sort()
	var arrry = [ arr[0] ];
	for (var i = 1; i < arr.length; i++) {
		if (arr[i] !== arr[i - 1]) {
			arrry.push(arr[i]);
		}
	}
	return arrry;
}

function getshowtime(createtime) {
	if (createtime) {
		createtime = createtime.replace(/-/g, "/"); // ?????? ios ???????????????
	}
	let createTime = new Date(createtime);
	let createTimes = createTime.getTime();
	let nowTime = new Date();
	let nowTimes = nowTime.getTime();
	if (nowTimes < createTimes) {
		return "??????????????????";
	}

	let createYear = createTime.getFullYear();
	let nowYear = nowTime.getFullYear();
	let createMonth = createTime.getMonth();
	let nowMonth = nowTime.getMonth();
	let createDate = createTime.getDate();
	let nowDate = nowTime.getDate();

	if (createYear < nowYear) {
		return (nowYear - createYear) + "??????";
	} else {
		if (createMonth < nowMonth) {
			return (nowMonth - createMonth) + "??????";
		} else {
			if (createDate < nowDate) {
				if (nowDate - createDate == 1) {
					return "??????"
				} else {
					return (nowDate - createDate) + "??????";
				}
			} else {
				let diffValue = nowTimes - createTimes;
				if (diffValue / (1000 * 60 * 60) >= 1) {
					return (parseInt(diffValue / (1000 * 60 * 60))) + "?????????";
				} else {
					if (diffValue / (1000 * 60) >= 1) {
						return (parseInt(diffValue / (1000 * 60))) + "?????????";
					} else {
						return "??????";
					}
				}
			}
		}
	}
}
/**
??????jquery???inArray??????????????????????????????????????????
@param {Object} arr ??????
@param {Object} value ?????????
*/
function isInArray(arr,value){
var index = $.inArray(value,arr);
if(index >= 0){
return true;
}
return false;
}
/**
 * ??????????????????
 * @param minutes
 * @returns
 */
function minTommss(minutes){
	 var sign = minutes < 0 ? "-" : "";
	 var min = Math.floor(Math.abs(minutes));
	 var sec = Math.floor((Math.abs(minutes) * 60) % 60);
	 return sign + (min < 10 ? "0" : "") + min + ":" + (sec < 10 ? "0" : "") + sec;
	}

function mustNumber(val) {
    var regPos = /^\d+(\.\d+)?$/; //???????????????
    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //????????????
    if(regPos.test(val) || regNeg.test(val)) {
        return true;
        } else {
        return false;
        }
    }
 

function getSmsType(module)
{
if(module=="EMAIL")
{
	return "??????????????????";
}else if(module=="NEWS")
{
	return "????????????";
}else if(module=="NOTICE")
{
	return "??????????????????";
}else if(module=="DIARY")
{
	return "??????????????????";
}else if(module=="BPM")
{
	return "???????????????";
}else if(module=="DOCUMENT")
{
	return "???????????????";
}else if(module=="USER")
{
	return "????????????";
}else if(module=="SYS")
{
	return "????????????";
}else if(module=="MEETING")
{
	return "????????????";
}else if(module=="TASK")
{
	return "????????????";
}else if(module=="HR")
{
	return "??????????????????";
}else if(module=="SUPERVERSION")
{
	return "??????????????????";
}else if(module=="CONTRACT")
{
	return "????????????";
}else if(module=="CRM")
{
	return "??????????????????";
}else if(module=="PROJECT")
{
	return "????????????";
}else if(module=="ERP")
{
	return "ERP??????";
}else if(module=="PUBLIC_FILE")
{
	return "??????????????????";
}else if(module=="WORKPLAN")
{
	return "??????????????????";
}else if(module=="DATAUPLOAD")
{
	return "??????????????????";
}
}
