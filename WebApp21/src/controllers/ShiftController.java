package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.SellerDao;
import dao.ShiftDao;
import models.shifts.Shift;
import models.shifts.ShiftQuery;

@Controller
@RequestMapping("/shifts")
public class ShiftController {
	
	ShiftDao shiftDao = new ShiftDao();
	SellerDao sellerDao = new SellerDao();
	ShiftQuery shiftQuery = new ShiftQuery();
	
	@GetMapping("/")
	public String index() {
		return "shifts/index";
	}
	
	@GetMapping("/all")
	public String allShifts(Model model) {
		model.addAttribute("shiftIndex", shiftDao.index());
		return "shifts/allShifts";
	}
	
	@GetMapping("/new")
	public String newShift(Model model) {
		
		model.addAttribute("seller", sellerDao.index());
		model.addAttribute("shift", new Shift());
		return "shifts/new";
	}
	
	@PostMapping("/")
	public String create(@ModelAttribute("shift") Shift shift) {
		shiftDao.save(shift);
		return "redirect:/shifts/all";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("shift", shiftDao.show(id));
		
		return "shifts/show";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("shift", shiftDao.show(id));
		model.addAttribute("seller", sellerDao.index());
		return "shifts/edit";
	}
	
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable("id") int id) {
		model.addAttribute("shift", shiftDao.show(id));
		int sellerId = shiftDao.show(id).getSellerId();
		model.addAttribute("seller", sellerDao.show(sellerId));
		return "shifts/delete";
	}
	
	 @PostMapping("/{id}")
	    public String update(@ModelAttribute("shift") Shift shift, @PathVariable("id") int id) {
	        shiftDao.update(id, shift);
	        return "redirect:/shifts/all";
	    }
	
	@PostMapping("/delete/{id}")
	public String delete(@ModelAttribute("shift") Shift shift, @PathVariable("id") int id) {
		shiftDao.delete(id);
		return "redirect:/shifts/all";
	}
	
	@GetMapping("/search")
	public String searchShift(Model model) {
		model.addAttribute("shiftQuery", this.shiftQuery);
		return "shifts/search";
	}
	
	@PostMapping("/search")
	public String postQuery(@ModelAttribute("shiftQuery") ShiftQuery shiftQuery) {		
		//model.addAttribute("shiftQuery", new ShiftQuery());
		shiftDao.setShiftQuery(shiftQuery);
		return "redirect:/shifts/search/result";
	}
	
	@GetMapping("/search/result")
	public String showResultQuery(Model model) {
		//System.out.println("GetMapping query begin,end" + this.shiftQuery.getShiftBegin() + this.shiftQuery.getShiftEnd());
		
		model.addAttribute("shiftIndex", shiftDao.search());
		return "shifts/allShifts";
	}

}

