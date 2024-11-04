<%--
    Document   : report
    Created on : Mar 12, 2024, 12:22:39 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report Form</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.x/dist/tailwind.min.css" rel="stylesheet">

    </head>
    <body class="bg-gray-100 font-sans min-h-screen flex flex-col items-center pt-6">
        <div class="w-full max-w-2xl bg-white rounded-lg shadow-md p-6">
            <h1 class="text-2xl font-semibold text-gray-800 mb-4">Diagnosis and Prognosis Form</h1>
            <form action="Report" method="post" class="space-y-4">
                <div>
                    <label for="cusName" class="block text-gray-700 text-sm font-bold mb-2">Customer name:</label>
                    <select id="cusName" name="cusName" required class="block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                        <option value="">Select a customer</option>
                        <c:forEach items="${customers}" var="customer">
                            <option value="${customer.id}">${customer.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label for="diagnosis" class="block text-gray-700 text-sm font-bold mb-2">Diagnosis:</label>
                    <textarea id="diagnosis" name="diagnosis" rows="4" required class="resize-none w-full p-2 border rounded focus:outline-none focus:shadow-outline"></textarea>
                </div>
                <div>
                    <label for="prognosis" class="block text-gray-700 text-sm font-bold mb-2">Prognosis:</label>
                    <textarea id="prognosis" name="prognosis" rows="4" required class="resize-none w-full p-2 border rounded focus:outline-none focus:shadow-outline"></textarea>
                </div>
                <div>
                    <label for="treatment" class="block text-gray-700 text-sm font-bold mb-2">Recommended Treatment:</label>
                    <textarea id="treatment" name="treatment" rows="4" required class="resize-none w-full p-2 border rounded focus:outline-none focus:shadow-outline"></textarea>
                </div>
                <div class="flex justify-between items-center">
                    <input type="submit" value="Submit Diagnosis" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded cursor-pointer">
                    <a href="Report" type="button" class="bg-gray-200 hover:bg-gray-300 text-gray-800 font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Go Back</a>
                </div>
            </form>
        </div>
    </body>

</html>
