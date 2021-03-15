package com.h2.caralho.h2caralho.domain;

import com.h2.caralho.h2caralho.domain.enums.EstadoPagamento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
public class PagamentoComBoleto extends Pagamento{
    private static final long serialVersionUID = 1L;
    private Date dataVencimento;
    private Date dataPagamento;

    public PagamentoComBoleto() {
    }

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }
}
