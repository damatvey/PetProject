package ru.matvey.club.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.matvey.club.entity.Review;

import java.util.Optional;
@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {

}
