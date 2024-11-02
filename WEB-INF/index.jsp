<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>REST Example</title>
</head>
<body>
    <h1>REST API Test Page</h1>
    
    <form action="http://localhost:8080/DS-CA1/api/student/create" method="POST">
        <input type="submit" value="Create Students">
    </form>

    <form action="http://localhost:8080/CA1_DS/api/student/" method="GET">
        <input type="submit" value="Print Message">
    </form>

    <form action="http://localhost:8080/CA1_DS/api/student/" method="GET">  
        <input type="submit" value="View All Employees">
    </form>
    
    <form action="http://localhost:8080/CA1_DS/api/student/" method="GET">  
        <input type="submit" value="View JSON All Employees">
    </form>
    
    <form action="http://localhost:8080/CA1_DS/api/student/1" method="GET">  
        <input type="submit" value="View JSON Employee 1">
    </form>
    
    <!-- New forms for loans and deposits -->
    <h2>Loan Operations</h2>
    <form action="http://localhost:8080/resteasyExtended/restful-services/loans" method="POST">
        <input type="text" name="loanId" placeholder="Loan ID" required>
        <input type="text" name="amount" placeholder="Amount" required>
        <input type="submit" value="Create Loan">
    </form>

    <form action="http://localhost:8080/resteasyExtended/restful-services/loans/1" method="DELETE">
        <input type="submit" value="Delete Loan with ID 1">
    </form>

    <h2>Deposit Operations</h2>
    <form action="http://localhost:8080/resteasyExtended/restful-services/deposits" method="POST">
        <input type="text" name="depositId" placeholder="Deposit ID" required>
        <input type="text" name="amount" placeholder="Amount" required>
        <input type="text" name="studentId" placeholder="Student ID" required>
        <input type="submit" value="Create Deposit">
    </form>

    <form action="http://localhost:8080/resteasyExtended/restful-services/deposits/1" method="DELETE">
        <input type="submit" value="Delete Deposit with ID 1">
    </form>

    <form action="http://localhost:8080/resteasyExtended/restful-services/deposits/student/1" method="GET">
        <input type="submit" value="View Deposits for Student ID 1">
    </form>

</body>
</html>
