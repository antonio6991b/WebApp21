package models.shifts;

import java.math.BigDecimal;
import java.sql.Date;

public class Shift {
	private int shiftId;
	private String shopId;
	private int sellerId;
	private String sellerName;
	private Date shiftBegin;
	private Date shiftEnd;
	private BigDecimal cashBegin;
	private BigDecimal cashEnd;

	public Shift() {}
	public Shift(int shiftId, String shopId, Date shiftBegin, Date shiftEnd,
			String sellerName, int sellerId,
			BigDecimal cashBegin, BigDecimal cashEnd) {
		this.setShiftId(shiftId);
		this.setShopId(shopId);
		this.setSellerName(sellerName);
		this.setSellerId(sellerId);
		this.setShiftBegin(shiftBegin);
		this.setShiftEnd(shiftEnd);
		this.setCashBegin(cashBegin);
		this.setCashEnd(cashEnd);
	}
	public int getShiftId() {
		return shiftId;
	}
	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
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
	public BigDecimal getCashBegin() {
		return cashBegin;
	}
	public void setCashBegin(BigDecimal cashBegin) {
		this.cashBegin = cashBegin;
	}
	public BigDecimal getCashEnd() {
		return cashEnd;
	}
	public void setCashEnd(BigDecimal cashEnd) {
		this.cashEnd = cashEnd;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
}
