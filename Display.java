package com.datastax.driver.core;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");

			Integer mid = Integer.valueOf(request.getParameter("id"));

			out.println("<font color = blue size = 5 >Monitored Data of Monitor ID " + mid + ":");

			String query1 = "SELECT * FROM Disk_Details where MonID = ?";

			Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();

			Session session = cluster.connect("sample");

//			session.execute(query1,mid);

			com.datastax.driver.core.ResultSet rs = session.execute(query1, mid);

			out.println(
					"<table style = 'border:1px solid;'><tr><td style = 'border:1px solid;font-size:20px;color:red;'>MonID</td><td style = 'border:1px solid;font-size:20px;color:red;'>Time</td><td style = 'border:1px solid;font-size:20px;color:red;'>Data</td></tr><br>");

			for (Row row : rs) {

				out.println("<tr><td style = 'border:1px solid;font-size:19px;'>" + row.getInt("MonID")
						+ "</td><td style = 'border:1px solid;font-size:19px;'>" + row.getString("Time")
						+ "</td><td style = 'border:1px solid;font-size:19px;'>" + row.getString("Data")
						+ "</td></tr><br>");
			}

			out.println("</table>");

			System.out.println("Data Displayed");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
