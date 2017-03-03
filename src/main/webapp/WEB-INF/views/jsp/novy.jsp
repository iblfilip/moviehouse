<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Student Information</h2>
<form:form method="POST" action="/createrecord">
   <table>
    <tr>
        <td><form:label path="title">Name</form:label></td>
        <td><form:input path="title" /></td>
    </tr>
    <tr>
        <td><form:label path="genre">Age</form:label></td>
        <td><form:input path="genre" /></td>
    </tr>
    <tr>
        <td><form:label path="seen">id</form:label></td>
        <td><form:input path="seen" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>
</form:form>
</body>
</html>