package com.likelion.seminar.dto;

import lombok.Getter;

@Getter
public class UserUpdateRequest {

    private String name;

    private String email;

    private String password;

    private Integer age;

}
