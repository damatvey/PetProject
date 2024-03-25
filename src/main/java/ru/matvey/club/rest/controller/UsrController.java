package ru.matvey.club.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matvey.club.entity.Usr;
import ru.matvey.club.rest.dto.NewUsrRequest;
import ru.matvey.club.rest.dto.UsrDto;
import ru.matvey.club.service.UsrService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usr")
@RequiredArgsConstructor
public class UsrController {

    private final UsrService usrService;

    @GetMapping
    public ResponseEntity<List<UsrDto>> getAll(){
        return ResponseEntity.ok(usrService.findAllUsrs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsrDto> getById(@PathVariable Long id){
    //@PathVariable - нужня чтобы связать айди метода и айди в аннотации @GetMapping
        return ResponseEntity.ok(usrService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody NewUsrRequest request){
        //ResponseEntity<Long> - что вернет метод (обертка респонс энтити и айдишник)
        /*
        После "add(" идет способ передачи параметров с помощью аннотации, т.е.
        нам нужно как-то передать данный из NewUsrRequestDto и поймать их на стороне сервера -
        для этого используем аннотацию @RequestBody
        Структура: @RequsetBody "необходимая dto" request
        */
        return ResponseEntity.status(HttpStatus.CREATED).body(usrService.addNewUsr(request));
        //в .body передаем то что сделаем с реквестом в сервисе
    }
}
