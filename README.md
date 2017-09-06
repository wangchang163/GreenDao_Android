# GreenDao_Android
封装GreenDao数据库的增删改查使用以及版本升级解决办法


## 优势

性能（可能是Android最快的ORM）:我们的基准也是开源的易于使用的功能强大的API涵盖关系和联接最小的内存消耗
大小（<100KB）:以保持建筑时间低，并避免65k方法限制
数据库加密：greenDAO支持SQLCipher，以保护用户的数据安全
强大的社区：超过5.000 GitHub明星表明有一个强大和积极的社区

## 使用

### step1：

在project.gradle添加  

    // In your root build.gradle file:
    buildscript {
        repositories {
            jcenter()
            mavenCentral() // add repository
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:2.3.0'
            classpath 'org.greenrobot:greendao-gradle-plugin:3.2.2' // add plugin
        }
    }

### step2：

在app.gradle添加

    
    apply plugin: 'org.greenrobot.greendao'
    
    //数据库版本号以及文件路径
    greendao{
        schemaVersion 3
        targetGenDir 'src/main/java'
    }
    
    
     // database
     compile 'org.greenrobot:greendao:3.2.0'
     //可视化数据库调试工具
     debugCompile 'com.amitshekhar.android:debug-db:1.0.1'
     

## 增删改查封装

### 增（insert）

 |     方法名    |   介绍    | 
 |--------------|:----------|
 |    insert    |插入一条数据|
 |insertInTx| 插入一条或多条数据|
 |insertOrReplace|插入或替换|
 |insertOrReplaceInTx|插入或替换一条或者多条数据|

### 删（delete）

 |     方法名    |   介绍    | 
 |--------------|:----------|
 |    delete    |删除一个对象|
 |deleteAll| 删除所有数据|
 |deleteByKey|根据id删除|
 |deleteInTx|删除一个或多个对象|
 |deleteByKeyInTx|根据一个或者多个key删除对象|
 
 ### 改（update）
 
  |     方法名    |   介绍    | 
  |--------------|:----------|
  |    update    |更改一个对象|
  |updateInTx| 更改多个对象|
  |insertOrReplaceInTx|插入或替换一条或者多条数据|
 
 ### 查（query）
 
  |     方法名    |   介绍    | 
   |--------------|:----------|
   |    loadAll    |查询所有|
   |queryBuilder| 根据语法查询|
 
 #### 查询条件
 
    //根据id查询
    daoSession.getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(id)).build().unique();
 
 eq用来判断两个属性是否对等，unique()返回一个对象
 
    //in查询包含在内的列表
    QueryBuilder<User> qb=daoSession.getUserDao().queryBuilder();
    qb.where(UserDao.Properties.Id.in(1,2,3,4,5)).build().list();
    
    //获取1970年10月或以后出生的名为“Joe”的用户
    QueryBuilder<User> qb = userDao.queryBuilder();
    qb.where(Properties.FirstName.eq("Joe"),
    qb.or(Properties.YearOfBirth.gt(1970),
    qb.and(Properties.YearOfBirth.eq(1970), Properties.MonthOfBirth.ge(10))));
    List<User> youngJoes = qb.list();
    
    // order by last name
    queryBuilder.orderAsc(Properties.LastName);
     
    // in reverse
    queryBuilder.orderDesc(Properties.LastName);
     
    // order by last name and year of birth
    queryBuilder.orderAsc(Properties.LastName).orderDesc(Properties.YearOfBirth);
    
 ### 查询API：
 
  http://greenrobot.org/files/greendao/javadoc/current/org/greenrobot/greendao/query/QueryBuilder.html
 
 
 
 ## 创建模型（创建好后，需要编译一下）
 
 参考User类
 
 @Entity 标注此类为数据库支持的实体类
 @Id(autoincrement = true) 标注键，作为实体id，自增
 @Property 标注属性
 
 参考文档：http://greenrobot.org/greendao/documentation/modelling-entities/
 
 
 
 ## 数据库更新
 
 1：修改schemaVersion
 
    greendao{
     schemaVersion 3
     targetGenDir 'src/main/java'
    }
 2：修改DaoMaster中的SCHEMA_VERSION
 
     public static final int SCHEMA_VERSION = 3;
     
 3:DbOpenHelper中，如果新版本号大于旧版本，迁移数据库，否则删除重建数据库
 
     if (newVersion > oldVersion) {
             // 升级、数据库迁移操作
             MigrationHelper.getInstance().migrate(db, UserDao.class);
         }else {
             // 默认操作
             dropAllTables(db, true);
             onCreate(db);
         }
         
 4：编译一下，在运行。
 
 
 本例将GreenDao进行一定程度上的封装，对于需要用的方法，
 在DbHelper中添加相应，在AppDbHelper中实现，在ui中引用，在application中获取AppDbHelper的实例。
 
 效果：
 
 ![image]()
 