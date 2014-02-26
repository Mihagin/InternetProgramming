package uk.ac.dundee.computing.mhairi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import uk.ac.dundee.computing.mhairi.storage.LoginStorage;

public class LoginBean {

	Context envContext = null;

	private List<LoginStorage> storeList = new ArrayList<LoginStorage>();

	public void setData(String UserName, String Password) {

	}

	public List<LoginStorage> verifyUser() {
		try {
			envContext = new InitialContext();
			Context initContext = (Context) envContext.lookup("java:/comp/env");
			DataSource _ds = (DataSource) initContext
					.lookup("jdbc/mhairitaylor");
			Connection conn = _ds.getConnection();
			System.out
					.println("Connected to the database in the LoginServlet!");

			Statement st = null;
			ResultSet rs = null;

			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM users");
			try {
				System.out.println("Query ");
				
				if (rs == null) 
				{
					System.out.println("result set is empty ");
					
					conn.close();
					return null;
				}
				
				System.out.println("While..... ");
				
				while (rs.next()) 
				{
					LoginStorage userList = new LoginStorage();

					userList.setUserN(rs.getString("UserName"));

					userList.setPassW(rs.getString("Password"));
					
					userList.setType(rs.getString("Type"));

					storeList.add(userList);
					System.out.println("Added to storelist");

				}
				rs.close();
				conn.close();
			} catch (Exception E) {
				System.out.println("Can't iterate result set: " + E);
			}

		} catch (Exception ex) {

			System.out.println("Not inserting data in the database, boo!");

		}

		return storeList;

	}
}
