package com.easybank.card.controller;

import com.easybank.card.dto.ResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api",consumes = {MediaType.APPLICATION_JSON_VALUE})
public class CardController {


    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(){

        return null;
    }
}
