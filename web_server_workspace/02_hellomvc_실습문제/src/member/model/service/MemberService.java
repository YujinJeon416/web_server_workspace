package member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {
	
	private MemberDao memberDao = new MemberDao();
	
	public static final String MEMBER_ROLE = "U"; //관리자 롤
	public static final String ADMIN_ROLE = "A"; //일반사용자 롤 
	


		public Member selectOne(String memberId) {
			
			//1. Connection객체 생성
			
			//java sql Connection으로 임포트
			Connection conn = getConnection();
			
			//2. dao요청
			Member member = memberDao.selectOne(conn, memberId);
			
			//3. 트랜잭션관리(DML만) - 단순 조회 목적이므로 큰 의미 없음
			
			//4. 자원반납
			close(conn);
			return member;
		}
		
		public int insertMember(Member member) {
			Connection conn = getConnection();
			int result = memberDao.insertMember(conn, member);
			
			if(result>0)
				commit(conn);
			else 
				rollback(conn);
			close(conn);
			return result;
		}

}
