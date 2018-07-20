package controller.documents;

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
import model.entity.Document;


@SuppressWarnings("serial")
public class DocumentsControllerView extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String ID = req.getParameter("documentId");
		Long documentid = new Long(Long.parseLong(ID));
		try{
			Document view;
			Key kdoc = KeyFactory.createKey(Document.class.getSimpleName(),documentid);
			view=pm.getObjectById(Document.class, kdoc);
			req.setAttribute("vista", view);
		}finally{
			pm.close();
		}			
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Documents/view.jsp");
		dispatcher.forward(req, resp);
		
	}
	}