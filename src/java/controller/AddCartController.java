/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ProductDAO;
import entity.Account;
import entity.Item;
import entity.Order;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cauch
 */
public class AddCartController extends HttpServlet {

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
        int quantity = 0;

        if (request.getParameter("quantity") != null) {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } else {
            quantity = 1;
        }
        int id;
        HttpSession sessionAcc = request.getSession();
        Account account = new Account();
        if (sessionAcc.getAttribute("account") != null) {
            account = (Account) sessionAcc.getAttribute("account");
        }

        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
            ProductDAO pd = new ProductDAO();
            Product product = pd.getProduct(id);

            if (product != null) {
                if (request.getParameter("quantity") != null) {
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                }
                HttpSession session = request.getSession();
                if (session.getAttribute("order") == null) {
                    Order order = new Order();
                    ArrayList<Item> listp = new ArrayList<>();
                    Item it = new Item();
                    it.setID(id);
                    it.setQuantity(quantity);
                    it.setProduct(product);
                    it.setPrice(product.getPrice());

                    listp.add(it);
                    order.setItems(listp);
                    session.setAttribute("order", order);
                    Double total = 0.0;
                    for (Item item : listp) {
                        total = total + item.getPrice() * item.getQuantity();
                    }

                    if (account != null || !account.equals("")) {
                        order.setCustomer(account);
                    }

                    request.setAttribute("total", total);

                } else {
                    Order order = (Order) session.getAttribute("order");
                    ArrayList<Item> it = order.getItems();
                    boolean check = false;
                    for (Item item : it) {
                        if (item.getProduct().getID() == product.getID()) {
                            item.setQuantity(item.getQuantity() + quantity);
                            check = true;
                        }
                    }
                    if (check == false) {
                        Item item = new Item();
                        item.setID(id);
                        item.setQuantity(quantity);
                        item.setProduct(product);
                        item.setPrice(product.getPrice());
                        it.add(item);
                    }
                    Double total = 0.0;
                    for (Item item : it) {
                        total = total + item.getPrice() * item.getQuantity();
                    }

                    session.setAttribute("order", order);

                    request.setAttribute("total", total);

                }

            }
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else {

            request.getRequestDispatcher("cart.jsp").forward(request, response);
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
