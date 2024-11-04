<%--
    Document   : addRota
    Created on : Mar 14, 2024, 2:23:52 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Rota</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.x/dist/tailwind.min.css" rel="stylesheet">

        <script>
            function setEndDate() {
                var startDate = document.getElementById("startDate").value;
                var endDateElement = document.getElementById("endDate");

                if (startDate) {
                    var startDateObj = new Date(startDate);
                    // Add 6 days to the start date to get a total of 7 days including the start date
                    startDateObj.setDate(startDateObj.getDate() + 6);

                    var dd = startDateObj.getDate();
                    var mm = startDateObj.getMonth() + 1; //January is 0!
                    var yyyy = startDateObj.getFullYear();

                    if (dd < 10) {
                        dd = '0' + dd;
                    }
                    if (mm < 10) {
                        mm = '0' + mm;
                    }

                    endDateElement.value = yyyy + '-' + mm + '-' + dd;
                }
            }
        </script>
    </head>
    <body class="bg-gray-100 font-sans leading-normal tracking-normal">
        <div class="container mx-auto mt-10">
            <div class="max-w-md mx-auto bg-white rounded-lg shadow-md overflow-hidden p-6">
                <h1 class="text-2xl font-semibold mb-4 text-gray-800">Add New Rota</h1>
                <form action="AddRota" method="post" class="space-y-4">
                    <div>
                        <label for="vetUserId" class="block text-sm font-bold mb-2">Vet User:</label>
                        <select id="vetUserId" name="vetUserId" required class="block appearance-none w-full bg-gray-200 text-gray-700 border rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                            <option value="">Select a Vet</option>
                            <c:forEach items="${vets}" var="vet">
                                <option value="${vet.userID}">${vet.userID.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <label for="startDate" class="block text-sm font-bold mb-2">Start Date:</label>
                        <input type="date" id="startDate" name="startDate" onchange="setEndDate()" required class="block appearance-none w-full bg-gray-200 text-gray-700 border rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>
                    <div>
                        <label for="endDate" class="block text-sm font-bold mb-2">End Date:</label>
                        <input type="date" id="endDate" name="endDate" required readonly class="block appearance-none w-full bg-gray-200 text-gray-700 border rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                    </div>
                    <div class="flex justify-between items-center">
                        <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Add Rota</button>
                        <a href="WorkingRota" class="mt-4 bg-gray-200 hover:bg-gray-300 text-gray-800 font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Go Back</a>
                    </div>
                </form>
            </div>
        </div>
    </body>

</html>
