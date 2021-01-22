package models.reports;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReportShow {
	
	private int shiftId;
	private String shopId;
	private Date shiftBegin;
	private Date shiftEnd;
	private int sellerId;
	private String sellerName;	
	
	private List<ReportProductShow> products = new ArrayList<>();
	
	private BigDecimal totalSumCurrent = BigDecimal.valueOf(0);
	private BigDecimal totalGrossProfit = BigDecimal.valueOf(0);
	private BigDecimal totalBalance = BigDecimal.valueOf(0);
	private BigDecimal totalExpenses = BigDecimal.valueOf(0);
	private BigDecimal totalNetProfit = BigDecimal.valueOf(0);
	
	private CashDesk cashDesk;
	private CashDesk writeOff;
	private CashDesk costs;
//	private Expenses expenses;
	//private WriteOff writeOff;
	
	public ReportShow() {
		
		
	}

	

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	public List<ReportProductShow> getProducts() {
		return products;
	}

	public void setProducts(List<ReportProductShow> products) {
		this.products = products;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public Date getShiftBegin() {
		return shiftBegin;
	}

	public void setShiftBegin(Date shiftBegin) {
		this.shiftBegin = shiftBegin;
	}

	public Date getShiftEnd() {
		return shiftEnd;
	}

	public void setShiftEnd(Date shiftEnd) {
		this.shiftEnd = shiftEnd;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}



	public int getSellerId() {
		return this.sellerId;
	}



	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}



	public BigDecimal getTotalSumCurrent() {
		return totalSumCurrent;
	}



	public void setTotalSumCurrent(BigDecimal totalSumCurrent) {
		this.totalSumCurrent = totalSumCurrent;
	}



	public BigDecimal getTotalGrossProfit() {
		return totalGrossProfit;
	}



	public void setTotalGrossProfit(BigDecimal totalGrossProfit) {
		this.totalGrossProfit = totalGrossProfit;
	}



	public BigDecimal getTotalBalance() {
		return totalBalance;
	}



	public void setTotalBalance(BigDecimal totalBalance) {
		this.totalBalance = totalBalance;
	}



	public BigDecimal getTotalExpenses() {
		return totalExpenses;
	}



	public void setTotalExpenses(BigDecimal totalExpenses) {
		this.totalExpenses = totalExpenses;
	}



	public BigDecimal getTotalNetProfit() {
		return totalNetProfit;
	}



	public void setTotalNetProfit(BigDecimal totalNetProfit) {
		this.totalNetProfit = totalNetProfit;
	}



	public CashDesk getCashDesk() {
		return cashDesk;
	}



	public void setCashDesk(CashDesk cashDesk) {
		this.cashDesk = cashDesk;
	}



	public CashDesk getWriteOff() {
		return writeOff;
	}



	public void setWriteOff(CashDesk writeOff) {
		this.writeOff = writeOff;
	}



	public CashDesk getCosts() {
		return costs;
	}



	public void setCosts(CashDesk costs) {
		this.costs = costs;
	}



	public void calculate() {
		BigDecimal totalExpenses = this.writeOff.getTotalExpenses().add(this.cashDesk.getTotalExpenses());
		BigDecimal netProfit = this.getTotalGrossProfit().subtract(this.writeOff.getTotalExpenses().add(this.cashDesk.getTotalExpenses()));
		
		this.setTotalExpenses(totalExpenses);
		this.setTotalNetProfit(netProfit);
		
	}
	

}
