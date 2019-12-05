package Book.controller;

import Book.entity.Book;
import Book.entity.User;
import Book.service.BookService;
import Book.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {

    private final BookService bookService;

    private final UserService userService;

    public CartController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/cart")
    public String cart(Model model,
                       @AuthenticationPrincipal User user,
                       @PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC, value = 5) Pageable pageable) {
        Page<Book> books = bookService.findBooksByUsers(user, pageable);
        model.addAttribute("books", books);
        model.addAttribute("url", "/cart");
        return "homeCart";
    }

    @PostMapping("/addToCart/{book_id}")
    public String addToCart(
                            @PathVariable("book_id") Book book,
                            @AuthenticationPrincipal User user)
    {
        user = userService.find(user.getUsername());
        userService.addToCart(user, book);
        return "redirect:/cart";
    }

    @PostMapping("/removeFromCart/{book_id}")
    public String removeFromCart(
            @PathVariable("book_id") Book book,
            @AuthenticationPrincipal User user
    ){
        user = userService.find(user.getUsername());
        userService.removeFromCart(user, book);
        return "redirect:/cart";
    }
}
