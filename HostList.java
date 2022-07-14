package cscorner;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class HostList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		int flag = 0;

		try (Jedis jedis = new Jedis("localhost")) {
			System.out.println("Connection to server sucessful");

			Set<String> range = jedis.zrangeByScore("Host_list", 0, 100);

			out.println("<font color=Blue size=5>MonID   HostName<br><br>");

			for (String r : range) {

				List<String> list = jedis.hvals(r);
				if (list.isEmpty())
					flag = 1;
			}

			if (flag == 0) {
				for (String r : range) {
					List<String> list = jedis.hvals(r);
					String ID = list.get(0);
					String Host = list.get(1);
					out.println("<form action='Display' method=post>"
							+ "<button style='color:Black;font-size:20px;background:none;border:none;cursor:pointer;' type='submit' name='id' value="
							+ ID + ">" + ID + " -------- " + Host + "</button></form><br>");
				}
			}
		}
		if (flag == 1) {

			Connection con = null;
			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useSSL=false", "root",
						"Deepi#123@");

				PreparedStatement preparedStatement = con.prepareStatement("select MonID,HostName from requestdata");
				ResultSet rs = preparedStatement.executeQuery();

				String ID = "";
				String Host = "";
				// out.println("<font color=Blue size=5>MonID HostName<br><br>");
				while (rs.next()) {
					ID = rs.getString("MonID");
					Host = rs.getString("HostName");
					out.println("<form action='Display' method=post>"
							+ "<button style='color:Black;font-size:20px;background:none;border:none;cursor:pointer;' type='submit' name='id' value="
							+ ID + ">" + ID + " --------> " + Host + "</button></form><br>");
				}
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

}
