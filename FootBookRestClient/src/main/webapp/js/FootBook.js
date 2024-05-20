$(document).ready(function() {
	// General AJAX error logging function
	function logAjaxError(xhr, status, error) {
		console.log("Ajax Error:", status, error);
	}

	// Fetch and populate user list on page load
	function populateUserList() {
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/FootBookRestClient/Users/",
			dataType: "json",
			success: function(users) {
				let userList = $("#userList");
				userList.empty(); // Clear current list
				users.forEach(function(user) {
					console.log("Adding user to list:", user.userId); // Log user ID being added
					userList.append(`<li class="list-group-item" data-id="${user.Id}">${user.Id}: ${user.Name}</li>`);
				});
			},
			error: logAjaxError
		});
	}

	populateUserList();

	// Geolocation functions
	function getLocation() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(showPosition, handleGeolocationError);
		} else {
			alert("Geolocation is not supported by this browser.");
		}
	}

	function showPosition(position) {
		ParseJsonFile(position);
	}

	function handleGeolocationError(error) {
		console.warn(`Geolocation error (${error.code}): ${error.message}`);
	}

	// Fetch and process weather data using OpenWeatherMap API
	function ParseJsonFile(position) {
		var lat = position.coords.latitude;
		var long = position.coords.longitude;

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
	
	//display ip address
	fetchIPAddress();
	
	//display weather
	getLocation();

	// Add User event handler
	$("#AddBtn").click(function() {
		var strUserId = $("#userId").val().toUpperCase();
		var strUserName = $("#userName").val();
		var strUserAge = $("#userAge").val();
		var strUserEmail = $("#userEmail").val();
		var strUserGender = $("#userGender").val().toUpperCase();

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

		var jsonString = JSON.stringify(userObj);

		$.ajax({
			method: "POST",
			url: "http://localhost:8080/FootBookRestClient/Users/",
			data: jsonString,
			dataType: "json",
			success: function(result) {
				ajaxAddReturnSuccess(result);
				populateUserList(); // Refresh the user list
			},
			error: ajaxAddReturnError
		});
	});

	// Update User event handler
	$("#UpdateBtn").click(function() {
		var strUserId = $("#userId").val().toUpperCase();
		var strUserName = $("#userName").val();
		var strUserAge = $("#userAge").val();
		var strUserEmail = $("#userEmail").val();
		var strUserGender = $("#userGender").val().toUpperCase();

		var userObj = {
			"User ID": strUserId
		};

		if (strUserName) {
			var namePattern = /^[A-Za-z\s]+$/;
			if (!namePattern.test(strUserName)) {
				alert("Name can only contain letters and spaces.");
				return;
			}
			userObj["Name"] = strUserName;
		}

		if (strUserAge) {
			if (isNaN(strUserAge) || strUserAge < 18 || strUserAge > 99) {
				alert("Age must be a number between 18 and 99.");
				return;
			}
			userObj["Age"] = strUserAge;
		}

		if (strUserEmail) {
			var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
			if (!emailPattern.test(strUserEmail)) {
				alert("Please enter a valid email address.");
				return;
			}
			userObj["Email"] = strUserEmail;
		}

		if (strUserGender) {
			if (strUserGender !== "M" && strUserGender !== "F") {
				alert("Please enter 'M' or 'F' for gender.");
				return;
			}
			userObj["Gender"] = strUserGender;
		}

		var jsonString = JSON.stringify(userObj);

		$.ajax({
			method: "PUT",
			url: `http://localhost:8080/FootBookRestClient/Users/` + strUserId,
			data: jsonString,
			contentType: "application/json",
			success: function(result) {
				ajaxUpdateReturnSuccess(result);
				populateUserList(); // Refresh the user list
			},
			error: ajaxUpdateReturnError
		});
	});

	// Delete User event handler
	$("#DeleteBtn").click(function() {
		var strUserId = $("#userId").val().toUpperCase();

		if (strUserId !== "") {
			$.ajax({
				method: "DELETE",
				url: `http://localhost:8080/FootBookRestClient/Users/` + strUserId,
				success: function(result) {
					ajaxDelReturnSuccess(result);
					populateUserList(); // Refresh the user list
				},
				error: ajaxDelReturnError
			});
		} else {
			alert("Please enter a valid User ID");
		}
	});

	// Success function for adding user
	function ajaxAddReturnSuccess(result) {
		clearUserFormFields();
		alert("User successfully added");
	}

	// Error function for adding user
	function ajaxAddReturnError(xhr, status, error) {
		if (xhr.status === 409) { // 409 Conflict
			alert("User ID already exists. Please enter a different User ID.");
		} else {
			alert("An error occurred while adding the user. Please try again.");
		}
		console.log("Ajax-add user error:", status, error);
	}

	// Success function for updating user
	function ajaxUpdateReturnSuccess(result) {
		clearUserFormFields();
		alert("User successfully updated");
		ParseJsonFileUser(result); // Refresh the user details box with the updated user details
	}

	// Error function for updating user
	function ajaxUpdateReturnError(xhr, status, error) {
		alert("Make sure to select a USER ID");
		console.log("Ajax-update user error:", status, error);
	}

	// Success function for deleting user
	function ajaxDelReturnSuccess(result) {
		clearUserFormFields();
		alert("User successfully deleted");
	}

	// Error function for deleting user
	function ajaxDelReturnError(xhr, status, error) {
		alert("Error deleting user");
		console.log("Ajax-delete user error:", status, error);
	}

	// Clear input fields for user form
	function clearUserFormFields() {
		$("#userId").val("");
		$("#userName").val("");
		$("#userAge").val("");
		$("#userEmail").val("");
		$("#userGender").val("");
	}

	// Function to display user details
	function ParseJsonFileUser(result) {
		$("#displayUserId").text("User ID: " + result["User ID"]);
		$("#displayUserName").text("Name: " + result.Name);
		$("#displayUserAge").text("Age: " + result.Age);
		$("#displayUserEmail").text("Email: " + result.Email);
		$("#displayUserGender").text("Gender: " + result.Gender);
	}

	// Handle user list click to display details
	$("#userList").on("click", "li", function() {
		var userId = $(this).data("id");
		$.ajax({
			method: "GET",
			url: `http://localhost:8080/FootBookRestClient/Users/` + userId,
			success: ajaxFindReturnSuccess,
			error: ajaxFindReturnError
		});
	});

	// Fetch and display user details based on user ID
	$("#FindBtn").click(function() {
		var strUserId = $("#userId").val().toUpperCase(); // Get the user ID from the input field

		if (strUserId != "") {
			$.ajax({
				method: "GET",
				url: `http://localhost:8080/FootBookRestClient/Users/` + strUserId, // Update with your endpoint
				success: ajaxFindReturnSuccess,
				error: ajaxFindReturnError
			});
		} else {
			alert("Please enter a valid User ID");
		}
	});

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
});
