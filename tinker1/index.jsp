
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>tinker</h1>
        <p>welcome to tinker</p>
        <s:form action="login">
            <s:textfield name="email" label="email"/>
            <s:textfield name="password" label="password"/>
            <s:submit value="login"/>
        </s:form>
        <s:form action="register" >
            <s:submit value="register"/>
        </s:form>
       

    </body>

</html>