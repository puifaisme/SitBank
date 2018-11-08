/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.midterm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import sit.midterm.controller.AccountJpaController;
import sit.midterm.model.Account;

/**
 *
 * @author Chonticha Sae-jiw
 */
public class LoginServlet extends HttpServlet {
    @PersistenceUnit(unitName = "Practice-MidtermPU")
    EntityManagerFactory emf;
    @Resource
    UserTransaction utx;
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
        HttpSession session = request.getSession(true);
        String AccountId = request.getParameter("AccountId");
        String Password = request.getParameter("Password");
        
        if(session.getAttribute("account") != null){
            response.sendRedirect("MyAccount");
            return;
        }
        
        if(AccountId != null && Password != null) {
            int numAccountId = Integer.parseInt(AccountId);
            int numPin = Integer.parseInt(Password);
            
            AccountJpaController accountJpa = new AccountJpaController(utx, emf);
            Account account = accountJpa.findAccount(numAccountId);
            
           if (account != null) {
                int pinDB = account.getPin();
                if (numPin == pinDB) {
                    request.getSession().setAttribute("account", account);
                    response.sendRedirect("MyAccount");
                    return;
                }
                request.setAttribute("Message", "Invalid Accountid or Pin");
            }
        }
        getServletContext().getRequestDispatcher("/LoginView.jsp").forward(request, response);
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
