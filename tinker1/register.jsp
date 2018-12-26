
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>wall</title>
    </head>
    <body>
        <h1>REGISTER</h1>
        <s:form action="createUser" >
            <s:textfield name="username" label="username"/>
            <s:textfield name="firstName" label="First Name"/>
            <s:textfield name="lastName" label="Last Name"/>
            <s:textfield name="email" label="Email"/>
            <s:textfield name="password" label="Password"/>
            <s:textfield name="dob"  label="DOB"/>
            <s:textfield name="gender"  label="Gender"/>
             <s:submit />
             </s:form>
    </body>
</html>