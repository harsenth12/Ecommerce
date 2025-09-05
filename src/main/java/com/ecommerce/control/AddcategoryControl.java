package com.ecommerce.control;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ecommerce.dao.CategoryDao;

@WebServlet(name = "CategoryControl", value = "/add-category")
@MultipartConfig
public class AddcategoryControl extends  HttpServlet{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get category name
        String categoryName = request.getParameter("category-name");

        InputStream inputStream = null;

        // Get upload image
        Part part = request.getPart("category-image"); // must match your <input name="...">
        if (part != null && part.getSize() > 0) {
            inputStream = part.getInputStream();
        } else {
            System.out.println("No file uploaded");
        }

        // Add category to database
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.addcategory(categoryName, inputStream);

        response.sendRedirect("category-management.jsp");
    }
	}
