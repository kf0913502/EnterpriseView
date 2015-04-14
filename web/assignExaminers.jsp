<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head lang="en">
    <script src="js/jquery-2.1.3.js"></script>
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

    <h1>Assign Examiners</h1>
    <br><br>
    <form action="assignExaminers" method="post" id="myform">
        <div class="Field">
            <h2>Internship Information</h2>
            <br>
            <label>Student Name: ${user.firstName}</label>
            <br>
            <label>Student ID: ${user.studentId}</label>
            <br>
            <label>Company Name: ${company.name}</label>
        </div>

        <div class="Field">
            <h2>Examiner Selection</h2>
            <br>


                <label>Examiner One</label>
                <br>
                <select id="examinerOne" name="examinerOne">
                    <c:forEach var="examiner" items="${staffMembers}">
                        <option value = "${examiner.staffNo}" >${examiner.firstName}</option>
                    </c:forEach>
                </select>

                <br><br>
                <label>Examiner Two</label>
                <br>
                <select id="examinerTwo" name="examinerTwo">
                    <c:forEach var="examiner" items="${staffMembers}">
                        <option value = "${examiner.staffNo}" >${examiner.firstName}</option>
                    </c:forEach>
                </select>
            <br><br>


        </div>
        <div class="Field">
            <h2>Presentation</h2>
            <br>
            <label>Time</label>
            <br>
            <input type="text" id = "time">


            <br><br>
            <label>Date</label>
            <br>
            <input type="date" id="date">

            <br><br>
            <label>Location</label>
            <br>
            <input type="text" id="location">

            <br><br>
        </div>




        <div class="formSubmit">
            <input type="submit" value="Confirm">
        </div>
            <input type="hidden" value="${internship.id}" name="internshipIndex">
    </form>
</div>

        <script type="text/javascript">
            $("#myform").submit(function(e){
                
                if ($("#examinerOne").val()== $("#examinerTwo").val())
                {
                    alert("Choose two different examiners");
                    e.preventDefault();
                }
            });
            
        </script>
</body>
</html>
