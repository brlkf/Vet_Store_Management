<%--
    Document   : pet
    Created on : Mar 13, 2024, 1:19:30 AM
    Author     : User
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pets</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.x/dist/tailwind.min.css" rel="stylesheet">

    </head>
    <body class="bg-gray-100 font-sans leading-normal tracking-normal">
        <div class="container mx-auto mt-8">
            <h1 class="text-2xl font-bold text-gray-800 mb-4">Pets List</h1>
            <div class="flex justify-end mb-4">
                <a href="AddPet" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Add Pet</a>
            </div>
            <div class="bg-white shadow-md rounded-lg overflow-hidden">
                <table class="min-w-full leading-normal">
                    <thead>
                        <tr class="bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
                            <th class="py-3 px-6 text-left">Name</th>
                            <th class="py-3 px-6 text-left">Type</th>
                            <th class="py-3 px-6 text-center">Action</th>
                        </tr>
                    </thead>
                    <tbody class="text-gray-700">
                        <c:forEach items="${pets}" var="pet">
                            <tr class="hover:bg-gray-100 border-b border-gray-200">
                                <td class="py-3 px-6">${pet.petName}</td>
                                <td class="py-3 px-6">${pet.type}</td>
                                <td class="py-3 px-6 text-center">
                                    <form action="CancelPet" method="post" class="inline-block">
                                        <input type="hidden" name="petId" value="${pet.id}" />
                                        <button type="submit" class="text-red-600 hover:text-red-900">Cancel</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="index.jsp" class="mt-4 bg-gray-200 hover:bg-gray-300 text-gray-800 font-bold py-2 px-4 rounded">Go Back</a>
        </div>
    </body>

</html>
