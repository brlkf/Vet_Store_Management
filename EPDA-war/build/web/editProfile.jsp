<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body class="bg-gray-100 flex justify-center items-center w-full h-screen">
        <div class="w-full max-w-md mx-auto bg-white rounded-lg shadow-lg overflow-hidden">
            <div class="py-8 px-6 mt-4 text-left bg-white">
                <h3 class="text-2xl font-bold text-center">Edit Profile</h3>
                <% if (request.getAttribute("errorMessage") != null) {%>
                <div class="bg-red-100 text-red-500 mt-4 p-3 rounded border border-red-500">
                    <%= request.getAttribute("errorMessage")%>
                </div>
                <% }%>
                <form action="EditProfile" method="POST" class="mt-6">
                    <div class="mt-4">
                        <label for="name" class="block">Name</label>
                        <input type="text" id="name" name="name" value="${sessionScope.user.name}"
                               readonly
                               class="mt-1 p-2 w-full border rounded-md focus:border-indigo-500 focus:ring focus:ring-indigo-400 focus:ring-opacity-40">
                    </div>
                    <div class="mt-4">
                        <label for="email" class="block">Email</label>
                        <input type="email" id="email" name="email" value="${sessionScope.user.email}"
                               readonly
                               class="mt-1 p-2 w-full border rounded-md focus:border-indigo-500 focus:ring focus:ring-indigo-400 focus:ring-opacity-40">
                    </div>
                    <div class="mt-4">
                        <label for="password" class="block">Password </label>
                        <input type="password" id="password" name="password"
                               class="mt-1 p-2 w-full border rounded-md focus:border-indigo-500 focus:ring focus:ring-indigo-400 focus:ring-opacity-40">
                    </div>
                    <div class="flex justify-between items-center mt-6">
                        <button class="px-6 py-2 leading-5 text-white transition-colors duration-200 transform bg-indigo-700 rounded hover:bg-indigo-600 focus:outline-none" type="submit">Update Profile</button>

                        <a href="index.jsp" class="px-6 py-2 leading-5 text-indigo-700 transition-colors duration-200 transform rounded hover:text-indigo-600 focus:outline-none">Go Back</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
