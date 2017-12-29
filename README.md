## 9.30  
 * 劳务工 staff 中company 跟随 主表劳务商名称
 * 承包商 同上 跟随承包商名称。
 * 访客跟随 主表来访单位lfunit。
 * 货运司机、随车人员公司写 "货运单位"。remarks 写车牌
 * DropDownTools 增加访客类型。
 
## 10.2
 * 解决。添加获取上下班打卡的时候的 null异常问题。


## 10.4 
* 劳务工，承包商的人员的在厂时间用cid  sid
* 访客  的在厂时间用的是单位id cid  人员没写计划在厂时间
* 访客  是否可以进入二道门的信息  scene  的字段用来判断单位权限写一道门的，还是全写。
* RegistrationService  访客单位的remarks 写访客 单位，  人的remarks写 访客


## 10.7
* 发现劳务工和承包商的单位权限 一个是存在的话删除重新添加，另一个是存在就什么也不做。应该都可以。
* 访客单位增加“访客单位”  解决承包商单位和访客单位有可能为同一个的问题。

## 10.10
* 连接池扩大
* 在DAO层释放了连接和ptmt 和ResultSet
* 从OA拿到了dbcp.properties配置文件，文件格式没问题了

## 10.17
* 访客性别原本用的Hash的，现在统一都用汉字的性别
* companyprivilegedao 添加公司权限时增加了 如果是化工码头和煤码头的话就不添加的代码

## 10.18
* CompanyPrivilegeDao 更改 如果是化工码头和煤码头不添加权限
* PlantimeDao 增加根据 cid sid 查询计划出厂时间的
* StaffDao 增加获取company ，获取ctype
* RegistrationService

## 10.19
* EntranceGuardDao 增加根据人员类型查询所有门禁
* ConstractortermService 更改默认承包商可以进入二道门。
* LwgStructionService 更改劳务工默认可以进入二道门。

## 10.23
增加出差人员的控制
* Model: OATocsbusiness
* Action: TocsBusiness
* service: ToBusinessService


## 10.24 
* 更改出差人员公用同一个卡号。这样就直接把原来卡号加时间，新来的人卡号用最新的。

## 11.13
* 黑名单支持删除操作。
* 新增读取人员数量的接口。
* 修改承包商。劳务工的到期日为23：59：59；

## 11.24
* 考勤数据如果员工卡号不足10，在前面补零
* 提入单号拆分装卸计划点，和提入单号一一对应。

## 12.5
* 新增排车系统
* 添加提入单号写入order表
* 添加称重 次数1  2
* 添加更新depart表中的数量

## 12.13
* 测试数据。
* 发给OA做测试了

## 12.27
* 监控中心，大屏的接口。


## 12.29
* 测试好监控中心大屏和排车接口


## TODO



 	
 	
