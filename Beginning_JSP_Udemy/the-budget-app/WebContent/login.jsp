<!DOCTYPE html>
<html>
<head>

    <title>Login</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">

</head>
<body>

    <div id = "wrapper">
        <div id ="header">
            <h2>The Budget App </h2>
        </div>
    </div>

    <div id ="container">
        <h3>Login</h3>

        <form action ="MemberControllerServlet" method ="POST">
        <input type="hidden" name="command" value="validateLogin" /> 
         <table>
            <tbody>
                <tr>
                    Username: <input type = "text" name ="userName"/>
                </tr><br>
                <tr>
                    PIN: <input type ="text" name ="pinCode" />
                </tr><br>
                <tr>
                     Account: <input type ="text" name ="accountId" />
                </tr><br>
                <tr>
                    <input type ="submit" value ="Login" class = "save" />
                </tr>
            </tbody>


            </table>
        </form>

    </div>



</body>



</html>