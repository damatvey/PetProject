package ru.matvey.club.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matvey.club.entity.Review;

import java.util.Optional;

public interface ReviewRepo extends JpaRepository<Review, Long> {

}
