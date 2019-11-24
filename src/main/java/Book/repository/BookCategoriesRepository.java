package Book.repository;

import Book.entity.Book;
import Book.entity.BookCategories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCategoriesRepository extends JpaRepository<BookCategories, Long> {
    List<BookCategories> findByBook(Book book);
}
