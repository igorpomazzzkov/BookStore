package Book.controller;

import Book.entity.Author;
import Book.entity.Book;
import Book.repository.AuthorRepository;
import Book.repository.BookRepository;
import Book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    ){
        Author author = authorRepository.findById(id);
        Page<Book> books = bookService.findAllByAuthorOrderByName(author, pageable);
        model.addAttribute("books", books);
        model.addAttribute("url","/book/author={author_id}");
        System.out.println();
        return "home";
    }
}
