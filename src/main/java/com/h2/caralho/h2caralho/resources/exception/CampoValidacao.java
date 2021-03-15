package com.h2.caralho.h2caralho.resources.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CampoValidacao implements Serializable {
    public static final long serialVersionUID = 1L;

    private String campo;
    private String mensagem;

}
