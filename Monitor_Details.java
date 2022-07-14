package cscorner;

import java.util.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import redis.clients.jedis.Jedis;

public class Monitor_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		try {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useSSL=false", "root", "Deepi#123@");

			HttpSession session = request.getSession();
			String name = (String) session.getAttribute("username");

			String hostName = request.getParameter("Host");
			String script = request.getParameter("Script");

			String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

			Statement ps = con.createStatement();
			String query = "insert into RequestData(Username,HostName,ScriptName,Time) values ('" + name + "','"
					+ hostName + "','" + script + "','" + time + "')";

			ps.executeUpdate(query);

			out.println("<font color=red size=10>Data inserted in Database.<br>");

			PreparedStatement preparedStatement = con.prepareStatement("select MonID from requestdata where time=?");
			preparedStatement.setString(1, time);
			ResultSet rs = preparedStatement.executeQuery();

			String a = "";
			while (rs.next()) {
				a = rs.getString("MonID");
			}

			Integer id = Integer.valueOf(a);

			try (Jedis jedis = new Jedis("localhost")) {
				System.out.println("Connection to server sucessfully");

				jedis.hset("Host:" + a, "MonID", a);
				jedis.hset("Host:" + a, "Hostname", hostName);
				jedis.hset("Host:" + a, "Scriptname", script);
				jedis.hset("Host:" + a, "Time", time);

				jedis.zadd("Host_list", id, "Host:" + a);
			}
			// for(;;) {
			try {
				String[] cmd = new String[3];
				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "D:/main.bat " + a + " " + name + " " + hostName;
				Runtime rt = Runtime.getRuntime();

				rt.exec(cmd);

			} catch (Throwable t) {
				t.printStackTrace();
			}
			// Thread.sleep(2000);
			// }

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
