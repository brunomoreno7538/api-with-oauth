package com.h2.caralho.h2caralho.domain;

import com.h2.caralho.h2caralho.domain.enums.EstadoPagamento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PagamentoComCartao extends Pagamento{
    private static final long serialVersionUID = 1L;
    private Integer numeroParcelas;

    public PagamentoComCartao(){

    }

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
        super(id, estado, pedido);
        this.numeroParcelas = numeroParcelas;
    }
}
