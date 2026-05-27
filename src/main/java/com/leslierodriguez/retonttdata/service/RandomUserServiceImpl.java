package com.leslierodriguez.retonttdata.service;

import com.leslierodriguez.retonttdata.dto.PersonResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class RandomUserServiceImpl implements IRandomUserService{

    private final RestTemplate restTemplate;
    //constante url
    private static final String BASE_URL = "https://randomuser.me/api/";

    public RandomUserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<PersonResponseDto> getpersonlist() {
        Map<String, Object> response = restTemplate.getForObject(BASE_URL + "?results=10", Map.class);
        List<Map<String, Object>> results = getList(response, "results");

        return results.stream()
                .map(this::mapToPersonResponseDto)
                .toList();
    }

    private PersonResponseDto mapToPersonResponseDto(Map<String, Object> person) {
        Map<String, Object> name = getMap(person, "name");
        Map<String, Object> location = getMap(person, "location");
        Map<String, Object> street = getMap(location, "street");
        Map<String, Object> dob = getMap(person, "dob");
        Map<String, Object> picture = getMap(person, "picture");

        PersonResponseDto personResponseDto = new PersonResponseDto();
        //Genero
        personResponseDto.setGender(value(person.get("gender")));
        //Nombre
        personResponseDto.setName(List.of(
                value(name.get("title")),
                value(name.get("first")),
                value(name.get("last"))
        ));
        //ubicacion
        personResponseDto.setLocation(List.of(
                value(street.get("number")),
                value(street.get("name")),
                value(location.get("city")),
                value(location.get("state")),
                value(location.get("country")),
                value(location.get("postcode"))
        ));
        //correo
        personResponseDto.setEmail(value(person.get("email")));
        //fecha de nacimiento
        personResponseDto.setDob(List.of(
                value(dob.get("date")),
                value(dob.get("age"))
        ));
        //fotografia
        personResponseDto.setPicture(List.of(
                value(picture.get("large")),
                value(picture.get("medium")),
                value(picture.get("thumbnail"))
        ));

        return personResponseDto;
    }

    private Map<String, Object> getMap(Map<String, Object> source, String key) {
        Object value = source.get(key);
        return value instanceof Map<?, ?> map ? (Map<String, Object>) map : Map.of();
    }

    private List<Map<String, Object>> getList(Map<String, Object> source, String key) {
        if (source == null) {
            return new ArrayList<>();
        }

        Object value = source.get(key);
        return value instanceof List<?> list
                ? list.stream()
                .filter(Map.class::isInstance)
                .map(item -> (Map<String, Object>) item)
                .toList()
                : new ArrayList<>();
    }

    private String value(Object value) {
        return Objects.toString(value, "");
    }

}
