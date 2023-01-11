package com.Jahedullah.ProjectV1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Integer mobilenumber;
    private String usertype;

}
