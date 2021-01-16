package models.shifts;

import java.sql.Date;

public class ShiftIndex {
	
	private int shiftId;
	private String shopId;
	private Date shiftBegin;
	private Date shiftEnd;
	private String sellerName;
	private int sellerId;
	
	public ShiftIndex() {}
	public ShiftIndex(int shiftId, String shopId, Date shiftBegin, Date shiftEnd, String sellerName, int sellerId) {
		this.setShiftId(shiftId);
		this.setShopId(shopId);
		this.setShiftBegin(shiftBegin);
		this.setShiftEnd(shiftEnd);
		this.setSellerName(sellerName);
		this.setSellerId(sellerId);
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
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	

}
