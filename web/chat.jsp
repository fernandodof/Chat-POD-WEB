<%-- 
    Document   : chat
    Created on : 10-May-2014, 1:42:23 PM
    Author     : Fernando
--%>

<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
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
        <link href="/ProjetoChatPodWeb/cssGeral.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <%
         ChatManager chatManager = ChatManager.getInstance();
         String login = request.getSession().getAttribute("login").toString();
         if(request.getParameter("message") != null){
            String messageToserver = "-m@"+login+"&"+request.getParameter("message");
            chatManager.sendMessage(messageToserver);
         }
            
        List clientMessages = Collections.synchronizedList(new ArrayList()); 
        clientMessages = chatManager.getClientMessages();
        pageContext.setAttribute("clientMessages", clientMessages);
        ArrayList<String> serverMessages = Server.getMessages();
            if(serverMessages != null){
                pageContext.setAttribute("serverMessages", serverMessages);
            }    
        pageContext.setAttribute("serverMessages", serverMessages);
        
        %>
        <div>
            <p class="ind">MENSAGENS ENVIADAS</p>  
            <c:forEach var="message" items="${clientMessages}">
                <p>${message}</p>
            </c:forEach>
        </div>
        <div>
          <p class="ind">MENSAGENS RECEBIDAS</p>  
          <form>
            <input TYPE="button" onClick="history.go(0)" VALUE="Recarregar">
          </form>
          <c:forEach var="message" items="${serverMessages}">
                <p>${message}</p>
            </c:forEach>
        </div>
            <form method="POST" action="chat.jsp">
                <input type="text" placeholder="Mensagem" name="message">
                <input type="submit" value="Enviar">
            </form>
            <form method="POST" action="logout">
                <input type="submit" value="Sair">
            </form>
    </body>
</html>