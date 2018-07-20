<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.entity.Product" %> 
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<% String ruc=(String) request.getAttribute("ruc"); %>
<% String dni=(String) request.getAttribute("dni"); %>
<% String businessReason = (String) request.getAttribute("businessReason"); %>
<% String direction = (String) request.getAttribute("direction"); %>
<% Double amount = (Double) getServletContext().getAttribute("amount"); %>
<% ArrayList<Integer> cifras = (ArrayList<Integer>) getServletContext().getAttribute("cifras"); %>
<% List<Product> productos = (List<Product>) request.getAttribute("productos"); %>
<% ArrayList<Product> carrito = (ArrayList<Product>) getServletContext().getAttribute("carrito"); %>
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
       
       <form method="post" action="/documents/add/queue">
	       <input type="text" name="ruc" placeholder="Ingrese su Ruc" value="<%= ruc%>" required>
	       <br>
	       <input type="text" name="dni" placeholder="Ingrese su DNI" value="<%= dni%>" required>
	       <br>
	       <input type="text" name="businessReason" placeholder="Ingrese su Razon Social" value="<%= businessReason%>" required>
	       <br>
	       <input type="text" name="direction" placeholder="Ingrese su Dirección" value="<%= direction%>" required>
	       <br>
	       <label>Escoga su producto</label>
	       <br>
	       <select name="id_product">
	       <%for(Product p: productos){ %>
	       			<option value="<%=p.getId() %>">Codigo: <%=p.getCode() %>|   Nombre del producto: <%=p.getName()%>  |  Precio: <%=p.getPrice() %> </option>	
	       <%} %>
	      
	       </select>
	       <br>
	        <input type="text" name="cantidad" placeholder="numero de productos" required>
	        <br>
	        <label>SUBTOTAL</label>
	       	<input type="text" name="amount" value="<%= amount %>" disabled>
	       	<br>
	       	<label>IGV</label>
	       	<input type="text" name="igv" value="<%= amount*0.18 %>" disabled>
	       	<br>
	       	<label>TOTAL</label>
	       	<input type="text" name="igv" value="<%= amount*1.18 %>" disabled>
	       	<br>
	       <input type="submit" value="Añadir a la lista">
       
       
       </form>
       
       		<% if (carrito.size() > 0) { %>
       		
       		<table style="margin:5px; color: #000000; text-align: center; border-color: #efefef;"border="1px" width="80%">
				<tr style="background-color:green; color:white; margin: 15px; height:30px">
					<td>#</td>
					<td>Id</td>
					<td>Codigo: </td>
					<td>Nombre del producto: </td>
					<td>Unidades: </td>
					<td>Precio</td>
					<td>Organización: </td>
					<td>Eliminar de la lista</td>
					
					
				
				</tr>
					<% for (int i = 0;i<carrito.size();i++) { %>
					<% Product p = (Product)carrito.get(i); %>
				<tr style="padding:20px background:#ffffff"onMouseOver="this.style.background='#ffffff';"onMouseOut="this.style.background='#efefef'; height:50px; ">
					<td><%=i %></td>
					<td><%= p.getId() %></td>
					<td><%= p.getCode() %></td>
					<td><%= p.getName() %></td>
					<td><%=cifras.get(i) %></td>
					<td><%= p.getPrice() %></td>
					<td><%= p.getOrgName() %></td>
					<td><a href="#" alt ="No pude implementar esta funcionalidad, ya que la unica forma es con AJAX">Eliminar</a></td>
				
				
				</tr>
					<% } %>
			</table>
			<% } else { %>
			<span>Aún no ha añadido ´productos a su compra</span>
			
			
			<% } %>
       
       		<a href="/documents/add/queue?ruc=<%=ruc%>&dni=<%=dni%>&businessReason=<%=businessReason%>&direction=<%=direction%>&amount=<%=amount*1.18%>">Crear recibo</a>
       
       
       
	   </div>
	   	  
	   	  	
      </body>
		
</html>