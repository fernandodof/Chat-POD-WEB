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
         if(request.getSession().getAttribute("login") == null){
             response.sendRedirect("index.jsp");
         }
         String login = request.getSession().getAttribute("login").toString();
         %>
         <h1>Ben vindo: ${login}</h1>
         <%
         if(request.getParameter("message") != null){
            String messageToserver = "-m@"+login+"&"+request.getParameter("message");
            chatManager.sendMessage(messageToserver);
         }
            
        ArrayList<String> clientMessages = chatManager.getClientMessages(login);
        pageContext.setAttribute("clientMessages", clientMessages);
        ArrayList<String> serverMessages = Server.getMessages();
            if(serverMessages != null){
                pageContext.setAttribute("serverMessages", serverMessages);
            }    
        pageContext.setAttribute("serverMessages", serverMessages);
        
        %>
        <div class id="part">
        <p class="ind">MENSAGENS ENVIADAS</p>
        <div>
            <c:forEach var="message" items="${clientMessages}">
                <p>${message}</p>
            </c:forEach>
        </div>
        </div>
        <div class id="part">
         <p class="ind">MENSAGENS RECEBIDAS</p>   
         <form>
            <input TYPE="button" onClick="history.go(0)" VALUE="Recarregar">
          </form>
         <c:if test="${serverMessages.size() != 0}">
         <div>    
          <c:forEach var="message" items="${serverMessages}">
                <p>${message}</p>
            </c:forEach>
         </div>
         </c:if>
         </div>
            <form method="POST" action="chat.jsp">
                <input type="text" placeholder="Mensagem" name="message"  autofocus="autofocus" id="message">
                <input type="submit" value="Enviar">
            </form>
            <form method="POST" action="logout">
                <input type="submit" value="Sair">
            </form>
    </body>
</html>