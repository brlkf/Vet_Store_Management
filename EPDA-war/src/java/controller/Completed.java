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
import model.appointments;
import model.appointmentsFacade;
import static model.appointments_.id;
import model.usersFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "Completed", urlPatterns = {"/Completed"})
public class Completed extends HttpServlet {

    @EJB
    private appointmentsFacade appointmentsFacade;

 

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
       String appointmentIdStr = request.getParameter("appointmentId");
        if (appointmentIdStr != null && !appointmentIdStr.isEmpty()) {
            try {
                Long appointmentId = Long.parseLong(appointmentIdStr);
                appointments appointment = appointmentsFacade.find(appointmentId);

                if (appointment != null) {
                    // Assume 'status' is a property of the appointments entity. Adjust as needed.
                    appointment.setStatus("Complete"); 
                    appointmentsFacade.edit(appointment);

                    // Optionally, add a success message to the session or request
                    request.getSession().setAttribute("successMessage", "Appointment marked as complete.");
                } else {
                    // Appointment not found
                    request.getSession().setAttribute("errorMessage", "Appointment not found.");
                }
            } catch (NumberFormatException e) {
                // Handle parsing error
                request.getSession().setAttribute("errorMessage", "Invalid appointment ID.");
            }
        } else {
            // Parameter missing
            request.getSession().setAttribute("errorMessage", "Missing appointment ID.");
        }

        // Redirect back to the appointments page or wherever is appropriate
        response.sendRedirect("Appointment"); // Adjust the redirect URL as needed
    
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
        processRequest(request, response);
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
