<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="css/FootBook.css">
    <title>FootBook RestClient</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="js/FootBook.js"></script>
</head>
<body>
    <header>
        <p>FootBook RestClient</p>
    </header>
    <section id="row">
        <nav>
            <ul>
                <li class="active"><a>User Management</a></li>
                <li><a>Team Settings</a></li>
                <li><a>Match Reports</a></li>
            </ul>
        </nav>
        <aside>
          <table>
              <tr>
                  <th>City: <span id="city"></span></th>
                  <th>IP: <span id="ipNbr"></span></th>
              </tr>
              <tr>
                  <td>Sunrise: <span id="sunrise"></span></td>
                  <td>Sunset: <span id="sunset"></span></td>
              </tr>
              <tr>
                  <td>Weather: <span id="weather"></span></td>
                  <td>Temperature: <span id="degree"></span>°C</td>
              </tr>
          </table>
      </aside>
        <section id="main">
            <section id="content">
                <article>
                    <fieldset id="PersonalFS">
                        <legend>User:</legend>
                        UserId:<br>
                        <input type="text" name="userId" id="userId" value=""><br>
                        Name:<br>
                        <input type="text" name="name" id="name" value=""><br>
                        Email:<br>
                        <input type="text" name="email" id="email" value=""><br><br>
                        <input type="button" name="submitBtn" value="Find" id="FindBtn">
                        <input type="button" name="submitBtn" value="Add" id="AddBtn">
                        <input type="button" name="submitBtn" value="Delete" id="DeleteBtn">
                        <input type="button" name="submitBtn" value="Update" id="UpdateBtn">
                    </fieldset>
                </article>
            </section>
        </section>
    </section>
    <footer>
        <p>&copy; CSharpShooters</p>
    </footer>
</body>
</html>