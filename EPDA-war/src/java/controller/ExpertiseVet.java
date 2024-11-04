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
import javax.servlet.http.HttpSession;
import model.users;
import model.vets;
import model.vetsFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "ExpertiseVet", urlPatterns = {"/ExpertiseVet"})
public class ExpertiseVet extends HttpServlet {

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
        HttpSession session = request.getSession();
        users user = (users) session.getAttribute("user");
        String expertise = request.getParameter("expertise");

        System.out.println("Users: " + user.getId());
        System.out.println("Expertise: " + expertise);

        if (user != null && expertise != null && !expertise.trim().isEmpty()) {
            vets vet = new vets();
            // Assuming the user ID can be directly mapped or used to create a new Vet instance
          // Regex pattern to allow only letters, commas, and spaces
        String pattern = "^[A-Za-z, ]+$";

        if (user == null || expertise == null) {
            session.setAttribute("errorMessage", "Invalid session or expertise not provided.");
                        doGet(request, response); // This will forward back to the form

            return;
        }

        if (!expertise.matches(pattern)) {
            request.setAttribute("errorMessage", "Expertise can only contain alphabets, commas, and spaces.");
            doGet(request, response); // This will forward back to the form
            return;

        }


            try {
                
                vet.setUserID(user.getId()); // If your Vet ID is auto-generated, you might not need to set it explicitly here
                vet.setExpName(expertise);
                vet.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                vet.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                vetsFacade.create(vet);

//                vetsFacade.create(vet);
                session.setAttribute("successMessage", "Expertise updated successfully.");
                response.sendRedirect("login.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("errorMessage", "Failed to update expertise.");

            }
        } else {
            session.setAttribute("errorMessage", "Invalid input.");
            response.sendRedirect("expertiseVet.jsp?err=Invalid");

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
    request.getRequestDispatcher("/expertiseVet.jsp").forward(request, response);
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
