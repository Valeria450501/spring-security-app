<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
   <head>
      <title>Access denied</title>
   </head>
   <body>
      <h2>Access denied</h2>
      <hr>
      <p>
         Please authorize to access
      </p>
      <hr>
      <p>
         <a href="${pageContext.request.contextPath}/showMyLoginPage">ABack to login page</a>
      </p>
   </body>
</html>