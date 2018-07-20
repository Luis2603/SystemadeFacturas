package controller.products;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


import controller.PMF;

import model.entity.Product;


@SuppressWarnings("serial")
public class ProductsControllerView extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String ID = req.getParameter("productId");
		Long productid = new Long(Long.parseLong(ID));
		try{
			Product view;
			Key kprod = KeyFactory.createKey(Product.class.getSimpleName(),productid);
			view=pm.getObjectById(Product.class, kprod);
			req.setAttribute("vista", view);
		}finally{
			pm.close();
		}			
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Products/view.jsp");
		dispatcher.forward(req, resp);
		
	}
	}