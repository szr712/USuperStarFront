package com.zjut.azure2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class bookAdd
 */
@WebServlet("/bookAdd")
public class bookAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BookDao bookDao=new BookDao();
		Book book=new Book();

		request.setCharacterEncoding("UTF-8");

		book.setNum(request.getParameter("num".trim()));
		book.setType(request.getParameter("type").trim());
		book.setName(request.getParameter("name").trim());
		book.setCheckin(request.getParameter("checkin").trim());
		book.setCheckout(request.getParameter("checkout").trim());
		book.setQuantity(Integer.parseInt(request.getParameter("quantity").trim()));
		boolean flag=bookDao.addBook(book);
		if(flag) {
			RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher("/bookSetting");
			requestDispatcher.forward(request, response);
		}
	}

}
