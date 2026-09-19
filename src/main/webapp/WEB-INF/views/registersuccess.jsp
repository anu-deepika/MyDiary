<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Successful - MyDiary App</title>
<link rel="stylesheet" href="<c:url value="/resources/styles/style.css"/>">
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(135deg, #e0eafc 0%, #cfdef3 100%);
        height: 100vh;
        display: flex;
        flex-direction: column;
    }

    .success-container {
        display: flex;
        align-items: center;
        justify-content: center;
        flex-grow: 1;
        text-align: center;
        padding: 20px;
    }

    .success-card {
        background: white;
        padding: 50px 60px;
        border-radius: 15px;
        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
        animation: slideUp 0.8s ease-out forwards;
        max-width: 500px;
        opacity: 0;
        transform: translateY(30px);
    }

    .success-icon {
        font-size: 70px;
        color: #28a745;
        margin-bottom: 15px;
        animation: popIn 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.275) forwards;
        opacity: 0;
        transform: scale(0.5);
    }

    .success-title {
        color: SteelBlue;
        font-size: 2.2em;
        margin: 0 0 15px 0;
    }

    .success-message {
        color: #555;
        font-size: 1.1em;
        line-height: 1.5;
        margin-bottom: 35px;
    }

    .btn-login {
        display: inline-block;
        padding: 12px 35px;
        border-radius: 25px;
        text-decoration: none;
        font-weight: bold;
        font-size: 1.1em;
        background: SteelBlue;
        color: white;
        border: 2px solid SteelBlue;
        transition: all 0.3s ease;
    }

    .btn-login:hover {
        background: white;
        color: SteelBlue;
        transform: translateY(-2px);
        box-shadow: 0 5px 15px rgba(70, 130, 180, 0.4);
    }

    /* Animations */
    @keyframes slideUp {
        to { opacity: 1; transform: translateY(0); }
    }
    
    @keyframes popIn {
        to { opacity: 1; transform: scale(1); }
    }
</style>
</head>
<body>

    <div class="header" style="box-shadow: 0 2px 10px rgba(0,0,0,0.1);">
        <div class="first">
            <img src="<c:url value="/resources/images/diary.jpg.jpeg"/>" width="60" height="60">
        </div>
        <div class="second">MyDiary App</div>
    </div>

    <div class="success-container">
        <div class="success-card">
            <div class="success-icon">&#10004;</div>
            <h1 class="success-title">Account Created!</h1>
            <p class="success-message">Your registration was successful. You can now log in and start writing your daily entries.</p>
            
            <a href="./login" class="btn-login">Proceed to Login</a>
        </div>
    </div>

</body>
</html>