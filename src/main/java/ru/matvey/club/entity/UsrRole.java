package ru.matvey.club.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "usr_role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UsrRole extends AbstractEntity {
    //нужно ли написать nullable = false если этого нет в бд? нужно ли написать это бд?
    @Column(name = "name", length = 50, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "many_usr_role_has_many_usr",
            joinColumns =  @JoinColumn(name = "id_usr"),
            inverseJoinColumns = @JoinColumn(name = "id_usr_role")
    )
    private List<Usr> usrs;
}
