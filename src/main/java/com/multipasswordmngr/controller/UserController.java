package com.multipasswordmngr.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.multipasswordmngr.domain.User;
import com.multipasswordmngr.repository.PasswordManagerRepo;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

@Controller
public class UserController {
    @Autowired
    private PasswordManagerRepo repository;

    @GetMapping("/users")
    public String getAll(Model model, @Param("keyword") String keyword) {
        try {
            List<User> users = new ArrayList<User>();
           
            if (keyword == null) {
                repository.findAll().forEach(users::add);
            } else {
                if (keyword.equals("masterPassword")) {
                    return "redirect:/users?masterMode";
                } else {
                    repository.findByNameContainingIgnoreCase(keyword).forEach(users::add);
                    model.addAttribute("keyword", keyword);
                }
            }

            model.addAttribute("users", users);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "users";
    }

    @GetMapping("/users/new")
    public String addUser(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Create new User");
        return "userCreateForm";
    }

    @PostMapping("/users/save")
    public String saveUser(@Valid @ModelAttribute("user") User user, @Param("password") String password,
            @Param("repeatPassword") String repeatPassword,
            RedirectAttributes redirectAttributes) {
        try {
            if (!password.equals(repeatPassword)) {
                return "redirect:/users/new?passDoNotMatch";
            } else {
                user.setOriginalPassword(password);
                repository.save(user);
                redirectAttributes.addFlashAttribute("message", "The User has been saved successfully!");
            }
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = repository.getReferenceById(id);

            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

            return "userCreateForm";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/users";
        }
    }

    @GetMapping("/users/copy/{id}")
    public String copyToClipBoard(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        StringSelection stringSelection = new StringSelection(repository.getReferenceById(id).getOriginalPassword());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        redirectAttributes.addFlashAttribute("message",
                "You have copied the password successfully!");

        return "redirect:/users";
    }
    

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            repository.deleteById(id);

            redirectAttributes.addFlashAttribute("message",
                    "The User with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/users";
    }
}
