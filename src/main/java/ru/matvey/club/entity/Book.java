package ru.matvey.club.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Book extends AbstractEntity {
    @Column(name = "name", length = 250)
    private String name;
    @Column(name = "date_of_writing", nullable = false)
    private LocalDate dateOfWriting;
    @Column(name = "thumbnail_url", length = 250)
    private String thumbnailUrl;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book", cascade = CascadeType.ALL)
    //чтобы была возможность создать в будущем
    //поиск рецензий по книге
    private List<Review> reviews;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    //одна  BOOK много usrs которые ее читают
    //при удалении книги мы не хоитм удалять всех пользователей
    //у которых эта книга в процессе
    //поэтому не ставим cascade = CascadeType.ALL
    private List<Usr> usrs;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_author")
    //когда мы выбираем книгу хотим ли мы видеть автора?
    //да, хотим, поэтому игр
    //у одного автора может быть много книг
    private Author author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "many_book_has_many_genre",
            joinColumns = @JoinColumn(name = "id_genre"),
            inverseJoinColumns = @JoinColumn(name = "id_book")
    )
    private List<Genre> genres;
}
