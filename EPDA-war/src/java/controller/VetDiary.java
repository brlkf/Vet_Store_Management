/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.appointments;
import model.appointmentsFacade;
import model.users;
import model.vets;
import model.vetsFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "VetDiary", urlPatterns = {"/VetDiary"})
public class VetDiary extends HttpServlet {

    @EJB
    private vetsFacade vetsFacade;

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
          HttpSession session = request.getSession();
        users user = (users) session.getAttribute("user");

        if (user != null && user.getRoleId() == 1) { // Assuming the user role check is applicable
            // Assuming 'vetId' is stored directly on the User object and corresponds to the 'vetId' attribute in Appointment entities
            Long vetId = user.getId(); // Adjust according to how vetId is stored on the User object
            
            // Find the Vet entity based on userId
            vets vet = vetsFacade.findByAttribute("userId", vetId);
                    
            if (vet != null) {
                // Now, find appointments for this Vet
                // Assuming findByAttribute returns List<Appointment> when looking for appointments by vetId
                List<appointments> appointments = appointmentsFacade.findAllByAttribute("vetId", vet.getId());

                request.setAttribute("appointments", appointments);
                request.getRequestDispatcher("/vetDiary.jsp").forward(request, response);
            } else {
                // Handle case where Vet is not found
                request.setAttribute("errorMessage", "Vet profile not found.");
                request.getRequestDispatcher("/vetDiary.jsp").forward(request, response);
            }
        } else {
            // Redirect to login if not logged in or not a vet
            response.sendRedirect("login.jsp");
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
