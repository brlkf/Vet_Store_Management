<%--
    Document   : editApp
    Created on : Mar 12, 2024, 11:06:41 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Appointment</title>
    </head>
    <body>
        <h1>Edit Appointment</h1>
        <form action="UpdateApp" method="post">
            <input type="hidden" name="appointmentId" value="${appointment.id}" />
            <div>
                <label for="subject">Subject:</label>
                <input type="text" id="subject" name="subject" value="${appointment.subject}" required>
            </div>
            <div>
                <label for="customerId">Customer:</label>
                <label for="customerId">Customer:</label>
                <select id="customerId" name="customerId" onchange="this.form.submit()">
                    <option value="">Select a Customer</option>
                    <c:forEach items="${customers}" var="customer">
                        <option value="${customer.id}" ${customer.id == selectedCustomerId ? 'selected' : ''}>${customer.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label>Pet Name:</label>
                <span>${appointment.petName} - ${appointment.petType}</span>
            </div>
            <div>
                <label for="vetId">Vet:</label>
                <select id="vetId" name="vetId">
                    <c:forEach items="${vets}" var="vet">
                        <option value="${vet.id}" ${vet.id == appointment.vetId ? 'selected' : ''}>${vet.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="description">Description:</label>
                <textarea id="description" name="description" required>${appointment.description}</textarea>
            </div>
            <div>
                <label for="date">Date:</label>
                <input type="datetime-local" id="date" name="date" value="${appointment.date}" required>
            </div>
            <div>
                <label for="status">Status:</label>
                <select id="status" name="status">
                    <option value="Scheduled" ${appointment.status == 'Scheduled' ? 'selected' : ''}>Scheduled</option>
                    <option value="Completed" ${appointment.status == 'Completed' ? 'selected' : ''}>Completed</option>
                    <option value="Cancelled" ${appointment.status == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
                </select>
            </div>
            <!-- Include other fields as necessary -->
            <div>
                <input type="submit" value="Update Appointment">
            </div>
        </form>
        <a href="appointment.jsp">Back to Appointments Overview</a>
    </body>
</html>
