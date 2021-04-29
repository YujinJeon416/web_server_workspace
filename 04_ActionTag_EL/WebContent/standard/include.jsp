<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/standard/header.jsp">
	<jsp:param value="INCLUDE" name="title"/>
</jsp:include>
		<article>
			<h2>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quidem officia provident repellat nemo aperiam ipsam alias illo quam itaque quasi est at vero porro neque odio aliquid perferendis velit consequatur!</h2>
			<a href="${pageContext.request.contextPath}/standard/another.jsp">another</a>
		</article>
<jsp:include page="/standard/footer.jsp"></jsp:include>