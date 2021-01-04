package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dao.PersonDao;
import models.Person;

@Controller

public class PeopleController {
	
	
	 PersonDao personDao= new PersonDao();;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("people", personDao.index());
		return "people/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("person", personDao.show(id));
		
		return "people/show";
	}
	
	@GetMapping("/new")
	public String newPerson(Model model) {
		model.addAttribute("person", new Person());
		return "people/new";
	}
	
	@PostMapping("/")
	public String create(@ModelAttribute("person") Person person) {
		personDao.save(person);
		return "redirect:/";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("person", personDao.show(id));
		return "people/edit";
	}
	
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable("id") int id) {
		model.addAttribute("person", personDao.show(id));
		return "people/delete";
	}
	
	 @PostMapping("/{id}")
	    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
	        personDao.update(id, person);
	        return "redirect:/";
	    }
	
	@PostMapping("/delete/{id}")
	public String delete(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
		personDao.delete(id);
		return "redirect:/";
	}
}
