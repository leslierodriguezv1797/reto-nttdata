package com.leslierodriguez.retonttdata.service;

import com.leslierodriguez.retonttdata.dto.PersonResponseDto;

import java.util.List;

public interface IRandomUserService {
    List<PersonResponseDto> getpersonlist();

}
