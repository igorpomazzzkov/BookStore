package Book.controller;

import Book.entity.Role;
import Book.entity.User;
import Book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEdit(User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @ModelAttribute("user") User userForm,
            @RequestParam Map<String, String> form,
            @RequestParam("usedId") User user) {
        userService.saveUser(user, userForm, form);
        return "redirect:/user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("deleteUser/{id}")
    public String deleteUsers(@PathVariable("id") long id){
        userService.deleteUser(id);
        return "redirect:/user";
    }

    @GetMapping("profile")
    public String profile(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                      @RequestParam String password,
                      @RequestParam String email,
                      @RequestParam String firstName,
                      @RequestParam String lastName){
        userService.updateProfile(user, password, email, firstName, lastName);
        return "redirect:/user/profile";
    }
}
