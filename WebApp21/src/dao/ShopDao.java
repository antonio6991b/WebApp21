package dao;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Shop;

public class ShopDao {
	
	
	private static String url = "jdbc:postgresql://localhost:5432/soulBeer";  // "jdbc:sqlserver://DARYA-PC\\SQLEXPRESS;databaseName=soul_beer";
	private static String name = "postgres";
	private static String pass = "13121994";
	private String select = "Select * from shops";
	private List<Shop> shops = new ArrayList<Shop>();
	
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
	
	
	public List<Shop> index(){
		try {
			this.shops.clear();
			Statement s = connection.createStatement();
			ResultSet resultSet = s.executeQuery(select);
			
			while(resultSet.next()) {
				
				String shopId = resultSet.getString(1);
				String shopAddress = resultSet.getString(2);
				Long shopPhone = resultSet.getLong(3);
				int isCity = resultSet.getInt(4);
				Shop shop = new Shop(shopId, shopAddress, shopPhone, isCity);
				this.shops.add(shop);
			}
			resultSet.close();
			s.close();
		//	connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.shops;
	}


	public Shop show(String shopId) {
		// TODO Auto-generated method stub
		Shop shop = new Shop(); //(0, "", "There s no seller", null);
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM shops where shopId = ?");
			preparedStatement.setString(1, shopId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			shop.setShopId(resultSet.getString(1));
			shop.setShopAddress(resultSet.getString(2));
			shop.setShopPhone(resultSet.getLong(3));
			shop.setIsCity(resultSet.getInt(4));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shop;
	}


	public void save(Shop shop) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO shops (shopId, shopAddress, shopPhone, isCity)\r\n"
					+ "values (?, ?, ?,?);");
			preparedStatement.setString(1, shop.getShopId());
			preparedStatement.setString(2, shop.getShopAddress());
			preparedStatement.setLong(3, shop.getShopPhone());
			preparedStatement.setInt(4,  shop.getIsCity());
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void delete(String shopId) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM shops WHERE shopId = ?;");
			preparedStatement.setString(1, shopId);
			
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void update(String shopId, Shop shop) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("UPDATE shops\r\n"
					+ "SET shopAddress = ? ,\r\n"
					+ "    shopPhone = ? ,\r\n"
					+"     isCity = ?\r\n"
					+ "WHERE shopId = ?;");
			preparedStatement.setString(1, shop.getShopAddress());
			preparedStatement.setLong(2, shop.getShopPhone());
			preparedStatement.setInt(3, shop.getIsCity());
			preparedStatement.setString(4, shopId);
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
	
}
