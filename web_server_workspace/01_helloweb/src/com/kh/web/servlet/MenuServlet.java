package com.kh.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MenuServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		// 1. 사용자입력값 처리
		String main_menu = request.getParameter("main_menu");
		String side_menu = request.getParameter("side_menu");
		String drink_menu = request.getParameter("drink_menu");
		
		//출력식으로 확인

		// 2. 합계계산 switch문
		int price = 0;
		switch (main_menu) {
		case "한우버거":
			price += 5000;
			break;
		case "밥버거":
			price += 4500;
			break;
		case "치즈버거":
			price += 4000;
			break;
		}
		switch (side_menu) {
		case "감자튀김":
			price += 1500;
			break;
		case "어니언링":
			price += 1700;
			break;
		}
		switch (drink_menu) {
		case "콜라":
			
		case "사이다":
			price += 1000; //switch fall-through
			break;
		case "커피":
			price += 1500;
			break;
		case "밀크쉐이크":
			price += 2500;
			break;
		}

		// 3. 값 저장
		request.setAttribute("price", price);
		
		//4.jsp에 위임. RequestDispatcher
		// /menu/menuOrder.jsp
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/menu/menuOrder.jsp");
		reqDispatcher.forward(request, response);
	}

}