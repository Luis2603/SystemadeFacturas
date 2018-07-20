<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.entity.User" %> 
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<% List<User> users= (List<User>)request.getAttribute("users"); %>
<%  UserService us = UserServiceFactory.getUserService();
	com.google.appengine.api.users.User user = us.getCurrentUser();
	boolean hayusuarioactivo=false;
	if(user != null){
		hayusuarioactivo=true;
	}else{
		hayusuarioactivo=false;
	} %>	
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SysFac</title>
</head>
<body>
	  <div class="menu">
              <ul class="nav nav-tabs" role="tablist">
                <li ><a href="/index.jsp" class="active">Inicio</a></li>
                <li ><a href="/documents">Comprobantes</a></li>
                <li ><a href="/products">Productos</a></li>
                <li ><a href="/users">Usuarios</a></li>
                <li ><a href="/users/add">Registrarse</a></li>
                 <%if(hayusuarioactivo){ %>
                <li ><a href="/users/login"><%= user.getEmail() %></a></li>
                <li ><a href="/users/logout">Salir</a></li>
                <% }else{ %>
                <li ><a href="/users/login">Iniciar Sesión</a></li>
                <% }%>
              </ul>
       </div>
       
       <div class="container">
        <h2>Este es el  Index de Usuarios </h2>
       	<% String message = (String)request.getAttribute("message"); %>
           <% if(message==null){ %>
           <h3></h3>
           <%}else{ %>
          <h3> <%=message %></h3>
          <% }%>
          
          <% if (users.size() > 0) { %>
			
			
			<table style="margin:5px; color: #000000; text-align: center; border-color: #efefef;"border="1px" width="80%">
				<tr style="background-color:green; color:white; margin: 15px; height:30px">
					<td>Id</td>
					<td>Correo del usuario</td>
					<td>Organización</td>
				
				</tr>
					<% for (int i = 0;i<users.size();i++) { %>
					<% User u = (User)users.get(i); %>
				<tr style="padding:20px background:#ffffff"onMouseOver="this.style.background='#ffffff';"onMouseOut="this.style.background='#efefef'; height:50px; ">
					<td><%= u.getId() %></td>
					<td><%= u.getEmail() %></td>
					<td><%= u.getOrganization() %></td>
					
				</tr>
					<% } %>
			</table>
			<% } else { %>
			<span class="heading">No hay Usuarios registrados</span>
			<% } %>
          
          
          
          
          
	   </div>
	   	  
          
          		
      </body>
		
</html>