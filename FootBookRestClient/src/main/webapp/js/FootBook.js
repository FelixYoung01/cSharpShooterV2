$(document).ready(function() { //Wait for the DOM to be ready then run the code
	// General AJAX error logging function
	function logAjaxError(xhr, status, error) {
		console.log("Ajax Error:", status, error);
	}

	// Fetch and populate user list on page load
	function populateUserList() {
		$.ajax({
			method: "GET", // Use the GET method on the endpoint
			url: "http://localhost:8080/FootBookRestClient/Users/", //The endpoint URL (We look for a servlet that matches the URL pattern "/Users/*" ,which is decla)
			dataType: "json", // The data type we expect back. this means that the endpoint data will be converted to a JSON object
			success: function(users) { //the success function will be called when the AJAX request is successful, with users being the data returned from the endpoint
				let userList = $("#userList"); // Get the user list element, defined in the HTML 
				userList.empty(); // Clear current list
				users.forEach(function(user) {
					userList.append(`<li class="list-group-item" data-id="${user.Id}">${user.Id}: ${user.Name}</li>`); //Add each user to the list element... Accessthe Id property of the user object and the Name property
				});
			},
			error: logAjaxError
		});
	}

	populateUserList();

	// Geolocation functions
	function getLocation() {
		if (navigator.geolocation) {//Check if the browser supports geolocation (Allow Google Chrome to access your location)
			navigator.geolocation.getCurrentPosition(showPosition, handleGeolocationError);
		} else {
			alert("Geolocation is not supported by this browser.");
		}
	}

	function showPosition(position) {//If the browser supports geolocation, get the current position of the user, and pass it to the ParseJsonFile function
		ParseJsonFile(position);//The position object contains the latitude and longitude of the user's current location
	}

	function handleGeolocationError(error) {
		console.warn(`Geolocation error (${error.code}): ${error.message}`);
	}

	// Fetch and process weather data using OpenWeatherMap API
	function ParseJsonFile(position) {//The position object contains the latitude and longitude of the user's current location
		var lat = position.coords.latitude;//Get the latitude and longitude of the user's current location
		var long = position.coords.longitude;//Get the latitude and longitude of the user's current location

		$.ajax({
			method: "GET",//Use the GET method on the endpoint
			url: `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&units=metric&APPID=ce3e097846c8e55864481f37b93db22f`,//The endpoint URL. The latitude and longitude of the user's current location are passed as parameters to the endpoint
			error: ajaxWeatherReturn_Error,
			success: ajaxWeatherReturn_Success
		});
	}

	function ajaxWeatherReturn_Success(result, status, xhr) {//The success function will be called when the AJAX request is successful, with result being the data returned from the endpoint
		var sunrise = result.sys.sunrise;//Get the sunrise and sunset times from the result object
		var sunset = result.sys.sunset;
		var sunriseDate = new Date(sunrise * 1000);//Convert the sunrise and sunset times to Date objects
		var sunsetDate = new Date(sunset * 1000);
		var timeStrSunrise = sunriseDate.toLocaleTimeString("sv-SE");//Convert the Date objects to a string in the format "HH:MM:SS"
		var timeStrSunset = sunsetDate.toLocaleTimeString("sv-SE");
		var city = result.name;//Get the city name from the result object

		$("#city").text(city);//Display the city name, sunrise and sunset times, weather, and temperature on the page with HTML  elements
		$("#sunrise").text("Sunrise: " + timeStrSunrise);
		$("#sunset").text("Sunset: " + timeStrSunset);
		$("#weather").text(result.weather[0].main);
		$("#degree").text(result.main.temp + " \u2103");//The temperature is displayed in degrees Celsius
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
	$("#AddBtn").click(function() {//When the Add User button is clicked, get the values of the user ID, name, age, etc... from the input fields (HTML elements)
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

		var userObj = {//Create a user object with the user ID, name, age, email, and gender. taken from the input fields
			"User ID": strUserId,
			"Name": strUserName,
			"Age": strUserAge,
			"Email": strUserEmail,
			"Gender": strUserGender
		};

		var jsonString = JSON.stringify(userObj);//Convert the user object to a JSON string. This is done because the endpoint expects JSON data as the data type.

		$.ajax({
			method: "POST", // Use the POST method on the endpoint
			url: "http://localhost:8080/FootBookRestClient/Users/",//The endpoint URL
			data: jsonString,//The user object as a JSON string that was created earlier
			dataType: "json",//The data type we expect back. this means that the endpoint data will be converted to a JSON object
			success: function(result) {//The success function will be called when the AJAX request is successful, with result being the data returned from the endpoint
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
