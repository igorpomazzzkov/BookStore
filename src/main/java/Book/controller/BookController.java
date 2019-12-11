package Book.controller;

import Book.entity.Author;
import Book.entity.Book;
import Book.entity.Category;
import Book.entity.Publishing;
import Book.repository.AuthorRepository;
import Book.repository.CategoryRepository;
import Book.repository.PublishingRepository;
import Book.service.BookService;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublishingRepository publishingRepository;

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

    @Transactional
    @PreAuthorize("hasAuthority('EDITOR')")
    @GetMapping("/bookDelete={id}")
    public String deleteBook(@PathVariable long id, Model model) {
        System.out.println("DELETE");
        bookService.deleteBook(id);
        return "redirect:/book";
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @GetMapping("/bookEdit/id={id}")
    public String editBook(@PathVariable long id, Model model) {
        Book book = bookService.findBooksById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("publishing", publishingRepository.findAll());
        getCategories(model);
        return "editBook";
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @RequestMapping(value = "/addBook/id={id}", method = RequestMethod.POST)
    public String addCategoryForBook(@PathVariable long id,
                                     @RequestParam Map<String, String> form) {
        Book book = bookService.findBooksById(id);
        System.out.println(form.get("author"));
        if (!form.get("category").isBlank()) {
            Category category = categoryRepository.findAllByName(form.get("category"));
            book.getCategories().add(category);
        }
        if (!form.get("name").isBlank()) {
            book.setName(form.get("name"));
        }
        if (!form.get("publishing").isBlank()) {
            Publishing publishing = publishingRepository.findAllByName(form.get("publishing"));
            book.setPublishing(publishing);
        }
        if (!form.get("price").isBlank()) {
            book.setPrice(Double.parseDouble(form.get("price")));
        }
        if (!form.get("pages").isBlank()) {
            book.setCountOfPage(Integer.parseInt(form.get("pages")));
        }
        if (!form.get("author").isBlank()) {
            Author author = authorRepository.findById(Integer.parseInt(form.get("author")));
            book.setAuthor(author);
        }
        book.setText(form.get("text"));
        bookService.addBook(book);
        return "redirect:/bookEdit/id=" + id + "";
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @GetMapping("/deleteCategory={name}byBook={id}")
    public String deleteCategory(@PathVariable String name,
                                 @PathVariable long id) {
        Category category = categoryRepository.findAllByName(name);
        Book book = bookService.findBooksById(id);
        book.getCategories().remove(category);
        return "redirect:/bookEdit/id=" + id + "";
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("publishing", publishingRepository.findAll());
        getCategories(model);
        return "addBook";
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @PostMapping("/bookSave")
    public String addBook(@RequestParam Map<String, String> form, @RequestParam("filename") MultipartFile file) {
        Date date = null;
        if (!form.get("dateOfPublication").isBlank()) {
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(form.get("dateOfPublication"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Book book = new Book(
                form.get("name"),
                Double.parseDouble(form.get("price")),
                Integer.parseInt(form.get("pages")),
                authorRepository.findById(Integer.parseInt(form.get("author"))),
                publishingRepository.findAllByName(form.get("publishing")),
                form.get("text"),
                date
        );
        try {
            String resultFile = getFileName(file);
            if(!resultFile.isBlank()){
                book.setFilename(resultFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!form.get("category").isBlank()) {
            book.getCategories().add(categoryRepository.findAllByName(form.get("category")));
        }
        bookService.addBook(book);
        return "redirect:/book";
    }

    public String getFileName(MultipartFile file) throws IOException {
        if (file != null) {
            File fileUpload = new File(uploadPath);
            if (!fileUpload.exists()) {
                fileUpload.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            return resultFilename;
        }
        return "";
    }
}
