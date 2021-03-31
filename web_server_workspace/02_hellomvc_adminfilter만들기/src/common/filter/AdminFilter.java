package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * @실습문제 : AdminFilter 만들기
/admin/memberList ,/admin/memberRoleUpdate , /admin/memberFinder 은 관리자 권한이 있는 사용자만 요청할 수 있다.
로그인하지 않거나, 일반사용자가 단순히 url을 요청하게 되면, 관리자만 사용할 수 있습니다. 경고창, 인덱스페이지로 리다이렉트 시킨다.
 */
@WebFilter("/admin/*")
public class AdminFilter implements Filter {



	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		//관리자가 아닌 부정요청에 대한 처리
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		HttpSession session = httpReq.getSession();
		Member loginMember = ((Member)session.getAttribute("loginMember"));
		
		if(loginMember==null || !MemberService.MEMBER_ROLE.equals(loginMember.getMemberRole())){
			session.setAttribute("msg", "관리자만 사용할 수 있습니다.");
			((HttpServletResponse)response).sendRedirect(httpReq.getContextPath());
			return;
		}
		
		chain.doFilter(request, response);
	}

	

}