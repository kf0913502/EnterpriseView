
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
        <li class="menu-item"><a class="menu-link" href="confirmInternship">Confirmed Internships</a></li>
    </ul>
</div>

<!--Completed List-->
<div class="completed-section">

    <h1>Confirmed Internships</h1>

    <table class="Task-list">

        <thead>
            <tr class="Task-row"><th>Internship ID</th><th>Student Name</th><th>Student ID</th><th>Company</th><th>Examiner One</th><th>Examiner Two</th><th>Action</th></tr>
        </thead>

        <tbody>

            <c:forEach var="internship" items="${internships}">
                <c:if test="${internship.IsConfirmed() == true}">
                    <tr class="Task-row">
                    <c:forEach var="student" items="${students}">
                        <c:if test="${internship.studentID == student.studentId}">
                            
                                <td>${internship.id}</td>
                                <td>${student.firstName}</td>
                                <td>${student.studentId}</td>
                        </c:if>
                    </c:forEach>
                                
                   <c:forEach var="company" items="${companies}">
                        <c:if test="${internship.companieID == company.id}">
                            <td>${company.name}</td>
                        </c:if>
                    </c:forEach>
                            
                    <c:if test="${internship.examiners == null}">
                        <td>NOT ASSIGNED</td> <td>NOT ASSIGNED</td>
                        <td><a href="assignExaminers?internshipID=${internship.id}">Assign Examiners</a></td>
                    </c:if>
                        
                    <c:if test="${internship.examiners != null}">  
                        
                       
                        <c:forEach var="examinerID" items="${internship.examiners}">
                            <c:forEach var="examiner" items="${staffMembers}">
                                <c:if test="${examinerID.key == examiner.staffNo}">
                                    <td>
                                        ${examiner.firstName}
                                    </td>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                                    <td><a href="viewGrades?internshipIndex=${internship.id}">View Grades</a></td>
                    </c:if>
                               
                            </tr>
                        
                    
                </c:if>
            </c:forEach>
                            

    </table>
</div>

</body>
</html>
