package member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/member/memberEnroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 회원가입페이지
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp")
		.forward(request, response);
	}

	/**
	 * 회원가입 처리 = db에 저장
	 * dml처리는 doPost로 하면 된다.
	 * 회원가입성공|실패시 사용자 메시지를 띄운다.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩처리
				
				request.setCharacterEncoding("UTF-8");
				
				//2.전송값
			
				String memberId = request.getParameter("memberId");
				String password = request.getParameter("password");
				String memberName = request.getParameter("memberName");
				String birthday = request.getParameter("birthday");
				String gender = request.getParameter("gender");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				String address = request.getParameter("address");
				String memberRole = MemberService.MEMBER_ROLE;
				
				
				
				String[] hobbies = request.getParameterValues("hobby");
				
				String hobby = "";
				
				if(hobbies != null) hobby = String.join(",", hobbies);

				
				Date birthday_ = null;
				if(birthday != null && !"".equals(birthday))
					birthday_ = Date.valueOf(birthday);
				
				Date enrolldate = new Date(new java.util.Date().getTime());
				
				
				
				Member member = 
						new Member(memberId, password, memberName,memberRole, 
								   gender, birthday_, email, phone, 
								   address, hobby, enrolldate );
				
				System.out.println("member@servlet = "+member);
				
				//3.업무로직
				int result = new MemberService().insertMember(member);
				
				
//				//DML, login등 요청후 반드시 url을 변경해서 새로고침 사고를 방지한다.
				//requestdispatcher 사용한 forward방식은 사용하면 안됨
				//새로고침 이슈때문에 redirection 을 사용해야함
				//msg값도 redirection을 사용하기 때문에
				//request에 저장하는게 아닌 session에 저장해줘야함 
				
				HttpSession session = request.getSession(true);
				if(result>0) {
					System.out.println("회원가입에 성공하였습니다.");
					session.setAttribute("msg", "회원가입에 성공하였습니다.");
				}
				else {
					System.out.println("회원가입에 실패하였습니다.");
					session.setAttribute("msg", "회원가입에 실패하였습니다.");
				}
				
				//리다이렉트: url변경
				response.sendRedirect(request.getContextPath());
			}
}
				
			
			


				
				
			

	

