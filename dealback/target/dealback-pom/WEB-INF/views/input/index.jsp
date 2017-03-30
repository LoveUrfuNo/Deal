<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Deal is in development now</title>
    <meta http-equiv = "content-type" content = "text/html" charset = "UTF-8">
    <link rel = "stylesheet" type = "text/css" href = "../../../resources/css/index.css">
</head>
<body>
<div id = "development">
    <p>Site is in development now. Enter the password to proceed.</p><br>
    <spring:form method="post"  modelAttribute="userJSP" action="/input">
        Password: <spring:input path="enteredPassword"/><br/>
        <spring:button>submit</spring:button>
    </spring:form>
</div>
</body>
</html>
