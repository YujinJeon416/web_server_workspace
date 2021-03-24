<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/header.jsp" %>

		<h1>Content1</h1>
		<p><%= name %>님, 반갑습니다.</p>
		<a href="/web/jsp/another.jsp">another.jsp</a>
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Possimus dolorum accusantium dolorem assumenda quas eveniet nihil obcaecati repudiandae iste dolores doloremque voluptatem ea in temporibus fugit illo vitae nesciunt vero.</p>
		<script>
		$(function(){
			$("h1").css("color", "deeppink");
		});
		</script>
<%@ include file="/jsp/footer.jsp" %>

