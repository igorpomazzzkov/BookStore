package Book.repository;

import Book.entity.Author;
import Book.entity.Book;
import Book.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByOrderByName(Pageable pageable);

    Book findByIdOrderByName(long id);

    Page<Book> findAllByAuthorOrderByName(Author author, Pageable pageable);

    Page<Book> findBooksByUsers(User user, Pageable pageable);

    @Query(value = "SELECT b FROM Book b WHERE " +
            "UPPER(b.name) LIKE CONCAT ('%',UPPER(:nameOfBook),'%') AND " +
            "(UPPER(b.author.firstName) LIKE CONCAT ('%',UPPER(:author),'%') OR UPPER(b.author.lastName) LIKE CONCAT('%',UPPER(:author),'%')) AND " +
            "(b.price >= :priceMin AND b.price <= :priceMax)")
    Page<Book> findBooksByName(String nameOfBook,
                               String author,
                               Double priceMin,
                               Double priceMax,
                               Pageable pageable);
}