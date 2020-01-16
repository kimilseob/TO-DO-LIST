package com.Todo.main;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.TodoDao;
import com.dto.TodoDto;

/**
 * Servlet implementation class Main
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		TodoDao dao = new TodoDao();
		List<TodoDto> list = dao.getTodos();
		System.out.println("리스트"+list);
		PrintWriter out = response.getWriter();
		out.println(list);
		request.setAttribute("list", list);
		
		/*RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
		rd.forward(request, response);*/
		
		
	}
}



