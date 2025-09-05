package com.ecommerce.control;

import java.io.IOException;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.dao.CategoryDao;
import com.ecommerce.entity.Category;
@WebServlet(name="CategoryManagementControl",value="/category-management")
public class CategoryManagementControl extends HttpServlet{

private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        CategoryDao categoryDao = new CategoryDao();
        // Get all categories from database.
        List<Category> categoryList = categoryDao.getAllCategories();
        

        request.setAttribute("category_list", categoryList);
        
        request.setAttribute("category_management_active", "active");
        // Get request dispatcher and render to product-management page.
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("category-management.jsp");
        requestDispatcher.forward(request, response);
    }

}
