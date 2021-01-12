# MyBatis 源码

![](../imgs/mybatis_frame.webp)

* SqlSession

通过会话工厂创建sqlSession即会话，程序员通过sqlsession会话接口对数据库进行增删改查操作。

* MappedStatement

    * 它是mybatis一个底层封装对象，它包装了mybatis配置信息及sql映射信息等。mapper.xml文件中一个select\insert\update\delete标签对应一个Mapped Statement对象，select\insert\update\delete标签的id即是Mapped statement的id。

    * Mapped Statement对sql执行输入参数进行定义，包括HashMap、基本类型、pojo，Executor通过MappedStatement在执行sql前将输入的java对象映射至sql中，输入参数映射就是jdbc编程中对preparedStatement设置参数。

    * Mapped Statement对sql执行输出结果进行定义，包括HashMap、基本类型、pojo，Executor通过MappedStatement在执行sql后将输出结果映射至java对象中，输出结果映射过程相当于jdbc编程中对结果的解析处理过程。

* Executor
  
MyBatis执行器，是MyBatis 调度的核心，负责SQL语句的生成和查询缓存的维护
  
* StatementHandler

装了JDBC Statement操作，负责对JDBC statement 的操作，如设置参数、将Statement结果集转换成List集合。
