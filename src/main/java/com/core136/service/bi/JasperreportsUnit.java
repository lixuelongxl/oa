package com.core136.service.bi;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.core136.bean.bi.BiTemplate;
import com.core136.bean.file.Attach;
import com.core136.service.file.AttachService;

import cyunsoft.bi.jasper.JasperTools;

@Service
public class JasperreportsUnit {
//	@Value("${app.jasreports.path}")
//	private String jasreportsPath;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private BiTemplateService biTemplateService;
	@Autowired
	private AttachService attachService;
	
	/**
	 * 
	 * @Title: getRepHtml   
	 * @Description: TODO 获取HTML格式报表在线查看
	 * @param biTemplate
	 * @return
	 * @throws Exception
	 * String
	 */
	public String getRepHtml(BiTemplate biTemplate) throws Exception
	{
		
		biTemplate = biTemplateService.selectOne(biTemplate);
		Attach attach = new Attach();
		attach.setOrgId(biTemplate.getOrgId());
		attach.setAttachId(biTemplate.getJasTemplate());
		attach = attachService.selectOne(attach);
		String jasPath = attach.getPath();
		JasperTools jasperTools = new JasperTools();
		Map<String,String> pathMap = new HashMap<String,String>();
		pathMap = jasperTools.getJasPaths(jasPath,attach.getOldName(),attach.getNewName());
		Connection conn = dataSource.getConnection();
		String jrxmlPath = pathMap.get("jrxmlPath");
		String jasperPath = pathMap.get("jasperPath");
		String destFileName = pathMap.get("htmlPath");
		Map<String, Object> params = new HashMap<String, Object>();
		return jasperTools.reportHtml(conn, params, jrxmlPath, jasperPath, destFileName);
	}
	
	/**
	 * 
	 * @Title: getRepForPdf   
	 * @Description: TODO 获取PDF格式报表
	 * @param request
	 * @param response
	 * @param biTemplate
	 * @throws Exception
	 * void
	 */
	public void getRepForPdf(HttpServletRequest request,HttpServletResponse response,BiTemplate biTemplate) throws Exception
	{
		
		biTemplate = biTemplateService.selectOne(biTemplate);
		Attach attach = new Attach();
		attach.setOrgId(biTemplate.getOrgId());
		attach.setAttachId(biTemplate.getJasTemplate());
		attach = attachService.selectOne(attach);
		String jasPath = attach.getPath();
		JasperTools jasperTools = new JasperTools();
		Map<String,String> pathMap = new HashMap<String,String>();
		pathMap = jasperTools.getJasPaths(jasPath,attach.getOldName(),attach.getNewName());
		Connection conn = dataSource.getConnection();
		String jrxmlPath = pathMap.get("jrxmlPath");
		String jasperPath = pathMap.get("jasperPath");
		Map<String, Object> params = new HashMap<String, Object>();
		jasperTools.pdfExport(request, response, conn, params, jrxmlPath, jasperPath);
	}
	
	
}
