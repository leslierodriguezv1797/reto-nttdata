package com.leslierodriguez.retonttdata.controller;

import com.leslierodriguez.retonttdata.dto.PersonResponseDto;
import com.leslierodriguez.retonttdata.service.IRandomUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final IRandomUserService randomUserService;

    public PersonController(IRandomUserService randomUserService) {
        this.randomUserService = randomUserService;
    }

    @GetMapping
    public List<PersonResponseDto> getPersonList() {
        return randomUserService.getpersonlist();
    }
}
