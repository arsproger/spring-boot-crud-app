package com.arsen.controllers;
import com.arsen.models.Person;
import com.arsen.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personService.findAll());
        return "people-index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable long id, Model model) {
        model.addAttribute("person", personService.findById(id));
        return "people-show";
    }


    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people-new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        personService.newPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("person", personService.findById(id));
        return "people-edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") long id) {
        personService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        personService.delete(id);
        return "redirect:/people";
    }
}
