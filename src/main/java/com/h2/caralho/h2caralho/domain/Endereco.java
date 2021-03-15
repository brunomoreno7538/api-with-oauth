package com.h2.caralho.h2caralho.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class  Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Integer id;
    @Getter @Setter private String logradouro;
    @Getter @Setter private String numero;
    @Getter @Setter private String complemento;
    @Getter @Setter private String bairro;
    @Getter @Setter private String cep;

    @JsonBackReference
    @ManyToOne
    @Getter @Setter private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    @Getter @Setter private Cidade cidade;

    public Endereco(){

    }

    public Endereco(Integer id, String logradouro, String numero, String complemento, String bairro, String cep, Cliente cliente, Cidade cidade) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cliente = cliente;
        this.cidade = cidade;
    }
}
