/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Timestamp;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.users;
import model.usersFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "EditStaff", urlPatterns = {"/EditStaff"})
public class EditStaff extends HttpServlet {

    @EJB
    private usersFacade usersFacade;

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
        
            Long id = Long.valueOf(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            // Additional fields as needed

            // Find the existing staff member by ID
        users staff = usersFacade.find(id);
        
        if (staff.getName().equals(name)) {
                       request.setAttribute("errorMessage", "Username already exists.");         
                   }
        
        if (staff != null) {
            // Update the staff member's details
            staff.setName(name);
            staff.setEmail(email);

            // Only update the password if a new one is provided
            if (password != null && !password.isEmpty()) {
                // Here you should hash the password before setting it
                staff.setPassword(password);
            }
            
            staff.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            
            try {
                // Update the staff member in the database
                usersFacade.edit(staff); // Make sure the update method merges the changes
                response.sendRedirect("ManagingStaff"); // Redirect to the list view
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the error appropriately
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the staff member.");
            }
        } else {
            // Staff member not found
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Staff member not found.");
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
         Long userId = Long.parseLong(request.getParameter("userId"));
       try {
            users staffMember = usersFacade.find(userId);
            if (staffMember != null) {
                request.setAttribute("staff", staffMember);
                request.getRequestDispatcher("/editStaff.jsp").forward(request, response);
            } else {
                response.sendRedirect("ManagingStaff"); // No staff member found, redirect to the list
            }
        } catch (NumberFormatException e) {
            // Handle invalid or missing staffId parameter
            response.sendRedirect("ManagingStaff");
        }
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
