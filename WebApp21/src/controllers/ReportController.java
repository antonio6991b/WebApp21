package controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.ProductDao;
import dao.ReportDao;
import dao.ShiftDao;
import models.reports.CashDesk;
import models.reports.Expense;
import models.reports.ReportProductShow;
import models.reports.ReportShow;
import models.shifts.Shift;
import models.Product;

@Controller
@RequestMapping("/reports")
public class ReportController {
	
	ReportDao reportDao = new ReportDao();
	ProductDao productDao = new ProductDao();
	ShiftDao shiftDao = new ShiftDao();
	
	
	@GetMapping("/{shiftId}")
	public String showShiftReport(@PathVariable("shiftId") int shiftId, Model model) {
		ReportShow report = reportDao.show(shiftId);
		CashDesk cashDesk = report.getCashDesk();
		List<Expense> cashDeskList = cashDesk.getCashDeskExpenses();
		CashDesk writeOff = report.getWriteOff();
		List<Expense> writeOffList = writeOff.getCashDeskExpenses();
		CashDesk costs = report.getCosts();
		List<Expense> costsList = costs.getCashDeskExpenses();
		Shift shift = shiftDao.show(shiftId);
		model.addAttribute("report", report);
		model.addAttribute("productList", report.getProducts());
		model.addAttribute("cashDesk", cashDesk);
		model.addAttribute("cashDeskList", cashDeskList);
		model.addAttribute("writeOff", writeOff);
		model.addAttribute("writeOffList", writeOffList);
		model.addAttribute("costs" , costs);
		model.addAttribute("costsList", costsList);
		model.addAttribute("shift", shift);
		
		return "reports/showShiftReport";
	}
	
	@GetMapping("/editItem/{itemId}")
	public String editItem(Model model, @PathVariable("itemId") int itemId) {
		ReportProductShow item = reportDao.showItem(itemId);
		int shiftId = item.getShiftId(itemId);
		
		model.addAttribute("item", item);
		
		//System.out.println(reportDao.showItem(itemId).getReportId());
		List<Product> missEditProduct = productDao.showMissProducts(shiftId, reportDao.show(shiftId));
		missEditProduct.add(productDao.show(item.getProductId()));
		model.addAttribute("products", missEditProduct);
		return "reports/editItem";
	}
	
	 @PostMapping("/edit/{itemId}")
	    public String updateItem(@ModelAttribute("item") ReportProductShow reportProductShow, 
	    		@PathVariable("itemId") int itemId) {
	        reportDao.editItem(itemId, reportProductShow);
	        
	        return "redirect:/reports/" + Integer.toString(reportProductShow.getShiftId(itemId));
	    }
	
	 @GetMapping("/{shiftId}/add")
	 public String addItem(Model model, @PathVariable("shiftId") int shiftId) {
		 model.addAttribute("products", productDao.showMissProducts(shiftId, reportDao.show(shiftId)));
		 model.addAttribute("item", new ReportProductShow());
		 model.addAttribute("report", reportDao.show(shiftId));
		 return "reports/addItem";
	 }
	 
	 @PostMapping("/{shiftId}/add")
	    public String addItem(@ModelAttribute("item") ReportProductShow reportProductShow, 
	    		@PathVariable("shiftId") int shiftId) {
	        reportDao.addItem(shiftId, reportProductShow);
	        
	        return "redirect:/reports/" + shiftId;
	    }
	
	 @GetMapping("/deleteItem/{itemId}")
		public String deleteItem(Model model, @PathVariable("itemId") int itemId) {
			model.addAttribute("item", reportDao.showItem(itemId));
			//System.out.println(reportDao.showItem(itemId).getReportId());
			//model.addAttribute("products", productDao.index());
			return "reports/deleteItem";
		}
		
	 @PostMapping("/delete/{itemId}")
		    public String deleteItem(@PathVariable("itemId") int itemId) {
		 	int rps = reportDao.showItem(itemId).getShiftId(itemId);
		        reportDao.deleteItem(itemId);
		        
		        return "redirect:/reports/" + Integer.toString(rps);
		    }
	 
	 
	 // add cashDesk expenses
	 
	 @GetMapping("/{shiftId}/addCashDesk")
	 public String addCashDesk(Model model, @PathVariable("shiftId") int shiftId) {
		
		 model.addAttribute("expense", new Expense());
		 model.addAttribute("report", reportDao.show(shiftId));
		 return "reports/cashDesk/add";
	 }
	 
	 @PostMapping("/{shiftId}/addCashDesk")
	    public String addCashDesk(@ModelAttribute("item") Expense expense, 
	    		@PathVariable("shiftId") int shiftId) {
		 	expense.setExpCategory("Касса");
	        reportDao.addExpense(shiftId, expense);
	        
	        return "redirect:/reports/" + shiftId;
	    }
	

