<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <li class="menu-item"><a class="menu-link logo" href="index.html">IMS</a></li>
        <li class="menu-item"><a class="menu-link" href="index.html">Pending Internships</a></li>
        <li class="menu-item"><a class="menu-link" href="completed-tasks.html">Confirmed Internships</a></li>
    </ul>
</div>

<!--Completed List-->
<div class="completed-section">

    <h1>View Grade</h1>

    <br><br>
        <table class="Task-list">

            <thead>
            <tr class="Task-row"><th>Criterea</th><th>Rating</th><th>Comment</th></tr>
            </thead>

            <tbody>

                        <c:forEach var="category" items="${categories}">
                <tr><td colspan="7"><h2>${category}</h2></td></tr>
                <c:forEach var="criterea" items="${critereas}" varStatus="loopIndex">
                    <c:if test="${criterea.category == category}">
                        <tr class="Task-row">
                            <td class="critereaTD"><p class = "critereaTitleText">${loopIndex.count}. ${criterea.title} (out of ${criterea.grade})</p>${criterea.description}</td>
                                <td>
                                        <c:forEach var="examiner" items="${internship.examiners}" varStatus="examinerNo">
                                            <c:if test="${internship.getExaminerGrade(examiner.key) != null}"> Examiner ${examinerNo.count}: <p>${ratings.get(internship.getExaminerGrade(examiner.key).getGradeForCriterea(criterea.id)-1).title}</p></c:if> 
                                        </c:forEach>
                                        
                                </td>
                                <td class = "commentTD" >
                                <c:forEach var="examiner" items="${internship.examiners}" varStatus="examinerNo">
                                    <c:if test="${internship.getExaminerGrade(examiner.key) != null}">
                                        <p>Examiner ${examinerNo.count}: ${internship.getExaminerGrade(examiner.key).getCommentForCriterea(criterea.id)}</p>
                                    </c:if> 
                                </c:forEach>
                                </td>
                        </tr>
                    </c:if>
               </c:forEach>
            </c:forEach>
        </tbody>
        </table>
        <br>
        <br>
        <label>Total Report (Out of 85): 75</label>
        <br>
        <label>Total Presentation (Out of 15): 15</label>
        <br>
        <label>Total (Out of 100): 90</label>

        <br>







</div>

</body>
</html>
