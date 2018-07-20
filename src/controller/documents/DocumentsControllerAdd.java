package controller.documents;
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
public class DocumentsControllerAdd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String query2 = "select from " + Product.class.getName();
		List<Product> productos = (List<Product>)pm.newQuery(query2).execute();
		
		req.setAttribute("productos", productos);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Documents/add.jsp");
		rd.forward(req, resp);
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
	}