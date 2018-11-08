/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.midterm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import sit.midterm.controller.HistoryJpaController;
import sit.midterm.model.Account;
import static sit.midterm.model.Account_.accountid;
import sit.midterm.model.History;

/**
 *
 * @author Chonticha Sae-jiw
 */
public class WithdrawServlet extends HttpServlet {
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
        HttpSession session = request.getSession(false);
        session.removeAttribute("complete");
        if(session.getAttribute("account") == null){
            response.sendRedirect("Login");
            return;
        }
        
        Account account = (Account)session.getAttribute("account");
        String Withdraw = request.getParameter("Withdraw");
        
        if(Withdraw != null){
            int accountId = account.getAccountid();
            int numWithdraw = Integer.parseInt(Withdraw);
            
            if(numWithdraw > 0){
                AccountJpaController accountJpa = new AccountJpaController(utx, emf);
                Account acc = accountJpa.findAccount(accountId);
                
                if(acc != null){
                    int balanceDB = acc.getBalance();
                    if(balanceDB >= numWithdraw){
                        balanceDB = balanceDB - numWithdraw;
                        acc.setBalance(balanceDB);
                        session.setAttribute("account", acc);
                      try{
                            accountJpa.edit(acc);
                            HistoryJpaController historyJpa = new HistoryJpaController(utx, emf);
                            History history = new History(historyJpa.getHistoryCount());
                            history.setAccountid(acc);
                            history.setAmount(numWithdraw);
                            history.setBalance(balanceDB);
                            history.setMethod("withdraw");
                            history.setTime(new Date());
                            historyJpa.create(history);
                        }catch(Exception ex){
                            System.out.println(ex);
                        }
                        session.setAttribute("complete", "Withdraw Complete");
                        response.sendRedirect("MyAccountView.jsp");
                        return;
                    }      
                }
                session.setAttribute("complete","You can't withdraw");
                    response.sendRedirect("MyAccountView.jsp");
                    return;
            }
            session.setAttribute("complete","You can't withdraw");
                    response.sendRedirect("MyAccountView.jsp");
                    return;
        }
        getServletContext().getRequestDispatcher("/WithdrawView.jsp").forward(request, response);
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
