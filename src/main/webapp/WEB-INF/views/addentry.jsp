<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add New Entry</title>
    <link rel="stylesheet" href="<c:url value="/resources/styles/style.css"/>">
</head>
<body>
    <div class="header">
        <div class="second">Add a New Diary Entry</div>
    </div>
    <br/><br/><hr/><br/>

    <div class="bodypart2">
        <form action="saveEntry" method="POST">
            <label>Date:</label> 
            <input type="date" name="entrydate" class="formcontrol"><br/><br/>
            
            <label>Description:</label><br/>
            <textarea name="description" rows="5" cols="40" class="formcontrol"></textarea><br/><br/>
            
            <button type="submit">SAVE ENTRY</button>
        </form>
        <br/>
        <a href="./home">Cancel</a>
    </div>
</body>
</html>