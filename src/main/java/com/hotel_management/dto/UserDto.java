package com.hotel_management.dto;

import com.hotel_management.model.Role;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class UserDto {

    private Long id;

    private String surname;

    private String name;

    private String patronymic;

    private String username;

    private String password;

    private String description;

    private Role role;

    private UserDto createdBy;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

}
