<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>REST Example</title>
    <script type="text/javascript">
        function submitForm() {
            const formData = {
                name: document.getElementById('name').value,
                phone_number: document.getElementById('phone_number').value,
                address: document.getElementById('address').value,
                programme_code: document.getElementById('programme_code').value
            };

            fetch('http://localhost:8080/DS-CA1/api/student/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            })
            .then(response => {
                console.log("Status Code:", response.status);
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => console.log(data))
            .catch(error => console.error('Error:', error));
        }
    </script>

</head>
<body>
    <h1>REST API Test Page</h1>
    
    <form id="studentForm">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" autocomplete="name" required><br>
    
        <label for="phone_number">Phone Number:</label>
        <input type="text" id="phone_number" name="phone_number" autocomplete="tel" required><br>
    
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" autocomplete="street-address"><br>
    
        <label for="programme_code">Programme Code:</label>
        <input type="text" id="programme_code" name="programme_code" autocomplete="off"><br>
    
        <button type="button" onclick="submitForm()">Create Student</button>
    </form>

    <form action="http://localhost:8080/DS-CA1/api/student/delete/{studentId}" method="GET">
        <input type="submit" value="Delete Student">
    </form>

    <form action="http://localhost:8080/DS-CA1/api/student/" method="GET">  
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
    <form action="http://localhost:8080/DS-CA1/api/loans" method="POST">
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
