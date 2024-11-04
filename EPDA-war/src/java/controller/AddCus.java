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
import model.pets;
import model.petsFacade;
import model.rolesFacade;
import model.users;

/**
 *
 * @author User
 */
@WebServlet(name = "AddCus", urlPatterns = {"/AddCus"})
public class AddCus extends HttpServlet {

    @EJB
    private petsFacade petsFacade;

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
        response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession(false);
        users user = (users) session.getAttribute("user");
       
                String name = request.getParameter("name");
               String phone = request.getParameter("phone");
               Long petId = Long.parseLong(request.getParameter("petId"));

                // Basic input validation
           if (name.isEmpty() || phone.isEmpty()) {
               request.setAttribute("errorMessage", "Please fill all fields.");
//               request.getRequestDispatcher("AddCus").forward(request, response);
                doGet(request, response);

               return;
           }
           
           // Validate customer name: only alphabets and spaces
            if (!name.matches("^[A-Za-z ]+$")) {
                request.setAttribute("errorMessage", "Customer name can only contain alphabets and spaces.");
//                request.getRequestDispatcher("AddCus").forward(request, response);
                doGet(request, response);

                return;
            }

            // Validate phone number: only digits
            if (!phone.matches("^\\d+$")) {
                request.setAttribute("errorMessage", "Phone number can only contain digits.");
//                request.getRequestDispatcher("AddCus").forward(request, response);
                doGet(request, response);
                return;
            }
           
           

            try {
                
                
                
                pets petEntity = petsFacade.find(petId);
                if (petEntity == null) {
                    throw new Exception("Pet not found");
                }
            
            // Step 4: Create user
            customers newCus = new customers();
            newCus.setName(name);
            newCus.setPhone(phone);
            newCus.setPet(petEntity);
            newCus.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            newCus.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            customersFacade.create(newCus);

            // If the operation is successful, redirect to the customers list page or a success page
            response.sendRedirect("Customer"); // Adjust the redirection URL as needed
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes

            // Optionally, set an error message and redirect back to the form or an error page
            request.setAttribute("errorMessage", "Failed to create customer. Please try again.");
            request.getRequestDispatcher("AddCus").forward(request, response);
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
          System.out.println("asjdhj");
            List<pets> petsList = petsFacade.findAll(); // Retrieves the list of pets
            request.setAttribute("pet", petsList);   // Sets the list as a request attribute
            request.getRequestDispatcher("/addCustomer.jsp").forward(request, response); // Forwards to the JSP page
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
