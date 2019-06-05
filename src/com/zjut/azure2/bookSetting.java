package com.zjut.azure2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class bookSetting
 */
@WebServlet("/bookSetting")
public class bookSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSource dataSource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookSetting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BookDao bookDao=new BookDao();
		ArrayList<Book> booklist=new ArrayList<>();
		booklist=bookDao.findBook();
		request.setAttribute("booklist", booklist);
		
		TypeDao typeDao=new TypeDao();
		ArrayList<Type> typelist=new ArrayList<>();
		typelist=typeDao.findType();
		request.setAttribute("typelist", typelist);
		
		RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher("/bookSetting.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
