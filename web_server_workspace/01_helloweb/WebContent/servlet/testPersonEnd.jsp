<%-- 
    jsp주석  
    jsp : java + html
    jsp의 모든 자바코드<%..%>는 모두 서버단에서 처리되고, 그 결과만 html에 반영된다.
    
--%>
<%@ page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    //jsp scriptlet 자바 공간임
    System.out.println(123);

    //사용자 입력값 가져오기
    //request, response에 선언 없이!!! 접근가능
    String name = request.getParameter("name");
    String color = request.getParameter("color");
    String animal = request.getParameter("animal");
    String[] foodArr = request.getParameterValues("food");
    
    System.out.println("name@jsp = "+ name);
    System.out.println("color@jsp = "+ color);
    System.out.println("animal@jsp = "+ animal);
    //java.lang 패키지를 제외한 다른 패키지는 임포트 시켜줘야한다.
    //상단에 java.util.Arrays 가 임포트된걸 볼 수 있다.
    System.out.println("foodArr@jsp = "+ Arrays.toString(foodArr));
    
    //저장된 속성 가져오기
    String recommendation = (String)request.getAttribute("recommendation");
    System.out.println("recommendation@jsp =" + recommendation);
%>
<!DOCTYPE html>
<html>
<head>
<title>개취 검사 결과</title>
<style>
.recommendation {
    font-size: 2em;
    color: lime;
    text-decoration: underline;
}
</style>
</head>
<body>
<%-- jsp표현식 : 출력시 사용. ;(세미콜론 금지)    jsp 자바코드에 =을 더해준게 출력식! --%>
    <h1>개인 취향 검사 결과 jsp</h1>
    <p><%= name %>님의 개인취향 검사 결과는</p>
    <p><%= color %>을 좋아합니다.</p>
    <p>좋아하는 동물은 <%= animal %>입니다.</p>
    <p>
    <% if(foodArr != null) {%>    	
        좋아하는 음식은 <%= Arrays.toString(foodArr) %>입니다.
    	<%}else{ %>
    	좋아하는 음식이 없습니다.
    <% } %>
    
    <hr>
    <p class='recommendation'>오늘은 <%= recommendation %> 어떠세요?</p>
</body>
</html>