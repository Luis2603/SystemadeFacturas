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
public class ProductsControllerEdit extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String ID = req.getParameter("productId");
		Long productid = new Long(Long.parseLong(ID));
		try{
			Product edit;
			Key kprod = KeyFactory.createKey(Product.class.getSimpleName(),productid);
			edit=pm.getObjectById(Product.class, kprod);
			req.setAttribute("editable", edit);
		}finally{
			pm.close();
		}			
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Products/edit.jsp");
		dispatcher.forward(req, resp);
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		int code_product = Integer.parseInt(req.getParameter("code_product"));
		String name_product = req.getParameter("name_product");

		double price_product = Double.parseDouble(req.getParameter("price_product"));
		Long productid = Long.parseLong(req.getParameter("id_product"));
		
		try{
			Product update;
			Key kprod = KeyFactory.createKey(Product.class.getSimpleName(),productid);
			update=pm.getObjectById(Product.class, kprod);
				update.setCode(code_product);
				update.setName(name_product);
				update.setPrice(price_product);
		
		
		}finally{
			pm.close();
		}
		resp.sendRedirect("/products");
		
	}
	
}