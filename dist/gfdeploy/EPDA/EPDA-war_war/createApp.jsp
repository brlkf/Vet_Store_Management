<%--
    Document   : createApp
    Created on : Mar 12, 2024, 6:27:30 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.users"%>
<%@page import="model.customers"%>
<%@page import="model.pets"%>
<%@page import="model.vets"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Appointment</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.x/dist/tailwind.min.css" rel="stylesheet">


        <script>
            function updatePetId() {
                var customerSelect = document.getElementById("customerName");
                var petInfoField = document.getElementById("petInfo");

                // Get the selected option
                var selectedOption = customerSelect.options[customerSelect.selectedIndex];

                // Get the petId, petName, and petType from the selected option's data attributes
                var selectedPetId = selectedOption.getAttribute("data-petid");
                var selectedPetName = selectedOption.getAttribute("data-petname");
                var selectedPetType = selectedOption.getAttribute("data-pettype");

                // Concatenate the pet ID, name, and type
                var petInfo = selectedPetId && selectedPetName && selectedPetType
                        ? selectedPetId + ' - ' + selectedPetName + ' (' + selectedPetType + ')'
                        : '';

                // Update the Pet Info field with the selected pet's ID, name, and type
                petInfoField.value = petInfo; // If no petInfo is found, set to an empty string
            }
        </script>

    </head>
    <body class="bg-gray-100 min-h-screen flex justify-center items-center p-5">
        <div class="w-full max-w-xl bg-white rounded-lg shadow-md p-6">
            <h2 class="text-xl font-semibold text-gray-800 mb-4">Create New Appointment</h2>

            <form action="AddAppointment" method="post" class="space-y-4">
                <div>
                    <label for="subject" class="block font-medium text-gray-700">Subject:</label>
                    <input type="text" id="subject" name="subject" required class="mt-1 block w-full border-gray-300 rounded-md shadow-sm">
                </div>
                <div>
                    <label for="createdBy" class="block font-medium text-gray-700">Created By:</label>
                    <input type="hidden" id="createdById" name="createdBy" value="${sessionScope.user.id}" />
                    <span id="createdByName" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm bg-gray-200">${sessionScope.user.name}</span>
                </div>
                <div>
                    <label for="customerName" class="block font-medium text-gray-700">Customer Name</label>
                    <select id="customerName" name="customerName" onchange="updatePetId();" required class="block w-full mt-1 border-gray-300 rounded-md shadow-sm">
                        <option value="">Select a Customer</option>
                        <c:forEach var="customer" items="${customers}">
                            <option value="${customer.id}"
                                    data-petid="${customer.pet.id}"
                                    data-petname="${customer.pet.petName}"
                                    data-pettype="${customer.pet.type}">
                                ${customer.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label for="petInfo" class="block font-medium text-gray-700">Pet Info:</label>
                    <input type="text" id="petInfo" name="petInfo" readonly
                           class="mt-1 block w-full border-gray-300 rounded-md shadow-sm bg-gray-200" />
                </div>
                <div>
                    <label for="vetName" class="block font-medium text-gray-700">Vet ID:</label>
                    <select id="vetName" name="vetName" required class="block w-full mt-1 border-gray-300 rounded-md shadow-sm">
                        <option value="">Select a Vet</option>
                        <c:forEach var="vet" items="${vets}">
                            <option value="${vet.id}">${vet.userID.name} - ${vet.expName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label for="description" class="block font-medium text-gray-700">Description:</label>
                    <textarea id="description" name="description" required class="mt-1 block w-full border-gray-300 rounded-md shadow-sm"></textarea>
                </div>
                <div>
                    <label for="appointmentDate" class="block font-medium text-gray-700">Appointment Date:</label>
                    <input type="datetime-local" id="appointmentDate" name="appointmentDate" required class="mt-1 block w-full border-gray-300 rounded-md shadow-sm">
                </div>
                <div class="flex justify-between items-center mt-6">
                    <input type="submit" value="Create Appointment" class="cursor-pointer bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                    <a href="Appointment" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded">Go Back</a>
                </div>
            </form>
        </div>
    </body>

</html>
