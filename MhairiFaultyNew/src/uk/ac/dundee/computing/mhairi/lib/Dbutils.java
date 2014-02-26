package uk.ac.dundee.computing.mhairi.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.sql.DataSource;

/* DButils class but changed so no hard coded values are inbuilt in database.*/

public class Dbutils {

	private static final void listContext(Context ctx, String indent) {
		try {
			@SuppressWarnings("rawtypes")
			NamingEnumeration list = ctx.listBindings("");
			while (list.hasMore()) {
				Binding item = (Binding) list.next();
				String className = item.getClassName();
				String name = item.getName();
				System.out.println("" + className + " " + name);
				Object o = item.getObject();
				if (o instanceof javax.naming.Context) {
					listContext((Context) o, indent + " ");

				}
			}
		} catch (NamingException ex) {
			System.out.println("JNDI failure: " + ex);
		}
	}

	/**
	 * Assembles a DataSource from JNDI.
	 */
	public DataSource assemble(ServletConfig config) throws ServletException {
		DataSource _ds = null;
		String dataSourceName = config.getInitParameter("data-source");
		System.out.println("Data Source Parameter " + dataSourceName);
		if (dataSourceName == null)
			throw new ServletException("data-source must be specified");
		Context envContext = null;
		try {
			Context ic = new InitialContext();
			System.out.println("initial context " + ic.getNameInNamespace());
			envContext = (Context) ic.lookup("java:/comp/env");
			System.out.println("envcontext  " + envContext);
			listContext(envContext, "");
		} catch (Exception et) {
			throw new ServletException("Can't get contexts " + et);
		}
		try {
			_ds = (DataSource) envContext.lookup(dataSourceName);

			if (_ds == null)
				throw new ServletException(dataSourceName
						+ " is an unknown data-source.");
		} catch (NamingException e) {
			throw new ServletException("Cant find datasource name "
					+ dataSourceName + " Error " + e);
		}
		System.out.println("Making sure the correct tables are there....");
		CreateSchema(_ds);
		return _ds;

	}

	// Create the schema if it doesn't exist
	private void CreateSchema(DataSource _ds) {
		PreparedStatement pmst = null;
		Connection Conn;
		try {
			Conn = _ds.getConnection();
			System.out
					.println("Connected to the database using datasource object.. ");
		} catch (Exception et) {
			return;
		}

		String sqlQuery = "CREATE TABLE IF NOT EXISTS `fault` ("
				+ "`FaultID` INT NOT NULL AUTO_INCREMENT,"
				+ "`Description` VARCHAR(45) NULL,"
				+ "`ReportedBy` INT NOT NULL," + "`Severity` VARCHAR(45) NULL,"
				+ "`Date` VARCHAR(45) NULL," + "PRIMARY KEY (`FaultID`))"
				+ "ENGINE = InnoDB;";
		try {
			pmst = Conn.prepareStatement(sqlQuery);
			pmst.executeUpdate();
			System.out.println("Fault table is there, yey! ");
		} catch (Exception ex) {
			System.out.println("Cannot create Faults table " + ex);
			return;
		}

		sqlQuery = "CREATE TABLE IF NOT EXISTS `users` ("
				+ "`UserID` INT NOT NULL AUTO_INCREMENT,"
				+ "`First_Name` VARCHAR(45) NULL," 
				+ "`Second_Name` VARCHAR(45) NULL,"
				+ "`Email` VARCHAR(45) NOT NULL,"
				+ "`UserName` VARCHAR(45) NOT NULL,"
				+ "`Password` VARCHAR(45) NOT NULL,"
				+ "`JoinDate` VARCHAR(45) NULL,"
				+ "`Type` VARCHAR(45) NULL,"
				+ "PRIMARY KEY (`UserID`))" + "ENGINE = InnoDB;";
		try {
			pmst = Conn.prepareStatement(sqlQuery);
			pmst.executeUpdate();
			System.out.println("Users table is there, yey! ");
		} catch (Exception ex) {
			System.out.println("Cannot create User table. " + ex);
			return;
		}

	}

	public void createSchema() {
		String url = "jdbc:mysql://localhost";
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, "mhairitaylor", "acc323;");

		} catch (Exception et) {
			System.out.println("Can't get connection to create schema " + et);
			return;
		}
		String sqlcreateSchema = "Create database if not exists mhairitaylor ;";
		try {
			java.sql.Statement statement = conn.createStatement();
			statement.execute(sqlcreateSchema);
			conn.close();
		} catch (Exception et) {
			System.out.println("Cannot create schema ");
			return;
		}

	}


}