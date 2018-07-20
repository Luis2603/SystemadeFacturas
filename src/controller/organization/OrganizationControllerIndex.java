package controller.organization;

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
import model.entity.Organization;

@SuppressWarnings("serial")
public class OrganizationControllerIndex extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		String query = "select from " + Organization.class.getName();

		List<Organization> users = (List<Organization>) pm.newQuery(query).execute();

		// pasar la lista al jsp
		req.setAttribute("users", users);

		// reenviar la solicitud al jsp
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Organization/index.jsp");

		dispatcher.forward(req, resp);

	}
}