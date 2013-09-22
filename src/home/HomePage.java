package home;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HomePage
 */
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(request.getParameter("param") != null)
			session.setAttribute("param", request.getParameter("param"));
		
		PrintWriter out = response.getWriter();
		out.println("Hello");
		out.println("Your session id is " + session.getId());
		out.println("Session attribute is " + session.getAttribute("param"));
	}
}
