<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Appointments</title>
        <!-- Tailwind CSS CDN for styling -->
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.x/dist/tailwind.min.css" rel="stylesheet">
        <script>
            function filterAppointments() {
                var input = document.getElementById("searchInput");
                var filter = input.value.toUpperCase();
                var table = document.getElementById("appointmentTable");
                var tr = table.querySelectorAll("tbody tr");

                tr.forEach(row => {
                    const td = Array.from(row.getElementsByTagName("td"));
                    const matches = td.some(column => column.textContent.toUpperCase().indexOf(filter) > -1);
                    row.style.display = matches ? "" : "none";
                });
            }
        </script>
    </head>
    <body class="bg-gray-100 font-sans leading-normal tracking-normal">
        <div class="container mx-auto px-4 pt-6">
            <h1 class="text-3xl mb-4 font-bold text-gray-800">Manage Appointments</h1>

            <div class="mb-4">
                <input type="text" id="searchInput" onkeyup="filterAppointments()" placeholder="Search for appointments..." class="p-2 border rounded shadow">
            </div>

            <div class="overflow-x-auto bg-white rounded shadow">
                <table class="min-w-full leading-normal" id="appointmentTable">
                    <thead>
                        <tr class="bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
                            <th class="py-3 px-6 text-left">Subject</th>
                            <th class="py-3 px-6 text-left">Created By</th>
                            <th class="py-3 px-6 text-left">Customer Name</th>
                            <th class="py-3 px-6 text-center">Pet Name</th>
                            <th class="py-3 px-6 text-center">Vet Name</th>
                            <th class="py-3 px-6 text-center">Vet Expertise</th>
                            <th class="py-3 px-6 text-center">Description</th>
                            <th class="py-3 px-6 text-center">Date</th>
                            <th class="py-3 px-6 text-center">Status</th>
                            <th class="py-3 px-6 text-center">Actions</th>
                        </tr>
                    </thead>
                    <tbody class="text-gray-600 text-sm font-light">
                        <c:forEach var="appointment" items="${appointments}">
                            <tr class="border-b border-gray-200 hover:bg-gray-100">
                                <td class="py-3 px-6">${appointment.subject}</td>
                                <td class="py-3 px-6">${appointment.created_by.name}</td>
                                <td class="py-3 px-6">${appointment.customers.name}</td>
                                <td class="py-3 px-6 text-center">${appointment.customers.pet.petName}</td>
                                <td class="py-3 px-6 text-center">${appointment.vet.userID.name}</td>
                                <td class="py-3 px-6 text-center">${appointment.vet.expName}</td>
                                <td class="py-3 px-6">${appointment.description}</td>
                                <td class="py-3 px-6 text-center">${appointment.date}</td>
                                <td class="py-3 px-6 text-center">${appointment.status}</td>
                                <td class="py-3 px-6 text-center flex justify-center">
                                    <form action="CancelApp" method="post" class="mr-2">
                                        <input type="hidden" name="appointmentId" value="${appointment.id}" />
                                        <button type="submit" class="bg-red-500 hover:bg-red-700 text-white py-1 px-3 rounded">Cancel</button>
                                    </form>
                                    <c:if test="${sessionScope.roleId == 1 and appointment.status ne 'Complete'}">
                                        <form action="Completed" method="post">
                                            <input type="hidden" name="appointmentId" value="${appointment.id}" />
                                            <button type="submit" class="bg-green-500 hover:bg-green-700 text-white py-1 px-3 rounded">Complete</button>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="mt-4">
                <c:if test="${sessionScope.roleId != 1}">
                    <a href="AddAppointment" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">New Appointment</a>
                </c:if>
                <a href="index.jsp" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded ml-2">Go Back</a>
            </div>
        </div>
    </body>
</html>
