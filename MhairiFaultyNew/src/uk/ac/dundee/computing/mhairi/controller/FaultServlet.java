package uk.ac.dundee.computing.mhairi.controller;

import java.io.IOException;
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
import uk.ac.dundee.computing.mhairi.model.FaultBean;

import uk.ac.dundee.computing.mhairi.storage.FaultStorage;

@WebServlet(urlPatterns = { "/FaultServlet", "/Faults/*" }, initParams = { @WebInitParam(name = "data-source", value = "jdbc/mhairitaylor") })
public class FaultServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DataSource ds = null;

	public FaultServlet() {
		super();

	}

	public void init(ServletConfig config) throws ServletException {

		Dbutils db = new Dbutils();
		db.createSchema();

		ds = db.assemble(config);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("In doGet");
		Iterator<FaultStorage> iterator;
		FaultBean Faults = new FaultBean();
		Faults.setDatasource(ds);
		LinkedList<FaultStorage> psl = Faults.getFaults();
		request.setAttribute("Faults", psl);
		RequestDispatcher rd = request.getRequestDispatcher("/ViewFaults.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Make new FaultBean object
		uk.ac.dundee.computing.mhairi.model.FaultBean InsertBean = new uk.ac.dundee.computing.mhairi.model.FaultBean();

		// Get content of description box
		String description = request.getParameter("txtDescription");

		// Get content of reportedBy box and cast as an int
		String temp = request.getParameter("txtReportedBy");
		int reportedBy = Integer.parseInt(temp);

		// Get content of severity selection box
		String severity = request.getParameter("txtSeverity");

		// Shows message in console
		System.out.println("The doPost in Fault.java is working, yey!");

		// Calls method to insert into Database
		InsertBean.setData(description, reportedBy, severity);

		// Redirects the user.
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("FaultAdded.jsp");
		dispatcher.forward(request, response);

	}

}