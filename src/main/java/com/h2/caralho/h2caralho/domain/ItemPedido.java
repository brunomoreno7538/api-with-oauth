package com.h2.caralho.h2caralho.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    ItemPedidoPK id = new ItemPedidoPK();
    private Double preco;
    private Integer qntd;
    private Double desconto;

    public ItemPedido(){

    }

    public ItemPedido(Pedido pedido, Produto produto, Double preco, Integer qntd, Double desconto) {
        id.setPedido(pedido);
        id.setProduto(produto);
        this.preco = preco;
        this.qntd = qntd;
        this.desconto = desconto;
    }

    @JsonIgnore
    public Pedido getPedido(){
        return id.getPedido();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @JsonIgnore
    public Produto getProduto(){
        return id.getProduto();


    }
}
