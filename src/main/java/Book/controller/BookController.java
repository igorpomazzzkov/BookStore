package Book.controller;

import Book.entity.Book;
import Book.entity.BookCategories;
import Book.entity.Category;
import Book.repository.BookCategoriesRepository;
import Book.repository.BookRepository;
import Book.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCategoriesRepository bookCategoriesRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String mainPage(Model model) {
        return "redirect:/book";
    }

    @GetMapping("/book")
    public String homePage(Map<String, Object> model,
                           @PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC ,value = 5) Pageable pageable) {
        Page<Book> books = bookRepository.findAllByOrderByName(pageable);
        model.put("books", books);
        model.put("url", "/book");
        List<Category> categories = categoryRepository.findAll();
        model.put("categories", categories);
        return "home";
    }

    @GetMapping("/book/book={id}")
    public String bookById(@PathVariable("id") int id,
                           Map<String, Object> model) {
        Book book = bookRepository.findByIdOrderByName(id);
        model.put("book", book);
        List<BookCategories> categories = bookCategoriesRepository.findByBook(book);
        model.put("bookCategories", categories);
        return "showBook";
    }
}
