# 稠云智能办公系统

#### 介绍
本项目为90%开源项目，用户可以用户开发学习，用户可自主开发除工作流以外的业务系统。

#### 软件架构
本项目JDK8x64+SpringBoot+MyBatis+Redis+Druid+Beetl的框架组合，自研工作流引擎，支持可视化表单设计与流程设计。支技分布式部署。功能完善能够满足中大型企业办公需要。
本项目自2020-08-17日起，为全面支持mysql8以上版本，Oracle，启用SpringBoot 2.3x的版本。支持jasperreport报表系统。

目前系统已全面支持国产达梦数据库8.0,人大金仓V8R3,神州通用v7，要需者可加群索取。

安装版测试DEMO下载地址：http://www.cyunsoft.com/cyunsoft_oa_v2.1_20200814_setup.exe （X86_MYSQL版） 最新版测试请加群索取！
正式版演示地址：http://test.cyunsoft.com:8080 账户：admin 密码：123456
#### 国产化
系统已全面支持国产化，统信UOS,中标麒麟操作系统+openJdk8x64bit+达梦8数据库、人大金仓V8R3、神州通用V7+东方通tongWeb中间件、中创中间件,金蝶天燕,宝兰德,文档支持在线WPS,OFFICE,福昕ofd,数科ofd,pdf等。若项目需要可联系我们索取相关源代码。
![image](http://www.cyunsoft.com/gc.jpg)

#### 安装教程

1.  mvn install lib/目录中的jar 文件拷贝到D盘根目录下，
		mvn install:install-file -DgroupId=com.zhuozhengsoft -DartifactId=pageoffice -Dversion=4.6.0.4 -Dpackaging=jar -Dfile=d:/pageoffice4.6.0.4.jar  
		mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar -Dfile=D:\ojdbc8.jar  
		mvn install:install-file -DgroupId=dm -DartifactId=dm.jdbc.driver -Dversion=1.8.0 -Dpackaging=jar -Dfile=D:\DmJdbcDriver18.jar  
		mvn install:install-file -DgroupId=com.kingbase8 -DartifactId=pgjdbc-core-parent -Dversion=1.1.2 -Dpackaging=jar -Dfile=D:\kingbase8-8.2.0.jar  
		mvn install:install-file -DgroupId=com.dingtalk.open -DartifactId=taobao-sdk-java-auto -Dversion=1479188381469-20200218 -Dpackaging=jar -Dfile=D:\taobao-sdk-java-auto_1479188381469-20200218.jar  
		mvn install:install-file -DgroupId=cyunsoft.common -DartifactId=cyunsoft-common -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar -Dfile=d:/cyunsoft-common-0.0.1-SNAPSHOT.jar  
		mvn install:install-file -DgroupId=cyunsoft.bean -DartifactId=cyunsoft-bean -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar -Dfile=d:/cyunsoft-bean-0.0.1-SNAPSHOT.jar  
		mvn install:install-file -DgroupId=cyunsoft.coreservice -DartifactId=cyunsoft-coreservice -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar -Dfile=d:/cyunsoft-coreservice-0.0.1-SNAPSHOT.jar  
		mvn install:install-file -DgroupId=cyunsoft.bi -DartifactId=cyunsoft-bi -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar -Dfile=d:/cyunsoft-bi-0.0.1-SNAPSHOT.jar  
		mvn install:install-file -DgroupId=cyunsoft.imservice -DartifactId=cyunsoft-imservice -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar  -Dfile=d:/cyunsoft-imservice-0.0.1-SNAPSHOT.jar  		
		开发环境正常启动后用jasperreports-6.12.2.jar 替换仓库中的jasperreports-6.12.2.jar，否则BI报表会出现中文乱码
2.  安装MYSQL8.0.21版本 后导入lib/mysql.sql
3.  运行cyunsoft-appservice中的AppGo.java 即可启动项目。
注：sigar-x86-winnt.dll文件拷贝到工程所用的jdk和jre的bin目录。
项目启动时需要d:/cyunsoft/attach和d:/cyunsoft/lic目录，建议事件下载安装测试版，一切需求的目录与静态文件都有了。
工程项目启动时会报ORCALE驱动文件缺少的错误。安提示在ORCALE安装目录中查找，并放到相应的位置。
工程项目启动成功后，在登陆系统时提示找不到指定的实体类时，请去除SpringBoot的热部署功能，再次启动即可。

#### 使用说明

1.  演示地址：http://test.cyunsoft.com:8080 账户：admin 密码：123456
2.  若有问题请联系：
					QQ:68311718 
					WX:13814042966 
					邮件：cyunoa@qq.com
3.  本产品的工作流手册与用户使用手册请联系我们索取。

#### 项目界面预览

  ![image](http://www.cyunsoft.com/main0.png)
  ![image](http://www.cyunsoft.com/main1.png)
  ![image](http://www.cyunsoft.com/main2.png)
  ![image](http://www.cyunsoft.com/main3.png)
  ![image](http://www.cyunsoft.com/main4.png)
  ![image](http://www.cyunsoft.com/main5.png)

#### APP界面预览
<img src="http://www.cyunsoft.com/app0.jpg" width="200"/>
<img src="http://www.cyunsoft.com/app1.jpg" width="200"/>
<img src="http://www.cyunsoft.com/app2.jpg" width="200"/>
<img src="http://www.cyunsoft.com/app3.jpg" width="200"/>
<img src="http://www.cyunsoft.com/app4.jpg" width="200"/>
<img src="http://www.cyunsoft.com/app5.jpg" width="200"/>
<img src="http://www.cyunsoft.com/app6.jpg" width="200"/>
<img src="http://www.cyunsoft.com/app7.jpg" width="200"/>


#### 开源宗旨
1.  本项目主要用于开发者了解企业办系统的基本功能，共同开发适合本国国情的工作流引擎。
2.  技术交流群QQ:660214195 群内不定时的发放技术文档。
3. 	开源项目不容易，大家感觉可以加个星。

