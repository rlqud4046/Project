<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table>
<c:set var="arr" value="<%=new int[]{1,2,3,4,5} %>"></c:set>

<c:forEach begin="0" end="${ Math.ceil(arr.length/4 +1 )}" step="1">
<c:forEach begin="0" end="3" step="1">
${arr }
</c:forEach>
<br>
</c:forEach>
<tr></tr>

</table>




<!-- 

String[] a = {"1","2","3","4","5"}; 
		
		for(int i=0;i<Math.ceil(a.length/4)+1;i++) {
			for(int j=0;j<4;j++) {
				System.out.print(a[i*4+j]);
				if(i*4+j==a.length-1) {
					break;
				}
			}
			System.out.println();
		} -->
</body>
</html>