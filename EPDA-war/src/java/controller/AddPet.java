/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import static java.lang.System.out;
import java.sql.Timestamp;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.pets;
import model.petsFacade;
import model.users;

/**
 *
 * @author User
 */
@WebServlet(name = "AddPet", urlPatterns = {"/AddPet"})
public class AddPet extends HttpServlet {

    @EJB
    private petsFacade petsFacade;

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
        users user = (users) session.getAttribute("user");

        String name = request.getParameter("name");
        String type = request.getParameter("type");
        
      if (name == null || name.trim().isEmpty()) {
        request.setAttribute("errorMessage", "Pet name is required.");
        doGet(request, response); // Forward to doGet to show the form with an error message
        return;
    }

        if (type == null || type.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Pet type is required.");
                doGet(request, response);
            return; // Stop the execution further
        }
        
         if (!name.matches("^[A-Za-z ]+$") || !type.matches("^[A-Za-z ]+$") ) {
                request.setAttribute("errorMessage", "Pet name can only contain alphabets and spaces.");
//                request.getRequestDispatcher("AddCus").forward(request, response);
                doGet(request, response);

                return;
            }
         
        try {
            // Create a new pet entity and set its properties
            pets pet = new pets();
            pet.setPetName(name);
            pet.settype(type);
            pet.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            pet.setUpdatedAt(new Timestamp(System.currentTimeMillis()));


            // Persist the new pet entity
            petsFacade.create(pet); // You will need to create this method in your PetFacade

            // Redirect to the pet list or a confirmation page
            response.sendRedirect("Pet");
            out.println("Add successfully!");

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes

            // Handle the error accordingly
            request.setAttribute("errorMessage", "Unable to create pet. Please try again.");
            request.getRequestDispatcher("/createPet.jsp").forward(request, response);
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
    request.getRequestDispatcher("/createPet.jsp").forward(request, response);
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
