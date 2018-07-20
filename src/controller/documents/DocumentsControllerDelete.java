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

import model.entity.Document;


@SuppressWarnings("serial")
public class DocumentsControllerDelete extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String ID = req.getParameter("documentId");
		Long documentid = new Long(Long.parseLong(ID));
		try{
			Document delete;
			Key kdoc = KeyFactory.createKey(Document.class.getSimpleName(),documentid);
			delete=pm.getObjectById(Document.class, kdoc);
			pm.deletePersistent(delete);
			
		}finally{
			pm.close();
		}			
		resp.sendRedirect("/documents");
		
	}
	}