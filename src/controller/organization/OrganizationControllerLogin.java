package controller.organization;

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
import controller.PMF;

@SuppressWarnings("serial")
public class OrganizationControllerLogin extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User user = us.getCurrentUser();
		if(user == null){
			resp.sendRedirect(us.createLoginURL("/users/login"));
		}else{
	
				
				PersistenceManager pm = PMF.get().getPersistenceManager();
				String query = "select from " + Organization.class.getName();
				List<Organization> organizaciones = (List<Organization>) pm.newQuery(query).execute();
				Organization orgencontrada=null;
				boolean existe=false;
				for(Organization search: organizaciones){
					if(search.getEmail().toLowerCase().equals(user.getEmail().toLowerCase())){
						existe=true;
						orgencontrada=search;
						break;	
					}
				}
				if(existe){		
					String mensaje2="Bienvenido ";
					req.setAttribute("user", user);
					req.setAttribute("EntityOrg", orgencontrada);
					req.setAttribute("message", mensaje2);
					RequestDispatcher var = getServletContext().getRequestDispatcher("index.jsp");
					var.forward(req, resp);
								
				}else{
					String mensaje3="Usted no esta registrado";
					req.setAttribute("message", mensaje3);
					RequestDispatcher var = getServletContext().getRequestDispatcher("index.jsp");
					}
				
		
				}
		
		}
	
	
}