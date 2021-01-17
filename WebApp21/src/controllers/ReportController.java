package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.ProductDao;
import dao.ReportDao;
import models.reports.ReportProductShow;
import models.reports.ReportShow;

@Controller
@RequestMapping("/reports")
public class ReportController {
	
	ReportDao reportDao = new ReportDao();
	ProductDao productDao = new ProductDao();
	
	
	@GetMapping("/{shiftId}")
	public String showShiftReport(@PathVariable("shiftId") int shiftId, Model model) {
		ReportShow report = reportDao.show(shiftId);
		model.addAttribute("report", report);
		model.addAttribute("productList", report.getProducts());
		
		return "reports/showShiftReport";
	}
	
	@GetMapping("/editItem/{itemId}")
	public String editItem(Model model, @PathVariable("itemId") int itemId) {
		model.addAttribute("item", reportDao.showItem(itemId));
		//System.out.println(reportDao.showItem(itemId).getReportId());
		model.addAttribute("products", productDao.index());
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

}
