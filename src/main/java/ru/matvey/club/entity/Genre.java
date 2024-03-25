package ru.matvey.club.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "genre")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Genre extends AbstractEntity {
    @Column(name = "name", length = 50, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
    private List<Usr> usrs;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "many_book_has_many_genre",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_genre")
    )
    private List<Book> books;
}
