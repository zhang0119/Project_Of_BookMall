# Project_Of_BookMall
This is my first project about JavaWeb.I wish it's a good begin.

## 关于分页条代码的优化
这里我将 "client/bookServlet?action=page" 和 "manager/bookServlet?action=page" 修改成
“${requestScope.page.url}” 