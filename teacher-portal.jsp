<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teacher Portal - Profile Settings</title>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(to right, #141e30, #243b55);
            margin: 0;
        }
        .container {
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
            max-width: 380px;
            text-align: center;
        }
        h2 {
            font-size: 24px;
            font-weight: 700;
            color: #333;
            margin-bottom: 10px;
        }
        h3 {
            font-size: 18px;
            font-weight: 500;
            color: #666;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }
        .form-group label {
            display: block;
            font-weight: 600;
            margin-bottom: 5px;
            color: #444;
        }
        .form-group input {
            width: 100%;
            padding: 10px;
            font-size: 14px;
            border: 2px solid #ccc;
            border-radius: 6px;
            outline: none;
            transition: 0.3s ease;
        }
        .btn {
            width: 100%;
            padding: 12px;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: 0.3s ease;
        }
        .save {
            background: #28a745;
            color: white;
        }
        .back {
            background: #dc3545;
            color: white;
            margin-top: 10px;
        }
        .profile-info {
            /* display: none; */
            text-align: left;
        }
        .nav-bar {
            position: absolute;
            top: 10px;
            right: 10px;
            display: flex;
            align-items: center;
        }
        .back-button {
            background: none;
            border: none;
            color: white;
            font-size: 18px;
            cursor: pointer;
        }
        .back-button:hover {
            text-decoration: underline;
        }
        button {
            background: #ff9800;
            color: white;
            cursor: pointer;
        }
        button:hover {
            background: #e68900;
        }
        footer { 
            margin-top: 20px; font-size: 12px; color: #1f1e1e; 
        }
    </style>
</head>
<body>


    <div class="nav-bar">
        <button class="back-button" onclick="goBack()">← Back</button>
    </div>
    <c:choose>
        <c:when test="${not empty profile}">
            <div id="profile-info" class="profile-info" style="max-width: 500px; margin: auto; padding: 20px; border: 1px solid #ccc; border-radius: 10px; background: #f9f9f9;">
                <h2 style="text-align: center; margin-bottom: 20px;">Profile Details</h2>
                
                <div style="margin-bottom: 10px;">
                    <strong>Name:</strong> <span id="display-name">${profile.name}</span>
                </div>
                <div style="margin-bottom: 10px;">
                    <strong>Contact:</strong> <span id="display-contact">${profile.contact}</span>
                </div>
                <div style="margin-bottom: 10px;">
                    <strong>Email:</strong> <span id="display-email">${profile.email}</span>
                </div>
                <div style="margin-bottom: 10px;">
                    <strong>Department:</strong> <span id="display-department">${profile.department}</span>
                </div>
                <div style="margin-bottom: 20px;">
                    <strong>Date of Birth:</strong> <span id="display-dob">${profile.dob}</span>
                </div>
            
                <div style="text-align: center;">
                    <button class="btn back" onclick="editProfile()" style="padding: 8px 16px; border: none; background: #007bff; color: white; border-radius: 5px; cursor: pointer;">
                        Edit Profile
                    </button>
                </div>
            </div>
            
        </c:when>
        <c:otherwise>

            <div class="container modal fade" id="info_modal">
                <div id="profile-settings">
                    <h2>Welcome to Educator’s Dashboard</h2>
                    <h3>My Profile</h3>
                    <form action="profile.do" method="post">
        
                        <div class="form-group">
                            <label for="teacher-name">Teacher Name</label>
                            <input type="text" id="teacher-name" name="teacher-name" placeholder="Enter your name">
                        </div>
                        <div class="form-group">
                            <label for="contact">Contact Number</label>
                            <input type="text" id="contact" name="contact" placeholder="Enter your contact number">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" id="email" name="email" placeholder="Enter your email">
                        </div>
                        <div class="form-group">
                            <label for="department">Department</label>
                            <input type="text" name="department" id="department" placeholder="Enter your department">
                        </div>
                        <div class="form-group">
                            <label for="dob">Date of Birth</label>
                            <input type="date" name="dob" id="dob">
                        </div>
                        <button type="submit" class="btn save">Save Changes</button>
                        <footer>©शिक्षा④All 2025. All Rights Reserved.</footer>
                    </form>
                </div>      
            </div>
        </c:otherwise>
    </c:choose>
    <script>
        // function saveProfile() {
        //     document.getElementById('display-name').innerText = document.getElementById('teacher-name').value;
        //     document.getElementById('display-contact').innerText = document.getElementById('contact').value;
        //     document.getElementById('display-email').innerText = document.getElementById('email').value;
        //     document.getElementById('display-department').innerText = document.getElementById('department').value;
        //     document.getElementById('display-dob').innerText = document.getElementById('dob').value;
            
        //     document.getElementById('profile-settings').style.display = 'none';
        //     document.getElementById('profile-info').style.display = 'block';
        // }
        
        // function editProfile() {
        //     document.getElementById('profile-settings').style.display = 'block';
        //     document.getElementById('profile-info').style.display = 'none';
        // }
        
        function goBack() {
            window.location.href = 'teachers.html';
        }
    </script>
</body>
</html>
