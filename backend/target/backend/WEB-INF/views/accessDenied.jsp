<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<html>
<head>
  <title>Access Denied</title>
</head>

<body>
<h1>Sorry, access is denied</h1>
<table>
    <%
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (auth != null) { %>
      <c:redirect url="/profile"/>
    <%= auth.getPrincipal().toString() %>

    <% } %>
    <c:redirect url="/main"/>
</table>
</body>
</html>
