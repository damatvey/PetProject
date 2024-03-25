package ru.matvey.club.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "review")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Review extends AbstractEntity {
    @Column(name = "likes_amount")
    private Integer likesAmount;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "review_text", nullable = false)
    private String reviewText;

    @ManyToOne(fetch = FetchType.EAGER)
    //к рецензии на книгу мы всегда хотим знать
    //автора рецензии значит исп. EAGER

    //one to many - нельзя потому что онда рецензия
    //не может быть у многих пользователей
    //значит many to one
    @JoinColumn(name = "id_usr") //говорим, что то что ниже из таблицы юзер
    private Usr usr;

    @ManyToOne(fetch = FetchType.EAGER)
    //может быть много(many) рецензий у одной(one) книги
    @JoinColumn(name = "id_book")
    private Book book;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "review", cascade = CascadeType.ALL)
    private List<Comment> comments;


}
