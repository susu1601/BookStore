/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Category;
import entity.Product;
import entity.implement.CategoryDAO;
import entity.implement.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author PC
 */
public class HomeController extends HttpServlet {

    ProductDAO productDAO = new ProductDAO();
    CategoryDAO categoryDAO = new CategoryDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> listProduct = findProductDoGet(request);

        //get list categoryDAO
        List<Category> listCategory = categoryDAO.findAll();
        //set listProduct, listCategory to session
        HttpSession session = request.getSession();
        session.setAttribute("listProduct", listProduct);
        session.setAttribute("listCategory", listCategory);
        //request.getRequestDispatcher("index.html").forward(request, response);
        request.getRequestDispatcher("/view/homepage/home.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private List<Product> findProductDoGet(HttpServletRequest request) {
        //get ve search
        String actionSearch = request.getParameter("search") == null ? "default" : request.getParameter("search");
        //get list productDAO
        List<Product> listProduct;
        switch (actionSearch) {
            case "category":
                String categoryId = request.getParameter("categoryId");
                listProduct = productDAO.findByCategory(categoryId);
                break;
            case "searchByName":
                String keyword = request.getParameter("keyword");
                listProduct = productDAO.findByName(keyword);
                break;
            default:
                listProduct = productDAO.findAll();
        }
        return listProduct;
    }

}
