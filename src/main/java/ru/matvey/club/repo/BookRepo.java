package ru.matvey.club.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.matvey.club.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

}
