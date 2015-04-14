<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Completed Tasks</title>
    <script src="js/jquery-2.1.3.js"></script>
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="css/Styles.css" rel="stylesheet">
</head>
<body>

<!--Navigation Bar-->
<div class="navbar-fixed-top">
    <ul class="menu">
        <li class="menu-item"><a class="menu-link logo" href="index.html">Internship Management System</a></li>
        <li class="menu-item"><a class="menu-link" href="index.html">Pending Internships</a></li>
        <li class="menu-item"><a class="menu-link" href="completed-tasks.html">Confirmed Internships</a></li>
    </ul>
</div>

<!--Completed List-->
<div class="completed-section">

    <h1>Grade Internships</h1>
        <form action="gradeInternships" method="post">
        <label>Internships</label>
        <br>
        <select name="internshipIndex" id = "internshipIndex" onchange="window.location = 'gradeInternships?internshipIndex=' + $(this).val();">
            <c:if test="${internships.size() != 0}">
                <c:forEach var="internshipEntry" items="${internships}">
                    <c:forEach var="student" items="${students}">
                        <c:if test="${internshipEntry.studentID == student.studentId}">
                            <option value="${internshipEntry.id}" <c:if test="${internship.id == internshipEntry.id}"> selected = "selected" </c:if> >   ${student.studentId} - ${student.firstName} @ 
                        </c:if>
                    </c:forEach>
                    <c:forEach var="company" items="${companies}">
                        <c:if test="${internshipEntry.companieID == company.id}">
                            ${company.name} </option>
                         </c:if>
                    </c:forEach>
                </c:forEach>
            
        </select>

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
                                    <select name="gradeComponent${criterea.id}" id="gradeComponent${criterea.id}" onchange="recalculateGrade();">
                                        <c:forEach var="rating" items="${ratings}">
                                            <option value="${rating.id}" <c:if test="${internship.getExaminerGrade(user.staffNo) != null && internship.getExaminerGrade(user.staffNo).getGradeForCriterea(criterea.id) == rating.id}"> selected = "selected" </c:if> >${rating.title}</option>

                                        </c:forEach>
                                    </select>
                                </td>
                                <td class = "commentTD" ><textarea name="gradeComment${criterea.id}" id = "gradeComment${criterea.id}" cols="35" rows="3"><c:if test="${internship.getExaminerGrade(user.staffNo) != null}">${internship.getExaminerGrade(user.staffNo).getCommentForCriterea(criterea.id)}</c:if></textarea></td>
                            </tr>
                        </c:if>
                   </c:forEach>
                </c:forEach>
        </table>
            <br>
            <br>
            
            <c:forEach var="category" items="${categories}">
            <label id="${category}Label">Total Report (Out of 85): 75</label>
            <br>
            </c:forEach>

            <label id="totalLabel">Total (Out of 100): 90</label>

            <br>

            <div class="formSubmit">
                <input type="submit" value="Confirm">
            </div>

            
    </form>

    <script type="text/javascript">
        
        $(document).ready(function(){
           recalculateGrade(); 
        });
        function recalculateGrade()
        {
            var ratings = {};
            <c:forEach var="rating" items="${ratings}">
                    ratings[${rating.id}] = ${rating.percentage};
            </c:forEach>
            
            
            <c:forEach var="category" items="${categories}">
                var ${category} = 0;
                var ${category}Total = 0;
            </c:forEach>
                
                var total = 0;
            <c:forEach var="criterea" items="${critereas}" varStatus="loopIndex">
                ${criterea.category} += ${criterea.grade} * ratings[parseInt($("#gradeComponent${criterea.id}").val())];
                ${criterea.category}Total += ${criterea.grade};
                
            </c:forEach>
           
           <c:forEach var="category" items="${categories}">
                $("#${category}Label").text("Total ${category} (out of " + ${category}Total + "):  " + ${category});
                total += ${category};
           </c:forEach>
                $("#totalLabel").text("Total (Out of 100): " + total);
        }
        
    </script>

    </c:if>

</div>

</body>
</html>
