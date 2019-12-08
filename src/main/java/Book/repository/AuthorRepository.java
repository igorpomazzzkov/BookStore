package Book.repository;

import Book.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findById(long id);

//    @Query(value = "SELECT a FROM Author a WHERE UPPER(a.firstName) LIKE CONCAT ('%',UPPER(:firstName),'%') OR UPPER(a.lastName) LIKE CONCAT('%', UPPER(:firstName),'%')")
//    List<Author> findAuthorByFirstName(String firstName);
}
