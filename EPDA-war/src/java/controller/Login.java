/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import static java.lang.System.out;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.users;
import model.usersFacade;


/**
 *
 * @author User
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
        String username = request.getParameter("username").toLowerCase(); // Convert input to lower case
        String password = request.getParameter("password");

        // Assuming 'findByName' will fetch the user entity where the username matches (case-insensitively)
            users user = usersFacade.findByAttribute("name", username);

        // If user is found, compare passwords (you may also consider using a more secure method for password comparison)
        if (user == null || !user.getPassword().equals(password)) {
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Check the user's approval status
        if (!"Approved".equalsIgnoreCase(user.getStatus())) {
            request.setAttribute("errorMessage", "Your account is not approved yet.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Success: User has been validated and approved
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("roleId", user.getRoleId());

        response.sendRedirect("index.jsp");
    } catch (Exception e) {
        out.println(e.getMessage()); // Log the exception
        request.setAttribute("errorMessage", "An error occurred during the login process.");
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
