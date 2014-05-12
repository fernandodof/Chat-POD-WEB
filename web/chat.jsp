<%-- 
    Document   : chat
    Created on : 10-May-2014, 1:42:23 PM
    Author     : Fernando
--%>

<%@page import="chatServer.Server"%>
<%@page import="java.util.ArrayList"%>
<%@page import="manager.ChatManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
         ChatManager chatManager = ChatManager.getInstance();
            String login = request.getSession().getAttribute("login").toString();
            if(request.getParameter("message") != null){
                String messageToserver = "-m@"+login+"&"+(request.getParameter("message").toString());
                chatManager.sendMessage(messageToserver);
            }
            
        ArrayList<String> clientMessages = chatManager.getClientMessages();
        pageContext.setAttribute("clientMessages", clientMessages);
        ArrayList<String> serverMessages = Server.getMessages();
            if(serverMessages != null){
                pageContext.setAttribute("serverMessages", serverMessages);
            }    
        pageContext.setAttribute("serverMessages", serverMessages);
        %>
        <div id="clienteMessages" style="width:400px; height:400px; overflow: auto;">
            <c:forEach var="message" items="${clientMessages}">
                <p>${message}</p>
            </c:forEach>
        </div>
        <form method="POST" action="chat.jsp">
            <input type="text" placeholder="Mensagem" name="message">
            <input type="submit" value="Enviar">
        </form>
        <br>
        <p>MENSAGENS NO SERVIDOR</p>
        <div id="ServerMessages" style="width:400px; height:400px; overflow: auto;">
           <c:forEach var="message" items="${serverMessages}">
                <p>${message}</p>
            </c:forEach>
        </div>
        
        <form>
            <input TYPE="button" onClick="history.go(0)" VALUE="Recarregar">
        </form>
    </body>
</html>