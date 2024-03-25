package ru.matvey.club.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usr")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usr extends AbstractEntity {
    //для id важно прописать не только аннотацию id, но и column
    @Column(name = "usrname", nullable = false, length = 100)
    private String usrname;
    @Column(name = "email", nullable = false, length = 150)
    private String email;
    @Column(name = "password", nullable = false, length = 150)
    private String password;
    @Column(name = "created_at", nullable = false)
    //исп. тип LocalDate потому что не важно время
    private LocalDate createdAt;
    @Column(name = "avatar_url", length = 250)
    private String avatarUrl;
    @Column(name = "hearts", nullable = false)
    private Long hearts;
    //внешние ключи это отдельная тема напишу потом
    //время пришло...


    //mappedBy = имя поля на другой стороне
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "usr", cascade = CascadeType.ALL)
    //один юзер может иметь много рецензий
    //значит исп. one to many
    private List<Review> reviews;
    //каскады - то, что мы хотим делать с рецензиями
    //(дочерними сущностями), когда мы изменяем, создаем,
    //сохраняем пользователя юзер

    @ManyToOne(fetch = FetchType.EAGER)
    //может быть много пользователей, у которых
    //одна кнгиа в процессе
    @JoinColumn(name = "book_in_progress_id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "favourite_genre_id")
    private Genre genre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usr")
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "many_usr_role_has_many_usr",
            //название буферной таблицы
            joinColumns = @JoinColumn(name = "id_usr_role"),
            //то, что берем от сущности с которой мы связываемся
            inverseJoinColumns = @JoinColumn(name = "id_usr")
            //колонки из пользователя
    )
    private List<UsrRole> usrRoles;
    //для роли все то же самое, только join и inverse
    //поменяются местами


}
