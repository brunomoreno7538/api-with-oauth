package com.h2.caralho.h2caralho.dto;

import com.h2.caralho.h2caralho.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCriacaoDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private String nome;
    private String email;
    private String cpfCnpj;
    private Integer tipo;
    @NotEmpty
    private String senha;

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private Integer cidadeId;


    private String telefone1;
}



