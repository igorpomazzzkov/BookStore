package Book.controller;

import Book.entity.Author;
import Book.entity.Book;
import Book.repository.AuthorRepository;
import Book.repository.BookRepository;
import Book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

@Controller
public class AuthorController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/book/author={author_id}")
    public String bookByAuthor(
            @PathVariable("author_id") long id,
            Model model,
            @PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC, value = 5) Pageable pageable
    ) {
        Author author = authorRepository.findById(id);
        Page<Book> books = bookService.findAllByAuthorOrderByName(author, pageable);
        model.addAttribute("books", books);
        model.addAttribute("url", "/book/author={author_id}");
        System.out.println();
        return "home";
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @GetMapping("/author")
    public String getAuthor(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "author";
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @GetMapping("/author/delete={id}")
    public String getAuthor(@PathVariable("id") Author author, Model model) {
        authorRepository.delete(author);
        model.addAttribute("authors", authorRepository.findAll());
        return "author";
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @PostMapping("/author/add")
    public String addAuthor(Model model,
                            @Valid Author author,
                            BindingResult bindingResult) {
        Date dateOfBirth = author.getDateOfBirth();
        Date dateOfDeath = author.getDateOfDeath();
        System.out.println(dateOfBirth);
        System.out.println(dateOfDeath);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("authors", authorRepository.findAll());
            return "author";
        }
        authorRepository.save(author);
        return "redirect:/author";
    }
}
