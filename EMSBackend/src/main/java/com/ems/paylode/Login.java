package com.ems.paylode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Login {
    private  String username;
    private  String password;
}
