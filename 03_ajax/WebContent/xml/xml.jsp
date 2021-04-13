<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax - xml</title>
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
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
	<h1>XML</h1>
	<button type="button" id="btn">실행</button>
	<button type="button" id="btn-product-sample">상품xml 가져오기</button>
<script>
$("#btn-product-sample").click(function(){
	$.ajax({
		url: "<%= request.getContextPath() %>/xml/sample-product.xml",
		success: function(data){
			//console.log(data);
			$Products = $(data).find("Product");
			//console.log($Products);
			var $ul = $("<ul></ul>");
			
			$Products.each(function(index, product){
				var name = $(product).children("Name").text();
				var price = $(product).children("Price").text();
				console.log(index, name, price);
				$ul.append("<li>" + name + "($" + price + ")</li>")
			});
			$ul.appendTo($("body"));
		},
		error: function(xhr, status, err){
			console.log(xhr, status, err);
		}
	});
});


$(btn).click(function(){
	
	$.ajax({
		url: "<%= request.getContextPath() %>/xml",
		//dataType: "xml",
		success: function(data){
			console.log(data);
			var $root = $(data).find(":root");
			//console.log($root);
			var $members = $root.find("member");
			var $table = $("<table></table>");
			$members.each(function(index, member){
				//console.log(index, member);
				var id = $(member).children("id").text();
				var name = $(member).children("name").text();
				var profile = $(member).children("profile").text();
				
				var $tr = $("<tr></tr>");
				$tr.append("<td>" + (index + 1) + "</td>")
				   .append("<td><img src='<%= request.getContextPath() %>/images/" + profile + "'/></td>")
				   .append("<td>" + id + "</td>")
				   .append("<td>" + name + "</td>")
				   .appendTo($table);
			});
			
			$("body").append($table);
			
		},
		error: function(xhr, status, err){
			console.log(xhr, status, err);
		}
		
	});
	
});
</script>	
</body>
</html>