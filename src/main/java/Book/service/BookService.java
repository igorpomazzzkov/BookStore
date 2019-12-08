package Book.service;

import Book.entity.Author;
import Book.entity.Book;
import Book.entity.Category;
import Book.entity.User;
import Book.repository.AuthorRepository;
import Book.repository.BookRepository;
import Book.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private static Map<String, String> searchForm;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public Page<Book> findBooksByUsers(User user, Pageable pageable) {
        return bookRepository.findBooksByUsers(user, pageable);
    }

    public Page<Book> findBooksByName(Map<String, String> form, Pageable pageable) {
        searchForm = form.get("nameOfBook") != null ? form : searchForm;
        Double priceMin = !searchForm.get("priceMin").isBlank() ? Double.parseDouble(searchForm.get("priceMin")) : 0;
        Double priceMax = !searchForm.get("priceMax").isBlank() ? Double.parseDouble(searchForm.get("priceMax")) : 10000;
        Page<Book> books = bookRepository.findBooksByName(form.get("nameOfBook"),
                searchForm.get("nameOfAuthor"),
                priceMin,
                priceMax,
                pageable);
        Category category = categoryRepository.findAllByName(searchForm.get("genre"));
        List<Book> bookList = new ArrayList<Book>();
        for(Book book: books){
            if(book.getCategories().contains(category)){
                bookList.add(book);
            }
        }
        if(!bookList.isEmpty() || !searchForm.get("genre").isBlank()){
            return new PageImpl<Book>(bookList, pageable, bookList.size());
        }
        return books;
    }

    public Page<Book> findAllByAuthorOrderByName(Author author, Pageable pageable) {
        return bookRepository.findAllByAuthorOrderByName(author, pageable);
    }

    public Book findByIdOrderByName(long id) {
        return bookRepository.findByIdOrderByName(id);
    }

    public Page<Book> findAllByOrderByName(Pageable pageable) {
        return bookRepository.findAllByOrderByName(pageable);
    }
}
