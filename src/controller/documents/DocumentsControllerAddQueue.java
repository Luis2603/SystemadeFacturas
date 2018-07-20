package controller.documents;
import java.io.IOException;

import controller.PMF;

import javax.jdo.PersistenceManager;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.util.ArrayList;
import java.util.List;

import model.entity.Product;
import model.entity.Document;


@SuppressWarnings("serial")
public class DocumentsControllerAddQueue extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String am=req.getParameter("amount");
		Double amount= Double.parseDouble(am);
		ArrayList<Product> carrito = (ArrayList<Product>)getServletContext().getAttribute("carrito");
		carrito.clear();
		getServletContext().setAttribute("carrito", carrito);
	
	
		Document doc1=new Document(req.getParameter("ruc"),
							   req.getParameter("dni"),
							   req.getParameter("businessReason"),
							   req.getParameter("direction"),
							   amount);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
			try{
				pm.makePersistent(doc1);
			}finally{
			pm.close();
			}
			resp.sendRedirect("/documents");
		
	}	

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String ruc=req.getParameter("ruc");
		String dni=req.getParameter("dni");
		String businessReason= req.getParameter("businessReason");
		String direction=req.getParameter("direction");
		
		
		Long id_product=Long.parseLong(req.getParameter("id_product"));
		String queryp="select from " + Product.class.getName() + " where id==" + id_product ;
		List<Product> producto= (List<Product>) pm.newQuery(queryp).execute();
		Product para_añadir= producto.get(0);
		
		String query2="select from " + Product.class.getName();
		List<Product> productos= (List<Product>) pm.newQuery(query2).execute();
		
		if(getServletContext().getAttribute("carrito")!=null){
			ArrayList<Product> carrito = (ArrayList<Product>) getServletContext().getAttribute("carrito");
			carrito.add(para_añadir);
			getServletContext().setAttribute("carrito", carrito);
		}else{
			ArrayList<Product> carrito = new ArrayList<Product>();
			carrito.add(para_añadir);
			getServletContext().setAttribute("carrito", carrito);
		}
		
		Integer cantidad=Integer.parseInt(req.getParameter("cantidad"));
		Double price = para_añadir.getPrice();
		
		double amount_new= price*cantidad;
		
		if(getServletContext().getAttribute("amount")!=null){
			double amount_total = (double) getServletContext().getAttribute("amount");
			amount_total = amount_total+amount_new;
			getServletContext().setAttribute("amount", amount_total);
		}else{
			double amount_total = 0;
			amount_total = amount_total+amount_new;
			getServletContext().setAttribute("amount", amount_total);
		}
		if(getServletContext().getAttribute("cifras")!=null){
			ArrayList<Integer> cifras = (ArrayList<Integer>) getServletContext().getAttribute("cifras");
			cifras.add(cantidad);
			getServletContext().setAttribute("cifras", cifras);
		}else{
			ArrayList<Integer> cifras = new ArrayList<Integer>();
			cifras.add(cantidad);
			getServletContext().setAttribute("cifras", cifras);
		}
		
		
		req.setAttribute("ruc", ruc);
		req.setAttribute("dni", dni);
		req.setAttribute("businessReason", businessReason);
		req.setAttribute("direction", direction);
		req.setAttribute("productos", productos);
	
		
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Views/Documents/addqueue.jsp");
		rd.forward(req, resp);
	}
	}