<%--
    Document   : createPet.jsp
    Created on : Mar 13, 2024, 1:54:24 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Pet</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.x/dist/tailwind.min.css" rel="stylesheet">

    </head>
    <body class="bg-gray-100 flex justify-center items-center min-h-screen">
        <div class="w-full max-w-xs bg-white rounded-lg shadow-md p-6 m-4">
            <h2 class="text-xl font-semibold mb-4 text-gray-800">Create New Pet</h2>
            <% if (request.getAttribute("errorMessage") != null) {%>
            <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
                <%= request.getAttribute("errorMessage")%>
            </div>
            <% }%>
            <form action="AddPet" method="post" class="space-y-4">
                <div>
                    <label for="name" class="block text-gray-700 text-sm font-bold mb-2">Pet Name:</label>
                    <input type="text" id="name" name="name" required class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                </div>
                <div>
                    <label for="type" class="block text-gray-700 text-sm font-bold mb-2">Pet Type:</label>
                    <input type="text" id="type" name="type" required class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                </div>
                <!-- Include additional fields as necessary -->
                <div class="flex items-center justify-between">
                    <input type="submit" value="Create Pet" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                    <a href="Pet" type="button" class="bg-gray-300 hover:bg-gray-400 text-gray-800 py-2 px-4 rounded inline-flex items-center">
                        Go Back
                    </a>
                </div>
            </form>
        </div>
    </body>

</html>
