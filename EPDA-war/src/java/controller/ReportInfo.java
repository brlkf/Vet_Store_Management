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
import model.appointments;
import model.appointmentsFacade;
import model.customers;
import model.customersFacade;
import model.reportType;
import model.reportTypeFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "ReportInfo", urlPatterns = {"/ReportInfo"})
public class ReportInfo extends HttpServlet {

    @EJB
    private customersFacade customersFacade;

    @EJB
    private reportTypeFacade reportTypeFacade;

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
          // Fetch active (non-deleted) records and count them
           List<customers> activeCustomers = customersFacade.findAllActiveCus();
            List<appointments> activeAppointments = appointmentsFacade.findAllActiveApp();
            List<reportType> activeReportTypes = reportTypeFacade.findAllActiveReportType();
            List<appointments> scheduleAppointments = appointmentsFacade.findAllScheduledApp();
            List<appointments> completedAppointments = appointmentsFacade.findAllCompletedApp();

            
            
            
            long appointmentCount = activeAppointments != null ? activeAppointments.size() : 0;
            long reportCount = activeReportTypes != null ? activeReportTypes.size() : 0;
            long customerCount = activeCustomers != null ? activeCustomers.size() : 0;
            long scheduledApp = scheduleAppointments != null ? scheduleAppointments.size() : 0;
            long completedApp = completedAppointments != null ? completedAppointments.size() : 0;



            request.setAttribute("appointmentCount", appointmentCount);
            request.setAttribute("reportCount", reportCount);
            request.setAttribute("customerCount", customerCount);
            request.setAttribute("scheduledAppCount", scheduledApp);
            request.setAttribute("completedAppCount", completedApp);

            

            request.getRequestDispatcher("/reportInfo.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating report.");
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
