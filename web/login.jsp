<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
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
        <li class="menu-item"><a class="menu-link logo">Internship Management System</a></li>
    </ul>
</div>

<div id="auth">

    <div class="well">
        <div class="heading">
            <h3>Sign In</h3>
            <br>
         <div style="margin:0;padding:0;display:inline">
             <form action="login" method="post">
             <div class="control-group email optional session_email">
                 <label class="email optional control-label" for="session_email">Username</label>
                 <div class="controls">
                     <input class="string email optional" id="userName" name="userName" placeholder="Username" size="50">
                 </div>
             </div>
            <br>
             <div class="control-group password optional session_password">
                 <label class="password optional control-label" for="session_password">Password</label>
                 <div class="controls">
                     <input class="password optional" data-smart-password-toggle="on" id="password" name="password" placeholder="Password" size="50" type="password">

                 </div>
             </div>
             <br>
             <div class="form-actions">
                 <input class="btn btn btn-primary btn-med" name="commit" type="submit" value="Sign In">
             </div>
             </form>
         </div>
        </div>
    </div>
</div>
</body>
</html>