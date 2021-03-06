package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.DataDao;

public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		 
		
		// GET-DELETE
		if(request.getParameter("do") != null 
				&& request.getParameter("id") != null
				&& request.getParameter("do").equals("delete")){
			delete(request);
		}
		
		// GET-Search
		if(request.getParameter("searchString") != null){
			search(request);
		} else {
			addData(request);
		}
		
		request.getRequestDispatcher("WEB-INF/jsp/search.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private void addData(HttpServletRequest request){		
		try {
			request.getSession().setAttribute("data", new DataDao().getAllUnits());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void search(HttpServletRequest request){
		try {
			request.getSession().setAttribute("data", new DataDao().searchUnit(request.getParameter("searchString")));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void delete(HttpServletRequest request){
		int id = -1;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		new DataDao().deleteUnit(id);
	}

}
