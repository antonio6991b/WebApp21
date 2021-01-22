package models.reports;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CashDesk {
	private int shiftId;
	private BigDecimal totalExpenses = BigDecimal.valueOf(0);
	
	private List<Expense> cashDeskExpenses;
	
	public CashDesk() {
	}

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	public BigDecimal getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(BigDecimal totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public List<Expense> getCashDeskExpenses() {
		return cashDeskExpenses;
	}

	public void setCashDeskExpenses(List<Expense> cashDeskExpenses) {
		this.cashDeskExpenses = cashDeskExpenses;
	}

	public void calculateTotalExpenses() {
		BigDecimal te = new BigDecimal("0");	
		
		for(int i = 0; i< cashDeskExpenses.size(); i++)
		{
			te = te.add(cashDeskExpenses.get(i).getExpSum());
		//	System.out.println(cashDeskExpenses.get(i).getExpSum());
		}
		this.setTotalExpenses(te.setScale(3, RoundingMode.HALF_EVEN));
		//System.out.println(te);
		
	}
	

}
