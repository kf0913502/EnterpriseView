<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>

<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Internship Management System</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="css/Styles.css" rel="stylesheet">
</head>
<body>

<!--Navigation Bar-->
<div class="navbar-fixed-top">
    <ul class="menu">
        <li class="menu-item"><a class="menu-link logo" href="index.html">Internship Management System</a></li>
    </ul>
</div>

<!--Completed List-->
<div class="completed-section">
    <c:if test="${internship == null}">
        <h1>Register For Internship</h1>
    </c:if>
        
    <c:if test="${internship != null && internship.IsConfirmed() == false}">
        <h1>Internship Status: Pending Coordinator Approval</h1>
    </c:if>
    <br><br>
    <form action="registerInternship" method="post">
        <div class="Field">
            <h2>Student Information</h2>
            <br>
            <label>Name: ${user.firstName}</label>
            <br>
            <label>Student ID: ${user.studentId}</label>
            <br>
            <label>Completed Hours: ${user.completedCHs}</label>
            <br>
            <label>GPA: ${user.GPA}</label>
        </div>
        <c:if test="${internship == null}">
            <div class="Field">
                <h2>Company Selection</h2>
                <br>
                <label>Available Companies</label>
                <br>
                <select multiple>
                    <c:forEach var="company" items="${companies}">
                        <option>${company.name}</option>
                    </c:forEach>
                </select>
                <br><br>
            </div>
            
            
            <div class="formSubmit">
                <input type="submit" value="Register">
            </div>
        </c:if>
        
        <c:if test="${internships != null && internship.IsConfirmed == true}">
            <div class="Field">
                <h2>Mentor Information</h2>
                <br>
                <label>First Name</label>
                <br>
                <input type="text">
                <br>

                <br>
                <label>Last Name</label>
                <br>
                <input type="text">
                <br>


                <br>
                <label>Office Phone</label>
                <br>
                <input type="text">
                <br>

                <br>
                <label>Mobile</label>
                <br>
                <input type="text">
                <br>


                <br>
                <label>Email</label>
                <br>
                <input type="text">
                <br>

            </div>

            <div class="Field">
                <h2>Internship Information</h2>
                <br>
                <label>Location</label>
                <br>
                <input type="text" placeholder="LONGITUDE">
                <input type="text" placeholder="LATITUDE">

                <br><br>
                <label>Abstract Upload</label>
                <input type="file">
            </div>

        
            <div class="formSubmit">
                <input type="submit" value="Register">
            </div>
        </c:if>
    </form>
</div>

</body>
</html>
