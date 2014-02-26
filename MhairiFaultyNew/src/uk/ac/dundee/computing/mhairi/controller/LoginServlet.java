package uk.ac.dundee.computing.mhairi.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import uk.ac.dundee.computing.mhairi.lib.*;
import uk.ac.dundee.computing.mhairi.model.*;
import uk.ac.dundee.computing.mhairi.storage.LoginStorage;

@WebServlet(urlPatterns = { "/LoginServlet", "/Login/*" }, initParams = { @WebInitParam(name = "data-source", value = "jdbc/mhairitaylor") })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DataSource _ds = null;

	public LoginServlet() {
		super();

	}

	@Override
	public void init(ServletConfig config) throws ServletException {

		Dbutils db = new Dbutils();
		db.createSchema();
		_ds = db.assemble(config);

		System.out.println("This is the initbit method of the LoginServlet");
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("This is the doPost method of the LoginServlet");

		// Start session stuff
		HttpSession session = request.getSession();

		// Make new LoginBean object
		uk.ac.dundee.computing.mhairi.model.LoginBean LoginBean = new uk.ac.dundee.computing.mhairi.model.LoginBean();

		// Get User Name and Password from the user input
		String UserName = request.getParameter("uname");
		String Password = request.getParameter("pass");

		// Make a list of logins/passwords to check against
		// (could this be done better? Only pulling from DB where
		// UserName=?Password=? Go back to this!
		List<LoginStorage> loginData = LoginBean.verifyUser();

		// Iterate through list
		Iterator<LoginStorage> it = loginData.iterator();

		int iter = 0;

		while (it.hasNext()) {
			LoginStorage item = new LoginStorage();
			item = it.next();

			String NAME = item.getUserN();
			String PASS = item.getPassW();
			String TYPE = item.getType();

			if (NAME.equals(UserName)) {

				System.out.println("Your username " + NAME + " is correct.");

				if (PASS.equals(Password)) {
					System.out.println("You have been logged in :]");
					session.setAttribute("userid", UserName);
					}
			else {
				System.out
						.println("Your password is wrong....");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("Login.jsp");
				dispatcher.forward(request, response);
			}
				RequestDispatcher dispatcher = request.getRequestDispatcher("AddFault.jsp");
				dispatcher.forward(request, response);
				break;
			}
				
		}

		iter++;
	}

}
