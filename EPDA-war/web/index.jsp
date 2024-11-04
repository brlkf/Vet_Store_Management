<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

    </head>
    <body class="bg-gray-100">

        <!-- Navigation Bar -->
        <nav class="bg-indigo-600 p-4 text-white">
            <div class="container mx-auto flex justify-between items-center">
                <p class="text-lg font-bold"> Dashboard</p>
                <div>
                    <a href="Logout" class="hover:bg-indigo-700 p-2 rounded">Logout</a>
                </div>
            </div>
        </nav>

        <!-- Main Content -->
        <main class="container mx-auto p-8">
            <div class="text-center mb-10">
                <h1 class="font-bold text-3xl mb-2">Welcome, ${sessionScope.user.getName()}</h1>
                <p class="text-xl text-gray-600">What would you like to do today?</p>
            </div>

            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                <!-- Conditional Rendering Based on Role -->
                <c:choose>
                    <c:when test="${sessionScope.user.roleId eq '1'}">
                        <!-- Vet Actions -->
                        <div class="bg-white rounded-lg shadow-lg p-6">
                            <h2 class="font-bold text-xl mb-4">Vet Dashboard</h2>
                            <ul class="space-y-3">
                                <li><a href="editProfile.jsp" class="text-indigo-600 hover:underline">Edit Personal Profile</a></li>
                                <li><a href="ReportView" class="text-indigo-600 hover:underline">Diagnosis and Prognosis</a></li>
                                <li><a href="Appointment" class="text-indigo-600 hover:underline">View Appointments</a></li>
                                <li><a href="WorkingRota" class="text-indigo-600 hover:underline">Working Rota</a></li>

                            </ul>
                        </div>
                    </c:when>
                    <c:when test="${sessionScope.user.roleId eq '2'}">
                        <!-- Receptionist Actions -->
                        <div class="bg-white rounded-lg shadow-lg p-6">
                            <h2 class="font-bold text-xl mb-4">Receptionist Dashboard</h2>
                            <ul class="space-y-3">
                                <li><a href="editProfile.jsp" class="text-indigo-600 hover:underline">Edit Personal Profile</a></li>
                                <li><a href="Appointment" class="text-indigo-600 hover:underline">Manage Appointments</a></li>
                                <li><a href="Customer" class="text-indigo-600 hover:underline">Customers</a></li>
                                <li><a href="Pet" class="text-indigo-600 hover:underline">Pets</a></li>
                            </ul>
                        </div>
                    </c:when>
                    <c:when test="${sessionScope.user.roleId eq '3'}">
                        <!-- Managing Staff Actions -->
                        <div class="bg-white rounded-lg shadow-lg p-6">
                            <h2 class="font-bold text-xl mb-4">Managing Staff Dashboard</h2>
                            <ul class="space-y-3">
                                <li><a href="ManagingStaff" class="text-indigo-600 hover:underline">Manage Staff</a></li>
                                <li><a href="WorkingRota" class="text-indigo-600 hover:underline">Working Rota</a></li>
                                <li><a href="ReportInfo" class="text-indigo-600 hover:underline">View Reports</a></li>
                            </ul>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </main>



    </body>
</html>
