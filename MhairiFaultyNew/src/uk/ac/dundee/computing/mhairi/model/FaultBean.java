package uk.ac.dundee.computing.mhairi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import uk.ac.dundee.computing.mhairi.storage.FaultStorage;


public class FaultBean {

	private DataSource ds = null;
	Context envContext = null;

	public void setDatasource(DataSource ds) {
		this.ds = ds;
		System.out.println("Set Data Source in Model" + ds.toString());
	}
	
	
	
	public void setData(String description, int reportedBy, String severity) {

		try {
			envContext = new InitialContext();
			Context initContext = (Context) envContext.lookup("java:/comp/env");
			DataSource _ds = (DataSource) initContext
					.lookup("jdbc/mhairitaylor");
			Connection conn = _ds.getConnection();
			System.out.println("Connected to the database!");

			PreparedStatement pmst = null;

			String query = "INSERT INTO fault(Description, ReportedBy, Severity, Date) VALUES (?,?,?,NOW())";
			try {
				pmst = conn.prepareStatement(query);
				pmst.setString(1, description);
				pmst.setInt(2, reportedBy);
				pmst.setString(3, severity);
				pmst.executeUpdate();
				System.out
						.println("You have successfully input the data, yey!");

			} catch (Exception ex) {

				System.out.println("Not inserting data in the database, boo!");
				return;
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (NamingException e1) {

			e1.printStackTrace();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
	}


public LinkedList<FaultStorage> getFaults() {
		
		LinkedList<FaultStorage> psl = new LinkedList<FaultStorage>();
		Connection Conn;
		FaultStorage ps = null;
		ResultSet rs = null;
		try {
			Conn = ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Faults Model");
			return null;
		}

		PreparedStatement pmst = null;
		Statement stmt = null;
		String sqlQuery = "select FaultID, Description, ReportedBy, Severity from fault";
		System.out.println("Fault Query " + sqlQuery);
		try {
			try {
			 pmst = Conn.prepareStatement(sqlQuery);
				stmt = Conn.createStatement();
			} catch (Exception et) {
				System.out.println("Can't create prepare statement");
				return null;
			}
			System.out.println("Created prepare");
			try {
				 rs=pmst.executeQuery();
				rs = stmt.executeQuery(sqlQuery);
			} catch (Exception et) {
				System.out.println("Can not execut query " + et);
				return null;
			}
			System.out.println("Statement executed");
			if (rs.wasNull()) {
				System.out.println("result set was null");
			} else {
				System.out.println("Well it wasn't null");
			}
			while (rs.next()) {
				System.out.println("Getting RS");
				ps = new FaultStorage();
				
				ps.setFaultID(rs.getInt("FaultID"));
				ps.setDescription(rs.getString("Description"));
				ps.setReportedBy(rs.getInt("ReportedBy"));
				ps.setSeverity(rs.getString("Severity"));
				psl.add(ps);
			}
		} catch (Exception ex) {
			System.out.println("Opps, error in query " + ex);
			return null;
		}

		try {
			
			Conn.close();
		} catch (Exception ex) {
			return null;
		}
		return psl;

	}
}
