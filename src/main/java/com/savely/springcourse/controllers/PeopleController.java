package com.savely.springcourse.controllers;

import com.savely.springcourse.dao.PersonDao;
import com.savely.springcourse.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {


    private final PersonDao personDao;

    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String getAllPeoples(Model model) {
        model.addAttribute("people", personDao.getPeople());
        return "people";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.getPerson(id));
        return "person";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "create-person";
    }

    @PostMapping()
    public String savePerson(@ModelAttribute("person") Person person) {
        personDao.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.getPerson(id));
        return "edit-person";
    }

    @PostMapping("/{id}")
    public String updatePerson(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        personDao.updatePerson(id, person);
        return "redirect:/people/" + id;
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        personDao.deletePerson(id);
        return "redirect:/people";
    }
}
