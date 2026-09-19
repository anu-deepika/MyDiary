<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to MyDiary App</title>
<link rel="stylesheet" href="<c:url value="/resources/styles/style.css"/>">
<style>
    /* Dynamic Landing Page Styles */
    body {
        margin: 0;
        padding: 0;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(135deg, #e0eafc 0%, #cfdef3 100%);
        height: 100vh;
        display: flex;
        flex-direction: column;
    }

    .landing-container {
        display: flex;
        align-items: center;
        justify-content: center;
        flex-grow: 1;
        text-align: center;
        padding: 20px;
    }

    .hero-card {
        background: white;
        padding: 50px 60px;
        border-radius: 15px;
        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
        animation: slideUp 0.8s ease-out forwards;
        max-width: 500px;
        opacity: 0;
        transform: translateY(30px);
    }

    .hero-img {
        width: 120px;
        height: 120px;
        border-radius: 50%;
        object-fit: cover;
        margin-bottom: 20px;
        box-shadow: 0 5px 15px rgba(70, 130, 180, 0.3);
        animation: pulse 2s infinite;
    }

    .hero-title {
        color: SteelBlue;
        font-size: 2.2em;
        margin: 0 0 15px 0;
        letter-spacing: 1px;
    }

    .hero-subtitle {
        color: #555;
        font-size: 1.1em;
        line-height: 1.5;
        margin-bottom: 35px;
    }

    .action-buttons {
        display: flex;
        gap: 20px;
        justify-content: center;
    }

    .btn {
        padding: 12px 30px;
        border-radius: 25px;
        text-decoration: none;
        font-weight: bold;
        font-size: 1.1em;
        transition: all 0.3s ease;
        border: 2px solid SteelBlue;
    }

    .btn-login {
        background: SteelBlue;
        color: white;
    }

    .btn-login:hover {
        background: white;
        color: SteelBlue;
        transform: translateY(-2px);
        box-shadow: 0 5px 15px rgba(70, 130, 180, 0.4);
    }

    .btn-register {
        background: white;
        color: SteelBlue;
    }

    .btn-register:hover {
        background: SteelBlue;
        color: white;
        transform: translateY(-2px);
        box-shadow: 0 5px 15px rgba(70, 130, 180, 0.4);
    }

    /* Animations */
    @keyframes slideUp {
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }

    @keyframes pulse {
        0% { transform: scale(1); }
        50% { transform: scale(1.05); }
        100% { transform: scale(1); }
    }
</style>
</head>
<body>

    <!-- Optional: Keep your standard header if you want it on the landing page -->
    <div class="header" style="box-shadow: 0 2px 10px rgba(0,0,0,0.1);">
        <div class="first">
            <img src="<c:url value="/resources/images/diary.jpg.jpeg"/>" width="60" height="60">
        </div>
        <div class="second">MyDiary App</div>
    </div>

    <div class="landing-container">
        <div class="hero-card">
            <img src="<c:url value="/resources/images/diary.jpg.jpeg"/>" alt="Diary Logo" class="hero-img">
            <h1 class="hero-title">MyDiary App</h1>
            <p class="hero-subtitle">Capture your thoughts, track your daily memories, and secure your personal reflections all in one beautiful space.</p>
            
            <div class="action-buttons">
                <a href="./login" class="btn btn-login">Login</a>
                <a href="./register" class="btn btn-register">Register</a>
            </div>
        </div>
    </div>

</body>
</html>