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
        <article>
          <h2>Fault Reporting System</h2>
          <p>
         <form method="post" action="RegisterServlet">
							<table>
								
								<tbody>
									<tr>
										<td>First Name</td>
										<td><input type="text" name="fname" value="" /></td>
									</tr>
									<tr>
										<td>Last Name</td>
										<td><input type="text" name="lname" value="" /></td>
									</tr>
									<tr>
										<td>Email</td>
										<td><input type="text" name="email" value="" /></td>
									</tr>
									<tr>
										<td>User Name</td>
										<td><input type="text" name="uname" value="" /></td>
									</tr>
									<tr>
										<td>Password</td>
										<td><input type="password" name="pass" value="" /></td>
									</tr>
									<tr>
									<tr>
									<td>User Type:</td>
									<td><select name="type" style="width: 145px">
											<option selected="selected"></option>
											<option>Reporter</option>
											<option>Developer</option>
											<option>Admin</option>
									</select></td>
								</tr>
										<td><input type="submit" value="Submit" /></td>
										<td><input type="reset" value="Reset" /><br></td>
									</tr>
									<tr><br>
										<td colspan="2">Already registered? <a
											href="Login.jsp">Login Here</a></td>
									</tr>
								</tbody>
							</table>

						</form>
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
