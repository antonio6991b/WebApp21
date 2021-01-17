package models.reports;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportProductShow {
	
	private int reportId;
	private int productId;
	private String productName;
	private BigDecimal priceBuy;
	private BigDecimal priceSell;
	private BigDecimal remainsLast;
	private BigDecimal coming;
	private BigDecimal remainsCurrent;
	private BigDecimal sumCurrent;
	private BigDecimal grossProfit;
	private BigDecimal notebookValue;
	private BigDecimal balance;
	
	
	public ReportProductShow() {}

	public ReportProductShow(int reportId, int productId, String productName, BigDecimal priceBuy, BigDecimal priceSell,
			BigDecimal remainsLast, BigDecimal coming, BigDecimal remainsCurrent,  
			BigDecimal notebookValue) {
		this.setReportId(reportId);
		this.setProductId(productId);
		this.setProductName(productName);
		this.setPriceBuy(priceBuy);
		this.setPriceSell(priceSell);
		this.setRemainsLast(remainsLast);
		this.setComing(coming);
		this.setRemainsCurrent(remainsCurrent);
		//this.setSumCurrent(sumCurrent);
		//this.setGrossProfit(grossProfit);
		this.setNotebookValue(notebookValue);
		//this.setBalance(balance);
		calculate();
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPriceBuy() {
		return priceBuy;
	}

	public void setPriceBuy(BigDecimal priceBuy) {
		this.priceBuy = priceBuy;
	}

	public BigDecimal getPriceSell() {
		return priceSell;
	}

	public void setPriceSell(BigDecimal priceSell) {
		this.priceSell = priceSell;
	}

	public BigDecimal getRemainsLast() {
		return remainsLast;
	}

	public void setRemainsLast(BigDecimal remainsLast) {
		this.remainsLast = remainsLast;
	}

	

	public BigDecimal getRemainsCurrent() {
		return remainsCurrent;
	}

	public void setRemainsCurrent(BigDecimal remainsCurrent) {
		this.remainsCurrent = remainsCurrent;
	}

	public BigDecimal getSumCurrent() {
		return sumCurrent;
	}

	public void setSumCurrent(BigDecimal sumCurrent) {
		this.sumCurrent = sumCurrent;
	}

	public BigDecimal getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}

	public BigDecimal getNotebookValue() {
		return notebookValue;
	}

	public void setNotebookValue(BigDecimal notebookValue) {
		this.notebookValue = notebookValue;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public int getShiftId(int itemId) {
		int shiftId = 0;
		// TODO Auto-generated method stub
		try {
			
			String select = "SELECT shiftId From WeekReport Where reportId=? ; ";
					
			PreparedStatement preparedStatement = connection.prepareStatement(select);
			preparedStatement.setInt(1,  itemId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			shiftId = resultSet.getInt(1);
		resultSet.close();
		preparedStatement.close();
	//	connection.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return shiftId;
	
	}
	
	public BigDecimal getComing() {
		return coming;
	}

	public void setComing(BigDecimal coming) {
		this.coming = coming;
	}
	
	public void calculate() {
	//	sumCurrent = priceSell * (remainsLast + coming - remainsCurrent);
		
		this.sumCurrent = (priceSell.multiply((remainsLast.add(coming)).subtract(remainsCurrent))).setScale(3, RoundingMode.HALF_EVEN);
		
	// grossProfit = (priceSell - priceBuy) * (remainsLast + coming - remainsCurrent);
		this.grossProfit = (priceSell.subtract(priceBuy)).multiply((remainsLast.add(coming).subtract(remainsCurrent))).setScale(3, RoundingMode.HALF_EVEN);
		
	//	balance = notebookValue - sumCurrent;
		this.balance = (notebookValue.subtract(sumCurrent)).setScale(3, RoundingMode.HALF_EVEN);
		
	}

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
}


