$(document).ready(function() {
    // General AJAX error logging function
    function logAjaxError(xhr, status, error) {
        console.error(`Ajax Error: ${status}, ${error}`);
    }

    // Fetch IP and geolocation data
    $.ajax({
        method: "GET",
        //url: "https://ipinfo.io/?token=7b10316367bd0b", // Replace with your token
        error: logAjaxError,
        success: function(result) {
            var city = result.city;
            var ipNbr = result.ip;
            var loc = result.loc.split(","); // Format is "latitude,longitude"
            var lat = loc[0];
            var lon = loc[1];

            // Update city and IP information
            $("#city").text(city);
            $("#ipNbr").text(ipNbr);

            // Fetch weather information based on geolocation
            $.ajax({
                method: "GET",
                //url: `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&units=metric&APPID=ce3e097846c8e55864481f37b93db22f`,
                error: logAjaxError,
                success: function(weatherResult) {
                    var sunrise = new Date(weatherResult.sys.sunrise * 1000).toLocaleTimeString("sv-SE");
                    var sunset = new Date(weatherResult.sys.sunset * 1000).toLocaleTimeString("sv-SE");
                    var weather = weatherResult.weather[0].main;
                    var temp = weatherResult.main.temp + " \u2103";

                    // Update the HTML elements with the weather data
                    $("#sunrise").text("Sunrise: " + sunrise);
                    $("#sunset").text("Sunset: " + sunset);
                    $("#weather").text(weather);
                    $("#degree").text(temp);
                }
            });
        }
    });
});
