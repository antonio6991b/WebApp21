package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.reports.CashDesk;
import models.reports.Expense;
import models.reports.ReportProductShow;
import models.reports.ReportShow;
import models.shifts.Shift;


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
		Shift shift = shiftDao.show(shiftId);
		BigDecimal totalSumCurrent = new BigDecimal(0);
		BigDecimal totalGrossProfit = new BigDecimal(0);
		BigDecimal totalBalance = new BigDecimal(0);
		
		CashDesk cashDesk = new CashDesk();
		CashDesk writeOff = new CashDesk();
		CashDesk costs = new CashDesk();
		
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
					//+ "weekReport.sumCurrent, \r\n"
					//+ "weekReport.grossProfit,\r\n"
					+ "weekReport.notebookValue \r\n"
					//+ "weekReport.balance\r\n"
					+ "FROM weekReport ,products\r\n"
					+ "WHERE (weekReport.productId = products.productId)"
					+ "AND (weekReport.shiftId = ?)"
					+" order by reportId; ";
					
			PreparedStatement preparedStatement = connection.prepareStatement(select);
			preparedStatement.setInt(1,  shiftId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {				
				
				int reportId = resultSet.getInt(1);
				int productId = resultSet.getInt(2);
				String productName = resultSet.getString(3);
				BigDecimal priceBuy = resultSet.getBigDecimal(4);
				BigDecimal priceSell = resultSet.getBigDecimal(5);
				BigDecimal remainsLast = resultSet.getBigDecimal(6);
				BigDecimal coming = resultSet.getBigDecimal(7);
				BigDecimal remainsCurrent = resultSet.getBigDecimal(8);
				BigDecimal notebookValue = resultSet.getBigDecimal(9);
				
				ReportProductShow reportProductShow = 
						new ReportProductShow(reportId, productId, productName, priceBuy, priceSell, remainsLast,
								coming, remainsCurrent, notebookValue);				
				totalSumCurrent = totalSumCurrent.add(reportProductShow.getSumCurrent());
				//System.out.print("total sum current");
				//System.out.println(reportProductShow.getSumCurrent());
				totalGrossProfit = totalGrossProfit.add(reportProductShow.getGrossProfit());
				totalBalance = totalBalance.add(reportProductShow.getBalance());
				
				productList.add(reportProductShow);
				
			}
			resultSet.close();
			preparedStatement.close();
		//	connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			
			String selectExp = "SELECT \r\n"
					+ "expId, \r\n"
					+ "expName,\r\n"
					+ "expSum,\r\n"
					+ "expCategory \r\n"
					
					+ "FROM expenses "
					+ " WHERE (shiftId = ?) " 
					+" order by expId; ";
					
			PreparedStatement preparedStatement = connection.prepareStatement(selectExp);
			preparedStatement.setInt(1,  shiftId);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Expense> cashDeskExpenses = new ArrayList<>();
			List<Expense> writeOffExpenses = new ArrayList<>();
			List<Expense> costsExpenses = new ArrayList<>();
			while(resultSet.next()) {				
				
				int expId = resultSet.getInt(1);
				String expName = resultSet.getString(2);
				BigDecimal expSum = resultSet.getBigDecimal(3);
				String expCategory = resultSet.getString(4);
				
				Expense expense = new Expense();
				expense.setExpId(expId);
				expense.setExpName(expName);
				expense.setExpSum(expSum);
				expense.setExpCategory(expCategory);
				
				if(expCategory.equals("Касса")) {
					cashDeskExpenses.add(expense);					
				}
				
				if(expCategory.equals("Списание")) {
					writeOffExpenses.add(expense);
				}
				
				if (expCategory.equals("Расходы")) {
					costsExpenses.add(expense);
				}
				cashDesk.setCashDeskExpenses(cashDeskExpenses);
				cashDesk.calculateTotalExpenses();
				writeOff.setCashDeskExpenses(writeOffExpenses);
				writeOff.calculateTotalExpenses();
				costs.setCashDeskExpenses(costsExpenses);
				costs.calculateTotalExpenses();
				
				
			}
			resultSet.close();
			preparedStatement.close();
		//	connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reportShow.setTotalSumCurrent(totalSumCurrent);
		reportShow.setTotalGrossProfit(totalGrossProfit);
		reportShow.setTotalBalance(totalBalance);
		reportShow.setProducts(productList);
		reportShow.setCashDesk(cashDesk);
		reportShow.setWriteOff(writeOff);
		reportShow.setCosts(costs);
		reportShow.calculate();
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
				//	+ "sumCurrent = ?, \r\n"
				//	+ "grossProfit = ?,\r\n"
					+ "notebookValue = ? \r\n"
				//	+ "balance = ? \r\n"
					
					+ "WHERE (weekReport.reportId = ?); ";
					
			PreparedStatement preparedStatement = connection.prepareStatement(select);
			preparedStatement.setInt(1,  reportProductShow.getProductId());
			preparedStatement.setBigDecimal(2, reportProductShow.getPriceBuy());
			preparedStatement.setBigDecimal(3, reportProductShow.getPriceSell());
			preparedStatement.setBigDecimal(4, reportProductShow.getRemainsLast());
			preparedStatement.setBigDecimal(5, reportProductShow.getComing());
			preparedStatement.setBigDecimal(6, reportProductShow.getRemainsCurrent());
		//	preparedStatement.setBigDecimal(7, reportProductShow.getSumCurrent());
		//	preparedStatement.setBigDecimal(8,  reportProductShow.getGrossProfit());
			preparedStatement.setBigDecimal(7,  reportProductShow.getNotebookValue());
		//	preparedStatement.setBigDecimal(10, reportProductShow.getBalance());
			preparedStatement.setInt(8,  reportId);
			
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
				//	+ "weekReport.sumCurrent, \r\n"
				//	+ "weekReport.grossProfit,\r\n"
					+ "weekReport.notebookValue \r\n"
				//	+ "weekReport.balance\r\n"
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
			reportProductShow.setComing(resultSet.getBigDecimal(7));
			reportProductShow.setRemainsCurrent(resultSet.getBigDecimal(8));
			//reportProductShow.setSumCurrent(resultSet.getBigDecimal(9));
			//reportProductShow.setGrossProfit(resultSet.getBigDecimal(10));
			reportProductShow.setNotebookValue(resultSet.getBigDecimal(9));
			//reportProductShow.setBalance(resultSet.getBigDecimal(12));
			reportProductShow.calculate();
			
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
				//	+ "sumCurrent , \r\n"
				//	+ "grossProfit ,\r\n"
					+ "notebookValue , \r\n"
				//	+ "balance,"
					+ "shiftId) \r\n"
					
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?); ";
					
			PreparedStatement preparedStatement = connection.prepareStatement(select);
			preparedStatement.setInt(1,  reportProductShow.getProductId());
			preparedStatement.setBigDecimal(2, reportProductShow.getPriceBuy());
			preparedStatement.setBigDecimal(3, reportProductShow.getPriceSell());
			preparedStatement.setBigDecimal(4, reportProductShow.getRemainsLast());
			preparedStatement.setBigDecimal(5, reportProductShow.getComing());
			preparedStatement.setBigDecimal(6, reportProductShow.getRemainsCurrent());
		//	preparedStatement.setBigDecimal(7, reportProductShow.getSumCurrent());
		//	preparedStatement.setBigDecimal(8,  reportProductShow.getGrossProfit());
			preparedStatement.setBigDecimal(7,  reportProductShow.getNotebookValue());
		//	preparedStatement.setBigDecimal(10, reportProductShow.getBalance());
			preparedStatement.setInt(8,  shiftId);
			
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
	
	public void addExpense(int shiftId, Expense expense) {
		try {
			
			String select = "INSERT INTO expenses ( \r\n"	
					
					+ "shiftId ,\r\n"
					+ "expName , \r\n"
					+ "expSum , \r\n"
					+ "expCategory ) \r\n"
					+ "VALUES ( ?, ?, ?, ?); ";
					
			PreparedStatement preparedStatement = connection.prepareStatement(select);
			preparedStatement.setInt(1, shiftId);
			preparedStatement.setString(2, expense.getExpName());
			preparedStatement.setBigDecimal(3, expense.getExpSum());
			preparedStatement.setString(4, expense.getExpCategory());
			preparedStatement.execute();			
			
			preparedStatement.close();
		//	connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public Expense showExpense(int expId) {
		Expense expense = new Expense();
		try {

			String selectExp = "SELECT \r\n"
					+ " expId, \r\n"
					+ " shiftId, "
					+ " expName,  \r\n"
					+ " expSum, \r\n"
					+ " expCategory \r\n"
					
					+ " FROM expenses "
					+ " WHERE (expId = ?) ;" ; 
							
			PreparedStatement preparedStatement = connection.prepareStatement(selectExp);
			preparedStatement.setInt(1,  expId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
				expId = resultSet.getInt(1);
				int shiftId = resultSet.getInt(2);
				String expName = resultSet.getString(3);
				BigDecimal expSum = resultSet.getBigDecimal(4);
				String expCategory = resultSet.getString(5);
				
				expense.setExpId(expId);
				expense.setShiftId(shiftId);
				expense.setExpName(expName);
				expense.setExpSum(expSum);
				expense.setExpCategory(expCategory);
			resultSet.close();
			preparedStatement.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
		return expense;
	}
	
	public void editExpense(Expense expense) {
		
		try {

			String updateExp = "UPDATE expenses SET \r\n"					
					+ "expName = ?,\r\n"
					+ "expSum = ? \r\n"
					+ "WHERE (expId = ?); ";
							
			PreparedStatement preparedStatement = connection.prepareStatement(updateExp);
			preparedStatement.setString(1, expense.getExpName());
			preparedStatement.setBigDecimal(2, expense.getExpSum());
			preparedStatement.setInt(3,  expense.getExpId());
			preparedStatement.execute();
			
		
			preparedStatement.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
		
	}

	
	public void deleteExpense(Expense expense) {
		
		try {

			String updateExp = "DELETE FROM expenses \r\n"					
					+ "WHERE (expId = ?); ";
							
			PreparedStatement preparedStatement = connection.prepareStatement(updateExp);
			preparedStatement.setInt(1, expense.getExpId());
			preparedStatement.execute();
			
		
			preparedStatement.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
		
	}
}
