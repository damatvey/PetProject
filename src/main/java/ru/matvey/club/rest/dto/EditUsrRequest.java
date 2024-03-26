package ru.matvey.club.rest.dto;

import lombok.Data;

@Data
public class EditUsrRequest {
    private String email;
    private String usrname;
    private String avatarUrl;
    private Long hearts;
}
