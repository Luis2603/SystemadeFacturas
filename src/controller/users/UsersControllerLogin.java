package controller.users;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


import model.entity.Organization;
import model.entity.User;
import controller.PMF;

@SuppressWarnings("serial")
public class UsersControllerLogin extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User user = us.getCurrentUser();
		if(user == null){
			resp.sendRedirect(us.createLoginURL("/users/login"));
		}else{
	
				
				PersistenceManager pm = PMF.get().getPersistenceManager();
				String query = "select from " + User.class.getName();
				List<User> usuarios = (List<User>) pm.newQuery(query).execute();
				User userencontrado=null;
				boolean existe=false;
				for(User search: usuarios){
					if(search.getEmail().toLowerCase().equals(user.getEmail().toLowerCase())){
						existe=true;
						userencontrado=search;
						break;	
					}
				}
				if(existe){		
					String mensaje2="Bienvenido "+ userencontrado.getEmail();
					req.setAttribute("user", user);
					
					req.setAttribute("message", mensaje2);
					RequestDispatcher var = getServletContext().getRequestDispatcher("/WEB-INF/Views/Users/login.jsp");
					var.forward(req, resp);
								
				}else{
					String mensaje3="Usted no esta registrado";
					
					req.setAttribute("message", mensaje3);
					resp.sendRedirect("/users/logout");
					
					}
				
		
				}
		
		}
	
	
}