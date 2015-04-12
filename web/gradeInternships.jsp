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

    <h1>Grade Internships</h1>
    <form>
        <label>Internships</label>
        <br>
        <select>
            <option>Internship one</option>
            <option>Internship two</option>
            <option>Internship three</option>
        </select>
    </form>
    <br><br>
    <form>
        <table class="Task-list">

            <thead>
            <tr class="Task-row"><th>Criterea</th><th>Rating</th><th>Comment</th></tr>
            </thead>

            <tbody>


                <tr class="Task-row">
                    <td class="critereaTD">This is the description for criterea one and this is really really really really long really really really really long</td>
                    <td><select>
                        <option>Excellent (Grade * 1)</option>
                        <option>Good (Grade * 0.8)</option>
                        <option>Satisfactory (Grade * 0.6)</option>
                        <option>Unsatisfactory (Grade * 0.4)</option>
                        <option>Very unsatisfactory (Grade * 0.1)</option>
                    </select></td>
                    <td class = "commentTD"><textarea cols="35" rows="3"></textarea></td>
                </tr>


        </table>
            <br>
            <br>
            <label>Total Report (Out of 85): 75</label>
            <br>
            <label>Total Presentation (Out of 15): 15</label>
            <br>
            <label>Total (Out of 100): 90</label>

            <br>

            <div class="formSubmit">
                <input type="submit" value="Confirm">
            </div>


    </form>





</div>

</body>
</html>
