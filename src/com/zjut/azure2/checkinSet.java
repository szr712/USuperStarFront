package com.zjut.azure2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class checkinSet
 */
@WebServlet("/checkinSet")
public class checkinSet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkinSet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		request.getParameter("idtype");
//		request.getParameter("checkin");
		request.setCharacterEncoding("UTF-8");
		checkinData checkinData=new checkinData();
		checkinData.setNum(request.getParameter("num"));
		checkinData.setName(request.getParameter("name").trim());
		checkinData.setIdtype(request.getParameter("idtype").trim());
		checkinData.setId(request.getParameter("id").trim());
		checkinData.setCheckin(request.getParameter("checkin").trim());
		checkinData.setCheckout(request.getParameter("checkout").trim());
		checkinDao checkinDao=new checkinDao();
		boolean flag=checkinDao.changeStatus(checkinData);
		if (flag) {
			RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher("/checkin");
			requestDispatcher.forward(request, response);
		}
	}

}
