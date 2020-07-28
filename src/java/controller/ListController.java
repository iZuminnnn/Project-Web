/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ProductDAO;
import entity.Account;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cauch
 */
public class ListController extends HttpServlet {

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

        ProductDAO pd = new ProductDAO();

        String page = request.getParameter("page");
        if (page == null || page.length() == 0) {
            page = "1";
        }
        int pageindex = Integer.parseInt(page);
        int pagesize = 8;

        String type = request.getParameter("type");
        String search = request.getParameter("search");

        HttpSession session = request.getSession();
        Account acc = new Account();
        if (session.getAttribute("account") != null) {
            acc = (Account) session.getAttribute("account");
        }

        String accountid = acc.getAccountid();

        if (type != null) {
            ArrayList<Product> list = pd.listType(Integer.parseInt(type));
            request.setAttribute("Product", list);
            request.setAttribute("accoount", accountid);
            int count = pd.countType(Integer.parseInt(type));
            int totalpage = count % pagesize > 0 ? count / pagesize + 1 : count / pagesize;

            request.setAttribute("totalpage", totalpage);
            request.setAttribute("pageindex", pageindex);
            request.getRequestDispatcher("listproduct.jsp").forward(request, response);
        } else if (search != null) {
            ArrayList<Product> list = pd.search(search, pageindex, pagesize);
            request.setAttribute("Product", list);
            request.setAttribute("accoount", accountid);
            int count = pd.countSearch(search);
            int totalpage = count % pagesize > 0 ? count / pagesize + 1 : count / pagesize;
            request.setAttribute("totalpage", totalpage);
            request.setAttribute("pageindex", pageindex);
            request.getRequestDispatcher("listproduct.jsp").forward(request, response);
        } else {
            ArrayList<Product> list = pd.list(pageindex, pagesize);
            request.setAttribute("Product", list);
            request.setAttribute("accoount", accountid);
            int count = pd.count();
            int totalpage = count % pagesize > 0 ? count / pagesize + 1 : count / pagesize;

            request.setAttribute("totalpage", totalpage);
            request.setAttribute("pageindex", pageindex);
            request.getRequestDispatcher("listproduct.jsp").forward(request, response);
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
