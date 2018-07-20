package controller.organization;
import java.io.IOException;

import controller.PMF;

import javax.jdo.PersistenceManager;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.util.List;

import model.entity.*;

@SuppressWarnings("serial")
public class OrganizationControllerAdd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Organization/add.jsp");
		rd.forward(req, resp);
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String org_name=req.getParameter("nombreorg");
		String org_email = req.getParameter("email");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query1 = "select from " + Organization.class.getName();
		List<Organization>orgs = (List<Organization>) pm.newQuery(query1).execute();
		boolean existe=false;
		if(orgs.size()>0){
			
			for(Organization orgsearch: orgs){
				if(orgsearch.getName().toLowerCase().equals(org_name.toLowerCase())&&orgsearch.getEmail().toLowerCase().equals(org_email)){
					existe=true;
					break;
				}
			}
			if(existe){
				String message = "El nombre de la organizacion con ese correo ya existe";
				req.setAttribute("message_for_existing", message);
				RequestDispatcher dp= getServletContext().getRequestDispatcher("/WEB-INF/Views/Organization/add.jsp");
				
			}else{
				Organization org1= new Organization(org_name, org_email);
				try {
					pm.makePersistent(org1);
					
				}finally{
					pm.close();
				}
				resp.sendRedirect("index.html");
			}
			
		}else{
				Organization created=new Organization(org_name, org_email);
				try {
					pm.makePersistent(created);
				}finally{
					pm.close();
				}
				resp.sendRedirect("index.html");
			}
		}
	}