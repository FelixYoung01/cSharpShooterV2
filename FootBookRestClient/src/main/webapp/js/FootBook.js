    $(document).ready(function() {
    $.ajax({
        method: "GET",
        url: "http://api.ipstack.com/check?access_key=38aefa001ae87173e457410cf4342079", // Replace with your actual API key
        error: ajaxReturn_Error,
        success: ajaxReturn_Success
    });

    function ajaxReturn_Success(result, status, xhr) {
        ParseJsonFile(result);
    }

    function ajaxReturn_Error(result, status, xhr) {
        console.log("Ajax-api-stack: " + status);
    }

    function ParseJsonFile(result) {
        var lat = result.latitude;
        var long = result.longitude;
        var city = result.city;
        var ipNbr = result.ip;
        $("#city").text(city);
        $("#ipNbr").text(ipNbr);

        $.ajax({
            method: "GET",
            url: `http://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&units=metric&APPID=866cc265e63402d4cecd532529f55c0c`, // Replace 'your_app_id' with your OpenWeatherMap API key
            error: ajaxWeatherReturn_Error,
            success: ajaxWeatherReturn_Success
        });
    }

    function ajaxWeatherReturn_Success(result, status, xhr) {
        var sunrise = result.sys.sunrise;
        var sunset = result.sys.sunset;
        var sunriseDate = new Date(sunrise*1000);
        var timeStrSunrise = sunriseDate.toLocaleTimeString("sv-SE");
        var sunsetDate = new Date(sunset*1000);
        var timeStrSunset = sunsetDate.toLocaleTimeString("sv-SE");
        $("#sunrise").text("Sunrise: " + timeStrSunrise);
        $("#sunset").text("Sunset: " + timeStrSunset);
        $("#weather").text(result.weather[0].main);
        $("#degree").text(result.main.temp + " \u2103");
    }

    function ajaxWeatherReturn_Error(result, status, xhr) {
        alert("Error in OpenWeatherMap Ajax");
        console.log("Ajax-find movie: " + status);
    }
}); 
   