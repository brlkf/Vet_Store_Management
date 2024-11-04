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
import model.customers;
import model.customersFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "CancelCus", urlPatterns = {"/CancelCus"})
public class CancelCus extends HttpServlet {

    @EJB
    private customersFacade customersFacade;

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
             String contextPath = request.getContextPath();

        try {
            Long customerId = Long.parseLong(request.getParameter("customerId"));
            customers customer = customersFacade.find(customerId);

            if (customer != null) {
                customer.setDeletedAt(new Timestamp(System.currentTimeMillis()));
                customersFacade.edit(customer); // Ensure you have an update method in your facade to merge changes
                
                // Redirect to the same page (self-redirect) to reflect the update
                response.sendRedirect(contextPath + "/Customer");
            } else {
                request.setAttribute("errorMessage", "Customer not found.");
                request.getRequestDispatcher("/Customer").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error deleting customer.");
            request.getRequestDispatcher("/Customer").forward(request, response);
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