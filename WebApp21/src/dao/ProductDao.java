package dao;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Product;
import models.reports.ReportShow;

public class ProductDao {
	
	
	private static String url = "jdbc:postgresql://localhost:5432/soulBeer";  // "jdbc:sqlserver://DARYA-PC\\SQLEXPRESS;databaseName=soul_beer";
	private static String name = "postgres";
	private static String pass = "13121994";
	private String select = "Select * from products";
	private List<Product> products = new ArrayList<Product>();
	
	private static Connection connection ;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			try {
				connection = DriverManager.getConnection(url,name,pass);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	public List<Product> index(){
		try {
			this.products.clear();
			Statement s = connection.createStatement();
			ResultSet resultSet = s.executeQuery(select);
			
			while(resultSet.next()) {
				
				int productId = resultSet.getInt(1);
				String productName = resultSet.getString(2);
				String productCategory = resultSet.getString(3);
				String productMaker = resultSet.getString(4);
				Product product = new Product(productId,	productName, productCategory, productMaker);
				this.products.add(product);
			}
			resultSet.close();
			s.close();
		//	connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.products;
	}


	public Product show(int productId) {
		// TODO Auto-generated method stub
		Product product = new Product();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM products where productId = ?");
			preparedStatement.setInt(1, productId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			product.setProductId(resultSet.getInt(1));
			product.setProductName(resultSet.getString(2));
			product.setProductCategory(resultSet.getString(3));
			product.setProductMaker(resultSet.getString(4));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}


	public void save(Product product) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT MAX(productId) from products;");
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			int order = rs.getInt(1) + 1;
			rs.close();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement("INSERT INTO products ( productName, productCategory, productMaker, productOrder)\r\n"
					+ "values ( ?, ?, ?, ?);");
			
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setString(2, product.getProductCategory());
			preparedStatement.setString(3,  product.getProductMaker());	
			preparedStatement.setInt(4, order);
			preparedStatement.execute();
			preparedStatement.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void delete(int productId) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM products WHERE productId = ?;");
			preparedStatement.setInt(1, productId);
			
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void update(int productId, Product product) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("UPDATE products\r\n"
					+ "SET productName = ? ,\r\n"
					+ "    productCategory = ? ,\r\n"
					+"     productMaker = ?\r\n"
					+ "WHERE productId = ?;");
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setString(2, product.getProductCategory());
			preparedStatement.setString(3, product.getProductMaker());
			preparedStatement.setInt(4, productId);
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public List<Product> showMissProducts(int shiftId, ReportShow reportShow) {
		List<Product> missProducts = new ArrayList<>();
		List<Product> allProducts = index();
		
		for(int i = 0; i < allProducts.size(); i++) {
			boolean missing = true;
			for(int j = 0; j < reportShow.getProducts().size(); j++) {
				if(allProducts.get(i).getProductId() == reportShow.getProducts().get(j).getProductId()) {
					missing = false;
				}
			}
			if (missing == true) {
				missProducts.add(allProducts.get(i));
				}
		}
		
		return missProducts;
	}


	
	
	
}
