package com.kh.ajax.text;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AjaxCsvServlet
 */
@WebServlet("/csv")
public class AjaxCsvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.업무로직
		List<Member> list = new ArrayList<>();
		list.add(new Member("ljh", "이재현", "jh.jpg"));
		list.add(new Member("jr", "줄리아로버츠", "juliaRoberts.jpg"));
		list.add(new Member("gone", "김고은", "김고은.jpg"));
		list.add(new Member("yjs", "유재석", "유재석.jpg"));
		
		//2.응답처리
		response.setContentType("text/csv; charset= utf-8");
		PrintWriter out = response.getWriter();
		for(Member m : list) {
			out.println(m); // toString 자동호출
			
		}
		
		
		
	
	
	
	}

}
