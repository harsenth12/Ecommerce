package com.ecommerce.dao;

import com.ecommerce.database.DBConnectionCloser;
import com.ecommerce.database.Database;
import com.ecommerce.entity.Category;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class CategoryDao extends DBConnectionCloser{
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    private String getBase64Image(Blob blob) {
        if (blob == null) return null;
        try (InputStream inputStream = blob.getBinaryStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] imageBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    
    // Method to set amount of products for category.
    private void queryCategoryProductAmount(Category category) {
        int productId = category.getId();
        String query = "SELECT COUNT(*) FROM product WHERE fk_category_id = " + productId + " AND product_is_deleted = false";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category.setTotalCategoryProduct(resultSet.getInt(1));
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Get category products amount catch: ");
            System.out.println(e.getMessage());
        }finally {
       	 dbConClose(connection);
       }
    }

    // Method to get category by id.
    public Category getCategory(int categoryId) {
        Category category = new Category();
        String query = "SELECT * FROM category WHERE category_id = " + categoryId;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }finally {
       	 dbConClose(connection);
       }

        // Call method to set category amount for category.
        queryCategoryProductAmount(category);

        return category;
    }

    // Method to get all categories from database.
    public List<Category> getAllCategories() throws IOException {
        List<Category> list = new ArrayList<>();
        String query = "SELECT * FROM category";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                Blob blob=(Blob)resultSet.getBlob("image");
                category.setBase64Image(getBase64Image(blob));
                list.add(category);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
       	 dbConClose(connection);
       }

        // Call method to set category amount for category.
        for (Category category : list) {
            queryCategoryProductAmount(category);
        }

        return list;
    }

	public boolean addcategory(String categoryName, InputStream inputStream) {
		    String sql = "INSERT INTO category (category_name, image) VALUES (?, ?)";
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;

		    try {
		        Class.forName("com.mysql.jdbc.Driver");
		        connection = new Database().getConnection();
		        preparedStatement = connection.prepareStatement(sql);

		        // set category name
		        preparedStatement.setString(1, categoryName);

		        // set image as blob
		        if (inputStream != null) {
		            preparedStatement.setBlob(2, inputStream);
		        } else {
		            preparedStatement.setNull(2, java.sql.Types.BLOB);
		        }

		        int row = preparedStatement.executeUpdate();
		        return row > 0; // true if insert succeeded

		    } catch (SQLException | ClassNotFoundException e) {
		        e.printStackTrace();
		    } finally {
		        dbConClose(connection); // your DBConnectionCloser method
		    }
		    return false;
		}

	}


		

