<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>REST Example</title>
    <script type="text/javascript">
        function submitForm() {
                const name = document.getElementById('name').value || "placeholderName";
                const phoneNumber = document.getElementById('phone_number').value || "0000000000";
                const address = document.getElementById('address').value || "placeholderAddress";
                const programmeCode = document.getElementById('programme_code').value || "placeholderProgrammeCode";

                if (!name || !phoneNumber || !address || !programmeCode) {
                    alert("Please fill in all fields.");
                    return; // Stop the function if validation fails
                }

                const formDataXML = `
                    <student>
                        <name>${name}</name>
                        <phone_number>${phoneNumber}</phone_number>
                        <address>${address}</address>
                        <programme_code>${programmeCode}</programme_code>
                    </student>
                `;

                fetch('http://localhost:8080/DS-CA1/api/student/createstudent', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/xml'
                    },
                    body: formDataXML
                })
                .then(response => {
                    console.log("Status Code:", response.status);
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
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

    <form action="http://localhost:8080/DS-CA1/api/student/delete/{id}" method="DELETE">
        <input type="submit" value="Delete Student">
    </form>

    <form action="http://localhost:8080/DS-CA1/api/student/allstudents/" method="GET">  
        <input type="submit" value="View All Students">
    </form>
    
    <form action="http://localhost:8080/DS-CA1/api/student/check/{id}" method="PUT">  
        <input type="submit" value="View a student">
    </form>
    
    <!-- <form action="http://localhost:8080/CA1_DS/api/student/1" method="GET">  
        <input type="submit" value="View JSON Employee 1">
    </form> -->
    
    <!-- New forms for loans and deposits -->
    <h2>Loan Operations</h2>
    <form action="http://localhost:8080/DS-CA1/api/loans/createLoan/" method="POST">
        <input type="text" name="amount" placeholder="Amount" required>
        <input type="text" name="interestRate" placeholder="Interest Rate" required>
        <input type="text" name="id" placeholder="Student ID" required>
        <input type="submit" value="Create Loan">
    </form>

    <form action="http://localhost:8080/DS-CA1/api/loans/delete/2" method="DELETE">
        <input type="submit" value="Delete a loan with Id">
    </form>

    <h2>Deposit Operations</h2>
    <form action="http://localhost:8080/DS-CA1/api/deposits/depositLoan" method="POST">
        <input type="text" name="depositDate" placeholder="Date" required>
        <input type="text" name="amount" placeholder="Amount" required>
        <input type="text" name="loan_id" placeholder="Loan ID" required>
        <input type="text" name="id" placeholder="Student ID" required>
        <input type="submit" value="Create Deposit">
    </form>

    <form action="http://localhost:8080/DS-CA1/api/deposits/alldeposits" method="GET">
        <input type="submit" value="View all deposits">
    </form>

    <form action="http://localhost:8080/DS-CA1/api/deposits/delete/{deposit_id}" method="DELETE">
        <input type="submit" value="Delete a deposit">
    </form>

</body>
</html>
