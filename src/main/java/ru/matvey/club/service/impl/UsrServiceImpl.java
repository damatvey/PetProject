package ru.matvey.club.service.impl;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.internal.util.CollectionsUtils;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.stereotype.Service;
import ru.matvey.club.entity.Usr;
import ru.matvey.club.exception.exception.EmptyFieldsException;
import ru.matvey.club.exception.exception.NotFoundException;
import ru.matvey.club.exception.exception.WeakPasswordException;
import ru.matvey.club.repo.UsrRepo;
import ru.matvey.club.rest.dto.NewUsrRequest;
import ru.matvey.club.rest.dto.UsrDto;
import ru.matvey.club.service.UsrService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsrServiceImpl implements UsrService {

    private final UsrRepo usrRepo;

    @Override
    public List<UsrDto> findAllUsrs() {
        List<Usr> all = usrRepo.findAll();
        //запускаем стрим, чтобы перегнать из одного в другое
        //используем шаблон билдер, который описали в dto
        //билдер пригождается тогда, когда нужно не просто перенести поля,
        //но и проделать с ними какие-то операции
        return all.stream().map(usr -> UsrDto.builder()
                        .email(usr.getEmail())
                        .avatarUrl(usr.getAvatarUrl())
                        .usrname(usr.getUsrname())
                        .hearts(usr.getHearts())
                        .reviewsCount(CollectionsUtils.hasItems(usr.getReviews()) ? 0 : usr.getReviews().size())
                        //испльзую клас CollectionsUtils потому что так удобно, у него есть удобный метод
                        .build())
                //по идее тут еще должен быть любимый жанр пользователя но этот код не работате
                //.favouriteGenre(CollectionsUtils.hasItems(usr.getGenre()) ? 0 : usr.getGenre())
                .toList();

    }

    @Override
    public Long addNewUsr(NewUsrRequest request) {

        //Нам нужны проверки, что бы не класть в бд что попало
        //Есть крутой класс StringUtils
        if(!StringUtils.hasText(request.getEmail())
        || !StringUtils.hasText(request.getUsrname())
        || !StringUtils.hasText(request.getPassword())){
            throw new EmptyFieldsException("Некорректные поля в запросе");
        }

        if(request.getPassword().length() < 8 ){
            throw new WeakPasswordException("Пароль должен содержать более 8 символов");
        }

        //Еще можно проверить валидность почты

        Usr usr = new Usr();
        usr.setUsrname(request.getUsrname());
        usr.setPassword(request.getPassword());
        usr.setEmail(request.getEmail());
        usr.setCreatedAt(LocalDate.now());
        usr.setHearts(3L);

        usrRepo.saveAndFlush(usr);
        /*В чем разница междц save и saveAndFLush?
        saveAndFlash - помимо того, что сохраняет, так еще и обновляет его в этой переменной
         */
        return usr.getId();
    }

    @Override
    public UsrDto findById(Long id) {
        Optional<Usr> usrFromDb = usrRepo.findById(id);
        //В каждом репозитории есть метод findById

        if(usrFromDb.isEmpty()){
            throw new NotFoundException("Пользователь с id " + id + " не найден");
        }

        Usr usr = usrFromDb.get();

        return UsrDto.builder()
                .email(usr.getEmail())
                .avatarUrl(usr.getAvatarUrl())
                .usrname(usr.getUsrname())
                .hearts(usr.getHearts())
                .reviewsCount(usr.getReviews().size())
                .build();
    }
}
