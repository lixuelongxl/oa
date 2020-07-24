# 稠云智能办公系统

#### 介绍
本项目为90%开源项目，用户可以用户开发学习，用户可自主开发除工作流以外的业务系统。

#### 软件架构
本项目JDK8x64+SpringBoot+MyBatis+Redis+Durid+Beetl的框架组合，自研工作流引擎，支持可视化表单设计与流程设计。支技分布式部署。功能完善能够满足中大型企业办公需要。


#### 安装教程

1.  mvn install lib/目录中的jar 文件
		mvn install:install-file -DgroupId=com.zhuozhengsoft -DartifactId=pageoffice -Dversion=4.6.0.4  -Dpackaging=jar  -Dfile=d:/pageoffice4.6.0.4.jar
		mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar -Dfile=D:\ojdbc8.jar
		mvn install:install-file -DgroupId=com.dingtalk.open -DartifactId=taobao-sdk-java-auto -Dversion=1479188381469-20200218 -Dpackaging=jar -Dfile=D:\taobao-sdk-java-auto_1479188381469-20200218.jar
		mvn install:install-file -DgroupId=common -DartifactId=cyunsoft-common -Dversion=0.0.1-SNAPSHOT  -Dpackaging=jar  -Dfile=d:/cyunsoft-common-0.0.1-SNAPSHOT.jar
		mvn install:install-file -DgroupId=cyunsoft.bean -DartifactId=cyunsoft-bean -Dversion=0.0.1-SNAPSHOT  -Dpackaging=jar  -Dfile=d:/cyunsoft-bean-0.0.1-SNAPSHOT.jar
		mvn install:install-file -DgroupId=cyunsoft.coreservice -DartifactId=cyunsoft-coreservice -Dversion=0.0.1-SNAPSHOT  -Dpackaging=jar  -Dfile=d:/cyunsoft-coreservice-0.0.1-SNAPSHOT.jar
		mvn install:install-file -DgroupId=cyunsoft.bi -DartifactId=cyunsoft-bi -Dversion=0.0.1-SNAPSHOT  -Dpackaging=jar  -Dfile=d:/cyunsoft-bi-0.0.1-SNAPSHOT.jar
		mvn install:install-file -DgroupId=cyunsoft.imservice -DartifactId=cyunsoft-imservice -Dversion=0.0.1-SNAPSHOT  -Dpackaging=jar  -Dfile=d:/cyunsoft-imservice-0.0.1-SNAPSHOT.jar
2.  安装MYSQL5.7 后导入lib/mysql.sql
3.  运行cyunsoft-appservice中的AppGo.java 即可启动项目。

#### 使用说明

1.  演示地址：http://oa.cyunsoft.com 账户：admin 密码：123456
2.  若有问题请联系：QQ:68311718 WX:13814042966 邮件：cyunoa@qq.com
3.  本产品的工作流手册与用户使用手册请联系我们索取。

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
