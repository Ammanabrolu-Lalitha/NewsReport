package src;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// read username and password from login page
		
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		
		// verify in database
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver loaded");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe","ep","ep123");
			
			System.out.println("connected to database");
			String sql = "select uname,pass from register where uname = ? and pass = ?";
			//set values for ?
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, uname);
			pstmt.setString(2, pass);
			
			ResultSet rs = pstmt.executeQuery();
			
			// Redirect 
			
			if(rs.next()){
				// Successful login
				//Create two cookies, un and pw
				Cookie un = new Cookie("un", uname);
				Cookie pw = new Cookie("pw", pass);
				
				// add the cookies to the response
				response.addCookie(un);
				response.addCookie(pw);
				
				RequestDispatcher rd = request.getRequestDispatcher("hom.html");
				rd.forward(request, response);
			} else {
				// Login Failed
				RequestDispatcher rd = request.getRequestDispatcher("hom.html");
				rd.forward(request, response);
			}
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//redirect
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
