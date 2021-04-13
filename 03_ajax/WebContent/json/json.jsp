<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax - json</title>
<style>
table { 
	border-collapse: collapse; 
	border: 1px solid #000;
	margin: 5px;
}
th, td {
	border: 1px solid #000;
}
table img {
	width: 150px;
}
</style>

<script src = "<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
<h1>JSON</h1>

	<div>
		<button type="button" id="btn">실행</button>
	</div>
	<div>
		<input type="search" id="searchId" placeholder="id검색"/>
		<button type="button" id="btn-search-id">검색</button>
	</div>
	
	<div>
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="id"/></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;">
					<button id="btn-save-member">회원가입</button>
				</td>
			</tr>
		</table>
	</div>
	
	<div class="wrapper"></div>

<script>
$("#btn-save-member").click(function(){
	$.ajax({
		url: "<%= request.getContextPath() %>/json",
		method: "post",
		data: {
			id: $("[name=id]").val(),
			name: $("[name=name]").val()
		},
		success: function(data){
			console.log(data);
		},
		error: function(xhr, status, err){
			console.log(xhr, status, err);
		}
	});
});


$("#btn-search-id").click(function(){
	$.ajax({
		url: "<%= request.getContextPath() %>/json",
		data: {
			searchId: $("#searchId").val()
		},
		success: function(data){
			console.log(data);
			
			if(data != null){
				var $table = $("<table></table>");
				$table
					.append(`<tr><th>아이디</th><td>\${data.id}</td></tr>`)
					.append(`<tr><th>이름</th><td>\${data.name}</td></tr>`)
					.append(`<tr><th>프로필</th><td><img src="<%= request.getContextPath() %>/images/\${data.profile}" /></td></tr>`)
				$(".wrapper").html($table);
			}
			else {
				alert("해당 id는 존재하지 않습니다.");
				$("#searchId").select();
			}
			
		},
		error: function(xhr, status, err){
			console.log(xhr, status, err);
		}
	});
});






$(btn).click(function(){
    $.ajax({
        url:"<%= request.getContextPath()%>/json",
        success:function(data){
            console.log(data);
            var $table = $("<table></table>");
            for(var i=0; i<data.length; i++){
                console.log(data[i]);
                var $tr = $("<tr></tr>");
                $tr.append($("<td>"+data[i].id+"</td>"));
                $tr.append($("<td>"+data[i].name+"</td>"));
                $tr.append($("<td><img src = '<%=request.getContextPath()%>/images/"+data[i].profile+"' width='150px'</td>"));
                $tr.appendTo($table);
                $(".wrapper").html($table);
            }
            
        },
        error:function(xhr, status, err){
            console.log(xhr, status, err);
        }
    });
});

$(btn).click(function(){
	$.ajax({
		url: "<%= request.getContextPath() %>/json",
		success: function(data){
			console.log(data);
			//table data로 가공하기
			//배열확인후, 하나의 member객체를 (tr>td*3)로 변환할것.
			var $table = $("<table></table>");
			var $members = $(data);
			$members.each(function(index, member){
				//console.log(index, member);
				var id = member.id;
				var name = member.name;
				var profile = member.profile;
				var $tr = $("<tr></tr>");
				$tr.append("<td>" + (index + 1) + "</td>")
				   .append("<td><img src='<%= request.getContextPath() %>/images/" + profile + "'/></td>")
				   .append("<td>" + id + "</td>")
				   .append("<td>" + name + "</td>")
				   .appendTo($table);
			});
			$(".wrapper").html($table);
		},
		error: function(xhr, status, err){
			console.log(xhr, status, err);
		}
	});
});

</script>
</body>
</html>