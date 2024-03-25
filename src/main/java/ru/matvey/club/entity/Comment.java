package ru.matvey.club.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Comment extends AbstractEntity {
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "comment_text", nullable = false)
    private String commentText;
    @Column(name = "likes_amount", nullable = false)
    private Integer likesAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usr")
    private Usr usr;
    //возможная ошибка! нужно тут ставить лэзи ли нет?
    //нужны ли нам все рицензии?
    //но у нас же может быть только одна рецензия
    //что будет если написать снизу eager?

    @ManyToOne(fetch = FetchType.LAZY)
    //у одной рецензии может быть много комментариев
    @JoinColumn(name = "id_review")
    private Review review;
}
