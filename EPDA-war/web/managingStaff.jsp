<%--
    Document   : managingStaff
    Created on : Mar 13, 2024, 5:48:47 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Managing Staff</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.x/dist/tailwind.min.css" rel="stylesheet">

        <script>
            function filterStaffs() {
                var input = document.getElementById("searchInput");
                var filter = input.value.toUpperCase();
                var table = document.getElementById("staff");
                var tr = table.querySelectorAll("tbody tr");

                tr.forEach(row => {
                    const td = Array.from(row.getElementsByTagName("td"));
                    const matches = td.some(column => column.textContent.toUpperCase().indexOf(filter) > -1);
                    row.style.display = matches ? "" : "none";
                });
            }
        </script>

    </head>
    <body class="bg-gray-100 min-h-screen flex flex-col items-center justify-center">
        <div class="container mx-auto p-6 bg-white shadow-lg rounded-lg">
            <h1 class="text-2xl font-semibold text-center text-gray-800 mb-4">Managing Staff</h1>

            <div class="flex justify-between items-center mb-4">
                <a href="AddStaff" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Create New Staff Member</a>
                <form action="ManagingStaff" method="get" class="flex">
                    <input type="text" name="search"  id="searchInput" onkeyup="filterStaffs()" placeholder="Search staff" class="rounded-l-md p-2 border-t mr-0 border-b border-l text-gray-800 border-gray-200 bg-white" />
                </form>
            </div>

            <div class="overflow-x-auto">
                <table class="table-auto w-full" id = "staff">
                    <thead>
                        <tr class="bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
                            <th class="py-3 px-6 text-left">Name</th>
                            <th class="py-3 px-6 text-left">Email</th>
                            <th class="py-3 px-6 text-center">Password</th>
                            <th class="py-3 px-6 text-center">Role ID</th>
                            <th class="py-3 px-6 text-center">Status</th>
                            <th class="py-3 px-6 text-center">Actions</th>
                        </tr>
                    </thead>
                    <tbody class="text-gray-600 text-sm font-light">
                        <c:forEach items="${user}" var="users">
                            <tr class="border-b border-gray-200 hover:bg-gray-100">
                                <td class="py-3 px-6 text-left whitespace-nowrap">${users.name}</td>
                                <td class="py-3 px-6 text-left">${users.email}</td>
                                <td class="py-3 px-6 text-center">${users.password}</td>
                                <td class="py-3 px-6 text-center">${users.roleId}</td>
                                <td class="py-3 px-6 text-center">
                                    <c:if test="${users.status ne 'Approved'}">
                                        <span class="bg-red-200 text-red-600 py-1 px-3 rounded-full text-xs">Pending</span>
                                    </c:if>
                                    <c:if test="${users.status eq 'Approved'}">
                                        <span class="bg-green-200 text-green-600 py-1 px-3 rounded-full text-xs">Approved</span>
                                    </c:if>
                                </td>
                                <td class="py-3 px-6 text-center">
                                    <div class="flex item-center justify-center">
                                        <form action="EditStaff" method="GET" class="mr-2">
                                            <input type="hidden" name="userId" value="${users.id}" />
                                            <button class="bg-yellow-500 text-white px-3 py-1 rounded hover:bg-yellow-600">Edit</button>
                                        </form>
                                        <form action="CancelStaff" method="post" class="mr-2">
                                            <input type="hidden" name="userId" value="${users.id}" />
                                            <button class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600" onclick="return confirm('Are you sure you want to delete this staff member?');">Delete</button>
                                        </form>
                                        <c:if test="${users.status ne 'Approved'}">
                                            <form action="Approve" method="post">
                                                <input type="hidden" name="userId" value="${users.id}" />
                                                <button class="bg-green-500 text-white px-3 py-1 rounded hover:bg-green-600">Approve</button>
                                            </form>
                                        </c:if>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <p class="text-gray-600 mt-4">Role ID: 1 - Vet, 2 - Receptionists, 3 - Managing Staff</p>
            <a href="index.jsp" class="mt-4 bg-gray-200 text-gray-800 py-2 px-4 rounded hover:bg-gray-300 inline-block text-center">Go Back</a>
        </div>
    </body>

</html>
