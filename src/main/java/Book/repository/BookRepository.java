package Book.repository;

import Book.entity.Author;
import Book.entity.Book;
import Book.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByOrderByName(Pageable pageable);
    Book findByIdOrderByName(long id);
    Page<Book> findAllByAuthorOrderByName(Author author, Pageable pageable);
    Page<Book> findBooksByUsers(User user, Pageable pageable);
}
