package ru.matvey.club.service;

import ru.matvey.club.rest.dto.EditUsrRequest;
import ru.matvey.club.rest.dto.NewUsrRequest;
import ru.matvey.club.rest.dto.UsrDto;

import java.util.List;

public interface UsrService {
    List<UsrDto> findAllUsrs();

    Long addNewUsr(NewUsrRequest request);

    UsrDto findById(Long id);
    //возвращаемый тип Long, потому что так написали в контроллере, а уже потом пишем метод в impl

    void deleteById(Long id);

    UsrDto edit(Long id, EditUsrRequest request);
}
