package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.appointments;
import model.appointmentsFacade;
import model.customersFacade;
import model.vets;
import model.vetsFacade;
import model.customers;

/**
 *
 * @author User
 */
@WebServlet(urlPatterns = {"/EditApp"})
public class EditApp extends HttpServlet {
    @EJB
    private appointmentsFacade appointmentsFacade;
    @EJB
    private customersFacade customersFacade;
    @EJB
    private vetsFacade vetsFacade;
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
             response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Long appointmentId = Long.parseLong(request.getParameter("appointmentId"));
        String subject = request.getParameter("subject");
        Long customerId = Long.parseLong(request.getParameter("customerId"));
        Long vetId = Long.parseLong(request.getParameter("vetId"));
        String description = request.getParameter("description");
        String dateString = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            appointments appointment = appointmentsFacade.find(appointmentId);
            customers customer = customersFacade.find(customerId);
            vets vet = vetsFacade.find(vetId);

            appointment.setSubject(subject);
            appointment.setCustomers(customer);
            appointment.setVet(vet);
            appointment.setDescription(description);
            appointment.setStatus(status);
            appointment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));


            // Assuming you have a way to convert dateString to Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Tinmestamp date = sdf.parse(dateString);
//            appointment.setDate(date);

            appointmentsFacade.edit(appointment); // Assuming there's an update method

            session.setAttribute("message", "Appointment updated successfully!");
            response.sendRedirect("appointment.jsp"); // Or wherever you want to redirect after update
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            session.setAttribute("error", "Failed to update the appointment.");
            response.sendRedirect("editApp.jsp"); // Redirect back to the form if there's an error
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
