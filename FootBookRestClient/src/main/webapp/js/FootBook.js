$(document).ready(function() {
	// General AJAX error logging function
	function logAjaxError(xhr, status, error) {
		console.log("Ajax Error:", status, error);
	}

	// Geolocation functions
	function getLocation() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(showPosition, handleGeolocationError);
		} else {
			alert("Geolocation is not supported by this browser.");
		}
	}

	function showPosition(position) {
		// Call ParseJsonFile with the obtained coordinates
		ParseJsonFile(position);
	}

	function handleGeolocationError(error) {
		console.warn(`Geolocation error (${error.code}): ${error.message}`);
	}

	// Fetch and process weather data using OpenWeatherMap API
	function ParseJsonFile(position) {
		var lat = position.coords.latitude;
		var long = position.coords.longitude;

		// Make the AJAX request to the OpenWeatherMap API
		$.ajax({
			method: "GET",
			url: `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&units=metric&APPID=ce3e097846c8e55864481f37b93db22f`, // Replace with your new API key
			error: ajaxWeatherReturn_Error,
			success: ajaxWeatherReturn_Success
		});
	}

	function ajaxWeatherReturn_Success(result, status, xhr) {
		var sunrise = result.sys.sunrise;
		var sunset = result.sys.sunset;
		var sunriseDate = new Date(sunrise * 1000);
		var sunsetDate = new Date(sunset * 1000);
		var timeStrSunrise = sunriseDate.toLocaleTimeString("sv-SE");
		var timeStrSunset = sunsetDate.toLocaleTimeString("sv-SE");
		var city = result.name;

		$("#city").text(city);
		$("#sunrise").text("Sunrise: " + timeStrSunrise);
		$("#sunset").text("Sunset: " + timeStrSunset);
		$("#weather").text(result.weather[0].main);
		$("#degree").text(result.main.temp + " \u2103");
	}

	function ajaxWeatherReturn_Error(result, status, xhr) {
		alert("Error in OpenWeatherMap Ajax");
		console.log("Ajax-find weather: " + status);
	}

	// Function to fetch and display the IP address using the ipinfo API
	function fetchIPAddress() {
		$.ajax({
			method: "GET",
			url: `https://ipinfo.io/?token=7b10316367bd0b`,
			error: logAjaxError,
			success: function(result) {
				$("#ipNbr").text(result.ip);
			}
		});
	}
	$("#UpdateBtn").click(function() {
		// Retrieve the selected Match ID
		var selectedMatchId = $("#findMatchIdSelect").val();
		if (selectedMatchId !== "") {
			// Retrieve updated data from section two
			var pitchId = $("#findPitchIdSelect").val();
			var refereeId = $("#findRefereeIdSelect").val();
			var dateValue = $("#findDate").val(); // In the format DD/MM/YYYY
			var timeValue = $("#findTime").val(); // In the format HH:MM

			// Create the object for updating
			var matchObj = { "Match ID": selectedMatchId };

			// Check if each field has a value and add it to the object only if non-empty
			if (pitchId !== "") matchObj["Pitch ID"] = pitchId;
			if (refereeId !== "") matchObj["Referee ID"] = refereeId;
			if (dateValue !== "") {
				let dateParts = dateValue.split("/");
				let formattedDate = `${dateParts[2]}-${dateParts[1]}-${dateParts[0]}`; // Convert to YYYY-MM-DD
				matchObj["Match Date"] = formattedDate;
			}
			if (timeValue !== "") matchObj["Match Time"] = timeValue;

			// Convert to JSON string
			var jsonString = JSON.stringify(matchObj);

			// Send the PUT request to update the match
			$.ajax({
				method: "PUT",
				url: `http://localhost:8080/FootBookRestClient/Matches/${selectedMatchId}`,
				data: jsonString,
				contentType: "application/json",
				error: function(xhr, status, error) {
					console.log("Error updating match:", error);
					alert("Error updating match: " + error);
				},
				success: function(result, status, xhr) {
					alert(`Match ID: ${selectedMatchId} updated successfully.`);

					// Clear and refresh section two
					clearAndRefreshSectionTwo();

					// Fetch and display the updated match details
					$.ajax({
						method: "GET",
						url: `http://localhost:8080/FootBookRestClient/Matches/${selectedMatchId}`,
						error: logAjaxError,
						success: function(result) {
							// Adjust date format (from yyyy-mm-dd to dd/mm/yyyy)
							let matchDate = result["Match Date"].split("-");
							let formattedDate = `${matchDate[2]}/${matchDate[1]}/${matchDate[0]}`;

							// Update the display with all the current match details
							$("#displayMatchId").text("Match ID: " + result["Match ID"]);
							$("#displayPitchId").text("Pitch ID: " + result["Pitch ID"]);
							$("#displayRefereeId").text("Referee ID: " + result["Referee ID"]);
							$("#displayMatchDate").text("Date: " + formattedDate);
							$("#displayMatchTime").text("Time: " + result["Match Time"]);
						}
					});
				}
			});
		} else {
			alert("Please select a valid Match ID to update.");
		}
	});

	function clearAndRefreshSectionTwo() {
		// Clear all dropdowns and inputs in section two
		$("#findMatchIdSelect").val("");
		$("#findPitchIdSelect").val("");
		$("#findRefereeIdSelect").val("");
		$("#findDate").val("");
		$("#findTime").val("");

		// Repopulate the dropdowns with the latest data
		populatePitchIds();
		populateRefereeIds();
		populateMatchIds();
	}











	// Find operation handler
	$("#FindBtn").click(function() {
		// Get the selected value from the dropdown
		var selectedMatchId = $("#findMatchIdSelect").val();
		if (selectedMatchId !== "") {
			$.ajax({
				method: "GET",
				url: `http://localhost:8080/FootBookRestClient/Matches/${selectedMatchId}`,
				error: logAjaxError,
				success: function(result) {
					// Adjust date format (from yyyy-mm-dd to dd/mm/yyyy)
					let matchDate = result["Match Date"].split("-");
					let formattedDate = `${matchDate[2]}/${matchDate[1]}/${matchDate[0]}`;

					// Update the text display fields
					$("#displayMatchId").text("Match ID: " + result["Match ID"]);
					$("#displayPitchId").text("Pitch ID: " + result["Pitch ID"]);
					$("#displayRefereeId").text("Referee ID: " + result["Referee ID"]);
					$("#displayMatchDate").text("Date: " + formattedDate);
					$("#displayMatchTime").text("Time: " + result["Match Time"]);
				}
			});
		} else {
			alert("Please select a valid Match ID");
		}
	});

	// Delete button click event handler
	$("#DeleteBtn").click(function() {
		var selectedMatchId = $("#findMatchIdSelect").val();
		if (selectedMatchId !== "") {
			// Confirm with the user before deleting
			if (confirm(`Are you sure you want to delete Match ID: ${selectedMatchId}?`)) {
				// Send DELETE request to the server
				$.ajax({
					method: "DELETE",
					url: `http://localhost:8080/FootBookRestClient/Matches/${selectedMatchId}`,
					error: logAjaxError,
					success: function() {
						alert(`Match ID: ${selectedMatchId} successfully deleted.`);
						// Clear text display fields after successful deletion
						$("#displayMatchId").text("");
						$("#displayPitchId").text("");
						$("#displayRefereeId").text("");
						$("#displayMatchDate").text("");
						$("#displayMatchTime").text("");

						// Refresh the Match dropdown
						populateMatchIds();
					}
				});
			}
		} else {
			alert("Please select a valid Match ID");
		}
	});

	// Add operation handler
	$("#AddBtn").click(function() {
		var matchId = $("#id").val();
		var matchDate = $("#date").val();
		var matchTime = $("#time").val();
		var pitchId = $("#pitchIdSelect").val();
		var refereeId = $("#refereeIdSelect").val();

		var matchObj = {
			"Match ID": matchId,
			"Match Date": matchDate,
			"Match Time": matchTime,
			"Pitch ID": pitchId,
			"Referee ID": refereeId
		};

		var jsonString = JSON.stringify(matchObj);

		if (matchId && matchDate && matchTime && pitchId && refereeId) {
			$.ajax({
				method: "POST",
				url: "http://localhost:8080/FootBookRestClient/Matches/",
				data: jsonString,
				contentType: "application/json",
				error: function(xhr, status, error) {
					console.log("Error in Add Ajax:", error);
					alert("Error adding match: " + error);
				},
				success: function(result, status, xhr) {
					alert("Match added successfully!");
					// Clear input fields after adding a match
					clearSectionOneFields();
					// Refresh the match dropdown in section 2
					populateMatchIds();
				}
			});
		} else {
			alert("Please provide all required fields");
		}
	});

	// Function to clear section one input fields
	function clearSectionOneFields() {
		$("#id").val("");
		$("#date").val("");
		$("#time").val("");
		$("#pitchIdSelect").val("");
		$("#refereeIdSelect").val("");
	}

	// Populate Match IDs
	function populateMatchIds() {
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/FootBookRestClient/Matches/ids",
			success: function(data) {
				let matchSelect = $("#findMatchIdSelect");
				matchSelect.empty(); // Clear current options
				matchSelect.append(new Option("Select Match ID", "")); // Default option
				data.forEach(function(item) {
					matchSelect.append(new Option(item, item));
				});
			},
			error: logAjaxError
		});
	}

	// Populate Pitch IDs
	function populatePitchIds() {
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/FootBookRestClient/Pitches/ids",
			success: function(data) {
				let pitchSelect1 = $("#pitchIdSelect");
				let pitchSelect2 = $("#findPitchIdSelect");

				// Clear previous options
				pitchSelect1.empty().append(new Option("Select Pitch ID", ""));
				pitchSelect2.empty().append(new Option("Select Pitch ID", ""));

				// Populate both dropdowns with options
				data.forEach(function(item) {
					pitchSelect1.append(new Option(item, item));
					pitchSelect2.append(new Option(item, item));
				});
			},
			error: logAjaxError
		});
	}

	// Populate Referee IDs
	function populateRefereeIds() {
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/FootBookRestClient/Referees/ids",
			success: function(data) {
				let refereeSelect1 = $("#refereeIdSelect");
				let refereeSelect2 = $("#findRefereeIdSelect");

				// Clear previous options
				refereeSelect1.empty().append(new Option("Select Referee ID", ""));
				refereeSelect2.empty().append(new Option("Select Referee ID", ""));

				// Populate both dropdowns with options
				data.forEach(function(item) {
					refereeSelect1.append(new Option(item, item));
					refereeSelect2.append(new Option(item, item));
				});
			},
			error: logAjaxError
		});
	}

	// Call functions to populate dropdowns
	populatePitchIds();
	populateRefereeIds();
	populateMatchIds();

	// Fetch the location and weather data when the page loads
	getLocation();
	fetchIPAddress();

});