<%--
    Document   : workingRota
    Created on : Mar 14, 2024, 12:35:57 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Weekly Rota</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.x/dist/tailwind.min.css" rel="stylesheet">

    </head>
    <body class="bg-gray-100 font-sans leading-normal tracking-normal">
        <div class="container mx-auto mt-8">
            <h1 class="text-2xl font-bold text-gray-800 mb-4">Vet's Weekly Rota</h1>
            <!-- Conditionally display the button for users with a roleId not equal to 1 -->
            <c:if test="${sessionScope.roleId ne '1'}">
                <div class="flex justify-end mb-4">
                    <a href="AddRota" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Add New Rota</a>
                </div>
            </c:if>
            <div class="bg-white shadow-md rounded-lg overflow-hidden">
                <table class="min-w-full leading-normal">
                    <thead>
                        <tr class="bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
                            <th class="py-3 px-6 text-left">Vet User ID</th>
                            <th class="py-3 px-6 text-left">Start Date</th>
                            <th class="py-3 px-6 text-left">End Date</th>
                        </tr>
                    </thead>
                    <tbody class="text-gray-700">
                        <c:forEach items="${rotas}" var="rota">
                            <tr class="border-b border-gray-200 hover:bg-gray-100">
                                <td class="py-3 px-6">${rota.userID}</td>
                                <td class="py-3 px-6">${rota.startDate}</td>
                                <td class="py-3 px-6">${rota.endDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="index.jsp" class="mt-4 bg-gray-200 hover:bg-gray-300 text-gray-800 font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Go Back</a>
        </div>
    </body>

</html>
