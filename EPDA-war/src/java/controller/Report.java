/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.customers;
import model.customersFacade;
import model.reportType;
import model.reportTypeFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "Report", urlPatterns = {"/Report"})
public class Report extends HttpServlet {

    @EJB
    private customersFacade customersFacade;

    @EJB
    private reportTypeFacade reportTypeFacade;
//    @PersistenceContext
//    private EntityManager entityManager;
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
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        // Extract data from request
        String cusName = request.getParameter("cusName");
        String diagnosis = request.getParameter("diagnosis");
        String prognosis = request.getParameter("prognosis");
        String treatment = request.getParameter("treatment");
        
        // Validate the input data 
        if (cusName.isEmpty() || diagnosis.isEmpty() || prognosis.isEmpty() || treatment.isEmpty()) {
            // Input data not valid, set an error message in the session
             request.setAttribute("errorMessage", "All fields are required.");
            // Redirect back to the form
            response.sendRedirect("Report");
            return;
        }
        

        
        try {
            
            
             Long cusId = Long.parseLong(cusName);

             // Fetch the customer based on the provided ID
            customers customerEntity = customersFacade.find(cusId);
            if (customerEntity == null) {
                throw new Exception("Customer not found");
            }

            
            // Perform the creation of the report
            reportType newReport = new reportType();
            // Assuming setters are available in the Report class for these fields
            newReport.setCustomer(customerEntity);
            newReport.setDiagnosis(diagnosis);
            newReport.setPrognosis(prognosis);
            newReport.setTreatment(treatment);
            newReport.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            newReport.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            reportTypeFacade.create(newReport);


            // Report creation successful, set a success message
            session.setAttribute("successMessage", "Report submitted successfully.");
            response.sendRedirect("ReportView"); // Redirect as appropriate
        } catch (Exception e) {
            // Handle any exceptions during the report creation process
            e.printStackTrace(); // Log the exception for debugging purposes
            // Set an error message in the session
            session.setAttribute("errorMessage", "Failed to submit report due to an error.");
            response.sendRedirect("ReportView"); // Redirect as appropriate
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
            
            request.setAttribute("customers", customersList); 
            
            request.getRequestDispatcher("/report.jsp").forward(request, response); // Forwards to the JSP page
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
