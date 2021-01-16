package models.shifts;

import java.sql.Date;

public class Shift {
	private int shiftId;
	private String shopId;
	private int sellerId;
	private Date shiftBegin;
	private Date shiftEnd;

	public Shift() {}
	public Shift(int shiftId, String shopId, int sellerId, Date shiftBegin, Date shiftEnd) {
		this.setShiftId(shiftId);
		this.setShopId(shopId);
		this.setSellerId(sellerId);
		this.setShiftBegin(shiftBegin);
		this.setShiftEnd(shiftEnd);
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
}
