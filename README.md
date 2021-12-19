# Project_Of_BookMall
This is my first project about JavaWeb.I wish it's a good begin.

## 关于分页条代码的优化
这里我将 "client/bookServlet?action=page" 和 "manager/bookServlet?action=page" 修改成
“${requestScope.page.url}” 

## 使用ThreadLocal 来确保所有dao 操作都在同一个 connection 连接对象中完成
```markdown
要确保所有操作要么都成功，要么都失败，就必须要使用数据库的事务。
要确保所有操作都在一个事务内，就必须要确保所有操作都使用同一个Connection连接对象。
如何确保所有操作都使用同一个 Connection连接对象？
我们这里就可以使用ThreadLocal对象，来确保所有操作都使用同一个连接对象
```
***详细做法***
JdbcUtils工具类的修改
