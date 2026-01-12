package com.david.project.rest.api.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserRequest {

    private Integer id;

    private String name;

    private String gender;

    private String phoneNumber;

    private String email;

    private String address;
}
