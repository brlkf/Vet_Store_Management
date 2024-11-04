/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.roles;
import model.usersFacade;
import model.rolesFacade;
import model.users;

/**
 *
 * @author User
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    @EJB
    private rolesFacade rolesFacade;

    @EJB
    private usersFacade usersFacade;

    
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

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            int roleId = Integer.parseInt(request.getParameter("role"));
            String status = "Pending";

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                throw new IllegalArgumentException("Please fill all fields.");

            }
       
                      // Validate customer name: only alphabets and spaces
            if (!username.matches("^[A-Za-z ]+$")) {
                request.setAttribute("errorMessage", "Username can only contain alphabets and spaces.");
//                request.getRequestDispatcher("AddCus").forward(request, response);
                doGet(request, response);

                return;
            }
            
        
            
            users newUser = new users();
            newUser.setName(username);
            newUser.setPassword(password); // TODO: Hash the password
            newUser.setEmail(email);
            newUser.setRoleId(roleId);
            newUser.setStatus(status);
            newUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            newUser.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            
            usersFacade.create(newUser);
            
            
            if (newUser.getRoleId() == 1) {
                request.getSession().setAttribute("user", newUser);
                response.sendRedirect("ExpertiseVet");
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // For debugging purposes, log the exception
            request.setAttribute("errorMessage", "An error occurred during the registration process.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        
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
            List<roles> rolesList = rolesFacade.findAll();
            request.setAttribute("roles", rolesList);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve roles.");
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
