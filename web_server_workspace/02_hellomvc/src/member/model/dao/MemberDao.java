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
	
		
		
		//java.util 프로퍼티즈 임포트

		private Properties prop = new Properties();
		
		//객체 생성시 member-querty.properties의 내용을 읽어다 prop필드에 저장
		public MemberDao() {
			
			//resources폴더
			String fileName = MemberDao.class
										.getResource("/sql/member/member-query.properties")
										.getPath();
			
			try {
				prop.load(new FileReader(fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//System.out.println("path@MemberDao = " + path);
			//System.out.println("prop@MemberDao = " + prop);
		}

		public Member selectOne(Connection conn, String memberId) {
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("selectOne");
			Member member = null;
			
			
			try {
				
				//1. PreparedStatement 객체생성(미완성쿼리 값대입)
				pstmt = conn.prepareStatement(sql);
				pstmt.setNString(1, memberId);
				
				//2. Statement실행 및 결과처리: ResultSet -> Member객체로 옮겨 담는 작업
				rset = pstmt.executeQuery();
				
				//resultSet 결과를 담아보자
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
				
				//3. 자원반납(ResultSet, PreparedStatement) connection은 여기서 반납하지 않는다.(서비스가 주도적으로 닫는다.)
				close(rset);
				close(pstmt);
			}

			return member;
		}
	}
	



