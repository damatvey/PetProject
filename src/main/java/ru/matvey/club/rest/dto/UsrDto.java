package ru.matvey.club.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsrDto {
    private String email;
    private String usrname;
    private String avatarUrl;
    private Long hearts;
    private Integer reviewsCount;
    private String favouriteGenre;
}
