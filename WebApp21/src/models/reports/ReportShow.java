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
	
	private BigDecimal totalSumCurrent;
	private BigDecimal totalGrossProfit;
	private BigDecimal totalBalance;
	
	public ReportShow() {}

	

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
	

}
