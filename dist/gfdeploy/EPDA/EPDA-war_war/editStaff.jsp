<%-- Document: editStaff.jsp Created on: Mar 13, 2024, 7:04:40 PM Author: User --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Managing Staff</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body class="bg-gray-100">
        <div class="container mx-auto px-4">
            <div class="max-w-md mx-auto my-10 bg-white p-5 rounded-md shadow-sm">
                <div class="text-center">
                    <h1 class="my-3 text-3xl font-semibold text-gray-700">Edit Staff Member</h1>
                </div>
                <c:if test="${not empty staff}">
                    <form action="EditStaff" method="post" class="space-y-5 mt-5">
                        <input type="hidden" name="id" value="${staff.id}" />
                        <div>
                            <label for="name" class="text-sm font-bold text-gray-600 block">Name:</label>
                            <input type="text" id="name" name="name" value="${staff.name}" class="w-full p-2 border rounded" readonly />
                        </div>
                        <div>
                            <label for="email" class="text-sm font-bold text-gray-600 block">Email:</label>
                            <input type="email" id="email" name="email" value="${staff.email}" class="w-full p-2 border rounded" readonly />
                        </div>
                        <div>
                            <label for="password" class="text-sm font-bold text-gray-600 block">Password:</label>
                            <input type="password" id="password" name="password" class="w-full p-2 border rounded" />
                            <small class="text-gray-600">Leave blank to keep the current password.</small>
                        </div>
                        <div class="flex justify-end mt-6">
                            <input type="submit" value="Update Staff" class="cursor-pointer py-2 px-4 bg-blue-600 text-white rounded" />
                        </div>
                    </form>
                </c:if>
                <c:if test="${empty staff}">
                    <p class="text-red-500">Staff member not found.</p>
                </c:if>
                <div class="mt-6">
                    <a href="ManagingStaff" class="text-blue-500 hover:text-blue-600 underline">Back to Staff List</a>
                </div>
            </div>
        </div>
    </body>
</html>
