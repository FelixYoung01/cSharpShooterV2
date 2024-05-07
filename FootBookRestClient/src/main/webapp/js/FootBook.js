    $(document).ready(function() {
    $.ajax({
        method: "GET",
        url: "http://api.ipstack.com/check?access_key=38aefa001ae87173e457410cf4342079", // Key is added to the URL
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
            url: `http://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&units=metric&APPID=866cc265e63402d4cecd532529f55c0c`, // Key is added to the URL
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
   
//Ta emot händelse i JQuery för att skicka REST anrop till vår RestServer
//FindBtn är id på knappen som vi klickar på   
$("#FindBtn").click(function() {
    var strValue = $("#id").val();
    if (strValue !== "") {
        
        // AJAX request setup
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/FootBookRestClient/Matches/" + strValue,
            error: ajaxReturnError,
            success: ajaxFindReturnSuccess
        });
    } else {
        alert("Please provide a valid ID");
    }
});

// DeleteBtn är id på knappen som vi klickar på
$("#DeleteBtn").click(function() {
    var strValue = $("#id").val();
    if (strValue !== "") {
        // AJAX request setup
        $.ajax({
            method: "DELETE",
            url: "http://localhost:8080/FootBookRestClient/Matches/" + strValue,
            error: ajaxDeleteReturnError,
            success: ajaxDeleteReturnSuccess
        });
    } else {
        alert("Please provide a valid Match ID");
    }
});

// Success handler for the DELETE operation
function ajaxDeleteReturnSuccess(result, status, xhr) {
    // Clear all fields after deleting the match
    clearFields();
    // Set the placeholder text to inform the user
    $("#id").attr("placeholder", "Match Deleted");
}

// Error handler for the DELETE operation
function ajaxDeleteReturnError(xhr, status, error) {
    alert("Error in Delete Ajax");
    console.log("Ajax-delete match: " + status + ", " + error);
}

// Success handler for the GET operation
function ajaxFindReturnSuccess(result, status, xhr) {
    ParseJsonFileMatch(result);
}

// Error handler for the GET operation
function ajaxReturnError(xhr, status, error) {
    alert("Error in Find Ajax");
    console.log("Ajax-find match: " + status + ", " + error);
}

// Parse the JSON object to populate the input fields
function ParseJsonFileMatch(result) {
    console.log("Received JSON:", result); // Log received data

    // Update the fields with the data from the received JSON object
    $("#id").val(result["Match ID"]);
    $("#date").val(result["Match Date"]);
    $("#time").val(result["Match Time"]);
}

// Clear all input fields
function clearFields() {
    $("#id").val("");
    $("#date").val("");
    $("#time").val("");
}
	});



   