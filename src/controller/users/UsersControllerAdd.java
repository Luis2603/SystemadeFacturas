package controller.users;
import java.io.IOException;

import controller.PMF;

import javax.jdo.PersistenceManager;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.util.List;

import model.entity.*;
import model.entity.User;

@SuppressWarnings("serial")
public class UsersControllerAdd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Users/add.jsp");
		rd.forward(req, resp);
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String org_name=req.getParameter("nombreorg");
		String org_email = req.getParameter("email");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query1 = "select from " + User.class.getName();
		List<User>usuarios = (List<User>) pm.newQuery(query1).execute();
		boolean existe=false;
		if(usuarios.size()>0){
			
			for(User usersearch: usuarios){
				if(usersearch.getOrganization().toLowerCase().equals(org_name.toLowerCase())&&usersearch.getEmail().toLowerCase().equals(org_email)){
					existe=true;
					break;
				}
			}
			if(existe){
				resp.setContentType("text/html");
			    resp.setCharacterEncoding("UTF-8");
			    resp.getWriter().print("<!Doctype html>");
			    resp.getWriter().print("<html>");
			    resp.getWriter().print("<head>");
			    resp.getWriter().print("<meta charset='UTF-8'>");
			    resp.getWriter().print("<meta http-equiv='refresh' content='3;/users/add'>");
			    resp.getWriter().print("</head>");
			    resp.getWriter().print("<body>");
			    resp.getWriter().print("<h1>Este nombre para su organizacion con este correo ya existe</h1>");
			    resp.getWriter().print("</body>");
			    resp.getWriter().print("</html>");
				
			}else{
				User user1= new User(org_email, org_name);
				try {
					pm.makePersistent(user1);
					
				}finally{
					pm.close();
				}
				resp.sendRedirect("/users");
			}
			
		}else{
				User created=new User(org_email, org_name);
				try {
					pm.makePersistent(created);
				}finally{
					pm.close();
				}
				
				resp.sendRedirect("/users");
			}
		}
	}