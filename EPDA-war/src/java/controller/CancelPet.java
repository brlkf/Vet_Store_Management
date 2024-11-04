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
import model.pets;
import model.petsFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "CancelPet", urlPatterns = {"/CancelPet"})
public class CancelPet extends HttpServlet {

    @EJB
    private petsFacade petsFacade;

    String url = "jdbc:mysql://localhost:1527/sample";

            
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
            Long petId = Long.parseLong(request.getParameter("petId"));
            pets pets = petsFacade.find(petId);

            if (pets != null) {
                pets.setDeletedAt(new Timestamp(System.currentTimeMillis()));
                petsFacade.edit(pets); // Ensure you have an update method in your facade to merge changes
                
                // Redirect to the same page (self-redirect) to reflect the update
                response.sendRedirect(contextPath + "/Pet");
            } else {
                request.setAttribute("errorMessage", "Pet not found.");
                request.getRequestDispatcher("/Pet").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error deleting pet.");
            request.getRequestDispatcher("/Pet").forward(request, response);
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
