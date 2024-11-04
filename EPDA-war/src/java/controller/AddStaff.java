/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.roles;
import model.rolesFacade;
import model.users;
import model.usersFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "AddStaff", urlPatterns = {"/AddStaff"})
public class AddStaff extends HttpServlet {

    @EJB
    private usersFacade usersFacade;

    @EJB
    private rolesFacade rolesFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // Ensure you handle password security properly!
        int roleId = Integer.parseInt(request.getParameter("role"));
        String status = "Pending";

        Map<String, String> errors = new HashMap<>();

        // Validate name
        if (name == null || name.trim().isEmpty()) {
            errors.put("name", "Name cannot be empty.");
        }

        // Validate email
        if (email == null ) {
            errors.put("email", "Invalid email address.");
        }

        // Validate password
        if (password == null ) {
            errors.put("password", "Password must be at least 6 characters long.");
        }

     
                  // Validate customer name: only alphabets and spaces
            if (!name.matches("^[A-Za-z ]+$")) {
                request.setAttribute("errorMessage", "Username can only contain alphabets and spaces.");
//                request.getRequestDispatcher("AddCus").forward(request, response);
                doGet(request, response);

                return;
            }
        
        // Create a new staff member object
        users newStaff = new users();
        newStaff.setName(name);
        newStaff.setEmail(email);
        // Handle password hashing before setting
        newStaff.setPassword(password);
        // Set other details such as role, etc., as necessary
         newStaff.setRoleId(roleId);
        newStaff.setStatus(status);
        newStaff.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        newStaff.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        try {
            // Persist the new staff member
            usersFacade.create(newStaff);
            // Redirect to a confirmation page or back to the staff list
            response.sendRedirect("ManagingStaff"); // Adjust the redirection URL as needed
        } catch (Exception e) {
            e.printStackTrace(); // Proper error handling should be implemented
            // Set an error message and perhaps redirect to a failure page
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to add new staff member.");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<roles> rolesList = rolesFacade.findAll();
        request.setAttribute("roles", rolesList);
        request.getRequestDispatcher("addStaff.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
