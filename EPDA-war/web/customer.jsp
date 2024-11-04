<%--
    Document   : customer
    Created on : Mar 13, 2024, 12:22:03 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customers</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.x/dist/tailwind.min.css" rel="stylesheet">

    </head>
    <body class="bg-gray-100 font-sans leading-normal tracking-normal">
        <div class="container mx-auto mt-8">
            <h1 class="text-xl font-bold text-gray-800 mb-4">Customers List</h1>
            <div class="bg-white shadow-md rounded">
                <table class="min-w-full leading-normal">
                    <thead>
                        <tr class="bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
                            <th class="py-3 px-6 text-left">Name</th>
                            <th class="py-3 px-6 text-left">Phone</th>
                            <th class="py-3 px-6 text-center">Pet Name</th>
                            <th class="py-3 px-6 text-center">Action</th>
                        </tr>
                    </thead>
                    <tbody class="text-gray-600 text-sm font-light">
                        <c:forEach items="${customers}" var="customer">
                            <tr class="border-b border-gray-200 hover:bg-gray-100">
                                <td class="py-3 px-6">${customer.name}</td>
                                <td class="py-3 px-6">${customer.phone}</td>
                                <td class="py-3 px-6 text-center">${customer.pet.petName}</td>
                                <td class="py-3 px-6 text-center">
                                    <form action="CancelCus" method="post" class="inline-block">
                                        <input type="hidden" name="customerId" value="${customer.id}" />
                                        <button type="submit" class="text-white bg-red-500 hover:bg-red-600 rounded-full px-4 py-2 focus:outline-none" onclick="return confirm('Are you sure you want to delete this customer?');">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="mt-4">
                <a href="AddCus" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Add Customer</a>
                <a href="index.jsp" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded ml-4">Go Back</a>
            </div>
        </div>
    </body>
</html>

</html>
