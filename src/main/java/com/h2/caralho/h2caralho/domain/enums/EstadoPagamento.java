package com.h2.caralho.h2caralho.domain.enums;

import com.h2.caralho.h2caralho.domain.Estado;
import lombok.Getter;

public enum EstadoPagamento {
    PENDETE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");


    @Getter private Integer cod;
    @Getter private String descricao;

    private EstadoPagamento(int cod, String descricao){
        this.cod = cod;
        this.descricao = descricao;
    }

    public static EstadoPagamento toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for (EstadoPagamento x : EstadoPagamento.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Código Inválido: " + cod);
    }
}
