package controller.products;

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

import controller.PMF;
import model.entity.User;
import model.entity.Product;


@SuppressWarnings("serial")
public class ProductsControllerIndex extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User user = us.getCurrentUser();
		if(user==null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Products/index.jsp");
			rd.forward(req, resp);
		}else{
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String query1 = "select from " + User.class.getName() + " where email=='" + user.getEmail() +"'";

		List<User> useractive = (List<User>) pm.newQuery(query1).execute();
		
		User u1= useractive.get(0);
		
		String query2 = "select from " + Product.class.getName() + " where orgId==" + u1.getId();
		
		List<Product> u1products = (List<Product>)pm.newQuery(query2).execute();
		
		// pasar datos al jsp
		req.setAttribute("user", u1);
		req.setAttribute("products", u1products);

		// reenviar la solicitud al jsp
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Products/index.jsp");

		dispatcher.forward(req, resp);
		}
		
	}
	}