<%--
    Document   : reportView
    Created on : Mar 15, 2024, 1:37:38 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report List</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.x/dist/tailwind.min.css" rel="stylesheet">


    </head>
    <body class="flex items-center justify-center p-10">
        <div class="container mx-auto mt-8">
            <h1 class="text-2xl font-bold text-gray-800 mb-6">Report List</h1>
            <div class="bg-white shadow-md rounded-lg overflow-hidden">
                <table class="min-w-full leading-normal">
                    <thead>
                        <tr class="bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
                            <th class="py-3 px-6 text-left">Name</th>
                            <th class="py-3 px-6 text-left">Diagnosis</th>
                            <th class="py-3 px-6 text-center">Prognosis</th>
                            <th class="py-3 px-6 text-center">Treatment</th>
                            <th class="py-3 px-6 text-center">Action</th>
                        </tr>
                    </thead>
                    <tbody class="text-gray-700">
                        <c:forEach items="${reportTypes}" var="reportType">
                            <tr class="border-b border-gray-200 hover:bg-gray-100">
                                <td class="py-3 px-6">${reportType.customer.name}</td>
                                <td class="py-3 px-6">${reportType.diagnosis}</td>
                                <td class="py-3 px-6 text-center">${reportType.prognosis}</td>
                                <td class="py-3 px-6 text-center">${reportType.treatment}</td>
                                <td class="py-3 px-6 text-center">
                                    <form action="CancelReport" method="post" class="inline-flex items-center justify-center">
                                        <input type="hidden" name="reportId" value="${reportType.id}" />
                                        <button type="submit" class="text-white bg-red-500 hover:bg-red-700 focus:outline-none focus:shadow-outline rounded-lg px-4 py-2" onclick="return confirm('Are you sure you want to delete this Report?');">
                                            Delete
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="flex justify-between items-center mt-6">
                <a href="Report" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Add Report</a>
                <a href="index.jsp" class="bg-gray-200 hover:bg-gray-300 text-gray-800 font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Go Back</a>
            </div>
        </div>
    </body>
</html>
