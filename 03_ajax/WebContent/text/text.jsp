<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax - text</title>
<style>
table{ boder-collapse:collapse;
        border: 1px solid #000;
        margin: 5px;
        }
        th,td{
        border: 1px solid #000;
        }
        table img{
        width: 150px;
        }
</style>
<script src = "<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
<h1>text</h1>
<input type="button" value="실행" id="btn1"/>
<div class="wrapper"></div>
<hr />
<h2>csv</h2>
<input type="button" value="실행" id="btn2" />
<div class = "container"></div>




<script>
$(btn2).click(function(){
	$.ajax({
		url: "<%= request.getContextPath() %>/csv",
		//method: "GET",
		//datatype: "text",//응답메세지를 보고 알아서 처리
		success: function(data){
			console.log(data);
			/*
			ljh,이재현,jh.jpg
			jr,줄리아로버츠,juliaRoberts.jpg
			gone,김고은,김고은.jpg
			yjs,유재석,유재석.jpg
			*/
			var $table = $("<table></table>");
			var members = data.split("\n");
			members = members.slice(0, members.length - 1);
			console.log(members);
			$.each(members, function(index, member){
				console.log(index, member);
				var arr = member.split(",");
				var tr = "<tr>";
				tr += "<td>" + arr[0] + "</td>";
				tr += "<td>" + arr[1] + "</td>";
				tr += "<td><img src='<%= request.getContextPath() %>/images/" + arr[2] + "'/></td>";				
				tr += "</tr>";
				$table.append(tr);
			});
			
			$(".container").html($table);
			
		},
		error: function(xhr, status, err){
			console.log(xhr, status, err);
		}
	});
});

$(btn1).click(function(){
	$.ajax({
		url: "<%=request.getContextPath() %>/text",
		//data: "name=podo&num=12345&flag=true", //직렬화된 형태
		data: {
			name: "strawberry",
			num: 34.56,
			flag: false
			
		},
		method: "POST", //기본값 (생략가능)
		datatype: "text", //응답데이터 형식 text | html | xml | json
		beforeSend: function(){
			//전송전 실행 콜백함수
			console.log("beforeSend call!");
		},
		success: function(data){
			// 요청 성공시 실행 콜백 함수, 매개인자로 응답 message 리턴
			console.log("success call!");
			console.log(data);		
		},
		error: function(xhr, status, error){
			//요청 오류시 실행 콜백 함수
			console.log("error call!");
			console.log(xhr, status, error);
		},
		complete: function(){
			// 요청 성공/오류 상관없이 무조건 마지막에 호출되는 콜백함수
			console.log("complate call!");
			
		}
	});
});


</script>

</body>
</html>