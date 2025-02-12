package ru.kata.spring.boot_security.demo.controller;

import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    // Страница со списком пользователей
    @GetMapping
    public String adminPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";  // шаблон admin.html
    }

    // Отображение формы для создания нового пользователя
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleRepository.findAll());
        return "create_user";  // шаблон create_user.html
    }

    // Обработка формы создания пользователя
    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user, @RequestParam("roles") List<Long> roleIds) {
        Set<ru.kata.spring.boot_security.demo.model.Role> roles = new HashSet<>(roleRepository.findAllById(roleIds));
        user.setRoles(roles);
        userService.createUser(user);
        return "redirect:/admin";
    }

    // Отображение формы редактирования пользователя
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/admin";
        }
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleRepository.findAll());
        return "edit_user";  // шаблон edit_user.html
    }

    // Обработка обновления пользователя
    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("roles") List<Long> roleIds) {
        Set<ru.kata.spring.boot_security.demo.model.Role> roles = new HashSet<>(roleRepository.findAllById(roleIds));
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    // Удаление пользователя
    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
