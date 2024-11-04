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
@WebServlet(name = "Approve", urlPatterns = {"/Approve"})
public class Approve extends HttpServlet {

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
        String userIdString = request.getParameter("userId");
        if (userIdString == null || userIdString.isEmpty()) {
            // Handle the case where no user ID is provided.
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is missing.");
            return;
        }
        
        try {
            Long userId = Long.parseLong(userIdString);
            users userToApprove = usersFacade.find(userId);
            
            if (userToApprove != null && "Pending".equals(userToApprove.getStatus())) {
                userToApprove.setStatus("Approved");
                userToApprove.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                usersFacade.edit(userToApprove);
                // Redirect back to the managing staff page or display a success message
                response.sendRedirect("ManagingStaff");
            } else {
                // Handle the case where the user is not found or is not pending.
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User is not found or not in Pending status.");
            }
        } catch (NumberFormatException e) {
            // Handle the case where the user ID is not a valid long value.
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format.");
        } catch (Exception e) {
            // Handle other exceptions.
            e.printStackTrace(); // Consider logging the exception with a logger
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while approving the user.");
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
         Long staffId = Long.parseLong(request.getParameter("staffId"));
        users staffMember = usersFacade.find(staffId);
        
        if (staffMember != null) {
            staffMember.setStatus("Approved");
            usersFacade.edit(staffMember); // Save the changes to the database
            // Redirect back to the managing staff list
            response.sendRedirect("ManagingStaff");
        } else {
            // If no staff member is found with the given ID, handle the error accordingly
            request.setAttribute("errorMessage", "Staff member not found.");
            request.getRequestDispatcher("/ManagingStaff").forward(request, response);
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
