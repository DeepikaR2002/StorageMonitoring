package cscorner;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
/*import jakarta.servlet.annotation.WebServlet;
 * 
 */
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection con = null;
		try {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useSSL=false", "root", "Deepi#123@");
			String n = request.getParameter("txtName");
			String p = request.getParameter("txtPwd");

			HttpSession session = request.getSession();
			session.setAttribute("username", n);

			PreparedStatement preparedStatement = con
					.prepareStatement("select uname from login where uname= ? and password= ?");
			preparedStatement.setString(1, n);
			preparedStatement.setString(2, p);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				RequestDispatcher rd = request.getRequestDispatcher("RequestData.jsp");
				rd.forward(request, response);
			}
			out.println("<font color=red size=6>Login Failed !<br>");
			out.println("<a style='color:blue;size:6' href=Login.jsp>TRY AGAIN !!</a>");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
