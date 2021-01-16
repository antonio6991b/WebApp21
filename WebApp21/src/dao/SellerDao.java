package dao;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Seller;

public class SellerDao {
	
	
	private static String url = "jdbc:postgresql://localhost:5432/soulBeer";  // "jdbc:sqlserver://DARYA-PC\\SQLEXPRESS;databaseName=soul_beer";
	private static String name = "postgres";
	private static String pass = "13121994";
	private String select = "Select * from sellers";
	private List<Seller> sellers = new ArrayList<Seller>();
	
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
	
	
	public List<Seller> index(){
		try {
			this.sellers.clear();
			Statement s = connection.createStatement();
			ResultSet resultSet = s.executeQuery(select);
			
			while(resultSet.next()) {
				int sellerId = resultSet.getInt(1);
				String shopId = resultSet.getString(2);
				String sellerName = resultSet.getString(3);
				Long sellerPhone = resultSet.getLong(4);
				Seller seller = new Seller(sellerId, shopId, sellerName, sellerPhone);
				this.sellers.add(seller);
			}
			resultSet.close();
			s.close();
		//	connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.sellers;
	}


	public Seller show(int sellerId) {
		// TODO Auto-generated method stub
		Seller seller = new Seller(); //(0, "", "There s no seller", null);
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM sellers where sellerId = ?");
			preparedStatement.setInt(1, sellerId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			seller.setSellerId(resultSet.getInt(1));
			seller.setShopId(resultSet.getString(2));
			seller.setSellerName(resultSet.getString(3));
			seller.setSellerPhone(resultSet.getLong(4));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seller;
	}


	public void save(Seller seller) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO sellers (shopId, sellerName, sellerPhone)\r\n"
					+ "values (?, ?, ?);");
			preparedStatement.setString(1, seller.getShopId());
			preparedStatement.setString(2, seller.getSellerName());
			preparedStatement.setLong(3, seller.getSellerPhone());
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void delete(int sellerId) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM SELLERS WHERE sellerId = ?;");
			preparedStatement.setInt(1, sellerId);
			
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void update(int sellerId, Seller seller) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("UPDATE sellers\r\n"
					+ "SET shopID = ? ,\r\n"
					+ "    sellerName = ? ,\r\n"
					+ "    sellerPhone = ? \r\n"
					+ "WHERE sellerId = ?;");
			preparedStatement.setString(1, seller.getShopId());
			preparedStatement.setString(2, seller.getSellerName());
			preparedStatement.setLong(3, seller.getSellerPhone());
			preparedStatement.setInt(4, sellerId);
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
