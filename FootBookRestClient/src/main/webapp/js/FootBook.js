$(document).ready(function() {
	// General AJAX error logging function
	function logAjaxError(xhr, status, error) {
		console.log("Ajax Error:", status, error);
	}

	// Fetch location and weather data using external APIs
	$.ajax({
		method: "GET",
		url: "http://api.ipstack.com/check?access_key=38aefa001ae87173e457410cf4342079",
		error: logAjaxError,
		success: ParseJsonFile
	});

	function ParseJsonFile(result) {
		var lat = result.latitude;
		var long = result.longitude;
		var city = result.city;
		var ipNbr = result.ip;
		$("#city").text(city);
		$("#ipNbr").text(ipNbr);

		$.ajax({
			method: "GET",
			url: `http://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&units=metric&APPID=866cc265e63402d4cecd532529f55c0c`,
			error: logAjaxError,
			success: ajaxWeatherReturn_Success
		});
	}

	function ajaxWeatherReturn_Success(result, status, xhr) {
		var sunrise = result.sys.sunrise;
		var sunset = result.sys.sunset;
		var sunriseDate = new Date(sunrise * 1000);
		var timeStrSunrise = sunriseDate.toLocaleTimeString("sv-SE");
		var sunsetDate = new Date(sunset * 1000);
		var timeStrSunset = sunsetDate.toLocaleTimeString("sv-SE");
		$("#sunrise").text("Sunrise: " + timeStrSunrise);
		$("#sunset").text("Sunset: " + timeStrSunset);
		$("#weather").text(result.weather[0].main);
		$("#degree").text(result.main.temp + " \u2103");
	}

	// Find operation handler
	$("#FindBtn").click(function() {
		var strValue = $("#id").val();
		if (strValue !== "") {
			$.ajax({
				method: "GET",
				url: "http://localhost:8080/FootBookRestClient/Matches/" + strValue,
				error: logAjaxError,
				success: ajaxFindReturnSuccess
			});
		} else {
			alert("Please provide a valid ID");
		}
	});

	// Delete operation handler
	$("#DeleteBtn").click(function() {
		var strValue = $("#id").val();
		if (strValue !== "") {
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

	// Delete success and error handlers
	function ajaxDeleteReturnSuccess(result, status, xhr) {
		clearFields();
		$("#id").attr("placeholder", "Match Deleted");
	}

	function ajaxDeleteReturnError(xhr, status, error) {
		alert("Error in Delete Ajax");
		logAjaxError(xhr, status, error);
	}

	// Find success handler
	function ajaxFindReturnSuccess(result, status, xhr) {
		ParseJsonFileMatch(result);
	}

	function ParseJsonFileMatch(result) {
		console.log("Received JSON:", result);
		$("#id").val(result["Match ID"]);
		$("#pitchId").val(result["Pitch ID"]);
		$("#refereeId").val(result["Referee ID"]);
		$("#date").val(result["Match Date"]);
		$("#time").val(result["Match Time"]);
	}


	// Clear all input fields
	function clearFields() {
		$("#id").val("");
		$("#date").val("");
		$("#time").val("");
		$("#pitchId").val("");
		$("#refereeId").val("");
	}


	// Add operation handler
	$("#AddBtn").click(function() {
		var strId = $("#id").val();
		var strDate = $("#date").val();
		var strTime = $("#time").val();
		var strPitchId = $("#pitchId").val();
		var strRefereeId = $("#refereeId").val();

		var obj = {
			"Match ID": strId,
			"Match Date": strDate,
			"Match Time": strTime,
			"Pitch ID": strPitchId,
			"Referee ID": strRefereeId
		};

		console.log("Match Data to Send:", obj); // Log the data to inspect it

		var jsonString = JSON.stringify(obj);

		if (strId !== "" && strPitchId !== "" && strRefereeId !== "") {
			$.ajax({
				method: "POST",
				url: "http://localhost:8080/FootBookRestClient/Matches/",
				data: jsonString,
				contentType: "application/json",
				error: ajaxAddReturnError,
				success: ajaxAddReturnSuccess
			});
		} else {
			alert("Please provide all required fields");
		}
	});


	// Add success and error handlers
	function ajaxAddReturnSuccess(result, status, xhr) {
		clearFields();
		$("#id").attr("placeholder", "Match Added");
	}

	function ajaxAddReturnError(xhr, status, error) {
		alert("Error in Add Ajax");
		logAjaxError(xhr, status, error);
	}

	$("#UpdateBtn").click(function() {
		var strId = $("#id").val();
		var strDate = $("#date").val();
		var strTime = $("#time").val();
		var strPitchId = $("#pitchId").val(); // Add this line
		var strRefereeId = $("#refereeId").val(); // Add this line

		var obj = {
			"Match ID": strId,
			"Match Date": strDate,
			"Match Time": strTime,
			"Pitch ID": strPitchId, // Add this field
			"Referee ID": strRefereeId // Add this field
		};

		var jsonString = JSON.stringify(obj);

		if (strId != "") {
			$.ajax({
				method: "PUT",
				url: "http://localhost:8080/FootBookRestClient/Matches/" + strId,
				data: jsonString,
				contentType: "application/json", // Ensure correct content type
				error: ajaxUpdateReturnError,
				success: ajaxUpdateReturnSuccess
			});
		} else {
			alert("Please provide a valid Match ID");
		}
	});


	// Update success and error handlers
	function ajaxUpdateReturnSuccess(result, status, xhr) {
		clearFields();
		$("#id").attr("placeholder", "Match updated");
	}

	function ajaxUpdateReturnError(xhr, status, error) {
		alert("Error Update");
		logAjaxError(xhr, status, error);
	}
	// Populate Pitch IDs
	function populatePitchIds() {
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/FootBookRestClient/Pitches/ids", // Adjust to your API endpoint
			success: function(data) {
				let pitchSelect1 = $("#pitchIdSelect");
				let pitchSelect2 = $("#findPitchIdSelect");
				data.forEach(function(item) {
					pitchSelect1.append(new Option(item.pitchId, item.pitchId));
					pitchSelect2.append(new Option(item.pitchId, item.pitchId));
				});
			},
			error: logAjaxError
		});
	}

	// Populate Referee IDs
	function populateRefereeIds() {
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/FootBookRestClient/Referees/ids", // Adjust to your API endpoint
			success: function(data) {
				let refereeSelect1 = $("#refereeIdSelect");
				let refereeSelect2 = $("#findRefereeIdSelect");
				data.forEach(function(item) {
					refereeSelect1.append(new Option(item.refereeId, item.refereeId));
					refereeSelect2.append(new Option(item.refereeId, item.refereeId));
				});
			},
			error: logAjaxError
		});
	}

	// Populate Match IDs
	function populateMatchIds() {
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/FootBookRestClient/Matches/ids", // Adjust to your API endpoint
			success: function(data) {
				let matchSelect = $("#findMatchIdSelect");
				data.forEach(function(item) {
					matchSelect.append(new Option(item, item)); // Use `item` directly for both value and display text
				});
			},
			error: logAjaxError
		});

	}

	// Call functions to populate dropdowns
	populatePitchIds();
	populateRefereeIds();
	populateMatchIds();



});
