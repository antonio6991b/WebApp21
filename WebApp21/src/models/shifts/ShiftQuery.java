package models.shifts;

import java.sql.Date;

public class ShiftQuery {
	private Date shiftBegin;
	private Date shiftEnd;
	private String shopId;
	
	public ShiftQuery(Date shiftBegin, Date shiftEnd, String shopId) {
		this.setShiftBegin(shiftBegin);
		this.setShiftEnd(shiftEnd);
		this.setShopId(shopId);
	}
	public ShiftQuery() {}
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
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

}
