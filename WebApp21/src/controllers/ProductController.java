package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.ProductDao;
import models.Product;

@Controller
@RequestMapping("/products")
public class ProductController {
	ProductDao productDao = new ProductDao();
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("product", productDao.index());
		return "products/index";
	}
	
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("product", productDao.show(id));
		
		return "products/show";
	}
	
	@GetMapping("/new")
	public String newPerson(Model model) {
		model.addAttribute("product", new Product());
		return "products/new";
	}
	
	@PostMapping("/")
	public String create(@ModelAttribute("product") Product product) {
		productDao.save(product);
		return "redirect:/products/";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("product", productDao.show(id));
		return "products/edit";
	}
	
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable("id") int id) {
		model.addAttribute("product", productDao.show(id));
		return "products/delete";
	}
	
	 @PostMapping("/{id}")
	    public String update(@ModelAttribute("product") Product product, @PathVariable("id") int id) {
	        productDao.update(id, product);
	        return "redirect:/products/";
	    }
	
	@PostMapping("/delete/{id}")
	public String delete(@ModelAttribute("product") Product product, @PathVariable("id") int id) {
		productDao.delete(id);
		return "redirect:/products/";
	}
	
}
