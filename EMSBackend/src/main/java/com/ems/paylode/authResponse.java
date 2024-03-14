package com.ems.paylode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class authResponse {
    private  String token;
    private String message;
}
