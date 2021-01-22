package models.reports;

import java.math.BigDecimal;

public class Expense {
	private int expId;
	private int shiftId;
	private String expName;
	private BigDecimal expSum;
	private String expCategory;
	
	public Expense() {}
	
	public Expense(int expId, int shiftId, String expName, BigDecimal expSum, String expCategory) {
		this.setExpId(expId);
		this.setShiftId(shiftId);
		this.setExpName(expName);
		this.setExpSum(expSum);
		this.setExpCategory(expCategory);
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	public String getExpName() {
		return expName;
	}

	public void setExpName(String expName) {
		this.expName = expName;
	}

	public BigDecimal getExpSum() {
		return expSum;
	}

	public void setExpSum(BigDecimal expSum) {
		this.expSum = expSum;
	}

	public String getExpCategory() {
		return expCategory;
	}

	public void setExpCategory(String expCategory) {
		this.expCategory = expCategory;
	}
	
	

}
