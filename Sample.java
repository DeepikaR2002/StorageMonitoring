package com.datastax.driver.core;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

public class Sample extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			StringBuilder sb = new StringBuilder();
			BufferedReader reader = request.getReader();

			try {
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line).append('\n');
				}
			} finally {
				reader.close();
			}

			String value = sb.toString();

			String[] arrOfStr = value.split(":|,|'", 11);

			Integer ID = Integer.valueOf(arrOfStr[2]);
			String Data = "{" + arrOfStr[3] + ":" + arrOfStr[4] + "}";
			String Time = arrOfStr[6] + ":" + arrOfStr[7] + ":" + arrOfStr[8];

			String query1 = "INSERT INTO Disk_Details (MonID,Data,Time)"

					+ " VALUES(?,?,?);";

			Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();

			Session session = cluster.connect("sample");

			session.execute(query1, ID, Data, Time);

			System.out.println("Data created");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
