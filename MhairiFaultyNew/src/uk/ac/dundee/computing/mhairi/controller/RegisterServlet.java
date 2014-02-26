package uk.ac.dundee.computing.mhairi.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import uk.ac.dundee.computing.mhairi.lib.Dbutils;
import uk.ac.dundee.computing.mhairi.model.FaultBean;
import uk.ac.dundee.computing.mhairi.model.RegisterBean;
import uk.ac.dundee.computing.mhairi.storage.FaultStorage;
import uk.ac.dundee.computing.mhairi.storage.UserStorage;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(urlPatterns = { "/RegisterServlet"}, 
		initParams = { 
		@WebInitParam(name = "data-source", value = "jdbc/mhairitaylor")
})


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds = null;
   
    public RegisterServlet() {
        super();
       
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
		Dbutils db = new Dbutils();
		db.createSchema();

        ds=db.assemble(config);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In RegisterdoGet");
		Iterator<UserStorage> iterator;
		RegisterBean Users = new RegisterBean();
		Users.setDatasource(ds);
		LinkedList<UserStorage> psl = Users.getUsers();
		request.setAttribute("Users", psl);
		RequestDispatcher rd = request.getRequestDispatcher("/ViewReporters.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//  Make new FaultBean object
				uk.ac.dundee.computing.mhairi.model.RegisterBean InsertBean=new uk.ac.dundee.computing.mhairi.model.RegisterBean();
						
				// Get contents of text boxes on RegisterServlet.jsp
			 	String firstname = request.getParameter("fname");
			 	String lastname = request.getParameter("lname");
		   	 	String email = request.getParameter("email");
		   	 	String uname = request.getParameter("uname");
		   	 	String pass = request.getParameter("pass");
		   	 	String type = request.getParameter("type");
		   	
				// Shows message in console
			 	System.out.println("The doPost in RegisterServlet.java is working, yey!");
			 	
			 	// Calls method to insert into Database
			 	InsertBean.setData(firstname, lastname, email, uname, pass, type);
			 		 	
			 	// Redirects the user.
			 	RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			 	dispatcher.forward(request, response);
	}

}
