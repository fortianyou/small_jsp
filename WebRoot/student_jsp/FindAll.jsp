<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="wanczy.entity.Wanczy_Student" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'FindAll.jsp' starting page</title>
  </head>
  
  <body>
   <div>
   	<table>
   		<tr><td>id</td><td>name</td><td>password</td></tr>
   		<%
   			List list = (List) request.getAttribute("studentList");
   			for (Object object : list) {
   				Wanczy_Student s = (Wanczy_Student) object;
   		%>
   				<tr><td><%=s.getId() %></td><td><%=s.getName() %></td><td><%=s.getPwd() %></td></tr>
   		<%
   			}
   		%>
   	</table>
   </div>
  </body>
</html>
