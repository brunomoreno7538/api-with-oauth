package com.h2.caralho.h2caralho.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String email;
    private String senha;
}
