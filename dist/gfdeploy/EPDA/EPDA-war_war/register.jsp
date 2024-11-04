<%--
    Document   : register
    Created on : Mar 10, 2024, 6:25:51 PM
    Author     : User
--%>


<%@page import="java.util.List"%>
<%@page import="model.roles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

    </head>
    <body class="bg-gray-100 flex justify-center items-center h-screen">
        <div class="w-full max-w-xs">
            <h1 class="mb-4 text-2xl text-center font-bold text-gray-700">Register</h1>
            <% if (request.getAttribute("errorMessage") != null) {%>
            <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
                <%= request.getAttribute("errorMessage")%>
            </div>
            <% }%>
            <form action="Register" method="POST" class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
                <div class="mb-4">
                    <label for="username" class="block text-gray-700 text-sm font-bold mb-2">Username:</label>
                    <input type="text" id="username" name="username" required class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                </div>
                <div class="mb-4">
                    <label for="password" class="block text-gray-700 text-sm font-bold mb-2">Password:</label>
                    <input type="password" id="password" name="password" required class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                </div>
                <div class="mb-4">
                    <label for="email" class="block text-gray-700 text-sm font-bold mb-2">Email:</label>
                    <input type="email" id="email" name="email" required class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                </div>
                <div class="mb-6">
                    <label for="role" class="block text-gray-700 text-sm font-bold mb-2">Role:</label>
                    <select id="role" name="role" class="block appearance-none w-full bg-white border border-gray-400 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline">
                        <option value="">Select Role</option>
                        <c:forEach var="role" items="${roles}">
                            <option value="${role.id}">${role.roleName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="flex items-center justify-between">
                    <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Register</button>
                    <a href="login.jsp" class="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800">Login</a>
                </div>
            </form>
        </div>
    </body>

</html>
