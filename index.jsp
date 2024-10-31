<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>REST Example</title>
</head>
<body>
    <form action= "http://localhost:8080/DS-CA1/CA1_DS/restful-services/student/createStudent" method="POST">
        
        <input type="submit" value="Create Student">
    </form>
	
	  <form action= "http://localhost:8080/DS-CA1/restful-services/student/json/all" method="GET">
        
        <input type="submit" value="Get Students">
    </form>

	<form action= "http://localhost:8080/DS-CA1/restful-services/student/update/{StudentId}" method="PUT">  
        <input type="submit" value="Update students">
    </form>
	
	<form action= "http://localhost:8080/DS-CA1/restful-services/student/delete/{studentId}" method="DELETE">  
        <input type="submit" value="Delete Students">
    </form>
	
	<form action= "http://localhost:8080/DS-CA1/restful-services/student/health" method="GET">  
        <input type="submit" value="CHECK">
    </form>
</body>
</html>