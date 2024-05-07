<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="css/FootBook.css"> <%-- Here we link our style sheet (CSS) to the JSP page --%>
    <title>FootBook RestClient</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="js/FootBook.js"></script><%-- Here we link the FootBook.js file to the JSP page --%>
</head>
<body>
    <header>
        <p>FootBook RestClient</p>
    </header>
    <section id="row">
        <nav>
            <ul>
                <li class="active"><a>Match</a></li>
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
                        <legend>Match:</legend>
                        Match ID:<br>
						<input type="text" name="matchId" id="id" value=""><br>
                        Date:<br>
						<input type="text" name="date" id="date" value=""><br>
                        Time:<br>
						<input type="text" name="time" id="time" value=""><br><br>
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