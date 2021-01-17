package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.shifts.Shift;
import models.shifts.ShiftIndex;
import models.shifts.ShiftQuery;

public class ShiftDao {
	

	private static String url = "jdbc:postgresql://localhost:5432/soulBeer"; 
	private static String name = "postgres";
	private static String pass = "13121994";
	
	private List<ShiftIndex> shifts = new ArrayList<ShiftIndex>();
	private ShiftQuery shiftQuery = new ShiftQuery();
	
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
	
	
	public List<ShiftIndex> index(){
		try {
			this.shifts.clear();
			String select = "SELECT  "
								+ " shifts.shiftId," 
								+ " sellers.shopId,"
								+ " shifts.shiftBegin," 
								+ " shifts.shiftEnd,"
								+ " sellers.sellerName,"
								+ " sellers.sellerId "
								+ " FROM shifts , sellers"
								+ " WHERE (sellers.sellerId = shifts.sellerId); ";
					
			Statement s = connection.createStatement();
			ResultSet resultSet = s.executeQuery(select);
			
			while(resultSet.next()) {
				int shiftId = resultSet.getInt(1);
				String shopId = resultSet.getString(2);
				Date shiftBegin = resultSet.getDate(3);
				Date shiftEnd = resultSet.getDate(4);
				String sellerName = resultSet.getString(5);
				int sellerId = resultSet.getInt(6);
				ShiftIndex shiftIndex = new ShiftIndex(shiftId, shopId, shiftBegin, shiftEnd, sellerName, sellerId);
				this.shifts.add(shiftIndex);
				
			}
			resultSet.close();
			s.close();
		//	connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.shifts;
	}


	public void save(Shift shift) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO shifts (sellerId, shiftBegin, shiftEnd)\r\n"
					+ "values (?, ?, ?);");
			preparedStatement.setInt(1, shift.getSellerId());
			preparedStatement.setDate(2, shift.getShiftBegin());
			preparedStatement.setDate(3, shift.getShiftEnd());
			
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


	public ShiftIndex show(int id) {
		ShiftIndex shiftIndex = new ShiftIndex();
		try {
			
			String select = "SELECT  "
								+ " shifts.shiftId," 
								+ " sellers.shopId,"
								+ " shifts.shiftBegin," 
								+ " shifts.shiftEnd,"
								+ " sellers.sellerName,"
								+ " sellers.sellerId "
								+ " FROM shifts , sellers"
								+ " WHERE (sellers.sellerId = shifts.sellerId) and (shifts.shiftId = ?) "
								+ " Order by shiftId";
			PreparedStatement preparedStatement = connection.prepareStatement(select);	
			preparedStatement.setInt(1,  id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
				
				shiftIndex.setShiftId(resultSet.getInt(1));
				shiftIndex.setShopId(resultSet.getString(2));
				shiftIndex.setShiftBegin(resultSet.getDate(3));
				shiftIndex.setShiftEnd(resultSet.getDate(4));
				shiftIndex.setSellerName(resultSet.getString(5));
				shiftIndex.setSellerId(resultSet.getInt(6));
				/*
				int sellerId = resultSet.getInt(6);
				shiftIndex.setShiftId(shiftId);
				shiftIndex.setShopId(shopId);
				shiftIndex.setShiftBegin(shiftBegin);
				shiftIndex.setShiftEnd(shiftEnd);
				shiftIndex.setSellerName(sellerName);
				shiftIndex.setSellerId(sellerId);
				
				*/
				
			
			resultSet.close();
			preparedStatement.close();
		//	connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shiftIndex;
	}
	
	public void delete(int shiftId) {
		
			PreparedStatement preparedStatement;
			try {
				preparedStatement = connection.prepareStatement("DELETE FROM shifts \r\n"
						+ "WHERE shiftID = ?;");
				preparedStatement.setInt(1, shiftId);
				preparedStatement.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}


	public void update(int shiftId, Shift shift) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("UPDATE shifts\r\n"
					+ "SET sellerId = ? ,\r\n"
					+ "    shiftBegin = ? ,\r\n"
					+ "    shiftEnd = ? \r\n"
					+ "WHERE shiftId = ?;");
			preparedStatement.setInt(1, shift.getSellerId());
			preparedStatement.setDate(2, shift.getShiftBegin());
			preparedStatement.setDate(3, shift.getShiftEnd());
			preparedStatement.setInt(4, shiftId);
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public List<ShiftIndex> search() {
		try {
			this.shifts.clear();
			String select = "SELECT  "
								+ " shifts.shiftId," 
								+ " sellers.shopId,"
								+ " shifts.shiftBegin," 
								+ " shifts.shiftEnd,"
								+ " sellers.sellerName,"
								+ " sellers.sellerId "
								+ " FROM shifts , sellers "
								+ " WHERE (sellers.sellerId = shifts.sellerId) "
								+ " AND (shifts.shiftBegin BETWEEN ? and ?)";
			
					
			PreparedStatement preparedStatement = connection.prepareStatement(select);
			
			preparedStatement.setDate(1, this.shiftQuery.getShiftBegin());
			preparedStatement.setDate(2,  this.shiftQuery.getShiftEnd());
			
		
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int shiftId = resultSet.getInt(1);
				String shopId = resultSet.getString(2);
				Date shiftBegin = resultSet.getDate(3);
				Date shiftEnd = resultSet.getDate(4);
				String sellerName = resultSet.getString(5);
				int sellerId = resultSet.getInt(6);
				ShiftIndex shiftIndex = new ShiftIndex(shiftId, shopId, shiftBegin, shiftEnd, sellerName, sellerId);
				this.shifts.add(shiftIndex);
				
			}
			resultSet.close();
			preparedStatement.close();
		//	connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.shifts;
	}


	public ShiftQuery getShiftQuery() {
		return shiftQuery;
	}


	public void setShiftQuery(ShiftQuery shiftQuery) {
		this.shiftQuery = shiftQuery;
	}

}
