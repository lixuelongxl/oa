package com.core136.config;

import org.springframework.stereotype.Component;

import ch.qos.logback.core.status.Status;

/**
 * 
 * @ClassName:  AppGobalConstant   
 * @Description:系统常量   
 * @author: 刘绍全
 * @date:   2018年10月18日 下午1:24:33   
 *     
 * @Copyright: 2018 www.cyunsoft.com Inc. All rights reserved. 
 * 注意：本内容仅限于江苏稠云信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Component
public class AppGobalConstant {
	public final static String SOFT_REG_PATH="sys";
	public final static String SOFT_NAME = "CY智能网络办公系统";
	//public final static String SOFT_VERISON = "V2018.0930.1";
	public final static String SOFT_VERISON = "V2020.0501.1";
	public final static String SOFT_COM = "江苏稠云信息技术有限公司";
	public final static String SOFT_TEAM = "Shaoquan liu,Yunyun Lu";
	public final static String SOFT_COPY = "@copyright 江苏稠云信息技术有限公司";
	public final static String SOFT_KEY = "L3IUX6-LUA-LXYZ-0829";
	//平台内部消息提醒类型
	public final static String MSG_TYPE_MOBILE="mobileSms";
	public final static String MSG_TYPE_WEBEMAIL="webMail";
	public final static String MSG_TYPE_APP="appSms";
	public final static String MSG_TYPE_WEB_SMS="webSms";
	public final static String MSG_TYPE_DINGDING_SMS="ddSms";
	public final static String MSG_TYPE_WEIXIN_SMS="wxSms";
	
	public final static String APP_MODULE_SYSTEM_ATTACH_PATH="/system";
	public final static String APP_MODULE_ERP_ATTACH_PATH="/erp";
	public final static String APP_MODULE_NEWS_ATTACH_PATH="/news";
	public final static String APP_MODULE_MEETING_ATTACH_PATH="/meeting";
	public final static String APP_MODULE_BPM_ATTACH_PATH="/bpm";
	public final static String APP_MODULE_EMAIL_ATTACH_PATH="/email";
	public final static String APP_MODULE_PUBLIC_FILE_ATTACH_PATH ="/publicfile";
	public final static String APP_MODULE_PERSONAL_FILE_ATTACH_PATH = "/personalfile";
	public final static String APP_MODULE_NOTICE_ATTACH_PATH="/notice";
	public final static String APP_MODULE_DIARY_ATTACH_PATH="/diary";
	public final static String APP_MODULE_PROJECT_BUILD_ATTACH_PATH="/projectbuild";
	public final static String APP_MODULE_TASK_ATTACH_PATH="/task";
	public final static String APP_MODULE_PROJECT_ATTACH_PATH="/project";
	public final static String APP_MODULE_KNOWLEDGE_ATTACH_PATH="/knowledge";
	public final static String APP_MODULE_OTHER_ATTACH_PATH="/other";
	public final static String APP_MODULE_TEMPLATE_PATH ="/template";
	public final static String APP_MODULE_EDITOR_ATTACH_PATH="/editor";
	public final static String APP_MODULE_IM_AUDIO_ATTACH_PATH="/im/audio";
	public final static String APP_MODULE_IM_IMG_ATTACH_PATH="/im/img";
	public final static String APP_MODULE_IM_ATTACH_ATTACH_PATH="/im/attach";
	public final static String APP_MODULE_IM_ATTACH_DYNAMIC_PATH="/im/dynamic";
	public final static String APP_MODULE_IM_ATTACH_INQUIRY_PATH="/im/inquiry";
	public final static String APP_MODULE_VECHICLE_ATTACH_PATH="/vechicle";
	public final static String APP_MODULE_VECHICLE_PHOTOS_PATH="/vechicle/photos";
	public final static String APP_MODULE_HR_PHOTOS_PATH="/hr/photos";
	public final static String APP_MODULE_DATAINFO_ATTACH_PATH="/datainfo";
	public final static String APP_MODULE_HR_ATTACH_PATH="/hr";
	public final static String APP_MODULE_WORK_PLAN_ATTACH_PATH="/workplan";
	public final static String APP_MODULE_DOCUMENT_ATTACH_PATH="/document";
	public final static String APP_MODULE_ARCHIVES_ATTACH_PATH="/archives";
	
	
}
