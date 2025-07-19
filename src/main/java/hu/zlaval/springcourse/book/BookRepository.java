package hu.zlaval.springcourse.book;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository //for errors to DataAccessException
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
