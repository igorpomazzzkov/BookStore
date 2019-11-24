package Book.controller;

import Book.entity.User;
import Book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    @GetMapping("/logout")
    public String logout(){
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("passwordToo") String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            Model model){
        boolean isConfirmEmpty = passwordConfirm.isEmpty();
        if(passwordConfirm.isEmpty()){
            model.addAttribute("passwordTooError", "Password confirmation is null");
        }

        boolean isDifferentPassword = user.getPassword().equals(passwordConfirm);
        if(user.getPassword() != null && !user.getPassword().equals(passwordConfirm)){
            model.addAttribute("passwordTooError", "Password are different");
        }

        if(!isDifferentPassword || isConfirmEmpty || bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("user",null);
            return "registration";
        }

        if(!userService.addUser(user)){
            model.addAttribute("usernameError", "user exists!");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);
        if(isActivated){
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated");
        }else{
            model.addAttribute("messageType", "secondary");
            model.addAttribute("message", "Activation code is not found");
        }
        return "login";
    }
}
