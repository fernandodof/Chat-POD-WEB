<%-- 
    Document   : index
    Created on : 2-May-2014, 9:13:53 AM
    Author     : Fernando
--%>
<%@page import="chatServer.Server"%>
<%@page import="manager.ChatManager"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chat POD</title>
        <link href="/ProjetoChatPodWeb/cssGeral.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <%  if(request.getSession().getAttribute("login") != null){
                response.sendRedirect("chat.jsp");
            }
        %>
        
        <h1>Chat POD</h1>

        <form method="POST" action="ComunicateServer">
            <input type="text" placeholder="Host" name="host"><br>
            <input type="text" placeholder="Port" name="port"><br>
            <input type="text" placeholder="Login" name="login"><br>
            <input type="text" placeholder="Senha" name="password"><br>
            <input type="Submit" value="Entrar">
        </form>

        <%
            ChatManager chatManager = ChatManager.getInstance();
            ArrayList<String> serverMessages = Server.getMessages();
            pageContext.setAttribute("serverMessages", serverMessages);
        %>
        <div class id="part">
        <div>
          <p class="ind">MENSAGENS RECEBIDAS</p>  
          <form>
            <input TYPE="button" onClick="history.go(0)" VALUE="Recarregar">
          </form>
          <c:forEach var="message" items="${serverMessages}">
                <p>${message}</p>
            </c:forEach>
        </div>
        </div>
    </body>

</html>