		@GetMapping("/editCashDesk/{cashDeskId}")
		public String editCashDesk(Model model, @PathVariable("cashDeskId") int cashDeskId) {
			Expense expense = reportDao.showExpense(cashDeskId);
			int shiftId = expense.getShiftId();			
			model.addAttribute("expense", expense);
			model.addAttribute("report", reportDao.show(shiftId));
			
			//System.out.println(reportDao.showItem(itemId).getReportId());
			
			return "reports/cashDesk/edit";
		}
		
		 @PostMapping("/editCashDesk/{itemId}")
		    public String updateCashDesk(@ModelAttribute("item") Expense expense, 
		    		@PathVariable("itemId") int itemId) {
		        reportDao.editExpense(expense);
		        
		        return "redirect:/reports/" + expense.getShiftId();
		    }
	
			@GetMapping("/deleteCashDesk/{cashDeskId}")
			public String deleteCashDesk(Model model, @PathVariable("cashDeskId") int cashDeskId) {
				Expense expense = reportDao.showExpense(cashDeskId);
				int shiftId = expense.getShiftId();			
				model.addAttribute("expense", expense);
				model.addAttribute("report", reportDao.show(shiftId));
				
				//System.out.println(reportDao.showItem(itemId).getReportId());
				
				return "reports/cashDesk/delete";
			}
			
			 @PostMapping("/deleteCashDesk/{itemId}")
			    public String deleteCashDesk(@ModelAttribute("item") Expense expense, 
			    		@PathVariable("itemId") int itemId) {
			        reportDao.deleteExpense(expense);
			        
			        return "redirect:/reports/" + expense.getShiftId();
			    }	 
			 // add write-off expenses
			 
			 @GetMapping("/{shiftId}/addWriteOff")
			 public String addWriteOff(Model model, @PathVariable("shiftId") int shiftId) {
				
				 model.addAttribute("expense", new Expense());
				 model.addAttribute("report", reportDao.show(shiftId));
				 return "reports/cashDesk/addWriteOff";
			 }
			 
			 @PostMapping("/{shiftId}/addWriteOff")
			    public String addWriteOff(@ModelAttribute("item") Expense expense, 
			    		@PathVariable("shiftId") int shiftId) {
				 	expense.setExpCategory("Списание");
			        reportDao.addExpense(shiftId, expense);
			        
			        return "redirect:/reports/" + shiftId;
			    }
			 // add costs expenses
			 
			 @GetMapping("/{shiftId}/addCosts")
			 public String addCosts(Model model, @PathVariable("shiftId") int shiftId) {
				
				 model.addAttribute("expense", new Expense());
				 model.addAttribute("report", reportDao.show(shiftId));
				 return "reports/cashDesk/addCosts";
			 }
			 
			 @PostMapping("/{shiftId}/addCosts")
			    public String addCosts(@ModelAttribute("item") Expense expense, 
			    		@PathVariable("shiftId") int shiftId) {
				 	expense.setExpCategory("Расходы");
			        reportDao.addExpense(shiftId, expense);
			        
			        return "redirect:/reports/" + shiftId;
			    }
			
/*
				@GetMapping("/editWriteOff/{cashDeskId}")
				public String editWriteOff(Model model, @PathVariable("cashDeskId") int cashDeskId) {
					Expense expense = reportDao.showExpense(cashDeskId);
					int shiftId = expense.getShiftId();			
					model.addAttribute("expense", expense);
					model.addAttribute("report", reportDao.show(shiftId));
					
					//System.out.println(reportDao.showItem(itemId).getReportId());
					
					return "reports/cashDesk/edit";
				}
				
				 @PostMapping("/editWriteOff/{itemId}")
				    public String updateWriteOff(@ModelAttribute("item") Expense expense, 
				    		@PathVariable("itemId") int itemId) {
				        reportDao.editExpense(expense);
				        
				        return "redirect:/reports/" + expense.getShiftId();
				    }
			
					@GetMapping("/deleteWriteOff/{cashDeskId}")
					public String deleteWriteOff(Model model, @PathVariable("cashDeskId") int cashDeskId) {
						Expense expense = reportDao.showExpense(cashDeskId);
						int shiftId = expense.getShiftId();			
						model.addAttribute("expense", expense);
						model.addAttribute("report", reportDao.show(shiftId));
						
						//System.out.println(reportDao.showItem(itemId).getReportId());
						
						return "reports/cashDesk/delete";
					}
					
					 @PostMapping("/deleteWriteOff/{itemId}")
					    public String deleteWriteOff(@ModelAttribute("item") Expense expense, 
					    		@PathVariable("itemId") int itemId) {
					        reportDao.deleteExpense(expense);
					        
					        return "redirect:/reports/" + expense.getShiftId();
					    }	 
*/
}
