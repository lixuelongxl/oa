package com.core136.controller.file;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.core136.bean.account.Account;
import com.core136.bean.account.UserInfo;
import com.core136.bean.file.NetDisk;
import com.core136.bean.file.PersonalFile;
import com.core136.bean.file.PersonalFileFolder;
import com.core136.bean.file.Photo;
import com.core136.bean.file.PublicFileFolder;
import com.core136.bean.file.Attach;
import com.core136.bean.sys.PageParam;
import com.core136.service.file.NetDiskService;
import com.core136.service.file.PersonalFileFolderService;
import com.core136.service.file.PersonalFileService;
import com.core136.service.file.PhotoService;
import com.core136.service.file.PublicFileFolderService;
import com.core136.service.file.PublicFileService;
import com.core136.service.account.AccountService;
import com.core136.service.file.AttachService;
import com.core136.unit.fileutils.DownUtils;
import org.core136.common.retdataunit.RetDataBean;
import org.core136.common.retdataunit.RetDataTools;
import org.core136.common.utils.StrTools;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@RestController
@RequestMapping("/ret/fileget")
public class RoutGetFileController {
	@Autowired
	private NetDiskService netDiskService;
	@Autowired
	private PersonalFileFolderService personalFileFolderService;
	@Autowired
	private PersonalFileService personalFileService;
	@Autowired
	private PublicFileFolderService publicFileFolderService;
	@Autowired
	private AttachService attachService;
	@Autowired
	private DownUtils downUtils;
	@Autowired
	private PublicFileService publicFileService;
	@Autowired
	private PhotoService photoService;
	@Autowired
	private AccountService accountService;
	/**
	 * 
	 * @Title: getMyPublicFolderInPrivForDesk   
	 * @Description: TODO ???????????????????????????
	 * @param request
	 * @return
	 * RetDataBean    

	 */
	@RequestMapping(value="/getMyPublicFolderInPrivForDesk",method=RequestMethod.POST)
	public RetDataBean getMyPublicFolderInPrivForDesk(HttpServletRequest request)
		{
			try
			{
				UserInfo userInfo = accountService.getRedisUserInfo(request);
				return  RetDataTools.Ok("??????????????????!",publicFileFolderService.getMyPublicFolderInPrivForDesk(userInfo.getOrgId(),userInfo.getAccountId(),userInfo.getDeptId(),userInfo.getLeadLeave()));
			}catch (Exception e) {
				// TODO: handle exception
				return RetDataTools.Error(e.getMessage());
			}
		}
/**
 * 	
 * @Title getNetDiskList   
 * @Description TODO ????????????????????????  
 * @param request
 * @param sortLeave
 * @param pageNumber
 * @param pageSize
 * @param search
 * @param sort
 * @param sortOrder
 * @return      
 * RetDataBean
 */
	@RequestMapping(value="/getNetDiskList",method=RequestMethod.POST)
	public RetDataBean getNetDiskList(
			HttpServletRequest request,
			String sortLeave,
			Integer pageNumber,
			Integer pageSize,
			String search,
			String sort,
			String sortOrder
			)
	{
		try
		{
			if(StringUtils.isBlank(sort))
			{
				sort="SORT_NO";
			}else
			{
				sort=StrTools.upperCharToUnderLine(sort);
			}
			if(StringUtils.isBlank(sortOrder))
			{
				sortOrder="asc";
			}
		String orderStr=sort +" "+sortOrder;	
		Account account=accountService.getRedisAccount(request);
		Example example = new Example(NetDisk.class);
		Criteria criteria = example.createCriteria();
		if(account.getOpFlag().equals("1"))
		{
			criteria.andEqualTo("orgId", account.getOrgId()).andLike("netDiskName", "%"+search+"%");
		}else
		{
			criteria.andEqualTo("orgId", account.getOrgId()).andEqualTo("diskCreateUser",account.getAccountId()).andLike("netDiskName", "%"+search+"%");
		}
		 PageInfo<NetDisk> pageInfo=netDiskService.getNetDiskList(example,pageSize, pageNumber, orderStr);
		return RetDataTools.Ok("??????????????????!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title getNetDisk   
	 * @Description TODO ??????????????????   
	 * @param request
	 * @param netDisk
	 * @return      
	 * RetDataBean
	 */
	@RequestMapping(value="/getNetDisk",method=RequestMethod.POST)
	public RetDataBean getNetDisk(HttpServletRequest request,NetDisk netDisk)
		{
			try
			{
				Account account=accountService.getRedisAccount(request);
				netDisk.setOrgId(account.getOrgId());
				return  RetDataTools.Ok("??????????????????!",netDiskService.selectOneNetDisk(netDisk));
			}catch (Exception e) {
				// TODO: handle exception
				return RetDataTools.Error(e.getMessage());
			}
		}
	/**
	 * 
	 * @Title: getPhotoFileList   
	 * @Description: TODO ????????????????????????   
	 * @param: request
	 * @param: photo
	 * @param: @return      
	 * @return: RetDataBean      

	 */
	@RequestMapping(value="/getPhotoFileList",method=RequestMethod.POST)
	public RetDataBean getPhotoFileList(HttpServletRequest request,Photo photo)
		{
			try
			{
				Account account=accountService.getRedisAccount(request);
				photo.setOrgId(account.getOrgId());
				return  RetDataTools.Ok("??????????????????!",photoService.getPhotoFileList(photo));
			}catch (Exception e) {
				// TODO: handle exception
				return RetDataTools.Error(e.getMessage());
			}
		}
	
	
	/**
	 * 
	 * @Title: getPhotoById   
	 * @Description: TODO ??????????????????
	 * @param: request
	 * @param: photo
	 * @param: @return      
	 * @return: RetDataBean      

	 */
	@RequestMapping(value="/getPhotoById",method=RequestMethod.POST)
	public RetDataBean getPhotoById(HttpServletRequest request,Photo photo)
		{
			try
			{
				Account account=accountService.getRedisAccount(request);
				photo.setOrgId(account.getOrgId());
				return  RetDataTools.Ok("??????????????????!",photoService.selectOnePhoto(photo));
			}catch (Exception e) {
				// TODO: handle exception
				return RetDataTools.Error(e.getMessage());
			}
		}
	/**
	 * 
	 * @Title: getMyPhotoList   
	 * @Description: TODO ??????????????????????????????
	 * @param: request
	 * @param: @return      
	 * @return: RetDataBean      

	 */
	@RequestMapping(value="/getMyPhotoList",method=RequestMethod.POST)
	public RetDataBean getMyPhotoList(HttpServletRequest request)
		{
			try
			{
				UserInfo userInfo = accountService.getRedisUserInfo(request);
				return  RetDataTools.Ok("??????????????????!",photoService.getMyPhotoList(userInfo.getOrgId(),userInfo.getAccountId(),userInfo.getDeptId(),userInfo.getLeadLeave()));
			}catch (Exception e) {
				// TODO: handle exception
				return RetDataTools.Error(e.getMessage());
			}
		}
	
	/**
	 * 
	 * @Title: getMyPhotoFileList   
	 * @Description: TODO ???????????????????????????
	 * @param: request
	 * @param: photo
	 * @param: @return      
	 * @return: RetDataBean      

	 */
	@RequestMapping(value="/getMyPhotoFileList",method=RequestMethod.POST)
	public RetDataBean getMyPhotoFileList(HttpServletRequest request,Photo photo)
		{
			try
			{
				Account account=accountService.getRedisAccount(request);
				photo.setOrgId(account.getOrgId());
				photo = photoService.selectOnePhoto(photo);
				return  RetDataTools.Ok("??????????????????!",photoService.getMyPhotoFileList(photo));
			}catch (Exception e) {
				// TODO: handle exception
				return RetDataTools.Error(e.getMessage());
			}
		}	
	
	/**
	 * 
	 * @Title: getPhotoList   
	 * @Description: TODO ??????????????????
	 * @param: request
	 * @param: sortId
	 * @param: pageParam
	 * @param: @return      
	 * @return: RetDataBean      

	 */
	@RequestMapping(value="/getPhotoList",method=RequestMethod.POST)
	public RetDataBean getPhotoList(
			HttpServletRequest request,
			String sortId,
			PageParam pageParam
			)
	{
		try
		{
			if(StringUtils.isBlank(pageParam.getSort()))
			{
				pageParam.setSort("SORT_NO");
			}else
			{
				pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
			}
			if(StringUtils.isBlank(pageParam.getSortOrder()))
			{
				pageParam.setSortOrder("asc");
			}
			
		Account account=accountService.getRedisAccount(request);
		if(!account.getOpFlag().equals("1"))
		{
			pageParam.setAccountId(account.getAccountId());
		}
		pageParam.setOrgId(account.getOrgId());
		pageParam.setOrderBy(pageParam.getSort()+ " " + pageParam.getSortOrder());
		PageInfo<Map<String, String>> pageInfo=photoService.getPhotoList(pageParam);
		return RetDataTools.Ok("??????????????????!", pageInfo);
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}
	
/**
 * 
 * @Title getNetDiskTree   
 * @Description TODO ????????????????????????????????????
 * @param request
 * @param sortId
 * @return      
 * List<Map<String,Object>>
 */
@RequestMapping(value="/getNetDiskTree",method=RequestMethod.POST)
public List<Map<String,Object>> getNetDiskTree(HttpServletRequest request)
	{
		try
		{
			Account account=accountService.getRedisAccount(request);
			return netDiskService.getNetDiskTree(account);
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

/**
 * 
 * @Title getNetDiskDirs   
 * @Description TODO ??????????????????????????????????????????  
 * @param request
 * @param path
 * @return      
 * List<Map<String,Object>>
 */
@RequestMapping(value="/getNetDiskDirs",method=RequestMethod.POST)
public List<Map<String,Object>> getNetDiskDirs(HttpServletRequest request,String rootPath,NetDisk netDisk)
	{
		try
		{
			netDisk.setRootPath(null);
			Account account=accountService.getRedisAccount(request);
			netDisk.setOrgId(account.getOrgId());
			netDisk = netDiskService.selectOneNetDisk(netDisk);
			return netDiskService.getNetDiskDirs(rootPath,netDisk);
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
/**
 * 
* @Title: getNetDiskPriv 
* @Description: TODO ????????????????????????????????????????????????
* @param @param request
* @param @param netDiskId
* @param @return ???????????? 
* @return RetDataBean ???????????? 

 */
@RequestMapping(value="/getNetDiskPriv",method=RequestMethod.POST)
public RetDataBean getNetDiskPriv(HttpServletRequest request,String netDiskId)
	{
		try
		{
			if(StringUtils.isBlank(netDiskId))
			{
				return RetDataTools.NotOk("?????????????????????,?????????!");
			}else
			{
				Account account=accountService.getRedisAccount(request);
				return RetDataTools.Ok("????????????!", netDiskService.getNetDiskPrivByAccount(account, netDiskId));
			}
		}catch (Exception e) {
			return RetDataTools.Error(e.getMessage());
		}
	}

/**
 * 
 * @Title getNetDiskFiles   
 * @Description TODO ?????????????????????????????? 
 * @param request
 * @param rootPath
 * @return      
 * RetDataBean
 */
@RequestMapping(value="/getNetDiskFiles",method=RequestMethod.POST)
public RetDataBean getNetDiskFiles(HttpServletRequest request,String rootPath,NetDisk netDisk)
	{
		try
		{
			netDisk.setRootPath(null);
			Account account=accountService.getRedisAccount(request);
			netDisk.setOrgId(account.getOrgId());
			netDisk = netDiskService.selectOneNetDisk(netDisk);
			rootPath = netDisk.getRootPath()+rootPath;
			return RetDataTools.Ok("??????????????????!",netDiskService.getFileAndFolder(rootPath,netDisk));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
/**
 * 
* @Title: getNetDiskFileInfo 
* @Description: TODO ??????????????????
* @param @param request
* @param @param rootPath
* @param @param netDisk
* @param @return ???????????? 
* @return RetDataBean ????????????
 */
@RequestMapping(value="/getNetDiskFileInfo",method=RequestMethod.POST)
public RetDataBean getNetDiskFileInfo(HttpServletRequest request,String sourcePath,NetDisk netDisk)
	{
		try
		{
			netDisk.setRootPath(null);
			Account account=accountService.getRedisAccount(request);
			netDisk.setOrgId(account.getOrgId());
			netDisk = netDiskService.selectOneNetDisk(netDisk);
			return RetDataTools.Ok("??????????????????!",netDiskService.getFolderInfo(sourcePath,netDisk));
		}catch (Exception e) {
			// TODO: handle exception
			return RetDataTools.Error(e.getMessage());
		}
	}
/**
 * 
 * @Title: getPersonalFolderForSelect   
 * @Description: TODO ?????????Checkbox??????  
 * @param: request
 * @param: folderId
 * @param: @return      
 * @return: List<Map<String,Object>>      

 */
@RequestMapping(value="/getPersonalFolderForSelect",method=RequestMethod.POST)
public List<Map<String,Object>> getPersonalFolderForSelect(HttpServletRequest request,String folderId)
{
	try
	{
		if(StringUtils.isBlank(folderId))
		{
			folderId="0";
		}
		Account account=accountService.getRedisAccount(request);
		return personalFileFolderService.getPersonalFolderForSelect(folderId,account.getAccountId(),account.getOrgId());
	}catch (Exception e) {
		// TODO: handle exception
		return null;
	}
}

/**
 * 
 * @Title getPersonalDir   
 * @Description TODO ??????????????????????????? 
 * @param request
 * @return      
 * List<Map<String,Object>>
 */
@RequestMapping(value="/getPersonalDir",method=RequestMethod.POST)
public List<Map<String,Object>> getPersonalDir(HttpServletRequest request,String folderId)
{
	try
	{
		if(StringUtils.isBlank(folderId))
		{
			folderId="0";
		}
		Account account=accountService.getRedisAccount(request);
		return personalFileFolderService.getPersonalDir(folderId,account.getAccountId(),account.getOrgId());
	}catch (Exception e) {
		// TODO: handle exception
		return null;
	}
}

/**
 * 
* @Title: getPublicFilelist 
* @Description: TODO ????????????????????????????????????
* @param @param request
* @param @param folderId
* @param @return ???????????? 
* @return RetDataBean ????????????
 */
@RequestMapping(value="/getPublicFilelist",method=RequestMethod.POST)
public RetDataBean getPublicFilelist(HttpServletRequest request,String folderId)
{
	try
	{
		Account account=accountService.getRedisAccount(request);
		List<Map<String, String>> list1 = publicFileFolderService.getChildFolderList(account.getOrgId(), folderId);
		List<Map<String, String>> list2 = publicFileService.getPublicFilelist(account.getOrgId(),folderId);
		list1.addAll(list2);
		return RetDataTools.Ok("??????????????????!",list1);
	}catch (Exception e) {
		return RetDataTools.Error(e.getMessage());
	}
}
/**
 * 
 * @Title: getMyPublicFolderInPrivForSelect   
 * @Description: TODO ???????????????????????????
 * @param: request
 * @param: folderId
 * @param: @return      
 * @return: List<Map<String,Object>>      

 */
@RequestMapping(value="/getMyPublicFolderInPrivForSelect",method=RequestMethod.POST)
public List<Map<String,Object>> getMyPublicFolderInPrivForSelect(HttpServletRequest request,String folderId)
{
	try
	{
		Account account=accountService.getRedisAccount(request);
		UserInfo userInfo = accountService.getRedisUserInfo(request);
		return publicFileFolderService.getMyPublicFolderInPriv(account.getOrgId(), folderId, account.getAccountId(), userInfo.getDeptId(), userInfo.getLeadLeave(), account.getOpFlag());
	}catch (Exception e) {
		return null;
	}
}
/**
 * 
 * @Title: getPublicFileByFolderId   
 * @Description: TODO 
 * @param: request
 * @param: folderId
 * @param: @return      
 * @return: RetDataBean      

 */
@RequestMapping(value="/getPublicFileByFolderId",method=RequestMethod.POST)
public RetDataBean getPublicFileByFolderId(HttpServletRequest request,String folderId)
{
	try
	{
		Account account=accountService.getRedisAccount(request);
		return RetDataTools.Ok("??????????????????!",publicFileService.getPublicFileByFolderId(account.getOrgId(),folderId));
	}catch (Exception e) {
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
 * @Title getfilelist   
 * @Description TODO ?????????????????????????????????
 * @param request
 * @param folderId
 * @return      
 * RetDataBean
 */
@RequestMapping(value="/getfilelist",method=RequestMethod.POST)
public RetDataBean getfilelist(HttpServletRequest request,String folderId)
{
	try
	{
		Account account=accountService.getRedisAccount(request);
		return RetDataTools.Ok("??????????????????!",personalFileService.getfilelist(folderId,account.getAccountId(),account.getOrgId()));
	}catch (Exception e) {
		// TODO: handle exception
		return RetDataTools.Error(e.getMessage());
	}
}
/**
 * 
 * @Title: getFileListForSelect   
 * @Description: TODO ?????????????????????    
 * @param: request
 * @param: folderId
 * @param: @return      
 * @return: RetDataBean      

 */
@RequestMapping(value="/getFileListForSelect",method=RequestMethod.POST)
public RetDataBean getFileListForSelect(HttpServletRequest request,String folderId)
{
	try
	{
		Account account=accountService.getRedisAccount(request);
		return RetDataTools.Ok("??????????????????!",personalFileService.getFileListForSelect(folderId,account.getAccountId(),account.getOrgId()));
	}catch (Exception e) {
		// TODO: handle exception
		return RetDataTools.Error(e.getMessage());
	}
}
/**
 * 
* @Title: getPublicFileFolderTree 
* @Description: TODO ?????????????????????????????????
* @param @param request
* @param @param folderId
* @param @return ???????????? 
* @return List<Map<String,Object>> ????????????
 */
@RequestMapping(value="/getPublicFileFolderTree",method=RequestMethod.POST)
public List<Map<String,Object>> getPublicFileFolderTree(HttpServletRequest request,String folderId)
{
	try
	{
		Account account=accountService.getRedisAccount(request);
		return publicFileFolderService.getPublicFileFolderAllDir(account.getOrgId(), folderId, account.getAccountId(),account.getOpFlag());
	}catch (Exception e) {
		return null;
	}
}
/**
 * 
 * @Title: getPublicFileFolderById   
 * @Description: TODO ??????????????????????????????
 * @param: request
 * @param: publicFileFolder
 * @param: @return      
 * @return: RetDataBean      

 */
@RequestMapping(value="/getPublicFileFolderById",method=RequestMethod.POST)
public RetDataBean getPublicFileFolderById(HttpServletRequest request,PublicFileFolder publicFileFolder)
{
	try
	{
		Account account=accountService.getRedisAccount(request);
		publicFileFolder.setOrgId(account.getOrgId());
		return RetDataTools.Ok("??????????????????!",publicFileFolderService.selectOnePublicFileFolder(publicFileFolder));
	}catch (Exception e) {
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
* @Title: getMyPublicFolderInPriv 
* @Description: TODO ?????????????????????????????????????????????
* @param @param request
* @param @param folderId
* @param @return ???????????? 
* @return List<Map<String,Object>> ????????????
 */
@RequestMapping(value="/getMyPublicFolderInPriv",method=RequestMethod.POST)
public List<Map<String,Object>> getMyPublicFolderInPriv(HttpServletRequest request,String folderId)
{
	try
	{
		Account account=accountService.getRedisAccount(request);
		UserInfo userInfo = accountService.getRedisUserInfo(request);
		return publicFileFolderService.getMyPublicFolderInPriv(account.getOrgId(), folderId, account.getAccountId(), userInfo.getDeptId(), userInfo.getLeadLeave(), account.getOpFlag());
	}catch (Exception e) {
		return null;
	}
}

/**
 * 
* @Title: getPublicFolderPrivInfo 
* @Description: TODO ????????????????????????????????????
* @param @param request
* @param @param folderId
* @param @return ???????????? 
* @return RetDataBean ????????????
 */
@RequestMapping(value="/getPublicFolderPrivInfo",method=RequestMethod.POST)
public RetDataBean getPublicFolderPrivInfo(HttpServletRequest request,String folderId)
{
	try
	{
		Account account=accountService.getRedisAccount(request);
		UserInfo userInfo = accountService.getRedisUserInfo(request);
		return RetDataTools.Ok("??????????????????!",publicFileFolderService.getPublicFolderPrivInfo(account,folderId,account.getAccountId(),userInfo.getDeptId(),userInfo.getLeadLeave()));
	}catch (Exception e) {
		return RetDataTools.Error(e.getMessage());
	}
}
/**
 * 
* @Title: getPersonalFileFolderChild 
* @Description: TODO ???????????????????????????
* @param @param request
* @param @param personalFileFolder
* @param @return ???????????? 
* @return RetDataBean ????????????
 */
@RequestMapping(value="/getPersonalFileFolderChild",method=RequestMethod.POST)
public RetDataBean getPersonalFileFolderChild(HttpServletRequest request,PersonalFileFolder personalFileFolder)
{
	try
	{
		if(StringUtils.isBlank(personalFileFolder.getFolderId()))
		{
			return RetDataTools.NotOk("?????????????????????,?????????!");
		}
		Account account=accountService.getRedisAccount(request);
		personalFileFolder.setCreateUser(account.getAccountId());
		personalFileFolder.setOrgId(account.getOrgId());
		return RetDataTools.Ok("??????????????????!",personalFileFolderService.getPersonalFileFolderChild(personalFileFolder));
	}catch (Exception e) {
		return RetDataTools.Error(e.getMessage());
	}
}

/**
 * 
* @Title: getFileDown 
* @Description: TODO ??????????????????????????????
* @param @param response
* @param @param request
* @param @param personalFile ???????????? 
* @return void ????????????
 */
@RequestMapping("/getFileDown")
public void getFileDown(HttpServletResponse response,HttpServletRequest request,PersonalFile personalFile)
{
	try
	{
		Account account=accountService.getRedisAccount(request);
		personalFile.setOrgId(account.getOrgId());
		personalFile = personalFileService.selectOnePersonalFile(personalFile);
		Attach attach = new Attach();
		attach.setAttachId(personalFile.getAttach());
		attach.setOrgId(account.getOrgId());
		attach=attachService.selectOne(attach);
		downUtils.download(attach.getPath(), response);
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}

/**
 * 
 * @Title: getAttachManageList   
 * @Description: TODO ??????????????????
 * @param request
 * @param pageParam
 * @param extName
 * @param beginTime
 * @param endTime
 * @param modules
 * @param createAccount
 * @return
 * RetDataBean    

 */
@RequestMapping(value="/getAttachManageList",method=RequestMethod.POST)
public RetDataBean getAttachManageList(
		HttpServletRequest request,
		PageParam pageParam,
		String extName,
		String beginTime,
		String endTime,
		String modules,
		String createAccount
		)
{
	try
	{
		if(StringUtils.isBlank(pageParam.getSort()))
		{
			pageParam.setSort("UP_TIME");
		}else
		{
			pageParam.setSort(StrTools.upperCharToUnderLine(pageParam.getSort()));
		}
		if(StringUtils.isBlank(pageParam.getSortOrder()))
		{
			pageParam.setSortOrder("desc");
		}
	Account account=accountService.getRedisAccount(request);
	pageParam.setOrgId(account.getOrgId());
	pageParam.setOrderBy(pageParam.getSort()+ " " + pageParam.getSortOrder());
	PageInfo<Map<String, String>> pageInfo=attachService.getAttachManageList(pageParam, createAccount, modules, beginTime, endTime, extName);
	return RetDataTools.Ok("??????????????????!", pageInfo);
	}catch (Exception e) {
		return RetDataTools.Error(e.getMessage());
	}
}



}
