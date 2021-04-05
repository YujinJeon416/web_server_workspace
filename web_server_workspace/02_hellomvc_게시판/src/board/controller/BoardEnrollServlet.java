package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardEnrollServlet
 */
@WebServlet("/board/boardEnroll")
public class BoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값처리
		String title = request.getParameter("title");
		String  writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
//		Board board = new Board(0, title, writer, content, null, 0, null);
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		//2. 업무로직 : db에 insert
		int result = boardService.insertBoard(board);
		System.out.println("result= " + result);
		String msg = (result > 0) ? 
						"게시글 등록 성공!" :
							"게시글 등록 실패!";
		
		
		//3. DML요청 : 리다이렉트 & 사용자피드백
		// /mvc/board/boardList
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/board/boardList");
		
	}

}


