<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script>
$("#updatePwdFrm").submit(function(){
	console.log("updatePassword.jsp")
	var $password = $(password_);
	var $newPassword = $(newPassword);
	var $passwordCheck = $(passwordCheck);
	if(/^.{4,}$/.test($password.val()) == false){
		alert("유효한 비밀번호를 입력하세요")
		$password.select();
		return false;
	}
	
	if(/^.{4,}$/.test($newPassword.val()) == false){
		alert("비밀번호가 유효하지 않습니다.")
		$newPassword.select();
		return false;
	}
	
	if($newPassword.val() != $passwordCheck.val()){
		alert("비밀번호를 다시 확인하세요.")
		$passwordCheck.select();
		return false;
	}
});
</script>
	<section id=enroll-container>
		<h2>비밀번호 변경</h2>
		<form 
			name="updatePwdFrm" 
			action="<%=request.getContextPath()%>/member/updatePassword" 
			method="post" >
			<table>
				<tr>
					<th>현재 비밀번호</th>
					<td><input type="password" name="password" id="password" required></td>
				</tr>
				<tr>
					<th>변경할 비밀번호</th>
					<td>
						<input type="password" name="newPassword" id="newPassword" required>
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>	
						<input type="password" id="newPasswordCheck" required><br>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type="submit"  value="변경" />
					</td>
				</tr>
			</table>
			<input type="hidden" name="memberId" value="<%= request.getParameter("memberId") %>"/>
		</form>
	</section>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
