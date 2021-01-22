package dao;

import java.math.BigDecimal;
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

import models.shifts.ShiftQuery;

public class ShiftDao {
	

	private static String url = "jdbc:postgresql://localhost:5432/soulBeer"; 
	private static String name = "postgres";
	private static String pass = "13121994";
	
	private List<Shift> shifts = new ArrayList<Shift>();
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
	
	
	public List<Shift> index(){
		try {
			this.shifts.clear();
			String select = "SELECT  "
								+ " shifts.shiftId," 
								+ " sellers.shopId,"
								+ " shifts.shiftBegin," 
								+ " shifts.shiftEnd,"
								+ " sellers.sellerName,"
								+ " sellers.sellerId, "
								+ " shifts.cashBegin, "
								+ " shifts.cashEnd "
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
				BigDecimal cashBegin = resultSet.getBigDecimal(7);
				BigDecimal cashEnd = resultSet.getBigDecimal(8);
				Shift shift = new Shift(shiftId, shopId, shiftBegin, shiftEnd, sellerName, sellerId, cashBegin, cashEnd);
				this.shifts.add(shift);
				
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
			preparedStatement = connection.prepareStatement("INSERT INTO shifts (sellerId, shiftBegin, shiftEnd, cashBegin, cashEnd)\r\n"
					+ "values (?, ?, ?, ?, ?);");
			preparedStatement.setInt(1, shift.getSellerId());
			preparedStatement.setDate(2, shift.getShiftBegin());
			preparedStatement.setDate(3, shift.getShiftEnd());
			preparedStatement.setBigDecimal(4, shift.getCashBegin());
			preparedStatement.setBigDecimal(5, shift.getCashEnd());
			
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


	public Shift show(int id) {
		Shift shift = new Shift();
		try {
			
			String select = "SELECT  "
								+ " shifts.shiftId," 
								+ " sellers.shopId,"
								+ " shifts.shiftBegin," 
								+ " shifts.shiftEnd,"
								+ " sellers.sellerName,"
								+ " sellers.sellerId, "
								+ " shifts.cashBegin, "
								+ " shifts.cashEnd "
								+ " FROM shifts , sellers"
								+ " WHERE (sellers.sellerId = shifts.sellerId) and (shifts.shiftId = ?) "
								+ " Order by shiftId";
			PreparedStatement preparedStatement = connection.prepareStatement(select);	
			preparedStatement.setInt(1,  id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
				
				shift.setShiftId(resultSet.getInt(1));
				shift.setShopId(resultSet.getString(2));
				shift.setShiftBegin(resultSet.getDate(3));
				shift.setShiftEnd(resultSet.getDate(4));
				shift.setSellerName(resultSet.getString(5));
				shift.setSellerId(resultSet.getInt(6));
				shift.setCashBegin(resultSet.getBigDecimal(7));
				shift.setCashEnd(resultSet.getBigDecimal(8));
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
		return shift;
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
					+ "    shiftEnd = ? , \r\n"
					+ "    cashBegin = ? , "
					+ "    cashEnd = ?"
					+ "WHERE shiftId = ?;");
			preparedStatement.setInt(1, shift.getSellerId());
			preparedStatement.setDate(2, shift.getShiftBegin());
			preparedStatement.setDate(3, shift.getShiftEnd());
			preparedStatement.setBigDecimal(4, shift.getCashBegin());
			preparedStatement.setBigDecimal(5, shift.getCashEnd());			
			preparedStatement.setInt(6, shiftId);
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public List<Shift> search() {
		try {
			this.shifts.clear();
			String select = "SELECT  "
								+ " shifts.shiftId," 
								+ " sellers.shopId,"
								+ " shifts.shiftBegin," 
								+ " shifts.shiftEnd,"
								+ " sellers.sellerName,"
								+ " sellers.sellerId, "
								+ " shifts.cashBegin, "
								+ " shifts.cashEnd "
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
				BigDecimal cashBegin = resultSet.getBigDecimal(7);
				BigDecimal cashEnd = resultSet.getBigDecimal(8);

				Shift shift = new Shift(shiftId, shopId, shiftBegin, shiftEnd, sellerName, sellerId, cashBegin, cashEnd);
				this.shifts.add(shift);
				
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
