<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Completed Tasks</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="css/Styles.css" rel="stylesheet">
</head>
<body>

<!--Navigation Bar-->
<div class="navbar-fixed-top">
    <ul class="menu">
        <li class="menu-item">Internship Management System</li>
        <li class="menu-item"><a class="menu-link" href="pendingInternships">Pending Internships</a></li>
        <li class="menu-item"><a class="menu-link" href="completedInternships">Confirmed Internships</a></li>
    </ul>
</div>

<!--Completed List-->
<div class="completed-section">

    <h1>Pending Internships</h1>

    <table class="Task-list">

        <thead>
            <tr class="Task-row"><th>Internship ID<th>Student Name</th><th>Student ID</th><th>Completed Hours</th><th>GPA</th><th></th></tr>
        </thead>

        <tbody>
            <c:forEach var="internship" items="${internships}">
                <c:forEach var="student" items="${users}">
                    <c:if test="${internship.studentID == student.studentId}">
                        <tr class="Task-row">
                            <td>${internship.id}</td>
                            <td>${student.firstName}</td>
                            <td>${student.studentId}</td>
                            <td>${student.completedCHs}</td>
                            <td>${student.GPA}</td>
                            <td><a href="confirmInternship.html">Confirm</a></td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:forEach>
    </table>
</div>

</body>
</html>
