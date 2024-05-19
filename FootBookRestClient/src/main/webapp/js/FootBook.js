$(document).ready(function() {
	// General AJAX error logging function
	function logAjaxError(xhr, status, error) {
		console.log("Ajax Error:", status, error);
	}

	// Fetch and populate user IDs on page load
	function populateUserIds() {
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/FootBookRestClient/Users/", // Fetch all users
			dataType: "json", // Expect JSON response
			success: function(users) {
				let userSelect = $("#findUserIdSelect");
				userSelect.empty(); // Clear current options
				userSelect.append(new Option("Select User ID", "")); // Default option
				users.forEach(function(user) {
					console.log("Adding user to dropdown:", user.userId); // Log user ID being added
					userSelect.append(new Option(user.Id, user.Id));
				});
			},
			error: logAjaxError
		});
	}

	populateUserIds();


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
			url: `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&units=metric&APPID=ce3e097846c8e55864481f37b93db22f`,
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

	// Fetch the location and weather data when the page loads
	getLocation();
	fetchIPAddress();

	// Add User event handler
	$("#AddBtn").click(function() {
		var strUserId = $("#userId").val().toUpperCase();
		var strUserName = $("#userName").val();
		var strUserAge = $("#userAge").val();
		var strUserEmail = $("#userEmail").val();
		var strUserGender = $("#userGender").val().toUpperCase(); // Convert to uppercase

		// Validate user ID format
		var userIdPattern = /^U\d{2}$/;
		if (!userIdPattern.test(strUserId)) {
			alert("User ID must be in the format 'U00'");
			return;
		}

		// Validate name format (only letters)
		var namePattern = /^[A-Za-z\s]+$/;
		if (!namePattern.test(strUserName)) {
			alert("Name can only contain letters and spaces.");
			return;
		}

		// Validate age (must be a number between 18 and 99)
		if (isNaN(strUserAge) || strUserAge < 18 || strUserAge > 99) {
			alert("Age must be a number between 18 and 99.");
			return;
		}


		// Validate email format
		var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		if (!emailPattern.test(strUserEmail)) {
			alert("Please enter a valid email address.");
			return;
		}

		// Validate gender format
		if (strUserGender !== "M" && strUserGender !== "F") {
			alert("Please enter 'M' or 'F' for gender.");
			return;
		}

		var userObj = {
			"User ID": strUserId,
			"Name": strUserName,
			"Age": strUserAge,
			"Email": strUserEmail,
			"Gender": strUserGender
		};

		var jsonString = JSON.stringify(userObj); // Serialize the object to JSON string

		if (strUserId && strUserName && strUserAge && strUserEmail && strUserGender) {
			$.ajax({
				method: "POST", // POST is used to add new data
				url: "http://localhost:8080/FootBookRestClient/Users/", // Update with your endpoint
				data: jsonString, // Data being sent to the server
				dataType: "json", // Specifies the content type of the request body

				error: ajaxAddReturnError,
				success: ajaxAddReturnSuccess
			});
		} else {
			alert("Please provide all required fields");
		}
	});



	// Fetch Users event handler
	$("#FindBtn").click(function() {
		var strValue = $("#findUserIdSelect").val();// Get the selected user ID from the dropdown

		if (strValue != "") {
			$.ajax({
				method: "GET",
				url: `http://localhost:8080/FootBookRestClient/Users/` + strValue, // Update with your endpoint
				error: ajaxFindReturnError,
				success: ajaxFindReturnSuccess
			});
		} else {
			alert("Please enter a valid User ID");
		}

	});

	// Update User event handler
	// Update User event handler
	$("#UpdateBtn").click(function() {
		var strUserId = $("#findUserIdSelect").val().toUpperCase();
		var strUserName = $("#findUserName").val();
		var strUserAge = $("#findUserAge").val();
		var strUserEmail = $("#findUserEmail").val();
		var strUserGender = $("#findUserGender").val().toUpperCase();

		var userObj = {
			"User ID": strUserId
		};

		// Validate and include name if provided
		if (strUserName) {
			var namePattern = /^[A-Za-z\s]+$/;
			if (!namePattern.test(strUserName)) {
				alert("Name can only contain letters and spaces.");
				return;
			}
			userObj["Name"] = strUserName;
		}

		// Validate and include age if provided
		if (strUserAge) {
			if (isNaN(strUserAge) || strUserAge < 18 || strUserAge > 99) {
				alert("Age must be a number between 18 and 99.");
				return;
			}
			userObj["Age"] = strUserAge;
		}

		// Validate and include email if provided
		if (strUserEmail) {
			var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
			if (!emailPattern.test(strUserEmail)) {
				alert("Please enter a valid email address.");
				return;
			}
			userObj["Email"] = strUserEmail;
		}

		// Validate and include gender if provided
		if (strUserGender) {
			if (strUserGender !== "M" && strUserGender !== "F") {
				alert("Please enter 'M' or 'F' for gender.");
				return;
			}
			userObj["Gender"] = strUserGender;
		}

		var jsonString = JSON.stringify(userObj); // Serialize the object to JSON string

		$.ajax({
			method: "PUT", // PUT is used to update data
			url: `http://localhost:8080/FootBookRestClient/Users/` + strUserId, // Update with your endpoint
			data: jsonString, // Data being sent to the server
			contentType: "application/json", // Specifies the content type of the request body
			error: ajaxUpdateReturnError,
			success: function(result, status, xhr) {
				ajaxUpdateReturnSuccess(result, status, xhr);
				ParseJsonFileUser(result); // Refresh display with updated user info
			}
		});
	});


	// Delete User event handler
	$("#DeleteBtn").click(function() {

		var strValue = $("#findUserIdSelect").val();
		if (strValue != "") {
			$.ajax({
				method: "DELETE", // DELETE is used to delete data
				url: `http://localhost:8080/FootBookRestClient/Users/` + strValue, // Update with your endpoint
				error: ajaxDelReturnError,
				success: ajaxDelReturnSuccess
			});
		} else {
			alert("Please enter a valid User ID");
		}

	});

	// Success function for update user
	function ajaxUpdateReturnSuccess(result, status, xhr) {
		clearFindUpdateFields();
		clearDisplayFields();
		alert("User successfully updated");
	}


	// Error function for update user
	function ajaxUpdateReturnError(result, status, xhr) {
		alert("Error updating user");
		console.log("Ajax-update user: " + status);
	}

	// Success function for add user
	function ajaxAddReturnSuccess(result, status, xhr) {
		clearAddUserFields();
		clearDisplayFields();
		populateUserIds(); // Refresh the user list in the dropdown

		alert("User successfully added");
	}

	// Error function for add user
	function ajaxAddReturnError(result, status, xhr) {
		alert("User ID already exists. Please enter a different User ID.");
		console.log("Ajax-add user: " + status);
	}

	// Success function for find user
	function ajaxFindReturnSuccess(result, status, xhr) {
		if ($.isEmptyObject(result)) {
			alert("User not found.");
		} else {
			ParseJsonFileUser(result);
		}
	}

	// Error function for find user
	function ajaxFindReturnError(result, status, xhr) {
		alert("Error");
		console.log("Ajax-find user: " + status);
	}

	// Success function for delete user
	function ajaxDelReturnSuccess(result, status, xhr) {
		clearFindUpdateFields();
		clearDisplayFields();
		populateUserIds(); // Refresh the user list in the dropdown

		alert("User successfully deleted");
	}


	// Error function for delete user
	function ajaxDelReturnError(result, status, xhr) {
		alert("Error deleting user");
		console.log("Ajax-delete user: " + status);
	}




	// Clear input fields for adding a new user:
	function clearAddUserFields() {
		$("#userId").val("");
		$("#userName").val("");
		$("#userAge").val("");
		$("#userEmail").val("");
		$("#userGender").val("");
	}

	//Clear Input Fields for Finding/Updating a User:
	function clearFindUpdateFields() {
		$("#findUserIdSelect").val("");
		$("#findUserName").val("");
		$("#findUserAge").val("");
		$("#findUserEmail").val("");
		$("#findUserGender").val("");
	}

	//Clear Display Fields:
	function clearDisplayFields() {
		$("#displayUserId").text("User ID:");
		$("#displayUserName").text("Name:");
		$("#displayUserAge").text("Age:");
		$("#displayUserEmail").text("Email:");
		$("#displayUserGender").text("Gender:");
	}


	function ParseJsonFileUser(result) {
		$("#displayUserId").text("User ID: " + result["User ID"]);
		$("#displayUserName").text("Name: " + result.Name);
		$("#displayUserAge").text("Age: " + result.Age);
		$("#displayUserEmail").text("Email: " + result.Email);
		$("#displayUserGender").text("Gender: " + result.Gender);
	}

});


