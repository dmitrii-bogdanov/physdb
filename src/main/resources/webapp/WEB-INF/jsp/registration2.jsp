<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration page</title>
</head>
<body>
<form method="POST" modelAttribute="userForm">
    <h2>Registration</h2>
    <div>
        <label for="username"><input type="text" placeholder="Username" id="username" path="username" required></label>
        <label for="email"><input type="text" placeholder="Email address" id="email" path="email" required></label>
    </div>
    <div>
        <label for="password"><input type="password" placeholder="Password" id="password" path="password" required></label>
        <label for="confirm_password"><input type="password" placeholder="Confirm Password" id="confirm_password" required></label>
    </div>
    <div>
        <label for="firstname"><input type="text" placeholder="First name" id="firstname" path="firstname" required></label>
        <label for="lastname"><input type="text" placeholder="Last name" id="lastname" path="lastname" required></label>
    </div>
    <button type="submit">Confirm</button>
    <script>
        var password = document.getElementById("password")
            , confirm_password = document.getElementById("confirm_password");

        function validatePassword() {
            if (password.value != confirm_password.value) {
                confirm_password.setCustomValidity("Passwords Don't Match");
            } else {
                confirm_password.setCustomValidity('');
            }
        }

        password.onchange = validatePassword;
        confirm_password.onkeyup = validatePassword;
    </script>
</form>
</body>
</html>