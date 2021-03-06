/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.servlet;

import com.bank.domain.Customer;
import com.bank.domain.DataConnection;
import com.bank.domain.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "declineRequest", urlPatterns = {"/declineRequest"})
public class declineRequestServlet extends HttpServlet {
      
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       UserDao userdao=DataConnection.getUserDao();
        HttpSession session=request.getSession(false);
        Customer cstm=(Customer) session.getAttribute("customer");
        String status=userdao.deleteUser(cstm.getAccount());
        if(status=="SUCCESS"){
        session.removeAttribute("customer");
        request.getRequestDispatcher("/inActiveUsers.jsp").forward(request, response);//RequestDispatcher is used to send the control to the invoked page.
        }
    }    
        
}
