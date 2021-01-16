package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.reports.ReportProductShow;
import models.reports.ReportShow;
import models.shifts.ShiftIndex;

public class ReportDao {
	
	private static String url = "jdbc:postgresql://localhost:5432/soulBeer"; 
	private static String name = "postgres";
	private static String pass = "13121994";
	
	
	
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

	
	public ReportShow show(int shiftId){
		ReportShow reportShow = new ReportShow();
		List<ReportProductShow> productList = new ArrayList<>();
		ShiftDao shiftDao = new ShiftDao();
		ShiftIndex shift = shiftDao.show(shiftId);
		reportShow.setShiftId(shiftId);
		reportShow.setShopId(shift.getShopId());
		reportShow.setShiftBegin(shift.getShiftBegin());
		reportShow.setShiftEnd(shift.getShiftEnd());
		reportShow.setSellerName(shift.getSellerName());
		reportShow.setSellerId(shift.getSellerId());
		try {
			
			String select = "SELECT \r\n"
					+ "weekReport.reportId, \r\n"
					+ "weekReport.productId,\r\n"
					+ "products.productName,\r\n"
					+ "weekReport.priceBuy, \r\n"
					+ "weekReport.priceSell, \r\n"
					+ "weekReport.remainsLast,\r\n"
					+ "weekReport.coming, \r\n"
					+ "weekReport.remainsCurrent,\r\n"
					+ "weekReport.sumCurrent, \r\n"
					+ "weekReport.grossProfit,\r\n"
					+ "weekReport.notebookValue, \r\n"
					+ "weekReport.balance\r\n"
					+ "FROM weekReport ,products\r\n"
					+ "WHERE (weekReport.productId = products.productId)"
					+ "AND (weekReport.shiftId = ?); ";
					
			PreparedStatement preparedStatement = connection.prepareStatement(select);
			preparedStatement.setInt(1,  shiftId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				ReportProductShow reportProductShow = new ReportProductShow();
				
				reportProductShow.setReportId(resultSet.getInt(1));
				reportProductShow.setProductId(resultSet.getInt(2));
				reportProductShow.setProductName(resultSet.getString(3));
				reportProductShow.setPriceBuy(resultSet.getBigDecimal(4));
				reportProductShow.setPriceSell(resultSet.getBigDecimal(5));
				reportProductShow.setRemainsLast(resultSet.getBigDecimal(6));
				reportProductShow.setComing(resultSet.getInt(7));
				reportProductShow.setRemainsCurrent(resultSet.getBigDecimal(8));
				reportProductShow.setSumCurrent(resultSet.getBigDecimal(9));
				reportProductShow.setGrossProfit(resultSet.getBigDecimal(10));
				reportProductShow.setNotebookValue(resultSet.getBigDecimal(11));
				reportProductShow.setBalance(resultSet.getBigDecimal(12));
				
				
				productList.add(reportProductShow);
				
			}
			resultSet.close();
			preparedStatement.close();
		//	connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reportShow.setProducts(productList);
		return reportShow;
	}

	public void editItem(int reportId, ReportProductShow reportProductShow) {
			try {
			
			String select = "UPDATE weekReport SET \r\n"					
					+ "productId = ?,\r\n"
					+ "priceBuy = ?, \r\n"
					+ "priceSell = ?, \r\n"
					+ "remainsLast = ?,\r\n"
					+ "coming = ?, \r\n"
					+ "remainsCurrent = ?,\r\n"
					+ "sumCurrent = ?, \r\n"
					+ "grossProfit = ?,\r\n"
					+ "notebookValue = ?, \r\n"
					+ "balance = ? \r\n"
					
					+ "WHERE (weekReport.reportId = ?); ";
					
			PreparedStatement preparedStatement = connection.prepareStatement(select);
			preparedStatement.setInt(1,  reportProductShow.getProductId());
			preparedStatement.setBigDecimal(2, reportProductShow.getPriceBuy());
			preparedStatement.setBigDecimal(3, reportProductShow.getPriceSell());
			preparedStatement.setBigDecimal(4, reportProductShow.getRemainsLast());
			preparedStatement.setInt(5, reportProductShow.getComing());
			preparedStatement.setBigDecimal(6, reportProductShow.getRemainsCurrent());
			preparedStatement.setBigDecimal(7, reportProductShow.getSumCurrent());
			preparedStatement.setBigDecimal(8,  reportProductShow.getGrossProfit());
			preparedStatement.setBigDecimal(9,  reportProductShow.getNotebookValue());
			preparedStatement.setBigDecimal(10, reportProductShow.getBalance());
			preparedStatement.setInt(11,  reportId);
			
			preparedStatement.execute();
			
			
			preparedStatement.close();
		//	connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ReportProductShow showItem(int reportId) {
		ReportProductShow reportProductShow= new ReportProductShow();
		try {
		
			String select = "SELECT \r\n"
					+ "weekReport.reportId, \r\n"
					+ "weekReport.productId,\r\n"
					+ "products.productName,\r\n"
					+ "weekReport.priceBuy, \r\n"
					+ "weekReport.priceSell, \r\n"
					+ "weekReport.remainsLast,\r\n"
					+ "weekReport.coming, \r\n"
					+ "weekReport.remainsCurrent,\r\n"
					+ "weekReport.sumCurrent, \r\n"
					+ "weekReport.grossProfit,\r\n"
					+ "weekReport.notebookValue, \r\n"
					+ "weekReport.balance\r\n"
					+ "FROM weekReport ,products\r\n"
					+ "WHERE (weekReport.reportId = ?); ";
					
			PreparedStatement preparedStatement = connection.prepareStatement(select);
			preparedStatement.setInt(1,  reportId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
			reportProductShow.setReportId(resultSet.getInt(1));
			reportProductShow.setProductId(resultSet.getInt(2));
			reportProductShow.setProductName(resultSet.getString(3));
			reportProductShow.setPriceBuy(resultSet.getBigDecimal(4));
			reportProductShow.setPriceSell(resultSet.getBigDecimal(5));
			reportProductShow.setRemainsLast(resultSet.getBigDecimal(6));
			reportProductShow.setComing(resultSet.getInt(7));
			reportProductShow.setRemainsCurrent(resultSet.getBigDecimal(8));
			reportProductShow.setSumCurrent(resultSet.getBigDecimal(9));
			reportProductShow.setGrossProfit(resultSet.getBigDecimal(10));
			reportProductShow.setNotebookValue(resultSet.getBigDecimal(11));
			reportProductShow.setBalance(resultSet.getBigDecimal(12));
			
		resultSet.close();
		preparedStatement.close();
	//	connection.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return reportProductShow;
	
}

	public void addItem(int shiftId, ReportProductShow reportProductShow) {
try {
			
			String select = "INSERT INTO weekReport ( \r\n"	
					
					+ "productId ,\r\n"
					+ "priceBuy , \r\n"
					+ "priceSell , \r\n"
					+ "remainsLast,\r\n"
					+ "coming , \r\n"
					+ "remainsCurrent ,\r\n"
					+ "sumCurrent , \r\n"
					+ "grossProfit ,\r\n"
					+ "notebookValue , \r\n"
					+ "balance,"
					+ "shiftId) \r\n"
					
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
					
			PreparedStatement preparedStatement = connection.prepareStatement(select);
			preparedStatement.setInt(1,  reportProductShow.getProductId());
			preparedStatement.setBigDecimal(2, reportProductShow.getPriceBuy());
			preparedStatement.setBigDecimal(3, reportProductShow.getPriceSell());
			preparedStatement.setBigDecimal(4, reportProductShow.getRemainsLast());
			preparedStatement.setInt(5, reportProductShow.getComing());
			preparedStatement.setBigDecimal(6, reportProductShow.getRemainsCurrent());
			preparedStatement.setBigDecimal(7, reportProductShow.getSumCurrent());
			preparedStatement.setBigDecimal(8,  reportProductShow.getGrossProfit());
			preparedStatement.setBigDecimal(9,  reportProductShow.getNotebookValue());
			preparedStatement.setBigDecimal(10, reportProductShow.getBalance());
			preparedStatement.setInt(11,  shiftId);
			
			preparedStatement.execute();
			
			
			preparedStatement.close();
		//	connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void deleteItem(int itemId) {
	try {
			
			String select = "DELETE from weekReport  WHERE reportId = ?; ";
			
			//System.out.println("reportId " + itemId);
			PreparedStatement preparedStatement = connection.prepareStatement(select);
			
			preparedStatement.setInt(1,  itemId);
			
			preparedStatement.execute();
			
			
			preparedStatement.close();
		//	connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}


}