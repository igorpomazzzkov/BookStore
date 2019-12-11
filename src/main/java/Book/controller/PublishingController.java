package Book.controller;

import Book.entity.Publishing;
import Book.repository.PublishingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/publish")
public class PublishingController {

    @Autowired
    private PublishingRepository publishingRepository;

    @PreAuthorize("hasAuthority('EDITOR')")
    @GetMapping
    public String getPublishingPage(Model model){
        model.addAttribute("publishing", publishingRepository.findAll());
        return "publish";
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @Transactional
    @GetMapping("/deletePublish={id}")
    public String deletePublishing(@PathVariable long id){
        publishingRepository.deleteById(id);
        return "redirect:/publish";
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @PostMapping("/add")
    public String addPublishHouse(@Valid Publishing publishing, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
        }else{
            Publishing publishingFromDB = publishingRepository.findAllByName(publishing.getName().trim());
            if(publishingFromDB != null){
                model.addAttribute("nameError","publishing exists");
            }else{
                publishingRepository.save(publishing);
                return "redirect:/publish";
            }
        }
        model.addAttribute("publishing", publishingRepository.findAll());
        return "publish";
    }
}
