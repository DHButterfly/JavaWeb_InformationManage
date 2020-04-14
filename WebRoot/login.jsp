<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>欢迎登录后台管理系统</title>
    <link href="login/css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="login/js/jquery.js"></script>
    <script src="login/js/cloud.js" type="text/javascript"></script>

    <script language="javascript">
        $(function () {
            $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            $(window).resize(function () {
                $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            })
        });
    </script>

</head>

<body style="background-color:#df7611; background-image:url(login/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
    <span>欢迎登录后台管理界面平台</span>
</div>

<div class="loginbody">

    <span class="systemlogo"></span>
    <br/>
    <!--使用JSTL和EI表达式完成提示语-->
    <c:choose>
        <c:when test="${requestScope.flag==0}">
            <div style="text-align: center;">
               <span style="font-size: 15px;color: darkred;font-weight: bold;">用户名或密码错误</span>
            </div>
        </c:when>
                <c:when test="${sessionScope.flag==1}">
            <div style="text-align: center;">
               <span style="font-size: 15px;color: darkred;font-weight: bold;">密码修改成功</span>
            </div>
        </c:when>
                <c:when test="${sessionScope.flag==2}">
            <div style="text-align: center;">
               <span style="font-size: 15px;color: darkred;font-weight: bold;">注册成功</span>
            </div>
        </c:when>
    </c:choose>
    <c:remove var="flag" scope="session"/>
    <!--传统方式获取提示语-->
    <%--
    <%
        //声明java代码块进行错误提示语的逻辑校验
        Object flag = request.getAttribute("flag");
        if (flag != null) {
    %>
    <div style="text-align: center;">
        <span style="font-size: 15px;color: darkred;font-weight: bold;">用户名或密码错误</span>
    </div>
    <% }
    %>
    <%
        //声明java代码块进行密码修改提示语的逻辑校验
        Object pwd = session.getAttribute("pwd");
        if (pwd != null) {
    %>
    <div style="text-align: center;">
        <span style="font-size: 15px;color: darkred;font-weight: bold;">密码修改成功</span>
    </div>
    <% }
        session.removeAttribute("pwd");
    %>

    <%
        //声明java代码块进行注测提示语的逻辑校验
        Object reg = session.getAttribute("reg");
        if (reg != null) {
    %>
    <div style="text-align: center;">
        <span style="font-size: 15px;color: darkred;font-weight: bold;">注册成功</span>
    </div>
    <% }
        session.removeAttribute("reg");
    %>
    --%>
    <div class="loginbox loginbox1">
        <form action="user" method="post">
            <input type="hidden" name="oper" value="login"/>
            <ul>
                <li><input name="uname" type="text" class="loginuser" placeholder="用户名"/></li>
                <li><input name="pwd" type="password" class="loginpwd" placeholder="密码"/></li>
                <li class="yzm">
                <span><input name="" type="text" value="验证码"
                             onclick="JavaScript:this.value=''"/></span><cite>X3D5S</cite>
                </li>
                <li><input name="" type="submit" class="loginbtn" value="登录"
                           onclick="javascript:window.location='main.html'"/>
                    <label><a href="user/reg.jsp">注册</a></label>
                    <label><a href="#">忘记密码？</a></label></li>
            </ul>
        </form>
    </div>
</div>
</body>
</html>

