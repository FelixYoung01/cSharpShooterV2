<%@page import="ics.ejb.RefereeLicense"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="ics.ejb.User, ics.ejb.Referee, java.util.Set"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Register</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div style="display: flex;">
        <div>
            <h2>Users</h2>
            <table class="box">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Gender</th>
                        <th>Age</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    Set<User> users = (Set<User>) request.getAttribute("users");
                    for (User user : users) {
                    %>
                    <tr>
                        <td><%=user.getUserId()%></td>
                        <td><%=user.getName()%></td>
                        <td><%=user.getEmail()%></td>
                        <td><%=user.getGender()%></td>
                        <td><%=user.getAge()%></td>
                        <td>
                            <button onclick="editUser('<%=user.getUserId()%>', '<%=user.getName()%>', '<%=user.getAge()%>', '<%=user.getEmail()%>', '<%=user.getGender()%>')">Edit</button>
                            <form action="/EJBFootBookWebProject/register" method="post" style="display:inline;">
                                <input type="hidden" name="formType" value="removeUser">
                                <input type="hidden" name="userId" value="<%=user.getUserId()%>">
                                <button type="submit" onclick="return confirm('Are you sure you want to remove this user?')">Remove</button>
                            </form>
                        </td>
                    </tr>
                    <%
                    }
                    %>
                </tbody>
            </table>
            <button id="addUserButton">Add</button>
            <div id="addUserForm" class="box popUp" style="display: none;">
                <h2>Add User</h2>
                <form id="addingUserForm" action="/EJBFootBookWebProject/register" name="userFormType" method="post">
                    <label for="userId">User ID will be auto-generated!</label> <br><br>
                    <label for="userName">Name:</label> 
                    <div style="display: flex; justify-content: center;">
                        <input type="text" class="bordered-input" id="userName" name="userName" required>
                    </div>
                    <label for="userAge">Age:</label> 
                    <div style="display: flex; justify-content: center;">
                        <input type="number" class="bordered-input" id="userAge" name="userAge" required>
                    </div>
                    <label for="userEmail">Email:</label> 
                    <div style="display: flex; justify-content: center;">
                        <input type="email" class="bordered-input" id="userEmail" name="userEmail" required>
                    </div>
                    <label for="userGender">Gender:</label> 
                    <div style="display: flex; justify-content: center;">
                        <select id="userGender" name="userGender" required>
                            <option value="M">Male</option>
                            <option value="F">Female</option>
                        </select>
                    </div><br>
                    <input type="hidden" name="formType" value="addUser">
                    <button type="submit">Submit</button>
                </form>
            </div>

            <div id="editUserForm" class="popUp" style="display: none;">
                <h2>Edit User</h2>
                <form id="editingUserForm" action="/EJBFootBookWebProject/register" method="post">
                    <input type="hidden" name="formType" value="editUser">
                    <input type="hidden" id="editUserId" name="userId" required>
                    <div>
                        <label>User ID:</label> <span id="displayUserId"></span>
                    </div>
                    <label for="editUserName">Name:</label> 
                    <input type="text" id="editUserName" name="userName" required><br>
                    <label for="editUserAge">Age:</label> 
                    <input type="number" id="editUserAge" name="userAge" required><br>
                    <label for="editUserEmail">Email:</label> 
                    <input type="email" id="editUserEmail" name="userEmail" required><br>
                    <label for="editUserGender">Gender:</label> 
                    <select id="editUserGender" name="userGender" required>
                        <option value="M">Male</option>
                        <option value="F">Female</option>
                    </select><br>
                    <button type="submit">Update User</button>
                </form>
            </div>
            
            <script>
                function editUser(userId, name, age, email, gender) {
                    document.getElementById('editUserId').value = userId;
                    document.getElementById('displayUserId').innerText = userId; // Display user ID but not editable
                    document.getElementById('editUserName').value = name;
                    document.getElementById('editUserAge').value = age;
                    document.getElementById('editUserEmail').value = email;
                    document.getElementById('editUserGender').value = gender;

                    document.getElementById('editUserForm').style.display = 'block';
                }

                // Event listener for showing add user form
                document.getElementById("addUserButton").addEventListener("click", function() {
                    document.getElementById("addUserForm").style.display = "block";
                });

                // Event listener for hiding add user form after submission
                document.getElementById("addingUserForm").addEventListener("submit", function(event) {
                    document.getElementById("addUserForm").style.display = "none";
                });
            </script>
        </div>

        <div style="margin-left: 20px;">
            <h2>Referees</h2>
            <table class="box">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>License ID</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    Set<Referee> referees = (Set<Referee>) request.getAttribute("referees");
                    for (Referee referee : referees) {
                    %>
                    <tr>
                        <td><%=referee.getRefereeId()%></td>
                        <td><%=referee.getRefereeName()%></td>
                        <td><%=referee.getRefereeLicense().getLicenseId()%></td>
                        <td>
                            <button onclick="editReferee('<%=referee.getRefereeId()%>', '<%=referee.getRefereeName()%>', '<%=referee.getRefereeLicense().getLicenseId()%>')">RefEdit</button>
                            <form action="/EJBFootBookWebProject/register" method="post" style="display:inline;">
                                <input type="hidden" name="formType" value="removeReferee">
                                <input type="hidden" name="refereeId" value="<%=referee.getRefereeId()%>">
                                <button type="submit" onclick="return confirm('Are you sure you want to remove this referee?')">Remove</button>
                            </form>
                        </td>
                    </tr>
                    <%
                    }
                    %>
                </tbody>
            </table>

            <button id="addRefereeButton">Add</button>
            <div id="addRefereeForm" class="box popUp" style="display: none;">
                <h2>Add Referee</h2>
                <form id="addingRefereeForm" action="/EJBFootBookWebProject/register" name="refereeFormType" method="post">
                    <label for="RefereeId">Referee ID will be auto-generated!</label> <br><br>
                    <label for="refereeName">Name:</label> 
                    <div style="display: flex; justify-content: center;">
                        <input type="text" class="bordered-input" id="refereeName" name="refereeName" required>
                    </div>
                    <label for="licenseId">License:</label>
                    <div style="display: flex; justify-content: center;">
                        <select id="licenseId" name="licenseId" required>
                            <option value="">Select License</option>
                            <%
                            List<RefereeLicense> licenses = (List<RefereeLicense>) request.getAttribute("licenses");
                            if (licenses == null) {
                                System.out.println("licenses is null");
                            } else {
                                for (RefereeLicense license : licenses) {
                            %>
                            <option value="<%=license.getLicenseId()%>"><%=license.getLicenseId()%></option>
                            <%
                                }
                            }
                            %>
                        </select>
                    </div><br>
                    <input type="hidden" name="formType" value="addReferee">
                    <button type="submit">Submit</button>
                </form>
            </div>

            <div id="editRefereeForm" class="popUp" style="display: none;">
                <h2>Edit Referee</h2>
                <form id="editingRefereeForm" action="/EJBFootBookWebProject/register" method="post">
                    <input type="hidden" name="formType" value="editReferee">
                    <input type="hidden" id="editRefereeId" name="refereeId" required>
                    <label for="displayRefereeId">Referee ID:</label> 
                    <input type="text" id="displayRefereeId" value="" disabled><br>
                    <label for="editRefereeName">Name:</label> 
                    <input type="text" id="editRefereeName" name="refereeName" required><br>
                    <label for="editRefereeLicense">License:</label> 
                    <select id="editRefereeLicense" name="licenseId" required>
                        <%
                        for (RefereeLicense license : licenses) {
                        %>
                        <option value="<%=license.getLicenseId()%>"><%=license.getLicenseId()%></option>
                        <%
                        }
                        %>
                    </select><br>
                    <button type="submit">Update Referee</button>
                </form>
            </div>

            <script>
                function editReferee(refereeId, refereeName, licenseId) {
                    document.getElementById('editRefereeId').value = refereeId;
                    document.getElementById('displayRefereeId').value = refereeId;
                    document.getElementById('editRefereeName').value = refereeName;
                    document.getElementById('editRefereeLicense').value = licenseId;

                    document.getElementById('editRefereeForm').style.display = 'block';
                }

                // Event listener for showing add referee form
                document.getElementById("addRefereeButton").addEventListener("click", function() {
                    document.getElementById("addRefereeForm").style.display = "block";
                });

                // Event listener for hiding add referee form after submission
                document.getElementById("addingRefereeForm").addEventListener("submit", function(event) {
                    document.getElementById("addRefereeForm").style.display = "none";
                });

                // Event listener for showing add user form
                document.getElementById("addUserButton").addEventListener("click", function() {
                    document.getElementById("addUserForm").style.display = "block";
                });

                // Event listener for hiding add user form after submission
                document.getElementById("addingUserForm").addEventListener("submit", function(event) {
                    document.getElementById("addUserForm").style.display = "none";
                });
            </script>
        </div>
    </div>
    <script src="Darkmode.js"></script>
</body>
</html>
