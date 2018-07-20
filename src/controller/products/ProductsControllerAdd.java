package controller.products;
import java.io.IOException;

import controller.PMF;

import javax.jdo.PersistenceManager;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.util.List;

import model.entity.Product;
import model.entity.User;

@SuppressWarnings("serial")
public class ProductsControllerAdd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User user = us.getCurrentUser();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " + User.class.getName() + " where email=='"+ user.getEmail() +"'";
		List<User> usuarios = (List<User>) pm.newQuery(query).execute();
		
		User useractive= usuarios.get(0);
		
		req.setAttribute("useractive", useractive);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Products/add.jsp");
		rd.forward(req, resp);
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User user = us.getCurrentUser();
		
		
		int code_product = Integer.parseInt(req.getParameter("code_product"));
		String name_product = req.getParameter("name_product");
		Long orgId_product = Long.parseLong(req.getParameter("orgId_product"));
		String orgName_product = req.getParameter("orgName_product");
		double price_product = Double.parseDouble(req.getParameter("price_product")); 
		
		Product product1 = new Product(code_product, name_product, orgId_product, price_product,orgName_product);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			pm.makePersistent(product1);
		}finally{
			pm.close();
		}
		resp.sendRedirect("/products");
		
		}
	}