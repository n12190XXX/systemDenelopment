package jp.ac.isc.cloud;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection users = null;
			try {
				request.setCharacterEncoding("utf-8");
				Class.forName("com.mysql.jdbc.Driver");
				users = DriverManager.getConnection("jdbc:mysql://localhost/servlet_db?useUnicode=true&characterEncoding=utf8","root","");
				String id = request.getParameter("insertId");
				String name = request.getParameter("insertName");
				String picture = request.getParameter("insertPicture");
				Statement state = users.createStatement();
				state.executeUpdate("INSERT INTO user_table VALUE('" + id + "','" + name + "','" + picture + "')");
				state.close();
				users.close();
				response.sendRedirect("/select");	//UserSelectServletを呼び出す
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}



