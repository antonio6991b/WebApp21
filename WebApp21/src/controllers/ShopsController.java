package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.ShopDao;
import models.Shop;

@Controller
@RequestMapping("/shops")
public class ShopsController {
	ShopDao shopDao = new ShopDao();
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("shop", shopDao.index());
		return "shops/index";
	}
	
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") String id, Model model) {
		
		model.addAttribute("shop", shopDao.show(id));
		
		return "shops/show";
	}
	
	@GetMapping("/new")
	public String newPerson(Model model) {
		model.addAttribute("shop", new Shop());
		return "shops/new";
	}
	
	@PostMapping("/")
	public String create(@ModelAttribute("shop") Shop shop) {
		shopDao.save(shop);
		return "redirect:/shops/";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") String id) {
		model.addAttribute("shop", shopDao.show(id));
		return "shops/edit";
	}
	
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable("id") String id) {
		model.addAttribute("shop", shopDao.show(id));
		return "shops/delete";
	}
	
	 @PostMapping("/{id}")
	    public String update(@ModelAttribute("shop") Shop shop, @PathVariable("id") String id) {
	        shopDao.update(id, shop);
	        return "redirect:/shops/";
	    }
	
	@PostMapping("/delete/{id}")
	public String delete(@ModelAttribute("shop") Shop shop, @PathVariable("id") String id) {
		shopDao.delete(id);
		return "redirect:/shops/";
	}
	
}
