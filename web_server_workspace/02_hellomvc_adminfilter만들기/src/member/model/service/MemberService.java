package member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import member.model.dao.MemberDao;
import member.model.vo.Member;


/**
 * Connection객체생성
 * Dao에 업무로직 하달
 * 트랜잭션처리
 * 자원반납
 */

public class MemberService {

	private MemberDao memberDao = new MemberDao();

	public static final String MEMBER_ROLE = "U";
	public static final String ADMIN_ROLE = "A";
	
	public Member selectOne(String memberId) {
		//1.Connection객체 생성
		Connection conn = getConnection();
		//2.dao요청
		Member member = memberDao.selectOne(conn, memberId);
		//3.트랜잭션관리(DML만)
		//4.자원반납
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
	
    public int updateMember(Member member) {
		Connection conn = getConnection();
		int result = memberDao.updateMember(conn, member);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}
    
    public int deleteMember(String memberId) {
		Connection conn = getConnection();
		int result = memberDao.deleteMember(conn, memberId);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int updatePassword(Member member) {
		Connection conn = getConnection();
		int result = memberDao.updatePassword(conn, member);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}
	
	public List<Member> selectList() {
		Connection conn = getConnection();
		List<Member> list = memberDao.selectList(conn);
		close(conn);
		return list;
	}

	
	public int updateMemberRole(Member member) {
		Connection conn = getConnection();
		int result = memberDao.updateMemberRole(conn, member);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public List<Member> searchMember(Map<String, String> param) {
		Connection conn = getConnection();
		List<Member> list = memberDao.searchMember(conn, param);
		close(conn);
		return list;
		
	}
	


}
