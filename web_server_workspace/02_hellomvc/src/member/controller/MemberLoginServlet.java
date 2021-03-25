package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * @WebServlet
 * 서블릿 등록을 자동으로 처리함.
 * 
 * - urlPatterns:String[]
 * - name:String
 */
//@WebServlet("/member/login")
@WebServlet(urlPatterns = {"/member/Login"})
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // 1. encoding처리 (post만 필요 - 근데 나중엔 귀찮아서 둘다한다고함)
	        request.setCharacterEncoding("utf-8");
	        
	        // 2. 사용자 입력값 처리 (자바변수에 옮기기)
	        String memberId = request.getParameter("memberId");
	        String password = request.getParameter("password");
	        System.out.println("memberId@servlet = " + memberId);
	        System.out.println("password@servlet = " + password);
	        
	        // 3. 업무로직 (Business logic) : memberId로 회원객체를 조회
	        //db와의 연동 -> service에게 위임
	        Member member = memberService.selectOne(memberId);
	        System.out.println("member@servlet = " + member);
	        
	        //로그인 성공/실패여부 판단
	        
	        //1.로그인 성공: member != null && password.equals(member.getPassword())
	        //2.로그인 실패: 
	        //아이디가 존재하지 않을때 member == null
	        //비번이 틀릴때  member != null && !password.equals(member.getPassword())
	        
	        if(member != null && password.equals(member.getPassword())) {
	        	//로그인 성공
//	        	request.setAttribute("msg","로그인에 성공했습니다.");
	        	
	        	//로그인한 사용자정보
	        	HttpSession session = request.getSession();
	        	session.setAttribute("loginMember",member);
	        }
	        else {
	        	//로그인 실패
	        	request.setAttribute("msg","로그인에 실패했습니다.");
	        	request.setAttribute("loc",request.getContextPath());
	        	
	        }
	        
	        
	        
	        // 4. 응답메세지 처리 html (jsp위임 | redirect)
	        RequestDispatcher reqDispatcher = 
	        		request.getRequestDispatcher("/index.jsp");
	        reqDispatcher.forward(request, response);
	    }
}
