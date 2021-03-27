package member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.vo.Member;


public class MemberDao {
	
		
		
	

		private static Properties prop = new Properties();
		
		
		public MemberDao() {
			
		
			String fileName = MemberDao.class
										.getResource("/sql/member/member-query.properties")
										.getPath();
			
			try {
				prop.load(new FileReader(fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}

		public Member selectOne(Connection conn, String memberId) {
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("selectOne");
			Member member = null;
			
			
			try {
				
				//1. PreparedStatement 객체생성
				pstmt = conn.prepareStatement(sql);
				pstmt.setNString(1, memberId);
				
				//2. ResultSet -> Member객체로 옮겨 담기
				rset = pstmt.executeQuery();
				
				//resultSet 결과
				while(rset.next()) {
					member = new Member();
					member.setMemberId(rset.getString("member_id"));
					member.setPassword(rset.getString("password"));
					member.setMemberName(rset.getString("member_name"));
					member.setMemberRole(rset.getString("member_role"));
					member.setGender(rset.getString("gender"));
					member.setBirthday(rset.getDate("birthday"));
					member.setEmail(rset.getString("email"));
					member.setPhone(rset.getString("phone"));
					member.setAddress(rset.getString("address"));
					member.setHobby(rset.getString("hobby"));
					member.setEnrollDate(rset.getDate("enroll_date"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				
				//3. 자원반납
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
				
				pstmt = conn.prepareStatement(query);
				
				
				pstmt.setString(1, m.getMemberId());
				pstmt.setString(2, m.getPassword());
				pstmt.setString(3, m.getMemberName());
				pstmt.setString(4, m.getMemberRole());
				pstmt.setString(5, m.getGender());
				pstmt.setDate(6, m.getBirthday());
				pstmt.setString(7, m.getEmail());
				pstmt.setString(8, m.getPhone());
				pstmt.setString(9, m.getAddress());
				pstmt.setString(10, m.getHobby());
				pstmt.setDate(11, m.getEnrollDate());
				
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}
		
		
}
	



