
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>See your Users</title>
    </head>
    <body>
       The Users are
	   <ul>
	   

	  
	   <s:iterator value ="users">
		<li><s:property value ="firstname"/> </li>
		<li><s:property value ="email"/> </li>
		</s:iterator>
	  
	   
	   
	   </ul>
    </body>
</html>
