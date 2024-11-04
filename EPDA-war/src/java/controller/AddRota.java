/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import static java.lang.System.out;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.vets;
import model.vetsFacade;
import model.workingRota;
import model.workingRotaFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "AddRota", urlPatterns = {"/AddRota"})
public class AddRota extends HttpServlet {

    @EJB
    private vetsFacade vetsFacade;

    @EJB
    private workingRotaFacade workingRotaFacade;

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
        String vetUserId = request.getParameter("vetUserId");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Extract the numerical ID from the string representation of the entity
            Pattern pattern = Pattern.compile("\\[ id=(\\d+) \\]");
            Matcher matcher = pattern.matcher(vetUserId);
            if (!matcher.find()) {
                throw new NumberFormatException("The vetUserId does not contain a parsable long: " + vetUserId);
            }
            vetUserId = matcher.group(1);
            
            // Parse dates from strings and convert to Timestamps
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);
            Timestamp startTimestamp = new Timestamp(startDate.getTime());
            Timestamp endTimestamp = new Timestamp(endDate.getTime());

            // Create a new rota instance
            workingRota rota = new workingRota();
            rota.setUserID(Long.parseLong(vetUserId)); // Parse the vet user ID
            rota.setStartDate(startTimestamp);
            rota.setEndDate(endTimestamp);
            rota.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            rota.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            // Persist the new rota
            workingRotaFacade.create(rota);

            // Redirect to the rota list page or a confirmation page
            response.sendRedirect("WorkingRota"); // Adjust the redirection to your correct URL
        } catch (ParseException e) {
            request.setAttribute("errorMessage", "Invalid date format.");
            request.getRequestDispatcher("/addRota.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid vet user ID format.");
            request.getRequestDispatcher("/addRota.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "There was an error processing the rota.");
            request.getRequestDispatcher("/addRota.jsp").forward(request, response);
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
            List<vets> vets = vetsFacade.findAll(); // Assuming findAll() returns a list of Vet entities
            request.setAttribute("vets", vets);
            request.getRequestDispatcher("/addRota.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve vets.");
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
