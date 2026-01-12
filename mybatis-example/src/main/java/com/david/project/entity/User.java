package com.david.project.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private Integer id;

    private String name;

    private String gender;

    private String phoneNumber;

    private String email;

    private String address;
}
