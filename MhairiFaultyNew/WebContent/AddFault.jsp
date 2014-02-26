<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<link rel="stylesheet" href="Style.css" type="text/css">
<!-- TemplateBeginEditable name="doctitle" -->
<title>Mr Faulty | Mhairi Taylor</title>
<!-- TemplateEndEditable -->
<!-- TemplateBeginEditable name="head" -->
<!-- TemplateEndEditable -->
<link href="Style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="wrapper row1">
  <header id="header" class="clear">
    <hgroup>
      <h1> Mr Faulty</h1>
    </hgroup>
     <nav>
			 	<ul>
				<% if ((session.getAttribute("userid") == null)	|| (session.getAttribute("userid") == "")) {%>
						<li><a href="Login.jsp">Login</a></li>
						<%
							} else {%>
						Logged in as: <%=session.getAttribute("userid")%>
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
       <h2>Report Your Fault</h2>
						<p>
						<%
							if ((session.getAttribute("userid") == null)
									|| (session.getAttribute("userid") == "")) {
						%>
						You are not logged in<br /><br> <a href="Login.jsp">Please Login</a>
						<%
							} else {
						%>
						<form action="/MhairiFaultyNew/FaultServlet" method="post">
							<table>
								<tr>
									<td>Enter user ID:</td>
									<td><input type="text" name="txtReportedBy" size="20" /></td>
								</tr>
								<tr>
									<td>Severity:</td>
									<td><select name="txtSeverity" id="txtSeverity"
										style="width: 145px">
											<option selected="selected"></option>
											<option>Low</option>
											<option>Medium</option>
											<option>High</option>
									</select></td>
								</tr>
								<tr>
									<td>Enter a description:</td>
									<td><input type="text" name="txtDescription" size="60" /></td>
								</tr>
								<tr>
									<td></td>
									<td><input type="submit" name="B1" /> <input type="reset"
										name="B2" /></td>
								</tr>
							</table>
						</form>
						<%} %>
						
</p>
					</article>
      </section>
    </div>
    <!-- / content body -->
    <div class="clear"></div>
  </div>
</div>
<!-- footer -->
<div class="wrapper row3">
  <footer id="footer"><br><br><br><br><br>
			<p align="center" class="fl_left">Copyright &copy; 2014 - All Rights Reserved -
				Mhairi Taylor</p>
			
			<div class="clear"></div>
	
  </footer>
</div>
</body>
</html>
