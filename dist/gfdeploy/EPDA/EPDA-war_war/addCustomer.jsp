<%--
    Document   : customer
    Created on : Mar 13, 2024, 12:20:19 AM
    Author     : User
--%>
<%@page import="java.util.List"%>
<%@page import="model.pets"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Customer</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.x/dist/tailwind.min.css" rel="stylesheet">

    </head>
    <body class="bg-gray-100 min-h-screen flex items-center justify-center">
        <div class="w-full max-w-md mx-auto bg-white rounded-lg shadow-md p-6">
            <h1 class="text-xl font-semibold text-gray-800 mb-4">New Customer</h1>

            <!-- Check for an error message -->
            <c:if test="${not empty errorMessage}">
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
                    <strong class="font-bold">Error!</strong>
                    <span class="block sm:inline">${errorMessage}</span>
                </div>
            </c:if>
            <form action="AddCus" method="post" class="space-y-4">
                <div>
                    <label for="name" class="block text-gray-700 text-sm font-bold mb-2">Name:</label>
                    <input type="text" id="name" name="name" required class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                </div>
                <div>
                    <label for="phone" class="block text-gray-700 text-sm font-bold mb-2">Phone:</label>
                    <input type="tel" id="phone" name="phone" required class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                </div>
                <div>
                    <label for="petId" class="block text-gray-700 text-sm font-bold mb-2">Pet:</label>
                    <select id="petId" name="petId" required class="block appearance-none w-full bg-white border border-gray-400 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:shadow-outline">
                        <option value="">Select a Pet</option>
                        <c:forEach items="${pet}" var="pet">
                            <option value="${pet.id}">${pet.petName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="flex items-center justify-between">
                    <input type="submit" value="Create Customer" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                    <a href="Customer" class="bg-gray-200 hover:bg-gray-300 text-gray-800 font-bold py-2 px-4 rounded">Go Back</a>
                </div>
            </form>
        </div>
    </body>

</html>
