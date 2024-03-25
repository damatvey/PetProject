package ru.matvey.club.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewUsrRequest {
    private String email;
    private String usrname;
    private String password;
}
