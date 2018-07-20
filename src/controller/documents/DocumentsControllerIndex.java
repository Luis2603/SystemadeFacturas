package controller.documents;

import java.io.IOException;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.User;
import model.entity.Document;


@SuppressWarnings("serial")
public class DocumentsControllerIndex extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		String query = "select from " + Document.class.getName();

		List<Document> documents = (List<Document>) pm.newQuery(query).execute();

		// pasar la lista al jsp
		req.setAttribute("documents", documents);

		// reenviar la solicitud al jsp
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Documents/index.jsp");

		dispatcher.forward(req, resp);

	}
}