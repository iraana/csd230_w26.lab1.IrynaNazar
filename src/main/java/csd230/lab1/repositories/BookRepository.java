package csd230.lab1.repositories;

import csd230.lab1.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    // Required derived queries
    List<BookEntity> findByIsbn(String isbn);

    BookEntity findById(long id);

    // Like query
    List<BookEntity> findByTitleLike(String title);

    // Custom @Query
    @Query("""
        SELECT b FROM BookEntity b
        WHERE b.price BETWEEN :minPrice AND :maxPrice
    """)
    List<BookEntity> findBooksInPriceRange(double minPrice, double maxPrice);
}