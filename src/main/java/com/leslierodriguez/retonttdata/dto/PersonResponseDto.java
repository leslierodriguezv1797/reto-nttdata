package com.leslierodriguez.retonttdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDto {
    private String gender;
    private List<String> name;
    private List<String> location;
    private  String email;
    private List<String> dob;
    private List<String> picture;
}
