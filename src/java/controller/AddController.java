/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ProductDAO;
import entity.Product;
import entity.Type;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cauch
 */
public class AddController extends BaseRequiredAuthorization {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO pd = new ProductDAO();

        ArrayList<Type> listType = pd.listType();

        request.setAttribute("type", listType);
        request.getRequestDispatcher("addproduct.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("fruitname");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String linkimage = "";
        String[] part = image.split("\\\\");

        linkimage += part[part.length - 1];

        String fulllink = "images/" + linkimage;

        int rate = 0;
        double price = Double.parseDouble(request.getParameter("price"));

        int Type = Integer.parseInt(request.getParameter("type"));

        Product p = new Product();
        p.setName(name);
        p.setDescription(description);
        p.setImage(fulllink);
        p.setPrice(price);
        p.setRate(rate);
        p.setStatus(1);
        p.setMenuID(Type);

        ProductDAO pd = new ProductDAO();
        pd.add(p);

        response.sendRedirect("List");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
