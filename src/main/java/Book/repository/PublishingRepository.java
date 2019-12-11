package Book.repository;

import Book.entity.Publishing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PublishingRepository extends JpaRepository<Publishing, Long> {
    Publishing findAllByName(String name);

    @Modifying
    @Query("DELETE FROM Publishing p WHERE p.id = :id")
    void deletePublishingById(long id);
}
