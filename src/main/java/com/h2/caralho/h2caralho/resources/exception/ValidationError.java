package com.h2.caralho.h2caralho.resources.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StandardError{

    private List<CampoValidacao> erros = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public void addError(String nomeCampo, String mensagem){
        erros.add(new CampoValidacao(nomeCampo, mensagem));
    }
}
