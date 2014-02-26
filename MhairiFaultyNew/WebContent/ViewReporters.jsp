<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="uk.ac.dundee.computing.mhairi.storage.UserStorage"%>

<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<link rel="stylesheet" href="Style.css" type="text/css">
<!-- TemplateBeginEditable name="doctitle" -->
<title>Mr Faulty | Mhairi Taylor</title>

<link href="Style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="wrapper row1">
		<header id="header" class="clear">
			<hgroup>
				<h1>Mr Faulty</h1>
			</hgroup>
			<nav>
				<ul>
					<%
						if ((session.getAttribute("userid") == null)
								|| (session.getAttribute("userid") == "")) {
					%>
					<li><a href="Login.jsp">Login</a></li>
					<%
						} else {
					%>
					Logged in as:
					<%=session.getAttribute("userid")%>
					<li><a href="Logout.jsp">| Logout</a></li>
					<%
						}
					%>

				</ul>
			</nav>
			<div class="clear"></div>
		</header>
	</div>
	<!-- content -->
	<div class="wrapper row2">
		<div id="container">
			<!-- content body -->
			<aside id="left_column">
				<h2 class="title">Navigation</h2>

				<nav>
					<ul>
						<li><a href="Index.jsp">Home</a></li>
						<li><a href="AddFault.jsp">Report A Fault</a></li>
						<li><a href="/MhairiFaultyNew/FaultServlet">View All Faults</a></li>
						<li><a href="/MhairiFaultyNew/RegisterServlet">View All Users</a></li>
					</ul>
				</nav>
			</aside>
			<!-- main content -->
			<div id="content">
				<!-- section 1 -->
				<section>
					<!-- article 1 -->
					<article>
						<h2>Fault Reporting System</h2><p>
						<%
							if ((session.getAttribute("userid") == null)
									|| (session.getAttribute("userid") == "")) {
						%>
						You are not logged in<br /><br> <a href="Login.jsp">Please Login</a>
						<%
							} else {
						%>
						<%
							System.out.println("In ViewFaults Page");
							List<UserStorage> lFaults = (List<UserStorage>) request
									.getAttribute("Users");
							if (lFaults == null) {
						%>
						<p>No faults found</p>
						<%
							} else {
						%>


						<%
							Iterator<UserStorage> iterator;

								iterator = lFaults.iterator();
								while (iterator.hasNext()) {
									UserStorage md = (UserStorage) iterator.next();
						%>
					<div class="datagrid">
								<table>
									<thead>
										<tr>
											<th>First Name</th>
											<th>Last Name</th>
											<th>User Type</th>
											
										</tr>
									</thead>

									<tbody>
										<tr>
											<td style="width: 50px;"><%=md.getFirst_Name()%></td>
											<td style="width: 50px;"><%=md.getSecond_Name()%></td>
											<td style="width: 50px;"><%=md.getType()%></td>
											
										</tr>
									</tbody>
								</table>
							</div>
						</p>
						
						
						
						
						<%
							}
							}
							}
						%>
						
					</article>
				</section>
			</div>
			<!-- / content body -->
			<div class="clear"></div>
		</div>
	</div>
	<!-- footer -->
	<div class="wrapper row3">
		<footer id="footer">
			<br> <br> <br> <br> <br>
			<p align="center" class="fl_left">Copyright &copy; 2014 - All
				Rights Reserved - Mhairi Taylor</p>

			<div class="clear"></div>

		</footer>
	</div>
</body>
</html>
