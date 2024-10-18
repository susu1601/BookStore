/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import constant.CommonConst;
import entity.Category;
import entity.Product;
import entity.implement.CategoryDAO;
import entity.implement.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class DashboardServlet extends HttpServlet {

    ProductDAO dao = new ProductDAO();
    CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> listProduct = dao.findAll();
        List<Category> listCategory = categoryDAO.findAll();
        HttpSession session = request.getSession();
        session.setAttribute(CommonConst.SESSION_PRODUCT, listProduct);
        session.setAttribute(CommonConst.SESSION_CATEGORY, listCategory);
        
        //chuyen sang trang dashboard
        request.getRequestDispatcher("../view/admin/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
