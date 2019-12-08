package Book.controller;

import Book.entity.Book;
import Book.entity.Category;
import Book.repository.CategoryRepository;
import Book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String mainPage() {
        return "redirect:/book";
    }

    public Model getCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return model;
    }

    @GetMapping("/book")
    public String homePage(Model model,
                           @PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC, value = 5) Pageable pageable) {
        Page<Book> books = bookService.findAllByOrderByName(pageable);
        model.addAttribute("books", books);
        model.addAttribute("url", "/book");
        getCategories(model);
        return "home";
    }

    @GetMapping("/book/book={id}")
    public String bookById(@PathVariable("id") int id,
                           Model model) {
        Book book = bookService.findByIdOrderByName(id);
        model.addAttribute("book", book);
        List<Category> categories = book.getCategories();
        model.addAttribute("bookCategories", categories);
        return "showBook";
    }

    @RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String search(Model model,
                         @PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC, value = 5) Pageable pageable,
                         @RequestParam(required = false) Map<String, String> form) {
        boolean isBlankForm = false;
        for (Map.Entry<String, String> element : form.entrySet()) {
            if (!element.getKey().equals("_csrf") && !element.getValue().isBlank()) {
                isBlankForm = true;
            }
        }
        if (isBlankForm) {
            Page<Book> books = bookService.findBooksByName(form, pageable);
            if (books.getNumberOfElements() == 0) {
                model.addAttribute("messageType", "danger");
                model.addAttribute("message", "Ничего не найдено");
            } else {
                model.addAttribute("messageType", "success");
                model.addAttribute("message", "Результат поиска");
            }
            model.addAttribute("books", books);
            model.addAttribute("url", "/search");
            getCategories(model);
            return "/homeSearch";
        }
        return "redirect:/book";
    }
}
