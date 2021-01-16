package controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dao.SellerDao;
import dao.ShopDao;
import models.Seller;


@Controller
@RequestMapping("/sellers")
public class SellersController {
	SellerDao sellerDao = new SellerDao();
	ShopDao shopDao = new ShopDao();
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("seller", sellerDao.index());
		return "sellers/index";
	}
	
	
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("seller", sellerDao.show(id));
		
		return "sellers/show";
	}
	
	@GetMapping("/new")
	public String newPerson(Model model) {
		model.addAttribute("seller", new Seller());
		model.addAttribute("shop", shopDao.index());
		return "sellers/new";
	}
	
	@PostMapping("/")
	public String create(@ModelAttribute("person") Seller seller) {
		sellerDao.save(seller);
		return "redirect:/sellers/";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("seller", sellerDao.show(id));
		model.addAttribute("shop", shopDao.index());
		return "sellers/edit";
	}
	
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable("id") int id) {
		model.addAttribute("seller", sellerDao.show(id));
		return "sellers/delete";
	}
	
	 @PostMapping("/{id}")
	    public String update(@ModelAttribute("seller") Seller seller, @PathVariable("id") int id) {
	        sellerDao.update(id, seller);
	        return "redirect:/sellers/";
	    }
	
	@PostMapping("/delete/{id}")
	public String delete(@ModelAttribute("seller") Seller seller, @PathVariable("id") int id) {
		sellerDao.delete(id);
		return "redirect:/sellers/";
	}
	
}
