package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.exception.BoardException;
import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardService boardService = new BoardService();

	/**
	 * 게시글 상세보기
	 * - board + attachment 조회
	 * - 조인없이 두번 쿼리요청할 것
	 * 
	 * 게시글 등록 성공시 바로 상세보기 페이지로 이동할 것.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 사용자 입력값 : no
		int no  = 0; 
		try {
			no = Integer.parseInt(request.getParameter("no"));
		} catch(NumberFormatException e) {
			throw new BoardException("등록된 게시글 번호가 아닙니다.", e);
		}
		
		//2. 업무로직 : board객체 조회(첨부파일 조회)
		Board board = boardService.selectOne(no);
		if(board == null) {
			throw new BoardException("게시글이 존재하지 않습니다.");
		}
		
		
		//3. jsp forwarding
		request.setAttribute("board", board);
		request.getRequestDispatcher("/WEB-INF/views/board/boardView.jsp")
			   .forward(request, response);
	
	
	}

}