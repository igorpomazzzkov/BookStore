package Book.service;

import Book.entity.Author;
import Book.entity.Book;
import Book.entity.User;
import Book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Page<Book> findBooksByUsers(User user, Pageable pageable){
        return bookRepository.findBooksByUsers(user, pageable);
    }

    public Page<Book> findBooksByName(String name, Pageable pageable){
        return bookRepository.findBooksByName(name, pageable);
    }

    public Page<Book> findAllByAuthorOrderByName(Author author, Pageable pageable){
        return bookRepository.findAllByAuthorOrderByName(author, pageable);
    }

    public Book findByIdOrderByName(long id){
        return bookRepository.findByIdOrderByName(id);
    }

    public Page<Book> findAllByOrderByName(Pageable pageable){
        return bookRepository.findAllByOrderByName(pageable);
    }
}
