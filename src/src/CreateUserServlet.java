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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
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
		// read username and password from login page
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
				String uname = request.getParameter("uname");
				
				String mobile = request.getParameter("mobile");
				String pass = request.getParameter("pass");
				// verify in database
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					System.out.println("driver loaded");
					Connection conn = DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521:xe","ep","ep123");
					
					System.out.println("connected to database");
					
					
					String sql = "insert into register values(?, ?, ?, ?, ?)";
					//set values for ?
					
					PreparedStatement pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, fname);
					pstmt.setString(2, lname);
					pstmt.setString(3, uname);
					pstmt.setString(4, mobile);
					
					pstmt.setString(5, pass);
					
					int count = pstmt.executeUpdate();
					
					// Redirect 
					
					if(count >0){
						// Successful Creation
						RequestDispatcher rd = request.getRequestDispatcher("Login.html");
						rd.forward(request, response);
					} else {
						// Creation Failed
						RequestDispatcher rd = request.getRequestDispatcher("createfail.html");
						rd.forward(request, response);
					}
					
					
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
	}

}
