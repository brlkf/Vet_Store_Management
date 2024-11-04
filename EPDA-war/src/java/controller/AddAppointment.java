/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.appointments;
import model.appointmentsFacade;
import model.customers;
import model.customersFacade;
import model.petsFacade;
import model.users;
import model.usersFacade;
import model.vets;
import model.vetsFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "AddAppointment", urlPatterns = {"/AddAppointment"})
public class AddAppointment extends HttpServlet {

    @EJB
    private usersFacade usersFacade;

    @EJB
    private customersFacade customersFacade;

    @EJB
    private vetsFacade vetsFacade;

    @EJB
    private petsFacade petsFacade;

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
          String subject = request.getParameter("subject");
            Long createdBy = Long.valueOf(request.getParameter("createdBy"));
        Long customerId = Long.valueOf(request.getParameter("customerName"));
        Long vetId = Long.valueOf(request.getParameter("vetName")); // Handle vet entity similarly if needed
        String description = request.getParameter("description");
        String appointmentDateStr = request.getParameter("appointmentDate");
        String status = "Scheduled";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"); // Adjust to match input format
        Timestamp appointmentDate;

        try {
            appointmentDate = new Timestamp(dateFormat.parse(appointmentDateStr).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid date format.");
            request.getRequestDispatcher("/createApp.jsp").forward(request, response);
            return;
        }

        try {
            // Fetch the customer based on the provided ID
            customers customerEntity = customersFacade.find(customerId);
            if (customerEntity == null) {
                throw new Exception("Customer not found");
            }
            
            vets vetEntity = vetsFacade.find(vetId);
            if (vetEntity == null) {
                throw new Exception("Vet not found");
            }
            
            users userEntity = usersFacade.find(createdBy);
            if (userEntity == null) {
                throw new Exception("User not found");
            }

            // Create a new appointment object and set its properties
            appointments newAppointment = new appointments();
            newAppointment.setSubject(subject);
            newAppointment.setCreated_by(userEntity);
            newAppointment.setCustomers(customerEntity); // Set the fetched customer entity
            newAppointment.setVet(vetEntity); // Make sure to handle vet relationship similarly if necessary
            newAppointment.setDescription(description);
            newAppointment.setDate(appointmentDate);
            newAppointment.setStatus(status);
            newAppointment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            newAppointment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            // Persist the new appointment using the facade
            appointmentsFacade.create(newAppointment);
            
            request.getSession().setAttribute("successMessage", "Appointment added successfully!");
            response.sendRedirect("Appointment"); 
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to create the appointment due to an error.");
            request.getRequestDispatcher("/createApp.jsp").forward(request, response);
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
        try {
            List<customers> customersList = customersFacade.findAllActiveCus(); 
            List<vets> vetsList = vetsFacade.findAll(); 
            
            request.setAttribute("customers", customersList); 
            request.setAttribute("vets", vetsList); 
            
            request.getRequestDispatcher("/createApp.jsp").forward(request, response); // Forwards to the JSP page
        } catch (Exception e) {
            e.printStackTrace(); 
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
