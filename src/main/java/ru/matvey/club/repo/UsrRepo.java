package ru.matvey.club.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.matvey.club.entity.Usr;

import java.util.Optional;

@Repository
public interface UsrRepo extends JpaRepository<Usr, Long> {
    Optional<Usr> findByUsrname(String usrname);
    //!!! возможно нужно поменять на User пока хз проверь будет ли работать
}
