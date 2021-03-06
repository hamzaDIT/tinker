
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
                body {
                  margin: 0;
                  font-family: Arial, Helvetica, sans-serif;
                }
                
                .topnav {
                  overflow: hidden;
                  background-color: #333;
                }
                
                .topnav a {
                  float: left;
                  color: #f2f2f2;
                  text-align: center;
                  padding: 14px 16px;
                  text-decoration: none;
                  font-size: 17px;
                }
                
                .topnav a:hover {
                  background-color: #ddd;
                  color: black;
                }
                
                .topnav a.active {
                  background-color: #4CAF50;
                  color: white;
                }
                </style>
    </head>
    <body>
            <div class="topnav">
                    <div class="active" href="#wall">
                      <s:form action="wall">
                      <s:submit value="wall"></s:submit>
                    </s:form>

                      <s:form action="profile">
                        <s:submit value="profile"></s:submit>
                      </s:form>
                    </div>
                    
                    <s:form action="addFriend">
                      <s:submit value="add friend"></s:submit>
                    </s:form>

                    <s:form action="viewFriends">
                      <s:submit value="view friends"></s:submit>
                    </s:form>

                    <s:form action="viewAllMembers">
                      <s:submit value="view all members"></s:submit>
                    </s:form>

                    <s:form action="postComment">
                      <s:submit value="post comment to a wall"></s:submit>
                    </s:form>
                    <s:form action="logout">
                      <s:submit value="logout"></s:submit>
                    </s:form>
                </div>
                  
                  <div style="padding-left:16px">
                    <h1>This is Your Profile</h1>
                    <h2>Your details:</h2>
                    Username: <s:property value="#session.currentUser.username" /><br/>
                    First Name: <s:property value="#session.currentUser.firstName" /><br/>
                    Last Name: <s:property value="#session.currentUser.lastName" /><br/>
                    Email: <s:property value="#session.currentUser.email" /><br/>
                    Password: <s:property value="#session.currentUser.password" /><br/>
                    Birthday: <s:property value="#session.currentUser.birthday" /><br/>
                    Gender: <s:property value="#session.currentUser.gender" /><br/>
                    <s:property value="profileString" />

                  </div>
    </body>
</html>