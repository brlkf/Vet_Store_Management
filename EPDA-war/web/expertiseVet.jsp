<%-- Document: expertiseVet.jsp Created on: Mar 12, 2024, 1:31:16 AM Author: User --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select Your Expertise</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.x/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body class="bg-gray-100">
        <div class="container mx-auto px-4">
            <div class="max-w-md mx-auto my-10 bg-white p-5 rounded-md shadow-sm">
                <div class="text-center">
                    <h1 class="my-3 text-3xl font-semibold text-gray-700">Select Your Expertise</h1>
                </div>
                <% if (request.getAttribute("errorMessage") != null) {%>
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
                    <strong class="font-bold">Oops!</strong>
                    <span class="block sm:inline"><%= request.getAttribute("errorMessage")%></span>
                </div>
                <% }%>
                <form action="ExpertiseVet" method="POST" class="mt-10">
                    <div class="mb-6">
                        <label for="expertise" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Your Expertise</label>
                        <input type="text" id="expertise" name="expertise" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" required>
                    </div>
                    <div class="flex justify-end pt-2">
                        <input type="submit" value="Submit" class="px-4 py-2 rounded text-white inline-block shadow-lg bg-blue-500 hover:bg-blue-600 focus:bg-blue-700">
                    </div>
                </form>
                <div class="flex justify-between pt-2">
                    <button onclick="history.back()" class="px-4 py-2 rounded text-blue-600 hover:text-blue-700 focus:underline">Go Back</button>
                </div>
            </div>
        </div>
    </body>
</html>
