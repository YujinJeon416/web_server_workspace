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
				
				pstmt.setString(1, member.getMemberId());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getMemberName());
				pstmt.setString(4, member.getGender());
				pstmt.setDate(5, member.getBirthday());
				pstmt.setString(6, member.getEmail());
				pstmt.setString(7, member.getPhone());
				pstmt.setString(8, member.getAddress());
				pstmt.setString(9, member.getHobby());
				
				
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}
		
		
}
	



