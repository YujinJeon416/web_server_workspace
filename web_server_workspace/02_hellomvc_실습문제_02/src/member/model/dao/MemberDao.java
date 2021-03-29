package member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.vo.Member;

public class MemberDao {

	private Properties prop = new Properties();
	
	public MemberDao() {
		String fileName = 
			MemberDao.class
					 .getResource("/sql/member/member-query.properties")
					 .getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Member selectOne(Connection conn, String memberId) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOne");
		
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//미완성 쿼리문 값대입
			pstmt.setString(1, memberId);
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setPassword(rset.getString("PASSWORD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberRole(rset.getString("MEMBER_ROLE"));
				member.setGender(rset.getString("GENDER"));
				member.setBirthday(rset.getDate("BIRTHDAY"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return member;

	}
	
	public int insertMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertMember"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberRole());
			pstmt.setString(5, member.getGender());
			pstmt.setDate(6, member.getBirthday());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getPhone());
			pstmt.setString(9, member.getAddress());
			pstmt.setString(10, member.getHobby());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	  public int updateMember(Connection conn, Member member) {
			int result = 0;
			PreparedStatement pstmt = null;
			String query = prop.getProperty("updateMember"); 

			try {
				//미완성쿼리문을 가지고 객체생성.
				pstmt = conn.prepareStatement(query);
				//쿼리문미완성
				pstmt.setString(1, member.getPassword());
				pstmt.setString(2, member.getMemberName());
				pstmt.setString(3, member.getGender());
				pstmt.setDate(4, member.getBirthday());
				pstmt.setString(5, member.getEmail());
				pstmt.setString(6, member.getPhone());
				pstmt.setString(7, member.getAddress());
				pstmt.setString(8, member.getHobby());
				pstmt.setString(9, member.getMemberId());
				
				//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
				//DML은 executeUpdate()
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

	    public int deleteMember(Connection conn, String membmerId) {
			int result = 0;
			PreparedStatement pstmt = null;
			String query = prop.getProperty("deleteMember"); 

			try {
				//미완성쿼리문을 가지고 객체생성.
				pstmt = conn.prepareStatement(query);
				//쿼리문미완성
				pstmt.setString(1, membmerId);
				
				//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
				//DML은 executeUpdate()
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
		}

}
